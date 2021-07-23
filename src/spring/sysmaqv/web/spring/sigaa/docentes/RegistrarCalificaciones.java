/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.sysmaqv.web.spring.sigaa.docentes;

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
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import spring.sysmaqv.domain.Conducta;
import spring.sysmaqv.domain.Curso;
import spring.sysmaqv.domain.CursoMateria;
import spring.sysmaqv.domain.Estudiante;
import spring.sysmaqv.domain.Evaluacion;
import spring.sysmaqv.domain.Gestion;
import spring.sysmaqv.domain.Materia;
import spring.sysmaqv.domain.Nota;
import spring.sysmaqv.domain.Personal;
import spring.sysmaqv.domain.PlanEvaluacion;
import spring.sysmaqv.domain.logic.SigaaInterface;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.util.WebUtils;
import spring.sysmaqv.domain.PeriodoCurso;

/**
 *
 * @author Marco Antonio Quenta Velasco
 */
public class RegistrarCalificaciones implements Controller {

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
            String nueva_opcion = request.getParameter("nueva_opcion");
            if (nueva_opcion != null) {
                String cualitativa = request.getParameter("cualitativa");
                String idest = request.getParameter("idest");
                String ideva = request.getParameter("ideva");
//                List profesiones = new ArrayList();
//                profesiones = cualitativa.split("_");
//                System.out.println(idest);
//                System.out.println(ideva);
                Evaluacion evaluacion = new Evaluacion();
                evaluacion.setId_estudiante(idest);
                evaluacion.setId_evaluacion(ideva);
                evaluacion.setCualitativa(cualitativa.toUpperCase());
                this.sigaa.setActualizarCualitativa(evaluacion);
                response.sendRedirect(url);
            }
            String id_curso = request.getParameter("id_curso");
            Gestion gestion = this.sigaa.getGestionActivo();
            retorno.put("tipo_meses", this.sigaa.getTipoMeses());
            String guardar = request.getParameter("guardar");
            String guardar_notas = request.getParameter("guardar_notas");
            if (id_curso != null) {
                String id_curso_materia = request.getParameter("id_curso_materia");
                String id_materia = request.getParameter("id_materia");
                String imprimirListaPdf = request.getParameter("imprimirListaPdf");
                String imprimirPdf = request.getParameter("imprimirPdf");
                String downloadExcel = request.getParameter("downloadExcel");
                CursoMateria cursomateria = new CursoMateria();
                cursomateria.setId_curso_materia(id_curso_materia);
                cursomateria.setId_curso(id_curso);
                cursomateria.setId_gestion(gestion.getId_gestion());
                Curso curso = this.sigaa.getCursoById(cursomateria);
                curso.setId_gestion(gestion.getId_gestion());
                Materia materia = this.sigaa.getMateriaById(id_materia);
                if (imprimirPdf != null) {
                    String pdf = this.estudiantesParaleloPdf(curso, materia, personal);
                    response.sendRedirect("documentos/docentes/" + pdf);
                }
                if (imprimirListaPdf != null) {
                    String pdf = this.estudiantesCursoMateriaPdf(curso, materia, personal);
                    response.sendRedirect("documentos/docentes/" + pdf);
                }
                if (downloadExcel != null) {
                    String periodo = request.getParameter("periodo");
                    String cant_dps = request.getParameter("cant_dps");
                    String excel = this.estudiantesCursoMateriaExcel(curso, materia, personal, Integer.parseInt(periodo), Integer.parseInt(cant_dps));
                    response.sendRedirect("documentos/docentes/listas_excel/" + excel);
                }

                if (guardar != null) {
                    SimpleDateFormat fec = new SimpleDateFormat("dd-MM-yyyy");
                    String evaluacion = request.getParameter("evaluacion");
                    String dia = request.getParameter("dia");
                    String mes = request.getParameter("mes");
                    String anio = request.getParameter("anio");
                    String descripcion = request.getParameter("descripcion");
                    String id_evaluacion = request.getParameter("id_evaluacion");
                    PlanEvaluacion planevaluacion = new PlanEvaluacion();
                    planevaluacion.setId_evaluacion(id_evaluacion);
                    planevaluacion.setEvaluacion(evaluacion);
                    planevaluacion.setNota(60);
                    planevaluacion.setDps(10);
                    planevaluacion.setFec_evaluacion(fec.parse(dia + "-" + mes + "-" + anio));
                    planevaluacion.setDescripcion(descripcion);
                    planevaluacion.setEstado("activo");
                    planevaluacion.setId_curso(id_curso);
                    this.sigaa.setUpdatePlanEvaluaciones(planevaluacion);
                }
                List plan_evaluaciones = this.sigaa.getListaPlanEvaluacionesByIdCursoMateria(id_curso_materia);
                for (int ii = 0; ii < plan_evaluaciones.size(); ii++) {
                    PlanEvaluacion pe = (PlanEvaluacion) plan_evaluaciones.get(ii);
                    Calendar fec_lim = Calendar.getInstance();
                    fec_lim.setTime(pe.getFec_limite());
                    fec_lim.add(Calendar.DATE, 1);
                    Calendar fec_actual = Calendar.getInstance();
                    Calendar fec_eva = Calendar.getInstance();
                    fec_eva.setTime(pe.getFec_evaluacion());
                    PlanEvaluacion p_eva = new PlanEvaluacion();
                    p_eva.setId_evaluacion(pe.getId_evaluacion());
                    p_eva.setFec_limite(pe.getFec_limite());
                    if (fec_actual.after(fec_eva) && fec_actual.before(fec_lim)) {
                        p_eva.setEstado("activo");
                    } else {
                        p_eva.setEstado("inactivo");
                    }
                    this.sigaa.setUpdateEvaluacionByIdEvaluacion(p_eva);
                }
                if (guardar_notas != null) {
                    for (int i = 0; i < curso.getEstudiantes().size(); i++) {
                        Estudiante estudiante = (Estudiante) curso.getEstudiantes().get(i);
                        for (int j = 0; j < plan_evaluaciones.size(); j++) {
                            PlanEvaluacion planevaluacion = (PlanEvaluacion) plan_evaluaciones.get(j);
                            if (planevaluacion.getEstado().equals("activo")) {
                                Evaluacion evaluacion = new Evaluacion();
                                evaluacion.setId_estudiante(estudiante.getId_estudiante());
                                evaluacion.setId_evaluacion(planevaluacion.getId_evaluacion());
                                if (id_curso.equals("K") || id_curso.equals("K1") || id_curso.equals("K12") || id_curso.equals("K21")|| curso.getId_curso().equals("P1")|| curso.getId_curso().equals("P12")) {
                                    evaluacion.setNota(0);
                                } else {
                                    evaluacion.setNota(Integer.parseInt(request.getParameter(planevaluacion.getId_evaluacion() + "-" + estudiante.getId_estudiante())));
                                }
//                                evaluacion.setCualitativa();
                                if (j == 3 || id_curso.equals("K") || id_curso.equals("K1") || id_curso.equals("K12") || id_curso.equals("K21")|| curso.getId_curso().equals("P1")|| curso.getId_curso().equals("P12")) {
                                    evaluacion.setDps(0);
                                } else {
                                    evaluacion.setDps(Integer.parseInt(request.getParameter("dps-" + planevaluacion.getId_evaluacion() + "-" + estudiante.getId_estudiante())));
                                }
//                                if (id_curso.equals("P1") || id_curso.equals("P2") || id_curso.equals("P3") || id_curso.equals("P4") || id_curso.equals("P5") || id_curso.equals("P6") || id_curso.equals("P7") || id_curso.equals("P8")) {
//                                    evaluacion.setId_cualitativa(Integer.parseInt(request.getParameter("id_cualitativa-" + planevaluacion.getId_evaluacion() + "-" + estudiante.getId_estudiante())));
//                                } else {
                                evaluacion.setId_cualitativa(0);
//                                }
                                if (!id_curso.equals("K") && !id_curso.equals("K1") && !id_curso.equals("K12") && !id_curso.equals("K21")&& !id_curso.equals("P1")&& !id_curso.equals("P12")) {
                                    System.out.println("hollllla");
                                    this.sigaa.setUpdateEvaluacionByIdEstudianteAndIdEvaluacion(evaluacion);
                                } else {
                                    String cualitativa = request.getParameter("cualitativa-" + planevaluacion.getId_evaluacion() + "-" + estudiante.getId_estudiante());
                                    evaluacion.setCualitativa(cualitativa.toUpperCase());
                                    this.sigaa.setUpdateEvaluacionByIdEstudianteAndIdEvaluacionKinder(evaluacion);
                                }
                            }
                        }
                    }
                }

                if (request.getParameter("cerrarmateria") != null) {
                    int id_gestion = Integer.parseInt(request.getParameter("id_gestion"));
                    cursomateria.setId_gestion(id_gestion);
                    for (int i = 0; i < curso.getEstudiantes().size(); i++) {
                        Estudiante estudiante = (Estudiante) curso.getEstudiantes().get(i);
                        Nota nota = new Nota();
                        nota.setId_estudiante(estudiante.getId_estudiante());
                        nota.setId_curso(curso.getCursomateria().getId_curso());
                        nota.setId_materia(curso.getCursomateria().getId_materia());
                        nota.setId_gestion(curso.getCursomateria().getId_gestion());
                        nota.setId_docente(personal.getId_personal());
                        nota.setNota1(0);
                        nota.setNota2(0);
                        nota.setNota3(0);
                        nota.setNota4(0);
                        nota.setDps1(0);
                        nota.setDps2(0);
                        nota.setDps3(0);
                        nota.setNotaanual(0);
                        nota.setNotafinal(0);
                        nota.setCualitativa1("");
                        nota.setCualitativa2("");
                        nota.setCualitativa3("");
                        nota.setDiagnostico1("");
                        nota.setDiagnostico2("");
                        nota.setObservacion("APROBADO");
                        nota.setId_docente(personal.getId_personal());
                        if (curso.getId_curso().equals("K") || curso.getId_curso().equals("K1") || curso.getId_curso().equals("K12") || curso.getId_curso().equals("K21")|| curso.getId_curso().equals("P1")|| curso.getId_curso().equals("P12")) {
                            for (int j = 0; j < estudiante.getEvaluaciones().size(); j++) {
                                Evaluacion evaluacion = (Evaluacion) estudiante.getEvaluaciones().get(j);
                                if (j == 0) {
                                    nota.setCualitativa1(evaluacion.getCualitativa());
                                } else if (j == 1) {
                                    nota.setCualitativa2(evaluacion.getCualitativa());
                                } else if (j == 2) {
                                    nota.setCualitativa3(evaluacion.getCualitativa());
                                }
                            }
                            for (int j = 0; j < estudiante.getConductas().size(); j++) {
                                Conducta conducta = (Conducta) estudiante.getConductas().get(j);
                                if (j == 0) {
                                    nota.setDiagnostico1(conducta.getDiagnostico());
                                } else if (j == 1) {
                                    nota.setDiagnostico2(conducta.getDiagnostico());
                                }
                            }
                        } else {
                            for (int j = 0; j < estudiante.getEvaluaciones().size(); j++) {
                                Evaluacion evaluacion = (Evaluacion) estudiante.getEvaluaciones().get(j);
                                if (j == 0) {
                                    nota.setNota1(evaluacion.getNota());
                                    nota.setDps1(evaluacion.getDps());
                                    nota.setCualitativa1(evaluacion.getCualitativa_p());
                                } else if (j == 1) {
                                    nota.setNota2(evaluacion.getNota());
                                    nota.setDps2(evaluacion.getDps());
                                    nota.setCualitativa2(evaluacion.getCualitativa_p());
                                } else if (j == 2) {
                                    nota.setNota3(evaluacion.getNota());
                                    nota.setDps3(evaluacion.getDps());
                                    nota.setCualitativa3(evaluacion.getCualitativa_p());
                                    int sumaanualbruto = nota.getNota1() + nota.getNota2() + nota.getNota3() + nota.getDps1() + nota.getDps2() + nota.getDps3();
                                    nota.setNotaanual((int) (Math.round(((double) sumaanualbruto / 3) * Math.pow(10, 0)) / Math.pow(10, 0)));
                                    if (nota.getNotaanual() <= 35) {
                                        nota.setObservacion("REPROBADO");
                                        if (nota.getNota1() == 0 && nota.getNota2() == 0 && nota.getNota3() == 0) {
                                            nota.setObservacion("ABANDONO");
                                        }
                                    }
                                } else if (j == 3 && evaluacion.getNota() != 0) {
                                    nota.setNota4(evaluacion.getNota());
                                    if (nota.getNota4() != 0) {
                                        nota.setNotafinal((int) (Math.round(((double) (nota.getNotaanual() + nota.getNota4()) / 2) * Math.pow(10, 0)) / Math.pow(10, 0)));
                                        if (nota.getNotafinal() <= 35) {
                                            nota.setObservacion("REPROBADO");
                                            if (nota.getNota1() == 0 && nota.getNota2() == 0 && nota.getNota3() == 0) {
                                                nota.setObservacion("ABANDONO");
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        this.sigaa.setInsertOrUpdateNotas(nota);
                    }
                    retorno.put("elemento", "_elemento");
                    cursomateria = new CursoMateria();
                    cursomateria.setId_gestion(gestion.getId_gestion());
                    cursomateria.setId_docente(personal.getId_personal());
                    retorno.put("cursos", this.sigaa.getCursosByIdDocenteAndIdGestion(cursomateria));
                } else {
                    plan_evaluaciones = this.sigaa.getListaPlanEvaluacionesByIdCursoMateria(id_curso_materia);
                    retorno.put("cant_evaluaciones", plan_evaluaciones.size());
                    retorno.put("plan_evaluaciones", plan_evaluaciones);
                    int cant_dps = -1;
                    if (plan_evaluaciones.size() > 0) {
                        cant_dps = 0;
                        for (int i = 0; i < plan_evaluaciones.size(); i++) {
                            PlanEvaluacion pe = (PlanEvaluacion) plan_evaluaciones.get(i);
                            cant_dps = cant_dps + pe.getDps();
                        }
                    }
                    retorno.put("cant_dps", cant_dps);
                    curso = this.sigaa.getCursoById(cursomateria);
                    curso.setId_gestion(gestion.getId_gestion());
                    PeriodoCurso periodocurso = this.sigaa.getPeriodoByIdcursoAndGestion(curso);
                    String idperiodo = Integer.toString(periodocurso.getIdperiodo());
                    int periodo = Integer.parseInt(idperiodo.substring(4, 5));
                    System.out.println(periodo);
                    retorno.put("periodo", periodo);
                    retorno.put("curso", curso);
                    retorno.put("materia", materia);
                    retorno.put("id_curso_materia", id_curso_materia);
                    CursoMateria cm = new CursoMateria();
                    cm.setId_curso(id_curso);
                    cm.setId_docente(personal.getId_personal());
                    cm.setId_gestion(gestion.getId_gestion());
                    List cualitativas = null;
                    if (id_curso.equals("P2") || id_curso.equals("P21")|| id_curso.equals("P2T") || id_curso.equals("P3") || id_curso.equals("P31") || id_curso.equals("P4") || id_curso.equals("P41") || id_curso.equals("P5") || id_curso.equals("P51")|| id_curso.equals("P61")|| id_curso.equals("P6")|| id_curso.equals("P71")|| id_curso.equals("P7")) {
                        cualitativas = this.sigaa.getNotasCualitativasById_curso_materia(id_curso_materia); // cualitativas = this.sigaa.getNotasCualitativasByIdDocenteAndIdcurso(cm);
                    } else {
                        cualitativas = this.sigaa.getNotasCualitativasById_curso_materia(id_curso_materia);
                    }
                    retorno.put("cualitativas", cualitativas);
                }
                retorno.put("gestion", gestion);
                return new ModelAndView(this.perfectView, retorno);
            }
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            if (isMultipart == true) {
                String id_evaluacion = "";
                String id_materia = "";
                String fichero = "";
                String columna = "";
                int cant_dps = 0;
                int periodo = 0;
                String id_curso_materia = "";
                CursoMateria cursomateria = new CursoMateria();
                try {
                    FileItemFactory factory = new DiskFileItemFactory();
                    ServletFileUpload upload = new ServletFileUpload(factory);
                    List fileItems = upload.parseRequest(request);
                    Iterator it = fileItems.iterator();
                    FileItem fi;

                    String direccion = System.getenv("SIGAA_HOME1") + "/documentos/docentes/notas_excel/";
                    String nombre_archivo = "";
                    while (it.hasNext()) {
                        fi = (FileItem) it.next();
                        if (fi.getFieldName().equals("periodo")) {
                            periodo = Integer.parseInt(fi.getString());
                        }
                        if (fi.getFieldName().equals("cant_dps")) {
                            cant_dps = Integer.parseInt(fi.getString());
                        }
                        if (fi.getFieldName().equals("id_curso")) {
                            id_curso = fi.getString();
                        }
                        if (fi.getFieldName().equals("id_materia")) {
                            id_materia = fi.getString();
                        }
                        if (fi.getFieldName().equals("id_curso_materia")) {
                            id_curso_materia = fi.getString();
                        }
                        if (fi.getFieldName().equals("columna")) {
                            columna = fi.getString();
                        }
                        if (fi.getFieldName().equals("id_evaluacion")) {
                            id_evaluacion = fi.getString();
                        }
                        if (fi.getFieldName().equals("fichero")) {
                            fichero = fi.getString();
                            if (!fi.getString().equals("")) {
                                nombre_archivo = id_evaluacion + ".xls";
                                fi.write(new File(direccion + nombre_archivo));
                            }
                        }
                    }
                    cursomateria.setId_curso_materia(id_curso_materia);
                    cursomateria.setId_curso(id_curso);
                    cursomateria.setId_gestion(gestion.getId_gestion());
                    Curso curso = this.sigaa.getCursoById(cursomateria);
                    int col = 0;
                    int fila = 0;
                    int ic = -4;
                    if (periodo == 3 && (id_curso.equals("K") || id_curso.equals("K1") || id_curso.equals("K12") || id_curso.equals("K21")|| curso.getId_curso().equals("P1")|| curso.getId_curso().equals("P12"))) {
                        if (columna.equals("1")) {
                            col = 5;
                            fila = 12;
                        } else if (columna.equals("2")) {
                            col = 6;
                            fila = 13;
                        } else if (columna.equals("3")) {
                            col = 7;
                            fila = 14;
                        }
                    }
                    if (periodo == 3 && cant_dps > 0) {
                        if (columna.equals("1")) {
                            col = 5;
                            fila = 12;
                        } else if (columna.equals("2")) {
                            if (id_curso.equals("K") || id_curso.equals("K1") || id_curso.equals("K12") || id_curso.equals("K21")|| curso.getId_curso().equals("P1")|| curso.getId_curso().equals("P12")) {
                                col = 6;
                            } else {
                                col = 8;
                            }
                            fila = 13;
                        } else if (columna.equals("3")) {
                            if (id_curso.equals("K") || id_curso.equals("K1") || id_curso.equals("K12") || id_curso.equals("K21")|| curso.getId_curso().equals("P1")|| curso.getId_curso().equals("P12")) {
                                col = 7;
                            } else {
                                col = 11;
                            }
                            fila = 14;
                        } else if (columna.equals("4")) {
                            col = 15;
                        }
                    }
                    if (periodo == 3 && cant_dps == 0) {
                        if (columna.equals("1")) {
                            col = 5;
                            ic = -4;
                        } else if (columna.equals("2")) {
                            col = 6;
                            ic = -3;
                        } else if (columna.equals("3")) {
                            col = 7;
                            ic = -2;
                        } else if (columna.equals("4")) {
                            col = 9;
                            ic = -1;
                        }
                    }
                    if (periodo == 2 && cant_dps == 0) {
                        if (curso.getId_curso().equals("K") || curso.getId_curso().equals("K1") || curso.getId_curso().equals("K12") || curso.getId_curso().equals("K21")|| curso.getId_curso().equals("P1")|| curso.getId_curso().equals("P12")) {
                            if (columna.equals("1")) {
                                col = 5;
                                ic = -4;
                            } else if (columna.equals("2")) {
                                col = 6;
                                ic = -3;
                            } else if (columna.equals("3")) {
                                col = 7;
                                ic = -2;
                            } else if (columna.equals("4")) {
                                col = 8;
                                ic = -1;
                            }
                            /* switch (columna) {
                             case "1":
                             col = 5;
                             ic = -4;
                             break;
                             case "2":
                             col = 6;
                             ic = -3;
                             break;
                             case "3":
                             col = 7;
                             ic = -2;
                             break;
                             case "4":
                             col = 8;
                             ic = -1;
                             break;
                             }*/
                        } else {
                            if (columna.equals("1")) {
                                col = 5;
                                ic = -4;
                            } else if (columna.equals("2")) {
                                col = 6;
                                ic = -3;
                            } else if (columna.equals("3")) {
                                col = 7;
                                ic = -2;
                            } else if (columna.equals("4")) {
                                col = 8;
                                ic = -1;
                            } else if (columna.equals("5")) {
                                col = 10;
                                ic = 0;//nada
                            }
                        }
                    }
                    //System.out.println(periodo + "___" + cant_dps+"____"+curso.getId_curso());
                    //System.out.println(direccion+""+nombre_archivo);
                    Materia materia = this.sigaa.getMateriaById(id_materia);
                    if (periodo == 0) {
                        if (!fichero.equals("")) {
                            Workbook workbook = Workbook.getWorkbook(new File(System.getenv("SIGAA_HOME1") + "/documentos/docentes/notas_excel/" + nombre_archivo));
                            Sheet sheet = workbook.getSheet("NOTAS_CUANTITATIVAS");
//                        Sheet sheet2 = workbook.getSheet("NOTAS_CUALITATIVAS");
/* capturar el valor de de ponderacion 100 o 90*/
                            Evaluacion evaluacion = new Evaluacion();
                            for (int i = 0; i < curso.getEstudiantes().size(); i++) {
                                Estudiante est = (Estudiante) curso.getEstudiantes().get(i);
                                String id_estudiante = sheet.getCell(2, i + 12).getContents();
                                String nota = "";
                                String dps = "";
                                String cualitativa = "";
                                if (id_curso.equals("P21") || id_curso.equals("P2") || id_curso.equals("P2T") || id_curso.equals("P3") || id_curso.equals("P31") || id_curso.equals("P4") || id_curso.equals("P41") || id_curso.equals("P5") || id_curso.equals("P51") || id_curso.equals("P6") || id_curso.equals("P61") || id_curso.equals("P7") || id_curso.equals("P8")|| id_curso.equals("P71") || id_curso.equals("P81") || id_curso.equals("S1") || id_curso.equals("S2") || id_curso.equals("S3") || id_curso.equals("S4")) {
                                    dps = sheet.getCell(col + 1, i + 12).getContents();
                                }
                                if (id_curso.equals("K") || id_curso.equals("K1") || id_curso.equals("K12") || id_curso.equals("K21")||id_curso.equals("P1") || id_curso.equals("P12") ) {
                                    cualitativa = sheet.getCell(col, i + 12).getContents();
                                } else {
                                    /* p1-p6             p7-s4  solo para primer parcial  debe ser i=0*/
                                    nota = sheet.getCell(col, i + 12).getContents();
                                }
//                            if (!id_curso.equals("K") && !id_curso.equals("S1") && !id_curso.equals("S2") && !id_curso.equals("S3") && !id_curso.equals("S4")) {
//                                cualitativa = sheet2.getCell(6, fila).getContents();
//                            }
                                fila = fila + 3;
                                String mat = sheet.getCell(4, 4).getContents();
                                if (mat.equals(materia.getMateria())) {
                                    if (id_estudiante.equals(est.getId_estudiante())) {
                                        evaluacion.setId_estudiante(id_estudiante);
                                        evaluacion.setId_evaluacion(id_evaluacion);
                                        if (!nota.equals("")) {
                                            if (Integer.parseInt(nota) > 35) {
                                                evaluacion.setNota(Integer.parseInt(nota));
                                            } else {
                                                evaluacion.setNota(0);
                                            }
                                        } else {
                                            evaluacion.setNota(0);
                                        }
                                        if (!dps.equals("")) {
                                            evaluacion.setDps(Integer.parseInt(dps));
                                        } else {
                                            evaluacion.setDps(0);
                                        }
                                        evaluacion.setCualitativa(cualitativa);
                                        evaluacion.setId_cur(id_curso);
                                        this.sigaa.getUpdateEvaluacionesByIdEstudianteAndIdEvaluacion(evaluacion);
                                    }
                                }
                            }
                            workbook.close();
                        }
                    }
                    String mensaje1 = "--- CALIFICACION CUANTITATIVA ---";
                    String mensaje2 = "--- CALIFICACION CUALITATIVA ---";
                    String mensaje3 = "--- CALIFICACION DPS ---";
                    if (periodo == 2) {
                        if (!fichero.equals("")) {
                            Workbook workbook = Workbook.getWorkbook(new File(System.getenv("SIGAA_HOME1") + "/documentos/docentes/notas_excel/" + nombre_archivo));
                            Sheet sheet = workbook.getSheet("NOTAS_CUANTITATIVAS");
                            // Sheet sheet2 = workbook.getSheet("NOTAS_CUALITATIVAS");
                            Evaluacion evaluacion = new Evaluacion();
                            int num1 = 0;
                            int num2 = 0;
                            System.out.println(periodo + "___" + cant_dps + "__dddddddddd__" + curso.getId_curso());
                            for (int i = 0; i < curso.getEstudiantes().size(); i++) {
                                Estudiante est = (Estudiante) curso.getEstudiantes().get(i);
                                String id_estudiante = sheet.getCell(2, i + 12).getContents();
                                if (curso.getId_curso().equals("K") || curso.getId_curso().equals("K1") || curso.getId_curso().equals("K12") || curso.getId_curso().equals("K21")||id_curso.equals("P1") || id_curso.equals("P12") ) {
                                    String nota = sheet.getCell(col, i + 12).getContents();
                                    String mat = sheet.getCell(4, 4).getContents();
                                    if (mat.equals(materia.getMateria())) {
                                        if (id_estudiante.equals(est.getId_estudiante())) {
                                            evaluacion.setId_estudiante(id_estudiante);
                                            evaluacion.setId_evaluacion(id_evaluacion);
                                            evaluacion.setCualitativa(nota.toUpperCase());
                                            evaluacion.setDps(0);
                                            evaluacion.setId_cur(id_curso);
                                            //System.out.println(nota+"-->"+est.getId_estudiante()+"-----"+materia.getMateria()+"---"+id_evaluacion);
                                            this.sigaa.getUpdateEvaluacionesByIdEstudianteAndIdEvaluacion(evaluacion);
                                        }
                                    }
                                } else {
                                    String nota = "";
                                    int dps = 0;
                                    ic = ic + 4;
                                    //System.out.println(periodo + "__ddsdssds___"+curso.getId_curso());
                                    //   String cualitativa = sheet2.getCell(6, ic + 12).getContents();
                                    nota = sheet.getCell(col, i + 12).getContents();

                                    String mat = sheet.getCell(4, 4).getContents();
                                    if (mat.equals(materia.getMateria())) {
                                        if (id_estudiante.equals(est.getId_estudiante())) {
                                            evaluacion.setId_estudiante(id_estudiante);
                                            evaluacion.setId_evaluacion(id_evaluacion);
                                            if (!nota.equals("")) {
                                                evaluacion.setNota(Integer.parseInt(nota));
                                                if (((evaluacion.getNota() > 0 && evaluacion.getNota() < 30)) || evaluacion.getNota() > 100 || evaluacion.getNota() == 50) {
                                                    num1++;
                                                    mensaje1 = mensaje1 + "| " + num1 + ".- " + est.getNombres() + " " + est.getPaterno() + " " + est.getMaterno() + ", Calificacion : " + nota;
                                                    evaluacion.setNota(0);
                                                }
                                            } else {
                                                evaluacion.setNota(0);
                                            }
                                            evaluacion.setDps(dps);
                                           // if (cualitativa.length() <= 150) {
                                            //     evaluacion.setCualitativa(cualitativa.toUpperCase());
                                            // } else {
                                            num2++;
                                            mensaje2 = mensaje2 + "| " + num2 + ".- " + est.getNombres() + " " + est.getPaterno() + " " + est.getMaterno() + ", Cualitativa tiene  caracteres.";
                                            evaluacion.setCualitativa("");
                                            //  }
                                            evaluacion.setId_cur(id_curso);
                                            this.sigaa.getUpdateEvaluacionesByIdEstudianteAndIdEvaluacion(evaluacion);
                                        }
                                    }
                                }
                            }
                            workbook.close();
                            String mensaje = "";
                            if (num1 >= 1) {
                                mensaje = mensaje1;
                            }
                            if (num2 >= 1) {
                                mensaje = mensaje + "|" + mensaje2;
                            }
                            retorno.put("mensaje", mensaje);
                        }
                    }
                    if (periodo == 3) {
                        if (!fichero.equals("")) {
                            Workbook workbook = Workbook.getWorkbook(new File(System.getenv("SIGAA_HOME1") + "/documentos/docentes/notas_excel/" + nombre_archivo));
                            Sheet sheet = workbook.getSheet("NOTAS_CUANTITATIVAS");
                            Sheet sheet2 = workbook.getSheet("NOTAS_CUALITATIVAS");
                            Evaluacion evaluacion = new Evaluacion();
                            int num1 = 0;
                            int num2 = 0;
                            int num3 = 0;
                            for (int i = 0; i < curso.getEstudiantes().size(); i++) {
                                Estudiante est = (Estudiante) curso.getEstudiantes().get(i);
                                String id_estudiante = sheet.getCell(2, i + 12).getContents();
                                String nota = "";
                                int dps = 0;
                                String cualitativa = "";
                                if (!id_curso.equals("K") && !id_curso.equals("K1") && !id_curso.equals("KT") && !id_curso.equals("K12") && !id_curso.equals("K21")&& !id_curso.equals("P1") && !id_curso.equals("P12") ) {
                                    if (cant_dps == 0) {
                                        ic = ic + 4;
                                        cualitativa = sheet2.getCell(6, ic + 12).getContents();
                                    } else {
                                        String dps_string = sheet.getCell(col + 1, i + 12).getContents();
                                        dps = Integer.parseInt(dps_string);

                                    }
                                    nota = sheet.getCell(col, i + 12).getContents();
                                } else {
                                    nota = "0";
                                    dps = 0;
                                    cualitativa = sheet.getCell(col, i + 12).getContents();
                                }

                                String mat = sheet.getCell(4, 4).getContents();
                                if (mat.equals(materia.getMateria())) {
                                    if (id_estudiante.equals(est.getId_estudiante())) {
                                        evaluacion.setId_estudiante(id_estudiante);
                                        evaluacion.setId_evaluacion(id_evaluacion);
                                        evaluacion.setDps(dps);
                                        if (!nota.equals("")) {
                                            evaluacion.setNota(Integer.parseInt(nota));
                                            if (cant_dps == 0) {
                                                if (((evaluacion.getNota() > 0 && evaluacion.getNota() < 35)) || evaluacion.getNota() > 100 || evaluacion.getNota() ==50) {
                                                    num1++;
                                                    mensaje1 = mensaje1 + "| " + num1 + ".- " + est.getNombres() + " " + est.getPaterno() + " " + est.getMaterno() + ", Calificacion : " + nota;
                                                    evaluacion.setNota(0);
                                                }
                                            }
                                            if (cant_dps > 0) {
                                                if (((evaluacion.getNota() > 0 && evaluacion.getNota() < 15)) || evaluacion.getNota() > 60) {
                                                    num1++;
                                                    mensaje1 = mensaje1 + "| " + num1 + ".- " + est.getNombres() + " " + est.getPaterno() + " " + est.getMaterno() + ", Calificacion : " + nota;
                                                    evaluacion.setNota(0);
                                                }
                                                if (((evaluacion.getDps() > 0 && evaluacion.getDps() < 5)) || evaluacion.getDps() > 10) {
                                                    num3++;
                                                    mensaje3 = mensaje3 + "| " + num3 + ".- " + est.getNombres() + " " + est.getPaterno() + " " + est.getMaterno() + ", DPS : " + dps;
                                                    evaluacion.setDps(0);
                                                }
                                            }
                                        } else {
                                            evaluacion.setNota(0);
                                        }
                                        if (cant_dps == 0) {
                                            if (cualitativa.length() <= 250) {
                                                evaluacion.setCualitativa(cualitativa.toUpperCase());
                                            } else {
                                                num2++;
                                                mensaje2 = mensaje2 + "| " + num2 + ".- " + est.getNombres() + " " + est.getPaterno() + " " + est.getMaterno() + ", Cualitativa tiene " + cualitativa.length() + " caracteres.";
                                                evaluacion.setCualitativa("");
                                            }
                                        } else {
                                            evaluacion.setCualitativa("");
                                        }
                                        evaluacion.setId_cur(id_curso);
                                        this.sigaa.getUpdateEvaluacionesByIdEstudianteAndIdEvaluacion(evaluacion);
                                    }
                                }
                            }
                            workbook.close();
                            String mensaje = "";
                            if (num1 >= 1) {
                                mensaje = mensaje1;
                            }
                            if (num2 >= 1) {
                                mensaje = mensaje + "|" + mensaje2;
                            }
                            if (num3 >= 1) {
                                mensaje = mensaje + "|" + mensaje3;
                            }
                            System.out.println("----->"+mensaje);
                            retorno.put("mensaje", mensaje);
                        }
                    }
                    retorno.put("periodo", periodo);
                    retorno.put("gestion", gestion);
                    retorno.put("id_curso_materia", id_curso_materia);
                    List plan_evaluaciones = this.sigaa.getListaPlanEvaluacionesByIdCursoMateria(id_curso_materia);
                    retorno.put("cant_evaluaciones", plan_evaluaciones.size());
                    retorno.put("plan_evaluaciones", plan_evaluaciones);
                    cant_dps = -1;
                    if (plan_evaluaciones.size() > 0) {
                        cant_dps = 0;
                        for (int i = 0; i < plan_evaluaciones.size(); i++) {
                            PlanEvaluacion pe = (PlanEvaluacion) plan_evaluaciones.get(i);
                            cant_dps = cant_dps + pe.getDps();
                        }
                    }
                    retorno.put("cant_dps", cant_dps);
                    retorno.put("materia", materia);
                    retorno.put("curso", this.sigaa.getCursoById(cursomateria));
                    retorno.put("cualitativas", this.sigaa.getNotasCualitativasById_curso_materia(id_curso_materia));
                    return new ModelAndView(this.perfectView, retorno);
                } catch (Exception de) {
                    de.printStackTrace();
                    return new ModelAndView(this.perfectView, retorno);
                }
            }
            retorno.put("elemento", "_elemento");
            retorno.put("gestion", gestion);
            CursoMateria cursomateria = new CursoMateria();
            cursomateria.setId_gestion(gestion.getId_gestion());
            cursomateria.setId_docente(personal.getId_personal());
            retorno.put("cursos", this.sigaa.getCursosByIdDocenteAndIdGestion(cursomateria));
            return new ModelAndView(this.perfectView, retorno);
        } else {
            return new ModelAndView(this.ecxessTimeView, "url", url);
        }
    }

    private String formatearFecha(Date fecha) {
        String nuevo = new String();
        SimpleDateFormat formato = new SimpleDateFormat("EEEE, d 'de' MMMM 'de' yyyy, hh:mm:ss a");
        nuevo = formato.format(fecha);
        return nuevo;
    }

    private String formatFecha(Date fecha) {
        String nuevo = new String();
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        nuevo = formato.format(fecha);
        return nuevo;
    }

    private String estudiantesCursoMateriaPdf(Curso curso, Materia materia, Personal personal) {
        String dir = System.getenv("SIGAA_HOME1") + "/documentos/docentes/";
        String archivo = "Nomina_" + curso.getCurso() + "_" + materia.getMateria() + "(" + this.formatFecha(new Date()) + ").pdf";
        Font font8 = FontFactory.getFont(FontFactory.HELVETICA, 8);
        Font font8b = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8);
        Font font2space = FontFactory.getFont(FontFactory.HELVETICA, 2);
        Document document = new Document(PageSize.LETTER);
        try {

            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dir + archivo));
            float width = document.getPageSize().getWidth();
            document.open();
            int n = curso.getEstudiantes().size();
            int pages = n / 50;
            if (n % 50 != 0) {
                pages += 1;
            }
            int index = 0;
            for (int x = 1; x <= pages; x++) {
                if (x > 1) {
                    document.newPage();
                }
                Image png = Image.getInstance(System.getenv("SIGAA_HOME1") + "/imagenes/iconos_sigaa/sigaa_oficial.png");
                png.setAbsolutePosition(470, 740);
                png.scalePercent(25);
                document.add(png);

                document.add(new Phrase("COLEGIO \"SANTA TERESA\"", font8b));
                document.add(Chunk.NEWLINE);
                document.add(new Phrase("GESTION " + curso.getId_gestion(), font8b));
                document.add(Chunk.NEWLINE);
                document.add(new Phrase("La Paz - Bolivia", font8b));
                document.add(Chunk.NEWLINE);
                Paragraph p = new Paragraph(new Phrase("NÃ“MINA DE ESTUDIANTES"));
                p.setAlignment(Element.ALIGN_CENTER);
                document.add(p);
                p = new Paragraph(new Phrase("Curso " + curso.getCurso() + " de " + curso.getCiclo() + "\n Materia " + materia.getMateria(), font8));
                p.setAlignment(Element.ALIGN_CENTER);
                document.add(p);
                document.add(new Phrase(" ", font2space));

                PdfPCell cell = null;
                float[] columnDefinitionSize = {5F, 10F, 40F, 45F};
                PdfPTable table = new PdfPTable(columnDefinitionSize);
                table.getDefaultCell().setBorder(0);
                table.setHorizontalAlignment(0);
                table.setTotalWidth(width - 72);
                table.calculateHeightsFast();

                table.getDefaultCell().setGrayFill(0.9f);
                table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(new Phrase("No.", font8));
                table.addCell(new Phrase("CÃ³digo", font8));
                table.addCell(new Phrase("Apellidos y nombres", font8));
                table.addCell(new Phrase("", font8));
                table.getDefaultCell().setGrayFill(1);
                table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);

                for (int i = 1; i <= 50; i++) {
                    if (index < curso.getEstudiantes().size()) {
                        Estudiante estudiante = (Estudiante) curso.getEstudiantes().get(index);
                        cell = new PdfPCell(new Phrase(Integer.toString(index + 1), font8));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setBorder(0);
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase(Integer.toString(estudiante.getCodigo()), font8));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setBorder(0);
                        table.addCell(cell);
                        table.addCell(new Phrase(estudiante.getPaterno() + " " + estudiante.getMaterno() + " " + estudiante.getNombres(), font8));
                        cell = new PdfPCell(new Phrase("", font8b));
                        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                        cell.setBorder(0);
                        table.addCell(cell);
                        index++;
                    }
                }
                float[] wid = table.getAbsoluteWidths();
                wid[0] = 40;
                wid[1] = 100;
                wid[2] = 400;
                wid[3] = 100;
                table.setWidths(wid);
                table.setWidthPercentage(100);
                document.add(table);

                document.add(new Phrase(" ", font2space));
                p = new Paragraph(new Phrase("Prof. " + personal.getNombres() + " " + personal.getPaterno() + " " + personal.getMaterno(), font8));
                p.setAlignment(Element.ALIGN_CENTER);
                document.add(p);
                p = new Paragraph(new Phrase("", font8));
                p.setAlignment(Element.ALIGN_CENTER);
                document.add(p);

                p = new Paragraph(new Phrase(this.formatearFecha(new Date()), font8));
                p.setAlignment(Element.ALIGN_RIGHT);
                document.add(p);
                p = new Paragraph(new Phrase("SIGAA Sistema Integrado de GestiÃ³n AcadÃ©mico - Administrativa " + curso.getId_gestion(), font8));
                document.add(p);
            }
        } catch (Exception de) {
            de.printStackTrace();
        }

