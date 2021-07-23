/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.sysmaqv.web.spring.sigaa.docentes;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import spring.sysmaqv.domain.CursoMateria;
import spring.sysmaqv.domain.Gestion;
import spring.sysmaqv.domain.Personal;
import spring.sysmaqv.domain.logic.SigaaInterface;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.util.WebUtils;
import spring.sysmaqv.domain.Cualitativa;

/**
 *
 * @author Marco Antonio Quenta Velasco
 */
public class NotasCualitativas implements Controller {

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
            String id_curso_materia = request.getParameter("id_curso_materia");
            String categoria = request.getParameter("categoria");
            String descripcion = request.getParameter("descripcion");
            String id_cualitativa = request.getParameter("id_cualitativa");
            if (id_curso_materia != null) {
                if (request.getParameter("nuevo") != null && !categoria.equals("") && !descripcion.equals("")) {
                    Cualitativa cualitativa = new Cualitativa();
                    cualitativa.setId_curso_materia(id_curso_materia);
                    cualitativa.setCategoria(categoria.toUpperCase());
                    cualitativa.setDescripcion(descripcion.toUpperCase());
                    this.sigaa.setGuardarCualitativa(cualitativa);
                }
                if (request.getParameter("delete") != null) {
                    this.sigaa.setDeleteCualitativaById(id_cualitativa);
                }
                if (request.getParameter("update") != null) {
                    Cualitativa cualitativa = new Cualitativa();
                    cualitativa.setId_cualitativa(Integer.parseInt(id_cualitativa));
                    cualitativa.setCategoria(categoria.toUpperCase());
                    cualitativa.setDescripcion(descripcion.toUpperCase());
                    this.sigaa.setUpdateCualitativaById(cualitativa);
                }
                retorno.put("materia", request.getParameter("materia"));
                retorno.put("curso", request.getParameter("curso"));
                retorno.put("cualitativas", this.sigaa.getNotasCualitativasById_curso_materia(id_curso_materia));
                retorno.put("id_curso_materia", id_curso_materia);
                return new ModelAndView(this.perfectView, retorno);
            }

            retorno.put("elemento", "_elemento");
            Gestion gestion = this.sigaa.getGestionActivo();
            CursoMateria cursomateria = new CursoMateria();
            cursomateria.setId_gestion(gestion.getId_gestion());
            cursomateria.setId_docente(personal.getId_personal());
            retorno.put("cursos", this.sigaa.getCursosByIdDocenteAndIdGestion(cursomateria));
            /*


             */
            return new ModelAndView(this.perfectView, retorno);
        } else {
            return new ModelAndView(this.ecxessTimeView, "url", url);
        }
    }
}
