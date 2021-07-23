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
import spring.sysmaqv.domain.Curso;
import spring.sysmaqv.domain.Gestion;
import spring.sysmaqv.domain.Personal;
import spring.sysmaqv.domain.Tipo_pension;
import spring.sysmaqv.domain.logic.SigaaInterface;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.util.WebUtils;

/**
 *
 * @author Marco Antonio
 */
public class RegistroGestionAcademico implements Controller {

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

                String id_gestion = request.getParameter("id_gestion");
                String colegio = request.getParameter("colegio").toUpperCase();
                String direccion = request.getParameter("direccion");
                String directora = request.getParameter("id_administrativo");
                String telefono = request.getParameter("telefono");
                String fax = request.getParameter("fax");
                String lema = request.getParameter("lema");
                String mail = request.getParameter("mail");
                String cuota_inicialk = request.getParameter("cuota_inicialk");
                if (id_gestion.equals("")) {
                    retorno.put("id_gestion", id_gestion);
                    retorno.put("colegio", colegio);
                    retorno.put("direccion", direccion);
                    retorno.put("id_administrativo", directora);
                    retorno.put("telefono", telefono);
                    retorno.put("fax", fax);
                    retorno.put("lema", lema);
                    retorno.put("mail", mail);
                    retorno.put("cuota_inicialk", cuota_inicialk);
                    mensaje = "false";
                } else {
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
                        this.sigaa.setRegistrarGestionAcademica(gestion);

                        Tipo_pension tipo_pension = new Tipo_pension();
                        List cursos = this.sigaa.getListaCursos(Integer.parseInt(id_gestion));
                        for (int i = 0; i < cursos.size(); i++) {
                            Curso curso = (Curso) cursos.get(i);
                            tipo_pension.setId_gestion(Integer.parseInt(id_gestion));
                            tipo_pension.setId_curso(curso.getId_curso());
                            tipo_pension.setCuota_inicial(Double.parseDouble(request.getParameter("monto-inicial-" + curso.getId_curso())));
                            tipo_pension.setCuota_total(Double.parseDouble(request.getParameter("monto-total-" + curso.getId_curso())) - Double.parseDouble(request.getParameter("monto-inicial-" + curso.getId_curso())));
                            this.sigaa.setRegistrarPensiones(tipo_pension);
                        }
                        mensaje = "true";
                    } catch (Exception e) {
                        e.printStackTrace();
                        retorno.put("id_gestion", id_gestion);
                        retorno.put("colegio", colegio);
                        retorno.put("direccion", direccion);
                        retorno.put("id_administrativo", directora);
                        retorno.put("telefono", telefono);
                        retorno.put("fax", fax);
                        retorno.put("lema", lema);
                        retorno.put("mail", mail);
                        mensaje = "false";
                    }
                }
                retorno.put("mensaje", mensaje);
            }
            Date fecha = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            int anio_actual = Integer.parseInt(sdf.format(fecha));
            retorno.put("id_gestion", anio_actual);
            retorno.put("tipo_dias", this.sigaa.getTipoDias());
            retorno.put("tipo_meses", this.sigaa.getTipoMeses());
            retorno.put("tipo_anios", this.sigaa.getTipoAnios());
            retorno.put("directores", this.sigaa.getAdministradorById_cargo("DIR"));
            Gestion gestion = this.sigaa.getGestionActivo();
            retorno.put("cursos", this.sigaa.getListaCursos(gestion.getId_gestion()));
            return new ModelAndView(this.perfectView, retorno);
        } else {
            return new ModelAndView(this.ecxessTimeView, "url", url);
        }
    }
}
