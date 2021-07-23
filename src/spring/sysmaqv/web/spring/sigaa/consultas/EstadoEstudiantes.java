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
import spring.sysmaqv.domain.Objeto;
import spring.sysmaqv.domain.Personal;
import spring.sysmaqv.domain.logic.SigaaInterface;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.util.WebUtils;

/**
 * Created on : 01-jul-2010, 20:37:30
 * @author MARCO ANTONIO QUENTA VELASCO
 */
public class EstadoEstudiantes implements Controller {

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
                retorno.put("cursos", this.sigaa.getListaCursos(Integer.parseInt(id_gestion)));
                retorno.put("tipos_sexos", this.sigaa.getTiposSexos());
                retorno.put("question", "ESTADO");
                String id_curso = request.getParameter("id_curso");
                String opcion = request.getParameter("opcion");
                String categoria = request.getParameter("categoria");
                String accion = request.getParameter("accion");
                List estados = this.sigaa.getEstadosOfEstudiantes();
                retorno.put("estados", estados);
                if (id_curso != null) {
                    retorno.put("question", opcion);
                    retorno.put("id_curso", id_curso);
                    if (opcion.equals("ESTADO")) {
                        String id_estado = request.getParameter("id_estado");
                        Curso curso = new Curso();
                        curso.setId_gestion(Integer.parseInt(id_gestion));
                        if (id_curso.equals("C")) {
                            List estudiantes = this.sigaa.getEstudiantesAllByIdGestion(curso);
                            curso.setEstudiantes(estudiantes);
                        } else {
                            curso.setId_curso(id_curso);
                            curso = this.sigaa.getConsultaEstudiantesAllByIdCursoAndIdGestion(curso);
                        }
                        retorno.put("curso", curso);
                        retorno.put("categoria", categoria);
                        retorno.put("estado", "_estado");
                        retorno.put("id_estado", id_estado);
                        if (accion != null) {
                            curso.setId_gestion(Integer.parseInt(id_gestion));
                            String excel = this.consultasEstado(curso, categoria, estados, id_estado);
                            response.sendRedirect("documentos/consultas/reportes-excel/estados/" + excel);
                            retorno.put("file", "documentos/consultas/reportes-excel/estados/" + excel);
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

    private String consultasEstado(Curso curso, String categoria, List estados, String id_estado) {
        Date FechaActual = new Date();
        SimpleDateFormat Formator = new SimpleDateFormat("dd-MM-yy");
        String CadenaFechar = Formator.format(FechaActual);
        SimpleDateFormat Formato = new SimpleDateFormat("EEEE, d 'de' MMMM 'de' yyyy, hh:mm:ss a");
        String CadenaFecha = Formato.format(FechaActual);
        String dirDestino = System.getenv("SIGAA_HOME1") + "/documentos/consultas/reportes-excel/estados/";
        String archivo = categoria + "-" + id_estado + "-ESTADO_kinder-4toSec_" + CadenaFechar + ".xls";
        String dirPlantilla = System.getenv("SIGAA_HOME1") + "/documentos/plantillas_excel/consultas_estado_all.xls";
        if (curso.getCurso() != null) {
            archivo = categoria + "-" + id_estado + "-ESTADO_" + curso.getId_curso() + "_" + CadenaFechar + ".xls";
            dirPlantilla = System.getenv("SIGAA_HOME1") + "/documentos/plantillas_excel/consultas_estado.xls";
        }
        try {
            Workbook plantilla = Workbook.getWorkbook(new File(dirPlantilla));
            WritableWorkbook workbook = Workbook.createWorkbook(new File(dirDestino + archivo), plantilla);
            WritableSheet sheet = workbook.getSheet("LISTA");
            WritableFont arial_11 = new WritableFont(WritableFont.ARIAL, 11);
            WritableCellFormat arial11 = new WritableCellFormat(arial_11);
            WritableFont arial_9 = new WritableFont(WritableFont.ARIAL, 9);
            WritableCellFormat arial9 = new WritableCellFormat(arial_9);
            WritableFont arial_8 = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat arial8 = new WritableCellFormat(arial_8);
            sheet.addCell(new Label(0, 1, "GESTION ACADEMICA " + curso.getId_gestion(), arial11));
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
            if (id_estado.equals("T")) {
                sheet.addCell(new Label(3, 8, "ACTIVO, RETIRADO Y NO INCORPORADO", arial8));
            } else {
                for (int i = 0; i < estados.size(); i++) {
                    Objeto objeto = (Objeto) estados.get(i);
                    if (id_estado.equals(objeto.getId())) {
                        sheet.addCell(new Label(3, 8, objeto.getValor(), arial8));
                    }
                }
            }

            sheet.addCell(new Label(3, 9, CadenaFecha, arial8));
            int cont = 1;
            for (int i = 0; i < curso.getEstudiantes().size(); i++) {
                Estudiante estudiante = (Estudiante) curso.getEstudiantes().get(i);
                if (categoria.equals("A")) {
                    if (id_estado.equals("T")) {
                        sheet.addCell(new jxl.write.Number(1, cont + 12, cont, arial9));
                        sheet.addCell(new jxl.write.Number(2, cont + 12, estudiante.getCodigo(), arial9));
                        sheet.addCell(new Label(3, cont + 12, estudiante.getPaterno(), arial9));
                        sheet.addCell(new Label(4, cont + 12, estudiante.getMaterno(), arial9));
                        sheet.addCell(new Label(5, cont + 12, estudiante.getNombres(), arial9));
                        sheet.addCell(new Label(6, cont + 12, estudiante.getEstado(), arial9));
                        sheet.addCell(new Label(7, cont + 12, estudiante.getCurso(), arial9));
                        cont++;
                    }
                    if (estudiante.getId_estado().equals(id_estado)) {
                        sheet.addCell(new jxl.write.Number(1, cont + 12, cont, arial9));
                        sheet.addCell(new jxl.write.Number(2, cont + 12, estudiante.getCodigo(), arial9));
                        sheet.addCell(new Label(3, cont + 12, estudiante.getPaterno(), arial9));
                        sheet.addCell(new Label(4, cont + 12, estudiante.getMaterno(), arial9));
                        sheet.addCell(new Label(5, cont + 12, estudiante.getNombres(), arial9));
                        sheet.addCell(new Label(6, cont + 12, estudiante.getEstado(), arial9));
                        sheet.addCell(new Label(7, cont + 12, estudiante.getCurso(), arial9));
                        cont++;
                    }
                }
                if (estudiante.getId_sexo().equals(categoria)) {
                    if (id_estado.equals("T")) {
                        sheet.addCell(new jxl.write.Number(1, cont + 12, cont, arial9));
                        sheet.addCell(new jxl.write.Number(2, cont + 12, estudiante.getCodigo(), arial9));
                        sheet.addCell(new Label(3, cont + 12, estudiante.getPaterno(), arial9));
                        sheet.addCell(new Label(4, cont + 12, estudiante.getMaterno(), arial9));
                        sheet.addCell(new Label(5, cont + 12, estudiante.getNombres(), arial9));
                        sheet.addCell(new Label(6, cont + 12, estudiante.getEstado(), arial9));
                        if (curso.getCurso() == null) {
                            sheet.addCell(new Label(7, cont + 12, estudiante.getCurso(), arial9));
                        }
                        cont++;
                    }
                    if (estudiante.getId_estado().equals(id_estado)) {
                        sheet.addCell(new jxl.write.Number(1, cont + 12, cont, arial9));
                        sheet.addCell(new jxl.write.Number(2, cont + 12, estudiante.getCodigo(), arial9));
                        sheet.addCell(new Label(3, cont + 12, estudiante.getPaterno(), arial9));
                        sheet.addCell(new Label(4, cont + 12, estudiante.getMaterno(), arial9));
                        sheet.addCell(new Label(5, cont + 12, estudiante.getNombres(), arial9));
                        sheet.addCell(new Label(6, cont + 12, estudiante.getEstado(), arial9));
                        if (curso.getCurso() == null) {
                            sheet.addCell(new Label(7, cont + 12, estudiante.getCurso(), arial9));
                        }
                        cont++;
                    }
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
