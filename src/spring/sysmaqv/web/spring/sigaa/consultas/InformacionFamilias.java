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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import spring.sysmaqv.domain.Familia;
import spring.sysmaqv.domain.Gestion;
import spring.sysmaqv.domain.Inscripcion;
import spring.sysmaqv.domain.Personal;
import spring.sysmaqv.domain.logic.SigaaInterface;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.util.WebUtils;

/**
 * Created on : 10-jul-2010, 19:33:30
 * @author MARCO ANTONIO QUENTA VELASCO
 */
public class InformacionFamilias implements Controller {

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
            Gestion gestion = this.sigaa.getGestionActivo();
            retorno.put("gestion", gestion);
            if (search != null) {
                List listafamilia = this.sigaa.getListaFamilias(search);
                retorno.put("listafamilia", listafamilia);
                retorno.put("search", search);
                if (request.getParameter("imprimirPdf") != null) {
                    String pdf = this.reporteResultado(listafamilia, gestion);
                    response.sendRedirect("documentos/consultas/familias/" + pdf);
                }
                return new ModelAndView(this.perfectView, retorno);
            }
            List listafamilia = this.sigaa.getListaFamilias("");
            retorno.put("listafamilia", listafamilia);
            retorno.put("search", " ");
            if (request.getParameter("imprimirPdf") != null) {
                String pdf = this.reporteResultado(listafamilia, gestion);
                response.sendRedirect("documentos/familias/" + pdf);
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
        SimpleDateFormat formato = new SimpleDateFormat("d-MMMM-yyyy");
        nuevo = formato.format(fecha);
        return nuevo;
    }

    private String reporteResultado(List familias, Gestion gestion) {
        String dir = System.getenv("SIGAA_HOME1") + "/documentos/consultas/familias/";
        String archivo = "ReporteFamilias(" + this.formatFecha(new Date()) + ").pdf";
        Font font8 = FontFactory.getFont(FontFactory.HELVETICA, 8);
        Font font7 = FontFactory.getFont(FontFactory.HELVETICA, 7);
        Font font8b = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8);

