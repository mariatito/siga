package spring.sysmaqv.web.spring.sigaa.inscripciones;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import spring.sysmaqv.domain.Curso;
import spring.sysmaqv.domain.Estudiante;
import spring.sysmaqv.domain.Familia;
import spring.sysmaqv.domain.Gestion;
import spring.sysmaqv.domain.Inscripcion;
import spring.sysmaqv.domain.PagoPension;
import spring.sysmaqv.domain.PagoServicio;
import spring.sysmaqv.domain.Persona;
import spring.sysmaqv.domain.Personal;
import spring.sysmaqv.domain.Servicio;
import spring.sysmaqv.domain.Tipo_pension;
import spring.sysmaqv.domain.logic.SigaaInterface;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.util.WebUtils;
import spring.sysmaqv.domain.PagoCole;

/**
 * @author Marco Antonio Quenta Velasco
 * @since 29.5.2009
 */
public class Inscripciones implements Controller {

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
            String regNuevo = request.getParameter("regNuevo");
            String guardarNuevo = request.getParameter("guardarNuevo");
            String estNuevo = request.getParameter("estNuevo");
            String busca = request.getParameter("busca");
            String regEst = request.getParameter("regEst");
            String id_estudiante = request.getParameter("id_estudiante");
            String reInsEst = request.getParameter("reInsEst");
            String search1 = request.getParameter("search1");
            String updateFamily = request.getParameter("updateFamily");
            String mensaje;
            retorno.put("tipo_meses", this.sigaa.getTipoMeses());
            retorno.put("gestion", this.sigaa.getGestionActivo());

