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
public class EliminarCursos implements Controller {

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
            String param = request.getParameter("param");
            String id_curso = request.getParameter("id_curso");
            String opcion1 = request.getParameter("opcion1");
            String mensaje = "true";
            if (param != null) {
                try {
                    this.sigaa.setEliminarCurso(param);
                    mensaje = "true";
                } catch (Exception e) {
                    e.printStackTrace();
                    mensaje = "false";
                }
                retorno.put("mensaje", mensaje);
            }
            if (id_curso != null) {
                if (opcion1 == null) {
                    List vi = this.sigaa.getBuscarCurso(id_curso);
                    Curso visualizar = (Curso) vi.get(0);
                    retorno.put("visualizar", visualizar);
                    retorno.put("cursos", "curs");
                    return new ModelAndView(this.perfectView, retorno);
                } else {
                    try {
                        this.sigaa.setEliminarCurso(id_curso);
                        mensaje = "true";
                    } catch (Exception e) {
                        e.printStackTrace();
                        mensaje = "false";
                    }
                    retorno.put("mensaje", mensaje);
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


