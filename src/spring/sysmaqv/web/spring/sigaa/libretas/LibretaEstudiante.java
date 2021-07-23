/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.sysmaqv.web.spring.sigaa.libretas;

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
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import spring.sysmaqv.domain.Conducta;
import spring.sysmaqv.domain.Curso;
import spring.sysmaqv.domain.Estudiante;
import spring.sysmaqv.domain.Evaluacion;
import spring.sysmaqv.domain.Falta;
import spring.sysmaqv.domain.Materia;
import spring.sysmaqv.domain.Personal;
import spring.sysmaqv.domain.logic.SigaaInterface;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.util.WebUtils;

/**
 *
 * @author Marco Antonio
 */
public class LibretaEstudiante implements Controller {

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
            String id_estudiante = request.getParameter("id_estudiante");
            String id_curso = request.getParameter("id_curso");
            Curso curso = new Curso();
            curso.setId_curso(id_curso);
            curso.setId_gestion(Integer.parseInt(id_gestion));
            curso.setId_estudiante(id_estudiante);
            curso = this.sigaa.getCursoByIdCursoAndIdEstudiantesAndIdGestion(curso);
            retorno.put("curso", curso);
            retorno.put("gestion", curso.getGestion());
            retorno.put("materias", curso.getMaterias());
            Estudiante estudiante = this.sigaa.getEstudianteByIdEstudiante(id_estudiante);
            retorno.put("estudiante", estudiante);
            String boletin = this.BoletaTrimestralPdf(curso, estudiante);
            retorno.put("boletin", boletin);
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

    private String formatoFechaNom(Date fecha) {
        String nuevo = new String();
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        nuevo = formato.format(fecha);
        return nuevo;
    }

