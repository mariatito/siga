/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.sysmaqv.web.spring.sigaa.consultas;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import spring.sysmaqv.domain.Estudiante;
import spring.sysmaqv.domain.Inscripcion;
import spring.sysmaqv.domain.Personal;
import spring.sysmaqv.domain.logic.SigaaInterface;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.util.WebUtils;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * Created on : 02-jul-2010, 22:42:07
 * @author MARCO ANTONIO QUENTA VELASCO
 */
public class InformacionEstudiantesCurso implements Controller {

    private SigaaInterface sigaa;
    private String ecxessTimeView;
    private String perfectView;

    public void setSigaa(SigaaInterface sigaa) {
        this.sigaa = sigaa;
    }

    public void setEcxessTimeView(String ecxessTimeView) {
        this.ecxessTimeView = ecxessTimeView;
    }

    public void setPerfectView(String perfectView) {
        this.perfectView = perfectView;
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String url = request.getServletPath();
        Personal personal = (Personal) WebUtils.getSessionAttribute(request, "___personal");
        if (personal != null) {
            if (!this.sigaa.personalAutorizado(personal, url)) {
                return new ModelAndView("seguridad/Error", null);
            }
            Map retorno = new HashMap();
            String id_gestion = request.getParameter("id_gestion");
            if (id_gestion != null) {
                retorno.put("id_gestion", id_gestion);
                String id_curso = request.getParameter("id_curso");
                if (id_curso != null) {
                    Inscripcion inscripcion = new Inscripcion();
                    inscripcion.setId_gestion(Integer.parseInt(id_gestion));
                    inscripcion.setId_curso(id_curso);
                    List estudiantes = this.sigaa.getEstudiantesByIdCursoAndIdGestion(inscripcion);
                    retorno.put("curso", ((Estudiante) estudiantes.get(0)));
                    retorno.put("estudiantes", estudiantes);
                    if (request.getParameter("imprimirPdf") != null) {
                        String pdf = this.reporteResultado(estudiantes, id_gestion, (Estudiante) estudiantes.get(0));
                        response.sendRedirect("documentos/consultas/estudiantes/" + pdf);
                    }
                    if (request.getParameter("imprimirExcel") != null) {
                        String excel = this.reporteResultadoExcel(estudiantes, id_gestion, (Estudiante) estudiantes.get(0));
                        response.sendRedirect("documentos/consultas/reportes-excel/kardex/" + excel);
                    }
                    if (request.getParameter("imprimirExcelCo") != null) {
                        String excel = this.reporteResultadoExcelCo(estudiantes, id_gestion, (Estudiante) estudiantes.get(0));
                        response.sendRedirect("documentos/consultas/reportes-excel/kardex/" + excel);
                    }

                    /*emails*/
                    String correos = "";
                    for (int j = 0; j < estudiantes.size(); j++) {
                        Estudiante estudiante = (Estudiante) estudiantes.get(j);
                        if (estudiante.getFamilia().getE_mail().length() > 5) {
                            correos = correos + estudiante.getFamilia().getE_mail() + ", ";
                        }
                        if (estudiante.getFamilia().getE_mailRP().length() > 5) {
                            correos = correos + estudiante.getFamilia().getE_mailRP() + ", ";
                        }
                    }
                    correos = correos.substring(0, correos.length() - 1);
                    retorno.put("correos", correos);
                    return new ModelAndView(this.perfectView, retorno);
                }
                retorno.put("cursos", this.sigaa.getListaCursos(Integer.parseInt(id_gestion)));
                return new ModelAndView(this.perfectView, retorno);
            }
            retorno.put("gestion", this.sigaa.getGestionActivo());
            return new ModelAndView(this.perfectView, retorno);
        } else {
            return new ModelAndView(this.ecxessTimeView, "url", url);
        }
    }

    private String formatearFecha(Date fecha) {
        String nuevo = new String();
        SimpleDateFormat formato = new SimpleDateFormat("EEEE, d 'de' MMMM 'de' yyyy");
        nuevo = formato.format(fecha);
        return nuevo;
    }

