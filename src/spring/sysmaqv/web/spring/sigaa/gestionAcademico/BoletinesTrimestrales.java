/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.sysmaqv.web.spring.sigaa.gestionAcademico;

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
import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import spring.sysmaqv.domain.Conducta;
import spring.sysmaqv.domain.Curso;
import spring.sysmaqv.domain.Estudiante;
import spring.sysmaqv.domain.Evaluacion;
import spring.sysmaqv.domain.Falta;
import spring.sysmaqv.domain.Gestion;
import spring.sysmaqv.domain.Materia;
import spring.sysmaqv.domain.Personal;
import spring.sysmaqv.domain.logic.SigaaInterface;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.util.WebUtils;
import spring.sysmaqv.domain.PeriodoCurso;

/**
 * @ Company : M&M
 * @ Author : Marco Antonio Quenta Velasco
 * @ Gestion : 2009
 * @ La Paz - Bolivia
 */
public class BoletinesTrimestrales implements Controller {

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
                    String opcion = request.getParameter("opcion");
                    Curso curso = new Curso();
                    curso.setId_curso(id_curso);
                    curso.setId_gestion(Integer.parseInt(id_gestion));
                    curso = this.sigaa.getCursoByIdCursoAndIdGestion(curso);
                    if (opcion != null) {
                        if (opcion.equals("BoletinEstudiante")) {
                            String id_estudiante = request.getParameter("id_estudiante");
                            curso.setId_estudiante(id_estudiante);
                            curso.setId_curso(id_curso);
                            curso.setId_gestion(Integer.parseInt(id_gestion));
                            curso = this.sigaa.getCursoByIdCursoAndIdEstudiantesAndIdGestion(curso);
                            retorno.put("curso", curso);
                            Gestion gestion = (Gestion) this.sigaa.getBuscarGestion(Integer.parseInt(id_gestion)).get(0);
                            retorno.put("gestion", gestion);
                            curso.setGestion(gestion);
                            int tRetraso = 0;
                            int tFalta = 0;
                            for (int i = 0; i < curso.getFaltas().size(); i++) {
                                Falta falta = (Falta) curso.getFaltas().get(i);
                                tRetraso = tRetraso + falta.getRetraso();
                                tFalta = tFalta + falta.getFcl() + falta.getFsl();
                            }
                            retorno.put("tRetraso", tRetraso);
                            retorno.put("tFalta", tFalta);
                            Estudiante estudiante = new Estudiante();
                            estudiante.setId_estudiante(id_estudiante);
                            estudiante.setId_gestion(Integer.parseInt(id_gestion));
                            estudiante.setId_curso(id_curso);
                            estudiante = this.sigaa.getDatosActualesEstudianteByIdEstudianteIdCursoIdGestion(estudiante);
                            retorno.put("estudiante", estudiante);
                            /*18-05-2013*/
                            Curso cur = new Curso();
                            cur.setId_curso(id_curso);
                            cur.setId_gestion(Integer.parseInt(id_gestion));
                            PeriodoCurso per_curso = this.sigaa.getPeriodoByIdcursoAndGestion(cur);
                            /*calculo de los parciales correspondientes al curso*/
                            int parciales = per_curso.getIdperiodo() - (cur.getId_gestion() * 10);
                            retorno.put("parciales", parciales);

                            retorno.put("boletin", this.BoletaTrimestralPdf(curso, estudiante, parciales));

                            return new ModelAndView(this.perfectView + "BoletinEstudiante", retorno);
                        }
                        if (opcion.equals("_imprimeExcel")) {
                            String excel = this.estudiantesCursoExcel(curso);
                            response.sendRedirect("documentos/nominas/excel/" + excel);
                            retorno.put("file", "documentos/docentes/nominas/excel/" + excel);
                        }
                        if (opcion.equals("_boletines")) {
                            Curso cur = new Curso();
                            cur.setId_curso(id_curso);
                            cur.setId_gestion(Integer.parseInt(id_gestion));
                            PeriodoCurso per_curso = this.sigaa.getPeriodoByIdcursoAndGestion(cur);
                            /*calculo de los parciales correspondientes al curso*/
                            int parciales = per_curso.getIdperiodo() - (cur.getId_gestion() * 10);
                            String pdf = this.boletinesCursoPdf(curso, id_gestion, parciales);
                            response.sendRedirect("documentos/boletines/" + pdf);
                        }
                    }
                    retorno.put("curso", curso);
                    retorno.put("pdf", this.estudiantesCursoPdf(curso));
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
        SimpleDateFormat formato = new SimpleDateFormat("d 'de' MMMM 'de' yyyy");
        nuevo = formato.format(fecha);
        return nuevo;
    }

    private String formatoFechaNom(Date fecha) {
        String nuevo = new String();
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        nuevo = formato.format(fecha);
        return nuevo;
    }

    private String estudiantesCursoPdf(Curso curso) {
        String dir = System.getenv("SIGAA_HOME1") + "/documentos/nominas/pdf/";
        String archivo = "Lista " + curso.getCurso() + " de " + curso.getCiclo() + " (" + this.formatoFechaNom(new Date()) + ").pdf";
        Font font8 = FontFactory.getFont(FontFactory.HELVETICA, 7);
        Font font8b = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8);

        Document document = new Document(PageSize.LETTER);
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dir + archivo));
            float width = document.getPageSize().getWidth();
            document.open();
            Image png = Image.getInstance(System.getenv("SIGAA_HOME1") + "/imagenes/iconos_sigaa/logo.png");
            Image pngfirma = Image.getInstance(System.getenv("SIGAA_HOME1") + "/imagenes/iconos_sigaa/mily.png");
            
           /* png.setAbsolutePosition(550, 945);
            png.scalePercent(25);*/
            pngfirma.setAbsolutePosition(550, 945);
            pngfirma.scalePercent(25);
           // document.add(pngfirma);
            document.add(Chunk.NEWLINE);
            document.add(new Phrase("COLEGIO \"SANTA TERESA\"", font8b));
            document.add(Chunk.NEWLINE);
            document.add(new Phrase("GESTION 2010", font8b));
            document.add(Chunk.NEWLINE);
            document.add(new Phrase("La Paz - Bolivia", font8b));
            document.add(Chunk.NEWLINE);
            Paragraph p = new Paragraph(new Phrase("LISTA DE ESTUDIANTES"));
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);
            p = new Paragraph(new Phrase("Curso " + curso.getCurso() + " de " + curso.getCiclo(), font8));
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);
            document.add(Chunk.NEWLINE);
            PdfPCell cell = null;

            float[] columnDefinitionSize = {5F, 10F, 40F, 45F};
            PdfPTable table = new PdfPTable(columnDefinitionSize);
            table.getDefaultCell().setBorder(0);
            table.getDefaultCell().setPadding(3);
            table.setHorizontalAlignment(0);
            table.setWidthPercentage(100);

            table.getDefaultCell().setGrayFill(0.9f);
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(new Phrase("No.", font8));
            table.addCell(new Phrase("Código", font8));
            table.addCell(new Phrase("Apellidos y nombres", font8));
            table.addCell(new Phrase(" ", font8));
            table.getDefaultCell().setGrayFill(1);
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            table.setHeaderRows(1);
            for (int i = 0; i < curso.getEstudiantes().size(); i++) {
                Estudiante estudiante = (Estudiante) curso.getEstudiantes().get(i);
                table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(new Phrase(Integer.toString(i + 1), font8));
                table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(new Phrase(Integer.toString(estudiante.getCodigo()), font8));
                table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(new Phrase(estudiante.getPaterno() + " " + estudiante.getMaterno() + " " + estudiante.getNombres(), font8));
                table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(new Phrase("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _", font8));
            }
            document.add(table);
            p = new Paragraph(new Phrase(this.formatearFecha(new Date()), font8));
            p.setAlignment(Element.ALIGN_RIGHT);
            document.add(p);
//            PdfContentByte cb = writer.getDirectContent();
//            Barcode128 code128 = new Barcode128();
//            code128.setCode(curso.getId_curso() + "-" + materia.getId_materia());
//            Image image128 = code128.createImageWithBarcode(cb, null, null);
//            p = new Paragraph(new Phrase(new Chunk(image128, 0, 0)));
//            document.add(p);
            p = new Paragraph(new Phrase("SIGAA Sistema Integrado de Gestión Académico - Administrativa 2010", font8));
            document.add(p);

        } catch (Exception de) {
            de.printStackTrace();
        }

        document.close();
        return archivo;
    }

    private String estudiantesCursoExcel(Curso curso) {
        Date FechaActual = new Date();
        SimpleDateFormat Formator = new SimpleDateFormat("dd-MM-yy");
        String CadenaFechar = Formator.format(FechaActual);
        SimpleDateFormat Formato = new SimpleDateFormat("EEEE, d 'de' MMMM 'de' yyyy, hh:mm:ss a");
        String CadenaFecha = Formato.format(FechaActual);
        String dirDestino = System.getenv("SIGAA_HOME1") + "/documentos/nominas/excel/";
        String archivo = "LISTA_" + curso.getId_curso() + "_" + CadenaFechar + ".xls";
        String dirPlantilla = System.getenv("SIGAA_HOME1") + "/documentos/plantillas_excel/plantilla_lista.xls";

        try {
            Workbook plantilla = Workbook.getWorkbook(new File(dirPlantilla));
            WritableWorkbook workbook = Workbook.createWorkbook(new File(dirDestino + archivo), plantilla);
            WritableSheet sheet = workbook.getSheet("LISTA");
            WritableFont arial_8 = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat arial8 = new WritableCellFormat(arial_8);
            sheet.addCell(new Label(3, 5, curso.getCurso() + " de " + curso.getCiclo(), arial8));
            sheet.addCell(new Label(3, 6, CadenaFecha, arial8));

            Estudiante est;
            for (int i = 0; i < curso.getEstudiantes().size(); i++) {
                est = (Estudiante) curso.getEstudiantes().get(i);
                sheet.addCell(new jxl.write.Number(1, i + 10, (i + 1), arial8));
                sheet.addCell(new jxl.write.Number(2, i + 10, est.getCodigo(), arial8));
                sheet.addCell(new Label(3, i + 10, est.getPaterno() + " " + est.getMaterno() + " " + est.getNombres(), arial8));
            }
            workbook.write();
            workbook.close();
        } catch (Exception de) {
            de.printStackTrace();
        }
        return archivo;
    }

    private String BoletaTrimestralPdf(Curso curso, Estudiante estudiante, int parciales) {
        String dir = System.getenv("SIGAA_HOME1") + "/documentos/boletines/";
        String archivo = curso.getDesc_curso() + "_" + estudiante.getId_estudiante() + "_" + this.formatoFechaNom(new Date()) + ".pdf";
        Font font8 = FontFactory.getFont(FontFactory.HELVETICA, 8);
        Font font8c = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE, 8);
        Font font8b = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8);
        Font font7b = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 7);
        Font font6 = FontFactory.getFont(FontFactory.HELVETICA, 8);
        Font font12 = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10);
        Font font6_n = FontFactory.getFont(FontFactory.HELVETICA, 6);

        Document document = new Document(PageSize.LEGAL);
       // document.addTitle("\n\n\n\n\n\n\n");
        try {

            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dir + archivo));
            document.open();
            document.addTitle("BOLETÍN TRIMESTRAL");
            document.addAuthor("Marco Antonio Quenta Velasco");
            document.addSubject("COLST");
            document.addKeywords("MAQV");
            Image png = Image.getInstance(System.getenv("SIGAA_HOME1") + "/imagenes/iconos_sigaa/santa_teresa_logo.png");
            png.setAbsolutePosition(555, 935);
            png.scalePercent(25);
            document.add(png);
           /* Image pngfirma = Image.getInstance(System.getenv("SIGAA_HOME1") + "/imagenes/iconos_sigaa/mily.png");
            pngfirma.setAbsolutePosition(555, 935);
            pngfirma.scalePercent(25);
            document.add(pngfirma);*/


            document.add(new Phrase("COLEGIO \"SANTA TERESA\"", font8b));
