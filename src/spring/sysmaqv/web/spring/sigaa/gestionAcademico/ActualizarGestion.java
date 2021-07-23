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
public class ActualizarGestion implements Controller {

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
            String id_gestion = request.getParameter("id_gestion");
            String guardar = request.getParameter("guardar");
            Date fecha = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            int anio_actual = Integer.parseInt(sdf.format(fecha));
            retorno.put("anio_actual", anio_actual);
            if (id_gestion != null) {
                if (guardar != null) {
                    String colegio = request.getParameter("colegio").toUpperCase();
                    String direccion = request.getParameter("direccion");
                    String directora = request.getParameter("id_administrativo");
                    String telefono = request.getParameter("telefono");
                    String fax = request.getParameter("fax");
                    String lema = request.getParameter("lema");
                    String mail = request.getParameter("mail");
                    try {
                        Gestion gestion = new Gestion();
                        gestion.setId_gestion(Integer.parseInt(id_gestion));
                        gestion.setColegio(colegio);
                        gestion.setDireccion(direccion);
                        gestion.setDirectora(directora);
                        gestion.setTelefono(telefono);
                        gestion.setFax(fax);
                        gestion.setLema(lema);
                        gestion.setMail(mail);
                        this.sigaa.setActualizarGestionAcademica(gestion);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                List gest = this.sigaa.getBuscarGestion(Integer.parseInt(id_gestion));
                Gestion gestion = (Gestion) gest.get(0);
                retorno.put("gestion", gestion);
                retorno.put("tipo_dias", this.sigaa.getTipoDias());
                retorno.put("tipo_meses", this.sigaa.getTipoMeses());
                retorno.put("tipo_anios", this.sigaa.getTipoAnios());
                retorno.put("directores", this.sigaa.getAdministradorById_cargo("DIR"));
                retorno.put("pensiones", this.sigaa.getTiposPensionesByIdGestion(Integer.parseInt(id_gestion)));
                retorno.put("servicios", this.sigaa.getServicioByID(Integer.parseInt(id_gestion)));
                return new ModelAndView(this.perfectView, retorno);

            }
            if (search != null) {
                retorno.put("search", search);
                retorno.put("gestiones", this.sigaa.getBuscarGestion(Integer.parseInt(search)));
            } else {
                retorno.put("gestiones", this.sigaa.getListaGestiones());
            }
            return new ModelAndView(this.perfectView, retorno);
        } else {
            return new ModelAndView(this.ecxessTimeView, "url", url);
        }
    }
}

