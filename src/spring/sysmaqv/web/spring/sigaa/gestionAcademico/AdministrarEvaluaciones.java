/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.sysmaqv.web.spring.sigaa.gestionAcademico;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import spring.sysmaqv.domain.Fecha_regnota;
import spring.sysmaqv.domain.Personal;
import spring.sysmaqv.domain.logic.SigaaInterface;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.util.WebUtils;

/**
 *
 * @author marco
 */
public class AdministrarEvaluaciones implements Controller {

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
            String id_eva = request.getParameter("id_eva");
            String save = request.getParameter("save");
            String id_gestion = request.getParameter("id_gestion");
            if (id_gestion != null) {
                if (id_eva != null) {
                    if (save != null) {
                        String dia = request.getParameter("dia");
                        String mes = request.getParameter("mes");
                        String anio = request.getParameter("anio");
                        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                        Date fecha_regnota = format.parse(dia + "-" + mes + "-" + anio);
                        Fecha_regnota fecharegnota = new Fecha_regnota();
                        fecharegnota.setId_eva(id_eva);
                        fecharegnota.setFec_limite(fecha_regnota);
                        this.sigaa.setUpdateFechaRegNota(fecharegnota);
                    } else {
                        retorno.put("eva", this.sigaa.getFechaRegNotaByIdEva(id_eva));
                        retorno.put("meses", this.sigaa.getTipoMeses());
                    }
                    retorno.put("id_gestion", id_gestion);
                }
                System.out.println("holaaaaaaaaaaaa");
                retorno.put("fecha_regnota", this.sigaa.getFechaRegNotaByIdGestion(Integer.parseInt(id_gestion)));
                return new ModelAndView(this.perfectView, retorno);
            }
            retorno.put("gestion", this.sigaa.getGestionActivo());
            return new ModelAndView(this.perfectView, retorno);
        } else {
            return new ModelAndView(this.ecxessTimeView, "url", url);
        }
    }
}
