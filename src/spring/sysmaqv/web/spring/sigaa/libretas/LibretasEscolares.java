/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.sysmaqv.web.spring.sigaa.libretas;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import spring.sysmaqv.domain.Inscripcion;
import spring.sysmaqv.domain.Materia;
import spring.sysmaqv.domain.Personal;
import spring.sysmaqv.domain.logic.SigaaInterface;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.util.WebUtils;
import spring.sysmaqv.domain.Falta;

/**
 *
 * @author Marco Antonio
 */
public class LibretasEscolares implements Controller {

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
            String id_curso = request.getParameter("id_curso");
            String gecxel = request.getParameter("gecxel");
            if (id_gestion != null) {
                retorno.put("id_gestion", id_gestion);
                if (id_curso != null) {
                    Inscripcion inscripcion = new Inscripcion();
                    inscripcion.setId_gestion(Integer.parseInt(id_gestion));
                    inscripcion.setId_curso(id_curso);
                    List estudiantes = this.sigaa.getEstudiantesByIdCursoAndIdGestion(inscripcion);
                    retorno.put("estudiantes", estudiantes);
                    retorno.put("id_gestion", id_gestion);
//                    Curso cur = (Curso) this.sigaa.getBuscarCurso(id_curso).get(0);

                    Curso curso = new Curso();
                    curso.setId_curso(id_curso);
                    curso.setId_gestion(Integer.parseInt(id_gestion));
                    curso = this.sigaa.getCursoByIdCursoAndIdGestion(curso);
                    retorno.put("curso", curso);
                    if (gecxel != null && curso.getId_curso().equals("K")) {
                        String excel = this.setGenerarBoletinesExcel(curso, id_gestion);
                        response.sendRedirect("documentos/libretas/report/KINDER/" + excel);
                    }
                    if (gecxel != null && curso.getId_curso().equals("P1")) {
                        String excel = this.setGenerarLibretasPrimaria1(curso, id_gestion);
                        response.sendRedirect("documentos/libretas/report/PRIMARIA1/" + excel);
                    }
                    if (gecxel != null && curso.getId_curso().equals("P2")) {
                        String excel = this.setGenerarLibretasPrimaria2(curso, id_gestion);
                        response.sendRedirect("documentos/libretas/report/PRIMARIA2/" + excel);
                    }
                    if (gecxel != null && curso.getId_curso().equals("P3")) {
                        String excel = this.setGenerarLibretasPrimaria3(curso, id_gestion);
                        response.sendRedirect("documentos/libretas/report/PRIMARIA3/" + excel);
                    }
                    if (gecxel != null && curso.getId_curso().equals("P4")) {
                        String excel = this.setGenerarLibretasPrimaria4(curso, id_gestion);
                        response.sendRedirect("documentos/libretas/report/PRIMARIA4/" + excel);
                    }
                    if (gecxel != null && curso.getId_curso().equals("P5")) {
                        String excel = this.setGenerarLibretasPrimaria5(curso, id_gestion);
                        response.sendRedirect("documentos/libretas/report/PRIMARIA5/" + excel);
                    }
                    if (gecxel != null && curso.getId_curso().equals("P6")) {
                        String excel = this.setGenerarLibretasPrimaria6(curso, id_gestion);
                        response.sendRedirect("documentos/libretas/report/PRIMARIA6/" + excel);
                    }
                    if (gecxel != null && curso.getId_curso().equals("P7")) {
                        String excel = this.setGenerarLibretasPrimaria7(curso, id_gestion);
                        response.sendRedirect("documentos/libretas/report/PRIMARIA7/" + excel);
                    }
                    if (gecxel != null && curso.getId_curso().equals("P8")) {
                        String excel = this.setGenerarLibretasPrimaria8(curso, id_gestion);
                        response.sendRedirect("documentos/libretas/report/PRIMARIA8/" + excel);
                    }
                    if (gecxel != null && curso.getId_curso().equals("S1")) {
                        String excel = this.setGenerarLibretasSecundaria1(curso, id_gestion);
                        response.sendRedirect("documentos/libretas/report/SECUNDARIA1/" + excel);
                    }
                    if (gecxel != null && curso.getId_curso().equals("S2")) {
                        String excel = this.setGenerarLibretasSecundaria2(curso, id_gestion);
                        response.sendRedirect("documentos/libretas/report/SECUNDARIA2/" + excel);
                    }
                    if (gecxel != null && curso.getId_curso().equals("S4")) {
                        String excel = this.setGenerarLibretasSecundaria4(curso, id_gestion);
                        response.sendRedirect("documentos/libretas/report/SECUNDARIA4/" + excel);
                    }
//                    if (gecxel != null) {
//                        if (id_curso.equals("K")) {
//                            curso = new Curso();
//                            curso.setId_curso(id_curso);
//                            curso.setId_gestion(Integer.parseInt(id_gestion));
//                            curso = this.sigaa.getCursoByIdCursoLibreta(curso);
//                            int Vk[] = new int[350];
//                            Vk[0] = 7;
//                            int contii = 0;
//                            int kin[] = {1, 1, 1, 1, 13};
//                            for (int ii = 1; ii < 340; ii++) {
//                                Vk[ii] = Vk[ii - 1] + kin[contii];
//                                if (contii == 4) {
//                                    contii = 0;
//                                } else {
//                                    contii++;
//                                }
//                            }
//                            Date FechaActual = new Date();
//                            SimpleDateFormat Formator = new SimpleDateFormat("dd-MM-yy");
//                            String CadenaFechar = Formator.format(FechaActual);
//                            String dirPlantilla = System.getenv("SIGAA_HOME") + "/documentos/libretas/plantillas/KINDER/2010_80730484_INI_M_2_A.xls";
//                            Workbook plantilla = Workbook.getWorkbook(new File(dirPlantilla));
//                            String dirDestino = System.getenv("SIGAA_HOME") + "/documentos/libretas/report/KINDER/";
//                            String nombreArch = "2010_80730484_INI_M_2_A____" + CadenaFechar + ".xls";
//                            WritableWorkbook workbook = Workbook.createWorkbook(new File(dirDestino + nombreArch), plantilla); // Excel 2003
//                            WritableSheet sheet = workbook.getSheet("FICHA1");
//                            WritableFont arial_10 = new WritableFont(WritableFont.ARIAL, 10);
//                            WritableCellFormat arial10 = new WritableCellFormat(arial_10);
//                            int fich = 0;
//                            int cont = 21;
//                            for (int i = 0; i < curso.getEstudiantes().size(); i++) {
//                                Estudiante estudiante = (Estudiante) curso.getEstudiantes().get(i);
//                                if (i == 0 || i == 20 || i == 40 || i == 60) {
//                                    fich = fich + 1;
//                                    String ficha = "CEN" + Integer.toString(fich);
//                                    sheet = workbook.getSheet(ficha);
//                                    cont = 21;
//                                }
//                                Conducta conducta = (Conducta) estudiante.getConductas().get(1);
//                                sheet.addCell(new Label(34, cont++, conducta.getDiagnostico(), arial10));
//                            }
//                            fich = 0;
//                            cont = 0;
//                            contii = 0;
//                            sheet = workbook.getSheet("FICHA1");
//
//                            for (int i = 0; i < curso.getEstudiantes().size(); i++) {
//                                cont++;
//                                if (cont == 1 || i == 20 || i == 40 || i == 60 || i == 80 || i == 100) {
//                                    fich = fich + 1;
//                                    String ficha = "FICHA" + Integer.toString(fich);
//                                    sheet = workbook.getSheet(ficha);
//                                    contii = 0;
//                                }
//                                Estudiante estudiante = (Estudiante) curso.getEstudiantes().get(i);
//
//                                for (int j = 0; j < estudiante.getMaterias().size(); j++) {
//                                    Materia materia = (Materia) curso.getMaterias().get(j);
//                                    materia.getCursomateria().setId_curso_materia(curso.getId_curso() + "-" + materia.getId_materia()+"-"+id_gestion);
//                                    materia.setId_estudiante(estudiante.getId_estudiante());
//                                    System.out.println("id_mat = "+curso.getId_curso() + "-" + materia.getId_materia());
//                                    System.out.println("est = "+estudiante.getId_estudiante());
//                                    List eval = this.sigaa.getEvaluacionesEstudianteCursoMateria(materia);
//                                    System.out.println("tamaño = "+eval.size());
//                                    for (int k = 0; k < eval.size(); k++) {
//                                        Evaluacion evaluacion = (Evaluacion) eval.get(k);
//                                        if (k == 0) {
//                                            System.out.println("col:" + 2 + " , fila:" + Vk[contii] + "  , nota:" + evaluacion.getCualitativa());
//                                            sheet.addCell(new Label(2, Vk[contii], evaluacion.getCualitativa(), arial10));
//                                        } else if (k == 1) {
//                                            System.out.println("col:" + 6 + " , fila:" + Vk[contii] + "  , nota:" + evaluacion.getCualitativa());
//                                            sheet.addCell(new Label(6, Vk[contii], evaluacion.getCualitativa(), arial10));
//                                        } else if (k == 2) {
//                                            System.out.println("col:" + 10 + " , fila:" + Vk[contii] + "  , nota:" + evaluacion.getCualitativa());
//                                            sheet.addCell(new Label(10, Vk[contii], evaluacion.getCualitativa(), arial10));
//                                        }
//                                    }
//                                    contii++;
//                                }
//                                Conducta conducta1 = (Conducta) estudiante.getConductas().get(0);
//                                sheet.addCell(new Label(2, (Vk[contii] - 12), conducta1.getDiagnostico(), arial10));
//                            }
//                            workbook.write();
//                            workbook.close();
//                        }
//                        if (id_curso.equals("P1") || id_curso.equals("P2") || id_curso.equals("P3") || id_curso.equals("P4") || id_curso.equals("P5") || id_curso.equals("P6")) {
////                            Curso curso = new Curso();
//                            curso.setId_curso(id_curso);
//                            curso.setId_gestion(Integer.parseInt(id_gestion));
//                            curso = this.sigaa.getCursoByIdCursoLibreta(curso);
//                            int Vk[] = new int[350];
//                            Vk[0] = 7;
//                            int contii = 0;
//                            int kin[] = {2, 1, 1, 1, 2, 1, 1, 16};
//                            for (int ii = 1; ii < 340; ii++) {
//                                Vk[ii] = Vk[ii - 1] + kin[contii];
//                                if (contii == 7) {
//                                    contii = 0;
//                                } else {
//                                    contii++;
//                                }
//                            }
//                            Date FechaActual = new Date();
//                            SimpleDateFormat Formator = new SimpleDateFormat("dd-MM-yy"); // formato de la fecha y hora
//                            String CadenaFechar = Formator.format(FechaActual);
//
//                            String dirPlantilla = System.getenv("SIGAA_HOME") + "/documentos/libretas/plantillas/PRIMARIA1/2010_80730484_PRI_M_1_A.xls";
//                            Workbook plantilla = Workbook.getWorkbook(new File(dirPlantilla));
//                            String dirDestino = System.getenv("SIGAA_HOME") + "/documentos/libretas/report/PRIMARIA1/";
//                            String nombreArch = "2009_80730484_PRI_M_1_A____" + CadenaFechar + ".xls";
//                            if (id_curso.equals("P2")) {
//                                dirPlantilla = System.getenv("SIGAA_HOME") + "/documentos/libretas/plantillas/PRIMARIA2/2010_80730484_PRI_M_2_A.xls";
//                                plantilla = Workbook.getWorkbook(new File(dirPlantilla));
//                                dirDestino = System.getenv("SIGAA_HOME") + "/documentos/libretas/report/PRIMARIA2/";
//                                nombreArch = "2009_80730484_PRI_M_2_A____" + CadenaFechar + ".xls";
//                            } else if (id_curso.equals("P3")) {
//                                dirPlantilla = System.getenv("SIGAA_HOME") + "/documentos/libretas/plantillas/PRIMARIA3/2010_80730484_PRI_M_3_A.xls";
//                                plantilla = Workbook.getWorkbook(new File(dirPlantilla));
//                                dirDestino = System.getenv("SIGAA_HOME") + "/documentos/libretas/report/PRIMARIA3/";
//                                nombreArch = "2009_80730484_PRI_M_3_A____" + CadenaFechar + ".xls";
//                            } else if (id_curso.equals("P4")) {
//                                dirPlantilla = System.getenv("SIGAA_HOME") + "/documentos/libretas/plantillas/PRIMARIA4/2010_80730484_PRI_M_4_A.xls";
//                                plantilla = Workbook.getWorkbook(new File(dirPlantilla));
//                                dirDestino = System.getenv("SIGAA_HOME") + "/documentos/libretas/report/PRIMARIA4/";
//                                nombreArch = "2009_80730484_PRI_M_4_A____" + CadenaFechar + ".xls";
//                            } else if (id_curso.equals("P5")) {
//                                dirPlantilla = System.getenv("SIGAA_HOME") + "/documentos/libretas/plantillas/PRIMARIA5/2010_80730484_PRI_M_5_A.xls";
//                                plantilla = Workbook.getWorkbook(new File(dirPlantilla));
//                                dirDestino = System.getenv("SIGAA_HOME") + "/documentos/libretas/report/PRIMARIA5/";
//                                nombreArch = "2009_80730484_PRI_M_5_A____" + CadenaFechar + ".xls";
//                            } else if (id_curso.equals("P6")) {
//                                dirPlantilla = System.getenv("SIGAA_HOME") + "/documentos/libretas/plantillas/PRIMARIA6/2010_80730484_PRI_M_6_A.xls";
//                                plantilla = Workbook.getWorkbook(new File(dirPlantilla));
//                                dirDestino = System.getenv("SIGAA_HOME") + "/documentos/libretas/report/PRIMARIA6/";
//                                nombreArch = "2009_80730484_PRI_M_6_A____" + CadenaFechar + ".xls";
//                            }
//                            WritableWorkbook workbook = Workbook.createWorkbook(new File(dirDestino + nombreArch), plantilla); // Excel 2003
//                            WritableSheet sheet = workbook.getSheet("Ficha1");
//                            WritableFont arial_10 = new WritableFont(WritableFont.ARIAL, 10);
//                            WritableCellFormat arial10 = new WritableCellFormat(arial_10);
//                            int fich = 0;
//                            int cont = 0;
//                            contii = 0;
//                            for (int i = 0; i < curso.getEstudiantes().size(); i++) {
//                                cont++;
//                                if (cont == 1 || cont == 26 || cont == 51 || cont == 76 || cont == 101 || cont == 126) {
//                                    fich = fich + 1;
//                                    String ficha = "Ficha" + Integer.toString(fich);
//                                    sheet = workbook.getSheet(ficha);
//                                    contii = 0;
//                                }
//                                Estudiante estudiante = (Estudiante) curso.getEstudiantes().get(i);
//                                if (i == 37 && id_curso.equals("P2")) {
//                                    contii = contii + 16;
//                                }
//                                if (i == 13 && id_curso.equals("P5")) {
//                                    contii = contii + 8;
//                                    cont++;
//                                }
//                                for (int j = 0; j < estudiante.getMaterias().size(); j++) {
//
//                                    Materia materia = (Materia) curso.getMaterias().get(j);
//                                    materia.getCursomateria().setId_curso_materia(curso.getId_curso() + "-" + materia.getId_materia());
//                                    materia.setId_estudiante(estudiante.getId_estudiante());
//                                    List eval = this.sigaa.getEvaluacionesEstudianteCursoMateria(materia);
//
//                                    if ((id_curso.equals("P4") || id_curso.equals("P5") || id_curso.equals("P6")) && (materia.getId_materia().equals("AYM") || materia.getId_materia().equals("ING"))) {
//                                        contii--;
//                                    } else {
//                                        for (int k = 0; k < eval.size(); k++) {
//                                            Evaluacion eva = (Evaluacion) eval.get(k);
//                                            if (k == 0) {
//                                                sheet.addCell(new jxl.write.Number(2, Vk[contii], eva.getNota() + eva.getDps()));
//                                                sheet.addCell(new Label(3, Vk[contii], eva.getCualitativa_p(), arial10));
//                                            } else if (k == 1) {
//                                                sheet.addCell(new jxl.write.Number(7, Vk[contii], eva.getNota() + eva.getDps()));
//                                                sheet.addCell(new Label(8, Vk[contii], eva.getCualitativa_p(), arial10));
//                                            } else if (k == 2) {
//                                                sheet.addCell(new jxl.write.Number(12, Vk[contii], eva.getNota() + eva.getDps()));
//                                                sheet.addCell(new Label(13, Vk[contii], eva.getCualitativa_p(), arial10));
//                                            } else if (k == 3) {
//                                                if (eva.getNota() > 0) {
//                                                    sheet.addCell(new jxl.write.Number(18, Vk[contii], eva.getNota()));
//                                                }
//                                            }
//                                        }
//                                    }
//                                    contii++;
//                                }
//                            }
//                            workbook.write();
//                            workbook.close();
//                        }
//                        if (id_curso.equals("P7") || id_curso.equals("P8")) {
////                            Curso curso = new Curso();
//                            curso.setId_curso(id_curso);
//                            curso.setId_gestion(Integer.parseInt(id_gestion));
//                            curso = this.sigaa.getCursoByIdCursoLibreta(curso);
//                            int Vk[] = new int[350];
//                            Vk[0] = 7;
//                            int contii = 0;
//                            int kin[] = {2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 13};
//                            for (int ii = 1; ii < 340; ii++) {
//                                Vk[ii] = Vk[ii - 1] + kin[contii];
//                                if (contii == 10) {
//                                    contii = 0;
//                                } else {
//                                    contii++;
//                                }
//                            }
//                            Date FechaActual = new Date();
//                            SimpleDateFormat Formator = new SimpleDateFormat("dd-MM-yy"); // formato de la fecha y hora
//                            String CadenaFechar = Formator.format(FechaActual);
//                            String dirPlantilla = System.getenv("SIGAA_HOME") + "/documentos/libretas/plantillas/PRIMARIA7/2009_80730484_PRI_M_7_A.xls";
//                            Workbook plantilla = Workbook.getWorkbook(new File(dirPlantilla));
//                            String dirDestino = System.getenv("SIGAA_HOME") + "/documentos/libretas/report/PRIMARIA7/";
//                            String nombreArch = "2009_80730484_PRI_M_7_A____" + CadenaFechar + ".xls";
//                            if (id_curso.equals("P8")) {
//                                dirPlantilla = System.getenv("SIGAA_HOME") + "/documentos/libretas/plantillas/PRIMARIA8/2009_80730484_PRI_M_8_A.xls";
//                                plantilla = Workbook.getWorkbook(new File(dirPlantilla));
//                                dirDestino = System.getenv("SIGAA_HOME") + "/documentos/libretas/report/PRIMARIA8/";
//                                nombreArch = "2009_80730484_PRI_M_8_A____" + CadenaFechar + ".xls";
//                            }
//                            WritableWorkbook workbook = Workbook.createWorkbook(new File(dirDestino + nombreArch), plantilla); // Excel 2003
//                            WritableSheet sheet = workbook.getSheet("Ficha1");
//                            WritableFont arial_10 = new WritableFont(WritableFont.ARIAL, 10);
//                            WritableCellFormat arial10 = new WritableCellFormat(arial_10);
//                            int fich = 0;
//                            int cont = 0;
//                            contii = 0;
//                            for (int i = 0; i < curso.getEstudiantes().size(); i++) {
//                                cont++;
//                                if (cont == 1 || cont == 26 || cont == 51 || cont == 76 || cont == 101 || cont == 126) {
//                                    fich = fich + 1;
//                                    String ficha = "Ficha" + Integer.toString(fich);
//                                    sheet = workbook.getSheet(ficha);
//                                    contii = 0;
//                                }
//                                Estudiante estudiante = (Estudiante) curso.getEstudiantes().get(i);
//                                for (int j = 0; j < estudiante.getMaterias().size(); j++) {
//                                    Materia materia = (Materia) curso.getMaterias().get(j);
//                                    materia.getCursomateria().setId_curso_materia(curso.getId_curso() + "-" + materia.getId_materia());
//                                    materia.setId_estudiante(estudiante.getId_estudiante());
//                                    List eval = this.sigaa.getEvaluacionesEstudianteCursoMateria(materia);
//                                    for (int k = 0; k < eval.size(); k++) {
//                                        Evaluacion eva = (Evaluacion) eval.get(k);
//                                        if (k == 0) {
//                                            sheet.addCell(new jxl.write.Number(2, Vk[contii], eva.getNota() + eva.getDps()));
//                                            sheet.addCell(new Label(3, Vk[contii], eva.getCualitativa_p(), arial10));
//                                        } else if (k == 1) {
//                                            sheet.addCell(new jxl.write.Number(7, Vk[contii], eva.getNota() + eva.getDps()));
//                                            sheet.addCell(new Label(8, Vk[contii], eva.getCualitativa_p(), arial10));
//                                        } else if (k == 2) {
//                                            sheet.addCell(new jxl.write.Number(12, Vk[contii], eva.getNota() + eva.getDps()));
//                                            sheet.addCell(new Label(13, Vk[contii], eva.getCualitativa_p(), arial10));
//                                        } else if (k == 3) {
//                                            if (eva.getNota() > 0) {
//                                                sheet.addCell(new jxl.write.Number(18, Vk[contii], eva.getNota()));
//                                            }
//                                        }
//                                    }
//                                    contii++;
//                                }
//                            }
//                            workbook.write();
//                            workbook.close();
//                        }
//
//                        if (id_curso.equals("S1")) {
////                            Curso curso = new Curso();
//                            curso.setId_curso(id_curso);
//                            curso.setId_gestion(Integer.parseInt(id_gestion));
//                            curso = this.sigaa.getCursoByIdCursoLibreta(curso);
//                            int Vk[] = new int[350];
//                            Vk[0] = 7;
//                            int contii = 0;
//                            int kin[] = {1, 2, 1, 3, 5, 1, 1, 1, 1, 1, 22};
//                            for (int ii = 1; ii < 340; ii++) {
//                                Vk[ii] = Vk[ii - 1] + kin[contii];
//                                if (contii == 10) {
//                                    contii = 0;
//                                } else {
//                                    contii++;
//                                }
//                            }
//                            Date FechaActual = new Date();
//                            SimpleDateFormat Formator = new SimpleDateFormat("dd-MM-yy"); // formato de la fecha y hora
//                            String CadenaFechar = Formator.format(FechaActual);
//                            String dirPlantilla = System.getenv("SIGAA_HOME") + "/documentos/libretas/plantillas/SECUNDARIA1/2009_80730484_SEC_M_1_A.xls";
//                            Workbook plantilla = Workbook.getWorkbook(new File(dirPlantilla));
//                            String dirDestino = System.getenv("SIGAA_HOME") + "/documentos/libretas/report/SECUNDARIA1/";
//                            String nombreArch = "2009_80730484_SEC_M_1_A____" + CadenaFechar + ".xls";
//                            WritableWorkbook workbook = Workbook.createWorkbook(new File(dirDestino + nombreArch), plantilla); // Excel 2003
//                            WritableSheet sheet = workbook.getSheet("ficha1");
//                            WritableFont arial_10 = new WritableFont(WritableFont.ARIAL, 10);
//                            WritableCellFormat arial10 = new WritableCellFormat(arial_10);
//                            int fich = 0;
//                            int cont = 0;
//                            contii = 0;
//                            for (int i = 0; i < curso.getEstudiantes().size(); i++) {
//                                cont++;
//                                if (cont == 1 || cont == 26 || cont == 51 || cont == 76 || cont == 101 || cont == 126) {
//                                    fich = fich + 1;
//                                    String ficha = "ficha" + Integer.toString(fich);
//                                    sheet = workbook.getSheet(ficha);
//                                    contii = 0;
//                                }
//                                Estudiante estudiante = (Estudiante) curso.getEstudiantes().get(i);
//                                int suma1 = 0;
//                                int suma2 = 0;
//                                int suma3 = 0;
//                                int dps1 = 0;
//                                int dps2 = 0;
//                                int dps3 = 0;
//                                for (int j = 0; j < estudiante.getMaterias().size(); j++) {
//                                    Materia materia = (Materia) curso.getMaterias().get(j);
//                                    materia.getCursomateria().setId_curso_materia(curso.getId_curso() + "-" + materia.getId_materia());
//                                    materia.setId_estudiante(estudiante.getId_estudiante());
//                                    List eval = this.sigaa.getEvaluacionesEstudianteCursoMateria(materia);
//                                    if (j == 3 || j == 4 || j == 5) {
//                                        Evaluacion eva = (Evaluacion) eval.get(0);
//                                        suma1 = suma1 + eva.getNota();
//                                        if (dps1 < eva.getDps()) {
//                                            dps1 = eva.getDps();
//                                        }
//                                        eva = (Evaluacion) eval.get(1);
//                                        suma2 = suma2 + eva.getNota();
//                                        if (dps2 < eva.getDps()) {
//                                            dps2 = eva.getDps();
//                                        }
//                                        eva = (Evaluacion) eval.get(2);
//                                        suma3 = suma3 + eva.getNota();
//                                        if (dps3 < eva.getDps()) {
//                                            dps3 = eva.getDps();
//                                        }
//                                        if (j == 5) {
//                                            int red1 = (int) (Math.round(((double) suma1 / 3) * Math.pow(10, 0)) / Math.pow(10, 0));
//                                            int red2 = (int) (Math.round(((double) suma2 / 3) * Math.pow(10, 0)) / Math.pow(10, 0));
//                                            int red3 = (int) (Math.round(((double) suma3 / 3) * Math.pow(10, 0)) / Math.pow(10, 0));
//                                            sheet.addCell(new jxl.write.Number(2, Vk[contii], red1));
//                                            sheet.addCell(new jxl.write.Number(3, Vk[contii], dps1));
//                                            sheet.addCell(new jxl.write.Number(5, Vk[contii], red2));
//                                            sheet.addCell(new jxl.write.Number(6, Vk[contii], dps2));
//                                            sheet.addCell(new jxl.write.Number(8, Vk[contii], red3));
//                                            sheet.addCell(new jxl.write.Number(9, Vk[contii], dps3));
//                                        } else {
//                                            contii--;
//                                        }
//                                    } else {
//                                        for (int k = 0; k < eval.size(); k++) {
//                                            Evaluacion eva = (Evaluacion) eval.get(k);
//                                            if (k == 0) {
//                                                sheet.addCell(new jxl.write.Number(2, Vk[contii], eva.getNota()));
//                                                sheet.addCell(new jxl.write.Number(3, Vk[contii], eva.getDps()));
//                                            } else if (k == 1) {
//                                                sheet.addCell(new jxl.write.Number(5, Vk[contii], eva.getNota()));
//                                                sheet.addCell(new jxl.write.Number(6, Vk[contii], eva.getDps()));
//                                            } else if (k == 2) {
//                                                sheet.addCell(new jxl.write.Number(8, Vk[contii], eva.getNota()));
//                                                sheet.addCell(new jxl.write.Number(9, Vk[contii], eva.getDps()));
//                                            } else if (k == 3) {
//                                                if (eva.getNota() > 0) {
//                                                    sheet.addCell(new jxl.write.Number(12, Vk[contii], eva.getNota()));
//                                                }
//                                            }
//                                        }
//                                    }
//                                    contii++;
//                                }
//                            }
//                            workbook.write();
//                            workbook.close();
//                        }
//
//                        if (id_curso.equals("S2")) {
////                            Curso curso = new Curso();
//                            curso.setId_curso(id_curso);
//                            curso.setId_gestion(Integer.parseInt(id_gestion));
//                            curso = this.sigaa.getCursoByIdCursoLibreta(curso);
//                            int Vk[] = new int[350];
//                            Vk[0] = 7;
//                            int contii = 0;
//                            int kin[] = {1, 1, 1, 1, 3, 5, 1, 1, 1, 1, 23};
//                            for (int ii = 1; ii < 340; ii++) {
//                                Vk[ii] = Vk[ii - 1] + kin[contii];
//                                if (contii == 10) {
//                                    contii = 0;
//                                } else {
//                                    contii++;
//                                }
//                            }
//                            Date FechaActual = new Date();
//                            SimpleDateFormat Formator = new SimpleDateFormat("dd-MM-yy"); // formato de la fecha y hora
//                            String CadenaFechar = Formator.format(FechaActual);
//                            String dirPlantilla = System.getenv("SIGAA_HOME") + "/documentos/libretas/plantillas/SECUNDARIA2/2009_80730484_SEC_M_2_A.xls";
//                            Workbook plantilla = Workbook.getWorkbook(new File(dirPlantilla));
//                            String dirDestino = System.getenv("SIGAA_HOME") + "/documentos/libretas/report/SECUNDARIA2/";
//                            String nombreArch = "2009_80730484_SEC_M_2_A____" + CadenaFechar + ".xls";
//                            WritableWorkbook workbook = Workbook.createWorkbook(new File(dirDestino + nombreArch), plantilla); // Excel 2003
//                            WritableSheet sheet = workbook.getSheet("ficha1");
//                            WritableFont arial_10 = new WritableFont(WritableFont.ARIAL, 10);
//                            WritableCellFormat arial10 = new WritableCellFormat(arial_10);
//                            int fich = 0;
//                            int cont = 0;
//                            contii = 0;
//                            for (int i = 0; i < curso.getEstudiantes().size(); i++) {
//                                cont++;
//                                if (cont == 1 || cont == 26 || cont == 51 || cont == 76 || cont == 101 || cont == 126) {
//                                    fich = fich + 1;
//                                    String ficha = "ficha" + Integer.toString(fich);
//                                    sheet = workbook.getSheet(ficha);
//                                    contii = 0;
//                                }
//                                Estudiante estudiante = (Estudiante) curso.getEstudiantes().get(i);
//                                int suma1 = 0;
//                                int suma2 = 0;
//                                int suma3 = 0;
//                                int dps1 = 0;
//                                int dps2 = 0;
//                                int dps3 = 0;
//                                for (int j = 0; j < estudiante.getMaterias().size(); j++) {
//                                    Materia materia = (Materia) curso.getMaterias().get(j);
//                                    materia.getCursomateria().setId_curso_materia(curso.getId_curso() + "-" + materia.getId_materia());
//                                    materia.setId_estudiante(estudiante.getId_estudiante());
//                                    List eval = this.sigaa.getEvaluacionesEstudianteCursoMateria(materia);
//                                    if (j == 4 || j == 5 || j == 6) {
//                                        Evaluacion eva = (Evaluacion) eval.get(0);
//                                        suma1 = suma1 + eva.getNota();
//                                        if (dps1 < eva.getDps()) {
//                                            dps1 = eva.getDps();
//                                        }
//                                        eva = (Evaluacion) eval.get(1);
//                                        suma2 = suma2 + eva.getNota();
//                                        if (dps2 < eva.getDps()) {
//                                            dps2 = eva.getDps();
//                                        }
//                                        eva = (Evaluacion) eval.get(2);
//                                        suma3 = suma3 + eva.getNota();
//                                        if (dps3 < eva.getDps()) {
//                                            dps3 = eva.getDps();
//                                        }
//                                        if (j == 6) {
//                                            int red1 = (int) (Math.round(((double) suma1 / 3) * Math.pow(10, 0)) / Math.pow(10, 0));
//                                            int red2 = (int) (Math.round(((double) suma2 / 3) * Math.pow(10, 0)) / Math.pow(10, 0));
//                                            int red3 = (int) (Math.round(((double) suma3 / 3) * Math.pow(10, 0)) / Math.pow(10, 0));
//                                            sheet.addCell(new jxl.write.Number(2, Vk[contii], red1));
//                                            sheet.addCell(new jxl.write.Number(3, Vk[contii], dps1));
//                                            sheet.addCell(new jxl.write.Number(5, Vk[contii], red2));
//                                            sheet.addCell(new jxl.write.Number(6, Vk[contii], dps2));
//                                            sheet.addCell(new jxl.write.Number(8, Vk[contii], red3));
//                                            sheet.addCell(new jxl.write.Number(9, Vk[contii], dps3));
//                                        } else {
//                                            contii--;
//                                        }
//                                    } else {
//                                        for (int k = 0; k < eval.size(); k++) {
//                                            Evaluacion eva = (Evaluacion) eval.get(k);
//                                            if (k == 0) {
//                                                sheet.addCell(new jxl.write.Number(2, Vk[contii], eva.getNota()));
//                                                sheet.addCell(new jxl.write.Number(3, Vk[contii], eva.getDps()));
//                                            } else if (k == 1) {
//                                                sheet.addCell(new jxl.write.Number(5, Vk[contii], eva.getNota()));
//                                                sheet.addCell(new jxl.write.Number(6, Vk[contii], eva.getDps()));
//                                            } else if (k == 2) {
//                                                sheet.addCell(new jxl.write.Number(8, Vk[contii], eva.getNota()));
//                                                sheet.addCell(new jxl.write.Number(9, Vk[contii], eva.getDps()));
//                                            } else if (k == 3) {
//                                                if (eva.getNota() > 0) {
//                                                    sheet.addCell(new jxl.write.Number(12, Vk[contii], eva.getNota()));
//                                                }
//                                            }
//                                        }
//                                    }
//                                    contii++;
//                                }
//                            }
//                            workbook.write();
//                            workbook.close();
//                        }
//
//                        if (id_curso.equals("S3")) {
////                            Curso curso = new Curso();
//                            curso.setId_curso(id_curso);
//                            curso.setId_gestion(Integer.parseInt(id_gestion));
//                            curso = this.sigaa.getCursoByIdCursoLibreta(curso);
//                            int Vk[] = new int[400];
//                            Vk[0] = 7;
//                            int contii = 0;
//                            int kin[] = {1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 23};
//                            for (int ii = 1; ii < 399; ii++) {
//                                Vk[ii] = Vk[ii - 1] + kin[contii];
//                                if (contii == 13) {
//                                    contii = 0;
//                                } else {
//                                    contii++;
//                                }
//                            }
//                            Date FechaActual = new Date();
//                            SimpleDateFormat Formator = new SimpleDateFormat("dd-MM-yy"); // formato de la fecha y hora
//                            String CadenaFechar = Formator.format(FechaActual);
//                            String dirPlantilla = System.getenv("SIGAA_HOME") + "/documentos/libretas/plantillas/SECUNDARIA3/2009_80730484_SEC_M_3_A.xls";
//                            Workbook plantilla = Workbook.getWorkbook(new File(dirPlantilla));
//                            String dirDestino = System.getenv("SIGAA_HOME") + "/documentos/libretas/report/SECUNDARIA3/";
//                            String nombreArch = "2009_80730484_SEC_M_3_A____" + CadenaFechar + ".xls";
//                            WritableWorkbook workbook = Workbook.createWorkbook(new File(dirDestino + nombreArch), plantilla); // Excel 2003
//                            WritableSheet sheet = workbook.getSheet("ficha1");
//                            WritableFont arial_10 = new WritableFont(WritableFont.ARIAL, 10);
//                            WritableCellFormat arial10 = new WritableCellFormat(arial_10);
//                            int fich = 0;
//                            int cont = 0;
//                            contii = 0;
//                            for (int i = 0; i < curso.getEstudiantes().size(); i++) {
//                                cont++;
//                                if (cont == 1 || cont == 26 || cont == 51 || cont == 76 || cont == 101 || cont == 126) {
//                                    fich = fich + 1;
//                                    String ficha = "ficha" + Integer.toString(fich);
//                                    sheet = workbook.getSheet(ficha);
//                                    contii = 0;
//                                }
//                                Estudiante estudiante = (Estudiante) curso.getEstudiantes().get(i);
//                                int suma1 = 0;
//                                int suma2 = 0;
//                                int suma3 = 0;
//                                int dps1 = 0;
//                                int dps2 = 0;
//                                int dps3 = 0;
//                                for (int j = 0; j < estudiante.getMaterias().size(); j++) {
//                                    Materia materia = (Materia) curso.getMaterias().get(j);
//                                    materia.getCursomateria().setId_curso_materia(curso.getId_curso() + "-" + materia.getId_materia());
//                                    materia.setId_estudiante(estudiante.getId_estudiante());
//                                    List eval = this.sigaa.getEvaluacionesEstudianteCursoMateria(materia);
//
//                                    for (int k = 0; k < eval.size(); k++) {
//                                        Evaluacion eva = (Evaluacion) eval.get(k);
//                                        if (k == 0) {
//                                            sheet.addCell(new jxl.write.Number(2, Vk[contii], eva.getNota()));
//                                            sheet.addCell(new jxl.write.Number(3, Vk[contii], eva.getDps()));
//                                        } else if (k == 1) {
//                                            sheet.addCell(new jxl.write.Number(5, Vk[contii], eva.getNota()));
//                                            sheet.addCell(new jxl.write.Number(6, Vk[contii], eva.getDps()));
//                                        } else if (k == 2) {
//                                            sheet.addCell(new jxl.write.Number(8, Vk[contii], eva.getNota()));
//                                            sheet.addCell(new jxl.write.Number(9, Vk[contii], eva.getDps()));
//                                        } else if (k == 3) {
//                                            if (eva.getNota() > 0) {
//                                                sheet.addCell(new jxl.write.Number(12, Vk[contii], eva.getNota()));
//                                            }
//                                        }
//                                    }
//
//                                    contii++;
//                                }
//                            }
//                            workbook.write();
//                            workbook.close();
//                        }
//
//                         if (id_curso.equals("S4")) {
////                            Curso curso = new Curso();
//                            curso.setId_curso(id_curso);
//                            curso.setId_gestion(Integer.parseInt(id_gestion));
//                            curso = this.sigaa.getCursoByIdCursoLibreta(curso);
//                            int Vk[] = new int[400];
//                            Vk[0] = 7;
//                            int contii = 0;
//                            int kin[] = {1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 23};
//                            for (int ii = 1; ii < 399; ii++) {
//                                Vk[ii] = Vk[ii - 1] + kin[contii];
//                                if (contii == 13) {
//                                    contii = 0;
//                                } else {
//                                    contii++;
//                                }
//                            }
//                            Date FechaActual = new Date();
//                            SimpleDateFormat Formator = new SimpleDateFormat("dd-MM-yy"); // formato de la fecha y hora
//                            String CadenaFechar = Formator.format(FechaActual);
//                            String dirPlantilla = System.getenv("SIGAA_HOME") + "/documentos/libretas/plantillas/SECUNDARIA4/2009_80730484_SEC_M_4_A.xls";
//                            Workbook plantilla = Workbook.getWorkbook(new File(dirPlantilla));
//                            String dirDestino = System.getenv("SIGAA_HOME") + "/documentos/libretas/report/SECUNDARIA4/";
//                            String nombreArch = "2009_80730484_SEC_M_4_A____" + CadenaFechar + ".xls";
//                            WritableWorkbook workbook = Workbook.createWorkbook(new File(dirDestino + nombreArch), plantilla); // Excel 2003
//                            WritableSheet sheet = workbook.getSheet("ficha1");
//                            WritableFont arial_10 = new WritableFont(WritableFont.ARIAL, 10);
//                            WritableCellFormat arial10 = new WritableCellFormat(arial_10);
//                            int fich = 0;
//                            int cont = 0;
//                            contii = 0;
//                            for (int i = 0; i < curso.getEstudiantes().size(); i++) {
//                                cont++;
//                                if (cont == 1 || cont == 26 || cont == 51 || cont == 76 || cont == 101 || cont == 126) {
//                                    fich = fich + 1;
//                                    String ficha = "ficha" + Integer.toString(fich);
//                                    sheet = workbook.getSheet(ficha);
//                                    contii = 0;
//                                }
//                                Estudiante estudiante = (Estudiante) curso.getEstudiantes().get(i);
//                                int suma1 = 0;
//                                int suma2 = 0;
//                                int suma3 = 0;
//                                int dps1 = 0;
//                                int dps2 = 0;
//                                int dps3 = 0;
//                                for (int j = 0; j < estudiante.getMaterias().size(); j++) {
//                                    Materia materia = (Materia) curso.getMaterias().get(j);
//                                    materia.getCursomateria().setId_curso_materia(curso.getId_curso() + "-" + materia.getId_materia());
//                                    materia.setId_estudiante(estudiante.getId_estudiante());
//                                    List eval = this.sigaa.getEvaluacionesEstudianteCursoMateria(materia);
//
//                                    for (int k = 0; k < eval.size(); k++) {
//                                        Evaluacion eva = (Evaluacion) eval.get(k);
//                                        if (k == 0) {
//                                            sheet.addCell(new jxl.write.Number(2, Vk[contii], eva.getNota()));
//                                            sheet.addCell(new jxl.write.Number(3, Vk[contii], eva.getDps()));
//                                        } else if (k == 1) {
//                                            sheet.addCell(new jxl.write.Number(5, Vk[contii], eva.getNota()));
//                                            sheet.addCell(new jxl.write.Number(6, Vk[contii], eva.getDps()));
//                                        } else if (k == 2) {
//                                            sheet.addCell(new jxl.write.Number(8, Vk[contii], eva.getNota()));
//                                            sheet.addCell(new jxl.write.Number(9, Vk[contii], eva.getDps()));
//                                        } else if (k == 3) {
//                                            if (eva.getNota() > 0) {
//                                                sheet.addCell(new jxl.write.Number(12, Vk[contii], eva.getNota()));
//                                            }
//                                        }
//                                    }
//
//                                    contii++;
//                                }
//                            }
//                            workbook.write();
//                            workbook.close();
//                        }
//
//                    }
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

    private String setGenerarBoletinesExcel(Curso curso, String id_gestion) {
        String archivo = "2010_80730484_INI_M_2_Aaaa.xls";
        try {
            String dirDestino = System.getenv("SIGAA_HOME") + "/documentos/libretas/report/KINDER/";
//            archivo = "2010_80730484_INI_M_2_A.xls";
            String dirPlantilla = System.getenv("SIGAA_HOME") + "/documentos/libretas/plantillas/KINDER/2010_80730484_INI_M_2_A.xls";
            Workbook plantilla = Workbook.getWorkbook(new File(dirPlantilla));
            WritableWorkbook workbook = Workbook.createWorkbook(new File(dirDestino + archivo), plantilla); // Excel 2003
            WritableSheet sheet = workbook.getSheet("FICHA1");
            WritableFont arial_10 = new WritableFont(WritableFont.ARIAL, 10);
            WritableCellFormat arial10 = new WritableCellFormat(arial_10);
            int fich = 0;
            int cont = 21;

//            for (int i = 0; i < curso.getEstudiantes().size(); i++) { // registra la apreciacion final
//                Estudiante estudiante = (Estudiante) curso.getEstudiantes().get(i);
//                if (i == 0 || i == 20 || i == 40 || i == 60) {
//                    fich = fich + 1;
//                    String ficha = "CEN" + Integer.toString(fich);
//                    sheet = workbook.getSheet(ficha);
//                    cont = 21;
//                }
//                Conducta conducta = (Conducta) estudiante.getConductas().get(1);
//                sheet.addCell(new Label(34, cont++, conducta.getDiagnostico(), arial10));
//            }
            int contii = 0;
            int Vk[] = new int[350];
            Vk[0] = 7;

            int kin[] = {1, 1, 1, 1, 13};
            for (int ii = 1; ii < 340; ii++) {
                Vk[ii] = Vk[ii - 1] + kin[contii];
                if (contii == 4) {
                    contii = 0;
                } else {
                    contii++;
                }
            }
            fich = 0;
            cont = 0;
            contii = 0;
//            workbook.getSheet("FICHA1");
            for (int x = 0; x < curso.getEstudiantes().size(); x++) {
//                cont++;
//                if (cont == 1 || x == 20 || x == 40 || x == 60 || x == 80 || x == 100) {
//                    fich = fich + 1;
//                    String ficha = "FICHA" + Integer.toString(fich);
//                    sheet = workbook.getSheet(ficha);
//                    contii = 0;
//                }
                Estudiante estudiante = (Estudiante) curso.getEstudiantes().get(x);
                Curso cur = new Curso();
                cur.setId_curso(curso.getId_curso());
                cur.setId_gestion(Integer.parseInt(id_gestion));
                cur.setId_estudiante(estudiante.getId_estudiante());
                cur = this.sigaa.getCursoByIdCursoAndIdEstudiantesAndIdGestion(cur);
                for (int i = 0; i < cur.getMaterias().size(); i++) {
                    Materia materia = (Materia) cur.getMaterias().get(i);
                    for (int j = 0; j < materia.getEvaluaciones().size(); j++) {
                        Evaluacion evaluacion = (Evaluacion) materia.getEvaluaciones().get(j);
                        if (j == 0) {
                            System.out.println("col:" + 2 + " , fila:" + Vk[contii] + "  , nota:" + evaluacion.getCualitativa());
                            sheet.addCell(new Label(2, Vk[contii], evaluacion.getCualitativa(), arial10));
                        }
                        if (j == 1) {
                            System.out.println("col:" + 6 + " , fila:" + Vk[contii] + "  , nota:" + evaluacion.getCualitativa());
                            sheet.addCell(new Label(6, Vk[contii], evaluacion.getCualitativa(), arial10));
                        }
                        if (j == 2) {
                            System.out.println("col:" + 10 + " , fila:" + Vk[contii] + "  , nota:" + evaluacion.getCualitativa());
                            sheet.addCell(new Label(10, Vk[contii], evaluacion.getCualitativa(), arial10));
                        }
                    }
                    contii++;
                }
//                Gestion gestion = (Gestion) this.sigaa.getBuscarGestion(Integer.parseInt(id_gestion)).get(0);
//                curso.setGestion(gestion);
            }
            workbook.write();
            workbook.close();
        } catch (Exception de) {
            de.printStackTrace();
        }
        return archivo;

    }

    private String setGenerarLibretasPrimaria1(Curso curso_gen, String id_gestion) {
        String archivo = "2010_80730484_PRI_M_1_A.xls";
        try {
            String dirDestino = System.getenv("SIGAA_HOME") + "/documentos/libretas/report/PRIMARIA1/";
            String dirPlantilla = System.getenv("SIGAA_HOME") + "/documentos/libretas/plantillas/PRIMARIA1/2010_80730484_PRI_M_1_A.xls";
            Workbook plantilla = Workbook.getWorkbook(new File(dirPlantilla));
            WritableWorkbook workbook = Workbook.createWorkbook(new File(dirDestino + archivo), plantilla); // Excel 2003
            WritableFont arial_10 = new WritableFont(WritableFont.ARIAL, 10);
            WritableCellFormat arial10 = new WritableCellFormat(arial_10);
            WritableSheet sheet = workbook.getSheet("Ficha1");
            int V[] = new int[350];
            V[1] = 7;
            for (int ii = 2; ii <= 25; ii++) {
                V[ii] = V[ii - 1] + 25;
            }
            int V_int[] = new int[10];
            V_int[1] = 0; //+0
            V_int[2] = 2; //+2
            V_int[3] = 3; //+1
            V_int[4] = 4; //+1
            V_int[5] = 5; //+1
            V_int[6] = 7; //+2
            V_int[7] = 8; //+1
            V_int[8] = 9; //+1
            V_int[9] = 15; //+6
            int ficha = 0;
            int cont_v = 0;
            for (int x = 0; x < curso_gen.getEstudiantes().size(); x++) {
                Estudiante estudiante = (Estudiante) curso_gen.getEstudiantes().get(x);
                Curso curso = new Curso();
                curso.setId_curso(curso_gen.getId_curso());
                curso.setId_gestion(Integer.parseInt(id_gestion));
                curso.setId_estudiante(estudiante.getId_estudiante());
                curso.setId_gestion(Integer.parseInt(id_gestion));
                curso = this.sigaa.getCursoByIdCursoAndIdEstudiantesAndIdGestion(curso);
                if (x == 0 || (x + 1) % 26 == 0) {
                    ficha++;
                    sheet = workbook.getSheet("Ficha" + ficha);
                    cont_v = 0;
                }
                cont_v++;
                int cont_vi = 0;
                for (int i = 0; i < curso.getMaterias().size(); i++) {
                    Materia materia = (Materia) curso.getMaterias().get(i);
                    cont_vi++;
                    for (int j = 0; j < materia.getEvaluaciones().size(); j++) {
                        Evaluacion evaluacion = (Evaluacion) materia.getEvaluaciones().get(j);
                        if (j == 0) {
                            sheet.addCell(new jxl.write.Number(2, (V[cont_v] + V_int[cont_vi]), evaluacion.getNota() + evaluacion.getDps()));
                            sheet.addCell(new Label(3, (V[cont_v] + V_int[cont_vi]), evaluacion.getCualitativa_p(), arial10));
                        }
                        if (j == 1) {
                            sheet.addCell(new jxl.write.Number(7, (V[cont_v] + V_int[cont_vi]), evaluacion.getNota() + evaluacion.getDps()));
                            sheet.addCell(new Label(8, (V[cont_v] + V_int[cont_vi]), evaluacion.getCualitativa_p(), arial10));
                        }
                        if (j == 2) {
                            sheet.addCell(new jxl.write.Number(12, (V[cont_v] + V_int[cont_vi]), evaluacion.getNota() + evaluacion.getDps()));
                            sheet.addCell(new Label(13, (V[cont_v] + V_int[cont_vi]), evaluacion.getCualitativa_p(), arial10));
                        }
                    }
                }
                cont_vi++;
                for (int l = 0; l < curso.getFaltas().size(); l++) {
                    Falta falta = (Falta) curso.getFaltas().get(l);
                    if (l == 0) {
                        sheet.addCell(new jxl.write.Number(3, (V[cont_v] + V_int[cont_vi]), falta.getRetraso()));
                        sheet.addCell(new jxl.write.Number(4, (V[cont_v] + V_int[cont_vi]), falta.getFcl()));
                        sheet.addCell(new jxl.write.Number(5, (V[cont_v] + V_int[cont_vi]), falta.getFsl()));
                        sheet.addCell(new jxl.write.Number(6, (V[cont_v] + V_int[cont_vi]), 68));
                    }
                    if (l == 1) {
                        sheet.addCell(new jxl.write.Number(8, (V[cont_v] + V_int[cont_vi]), falta.getRetraso()));
                        sheet.addCell(new jxl.write.Number(9, (V[cont_v] + V_int[cont_vi]), falta.getFcl()));
                        sheet.addCell(new jxl.write.Number(10, (V[cont_v] + V_int[cont_vi]), falta.getFsl()));
                        sheet.addCell(new jxl.write.Number(11, (V[cont_v] + V_int[cont_vi]), 67));
                    }
                    if (l == 2) {
                        sheet.addCell(new jxl.write.Number(13, (V[cont_v] + V_int[cont_vi]), falta.getRetraso()));
                        sheet.addCell(new jxl.write.Number(14, (V[cont_v] + V_int[cont_vi]), falta.getFcl()));
                        sheet.addCell(new jxl.write.Number(15, (V[cont_v] + V_int[cont_vi]), falta.getFsl()));
                        sheet.addCell(new jxl.write.Number(16, (V[cont_v] + V_int[cont_vi]), 65));
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

    private String setGenerarLibretasPrimaria2(Curso curso_gen, String id_gestion) {
        String archivo = "2010_80730484_PRI_M_2_A.xls";
        try {
            String dirDestino = System.getenv("SIGAA_HOME") + "/documentos/libretas/report/PRIMARIA2/";
            String dirPlantilla = System.getenv("SIGAA_HOME") + "/documentos/libretas/plantillas/PRIMARIA2/2010_80730484_PRI_M_2_A.xls";
            Workbook plantilla = Workbook.getWorkbook(new File(dirPlantilla));
            WritableWorkbook workbook = Workbook.createWorkbook(new File(dirDestino + archivo), plantilla); // Excel 2003
            WritableFont arial_10 = new WritableFont(WritableFont.ARIAL, 10);
            WritableCellFormat arial10 = new WritableCellFormat(arial_10);
            WritableSheet sheet = workbook.getSheet("Ficha1");
            int V[] = new int[350];
            V[1] = 7;
            for (int ii = 2; ii <= 25; ii++) {
                V[ii] = V[ii - 1] + 25;
            }
            int V_int[] = new int[10];
            V_int[1] = 0; //+0
            V_int[2] = 2; //+2
            V_int[3] = 3; //+1
            V_int[4] = 4; //+1
            V_int[5] = 5; //+1
            V_int[6] = 7; //+2
            V_int[7] = 8; //+1
            V_int[8] = 9; //+1
            V_int[9] = 15; //+6
            int ficha = 0;
            int cont_v = 0;
            for (int x = 0; x < curso_gen.getEstudiantes().size(); x++) {
                Estudiante estudiante = (Estudiante) curso_gen.getEstudiantes().get(x);
                Curso curso = new Curso();
                curso.setId_curso(curso_gen.getId_curso());
                curso.setId_gestion(Integer.parseInt(id_gestion));
                curso.setId_estudiante(estudiante.getId_estudiante());
                curso.setId_gestion(Integer.parseInt(id_gestion));
                curso = this.sigaa.getCursoByIdCursoAndIdEstudiantesAndIdGestion(curso);
                if (x == 0 || (x + 1) % 26 == 0) {
                    ficha++;
                    sheet = workbook.getSheet("Ficha" + ficha);
                    cont_v = 0;
                }
                cont_v++;
                int cont_vi = 0;
                for (int i = 0; i < curso.getMaterias().size(); i++) {
                    Materia materia = (Materia) curso.getMaterias().get(i);
                    cont_vi++;
                    for (int j = 0; j < materia.getEvaluaciones().size(); j++) {
                        Evaluacion evaluacion = (Evaluacion) materia.getEvaluaciones().get(j);
                        if (j == 0) {
                            sheet.addCell(new jxl.write.Number(2, (V[cont_v] + V_int[cont_vi]), evaluacion.getNota() + evaluacion.getDps()));
                            sheet.addCell(new Label(3, (V[cont_v] + V_int[cont_vi]), evaluacion.getCualitativa_p(), arial10));
                        }
                        if (j == 1) {
                            sheet.addCell(new jxl.write.Number(7, (V[cont_v] + V_int[cont_vi]), evaluacion.getNota() + evaluacion.getDps()));
                            sheet.addCell(new Label(8, (V[cont_v] + V_int[cont_vi]), evaluacion.getCualitativa_p(), arial10));
                        }
                        if (j == 2) {
                            sheet.addCell(new jxl.write.Number(12, (V[cont_v] + V_int[cont_vi]), evaluacion.getNota() + evaluacion.getDps()));
                            sheet.addCell(new Label(13, (V[cont_v] + V_int[cont_vi]), evaluacion.getCualitativa_p(), arial10));
                        }
                    }
                }
                cont_vi++;
                for (int l = 0; l < curso.getFaltas().size(); l++) {
                    Falta falta = (Falta) curso.getFaltas().get(l);
                    if (l == 0) {
                        sheet.addCell(new jxl.write.Number(3, (V[cont_v] + V_int[cont_vi]), falta.getRetraso()));
                        sheet.addCell(new jxl.write.Number(4, (V[cont_v] + V_int[cont_vi]), falta.getFcl()));
                        sheet.addCell(new jxl.write.Number(5, (V[cont_v] + V_int[cont_vi]), falta.getFsl()));
                        sheet.addCell(new jxl.write.Number(6, (V[cont_v] + V_int[cont_vi]), 68));
                    }
                    if (l == 1) {
                        sheet.addCell(new jxl.write.Number(8, (V[cont_v] + V_int[cont_vi]), falta.getRetraso()));
                        sheet.addCell(new jxl.write.Number(9, (V[cont_v] + V_int[cont_vi]), falta.getFcl()));
                        sheet.addCell(new jxl.write.Number(10, (V[cont_v] + V_int[cont_vi]), falta.getFsl()));
                        sheet.addCell(new jxl.write.Number(11, (V[cont_v] + V_int[cont_vi]), 67));
                    }
                    if (l == 2) {
                        sheet.addCell(new jxl.write.Number(13, (V[cont_v] + V_int[cont_vi]), falta.getRetraso()));
                        sheet.addCell(new jxl.write.Number(14, (V[cont_v] + V_int[cont_vi]), falta.getFcl()));
                        sheet.addCell(new jxl.write.Number(15, (V[cont_v] + V_int[cont_vi]), falta.getFsl()));
                        sheet.addCell(new jxl.write.Number(16, (V[cont_v] + V_int[cont_vi]), 65));
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

    private String setGenerarLibretasPrimaria3(Curso curso_gen, String id_gestion) {
        String archivo = "2010_80730484_PRI_M_3_A.xls";
        try {
            String dirDestino = System.getenv("SIGAA_HOME") + "/documentos/libretas/report/PRIMARIA3/";
            String dirPlantilla = System.getenv("SIGAA_HOME") + "/documentos/libretas/plantillas/PRIMARIA3/2010_80730484_PRI_M_3_A.xls";
            Workbook plantilla = Workbook.getWorkbook(new File(dirPlantilla));
            WritableWorkbook workbook = Workbook.createWorkbook(new File(dirDestino + archivo), plantilla); // Excel 2003
            WritableFont arial_10 = new WritableFont(WritableFont.ARIAL, 10);
            WritableCellFormat arial10 = new WritableCellFormat(arial_10);
            WritableSheet sheet = workbook.getSheet("Ficha1");
            int V[] = new int[350];
            V[1] = 7;
            for (int ii = 2; ii <= 25; ii++) {
                V[ii] = V[ii - 1] + 25;
            }
            int V_int[] = new int[10];
            V_int[1] = 0; //+0
            V_int[2] = 2; //+2
            V_int[3] = 3; //+1
            V_int[4] = 4; //+1
            V_int[5] = 5; //+1
            V_int[6] = 7; //+2
            V_int[7] = 8; //+1
            V_int[8] = 9; //+1
            V_int[9] = 15; //+6
            int ficha = 0;
            int cont_v = 0;
            for (int x = 0; x < curso_gen.getEstudiantes().size(); x++) {
                Estudiante estudiante = (Estudiante) curso_gen.getEstudiantes().get(x);
                Curso curso = new Curso();
                curso.setId_curso(curso_gen.getId_curso());
                curso.setId_gestion(Integer.parseInt(id_gestion));
                curso.setId_estudiante(estudiante.getId_estudiante());
                curso.setId_gestion(Integer.parseInt(id_gestion));
                curso = this.sigaa.getCursoByIdCursoAndIdEstudiantesAndIdGestion(curso);
                if (x == 0 || (x + 1) % 26 == 0) {
                    ficha++;
                    sheet = workbook.getSheet("Ficha" + ficha);
                    cont_v = 0;
                }
                cont_v++;
                int cont_vi = 0;
                for (int i = 0; i < curso.getMaterias().size(); i++) {
                    Materia materia = (Materia) curso.getMaterias().get(i);
                    cont_vi++;
                    for (int j = 0; j < materia.getEvaluaciones().size(); j++) {
                        Evaluacion evaluacion = (Evaluacion) materia.getEvaluaciones().get(j);
                        if (j == 0) {
                            sheet.addCell(new jxl.write.Number(2, (V[cont_v] + V_int[cont_vi]), evaluacion.getNota() + evaluacion.getDps()));
                            sheet.addCell(new Label(3, (V[cont_v] + V_int[cont_vi]), evaluacion.getCualitativa_p(), arial10));
                        }
                        if (j == 1) {
                            sheet.addCell(new jxl.write.Number(7, (V[cont_v] + V_int[cont_vi]), evaluacion.getNota() + evaluacion.getDps()));
                            sheet.addCell(new Label(8, (V[cont_v] + V_int[cont_vi]), evaluacion.getCualitativa_p(), arial10));
                        }
                        if (j == 2) {
                            sheet.addCell(new jxl.write.Number(12, (V[cont_v] + V_int[cont_vi]), evaluacion.getNota() + evaluacion.getDps()));
                            sheet.addCell(new Label(13, (V[cont_v] + V_int[cont_vi]), evaluacion.getCualitativa_p(), arial10));
                        }
                    }
                }
                cont_vi++;
                for (int l = 0; l < curso.getFaltas().size(); l++) {
                    Falta falta = (Falta) curso.getFaltas().get(l);
                    if (l == 0) {
                        sheet.addCell(new jxl.write.Number(3, (V[cont_v] + V_int[cont_vi]), falta.getRetraso()));
                        sheet.addCell(new jxl.write.Number(4, (V[cont_v] + V_int[cont_vi]), falta.getFcl()));
                        sheet.addCell(new jxl.write.Number(5, (V[cont_v] + V_int[cont_vi]), falta.getFsl()));
                        sheet.addCell(new jxl.write.Number(6, (V[cont_v] + V_int[cont_vi]), 68));
                    }
                    if (l == 1) {
                        sheet.addCell(new jxl.write.Number(8, (V[cont_v] + V_int[cont_vi]), falta.getRetraso()));
                        sheet.addCell(new jxl.write.Number(9, (V[cont_v] + V_int[cont_vi]), falta.getFcl()));
                        sheet.addCell(new jxl.write.Number(10, (V[cont_v] + V_int[cont_vi]), falta.getFsl()));
                        sheet.addCell(new jxl.write.Number(11, (V[cont_v] + V_int[cont_vi]), 67));
                    }
                    if (l == 2) {
                        sheet.addCell(new jxl.write.Number(13, (V[cont_v] + V_int[cont_vi]), falta.getRetraso()));
                        sheet.addCell(new jxl.write.Number(14, (V[cont_v] + V_int[cont_vi]), falta.getFcl()));
                        sheet.addCell(new jxl.write.Number(15, (V[cont_v] + V_int[cont_vi]), falta.getFsl()));
                        sheet.addCell(new jxl.write.Number(16, (V[cont_v] + V_int[cont_vi]), 65));
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

    private String setGenerarLibretasPrimaria4(Curso curso_gen, String id_gestion) {
        String archivo = "2010_80730484_PRI_M_4_A.xls";
        try {
            String dirDestino = System.getenv("SIGAA_HOME") + "/documentos/libretas/report/PRIMARIA4/";
            String dirPlantilla = System.getenv("SIGAA_HOME") + "/documentos/libretas/plantillas/PRIMARIA4/2010_80730484_PRI_M_4_A.xls";
            Workbook plantilla = Workbook.getWorkbook(new File(dirPlantilla));
            WritableWorkbook workbook = Workbook.createWorkbook(new File(dirDestino + archivo), plantilla); // Excel 2003
            WritableFont arial_10 = new WritableFont(WritableFont.ARIAL, 10);
            WritableCellFormat arial10 = new WritableCellFormat(arial_10);
            WritableSheet sheet = workbook.getSheet("Ficha1");
            int V[] = new int[350];
            V[1] = 7;
            for (int ii = 2; ii <= 25; ii++) {
                V[ii] = V[ii - 1] + 25;
            }
            int V_int[] = new int[10];
            V_int[1] = 0; //+0
            V_int[2] = 2; //+2
            V_int[3] = 3; //+1
            V_int[4] = 4; //+1
            V_int[5] = 5; //+1
            V_int[6] = 7; //+2
            V_int[7] = 8; //+1
            V_int[8] = 9; //+1
            V_int[9] = 15; //+6
            int ficha = 0;
            int cont_v = 0;
            for (int x = 0; x < curso_gen.getEstudiantes().size(); x++) {
                Estudiante estudiante = (Estudiante) curso_gen.getEstudiantes().get(x);

                Curso curso = new Curso();
                curso.setId_curso(curso_gen.getId_curso());
                curso.setId_gestion(Integer.parseInt(id_gestion));
                curso.setId_estudiante(estudiante.getId_estudiante());
                curso.setId_gestion(Integer.parseInt(id_gestion));
                curso = this.sigaa.getCursoByIdCursoAndIdEstudiantesAndIdGestion(curso);
                if (x == 0 || (x + 1) % 26 == 0) {
                    ficha++;
                    sheet = workbook.getSheet("Ficha" + ficha);
                    cont_v = 0;
                }
                cont_v++;
                int cont_vi = 0;
                for (int i = 0; i < curso.getMaterias().size() - 2; i++) {
                    Materia materia = (Materia) curso.getMaterias().get(i);
                    cont_vi++;
                    for (int j = 0; j < materia.getEvaluaciones().size(); j++) {
                        Evaluacion evaluacion = (Evaluacion) materia.getEvaluaciones().get(j);
                        if (j == 0) {
                            sheet.addCell(new jxl.write.Number(2, (V[cont_v] + V_int[cont_vi]), evaluacion.getNota() + evaluacion.getDps()));
                            sheet.addCell(new Label(3, (V[cont_v] + V_int[cont_vi]), evaluacion.getCualitativa_p(), arial10));
                        }
                        if (j == 1) {
                            sheet.addCell(new jxl.write.Number(7, (V[cont_v] + V_int[cont_vi]), evaluacion.getNota() + evaluacion.getDps()));
                            sheet.addCell(new Label(8, (V[cont_v] + V_int[cont_vi]), evaluacion.getCualitativa_p(), arial10));
                        }
                        if (j == 2) {
                            sheet.addCell(new jxl.write.Number(12, (V[cont_v] + V_int[cont_vi]), evaluacion.getNota() + evaluacion.getDps()));
                            sheet.addCell(new Label(13, (V[cont_v] + V_int[cont_vi]), evaluacion.getCualitativa_p(), arial10));
                        }
                    }
                }
                cont_vi++;
                for (int l = 0; l < curso.getFaltas().size(); l++) {
                    Falta falta = (Falta) curso.getFaltas().get(l);
                    if (l == 0) {
                        sheet.addCell(new jxl.write.Number(3, (V[cont_v] + V_int[cont_vi]), falta.getRetraso()));
                        sheet.addCell(new jxl.write.Number(4, (V[cont_v] + V_int[cont_vi]), falta.getFcl()));
                        sheet.addCell(new jxl.write.Number(5, (V[cont_v] + V_int[cont_vi]), falta.getFsl()));
                        sheet.addCell(new jxl.write.Number(6, (V[cont_v] + V_int[cont_vi]), 68));
                    }
                    if (l == 1) {
                        sheet.addCell(new jxl.write.Number(8, (V[cont_v] + V_int[cont_vi]), falta.getRetraso()));
                        sheet.addCell(new jxl.write.Number(9, (V[cont_v] + V_int[cont_vi]), falta.getFcl()));
                        sheet.addCell(new jxl.write.Number(10, (V[cont_v] + V_int[cont_vi]), falta.getFsl()));
                        sheet.addCell(new jxl.write.Number(11, (V[cont_v] + V_int[cont_vi]), 67));
                    }
                    if (l == 2) {
                        sheet.addCell(new jxl.write.Number(13, (V[cont_v] + V_int[cont_vi]), falta.getRetraso()));
                        sheet.addCell(new jxl.write.Number(14, (V[cont_v] + V_int[cont_vi]), falta.getFcl()));
                        sheet.addCell(new jxl.write.Number(15, (V[cont_v] + V_int[cont_vi]), falta.getFsl()));
                        sheet.addCell(new jxl.write.Number(16, (V[cont_v] + V_int[cont_vi]), 65));
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

    private String setGenerarLibretasPrimaria5(Curso curso_gen, String id_gestion) {
        String archivo = "2010_80730484_PRI_M_5_A.xls";
        try {
            String dirDestino = System.getenv("SIGAA_HOME") + "/documentos/libretas/report/PRIMARIA5/";
            String dirPlantilla = System.getenv("SIGAA_HOME") + "/documentos/libretas/plantillas/PRIMARIA5/2010_80730484_PRI_M_5_A.xls";
            Workbook plantilla = Workbook.getWorkbook(new File(dirPlantilla));
            WritableWorkbook workbook = Workbook.createWorkbook(new File(dirDestino + archivo), plantilla); // Excel 2003
            WritableFont arial_10 = new WritableFont(WritableFont.ARIAL, 10);
            WritableCellFormat arial10 = new WritableCellFormat(arial_10);
            WritableSheet sheet = workbook.getSheet("Ficha1");
            int V[] = new int[350];
            V[1] = 7;
            for (int ii = 2; ii <= 25; ii++) {
                V[ii] = V[ii - 1] + 25;
            }
            int V_int[] = new int[10];
            V_int[1] = 0; //+0
            V_int[2] = 2; //+2
            V_int[3] = 3; //+1
            V_int[4] = 4; //+1
            V_int[5] = 5; //+1
            V_int[6] = 7; //+2
            V_int[7] = 8; //+1
            V_int[8] = 9; //+1
            V_int[9] = 15; //+6
            int ficha = 0;
            int cont_v = 0;
            for (int x = 0; x < curso_gen.getEstudiantes().size(); x++) {
                Estudiante estudiante = (Estudiante) curso_gen.getEstudiantes().get(x);

                Curso curso = new Curso();
                curso.setId_curso(curso_gen.getId_curso());
                curso.setId_gestion(Integer.parseInt(id_gestion));
                curso.setId_estudiante(estudiante.getId_estudiante());
                curso.setId_gestion(Integer.parseInt(id_gestion));
                curso = this.sigaa.getCursoByIdCursoAndIdEstudiantesAndIdGestion(curso);
                if (x == 0 || (x + 1) % 26 == 0) {
                    ficha++;
                    sheet = workbook.getSheet("Ficha" + ficha);
                    cont_v = 0;
                }
                cont_v++;
                int cont_vi = 0;
                for (int i = 0; i < curso.getMaterias().size() - 2; i++) {
                    Materia materia = (Materia) curso.getMaterias().get(i);
                    cont_vi++;
                    for (int j = 0; j < materia.getEvaluaciones().size(); j++) {
                        Evaluacion evaluacion = (Evaluacion) materia.getEvaluaciones().get(j);
                        if (j == 0) {
                            sheet.addCell(new jxl.write.Number(2, (V[cont_v] + V_int[cont_vi]), evaluacion.getNota() + evaluacion.getDps()));
                            sheet.addCell(new Label(3, (V[cont_v] + V_int[cont_vi]), evaluacion.getCualitativa_p(), arial10));
                        }
                        if (j == 1) {
                            sheet.addCell(new jxl.write.Number(7, (V[cont_v] + V_int[cont_vi]), evaluacion.getNota() + evaluacion.getDps()));
                            sheet.addCell(new Label(8, (V[cont_v] + V_int[cont_vi]), evaluacion.getCualitativa_p(), arial10));
                        }
                        if (j == 2) {
                            sheet.addCell(new jxl.write.Number(12, (V[cont_v] + V_int[cont_vi]), evaluacion.getNota() + evaluacion.getDps()));
                            sheet.addCell(new Label(13, (V[cont_v] + V_int[cont_vi]), evaluacion.getCualitativa_p(), arial10));
                        }
                    }
                }
                cont_vi++;
                for (int l = 0; l < curso.getFaltas().size(); l++) {
                    Falta falta = (Falta) curso.getFaltas().get(l);
                    if (l == 0) {
                        sheet.addCell(new jxl.write.Number(3, (V[cont_v] + V_int[cont_vi]), falta.getRetraso()));
                        sheet.addCell(new jxl.write.Number(4, (V[cont_v] + V_int[cont_vi]), falta.getFcl()));
                        sheet.addCell(new jxl.write.Number(5, (V[cont_v] + V_int[cont_vi]), falta.getFsl()));
                        sheet.addCell(new jxl.write.Number(6, (V[cont_v] + V_int[cont_vi]), 68));
                    }
                    if (l == 1) {
                        sheet.addCell(new jxl.write.Number(8, (V[cont_v] + V_int[cont_vi]), falta.getRetraso()));
                        sheet.addCell(new jxl.write.Number(9, (V[cont_v] + V_int[cont_vi]), falta.getFcl()));
                        sheet.addCell(new jxl.write.Number(10, (V[cont_v] + V_int[cont_vi]), falta.getFsl()));
                        sheet.addCell(new jxl.write.Number(11, (V[cont_v] + V_int[cont_vi]), 67));
                    }
                    if (l == 2) {
                        sheet.addCell(new jxl.write.Number(13, (V[cont_v] + V_int[cont_vi]), falta.getRetraso()));
                        sheet.addCell(new jxl.write.Number(14, (V[cont_v] + V_int[cont_vi]), falta.getFcl()));
                        sheet.addCell(new jxl.write.Number(15, (V[cont_v] + V_int[cont_vi]), falta.getFsl()));
                        sheet.addCell(new jxl.write.Number(16, (V[cont_v] + V_int[cont_vi]), 65));
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

    private String setGenerarLibretasPrimaria6(Curso curso_gen, String id_gestion) {
        String archivo = "2010_80730484_PRI_M_6_A.xls";
        try {
            String dirDestino = System.getenv("SIGAA_HOME") + "/documentos/libretas/report/PRIMARIA6/";
            String dirPlantilla = System.getenv("SIGAA_HOME") + "/documentos/libretas/plantillas/PRIMARIA6/2010_80730484_PRI_M_6_A.xls";
            Workbook plantilla = Workbook.getWorkbook(new File(dirPlantilla));
            WritableWorkbook workbook = Workbook.createWorkbook(new File(dirDestino + archivo), plantilla); // Excel 2003
            WritableFont arial_10 = new WritableFont(WritableFont.ARIAL, 10);
            WritableCellFormat arial10 = new WritableCellFormat(arial_10);
            WritableSheet sheet = workbook.getSheet("Ficha1");
            int V[] = new int[350];
            V[1] = 7;
            for (int ii = 2; ii <= 25; ii++) {
                V[ii] = V[ii - 1] + 25;
            }
            int V_int[] = new int[10];
            V_int[1] = 0; //+0
            V_int[2] = 2; //+2
            V_int[3] = 3; //+1
            V_int[4] = 4; //+1
            V_int[5] = 5; //+1
            V_int[6] = 7; //+2
            V_int[7] = 8; //+1
            V_int[8] = 9; //+1
            V_int[9] = 15; //+6
            int ficha = 0;
            int cont_v = 0;
            for (int x = 0; x < curso_gen.getEstudiantes().size(); x++) {
                Estudiante estudiante = (Estudiante) curso_gen.getEstudiantes().get(x);

                Curso curso = new Curso();
                curso.setId_curso(curso_gen.getId_curso());
                curso.setId_gestion(Integer.parseInt(id_gestion));
                curso.setId_estudiante(estudiante.getId_estudiante());
                curso.setId_gestion(Integer.parseInt(id_gestion));
                curso = this.sigaa.getCursoByIdCursoAndIdEstudiantesAndIdGestion(curso);
                if (x == 0 || (x + 1) % 26 == 0) {
                    ficha++;
                    sheet = workbook.getSheet("Ficha" + ficha);
                    cont_v = 0;
                }
                cont_v++;
                int cont_vi = 0;
                for (int i = 0; i < curso.getMaterias().size() - 3; i++) {
                    Materia materia = (Materia) curso.getMaterias().get(i);
                    cont_vi++;
                    for (int j = 0; j < materia.getEvaluaciones().size(); j++) {
                        Evaluacion evaluacion = (Evaluacion) materia.getEvaluaciones().get(j);
                        if (j == 0) {
                            sheet.addCell(new jxl.write.Number(2, (V[cont_v] + V_int[cont_vi]), evaluacion.getNota() + evaluacion.getDps()));
                            sheet.addCell(new Label(3, (V[cont_v] + V_int[cont_vi]), evaluacion.getCualitativa_p(), arial10));
                        }
                        if (j == 1) {
                            sheet.addCell(new jxl.write.Number(7, (V[cont_v] + V_int[cont_vi]), evaluacion.getNota() + evaluacion.getDps()));
                            sheet.addCell(new Label(8, (V[cont_v] + V_int[cont_vi]), evaluacion.getCualitativa_p(), arial10));
                        }
                        if (j == 2) {
                            sheet.addCell(new jxl.write.Number(12, (V[cont_v] + V_int[cont_vi]), evaluacion.getNota() + evaluacion.getDps()));
                            sheet.addCell(new Label(13, (V[cont_v] + V_int[cont_vi]), evaluacion.getCualitativa_p(), arial10));
                        }
                    }
                }
                cont_vi++;
                for (int l = 0; l < curso.getFaltas().size(); l++) {
                    Falta falta = (Falta) curso.getFaltas().get(l);
                    if (l == 0) {
                        sheet.addCell(new jxl.write.Number(3, (V[cont_v] + V_int[cont_vi]), falta.getRetraso()));
                        sheet.addCell(new jxl.write.Number(4, (V[cont_v] + V_int[cont_vi]), falta.getFcl()));
                        sheet.addCell(new jxl.write.Number(5, (V[cont_v] + V_int[cont_vi]), falta.getFsl()));
                        sheet.addCell(new jxl.write.Number(6, (V[cont_v] + V_int[cont_vi]), 68));
                    }
                    if (l == 1) {
                        sheet.addCell(new jxl.write.Number(8, (V[cont_v] + V_int[cont_vi]), falta.getRetraso()));
                        sheet.addCell(new jxl.write.Number(9, (V[cont_v] + V_int[cont_vi]), falta.getFcl()));
                        sheet.addCell(new jxl.write.Number(10, (V[cont_v] + V_int[cont_vi]), falta.getFsl()));
                        sheet.addCell(new jxl.write.Number(11, (V[cont_v] + V_int[cont_vi]), 67));
                    }
                    if (l == 2) {
                        sheet.addCell(new jxl.write.Number(13, (V[cont_v] + V_int[cont_vi]), falta.getRetraso()));
                        sheet.addCell(new jxl.write.Number(14, (V[cont_v] + V_int[cont_vi]), falta.getFcl()));
                        sheet.addCell(new jxl.write.Number(15, (V[cont_v] + V_int[cont_vi]), falta.getFsl()));
                        sheet.addCell(new jxl.write.Number(16, (V[cont_v] + V_int[cont_vi]), 65));
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

    private String setGenerarLibretasPrimaria7(Curso curso_gen, String id_gestion) {
        String archivo = "2010_80730484_PRI_M_7_A.xls";
        try {
            String dirDestino = System.getenv("SIGAA_HOME") + "/documentos/libretas/report/PRIMARIA7/";
            String dirPlantilla = System.getenv("SIGAA_HOME") + "/documentos/libretas/plantillas/PRIMARIA7/2010_80730484_PRI_M_7_A.xls";
            Workbook plantilla = Workbook.getWorkbook(new File(dirPlantilla));
            WritableWorkbook workbook = Workbook.createWorkbook(new File(dirDestino + archivo), plantilla); // Excel 2003
            WritableFont arial_10 = new WritableFont(WritableFont.ARIAL, 10);
            WritableCellFormat arial10 = new WritableCellFormat(arial_10);
            WritableSheet sheet = workbook.getSheet("Ficha1");
            int V[] = new int[350];
            V[1] = 7;
            for (int ii = 2; ii <= 25; ii++) {
                V[ii] = V[ii - 1] + 25;
            }
            int V_int[] = new int[20];
            V_int[1] = 0; //+0
            V_int[2] = 2; //+2
            V_int[3] = 3; //+1
            V_int[4] = 4; //+1
            V_int[5] = 5; //+1
            V_int[6] = 6; //+1
            V_int[7] = 7; //+1
            V_int[8] = 8; //+1
            V_int[9] = 9; //+1
            V_int[10] = 10; //+1
            V_int[11] = 12; //+2
            V_int[12] = 15; //+3
            int ficha = 0;
            int cont_v = 0;
            for (int x = 0; x < curso_gen.getEstudiantes().size(); x++) {
                Estudiante estudiante = (Estudiante) curso_gen.getEstudiantes().get(x);

                Curso curso = new Curso();
                curso.setId_curso(curso_gen.getId_curso());
                curso.setId_gestion(Integer.parseInt(id_gestion));
                curso.setId_estudiante(estudiante.getId_estudiante());
                curso.setId_gestion(Integer.parseInt(id_gestion));
                curso = this.sigaa.getCursoByIdCursoAndIdEstudiantesAndIdGestion(curso);
                if (x == 0 || (x + 1) % 26 == 0) {
                    ficha++;
                    sheet = workbook.getSheet("Ficha" + ficha);
                    cont_v = 0;
                }
                cont_v++;
                int cont_vi = 0;
                for (int i = 0; i < curso.getMaterias().size() - 1; i++) {
                    Materia materia = (Materia) curso.getMaterias().get(i);
                    cont_vi++;
                    for (int j = 0; j < materia.getEvaluaciones().size(); j++) {
                        Evaluacion evaluacion = (Evaluacion) materia.getEvaluaciones().get(j);
                        if (j == 0) {
                            sheet.addCell(new jxl.write.Number(2, (V[cont_v] + V_int[cont_vi]), evaluacion.getNota() + evaluacion.getDps()));
                            sheet.addCell(new Label(3, (V[cont_v] + V_int[cont_vi]), evaluacion.getCualitativa_p(), arial10));
                        }
                        if (j == 1) {
                            sheet.addCell(new jxl.write.Number(7, (V[cont_v] + V_int[cont_vi]), evaluacion.getNota() + evaluacion.getDps()));
                            sheet.addCell(new Label(8, (V[cont_v] + V_int[cont_vi]), evaluacion.getCualitativa_p(), arial10));
                        }
                        if (j == 2) {
                            sheet.addCell(new jxl.write.Number(12, (V[cont_v] + V_int[cont_vi]), evaluacion.getNota() + evaluacion.getDps()));
                            sheet.addCell(new Label(13, (V[cont_v] + V_int[cont_vi]), evaluacion.getCualitativa_p(), arial10));
                        }
                    }
                }
                cont_vi++;
                for (int l = 0; l < curso.getFaltas().size(); l++) {
                    Falta falta = (Falta) curso.getFaltas().get(l);
                    if (l == 0) {
                        sheet.addCell(new jxl.write.Number(3, (V[cont_v] + V_int[cont_vi]), falta.getRetraso()));
                        sheet.addCell(new jxl.write.Number(4, (V[cont_v] + V_int[cont_vi]), falta.getFcl()));
                        sheet.addCell(new jxl.write.Number(5, (V[cont_v] + V_int[cont_vi]), falta.getFsl()));
                        sheet.addCell(new jxl.write.Number(6, (V[cont_v] + V_int[cont_vi]), 68));
                    }
                    if (l == 1) {
                        sheet.addCell(new jxl.write.Number(8, (V[cont_v] + V_int[cont_vi]), falta.getRetraso()));
                        sheet.addCell(new jxl.write.Number(9, (V[cont_v] + V_int[cont_vi]), falta.getFcl()));
                        sheet.addCell(new jxl.write.Number(10, (V[cont_v] + V_int[cont_vi]), falta.getFsl()));
                        sheet.addCell(new jxl.write.Number(11, (V[cont_v] + V_int[cont_vi]), 67));
                    }
                    if (l == 2) {
                        sheet.addCell(new jxl.write.Number(13, (V[cont_v] + V_int[cont_vi]), falta.getRetraso()));
                        sheet.addCell(new jxl.write.Number(14, (V[cont_v] + V_int[cont_vi]), falta.getFcl()));
                        sheet.addCell(new jxl.write.Number(15, (V[cont_v] + V_int[cont_vi]), falta.getFsl()));
                        sheet.addCell(new jxl.write.Number(16, (V[cont_v] + V_int[cont_vi]), 65));
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

    private String setGenerarLibretasPrimaria8(Curso curso_gen, String id_gestion) {
        String archivo = "2010_80730484_PRI_M_8_A.xls";
        try {
            String dirDestino = System.getenv("SIGAA_HOME") + "/documentos/libretas/report/PRIMARIA8/";
            String dirPlantilla = System.getenv("SIGAA_HOME") + "/documentos/libretas/plantillas/PRIMARIA8/2010_80730484_PRI_M_8_A.xls";
            Workbook plantilla = Workbook.getWorkbook(new File(dirPlantilla));
            WritableWorkbook workbook = Workbook.createWorkbook(new File(dirDestino + archivo), plantilla); // Excel 2003
            WritableFont arial_10 = new WritableFont(WritableFont.ARIAL, 10);
            WritableCellFormat arial10 = new WritableCellFormat(arial_10);
            WritableSheet sheet = workbook.getSheet("Ficha1");
            int V[] = new int[350];
            V[1] = 7;
            for (int ii = 2; ii <= 25; ii++) {
                V[ii] = V[ii - 1] + 25;
            }
            int V_int[] = new int[20];
            V_int[1] = 0; //+0
            V_int[2] = 2; //+2
            V_int[3] = 3; //+1
            V_int[4] = 4; //+1
            V_int[5] = 5; //+1
            V_int[6] = 6; //+1
            V_int[7] = 7; //+1
            V_int[8] = 8; //+1
            V_int[9] = 9; //+1
            V_int[10] = 10; //+1
            V_int[11] = 12; //+2
            V_int[12] = 15; //+3
            int ficha = 0;
            int cont_v = 0;
            for (int x = 0; x < curso_gen.getEstudiantes().size(); x++) {
                Estudiante estudiante = (Estudiante) curso_gen.getEstudiantes().get(x);

                Curso curso = new Curso();
                curso.setId_curso(curso_gen.getId_curso());
                curso.setId_gestion(Integer.parseInt(id_gestion));
                curso.setId_estudiante(estudiante.getId_estudiante());
                curso.setId_gestion(Integer.parseInt(id_gestion));
                curso = this.sigaa.getCursoByIdCursoAndIdEstudiantesAndIdGestion(curso);
                if (x == 0 || (x + 1) % 26 == 0) {
                    ficha++;
                    sheet = workbook.getSheet("Ficha" + ficha);
                    cont_v = 0;
                }
                cont_v++;
                int cont_vi = 0;
                for (int i = 0; i < curso.getMaterias().size() - 1; i++) {
                    Materia materia = (Materia) curso.getMaterias().get(i);
                    cont_vi++;
                    for (int j = 0; j < materia.getEvaluaciones().size(); j++) {
                        Evaluacion evaluacion = (Evaluacion) materia.getEvaluaciones().get(j);
                        if (j == 0) {
                            sheet.addCell(new jxl.write.Number(2, (V[cont_v] + V_int[cont_vi]), evaluacion.getNota() + evaluacion.getDps()));
                            sheet.addCell(new Label(3, (V[cont_v] + V_int[cont_vi]), evaluacion.getCualitativa_p(), arial10));
                        }
                        if (j == 1) {
                            sheet.addCell(new jxl.write.Number(7, (V[cont_v] + V_int[cont_vi]), evaluacion.getNota() + evaluacion.getDps()));
                            sheet.addCell(new Label(8, (V[cont_v] + V_int[cont_vi]), evaluacion.getCualitativa_p(), arial10));
                        }
                        if (j == 2) {
                            sheet.addCell(new jxl.write.Number(12, (V[cont_v] + V_int[cont_vi]), evaluacion.getNota() + evaluacion.getDps()));
                            sheet.addCell(new Label(13, (V[cont_v] + V_int[cont_vi]), evaluacion.getCualitativa_p(), arial10));
                        }
                        if (j == 3) {
                            if (evaluacion.getNota() > 0) {
                                sheet.addCell(new jxl.write.Number(18, (V[cont_v] + V_int[cont_vi]), evaluacion.getNota()));
                            }
                        }
                    }
                }
                cont_vi++;
                for (int l = 0; l < curso.getFaltas().size(); l++) {
                    Falta falta = (Falta) curso.getFaltas().get(l);
                    if (l == 0) {
                        sheet.addCell(new jxl.write.Number(3, (V[cont_v] + V_int[cont_vi]), falta.getRetraso()));
                        sheet.addCell(new jxl.write.Number(4, (V[cont_v] + V_int[cont_vi]), falta.getFcl()));
                        sheet.addCell(new jxl.write.Number(5, (V[cont_v] + V_int[cont_vi]), falta.getFsl()));
                        sheet.addCell(new jxl.write.Number(6, (V[cont_v] + V_int[cont_vi]), 68));
                    }
                    if (l == 1) {
                        sheet.addCell(new jxl.write.Number(8, (V[cont_v] + V_int[cont_vi]), falta.getRetraso()));
                        sheet.addCell(new jxl.write.Number(9, (V[cont_v] + V_int[cont_vi]), falta.getFcl()));
                        sheet.addCell(new jxl.write.Number(10, (V[cont_v] + V_int[cont_vi]), falta.getFsl()));
                        sheet.addCell(new jxl.write.Number(11, (V[cont_v] + V_int[cont_vi]), 67));
                    }
                    if (l == 2) {
                        sheet.addCell(new jxl.write.Number(13, (V[cont_v] + V_int[cont_vi]), falta.getRetraso()));
                        sheet.addCell(new jxl.write.Number(14, (V[cont_v] + V_int[cont_vi]), falta.getFcl()));
                        sheet.addCell(new jxl.write.Number(15, (V[cont_v] + V_int[cont_vi]), falta.getFsl()));
                        sheet.addCell(new jxl.write.Number(16, (V[cont_v] + V_int[cont_vi]), 65));
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

    private String setGenerarLibretasSecundaria1(Curso curso_gen, String id_gestion) {
        String archivo = "2010_80730484_SEC_M_1_A.xls";
        try {
            String dirDestino = System.getenv("SIGAA_HOME") + "/documentos/libretas/report/SECUNDARIA1/";
            String dirPlantilla = System.getenv("SIGAA_HOME") + "/documentos/libretas/plantillas/SECUNDARIA1/2010_80730484_SEC_M_1_A.xls";
            Workbook plantilla = Workbook.getWorkbook(new File(dirPlantilla));
            WritableWorkbook workbook = Workbook.createWorkbook(new File(dirDestino + archivo), plantilla); // Excel 2003
            WritableFont arial_10 = new WritableFont(WritableFont.ARIAL, 10);
            WritableCellFormat arial10 = new WritableCellFormat(arial_10);
            WritableSheet sheet = workbook.getSheet("Ficha1");
            int V[] = new int[350];
            V[1] = 7;
            for (int ii = 2; ii <= 25; ii++) {
                V[ii] = V[ii - 1] + 39;
            }
            int V_int[] = new int[20];
            V_int[1] = 0; //+0
            V_int[2] = 1; //+1
            V_int[3] = 3; //+2
            V_int[4] = 4; //+1
            V_int[5] = 7; //+3
            V_int[6] = 12; //+5
            V_int[7] = 13; //+1
            V_int[8] = 14; //+1
            V_int[9] = 15; //+1
            V_int[10] = 16; //+1
            V_int[11] = 17; //+1
            V_int[12] = 28; //+11
            V_int[13] = 29; //+1
            int ficha = 0;
            int cont_v = 0;
            for (int x = 0; x < curso_gen.getEstudiantes().size(); x++) {
                Estudiante estudiante = (Estudiante) curso_gen.getEstudiantes().get(x);

                Curso curso = new Curso();
                curso.setId_curso(curso_gen.getId_curso());
                curso.setId_gestion(Integer.parseInt(id_gestion));
                curso.setId_estudiante(estudiante.getId_estudiante());
                curso.setId_gestion(Integer.parseInt(id_gestion));
                curso = this.sigaa.getCursoByIdCursoAndIdEstudiantesAndIdGestion(curso);
                if (x == 0 || (x + 1) % 26 == 0) {
                    ficha++;
                    sheet = workbook.getSheet("Ficha" + ficha);
                    cont_v = 0;
                }
                cont_v++;
                int cont_vi = 0;
                int nota1 = 0;
                int nota2 = 0;
                int nota3 = 0;
                int dps1 = 0;
                int dps2 = 0;
                int dps3 = 0;
                for (int i = 0; i < curso.getMaterias().size() - 1; i++) {
                    Materia materia = (Materia) curso.getMaterias().get(i);
                    if (i == 3 || i == 4 || i == 5) {
                        if (i == 5) {
                            cont_vi++;
                        }
                        for (int j = 0; j < materia.getEvaluaciones().size(); j++) {
                            Evaluacion evaluacion = (Evaluacion) materia.getEvaluaciones().get(j);
                            if (j == 0) {
                                nota1 = nota1 + evaluacion.getNota();
                                dps1 = dps1 + evaluacion.getDps();
                                if (i == 5) {
                                    nota1 = (int) (Math.round(((double) nota1 / 3) * Math.pow(10, 0)) / Math.pow(10, 0));
                                    dps1 = (int) (Math.round(((double) dps1 / 3) * Math.pow(10, 0)) / Math.pow(10, 0));
                                    sheet.addCell(new jxl.write.Number(2, (V[cont_v] + V_int[cont_vi]), nota1));
                                    sheet.addCell(new jxl.write.Number(3, (V[cont_v] + V_int[cont_vi]), dps1));
                                }
                            }
                            if (j == 1) {
                                nota2 = nota2 + evaluacion.getNota();
                                dps2 = dps2 + evaluacion.getDps();
                                if (i == 5) {
                                    nota2 = (int) (Math.round(((double) nota2 / 3) * Math.pow(10, 0)) / Math.pow(10, 0));
                                    dps2 = (int) (Math.round(((double) dps2 / 3) * Math.pow(10, 0)) / Math.pow(10, 0));
                                    sheet.addCell(new jxl.write.Number(5, (V[cont_v] + V_int[cont_vi]), nota2));
                                    sheet.addCell(new jxl.write.Number(6, (V[cont_v] + V_int[cont_vi]), dps2));
                                }
                            }
                            if (j == 2) {
                                nota3 = nota3 + evaluacion.getNota();
                                dps3 = dps3 + evaluacion.getDps();
                                if (i == 5) {
                                    nota3 = (int) (Math.round(((double) nota3 / 3) * Math.pow(10, 0)) / Math.pow(10, 0));
                                    dps3 = (int) (Math.round(((double) dps3 / 3) * Math.pow(10, 0)) / Math.pow(10, 0));
                                    sheet.addCell(new jxl.write.Number(8, (V[cont_v] + V_int[cont_vi]), nota3));
                                    sheet.addCell(new jxl.write.Number(9, (V[cont_v] + V_int[cont_vi]), dps3)); 
                                }
                            }
                        }
                    } else {
                        cont_vi++;
                        for (int j = 0; j < materia.getEvaluaciones().size(); j++) {
                            Evaluacion evaluacion = (Evaluacion) materia.getEvaluaciones().get(j);
                            if (j == 0) {
                                sheet.addCell(new jxl.write.Number(2, (V[cont_v] + V_int[cont_vi]), evaluacion.getNota()));
                                sheet.addCell(new jxl.write.Number(3, (V[cont_v] + V_int[cont_vi]), evaluacion.getDps()));
                            }
                            if (j == 1) {
                                sheet.addCell(new jxl.write.Number(5, (V[cont_v] + V_int[cont_vi]), evaluacion.getNota()));
                                sheet.addCell(new jxl.write.Number(6, (V[cont_v] + V_int[cont_vi]), evaluacion.getDps()));
                            }
                            if (j == 2) {
                                sheet.addCell(new jxl.write.Number(8, (V[cont_v] + V_int[cont_vi]), evaluacion.getNota()));
                                sheet.addCell(new jxl.write.Number(9, (V[cont_v] + V_int[cont_vi]), evaluacion.getDps()));
                            }
                            if (j == 3) {
                                if (evaluacion.getNota() > 0) {
                                    sheet.addCell(new jxl.write.Number(12, (V[cont_v] + V_int[cont_vi]), evaluacion.getNota()));
                                }
                            }
                        }
                    }
                }
                cont_vi++;
                for (int l = 0; l < curso.getFaltas().size(); l++) {
                    Falta falta = (Falta) curso.getFaltas().get(l);
                    if (l == 0) {
                        sheet.addCell(new jxl.write.Number(2, (V[cont_v] + V_int[cont_vi]), falta.getRetraso()));
                        sheet.addCell(new jxl.write.Number(3, (V[cont_v] + V_int[cont_vi]), falta.getFcl()));
                        sheet.addCell(new jxl.write.Number(4, (V[cont_v] + V_int[cont_vi]), falta.getFsl()));
                    }
                    if (l == 1) {
                        sheet.addCell(new jxl.write.Number(5, (V[cont_v] + V_int[cont_vi]), falta.getRetraso()));
                        sheet.addCell(new jxl.write.Number(6, (V[cont_v] + V_int[cont_vi]), falta.getFcl()));
                        sheet.addCell(new jxl.write.Number(7, (V[cont_v] + V_int[cont_vi]), falta.getFsl()));
                    }
                    if (l == 2) {
                        sheet.addCell(new jxl.write.Number(8, (V[cont_v] + V_int[cont_vi]), falta.getRetraso()));
                        sheet.addCell(new jxl.write.Number(9, (V[cont_v] + V_int[cont_vi]), falta.getFcl()));
                        sheet.addCell(new jxl.write.Number(10, (V[cont_v] + V_int[cont_vi]), falta.getFsl()));
                    }
                }
                cont_vi++;
                sheet.addCell(new jxl.write.Number(3, (V[cont_v] + V_int[cont_vi]), 67));
                sheet.addCell(new jxl.write.Number(6, (V[cont_v] + V_int[cont_vi]), 68));
                sheet.addCell(new jxl.write.Number(9, (V[cont_v] + V_int[cont_vi]), 65));

            }
            workbook.write();
            workbook.close();
        } catch (Exception de) {
            de.printStackTrace();
        }
        return archivo;
    }

    private String setGenerarLibretasSecundaria2(Curso curso_gen, String id_gestion) {
        String archivo = "2010_80730484_SEC_M_2_A.xls";
        try {
            String dirDestino = System.getenv("SIGAA_HOME") + "/documentos/libretas/report/SECUNDARIA2/";
            String dirPlantilla = System.getenv("SIGAA_HOME") + "/documentos/libretas/plantillas/SECUNDARIA2/2010_80730484_SEC_M_2_A.xls";
            Workbook plantilla = Workbook.getWorkbook(new File(dirPlantilla));
            WritableWorkbook workbook = Workbook.createWorkbook(new File(dirDestino + archivo), plantilla); // Excel 2003
            WritableFont arial_10 = new WritableFont(WritableFont.ARIAL, 10);
            WritableCellFormat arial10 = new WritableCellFormat(arial_10);
            WritableSheet sheet = workbook.getSheet("Ficha1");
            int V[] = new int[350];
            V[1] = 7;
            for (int ii = 2; ii <= 25; ii++) {
                V[ii] = V[ii - 1] + 39;
            }
            int V_int[] = new int[20];
            V_int[1] = 0; //+0
            V_int[2] = 1; //+1
            V_int[3] = 2; //+1
            V_int[4] = 3; //+1
            V_int[5] = 4; //+1
            V_int[6] = 7; //+3
            V_int[7] = 12; //+5
            V_int[8] = 13; //+1
            V_int[9] = 14; //+1
            V_int[10] = 15; //+1
            V_int[11] = 16; //+1
            V_int[12] = 28; //+12
            V_int[13] = 29; //+1
            int ficha = 0;
            int cont_v = 0;
            for (int x = 0; x < curso_gen.getEstudiantes().size(); x++) {
                Estudiante estudiante = (Estudiante) curso_gen.getEstudiantes().get(x);

                Curso curso = new Curso();
                curso.setId_curso(curso_gen.getId_curso());
                curso.setId_gestion(Integer.parseInt(id_gestion));
                curso.setId_estudiante(estudiante.getId_estudiante());
                curso.setId_gestion(Integer.parseInt(id_gestion));
                curso = this.sigaa.getCursoByIdCursoAndIdEstudiantesAndIdGestion(curso);
                if (x == 0 || (x + 1) % 26 == 0) {
                    ficha++;
                    sheet = workbook.getSheet("Ficha" + ficha);
                    cont_v = 0;
                }
                cont_v++;
                int cont_vi = 0;
                int nota1 = 0;
                int nota2 = 0;
                int nota3 = 0;
                int dps1 = 0;
                int dps2 = 0;
                int dps3 = 0;
                for (int i = 0; i < curso.getMaterias().size() - 1; i++) {
                    Materia materia = (Materia) curso.getMaterias().get(i);
                    if (i == 4 || i == 5 || i == 6) {
                        if (i == 6) {
                            cont_vi++;
                        }
                        for (int j = 0; j < materia.getEvaluaciones().size(); j++) {
                            Evaluacion evaluacion = (Evaluacion) materia.getEvaluaciones().get(j);
                            if (j == 0) {
                                nota1 = nota1 + evaluacion.getNota();
                                dps1 = dps1 + evaluacion.getDps();
                                if (i == 6) {
                                    nota1 = (int) (Math.round(((double) nota1 / 3) * Math.pow(10, 0)) / Math.pow(10, 0));
                                    dps1 = (int) (Math.round(((double) dps1 / 3) * Math.pow(10, 0)) / Math.pow(10, 0));
                                    sheet.addCell(new jxl.write.Number(2, (V[cont_v] + V_int[cont_vi]), nota1));
                                    sheet.addCell(new jxl.write.Number(3, (V[cont_v] + V_int[cont_vi]), dps1));
                                }
                            }
                            if (j == 1) {
                                nota2 = nota2 + evaluacion.getNota();
                                dps2 = dps2 + evaluacion.getDps();
                                if (i == 6) {
                                    nota2 = (int) (Math.round(((double) nota2 / 3) * Math.pow(10, 0)) / Math.pow(10, 0));
                                    dps2 = (int) (Math.round(((double) dps2 / 3) * Math.pow(10, 0)) / Math.pow(10, 0));
                                    sheet.addCell(new jxl.write.Number(5, (V[cont_v] + V_int[cont_vi]), nota2));
                                    sheet.addCell(new jxl.write.Number(6, (V[cont_v] + V_int[cont_vi]), dps2));
                                }
                            }
                            if (j == 2) {
                                nota3 = nota3 + evaluacion.getNota();
                                dps3 = dps3 + evaluacion.getDps();
                                if (i == 6) {
                                    nota3 = (int) (Math.round(((double) nota3 / 3) * Math.pow(10, 0)) / Math.pow(10, 0));
                                    dps3 = (int) (Math.round(((double) dps3 / 3) * Math.pow(10, 0)) / Math.pow(10, 0));
                                    sheet.addCell(new jxl.write.Number(8, (V[cont_v] + V_int[cont_vi]), nota3));
                                    sheet.addCell(new jxl.write.Number(9, (V[cont_v] + V_int[cont_vi]), dps3));
                                }
                            }
                        }
                    } else {
                        cont_vi++;
                        for (int j = 0; j < materia.getEvaluaciones().size(); j++) {
                            Evaluacion evaluacion = (Evaluacion) materia.getEvaluaciones().get(j);
                            if (j == 0) {
                                sheet.addCell(new jxl.write.Number(2, (V[cont_v] + V_int[cont_vi]), evaluacion.getNota()));
                                sheet.addCell(new jxl.write.Number(3, (V[cont_v] + V_int[cont_vi]), evaluacion.getDps()));
                            }
                            if (j == 1) {
                                sheet.addCell(new jxl.write.Number(5, (V[cont_v] + V_int[cont_vi]), evaluacion.getNota()));
                                sheet.addCell(new jxl.write.Number(6, (V[cont_v] + V_int[cont_vi]), evaluacion.getDps()));
                            }
                            if (j == 2) {
                                sheet.addCell(new jxl.write.Number(8, (V[cont_v] + V_int[cont_vi]), evaluacion.getNota()));
                                sheet.addCell(new jxl.write.Number(9, (V[cont_v] + V_int[cont_vi]), evaluacion.getDps()));
                            }
                            if (j == 3) {
                                if (evaluacion.getNota() > 0) {
                                    sheet.addCell(new jxl.write.Number(12, (V[cont_v] + V_int[cont_vi]), evaluacion.getNota()));
                                }
                            }
                        }
                    }
                }
                cont_vi++;
                for (int l = 0; l < curso.getFaltas().size(); l++) {
                    Falta falta = (Falta) curso.getFaltas().get(l);
                    if (l == 0) {
                        sheet.addCell(new jxl.write.Number(2, (V[cont_v] + V_int[cont_vi]), falta.getRetraso()));
                        sheet.addCell(new jxl.write.Number(3, (V[cont_v] + V_int[cont_vi]), falta.getFcl()));
                        sheet.addCell(new jxl.write.Number(4, (V[cont_v] + V_int[cont_vi]), falta.getFsl()));
                    }
                    if (l == 1) {
                        sheet.addCell(new jxl.write.Number(5, (V[cont_v] + V_int[cont_vi]), falta.getRetraso()));
                        sheet.addCell(new jxl.write.Number(6, (V[cont_v] + V_int[cont_vi]), falta.getFcl()));
                        sheet.addCell(new jxl.write.Number(7, (V[cont_v] + V_int[cont_vi]), falta.getFsl()));
                    }
                    if (l == 2) {
                        sheet.addCell(new jxl.write.Number(8, (V[cont_v] + V_int[cont_vi]), falta.getRetraso()));
                        sheet.addCell(new jxl.write.Number(9, (V[cont_v] + V_int[cont_vi]), falta.getFcl()));
                        sheet.addCell(new jxl.write.Number(10, (V[cont_v] + V_int[cont_vi]), falta.getFsl()));
                    }
                }
                cont_vi++;
                sheet.addCell(new jxl.write.Number(3, (V[cont_v] + V_int[cont_vi]), 67));
                sheet.addCell(new jxl.write.Number(6, (V[cont_v] + V_int[cont_vi]), 68));
                sheet.addCell(new jxl.write.Number(9, (V[cont_v] + V_int[cont_vi]), 65));

            }
            workbook.write();
            workbook.close();
        } catch (Exception de) {
            de.printStackTrace();
        }
        return archivo;
    }

    private String setGenerarLibretasSecundaria4(Curso curso_gen, String id_gestion) {
        String archivo = "2010_80730484_SEC_M_4_A.xls";
        try {
            String dirDestino = System.getenv("SIGAA_HOME") + "/documentos/libretas/report/SECUNDARIA4/";
            String dirPlantilla = System.getenv("SIGAA_HOME") + "/documentos/libretas/plantillas/SECUNDARIA4/2010_80730484_SEC_M_4_A.xls";
            Workbook plantilla = Workbook.getWorkbook(new File(dirPlantilla));
            WritableWorkbook workbook = Workbook.createWorkbook(new File(dirDestino + archivo), plantilla); // Excel 2003
            WritableFont arial_10 = new WritableFont(WritableFont.ARIAL, 10);
            WritableCellFormat arial10 = new WritableCellFormat(arial_10);
            WritableSheet sheet = workbook.getSheet("Ficha1");
            int V[] = new int[350];
            V[1] = 7;
            for (int ii = 2; ii <= 25; ii++) {
                V[ii] = V[ii - 1] + 39;
            }
            int V_int[] = new int[20];
            V_int[1] = 0; //+0
            V_int[2] = 1; //+1
            V_int[3] = 2; //+1
            V_int[4] = 4; //+2
            V_int[5] = 5; //+1
            V_int[6] = 6; //+1
            V_int[7] = 8; //+2
            V_int[8] = 9; //+1
            V_int[9] = 10; //+1
            V_int[10] = 12; //+2
            V_int[11] = 13; //+1
            V_int[12] = 14; //+1
            V_int[13] = 15; //+1
            V_int[14] = 16; //+1
            V_int[15] = 28; //+12
            V_int[16] = 29; //+1
            int ficha = 0;
            int cont_v = 0;
            for (int x = 0; x < curso_gen.getEstudiantes().size(); x++) {
                Estudiante estudiante = (Estudiante) curso_gen.getEstudiantes().get(x);

                Curso curso = new Curso();
                curso.setId_curso(curso_gen.getId_curso());
                curso.setId_gestion(Integer.parseInt(id_gestion));
                curso.setId_estudiante(estudiante.getId_estudiante());
                curso.setId_gestion(Integer.parseInt(id_gestion));
                curso = this.sigaa.getCursoByIdCursoAndIdEstudiantesAndIdGestion(curso);
                if (x == 0 || (x + 1) % 26 == 0) {
                    ficha++;
                    sheet = workbook.getSheet("Ficha" + ficha);
                    cont_v = 0;
                }
                cont_v++;
                int cont_vi = 0;
                for (int i = 0; i < curso.getMaterias().size() - 1; i++) {
                    Materia materia = (Materia) curso.getMaterias().get(i);
                    cont_vi++;
                    for (int j = 0; j < materia.getEvaluaciones().size(); j++) {
                        Evaluacion evaluacion = (Evaluacion) materia.getEvaluaciones().get(j);
                        if (j == 0) {
                            sheet.addCell(new jxl.write.Number(2, (V[cont_v] + V_int[cont_vi]), evaluacion.getNota()));
                            sheet.addCell(new jxl.write.Number(3, (V[cont_v] + V_int[cont_vi]), evaluacion.getDps()));
                        }
                        if (j == 1) {
                            sheet.addCell(new jxl.write.Number(5, (V[cont_v] + V_int[cont_vi]), evaluacion.getNota()));
                            sheet.addCell(new jxl.write.Number(6, (V[cont_v] + V_int[cont_vi]), evaluacion.getDps()));
                        }
                        if (j == 2) {
                            sheet.addCell(new jxl.write.Number(8, (V[cont_v] + V_int[cont_vi]), evaluacion.getNota()));
                            sheet.addCell(new jxl.write.Number(9, (V[cont_v] + V_int[cont_vi]), evaluacion.getDps()));
                        }
                        if (j == 3) {
                            if (evaluacion.getNota() > 0) {
                                sheet.addCell(new jxl.write.Number(12, (V[cont_v] + V_int[cont_vi]), evaluacion.getNota()));
                            }
                        }
                    }

                }
                cont_vi++;
                for (int l = 0; l < curso.getFaltas().size(); l++) {
                    Falta falta = (Falta) curso.getFaltas().get(l);
                    if (l == 0) {
                        sheet.addCell(new jxl.write.Number(2, (V[cont_v] + V_int[cont_vi]), falta.getRetraso()));
                        sheet.addCell(new jxl.write.Number(3, (V[cont_v] + V_int[cont_vi]), falta.getFcl()));
                        sheet.addCell(new jxl.write.Number(4, (V[cont_v] + V_int[cont_vi]), falta.getFsl()));
                    }
                    if (l == 1) {
                        sheet.addCell(new jxl.write.Number(5, (V[cont_v] + V_int[cont_vi]), falta.getRetraso()));
                        sheet.addCell(new jxl.write.Number(6, (V[cont_v] + V_int[cont_vi]), falta.getFcl()));
                        sheet.addCell(new jxl.write.Number(7, (V[cont_v] + V_int[cont_vi]), falta.getFsl()));
                    }
                    if (l == 2) {
                        sheet.addCell(new jxl.write.Number(8, (V[cont_v] + V_int[cont_vi]), falta.getRetraso()));
                        sheet.addCell(new jxl.write.Number(9, (V[cont_v] + V_int[cont_vi]), falta.getFcl()));
                        sheet.addCell(new jxl.write.Number(10, (V[cont_v] + V_int[cont_vi]), falta.getFsl()));
                    }
                }
                cont_vi++;
                sheet.addCell(new jxl.write.Number(3, (V[cont_v] + V_int[cont_vi]), 67));
                sheet.addCell(new jxl.write.Number(6, (V[cont_v] + V_int[cont_vi]), 68));
                sheet.addCell(new jxl.write.Number(9, (V[cont_v] + V_int[cont_vi]), 65));

            }
            workbook.write();
            workbook.close();
        } catch (Exception de) {
            de.printStackTrace();
        }
        return archivo;
    }
}