            if (updateFamily != null) {
                String nombres1 = request.getParameter("nombres1").toUpperCase();
                String ci1 = request.getParameter("ci1");
                String nombres2 = request.getParameter("nombres2").toUpperCase();
                String ci2 = request.getParameter("ci2");
                String nombres3 = request.getParameter("nombres3").toUpperCase();
                String ci3 = request.getParameter("ci3");
                String resp = request.getParameter("resp");
                String dir1 = request.getParameter("dir1");
                String tf1 = request.getParameter("tf1");
                String dir2 = request.getParameter("dir2");
                String tf2 = request.getParameter("tf2");
                String telefonos = request.getParameter("telefonos");
                String e_mail = request.getParameter("e_mail");
                String e_mailRP = request.getParameter("e_mailRP");
                String observacion = request.getParameter("observacion");
                String id_familia = request.getParameter("id_familia");
                String lugtrab = request.getParameter("lugtrab");
                mensaje = "false";
                if (!resp.equals(null) && !nombres1.equals("") && !ci1.equals("") && !dir1.equals("") && !tf1.equals("")) {
                    Familia familia = new Familia();
                    familia.setId_familia(id_familia);
                    familia.setNombre_apellidos_tutor_1(nombres1);
                    familia.setNombre_apellidos_tutor_2(nombres2);
                    familia.setNombre_apellidos_tutor_3(nombres3);
                    familia.setCi_tutor_1(ci1);
                    familia.setCi_tutor_2(ci2);
                    familia.setCi_tutor_3(ci3);
                    if (resp.equals("tutor1")) {
                        familia.setCi_resp_pago(ci1);
                    } else {
                        if (resp.equals("tutor2")) {
                            familia.setCi_resp_pago(ci2);
                        } else {
                            familia.setCi_resp_pago(ci3);
                        }
                    }
                    familia.setDireccion_1(dir1);
                    familia.setDireccion_2(dir2);
                    familia.setTelefono_1(tf1);
                    familia.setTelefono_2(tf2);
                    familia.setTelefonos(telefonos);
                    familia.setE_mail(e_mail);
                    familia.setObservacion(observacion);
                    familia.setE_mailRP(e_mailRP);
                    familia.setLugtrab(lugtrab);
                    this.sigaa.setUpdateFamilia(familia);
                    mensaje = "true";
                }
                retorno.put("mensaje", mensaje);
                search = id_familia;
            }
            if (search1 != null) {


                /*
                recepcionar radiobuton 0:fami, 1=est
                 * List listafamilia=null;
                 * if(0 o 1)
                 * 
                 * listafamilia = this.sigaa.getListaFamiliasPor est(search1);
                 */
                //String telefonos = request.getParameter("telefonos");
                System.out.println(request.getParameter("tipobus"));
                List listafamilia = null;
                String sel = "";
                if (request.getParameter("tipobus").equals("fam")) {
                    listafamilia = this.sigaa.getListaFamilias(search1);
                    sel = "fam";
                } else if (request.getParameter("tipobus").equals("est")) {
                    listafamilia = this.sigaa.getListaFamiliasEst(search1);
                    sel = "est";
                }

                retorno.put("listafamilia", listafamilia);
                retorno.put("search", search1);
                retorno.put("sel", sel);
                return new ModelAndView(this.perfectView, retorno);
            }
            if (search != null) {
                List resultados = this.sigaa.getBuscarFamilia(search.toUpperCase());
                retorno.put("search", search.toUpperCase());
                if (resultados.size() == 0) {
                    retorno.put("resul", "sin_datos");
                } else {
                    retorno.put("resultados", resultados);
                    Familia family = (Familia) resultados.get(0);
                    retorno.put("family", family);
                    retorno.put("gestion", this.sigaa.getGestionActivo());
                    Date fecha = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                    int anio_actual = Integer.parseInt(sdf.format(fecha));
                    retorno.put("anio_actual", anio_actual);
                    Inscripcion inscripcion = new Inscripcion();
                    inscripcion.setId_familia(search);
                    inscripcion.setId_gestion(anio_actual - 1);
                    retorno.put("_alumnos", this.sigaa.getAlumnosById_familiaAndGestion(inscripcion));
                    inscripcion.setId_familia(search);
                    inscripcion.setId_gestion(anio_actual);
                    retorno.put("alumnos", this.sigaa.getAlumnosById_familiaAndGestion(inscripcion));
                }
                retorno.put("intro", "intro");
                return new ModelAndView(this.perfectView, retorno);
            }
            if (regNuevo == null) {
                List listafamilia = this.sigaa.getListaFamilias("");
                retorno.put("listafamilia", listafamilia);
                retorno.put("sel", "fam");
            }
            if (regNuevo != null) {
                retorno.put("registroNuevo", "nuevo");
                retorno.put("intro", "intro");
                return new ModelAndView(this.perfectView, retorno);
            }
            if (guardarNuevo != null) {
                String nombres1 = request.getParameter("nombres1").toUpperCase();
                String paterno1 = request.getParameter("paterno1").toUpperCase();
                String materno1 = request.getParameter("materno1").toUpperCase();
                String ci1 = request.getParameter("ci1");
                String dir1 = request.getParameter("dir1");
                String tf1 = request.getParameter("tf1");
                String nombres2 = request.getParameter("nombres2").toUpperCase();
                String paterno2 = request.getParameter("paterno2").toUpperCase();
                String materno2 = request.getParameter("materno2").toUpperCase();
                String ci2 = request.getParameter("ci2");
                String dir2 = request.getParameter("dir2");
                String tf2 = request.getParameter("tf2");
                String nombres3 = request.getParameter("nombres3").toUpperCase();
                String paterno3 = request.getParameter("paterno3").toUpperCase();
                String materno3 = request.getParameter("materno3").toUpperCase();
                String ci3 = request.getParameter("ci3");
                String resp = request.getParameter("resp");
                String telefonos = request.getParameter("telefonos");
                String e_mail = request.getParameter("e_mail");
                String e_mailRP = request.getParameter("e_mailRP");
                String lugtrab = request.getParameter("lugtrab");
                String observacion = request.getParameter("observacion");

                mensaje = "true";
                if (nombres1.equals("") || paterno1.equals("") || materno1.equals("") || ci1.equals("") || dir1.equals("") || tf1.equals("") || resp == null) {
                    retorno.put("nombres1", nombres1);
                    retorno.put("paterno1", paterno1);
                    retorno.put("materno1", materno1);
                    retorno.put("ci1", ci1);
                    retorno.put("dir1", dir1);
                    retorno.put("tf1", tf1);
                    retorno.put("nombres2", nombres2);
                    retorno.put("paterno2", paterno2);
                    retorno.put("materno2", materno2);
                    retorno.put("ci2", ci2);
                    retorno.put("dir2", dir2);
                    retorno.put("tf2", tf2);
                    retorno.put("nombres3", nombres3);
                    retorno.put("paterno3", paterno3);
                    retorno.put("materno3", materno3);
                    retorno.put("ci3", ci3);
                    retorno.put("telefonos", telefonos);
                    retorno.put("e_mail", e_mail);
                    retorno.put("e_mailRP", e_mailRP);
                    retorno.put("lugtrab", lugtrab);
                    retorno.put("observacion", observacion);
                    retorno.put("registroNuevo", "nuevo");
                    retorno.put("intro", "intro");
                    mensaje = "false";
                } else {
                    mensaje = "true";
                    try {
                        Familia familia = new Familia();
                        String id_familia = "";
                        String ci_resp_pago = "";
                        if (resp.equals("tutor1") && !nombres1.equals("") && !paterno1.equals("") && !materno1.equals("") && !ci1.equals("")) {
                            if (paterno1.equals("")) {
                                id_familia = materno1.substring(0, 1) + nombres1.substring(0, 1) + ci1;
                            } else {
                                if (materno1.equals("")) {
                                    id_familia = paterno1.substring(0, 1) + nombres1.substring(0, 1) + ci1;
                                } else {
                                    id_familia = paterno1.substring(0, 1) + materno1.substring(0, 1) + nombres1.substring(0, 1) + ci1;
                                }
                            }
                            ci_resp_pago = ci1;
                        } else {
                            if (resp.equals("tutor2") && !nombres2.equals("") && !paterno2.equals("") && !materno2.equals("") && !ci2.equals("")) {
                                if (paterno2.equals("")) {
                                    id_familia = materno2.substring(0, 1) + nombres2.substring(0, 1) + ci2;
                                } else {
                                    if (materno2.equals("")) {
                                        id_familia = paterno2.substring(0, 1) + nombres2.substring(0, 1) + ci2;
                                    } else {
                                        id_familia = paterno2.substring(0, 1) + materno2.substring(0, 1) + nombres2.substring(0, 1) + ci2;
                                    }
                                }
                                ci_resp_pago = ci2;
                            } else {
                                if (resp.equals("tutor3") && !nombres3.equals("") && !paterno3.equals("") && !materno3.equals("") && !ci3.equals("")) {
                                    if (paterno1.equals("")) {
                                        id_familia = materno3.substring(0, 1) + nombres3.substring(0, 1) + ci3;
                                    } else {
                                        if (materno3.equals("")) {
                                            id_familia = paterno3.substring(0, 1) + nombres3.substring(0, 1) + ci3;
                                        } else {
                                            id_familia = paterno3.substring(0, 1) + materno3.substring(0, 1) + nombres3.substring(0, 1) + ci3;
                                        }
                                    }
                                    ci_resp_pago = ci3;
                                } else {
                                    retorno.put("nombres1", nombres1);
                                    retorno.put("paterno1", paterno1);
                                    retorno.put("materno1", materno1);
                                    retorno.put("ci1", ci1);
                                    retorno.put("dir1", dir1);
                                    retorno.put("tf1", tf1);
                                    retorno.put("nombres2", nombres2);
                                    retorno.put("paterno2", paterno2);
                                    retorno.put("materno2", materno2);
                                    retorno.put("ci2", ci2);
                                    retorno.put("dir2", dir2);
                                    retorno.put("tf2", tf2);
                                    retorno.put("nombres3", nombres3);
                                    retorno.put("paterno3", paterno3);
                                    retorno.put("materno3", materno3);
                                    retorno.put("ci3", ci3);
                                    retorno.put("telefonos", telefonos);
                                    retorno.put("e_mail", e_mail);
                                    retorno.put("e_mailRP", e_mailRP);
                                    retorno.put("lugtrab", lugtrab);
                                    retorno.put("observacion", observacion);
                                    retorno.put("registroNuevo", "nuevo");
                                    retorno.put("intro", "intro");
                                    retorno.put("mensaje", "false");
                                    return new ModelAndView(this.perfectView, retorno);
                                }
                            }
                        }
                        familia.setId_familia(id_familia);
                        familia.setNombre_apellidos_tutor_1(nombres1 + " " + paterno1 + " " + materno1);
                        familia.setNombre_apellidos_tutor_2(nombres2 + " " + paterno2 + " " + materno2);
                        familia.setNombre_apellidos_tutor_3(nombres3 + " " + paterno3 + " " + materno3);
                        familia.setCi_tutor_1(ci1);
                        familia.setCi_tutor_2(ci2);
                        familia.setCi_tutor_3(ci3);
                        familia.setCi_resp_pago(ci_resp_pago);
                        familia.setDireccion_1(dir1);
                        familia.setDireccion_2(dir2);
                        familia.setTelefono_1(tf1);
                        familia.setTelefono_2(tf2);
                        familia.setTelefonos(telefonos);
                        familia.setE_mail(e_mail);
                        familia.setE_mailRP(e_mailRP);
                        familia.setLugtrab(lugtrab);
                        familia.setObservacion(observacion);
                        this.sigaa.setRegistrarFamilia(familia);

                        List resultados = this.sigaa.getBuscarFamilia(id_familia);
                        retorno.put("search", id_familia);
                        retorno.put("sel", "fam");
                        retorno.put("resultados", resultados);
                        Familia family = (Familia) resultados.get(0);
                        retorno.put("family", family);
                        retorno.put("gestion", this.sigaa.getGestionActivo());
                        Date fecha = new Date();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                        int anio_actual = Integer.parseInt(sdf.format(fecha));
                        retorno.put("anio_actual", anio_actual);
                    } catch (Exception e) {
                        e.printStackTrace();
                        retorno.put("intro", "intro");
                        mensaje = "false";
                    }
                    retorno.put("mensaje", mensaje);
                    retorno.put("intro", "intro");
                    return new ModelAndView(this.perfectView, retorno);
                }
                retorno.put("mensaje", mensaje);
                return new ModelAndView(this.perfectView, retorno);
            }
            if (estNuevo != null) {
                List resultados = this.sigaa.getBuscarFamilia(busca);
                retorno.put("sel", "fam");
                retorno.put("search", busca);
                retorno.put("resultados", resultados);
                Familia family = (Familia) resultados.get(0);
                retorno.put("family", family);
                retorno.put("ins", "ins");
                Gestion gestion = this.sigaa.getGestionActivo();
                retorno.put("tipo_meses", this.sigaa.getTipoMeses());
                retorno.put("cursos", this.sigaa.getListaCursos(gestion.getId_gestion()));
                retorno.put("gondolas", this.sigaa.getListaGondolas());
                retorno.put("intro", "intro");
                retorno.put("tipos_sexos", this.sigaa.getTiposSexos());
                Date fecha = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                int anio_actual = Integer.parseInt(sdf.format(fecha));
                retorno.put("gestion", gestion);
                retorno.put("anio_actual", anio_actual);
                Inscripcion inscripcion = new Inscripcion();
                inscripcion.setId_familia(busca);
                inscripcion.setId_gestion(anio_actual - 1);
                retorno.put("_alumnos", this.sigaa.getAlumnosById_familiaAndGestion(inscripcion));
                inscripcion.setId_familia(busca);
                inscripcion.setId_gestion(anio_actual);
                retorno.put("alumnos", this.sigaa.getAlumnosById_familiaAndGestion(inscripcion));
                retorno.put("tiposPensiones", this.sigaa.getTiposPensionesByIdGestion(gestion.getId_gestion()));
                retorno.put("servicios", this.sigaa.getServicioByID(gestion.getId_gestion()));
                retorno.put("cuotas", this.sigaa.getListaCuotas());
                retorno.put("id_curso", "K");
                retorno.put("cuota", "1");
                return new ModelAndView(this.perfectView, retorno);
            }
            if (regEst != null) {
                String id_familia = request.getParameter("id_familia");
                List resultados = this.sigaa.getBuscarFamilia(id_familia);
                retorno.put("sel", "fam");
                retorno.put("search", id_familia);
                retorno.put("resultados", resultados);
                Familia family = (Familia) resultados.get(0);
                retorno.put("family", family);
                retorno.put("intro", "intro");
                mensaje = "true";
                String codigo = request.getParameter("codigo");
                String nombres = request.getParameter("nombres").toUpperCase();
                String paterno = request.getParameter("paterno").toUpperCase();
                String materno = request.getParameter("materno").toUpperCase();
                String id_sexo = request.getParameter("id_sexo");
                String dia = request.getParameter("dia");
                String mes = request.getParameter("mes");
                String anio = request.getParameter("anio");
                String id_curso = request.getParameter("id_curso");
                String id_gondola = request.getParameter("id_gondola");
                String id_gestion = request.getParameter("id_gestion");
                String ci = request.getParameter("ci");
                String carnet = request.getParameter("carnet");
                List nroHijo = this.sigaa.getNroHijoEstudiante(id_familia);//numero de hijos de la familia
                String nro_hijo = Integer.toString(nroHijo.size() + 1);
                if (nombres.equals("") || paterno.equals("") || materno.equals("") || dia.equals("") || mes.equals("") || anio.equals("") || id_gestion.equals("") || codigo.equals("")) {
                    retorno.put("nombres", nombres);
                    retorno.put("paterno", paterno);
                    retorno.put("materno", materno);
                    retorno.put("id_sexo", id_sexo);
                    retorno.put("dia", dia);
                    retorno.put("mes", mes);
                    retorno.put("anio", anio);
                    retorno.put("nro_hijo", nro_hijo);
                    retorno.put("id_curso", id_curso);
                    retorno.put("id_gondola", id_gondola);
                    retorno.put("id_gestion", id_gestion);
                    retorno.put("codigo", codigo);
                    retorno.put("carnet", carnet);
                    retorno.put("ins", "ins");
                    retorno.put("tipo_meses", this.sigaa.getTipoMeses());
                    retorno.put("cursos", this.sigaa.getListaCursos(Integer.parseInt(id_gestion)));
                    retorno.put("gondolas", this.sigaa.getListaGondolas());
                    retorno.put("tipos_sexos", this.sigaa.getTiposSexos());
                    Gestion gestion = this.sigaa.getGestionActivo();
                    retorno.put("gestion", gestion);
                    Date fecha = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                    int anio_actual = Integer.parseInt(sdf.format(fecha));
                    retorno.put("anio_actual", anio_actual);
                    Inscripcion inscripcion = new Inscripcion();
                    inscripcion.setId_familia(id_familia);
                    inscripcion.setId_gestion(anio_actual - 1);
                    retorno.put("_alumnos", this.sigaa.getAlumnosById_familiaAndGestion(inscripcion));
                    inscripcion.setId_familia(id_familia);
                    inscripcion.setId_gestion(anio_actual);
                    retorno.put("alumnos", this.sigaa.getAlumnosById_familiaAndGestion(inscripcion));
                    //getPensionesByIdGestion
                    retorno.put("tiposPensiones", this.sigaa.getTiposPensionesByIdGestion(gestion.getId_gestion()));
                    retorno.put("servicios", this.sigaa.getServicioByID(gestion.getId_gestion()));
                    retorno.put("cuotas", this.sigaa.getListaCuotas());
                    mensaje = "false";
                } else {
                    try {
                        String pago_cole = request.getParameter("pago_cole");
                        PagoCole pagocole = new PagoCole();
                        if (pago_cole != null) {
                            String desc_pagoCole = request.getParameter("desc_pagoCole");
                            String nro_factura = request.getParameter("nro_factura");
                            double monto_cole = Double.parseDouble(request.getParameter("monto_cole"));
                            pagocole.setDescripcion(desc_pagoCole);
                            pagocole.setNro_factura(nro_factura);
                            pagocole.setMonto(monto_cole);
                            pagocole.setCancelado(false);
                            if (monto_cole > 0) {
                                pagocole.setCancelado(true);
                            }
                            pagocole.setFecha_pago(new Date());
                        }
                        Persona persona = new Persona();
                        Estudiante estudiante = new Estudiante();
                        estudiante.setCodigo(Integer.parseInt(codigo));

                        persona.setId_persona(id_familia + "-" + nro_hijo);
                        persona.setPaterno(paterno);
                        persona.setMaterno(materno);
                        persona.setNombres(nombres);
                        persona.setDip(ci + "-" + nro_hijo);
                        persona.setId_sexo(id_sexo);
                        persona.setId_tipo_usuario("EST");
                        this.sigaa.setRegistrarPersona(persona);

                        estudiante.setId_estudiante(id_familia + "-" + nro_hijo + "e");
                        estudiante.setId_persona(id_familia + "-" + nro_hijo);
                        estudiante.setId_familia(id_familia);
                        estudiante.setCarnet(carnet);
                        estudiante.setNro_hijo(Integer.parseInt(nro_hijo));
                        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                        Date fecha_nacimiento = format.parse(dia + "-" + mes + "-" + anio);
                        estudiante.setFecha_nacimiento(fecha_nacimiento);
                        this.sigaa.setRegistrarEstudiante(estudiante);

                        Inscripcion inscripcion = new Inscripcion();
                        inscripcion.setId_gestion(Integer.parseInt(id_gestion));
                        inscripcion.setId_estudiante(id_familia + "-" + nro_hijo + "e");
                        inscripcion.setId_curso(id_curso);
                        inscripcion.setId_gondola(id_gondola);
                        inscripcion.setId_familia(id_familia);
                        inscripcion.setNombre_foto(null);
                        this.sigaa.setInscripcionEstudiante(inscripcion);

                        Date fecha = new Date();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                        int anio_actual = Integer.parseInt(sdf.format(fecha));
                        Gestion gestion = this.sigaa.getGestionActivo();
                        retorno.put("gestion", gestion);
                        retorno.put("anio_actual", anio_actual);
                        inscripcion.setId_familia(id_familia);
                        inscripcion.setId_gestion(anio_actual - 1);
                        retorno.put("_alumnos", this.sigaa.getAlumnosById_familiaAndGestion(inscripcion));
                        inscripcion.setId_familia(id_familia);
                        inscripcion.setId_gestion(anio_actual);
                        List alumnos = this.sigaa.getAlumnosById_familiaAndGestion(inscripcion);
                        retorno.put("alumnos", alumnos);

                        int id_inscripcion = 0;
                        PagoPension pagopension = new PagoPension();
                        pagopension.setId_familia(id_familia);
                        for (int i = 0; i < alumnos.size(); i++) {
                            Inscripcion ins = (Inscripcion) alumnos.get(i);
                            if (ins.getId_estudiante().equals(id_familia + "-" + nro_hijo + "e")) {
                                id_inscripcion = ins.getId_inscripcion();
                            }
                        }
                        pagopension.setId_inscripcion(id_inscripcion);
                        pagopension.setId_gestion(anio_actual);
                        List tiposPensiones = this.sigaa.getTiposPensionesByIdGestion(Integer.parseInt(id_gestion));
                        for (int i = 0; i < tiposPensiones.size(); i++) {
                            Tipo_pension tipo_pension = (Tipo_pension) tiposPensiones.get(i);
                            if (tipo_pension.getId_curso().equals(id_curso)) {
                                pagopension.setMonto_inicial(tipo_pension.getCuota_inicial());
                                pagopension.setMonto_total(tipo_pension.getCuota_total());
                            }
                        }
                        pagopension.setDescuento(0);
                        pagopension.setBeca(0);
                        pagopension.setPension_total(0);
                        pagopension.setCuota(Integer.parseInt(request.getParameter("cuota")));
                        pagopension.setId_usuario(personal.getId_personal());
                        pagopension.setCodigo(estudiante.getCodigo());
                        this.sigaa.setRegistrarPagoPensiones(pagopension);

                        List servicios = this.sigaa.getServicioByID(gestion.getId_gestion());
                        for (int j = 0; j < servicios.size(); j++) {
                            Servicio servicio = (Servicio) servicios.get(j);
                            String id_servicio = request.getParameter("serv-" + servicio.getId_servicio());
                            String cancelado = request.getParameter("cancel-" + servicio.getId_servicio());
                            if (id_servicio != null) {
                                PagoServicio pagoservicio = new PagoServicio();
                                pagoservicio.setId_familia(id_familia);
                                pagoservicio.setId_inscripcion(id_inscripcion);
                                pagoservicio.setId_servicio(servicio.getId_servicio());
                                pagoservicio.setId_gestion(Integer.parseInt(id_gestion));
                                if (cancelado != null) {
                                    pagoservicio.setEstado("cancelado");
                                    pagoservicio.setEditable(false);
                                } else {
                                    pagoservicio.setEstado("pendiente");
                                    pagoservicio.setEditable(true);
                                }
                                pagoservicio.setId_usuario(personal.getId_personal());
                                this.sigaa.setRegistrarPagoServicios(pagoservicio);

                            }
                        }
                        inscripcion.setId_inscripcion(id_inscripcion);
                        pagocole.setId_inscripcion(id_inscripcion);
                        if (pago_cole != null) {
                            this.sigaa.setRegistrarPagoColeByIdInscripcion(pagocole);
                        }
//                        this.estudiantesCursoMateriaPdf(persona, inscripcion);
                    } catch (Exception e) {
                        e.printStackTrace();
                        retorno.put("nombres", nombres);
                        retorno.put("paterno", paterno);
                        retorno.put("materno", materno);
                        retorno.put("id_sexo", id_sexo);
                        retorno.put("dia", dia);
                        retorno.put("mes", mes);
                        retorno.put("anio", anio);
                        retorno.put("nro_hijo", nro_hijo);
                        retorno.put("id_curso", id_curso);
                        retorno.put("id_gondola", id_gondola);
                        retorno.put("id_gestion", id_gestion);
                        retorno.put("codigo", codigo);
                        retorno.put("carnet", carnet);
                        retorno.put("ins", "ins");
                        retorno.put("tipo_meses", this.sigaa.getTipoMeses());
                        retorno.put("cursos", this.sigaa.getListaCursos(Integer.parseInt(id_gestion)));
                        retorno.put("gondolas", this.sigaa.getListaGondolas());
                        retorno.put("tipos_sexos", this.sigaa.getTiposSexos());
                        Gestion gestion = this.sigaa.getGestionActivo();
                        retorno.put("gestion", gestion);
                        Date fecha = new Date();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                        int anio_actual = Integer.parseInt(sdf.format(fecha));
                        retorno.put("anio_actual", anio_actual);
                        Inscripcion inscripcion = new Inscripcion();
                        inscripcion.setId_familia(id_familia);
                        inscripcion.setId_gestion(anio_actual - 1);
                        retorno.put("_alumnos", this.sigaa.getAlumnosById_familiaAndGestion(inscripcion));
                        inscripcion.setId_familia(id_familia);
                        inscripcion.setId_gestion(anio_actual);
                        retorno.put("alumnos", this.sigaa.getAlumnosById_familiaAndGestion(inscripcion));
                        retorno.put("tiposPensiones", this.sigaa.getTiposPensionesByIdGestion(gestion.getId_gestion()));
                        retorno.put("servicios", this.sigaa.getServicioByID(gestion.getId_gestion()));
                        retorno.put("cuotas", this.sigaa.getListaCuotas());
                        mensaje = "false";
                    }
                }
                retorno.put("mensaje", mensaje);
                return new ModelAndView(this.perfectView, retorno);
            }