        document.close();
        return archivo;
    }

    private String estudiantesParaleloPdf(Curso curso, Materia materia, Personal personal) {
        String dir = System.getenv("SIGAA_HOME1") + "/documentos/docentes/";
        String archivo = "Actas_" + curso.getCurso() + "_" + materia.getMateria() + "(" + this.formatFecha(new Date()) + ").pdf";
        Font font8 = FontFactory.getFont(FontFactory.HELVETICA, 8);
        Font font8b = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8);
        Font font2space = FontFactory.getFont(FontFactory.HELVETICA, 2);

        Document document = new Document(PageSize.LETTER);
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dir + archivo));
            float width = document.getPageSize().getWidth();
            document.open();
            int n = curso.getEstudiantes().size();
            int pages = n / 35;
            if (n % 35 != 0) {
                pages += 1;
            }
            int index = 0;
            for (int x = 1; x <= pages; x++) {
                if (x > 1) {
                    document.newPage();
                }
                document.open();
                Image png = Image.getInstance(System.getenv("SIGAA_HOME1") + "/imagenes/iconos_sigaa/sigaa_oficial.png");
                png.setAbsolutePosition(470, 740);
                png.scalePercent(25);
                document.add(png);
                document.add(Chunk.NEWLINE);
                document.add(new Phrase("COLEGIO \"SANTA TERESA\"", font8b));
                document.add(Chunk.NEWLINE);
                document.add(new Phrase("GESTION " + curso.getId_gestion(), font8b));
//                document.add(Chunk.NEWLINE);
//                document.add(new Phrase("La Paz - Bolivia", font8b));
                Paragraph p = new Paragraph(new Phrase("CALIFICACIONES"));
                p.setAlignment(Element.ALIGN_CENTER);
                document.add(p);
                p = new Paragraph(new Phrase("Curso " + curso.getCurso() + " de " + curso.getCiclo() + "\n Materia " + materia.getMateria(), font8));
                p.setAlignment(Element.ALIGN_CENTER);
                document.add(p);
                document.add(new Phrase(" ", font2space));
                PdfPCell cell = null;

                PdfPTable tevas = new PdfPTable(6);
                tevas.getDefaultCell().setBorder(0);
                tevas.setHorizontalAlignment(0);
                tevas.getDefaultCell().setGrayFill(0.9f);
                tevas.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
//                tevas.addCell(new Phrase("NRO.", font8));
//                tevas.addCell(new Phrase("TRIMESTRE", font8));
//                tevas.addCell(new Phrase("NOTA", font8));
//                tevas.addCell(new Phrase("DPS", font8));
//                tevas.addCell(new Phrase("TOTAL", font8));
//                tevas.addCell(new Phrase("FECHA LÃMITE DE REGISTRO DE NOTAS", font8));
//                tevas.getDefaultCell().setGrayFill(1);
////                tevas.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
//                for (int i = 0; i < curso.getEvaluaciones().size(); i++) {
//                    PlanEvaluacion ev = (PlanEvaluacion) curso.getEvaluaciones().get(i);
//                    tevas.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
//                    tevas.addCell(new Phrase(Integer.toString(i + 1), font8));
//                    tevas.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
//                    tevas.addCell(new Phrase(ev.getEvaluacion(), font8));
//                    tevas.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
//                    tevas.addCell(new Phrase(Integer.toString(ev.getNota()) + " Pts.", font8));
//                    tevas.addCell(new Phrase(Integer.toString(ev.getDps()) + " Pts.", font8));
//                    tevas.addCell(new Phrase(Integer.toString(ev.getNota() + ev.getDps()) + " Pts.", font8));
//                    tevas.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
//                    tevas.addCell(new Phrase(ev.getSfec_limite(), font8));
//                }
                int[] evis = {10, 20, 10, 10, 10, 40};
                tevas.setWidths(evis);
                tevas.setWidthPercentage(90);
                tevas.setHorizontalAlignment(Element.ALIGN_CENTER);
                document.add(tevas);
                document.add(new Phrase(" ", font2space));

                int sumaaprob = 0;
                int sumareprob = 0;
                PdfPTable table = new PdfPTable(curso.getEvaluaciones().size() + 5);
                table.getDefaultCell().setBorder(0);
                table.setHorizontalAlignment(0);
                table.setTotalWidth(width - 72);
                table.calculateHeightsFast();

                table.getDefaultCell().setGrayFill(0.9f);
                table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(new Phrase("Nro.", font8));
                table.addCell(new Phrase("CÃ³digo", font8));
                table.addCell(new Phrase("Apellidos y nombres", font8));
                for (int j = 0; j < curso.getEvaluaciones().size(); j++) {
                    table.addCell(new Phrase("E" + (j + 1), font8));
                }
                table.addCell(new Phrase("P.F.", font8));
                table.addCell(new Phrase("ObservaciÃ³n", font8));
                table.getDefaultCell().setGrayFill(1);
                table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
                table.setHeaderRows(1);
                for (int i = 1; i <= 35; i++) {
                    if (index < curso.getEstudiantes().size()) {
                        Estudiante estudiante = (Estudiante) curso.getEstudiantes().get(index);
                        cell = new PdfPCell(new Phrase(Integer.toString(index + 1), font8));
                        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                        cell.setBorder(0);
                        table.addCell(cell);
                        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(new Phrase(Integer.toString(estudiante.getCodigo()), font8));
                        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
                        table.addCell(new Phrase(estudiante.getPaterno() + " " + estudiante.getMaterno() + " " + estudiante.getNombres(), font8));
                        int suma = 0;
                        int suma1 = 0;
                        for (int j = 0; j < estudiante.getEvaluaciones().size(); j++) {
                            Evaluacion evaluacion = (Evaluacion) estudiante.getEvaluaciones().get(j);
                            cell = new PdfPCell(new Phrase(Integer.toString(evaluacion.getNota() + evaluacion.getDps()), font8));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            cell.setBorder(0);
                            table.addCell(cell);
                            if (j == 3) {
                                suma1 = suma1 + evaluacion.getNota() + evaluacion.getDps();
                            } else {
                                suma = suma + evaluacion.getNota() + evaluacion.getDps();
                            }
                        }
                        double redondeado = 0;
                        if (suma1 != 0) {
                            redondeado = Math.round(((double) (((double) suma / 3) + suma1) / 2) * Math.pow(10, 0)) / Math.pow(10, 0);
                            cell = new PdfPCell(new Phrase(Integer.toString((int) redondeado), font8b));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            cell.setBorder(0);
                            table.addCell(cell);
                        } else {
                            redondeado = Math.round(((double) suma / 3) * Math.pow(10, 0)) / Math.pow(10, 0);
                            cell = new PdfPCell(new Phrase(Integer.toString((int) redondeado), font8b));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            cell.setBorder(0);
                            table.addCell(cell);
                        }
                        int nuevanota = (int) redondeado;
                        if (estudiante.getEvaluaciones().size() < 3) {
                            table.addCell(new Phrase("PENDIENTE", font8));
                        } else {
                            if (nuevanota < 36) {
                                table.addCell(new Phrase("REPROBADO", font8));
                                sumareprob++;
                            } else {
                                table.addCell(new Phrase("APROBADO", font8));
                                sumaaprob++;
                            }
                        }
                        index++;
                    }
                }
                float[] wid = table.getAbsoluteWidths();

                wid[0] = 40;
                wid[1] = 100;
                wid[2] = 400;
                wid[curso.getEvaluaciones().size() + (5 - 1)] = 100;
                table.setWidths(wid);

                table.setWidthPercentage(100);
                document.add(table);
                cell = new PdfPCell(new Phrase("", font8));
                cell.setColspan(curso.getEvaluaciones().size() + 5);
                cell.setBackgroundColor(new Color(200, 200, 200));
                cell.setBorder(0);
                table.addCell(cell);
                if (curso.getEvaluaciones().size() >= 3) {
                    PdfPTable tresumen = new PdfPTable(3);
                    tresumen.getDefaultCell().setBorder(0);
                    tresumen.setHorizontalAlignment(0);
                    tresumen.getDefaultCell().setGrayFill(0.9f);
                    tresumen.addCell(new Phrase("Aprobados", font8));
                    tresumen.addCell(new Phrase("Reprobados", font8));
                    tresumen.addCell(new Phrase("", font8));
                    tresumen.getDefaultCell().setGrayFill(1);
                    tresumen.addCell(new Phrase(Integer.toString(sumaaprob), font8));
                    tresumen.addCell(new Phrase(Integer.toString(sumareprob), font8));
                    tresumen.addCell(new Phrase("", font8));
                    tresumen.setWidthPercentage(30);
                    tresumen.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    document.add(tresumen);
                }
                document.add(new Phrase("\n \n \n \n ", font8));
                p = new Paragraph(new Phrase("__________________________________________", font8));
                p.setAlignment(Element.ALIGN_CENTER);
                document.add(p);
                p = new Paragraph(new Phrase("Prof. " + personal.getNombres() + " " + personal.getPaterno() + " " + personal.getMaterno(), font8));
                p.setAlignment(Element.ALIGN_CENTER);
                document.add(p);
                p = new Paragraph(new Phrase("DOCENTE", font8));
                p.setAlignment(Element.ALIGN_CENTER);
                document.add(p);
                p = new Paragraph(new Phrase(this.formatearFecha(new Date()), font8));
                p.setAlignment(Element.ALIGN_RIGHT);
                document.add(p);
                p = new Paragraph(new Phrase("SIGAA Sistema Integrado de GestiÃ³n AcadÃ©mico - Administrativo " + curso.getId_gestion(), font8));
                document.add(p);

//            p = new Paragraph(new Phrase("____________________________________________", font8));
//            p.setAlignment(Element.ALIGN_CENTER);
//            document.add(p);
//            p = new Paragraph(new Phrase("Prof. " + personal.getNombres() + " " + personal.getPaterno() + " " + personal.getMaterno(), font8));
//            p.setAlignment(Element.ALIGN_CENTER);
//            document.add(p);
//            p = new Paragraph(new Phrase("DOCENTE", font8));
//            p.setAlignment(Element.ALIGN_CENTER);
//            document.add(p);
//            document.add(Chunk.NEWLINE);
//
//            document.add(Chunk.NEWLINE);
//            p = new Paragraph(new Phrase(this.formatearFecha(new Date()), font8));
//            p.setAlignment(Element.ALIGN_RIGHT);
//            document.add(p);
//
//            PdfContentByte cb = writer.getDirectContent();
//            Barcode128 code128 = new Barcode128();
//            code128.setCode(curso.getId_curso() + "-" + materia.getId_materia());
//            Image image128 = code128.createImageWithBarcode(cb, null, null);
//
//            p = new Paragraph(new Phrase(new Chunk(image128, 0, 0)));
//            document.add(p);
//            p = new Paragraph(new Phrase("SIGAA Sistema Integrado de GestiÃ³n AcadÃ©mico - Administrativo 2010", font8));
//            document.add(p);
            }

        } catch (Exception de) {
            de.printStackTrace();
        }
        document.close();
        return archivo;
    }

    private String estudiantesCursoMateriaExcel(Curso curso, Materia materia, Personal personal, int periodo, int cant_dps) {
        String dirDestino = System.getenv("SIGAA_HOME1") + "/documentos/docentes/listas_excel/";
        String archivo = "Calificaciones_" + materia.getMateria() + "-" + curso.getId_curso() + "(" + this.formatFecha(new Date()) + ").xls";
        String dirPlantilla = "";

        if (curso.getId_curso().equals("K") || curso.getId_curso().equals("K1") || curso.getId_curso().equals("K12") || curso.getId_curso().equals("K21")|| curso.getId_curso().equals("P1")|| curso.getId_curso().equals("P12")) {
            cant_dps = 10;/*solo para que no busque la otra hoja de cualitativas*/

            dirPlantilla = System.getenv("SIGAA_HOME1") + "/documentos/plantillas_excel/plantilla_kinder.xls";
        } else {
            if (periodo == 3) {
                if (cant_dps == -1) {
                    dirPlantilla = System.getenv("SIGAA_HOME1") + "/documentos/plantillas_excel/plantilla_sin_evaluaciones.xls";/*funcional*/

                } else if (cant_dps == 0) {
                    dirPlantilla = System.getenv("SIGAA_HOME1") + "/documentos/plantillas_excel/plantilla_trimestral_sin_dps.xls";/*funcional*/

                } else if (cant_dps > 0) {
                    dirPlantilla = System.getenv("SIGAA_HOME1") + "/documentos/plantillas_excel/plantilla_trimestral_con_dps.xls";/*funcional*/

                }
            } else {
                if (cant_dps == -1) {
                    dirPlantilla = System.getenv("SIGAA_HOME1") + "/documentos/plantillas_excel/plantilla_sin_evaluaciones.xls";/*funcional*/

                } else if (cant_dps == 0) {
                    dirPlantilla = System.getenv("SIGAA_HOME1") + "/documentos/plantillas_excel/plantilla_bimestral.xls";/*funcional*/

                } else if (cant_dps > 0) {
                    dirPlantilla = System.getenv("SIGAA_HOME1") + "/documentos/plantillas_excel/plantilla_bimestral.xls";/*pendiente*/

                }
            }
        }
        System.out.println("direccion de la plantilla : " + dirPlantilla);
        try {
            Workbook plantilla = Workbook.getWorkbook(new File(dirPlantilla));
            WritableWorkbook workbook = Workbook.createWorkbook(new File(dirDestino + archivo), plantilla);
            WritableSheet sheet = workbook.getSheet("NOTAS_CUANTITATIVAS");
            WritableSheet sheet2 = workbook.getSheet("NOTAS_CUALITATIVAS");
            WritableFont arial_7 = new WritableFont(WritableFont.ARIAL, 9);
            WritableCellFormat arial7 = new WritableCellFormat(arial_7);
            WritableFont arial_8 = new WritableFont(WritableFont.ARIAL, 10);
            WritableCellFormat arial8 = new WritableCellFormat(arial_8);

            WritableCellFormat arial7green = new WritableCellFormat(arial_7);
//            arial7green.setBorder(Border.ALL, BorderLineStyle.DASHED, Colour.GRAY_80);
            arial7green.setBackground(Colour.LIGHT_GREEN);
            WritableCellFormat arial7blue = new WritableCellFormat(arial_7);
//            arial7blue.setBorder(Border.ALL, BorderLineStyle.DASHED, Colour.GRAY_80);
            arial7blue.setBackground(Colour.LIGHT_BLUE);
            WritableCellFormat arial7orange = new WritableCellFormat(arial_7);
            arial7orange.setBackground(Colour.LIGHT_ORANGE);
//            arial7orange.setBorder(Border.ALL, BorderLineStyle.DASHED, Colour.GRAY_80);
            arial7blue.setBackground(Colour.LIGHT_BLUE);
            WritableCellFormat arial7tur = new WritableCellFormat(arial_7);
            arial7tur.setBackground(Colour.LIME);
//            arial7tur.setBorder(Border.ALL, BorderLineStyle.DASHED, Colour.GRAY_80);

            String id_curso = curso.getId_curso();
            if (!id_curso.equals("K") && !id_curso.equals("S1") && !id_curso.equals("S2") && !id_curso.equals("S3") && !id_curso.equals("S4")) {
//                sheet2.addCell(new Label(4, 4, materia.getMateria(), arial8));
//                sheet2.addCell(new Label(4, 5, curso.getCurso() + " de " + curso.getCiclo(), arial8));
//                sheet2.addCell(new Label(4, 6, "Prof. " + personal.getNombres() + " " + personal.getPaterno() + " " + personal.getMaterno(), arial8));
//                sheet2.addCell(new Label(4, 7, this.formatearFecha(new Date()), arial8));
            }
            sheet.addCell(new Label(0, 1, "GESTION ACADEMICO " + curso.getId_gestion(), arial8));
            sheet.addCell(new Label(4, 4, materia.getMateria(), arial8));
            sheet.addCell(new Label(4, 5, curso.getCurso() + " de " + curso.getCiclo(), arial8));
            sheet.addCell(new Label(4, 6, "Prof. " + personal.getNombres() + " " + personal.getPaterno() + " " + personal.getMaterno(), arial8));
            sheet.addCell(new Label(4, 7, this.formatearFecha(new Date()), arial8));
            if ((periodo == 2 || periodo == 3) && cant_dps == 0) {
                sheet2.addCell(new Label(0, 1, "GESTION ACADEMICO " + curso.getId_gestion(), arial8));
                sheet2.addCell(new Label(4, 4, materia.getMateria(), arial8));
                sheet2.addCell(new Label(4, 5, curso.getCurso() + " de " + curso.getCiclo(), arial8));
                sheet2.addCell(new Label(4, 6, "Prof. " + personal.getNombres() + " " + personal.getPaterno() + " " + personal.getMaterno(), arial8));
                sheet2.addCell(new Label(4, 7, this.formatearFecha(new Date()), arial8));
            }

            Estudiante est;
            int i = 0;
            int ic = -4;
            for (i = 0; i < curso.getEstudiantes().size(); i++) {
                est = (Estudiante) curso.getEstudiantes().get(i);
                if (!id_curso.equals("K") && !id_curso.equals("K1") && !id_curso.equals("K12") && !id_curso.equals("K21") && !id_curso.equals("S1") && !id_curso.equals("S2") && !id_curso.equals("S3") && !id_curso.equals("S4")) {
//                    sheet2.addCell(new jxl.write.Number(1, i + 12 + y, (i + 1), arial7));
//                    sheet2.addCell(new Label(2, i + 12 + y, est.getId_estudiante(), arial7));
//                    sheet2.addCell(new jxl.write.Number(3, i + 12 + y, est.getCodigo(), arial7));
//                    sheet2.addCell(new Label(4, i + 12 + y, est.getPaterno() + " " + est.getMaterno() + " " + est.getNombres(), arial7));
                }
                sheet.addCell(new jxl.write.Number(1, i + 12, (i + 1), arial7));
                sheet.addCell(new Label(2, i + 12, est.getId_estudiante(), arial7));
                sheet.addCell(new jxl.write.Number(3, i + 12, est.getCodigo(), arial7));
                sheet.addCell(new Label(4, i + 12, est.getPaterno() + " " + est.getMaterno() + " " + est.getNombres(), arial7));
                if ((periodo == 2 || periodo == 3) && cant_dps == 0) {
                    ic = ic + 4;
                    sheet2.addCell(new jxl.write.Number(1, ic + 12, (i + 1), arial7));
                    sheet2.addCell(new Label(2, ic + 12, est.getId_estudiante(), arial7));
                    sheet2.addCell(new jxl.write.Number(3, ic + 12, est.getCodigo(), arial7));
                    sheet2.addCell(new Label(4, ic + 12, est.getPaterno() + " " + est.getMaterno() + " " + est.getNombres(), arial7));
                }
                int col = 0;
                if (periodo == 3 && cant_dps != -1) {
                    for (int j = 0; j < est.getEvaluaciones().size(); j++) {
                        Evaluacion eva = (Evaluacion) est.getEvaluaciones().get(j);
                        if (cant_dps == 0) {
                            if (j == 0) {
                                sheet.addCell(new jxl.write.Number(5, i + 12, eva.getNota(), arial7));
                            } else if (j == 1) {
                                sheet.addCell(new jxl.write.Number(6, i + 12, eva.getNota(), arial7));
                            } else if (j == 2) {
                                sheet.addCell(new jxl.write.Number(7, i + 12, eva.getNota(), arial7));
                            } else if (j == 3) {
                                sheet.addCell(new jxl.write.Number(9, i + 12, eva.getNota(), arial7));
                            }
                            /*calificacion cualitativa*/
                            if (j == 0) {
                                sheet2.addCell(new Label(5, ic + 12, "1er Trimestre", arial7green));
                                sheet2.addCell(new Label(6, ic + 12, eva.getCualitativa(), arial7));
                            } else if (j == 1) {
                                sheet2.addCell(new Label(5, ic + 13, "2do Trimestre", arial7blue));
                                sheet2.addCell(new Label(6, ic + 13, eva.getCualitativa(), arial7));
                            } else if (j == 2) {
                                sheet2.addCell(new Label(5, ic + 14, "3er Trimestre", arial7orange));
                                sheet2.addCell(new Label(6, ic + 14, eva.getCualitativa(), arial7));
                            }
                        } else if (cant_dps > 0) {
                            String erdo = (j + 1) + "er. ";
                            if (j == 0) {
                                col = 5;
                            } else if (j == 1) {
                                erdo = (j + 1) + "do. ";
                                col = 8;
                            } else if (j == 2) {
                                col = 11;
                            } else if (j == 3) {
                                col = 15;
                            }
                            if (curso.getId_curso().equals("K1") || curso.getId_curso().equals("K") || curso.getId_curso().equals("K12") || curso.getId_curso().equals("K21")) {
                                sheet.addCell(new Label(j + 5, i + 12, eva.getCualitativa(), arial7));
                            } else {
                                sheet.addCell(new jxl.write.Number(col, i + 12, eva.getNota(), arial7));
                                sheet.addCell(new jxl.write.Number(col + 1, i + 12, eva.getDps(), arial7));
                            }
                            if (!id_curso.equals("K") && !id_curso.equals("S1") && !id_curso.equals("S2") && !id_curso.equals("S3") && !id_curso.equals("S4")) {
//                        sheet2.addCell(new Label(5, i + 12 + j + y, erdo + "TRIMESTRE ", arial7));
//                        sheet2.addCell(new Label(6, i + 12 + j + y, eva.getCualitativa(), arial7));
                            }
                        }
                    }
                } else if ((periodo == 2 || periodo == 3) && cant_dps != -1) {
                    for (int j = 0; j < est.getEvaluaciones().size(); j++) {
                        /*calificacion cuantitativa*/
                        Evaluacion eva = (Evaluacion) est.getEvaluaciones().get(j);
                        if (id_curso.equals("K1") || id_curso.equals("K") || id_curso.equals("K12") || id_curso.equals("K21")) {
                            if (j == 0) {
                                sheet.addCell(new Label(5, i + 12, eva.getCualitativa(), arial7));
                            } else if (j == 1) {
                                sheet.addCell(new Label(6, i + 12, eva.getCualitativa(), arial7));
                            } else if (j == 2) {
                                sheet.addCell(new Label(7, i + 12, eva.getCualitativa(), arial7));
                            } else if (j == 3) {
                                sheet.addCell(new Label(8, i + 12, eva.getCualitativa(), arial7));
                            } else if (j == 4) {
                                sheet.addCell(new Label(10, i + 12, eva.getCualitativa(), arial7));
                            }
                        } else {
                            if (j == 0) {
                                sheet.addCell(new jxl.write.Number(5, i + 12, eva.getNota(), arial7));
                            } else if (j == 1) {
                                sheet.addCell(new jxl.write.Number(6, i + 12, eva.getNota(), arial7));
                            } else if (j == 2) {
                                sheet.addCell(new jxl.write.Number(7, i + 12, eva.getNota(), arial7));
                            } else if (j == 3) {
                                sheet.addCell(new jxl.write.Number(8, i + 12, eva.getNota(), arial7));
                            } else if (j == 4) {
                                sheet.addCell(new jxl.write.Number(10, i + 12, eva.getNota(), arial7));
                            }
                        }
                        /*calificacion cualitativa*/
                        if (j == 0) {
                            sheet2.addCell(new Label(5, ic + 12, "1er Bimestre", arial7green));
                            sheet2.addCell(new Label(6, ic + 12, eva.getCualitativa(), arial7));
                        } else if (j == 1) {
                            sheet2.addCell(new Label(5, ic + 13, "2do Bimestre", arial7blue));
                            sheet2.addCell(new Label(6, ic + 13, eva.getCualitativa(), arial7));
                        } else if (j == 2) {
                            sheet2.addCell(new Label(5, ic + 14, "3er Bimestre", arial7orange));
                            sheet2.addCell(new Label(6, ic + 14, eva.getCualitativa(), arial7));
                        } else if (j == 3) {
                            sheet2.addCell(new Label(5, ic + 15, "4to Bimestre", arial7tur));
                            sheet2.addCell(new Label(6, ic + 15, eva.getCualitativa(), arial7));
                        }
                    }
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
