package spring.sysmaqv.web.spring.sigaa.adminPersonal;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spring.sysmaqv.domain.Administrativo;
import spring.sysmaqv.domain.Docente;
import spring.sysmaqv.domain.Persona;
import spring.sysmaqv.domain.Personal;
import spring.sysmaqv.domain.logic.SigaaInterface;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.util.WebUtils;

/**
 * @author Marco Antonio Quenta Velasco
 * @since 14.5.2009
 */
public class BuscarPersona implements Controller {

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
            String search = request.getParameter("search");
            String param = request.getParameter("param");
            String id_persona = request.getParameter("id_persona");
            if (id_persona != null) {
                String opcion = request.getParameter("opcion");
                if (opcion == null) {
                    Persona persona = this.sigaa.getPersonaById(id_persona);
                    retorno.put("persona", persona);
                    retorno.put("tipos_sexos", this.sigaa.getTiposSexos());
                    retorno.put("cargos_academicos", this.sigaa.getCargosAcademicos());
                    retorno.put("titulos_profesionales", this.sigaa.getTiposTitulos());
                    retorno.put("tipos_cargos", this.sigaa.getTiposCargos());
                    retorno.put("tipos_categorias", this.sigaa.getTiposCategorias());
                    if (persona.getId_tipo_usuario().equals("ADM")) {
                        Administrativo administrativo = this.sigaa.getAdministrativoByIdPersona(persona.getId_persona());
                        retorno.put("administrativo", administrativo);
                        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy"); 
                        String fec_asum = formato.format(administrativo.getFecha_asume());
                        retorno.put("fec_asum", fec_asum);
                    }
                    if (persona.getId_tipo_usuario().equals("DOC")) {
                        Docente docente = this.sigaa.getDocenteByIdPersona(persona.getId_persona());
                        retorno.put("docente", docente);
                    }
                    return new ModelAndView(this.perfectView, retorno);
                } else {
                    if (opcion.equals("_delete")) {
                        String mensaje = "true";
                        try {
                            Persona persona = this.sigaa.getPersonaById(id_persona);
                            this.sigaa.setDeletePersona(persona);
                        } catch (Exception e) {
                            e.printStackTrace();
                            mensaje = "false";
                        }
                        retorno.put("mensaje", mensaje);
                        List resultados = this.sigaa.getListaPersonas();
                        retorno.put("resultados", resultados);
                        return new ModelAndView(this.perfectView, retorno);
                    }
                }
            }
            if (search != null) {
                List resultados = this.sigaa.getBuscarPersona(search);
                retorno.put("resultados", resultados);
                retorno.put("search", search);
                return new ModelAndView(this.perfectView, retorno);
            }
            if (search == null) {
                List resultados = this.sigaa.getListaPersonas();
                retorno.put("resultados", resultados);
            }
            if (param != null) {
                String mensaje = "true";
                try {
                    Persona persona = this.sigaa.getPersonaById(param);
                    this.sigaa.setDeletePersona(persona);
                } catch (Exception e) {
                    e.printStackTrace();
                    mensaje = "false";
                }
                retorno.put("mensaje", mensaje);
                 List resultados = this.sigaa.getListaPersonas();
                retorno.put("resultados", resultados);
                return new ModelAndView(this.perfectView, retorno);
            }
            return new ModelAndView(this.perfectView, retorno);
        } else {
            return new ModelAndView(this.ecxessTimeView, "url", url);
        }
    }
}