    private String formatFecha(Date fecha) {
        String nuevo = new String();
        SimpleDateFormat formato = new SimpleDateFormat("d-MMMM-yyyy");
        nuevo = formato.format(fecha);
        return nuevo;
    }

    private String reporteResultado(List estudiantes, String id_gestion, Estudiante est) {
        String dir = System.getenv("SIGAA_HOME1") + "/documentos/consultas/estudiantes/";
        String archivo = "DetalleEstudiantesByCurso(" + this.formatFecha(new Date()) + ").pdf";
        Font font8 = FontFactory.getFont(FontFactory.HELVETICA, 8);
        Font font7 = FontFactory.getFont(FontFactory.HELVETICA, 7);
        Font font8b = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8);

        Document document = new Document(PageSize.LETTER);
        try {

            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dir + archivo));
            document.open();
            Image png = Image.getInstance(System.getenv("SIGAA_HOME1") + "/imagenes/iconos_sigaa/sigaa_oficial.png");
            png.setAbsolutePosition(470, 740);
            png.scalePercent(25);
            document.add(png);

            document.add(new Phrase("COLEGIO \"SANTA TERESA\"", font8b));
            document.add(Chunk.NEWLINE);
            document.add(new Phrase("GESTION " + id_gestion, font8b));
//            document.add(Chunk.NEWLINE);
//            document.add(new Phrase("La Paz - Bolivia", font8b));
            document.add(Chunk.NEWLINE);
            Paragraph p = new Paragraph(new Phrase("INFORMACIÓN DETALLADA DE ESTUDIANTES"));
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);
            p = new Paragraph(new Phrase("(Curso " + est.getCurso() + " de " + est.getCiclo() + ")", font8));
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);
            p = new Paragraph(new Phrase("\n", font8));
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);

            float[] columnDefinitionSize = {5F, 40F, 55F};
            PdfPTable table = new PdfPTable(columnDefinitionSize);
            table.getDefaultCell().setBorder(0);
            table.setWidthPercentage(100);

            table.getDefaultCell().setGrayFill(0.5f);
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(new Phrase("No.", font8b));
            table.addCell(new Phrase("ESTUDIANTE/S", font8b));
            table.addCell(new Phrase("TUTOR/ES", font8b));
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            table.setHeaderRows(1);
            PdfPCell cell = null;
            for (int i = 0; i < estudiantes.size(); i++) {
                Estudiante estudiante = (Estudiante) estudiantes.get(i);
                if ((i + 1) % 2 == 0) {
                    table.getDefaultCell().setGrayFill(1);
                } else {
                    table.getDefaultCell().setGrayFill(0.9f);
                }
                table.addCell(new Phrase(Integer.toString(i + 1), font7));

                float[] columnaDef = {25F, 75F};
                PdfPTable table1 = new PdfPTable(columnaDef);
                table1.getDefaultCell().setBorder(0);
                table1.setWidthPercentage(100);
                table1.addCell(new Phrase("Código: ", font7));
                table1.addCell(new Phrase(Integer.toString(estudiante.getCodigo()), font7));
                table1.addCell(new Phrase("Nombre/s: ", font7));
                table1.addCell(new Phrase(estudiante.getNombres(), font7));
                table1.addCell(new Phrase("Paterno: ", font7));
                table1.addCell(new Phrase(estudiante.getPaterno(), font7));
                table1.addCell(new Phrase("Materno: ", font7));
                table1.addCell(new Phrase(estudiante.getMaterno(), font7));
                table1.addCell(new Phrase("Sexo: ", font7));
                if (estudiante.getId_sexo().equals("A")) {
                    table1.addCell(new Phrase(" ", font7));
                } else if (estudiante.getId_sexo().equals("F")) {
                    table1.addCell(new Phrase("FEMENINO", font7));
                } else if (estudiante.getId_sexo().equals("M")) {
                    table1.addCell(new Phrase("MASCULINO", font7));
                }
                table1.addCell(new Phrase("Fecha nac.: ", font7));
                table1.addCell(new Phrase(estudiante.getSfecha_nacimiento(), font7));
                table1.addCell(new Phrase("Curso: ", font7));
                table1.addCell(new Phrase(estudiante.getCurso() + " de " + estudiante.getCiclo(), font7));
                table.addCell(table1);
                float[] columnaDefi = {12F, 88F};
                PdfPTable table2 = new PdfPTable(columnaDefi);
                table2.getDefaultCell().setBorder(0);
                table2.setWidthPercentage(100);
                table2.addCell(new Phrase("Código: ", font7));
                table2.addCell(new Phrase(estudiante.getFamilia().getId_familia(), font7));
                if (estudiante.getFamilia().getNombre_apellidos_tutor_1() != null && !estudiante.getFamilia().getNombre_apellidos_tutor_1().equals(" ") && !estudiante.getFamilia().getNombre_apellidos_tutor_1().equals("  ")) {
                    table2.addCell(new Phrase("Tutor1: ", font7));
                    if (estudiante.getFamilia().getCi_tutor_1().equals(estudiante.getFamilia().getCi_resp_pago())) {
                        table2.addCell(new Phrase(estudiante.getFamilia().getNombre_apellidos_tutor_1() + "(RP);   ci: " + estudiante.getFamilia().getCi_tutor_1(), font7));
                    } else {
                        table2.addCell(new Phrase(estudiante.getFamilia().getNombre_apellidos_tutor_1() + ";  ci: " + estudiante.getFamilia().getCi_tutor_1(), font7));
                    }
                }
                if (estudiante.getFamilia().getNombre_apellidos_tutor_2() != null && !estudiante.getFamilia().getNombre_apellidos_tutor_2().equals(" ") && !estudiante.getFamilia().getNombre_apellidos_tutor_2().equals("  ")) {
                    table2.addCell(new Phrase("Tutor2: ", font7));
                    if (estudiante.getFamilia().getCi_tutor_2().equals(estudiante.getFamilia().getCi_resp_pago())) {
                        table2.addCell(new Phrase(estudiante.getFamilia().getNombre_apellidos_tutor_2() + "(RP);  ci: " + estudiante.getFamilia().getCi_tutor_2(), font7));
                    } else {
                        table2.addCell(new Phrase(estudiante.getFamilia().getNombre_apellidos_tutor_2() + ";  ci: " + estudiante.getFamilia().getCi_tutor_2(), font7));
                    }
                }
                if (estudiante.getFamilia().getNombre_apellidos_tutor_3() != null && !estudiante.getFamilia().getNombre_apellidos_tutor_3().equals(" ") && !estudiante.getFamilia().getNombre_apellidos_tutor_3().equals("  ")) {
                    table2.addCell(new Phrase("Tutor3: ", font7));
                    if (estudiante.getFamilia().getCi_tutor_3().equals(estudiante.getFamilia().getCi_resp_pago())) {
                        table2.addCell(new Phrase(estudiante.getFamilia().getNombre_apellidos_tutor_3() + "(RP);  ci: " + estudiante.getFamilia().getCi_tutor_3(), font7));
                    } else {
                        table2.addCell(new Phrase(estudiante.getFamilia().getNombre_apellidos_tutor_3() + ";  ci: " + estudiante.getFamilia().getCi_tutor_3(), font7));
                    }
                }
                table2.addCell(new Phrase("Dir1: ", font7));
                table2.addCell(new Phrase(estudiante.getFamilia().getDireccion_1(), font7));
                table2.addCell(new Phrase("Dir2: ", font7));
                table2.addCell(new Phrase(estudiante.getFamilia().getDireccion_2(), font7));
                table2.addCell(new Phrase("Telef1: ", font7));
                table2.addCell(new Phrase(estudiante.getFamilia().getTelefono_1(), font7));
                table2.addCell(new Phrase("Telef2: ", font7));
                table2.addCell(new Phrase(estudiante.getFamilia().getTelefono_2(), font7));
                table2.addCell(new Phrase("Otros: ", font7));
                table2.addCell(new Phrase(estudiante.getFamilia().getTelefonos(), font7));
                table2.addCell(new Phrase("Mail1: ", font7));
                table2.addCell(new Phrase(estudiante.getFamilia().getE_mail(), font7));
                table2.addCell(new Phrase("Mail2: ", font7));
                table2.addCell(new Phrase(estudiante.getFamilia().getE_mailRP(), font7));
                table2.addCell(new Phrase("Obs: ", font7));
                table2.addCell(new Phrase(estudiante.getFamilia().getObservacion(), font7));
                table.addCell(table2);
            }
            document.add(table);
            document.add(Chunk.NEWLINE);
            p = new Paragraph(new Phrase(this.formatearFecha(new Date()), font8));
            p.setAlignment(Element.ALIGN_RIGHT);
            document.add(p);
            document.add(Chunk.NEWLINE);
            p = new Paragraph(new Phrase("SIGAA Sistema Integrado de Gestión Académico - Administrativa " + id_gestion, font8));
            document.add(p);

        } catch (Exception de) {
            de.printStackTrace();
        }

        document.close();
        return archivo;
    }

    private String reporteResultadoExcel(List estudiantes, String id_gestion, Estudiante est) {
        String dirDestino = System.getenv("SIGAA_HOME1") + "/documentos/consultas/reportes-excel/kardex/";
        String archivo = "Kardex_(" + this.formatFecha(new Date()) + ").xls";
        String dirPlantilla = "";

        dirPlantilla = System.getenv("SIGAA_HOME1") + "/documentos/plantillas_excel/consultas_kardex.xls";
        try {
            Workbook plantilla = Workbook.getWorkbook(new File(dirPlantilla));
            WritableWorkbook workbook = Workbook.createWorkbook(new File(dirDestino + archivo), plantilla);
            WritableSheet sheet = workbook.getSheet("LISTA");
            WritableFont arial_7 = new WritableFont(WritableFont.ARIAL, 9);
            WritableCellFormat arial7 = new WritableCellFormat(arial_7);
            WritableFont arial_8 = new WritableFont(WritableFont.ARIAL, 10);
            WritableCellFormat arial8 = new WritableCellFormat(arial_8);
            sheet.addCell(new Label(3, 6, est.getCurso() + " de " + est.getCiclo(), arial8));
            sheet.addCell(new Label(3, 7, this.formatearFecha(new Date()), arial8));

            int i = 0;
            for (i = 0; i < estudiantes.size(); i++) {
                Estudiante estudiante = (Estudiante) estudiantes.get(i);
                sheet.addCell(new jxl.write.Number(1, i + 11, (i + 1), arial7));
                sheet.addCell(new jxl.write.Number(2, i + 11, estudiante.getCodigo(), arial7));
                sheet.addCell(new Label(3, i + 11, estudiante.getPaterno() + " " + estudiante.getMaterno() + ", " + estudiante.getNombres(), arial7));
                sheet.addCell(new Label(4, i + 11, estudiante.getId_sexo(), arial7));
                sheet.addCell(new Label(5, i + 11, estudiante.getSfecha_nacimiento(), arial7));
                sheet.addCell(new Label(6, i + 11, estudiante.getFamilia().getTelefono_1() + ", " + estudiante.getFamilia().getTelefono_2() + ", " + estudiante.getFamilia().getTelefonos(), arial7));
                sheet.addCell(new Label(7, i + 11, estudiante.getFamilia().getNombre_apellidos_tutor_1(), arial7));
                sheet.addCell(new Label(8, i + 11, estudiante.getFamilia().getNombre_apellidos_tutor_2(), arial7));
                sheet.addCell(new Label(9, i + 11, estudiante.getFamilia().getNombre_apellidos_tutor_3(), arial7));
                sheet.addCell(new Label(10, i + 11, estudiante.getFamilia().getDireccion_1(), arial7));
                sheet.addCell(new Label(11, i + 11, estudiante.getFamilia().getE_mail() + ", " + estudiante.getFamilia().getE_mailRP(), arial7));
                sheet.addCell(new Label(12, i + 11, estudiante.getFamilia().getObservacion(), arial7));
            }
            workbook.write();
            workbook.close();
        } catch (Exception de) {
            de.printStackTrace();
        }
        return archivo;
    }

    private String reporteResultadoExcelCo(List estudiantes, String id_gestion, Estudiante est) {
        String dirDestino = System.getenv("SIGAA_HOME1") + "/documentos/consultas/reportes-excel/kardex/";
        String archivo = "Kardex_(" + this.formatFecha(new Date()) + ").xls";
        String dirPlantilla = "";

        dirPlantilla = System.getenv("SIGAA_HOME1") + "/documentos/plantillas_excel/consultas_correos.xls";
        try {
            Workbook plantilla = Workbook.getWorkbook(new File(dirPlantilla));
            WritableWorkbook workbook = Workbook.createWorkbook(new File(dirDestino + archivo), plantilla);
            WritableSheet sheet = workbook.getSheet("LISTA");
            WritableFont arial_7 = new WritableFont(WritableFont.ARIAL, 9);
            WritableCellFormat arial7 = new WritableCellFormat(arial_7);
            WritableFont arial_8 = new WritableFont(WritableFont.ARIAL, 10);
            WritableCellFormat arial8 = new WritableCellFormat(arial_8);
            sheet.addCell(new Label(3, 6, est.getCurso() + " de " + est.getCiclo(), arial8));
            sheet.addCell(new Label(3, 7, this.formatearFecha(new Date()), arial8));

            int i = 0;
            for (i = 0; i < estudiantes.size(); i++) {
                Estudiante estudiante = (Estudiante) estudiantes.get(i);
                sheet.addCell(new jxl.write.Number(1, i + 11, (i + 1), arial7));
                sheet.addCell(new jxl.write.Number(2, i + 11, estudiante.getCodigo(), arial7));
                sheet.addCell(new Label(3, i + 11, estudiante.getPaterno() + " " + estudiante.getMaterno() + ", " + estudiante.getNombres(), arial7));

//                sheet.addCell(new Label(5, i + 11, estudiante.getSfecha_nacimiento(), arial7));
//                sheet.addCell(new Label(6, i + 11, estudiante.getFamilia().getTelefono_1()+", "+estudiante.getFamilia().getTelefono_2()+", "+estudiante.getFamilia().getTelefonos(), arial7));
//                sheet.addCell(new Label(7, i + 11, estudiante.getFamilia().getNombre_apellidos_tutor_1(), arial7));
//                sheet.addCell(new Label(8, i + 11, estudiante.getFamilia().getNombre_apellidos_tutor_2(), arial7));
//                sheet.addCell(new Label(9, i + 11, estudiante.getFamilia().getNombre_apellidos_tutor_3(), arial7));
//                sheet.addCell(new Label(10, i + 11, estudiante.getFamilia().getDireccion_1(), arial7));
            }
            int j = 0;
            String correos = "";
            for (j = 0; j < estudiantes.size(); j++) {
                Estudiante estudiante = (Estudiante) estudiantes.get(j);
                if (estudiante.getFamilia().getE_mail().length() > 5) {
                    correos = correos + estudiante.getFamilia().getE_mail() + ",";
                }
                if (estudiante.getFamilia().getE_mailRP().length() > 5) {
                    correos = correos + estudiante.getFamilia().getE_mailRP() + ",";
                }
            }
            correos = correos.substring(0, correos.length() - 1);
            sheet.addCell(new Label(4, 11, correos, arial7));
//                sheet.addCell(new Label(12, i + 11, estudiante.getFamilia().getObservacion(), arial7));

            workbook.write();
            workbook.close();
        } catch (Exception de) {
            de.printStackTrace();
        }
        return archivo;
    }
}
