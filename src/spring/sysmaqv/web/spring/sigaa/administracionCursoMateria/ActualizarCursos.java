/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.sysmaqv.web.spring.sigaa.administracionCursoMateria;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import spring.sysmaqv.domain.Curso;
import spring.sysmaqv.domain.Gestion;
import spring.sysmaqv.domain.Personal;
import spring.sysmaqv.domain.logic.SigaaInterface;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.util.WebUtils;

/**
 *
 * @author Marco Antonio
 */
public class ActualizarCursos implements Controller {

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
            String opcion = request.getParameter("opcion");
            String search = request.getParameter("search");
            String id_curso = request.getParameter("id_curso");
            if (id_curso != null) {
                String opcion1 = request.getParameter("opcion1");
                if (opcion1 == null) {
                    List vi = this.sigaa.getBuscarCurso(id_curso);
                    Curso visualizar = (Curso) vi.get(0);
                    retorno.put("visualizar", visualizar);
                    retorno.put("cursos", "cursos");
                    return new ModelAndView(this.perfectView, retorno);
                } else {
                    try {
                        String curso = request.getParameter("curso");
                        String ciclo = request.getParameter("ciclo");
                        Curso cur = new Curso();
                        cur.setId_curso(id_curso);
                        cur.setCurso(curso);
                        cur.setCiclo(ciclo);
                        this.sigaa.setActualizarCurso(cur);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
            if (opcion != null) {
                retorno.put("curso", this.sigaa.getBuscarCurso(search.toUpperCase()));
                retorno.put("search", search.toUpperCase());
            } else {
                Gestion gestion = this.sigaa.getGestionActivo();
                retorno.put("curso", this.sigaa.getListaCursos(gestion.getId_gestion()));
            }
            return new ModelAndView(this.perfectView, retorno);
        } else {
            return new ModelAndView(this.ecxessTimeView, "url", url);
        }
    }
}