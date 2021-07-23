/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.sysmaqv.web.spring.sigaa.administracionCursoMateria;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import spring.sysmaqv.domain.Curso;
import spring.sysmaqv.domain.Personal;
import spring.sysmaqv.domain.logic.SigaaInterface;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.util.WebUtils;

/**
 *
 * @author Marco Antonio
 */
public class RegistroCursos implements Controller {

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
            String mensaje = "true";
            if (request.getParameter("opcion") != null) {

                String id_curso = request.getParameter("id_curso").toUpperCase();
                String curso = request.getParameter("curso");
                String ciclo= request.getParameter("ciclo");
               
                if (id_curso.equals("")) {
                    retorno.put("id_curso", id_curso);
                    retorno.put("curso", curso);
                    retorno.put("ciclo", ciclo);
                    mensaje = "false";
                } else {
                   Curso curs = new Curso();
                    curs.setId_curso(id_curso);
                    curs.setCurso(curso);
                    curs.setCiclo(ciclo);
                   

                    this.sigaa.setRegistrarCurso(curs);
                }

                retorno.put("mensaje", mensaje);


            }
//            retorno.put("gestiones", this.sigaa.getTiposGestiones());
            return new ModelAndView(this.perfectView, retorno);
        } else {
            return new ModelAndView(this.ecxessTimeView, "url", url);
        }
    }
}