        Document document = new Document(PageSize.LETTER);
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dir + archivo));
            document.open();
            Image png = Image.getInstance(System.getenv("SIGAA_HOME1") + "/imagenes/iconos_sigaa/sigaa_oficial.png");
            png.setAbsolutePosition(470, 740);
            png.scalePercent(25);
            document.add(png);

            document.add(new Phrase("COLEGIO \"SANTA TERESA\"", font8b));
            document.add(Chunk.NEWLINE);
            document.add(new Phrase("GESTION " + gestion.getId_gestion(), font8b));
            document.add(Chunk.NEWLINE);
            document.add(new Phrase("La Paz - Bolivia", font8b));
            document.add(Chunk.NEWLINE);
            Paragraph p = new Paragraph(new Phrase("FAMILIAS ACTIVAS"));
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);
            p = new Paragraph(new Phrase("\n", font8));
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);

            float[] columnDefinitionSize = {5F, 50F, 45F};
            PdfPTable table = new PdfPTable(columnDefinitionSize);
            table.getDefaultCell().setBorder(0);
            table.setWidthPercentage(100);

            table.getDefaultCell().setGrayFill(0.5f);
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(new Phrase("No.", font8b));
            table.addCell(new Phrase("TUTOR/ES", font8b));
            table.addCell(new Phrase("ESTUDIANTE/S(CURSO/S)", font8b));
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            table.setHeaderRows(1);
            int c = 1;
            for (int i = 0; i < familias.size(); i++) {
                Familia familia = (Familia) familias.get(i);
                if (familia.getEstado().equals("activo")) {
                    if ((c + 1) % 2 == 0) {
                        table.getDefaultCell().setGrayFill(1);
                    } else {
                        table.getDefaultCell().setGrayFill(0.9f);
                    }
                    table.addCell(new Phrase(Integer.toString(c), font7));
                    float[] columnaDef = {12F, 88F};
                    PdfPTable table1 = new PdfPTable(columnaDef);
                    table1.getDefaultCell().setBorder(0);
                    table1.setWidthPercentage(100);
                    table1.addCell(new Phrase("Código: ", font7));
                    table1.addCell(new Phrase(familia.getId_familia(), font7));
                    if (familia.getNombre_apellidos_tutor_1() != null && !familia.getNombre_apellidos_tutor_1().equals(" ") && !familia.getNombre_apellidos_tutor_1().equals("  ")) {
                        table1.addCell(new Phrase("Tutor1: ", font7));
                        if (familia.getCi_tutor_1().equals(familia.getCi_resp_pago())) {
                            table1.addCell(new Phrase(familia.getNombre_apellidos_tutor_1() + "(RP);   ci: " + familia.getCi_tutor_1(), font7));
                        } else {
                            table1.addCell(new Phrase(familia.getNombre_apellidos_tutor_1() + ";  ci: " + familia.getCi_tutor_1(), font7));
                        }
                    }
                    if (familia.getNombre_apellidos_tutor_2() != null && !familia.getNombre_apellidos_tutor_2().equals(" ") && !familia.getNombre_apellidos_tutor_2().equals("  ")) {
                        table1.addCell(new Phrase("Tutor2: ", font7));
                        if (familia.getCi_tutor_2().equals(familia.getCi_resp_pago())) {
                            table1.addCell(new Phrase(familia.getNombre_apellidos_tutor_2() + "(RP);  ci: " + familia.getCi_tutor_2(), font7));
                        } else {
                            table1.addCell(new Phrase(familia.getNombre_apellidos_tutor_2() + ";  ci: " + familia.getCi_tutor_2(), font7));
                        }
                    }
                    if (familia.getNombre_apellidos_tutor_3() != null && !familia.getNombre_apellidos_tutor_3().equals(" ") && !familia.getNombre_apellidos_tutor_3().equals("  ")) {
                        table1.addCell(new Phrase("Tutor3: ", font7));
                        if (familia.getCi_tutor_3().equals(familia.getCi_resp_pago())) {
                            table1.addCell(new Phrase(familia.getNombre_apellidos_tutor_3() + "(RP);  ci: " + familia.getCi_tutor_3(), font7));
                        } else {
                            table1.addCell(new Phrase(familia.getNombre_apellidos_tutor_3() + ";  ci: " + familia.getCi_tutor_3(), font7));
                        }
                    }
                    table1.addCell(new Phrase("Dir1: ", font7));
                    table1.addCell(new Phrase(familia.getDireccion_1(), font7));
                    table1.addCell(new Phrase("Dir2: ", font7));
                    table1.addCell(new Phrase(familia.getDireccion_2(), font7));
                    table1.addCell(new Phrase("Telef1: ", font7));
                    table1.addCell(new Phrase(familia.getTelefono_1(), font7));
                    table1.addCell(new Phrase("Telef2: ", font7));
                    table1.addCell(new Phrase(familia.getTelefono_2(), font7));
                    table1.addCell(new Phrase("Otros: ", font7));
                    table1.addCell(new Phrase(familia.getTelefonos(), font7));
                    table1.addCell(new Phrase("Mail1: ", font7));
                    table1.addCell(new Phrase(familia.getE_mail(), font7));
                    table1.addCell(new Phrase("Mail2: ", font7));
                    table1.addCell(new Phrase(familia.getE_mailRP(), font7));
                    table1.addCell(new Phrase("Obs: ", font7));
                    table1.addCell(new Phrase(familia.getObservacion(), font7));
                    table.addCell(table1);
                    float[] columnaDefi = {100F};
                    PdfPTable table2 = new PdfPTable(columnaDefi);
                    table2.getDefaultCell().setBorder(0);
                    table2.setWidthPercentage(100);
                    for (int j = 0; j < familia.getEstudiantes().size(); j++) {
                        Inscripcion ins = (Inscripcion) familia.getEstudiantes().get(j);
                        table2.addCell(new Phrase(Integer.toString(j + 1) + ".- " + ins.getNombres() + " " + ins.getPaterno() + " " + ins.getMaterno() + "(" + ins.getCurso() + " de " + ins.getCiclo() + ")", font7));
                    }
                    table.addCell(table2);
                    c++;
                }
            }
            document.add(table);
            document.add(Chunk.NEWLINE);
            p = new Paragraph(new Phrase(this.formatearFecha(new Date()), font8));
            p.setAlignment(Element.ALIGN_RIGHT);
            document.add(p);
            document.add(Chunk.NEWLINE);
            p = new Paragraph(new Phrase("SIGAA Sistema Integrado de Gestión Académico - Administrativa " + gestion.getId_gestion(), font8));
            document.add(p);

        } catch (Exception de) {
            de.printStackTrace();
        }
        document.close();
        return archivo;
    }
}
