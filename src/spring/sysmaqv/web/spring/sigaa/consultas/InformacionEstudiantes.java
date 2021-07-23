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
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import spring.sysmaqv.domain.Estudiante;
import spring.sysmaqv.domain.Familia;
import spring.sysmaqv.domain.Gestion;
import spring.sysmaqv.domain.Inscripcion;
import spring.sysmaqv.domain.Personal;
import spring.sysmaqv.domain.logic.SigaaInterface;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.util.WebUtils;

/**
 * Created on : 02-jul-2010, 14:43:34
 * @author MARCO ANTONIO QUENTA VELASCO
 */
public class InformacionEstudiantes implements Controller {

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
                retorno.put("search", "_search");
                retorno.put("id_gestion", id_gestion);
                String buscar = request.getParameter("buscar");
                if (buscar != null) {
                    Estudiante estudiante = new Estudiante();
                    estudiante.setId_gestion(Integer.parseInt(id_gestion));
                    estudiante.setNombres(buscar);
                    List estudiantes = this.sigaa.getBuscarEstudianteByIdGestion(estudiante);
                    retorno.put("buscar", buscar);
                    retorno.put("estudiantes", estudiantes);
                    if (request.getParameter("imprimirPdf") != null) {
                        String pdf = this.reporteResultado(estudiantes, id_gestion);
                        response.sendRedirect("documentos/consultas/estudiantes/" + pdf);
                    }
                }
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

    private String reporteResultado(List estudiantes, String id_gestion) {
        String dir = System.getenv("SIGAA_HOME") + "/documentos/consultas/estudiantes/";
        String archivo = "DetalleEstudiantes(" + this.formatFecha(new Date()) + ").pdf";
        Font font8 = FontFactory.getFont(FontFactory.HELVETICA, 8);
        Font font7 = FontFactory.getFont(FontFactory.HELVETICA, 7);
        Font font8b = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8);

        Document document = new Document(PageSize.LETTER);
        try {

            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dir + archivo));
            document.open();
            Image png = Image.getInstance(System.getenv("SIGAA_HOME") + "/imagenes/iconos_sigaa/sigaa_oficial.png");
            png.setAbsolutePosition(470, 740);
            png.scalePercent(25);
            document.add(png);

            document.add(new Phrase("COLEGIO \"SANTA TERESA\"", font8b));
            document.add(Chunk.NEWLINE);
            document.add(new Phrase("GESTION " + id_gestion, font8b));
            document.add(Chunk.NEWLINE);
            document.add(new Phrase("La Paz - Bolivia", font8b));
            document.add(Chunk.NEWLINE);
            Paragraph p = new Paragraph(new Phrase("INFORMACIÓN DETALLADA DE ESTUDIANTES"));
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
                }else if(estudiante.getId_sexo().equals("F")){
                    table1.addCell(new Phrase("FEMENINO", font7));
                }else if(estudiante.getId_sexo().equals("M")){
                    table1.addCell(new Phrase("MASCULINO", font7));
                }
                table1.addCell(new Phrase("Fecha nac.: ", font7));
                table1.addCell(new Phrase(estudiante.getSfecha_nacimiento(), font7));
                table1.addCell(new Phrase("Curso: ", font7));
                table1.addCell(new Phrase(estudiante.getCurso() + " de " + estudiante.getCiclo(), font7));
                table1.addCell(new Phrase("Estado: ", font7));
                if (estudiante.getId_estado().equals("A")) {
                table1.addCell(new Phrase("ACTIVO", font7));
                }else if(estudiante.getId_estado().equals("R")){
                table1.addCell(new Phrase("RETIRADO", font7));
                }else if(estudiante.getId_estado().equals("NI")){
                table1.addCell(new Phrase("NO INCORPORADO", font7));
                }
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
}
