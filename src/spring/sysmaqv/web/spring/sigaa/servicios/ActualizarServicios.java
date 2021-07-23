/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.sysmaqv.web.spring.sigaa.servicios;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import spring.sysmaqv.domain.Gestion;
import spring.sysmaqv.domain.Personal;
import spring.sysmaqv.domain.Servicio;
import spring.sysmaqv.domain.logic.SigaaInterface;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.util.WebUtils;

/**
 * @author  : Marco Antonio Quenta Velasco
 * @Gestion : 2009
 * @LaPaz,Bolivia
 */
public class ActualizarServicios implements Controller {

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
            String id_gestion = request.getParameter("id_gestion");
            String id_servicio = request.getParameter("id_servicio");
            String opcion = request.getParameter("opcion");
            if (id_gestion != null) {
                retorno.put("id_gestion", id_gestion);
                retorno.put("tipo_anios", this.sigaa.getTipoAnios());
                //request.getParameter("opcion")
                if (id_servicio != null) {
                    if (opcion != null) {
                        String tipo_servicio = request.getParameter("tipo_servicio").toUpperCase();
                        String monto_servicio = request.getParameter("monto_servicio");
                        String anio = request.getParameter("anio");
                        if (!tipo_servicio.equals("") && !monto_servicio.equals("")) {
                            Servicio servicio= new Servicio();
                            servicio.setId_servicio(Integer.parseInt(id_servicio));
                            servicio.setTipo_servicio(tipo_servicio);
                            servicio.setMonto_servicio(Double.parseDouble(monto_servicio));
                            servicio.setId_gestion(Integer.parseInt(id_gestion));
                            this.sigaa.setActualizarServicio(servicio);
                            retorno.put("mensaje", true);
                        } else {
                            retorno.put("mensaje", false);
                        }
                    }
                    retorno.put("servicio", this.sigaa.getServicioByIdServicio(Integer.parseInt(id_servicio)));
                    return new ModelAndView(this.perfectView, retorno);
                }
                retorno.put("servicios", this.sigaa.getServicioByID(Integer.parseInt(id_gestion)));
                return new ModelAndView(this.perfectView, retorno);
            }
            Gestion gestion = this.sigaa.getGestionActivo();
            retorno.put("gestion", gestion);

//            if (request.getParameter("opcion") != null) {
//                String tipo_servicio = request.getParameter("tipo_servicio").toUpperCase();
//                String monto_servicio = request.getParameter("monto_servicio").toUpperCase();
//                String id_gestion = request.getParameter("id_gestion");
//                if (tipo_servicio.equals("") && monto_servicio.equals("")) {
//                    retorno.put("tipo_servicio", tipo_servicio);
//                    retorno.put("monto_servicio", monto_servicio);
//                    retorno.put("id_gestion", id_gestion);
//                    mensaje = "false";
//                } else {
//                    Servicio servicio = new Servicio();
//                    servicio.setTipo_servicio(tipo_servicio);
//                    servicio.setMonto_servicio(Double.parseDouble(monto_servicio));
//                    servicio.setId_gestion(Integer.parseInt(id_gestion));
//                    this.sigaa.setRegistrarServicio(servicio);
//                }
//
//                retorno.put("mensaje", mensaje);
//            }
            return new ModelAndView(this.perfectView, retorno);
        } else {
            return new ModelAndView(this.ecxessTimeView, "url", url);
        }
    }
}