//            document.add(Chunk.NEWLINE);
//            document.add(new Phrase("La Paz - Bolivia", font8b));
            document.add(Chunk.NEWLINE);
            Paragraph p = new Paragraph(new Phrase("BOLETÍN DE CALIFICACIONES \n", font12));
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);

            PdfPCell cell = null;
            PdfPTable table_uno = new PdfPTable(6);
            int headerwidths[] = {10, 5, 50, 7, 5, 23}; // percentage
            table_uno.getDefaultCell().setBorder(0);
            table_uno.setWidths(headerwidths);
            table_uno.setWidthPercentage(100);

            cell = new PdfPCell(new Phrase("Estudiante", font8));
            cell.setBorder(0);
            table_uno.addCell(cell);
            cell = new PdfPCell(new Phrase(":", font8));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(0);
            table_uno.addCell(cell);
            cell = new PdfPCell(new Phrase(estudiante.getPaterno() + " " + estudiante.getMaterno() + " " + estudiante.getNombres(), font8b));
            cell.setBorder(0);
            table_uno.addCell(cell);
            cell = new PdfPCell(new Phrase("Curso", font8));
            cell.setBorder(0);
            table_uno.addCell(cell);
            cell = new PdfPCell(new Phrase(":", font8));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(0);
            table_uno.addCell(cell);
            cell = new PdfPCell(new Phrase(curso.getCurso() + " de " + curso.getCiclo(), font8b));
            cell.setBorder(0);
            table_uno.addCell(cell);

            cell = new PdfPCell(new Phrase("Fecha", font8));
            cell.setBorder(0);
            table_uno.addCell(cell);
            cell = new PdfPCell(new Phrase(":", font8));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(0);
            table_uno.addCell(cell);
            cell = new PdfPCell(new Phrase(this.formatearFecha(new Date()), font8b));
            cell.setBorder(0);
            table_uno.addCell(cell);
            cell = new PdfPCell(new Phrase("Gestión", font8));
            cell.setBorder(0);
            table_uno.addCell(cell);
            cell = new PdfPCell(new Phrase(":", font8));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(0);
            table_uno.addCell(cell);
            cell = new PdfPCell(new Phrase(Integer.toString(curso.getGestion().getId_gestion()), font8b));
            cell.setBorder(0);
            table_uno.addCell(cell);
            document.add(table_uno);

            if (!curso.getId_curso().equals("K") && !curso.getId_curso().equals("K1")&& !curso.getId_curso().equals("K12")&& !curso.getId_curso().equals("K21")&& !curso.getId_curso().equals("P1")&& !curso.getId_curso().equals("P12")) {
                PdfPTable tabla = new PdfPTable(5);
                if (parciales == 2) {
                    tabla = new PdfPTable(6);
                    int anchos_tabla1[] = {20, 12, 12, 12, 12, 12};
                    tabla.setWidths(anchos_tabla1);
                    tabla.getDefaultCell().setBorder(0);
                    tabla.setWidthPercentage(100);

                    PdfPTable tabla1 = new PdfPTable(3);
                    int tam[] = {33, 34, 33};
                    tabla1.setWidths(tam);
                    tabla1.setWidthPercentage(100);
                    cell = new PdfPCell(new Phrase("1er. Bimestre", font8b));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setColspan(3);
                    tabla1.addCell(cell);
//                    cell = new PdfPCell(new Phrase("P.C.", font7b));
//                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    cell.setBorder(0);
//                    tabla1.addCell(cell);
                    cell = new PdfPCell(new Phrase("P.Total.", font7b));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setColspan(3);
                    tabla1.addCell(cell);
//                    cell = new PdfPCell(new Phrase("P.T.", font7b));
//                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    cell.setBorder(0);
//                    tabla1.addCell(cell);

                    PdfPTable tabla2 = new PdfPTable(3);
                    tabla2.setWidths(tam);
                    tabla2.setWidthPercentage(100);
                    cell = new PdfPCell(new Phrase("2do. Bimestre", font8b));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setColspan(3);
                    tabla2.addCell(cell);
//                    cell = new PdfPCell(new Phrase("P.C.", font7b));
//                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    cell.setBorder(0);
//                    tabla2.addCell(cell);
                    cell = new PdfPCell(new Phrase("P.Total.", font7b));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setColspan(3);
                    tabla2.addCell(cell);
//                    cell = new PdfPCell(new Phrase("P.T.", font7b));
//                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    cell.setBorder(0);
//                    tabla2.addCell(cell);

                    PdfPTable tabla3 = new PdfPTable(3);
                    tabla3.setWidths(tam);
                    tabla3.setWidthPercentage(100);
                    cell = new PdfPCell(new Phrase("3er. Bimestre", font8b));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setColspan(3);
                    tabla3.addCell(cell);
//                    cell = new PdfPCell(new Phrase("P.C.", font7b));
//                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    cell.setBorder(0);
//                    tabla3.addCell(cell);
                    cell = new PdfPCell(new Phrase("P.Total.", font7b));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setColspan(3);
                    tabla3.addCell(cell);
//                    cell = new PdfPCell(new Phrase("P.T.", font7b));
//                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    cell.setBorder(0);
//                    tabla3.addCell(cell);

                    PdfPTable tabla4 = new PdfPTable(3);
                    tabla4.setWidths(tam);
                    tabla4.setWidthPercentage(100);
                    cell = new PdfPCell(new Phrase("4to. Bimestre", font8b));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setColspan(3);
                    tabla4.addCell(cell);
//                    cell = new PdfPCell(new Phrase("P.C.", font7b));
//                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    cell.setBorder(0);
//                    tabla4.addCell(cell);
                    cell = new PdfPCell(new Phrase("P.Total.", font7b));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setColspan(3);
                    tabla4.addCell(cell);
//                    cell = new PdfPCell(new Phrase("P.T.", font7b));
//                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    cell.setBorder(0);
//                    tabla4.addCell(cell);

                    PdfPTable tabla5 = new PdfPTable(3);
                    tabla5.setWidths(tam);
                    tabla5.setWidthPercentage(100);
                    cell = new PdfPCell(new Phrase("  ", font7b));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(0);
                    tabla5.addCell(cell);
                    cell = new PdfPCell(new Phrase("  ", font7b));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(0);
                    tabla5.addCell(cell);
                    cell = new PdfPCell(new Phrase("  ", font7b));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(0);
                    tabla5.addCell(cell);

//                    tabla.getDefaultCell().setGrayFill(0.9f);
//                    tabla.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell = new PdfPCell(new Phrase("\nMateria", font8b));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.getBorder();
                    tabla.addCell(cell);
                    cell = new PdfPCell(tabla1);
                    cell.getBorder();
                    tabla.addCell(cell);
                    cell = new PdfPCell(tabla2);
                    cell.getBorder();
                    tabla.addCell(cell);
                    cell = new PdfPCell(tabla3);
                    cell.getBorder();
                    tabla.addCell(cell);
                    cell = new PdfPCell(tabla4);
                    cell.getBorder();
                    tabla.addCell(cell);
                    cell = new PdfPCell(tabla5);
                    cell.getBorder();
                    tabla.addCell(cell);

                    for (int i = 0; i < curso.getMaterias().size(); i++) {
                        Materia materia = (Materia) curso.getMaterias().get(i);
                        cell = new PdfPCell(new Phrase(materia.getMateria(), font7b));
                        cell.setGrayFill(0.9f);
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.getBorder();
                        tabla.addCell(cell);
                        tabla.getDefaultCell().setGrayFill(1);

                        PdfPTable t1 = new PdfPTable(1);
                        int tam_2[] = {100};
                        t1.setWidths(tam_2);
                        t1.setWidthPercentage(100);

                        PdfPTable t2 = new PdfPTable(1);
                        t2.setWidths(tam_2);
                        t2.setWidthPercentage(100);

                        PdfPTable t3 = new PdfPTable(1);
                        t3.setWidths(tam_2);
                        t3.setWidthPercentage(100);

                        PdfPTable t4 = new PdfPTable(1);
                        t4.setWidths(tam_2);
                        t4.setWidthPercentage(100);

                        PdfPTable t5 = new PdfPTable(3);
                        t5.setWidths(tam);
                        t5.setWidthPercentage(100);

                        int suma_e1 = 0;
                        int suma_e2 = 0;
                        int suma_e3 = 0;
                        int suma_e4 = 0;
                        for (int j = 0; j < materia.getEvaluaciones().size(); j++) {
                            Evaluacion evaluacion = (Evaluacion) materia.getEvaluaciones().get(j);
                            String nota = Integer.toString(evaluacion.getNota());
                            String pt = Integer.toString(evaluacion.getNota() + evaluacion.getDps());

                            if (j == 0) {
                                cell = new PdfPCell(new Phrase(pt, font7b));
                                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                cell.setBorder(0);
                                t1.addCell(cell);
                                suma_e1 = Integer.parseInt(pt);
                                /*cualitativa*/
                                cell = new PdfPCell(new Phrase(evaluacion.getCualitativa(), font6_n));
                                cell.setGrayFill(0.9f);
                                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                cell.setBorder(0);
                                t1.addCell(cell);
                            }
                            if (j == 1) {
                                cell = new PdfPCell(new Phrase(pt, font7b));
                                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                cell.setBorder(0);
                                t2.addCell(cell);
                                suma_e2 = Integer.parseInt(pt);
                                /*cualitativa*/
                                cell = new PdfPCell(new Phrase(evaluacion.getCualitativa(), font6_n));
                                cell.setGrayFill(0.9f);
                                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                cell.setBorder(0);
                                t2.addCell(cell);
                            }
                            if (j == 2) {
                                cell = new PdfPCell(new Phrase(pt, font7b));
                                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                cell.setBorder(0);
                                t3.addCell(cell);
                                suma_e3 = Integer.parseInt(pt);
                                /*cualitativa*/
                                cell = new PdfPCell(new Phrase(evaluacion.getCualitativa(), font6_n));
                                cell.setGrayFill(0.9f);
                                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                cell.setBorder(0);
                                t3.addCell(cell);
                            }
                            if (j == 3) {
                                cell = new PdfPCell(new Phrase(pt, font7b));
                                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                cell.setBorder(0);
                                t4.addCell(cell);
                                suma_e4 = Integer.parseInt(pt);
                                /*cualitativa*/
                                cell = new PdfPCell(new Phrase(evaluacion.getCualitativa(), font6_n));
                                cell.setGrayFill(0.9f);
                                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                cell.setBorder(0);
                                t4.addCell(cell);
                                if (materia.getEvaluaciones().size() == 4) {
                                    int promedio_anual = (int) (Math.round(((double) (suma_e1 + suma_e2 + suma_e3 + suma_e4) / 4) * Math.pow(10, 0)) / Math.pow(10, 0));
                                    cell = new PdfPCell(new Phrase(Integer.toString(promedio_anual), font7b));
                                    cell.setGrayFill(0.6f);
                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    cell.setBorder(0);
                                    t5.addCell(cell);
                                    cell = new PdfPCell(new Phrase(""));
                                    cell.setBorder(0);
                                    t5.addCell(cell);
                                    cell = new PdfPCell(new Phrase(""));
                                    cell.setBorder(0);
                                    t5.addCell(cell);

                                }
                            }
                            if (j == 4) {
                                int promedio_anual = (int) (Math.round(((double) (suma_e1 + suma_e2 + suma_e3 + suma_e4) / 4) * Math.pow(10, 0)) / Math.pow(10, 0));
                                int promedio_final = (int) (Math.round(((double) (promedio_anual + Integer.parseInt(nota)) / 2) * Math.pow(10, 0)) / Math.pow(10, 0));
                                cell = new PdfPCell(new Phrase(Integer.toString(promedio_anual), font7b));
                                cell.setGrayFill(0.6f);
                                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                cell.setBorder(0);
                                t5.addCell(cell);
                                if (!nota.equals("0")) {
                                    cell = new PdfPCell(new Phrase(nota, font7b));
                                    cell.setGrayFill(0.9f);
                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    cell.setBorder(0);
                                    t5.addCell(cell);
                                    cell = new PdfPCell(new Phrase(Integer.toString(promedio_final), font7b));
                                    cell.setGrayFill(0.6f);
                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    cell.setBorder(0);
                                    t5.addCell(cell);
                                } else {
                                    cell = new PdfPCell(new Phrase(""));
                                    cell.setBorder(0);
                                    t5.addCell(cell);
                                    cell = new PdfPCell(new Phrase(""));
                                    cell.setBorder(0);
                                    t5.addCell(cell);
                                }
                            }
                        }
                        cell = new PdfPCell(t1);
                        cell.getBorder();
                        tabla.addCell(cell);
                        cell = new PdfPCell(t2);
                        cell.getBorder();
                        tabla.addCell(cell);
                        cell = new PdfPCell(t3);
                        cell.getBorder();
                        tabla.addCell(cell);
                        cell = new PdfPCell(t4);
                        cell.getBorder();
                        tabla.addCell(cell);
                        cell = new PdfPCell(t5);
                        cell.getBorder();
                        tabla.addCell(cell);
                    }
                    cell = new PdfPCell(new Phrase("Promedio : ", font7b));
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell.setBorder(0);
                    tabla.addCell(cell);

                    cell = new PdfPCell(new Phrase(Integer.toString(curso.getPtrim1()) + " Pts.", font7b));
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell.setBorder(0);
                    tabla.addCell(cell);
                    cell = new PdfPCell(new Phrase(Integer.toString(curso.getPtrim2()) + " Pts.", font7b));
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell.setBorder(0);
                    tabla.addCell(cell);
                    cell = new PdfPCell(new Phrase(Integer.toString(curso.getPtrim3()) + " Pts.", font7b));
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell.setBorder(0);
                    tabla.addCell(cell);
                    cell = new PdfPCell(new Phrase(Integer.toString(0) + " Pts.", font7b));
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell.setBorder(0);
                    tabla.addCell(cell);

                    cell = new PdfPCell(new Phrase(" ", font7b));
                    cell.setBorder(0);
                    tabla.addCell(cell);

                    cell = new PdfPCell(new Phrase("Conducta : ", font7b));
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell.setBorder(0);
                    tabla.addCell(cell);
                    for (int j = 0; j < curso.getConductas().size(); j++) {
                        Conducta conducta = (Conducta) curso.getConductas().get(j);
                        cell = new PdfPCell(new Phrase(conducta.getTipo_conducta(), font7b));
                        cell.setBorder(0);
                        tabla.addCell(cell);
                    }
                    cell = new PdfPCell(new Phrase(" ", font7b));
                    cell.setBorder(0);
                    tabla.addCell(cell);
                    cell = new PdfPCell(new Phrase(" ", font7b));
                    cell.setBorder(0);
                    tabla.addCell(cell);

                    // List faltas=this.sigaa.getFaltasByIdEstudiateAndGestion();
                    cell = new PdfPCell(new Phrase("Faltas : ", font7b));
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell.setBorder(0);
                    tabla.addCell(cell);
                    for (int j = 0; j < curso.getFaltas().size(); j++) {
                        Falta falta = (Falta) curso.getFaltas().get(j);
                        cell = new PdfPCell(new Phrase(Integer.toString(falta.getFsl()), font7b));
                        cell.setBorder(0);
                        tabla.addCell(cell);
                    }
                    cell = new PdfPCell(new Phrase(" ", font7b));
                    cell.setBorder(0);
                    tabla.addCell(cell);
                    cell = new PdfPCell(new Phrase(" ", font7b));
                    cell.setBorder(0);
                    tabla.addCell(cell);

                    cell = new PdfPCell(new Phrase("Atrasos : ", font7b));
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell.setBorder(0);
                    tabla.addCell(cell);
//                    System.out.println("____________________>>>"+curso.getFaltas().size());
                    for (int j = 0; j < curso.getFaltas().size(); j++) {
                        Falta falta = (Falta) curso.getFaltas().get(j);
                        cell = new PdfPCell(new Phrase(Integer.toString(falta.getRetraso()), font7b));
                        cell.setBorder(0);
                        tabla.addCell(cell);
                    }
                    cell = new PdfPCell(new Phrase(" ", font7b));
                    cell.setBorder(0);
                    tabla.addCell(cell);
                    cell = new PdfPCell(new Phrase(" ", font7b));
                    cell.setBorder(0);
                    tabla.addCell(cell);

                    cell = new PdfPCell(new Phrase("Licencias : ", font7b));
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell.setBorder(0);
                    tabla.addCell(cell);
                    for (int j = 0; j < curso.getFaltas().size(); j++) {
                        Falta falta = (Falta) curso.getFaltas().get(j);
                        cell = new PdfPCell(new Phrase(Integer.toString(falta.getFcl()), font7b));
                        cell.setBorder(0);
                        tabla.addCell(cell);
                    }
                    cell = new PdfPCell(new Phrase(" ", font7b));
                    cell.setBorder(0);
                    tabla.addCell(cell);
                    cell = new PdfPCell(new Phrase(" ", font7b));
                    cell.setBorder(0);
                    tabla.addCell(cell);

                    tabla.setHorizontalAlignment(Element.ALIGN_CENTER);
                    tabla.addCell(new Phrase("", font8b));
                    tabla.addCell(new Phrase("\n\n\n________________", font8b));
                    tabla.addCell(new Phrase("", font8b));
                    tabla.addCell(new Phrase("\n\n\n________________", font8b));
                    tabla.addCell(new Phrase("", font8b));
                    tabla.addCell(new Phrase("", font8b));

                    tabla.addCell(new Phrase("", font8b));
                    tabla.addCell(new Phrase("PROFESOR/A/A", font8b));
                    tabla.addCell(new Phrase("", font8b));
                    tabla.addCell(new Phrase("DIRECTORA", font8b));
                    tabla.addCell(new Phrase("", font8b));
                    tabla.addCell(new Phrase("", font8b));

                    cell = new PdfPCell(new Phrase("\n" + curso.getGestion().getLema(), font8c));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(0);
                    cell.setColspan(6);
                    tabla.addCell(cell);

                    document.add(tabla);
                } else if (parciales == 3) {
                    tabla = new PdfPTable(5);
                    int anchos_tabla2[] = {20, 15, 15, 15, 15};
                    tabla.setWidths(anchos_tabla2);
                    tabla.getDefaultCell().setBorder(0);
                    tabla.setWidthPercentage(100);

                    PdfPTable tabla1 = new PdfPTable(3);
                    int tam[] = {33, 34, 33};
                    tabla1.setWidths(tam);
                    tabla1.setWidthPercentage(100);
                    cell = new PdfPCell(new Phrase("1er. TrimestreTT", font8b));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(0);
                    cell.setColspan(3);
                    tabla1.addCell(cell);
                    cell = new PdfPCell(new Phrase("P.C.", font7b));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(0);
                    tabla1.addCell(cell);
                    cell = new PdfPCell(new Phrase("DPS", font7b));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(0);
                    tabla1.addCell(cell);
                    cell = new PdfPCell(new Phrase("P.T.", font7b));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(0);
                    tabla1.addCell(cell);

                    PdfPTable tabla2 = new PdfPTable(3);
                    tabla2.setWidths(tam);
                    tabla2.setWidthPercentage(100);
                    cell = new PdfPCell(new Phrase("2do. Trimestre", font8b));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(0);
                    cell.setColspan(3);
                    tabla2.addCell(cell);
                    cell = new PdfPCell(new Phrase("P.C.", font7b));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(0);
                    tabla2.addCell(cell);
                    cell = new PdfPCell(new Phrase("DPS", font7b));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(0);
                    tabla2.addCell(cell);
                    cell = new PdfPCell(new Phrase("P.T.", font7b));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(0);
                    tabla2.addCell(cell);

                    PdfPTable tabla3 = new PdfPTable(3);
                    tabla3.setWidths(tam);
                    tabla3.setWidthPercentage(100);
                    cell = new PdfPCell(new Phrase("3er. Trimestre", font8b));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(0);
                    cell.setColspan(3);
                    tabla3.addCell(cell);
                    cell = new PdfPCell(new Phrase("P.C.", font7b));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(0);
                    tabla3.addCell(cell);
                    cell = new PdfPCell(new Phrase("DPS", font7b));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(0);
                    tabla3.addCell(cell);
                    cell = new PdfPCell(new Phrase("P.T.", font7b));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(0);
                    tabla3.addCell(cell);

                    PdfPTable tabla4 = new PdfPTable(3);
                    tabla4.setWidths(tam);
                    tabla4.setWidthPercentage(100);
                    cell = new PdfPCell(new Phrase("\nP.A.", font7b));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(0);
                    tabla4.addCell(cell);
                    cell = new PdfPCell(new Phrase("\nP.R.", font7b));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(0);
                    tabla4.addCell(cell);
                    cell = new PdfPCell(new Phrase("\nP.F.", font7b));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(0);
                    tabla4.addCell(cell);

                    tabla.getDefaultCell().setGrayFill(0.9f);
                    tabla.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                    tabla.addCell(new Phrase("\nMateria", font8b));
                    tabla.addCell(tabla1);
                    tabla.addCell(tabla2);
                    tabla.addCell(tabla3);
                    tabla.addCell(tabla4);
                    for (int i = 0; i < curso.getMaterias().size(); i++) {
                        Materia materia = (Materia) curso.getMaterias().get(i);
                        cell = new PdfPCell(new Phrase(materia.getMateria(), font7b));
                        cell.setGrayFill(0.9f);
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setBorder(0);
                        cell.setBorderWidthTop(0.1f);
                        tabla.addCell(cell);
                        tabla.getDefaultCell().setGrayFill(1);

                        PdfPTable t1 = new PdfPTable(3);
                        t1.setWidths(tam);
                        t1.setWidthPercentage(100);

                        PdfPTable t2 = new PdfPTable(3);
                        t2.setWidths(tam);
                        t2.setWidthPercentage(100);

                        PdfPTable t3 = new PdfPTable(3);
                        t3.setWidths(tam);
                        t3.setWidthPercentage(100);

                        PdfPTable t4 = new PdfPTable(3);
                        t4.setWidths(tam);
                        t4.setWidthPercentage(100);

                        int suma_e1 = 0;
                        int suma_e2 = 0;
                        int suma_e3 = 0;
                        for (int j = 0; j < materia.getEvaluaciones().size(); j++) {
                            Evaluacion evaluacion = (Evaluacion) materia.getEvaluaciones().get(j);
                            String nota = Integer.toString(evaluacion.getNota());
                            String dps = Integer.toString(evaluacion.getDps());
                            String pt = Integer.toString(evaluacion.getNota() + evaluacion.getDps());

                            if (j == 0) {
                                cell = new PdfPCell(new Phrase(nota, font7b));
                                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                cell.setBorder(0);
                                t1.addCell(cell);
                                cell = new PdfPCell(new Phrase(dps, font7b));
                                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                cell.setBorder(0);
                                t1.addCell(cell);
                                cell = new PdfPCell(new Phrase(pt, font7b));
                                cell.setGrayFill(0.9f);
                                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                cell.setBorder(0);
                                t1.addCell(cell);
                                suma_e1 = Integer.parseInt(pt);
                                /*cualitativa*/
                                cell = new PdfPCell(new Phrase(evaluacion.getCualitativa(), font6_n));
                                cell.setGrayFill(0.9f);
                                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                cell.setColspan(3);
                                cell.setBorder(0);
                                t1.addCell(cell);
                            }
                            if (j == 1) {
                                cell = new PdfPCell(new Phrase(nota, font7b));
                                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                cell.setBorder(0);
                                t2.addCell(cell);
                                cell = new PdfPCell(new Phrase(dps, font7b));
                                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                cell.setBorder(0);
                                t2.addCell(cell);
                                cell = new PdfPCell(new Phrase(pt, font7b));
                                cell.setGrayFill(0.9f);
                                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                cell.setBorder(0);
                                t2.addCell(cell);
                                suma_e2 = Integer.parseInt(pt);
                                /*cualitativa*/
                                cell = new PdfPCell(new Phrase(evaluacion.getCualitativa(), font6_n));
                                cell.setGrayFill(0.9f);
                                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                cell.setColspan(3);
                                cell.setBorder(0);
                                t2.addCell(cell);
                            }
                            if (j == 2) {
                                cell = new PdfPCell(new Phrase(nota, font7b));
                                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                cell.setBorder(0);
                                t3.addCell(cell);
                                cell = new PdfPCell(new Phrase(dps, font7b));
                                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                cell.setBorder(0);
                                t3.addCell(cell);
                                cell = new PdfPCell(new Phrase(pt, font7b));
                                cell.setGrayFill(0.9f);
                                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                cell.setBorder(0);
                                t3.addCell(cell);
                                suma_e3 = Integer.parseInt(pt);
                                /*cualitativa*/
                                cell = new PdfPCell(new Phrase(evaluacion.getCualitativa(), font6_n));
                                cell.setGrayFill(0.9f);
                                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                cell.setColspan(3);
                                cell.setBorder(0);
                                t3.addCell(cell);
                                if (materia.getEvaluaciones().size() == 3) {
                                    int promedio_anual = (int) (Math.round(((double) (suma_e1 + suma_e2 + suma_e3) / 3) * Math.pow(10, 0)) / Math.pow(10, 0));
                                    cell = new PdfPCell(new Phrase(Integer.toString(promedio_anual), font7b));
                                    cell.setGrayFill(0.6f);
                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    cell.setBorder(0);
                                    t4.addCell(cell);
                                    cell = new PdfPCell(new Phrase(""));
                                    cell.setBorder(0);
                                    t4.addCell(cell);
                                    cell = new PdfPCell(new Phrase(""));
                                    cell.setBorder(0);
                                    t4.addCell(cell);

                                }
                            }
                            if (j == 3) {
                                int promedio_anual = (int) (Math.round(((double) (suma_e1 + suma_e2 + suma_e3) / 3) * Math.pow(10, 0)) / Math.pow(10, 0));
                                int promedio_final = (int) (Math.round(((double) (promedio_anual + Integer.parseInt(nota)) / 2) * Math.pow(10, 0)) / Math.pow(10, 0));
                                cell = new PdfPCell(new Phrase(Integer.toString(promedio_anual), font7b));
                                cell.setGrayFill(0.6f);
                                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                cell.setBorder(0);
                                t4.addCell(cell);
                                if (!nota.equals("0")) {
                                    cell = new PdfPCell(new Phrase(nota, font7b));
                                    cell.setGrayFill(0.9f);
                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    cell.setBorder(0);
                                    t4.addCell(cell);
                                    cell = new PdfPCell(new Phrase(Integer.toString(promedio_final), font7b));
                                    cell.setGrayFill(0.6f);
                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    cell.setBorder(0);
                                    t4.addCell(cell);
                                } else {
                                    cell = new PdfPCell(new Phrase(""));
                                    cell.setBorder(0);
                                    t4.addCell(cell);
                                    cell = new PdfPCell(new Phrase(""));
                                    cell.setBorder(0);
                                    t4.addCell(cell);
                                }
                            }
                        }
                        cell = new PdfPCell(t1);
                        cell.setBorder(0);
                        cell.setBorderWidthTop(0.1f);
                        tabla.addCell(cell);
                        cell = new PdfPCell(t2);
                        cell.setBorder(0);
                        cell.setBorderWidthTop(0.1f);
                        tabla.addCell(cell);
                        cell = new PdfPCell(t3);
                        cell.setBorder(0);
                        cell.setBorderWidthTop(0.1f);
                        tabla.addCell(cell);
                        cell = new PdfPCell(t4);
                        cell.setBorder(0);
                        cell.setBorderWidthTop(0.1f);
                        tabla.addCell(cell);
                    }
                    cell = new PdfPCell(new Phrase("Promedio : ", font7b));
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell.setBorder(0);
                    tabla.addCell(cell);

                    cell = new PdfPCell(new Phrase(Integer.toString(curso.getPtrim1()) + " Pts.", font7b));
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell.setBorder(0);
                    tabla.addCell(cell);
                    cell = new PdfPCell(new Phrase(Integer.toString(curso.getPtrim2()) + " Pts.", font7b));
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell.setBorder(0);
                    tabla.addCell(cell);
                    cell = new PdfPCell(new Phrase(Integer.toString(curso.getPtrim3()) + " Pts.", font7b));
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell.setBorder(0);
                    tabla.addCell(cell);

                    cell = new PdfPCell(new Phrase(" ", font7b));
                    cell.setBorder(0);
                    tabla.addCell(cell);

                    cell = new PdfPCell(new Phrase("Conducta : ", font7b));
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell.setBorder(0);
                    tabla.addCell(cell);
                    for (int j = 0; j < curso.getConductas().size(); j++) {
                        Conducta conducta = (Conducta) curso.getConductas().get(j);
                        cell = new PdfPCell(new Phrase(conducta.getTipo_conducta(), font7b));
                        cell.setBorder(0);
                        tabla.addCell(cell);
                    }
                    cell = new PdfPCell(new Phrase(" ", font7b));
                    cell.setBorder(0);
                    tabla.addCell(cell);

                    cell = new PdfPCell(new Phrase("Faltas : ", font7b));
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell.setBorder(0);
                    tabla.addCell(cell);
//                    System.out.println("_______________"+curso.getFaltas().size());
                    for (int j = 0; j < curso.getFaltas().size(); j++) {
                        Falta falta = (Falta) curso.getFaltas().get(j);
                        cell = new PdfPCell(new Phrase(Integer.toString(falta.getFsl()), font7b));
                        cell.setBorder(0);
                        tabla.addCell(cell);
                    }
                    cell = new PdfPCell(new Phrase(" ", font7b));
                    cell.setBorder(0);
                    tabla.addCell(cell);

                    cell = new PdfPCell(new Phrase("Atrasos : ", font7b));
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell.setBorder(0);
                    tabla.addCell(cell);
                    for (int j = 0; j < curso.getFaltas().size(); j++) {
                        Falta falta = (Falta) curso.getFaltas().get(j);
                        cell = new PdfPCell(new Phrase(Integer.toString(falta.getRetraso()), font7b));
                        cell.setBorder(0);
                        tabla.addCell(cell);
                    }
                    cell = new PdfPCell(new Phrase(" ", font7b));
                    cell.setBorder(0);
                    tabla.addCell(cell);

                    cell = new PdfPCell(new Phrase("Licencias : ", font7b));
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell.setBorder(0);
                    tabla.addCell(cell);
                    for (int j = 0; j < curso.getFaltas().size(); j++) {
                        Falta falta = (Falta) curso.getFaltas().get(j);
                        cell = new PdfPCell(new Phrase(Integer.toString(falta.getFcl()), font7b));
                        cell.setBorder(0);
                        tabla.addCell(cell);
                    }
                    cell = new PdfPCell(new Phrase(" ", font7b));
                    cell.setBorder(0);
                    tabla.addCell(cell);

                    tabla.setHorizontalAlignment(Element.ALIGN_CENTER);
                    tabla.addCell(new Phrase("", font8b));
                    tabla.addCell(new Phrase("\n\n\n________________", font8b));
                    tabla.addCell(new Phrase("", font8b));
                    tabla.addCell(new Phrase("\n\n\n________________", font8b));
                    tabla.addCell(new Phrase("", font8b));

                    tabla.addCell(new Phrase("", font8b));
                    tabla.addCell(new Phrase("PROFESOR/A/A", font8b));
                    tabla.addCell(new Phrase("", font8b));
                    tabla.addCell(new Phrase("DIRECTORA", font8b));
                    tabla.addCell(new Phrase("", font8b));

                    cell = new PdfPCell(new Phrase("\n" + curso.getGestion().getLema(), font8c));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(0);
                    cell.setColspan(5);
                    tabla.addCell(cell);

                    document.add(tabla);
                }
            } else {
                PdfPTable table = new PdfPTable(4);
                int twidths[] = {25, 25, 25, 25}; // percentage
                table.getDefaultCell().setBorder(0);
                table.setWidths(twidths);
                table.setWidthPercentage(100);

                table.getDefaultCell().setGrayFill(0.9f);
                table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(new Phrase("Materia", font8b));
                table.addCell(new Phrase("1er. Bimestre", font8b));
                table.addCell(new Phrase("2do. Bimestre", font8b));
                table.addCell(new Phrase("3er. Bimestre", font8b));

                for (int i = 0; i < curso.getMaterias().size(); i++) {
                    Materia materia = (Materia) curso.getMaterias().get(i);
                    cell = new PdfPCell(new Phrase(materia.getMateria(), font7b));
                    cell.setGrayFill(0.9f);
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(0);
                    table.addCell(cell);
                    table.getDefaultCell().setGrayFill(1);

                    PdfPTable t1 = new PdfPTable(3);
                    int t1widths[] = {33, 34, 33};
                    t1.setWidths(t1widths);
                    t1.setWidthPercentage(100);

                    PdfPTable t2 = new PdfPTable(3);
                    t2.setWidths(t1widths);
                    t2.setWidthPercentage(100);

                    PdfPTable t3 = new PdfPTable(3);
                    t3.setWidths(t1widths);
                    t3.setWidthPercentage(100);

                    for (int j = 0; j < materia.getEvaluaciones().size(); j++) {
                        Evaluacion evaluacion = (Evaluacion) materia.getEvaluaciones().get(j);
                        if (j == 0) {
                            cell = new PdfPCell(new Phrase(evaluacion.getCualitativa(), font7b));
                            cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
                            cell.setBorder(0);
                            cell.setColspan(3);
                            t1.addCell(cell);
                        }
                        if (j == 1) {
                            cell = new PdfPCell(new Phrase(evaluacion.getCualitativa(), font7b));
                            cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
                            cell.setBorder(0);
                            cell.setColspan(3);
                            t2.addCell(cell);
                        }
                        if (j == 2) {
                            cell = new PdfPCell(new Phrase(evaluacion.getCualitativa(), font7b));
                            cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
                            cell.setBorder(0);
                            cell.setColspan(3);
                            t3.addCell(cell);
                        }
                    }
                    table.addCell(t1);
                    table.addCell(t2);
                    table.addCell(t3);
                }

                if (curso.getConductas().size() != 0) {
                    cell = new PdfPCell(new Phrase("Diagnóstico incial : ", font7b));
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell.setBorder(0);
                    table.addCell(cell);
                    Conducta conducta = (Conducta) curso.getConductas().get(0);
                    cell = new PdfPCell(new Phrase(conducta.getDiagnostico(), font8));
                    cell.setColspan(3);
                    cell.setBorder(0);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase("Diagnóstico final : ", font7b));
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell.setBorder(0);
                    table.addCell(cell);
                    conducta = (Conducta) curso.getConductas().get(1);
                    cell = new PdfPCell(new Phrase(conducta.getDiagnostico(), font8));
                    cell.setColspan(3);
                    cell.setBorder(0);
                    table.addCell(cell);
                }

                cell = new PdfPCell(new Phrase("Faltas : ", font7b));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(0);
                table.addCell(cell);
                for (int j = 0; j < curso.getFaltas().size(); j++) {
                    Falta falta = (Falta) curso.getFaltas().get(j);
                    cell = new PdfPCell(new Phrase(Integer.toString(falta.getFsl()), font7b));
                    cell.setBorder(0);
                    table.addCell(cell);
                }

                cell = new PdfPCell(new Phrase("Atrasos : ", font7b));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(0);
                table.addCell(cell);
                for (int j = 0; j < curso.getFaltas().size(); j++) {
                    Falta falta = (Falta) curso.getFaltas().get(j);
                    cell = new PdfPCell(new Phrase(Integer.toString(falta.getRetraso()), font7b));
                    cell.setBorder(0);
                    table.addCell(cell);
                }

                cell = new PdfPCell(new Phrase("Licencias : ", font7b));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(0);
                table.addCell(cell);
                for (int j = 0; j < curso.getFaltas().size(); j++) {
                    Falta falta = (Falta) curso.getFaltas().get(j);
                    cell = new PdfPCell(new Phrase(Integer.toString(falta.getFcl()), font7b));
                    cell.setBorder(0);
                    table.addCell(cell);
                }

                table.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(new Phrase("", font8b));
                table.addCell(new Phrase("\n\n\n__________________", font8b));
                table.addCell(new Phrase("", font8b));
                table.addCell(new Phrase("\n\n\n__________________", font8b));

                table.addCell(new Phrase("", font8b));
                table.addCell(new Phrase("PROFESOR/A/A", font8b));
                table.addCell(new Phrase("", font8b));
                table.addCell(new Phrase("COO. ACADEMICA", font8b));

                cell = new PdfPCell(new Phrase("\n" + curso.getGestion().getLema(), font8c));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(0);
                cell.setColspan(4);
                table.addCell(cell);

                document.add(table);
            }
            p = new Paragraph(new Phrase("SIGAA Sistema Integrado de Gestión Académico - Administrativa " + curso.getGestion().getId_gestion(), font6));
            document.add(p);
        } catch (Exception de) {
            de.printStackTrace();
        }
        document.close();
        return archivo;
    }

    private String boletinesCursoPdf(Curso curso_gen, String id_gestion, int parciales) {
        String dir = System.getenv("SIGAA_HOME1") + "/documentos/boletines/";

        String archivo = "Boletines_" + curso_gen.getDesc_curso() + "(" + this.formatoFechaNom(new Date()) + ").pdf";
//        String archivo = "Boletines_" + curso_gen.getCurso() + "_de_" + curso_gen.getCiclo() + "(" + this.formatoFechaNom(new Date()) + ").pdf";
        Font font8 = FontFactory.getFont(FontFactory.HELVETICA, 8);
        Font font8c = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE, 8);
        Font font8b = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8);
        Font font7b = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 7);
        Font font7 = FontFactory.getFont(FontFactory.HELVETICA, 7);
        Font font6 = FontFactory.getFont(FontFactory.HELVETICA, 7);
        Font font12 = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10);
        Font font6_n = FontFactory.getFont(FontFactory.HELVETICA, 6);

        Document document = new Document(PageSize.LEGAL);
        document.setMargins(30, 30, 60, 30);
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dir + archivo));
            float width = document.getPageSize().getWidth();
            document.open();
            for (int x = 0; x <= curso_gen.getEstudiantes().size(); x++) {
                if (x > 0) {
                    document.newPage();
                }
                Estudiante estudiante = (Estudiante) curso_gen.getEstudiantes().get(x);
                Curso curso = new Curso();
                curso.setId_curso(curso_gen.getId_curso());
                curso.setId_gestion(Integer.parseInt(id_gestion));
                curso.setId_estudiante(estudiante.getId_estudiante());
                curso.setId_gestion(Integer.parseInt(id_gestion));
                curso = this.sigaa.getCursoByIdCursoAndIdEstudiantesAndIdGestion(curso);
                Gestion gestion = (Gestion) this.sigaa.getBuscarGestion(Integer.parseInt(id_gestion)).get(0);
                curso.setGestion(gestion);

              
                
                /*Image firma = Image.getInstance(System.getenv("SIGAA_HOME1") + "/imagenes/iconos_sigaa/mily.png");
                firma.setAbsolutePosition(300, 405);
                firma.scalePercent(15);
                document.add(firma); */
                
                Image png = Image.getInstance(System.getenv("SIGAA_HOME1") + "/imagenes/iconos_sigaa/santa_teresa_logo.png");
                png.setAbsolutePosition(550, 945);
                png.scalePercent(15);
                document.add(png);

                document.add(new Phrase("COLEGIO \"SANTA TERESA\"", font8b));
//            document.add(Chunk.NEWLINE);
//            document.add(new Phrase("La Paz - Bolivia", font8b));
                document.add(Chunk.NEWLINE);
                Paragraph p = new Paragraph(new Phrase("BOLETÍN DE CALIFICACIONES \n", font12));
                p.setAlignment(Element.ALIGN_CENTER);
                document.add(p);

                PdfPCell cell = null;
                PdfPTable table_uno = new PdfPTable(6);
                int headerwidths[] = {10, 5, 50, 7, 5, 23}; // percentage
                table_uno.getDefaultCell().setBorder(0);
                table_uno.setWidths(headerwidths);
                table_uno.setWidthPercentage(100);

                cell = new PdfPCell(new Phrase("Estudiante", font8));
                cell.setBorder(0);
                table_uno.addCell(cell);
                cell = new PdfPCell(new Phrase(":", font8));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(0);
                table_uno.addCell(cell);
                cell = new PdfPCell(new Phrase(estudiante.getPaterno() + " " + estudiante.getMaterno() + " " + estudiante.getNombres(), font8b));
                cell.setBorder(0);
                table_uno.addCell(cell);
                cell = new PdfPCell(new Phrase("Curso", font8));
                cell.setBorder(0);
                table_uno.addCell(cell);
                cell = new PdfPCell(new Phrase(":", font8));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(0);
                table_uno.addCell(cell);
                cell = new PdfPCell(new Phrase(curso.getCurso() + " de " + curso.getCiclo(), font8b));
                cell.setBorder(0);
                table_uno.addCell(cell);

                cell = new PdfPCell(new Phrase("Fecha", font8));
                cell.setBorder(0);
                table_uno.addCell(cell);
                cell = new PdfPCell(new Phrase(":", font8));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(0);
                table_uno.addCell(cell);
                cell = new PdfPCell(new Phrase(this.formatearFecha(new Date()), font8b));
                cell.setBorder(0);
                table_uno.addCell(cell);
                cell = new PdfPCell(new Phrase("Gestión", font8));
                cell.setBorder(0);
                table_uno.addCell(cell);
                cell = new PdfPCell(new Phrase(":", font8));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(0);
                table_uno.addCell(cell);
                cell = new PdfPCell(new Phrase(Integer.toString(curso.getGestion().getId_gestion()), font8b));
                cell.setBorder(0);
                table_uno.addCell(cell);
                document.add(table_uno);

                if (!curso.getId_curso().equals("K") && !curso.getId_curso().equals("K1")&& !curso.getId_curso().equals("K21")&& !curso.getId_curso().equals("K12")&& !curso.getId_curso().equals("P12")&& !curso.getId_curso().equals("P1")) {
                    PdfPTable tabla = new PdfPTable(5);
                    if (parciales == 2) {
                        tabla = new PdfPTable(6);
                        int anchos_tabla1[] = {20, 12, 12, 12, 12, 12};
                        tabla.setWidths(anchos_tabla1);
                        tabla.getDefaultCell().setBorder(0);
                        tabla.setWidthPercentage(100);

                        PdfPTable tabla1 = new PdfPTable(3);
                        int tam[] = {33, 34, 33};
                        tabla1.setWidths(tam);
                        tabla1.setWidthPercentage(100);
                        cell = new PdfPCell(new Phrase("1er. Bimestre", font8b));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setBorder(0);
                        cell.setColspan(3);
                        tabla1.addCell(cell);
//                    cell = new PdfPCell(new Phrase("P.C.", font7b));
//                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    cell.setBorder(0);
//                    tabla1.addCell(cell);
                        cell = new PdfPCell(new Phrase("P.Total.", font7b));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setBorder(0);
                        cell.setColspan(3);
                        tabla1.addCell(cell);
//                    cell = new PdfPCell(new Phrase("", font7b));
//                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    cell.setBorder(0);
//                    tabla1.addCell(cell);

                        PdfPTable tabla2 = new PdfPTable(3);
                        tabla2.setWidths(tam);
                        tabla2.setWidthPercentage(100);
                        cell = new PdfPCell(new Phrase("2do. Bimestre", font8b));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setBorder(0);
                        cell.setColspan(3);
                        tabla2.addCell(cell);
//                    cell = new PdfPCell(new Phrase("P.C.", font7b));
//                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    cell.setBorder(0);
//                    tabla2.addCell(cell);
                        cell = new PdfPCell(new Phrase("P.Total.", font7b));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setBorder(0);
                        cell.setColspan(3);
                        tabla2.addCell(cell);
//                    cell = new PdfPCell(new Phrase("P.T.", font7b));
//                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    cell.setBorder(0);
//                    tabla2.addCell(cell);

                        PdfPTable tabla3 = new PdfPTable(3);
                        tabla3.setWidths(tam);
                        tabla3.setWidthPercentage(100);
                        cell = new PdfPCell(new Phrase("3er. Bimestre", font8b));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setBorder(0);
                        cell.setColspan(3);
                        tabla3.addCell(cell);
//                    cell = new PdfPCell(new Phrase("P.C.", font7b));
//                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    cell.setBorder(0);
//                    tabla3.addCell(cell);
                        cell = new PdfPCell(new Phrase("P.Total.", font7b));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setBorder(0);
                        cell.setColspan(3);
                        tabla3.addCell(cell);
//                    cell = new PdfPCell(new Phrase("P.T.", font7b));
//                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    cell.setBorder(0);
//                    tabla3.addCell(cell);

                        PdfPTable tabla4 = new PdfPTable(3);
                        tabla4.setWidths(tam);
                        tabla4.setWidthPercentage(100);
                        cell = new PdfPCell(new Phrase("4to. Bimestre", font8b));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setBorder(0);
                        cell.setColspan(3);
                        tabla4.addCell(cell);
//                    cell = new PdfPCell(new Phrase("P.C.", font7b));
//                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    cell.setBorder(0);
//                    tabla4.addCell(cell);
                        cell = new PdfPCell(new Phrase("P.Total.", font7b));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setBorder(0);
                        cell.setColspan(3);
                        tabla4.addCell(cell);
//                    cell = new PdfPCell(new Phrase("P.T.", font7b));
//                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    cell.setBorder(0);
//                    tabla4.addCell(cell);

                        PdfPTable tabla5 = new PdfPTable(3);
                        tabla5.setWidths(tam);
                        tabla5.setWidthPercentage(100);
                        cell = new PdfPCell(new Phrase("\nP.A.", font7b));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setBorder(0);
                        tabla5.addCell(cell);
                        cell = new PdfPCell(new Phrase("\nP.R.", font7b));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setBorder(0);
                        tabla5.addCell(cell);
                        cell = new PdfPCell(new Phrase("\nP.F.", font7b));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setBorder(0);
                        tabla5.addCell(cell);

//                        tabla.getDefaultCell().setGrayFill(0.9f);
//                        tabla.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
//                        tabla.addCell(new Phrase("\nMateria", font8b));
                        cell = new PdfPCell(new Phrase("\nMateria", font8b));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.getBorder();
                        tabla.addCell(cell);

                        cell = new PdfPCell(tabla1);
                        cell.getBorder();
                        tabla.addCell(cell);
                        cell = new PdfPCell(tabla2);
                        cell.getBorder();
                        tabla.addCell(cell);
                        cell = new PdfPCell(tabla3);
                        cell.getBorder();
                        tabla.addCell(cell);
                        cell = new PdfPCell(tabla4);
                        cell.getBorder();
                        tabla.addCell(cell);
                        cell = new PdfPCell(tabla5);
                        cell.getBorder();
                        tabla.addCell(cell);

                        for (int i = 0; i < curso.getMaterias().size(); i++) {
                            Materia materia = (Materia) curso.getMaterias().get(i);
                            cell = new PdfPCell(new Phrase(materia.getMateria(), font7b));
                            cell.setGrayFill(0.9f);
                            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                            cell.getBorder();
//                            cell.setBorderWidthTop(0.1f);
                            tabla.addCell(cell);
                            tabla.getDefaultCell().setGrayFill(1);

                            PdfPTable t1 = new PdfPTable(1);
                            int tam_2[] = {100};
                            t1.setWidths(tam_2);
                            t1.setWidthPercentage(100);

                            PdfPTable t2 = new PdfPTable(1);
                            t2.setWidths(tam_2);
                            t2.setWidthPercentage(100);

                            PdfPTable t3 = new PdfPTable(1);
                            t3.setWidths(tam_2);
                            t3.setWidthPercentage(100);

                            PdfPTable t4 = new PdfPTable(1);
                            t4.setWidths(tam_2);
                            t4.setWidthPercentage(100);

                            PdfPTable t5 = new PdfPTable(3);
                            t5.setWidths(tam);
                            t5.setWidthPercentage(100);

                            int suma_e1 = 0;
                            int suma_e2 = 0;
                            int suma_e3 = 0;
                            int suma_e4 = 0;
                            for (int j = 0; j < materia.getEvaluaciones().size(); j++) {
                                Evaluacion evaluacion = (Evaluacion) materia.getEvaluaciones().get(j);
                                String nota = Integer.toString(evaluacion.getNota());
                                String dps = Integer.toString(evaluacion.getDps());
                                String pt = Integer.toString(evaluacion.getNota() + evaluacion.getDps());

                                if (j == 0) {
                                    cell = new PdfPCell(new Phrase(pt, font7b));
                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    cell.setBorder(0);
                                    t1.addCell(cell);
                                    suma_e1 = Integer.parseInt(pt);
                                    /*cualitativa*/
                                    cell = new PdfPCell(new Phrase(evaluacion.getCualitativa(), font6_n));
                                    cell.setGrayFill(0.9f);
                                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                    cell.setBorder(0);
                                    t1.addCell(cell);
                                }
                                if (j == 1) {
                                    cell = new PdfPCell(new Phrase(pt, font7b));
                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    cell.setBorder(0);
                                    t2.addCell(cell);
                                    suma_e2 = Integer.parseInt(pt);
                                    /*cualitativa*/
                                    cell = new PdfPCell(new Phrase(evaluacion.getCualitativa(), font6_n));
                                    cell.setGrayFill(0.9f);
                                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                    cell.setBorder(0);
                                    t2.addCell(cell);
                                }
                                if (j == 2) {
                                    cell = new PdfPCell(new Phrase(pt, font7b));
                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    cell.setBorder(0);
                                    t3.addCell(cell);
                                    suma_e3 = Integer.parseInt(pt);
                                    /*cualitativa*/
                                    cell = new PdfPCell(new Phrase(evaluacion.getCualitativa(), font6_n));
                                    cell.setGrayFill(0.9f);
                                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                    cell.setBorder(0);
                                    t3.addCell(cell);
                                }
                                if (j == 3) {
                                    cell = new PdfPCell(new Phrase(pt, font7b));
                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    cell.setBorder(0);
                                    t4.addCell(cell);
                                    suma_e4 = Integer.parseInt(pt);
                                    /*cualitativa*/
                                    cell = new PdfPCell(new Phrase(evaluacion.getCualitativa(), font6_n));
                                    cell.setGrayFill(0.9f);
                                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                    cell.setBorder(0);
                                    t4.addCell(cell);
                                    if (materia.getEvaluaciones().size() == 4) {
                                        int promedio_anual = (int) (Math.round(((double) (suma_e1 + suma_e2 + suma_e3 + suma_e4) / 4) * Math.pow(10, 0)) / Math.pow(10, 0));
                                        cell = new PdfPCell(new Phrase(Integer.toString(promedio_anual), font7b));
                                        cell.setGrayFill(0.6f);
                                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                        cell.setBorder(0);
                                        t5.addCell(cell);
                                        cell = new PdfPCell(new Phrase(""));
                                        cell.setBorder(0);
                                        t5.addCell(cell);
                                        cell = new PdfPCell(new Phrase(""));
                                        cell.setBorder(0);
                                        t5.addCell(cell);

                                    }
                                }
                                if (j == 4) {
                                    int promedio_anual = (int) (Math.round(((double) (suma_e1 + suma_e2 + suma_e3 + suma_e4) / 4) * Math.pow(10, 0)) / Math.pow(10, 0));
                                    int promedio_final = (int) (Math.round(((double) (promedio_anual + Integer.parseInt(nota)) / 2) * Math.pow(10, 0)) / Math.pow(10, 0));
                                    cell = new PdfPCell(new Phrase(Integer.toString(promedio_anual), font7b));
                                    cell.setGrayFill(0.6f);
                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    cell.setBorder(0);
                                    t5.addCell(cell);
                                    if (!nota.equals("0")) {
                                        cell = new PdfPCell(new Phrase(nota, font7b));
                                        cell.setGrayFill(0.9f);
                                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                        cell.setBorder(0);
                                        t5.addCell(cell);
                                        cell = new PdfPCell(new Phrase(Integer.toString(promedio_final), font7b));
                                        cell.setGrayFill(0.6f);
                                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                        cell.setBorder(0);
                                        t5.addCell(cell);
                                    } else {
                                        cell = new PdfPCell(new Phrase(""));
                                        cell.setBorder(0);
                                        t5.addCell(cell);
                                        cell = new PdfPCell(new Phrase(""));
                                        cell.setBorder(0);
                                        t5.addCell(cell);
                                    }
                                }
                            }
                            cell = new PdfPCell(t1);
                            cell.getBorder();
                            tabla.addCell(cell);
                            cell = new PdfPCell(t2);
                            cell.getBorder();
                            tabla.addCell(cell);
                            cell = new PdfPCell(t3);
                            cell.getBorder();
                            tabla.addCell(cell);
                            cell = new PdfPCell(t4);
                            cell.getBorder();
                            tabla.addCell(cell);
                            cell = new PdfPCell(t5);
                            cell.getBorder();
                            tabla.addCell(cell);
                        }
                        cell = new PdfPCell(new Phrase("Promedio : ", font7b));
                        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                        cell.setBorder(0);
                        tabla.addCell(cell);

                        cell = new PdfPCell(new Phrase(Integer.toString(curso.getPtrim1()) + " Pts.", font7b));
                        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                        cell.setBorder(0);
                        tabla.addCell(cell);
                        cell = new PdfPCell(new Phrase(Integer.toString(curso.getPtrim2()) + " Pts.", font7b));
                        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                        cell.setBorder(0);
                        tabla.addCell(cell);
                        cell = new PdfPCell(new Phrase(Integer.toString(curso.getPtrim3()) + " Pts.", font7b));
                        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                        cell.setBorder(0);
                        tabla.addCell(cell);
                        cell = new PdfPCell(new Phrase(Integer.toString(curso.getPtrim4()) + " Pts.", font7b));
                        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                        cell.setBorder(0);
                        tabla.addCell(cell);

                        cell = new PdfPCell(new Phrase(" ", font7b));
                        cell.setBorder(0);
                        tabla.addCell(cell);

                        cell = new PdfPCell(new Phrase("Conducta : ", font7b));
                        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                        cell.setBorder(0);
                        tabla.addCell(cell);
                        for (int j = 0; j < curso.getConductas().size(); j++) {
                            Conducta conducta = (Conducta) curso.getConductas().get(j);
                            cell = new PdfPCell(new Phrase(conducta.getTipo_conducta(), font7b));
                            cell.setBorder(0);
                            tabla.addCell(cell);
                        }
                        cell = new PdfPCell(new Phrase(" ", font7b));
                        cell.setBorder(0);
                        tabla.addCell(cell);
//                        cell = new PdfPCell(new Phrase(" ", font7b));
//                        cell.setBorder(0);
//                        tabla.addCell(cell);

                        cell = new PdfPCell(new Phrase("Faltas : ", font7b));
                        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                        cell.setBorder(0);
                        tabla.addCell(cell);
                        for (int j = 0; j < curso.getFaltas().size(); j++) {
                            Falta falta = (Falta) curso.getFaltas().get(j);
                            cell = new PdfPCell(new Phrase(Integer.toString(falta.getFsl()), font7b));
                            cell.setBorder(0);
                            tabla.addCell(cell);
                        }
//                        cell = new PdfPCell(new Phrase(" ", font7b));
//                        cell.setBorder(0);
//                        tabla.addCell(cell);
                        cell = new PdfPCell(new Phrase(" ", font7b));
                        cell.setBorder(0);
                        tabla.addCell(cell);

                        cell = new PdfPCell(new Phrase("Atrasos : ", font7b));
                        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                        cell.setBorder(0);
                        tabla.addCell(cell);
                        for (int j = 0; j < curso.getFaltas().size(); j++) {
                            Falta falta = (Falta) curso.getFaltas().get(j);
                            cell = new PdfPCell(new Phrase(Integer.toString(falta.getRetraso()), font7b));
                            cell.setBorder(0);
                            tabla.addCell(cell);
                        }
//                        cell = new PdfPCell(new Phrase(" ", font7b));
//                        cell.setBorder(0);
//                        tabla.addCell(cell);
                        cell = new PdfPCell(new Phrase(" ", font7b));
                        cell.setBorder(0);
                        tabla.addCell(cell);

                        cell = new PdfPCell(new Phrase("Licencias : ", font7b));
                        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                        cell.setBorder(0);
                        tabla.addCell(cell);
                        for (int j = 0; j < curso.getFaltas().size(); j++) {
                            Falta falta = (Falta) curso.getFaltas().get(j);
                            cell = new PdfPCell(new Phrase(Integer.toString(falta.getFcl()), font7b));
                            cell.setBorder(0);
                            tabla.addCell(cell);
                        }
                        cell = new PdfPCell(new Phrase(" ", font7b));
                        cell.setBorder(0);
                        tabla.addCell(cell);
//                        cell = new PdfPCell(new Phrase(" ", font7b));
//                        cell.setBorder(0);
//                        tabla.addCell(cell);

                        cell = new PdfPCell(new Phrase("\n", font7b));
                        cell.setBorder(0);
                        cell.setColspan(6);
                        tabla.addCell(cell);

                        //Diagnósticos
                        /*cell = new PdfPCell(new Phrase("APRECIACIÓN CUALITATIVA", font7b));
                        cell.setBorder(0);
                        cell.setBorderWidthBottom(0.1f);
                        cell.setBorderWidthTop(0.1f);
                        cell.setGrayFill(0.9f);
                        cell.setColspan(6);
                        tabla.addCell(cell);
                        String descripcion = "";
                        for (int j = 0; j < curso.getConductas().size(); j++) {
                            descripcion = "";
                            switch (j) {
                                case 0:
                                    descripcion = "1er. Bimestre : ";
                                    break;
                                case 1:
                                    descripcion = "2do. Bimestre : ";
                                    break;
                                case 2:
                                    descripcion = "3er. Bimestre : ";
                                    break;
                                case 3:
                                    descripcion = "4to. Bimestre : ";
                                    break;
                            }
                            Conducta conducta = (Conducta) curso.getConductas().get(j);
                            cell = new PdfPCell(new Phrase(descripcion, font7b));
                            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                            cell.setBorder(0);
                            cell.setBorderWidthBottom(0.1f);
                            tabla.addCell(cell);
                            cell = new PdfPCell(new Phrase(conducta.getDiagnostico(), font7));
                            cell.setBorder(0);
                            cell.setBorderWidthBottom(0.1f);
                            cell.setColspan(5);
                            tabla.addCell(cell);
                        }*/

                        tabla.setHorizontalAlignment(Element.ALIGN_CENTER);
                        tabla.addCell(firma);
                        tabla.addCell(new Phrase("\n\n\n________________", font8b));
                        tabla.addCell(new Phrase("", font8b));
                        tabla.addCell(new Phrase("\n\n\n________________", font8b));
                        tabla.addCell(new Phrase("", font8b));
                        tabla.addCell(new Phrase("", font8b));

                        tabla.addCell(new Phrase("", font8b));
                        tabla.addCell(new Phrase("PROFESOR/A/A", font8b));
                        tabla.addCell(new Phrase("", font8b));
                        tabla.addCell(new Phrase("DIRECTORA", font8b));
                        tabla.addCell(new Phrase("", font8b));
                        tabla.addCell(new Phrase("", font8b));

                        cell = new PdfPCell(new Phrase("\n" + curso.getGestion().getLema(), font8c));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setBorder(0);
                        cell.setColspan(6);
                        tabla.addCell(cell);

                        document.add(tabla);
                                                

                    } else if (parciales == 3) {
                        tabla = new PdfPTable(5);
                        int anchos_tabla2[] = {20, 15, 15, 15, 15};
                        tabla.setWidths(anchos_tabla2);
                        tabla.getDefaultCell().setBorder(0);
                        tabla.setWidthPercentage(100);

                        PdfPTable tabla1 = new PdfPTable(3);
                        int tam[] = {33, 34, 33};
                        tabla1.setWidths(tam);
                        tabla1.setWidthPercentage(100);
                        cell = new PdfPCell(new Phrase("1er. Trimestre", font8b));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setBorder(0);
                        cell.setColspan(3);
                        tabla1.addCell(cell);

                        //cell = new PdfPCell(new Phrase("P.C.", font7b));
                        //cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                       // cell.setBorder(0);
                       // tabla1.addCell(cell);
                       // cell = new PdfPCell(new Phrase("DPS", font7b));
                       // cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                       // cell.setBorder(0);
                       // tabla1.addCell(cell);
                        cell = new PdfPCell(new Phrase("P.T.", font7b));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                       cell.setBorder(0);
                        tabla1.addCell(cell);

                        PdfPTable tabla2 = new PdfPTable(3);
                        tabla2.setWidths(tam);
                        tabla2.setWidthPercentage(100);
                        cell = new PdfPCell(new Phrase("2do. Trimestre", font8b));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setBorder(0);
                        cell.setColspan(3);
                        tabla2.addCell(cell);
                        //cell = new PdfPCell(new Phrase("P.C.", font7b));
                       // cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        //cell.setBorder(0);
                       // tabla2.addCell(cell);
                       // cell = new PdfPCell(new Phrase("DPS", font7b));
                       // cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                       // cell.setBorder(0);
                       // tabla2.addCell(cell);
                       // cell = new PdfPCell(new Phrase("P.T.", font7b));
                      //  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                       // cell.setBorder(0);
                       // tabla2.addCell(cell);

                        PdfPTable tabla3 = new PdfPTable(3);
                        tabla3.setWidths(tam);
                        tabla3.setWidthPercentage(100);
                        cell = new PdfPCell(new Phrase("3er. Trimestre", font8b));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setBorder(0);
                        cell.setColspan(3);
                        tabla3.addCell(cell);
                        //cell = new PdfPCell(new Phrase("P.C.", font7b));
                        //cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        //cell.setBorder(0);
                        //tabla3.addCell(cell);
                        cell = new PdfPCell(new Phrase(" ", font7b));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setBorder(0);
                        tabla3.addCell(cell);
                        //cell = new PdfPCell(new Phrase("P.T.", font7b));
                        //cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        //cell.setBorder(0);
                        //tabla3.addCell(cell);

                        PdfPTable tabla4 = new PdfPTable(3);
                        tabla4.setWidths(tam);
                        tabla4.setWidthPercentage(100);
                        cell = new PdfPCell(new Phrase("\nP.A.", font7b));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setBorder(0);
                        tabla4.addCell(cell);
                        cell = new PdfPCell(new Phrase("\nP.R.", font7b));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setBorder(0);
                        tabla4.addCell(cell);
                        cell = new PdfPCell(new Phrase("\nP.F.", font7b));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setBorder(0);
                        tabla4.addCell(cell);

                        tabla.getDefaultCell().setGrayFill(0.9f);
                        tabla.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                        tabla.addCell(new Phrase("\nMateria", font8b));
                        tabla.addCell(tabla1);
                        tabla.addCell(tabla2);
                        tabla.addCell(tabla3);
                        tabla.addCell(tabla4);
                        for (int i = 0; i < curso.getMaterias().size(); i++) {
                            Materia materia = (Materia) curso.getMaterias().get(i);
                            cell = new PdfPCell(new Phrase(materia.getMateria(), font7b));
                            cell.setGrayFill(0.9f);
                            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                            cell.setBorder(0);
                            cell.setBorderWidthTop(0.1f);
                            tabla.addCell(cell);
                            tabla.getDefaultCell().setGrayFill(1);

                            PdfPTable t1 = new PdfPTable(3);
                            t1.setWidths(tam);
                            t1.setWidthPercentage(100);

                            PdfPTable t2 = new PdfPTable(3);
                            t2.setWidths(tam);
                            t2.setWidthPercentage(100);

                            PdfPTable t3 = new PdfPTable(3);
                            t3.setWidths(tam);
                            t3.setWidthPercentage(100);

                            PdfPTable t4 = new PdfPTable(3);
                            t4.setWidths(tam);
                            t4.setWidthPercentage(100);

                            int suma_e1 = 0;
                            int suma_e2 = 0;
                            int suma_e3 = 0;
                            for (int j = 0; j < materia.getEvaluaciones().size(); j++) {
                                Evaluacion evaluacion = (Evaluacion) materia.getEvaluaciones().get(j);
                                String nota = Integer.toString(evaluacion.getNota());
                                String dps = Integer.toString(evaluacion.getDps());
                                String pt = Integer.toString(evaluacion.getNota() + evaluacion.getDps());

                                if (j == 0) {
                                    cell = new PdfPCell(new Phrase(nota, font7b));
                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    cell.setBorder(0);
                                    t1.addCell(cell);
                                    //cell = new PdfPCell(new Phrase(dps, font7b));
                                   // cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                  //  cell.setBorder(0);
                                   // t1.addCell(cell);
                                  // cell = new PdfPCell(new Phrase(pt, font7b));
                                   // cell.setGrayFill(0.9f);
                                   // cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    //cell.setBorder(0);
                                   // t1.addCell(cell);
                                   // suma_e1 = Integer.parseInt(pt);
                                    /*cualitativa*/
                                    cell = new PdfPCell(new Phrase(evaluacion.getCualitativa(), font6_n));
                                    cell.setGrayFill(0.9f);
                                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                    cell.setColspan(3);
                                    cell.setBorder(0);
                                    t1.addCell(cell);
                                }
                                if (j == 1) {
                                    //cell = new PdfPCell(new Phrase(nota, font7b));
                                    //cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    //cell.setBorder(0);
                                    //t2.addCell(cell);
                                   // cell = new PdfPCell(new Phrase(dps, font7b));
                                    //cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    //cell.setBorder(0);
                                    //t2.addCell(cell);
                                    //cell = new PdfPCell(new Phrase(pt, font7b));
                                    //cell.setGrayFill(0.9f);
                                    //cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    //cell.setBorder(0);
                                    t2.addCell(cell);
                                    suma_e2 = Integer.parseInt(pt);
                                    /*cualitativa*/
                                    cell = new PdfPCell(new Phrase(evaluacion.getCualitativa(), font6_n));
                                    cell.setGrayFill(0.9f);
                                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                    cell.setColspan(3);
                                    cell.setBorder(0);
                                    t2.addCell(cell);
                                }
                                if (j == 2) {
                                    cell = new PdfPCell(new Phrase(nota, font7b));
                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    cell.setBorder(0);
                                    t3.addCell(cell);
                                    cell = new PdfPCell(new Phrase(dps, font7b));
                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    cell.setBorder(0);
                                    t3.addCell(cell);
                                    cell = new PdfPCell(new Phrase(pt, font7b));
                                    cell.setGrayFill(0.9f);
                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    cell.setBorder(0);
                                    t3.addCell(cell);
                                    suma_e3 = Integer.parseInt(pt);
                                    /*cualitativa*/
                                    cell = new PdfPCell(new Phrase(evaluacion.getCualitativa(), font6_n));
                                    cell.setGrayFill(0.9f);
                                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                    cell.setColspan(3);
                                    cell.setBorder(0);
                                    t3.addCell(cell);
                                    if (materia.getEvaluaciones().size() == 3) {
                                        int promedio_anual = (int) (Math.round(((double) (suma_e1 + suma_e2 + suma_e3) / 3) * Math.pow(10, 0)) / Math.pow(10, 0));
                                        cell = new PdfPCell(new Phrase(Integer.toString(promedio_anual), font7b));
                                        cell.setGrayFill(0.6f);
                                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                        cell.setBorder(0);
                                        t4.addCell(cell);
                                        cell = new PdfPCell(new Phrase(""));
                                        cell.setBorder(0);
                                        t4.addCell(cell);
                                        cell = new PdfPCell(new Phrase(""));
                                        cell.setBorder(0);
                                        t4.addCell(cell);

                                    }
                                }
                                if (j == 3) {
                                    int promedio_anual = (int) (Math.round(((double) (suma_e1 + suma_e2 + suma_e3) / 3) * Math.pow(10, 0)) / Math.pow(10, 0));
                                    int promedio_final = (int) (Math.round(((double) (promedio_anual + Integer.parseInt(nota)) / 2) * Math.pow(10, 0)) / Math.pow(10, 0));
                                    cell = new PdfPCell(new Phrase(Integer.toString(promedio_anual), font7b));
                                    cell.setGrayFill(0.6f);
                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    cell.setBorder(0);
                                    t4.addCell(cell);
                                    if (!nota.equals("0")) {
                                        cell = new PdfPCell(new Phrase(nota, font7b));
                                        cell.setGrayFill(0.9f);
                                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                        cell.setBorder(0);
                                        t4.addCell(cell);
                                        cell = new PdfPCell(new Phrase(Integer.toString(promedio_final), font7b));
                                        cell.setGrayFill(0.6f);
                                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                        cell.setBorder(0);
                                        t4.addCell(cell);
                                    } else {
                                        cell = new PdfPCell(new Phrase(""));
                                        cell.setBorder(0);
                                        t4.addCell(cell);
                                        cell = new PdfPCell(new Phrase(""));
                                        cell.setBorder(0);
                                        t4.addCell(cell);
                                    }
                                }
                            }
                            cell = new PdfPCell(t1);
                            cell.setBorder(0);
                            cell.setBorderWidthTop(0.1f);
                            tabla.addCell(cell);
                            cell = new PdfPCell(t2);
                            cell.setBorder(0);
                            cell.setBorderWidthTop(0.1f);
                            tabla.addCell(cell);
                            cell = new PdfPCell(t3);
                            cell.setBorder(0);
                            cell.setBorderWidthTop(0.1f);
                            tabla.addCell(cell);
                            cell = new PdfPCell(t4);
                            cell.setBorder(0);
                            cell.setBorderWidthTop(0.1f);
                            tabla.addCell(cell);
                        }
                        cell = new PdfPCell(new Phrase("Promedio : ", font7b));
                        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                        cell.setBorder(0);
                        tabla.addCell(cell);

                        cell = new PdfPCell(new Phrase(Integer.toString(curso.getPtrim1()) + " Pts.", font7b));
                        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                        cell.setBorder(0);
                        tabla.addCell(cell);
                        cell = new PdfPCell(new Phrase(Integer.toString(curso.getPtrim2()) + " Pts.", font7b));
                        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                        cell.setBorder(0);
                        tabla.addCell(cell);
                        cell = new PdfPCell(new Phrase(Integer.toString(curso.getPtrim3()) + " Pts.", font7b));
                        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                        cell.setBorder(0);
                        tabla.addCell(cell);

                        cell = new PdfPCell(new Phrase(" ", font7b));
                        cell.setBorder(0);
                        tabla.addCell(cell);

                        //cell = new PdfPCell(new Phrase("Conducta : ", font7b));
                       // cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                       // cell.setBorder(0);
                       // tabla.addCell(cell);
                        //for (int j = 0; j < curso.getConductas().size(); j++) {
                         //   Conducta conducta = (Conducta) curso.getConductas().get(j);
                         //   cell = new PdfPCell(new Phrase(conducta.getTipo_conducta(), font7b));
                          //  cell.setBorder(0);
                         //   tabla.addCell(cell);
                       // }
                       // cell = new PdfPCell(new Phrase(" ", font7b));
                       // cell.setBorder(0);
                       // tabla.addCell(cell);

                       // cell = new PdfPCell(new Phrase("Faltas : ", font7b));
                       // cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                       // cell.setBorder(0);
                       // tabla.addCell(cell);
                        //for (int j = 0; j < curso.getFaltas().size(); j++) {
                           // Falta falta = (Falta) curso.getFaltas().get(j);
                          //  cell = new PdfPCell(new Phrase(Integer.toString(falta.getFsl()), font7b));
                           // cell.setBorder(0);
                            //tabla.addCell(cell);
                      //  }
                       // cell = new PdfPCell(new Phrase(" ", font7b));
                       // cell.setBorder(0);
                       // tabla.addCell(cell);

                       // cell = new PdfPCell(new Phrase("Atrasos : ", font7b));
                       // cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                       // cell.setBorder(0);
                       // tabla.addCell(cell);
                       // for (int j = 0; j < curso.getFaltas().size(); j++) {
                        //    Falta falta = (Falta) curso.getFaltas().get(j);
                        //    cell = new PdfPCell(new Phrase(Integer.toString(falta.getRetraso()), font7b));
                         //   cell.setBorder(0);
                           // tabla.addCell(cell);
                       // }
                      //  cell = new PdfPCell(new Phrase(" ", font7b));
                      //  cell.setBorder(0);
                      //  tabla.addCell(cell);

                       /* cell = new PdfPCell(new Phrase("Licencias : ", font7b));
                        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                        cell.setBorder(0);
                        tabla.addCell(cell);
                        for (int j = 0; j < curso.getFaltas().size(); j++) {
                            Falta falta = (Falta) curso.getFaltas().get(j);
                            cell = new PdfPCell(new Phrase(Integer.toString(falta.getFcl()), font7b));
                            cell.setBorder(0);
                            tabla.addCell(cell);
                        }
                        cell = new PdfPCell(new Phrase(" ", font7b));
                        cell.setBorder(0);
                        tabla.addCell(cell);
*/
                        tabla.setHorizontalAlignment(Element.ALIGN_CENTER);
                        tabla.addCell(new Phrase("", font8b));
                        tabla.addCell(new Phrase("\n\n\n________________", font8b));
                        tabla.addCell(new Phrase("", font8b));
                        tabla.addCell(new Phrase("\n\n\n________________", font8b));
                        tabla.addCell(new Phrase("", font8b));

                        tabla.addCell(new Phrase("", font8b));
                        tabla.addCell(new Phrase("PROFESOR/A", font8b));
                        tabla.addCell(new Phrase("", font8b));
                        tabla.addCell(new Phrase("DIRECTORA", font8b));
                        tabla.addCell(new Phrase("", font8b));

                        cell = new PdfPCell(new Phrase("\n" + curso.getGestion().getLema(), font8c));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setBorder(0);
                        cell.setColspan(5);
                        tabla.addCell(cell);

                        document.add(tabla);
                    }
                } else {
                    if (curso.getId_curso().equals("K") || curso.getId_curso().equals("K1")|| curso.getId_curso().equals("K12")|| curso.getId_curso().equals("K21")|| curso.getId_curso().equals("P1")|| curso.getId_curso().equals("P12")) {
                        PdfPTable tabla = new PdfPTable(5);
                        if (parciales == 2) {
                            tabla = new PdfPTable(5);
                            int anchos_tabla1[] = {20, 20, 20, 20, 20};
                            tabla.setWidths(anchos_tabla1);
                            tabla.getDefaultCell().setBorder(0);
                            tabla.setWidthPercentage(100);

                            PdfPTable tabla1 = new PdfPTable(3);
                            int tam[] = {33, 34, 33};
                            tabla1.setWidths(tam);
                            tabla1.setWidthPercentage(100);
                            cell = new PdfPCell(new Phrase("1er. Bimestre", font8b));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            cell.setBorder(0);
                            cell.setColspan(3);
                            tabla1.addCell(cell);
//                    cell = new PdfPCell(new Phrase("P.C.", font7b));
//                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    cell.setBorder(0);
//                    tabla1.addCell(cell);
                            cell = new PdfPCell(new Phrase("P.Total.", font7b));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            cell.setBorder(0);
                            cell.setColspan(3);
                            tabla1.addCell(cell);
//                    cell = new PdfPCell(new Phrase("", font7b));
//                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    cell.setBorder(0);
//                    tabla1.addCell(cell);

                            PdfPTable tabla2 = new PdfPTable(3);
                            tabla2.setWidths(tam);
                            tabla2.setWidthPercentage(100);
                            cell = new PdfPCell(new Phrase("2do. Bimestre", font8b));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            cell.setBorder(0);
                            cell.setColspan(3);
                            tabla2.addCell(cell);
//                    cell = new PdfPCell(new Phrase("P.C.", font7b));
//                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    cell.setBorder(0);
//                    tabla2.addCell(cell);
                            cell = new PdfPCell(new Phrase("P.Total.", font7b));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            cell.setBorder(0);
                            cell.setColspan(3);
                            tabla2.addCell(cell);
//                    cell = new PdfPCell(new Phrase("P.T.", font7b));
//                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    cell.setBorder(0);
//                    tabla2.addCell(cell);

                            PdfPTable tabla3 = new PdfPTable(3);
                            tabla3.setWidths(tam);
                            tabla3.setWidthPercentage(100);
                            cell = new PdfPCell(new Phrase("3er. Bimestre", font8b));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            cell.setBorder(0);
                            cell.setColspan(3);
                            tabla3.addCell(cell);
//                    cell = new PdfPCell(new Phrase("P.C.", font7b));
//                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    cell.setBorder(0);
//                    tabla3.addCell(cell);
                            cell = new PdfPCell(new Phrase("P.Total.", font7b));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            cell.setBorder(0);
                            cell.setColspan(3);
                            tabla3.addCell(cell);
//                    cell = new PdfPCell(new Phrase("P.T.", font7b));
//                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    cell.setBorder(0);
//                    tabla3.addCell(cell);

                            PdfPTable tabla4 = new PdfPTable(3);
                            tabla4.setWidths(tam);
                            tabla4.setWidthPercentage(100);
                            cell = new PdfPCell(new Phrase("4to. Bimestre", font8b));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            cell.setBorder(0);
                            cell.setColspan(3);
                            tabla4.addCell(cell);
//                    cell = new PdfPCell(new Phrase("P.C.", font7b));
//                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    cell.setBorder(0);
//                    tabla4.addCell(cell);
                            cell = new PdfPCell(new Phrase("P.Total.", font7b));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            cell.setBorder(0);
                            cell.setColspan(3);
                            tabla4.addCell(cell);
//                    cell = new PdfPCell(new Phrase("P.T.", font7b));
//                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    cell.setBorder(0);
//                    tabla4.addCell(cell);

                            /* PdfPTable tabla5 = new PdfPTable(3);
                             tabla5.setWidths(tam);
                             tabla5.setWidthPercentage(100);
                             cell = new PdfPCell(new Phrase("\nP.A.", font7b));
                             cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                             cell.setBorder(0);
                             tabla5.addCell(cell);
                             cell = new PdfPCell(new Phrase("\nP.R.", font7b));
                             cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                             cell.setBorder(0);
                             tabla5.addCell(cell);
                             cell = new PdfPCell(new Phrase("\nP.F.", font7b));
                             cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                             cell.setBorder(0);
                             tabla5.addCell(cell);*/
//                        tabla.getDefaultCell().setGrayFill(0.9f);
//                        tabla.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
//                        tabla.addCell(new Phrase("\nMateria", font8b));
                            cell = new PdfPCell(new Phrase("\nMateria", font8b));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            cell.getBorder();
                            tabla.addCell(cell);

                            cell = new PdfPCell(tabla1);
                            cell.getBorder();
                            tabla.addCell(cell);
                            cell = new PdfPCell(tabla2);
                            cell.getBorder();
                            tabla.addCell(cell);
                            cell = new PdfPCell(tabla3);
                            cell.getBorder();
                            tabla.addCell(cell);
                            cell = new PdfPCell(tabla4);
                            cell.getBorder();
                            tabla.addCell(cell);
                            /*cell = new PdfPCell(tabla5);
                             cell.getBorder();
                             tabla.addCell(cell);*/

                            for (int i = 0; i < curso.getMaterias().size(); i++) {
                                Materia materia = (Materia) curso.getMaterias().get(i);
                                cell = new PdfPCell(new Phrase(materia.getMateria(), font7b));
                                cell.setGrayFill(0.9f);
                                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                cell.getBorder();
//                            cell.setBorderWidthTop(0.1f);
                                tabla.addCell(cell);
                                tabla.getDefaultCell().setGrayFill(1);

                                PdfPTable t1 = new PdfPTable(1);
                                int tam_2[] = {100};
                                t1.setWidths(tam_2);
                                t1.setWidthPercentage(100);

                                PdfPTable t2 = new PdfPTable(1);
                                t2.setWidths(tam_2);
                                t2.setWidthPercentage(100);

                                PdfPTable t3 = new PdfPTable(1);
                                t3.setWidths(tam_2);
                                t3.setWidthPercentage(100);

                                PdfPTable t4 = new PdfPTable(1);
                                t4.setWidths(tam_2);
                                t4.setWidthPercentage(100);

                                PdfPTable t5 = new PdfPTable(3);
                                t5.setWidths(tam);
                                t5.setWidthPercentage(100);

                                int suma_e1 = 0;
                                int suma_e2 = 0;
                                int suma_e3 = 0;
                                int suma_e4 = 0;
                                for (int j = 0; j < materia.getEvaluaciones().size(); j++) {
                                    Evaluacion evaluacion = (Evaluacion) materia.getEvaluaciones().get(j);
                                    String nota = Integer.toString(evaluacion.getNota());
                                    String dps = Integer.toString(evaluacion.getDps());
                                    String pt = Integer.toString(evaluacion.getNota() + evaluacion.getDps());

                                    if (j == 0) {
                                        /*cell = new PdfPCell(new Phrase(pt, font7b));
                                         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                         cell.setBorder(0);
                                         t1.addCell(cell);
                                         suma_e1 = Integer.parseInt(pt);*/
                                        /*cualitativa*/
                                        cell = new PdfPCell(new Phrase(evaluacion.getCualitativa(), font6_n));
                                        cell.setGrayFill(0.9f);
                                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                        cell.setBorder(0);
                                        t1.addCell(cell);
                                    }
                                    if (j == 1) {
                                        /* cell = new PdfPCell(new Phrase(pt, font7b));
                                         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                         cell.setBorder(0);
                                         t2.addCell(cell);
                                         suma_e2 = Integer.parseInt(pt);*/
                                        /*cualitativa*/
                                        cell = new PdfPCell(new Phrase(evaluacion.getCualitativa(), font6_n));
                                        cell.setGrayFill(0.9f);
                                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                        cell.setBorder(0);
                                        t2.addCell(cell);
                                    }
                                    if (j == 2) {
                                        /*cell = new PdfPCell(new Phrase(pt, font7b));
                                         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                         cell.setBorder(0);
                                         t3.addCell(cell);
                                         suma_e3 = Integer.parseInt(pt);*/
                                        /*cualitativa*/
                                        cell = new PdfPCell(new Phrase(evaluacion.getCualitativa(), font6_n));
                                        cell.setGrayFill(0.9f);
                                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                        cell.setBorder(0);
                                        t3.addCell(cell);
                                    }
                                    if (j == 3) {
                                        /*cell = new PdfPCell(new Phrase(pt, font7b));
                                         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                         cell.setBorder(0);
                                         t4.addCell(cell);
                                         suma_e4 = Integer.parseInt(pt);*/
                                        /*cualitativa*/
                                        cell = new PdfPCell(new Phrase(evaluacion.getCualitativa(), font6_n));
                                        cell.setGrayFill(0.9f);
                                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                        cell.setBorder(0);
                                        t4.addCell(cell);
                                        if (materia.getEvaluaciones().size() == 4) {
                                            int promedio_anual = (int) (Math.round(((double) (suma_e1 + suma_e2 + suma_e3 + suma_e4) / 4) * Math.pow(10, 0)) / Math.pow(10, 0));
                                            cell = new PdfPCell(new Phrase(Integer.toString(promedio_anual), font7b));
                                            cell.setGrayFill(0.6f);
                                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                            cell.setBorder(0);
                                            t5.addCell(cell);
                                            cell = new PdfPCell(new Phrase(""));
                                            cell.setBorder(0);
                                            t5.addCell(cell);
                                            cell = new PdfPCell(new Phrase(""));
                                            cell.setBorder(0);
                                            t5.addCell(cell);

                                        }
                                    }
                                    if (j == 4) {
                                        int promedio_anual = (int) (Math.round(((double) (suma_e1 + suma_e2 + suma_e3 + suma_e4) / 4) * Math.pow(10, 0)) / Math.pow(10, 0));
                                        int promedio_final = (int) (Math.round(((double) (promedio_anual + Integer.parseInt(nota)) / 2) * Math.pow(10, 0)) / Math.pow(10, 0));
                                        cell = new PdfPCell(new Phrase(Integer.toString(promedio_anual), font7b));
                                        cell.setGrayFill(0.6f);
                                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                        cell.setBorder(0);
                                        t5.addCell(cell);
                                        if (!nota.equals(" ")) {
                                            cell = new PdfPCell(new Phrase(nota, font7b));
                                            cell.setGrayFill(0.9f);
                                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                            cell.setBorder(0);
                                            t5.addCell(cell);
                                            cell = new PdfPCell(new Phrase(Integer.toString(promedio_final), font7b));
                                            cell.setGrayFill(0.6f);
                                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                            cell.setBorder(0);
                                            t5.addCell(cell);
                                        } else {
                                            cell = new PdfPCell(new Phrase(""));
                                            cell.setBorder(0);
                                            t5.addCell(cell);
                                            cell = new PdfPCell(new Phrase(""));
                                            cell.setBorder(0);
                                            t5.addCell(cell);
                                        }
                                    }
                                }
                                cell = new PdfPCell(t1);
                                cell.getBorder();
                                tabla.addCell(cell);
                                cell = new PdfPCell(t2);
                                cell.getBorder();
                                tabla.addCell(cell);
                                cell = new PdfPCell(t3);
                                cell.getBorder();
                                tabla.addCell(cell);
                                cell = new PdfPCell(t4);
                                cell.getBorder();
                                tabla.addCell(cell);
                                /*cell = new PdfPCell(t5);
                                 cell.getBorder();
                                 tabla.addCell(cell);*/
                            }
                            /*cell = new PdfPCell(new Phrase("Promedio : ", font7b));
                            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                            cell.setBorder(0);
                            tabla.addCell(cell);

                            cell = new PdfPCell(new Phrase(Integer.toString(curso.getPtrim1()) + " Pts.", font7b));
                            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                            cell.setBorder(0);
                            tabla.addCell(cell);
                            cell = new PdfPCell(new Phrase(Integer.toString(curso.getPtrim2()) + " Pts.", font7b));
                            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                            cell.setBorder(0);
                            tabla.addCell(cell);
                            cell = new PdfPCell(new Phrase(Integer.toString(curso.getPtrim3()) + " Pts.", font7b));
                            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                            cell.setBorder(0);
                            tabla.addCell(cell);*/
                            /*cell = new PdfPCell(new Phrase(Integer.toString(curso.getPtrim4()) + " Pts.", font7b));
                             cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                             cell.setBorder(0);
                             tabla.addCell(cell);*/

                          /*  cell = new PdfPCell(new Phrase(" ", font7b));
                            cell.setBorder(0);
                            tabla.addCell(cell);

                            cell = new PdfPCell(new Phrase("Conducta : ", font7b));
                            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                            cell.setBorder(0);
                            tabla.addCell(cell);
                            for (int j = 0; j < curso.getConductas().size(); j++) {
                                Conducta conducta = (Conducta) curso.getConductas().get(j);
                                cell = new PdfPCell(new Phrase(conducta.getTipo_conducta(), font7b));
                                cell.setBorder(0);
                                tabla.addCell(cell);
                            }*/
                            /*comentado para ordenar faltas
                            */
                            
//                            cell = new PdfPCell(new Phrase(" ", font7b));
//                            cell.setBorder(0);
//                            tabla.addCell(cell);
//                        cell = new PdfPCell(new Phrase(" ", font7b));
//                        cell.setBorder(0);
//                        tabla.addCell(cell);

                            cell = new PdfPCell(new Phrase("Faltas : ", font7b));
                            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                            cell.setBorder(0);
                            tabla.addCell(cell);
                            for (int j = 0; j < curso.getFaltas().size(); j++) {
                                Falta falta = (Falta) curso.getFaltas().get(j);
                                cell = new PdfPCell(new Phrase(Integer.toString(falta.getFsl()), font7b));
                                cell.setBorder(0);
                                tabla.addCell(cell);
                            }
//                        cell = new PdfPCell(new Phrase(" ", font7b));
//                        cell.setBorder(0);
//                        tabla.addCell(cell);
                      /*  cell = new PdfPCell(new Phrase(" ", font7b));
                             cell.setBorder(0);
                             tabla.addCell(cell);*/

                            cell = new PdfPCell(new Phrase("Atrasos : ", font7b));
                            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                            cell.setBorder(0);
                            tabla.addCell(cell);
                            for (int j = 0; j < curso.getFaltas().size(); j++) {
                                Falta falta = (Falta) curso.getFaltas().get(j);
                                cell = new PdfPCell(new Phrase(Integer.toString(falta.getRetraso()), font7b));
                                cell.setBorder(0);
                                tabla.addCell(cell);
                            }
//                        cell = new PdfPCell(new Phrase(" ", font7b));
//                        cell.setBorder(0);
//                        tabla.addCell(cell);
                        /*cell = new PdfPCell(new Phrase(" ", font7b));
                             cell.setBorder(0);
                             tabla.addCell(cell);*/

                            cell = new PdfPCell(new Phrase("Licencias : ", font7b));
                            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                            cell.setBorder(0);
                            tabla.addCell(cell);
                            for (int j = 0; j < curso.getFaltas().size(); j++) {
                                Falta falta = (Falta) curso.getFaltas().get(j);
                                cell = new PdfPCell(new Phrase(Integer.toString(falta.getFcl()), font7b));
                                cell.setBorder(0);
                                tabla.addCell(cell);
                            }
                            /* cell = new PdfPCell(new Phrase(" ", font7b));
                             cell.setBorder(0);
                             tabla.addCell(cell);*/

                            /* 
                             for (int i = 0; i < 4; i++) {
                             tabla.addCell(new Phrase("Primer bimestre : ", font8b));
                             cell = new PdfPCell(new Phrase("texto por bimetres", font7b));
                             cell.setBorder(0);
                             cell.setColspan(4);
                             tabla.addCell(cell);
                             }
                             */
                            /* tabla.addCell(new Phrase("2", font8b));
                             tabla.addCell(new Phrase("3", font8b));
                             tabla.addCell(new Phrase("4", font8b));
                             tabla.addCell(new Phrase("5", font8b));*/
                            /*cell = new PdfPCell(new Phrase(" ", font7b));
                             cell.setBorder(0);
                             tabla.addCell(cell);*/
                            tabla.setHorizontalAlignment(Element.ALIGN_CENTER);
                            tabla.addCell(new Phrase("", font8b));
                            tabla.addCell(new Phrase("\n\n\n________________", font8b));
                            tabla.addCell(new Phrase("", font8b));
                            tabla.addCell(new Phrase("\n\n\n________________", font8b));
                            tabla.addCell(new Phrase("", font8b));
                            //tabla.addCell(new Phrase("", font8b));

                            tabla.addCell(new Phrase("", font8b));
                            tabla.addCell(new Phrase("PROFESOR/A", font8b));
                            tabla.addCell(new Phrase("", font8b));
                            tabla.addCell(new Phrase("DIRECTORA", font8b));
                            tabla.addCell(new Phrase("", font8b));
                            //tabla.addCell(new Phrase("", font8b));

                            /* cell = new PdfPCell(new Phrase("\n" + curso.getGestion().getLema(), font8c));
                             cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                             cell.setBorder(0);
                             cell.setColspan(6);
                             tabla.addCell(cell);*/
                            document.add(tabla);
                        } else if (parciales == 3) {
                            tabla = new PdfPTable(5);
                            int anchos_tabla2[] = {20, 15, 15, 15, 15};
                            tabla.setWidths(anchos_tabla2);
                            tabla.getDefaultCell().setBorder(0);
                            tabla.setWidthPercentage(100);

                            PdfPTable tabla1 = new PdfPTable(3);
                            int tam[] = {33, 34, 33};
                            tabla1.setWidths(tam);
                            tabla1.setWidthPercentage(100);
                            cell = new PdfPCell(new Phrase("1er. TrimestreTTT", font8b));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            cell.setBorder(0);
                            cell.setColspan(3);
                            tabla1.addCell(cell);

                           // cell = new PdfPCell(new Phrase("P.C.", font7b));
                            //cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            //cell.setBorder(0);
                            //tabla1.addCell(cell);
                            //cell = new PdfPCell(new Phrase("DPS", font7b));
                            //cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            //cell.setBorder(0);
                            //tabla1.addCell(cell);
                            //cell = new PdfPCell(new Phrase("P.T.", font7b));
                            //cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            //cell.setBorder(0);
                            //tabla1.addCell(cell);

                            PdfPTable tabla2 = new PdfPTable(3);
                            tabla2.setWidths(tam);
                            tabla2.setWidthPercentage(100);
                            cell = new PdfPCell(new Phrase("2do. Trimestre", font8b));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            cell.setBorder(0);
                            cell.setColspan(3);
                            tabla2.addCell(cell);
                            /*cell = new PdfPCell(new Phrase("P.C.", font7b));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            cell.setBorder(0);
                            tabla2.addCell(cell);
                            cell = new PdfPCell(new Phrase("DPS", font7b));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            cell.setBorder(0);
                            tabla2.addCell(cell);
                            cell = new PdfPCell(new Phrase("P.T.", font7b));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            cell.setBorder(0);
                            tabla2.addCell(cell);*/

                            PdfPTable tabla3 = new PdfPTable(3);
                            tabla3.setWidths(tam);
                            tabla3.setWidthPercentage(100);
                            cell = new PdfPCell(new Phrase("3er. Trimestre", font8b));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            cell.setBorder(0);
                            cell.setColspan(3);
                            tabla3.addCell(cell);
                           /* cell = new PdfPCell(new Phrase("P.C.", font7b));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            cell.setBorder(0);
                            tabla3.addCell(cell);
                            cell = new PdfPCell(new Phrase("DPS", font7b));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            cell.setBorder(0);
                            tabla3.addCell(cell);
                            cell = new PdfPCell(new Phrase("P.T.", font7b));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            cell.setBorder(0);
                            tabla3.addCell(cell);*/

                            PdfPTable tabla4 = new PdfPTable(3);
                            tabla4.setWidths(tam);
                            tabla4.setWidthPercentage(100);
                            cell = new PdfPCell(new Phrase("   ", font7b));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            cell.setBorder(0);
                            tabla4.addCell(cell);
                            cell = new PdfPCell(new Phrase("   ", font7b));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            cell.setBorder(0);
                            tabla4.addCell(cell);
                            cell = new PdfPCell(new Phrase("   ", font7b));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            cell.setBorder(0);
                            tabla4.addCell(cell);

                            tabla.getDefaultCell().setGrayFill(0.9f);
                            tabla.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                            tabla.addCell(new Phrase("\nMateria", font8b));
                            tabla.addCell(tabla1);
                            tabla.addCell(tabla2);
                            tabla.addCell(tabla3);
                            tabla.addCell(tabla4);
                            for (int i = 0; i < curso.getMaterias().size(); i++) {
                                Materia materia = (Materia) curso.getMaterias().get(i);
                                cell = new PdfPCell(new Phrase(materia.getMateria(), font7b));
                                cell.setGrayFill(0.9f);
                                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                cell.setBorder(0);
                                cell.setBorderWidthTop(0.1f);
                                tabla.addCell(cell);
                                tabla.getDefaultCell().setGrayFill(1);

                                PdfPTable t1 = new PdfPTable(3);
                                t1.setWidths(tam);
                                t1.setWidthPercentage(100);

                                PdfPTable t2 = new PdfPTable(3);
                                t2.setWidths(tam);
                                t2.setWidthPercentage(100);

                                PdfPTable t3 = new PdfPTable(3);
                                t3.setWidths(tam);
                                t3.setWidthPercentage(100);

                                PdfPTable t4 = new PdfPTable(3);
                                t4.setWidths(tam);
                                t4.setWidthPercentage(100);

                                int suma_e1 = 0;
                                int suma_e2 = 0;
                                int suma_e3 = 0;
                                for (int j = 0; j < materia.getEvaluaciones().size(); j++) {
                                    Evaluacion evaluacion = (Evaluacion) materia.getEvaluaciones().get(j);
                                    String nota = Integer.toString(evaluacion.getNota());
                                    String dps = Integer.toString(evaluacion.getDps());
                                    String pt = Integer.toString(evaluacion.getNota() + evaluacion.getDps());

                                    if (j == 0) {
                                        /*cell = new PdfPCell(new Phrase(nota, font7b));
                                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                        cell.setBorder(0);
                                        t1.addCell(cell);
                                        cell = new PdfPCell(new Phrase(dps, font7b));
                                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                        cell.setBorder(0);
                                        t1.addCell(cell);
                                        cell = new PdfPCell(new Phrase(pt, font7b));
                                        cell.setGrayFill(0.9f);
                                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                        cell.setBorder(0);
                                        t1.addCell(cell);
                                        suma_e1 = Integer.parseInt(pt);*/
                                        /*cualitativa*/
                                        cell = new PdfPCell(new Phrase(evaluacion.getCualitativa(), font6_n));
                                        cell.setGrayFill(0.9f);
                                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                        cell.setColspan(3);
                                        cell.setBorder(0);
                                        t1.addCell(cell);
                                    }
                                    if (j == 1) {
                                        cell = new PdfPCell(new Phrase(nota, font7b));
                                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                        cell.setBorder(0);
                                        t2.addCell(cell);
                                        cell = new PdfPCell(new Phrase(dps, font7b));
                                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                        cell.setBorder(0);
                                        t2.addCell(cell);
                                        cell = new PdfPCell(new Phrase(pt, font7b));
                                        cell.setGrayFill(0.9f);
                                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                        cell.setBorder(0);
                                        t2.addCell(cell);
                                        suma_e2 = Integer.parseInt(pt);
                                        /*cualitativa*/
                                        cell = new PdfPCell(new Phrase(evaluacion.getCualitativa(), font6_n));
                                        cell.setGrayFill(0.9f);
                                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                        cell.setColspan(3);
                                        cell.setBorder(0);
                                        t2.addCell(cell);
                                    }
                                    if (j == 2) {
                                        cell = new PdfPCell(new Phrase(nota, font7b));
                                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                        cell.setBorder(0);
                                        t3.addCell(cell);
                                        cell = new PdfPCell(new Phrase(dps, font7b));
                                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                        cell.setBorder(0);
                                        t3.addCell(cell);
                                        cell = new PdfPCell(new Phrase(pt, font7b));
                                        cell.setGrayFill(0.9f);
                                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                        cell.setBorder(0);
                                        t3.addCell(cell);
                                        suma_e3 = Integer.parseInt(pt);
                                        /*cualitativa*/
                                        cell = new PdfPCell(new Phrase(evaluacion.getCualitativa(), font6_n));
                                        cell.setGrayFill(0.9f);
                                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                        cell.setColspan(3);
                                        cell.setBorder(0);
                                        t3.addCell(cell);
                                        if (materia.getEvaluaciones().size() == 3) {
                                            int promedio_anual = (int) (Math.round(((double) (suma_e1 + suma_e2 + suma_e3) / 3) * Math.pow(10, 0)) / Math.pow(10, 0));
                                            cell = new PdfPCell(new Phrase(Integer.toString(promedio_anual), font7b));
                                            cell.setGrayFill(0.6f);
                                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                            cell.setBorder(0);
                                            t4.addCell(cell);
                                            cell = new PdfPCell(new Phrase(""));
                                            cell.setBorder(0);
                                            t4.addCell(cell);
                                            cell = new PdfPCell(new Phrase(""));
                                            cell.setBorder(0);
                                            t4.addCell(cell);

                                        }
                                    }
                                    if (j == 3) {
                                        int promedio_anual = (int) (Math.round(((double) (suma_e1 + suma_e2 + suma_e3) / 3) * Math.pow(10, 0)) / Math.pow(10, 0));
                                        int promedio_final = (int) (Math.round(((double) (promedio_anual + Integer.parseInt(nota)) / 2) * Math.pow(10, 0)) / Math.pow(10, 0));
                                        cell = new PdfPCell(new Phrase(Integer.toString(promedio_anual), font7b));
                                        cell.setGrayFill(0.6f);
                                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                        cell.setBorder(0);
                                        t4.addCell(cell);
                                        if (!nota.equals("0")) {
                                            cell = new PdfPCell(new Phrase(nota, font7b));
                                            cell.setGrayFill(0.9f);
                                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                            cell.setBorder(0);
                                            t4.addCell(cell);
                                            cell = new PdfPCell(new Phrase(Integer.toString(promedio_final), font7b));
                                            cell.setGrayFill(0.6f);
                                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                            cell.setBorder(0);
                                            t4.addCell(cell);
                                        } else {
                                            cell = new PdfPCell(new Phrase(""));
                                            cell.setBorder(0);
                                            t4.addCell(cell);
                                            cell = new PdfPCell(new Phrase(""));
                                            cell.setBorder(0);
                                            t4.addCell(cell);
                                        }
                                    }
                                }
                                cell = new PdfPCell(t1);
                                cell.setBorder(0);
                                cell.setBorderWidthTop(0.1f);
                                tabla.addCell(cell);
                                cell = new PdfPCell(t2);
                                cell.setBorder(0);
                                cell.setBorderWidthTop(0.1f);
                                tabla.addCell(cell);
                                cell = new PdfPCell(t3);
                                cell.setBorder(0);
                                cell.setBorderWidthTop(0.1f);
                                tabla.addCell(cell);
                                cell = new PdfPCell(t4);
                                cell.setBorder(0);
                                cell.setBorderWidthTop(0.1f);
                                tabla.addCell(cell);
                            }
                          /*  cell = new PdfPCell(new Phrase("Promedio : ", font7b));
                            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                            cell.setBorder(0);
                            tabla.addCell(cell);

                            cell = new PdfPCell(new Phrase(Integer.toString(curso.getPtrim1()) + " Pts.", font7b));
                            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                            cell.setBorder(0);
                            tabla.addCell(cell);
                            cell = new PdfPCell(new Phrase(Integer.toString(curso.getPtrim2()) + " Pts.", font7b));
                            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                            cell.setBorder(0);
                            tabla.addCell(cell);
                            cell = new PdfPCell(new Phrase(Integer.toString(curso.getPtrim3()) + " Pts.", font7b));
                            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                            cell.setBorder(0);
                            tabla.addCell(cell);

                            cell = new PdfPCell(new Phrase(" ", font7b));
                            cell.setBorder(0);
                            tabla.addCell(cell);

                            cell = new PdfPCell(new Phrase("Conducta : ", font7b));
                            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                            cell.setBorder(0);
                            tabla.addCell(cell);
                            for (int j = 0; j < curso.getConductas().size(); j++) {
                                Conducta conducta = (Conducta) curso.getConductas().get(j);
                                cell = new PdfPCell(new Phrase(conducta.getTipo_conducta(), font7b));
                                cell.setBorder(0);
                                tabla.addCell(cell);
                            }
                            cell = new PdfPCell(new Phrase(" ", font7b));
                            cell.setBorder(0);
                            tabla.addCell(cell);

                            cell = new PdfPCell(new Phrase("Faltas : ", font7b));
                            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                            cell.setBorder(0);
                            tabla.addCell(cell);
                            for (int j = 0; j < curso.getFaltas().size(); j++) {
                                Falta falta = (Falta) curso.getFaltas().get(j);
                                cell = new PdfPCell(new Phrase(Integer.toString(falta.getFsl()), font7b));
                                cell.setBorder(0);
                                tabla.addCell(cell);
                            }
                            cell = new PdfPCell(new Phrase(" ", font7b));
                            cell.setBorder(0);
                            tabla.addCell(cell);

                            cell = new PdfPCell(new Phrase("Atrasos : ", font7b));
                            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                            cell.setBorder(0);
                            tabla.addCell(cell);
                            for (int j = 0; j < curso.getFaltas().size(); j++) {
                                Falta falta = (Falta) curso.getFaltas().get(j);
                                cell = new PdfPCell(new Phrase(Integer.toString(falta.getRetraso()), font7b));
                                cell.setBorder(0);
                                tabla.addCell(cell);
                            }
                            cell = new PdfPCell(new Phrase(" ", font7b));
                            cell.setBorder(0);
                            tabla.addCell(cell);

                            cell = new PdfPCell(new Phrase("Licencias : ", font7b));
                            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                            cell.setBorder(0);
                            tabla.addCell(cell);
                            for (int j = 0; j < curso.getFaltas().size(); j++) {
                                Falta falta = (Falta) curso.getFaltas().get(j);
                                cell = new PdfPCell(new Phrase(Integer.toString(falta.getFcl()), font7b));
                                cell.setBorder(0);
                                tabla.addCell(cell);
                            }
                            cell = new PdfPCell(new Phrase(" ", font7b));
                            cell.setBorder(0);
                            tabla.addCell(cell);*/

                            tabla.setHorizontalAlignment(Element.ALIGN_CENTER);
                            tabla.addCell(new Phrase("", font8b));
                            tabla.addCell(new Phrase("\n\n\n________________", font8b));
                            tabla.addCell(new Phrase("", font8b));
                            tabla.addCell(new Phrase("\n\n\n________________", font8b));
                            tabla.addCell(new Phrase("", font8b));

                            tabla.addCell(new Phrase("", font8b));
                            tabla.addCell(new Phrase("PROFESOR/A", font8b));
                            tabla.addCell(new Phrase("", font8b));
                            tabla.addCell(new Phrase("DIRECTORA", font8b));
                            tabla.addCell(new Phrase("", font8b));

                            cell = new PdfPCell(new Phrase("\n" + curso.getGestion().getLema(), font8c));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            cell.setBorder(0);
                            cell.setColspan(5);
                            tabla.addCell(cell);

                            document.add(tabla);
                        }
                    } else {
                        PdfPTable table = new PdfPTable(4);
                        int twidths[] = {25, 25, 25, 25}; // percentage
                        table.getDefaultCell().setBorder(0);
                        table.setWidths(twidths);
                        table.setWidthPercentage(100);

                        table.getDefaultCell().setGrayFill(0.9f);
                        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(new Phrase("Materia", font8b));
                        table.addCell(new Phrase("1er. Trimestre", font8b));
                        table.addCell(new Phrase("2do. Trimestre", font8b));
                        table.addCell(new Phrase("3er. Trimestre", font8b));

                        for (int i = 0; i < curso.getMaterias().size(); i++) {
                            Materia materia = (Materia) curso.getMaterias().get(i);
                            cell = new PdfPCell(new Phrase(materia.getMateria(), font7b));
                            cell.setGrayFill(0.9f);
                            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                            cell.setBorder(0);
                            table.addCell(cell);
                            table.getDefaultCell().setGrayFill(1);

                            PdfPTable t1 = new PdfPTable(3);
                            int t1widths[] = {33, 34, 33};
                            t1.setWidths(t1widths);
                            t1.setWidthPercentage(100);

                            PdfPTable t2 = new PdfPTable(3);
                            t2.setWidths(t1widths);
                            t2.setWidthPercentage(100);

                            PdfPTable t3 = new PdfPTable(3);
                            t3.setWidths(t1widths);
                            t3.setWidthPercentage(100);

                            for (int j = 0; j < materia.getEvaluaciones().size(); j++) {
                                Evaluacion evaluacion = (Evaluacion) materia.getEvaluaciones().get(j);
                                if (j == 0) {
                                    cell = new PdfPCell(new Phrase(evaluacion.getCualitativa(), font7b));
                                    cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
                                    cell.setBorder(0);
                                    cell.setColspan(3);
                                    t1.addCell(cell);
                                }
                                if (j == 1) {
                                    cell = new PdfPCell(new Phrase(evaluacion.getCualitativa(), font7b));
                                    cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
                                    cell.setBorder(0);
                                    cell.setColspan(3);
                                    t2.addCell(cell);
                                }
                                if (j == 2) {
                                    cell = new PdfPCell(new Phrase(evaluacion.getCualitativa(), font7b));
                                    cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
                                    cell.setBorder(0);
                                    cell.setColspan(3);
                                    t3.addCell(cell);
                                }
                            }
                            table.addCell(t1);
                            table.addCell(t2);
                            table.addCell(t3);
                        }

                        if (curso.getConductas().size() != 0) {
                            cell = new PdfPCell(new Phrase("Diagnóstico incial : ", font7b));
                            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                            cell.setBorder(0);
                            table.addCell(cell);
                            Conducta conducta = (Conducta) curso.getConductas().get(0);
                            cell = new PdfPCell(new Phrase(conducta.getDiagnostico(), font8));
                            cell.setColspan(3);
                            cell.setBorder(0);
                            table.addCell(cell);

                            cell = new PdfPCell(new Phrase("Diagnóstico final : ", font7b));
                            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                            cell.setBorder(0);
                            table.addCell(cell);
                            conducta = (Conducta) curso.getConductas().get(1);
                            cell = new PdfPCell(new Phrase(conducta.getDiagnostico(), font8));
                            cell.setColspan(3);
                            cell.setBorder(0);
                            table.addCell(cell);
                        }

                        cell = new PdfPCell(new Phrase("Faltas : ", font7b));
                        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                        cell.setBorder(0);
                        table.addCell(cell);
                        for (int j = 0; j < curso.getFaltas().size(); j++) {
                            Falta falta = (Falta) curso.getFaltas().get(j);
                            cell = new PdfPCell(new Phrase(Integer.toString(falta.getFsl()), font7b));
                            cell.setBorder(0);
                            table.addCell(cell);
                        }

                        cell = new PdfPCell(new Phrase("Atrasos : ", font7b));
                        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                        cell.setBorder(0);
                        table.addCell(cell);
                        for (int j = 0; j < curso.getFaltas().size(); j++) {
                            Falta falta = (Falta) curso.getFaltas().get(j);
                            cell = new PdfPCell(new Phrase(Integer.toString(falta.getRetraso()), font7b));
                            cell.setBorder(0);
                            table.addCell(cell);
                        }

                        cell = new PdfPCell(new Phrase("Licencias : ", font7b));
                        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                        cell.setBorder(0);
                        table.addCell(cell);
                        for (int j = 0; j < curso.getFaltas().size(); j++) {
                            Falta falta = (Falta) curso.getFaltas().get(j);
                            cell = new PdfPCell(new Phrase(Integer.toString(falta.getFcl()), font7b));
                            cell.setBorder(0);
                            table.addCell(cell);
                        }

                        table.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(new Phrase("", font8b));
                        table.addCell(new Phrase("\n\n\n__________________", font8b));
                        table.addCell(new Phrase("", font8b));
                        table.addCell(new Phrase("\n\n\n__________________", font8b));

                        table.addCell(new Phrase("", font8b));
                        table.addCell(new Phrase("PROFESOR/A", font8b));
                        table.addCell(new Phrase("", font8b));
                        table.addCell(new Phrase("DIRECTORA", font8b));

                        cell = new PdfPCell(new Phrase("\n" + curso.getGestion().getLema(), font8c));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setBorder(0);
                        cell.setColspan(4);
                        table.addCell(cell);

                        document.add(table);
                    }
                    p = new Paragraph(new Phrase("SIGAA Sistema Integrado de Gestión Académico - Administrativa " + curso.getGestion().getId_gestion(), font6));
                    document.add(p);
                }
            }
        } catch (Exception de) {
            de.printStackTrace();
        }

        document.close();
        return archivo;
    }
