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
import java.lang.Math.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import spring.sysmaqv.domain.Curso;
import spring.sysmaqv.domain.Familia;
import spring.sysmaqv.domain.Gestion;
import spring.sysmaqv.domain.Inscripcion;
import spring.sysmaqv.domain.PagoPension;
import spring.sysmaqv.domain.PagoServicio;
import spring.sysmaqv.domain.Personal;
import spring.sysmaqv.domain.Servicio;
import spring.sysmaqv.domain.Tipo_pension;
import spring.sysmaqv.domain.logic.SigaaInterface;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.util.WebUtils;
import spring.sysmaqv.domain.DepositoAsignada;

/** 
 *
 * @author Marco Antonio Quenya Velasco
 */
public class AsignacionPagos implements Controller {

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
                String id_curso = request.getParameter("id_curso");
                if (id_curso != null) {
                    retorno.put("id_curso", id_curso);
                    retorno.put("curso_act", this.sigaa.getBuscarCurso(id_curso).get(0));
                    String search = request.getParameter("search");
                    String regPagos = request.getParameter("regPagos");
                    String mensaje = "true";
//                    Date fecha = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                    retorno.put("meses", this.sigaa.getTipoMeses());
                    if (search != null) {
                        String opcion = request.getParameter("opcion");
                        if (opcion != null) {
                            if (opcion.equals("_update")) {
                                PagoServicio pagoservicio = new PagoServicio();
                                pagoservicio.setId_familia(search);
                                pagoservicio.setId_inscripcion(Integer.parseInt(request.getParameter("id_insc")));
                                pagoservicio.setId_servicio(Integer.parseInt(request.getParameter("id_serv")));
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
                                pagoservicio.setId_familia(search);
                                pagoservicio.setId_inscripcion(Integer.parseInt(request.getParameter("id_insc")));
                                pagoservicio.setId_servicio(Integer.parseInt(request.getParameter("id_serv")));
                                this.sigaa.setDeletePagoServicio(pagoservicio);
                            }
                            if (opcion.equals("_newService")) {
                                int anio_actual = Integer.parseInt(id_gestion);
                                List servicios = this.sigaa.getServicioByID(anio_actual);
                                String id_inscripcion = request.getParameter("id_insc");
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
                                        pagoservicio.setId_familia(search);
                                        pagoservicio.setId_inscripcion(Integer.parseInt(id_inscripcion));
                                        pagoservicio.setId_servicio(servicio.getId_servicio());
                                        pagoservicio.setId_gestion(servicio.getId_gestion());
                                        pagoservicio.setEstado("pendiente");
                                        pagoservicio.setId_usuario(personal.getId_personal());
                                        this.sigaa.setRegistrarPagoServicios(pagoservicio);
                                    }
                                }
                            }
                            if (opcion.equals("_regObs")) {
                                String id_dep_asignada = request.getParameter("id_dep_asignada");
                                String observacion = request.getParameter("observacion");
                                DepositoAsignada depositoasignada = new DepositoAsignada();
                                depositoasignada.setId_dep_asignada(Integer.parseInt(id_dep_asignada));
                                depositoasignada.setObservacion(observacion);
                                this.sigaa.setUpdateObservacionByIdDepAsignada(depositoasignada);
                            }
                            if (opcion.equals("_reAnularAsignacionPagos")) {
                                PagoPension pagopension = new PagoPension();
                                pagopension.setId_familia(search);
                                pagopension.setId_gestion(Integer.parseInt(id_gestion));
                                this.sigaa.setUpdateEstadoPagoPensionesByIdFamiliaAndIdGestion(pagopension);
//                                this.sigaa.getPagoPensionesByIdFamiliaAndIdGestion(pagopension)
                            }
                        }
                        List resultados = this.sigaa.getBuscarFamilia(search);
                        retorno.put("search", search.toUpperCase());
                        if (resultados.size() == 0) {
                            retorno.put("resul", "sin_datos");
                        } else {
                            Gestion gestion = this.sigaa.getGestionActivo();
                            retorno.put("gestion", gestion);
                            int anio_actual = Integer.parseInt(id_gestion);
                            retorno.put("resultados", resultados);
                            Familia family = (Familia) resultados.get(0);
                            retorno.put("family", family);
                            retorno.put("gestion", gestion);
                            retorno.put("anio_actual", anio_actual);
                            Inscripcion inscripcion = new Inscripcion();
                            inscripcion.setId_familia(search);
                            inscripcion.setId_gestion(anio_actual - 1);
                            retorno.put("_alumnos", this.sigaa.getAlumnosById_familiaAndGestion(inscripcion));
                            inscripcion.setId_familia(search);
                            inscripcion.setId_gestion(anio_actual);
                            List alumnos = this.sigaa.getAlumnosById_familiaAndGestion(inscripcion);
                            retorno.put("alumnos", alumnos);

                            PagoPension pagopension = new PagoPension();
                            pagopension.setId_familia(search);
                            pagopension.setId_gestion(anio_actual);
                            List pagoPensiones = this.sigaa.getPagoPensionesByIdFamiliaAndIdGestion(pagopension);
                            retorno.put("pagoPensiones", pagoPensiones);
                            if (pagoPensiones.size() == 0) {
                                retorno.put("noServicio", "no");
                            }
                            PagoServicio pagoservicio = new PagoServicio();
                            pagoservicio.setId_familia(search);
                            pagoservicio.setId_gestion(anio_actual);
                            List pagoServicios = this.sigaa.getPagoServiciosByIdFamiliaAndIdGestion(pagoservicio);
                            retorno.put("pagoServicios", pagoServicios);

                            retorno.put("newPagos", "_newPagos");
                            retorno.put("cuotas", this.sigaa.getListaCuotas());
                            List descuentos = this.sigaa.getListaDescuentos();
                            retorno.put("descuentos", descuentos);
                            retorno.put("servicios", this.sigaa.getServicioByID(Integer.parseInt(id_gestion)));
                            retorno.put("pagos_colegio", this.sigaa.getPagoColegioByIdInscripcion(alumnos));
                            retorno.put("dma", new Date());
                        }
                        retorno.put("intro", "intro");
                        return new ModelAndView(this.perfectView, retorno);
                    }

                    if (regPagos != null) {
                        Gestion gestion = this.sigaa.getGestionActivo();
                        retorno.put("gestion", gestion);
                        String id_fam = request.getParameter("id_fam");
                        retorno.put("search", id_fam);
//                        int anio_actual = Integer.parseInt(sdf.format(fecha));
                        try {
                            List resultados = this.sigaa.getBuscarFamilia(id_fam.toUpperCase());
                            retorno.put("resultados", resultados);
                            Familia family = (Familia) resultados.get(0);
                            retorno.put("family", family);
                            retorno.put("gestion", this.sigaa.getGestionActivo());
                            Inscripcion inscripcion = new Inscripcion();
                            inscripcion.setId_familia(id_fam);
                            inscripcion.setId_gestion(Integer.parseInt(id_gestion));
                            List alumnos = this.sigaa.getAlumnosById_familiaAndGestion(inscripcion);
                            retorno.put("alumnos", alumnos);
                            retorno.put("pagos_colegio", this.sigaa.getPagoColegioByIdInscripcion(alumnos));
                            retorno.put("intro", "intro");

                            for (int i = 0; i < alumnos.size(); i++) {
                                Inscripcion ins = (Inscripcion) alumnos.get(i);
                                String descuento = request.getParameter("descuento-" + ins.getId_estudiante());
                                String cuota = request.getParameter("cuota_" + ins.getId_estudiante());
                                String beca = request.getParameter("beca-" + ins.getId_estudiante());
                                if (descuento != null && cuota != null && beca != null) {
                                    String id_pago_pension = request.getParameter("id_pago_pension-" + ins.getId_estudiante());
                                    PagoPension pagopension = new PagoPension();
                                    pagopension.setId_familia(id_fam);
                                    pagopension.setId_inscripcion(ins.getId_inscripcion());
                                    pagopension.setId_gestion(Integer.parseInt(id_gestion));
                                    List tiposPensiones = this.sigaa.getTiposPensionesByIdGestion(Integer.parseInt(id_gestion));
                                    for (int j = 0; j < tiposPensiones.size(); j++) {
                                        Tipo_pension tipo_pension = (Tipo_pension) tiposPensiones.get(j);
                                        if (tipo_pension.getId_curso().equals(ins.getId_curso())) {
                                            pagopension.setMonto_inicial(tipo_pension.getCuota_inicial());
                                            pagopension.setMonto_total(tipo_pension.getCuota_total());
                                        }
                                    }
                                    if (descuento.equals("")) {
                                        descuento = "0";
                                    }
                                    pagopension.setDescuento(Integer.parseInt(descuento));
                                    if (beca.equals("")) {
                                        beca = "0";
                                    }
                                    pagopension.setBeca(Integer.parseInt(beca));
                                    double monto_total_a_operar = pagopension.getMonto_inicial() + pagopension.getMonto_total();
                                    double monto_x_pension = Math.round((monto_total_a_operar/10) * Math.pow(10, 0)) / Math.pow(10, 0);
                                    double diferencia=monto_x_pension-pagopension.getMonto_inicial();
                                    double monto_a_operar=monto_x_pension*9;
                                    double pension_total = (monto_a_operar - (monto_a_operar * (pagopension.getDescuento() + pagopension.getBeca()) / 100));
                                    double redondeado = Math.round(pension_total * Math.pow(10,0)) / Math.pow(10,0);
                                    pagopension.setPension_total(redondeado+diferencia);
                                    pagopension.setCuota(Integer.parseInt(cuota));
                                    pagopension.setEditable(false);
                                    pagopension.setId_usuario(personal.getId_personal());
                                    pagopension.setId_pago_pension(Integer.parseInt(id_pago_pension));
                                    pagopension.setId_gestion(Integer.parseInt(id_gestion));
                                    pagopension.setCodigo(ins.getCodigo());
                                    this.sigaa.setUpdatePagoPensiones(pagopension,diferencia);
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            mensaje = "false";
                        }
                        PagoPension pagopension = new PagoPension();
                        pagopension.setId_familia(id_fam);
                        pagopension.setId_gestion(Integer.parseInt(id_gestion));
                        List pagoPensiones = this.sigaa.getPagoPensionesByIdFamiliaAndIdGestion(pagopension);
                        for (int i = 0; i < pagoPensiones.size(); i++) {
                            PagoPension pago_pension = (PagoPension) pagoPensiones.get(i);
                            for (int j = 0; j < pago_pension.getDepositosAsignadas().size(); j++) {
                                DepositoAsignada depasignada = (DepositoAsignada) pago_pension.getDepositosAsignadas().get(j);
                                String monto = request.getParameter("monto-" + depasignada.getId_dep_asignada());
                                String m_d = request.getParameter("monto_dep-" + depasignada.getId_dep_asignada());
                                String id_dep_asignada = request.getParameter("id-" + depasignada.getId_dep_asignada());
                                String ec = request.getParameter("estado_cuota-" + depasignada.getId_dep_asignada());
//                                String estado_cuota = depasignada.getEstado_cuota();
//                                if (ec != null) {
//                                    estado_cuota = "cancelado";
//                                }
                                if (monto != null) {
                                    double monto_dep = Double.parseDouble(m_d);
                                    Double newMonto = Double.parseDouble(monto);
//                                    System.out.println(newMonto+":::::::::::::::::::::"+depasignada.getMonto());
                                    if (depasignada.getId_dep_asignada() == Integer.parseInt(id_dep_asignada) && (newMonto != depasignada.getMonto() || ec != null || monto_dep != depasignada.getMonto_dep())) {
//                                        System.out.println(":::::::::::::::::::::" + j);
                                        depasignada.setMonto_dep(monto_dep);
                                        if (monto_dep > 0) {
                                            depasignada.setEstado_cuota("cancelado");
                                            depasignada.setObservacion("Pago realizado en el colegio");
                                            depasignada.setFecha_dep(new Date());
                                            depasignada.setMonto_dep(monto_dep);
                                        }
                                        if (ec != null) {
                                            depasignada.setEstado_cuota("cancelado");
                                        }
                                        depasignada.setMonto(newMonto);
                                        this.sigaa.getUpdateMontoDepositoAsignadaByIdDepAsignada(depasignada);
                                    }

                                }
                            }
                        }
                        pagoPensiones = this.sigaa.getPagoPensionesByIdFamiliaAndIdGestion(pagopension);
                        retorno.put("pagoPensiones", pagoPensiones);
                        if (pagoPensiones.size() == 0) {
                            retorno.put("noServicio", "no");
                        }
                        PagoServicio pagoservicio = new PagoServicio();
                        pagoservicio.setId_familia(id_fam);
                        pagoservicio.setId_gestion(Integer.parseInt(id_gestion));
                        List pagoServicios = this.sigaa.getPagoServiciosByIdFamiliaAndIdGestion(pagoservicio);
                        retorno.put("pagoServicios", pagoServicios);
                        retorno.put("newPagos", "_newPagos");
                        retorno.put("cuotas", this.sigaa.getListaCuotas());
                        List descuentos = this.sigaa.getListaDescuentos();
                        retorno.put("descuentos", descuentos);
                        retorno.put("servicios", this.sigaa.getServicioByID(Integer.parseInt(id_gestion)));
                        retorno.put("dma", new Date());
                        retorno.put("mensaje", mensaje);
                        return new ModelAndView(this.perfectView, retorno);
                    }
                    Curso curso = new Curso();
                    curso.setId_curso(id_curso);
                    curso.setId_gestion(Integer.parseInt(id_gestion));
                    curso = this.sigaa.getCursoByIdCursoAndIdGestion(curso);
                    retorno.put("curso", curso);
                    return new ModelAndView(this.perfectView, retorno);
                }
                retorno.put("cursos", this.sigaa.getListaCursos(Integer.parseInt(id_gestion)));
                return new ModelAndView(this.perfectView, retorno);
            }
            retorno.put("gestiones", this.sigaa.getGestionActivo());
            return new ModelAndView(this.perfectView, retorno);
        } else {
            return new ModelAndView(this.ecxessTimeView, "url", url);
        }
    }
}
