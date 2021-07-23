/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.sysmaqv.web.spring.sigaa.consultas;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import spring.sysmaqv.domain.Curso;
import spring.sysmaqv.domain.CursoMateria;
import spring.sysmaqv.domain.Estudiante;
import spring.sysmaqv.domain.Personal;
import spring.sysmaqv.domain.PlanEvaluacion;
import spring.sysmaqv.domain.logic.SigaaInterface;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.util.WebUtils;

/**
 * Created on : 01-jul-2010, 20:31:05
 * @author MARCO ANTONIO QUENTA VELASCO
 */
public class ActualizarEstados implements Controller {

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
            String id_gestion = request.getParameter("id_gestion");
            if (id_gestion != null) {
                retorno.put("id_gestion", id_gestion);
                String id_curso = request.getParameter("id_curso");
                if (id_curso != null) {
                    Curso curso = new Curso();
                    curso.setId_curso(id_curso);
                    curso.setId_gestion(Integer.parseInt(id_gestion));
                    curso = this.sigaa.getConsultaEstudiantesAllByIdCursoAndIdGestion(curso);
                    String newEstado = request.getParameter("newEstado");
                    if (newEstado != null) {
                        for (int i = 0; i < curso.getEstudiantes().size(); i++) {
                            Estudiante estudiante = (Estudiante) curso.getEstudiantes().get(i);
                            String id_estado = request.getParameter("id_estado-" + estudiante.getId_estudiante());
                            if (!id_estado.equals(estudiante.getId_estado())) {
                                estudiante.setId_estado(id_estado);
                                estudiante.setId_gestion(Integer.parseInt(id_gestion));
                                this.sigaa.setUpdateEstadoByIdEstudianteAndIdGestion(estudiante);
                            }
                        }
                    }
                    curso.setId_curso(id_curso);
                    curso.setId_gestion(Integer.parseInt(id_gestion));
                    curso = this.sigaa.getConsultaEstudiantesAllByIdCursoAndIdGestion(curso);
                    retorno.put("curso", curso);
                    retorno.put("estados", this.sigaa.getEstadosOfEstudiantes());
                    return new ModelAndView(this.perfectView, retorno);
                }
                retorno.put("cursos", this.sigaa.getListaCursos(Integer.parseInt(id_gestion)));
                return new ModelAndView(this.perfectView, retorno);
            }
            retorno.put("gestion", this.sigaa.getGestionActivo());
            return new ModelAndView(this.perfectView, retorno);
        } else {
            return new ModelAndView(this.ecxessTimeView, "url", url);
        }
    }
}
