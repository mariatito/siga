/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.sysmaqv.web.spring.sigaa.consultas;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import spring.sysmaqv.domain.Curso;
import spring.sysmaqv.domain.Estudiante;
import spring.sysmaqv.domain.Gestion;
import spring.sysmaqv.domain.Personal;
import spring.sysmaqv.domain.logic.SigaaInterface;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.util.WebUtils;
import spring.sysmaqv.domain.PeriodoCurso;

/**
 * Created on : 01-jul-2010, 9:59:20
 *
 * @author MARCO ANTONIO QUENTA VELASCO
 */
public class MejoresEstudiantes implements Controller {

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

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getServletPath();
        Personal personal = (Personal) WebUtils.getSessionAttribute(request, "___personal");
        if (personal != null) {


            if (!this.sigaa.personalAutorizado(personal, url)) {
                return new ModelAndView("seguridad/Error", null);
            }
            Map retorno = new HashMap();
            String opcion = request.getParameter("opcion");
            int valor = 1;
            String texto = "Bimestral y Trimestral";
            int bimestral = 0;
            int trimestral = 0;
            if (opcion != null) {
                if (opcion.equals("getTrimestreBimestre")) {
                    String gestion_id = request.getParameter("gestion_id");
                    String curso_id = request.getParameter("curso_id");
                    PeriodoCurso pc = new PeriodoCurso();
                    pc.setGestion(Integer.parseInt(gestion_id));
                    pc.setId_curso(curso_id);
                    List periodo_cursos = (curso_id.equals("C")) ? this.sigaa.getPeriodoCursoByIdgestion(Integer.parseInt(gestion_id)) : this.sigaa.getSigaaPeriodoCursoByIdCursoAndIdgestion(pc);
                    for (int i = 0; i < periodo_cursos.size(); i++) {
                        PeriodoCurso p = (PeriodoCurso) periodo_cursos.get(i);
                        bimestral = (p.getIdperiodo() == (p.getGestion() * 10 + 2)) ? 2 : bimestral;
                        trimestral = (p.getIdperiodo() == (p.getGestion() * 10 + 3)) ? 3 : trimestral;
                    }
                    if (bimestral == 2 && trimestral == 0) {
                        valor = 2;
                        texto = "Bimestral";
                    } else if (bimestral == 0 && trimestral == 3) {
                        valor = 3;
                        texto = "Trimestral";
                    }
                    String html = texto + "|" + valor;
                    retorno.put("valor", html);
                    return new ModelAndView("consultas/JqueryJava", retorno);
                }
                if (opcion.equals("getResultado")) {
                    String gestion_id = request.getParameter("gestion_id");
                    String curso_id = request.getParameter("curso_id");
                    String id_sexo = request.getParameter("id_sexo");
                    int tipo = Integer.parseInt(request.getParameter("tipo"));
                    Estudiante estudiante = new Estudiante();
                    estudiante.setId_gestion(Integer.parseInt(gestion_id));
                    estudiante.setId_curso(curso_id);
                    estudiante.setGestion(gestion_id);
                    int cont = 0;
                    String html = "";
                    if (tipo == 3) {
                        estudiante.setEva1(request.getParameter("t1"));
                        estudiante.setEva2(request.getParameter("t2"));
                        estudiante.setEva3(request.getParameter("t3"));
                        estudiante.setEva4("");
                        List estudiantes = this.sigaa.getSigaaGetEstudiantesByCursoAndGestionT(estudiante);
                        html = "<h2>Evaluacion Trimestral</h2><table class='table ui-widget ui-widget-content' cellpadding='0' cellspacing='0'>";
                        html += "<thead><tr class='ui-widget-header '><th>Nro</th>";
                        html += "<th>Paterno</th>";
                        html += "<th>Materno</th>";
                        html += "<th>Nombres</th>";
                        html += (estudiante.getEva1().equals("E1")) ? "<th title='1er Trimestre'>I</th>" : "";
                        html += (estudiante.getEva2().equals("E2")) ? "<th title='2do Trimestre'>II</th>" : "";
                        html += (estudiante.getEva3().equals("E3")) ? "<th title='3er Trimestre'>III</th>" : "";
                        html += "<th title='Promedio al 70%'>P. 70%</th>";
                        html += "<th title='Promedio al 100%'>P. 100%</th>";
                        html += "</tr></thead>";
                        html += "<tbody>";
                        html += "<tr>";
                        cont = 0;
                        int por = 0;
                        for (int i = 0; i < estudiantes.size(); i++) {
                            Estudiante est = (Estudiante) estudiantes.get(i);
                            por = Math.round((est.getNotapromedio() * 100) / 70);
                            if (est.getId_sexo().equals(id_sexo) || id_sexo.equals("A")) {
                                html += "<td style='text-align:center'>" + (++cont) + "</td>";
                                html += "<td>" + est.getPaterno() + "</td>";
                                html += "<td>" + est.getMaterno() + "</td>";
                                html += "<td>" + est.getNombres() + "</td>";
                                html += (estudiante.getEva1().equals("E1")) ? "<td style='text-align: center;'>" + est.getE1() + "</td>" : "";
                                html += (estudiante.getEva2().equals("E2")) ? "<td style='text-align: center;'>" + est.getE2() + "</td>" : "";
                                html += (estudiante.getEva3().equals("E3")) ? "<td style='text-align: center;'>" + est.getE3() + "</td>" : "";
                                html += "<td style='text-align:center'>" + est.getNotapromedio() + "</td>";
                                html += "<td style='text-align:center'>" + por + "</td>";
                                html += "</tr>";
                            }
                        }
                        html += "</tbody>";
                        html += "</table>";
                    }
                    if (tipo == 2) {
                        estudiante.setEva1(request.getParameter("b1"));
                        estudiante.setEva2(request.getParameter("b2"));
                        estudiante.setEva3(request.getParameter("b3"));
                        estudiante.setEva4(request.getParameter("b4"));
                        List estudiantes = this.sigaa.getSigaaGetEstudiantesByCursoAndGestionB(estudiante);
                        html = "<h2>Evaluacion Bimestral</h2><table class='table ui-widget ui-widget-content' cellpadding='0' cellspacing='0'>";
                        html += "<thead><tr class='ui-widget-header '><th>Nro</th>";
                        html += "<th>Paterno</th>";
                        html += "<th>Materno</th>";
                        html += "<th>Nombres</th>";
                        html += (estudiante.getEva1().equals("E1")) ? "<th title='1er Bimestre'>I</th>" : "";
                        html += (estudiante.getEva2().equals("E2")) ? "<th title='2do Bimestre'>II</th>" : "";
                        html += (estudiante.getEva3().equals("E3")) ? "<th title='3er Bimestre'>III</th>" : "";
                        html += (estudiante.getEva4().equals("E4")) ? "<th title='4to Bimestre'>IV</th>" : "";
                        html += "<th title='Promedio al 100%'>P. 100%</th>";
                        html += "</tr></thead>";
                        html += "<tbody>";
                        html += "<tr>";
                        cont = 0;
                        for (int i = 0; i < estudiantes.size(); i++) {
                            Estudiante est = (Estudiante) estudiantes.get(i);
                            if (est.getId_sexo().equals(id_sexo) || id_sexo.equals("A")) {
                                html += "<td style='text-align:center'>" + (++cont) + "</td>";
                                html += "<td>" + est.getPaterno() + "</td>";
                                html += "<td>" + est.getMaterno() + "</td>";
                                html += "<td>" + est.getNombres() + "</td>";
                                html += (estudiante.getEva1().equals("E1")) ? "<td style='text-align: center;'>" + est.getE1() + "</td>" : "";
                                html += (estudiante.getEva2().equals("E2")) ? "<td style='text-align: center;'>" + est.getE2() + "</td>" : "";
                                html += (estudiante.getEva3().equals("E3")) ? "<td style='text-align: center;'>" + est.getE3() + "</td>" : "";
                                html += (estudiante.getEva4().equals("E4")) ? "<td style='text-align: center;'>" + est.getE4() + "</td>" : "";
                                html += "<td style='text-align:center'>" + est.getNotapromedio() + "</td>";
                                html += "</tr>";
                            }
                        }
                        html += "</tbody>";
                        html += "</table>";
                    }
                    retorno.put("valor", html);
                    System.out.println(html);
                    return new ModelAndView("consultas/JqueryJava", retorno);
                }
            }
            List gestiones = this.sigaa.getSigaaGestiones();
            Gestion gestion = (Gestion) gestiones.get(0);
            List periodo_cursos = this.sigaa.getPeriodoCursoByIdgestion(gestion.getId_gestion());
            for (int i = 0; i < periodo_cursos.size(); i++) {
                PeriodoCurso p = (PeriodoCurso) periodo_cursos.get(i);
                bimestral = (p.getIdperiodo() == (p.getGestion() * 10 + 2)) ? 2 : bimestral;
                trimestral = (p.getIdperiodo() == (p.getGestion() * 10 + 3)) ? 3 : trimestral;
            }
            if (bimestral == 2 && trimestral == 0) {
                valor = 2;
                texto = "Bimestral";
            } else if (bimestral == 0 && trimestral == 3) {
                valor = 3;
                texto = "Trimestral";
            }
            retorno.put("valor", valor);
            retorno.put("texto", texto);
            retorno.put("gestiones", gestiones);
            retorno.put("cursos", this.sigaa.getSigaaCursos());
            retorno.put("tipos_sexos", this.sigaa.getTiposSexos());

//            String id_gestion = request.getParameter("id_gestion");
//            if (id_gestion != null) {
//                retorno.put("id_gestion", id_gestion);
//                retorno.put("cursos", this.sigaa.getListaCursos(Integer.parseInt(id_gestion)));
//                retorno.put("tipos_sexos", this.sigaa.getTiposSexos());
//                retorno.put("question", "PROMEDIO");
//                List periodo_cursos = this.sigaa.getPeriodoCursoByIdgestion(Integer.parseInt(id_gestion));
//                retorno.put("periodo_cursos", periodo_cursos);
//                retorno.put("id_curso", "K");
//
//                String id_curso = request.getParameter("id_curso");
//                // String opcion = request.getParameter("opcion");
//                String categoria = request.getParameter("categoria");
//                String accion = request.getParameter("accion");
//                String act = request.getParameter("act");
//                if (act != null) {
//                    this.sigaa.setUpdateAllPromedioByIdGestion(Integer.parseInt(id_gestion));
//                }
//                if (id_curso != null) {
//                    retorno.put("id_curso", id_curso);
//                    if (opcion.equals("PROMEDIO")) {
//                        int cont = 0;
//                        String tb1 = request.getParameter("check_" + id_curso + "_0");
//                        String tb2 = request.getParameter("check_" + id_curso + "_1");
//                        String tb3 = request.getParameter("check_" + id_curso + "_2");
//                        String tb4 = request.getParameter("check_" + id_curso + "_3");
//                        String tb5 = request.getParameter("check_" + id_curso + "_4");
//                        String evas = "'0'";
//                        if (tb1 != null) {
//                            cont++;
//                            evas = evas + ",'" + tb1 + "'";
//                        }
//                        if (tb2 != null) {
//                            cont++;
//                            evas = evas + ",'" + tb2 + "'";
//                        }
//                        if (tb3 != null) {
//                            cont++;
//                            evas = evas + ",'" + tb3 + "'";
//                        }
//                        if (tb4 != null) {
//                            cont++;
//                            evas = evas + ",'" + tb4 + "'";
//                        }
//                        if (tb5 != null) {
//                            cont++;
//                            evas = evas + ",'" + tb5 + "'";
//                        }
//
//                        System.out.println("_____" + tb1 + "____" + tb2 + "____" + tb3 + "____" + tb4 + "____" + tb5);
////                        String tim_bim = request.getParameter("trim_bim"+id_curso);
////                        String tim_bim1 = request.getParameter("trim_bim1"+id_curso);
////                        String tim_bim2 = request.getParameter("trim_bim2"+id_curso);
//                        Curso curso = new Curso();
//                        curso.setId_gestion(Integer.parseInt(id_gestion));
////                        curso.setTribim1(tim_bim1);
////                        curso.setTribim2(tim_bim2);
//////                       curso.setId_trimestre(id_trimestre);
//                        curso.setId_curso(id_curso);
//                        curso.setEvas(evas);
//                        System.out.println(evas);
//                        curso.setCant(cont);
//                        curso = this.sigaa.getConsultasPromedioByIdCursoAndIdGestion(curso);
////                        retorno.put("curso", curso);
////                        retorno.put("categoria", categoria);
////                        retorno.put("promedio", "_promedio");
////                        retorno.put("id_trimestre", id_trimestre);
////                        if (accion != null) {
////                            curso.setId_gestion(Integer.parseInt(id_gestion));
////                            String excel = this.consultasPromedio(curso, categoria, id_trimestre);
////                            response.sendRedirect("documentos/consultas/reportes-excel/promedios/" + excel);
////                            retorno.put("file", "documentos/consultas/reportes-excel/promedioss/" + excel);
////                            return new ModelAndView(this.perfectView, retorno);
////                        }
//                    }
//                    if (opcion.equals("_DetalleCalificaciones")) {
//                        Curso curso = new Curso();
//                        String id_estudiante = request.getParameter("id_estudiante");
//                        curso.setId_estudiante(id_estudiante);
//                        curso.setId_curso(id_curso);
//                        curso.setId_gestion(Integer.parseInt(id_gestion));
//                        curso = this.sigaa.getCursoByIdCursoAndIdEstudiantesAndIdGestion(curso);
//                        retorno.put("curso", curso);
//                        Gestion gestion = (Gestion) this.sigaa.getBuscarGestion(Integer.parseInt(id_gestion)).get(0);
//                        retorno.put("gestion", gestion);
//                        curso.setGestion(gestion);
//                        int tRetraso = 0;
//                        int tFalta = 0;
//                        for (int i = 0; i < curso.getFaltas().size(); i++) {
//                            Falta falta = (Falta) curso.getFaltas().get(i);
//                            tRetraso = tRetraso + falta.getRetraso();
//                            tFalta = tFalta + falta.getFcl() + falta.getFsl();
//                        }
//                        retorno.put("tRetraso", tRetraso);
//                        retorno.put("tFalta", tFalta);
//                        Estudiante estudiante = new Estudiante();
//                        estudiante.setId_estudiante(id_estudiante);
//                        estudiante.setId_gestion(Integer.parseInt(id_gestion));
//                        estudiante.setId_curso(id_curso);
//                        estudiante = this.sigaa.getDatosActualesEstudianteByIdEstudianteIdCursoIdGestion(estudiante);
//                        retorno.put("estudiante", estudiante);
//                        return new ModelAndView(this.perfectView + "DetalleCalificaciones", retorno);
//                    }
//                }
//                return new ModelAndView(this.perfectView, retorno);
//            }

            return new ModelAndView(this.perfectView, retorno);
        } else {
            return new ModelAndView(this.ecxessTimeView, "url", url);
        }
    }

    private String consultasPromedio(Curso curso, String categoria, String id_trimestre) {
        Date FechaActual = new Date();
        SimpleDateFormat Formator = new SimpleDateFormat("dd-MM-yy");
        String CadenaFechar = Formator.format(FechaActual);
        SimpleDateFormat Formato = new SimpleDateFormat("EEEE, d 'de' MMMM 'de' yyyy, hh:mm:ss a");
        String CadenaFecha = Formato.format(FechaActual);
        String dirDestino = System.getenv("SIGAA_HOME") + "/documentos/consultas/reportes-excel/promedios/";
        String archivo = categoria + "-" + id_trimestre + "-PROMEDIO_1roPrim-4toSec_" + CadenaFechar + ".xls";
        String dirPlantilla = System.getenv("SIGAA_HOME") + "/documentos/plantillas_excel/consultas_promedio_all.xls";
        if (curso.getCurso() != null) {
            archivo = categoria + "-" + id_trimestre + "-PROMEDIO_" + curso.getId_curso() + "_" + CadenaFechar + ".xls";
            dirPlantilla = System.getenv("SIGAA_HOME") + "/documentos/plantillas_excel/consultas_promedio.xls";
        }
        try {
            Workbook plantilla = Workbook.getWorkbook(new File(dirPlantilla));
            WritableWorkbook workbook = Workbook.createWorkbook(new File(dirDestino + archivo), plantilla);
            WritableSheet sheet = workbook.getSheet("LISTA");
            WritableFont arial_11 = new WritableFont(WritableFont.ARIAL, 11);
            WritableCellFormat arial11 = new WritableCellFormat(arial_11);
            WritableFont arial_9 = new WritableFont(WritableFont.ARIAL, 9);
            WritableCellFormat arial9 = new WritableCellFormat(arial_9);
            WritableFont arial_9n = new WritableFont(WritableFont.ARIAL, 9, WritableFont.BOLD);
            WritableCellFormat arial9n = new WritableCellFormat(arial_9n);
            WritableFont arial_8 = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat arial8 = new WritableCellFormat(arial_8);
            sheet.addCell(new Label(0, 1, "GESTION ACADEMICA " + curso.getId_gestion(), arial11));
            if (curso.getCurso() == null) {
                sheet.addCell(new Label(3, 6, "1ro de primaria a 4to de secundaria", arial8));
            } else {
                sheet.addCell(new Label(3, 6, curso.getCurso() + " de " + curso.getCiclo(), arial8));
            }
            if (categoria.equals("A")) {
                sheet.addCell(new Label(3, 7, "FEMENINO Y MASCULINO", arial8));
            } else if (categoria.equals("F")) {
                sheet.addCell(new Label(3, 7, "FEMENINO", arial8));
            } else if (categoria.equals("M")) {
                sheet.addCell(new Label(3, 7, "MASCULINO", arial8));
            }
            if (id_trimestre.equals("T1")) {
                sheet.addCell(new Label(3, 8, "1er TRIMESTRE", arial8));
            } else if (id_trimestre.equals("T2")) {
                sheet.addCell(new Label(3, 8, "2do TRIMESTRE", arial8));
            } else if (id_trimestre.equals("T3")) {
                sheet.addCell(new Label(3, 8, "3er TRIMESTRE", arial8));
            } else if (id_trimestre.equals("A")) {
                sheet.addCell(new Label(3, 8, "PROMEDIO ANUAL", arial8));
            }

            sheet.addCell(new Label(3, 9, CadenaFecha, arial8));
            int cont = 1;
            for (int i = 0; i < curso.getEstudiantes().size(); i++) {
                Estudiante estudiante = (Estudiante) curso.getEstudiantes().get(i);
                if (categoria.equals("A")) {
                    sheet.addCell(new jxl.write.Number(1, cont + 12, cont, arial9));
                    sheet.addCell(new jxl.write.Number(2, cont + 12, estudiante.getCodigo(), arial9));
                    sheet.addCell(new Label(3, cont + 12, estudiante.getPaterno(), arial9));
                    sheet.addCell(new Label(4, cont + 12, estudiante.getMaterno(), arial9));
                    sheet.addCell(new Label(5, cont + 12, estudiante.getNombres(), arial9));
                    int sum = 0;
                    if (estudiante.getCurso() != null) {
                        sheet.addCell(new Label(6, cont + 12, estudiante.getCurso(), arial9));
                        sum = 1;
                    }
                    if (id_trimestre.equals("T1")) {
                        sheet.addCell(new jxl.write.Number(6 + sum, cont + 12, estudiante.getTrim1(), arial9n));
                    } else {
                        sheet.addCell(new jxl.write.Number(6 + sum, cont + 12, estudiante.getTrim1(), arial9));
                    }
                    if (id_trimestre.equals("T2")) {
                        sheet.addCell(new jxl.write.Number(7 + sum, cont + 12, estudiante.getTrim2(), arial9n));
                    } else {
                        sheet.addCell(new jxl.write.Number(7 + sum, cont + 12, estudiante.getTrim2(), arial9));
                    }
                    if (id_trimestre.equals("T3")) {
                        sheet.addCell(new jxl.write.Number(8 + sum, cont + 12, estudiante.getTrim3(), arial9n));
                    } else {
                        sheet.addCell(new jxl.write.Number(8 + sum, cont + 12, estudiante.getTrim3(), arial9));
                    }
                    if (id_trimestre.equals("A")) {
                        sheet.addCell(new jxl.write.Number(9 + sum, cont + 12, estudiante.getTotal(), arial9n));
                    } else {
                        sheet.addCell(new jxl.write.Number(9 + sum, cont + 12, estudiante.getTotal(), arial9));
                    }
                    cont++;
                }
                if (estudiante.getId_sexo().equals(categoria)) {
                    sheet.addCell(new jxl.write.Number(1, cont + 12, cont, arial9));
                    sheet.addCell(new jxl.write.Number(2, cont + 12, estudiante.getCodigo(), arial9));
                    sheet.addCell(new Label(3, cont + 12, estudiante.getPaterno(), arial9));
                    sheet.addCell(new Label(4, cont + 12, estudiante.getMaterno(), arial9));
                    sheet.addCell(new Label(5, cont + 12, estudiante.getNombres(), arial9));
                    int sum = 0;
                    if (estudiante.getCurso() != null) {
                        sheet.addCell(new Label(6, cont + 12, estudiante.getCurso(), arial9));
                        sum = 1;
                    }
                    if (id_trimestre.equals("T1")) {
                        sheet.addCell(new jxl.write.Number(6 + sum, cont + 12, estudiante.getTrim1(), arial9n));
                    } else {
                        sheet.addCell(new jxl.write.Number(6 + sum, cont + 12, estudiante.getTrim1(), arial9));
                    }
                    if (id_trimestre.equals("T2")) {
                        sheet.addCell(new jxl.write.Number(7 + sum, cont + 12, estudiante.getTrim2(), arial9n));
                    } else {
                        sheet.addCell(new jxl.write.Number(7 + sum, cont + 12, estudiante.getTrim2(), arial9));
                    }
                    if (id_trimestre.equals("T3")) {
                        sheet.addCell(new jxl.write.Number(8 + sum, cont + 12, estudiante.getTrim3(), arial9n));
                    } else {
                        sheet.addCell(new jxl.write.Number(8 + sum, cont + 12, estudiante.getTrim3(), arial9));
                    }
                    if (id_trimestre.equals("A")) {
                        sheet.addCell(new jxl.write.Number(9 + sum, cont + 12, estudiante.getTotal(), arial9n));
                    } else {
                        sheet.addCell(new jxl.write.Number(9 + sum, cont + 12, estudiante.getTotal(), arial9));
                    }
                    cont++;
                }
            }
            workbook.write();
            workbook.close();
        } catch (Exception de) {
            de.printStackTrace();
        }
        return archivo;
    }
}
