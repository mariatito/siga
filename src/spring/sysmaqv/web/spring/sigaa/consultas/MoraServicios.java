/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package spring.sysmaqv.web.spring.sigaa.consultas;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
import spring.sysmaqv.domain.Curso;
import spring.sysmaqv.domain.Estudiante;
import spring.sysmaqv.domain.PagoServicio;
import spring.sysmaqv.domain.Personal;
import spring.sysmaqv.domain.logic.SigaaInterface;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.util.WebUtils;
/**
 * Created on : 01-jul-2010, 8:47:10
 * @author MARCO ANTONIO QUENTA VELASCO
 */
public class MoraServicios implements Controller {

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
                retorno.put("question", "MORA");
                retorno.put("id_gestion", id_gestion);
                retorno.put("cursos", this.sigaa.getListaCursos(Integer.parseInt(id_gestion)));
                retorno.put("tipos_sexos", this.sigaa.getTiposSexos());
                String id_curso = request.getParameter("id_curso");
                String opcion = request.getParameter("opcion");
                String categoria = request.getParameter("categoria");
                String accion = request.getParameter("accion");
                if (id_curso != null) {
                    retorno.put("id_curso", id_curso);
                    if (opcion.equals("MORA")) {
                        Curso curso = new Curso();
                        curso.setId_gestion(Integer.parseInt(id_gestion));
                        if (id_curso.equals("C")) {
                            List estudiantes = this.sigaa.getEstudiantesMorososServicioByIdGestion(curso);
                            curso.setEstudiantes(estudiantes);
                        } else {
                            curso.setId_curso(id_curso);
                            curso = this.sigaa.getEstudiantesMorososServicioByIdCursoIdGestion(curso);
                        }
                        retorno.put("curso", curso);
                        retorno.put("categoria", categoria);
                        retorno.put("mora", "_mora");
                        if (accion != null) {
                            curso.setId_gestion(Integer.parseInt(id_gestion));
                            String excel = this.consultasMora(curso, categoria);
                            response.sendRedirect("documentos/consultas/reportes-excel/moras/" + excel);
                            retorno.put("file", "documentos/consultas/reportes-excel/moras/" + excel);
                            return new ModelAndView(this.perfectView, retorno);
                        }
                    }
                }
                return new ModelAndView(this.perfectView, retorno);
            }
            retorno.put("gestion", this.sigaa.getGestionActivo());
            return new ModelAndView(this.perfectView, retorno);
        } else {
            return new ModelAndView(this.ecxessTimeView, "url", url);
        }
    }

    private String consultasMora(Curso curso, String categoria) {
        Date FechaActual = new Date();
        SimpleDateFormat Formator = new SimpleDateFormat("dd-MM-yy");
        String CadenaFechar = Formator.format(FechaActual);
        SimpleDateFormat Formato = new SimpleDateFormat("EEEE, d 'de' MMMM 'de' yyyy, hh:mm:ss a");
        String CadenaFecha = Formato.format(FechaActual);
        String dirDestino = System.getenv("SIGAA_HOME") + "/documentos/consultas/reportes-excel/moras/";
        String archivo = categoria + "-MORAS_kinder-4toSec_" + CadenaFechar + ".xls";
        String dirPlantilla = System.getenv("SIGAA_HOME") + "/documentos/plantillas_excel/consultas_mora_all.xls";
        if (curso.getCurso() != null) {
            archivo = categoria + "-MORAS_" + curso.getId_curso() + "_" + CadenaFechar + ".xls";
            dirPlantilla = System.getenv("SIGAA_HOME") + "/documentos/plantillas_excel/consultas_mora.xls";
        }
        try {
            Workbook plantilla = Workbook.getWorkbook(new File(dirPlantilla));
            WritableWorkbook workbook = Workbook.createWorkbook(new File(dirDestino + archivo), plantilla);
            WritableSheet sheet = workbook.getSheet("LISTA1");
            WritableFont arial_11 = new WritableFont(WritableFont.ARIAL, 11);
            WritableCellFormat arial11 = new WritableCellFormat(arial_11);
            WritableFont arial_9 = new WritableFont(WritableFont.ARIAL, 9);
            WritableCellFormat arial9 = new WritableCellFormat(arial_9);
            WritableCellFormat arial8 = new WritableCellFormat(arial_9);
            System.out.println("____________________ " + curso.getId_curso() + "----" + curso.getCurso());
            sheet.addCell(new Label(1, 1, "GESTION ACADEMICA " + curso.getId_gestion(), arial11));
            if (curso.getCurso() == null) {
                sheet.addCell(new Label(3, 6, "KINDER a 4to de secundaria", arial8));
            } else {
                sheet.addCell(new Label(3, 6, curso.getCurso() + " de " + curso.getCiclo(), arial8));
            }
            if (categoria.equals("A")) {
                sheet.addCell(new Label(3, 7, "FEMENINO Y MASCULINO", arial8));
            } else if (categoria.equals("F")) {
                sheet.addCell(new Label(3, 7, "FEMENINO", arial8));
            } else if (categoria.equals("M")) {
                sheet.addCell(new Label(3, 7, "MASCULINO", arial8));
            }
            sheet.addCell(new Label(3, 8, CadenaFecha, arial8));
            int cont = 1;
            int c = 1;
            for (int i = 0; i < curso.getEstudiantes().size(); i++) {
                Estudiante estudiante = (Estudiante) curso.getEstudiantes().get(i);
                if (categoria.equals("A")) {
                    sheet.addCell(new jxl.write.Number(1, cont + 11, c, arial9));
                    sheet.addCell(new jxl.write.Number(2, cont + 11, estudiante.getCodigo(), arial9));
                    sheet.addCell(new Label(3, cont + 11, estudiante.getPaterno(), arial9));
                    sheet.addCell(new Label(4, cont + 11, estudiante.getMaterno(), arial9));
                    sheet.addCell(new Label(5, cont + 11, estudiante.getNombres(), arial9));
                     sheet.addCell(new Label(7, cont + 11, estudiante.getCurso(), arial9));
                    for (int j = 0; j < estudiante.getServicios().size(); j++) {
                        PagoServicio pagoservicio = (PagoServicio) estudiante.getServicios().get(j);
                        if (j == 0) {
                            sheet.addCell(new Label(6, cont + 11, pagoservicio.getTipo_servicio() + " [" + pagoservicio.getMonto_servicio() + "]", arial9));
                            cont++;
                        } else {
                            sheet.addCell(new Label(1, cont + 11, "", arial9));
                            sheet.addCell(new Label(2, cont + 11, "", arial9));
                            sheet.addCell(new Label(3, cont + 11, "", arial9));
                            sheet.addCell(new Label(4, cont + 11, "", arial9));
                            sheet.addCell(new Label(5, cont + 11, "", arial9));
                            sheet.addCell(new Label(6, cont + 11, pagoservicio.getTipo_servicio() + " [" + pagoservicio.getMonto_servicio() + "]", arial9));
                            sheet.addCell(new Label(7, cont + 11, "", arial9));
                            cont++;
                        }
                    }
                    c++;
                }
                if (estudiante.getId_sexo().equals(categoria)) {
                    sheet.addCell(new jxl.write.Number(1, cont + 11, c, arial9));
                    sheet.addCell(new jxl.write.Number(2, cont + 11, estudiante.getCodigo(), arial9));
                    sheet.addCell(new Label(3, cont + 11, estudiante.getPaterno(), arial9));
                    sheet.addCell(new Label(4, cont + 11, estudiante.getMaterno(), arial9));
                    sheet.addCell(new Label(5, cont + 11, estudiante.getNombres(), arial9));
                    for (int j = 0; j < estudiante.getServicios().size(); j++) {
                        PagoServicio pagoservicio = (PagoServicio) estudiante.getServicios().get(j);
                        if (j == 0) {
                            sheet.addCell(new Label(6, cont + 11, pagoservicio.getTipo_servicio() + " [" + pagoservicio.getMonto_servicio() + "]", arial9));
                            if (curso.getCurso() == null) {
                                sheet.addCell(new Label(7, cont + 12, estudiante.getCurso(), arial9));
                            }
                            cont++;
                        } else {
                            sheet.addCell(new Label(1, cont + 11, "", arial9));
                            sheet.addCell(new Label(2, cont + 11, "", arial9));
                            sheet.addCell(new Label(3, cont + 11, "", arial9));
                            sheet.addCell(new Label(4, cont + 11, "", arial9));
                            sheet.addCell(new Label(5, cont + 11, "", arial9));
                            sheet.addCell(new Label(6, cont + 11, pagoservicio.getTipo_servicio() + " [" + pagoservicio.getMonto_servicio() + "]", arial9));
                            sheet.addCell(new Label(7, cont + 11, "", arial9));
                            cont++;
                        }
                    }
                    c++;
                }
            }
            workbook.write();
            workbook.close();
        } catch (Exception de) {
            de.printStackTrace();
        }
        return archivo;
    }
}