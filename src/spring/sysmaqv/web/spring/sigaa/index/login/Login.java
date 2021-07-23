package spring.sysmaqv.web.spring.sigaa.index.login;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spring.sysmaqv.domain.Administrativo;
import spring.sysmaqv.domain.Docente;
import spring.sysmaqv.domain.Estudiante;
import spring.sysmaqv.domain.Persona;
import spring.sysmaqv.domain.Personal;
import spring.sysmaqv.domain.logic.SigaaInterface;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.util.WebUtils;

/**
 * @author Marco Antonio Quenta Velasco
 * @since 16.6.2010
 */
public class Login implements Controller {

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
        /* extrae el personal logueado ... */
        Personal personal = (Personal) WebUtils.getSessionAttribute(request, "___personal");
        if (personal == null) {
            String id_personal = request.getParameter("login");
            String password = request.getParameter("password");
            String servletPath = request.getParameter("servletPath");
            String urlDestino = request.getParameter("url");
            personal=new Personal();
            personal.setId_personal(id_personal);
            personal.setClave(password);
            if (this.sigaa.personalActivo(personal)) {
                personal = this.sigaa.descripcionPersonal(personal); //descripcion del personal
                personal.setMenu(this.sigaa.getMenuPersonal(personal));
                if ("SYS".equals(personal.getId_tpersonal())) {
                    personal.setNombres("Sistemas");
                }
                if ("EST".equals(personal.getId_tpersonal())) {
                    Estudiante estudiante = this.sigaa.getEstudianteByID(personal.getId_personal());
                    Persona persona = this.sigaa.getPersonaById(estudiante.getId_persona());
                    personal.setDip(persona.getDip());
                    personal.setPaterno(persona.getPaterno());
                    personal.setMaterno(persona.getMaterno());
                    personal.setNombres(persona.getNombres());
                }
                if ("ADM".equals(personal.getId_tpersonal())) {
                    Administrativo administrativo = this.sigaa.getAdministrativoByID(personal.getId_personal());
                    Persona persona = this.sigaa.getPersonaById(administrativo.getId_persona());
                    personal.setDip(persona.getDip());
                    personal.setPaterno(persona.getPaterno());
                    personal.setMaterno(persona.getMaterno());
                    personal.setNombres(persona.getNombres());
                }
                if ("DOC".equals(personal.getId_tpersonal())) {
                    Docente docente = this.sigaa.getDocenteByID(personal.getId_personal());
                    Persona persona = this.sigaa.getPersonaById(docente.getId_persona());
                    personal.setDip(persona.getDip());
                    personal.setPaterno(persona.getPaterno());
                    personal.setMaterno(persona.getMaterno());
                    personal.setNombres(persona.getNombres());
                }
                request.getSession().setAttribute("___personal", personal);
                /* urlDestino = null, es si es un login normal desde la entrada principal 
                 * de lo contrario es una peticion por inactividad y renenviando ala direccion solicitada */
                if (urlDestino != null) {
                    response.sendRedirect(urlDestino);
                    return null;
                } else {
                    return new ModelAndView(this.perfectView, "personal", personal);
                }
            } else {
                if (urlDestino != null) {
                    Map retorno = new HashMap();
                    retorno.put("key", "failure");
                    retorno.put("url", servletPath);
                    return new ModelAndView("index/login/ErrorOfTimeOfSession", retorno);
                } else {
                    return new ModelAndView(this.ecxessTimeView, "key", "failure");
                }
            }
        } else {
            return new ModelAndView("system/ApplicationMainPage", null);
        }
    }
}