    private String BoletaTrimestralPdf(Curso curso, Estudiante estudiante) {
        String dir = System.getenv("SIGAA_HOME") + "/documentos/boletines/";
        String archivo = curso.getId_curso() + "_" + estudiante.getId_estudiante() + "_" + this.formatoFechaNom(new Date()) + ".pdf";
        Font font8 = FontFactory.getFont(FontFactory.HELVETICA, 8);
        Font font8c = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE, 8);
        Font font8b = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8);
        Font font7b = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 7);
        Font font6 = FontFactory.getFont(FontFactory.HELVETICA, 6);

        Document document = new Document(PageSize.LETTER);
        try {

            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dir + archivo));
            document.open();
            document.addTitle("BOLETÍN TRIMESTRAL");
            document.addAuthor("Marco Antonio Quenta Velasco");
            document.addSubject("COLST");
            document.addKeywords("MAQV");
            Image png = Image.getInstance(System.getenv("SIGAA_HOME") + "/imagenes/iconos_sigaa/sigaa_oficial.png");
            png.setAbsolutePosition(470, 740);
            png.scalePercent(25);
            document.add(png);

            document.add(new Phrase("COLEGIO \"SANTA TERESA\"", font8b));
            document.add(Chunk.NEWLINE);
            document.add(new Phrase("La Paz - Bolivia", font8b));
            document.add(Chunk.NEWLINE);
            Paragraph p = new Paragraph(new Phrase("BOLETÍN DE CALIFICACIONES"));
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);

            PdfPCell cell = null;
            PdfPTable table_uno = new PdfPTable(3);
            int headerwidths[] = {10, 5, 85}; // percentage
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

            if (!curso.getId_curso().equals("K")) {
                PdfPTable table = new PdfPTable(5);
                int twidths[] = {20, 15, 15, 15, 15}; // percentage
                table.getDefaultCell().setBorder(0);
                table.setWidths(twidths);
                table.setWidthPercentage(100);

                PdfPTable table1 = new PdfPTable(3);
                int t1widths[] = {33, 34, 33};
                table1.setWidths(t1widths);
                table1.setWidthPercentage(100);

                cell = new PdfPCell(new Phrase("1er. Trimestre", font8b));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(0);
                cell.setColspan(3);
                table1.addCell(cell);
                cell = new PdfPCell(new Phrase("P.C.", font7b));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(0);
                table1.addCell(cell);
                cell = new PdfPCell(new Phrase("DPS", font7b));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(0);
                table1.addCell(cell);
                cell = new PdfPCell(new Phrase("P.T.", font7b));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(0);
                table1.addCell(cell);

                PdfPTable table2 = new PdfPTable(3);
//            table.getDefaultCell().setBorder(0);
                table2.setWidths(t1widths);
                table2.setWidthPercentage(100);
                cell = new PdfPCell(new Phrase("2do. Trimestre", font8b));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(0);
                cell.setColspan(3);
                table2.addCell(cell);
                cell = new PdfPCell(new Phrase("P.C.", font7b));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(0);
                table2.addCell(cell);
                cell = new PdfPCell(new Phrase("DPS", font7b));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(0);
                table2.addCell(cell);
                cell = new PdfPCell(new Phrase("P.T.", font7b));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(0);
                table2.addCell(cell);

                PdfPTable table3 = new PdfPTable(3);
                table3.setWidths(t1widths);
                table3.setWidthPercentage(100);
                cell = new PdfPCell(new Phrase("3er. Trimestre", font8b));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(0);
                cell.setColspan(3);
                table3.addCell(cell);
                cell = new PdfPCell(new Phrase("P.C.", font7b));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(0);
                table3.addCell(cell);
                cell = new PdfPCell(new Phrase("DPS", font7b));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(0);
                table3.addCell(cell);
                cell = new PdfPCell(new Phrase("P.T.", font7b));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(0);
                table3.addCell(cell);

                PdfPTable table4 = new PdfPTable(3);
                table4.setWidths(t1widths);
                table4.setWidthPercentage(100);
                cell = new PdfPCell(new Phrase("\nP.A.", font7b));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(0);
                table4.addCell(cell);
                cell = new PdfPCell(new Phrase("\nP.R.", font7b));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(0);
                table4.addCell(cell);
                cell = new PdfPCell(new Phrase("\nP.F.", font7b));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(0);
                table4.addCell(cell);

                table.getDefaultCell().setGrayFill(0.9f);
                table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(new Phrase("\nMateria", font8b));
                table.addCell(table1);
                table.addCell(table2);
                table.addCell(table3);
                table.addCell(table4);

                for (int i = 0; i < curso.getMaterias().size(); i++) {
                    Materia materia = (Materia) curso.getMaterias().get(i);
                    cell = new PdfPCell(new Phrase(materia.getMateria(), font7b));
                    cell.setGrayFill(0.9f);
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(0);
                    table.addCell(cell);
                    table.getDefaultCell().setGrayFill(1);

                    PdfPTable t1 = new PdfPTable(3);
                    t1.setWidths(t1widths);
                    t1.setWidthPercentage(100);

                    PdfPTable t2 = new PdfPTable(3);
                    t2.setWidths(t1widths);
                    t2.setWidthPercentage(100);

                    PdfPTable t3 = new PdfPTable(3);
                    t3.setWidths(t1widths);
                    t3.setWidthPercentage(100);

                    PdfPTable t4 = new PdfPTable(3);
                    t4.setWidths(t1widths);
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
                    table.addCell(t1);
                    table.addCell(t2);
                    table.addCell(t3);
                    table.addCell(t4);
                }

                cell = new PdfPCell(new Phrase("Promedio : ", font7b));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(0);
                table.addCell(cell);
                for (int j = 0; j < curso.getConductas().size(); j++) {
                    Conducta conducta = (Conducta) curso.getConductas().get(j);
                    cell = new PdfPCell(new Phrase(Integer.toString(conducta.getPromedio()) + " Pts.", font7b));
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell.setBorder(0);
                    table.addCell(cell);
                }
                cell = new PdfPCell(new Phrase(" ", font7b));
                cell.setBorder(0);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase("Conducta : ", font7b));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(0);
                table.addCell(cell);
                for (int j = 0; j < curso.getConductas().size(); j++) {
                    Conducta conducta = (Conducta) curso.getConductas().get(j);
                    cell = new PdfPCell(new Phrase(conducta.getTipo_conducta(), font7b));
                    cell.setBorder(0);
                    table.addCell(cell);
                }
                cell = new PdfPCell(new Phrase(" ", font7b));
                cell.setBorder(0);
                table.addCell(cell);

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
                cell = new PdfPCell(new Phrase(" ", font7b));
                cell.setBorder(0);
                table.addCell(cell);

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
                cell = new PdfPCell(new Phrase(" ", font7b));
                cell.setBorder(0);
                table.addCell(cell);

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
                cell = new PdfPCell(new Phrase(" ", font7b));
                cell.setBorder(0);
                table.addCell(cell);

                table.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(new Phrase("", font8b));
                table.addCell(new Phrase("\n\n\n__________________", font8b));
                table.addCell(new Phrase("", font8b));
                table.addCell(new Phrase("\n\n\n__________________", font8b));
                table.addCell(new Phrase("", font8b));

                table.addCell(new Phrase("", font8b));
                table.addCell(new Phrase("SECRETARIA", font8b));
                table.addCell(new Phrase("", font8b));
                table.addCell(new Phrase("DIRECTORA", font8b));
                table.addCell(new Phrase("", font8b));

                cell = new PdfPCell(new Phrase("\n\n" + curso.getGestion().getLema(), font8c));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(0);
                cell.setColspan(5);
                table.addCell(cell);

                document.add(table);
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
                table.addCell(new Phrase("SECRETARIA", font8b));
                table.addCell(new Phrase("", font8b));
                table.addCell(new Phrase("DIRECTORA", font8b));

                cell = new PdfPCell(new Phrase("\n\n" + curso.getGestion().getLema(), font8c));
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
}
