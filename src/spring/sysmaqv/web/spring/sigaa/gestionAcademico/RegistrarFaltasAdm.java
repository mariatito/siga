/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.sysmaqv.web.spring.sigaa.gestionAcademico;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import spring.sysmaqv.domain.Estudiante;
import spring.sysmaqv.domain.Falta;
import spring.sysmaqv.domain.Gestion;
import spring.sysmaqv.domain.Personal;
import spring.sysmaqv.domain.logic.SigaaInterface;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.util.WebUtils;
import spring.sysmaqv.domain.Curso;
import spring.sysmaqv.domain.PeriodoCurso;

/**
 * @ Company : M&M
 * @ Author : Marco Antonio Quenta Velasco
 * @ Gestion : 2010
 * @ La Paz - Bolivia
 */
public class RegistrarFaltasAdm implements Controller {

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
            String id_curso = request.getParameter("id_curso");
            String opcion = request.getParameter("opcion");
            Gestion gestion = this.sigaa.getGestionActivo();
            retorno.put("id_gestion", gestion.getId_gestion());
            if (id_curso != null) {
                Date fechaActual=new Date();
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                retorno.put("fechaActual",formato.format(fechaActual));
                Curso cur = new Curso();
                cur.setId_curso(id_curso);
                cur.setId_gestion(gestion.getId_gestion());
                Curso curso = this.sigaa.getCursoByIdCursoAndIdGestion(cur);
                  System.out.println("ssssss"+curso);
                if (opcion != null) {
                    if (opcion.equals("_update")) {
                        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                        String fechaFalta=request.getParameter("fechaFalta");                        
                        retorno.put("fechaActual",fechaFalta);
                        fechaFalta=fechaFalta.replace("/", "-");
                        Date fecha_falta = format.parse(fechaFalta);
                        for (int i = 0; i < curso.getEstudiantes().size(); i++) {
                            Estudiante estudiante = (Estudiante) curso.getEstudiantes().get(i);
                            String tipo_id_falta = request.getParameter("falta-" + estudiante.getId_estudiante());
                            if (tipo_id_falta != null) {
                                String tipo_falta = tipo_id_falta.substring(0, 3);
                                int id_falta = Integer.parseInt(tipo_id_falta.substring(4, tipo_id_falta.length()));
                                Falta f = this.sigaa.getFaltaByIdFalta(id_falta);// las sumas en la consulta
                                Falta falta = new Falta();
                                falta.setId_falta(id_falta);
                                falta.setFcl(f.getFcl());
                                falta.setFsl(f.getFsl());
                                falta.setRetraso(f.getRetraso());
                                falta.setUniforme(f.getUniforme());
                                if (tipo_falta.equals("fcl")) {
                                    falta.setTipo("fcl");
                                    falta.setFcl(f.getFcl() + 1);
                                } else if (tipo_falta.equals("fsl")) {
                                    falta.setTipo("fsl");
                                    falta.setFsl(f.getFsl() + 1);
                                } else if (tipo_falta.equals("ret")) {
                                    falta.setTipo("ret");
                                    falta.setRetraso(f.getRetraso() + 1);
                                }                                
                                falta.setId_usuario_reg(personal.getId_personal());
                                falta.setId_fecha_falta(0);
                                falta.setFecha_falta(fecha_falta);
                                this.sigaa.getUpdateFaltasByIdEstudianteAndIdEva(falta);
                            }
                            
                            String sinUniforme=request.getParameter("sunif-" + estudiante.getId_estudiante());
                            if (sinUniforme != null) {
                                String tipo_falta = sinUniforme.substring(0, 3);
                                int id_falta = Integer.parseInt(sinUniforme.substring(4, sinUniforme.length()));
                                Falta f = this.sigaa.getFaltaByIdFalta(id_falta);// las sumas en la consulta
                                Falta falta = new Falta();
                                falta.setId_falta(id_falta);
                                falta.setFcl(f.getFcl());
                                falta.setFsl(f.getFsl());
                                falta.setRetraso(f.getRetraso());
                                falta.setUniforme(f.getUniforme());
                                
                                 //  System.out.println("ssssss"+sinUniforme);
                                if (tipo_falta.equals("sun")) {
                                //   System.out.println("holszdsd"+(f.getUniforme() + 1));
                                    falta.setTipo("sun");
                                    falta.setUniforme(f.getUniforme() + 1);
                                }                               
                                falta.setId_usuario_reg(personal.getId_personal());
                                falta.setId_fecha_falta(0);
                                falta.setFecha_falta(fecha_falta);
                                this.sigaa.getUpdateFaltasByIdEstudianteAndIdEva(falta);
                            }
                        }
                       // return new ModelAndView(this.perfectView, retorno);
                    }
                    if (opcion.equals("_updateFalta")) {
                        try {
                            String id_estudiante = request.getParameter("id_estudiante");
                            String id_fecha_falta = request.getParameter("id_fecha_falta");
                            Estudiante estudiante = this.sigaa.getEstudianteByIdEstudiante(id_estudiante);
                            curso = (Curso) this.sigaa.getBuscarCurso(id_curso).get(0);
                            retorno.put("curso", curso);
                            retorno.put("estudiante", estudiante);
                            if (id_fecha_falta != null) {
                                Falta falta = this.sigaa.getFechaFaltaById(Integer.parseInt(id_fecha_falta));
                                retorno.put("falta", falta);
                                if (request.getParameter("guardar") != null) {
                                    String tipo_falta = request.getParameter("tipo_falta");
                                    String post_tipo = request.getParameter("post_tipo");
                                    int id_falta = Integer.parseInt(request.getParameter("id_falta"));
                                    Falta f = this.sigaa.getFaltaByIdFalta(id_falta);
                                    Falta falt = new Falta();
                                    falt.setId_falta(id_falta);

                                    int fcl = f.getFcl();
                                    int fsl = f.getFsl();
                                    int ret = f.getRetraso();
                                    if (post_tipo.equals("fcl")) {
                                        fcl = fcl - 1;
                                    } else if (post_tipo.equals("fsl")) {
                                        fsl = fsl - 1;
                                    } else if (post_tipo.equals("ret")) {
                                        ret = ret - 1;
                                    }
                                    if (tipo_falta.equals("fcl")) {
                                        falt.setTipo("fcl");
                                        fcl = fcl + 1;
                                    } else if (tipo_falta.equals("fsl")) {
                                        falt.setTipo("fsl");
                                        fsl = fsl + 1;
                                    } else if (tipo_falta.equals("ret")) {
                                        falt.setTipo("ret");
                                        ret = ret + 1;
                                    }
                                    falt.setFcl(fcl);
                                    falt.setFsl(fsl);
                                    falt.setRetraso(ret);
                                    falt.setId_fecha_falta(Integer.parseInt(id_fecha_falta));
                                    falt.setId_usuario_modific(personal.getId_personal());
                                    this.sigaa.getUpdateFaltasByIdEstudianteAndIdEva(falt);
                                    retorno.put("falta", null);
                                }
                                if (request.getParameter("eliminar") != null) {
                                    falta.setId_estudiante(id_estudiante);
                                    this.sigaa.setDeleteFaltaByIdFaltaAndTipo(falta);
                                    retorno.put("falta", null);
                                }
                            }
                            Falta falta = new Falta();
                            falta.setId_estudiante(id_estudiante);
                            falta.setId_gestion(gestion.getId_gestion());
                            List faltas = this.sigaa.getFaltasByIdestudianteAndIdGestion(falta);
                            retorno.put("faltas", faltas);
                            return new ModelAndView(this.perfectView + "Update", retorno);
                        } catch (Exception e) {
                            e.printStackTrace();
                            return new ModelAndView(this.perfectView + "Update", retorno);
                        }
                    }
                }
                curso = this.sigaa.getCursoByIdCursoAndIdGestion(cur);
                PeriodoCurso periodocurso = this.sigaa.getPeriodoByIdcursoAndGestion(cur);
                String idperiodo = Integer.toString(periodocurso.getIdperiodo());
                int periodo = Integer.parseInt(idperiodo.substring(4, 5));
                retorno.put("periodo", periodo);
                retorno.put("curso", curso);
            } else {
                retorno.put("cursos", this.sigaa.getListaCursos(gestion.getId_gestion()));
            }

            return new ModelAndView(this.perfectView, retorno);
        } else {
            return new ModelAndView(this.ecxessTimeView, "url", url);
        }
    }
}
