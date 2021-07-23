/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.sysmaqv.web.spring.sigaa.orientacion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import spring.sysmaqv.domain.ChasideRespuesta;
import spring.sysmaqv.domain.Gestion;
import spring.sysmaqv.domain.Inscripcion;
import spring.sysmaqv.domain.Objeto;
import spring.sysmaqv.domain.Personal;
import spring.sysmaqv.domain.logic.SigaaInterface;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.util.WebUtils;

/**
 *
 * @author MARCO
 */
public class TestChaside implements Controller {

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
            List preguntas_chaside = this.sigaa.getPreguntasChaside();
            String evaluacion = request.getParameter("evaluacion");
            String nuevo = request.getParameter("nuevo");
            Gestion gestion = this.sigaa.getGestionActivo();
            Inscripcion inscripcion = new Inscripcion();
            inscripcion.setId_estudiante(personal.getId_personal());
            inscripcion.setId_gestion(gestion.getId_gestion());
            inscripcion = this.sigaa.getEstudianteByIdEstudianteAndGestion(inscripcion);
            if (nuevo != null) {
                retorno.put("preguntas_chaside", preguntas_chaside);
                return new ModelAndView(this.perfectView, retorno);
            }
            if (evaluacion != null) {
                for (int i = 0; i < preguntas_chaside.size(); i++) {
                    Objeto objeto = (Objeto) preguntas_chaside.get(i);
                    String res = request.getParameter("res-" + objeto.getId());
                    String id_chaside = request.getParameter("id_chaside-" + objeto.getId());
                    ChasideRespuesta chasiderespuesta = new ChasideRespuesta();
                    chasiderespuesta.setId_estudiante(inscripcion.getId_estudiante());
                    chasiderespuesta.setId_gestion(inscripcion.getId_gestion());
                    chasiderespuesta.setId_curso(inscripcion.getId_curso());
                    chasiderespuesta.setId_chaside_pregunta(objeto.getId());
                    chasiderespuesta.setRespuesta("no");
                    if (res != null) {
                        chasiderespuesta.setRespuesta("si");
                    }
                    chasiderespuesta.setId_chaside(id_chaside);
                    if (inscripcion.getChasideRespuestas().size() > 0) {
                        this.sigaa.setActualizarChasideRespuesta(chasiderespuesta);
                    } else {
                        this.sigaa.setRegistrarChasideRespuesta(chasiderespuesta); 
                    }
                }
            }
            inscripcion = this.sigaa.getEstudianteByIdEstudianteAndGestion(inscripcion);
            if (inscripcion.getChasideRespuestas().size() > 0) {
                List intereses = this.sigaa.getInteresesByIdEstudianteAndIdGestion(inscripcion);
                retorno.put("intereses", intereses);
                List aptitudes = this.sigaa.getAptitudesByIdEstudianteAndIdGestion(inscripcion);
                retorno.put("aptitudes", aptitudes);
                retorno.put("resultados", "_resultados");
                return new ModelAndView(this.perfectView, retorno);
            }
            retorno.put("preguntas_chaside", preguntas_chaside);
            return new ModelAndView(this.perfectView, retorno);
        } else {
            return new ModelAndView(this.ecxessTimeView, "url", url);
        }
    }
}
