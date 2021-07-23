/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.sysmaqv.web.spring.sigaa.docentes;

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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import spring.sysmaqv.domain.Conducta;
import spring.sysmaqv.domain.Curso;
import spring.sysmaqv.domain.CursoMateria;
import spring.sysmaqv.domain.Estudiante;
import spring.sysmaqv.domain.Gestion;
import spring.sysmaqv.domain.Personal;
import spring.sysmaqv.domain.logic.SigaaInterface;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.util.WebUtils;

/**
 * @ Company : M&M
 * @ Author : Marco Antonio Quenta Velasco
 * @ Gestion : 2009
 * @ La Paz - Bolivia
 */
public class TutorCurso implements Controller {

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
            String update = request.getParameter("update");
            Gestion gestion = this.sigaa.getGestionActivo();
            if (id_curso != null) {
                Curso cur = new Curso();
                cur.setId_curso(id_curso);
                cur.setId_gestion(gestion.getId_gestion());
                Curso curso = this.sigaa.getCursoByIdCursoAndIdGestion(cur);
                if (update != null) {
                    Conducta conducta = new Conducta();
                    Estudiante estudiante = new Estudiante();
                    for (int i = 0; i < curso.getEstudiantes().size(); i++) {
                        estudiante = (Estudiante) curso.getEstudiantes().get(i);
                        for (int j = 0; j < estudiante.getConductas().size(); j++) {
                            conducta = (Conducta) estudiante.getConductas().get(j);
                            //if (conducta.isEstado() == true) {
                            conducta.setDiagnostico("...");
                            conducta.setId_tipo_conducta("0");
                            if (id_curso.equals("K")||id_curso.equals("K1")||id_curso.equals("KT")) {
                                String diagnostico = request.getParameter("diagnostico-" + conducta.getId_conducta() + "-" + estudiante.getId_estudiante());
                                conducta.setDiagnostico(diagnostico);
                            } else {
                                String id_tipo_conducta = request.getParameter("id_tipo_conducta-" + conducta.getId_conducta() + "-" + estudiante.getId_estudiante());
                                conducta.setId_tipo_conducta(id_tipo_conducta);
                            }
                            conducta.setDps(0);
                            conducta.setId_estudiante(estudiante.getId_estudiante());
                            this.sigaa.setSaveConductaByIdConducta(conducta);
                            //}
                        }
                    }
                }
                curso = this.sigaa.getCursoByIdCursoAndIdGestion(cur);
                curso.setId_gestion(gestion.getId_gestion());
                retorno.put("curso", curso);
                retorno.put("pdf", this.conductasParaleloPdf(curso));
                retorno.put("tipos_conductas", this.sigaa.getTiposConductas());
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
        SimpleDateFormat formato = new SimpleDateFormat("EEEE, d 'de' MMMM 'de' yyyy");
        nuevo = formato.format(fecha);
        return nuevo;
    }

    private String formatFecha(Date fecha) {
        String nuevo = new String();
        SimpleDateFormat formato = new SimpleDateFormat("d 'de' MMMM 'de' yyyy");
        nuevo = formato.format(fecha);
        return nuevo;
    }

    private String conductasParaleloPdf(Curso curso) {
        String dir = System.getenv("SIGAA_HOME1") + "/documentos/docentes/";
        String archivo = "Conductas " + curso.getCurso() + " de " + curso.getCiclo() + " (" + this.formatFecha(new Date()) + ").pdf";
        Font font8 = FontFactory.getFont(FontFactory.HELVETICA, 7);
        Font font8b = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8);
        Font font2space = FontFactory.getFont(FontFactory.HELVETICA, 2);

        Document document = new Document(PageSize.LETTER);
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dir + archivo));
            float width = document.getPageSize().getWidth();
            document.open();
            Image png = Image.getInstance(System.getenv("SIGAA_HOME1") + "/imagenes/iconos_sigaa/sigaa_oficial.png");
            png.setAbsolutePosition(470, 740);
            png.scalePercent(25);
            document.add(png);
            document.add(Chunk.NEWLINE);
            document.add(new Phrase("COLEGIO \"SANTA TERESA\"", font8b));
            document.add(Chunk.NEWLINE);
            document.add(new Phrase("GESTION " + curso.getId_gestion(), font8b));
            document.add(Chunk.NEWLINE);
            document.add(new Phrase("La Paz - Bolivia", font8b));
            document.add(Chunk.NEWLINE);
            Paragraph p = new Paragraph(new Phrase("CONDUCTAS"));
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);
            p = new Paragraph(new Phrase("Curso " + curso.getCurso() + " de " + curso.getCiclo(), font8));
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);
            document.add(new Phrase(" ", font2space));

            float[] columnDefinitionSize = {5, 10, 40, 15, 15, 15};
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
            table.addCell(new Phrase("1er. Trimestre", font8));
            table.addCell(new Phrase("2do. Trimestre", font8));
            table.addCell(new Phrase("3er. Trimestre", font8));
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
                table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
                for (int j = 0; j < estudiante.getConductas().size(); j++) {
                    Conducta conducta = (Conducta) estudiante.getConductas().get(j);
                    table.addCell(new Phrase(conducta.getTipo_conducta(), font8));
                }
            }
            document.add(table);
            document.add(Chunk.NEWLINE);
            p = new Paragraph(new Phrase(this.formatearFecha(new Date()), font8));
            p.setAlignment(Element.ALIGN_RIGHT);
            document.add(p);
            p = new Paragraph(new Phrase("SIGAA Sistema Integrado de Gestión Académico - Administrativa " + curso.getId_gestion(), font8));
            document.add(p);

        } catch (Exception de) {
            de.printStackTrace();
        }
        document.close();
        return archivo;
    }
}
