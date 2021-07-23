package spring.sysmaqv.web.spring.sigaa.adminPersonal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spring.sysmaqv.domain.Administrativo;
import spring.sysmaqv.domain.Docente;
import spring.sysmaqv.domain.Persona;
import spring.sysmaqv.domain.Personal;
import spring.sysmaqv.domain.logic.SigaaInterface;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.util.WebUtils;

/**
 * @author Marco Antonio Quenta Velasco
 * @since 14.5.2009
 */
public class ActualizarDatos implements Controller {

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
            String id_persona = request.getParameter("id_persona");
            if (id_persona != null) {
                String opcion = request.getParameter("opcion");
                if (opcion == null) {
                    Persona persona = this.sigaa.getPersonaById(id_persona);
                    retorno.put("persona", persona);
                    retorno.put("tipos_sexos", this.sigaa.getTiposSexos());
                    retorno.put("cargos_academicos", this.sigaa.getCargosAcademicos());
                    retorno.put("titulos_profesionales", this.sigaa.getTiposTitulos());
                    retorno.put("tipos_cargos", this.sigaa.getTiposCargos());
                    retorno.put("tipos_categorias", this.sigaa.getTiposCategorias());
                    retorno.put("meses", this.sigaa.getTipoMeses());
                    if (persona.getId_tipo_usuario().equals("ADM")) {
                        Administrativo administrativo = this.sigaa.getAdministrativoByIdPersona(persona.getId_persona());
                        retorno.put("administrativo", administrativo);
                    }
                    if (persona.getId_tipo_usuario().equals("DOC")) {
                        Docente docente = this.sigaa.getDocenteByIdPersona(persona.getId_persona());
                        retorno.put("docente", docente);
                    }
                    return new ModelAndView(this.perfectView, retorno);
                } else {
                    if (opcion.equals("_update")) {
                        String nombres = request.getParameter("nombres").toUpperCase();
                        String paterno = request.getParameter("paterno").toUpperCase();
                        String materno = request.getParameter("materno").toUpperCase();
                        String ci = request.getParameter("ci");
                        String direccion = request.getParameter("direccion");
                        String telefono1 = request.getParameter("telefono1");
                        String telefono2 = request.getParameter("telefono2");
                        String id_sexo = request.getParameter("id_sexo");
                        String id_tipo_usuario = request.getParameter("id_tipo_usuario");
                        String mensaje = "true";
                        try {
                            Persona persona = new Persona();
                            persona.setId_persona(id_persona);
                            persona.setPaterno(paterno);
                            persona.setMaterno(materno);
                            persona.setNombres(nombres);
                            persona.setDip(ci);
                            persona.setDireccion(direccion);
                            persona.setTelefono1(telefono1);
                            persona.setTelefono2(telefono2);
                            persona.setId_sexo(id_sexo);
                            this.sigaa.setActualizarPersona(persona);

                            if (id_tipo_usuario.equals("DOC")) {
                                String id_docente = request.getParameter("id_docente");
                                String id_titulo_doc = request.getParameter("id_titulo_doc");
                                String id_categoria_doc = request.getParameter("id_categoria_doc");
                                String cargahoraria = request.getParameter("cargahoraria");
                                Docente docente = new Docente();
                                docente.setId_docente(id_docente);
                                docente.setId_persona(id_persona);
                                docente.setId_titulo(id_titulo_doc);
                                docente.setId_tipo_categoria(id_categoria_doc);
                                docente.setCargahoraria(cargahoraria);
                                this.sigaa.setUpdateDocente(docente);
                            }
                            if (id_tipo_usuario.equals("ADM")) {
                                String id_administrativo = request.getParameter("id_administrativo");
                                String dia = request.getParameter("dia");
                                String mes = request.getParameter("mes");
                                String anio = request.getParameter("anio");
                                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                                Date fecha_asume = format.parse(dia + "-" + mes + "-" + anio);
                                String id_titulo_adm = request.getParameter("id_titulo_adm");
                                String id_cargo_adm = request.getParameter("id_cargo_adm");
                                Administrativo administrativo = new Administrativo();
                                administrativo.setId_administrativo(id_administrativo);
                                administrativo.setId_persona(id_persona);
                                administrativo.setFecha_asume(fecha_asume);
                                administrativo.setId_titulo(id_titulo_adm);
                                administrativo.setId_tipo_cargo(id_cargo_adm);
                                this.sigaa.setUpdateAdministrativo(administrativo);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            mensaje = "false";
                        }
                        retorno.put("mensaje", mensaje);
                        List resultados = this.sigaa.getListaPersonas();
                        retorno.put("resultados", resultados);
                        return new ModelAndView(this.perfectView, retorno);
                    }
                }
            }
            if (search != null) {
                List resultados = this.sigaa.getBuscarPersona(search);
                retorno.put("resultados", resultados);
                retorno.put("search", search);
                return new ModelAndView(this.perfectView, retorno);
            }
            if (search == null) {
                List resultados = this.sigaa.getListaPersonas();
                retorno.put("resultados", resultados);
                return new ModelAndView(this.perfectView, retorno);
            }
            return new ModelAndView(this.perfectView, retorno);
        } else {
            return new ModelAndView(this.ecxessTimeView, "url", url);
        }
    }
}
