/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.sysmaqv.web.spring.sigaa.kardex;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.util.WebUtils;
import spring.sysmaqv.domain.*;
import spring.sysmaqv.domain.logic.SigaaInterface;

/**
 *
 * @author Marco Antonio Quenya Velasco
 */
public class ReporteKardex implements Controller {

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

    @Override
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
                    retorno.put("id_curso", id_curso);
                    String opcion = request.getParameter("opcion");
                    retorno.put("opcion", opcion);
                    String cant = request.getParameter("cant");
                    retorno.put("cant", cant);
                    if (opcion != null) {
                        if (opcion.equals("__reporteKardex")) {
                            Curso _curso = new Curso();
                            _curso.setId_curso(id_curso);
                            _curso.setId_gestion(Integer.parseInt(id_gestion));
                            System.out.println("----" + _curso);
                            Curso curso = this.sigaa.getCursoByIdCursoAndIdGestionSimple(_curso);

//                            curso.setId_gestion(Integer.parseInt(id_gestion));
//                            PeriodoCurso periodocurso = this.sigaa.getPeriodoByIdcursoAndGestion(_curso);
//                            Fecha_regnota fechas_regnotas=this.sigaa.getFechaRegNotaByIdperiodo(periodocurso.getIdperiodo());
//                            String idperiodo = Integer.toString(periodocurso.getIdperiodo());
//                            int periodo = Integer.parseInt(idperiodo.substring(4, 5));
                            retorno.put("cant_fechas", curso.getFecRegNotas().size());
                            retorno.put("curso", curso);
                            return new ModelAndView(this.perfectView, retorno);
                        }
                        if (opcion.equals("_kardex_pdf")) {
                            String periodo = request.getParameter("periodo");
                            String id_eva = request.getParameter("id_eva");
                            Fecha_regnota fecha_regnota = this.sigaa.getFechaRegNotaByIdEva(id_eva);
                            Curso curso = new Curso();
                            curso.setId_curso(id_curso);
                            curso.setId_gestion(Integer.parseInt(id_gestion));
                            curso.setFecha1(fecha_regnota.getFec_inicio());
                            curso.setFecha2(fecha_regnota.getFec_limite());
                            curso = this.sigaa.getCursoByIdCursoAndIdGestionSimple(curso);
                            curso.setId_gestion(Integer.parseInt(id_gestion));
                            String pdf = this.kardexCursoPdf(curso, id_gestion, fecha_regnota);
                            response.sendRedirect("documentos/kardex/" + pdf);
                        }
                        if (opcion.equals("__repHtml")) {
                            String id_estudiante = request.getParameter("id_estudiante");
                            Estudiante estudiante = this.sigaa.getEstudianteKardexByIdEstudianteAndIdcursoAndIdgestion(id_estudiante, id_curso, Integer.parseInt(id_gestion));
                            retorno.put("estudiante", estudiante);
                            return new ModelAndView(this.perfectView, retorno);
                        }
                    }
                }
                retorno.put("cursoMaterias", this.sigaa.getListaCursoMateriasByIdGestion(Integer.parseInt(id_gestion)));
                retorno.put("cursos", this.sigaa.getListaCursos(Integer.parseInt(id_gestion)));
                return new ModelAndView(this.perfectView, retorno);
            }
            retorno.put("gestiones", this.sigaa.getGestionActivo());
            return new ModelAndView(this.perfectView, retorno);
        } else {
            return new ModelAndView(this.ecxessTimeView, "url", url);
        }
    }

    private String formatoFechaNom(Date fecha) {
        String nuevo = new String();
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        nuevo = formato.format(fecha);
        return nuevo;
    }

    private String formatearFecha(Date fecha) {
        String nuevo = new String();
        SimpleDateFormat formato = new SimpleDateFormat("EEEE, d 'de' MMMM 'de' yyyy");
        nuevo = formato.format(fecha);
        return nuevo;
    }

    private String kardexCursoPdf(Curso curso_gen, String id_gestion, Fecha_regnota fecha_regnota) {
        String dir = System.getenv("SIGAA_HOME1") + "/documentos/kardex/";
        String archivo = "Kardex_" + curso_gen.getCurso() + "_de_" + curso_gen.getCiclo() + "(" + this.formatoFechaNom(new Date()) + ").pdf";
        Font font8 = FontFactory.getFont(FontFactory.HELVETICA, 8);
        Font font8c = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE, 8);
        Font font8b = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8);
        Font font7 = FontFactory.getFont(FontFactory.HELVETICA, 8);
        Font fontRed7 = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 7, Color.black);
        Font font7b = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 7);
        Font font6 = FontFactory.getFont(FontFactory.HELVETICA, 7);
        Font font12 = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10);

        Document document = new Document(PageSize.LEGAL);
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dir + archivo));
            float width = document.getPageSize().getWidth();
            document.open();
            for (int x = 0; x < curso_gen.getEstudiantes().size(); x++) {
                if (x > 0) {
                    document.newPage();
                }
                Estudiante estudiante = (Estudiante) curso_gen.getEstudiantes().get(x);
                Gestion gestion = (Gestion) this.sigaa.getBuscarGestion(Integer.parseInt(id_gestion)).get(0);

                Image png = Image.getInstance(System.getenv("SIGAA_HOME1") + "/imagenes/iconos_sigaa/santa_teresa_logo.png");
                png.setAbsolutePosition(550, 945);
                png.scalePercent(25);
                document.add(png);

                document.add(new Phrase("COLEGIO \"SANTA TERESA\"", font8b));
                document.add(Chunk.NEWLINE);
                Paragraph p = new Paragraph(new Phrase("SEGUIMIENTO INTEGRAL DEL ESTUDIANTE \n(" + fecha_regnota.getDescripcion() + ")", font12));
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
                cell = new PdfPCell(new Phrase(curso_gen.getCurso() + " de " + curso_gen.getCiclo(), font8b));
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
                cell = new PdfPCell(new Phrase(Integer.toString(curso_gen.getId_gestion()), font8b));
                cell.setBorder(0);
                table_uno.addCell(cell);
                document.add(table_uno);

                if (!curso_gen.getId_curso().equals("K")) {
                    PdfPTable table = new PdfPTable(2);
                    int twidths[] = {20, 80};
                    table.getDefaultCell().setBorder(0);
                    table.setWidths(twidths);
                    table.setWidthPercentage(100);
                    PdfPTable table1 = new PdfPTable(9);
                    int t1widths[] = {10, 5, 5, 5, 5, 5, 20, 20, 25};
                    table1.setWidths(t1widths);
                    table1.setWidthPercentage(100);
                    cell = new PdfPCell(new Phrase("FECHA", font7b));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(0);
                    table1.addCell(cell);
                    cell = new PdfPCell(new Phrase("FSL", font7b));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(0);
                    table1.addCell(cell);
                    cell = new PdfPCell(new Phrase("NEC", font7b));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(0);
                    table1.addCell(cell);
                    cell = new PdfPCell(new Phrase("TNR", font7b));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(0);
                    table1.addCell(cell);
                    cell = new PdfPCell(new Phrase("AA", font7b));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(0);
                    table1.addCell(cell);
                    cell = new PdfPCell(new Phrase("NF", font7b));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(0);
                    table1.addCell(cell);
                    cell = new PdfPCell(new Phrase("OTRAS FALTAS", font7b));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(0);
                    table1.addCell(cell);
                    cell = new PdfPCell(new Phrase("ASPECTOS POSITIVOS", font7b));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(0);
                    table1.addCell(cell);
                    cell = new PdfPCell(new Phrase("OBSERVACIONES", font7b));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(0);
                    table1.addCell(cell);

                    table.getDefaultCell().setGrayFill(0.9f);
                    table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(new Phrase("MATERIA", font7b));
                    table.addCell(table1);

                    for (int i = 0; i < estudiante.getCursomaterias().size(); i++) {
                        CursoMateria cm = (CursoMateria) estudiante.getCursomaterias().get(i);
                        PdfPTable table3 = new PdfPTable(1);
                        int t1[] = {100};
                        table3.setWidths(t1);
                        table3.setWidthPercentage(100);
                        cell = new PdfPCell(new Phrase(cm.getMateria(), font7b));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        table3.addCell(cell);

                        cell = new PdfPCell(table3);
                        if (i % 2 != 0) {
                            cell.setGrayFill(0.9f);
                        }
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setBorder(1);
                        table.addCell(cell);
                        PdfPTable table2 = new PdfPTable(9);
                        String m = "";
                        if (cm.getKardex().size() > 0) {
                            for (int k = 0; k < cm.getKardex().size(); k++) {
                                KardexDetalle kd = (KardexDetalle) cm.getKardex().get(k);

                                int t1widths2[] = {10, 5, 5, 5, 5, 5, 20, 20, 25};
                                table2.setWidths(t1widths2);
                                table2.setWidthPercentage(100);
                                cell = new PdfPCell(new Phrase(kd.getSfecha2(), font7));
                                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                table2.addCell(cell);
                                cell = new PdfPCell(new Phrase((kd.getFsl() == 1) ? "x" : "", font7));
                                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                table2.addCell(cell);
                                cell = new PdfPCell(new Phrase((kd.getA() == 1) ? "x" : "", font7));
                                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                table2.addCell(cell);
                                cell = new PdfPCell(new Phrase((kd.getTnr() == 1) ? "x" : "", font7));
                                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                table2.addCell(cell);
                                cell = new PdfPCell(new Phrase((kd.getAa() == 1) ? "x" : "", font7));
                                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                table2.addCell(cell);
                                cell = new PdfPCell(new Phrase((kd.getSu() == 1) ? "x" : "", fontRed7));
                                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                table2.addCell(cell);
                                cell = new PdfPCell(new Phrase(kd.getOtrasfaltas(), font7));
                                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                table2.addCell(cell);
                                cell = new PdfPCell(new Phrase(kd.getAspectospositivos(), font7));
                                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                table2.addCell(cell);
                                cell = new PdfPCell(new Phrase(kd.getObservaciones(), font7));
                                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                table2.addCell(cell);
                            }
                        } else {
                            int t1widths2[] = {10, 5, 5, 5, 5, 5, 20, 20, 25};
                            table2.setWidths(t1widths2);
                            table2.setWidthPercentage(100);
                            table2.addCell(new Phrase(""));
                            table2.addCell(new Phrase(""));
                            table2.addCell(new Phrase(""));
                            table2.addCell(new Phrase(""));
                            table2.addCell(new Phrase(""));
                            table2.addCell(new Phrase(""));
                            table2.addCell(new Phrase(""));
                            table2.addCell(new Phrase(""));
                            table2.addCell(new Phrase(""));
                        }
                        cell = new PdfPCell(table2);
                        if (i % 2 != 0) {
                            cell.setGrayFill(0.9f);
                        }
                        cell.setBorder(1);
                        table.addCell(cell);
                    }
                    cell = new PdfPCell(new Phrase("\n" + gestion.getLema(), font8c));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(0);
                    cell.setColspan(2);
                    table.addCell(cell);

                    document.add(table);
                }

                p = new Paragraph(new Phrase("F.S.L.:Falta Sin Licencia, N.E.C.:No Enciende la Cámara,T.N.R.:Tarea No Realizada, A.A.:Atrasos,N.F.:Notificado a la Familia", font7));
                document.add(p);
                 p = new Paragraph(new Phrase("De acuerdo a la información reflejada en este reporte, La Madre/Padre de Familia DEBE COMUNICARSE OPORTUNAMENTE CON EL DOCENTE CORRESPONDIENTE.", font7));
                document.add(p);
                p = new Paragraph(new Phrase("SIGAA Sistema Integrado de Gestión Académico - Administrativa " + curso_gen.getId_gestion(), font6));
                document.add(p);
            }
        } catch (Exception de) {
            de.printStackTrace();
        }

        document.close();
        return archivo;
    }
}
