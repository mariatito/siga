/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
import spring.sysmaqv.domain.Persona;
import spring.sysmaqv.domain.Personal;
import spring.sysmaqv.domain.logic.SigaaInterface;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.util.WebUtils;
import spring.sysmaqv.domain.Conducta;
import spring.sysmaqv.domain.CursoMateria;
import spring.sysmaqv.domain.Evaluacion;
import spring.sysmaqv.domain.PagoPension;
import spring.sysmaqv.domain.PagoServicio;
import spring.sysmaqv.domain.PlanEvaluacion;
import spring.sysmaqv.domain.Servicio;
import spring.sysmaqv.domain.Tipo_pension;

/**
 * Created on : 16-jul-2010, 23:42:52
 * @author MARCO ANTONIO QUENTA VELASCO 
 */
public class InscripcionesRezagadas implements Controller {

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
                String mensaje = "true";
                String search = request.getParameter("search");
                String regNuevo = request.getParameter("regNuevo");
                String id_familia = request.getParameter("id_familia");
                if (search != null) {
                    retorno.put("listafamilia", this.sigaa.getListaFamilias(search));
                    retorno.put("search", search);
                    retorno.put("busq", "_busq");
                }
                if (regNuevo != null) {
                    retorno.put("registroNuevo", "_registroNuevo");
                }
                if (request.getParameter("guardarNuevo") != null) {
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
                    String observacion = request.getParameter("observacion");
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
                        retorno.put("observacion", observacion);
                        retorno.put("registroNuevo", "_registroNuevo");
                        retorno.put("id_gestion", id_gestion);
                        mensaje = "false";
                    } else {
                        try {
                            Familia familia = new Familia();
                            id_familia = "";
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
                                        retorno.put("observacion", observacion);
                                        retorno.put("registroNuevo", "_registroNuevo");
                                        retorno.put("id_gestion", id_gestion);
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
                            familia.setObservacion(observacion);
                            this.sigaa.setRegistrarFamilia(familia);
                        } catch (Exception e) {
                            e.printStackTrace();
                            retorno.put("registroNuevo", "_registroNuevo");
                            retorno.put("id_gestion", id_gestion);
                            mensaje = "false";
                        }
                    }
                    retorno.put("mensaje", mensaje);
                }
                if (id_familia != null) {
                    List resultados = this.sigaa.getBuscarFamilia(id_familia);
                    retorno.put("search", id_familia);
                    retorno.put("resultados", resultados);
                    Familia family = (Familia) resultados.get(0);
                    retorno.put("family", family);
                    Gestion gestion = this.sigaa.getGestionActivo();
                    Inscripcion inscripcion = new Inscripcion();
                    inscripcion.setId_familia(id_familia);
                    inscripcion.setId_gestion(gestion.getId_gestion() - 1);
                    retorno.put("_alumnos", this.sigaa.getAlumnosById_familiaAndGestion(inscripcion));
                    inscripcion.setId_familia(id_familia);
                    inscripcion.setId_gestion(gestion.getId_gestion());
                    retorno.put("alumnos", this.sigaa.getAlumnosById_familiaAndGestion(inscripcion));
                    String estNuevo = request.getParameter("estNuevo");
                    if (estNuevo != null) {
                        retorno.put("cursos", this.sigaa.getListaCursos(gestion.getId_gestion()));
                        retorno.put("gondolas", this.sigaa.getListaGondolas());
                        retorno.put("tipos_sexos", this.sigaa.getTiposSexos());
                        retorno.put("tipo_meses", this.sigaa.getTipoMeses());
                        retorno.put("ins", "ins");
                        String id_estudiante = request.getParameter("id_estudiante");
                        String id_curso = request.getParameter("id_curso");
                        if (id_estudiante != null) {
                            Estudiante estudiante = this.sigaa.getEstudianteByIdEstudiante(id_estudiante);
                            retorno.put("estudiante", estudiante);
                            retorno.put("id_curso", id_curso);
                        }
                        retorno.put("tiposPensiones", this.sigaa.getTiposPensionesByIdGestion(gestion.getId_gestion()));
                        retorno.put("servicios", this.sigaa.getServicioByID(gestion.getId_gestion()));
                        retorno.put("cuotas", this.sigaa.getListaCuotas());
                    }
                    if (request.getParameter("regEst") != null) {
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
                        String ci = request.getParameter("ci");
                        List nroHijo = this.sigaa.getNroHijoEstudiante(id_familia);
                        String nro_hijo = Integer.toString(nroHijo.size() + 1);
                        if (nombres.equals("") || paterno.equals("") || materno.equals("") || dia.equals("") || mes.equals("") || anio.equals("") || id_gestion.equals("") || codigo.equals("")) {
                            retorno.put("nombres", nombres);
                            retorno.put("paterno", paterno);
                            retorno.put("materno", materno);
                            retorno.put("id_sexo", id_sexo);
                            retorno.put("dia", dia);
                            retorno.put("mes", mes);
                            retorno.put("anio", anio);
                            retorno.put("id_curso", id_curso);
                            retorno.put("id_gondola", id_gondola);
                            retorno.put("id_gestion", id_gestion);
                            retorno.put("codigo", codigo);
                            retorno.put("ins", "ins");
                            retorno.put("tipo_meses", this.sigaa.getTipoMeses());
                            retorno.put("cursos", this.sigaa.getListaCursos(Integer.parseInt(id_gestion)));
                            retorno.put("gondolas", this.sigaa.getListaGondolas());
                            retorno.put("tipos_sexos", this.sigaa.getTiposSexos());
                            mensaje = "false";
                        } else {
                            try {
                                String id_estudiante_ant = request.getParameter("id_estudiante_ant");
                                if (id_estudiante_ant == null) {
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
                                    estudiante.setNro_hijo(Integer.parseInt(nro_hijo));
                                    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                                    Date fecha_nacimiento = format.parse(dia + "-" + mes + "-" + anio);
                                    estudiante.setFecha_nacimiento(fecha_nacimiento);
                                    this.sigaa.setRegistrarEstudiante(estudiante);
                                }

                                inscripcion = new Inscripcion();
                                inscripcion.setId_gestion(Integer.parseInt(id_gestion));
                                if (id_estudiante_ant != null) {
                                    inscripcion.setId_estudiante(id_estudiante_ant);
                                } else {
                                    inscripcion.setId_estudiante(id_familia + "-" + nro_hijo + "e");
                                }
                                inscripcion.setId_curso(id_curso);
                                inscripcion.setId_gondola(id_gondola);
                                inscripcion.setId_familia(id_familia);
                                this.sigaa.setInscripcionEstudiante(inscripcion);

                                Curso curso = new Curso();
                                curso.setId_curso(id_curso);
                                curso.setId_gestion(Integer.parseInt(id_gestion));
                                List cursoMaterias = this.sigaa.getMateriasAndPlanEvaluacionesByIdCurso(curso);
                                for (int i = 0; i < cursoMaterias.size(); i++) {
                                    CursoMateria cursomateria = (CursoMateria) cursoMaterias.get(i);
                                    for (int j = 0; j < cursomateria.getPlanEvaluaciones().size(); j++) {
                                        PlanEvaluacion planEvaluacion = (PlanEvaluacion) cursomateria.getPlanEvaluaciones().get(j);
                                        Evaluacion eva = new Evaluacion();
                                        eva.setId_estudiante(inscripcion.getId_estudiante());
                                        eva.setId_evaluacion(planEvaluacion.getId_evaluacion());
                                        eva.setDps(0);
                                        eva.setId_gestion(inscripcion.getId_gestion());
                                        eva.setId_curso(id_curso);
                                        this.sigaa.setRegistrarEvaluacionesByCursoMateria(eva);
                                    }
                                }
                                inscripcion.setId_gestion(gestion.getId_gestion() - 1);
                                retorno.put("_alumnos", this.sigaa.getAlumnosById_familiaAndGestion(inscripcion));
                                inscripcion.setId_familia(id_familia);
                                inscripcion.setId_gestion(gestion.getId_gestion());
                                List alumnos = this.sigaa.getAlumnosById_familiaAndGestion(inscripcion);
                                retorno.put("alumnos", alumnos);

                                curso.setId_curso(id_curso);
                                curso.setId_gestion(Integer.parseInt(id_gestion));
                                retorno.put("cursoMaterias", cursoMaterias);
                                retorno.put("tipos_conductas", this.sigaa.getTiposConductas());
                                retorno.put("calificaciones", "_calificaciones");
                                retorno.put("id_curso", id_curso);
                                retorno.put("id_estudiante", inscripcion.getId_estudiante());
                                retorno.put("conductas", this.sigaa.getConductasByIdEstudianteAndIdGestion(inscripcion));

                                int id_inscripcion = 0;
                                PagoPension pagopension = new PagoPension();
                                pagopension.setId_familia(id_familia);
                                for (int i = 0; i < alumnos.size(); i++) {
                                    Inscripcion ins = (Inscripcion) alumnos.get(i);
                                    if (ins.getId_estudiante().equals(id_familia + "-" + nro_hijo + "e")||ins.getId_estudiante().equals(id_estudiante_ant)) {
                                        id_inscripcion = ins.getId_inscripcion();
                                    }
                                }
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
                                this.sigaa.setRegistrarPagoPensiones(pagopension);
//
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
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        retorno.put("mensaje", mensaje);
                    }
                    if (request.getParameter("regNotas") != null) {
                        String id_curso = request.getParameter("id_curso");
                        String id_estudiante = request.getParameter("id_estudiante");
                        Curso curso = new Curso();
                        curso.setId_curso(id_curso);
                        curso.setId_gestion(Integer.parseInt(id_gestion));
                        List cursoMaterias = this.sigaa.getMateriasAndPlanEvaluacionesByIdCurso(curso);
                        retorno.put("mensaje", mensaje);
                        for (int j = 0; j < cursoMaterias.size(); j++) {
                            CursoMateria cursomateria = (CursoMateria) cursoMaterias.get(j);
                            for (int i = 0; i < cursomateria.getPlanEvaluaciones().size(); i++) {
                                PlanEvaluacion planevaluacion = (PlanEvaluacion) cursomateria.getPlanEvaluaciones().get(i);
                                Evaluacion evaluacion = new Evaluacion();
                                evaluacion.setId_estudiante(id_estudiante);
                                evaluacion.setId_evaluacion(planevaluacion.getId_evaluacion());
                                evaluacion.setNota(0);
                                evaluacion.setDps(0);
                                if (!id_curso.equals("K")) {
                                    evaluacion.setNota(Integer.parseInt(request.getParameter("nota-" + planevaluacion.getId_evaluacion())));
                                    evaluacion.setDps(Integer.parseInt(request.getParameter("dps-" + planevaluacion.getId_evaluacion())));
                                }
                                evaluacion.setCualitativa(request.getParameter("cualitativa-" + planevaluacion.getId_evaluacion()));
//                                evaluacion.setId_cualitativa(0);
                                this.sigaa.setUpdateEvaluacionByIdEstudianteAndIdEvaluacion(evaluacion);
                            }
                        }

                        inscripcion.setId_estudiante(id_estudiante);
                        inscripcion.setId_gestion(Integer.parseInt(id_gestion));
                        List conductas = this.sigaa.getConductasByIdEstudianteAndIdGestion(inscripcion);
                        for (int j = 0; j < conductas.size(); j++) {
                            Conducta conducta = (Conducta) conductas.get(j);
                            conducta.setDiagnostico("...");
                            conducta.setId_tipo_conducta("0");
                            if (id_curso.equals("K")) {
                                String diagnostico = request.getParameter("diagnostico-" + conducta.getId_conducta());
                                conducta.setDiagnostico(diagnostico);
                            } else {
                                String id_tipo_conducta = request.getParameter("id_tipo_conducta-" + conducta.getId_conducta());
                                conducta.setId_tipo_conducta(id_tipo_conducta);
                            }
                            conducta.setDps(0);
                            conducta.setId_estudiante(id_estudiante);
                            this.sigaa.setSaveConductaByIdConducta(conducta);
                        }
                    }
                }
//                String id_estudiante = request.getParameter("id_estudiante");
//                String id_curso = request.getParameter("id_curso");
//                if (id_estudiante != null) {
//                    retorno.put("calificaciones", "_calificaciones");
//                    retorno.put("id_curso", id_curso);
//                    retorno.put("id_estudiante", id_estudiante);
//                    Curso curso = new Curso();
//                    curso.setId_curso(id_curso);
//                    curso.setId_gestion(Integer.parseInt(id_gestion));
//                    retorno.put("cursoMaterias", this.sigaa.getMateriasAndPlanEvaluacionesByIdCurso(curso));
//                    retorno.put("tipos_conductas", this.sigaa.getTiposConductas());
//                    Inscripcion inscripcion = new Inscripcion();
//                    inscripcion.setId_estudiante(id_estudiante);
//                    inscripcion.setId_gestion(Integer.parseInt(id_gestion));
//                    retorno.put("conductas", this.sigaa.getConductasByIdEstudianteAndIdGestion(inscripcion));
//                }

                retorno.put("id_gestion", id_gestion);
                return new ModelAndView(this.perfectView, retorno);
            }
            retorno.put("gestion", this.sigaa.getGestionActivo());
            return new ModelAndView(this.perfectView, retorno);
        } else {
            return new ModelAndView(this.ecxessTimeView, "url", url);
        }
    }
}