            if (id_estudiante != null) {
                String opcion = request.getParameter("opcion");
                if (opcion != null) {
                    if (opcion.equals("_update")) {
                        PagoServicio pagoservicio = new PagoServicio();
                        pagoservicio.setId_familia(busca);
                        pagoservicio.setId_inscripcion(Integer.parseInt(request.getParameter("id_inscripcion")));
                        pagoservicio.setId_servicio(Integer.parseInt(request.getParameter("id_servicio")));
                        pagoservicio.setEstado("cancelado");
                        String fec_reg = request.getParameter("fec_reg");
                        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                        Date fec = format.parse(fec_reg);
                        pagoservicio.setFec_pago(fec);
                        pagoservicio.setEditable(false);
                        pagoservicio.setId_usuario(personal.getId_personal());
                        this.sigaa.setUpdatePagoServicio(pagoservicio);
                    }
                    if (opcion.equals("_delete")) {
                        PagoServicio pagoservicio = new PagoServicio();
                        pagoservicio.setId_familia(busca);
                        pagoservicio.setId_inscripcion(Integer.parseInt(request.getParameter("id_inscripcion")));
                        pagoservicio.setId_servicio(Integer.parseInt(request.getParameter("id_servicio")));
                        this.sigaa.setDeletePagoServicio(pagoservicio);
                    }
                    if (opcion.equals("_newService")) {
                        Gestion gestion = this.sigaa.getGestionActivo();
                        List servicios = this.sigaa.getServicioByID(gestion.getId_gestion());
                        String id_inscripcion = request.getParameter("id_inscripcion");
                        List pagoServicios = this.sigaa.getServiciosByIdEstudiante(Integer.parseInt(id_inscripcion));
                        for (int j = 0; j < servicios.size(); j++) {
                            Servicio servicio = (Servicio) servicios.get(j);
                            String id_servicio = request.getParameter("serv-" + servicio.getId_servicio());
                            int sw = 0;
                            for (int i = 0; i < pagoServicios.size(); i++) {
                                PagoServicio pagoservicio = (PagoServicio) pagoServicios.get(i);
                                if (servicio.getId_servicio() == pagoservicio.getId_servicio()) {
                                    sw = 1;
                                }
                            }
                            if (sw == 0 && id_servicio != null) {
                                PagoServicio pagoservicio = new PagoServicio();
                                pagoservicio.setId_familia(busca);
                                pagoservicio.setId_inscripcion(Integer.parseInt(id_inscripcion));
                                pagoservicio.setId_servicio(servicio.getId_servicio());
                                pagoservicio.setId_gestion(servicio.getId_gestion());
                                pagoservicio.setEstado("pendiente");
                                pagoservicio.setId_usuario(personal.getId_personal());
                                this.sigaa.setRegistrarPagoServicios(pagoservicio);
                            }
                        }
                    }
                }
                List resultados = this.sigaa.getBuscarFamilia(busca);
                retorno.put("sel", "fam");
                retorno.put("search", busca);
                retorno.put("resultados", resultados);
                Familia family = (Familia) resultados.get(0);
                retorno.put("family", family);
                String id_inscripcion = request.getParameter("id_inscripcion");
                if (id_inscripcion != null) {
                    retorno.put("tipo_meses", this.sigaa.getTipoMeses());
                    retorno.put("insc", this.sigaa.getInscripcionByIdInscripcion(Integer.parseInt(id_inscripcion)));
                    retorno.put("edit", "edit");
                    PagoPension pagoPension = this.sigaa.getPensionByIdEstudiante(Integer.parseInt(id_inscripcion));
                    if (pagoPension != null) {
                        retorno.put("pagoPension", pagoPension);
                        retorno.put("cuota", pagoPension.getCuota());
                    }
                    List pagoServicios = this.sigaa.getServiciosByIdEstudiante(Integer.parseInt(id_inscripcion));
                    retorno.put("meses", this.sigaa.getTipoMeses());
                    retorno.put("pagoServicios", pagoServicios);
                    if (pagoServicios.size() != 0) {
                        retorno.put("pagoServicios", pagoServicios);
                        int sw = 0;
                        for (int j = 0; j < pagoServicios.size(); j++) {
                            PagoServicio ser = (PagoServicio) pagoServicios.get(j);
                            if (ser.isEditable() == true) {
                                sw++;
                            }
                        }
                        if (sw == pagoServicios.size()) {
                            retorno.put("modific", "yes");
                        } else {
                            retorno.put("modific", "no");
                        }
                    }

                } else {
                    retorno.put("ins2", "ins2");
                    retorno.put("cuota", "1");
                }
                Gestion gestion = this.sigaa.getGestionActivo();
                retorno.put("cursos", this.sigaa.getListaCursos(gestion.getId_gestion()));
                retorno.put("gondolas", this.sigaa.getListaGondolas());
                retorno.put("intro", "intro");
                retorno.put("tipos_sexos", this.sigaa.getTiposSexos());
                Date fecha = new Date();
                retorno.put("dma", fecha);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                int anio_actual = Integer.parseInt(sdf.format(fecha));
                retorno.put("gestion", gestion);
                retorno.put("anio_actual", anio_actual);
                Inscripcion inscripcion = new Inscripcion();
                inscripcion.setId_familia(busca);
                inscripcion.setId_gestion(anio_actual - 1);
                retorno.put("_alumnos", this.sigaa.getAlumnosById_familiaAndGestion(inscripcion));
                inscripcion.setId_familia(busca);
                inscripcion.setId_gestion(anio_actual);
                retorno.put("alumnos", this.sigaa.getAlumnosById_familiaAndGestion(inscripcion));
                Estudiante est = this.sigaa.getEstudianteByIdEstudiante(id_estudiante);
                retorno.put("estudiante", est);
                String id_curso = request.getParameter("id_curso");
                retorno.put("id_curso", id_curso);//////////////////77
                Curso curso = new Curso();
                curso.setId_curso(id_curso);
                curso.setId_gestion(gestion.getId_gestion());
                retorno.put("curso", this.sigaa.getCursoByIdCursoAndIdGestion(curso));
                retorno.put("tiposPensiones", this.sigaa.getTiposPensionesByIdGestion(gestion.getId_gestion()));
                retorno.put("servicios", this.sigaa.getServicioByID(gestion.getId_gestion()));
                retorno.put("cuotas", this.sigaa.getListaCuotas());
            }
            if (reInsEst != null) {
                String id_familia = request.getParameter("id_familia");
                List resultados = this.sigaa.getBuscarFamilia(id_familia);
                retorno.put("sel", "fam");
                retorno.put("search", id_familia);
                retorno.put("resultados", resultados);
                Familia family = (Familia) resultados.get(0);
                retorno.put("family", family);
                retorno.put("intro", "intro");
                mensaje = "true";
                String id_curso = request.getParameter("id_curso");
                String id_gondola = request.getParameter("id_gondola");
                String id_gestion = request.getParameter("id_gestion");
                String id_est = request.getParameter("id_est");
                String cod = request.getParameter("codigo");
                String carnet = request.getParameter("carnet");
                String dia = request.getParameter("dia");
                String mes = request.getParameter("mes");
                String anio = request.getParameter("anio");
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                Date fecha_nacimiento = format.parse(dia + "-" + mes + "-" + anio);
                Estudiante est= new Estudiante();
                est.setId_estudiante(id_est);
                est.setCarnet(carnet);
                est.setFecha_nacimiento(fecha_nacimiento);
                this.sigaa.setUpdateEstudianteByIdest(est);// Actualiza solo carnet y feccha de nacimiento en las inscripciones
                try {
                    String pago_cole = request.getParameter("pago_cole");
                    PagoCole pagocole = new PagoCole();
                    if (pago_cole != null) {
                        String desc_pagoCole = request.getParameter("desc_pagoCole");
                        String nro_factura = request.getParameter("nro_factura");
                        double monto_cole = Double.parseDouble(request.getParameter("monto_cole"));
                        pagocole.setDescripcion(desc_pagoCole);
                        pagocole.setNro_factura(nro_factura);
                        pagocole.setMonto(monto_cole);
                        pagocole.setCancelado(false);
                        if (monto_cole > 0) {
                            pagocole.setCancelado(true);
                        }
                        pagocole.setFecha_pago(new Date());
                    }
                    Inscripcion inscripcion = new Inscripcion();
                    inscripcion.setId_gestion(Integer.parseInt(id_gestion));
                    inscripcion.setId_estudiante(id_est);
                    inscripcion.setId_curso(id_curso);
                    inscripcion.setId_gondola(id_gondola);
                    inscripcion.setId_familia(id_familia);
                    inscripcion.setNombre_foto(cod + "-" + (Integer.parseInt(id_gestion) - 1) + ".jpg");
                    List alumnos = this.sigaa.getAlumnosById_familiaAndGestion(inscripcion);
                    int id_inscripcion = 0;
                    for (int i = 0; i < alumnos.size(); i++) {
                        Inscripcion ins = (Inscripcion) alumnos.get(i);
                        if (ins.getId_estudiante().equals(id_est)) {
                            id_inscripcion = ins.getId_inscripcion();
                        }
                    }
                    if (id_inscripcion != 0) {
                        mensaje = "false";
                    } else {
                        this.sigaa.setInscripcionEstudiante(inscripcion);
//                        Persona persona = this.sigaa.getPersonaByIdEstudiante(id_est);
                        alumnos = this.sigaa.getAlumnosById_familiaAndGestion(inscripcion);
                        int codigo = 0;
                        for (int i = 0; i < alumnos.size(); i++) {
                            Inscripcion ins = (Inscripcion) alumnos.get(i);
                            if (ins.getId_estudiante().equals(id_est)) {
                                id_inscripcion = ins.getId_inscripcion();
                                codigo = ins.getCodigo();
                            }
                        }
                        PagoPension pagopension = new PagoPension();
                        pagopension.setId_familia(id_familia);
                        pagopension.setId_inscripcion(id_inscripcion);
                        pagopension.setId_gestion(Integer.parseInt(id_gestion));
                        List tiposPensiones = this.sigaa.getTiposPensionesByIdGestion(Integer.parseInt(id_gestion));
                        for (int i = 0; i < tiposPensiones.size(); i++) {
                            Tipo_pension tipo_pension = (Tipo_pension) tiposPensiones.get(i);
                            if (tipo_pension.getId_curso().equals(id_curso)) {
                                pagopension.setMonto_inicial(tipo_pension.getCuota_inicial());
                                pagopension.setMonto_total(tipo_pension.getCuota_total());
                            }
                        }
                        pagopension.setDescuento(0);
                        pagopension.setBeca(0);
                        pagopension.setPension_total(0);
                        pagopension.setCuota(Integer.parseInt(request.getParameter("cuota")));
                        pagopension.setId_usuario(personal.getId_personal());
                        pagopension.setCodigo(codigo);
                        this.sigaa.setRegistrarPagoPensiones(pagopension);

                        List servicios = this.sigaa.getServicioByID(Integer.parseInt(id_gestion));
                        for (int j = 0; j < servicios.size(); j++) {
                            Servicio servicio = (Servicio) servicios.get(j);
                            String id_servicio = request.getParameter("serv-" + servicio.getId_servicio());
                            String cancelado = request.getParameter("cancel-" + servicio.getId_servicio());
                            if (id_servicio != null) {
                                PagoServicio pagoservicio = new PagoServicio();
                                pagoservicio.setId_familia(id_familia);
                                pagoservicio.setId_inscripcion(id_inscripcion);
                                pagoservicio.setId_servicio(servicio.getId_servicio());
                                pagoservicio.setId_gestion(Integer.parseInt(id_gestion));
                                if (cancelado != null) {
                                    pagoservicio.setEstado("cancelado");
                                    pagoservicio.setEditable(false);
                                } else {
                                    pagoservicio.setEstado("pendiente");
                                    pagoservicio.setEditable(true);
                                }
                                pagoservicio.setId_usuario(personal.getId_personal());
                                this.sigaa.setRegistrarPagoServicios(pagoservicio);
                            }
                        }
                        inscripcion.setId_inscripcion(id_inscripcion);
                        pagocole.setId_inscripcion(id_inscripcion);
                        if (pago_cole != null) {
                            this.sigaa.setRegistrarPagoColeByIdInscripcion(pagocole);
                        }
//                        this.estudiantesCursoMateriaPdf(persona, inscripcion);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    mensaje = "false";
                }
                Date fecha = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                int anio_actual = Integer.parseInt(sdf.format(fecha));
                retorno.put("gestion", this.sigaa.getGestionActivo());
                retorno.put("anio_actual", anio_actual);
                Inscripcion inscripcion = new Inscripcion();
                inscripcion.setId_familia(id_familia);
                inscripcion.setId_gestion(anio_actual - 1);
                retorno.put("_alumnos", this.sigaa.getAlumnosById_familiaAndGestion(inscripcion));
                inscripcion.setId_familia(id_familia);
                inscripcion.setId_gestion(anio_actual);
                retorno.put("alumnos", this.sigaa.getAlumnosById_familiaAndGestion(inscripcion));
                retorno.put("mensaje", mensaje);
                return new ModelAndView(this.perfectView, retorno);
            }
            String modific = request.getParameter("modific");
            if (modific != null) {
                String id_familia = request.getParameter("id_familia");
                List resultados = this.sigaa.getBuscarFamilia(id_familia);
                retorno.put("sel", "fam");
                retorno.put("search", id_familia);
                retorno.put("resultados", resultados);
                Familia family = (Familia) resultados.get(0);
                retorno.put("family", family);
                retorno.put("intro", "intro");
                mensaje = "true";
                String id_persona = request.getParameter("id_persona");
                String id_est = request.getParameter("id_est");
                String codigo = request.getParameter("codigo");
                String nombres = request.getParameter("nombres").toUpperCase();
                String paterno = request.getParameter("paterno").toUpperCase();
                String materno = request.getParameter("materno").toUpperCase();
                String id_sexo = request.getParameter("id_sexo");
                String dia = request.getParameter("dia");
                String mes = request.getParameter("mes");
                String anio = request.getParameter("anio");
                String id_curso = request.getParameter("id_curso");
                String id_curso_ant = request.getParameter("id_curso_ant");
                String id_gondola = request.getParameter("id_gondola");
                String id_gestion = request.getParameter("id_gestion");
                Inscripcion inscripcion = new Inscripcion();
                inscripcion.setId_curso(id_curso);
                inscripcion.setId_estudiante(id_est);
                inscripcion.setId_gestion(Integer.parseInt(id_gestion));

                Persona persona = new Persona();
                persona.setId_persona(id_persona);
                persona.setPaterno(paterno);
                persona.setMaterno(materno);
                persona.setNombres(nombres);
                persona.setId_sexo(id_sexo);
                this.sigaa.setUpdatePersona(persona);

                Estudiante estudiante = new Estudiante();
                estudiante.setCodigo(Integer.parseInt(codigo));
                estudiante.setId_estudiante(id_est);
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                Date fecha_nacimiento = format.parse(dia + "-" + mes + "-" + anio);
                estudiante.setFecha_nacimiento(fecha_nacimiento);
                this.sigaa.setUpdateEstudiante(estudiante);


                String id_inscripcion = request.getParameter("id_inscripcion");
                Estudiante est = this.sigaa.getEstudianteByIdEstudiante(id_est);
                if (est.getNroEvasDefinidos() != 0) {
                    inscripcion.setId_curso(id_curso_ant);
//                    this.estudiantesCursoMateriaPdf(persona, inscripcion);
                    mensaje = "false";
                } else {
                    inscripcion.setId_inscripcion(Integer.parseInt(id_inscripcion));
                    inscripcion.setId_gondola(id_gondola);
                    this.sigaa.setUpdateInscripcion(inscripcion);
//                    this.estudiantesCursoMateriaPdf(persona, inscripcion);
                }
                PagoPension pagoP = this.sigaa.getPensionByIdEstudiante(Integer.parseInt(id_inscripcion));
                if (pagoP != null) {
                    if (pagoP.isEditable() == true) {
                        PagoPension pagopension = new PagoPension();
                        pagopension.setId_familia(id_familia);
                        pagopension.setId_inscripcion(Integer.parseInt(id_inscripcion));
                        List tiposPensiones = this.sigaa.getTiposPensionesByIdGestion(Integer.parseInt(id_gestion));
                        for (int i = 0; i < tiposPensiones.size(); i++) {
                            Tipo_pension tipo_pension = (Tipo_pension) tiposPensiones.get(i);
                            if (tipo_pension.getId_curso().equals(id_curso)) {
                                pagopension.setMonto_inicial(tipo_pension.getCuota_inicial());
                                pagopension.setMonto_total(tipo_pension.getCuota_total());
                            }
                        }
                        /*-----*/
                        double monto_total_a_operar = pagopension.getMonto_inicial() + pagopension.getMonto_total();
                        double monto_x_pension = Math.round((monto_total_a_operar / 10) * Math.pow(10, 0)) / Math.pow(10, 0);
                        double diferencia = monto_x_pension - pagopension.getMonto_inicial();
                        /*-----*/
                        pagopension.setDescuento(0);
                        pagopension.setBeca(0);
                        pagopension.setPension_total(0);
                        pagopension.setCuota(Integer.parseInt(request.getParameter("cuota")));
                        pagopension.setEditable(true);
                        pagopension.setId_usuario(personal.getId_personal());
                        this.sigaa.setUpdatePagoPensiones(pagopension, diferencia);
                    }

                    List pagoServicios = this.sigaa.getServiciosByIdEstudiante(Integer.parseInt(id_inscripcion));
                    if (pagoServicios.size() != 0) {
                        int sw = 0;
                        for (int j = 0; j < pagoServicios.size(); j++) {
                            PagoServicio ser = (PagoServicio) pagoServicios.get(j);
                            if (ser.isEditable() == false) {
                                sw++;
                            }
                        }
                        if (sw == 0) {
//                            this.sigaa.setDeleteServiciosByIdInscripcion(Integer.parseInt(id_inscripcion));
                            List servicios = this.sigaa.getServicioByID(pagoP.getId_gestion());
                            for (int j = 0; j < servicios.size(); j++) {
                                Servicio servicio = (Servicio) servicios.get(j);
                                String id_servicio = request.getParameter("serv-" + servicio.getId_servicio());
                                String cancelado = request.getParameter("cancel-" + servicio.getId_servicio());
                                if (id_servicio != null) {
                                    PagoServicio pagoservicio = new PagoServicio();
                                    pagoservicio.setId_familia(id_familia);
                                    pagoservicio.setId_inscripcion(Integer.parseInt(id_inscripcion));
                                    pagoservicio.setId_servicio(servicio.getId_servicio());
                                    pagoservicio.setId_gestion(Integer.parseInt(id_gestion));
                                    if (cancelado != null) {
                                        pagoservicio.setEstado("cancelado");
                                        pagoservicio.setEditable(false);
                                    } else {
                                        pagoservicio.setEstado("pendiente");
                                        pagoservicio.setEditable(true);
                                    }
                                    pagoservicio.setId_usuario(personal.getId_personal());
                                    this.sigaa.setRegistrarPagoServicios(pagoservicio);
                                }
                            }
                        }
                    } else {
                        List servicios = this.sigaa.getServicioByID(pagoP.getId_gestion());
                        for (int j = 0; j < servicios.size(); j++) {
                            Servicio servicio = (Servicio) servicios.get(j);
                            String id_servicio = request.getParameter("serv-" + servicio.getId_servicio());
                            String cancelado = request.getParameter("cancel-" + servicio.getId_servicio());
                            if (id_servicio != null) {
                                PagoServicio pagoservicio = new PagoServicio();
                                pagoservicio.setId_familia(id_familia);
                                pagoservicio.setId_inscripcion(Integer.parseInt(id_inscripcion));
                                pagoservicio.setId_servicio(servicio.getId_servicio());
                                pagoservicio.setId_gestion(Integer.parseInt(id_gestion));
                                if (cancelado != null) {
                                    pagoservicio.setEstado("cancelado");
                                    pagoservicio.setEditable(false);
                                } else {
                                    pagoservicio.setEstado("pendiente");
                                    pagoservicio.setEditable(true);
                                }
                                pagoservicio.setId_usuario(personal.getId_personal());
                                this.sigaa.setRegistrarPagoServicios(pagoservicio);
                            }
                        }
                    }
                }

                Date fecha = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                int anio_actual = Integer.parseInt(sdf.format(fecha));
                retorno.put("gestion", this.sigaa.getGestionActivo());
                retorno.put("anio_actual", anio_actual);
                inscripcion.setId_familia(id_familia);
                inscripcion.setId_gestion(anio_actual - 1);
                retorno.put("_alumnos", this.sigaa.getAlumnosById_familiaAndGestion(inscripcion));
                inscripcion.setId_familia(id_familia);
                inscripcion.setId_gestion(anio_actual);
                retorno.put("alumnos", this.sigaa.getAlumnosById_familiaAndGestion(inscripcion));
                retorno.put("mensaje", mensaje);
                return new ModelAndView(this.perfectView, retorno);
            }
            return new ModelAndView(this.perfectView, retorno);
        } else {
            return new ModelAndView(this.ecxessTimeView, "url", url);
        }
    }
