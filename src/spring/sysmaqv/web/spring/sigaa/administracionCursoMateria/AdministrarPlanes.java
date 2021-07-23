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
import spring.sysmaqv.domain.CursoMateria;
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
public class AdministrarPlanes implements Controller {

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
            String _guardarMatDoc = request.getParameter("_guardarMatDoc");
            String param = request.getParameter("param");
            String id_cursoDelete = request.getParameter("id_cursoDelete");
            String id_gestion = request.getParameter("id_gestion");
            if (id_gestion != null) {
                if (_guardarMatDoc != null) {
                    String id_curso = request.getParameter("id_curso");
//                    String id_doc_tutor = request.getParameter("id_doc_tutor");
                    List listamaterias = this.sigaa.getListaMaterias();
                    for (int j = 0; j < listamaterias.size(); j++) {
                        Materia materia = (Materia) listamaterias.get(j);
                        if ((request.getParameter(materia.getId_materia())) != null) {
                            CursoMateria curso_materia = new CursoMateria();
                            curso_materia.setId_curso_materia(id_curso + "-" + materia.getId_materia()+"-"+id_gestion);
                            curso_materia.setId_curso(id_curso);
                            curso_materia.setId_materia(materia.getId_materia());
                            curso_materia.setId_gestion(Integer.parseInt(id_gestion));
                            curso_materia.setEstado(true);
                            this.sigaa.setRegistrarCursoMaterias(curso_materia);
                        }
                    }
                }
                if (param != null) {
                    try {
                        this.sigaa.setDeleteCursoMateriaById(param);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (id_cursoDelete != null) {
                    try {
                        CursoMateria cursomateria = new CursoMateria();
                        cursomateria.setId_gestion(Integer.parseInt(id_gestion));
                        cursomateria.setId_curso(id_cursoDelete);
                        this.sigaa.setDeleteCursoMateriasByIdCursoAndIdGestion(cursomateria);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                retorno.put("cursos", this.sigaa.getListaCursos(Integer.parseInt(id_gestion)));
                retorno.put("materias", this.sigaa.getListaMaterias());
                retorno.put("docentes", this.sigaa.getListaDocentes());
                retorno.put("cursoMaterias", this.sigaa.getListaCursoMateriasByIdGestion(Integer.parseInt(id_gestion)));
                retorno.put("id_gestion", id_gestion);
                return new ModelAndView(this.perfectView, retorno);
            }
            retorno.put("gestion", this.sigaa.getGestionActivo());
            return new ModelAndView(this.perfectView, retorno);
        } else {
            return new ModelAndView(this.ecxessTimeView, "url", url);
        }
    }
}
