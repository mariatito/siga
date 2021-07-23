/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.sysmaqv.web.spring.sigaa.publicaciones;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import spring.sysmaqv.domain.Personal;
import spring.sysmaqv.domain.logic.SigaaInterface;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.util.WebUtils;
import spring.sysmaqv.domain.Documento;
import spring.sysmaqv.domain.Persona;

/**
 *
 * @author Antonio
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
public class PublicacionesDireccion implements Controller {

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

            String id_persona = personal.getId_persona();
            // System.out.println("hhhhhhhhhhhh"+id_persona);
            Map retorno = new HashMap();

            Persona persona = this.sigaa.getPersonaByIdCargo(id_persona);
            retorno.put("tpersona", persona);
            String cargo = persona.getCargo();
            // System.out.println("este es el cargo"+cargo);

            String opcion = request.getParameter("opcion");

            if (opcion != null) {
                String urll = request.getParameter("url");
                if (opcion.equals("_descarga")) {
                    response.sendRedirect("documentos/docs/" + urll);
                }
            }
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            if (isMultipart) {
                String descripcion = "";
                String nota = "";
                String fichero = "";
                String fec = "";
                try {
                    FileItemFactory factory = new DiskFileItemFactory();
                    ServletFileUpload upload = new ServletFileUpload(factory);
                    List fileItems = upload.parseRequest(request);
                    Iterator it = fileItems.iterator();
                    FileItem fi;
                    String direccion = System.getenv("SIGAA_HOME") + "/documentos/docs/";
                    String nombre_archivo = "";
                    while (it.hasNext()) {
                        fi = (FileItem) it.next();
                        if (fi.getFieldName().equals("descripcion")) {
                            descripcion = fi.getString();
                        }
                        if (fi.getFieldName().equals("nota")) {
                            nota = fi.getString();
                        }
                        if (fi.getFieldName().equals("fec")) {
                            fec = fi.getString();
                        }
                        if (fi.getFieldName().equals("fichero")) {
                            fichero = fi.getString();
                            if (!fi.getString().equals("")) {
                                nombre_archivo = descripcion + "_" + fi.getName();
                                fi.write(new File(direccion + nombre_archivo));
                            }
                        }
                    }
                    Documento documento = new Documento();
                    documento.setDescripcion(descripcion);
                    documento.setNota(nota);
                    documento.setUrl(nombre_archivo);
                    documento.setArea(cargo);
                    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                    Date fe = format.parse(fec);

                    documento.setFec_exp(fe);
                    documento.setIdpersona(personal.getId_persona());
                    this.sigaa.setRegistrarDocumento(documento);

                    Date fecha = new Date();
                    String nuevo = new String();
                    SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
                    nuevo = formato.format(fecha);

                    retorno.put("fec", nuevo);

                    List documentos = this.sigaa.getListarDocumentosByArea(cargo);

                    retorno.put("documentos", documentos);
                   

                    return new ModelAndView(this.perfectView, retorno);
                } catch (Exception de) {
                    de.printStackTrace();
                    return new ModelAndView(this.perfectView, retorno);
                }
            }


            List documentos = this.sigaa.getListarDocumentosByArea(cargo);
            retorno.put("documentos", documentos);
            retorno.put("tipos_cargos", this.sigaa.getTiposCargos());

            Date fecha = new Date();
            String nuevo = new String();
            SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
            nuevo = formato.format(fecha);
            retorno.put("fec", nuevo);
            return new ModelAndView(this.perfectView, retorno);
        } else {
            return new ModelAndView(this.ecxessTimeView, "url", url);
        }
    }
}
