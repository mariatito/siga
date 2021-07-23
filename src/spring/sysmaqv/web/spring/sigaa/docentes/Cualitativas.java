/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.sysmaqv.web.spring.sigaa.docentes;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.util.WebUtils;
import spring.sysmaqv.domain.Conducta;
import spring.sysmaqv.domain.Curso;
import spring.sysmaqv.domain.CursoMateria;
import spring.sysmaqv.domain.Estudiante;
import spring.sysmaqv.domain.Evaluacion;
import spring.sysmaqv.domain.Falta;
import spring.sysmaqv.domain.Gestion;
import spring.sysmaqv.domain.Materia;
import spring.sysmaqv.domain.PeriodoCurso;
import spring.sysmaqv.domain.Personal;
import spring.sysmaqv.domain.PlanEvaluacion;
import spring.sysmaqv.domain.logic.SigaaInterface;

/**
 *
 * @author Marco Antonio
 */
public class Cualitativas implements Controller {

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
            String id_curso = request.getParameter("id_curso");
            String opcion = request.getParameter("opcion");
            Gestion gestion = this.sigaa.getGestionActivo();
            retorno.put("id_gestion", gestion.getId_gestion());

            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            System.out.println("|||||||||||||" + isMultipart);
            if (isMultipart == true) {
                String fichero = "";
                String columna = "";
                try {
                    FileItemFactory factory = new DiskFileItemFactory();
                    ServletFileUpload upload = new ServletFileUpload(factory);
                    List fileItems = upload.parseRequest(request);
                    Iterator it = fileItems.iterator();
                    FileItem fi;

                    String direccion = System.getenv("SIGAA_HOME1") + "/documentos/docentes/notas_excel/";
                    String nombre_archivo = "";
                    Conducta conducta;
                    while (it.hasNext()) {
                        fi = (FileItem) it.next();
                        if (fi.getFieldName().equals("id_curso")) {
                            id_curso = fi.getString();
                        }
                        if (fi.getFieldName().equals("columna")) {
                            columna = fi.getString();
                        }
                        if (fi.getFieldName().equals("fichero")) {
                            fichero = fi.getString();
                            if (!fi.getString().equals("")) {
                                nombre_archivo = new SimpleDateFormat("ddMMyyyyHHmmss") + ".xls";
                                fi.write(new File(direccion + nombre_archivo));
                            }
                        }
                    }
                    int col = 0;
                    String id_eva = "E" + columna + "-" + gestion.getId_gestion();
                    if (columna.equals("1")) {
                        col = 5;
                    } else if (columna.equals("2")) {
                        col = 6;
                    } else if (columna.equals("3")) {
                        col = 7;
                    } else if (columna.equals("4")) {
                        col = 8;
                    }

                    if (!fichero.equals("")) {
                        Workbook workbook = Workbook.getWorkbook(new File(System.getenv("SIGAA_HOME1") + "/documentos/docentes/notas_excel/" + nombre_archivo));
                        Sheet sheet = workbook.getSheet("CUANTITATIVAS");
                        Curso cur = new Curso();
                        cur.setId_curso(id_curso);
                        cur.setId_gestion(gestion.getId_gestion());
                        Curso curso = this.sigaa.getCursoByIdCursoAndIdGestion(cur);
                        for (int i = 0; i < curso.getEstudiantes().size(); i++) {
                            Estudiante est = (Estudiante) curso.getEstudiantes().get(i);
                            String id_estudiante = sheet.getCell(2, i + 12).getContents();
                                                        String diagnostico = sheet.getCell(col, i + 12).getContents();
                            if (id_estudiante.equals(est.getId_estudiante())) {
                                conducta = new Conducta();
                                conducta.setId_estudiante(est.getId_estudiante());
                                conducta.setId_curso(id_curso);
                                conducta.setId_eva(id_eva);
                                conducta.setId_gestion(gestion.getId_gestion());
                                conducta.setDiagnostico(diagnostico.toUpperCase());
                                this.sigaa.setUpdateDiagnostico(conducta);
                            }
                        }
                        workbook.close();
                    }
                } catch (Exception de) {
                    de.printStackTrace();
                }
            }

            if (id_curso != null) {
                Curso cur = new Curso();
                cur.setId_curso(id_curso);
                cur.setId_gestion(gestion.getId_gestion());
                Curso curso = this.sigaa.getCursoByIdCursoAndIdGestion(cur);
                curso.setId_gestion(cur.getId_gestion());
                if (opcion != null) {
                    if (opcion.equals("_excel")) {
                        String excel = this.estudiantesCursoMateriaExcel(curso, personal);
                        response.sendRedirect("documentos/docentes/listas_excel/" + excel);
                    }
                }
                curso.setId_gestion(gestion.getId_gestion());
                retorno.put("curso", curso);
            } else {
                CursoMateria cursomateria = new CursoMateria();
                cursomateria.setId_docente_tutor(personal.getId_personal());
                cursomateria.setId_gestion(gestion.getId_gestion());
                retorno.put("cursos", this.sigaa.getCursosTutorByIdDocenteAndIdGestion(cursomateria));
            }

