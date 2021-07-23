package spring.sysmaqv.web.spring.sigaa.adminPersonal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
public class RegistrarPersona implements Controller {

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
            String mensaje = "true";
            if (request.getParameter("opcion") != null) {
                String nombres = request.getParameter("nombres").toUpperCase();
                String paterno = request.getParameter("paterno").toUpperCase();
                String materno = request.getParameter("materno").toUpperCase();
                String ci = request.getParameter("ci");
                String direccion = request.getParameter("direccion");
                String telefono1 = request.getParameter("telefono1");
                String telefono2 = request.getParameter("telefono2");
                String id_sexo = request.getParameter("id_sexo");
                String id_tipo_usuario = request.getParameter("id_tipo_usuario").toUpperCase();
                String id_titulo_doc = request.getParameter("id_titulo_doc");
                String id_categoria_doc = request.getParameter("id_categoria_doc");
                String cargahoraria = request.getParameter("cargahoraria");
                String salario = request.getParameter("salario");
                String fecha_asume_adm = request.getParameter("fecha_asume_adm");
                String id_titulo_adm = request.getParameter("id_titulo_adm");
                String id_cargo_adm = request.getParameter("id_cargo_adm");

                if (nombres.equals("") || ci.equals("") || id_tipo_usuario.equals("A")) {
                    retorno.put("nombres", nombres);
                    retorno.put("paterno", paterno);
                    retorno.put("materno", materno);
                    retorno.put("ci", ci);
                    retorno.put("direccion", direccion);
                    retorno.put("telefono1", telefono1);
                    retorno.put("telefono2", telefono2);
                    retorno.put("id_sexo", id_sexo);
                    retorno.put("id_tipo_usuario", id_tipo_usuario);
                    retorno.put("id_titulo_doc", id_titulo_doc);
                    retorno.put("id_categoria_doc", id_categoria_doc);
                    retorno.put("cargahoraria", cargahoraria);
                    retorno.put("salario", salario);
                    retorno.put("fecha_asume_adm", fecha_asume_adm);
                    retorno.put("id_titulo_adm", id_titulo_adm);
                    retorno.put("id_cargo_adm", id_cargo_adm);

                    mensaje = "false";
                } else {
                    try {
                        //REGISTRO DE PERSONAS
                        Persona persona = new Persona();
                        String id_persona;
                        if (paterno.equals("")) {
                            id_persona = materno.substring(0, 1) + nombres.substring(0, 1) + ci;
                        } else {
                            if (materno.equals("")) {
                                id_persona = paterno.substring(0, 1) + nombres.substring(0, 1) + ci;
                            } else {
                                id_persona = paterno.substring(0, 1) + materno.substring(0, 1) + nombres.substring(0, 1) + ci;
                            }
                        }
                        persona.setId_persona(id_persona);
                        persona.setPaterno(paterno);
                        persona.setMaterno(materno);
                        persona.setNombres(nombres);
                        persona.setDip(ci);
                        persona.setDireccion(direccion);
                        persona.setTelefono1(telefono1);
                        persona.setTelefono2(telefono2);
                        persona.setId_sexo(id_sexo);
                        persona.setId_tipo_usuario(id_tipo_usuario);
                        this.sigaa.setRegistrarPersona(persona);

                        if (id_tipo_usuario.equals("DOC")) {
                            Docente docente = new Docente();
                            String id_docente = nombres.substring(0, 1) + paterno;
                            docente.setId_docente(id_docente.toLowerCase()); //id_docente ej: mquenta
                            docente.setId_persona(id_persona);
                            docente.setId_titulo(id_titulo_doc);
                            docente.setId_tipo_categoria(id_categoria_doc);
                            docente.setCargahoraria(cargahoraria);
                            docente.setSalario(salario);
                            docente.setDip(ci);
                            docente.setId_tpersonal(id_tipo_usuario);
                            this.sigaa.setRegistrarDocente(docente);
                        }
                        if (id_tipo_usuario.equals("ADM")) {
                            Administrativo administrativo = new Administrativo();
                            String id_administrativo = nombres.substring(0, 1) + paterno;
                            administrativo.setId_administrativo(id_administrativo.toLowerCase());
                            administrativo.setId_persona(id_persona);
                            String dia = request.getParameter("dia");
                            String mes = request.getParameter("mes");
                            String anio = request.getParameter("anio");
                            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                            Date fecha_asume = format.parse(dia + "-" + mes + "-" + anio);
                            administrativo.setFecha_asume(fecha_asume);
                            administrativo.setId_titulo(id_titulo_adm);
                            administrativo.setId_tipo_cargo(id_cargo_adm);
                            administrativo.setDip(ci);
                            administrativo.setId_tpersonal(id_tipo_usuario);
                            this.sigaa.setRegistrarAdministrativo(administrativo);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        retorno.put("nombres", nombres);
                        retorno.put("paterno", paterno);
                        retorno.put("materno", materno);
                        retorno.put("ci", ci);
                        retorno.put("direccion", direccion);
                        retorno.put("telefono1", telefono1);
                        retorno.put("telefono2", telefono2);
                        retorno.put("id_sexo", id_sexo);
                        retorno.put("id_tipo_usuario", id_tipo_usuario);
                        retorno.put("id_titulo_doc", id_titulo_doc);
                        retorno.put("id_categoria_doc", id_categoria_doc);
                        retorno.put("cargahoraria", cargahoraria);
                        retorno.put("salario", salario);
                        retorno.put("id_titulo_adm", id_titulo_adm);
                        retorno.put("id_cargo_adm", id_cargo_adm);
                        mensaje = "false";
                    }
                }
                retorno.put("mensaje", mensaje);
            }
            retorno.put("tipos_sexos", this.sigaa.getTiposSexos());
            retorno.put("cargos_academicos", this.sigaa.getCargosAcademicos());
            retorno.put("titulos_profesionales", this.sigaa.getTiposTitulos());
            retorno.put("tipos_cargos", this.sigaa.getTiposCargos());
            retorno.put("tipos_categorias", this.sigaa.getTiposCategorias());
            retorno.put("fecha_actual", new Date());
            retorno.put("meses", this.sigaa.getTipoMeses());
            return new ModelAndView(this.perfectView, retorno);
        } else {
            return new ModelAndView(this.ecxessTimeView, "url", url);
        }
    }
}
