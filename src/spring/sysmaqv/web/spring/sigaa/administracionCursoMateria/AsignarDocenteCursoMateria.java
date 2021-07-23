/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.sysmaqv.web.spring.sigaa.administracionCursoMateria;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import spring.sysmaqv.domain.CursoMateria;
import spring.sysmaqv.domain.Personal;
import spring.sysmaqv.domain.logic.SigaaInterface;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.util.WebUtils;

/**
 *
 * @author Marco Antonio
 */
public class AsignarDocenteCursoMateria implements Controller {

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
            String id_cursoTutor = request.getParameter("id_cursoTutor");
            String id_materiaCurso = request.getParameter("id_materiaCurso");
            String id_gestion = request.getParameter("id_gestion");
            String id_docente_regfaltas = request.getParameter("id_docente_regfaltas");
            String id_cursoCoo = request.getParameter("id_cursoCoo");
            if (id_gestion != null) {
                if (id_cursoTutor != null) {
                    String tutor = request.getParameter("tutor");
                    if (tutor != null) {
                        CursoMateria cursomateria = new CursoMateria();
                        cursomateria.setId_curso(id_cursoTutor);
                        cursomateria.setId_docente_tutor(tutor);
                        cursomateria.setId_gestion(Integer.parseInt(id_gestion));
                        this.sigaa.setAsignarDocenteTutorCurso(cursomateria);
                    }
                }
                 if (id_cursoCoo != null) {
                    // Systemout.println("id_cursoCoo");
                     String coordinador = request.getParameter("coordinador");
                    if (coordinador != null) {

                        CursoMateria cursomateria = new CursoMateria();
                        cursomateria.setId_curso(id_cursoCoo);
                        cursomateria.setId_docente_coordinador(coordinador);
                        cursomateria.setId_gestion(Integer.parseInt(id_gestion));
                        this.sigaa.setAsignarDocenteCoordinadorCurso(cursomateria);
                    }
                }
                if (id_materiaCurso != null) {
                    String docmat = request.getParameter("docmat");
                    if (docmat != null) {
                        CursoMateria cursomateria = new CursoMateria();
                        cursomateria.setId_curso_materia(id_materiaCurso);
                        cursomateria.setId_docente(docmat);
                        cursomateria.setId_gestion(Integer.parseInt(id_gestion));
                        this.sigaa.setAsignarDocenteMateria(cursomateria);
                    }
                }
                if (id_docente_regfaltas != null) {
                    String id_curso = request.getParameter("id_curso");
                    CursoMateria cursomateria = new CursoMateria();
                    cursomateria.setId_curso(id_curso);
                    cursomateria.setId_docente_regfaltas(id_docente_regfaltas);
                    cursomateria.setId_gestion(Integer.parseInt(id_gestion));
                    this.sigaa.setAsignarDocenteRegFaltasCurso(cursomateria);
                }
                retorno.put("cursos", this.sigaa.getListaCursos(Integer.parseInt(id_gestion)));
                retorno.put("materias", this.sigaa.getListaMaterias());
                retorno.put("docentes", this.sigaa.getListaDocentes());
                retorno.put("cursoMaterias", this.sigaa.getListaCursoMateriasByIdGestion(Integer.parseInt(id_gestion)));
                retorno.put("id_gestion", id_gestion);
                return new ModelAndView(this.perfectView, retorno);
            }
            retorno.put("gestion", this.sigaa.getGestionActivo());
            return new ModelAndView(this.perfectView, retorno);
        } else {
            return new ModelAndView(this.ecxessTimeView, "url", url);
        }
    }
}