            return new ModelAndView(this.perfectView, retorno);
        } else {
            return new ModelAndView(this.ecxessTimeView, "url", url);
        }
    }

    private String formatearFecha(Date fecha) {
        String nuevo = new String();
        SimpleDateFormat formato = new SimpleDateFormat("EEEE, d 'de' MMMM 'de' yyyy, hh:mm:ss a");
        nuevo = formato.format(fecha);
        return nuevo;
    }

    private String formatFecha(Date fecha) {
        SimpleDateFormat formato = new SimpleDateFormat("ddMMyyyyHHmmss");
        return formato.format(fecha);
    }

    private String estudiantesCursoMateriaExcel(Curso curso, Personal personal) {
        String dirDestino = System.getenv("SIGAA_HOME1") + "/documentos/docentes/listas_excel/";
        String archivo = "Cualitativa_" + curso.getId_curso() + "(" + this.formatFecha(new Date()) + ").xls";
        System.out.println(" agui esta"+ this.formatFecha(new Date()));
        String dirPlantilla = System.getenv("SIGAA_HOME1") + "/documentos/plantillas_excel/cualitativas_bimestral.xls";

        try {
            Workbook plantilla = Workbook.getWorkbook(new File(dirPlantilla));
            WritableWorkbook workbook = Workbook.createWorkbook(new File(dirDestino + archivo), plantilla);
            WritableSheet sheet = workbook.getSheet("CUANTITATIVAS");
            //WritableSheet sheet2 = workbook.getSheet("NOTAS_CUALITATIVAS");
            WritableFont arial_7 = new WritableFont(WritableFont.ARIAL, 9);
            WritableCellFormat arial7 = new WritableCellFormat(arial_7);
            WritableFont arial_8 = new WritableFont(WritableFont.ARIAL, 10);
            WritableCellFormat arial8 = new WritableCellFormat(arial_8);

            WritableCellFormat arial7green = new WritableCellFormat(arial_7);
//            arial7green.setBorder(Border.ALL, BorderLineStyle.DASHED, Colour.GRAY_80);
            arial7green.setBackground(Colour.LIGHT_GREEN);
            WritableCellFormat arial7blue = new WritableCellFormat(arial_7);
//            arial7blue.setBorder(Border.ALL, BorderLineStyle.DASHED, Colour.GRAY_80);
            arial7blue.setBackground(Colour.LIGHT_BLUE);
            WritableCellFormat arial7orange = new WritableCellFormat(arial_7);
            arial7orange.setBackground(Colour.LIGHT_ORANGE);
//            arial7orange.setBorder(Border.ALL, BorderLineStyle.DASHED, Colour.GRAY_80);
            arial7blue.setBackground(Colour.LIGHT_BLUE);
            WritableCellFormat arial7tur = new WritableCellFormat(arial_7);
            arial7tur.setBackground(Colour.LIME);
//            arial7tur.setBorder(Border.ALL, BorderLineStyle.DASHED, Colour.GRAY_80);

            sheet.addCell(new Label(0, 1, "GESTION ACADEMICO " + curso.getId_gestion(), arial8));
            sheet.addCell(new Label(4, 5, curso.getCurso() + " de " + curso.getCiclo(), arial8));
            sheet.addCell(new Label(4, 6, "Prof. " + personal.getNombres() + " " + personal.getPaterno() + " " + personal.getMaterno(), arial8));
            sheet.addCell(new Label(4, 7, this.formatearFecha(new Date()), arial8));

            Estudiante est;
            for (int i = 0; i < curso.getEstudiantes().size(); i++) {
                est = (Estudiante) curso.getEstudiantes().get(i);
                sheet.addCell(new jxl.write.Number(1, i + 12, (i + 1), arial7));
                sheet.addCell(new Label(2, i + 12, est.getId_estudiante(), arial7));
                sheet.addCell(new jxl.write.Number(3, i + 12, est.getCodigo(), arial7));
                sheet.addCell(new Label(4, i + 12, est.getPaterno() + " " + est.getMaterno() + " " + est.getNombres(), arial7));
                Conducta conducta;
                for (int k = 0; k < est.getConductas().size(); k++) {
                    conducta = (Conducta) est.getConductas().get(k);
                    sheet.addCell(new Label(5 + k, i + 12, conducta.getDiagnostico(), arial7));
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
