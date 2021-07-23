/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.sysmaqv.web.spring.sigaa.administracionCursoMateria;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import spring.sysmaqv.domain.Materia;
import spring.sysmaqv.domain.Personal;
import spring.sysmaqv.domain.logic.SigaaInterface;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.util.WebUtils;

/**
 *
 * @author Marco Antonio
 */
public class ActualizarMaterias implements Controller {

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
            String id_materia = request.getParameter("id_materia");
            String opcion = request.getParameter("opcion");
            String mensaje="true";

            if (id_materia != null) {
                if (opcion != null) {
                    String materia = request.getParameter("materia").toUpperCase();
                    String promocionable = request.getParameter("promocionable");
                    try {
                        Materia materias = new Materia();
                        materias.setId_materia(id_materia);
                        materias.setMateria(materia);
                        if (promocionable != null) {
                            materias.setPromocionable(true);
                        } else {
                            materias.setPromocionable(false);
                        }
                        this.sigaa.setActualizarMateria(materias);
                    } catch (Exception e) {
                        e.printStackTrace();
                        mensaje = "false";
                    }
                } else {
                    List mat = this.sigaa.getBuscarMateria(id_materia);
                    Materia materia = (Materia) mat.get(0);
                    retorno.put("visualizar", materia);
                    retorno.put("materia", "materia");
                    return new ModelAndView(this.perfectView, retorno);
                }
                retorno.put("mensaje", mensaje);
            }
            if (search != null) {
                retorno.put("materias", this.sigaa.getBuscarMateria(search.toUpperCase()));
                retorno.put("search", search.toUpperCase());
            } else {
                retorno.put("materias", this.sigaa.getListaMaterias());
            }
            return new ModelAndView(this.perfectView, retorno);
        } else {
            return new ModelAndView(this.ecxessTimeView, "url", url);
        }
    }
}
