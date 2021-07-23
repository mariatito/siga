/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.sysmaqv.web.spring.sigaa.gestionAcademico;

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
import java.util.List;
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
import spring.sysmaqv.domain.Conducta;
import spring.sysmaqv.domain.Evaluacion;
import spring.sysmaqv.domain.PeriodoCurso;

/**
 *
 * @author Marco Antonio
 */
public class AdministrarCurso implements Controller {

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
                String opcion = request.getParameter("opcion");
                String opcionInterno = request.getParameter("opcionInterno");
                retorno.put("id_gestion", id_gestion);
                if (opcion != null) {
                    String id_curso = request.getParameter("id_curso");
                    String id_materia = request.getParameter("id_materia");
                    String id_curso_materia = request.getParameter("id_curso_materia");
                    if (opcion.equals("DatosGenerales")) {
                        CursoMateria cursomateria = new CursoMateria();
                        cursomateria.setId_curso_materia(id_curso_materia);
                        cursomateria.setId_curso(id_curso);
                        cursomateria.setId_gestion(Integer.parseInt(id_gestion));
                        cursomateria.setId_materia(id_materia);
                        Curso curso = this.sigaa.getCursoById(cursomateria);
                        retorno.put("curso", curso);
                        if (opcionInterno != null) {
                            if (opcionInterno.equals("_imprimirlista")) {
                                String archivo = this.listaEstudiantesPdf(curso);
                                response.sendRedirect("documentos/nominas/pdf/" + archivo);
                            }
                        }
                        return new ModelAndView(this.perfectView + "DatosGenerales", retorno);
                    }
                    if (opcion.equals("Evaluaciones")) {
                        if (opcionInterno != null) {
                            System.out.println("opcion interno eeva");
                            if (opcionInterno.equals("modificar")) {
                                SimpleDateFormat fec = new SimpleDateFormat("dd-MM-yyyy");
                                String evaluacion = request.getParameter("evaluacion");
                                String dia = request.getParameter("dia");
                                String mes = request.getParameter("mes");
                                String anio = request.getParameter("anio");
                                String descripcion = request.getParameter("descripcion");
                                String id_evaluacion = request.getParameter("id_evaluacion");
                                PlanEvaluacion planevaluacion = new PlanEvaluacion();
                                planevaluacion.setId_evaluacion(id_evaluacion);
                                planevaluacion.setEvaluacion(evaluacion);
                                planevaluacion.setFec_limite(fec.parse(dia + "-" + mes + "-" + anio));
                                planevaluacion.setDescripcion(descripcion);
                                planevaluacion.setEstado("activo");
                                this.sigaa.setUpdatePlanEvaluacionesByIdEvaluacion(planevaluacion);
                            }
                            if (opcionInterno.equals("reg_new_eva")) {
                                SimpleDateFormat fec = new SimpleDateFormat("dd-MM-yyyy");
                                String id_eva = request.getParameter("id_eva");
                                String new_nota = "0";
                                String new_dps = "0";
                                if (!id_curso.equals("K")) {
                                    new_nota = request.getParameter("nota");
                                    new_dps = request.getParameter("dps");
                                }
                                String dia_ini = request.getParameter("dia_ini");
                                String mes_ini = request.getParameter("mes_ini");
                                String anio_ini = request.getParameter("anio_ini");
                                String dia_fin = request.getParameter("dia_fin");
                                String mes_fin = request.getParameter("mes_fin");
                                String anio_fin = request.getParameter("anio_fin");
                                String num = request.getParameter("nro_evas");
                                String evaluacion = request.getParameter("evaluacion");
                                String descripcion = request.getParameter("descripcion");

                                PlanEvaluacion planevaluacion = new PlanEvaluacion();
                                planevaluacion.setId_evaluacion(id_curso_materia + "-E" + num);
                                planevaluacion.setId_curso_materia(id_curso_materia);
                                planevaluacion.setEvaluacion(evaluacion);
                                planevaluacion.setNota(Integer.parseInt(new_nota));
                                planevaluacion.setDps(Integer.parseInt(new_dps));
                                planevaluacion.setFec_evaluacion(fec.parse(dia_ini + "-" + mes_ini + "-" + anio_ini));
                                planevaluacion.setFec_limite(fec.parse(dia_fin + "-" + mes_fin + "-" + anio_fin));
                                planevaluacion.setDescripcion(descripcion);
                                planevaluacion.setEstado("inactivo");
                                planevaluacion.setId_curso(id_curso);
                                planevaluacion.setId_eva(id_eva);
                                planevaluacion.setId_gestion(Integer.parseInt(id_gestion));
                                this.sigaa.setRegistrarPlanEvaluaciones(planevaluacion);

                                CursoMateria cursomateria = new CursoMateria();
                                cursomateria.setId_curso_materia(id_curso_materia);
                                cursomateria.setId_curso(id_curso);
                                cursomateria.setId_gestion(Integer.parseInt(id_gestion));
                                Curso curso = this.sigaa.getCursoById(cursomateria);
                                Conducta conducta = new Conducta();
                                conducta.setId_curso(id_curso);
                                conducta.setId_eva(planevaluacion.getId_eva());
                                List conductas = this.sigaa.setConductasByIdCursoAndIdEva(conducta);

                                for (int i = 0; i < curso.getEstudiantes().size(); i++) {
                                    Estudiante ins = (Estudiante) curso.getEstudiantes().get(i);
                                    Evaluacion eva = new Evaluacion();
                                    eva.setId_estudiante(ins.getId_estudiante());
                                    eva.setId_evaluacion(planevaluacion.getId_evaluacion());
                                    int dps = 0;
                                    for (int j = 0; j < conductas.size(); j++) {
                                        conducta = (Conducta) conductas.get(j);
                                        if (ins.getId_estudiante().equals(conducta.getId_estudiante())) {
                                            dps = conducta.getDps();
                                        }
                                    }
                                    eva.setDps(dps);
                                    eva.setId_gestion(Integer.parseInt(id_gestion));
                                    eva.setId_curso(id_curso);
                                    this.sigaa.setRegistrarEvaluacionesByCursoMateria(eva);
                                }
                            }
                        }
                        CursoMateria cursomateria = new CursoMateria();
                        cursomateria.setId_curso_materia(id_curso_materia);
                        cursomateria.setId_curso(id_curso);
                        cursomateria.setId_gestion(Integer.parseInt(id_gestion));
                        cursomateria.setId_materia(id_materia);
                        Curso curso = this.sigaa.getCursoById(cursomateria);
                        for (int ii = 0; ii < curso.getEvaluaciones().size(); ii++) {
                            PlanEvaluacion pe = (PlanEvaluacion) curso.getEvaluaciones().get(ii);
                            Calendar fec_lim = Calendar.getInstance();
                            fec_lim.setTime(pe.getFec_limite());
                            fec_lim.add(Calendar.DATE, 1);
                            Calendar fec_actual = Calendar.getInstance();
                            Calendar fec_eva = Calendar.getInstance();
                            fec_eva.setTime(pe.getFec_evaluacion());
                            PlanEvaluacion p_eva = new PlanEvaluacion();
                            p_eva.setId_evaluacion(pe.getId_evaluacion());
                            p_eva.setFec_limite(pe.getFec_limite());
                            if (fec_actual.after(fec_eva) && fec_actual.before(fec_lim)) {
                                p_eva.setEstado("activo");
                            } else {
                                p_eva.setEstado("inactivo");
                            }
                            this.sigaa.setUpdateEvaluacionByIdEvaluacion(p_eva);
                        }
                        curso = this.sigaa.getCursoById(cursomateria);
                        retorno.put("nro_evas", curso.getEvaluaciones().size());
                        /*verificamos cuantos periodos tiene este curso por idcurso y gestion, y devolver id_eva_ant de acuerdo a eso, si es trimestral o bimestral*/
                        curso.setId_gestion(Integer.parseInt(id_gestion));
                        PeriodoCurso periodocurso = this.sigaa.getPeriodoByIdcursoAndGestion(curso);
                        String idperiodo = Integer.toString(periodocurso.getIdperiodo());
                        int periodo = Integer.parseInt(idperiodo.substring(4, 5));
                        if (periodo == 3) {
                            retorno.put("id_eva_ant", "E" + (curso.getEvaluaciones().size() + 1) + "-" + id_gestion);
                        } else if (periodo == 2) {
                            retorno.put("id_eva_ant", "E" + (curso.getEvaluaciones().size() + 1) + "-" + id_gestion + "B");
                        }
                        retorno.put("fecha_regnota", this.sigaa.getFechaRegNotaByIdGestion(Integer.parseInt(id_gestion)));
                        retorno.put("curso", curso);
                        retorno.put("tipo_meses", this.sigaa.getTipoMeses());
                        return new ModelAndView(this.perfectView + "Evaluaciones", retorno);
                    }
                    if (opcion.equals("Horario")) {
                        CursoMateria cursomateria = new CursoMateria();
                        cursomateria.setId_curso_materia(id_curso_materia);
//                        cursomateria.setId_curso_materia(id_curso + "-" + id_materia);
                        cursomateria.setId_curso(id_curso);
                        cursomateria.setId_gestion(Integer.parseInt(id_gestion));
                        cursomateria.setId_materia(id_materia);
                        Curso curso = this.sigaa.getCursoById(cursomateria);
                        retorno.put("curso", curso);
//                        retorno.put("id_gestion", id_gestion);
                        if (opcionInterno != null) {
                            if (opcionInterno.equals("_imprimirlista")) {
                                String archivo = this.listaEstudiantesPdf(curso);
                                response.sendRedirect("documentos/nominas/pdf/" + archivo);
                            }
                        }
                        return new ModelAndView(this.perfectView + "Horario", retorno);
                    }
                    if (opcion.equals("Estadisticas")) {
                        CursoMateria cursomateria = new CursoMateria();
                        cursomateria.setId_curso_materia(id_curso_materia);
                        cursomateria.setId_curso(id_curso);
                        cursomateria.setId_gestion(Integer.parseInt(id_gestion));
                        cursomateria.setId_materia(id_materia);
                        Curso curso = this.sigaa.getCursoById(cursomateria);
                        retorno.put("curso", curso);
//                        retorno.put("id_gestion", id_gestion);
                        if (opcionInterno != null) {
                            if (opcionInterno.equals("_imprimirlista")) {
                                String archivo = this.listaEstudiantesPdf(curso);
                                response.sendRedirect("documentos/nominas/pdf/" + archivo);
                            }
                        }
                        return new ModelAndView(this.perfectView + "Estadisticas", retorno);
                    }
                }
                retorno.put("cursoMaterias", this.sigaa.getListaCursoMateriasByIdGestion(Integer.parseInt(id_gestion)));
                retorno.put("cursos", this.sigaa.getListaCursos(Integer.parseInt(id_gestion)));
//                retorno.put("id_gestion", id_gestion);
                return new ModelAndView(this.perfectView, retorno);
            }
            retorno.put("gestion", this.sigaa.getGestionActivo());
            return new ModelAndView(this.perfectView, retorno);
        } else {
            return new ModelAndView(this.ecxessTimeView, "url", url);
        }
    }

    private String formatearFecha(Date fecha) {
        String nuevo = new String();
        SimpleDateFormat formato = new SimpleDateFormat("'La Paz', EEEE d 'de' MMMM 'de' yyyy");
        nuevo = formato.format(fecha);
        return nuevo;
    }

    private String formatFecha(Date fecha) {
        String nuevo = new String();
        SimpleDateFormat formato = new SimpleDateFormat("d'-'MMMM'-'yyyy");
        nuevo = formato.format(fecha);
        return nuevo;
    }

    private String listaEstudiantesPdf(Curso curso) {
        String dir = System.getenv("SIGAA_HOME") + "/documentos/nominas/pdf/";
        String archivo = "Lista_" + curso.getCurso() + "_de_" + curso.getCiclo() + "-" + curso.getMateria().getId_materia() + "_(" + this.formatFecha(new Date()) + ").pdf";
        Font font8 = FontFactory.getFont(FontFactory.HELVETICA, 7);
        Font font8b = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 7);
        Font font10b = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10);
        Document document = new Document(PageSize.LETTER);
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dir + archivo));
            float width = document.getPageSize().getWidth();
            document.open();
            Image png = Image.getInstance(System.getenv("SIGAA_HOME") + "/imagenes/iconos_sigaa/sigaa_oficial.png");
            png.setAbsolutePosition(470, 740);
            png.scalePercent(25);
            document.add(png);
            document.add(Chunk.NEWLINE);
            document.add(new Phrase("COLEGIO \"SANTA TERESA\"", font8b));
            document.add(Chunk.NEWLINE);
            document.add(new Phrase("GESTION 2010", font8b));
            document.add(Chunk.NEWLINE);
            Paragraph p = new Paragraph(new Phrase("LISTA DE ESTUDIANTES (" + curso.getMateria().getMateria() + ")", font10b));
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);
            document.add(new Phrase("Docente : ", font8b));
            if (curso.getDocenteOBJ() == null) {
                document.add(new Phrase("Sin docente", font8));
            } else {
                document.add(new Phrase(" " + curso.getDocenteOBJ().getAbreviatura() + " " + curso.getDocenteOBJ().getNombres() + " " + curso.getDocenteOBJ().getPaterno() + " " + curso.getDocenteOBJ().getMaterno(), font8));
            }
            document.add(new Phrase("         Curso : ", font8b));
            document.add(new Phrase(" " + curso.getCurso() + " de " + curso.getCiclo(), font8));
            document.add(new Phrase("         Cantidad de estudiantes inscrictos : ", font8b));
            document.add(new Phrase(" " + Integer.toString(curso.getEstudiantes().size()), font8));
            document.add(Chunk.NEWLINE);

            float[] columnDefinitionSize = {5F, 10F, 40F, 45F};
            PdfPTable table = new PdfPTable(columnDefinitionSize);
            table.getDefaultCell().setBorder(0);
            table.getDefaultCell().setPadding(3);
            table.setHorizontalAlignment(0);
            table.setWidthPercentage(100);

            table.getDefaultCell().setGrayFill(0.9f);
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(new Phrase("No.", font8));
            table.addCell(new Phrase("Código", font8));
            table.addCell(new Phrase("Apellidos y nombres", font8));
            table.addCell(new Phrase(" ", font8));
            table.getDefaultCell().setGrayFill(1);
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            table.setHeaderRows(1);
            for (int i = 0; i < curso.getEstudiantes().size(); i++) {
                Estudiante estudiante = (Estudiante) curso.getEstudiantes().get(i);
                table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(new Phrase(Integer.toString(i + 1), font8));
                table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(new Phrase(Integer.toString(estudiante.getCodigo()), font8));
                table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(new Phrase(estudiante.getPaterno() + " " + estudiante.getMaterno() + " " + estudiante.getNombres(), font8));
                table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(new Phrase("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _", font8));
            }
            document.add(table);
            p = new Paragraph(new Phrase(this.formatearFecha(new Date()), font8));
            p.setAlignment(Element.ALIGN_RIGHT);
            document.add(p);
            p = new Paragraph(new Phrase("SIGAA Sistema Integrado de Gestión Académico - Administrativa 2010", font8));
            document.add(p);

        } catch (Exception de) {
            de.printStackTrace();
        }

        document.close();
        return archivo;
    }
}
