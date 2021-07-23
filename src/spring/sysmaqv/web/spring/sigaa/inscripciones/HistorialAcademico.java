/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.sysmaqv.web.spring.sigaa.inscripciones;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import spring.sysmaqv.domain.Curso;
import spring.sysmaqv.domain.Estudiante;
import spring.sysmaqv.domain.Familia;
import spring.sysmaqv.domain.Gestion;
import spring.sysmaqv.domain.Inscripcion;
import spring.sysmaqv.domain.Persona;
import spring.sysmaqv.domain.Personal;
import spring.sysmaqv.domain.logic.SigaaInterface;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.util.WebUtils;

/**
 * @ Company : M&M
 * @ Author  : Marco Antonio Quenta Velasco
 * @ Gestion : 2010
 * @ La Paz - Bolivia
 */
public class HistorialAcademico implements Controller {

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

            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            if (isMultipart == true) {
                Gestion gestion = this.sigaa.getGestionActivo();
                String codigo = "";
                String paterno = "";
                String materno = "";
                String nombres = "";
                String archivo = "";
                String id_sexo = "";
                String dia = "";
                String mes = "";
                String anio = "";
                String id_curso = "";
                String id_gondola = "";
                String id_familia = "";
                String id_estudiante = "";
                String id_gestion = "";
                String id_persona = "";
                String carnet = "";
                try {
                    FileItemFactory factory = new DiskFileItemFactory();
                    ServletFileUpload upload = new ServletFileUpload(factory);
                    List fileItems = upload.parseRequest(request);
                    Iterator it = fileItems.iterator();
                    FileItem fi;
                    String direccion = System.getenv("SIGAA_HOME") + "/documentos/fotos/";
                    String nombre_foto = "";
                    while (it.hasNext()) {
                        fi = (FileItem) it.next();
                        if (fi.getFieldName().equals("codigo")) {
                            codigo = fi.getString();
                        }
                        if (fi.getFieldName().equals("paterno")) {
                            paterno = fi.getString();
                        }
                        if (fi.getFieldName().equals("materno")) {
                            materno = fi.getString();
                        }
                        if (fi.getFieldName().equals("nombres")) {
                            nombres = fi.getString();
                        }
                        if (fi.getFieldName().equals("archivo")) {
                            archivo = fi.getString();
                            if (!fi.getString().equals("")) {
                                nombre_foto = codigo + "-" + gestion.getId_gestion() + ".jpg";
                                fi.write(new File(direccion + nombre_foto));
                            }
                        }
                        if (fi.getFieldName().equals("id_sexo")) {
                            id_sexo = fi.getString();
                        }
                        if (fi.getFieldName().equals("dia")) {
                            dia = fi.getString();
                        }
                        if (fi.getFieldName().equals("mes")) {
                            mes = fi.getString();
                        }
                        if (fi.getFieldName().equals("anio")) {
                            anio = fi.getString();
                        }
                        if (fi.getFieldName().equals("id_curso")) {
                            id_curso = fi.getString();
                        }
                        if (fi.getFieldName().equals("id_gondola")) {
                            id_gondola = fi.getString();
                        }
                        if (fi.getFieldName().equals("id_familia")) {
                            id_familia = fi.getString();
                        }
                        if (fi.getFieldName().equals("id_estudiante")) {
                            id_estudiante = fi.getString();
                        }
                        if (fi.getFieldName().equals("id_gestion")) {
                            id_gestion = fi.getString();
                        }
                        if (fi.getFieldName().equals("id_persona")) {
                            id_persona = fi.getString();
                        }
                        if (fi.getFieldName().equals("carnet")) {
                            carnet = fi.getString();
                        }
                    }
                    Persona persona = new Persona();
                    persona.setId_persona(id_persona);
                    persona.setPaterno(paterno.toUpperCase());
                    persona.setMaterno(materno.toUpperCase());
                    persona.setNombres(nombres.toUpperCase());
                    persona.setId_sexo(id_sexo);
                    this.sigaa.setUpdatePersona(persona);

                    Estudiante estudiante = new Estudiante();
                    estudiante.setCodigo(Integer.parseInt(codigo));
                    estudiante.setCarnet(carnet);
                    estudiante.setId_estudiante(id_estudiante);
                    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                    Date fecha_nacimiento = format.parse(dia + "-" + mes + "-" + anio);
                    estudiante.setFecha_nacimiento(fecha_nacimiento);
                    this.sigaa.setUpdateEstudiante(estudiante);

                    Inscripcion inscripcion = new Inscripcion();
                    inscripcion.setId_gestion(Integer.parseInt(id_gestion));
                    inscripcion.setId_gondola(id_gondola);
                    inscripcion.setNombre_foto(nombre_foto);
                    inscripcion.setId_estudiante(id_estudiante);
                    this.sigaa.setUpdateInscripcionFotografia(inscripcion);

                    retorno.put("family", (Familia) this.sigaa.getBuscarFamilia(id_familia).get(0));
                    estudiante.setId_gestion(Integer.parseInt(id_gestion));
                    estudiante.setId_curso(id_curso);
                    retorno.put("estudiante", this.sigaa.getDatosActualesEstudianteByIdEstudianteIdCursoIdGestion(estudiante));
                    retorno.put("tipos_sexos", this.sigaa.getTiposSexos());
                    retorno.put("tipo_meses", this.sigaa.getTipoMeses());
                    retorno.put("cursos", this.sigaa.getListaCursos(Integer.parseInt(id_gestion)));
                    retorno.put("gondolas", this.sigaa.getListaGondolas());
                    retorno.put("id_gestion", id_gestion);
                    return new ModelAndView(this.perfectView + "HistorialEstudiante", retorno);
                } catch (Exception de) {
                    de.printStackTrace();
                    return new ModelAndView(this.perfectView, retorno);
                }
            }
            String id_gestion = request.getParameter("id_gestion");
            if (id_gestion != null) {
                retorno.put("id_gestion", id_gestion);
                String id_curso = request.getParameter("id_curso");
                if (id_curso != null) {
                    String opcion = request.getParameter("opcion");
                    Curso curso = new Curso();
                    curso.setId_curso(id_curso);
                    curso.setId_gestion(Integer.parseInt(id_gestion));
                    curso = this.sigaa.getCursoByIdCursoAndIdGestion(curso);
                    if (opcion != null) {
                        if (opcion.equals("HistorialEstudiante")) {
                            String id_familia = request.getParameter("id_familia");
                            String id_estudiante = request.getParameter("id_estudiante");
                            retorno.put("family", (Familia) this.sigaa.getBuscarFamilia(id_familia).get(0));
                            Estudiante estudiante = new Estudiante();
                            estudiante.setId_estudiante(id_estudiante);
                            estudiante.setId_gestion(Integer.parseInt(id_gestion));
                            estudiante.setId_curso(id_curso);
                            retorno.put("estudiante", this.sigaa.getDatosActualesEstudianteByIdEstudianteIdCursoIdGestion(estudiante));
                            retorno.put("tipos_sexos", this.sigaa.getTiposSexos());
                            retorno.put("tipo_meses", this.sigaa.getTipoMeses());
                            retorno.put("cursos", this.sigaa.getListaCursos(Integer.parseInt(id_gestion)));
                            retorno.put("gondolas", this.sigaa.getListaGondolas());
                            retorno.put("id_gestion", id_gestion);
                            return new ModelAndView(this.perfectView + "HistorialEstudiante", retorno);
                        }
                        if (opcion.equals("_imprimeExcel")) {
                            String excel = this.estudiantesCursoExcel(curso);
                            response.sendRedirect("documentos/nominas/excel/" + excel);
                            retorno.put("file", "documentos/docentes/nominas/excel/" + excel);
                        }
                    }
                    
                    retorno.put("curso", curso);
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

    private String estudiantesCursoExcel(Curso curso) {
        Date FechaActual = new Date();
        SimpleDateFormat Formator = new SimpleDateFormat("dd-MM-yy");
        String CadenaFechar = Formator.format(FechaActual);
        SimpleDateFormat Formato = new SimpleDateFormat("EEEE, d 'de' MMMM 'de' yyyy, hh:mm:ss a");
        String CadenaFecha = Formato.format(FechaActual);
        String dirDestino = System.getenv("SIGAA_HOME") + "/documentos/nominas/excel/";
        String archivo = "ListCumple_" + curso.getId_curso() + "_" + CadenaFechar + ".xls";
        String dirPlantilla = System.getenv("SIGAA_HOME") + "/documentos/plantillas_excel/plantilla_historial.xls";
        try {
            Workbook plantilla = Workbook.getWorkbook(new File(dirPlantilla));
            WritableWorkbook workbook = Workbook.createWorkbook(new File(dirDestino + archivo), plantilla);
            WritableSheet sheet = workbook.getSheet("LISTA");
            WritableFont arial_8 = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat arial8 = new WritableCellFormat(arial_8);
            sheet.addCell(new Label(3, 5, curso.getCurso() + " de " + curso.getCiclo(), arial8));
            sheet.addCell(new Label(3, 6, CadenaFecha, arial8));

            Estudiante est;
            for (int i = 0; i < curso.getEstudiantes().size(); i++) {
                est = (Estudiante) curso.getEstudiantes().get(i);
                sheet.addCell(new jxl.write.Number(1, i + 10, (i + 1), arial8));
                sheet.addCell(new jxl.write.Number(2, i + 10, est.getCodigo(), arial8));
                sheet.addCell(new Label(3, i + 10, est.getPaterno() + " " + est.getMaterno() + " " + est.getNombres(), arial8));
                sheet.addCell(new Label(4, i + 10, est.getSfecha_nacimiento(), arial8));
            }
            workbook.write();
            workbook.close();
        } catch (Exception de) {
            de.printStackTrace();
        }
        return archivo;
    }
}