//    private String formatearFecha(Date fecha) {
//        String nuevo = new String();
//        SimpleDateFormat formato = new SimpleDateFormat("EEEE, d 'de' MMMM 'de' yyyy");
//        nuevo = formato.format(fecha);
//        return nuevo;
//    }
//    private String estudiantesCursoMateriaPdf(Persona persona, Inscripcion inscripcion) {
//        Curso curso = this.sigaa.getCursoByIdCurso(inscripcion.getId_curso());
//        List materias = this.sigaa.getMateriasByIdCurso(inscripcion.getId_curso());
//        PagoPension pagopension = this.sigaa.getPensionByIdEstudiante(Integer.toString(inscripcion.getId_inscripcion()));
////        List pagoServicios = this.sigaa.getServiciosByIdEstudiante(Integer.toString(inscripcion.getId_inscripcion()));
//        String dir = System.getenv("SIGAA_HOME") + "/documentos/boleta_inscripcion/";
//        String archivo = inscripcion.getId_estudiante() + "&" + inscripcion.getId_gestion() + ".pdf";
//        Font font8 = FontFactory.getFont(FontFactory.HELVETICA, 8);
//        Font font8b = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8);
//
//        Document document = new Document(PageSize.LETTER);
//        try {
//
//            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dir + archivo));
//            document.open();
//            document.addTitle("BOLETA DE INSCRIPCION");
//            document.addAuthor("Marco Antonio Quenta Velasco");
//            document.addSubject("COLST");
//            document.addKeywords("MAQV");
//            Image png = Image.getInstance(System.getenv("SIGAA_HOME") + "/imagenes/iconos_sigaa/sigaa_oficial.png");
//            png.setAbsolutePosition(470, 740);
//            png.scalePercent(25);
//            document.add(png);
//
//            document.add(new Phrase("COLEGIO \"SANTA TERESA\"", font8b));
//            document.add(Chunk.NEWLINE);
//            document.add(new Phrase("GESTION " + inscripcion.getId_gestion(), font8b));
//            document.add(Chunk.NEWLINE);
//            document.add(new Phrase("La Paz - Bolivia", font8b));
//            document.add(Chunk.NEWLINE);
//            Paragraph p = new Paragraph(new Phrase("BOLETA DE INSCRIPCIÓN"));
//            p.setAlignment(Element.ALIGN_CENTER);
//            document.add(p);
//            p = new Paragraph(new Phrase(this.formatearFecha(new Date()) + "\n\n", font8));
//            p.setAlignment(Element.ALIGN_CENTER);
//            document.add(p);
//
//            PdfPCell cell = null;
//            PdfPTable table2 = new PdfPTable(3);
//            int headerwidths[] = {10, 5, 85}; // percentage
//            table2.getDefaultCell().setBorder(0);
//            table2.setWidths(headerwidths);
//            table2.setWidthPercentage(100);
//
//            cell = new PdfPCell(new Phrase("Estudiante", font8));
//            cell.setGrayFill(0.9f);
//            cell.setBorder(0);
//            table2.addCell(cell);
//            cell = new PdfPCell(new Phrase(":", font8));
//            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cell.setBorder(0);
//            table2.addCell(cell);
//            cell = new PdfPCell(new Phrase(persona.getPaterno() + " " + persona.getMaterno() + " " + persona.getNombres(), font8b));
//            cell.setBorder(0);
//            table2.addCell(cell);
//
//            cell = new PdfPCell(new Phrase("Curso", font8));
//            cell.setGrayFill(0.9f);
//            cell.setBorder(0);
//            table2.addCell(cell);
//            cell = new PdfPCell(new Phrase(":", font8));
//            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cell.setBorder(0);
//            table2.addCell(cell);
//            cell = new PdfPCell(new Phrase(curso.getCurso() + " de " + curso.getCiclo(), font8b));
//            cell.setBorder(0);
//            table2.addCell(cell);
//
//            cell = new PdfPCell(new Phrase("Gestión", font8));
//            cell.setGrayFill(0.9f);
//            cell.setBorder(0);
//            table2.addCell(cell);
//            cell = new PdfPCell(new Phrase(":", font8));
//            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cell.setBorder(0);
//            table2.addCell(cell);
//            cell = new PdfPCell(new Phrase(Integer.toString(inscripcion.getId_gestion()), font8b));
//            cell.setBorder(0);
//            table2.addCell(cell);
//
//            float[] columnDefinitionSize = {100};
//            PdfPTable table = new PdfPTable(columnDefinitionSize);
//            table.getDefaultCell().setBorder(0);
//            table.getDefaultCell().setPadding(3);
//            table.setHorizontalAlignment(0);
//            table.setWidthPercentage(100);
//            for (int i = 0; i < materias.size(); i++) {
//                Materia materia = (Materia) materias.get(i);
//                table.addCell(new Phrase(materia.getMateria(), font8));
//            }
//            cell = new PdfPCell(new Phrase("Materias", font8));
//            cell.setGrayFill(0.9f);
//            cell.setBorder(0);
//            table2.addCell(cell);
//            cell = new PdfPCell(new Phrase(":", font8));
//            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cell.setBorder(0);
//            table2.addCell(cell);
//            cell = new PdfPCell(table);
//            cell.setBorder(0);
//            table2.addCell(cell);
//            document.add(table2);
//
//            PdfPTable tabled = new PdfPTable(7);
//            int twidths[] = {15, 15, 10, 25, 15, 10, 10}; // percentage
//            tabled.getDefaultCell().setBorder(0);
//            tabled.setWidths(twidths);
//            tabled.setWidthPercentage(100);
//
//            cell = new PdfPCell(new Phrase("PENSIONES (No oficial)", font8b));
//            cell.setGrayFill(0.9f);
//            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cell.setBorder(0);
//            cell.setColspan(7);
//            tabled.addCell(cell);
//
//            tabled.getDefaultCell().setGrayFill(0.9f);
//            tabled.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
//            tabled.addCell(new Phrase("Monto inicial", font8b));
//            tabled.addCell(new Phrase("Monto anual", font8b));
//            tabled.addCell(new Phrase("Nro. cuotas", font8b));
//            tabled.addCell(new Phrase("Meses de pago", font8b));
//            tabled.addCell(new Phrase("Monto por cuota", font8b));
//            tabled.addCell(new Phrase("Descuento", font8b));
//            tabled.addCell(new Phrase("Beca", font8b));
//
//            tabled.getDefaultCell().setGrayFill(1f);
//            tabled.addCell(new Phrase(Double.toString(pagopension.getMonto_inicial()), font8b));
//            tabled.addCell(new Phrase(Double.toString(pagopension.getMonto_total()), font8b));
//            tabled.addCell(new Phrase(Integer.toString(pagopension.getId_cuota()), font8b));
//
//            PdfPTable tb = new PdfPTable(1);
//            int wtb[] = {100}; // percentage
//            tb.getDefaultCell().setBorder(0);
//            tb.setWidths(wtb);
//            tb.setWidthPercentage(100);
//            tb.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
//            if (pagopension.getId_cuota() == 1) {
//                tb.addCell(new Phrase("Febrero", font8b));
//            } else if (pagopension.getId_cuota() == 3) {
//                tb.addCell(new Phrase("Abril", font8b));
//                tb.addCell(new Phrase("Julio", font8b));
//                tb.addCell(new Phrase("Octubre", font8b));
//            } else if (pagopension.getId_cuota() == 9) {
//                tb.addCell(new Phrase("Marzo", font8b));
//                tb.addCell(new Phrase("Abril", font8b));
//                tb.addCell(new Phrase("Mayo", font8b));
//                tb.addCell(new Phrase("Junio", font8b));
//                tb.addCell(new Phrase("Julio", font8b));
//                tb.addCell(new Phrase("Agosto", font8b));
//                tb.addCell(new Phrase("Septiembre", font8b));
//                tb.addCell(new Phrase("Octubre", font8b));
//                tb.addCell(new Phrase("Noviembre", font8b));
//            } else if (pagopension.getId_cuota() == 10) {
//                tb.addCell(new Phrase("Febrero", font8b));
//                tb.addCell(new Phrase("Marzo", font8b));
//                tb.addCell(new Phrase("Abril", font8b));
//                tb.addCell(new Phrase("Mayo", font8b));
//                tb.addCell(new Phrase("Junio", font8b));
//                tb.addCell(new Phrase("Julio", font8b));
//                tb.addCell(new Phrase("Agosto", font8b));
//                tb.addCell(new Phrase("Septiembre", font8b));
//                tb.addCell(new Phrase("Octubre", font8b));
//                tb.addCell(new Phrase("Noviembre", font8b));
//            }
//            tabled.addCell(tb);
//            tabled.addCell(new Phrase(Double.toString(pagopension.getPension_mensual()), font8b));
//            tabled.addCell(new Phrase(Integer.toString(pagopension.getDescuento()), font8b));
//            tabled.addCell(new Phrase(Integer.toString(pagopension.getBeca()), font8b));
//            document.add(tabled);
//            document.add(Chunk.NEWLINE);
//            p = new Paragraph(new Phrase("SIGAA Sistema Integrado de Gestión Académico - Administrativa " + inscripcion.getId_gestion(), font8));
//            document.add(p);
//        } catch (Exception de) {
//            de.printStackTrace();
//        }
//        document.close();
//        return archivo;
//    }
}