//    private String boletinesCursoPdf(Curso curso_gen, String id_gestion) {
//        String dir = System.getenv("SIGAA_HOME") + "/documentos/boletines/";
//        
//        String archivo = "Boletines_" + curso_gen.getDesc_curso() + "(" + this.formatoFechaNom(new Date()) + ").pdf";
////        String archivo = "Boletines_" + curso_gen.getCurso() + "_de_" + curso_gen.getCiclo() + "(" + this.formatoFechaNom(new Date()) + ").pdf";
//        Font font8 = FontFactory.getFont(FontFactory.HELVETICA, 8);
//        Font font8c = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE, 8);
//        Font font8b = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8);
//        Font font7b = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 7);
//        Font font6 = FontFactory.getFont(FontFactory.HELVETICA, 7);
//        Font font12 = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10);
//
//        Document document = new Document(PageSize.LEGAL);
//        try {
//            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dir + archivo));
//            float width = document.getPageSize().getWidth();
//            document.open();
//            for (int x = 0; x <= curso_gen.getEstudiantes().size(); x++) {
//                if (x > 0) {
//                    document.newPage();
//                }
//                Estudiante estudiante = (Estudiante) curso_gen.getEstudiantes().get(x);
//                Curso curso = new Curso();
//                curso.setId_curso(curso_gen.getId_curso());
//                curso.setId_gestion(Integer.parseInt(id_gestion));
//                curso.setId_estudiante(estudiante.getId_estudiante());
//                curso.setId_gestion(Integer.parseInt(id_gestion));
//                curso = this.sigaa.getCursoByIdCursoAndIdEstudiantesAndIdGestion(curso);
//                Gestion gestion = (Gestion) this.sigaa.getBuscarGestion(Integer.parseInt(id_gestion)).get(0);
//                curso.setGestion(gestion);
//
//                Image png = Image.getInstance(System.getenv("SIGAA_HOME") + "/imagenes/iconos_sigaa/santa_teresa_logo.png");
//                png.setAbsolutePosition(550, 945);
//                png.scalePercent(25);
//                document.add(png);
//
//                document.add(new Phrase("COLEGIO \"SANTA TERESA\"", font8b));
////            document.add(Chunk.NEWLINE);
////            document.add(new Phrase("La Paz - Bolivia", font8b));
//                document.add(Chunk.NEWLINE);
//                Paragraph p = new Paragraph(new Phrase("BOLETÍN DE CALIFICACIONES \n", font12));
//                p.setAlignment(Element.ALIGN_CENTER);
//                document.add(p);
//
//                PdfPCell cell = null;
//                PdfPTable table_uno = new PdfPTable(6);
//                int headerwidths[] = {10, 5, 50, 7, 5, 23}; // percentage
//                table_uno.getDefaultCell().setBorder(0);
//                table_uno.setWidths(headerwidths);
//                table_uno.setWidthPercentage(100);
//
//                cell = new PdfPCell(new Phrase("Estudiante", font8));
//                cell.setBorder(0);
//                table_uno.addCell(cell);
//                cell = new PdfPCell(new Phrase(":", font8));
//                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                cell.setBorder(0);
//                table_uno.addCell(cell);
//                cell = new PdfPCell(new Phrase(estudiante.getPaterno() + " " + estudiante.getMaterno() + " " + estudiante.getNombres(), font8b));
//                cell.setBorder(0);
//                table_uno.addCell(cell);
//                cell = new PdfPCell(new Phrase("Curso", font8));
//                cell.setBorder(0);
//                table_uno.addCell(cell);
//                cell = new PdfPCell(new Phrase(":", font8));
//                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                cell.setBorder(0);
//                table_uno.addCell(cell);
//                cell = new PdfPCell(new Phrase(curso.getCurso() + " de " + curso.getCiclo(), font8b));
//                cell.setBorder(0);
//                table_uno.addCell(cell);
//
//                cell = new PdfPCell(new Phrase("Fecha", font8));
//                cell.setBorder(0);
//                table_uno.addCell(cell);
//                cell = new PdfPCell(new Phrase(":", font8));
//                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                cell.setBorder(0);
//                table_uno.addCell(cell);
//                cell = new PdfPCell(new Phrase(this.formatearFecha(new Date()), font8b));
//                cell.setBorder(0);
//                table_uno.addCell(cell);
//                cell = new PdfPCell(new Phrase("Gestión", font8));
//                cell.setBorder(0);
//                table_uno.addCell(cell);
//                cell = new PdfPCell(new Phrase(":", font8));
//                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                cell.setBorder(0);
//                table_uno.addCell(cell);
//                cell = new PdfPCell(new Phrase(Integer.toString(curso.getGestion().getId_gestion()), font8b));
//                cell.setBorder(0);
//                table_uno.addCell(cell);
//                document.add(table_uno);
//
//                if (!curso.getId_curso().equals("K")) {
//                    PdfPTable table = new PdfPTable(5);
//                    int twidths[] = {20, 15, 15, 15, 15}; // percentage
//                    table.getDefaultCell().setBorder(0);
//                    table.setWidths(twidths);
//                    table.setWidthPercentage(100);
//
//                    PdfPTable table1 = new PdfPTable(3);
//                    int t1widths[] = {33, 34, 33};
//                    table1.setWidths(t1widths);
//                    table1.setWidthPercentage(100);
//
//                    cell = new PdfPCell(new Phrase("1er. Trimestre", font8b));
//                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    cell.setBorder(0);
//                    cell.setColspan(3);
//                    table1.addCell(cell);
//                    cell = new PdfPCell(new Phrase("P.C.", font7b));
//                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    cell.setBorder(0);
//                    table1.addCell(cell);
//                    cell = new PdfPCell(new Phrase("DPS", font7b));
//                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    cell.setBorder(0);
//                    table1.addCell(cell);
//                    cell = new PdfPCell(new Phrase("P.T.", font7b));
//                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    cell.setBorder(0);
//                    table1.addCell(cell);
//
//                    PdfPTable table2 = new PdfPTable(3);
////            table.getDefaultCell().setBorder(0);
//                    table2.setWidths(t1widths);
//                    table2.setWidthPercentage(100);
//                    cell = new PdfPCell(new Phrase("2do. Trimestre", font8b));
//                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    cell.setBorder(0);
//                    cell.setColspan(3);
//                    table2.addCell(cell);
//                    cell = new PdfPCell(new Phrase("P.C.", font7b));
//                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    cell.setBorder(0);
//                    table2.addCell(cell);
//                    cell = new PdfPCell(new Phrase("DPS", font7b));
//                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    cell.setBorder(0);
//                    table2.addCell(cell);
//                    cell = new PdfPCell(new Phrase("P.T.", font7b));
//                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    cell.setBorder(0);
//                    table2.addCell(cell);
//
//                    PdfPTable table3 = new PdfPTable(3);
//                    table3.setWidths(t1widths);
//                    table3.setWidthPercentage(100);
//                    cell = new PdfPCell(new Phrase("3er. Trimestre", font8b));
//                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    cell.setBorder(0);
//                    cell.setColspan(3);
//                    table3.addCell(cell);
//                    cell = new PdfPCell(new Phrase("P.C.", font7b));
//                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    cell.setBorder(0);
//                    table3.addCell(cell);
//                    cell = new PdfPCell(new Phrase("DPS", font7b));
//                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    cell.setBorder(0);
//                    table3.addCell(cell);
//                    cell = new PdfPCell(new Phrase("P.T.", font7b));
//                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    cell.setBorder(0);
//                    table3.addCell(cell);
//
//                    PdfPTable table4 = new PdfPTable(3);
//                    table4.setWidths(t1widths);
//                    table4.setWidthPercentage(100);
//                    cell = new PdfPCell(new Phrase("\nP.A.", font7b));
//                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    cell.setBorder(0);
//                    table4.addCell(cell);
//                    cell = new PdfPCell(new Phrase("\nP.R.", font7b));
//                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    cell.setBorder(0);
//                    table4.addCell(cell);
//                    cell = new PdfPCell(new Phrase("\nP.F.", font7b));
//                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    cell.setBorder(0);
//                    table4.addCell(cell);
//
//                    table.getDefaultCell().setGrayFill(0.9f);
//                    table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
//                    table.addCell(new Phrase("\nMateria", font8b));
//                    table.addCell(table1);
//                    table.addCell(table2);
//                    table.addCell(table3);
//                    table.addCell(table4);
//
//                    for (int i = 0; i < curso.getMaterias().size(); i++) {
//                        Materia materia = (Materia) curso.getMaterias().get(i);
//                        cell = new PdfPCell(new Phrase(materia.getMateria(), font7b));
//                        cell.setGrayFill(0.9f);
//                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//                        cell.setBorder(0);
//                        table.addCell(cell);
//                        table.getDefaultCell().setGrayFill(1);
//
//                        PdfPTable t1 = new PdfPTable(3);
//                        t1.setWidths(t1widths);
//                        t1.setWidthPercentage(100);
//
//                        PdfPTable t2 = new PdfPTable(3);
//                        t2.setWidths(t1widths);
//                        t2.setWidthPercentage(100);
//
//                        PdfPTable t3 = new PdfPTable(3);
//                        t3.setWidths(t1widths);
//                        t3.setWidthPercentage(100);
//
//                        PdfPTable t4 = new PdfPTable(3);
//                        t4.setWidths(t1widths);
//                        t4.setWidthPercentage(100);
//
//                        int suma_e1 = 0;
//                        int suma_e2 = 0;
//                        int suma_e3 = 0;
//                        for (int j = 0; j < materia.getEvaluaciones().size(); j++) {
//                            Evaluacion evaluacion = (Evaluacion) materia.getEvaluaciones().get(j);
//                            String nota = Integer.toString(evaluacion.getNota());
//                            String dps = Integer.toString(evaluacion.getDps());
//                            String pt = Integer.toString(evaluacion.getNota() + evaluacion.getDps());
//
//                            if (j == 0) {
//                                cell = new PdfPCell(new Phrase(nota, font7b));
//                                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                                cell.setBorder(0);
//                                t1.addCell(cell);
//                                cell = new PdfPCell(new Phrase(dps, font7b));
//                                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                                cell.setBorder(0);
//                                t1.addCell(cell);
//                                cell = new PdfPCell(new Phrase(pt, font7b));
//                                cell.setGrayFill(0.9f);
//                                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                                cell.setBorder(0);
//                                t1.addCell(cell);
//                                suma_e1 = Integer.parseInt(pt);
//                            }
//                            if (j == 1) {
//                                cell = new PdfPCell(new Phrase(nota, font7b));
//                                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                                cell.setBorder(0);
//                                t2.addCell(cell);
//                                cell = new PdfPCell(new Phrase(dps, font7b));
//                                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                                cell.setBorder(0);
//                                t2.addCell(cell);
//                                cell = new PdfPCell(new Phrase(pt, font7b));
//                                cell.setGrayFill(0.9f);
//                                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                                cell.setBorder(0);
//                                t2.addCell(cell);
//                                suma_e2 = Integer.parseInt(pt);
//                            }
//                            if (j == 2) {
//                                cell = new PdfPCell(new Phrase(nota, font7b));
//                                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                                cell.setBorder(0);
//                                t3.addCell(cell);
//                                cell = new PdfPCell(new Phrase(dps, font7b));
//                                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                                cell.setBorder(0);
//                                t3.addCell(cell);
//                                cell = new PdfPCell(new Phrase(pt, font7b));
//                                cell.setGrayFill(0.9f);
//                                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                                cell.setBorder(0);
//                                t3.addCell(cell);
//                                suma_e3 = Integer.parseInt(pt);
//                                if (materia.getEvaluaciones().size() == 3) {
//                                    int promedio_anual = (int) (Math.round(((double) (suma_e1 + suma_e2 + suma_e3) / 3) * Math.pow(10, 0)) / Math.pow(10, 0));
//                                    cell = new PdfPCell(new Phrase(Integer.toString(promedio_anual), font7b));
//                                    cell.setGrayFill(0.6f);
//                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                                    cell.setBorder(0);
//                                    t4.addCell(cell);
//                                    cell = new PdfPCell(new Phrase(""));
//                                    cell.setBorder(0);
//                                    t4.addCell(cell);
//                                    cell = new PdfPCell(new Phrase(""));
//                                    cell.setBorder(0);
//                                    t4.addCell(cell);
//
//                                }
//                            }
//                            if (j == 3) {
//                                int promedio_anual = (int) (Math.round(((double) (suma_e1 + suma_e2 + suma_e3) / 3) * Math.pow(10, 0)) / Math.pow(10, 0));
//                                int promedio_final = (int) (Math.round(((double) (promedio_anual + Integer.parseInt(nota)) / 2) * Math.pow(10, 0)) / Math.pow(10, 0));
//                                cell = new PdfPCell(new Phrase(Integer.toString(promedio_anual), font7b));
//                                cell.setGrayFill(0.6f);
//                                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                                cell.setBorder(0);
//                                t4.addCell(cell);
//                                if (!nota.equals("0")) {
//                                    cell = new PdfPCell(new Phrase(nota, font7b));
//                                    cell.setGrayFill(0.9f);
//                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                                    cell.setBorder(0);
//                                    t4.addCell(cell);
//                                    cell = new PdfPCell(new Phrase(Integer.toString(promedio_final), font7b));
//                                    cell.setGrayFill(0.6f);
//                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                                    cell.setBorder(0);
//                                    t4.addCell(cell);
//                                } else {
//                                    cell = new PdfPCell(new Phrase(""));
//                                    cell.setBorder(0);
//                                    t4.addCell(cell);
//                                    cell = new PdfPCell(new Phrase(""));
//                                    cell.setBorder(0);
//                                    t4.addCell(cell);
//                                }
//                            }
//                        }
//                        table.addCell(t1);
//                        table.addCell(t2);
//                        table.addCell(t3);
//                        table.addCell(t4);
//                    }
//
//                    cell = new PdfPCell(new Phrase("Promedio : ", font7b));
//                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                    cell.setBorder(0);
//                    table.addCell(cell);
//
//                    cell = new PdfPCell(new Phrase(Integer.toString(curso.getPtrim1()) + " Pts.", font7b));
//                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                    cell.setBorder(0);
//                    table.addCell(cell);
//                    cell = new PdfPCell(new Phrase(Integer.toString(curso.getPtrim2()) + " Pts.", font7b));
//                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                    cell.setBorder(0);
//                    table.addCell(cell);
//                    cell = new PdfPCell(new Phrase(Integer.toString(curso.getPtrim3()) + " Pts.", font7b));
//                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                    cell.setBorder(0);
//                    table.addCell(cell);
//
//                    cell = new PdfPCell(new Phrase(" ", font7b));
//                    cell.setBorder(0);
//                    table.addCell(cell);
//
//                    cell = new PdfPCell(new Phrase("Conducta : ", font7b));
//                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                    cell.setBorder(0);
//                    table.addCell(cell);
//                    for (int j = 0; j < curso.getConductas().size(); j++) {
//                        Conducta conducta = (Conducta) curso.getConductas().get(j);
//                        cell = new PdfPCell(new Phrase(conducta.getTipo_conducta(), font7b));
//                        cell.setBorder(0);
//                        table.addCell(cell);
//                    }
//                    cell = new PdfPCell(new Phrase(" ", font7b));
//                    cell.setBorder(0);
//                    table.addCell(cell);
//
//                    cell = new PdfPCell(new Phrase("Faltas : ", font7b));
//                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                    cell.setBorder(0);
//                    table.addCell(cell);
//                    for (int j = 0; j < curso.getFaltas().size(); j++) {
//                        Falta falta = (Falta) curso.getFaltas().get(j);
//                        cell = new PdfPCell(new Phrase(Integer.toString(falta.getFsl()), font7b));
//                        cell.setBorder(0);
//                        table.addCell(cell);
//                    }
//                    cell = new PdfPCell(new Phrase(" ", font7b));
//                    cell.setBorder(0);
//                    table.addCell(cell);
//
//                    cell = new PdfPCell(new Phrase("Atrasos : ", font7b));
//                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                    cell.setBorder(0);
//                    table.addCell(cell);
//                    for (int j = 0; j < curso.getFaltas().size(); j++) {
//                        Falta falta = (Falta) curso.getFaltas().get(j);
//                        cell = new PdfPCell(new Phrase(Integer.toString(falta.getRetraso()), font7b));
//                        cell.setBorder(0);
//                        table.addCell(cell);
//                    }
//                    cell = new PdfPCell(new Phrase(" ", font7b));
//                    cell.setBorder(0);
//                    table.addCell(cell);
//
//                    cell = new PdfPCell(new Phrase("Licencias : ", font7b));
//                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                    cell.setBorder(0);
//                    table.addCell(cell);
//                    for (int j = 0; j < curso.getFaltas().size(); j++) {
//                        Falta falta = (Falta) curso.getFaltas().get(j);
//                        cell = new PdfPCell(new Phrase(Integer.toString(falta.getFcl()), font7b));
//                        cell.setBorder(0);
//                        table.addCell(cell);
//                    }
//                    cell = new PdfPCell(new Phrase(" ", font7b));
//                    cell.setBorder(0);
//                    table.addCell(cell);
//
//                    table.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    table.addCell(new Phrase("", font8b));
//                    table.addCell(new Phrase("\n\n\n________________", font8b));
//                    table.addCell(new Phrase("", font8b));
//                    table.addCell(new Phrase("\n\n\n________________", font8b));
//                    table.addCell(new Phrase("", font8b));
//
//                    table.addCell(new Phrase("", font8b));
//                    table.addCell(new Phrase("SECRETARIA", font8b));
//                    table.addCell(new Phrase("", font8b));
//                    table.addCell(new Phrase("COO. ACADEMICA", font8b));
//                    table.addCell(new Phrase("", font8b));
//
//                    cell = new PdfPCell(new Phrase("\n" + curso.getGestion().getLema(), font8c));
//                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    cell.setBorder(0);
//                    cell.setColspan(5);
//                    table.addCell(cell);
//
//                    document.add(table);
//                } else {
//                    PdfPTable table = new PdfPTable(4);
//                    int twidths[] = {25, 25, 25, 25}; // percentage
//                    table.getDefaultCell().setBorder(0);
//                    table.setWidths(twidths);
//                    table.setWidthPercentage(100);
//
//                    table.getDefaultCell().setGrayFill(0.9f);
//                    table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
//                    table.addCell(new Phrase("Materia", font8b));
//                    table.addCell(new Phrase("1er. Trimestre", font8b));
//                    table.addCell(new Phrase("2do. Trimestre", font8b));
//                    table.addCell(new Phrase("3er. Trimestre", font8b));
//
//                    for (int i = 0; i < curso.getMaterias().size(); i++) {
//                        Materia materia = (Materia) curso.getMaterias().get(i);
//                        cell = new PdfPCell(new Phrase(materia.getMateria(), font7b));
//                        cell.setGrayFill(0.9f);
//                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//                        cell.setBorder(0);
//                        table.addCell(cell);
//                        table.getDefaultCell().setGrayFill(1);
//
//                        PdfPTable t1 = new PdfPTable(3);
//                        int t1widths[] = {33, 34, 33};
//                        t1.setWidths(t1widths);
//                        t1.setWidthPercentage(100);
//
//                        PdfPTable t2 = new PdfPTable(3);
//                        t2.setWidths(t1widths);
//                        t2.setWidthPercentage(100);
//
//                        PdfPTable t3 = new PdfPTable(3);
//                        t3.setWidths(t1widths);
//                        t3.setWidthPercentage(100);
//
//                        for (int j = 0; j < materia.getEvaluaciones().size(); j++) {
//                            Evaluacion evaluacion = (Evaluacion) materia.getEvaluaciones().get(j);
//                            if (j == 0) {
//                                cell = new PdfPCell(new Phrase(evaluacion.getCualitativa(), font7b));
//                                cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
//                                cell.setBorder(0);
//                                cell.setColspan(3);
//                                t1.addCell(cell);
//                            }
//                            if (j == 1) {
//                                cell = new PdfPCell(new Phrase(evaluacion.getCualitativa(), font7b));
//                                cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
//                                cell.setBorder(0);
//                                cell.setColspan(3);
//                                t2.addCell(cell);
//                            }
//                            if (j == 2) {
//                                cell = new PdfPCell(new Phrase(evaluacion.getCualitativa(), font7b));
//                                cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
//                                cell.setBorder(0);
//                                cell.setColspan(3);
//                                t3.addCell(cell);
//                            }
//                        }
//                        table.addCell(t1);
//                        table.addCell(t2);
//                        table.addCell(t3);
//                    }
//
//                    if (curso.getConductas().size() != 0) {
//                        cell = new PdfPCell(new Phrase("Diagnóstico incial : ", font7b));
//                        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                        cell.setBorder(0);
//                        table.addCell(cell);
//                        Conducta conducta = (Conducta) curso.getConductas().get(0);
//                        cell = new PdfPCell(new Phrase(conducta.getDiagnostico(), font8));
//                        cell.setColspan(3);
//                        cell.setBorder(0);
//                        table.addCell(cell);
//
//                        cell = new PdfPCell(new Phrase("Diagnóstico final : ", font7b));
//                        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                        cell.setBorder(0);
//                        table.addCell(cell);
//                        conducta = (Conducta) curso.getConductas().get(1);
//                        cell = new PdfPCell(new Phrase(conducta.getDiagnostico(), font8));
//                        cell.setColspan(3);
//                        cell.setBorder(0);
//                        table.addCell(cell);
//                    }
//
//                    cell = new PdfPCell(new Phrase("Faltas : ", font7b));
//                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                    cell.setBorder(0);
//                    table.addCell(cell);
//                    for (int j = 0; j < curso.getFaltas().size(); j++) {
//                        Falta falta = (Falta) curso.getFaltas().get(j);
//                        cell = new PdfPCell(new Phrase(Integer.toString(falta.getFsl()), font7b));
//                        cell.setBorder(0);
//                        table.addCell(cell);
//                    }
//
//                    cell = new PdfPCell(new Phrase("Atrasos : ", font7b));
//                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                    cell.setBorder(0);
//                    table.addCell(cell);
//                    for (int j = 0; j < curso.getFaltas().size(); j++) {
//                        Falta falta = (Falta) curso.getFaltas().get(j);
//                        cell = new PdfPCell(new Phrase(Integer.toString(falta.getRetraso()), font7b));
//                        cell.setBorder(0);
//                        table.addCell(cell);
//                    }
//
//                    cell = new PdfPCell(new Phrase("Licencias : ", font7b));
//                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                    cell.setBorder(0);
//                    table.addCell(cell);
//                    for (int j = 0; j < curso.getFaltas().size(); j++) {
//                        Falta falta = (Falta) curso.getFaltas().get(j);
//                        cell = new PdfPCell(new Phrase(Integer.toString(falta.getFcl()), font7b));
//                        cell.setBorder(0);
//                        table.addCell(cell);
//                    }
//
//                    table.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    table.addCell(new Phrase("", font8b));
//                    table.addCell(new Phrase("\n\n\n__________________", font8b));
//                    table.addCell(new Phrase("", font8b));
//                    table.addCell(new Phrase("\n\n\n__________________", font8b));
//
//                    table.addCell(new Phrase("", font8b));
//                    table.addCell(new Phrase("SECRETARIA", font8b));
//                    table.addCell(new Phrase("", font8b));
//                    table.addCell(new Phrase("COO. ACADEMICA", font8b));
//
//                    cell = new PdfPCell(new Phrase("\n" + curso.getGestion().getLema(), font8c));
//                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    cell.setBorder(0);
//                    cell.setColspan(4);
//                    table.addCell(cell);
//
//                    document.add(table);
//                }
//                p = new Paragraph(new Phrase("SIGAA Sistema Integrado de Gestión Académico - Administrativa " + curso.getGestion().getId_gestion(), font6));
//                document.add(p);
//            }
//        } catch (Exception de) {
//            de.printStackTrace();
//        }
//
//        document.close();
//        return archivo;
//    }
}

