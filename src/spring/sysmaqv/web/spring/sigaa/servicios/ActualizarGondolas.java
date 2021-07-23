/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.sysmaqv.web.spring.sigaa.servicios;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import spring.sysmaqv.domain.Gondola;
import spring.sysmaqv.domain.Personal;
import spring.sysmaqv.domain.logic.SigaaInterface;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.util.WebUtils;

/**
 *
 * @author Marco Antonio
 */
public class ActualizarGondolas implements Controller {

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
            String opcion = request.getParameter("opcion");
            String id_gondola = request.getParameter("id_gondola");
            if (id_gondola != null) {
                if (opcion != null) {
                    String placa = request.getParameter("placa").toUpperCase();
                    String conductor = request.getParameter("conductor").toUpperCase();
                    String ruta = request.getParameter("ruta").toUpperCase();
                    String color = request.getParameter("color").toUpperCase();
                    String empresa = request.getParameter("empresa").toUpperCase();
                    String nro_gondola = request.getParameter("nro_gondola");
                    System.out.println("_____ "+placa);System.out.println("_____ "+nro_gondola);
                    if (!placa.equals("") && !nro_gondola.equals("")) {
                        Gondola gondola = new Gondola();
                        gondola.setId_gondola(id_gondola);
//                        gondola.setPlaca(placa);
//                        gondola.setNro_gondola(Integer.parseInt(nro_gondola));
                        gondola.setConductor(conductor);
                        gondola.setRuta(ruta);
                        gondola.setColor(color);
                        gondola.setEmpresa(empresa);
                        this.sigaa.setActualizarGondola(gondola);
                        retorno.put("mensaje", true);
                    } else {
                        retorno.put("mensaje", false);
                    }
                }
                Gondola gondola = this.sigaa.getGondolaByIdGondola(id_gondola);
                retorno.put("gondola", gondola);
                return new ModelAndView(this.perfectView, retorno);
            }
            retorno.put("gondolas", this.sigaa.getListaGondolas());
            return new ModelAndView(this.perfectView, retorno);
        } else {
            return new ModelAndView(this.ecxessTimeView, "url", url);
        }
    }
}
