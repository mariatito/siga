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
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import spring.sysmaqv.domain.Curso;
import spring.sysmaqv.domain.CursoMateria;
import spring.sysmaqv.domain.Estudiante;
import spring.sysmaqv.domain.Personal;
import spring.sysmaqv.domain.PlanEvaluacion;
import spring.sysmaqv.domain.logic.SigaaInterface;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.util.WebUtils;
import spring.sysmaqv.domain.*;

/**
 * Created on : 06-abr-2013, 11:21:50
 *
 * @author MARCO ANTONIO QUENTA VELASCO
 */
public class CentralizadorCalificaciones implements Controller {

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
                retorno.put("id_curso", "K");
                String opcion = request.getParameter("opcion");
                if (id_curso != null) {
                    retorno.put("id_curso", id_curso);
                    String id_eva = request.getParameter("id_eva");
                    retorno.put("id_eva", id_eva);
                    Fecha_regnota frn = this.sigaa.getFechaRegNotaByIdEva(id_eva);
                    String eva = id_eva.substring(0, 2);
                    Curso curso = new Curso();
                    curso.setId_curso(id_curso);
                    curso.setId_gestion(Integer.parseInt(id_gestion));
                    curso = this.sigaa.getConsultaEstudiantesAllByIdCursoAndIdGestion(curso);
                    List estudiantes = curso.getEstudiantes();
                    List cursomaterias = curso.getCursomaterias();
                    List cursomaterias_totales_t = new ArrayList();
                    int[] vect_p = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
                    int[] vect_a = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
                    int[] vect_r = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

                    List new_estudiantes = new ArrayList();
                    for (int i = 0; i < estudiantes.size(); i++) {
                        Estudiante estudiante = (Estudiante) curso.getEstudiantes().get(i);
                        if (estudiante.getId_estado().equals("A")) {
                        estudiante.setId_gestion(Integer.parseInt(id_gestion));
//                        estudiante.setNombres(estudiante.getNombres().replaceAll(" ", "&nbsp;"));
//                        estudiante.setPaterno(estudiante.getPaterno().replaceAll(" ", "&nbsp;"));
//                        estudiante.setMaterno(estudiante.getMaterno().replaceAll(" ", "&nbsp;")); 
                        List new_cursomaterias = new ArrayList();
                        int notapromedio = 0;
                        //System.out.println("tama;o...:"+cursomaterias.size());
                        for (int j = 0; j < cursomaterias.size(); j++) {
                            CursoMateria cursomateria = (CursoMateria) cursomaterias.get(j);
                            String id_evaluacion = cursomateria.getId_curso_materia() + "-" + eva;
                            Evaluacion evaluacion = new Evaluacion();
                            evaluacion.setId_estudiante(estudiante.getId_estudiante());
                            evaluacion.setId_evaluacion(id_evaluacion);
                            evaluacion = this.sigaa.getEvaluacionByIdestudianteAndIdevaluacion(evaluacion);
                            CursoMateria cm = new CursoMateria();
                            cm.setId_curso_materia(cursomateria.getId_curso_materia());
                            cm.setNota(0);
                            cm.setDps(0);
                            cm.setCualitativa("");
                            int min = 0;
                            if (evaluacion != null) {
                                cm.setNota(evaluacion.getNota());
                                cm.setDps(evaluacion.getDps());
                                cm.setCualitativa(evaluacion.getCualitativa());
                                min = ((evaluacion.getNota_plan() + evaluacion.getDps_plan()) / 2) + 1;
                            }
                            notapromedio = notapromedio + cm.getNota() + cm.getDps();
                            vect_p[j] = vect_p[j] + cm.getNota() + cm.getDps();
                            cm.setNota_min(min);
                          //  System.out.println(id_evaluacion+"_____"+cm.getNota());
                            if (cm.getNota() + cm.getDps() < min) {
                                vect_r[j] = vect_r[j] + 1;
                            } else {
                                vect_a[j] = vect_a[j] + 1;
                            }
                            new_cursomaterias.add(cm);

                        }
                       // System.out.println(estudiante.getId_estudiante()+"_____"+id_curso+"_____"+notapromedio);
                        notapromedio = (int) (Math.round(((double) notapromedio / cursomaterias.size()) * Math.pow(10, 0)) / Math.pow(10, 0));
                        estudiante.setNotapromedio(notapromedio);
                        estudiante.setCursomaterias(new_cursomaterias);
                        new_estudiantes.add(estudiante);
                    }
                    }
                    curso.setEstudiantes(new_estudiantes);
                    for (int l = 0; l < cursomaterias.size(); l++) {
                        CursoMateria c_m = (CursoMateria) cursomaterias.get(l);
                        int p = (int) (Math.round(((double) vect_p[l] / estudiantes.size()) * Math.pow(10, 0)) / Math.pow(10, 0));
                        c_m.setPromedio(p);
                        c_m.setAprobados(vect_a[l]);
                        c_m.setReprobados(vect_r[l]);
                        cursomaterias_totales_t.add(c_m);
                    }
                    curso.setCursomaterias(cursomaterias_totales_t);
                    retorno.put("curso", curso);
                    if (opcion != null) {
                        if (opcion.equals("_imprimeExcel")) {
                            String excel = this.centralizadorExcel(curso, id_gestion,frn);
                            response.sendRedirect("documentos/centralizador/" + excel);
                            retorno.put("file", "documentos/centralizador/" + excel);
                        }
                    }
                }
                retorno.put("cursos", this.sigaa.getListaCursos(Integer.parseInt(id_gestion)));
//                Curso curso =new Curso();
//                curso.setId_curso(id_curso);
//                curso.setId_gestion(Integer.parseInt(id_gestion));
                List periodo_cursos = this.sigaa.getPeriodoCursoByIdgestion(Integer.parseInt(id_gestion));
                retorno.put("periodo_cursos", periodo_cursos);
                return new ModelAndView(this.perfectView, retorno);
            }
            retorno.put("gestion", this.sigaa.getGestionActivo());
            return new ModelAndView(this.perfectView, retorno);
        } else {
            return new ModelAndView(this.ecxessTimeView, "url", url);
        }
    }

    private String centralizadorExcel(Curso curso, String id_gestion, Fecha_regnota f) {
        Date FechaActual = new Date();
        SimpleDateFormat Formator = new SimpleDateFormat("dd-MM-yy");
        String CadenaFechar = Formator.format(FechaActual);
        SimpleDateFormat Formato = new SimpleDateFormat("EEEE, d 'de' MMMM 'de' yyyy, hh:mm:ss a");
        String CadenaFecha = Formato.format(FechaActual);
        String dirDestino = System.getenv("SIGAA_HOME1") + "/documentos/centralizador/";
        String archivo = "Centralizado_" + curso.getId_curso() + "_" + CadenaFechar + ".xls";
        String dirPlantilla = System.getenv("SIGAA_HOME1") + "/documentos/plantillas_excel/plantilla_cetralizador.xls";

        try {
            Workbook plantilla = Workbook.getWorkbook(new File(dirPlantilla));
            WritableWorkbook workbook = Workbook.createWorkbook(new File(dirDestino + archivo), plantilla);
            WritableSheet sheet = workbook.getSheet("CENTRALIZADOR");
            WritableFont arial_8 = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat arial8 = new WritableCellFormat(arial_8);
            WritableFont arial_10 = new WritableFont(WritableFont.ARIAL, 10);
            WritableCellFormat arial10 = new WritableCellFormat(arial_10);

            WritableCellFormat arial8green = new WritableCellFormat(arial_8);
            arial8green.setBorder(Border.ALL, BorderLineStyle.DASHED, Colour.GRAY_80);
            arial8green.setBackground(Colour.LIGHT_GREEN);
            arial8green.setAlignment(Alignment.CENTRE);

            WritableCellFormat arial8blue1 = new WritableCellFormat(arial_8);
            arial8blue1.setBorder(Border.ALL, BorderLineStyle.DASHED, Colour.GRAY_80);
            arial8blue1.setBackground(Colour.GREY_25_PERCENT);
            arial8blue1.setAlignment(Alignment.CENTRE);

            WritableCellFormat arial8blue2 = new WritableCellFormat(arial_8);
            arial8blue2.setBorder(Border.ALL, BorderLineStyle.DASHED, Colour.GRAY_80);
            arial8blue2.setBackground(Colour.GREY_25_PERCENT);
            arial8blue2.setAlignment(Alignment.RIGHT);

            WritableCellFormat arial8red = new WritableCellFormat(arial_8);
            arial8red.setBorder(Border.ALL, BorderLineStyle.DASHED, Colour.GRAY_80);
            arial8red.setBackground(Colour.YELLOW);
            arial8red.setAlignment(Alignment.CENTRE);

            sheet.addCell(new Label(0, 1, "GESTION ACADEMINO " + id_gestion, arial10));
            sheet.addCell(new Label(3, 4, curso.getCurso() + " de " + curso.getCiclo(), arial8));
            sheet.addCell(new Label(3, 5, f.getDescripcion(), arial8));
            sheet.addCell(new Label(3, 6, CadenaFecha, arial8));
            int val = 4;
            for (int j = 0; j < curso.getCursomaterias().size(); j++) {
                CursoMateria cm = (CursoMateria) curso.getCursomaterias().get(j);
                sheet.addCell(new Label(val, 9, cm.getId_materia(), arial8green));
                val = val + 3;
            }
            sheet.addCell(new Label(val, 9, "PROMEDIO", arial8blue1));
            Estudiante est;
            int i = 0;
            for (i = 0; i < curso.getEstudiantes().size(); i++) {
                est = (Estudiante) curso.getEstudiantes().get(i);
                sheet.addCell(new jxl.write.Number(0, i + 11, (i + 1), arial8));
                sheet.addCell(new jxl.write.Number(1, i + 11, est.getCodigo(), arial8));
                sheet.addCell(new Label(2, i + 11, est.getPaterno() + " " + est.getMaterno() + " " + est.getNombres(), arial8));
                int h = 4;
                for (int j = 0; j < est.getCursomaterias().size(); j++) {
                    CursoMateria cm_e = (CursoMateria) est.getCursomaterias().get(j);
                    if (!curso.getId_curso().equals("K")||!curso.getId_curso().equals("K1")||!curso.getId_curso().equals("P1")||!curso.getId_curso().equals("P12")) {
                        sheet.addCell(new jxl.write.Number(h++, i + 11, cm_e.getNota(), arial8));
                        sheet.addCell(new jxl.write.Number(h++, i + 11, cm_e.getDps(), arial8));
                        if (cm_e.getNota() + cm_e.getDps() < cm_e.getNota_min()) {
                            sheet.addCell(new jxl.write.Number(h++, i + 11, cm_e.getNota() + cm_e.getDps(), arial8red));
                        } else {
                            sheet.addCell(new jxl.write.Number(h++, i + 11, cm_e.getNota() + cm_e.getDps(), arial8green));
                        }
                    } else {
                    }
                }
                sheet.addCell(new jxl.write.Number(h + 1, i + 11, est.getNotapromedio(), arial8blue1));
            }
            i++;
            sheet.addCell(new Label(2, i + 11, "Promedio por Materias", arial8blue2));
            sheet.addCell(new Label(2, i + 12, "Aprobados", arial8blue2));
            sheet.addCell(new Label(2, i + 13, "Reprobados", arial8blue2));
            int h = 6;
            for (int j = 0; j < curso.getCursomaterias().size(); j++) {
                CursoMateria cm_p = (CursoMateria) curso.getCursomaterias().get(j);
                sheet.addCell(new jxl.write.Number(h, i + 11, cm_p.getPromedio(), arial8blue1));
                sheet.addCell(new jxl.write.Number(h, i + 12, cm_p.getAprobados(), arial8blue1));
                sheet.addCell(new jxl.write.Number(h, i + 13, cm_p.getReprobados(), arial8blue1));
                h = h + 3;
            }
            workbook.write();
            workbook.close();
        } catch (Exception de) {
            de.printStackTrace();
        }
        return archivo;
    }
}
