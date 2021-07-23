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
 * @author CASA
 */
public class RegistroGondolas implements Controller {

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

                String placa = request.getParameter("placa").toUpperCase();
                String nro_gondola = request.getParameter("nro_gondola");
                String conductor = request.getParameter("conductor").toUpperCase();
                String ruta = request.getParameter("ruta").toUpperCase();
                String color = request.getParameter("color").toUpperCase();
                String empresa = request.getParameter("empresa").toUpperCase();

                if (placa.equals("") && nro_gondola.equals("")) {
                    retorno.put("placa", placa);
                    retorno.put("nro_gondola", nro_gondola);
                    retorno.put("conductor", conductor);
                    retorno.put("ruta", ruta);
                    retorno.put("color", color);
                    retorno.put("empresa", empresa);
                    mensaje = "false";
                } else {
                    Gondola gondola = new Gondola();
                    gondola.setId_gondola(nro_gondola + "-" + placa);
                    gondola.setPlaca(placa);
                    gondola.setNro_gondola(Integer.parseInt(nro_gondola));
                    gondola.setConductor(conductor);
                    gondola.setRuta(ruta);
                    gondola.setColor(color);
                    gondola.setEmpresa(empresa);
                    this.sigaa.setRegistrarGondola(gondola);
                }
                retorno.put("mensaje", mensaje);
            }
            return new ModelAndView(this.perfectView, retorno);
        } else {
            return new ModelAndView(this.ecxessTimeView, "url", url);
        }
    }
}
