/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.sysmaqv.web.spring.sigaa.kardex;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.util.WebUtils;
import spring.sysmaqv.domain.Curso;
import spring.sysmaqv.domain.CursoMateria;
import spring.sysmaqv.domain.Kardex;
import spring.sysmaqv.domain.KardexDetalle;
import spring.sysmaqv.domain.Materia;
import spring.sysmaqv.domain.Personal;
import spring.sysmaqv.domain.logic.SigaaInterface;

/** 
 *
 * @author Marco Antonio Quenya Velasco
 */
public class KardexEstudiantes implements Controller {

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

    @Override
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
                    String opcion = request.getParameter("opcion");
                    retorno.put("opcion", opcion);
                    String cant = request.getParameter("cant");
                    retorno.put("cant", cant);
                    String id_materia = request.getParameter("id_materia");
                    retorno.put("id_materia", id_materia);
                    String id_curso_materia = request.getParameter("id_curso_materia");
                    retorno.put("id_curso_materia", id_curso_materia);
                    String opcion2 = request.getParameter("opcion2");
                    if (opcion2 != null) {
                        if (opcion2.equals("__guardarKardex")) {
                            /*para kardex*/
                            String periodo = request.getParameter("periodo");
                            String fecha = request.getParameter("fecha");
                            String id_docente = request.getParameter("id_docente");
                            String temario = request.getParameter("temario");
                            Kardex kardex = new Kardex();
                            kardex.setId_curso_materia(id_curso_materia);
                            kardex.setPeriodo(Integer.parseInt(periodo));
                            kardex.setId_docente(id_docente);
                            kardex.setTemario(temario);
                            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                            Date nfecha = format.parse(fecha);
                            kardex.setFecha(nfecha);
                            kardex.setUsrreg(personal.getId_personal());
                            this.sigaa.setGuardarKardex(kardex);
                            /*Recuperamos el ultimo idkardex insertado*/
                            int idkardex = this.sigaa.getMaxIdkardex();
                            /*para kardex detalles*/
                            for (int j = 1; j <= 10; j++) {
                                String id_estudiante = request.getParameter("id_estudiante_" + j);
                                if (!id_estudiante.equals("0")) {
                                    int fsl = 0;
                                    if (request.getParameter("fsl_" + j) != null) {
                                        fsl = 1;
                                    }
                                    int a = 0;
                                    if (request.getParameter("a_" + j) != null) {
                                        a = 1;
                                    }
                                    int tnr = 0;
                                    if (request.getParameter("tnr_" + j) != null) {
                                        tnr = 1;
                                    }
                                    int aa = 0;
                                    if (request.getParameter("aa_" + j) != null) {
                                        aa = 1;
                                    }
                                    int su = 0;
                                    if (request.getParameter("su_" + j) != null) {
                                        su = 1;
                                    }
                                    String otrasfaltas = request.getParameter("otrasfaltas_" + j);
                                    String aspectospositivos = request.getParameter("aspectospositivos_" + j);
                                    String observaciones = request.getParameter("observaciones_" + j);
                                    KardexDetalle kardexdetalle = new KardexDetalle();
                                    kardexdetalle.setIdkardex(idkardex);
                                    kardexdetalle.setId_estudiante(id_estudiante);
                                    kardexdetalle.setFsl(fsl);
                                    kardexdetalle.setA(a);
                                    kardexdetalle.setTnr(tnr);
                                    kardexdetalle.setAa(aa);
                                    kardexdetalle.setSu(su);
                                    kardexdetalle.setOtrasfaltas(otrasfaltas);
                                    kardexdetalle.setAspectospositivos(aspectospositivos);
                                    kardexdetalle.setObservaciones(observaciones);
                                    this.sigaa.setRegistrarKardexDetalle(kardexdetalle);
                                }
                            }
                        }
                        if (opcion2.equals("__guardarEditKardex")) {
                            /*para kardex*/
                            opcion="__nuevoKardex";
                            retorno.put("opcion",opcion);
                            String periodo = request.getParameter("periodo");
                            String fecha = request.getParameter("fecha");
                            String id_docente = request.getParameter("id_docente");
                            String temario = request.getParameter("temario");
                            String idkardex = request.getParameter("idkardex");
                            Kardex kardex = new Kardex();
                            kardex.setIdkardex(Integer.parseInt(idkardex));
                            kardex.setId_curso_materia(id_curso_materia);
                            kardex.setPeriodo(Integer.parseInt(periodo));
                            kardex.setId_docente(id_docente);
                            kardex.setTemario(temario);
                            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                            Date nfecha = format.parse(fecha);
                            kardex.setFecha(nfecha);
                            kardex.setUsrreg(personal.getId_personal());
                            this.sigaa.setGuardarEditKardex(kardex);
                            /*Recuperamos el ultimo idkardex insertado*/
//                            int idkardex = this.sigaa.getMaxIdkardex();
                            /*para kardex detalles*/
                            for (int j = 1; j <= 10; j++) {
                                String id_estudiante = request.getParameter("id_estudiante_" + j);

                                int fsl = 0;
                                if (request.getParameter("fsl_" + j) != null) {
                                    fsl = 1;
                                }
                                int a = 0;
                                if (request.getParameter("a_" + j) != null) {
                                    a = 1;
                                }
                                int tnr = 0;
                                if (request.getParameter("tnr_" + j) != null) {
                                    tnr = 1;
                                }
                                int aa = 0;
                                if (request.getParameter("aa_" + j) != null) {
                                    aa = 1;
                                }
                                int su = 0;
                                if (request.getParameter("su_" + j) != null) {
                                    su = 1;
                                }
                                
                                String otrasfaltas = request.getParameter("otrasfaltas_" + j);
                                String aspectospositivos = request.getParameter("aspectospositivos_" + j);
                                String observaciones = request.getParameter("observaciones_" + j);                                
                                String idkardexdetalle = request.getParameter("idkardexdetalle_" + j);
                                
                                KardexDetalle kardexdetalle = new KardexDetalle();
                                kardexdetalle.setIdkardex(Integer.parseInt(idkardex));
                                kardexdetalle.setIdkardexdetalle(Integer.parseInt(idkardexdetalle));
                                kardexdetalle.setId_estudiante(id_estudiante);
                                kardexdetalle.setFsl(fsl);
                                kardexdetalle.setA(a);
                                kardexdetalle.setTnr(tnr);
                                kardexdetalle.setAa(aa);
                                kardexdetalle.setSu(su);
                                kardexdetalle.setOtrasfaltas(otrasfaltas);
                                kardexdetalle.setAspectospositivos(aspectospositivos);
                                kardexdetalle.setObservaciones(observaciones);
                                if (Integer.parseInt(idkardexdetalle) != 0) {
                                    if (!id_estudiante.equals("0")) {
                                        this.sigaa.setRegistrarEditKardexDetalle(kardexdetalle);
                                    } else {
                                        this.sigaa.setDeleteKardexDetalleById(Integer.parseInt(idkardexdetalle));
                                    }
                                } else {
                                    if (!id_estudiante.equals("0")) {
                                        this.sigaa.setRegistrarKardexDetalle(kardexdetalle);                                        
                                    }
                                }
                            }
                        }
                    }
                    if (opcion.equals("__nuevoKardex")) {
                        retorno.put("idkardex",0);
                        CursoMateria cursomateria = new CursoMateria();
                        cursomateria.setId_curso_materia(id_curso_materia);
                        cursomateria.setId_curso(id_curso);
                        cursomateria.setId_gestion(Integer.parseInt(id_gestion));
                        cursomateria.setId_materia(id_materia);
                        Curso curso = this.sigaa.getCursoById(cursomateria);
                        Materia materia = this.sigaa.getMateriaById(id_materia);
                        retorno.put("materia", materia);
                        retorno.put("curso", curso);
                        retorno.put("btn_text", "Guardar");
                        retorno.put("opcion2", "__guardarKardex");
                        retorno.put("docentes", this.sigaa.getListaDocentes());
                        retorno.put("listakardex", this.sigaa.getListaKardex(id_curso_materia));
//                        CursoMateria cursomateria= new CursoMateria();
//                        cursomateria.setId_curso_materia(id_curso_materia);
//                        cursomateria=this.sigaa.getCursoMateriaByIdCursoMateria(cursomateria);

                        retorno.put("desde", 0);
                        String nuevo = new String();
                        SimpleDateFormat formato = new SimpleDateFormat("dd'-'MM'-'yyyy");
                        nuevo = formato.format(new Date());
                        retorno.put("fecha", nuevo);
                        return new ModelAndView(this.perfectView, retorno);
                    }
                    if (opcion.equals("__editKardex")) {
                        String idkardex = request.getParameter("idkardex");
                        retorno.put("idkardex",idkardex);
                        CursoMateria cursomateria = new CursoMateria();
                        cursomateria.setId_curso_materia(id_curso_materia);
                        cursomateria.setId_curso(id_curso);
                        cursomateria.setId_gestion(Integer.parseInt(id_gestion));
                        cursomateria.setId_materia(id_materia);
                        Curso curso = this.sigaa.getCursoById(cursomateria);
                        Materia materia = this.sigaa.getMateriaById(id_materia);
                        retorno.put("materia", materia);
                        retorno.put("curso", curso);
                        retorno.put("opcion2", "__guardarEditKardex");
                        retorno.put("btn_text", "Guardar Cambios");

                        retorno.put("docentes", this.sigaa.getListaDocentes());
                        List listakardex = this.sigaa.getListaKardex(id_curso_materia);
                        retorno.put("listakardex", listakardex);
//                        Kardex kardex=this.sigaa.getKardexByIdkardex(Integer.parseInt(idkardex));
                        for (int i = 0; i < listakardex.size(); i++) {
                            Kardex k = (Kardex) listakardex.get(i);
                            if (k.getIdkardex() == Integer.parseInt(idkardex)) {
                                retorno.put("periodo", k.getPeriodo());
                                SimpleDateFormat formato = new SimpleDateFormat("dd'-'MM'-'yyyy");
                                String nuevo = formato.format(k.getFecha());
                                retorno.put("fecha", nuevo);
                                retorno.put("id_docente", k.getId_docente());
                                retorno.put("temario", k.getTemario());
                                retorno.put("detalleskardex", k.getKardexdetalles());
                                retorno.put("desde", k.getKardexdetalles().size());
                                break;
                            }
                        }
//                        retorno.put("idkardex", idkardex);

//                        String nuevo = new String();
//                        SimpleDateFormat formato = new SimpleDateFormat("dd'-'MM'-'yyyy");
//                        nuevo = formato.format(new Date());
//                        retorno.put("fecha", nuevo);
                        return new ModelAndView(this.perfectView, retorno);
                    }
                }
//                        List resultados = this.sigaa.getBuscarFamilia(search);
//                        retorno.put("search", search.toUpperCase());
//                        if (resultados.size() == 0) {
//                            retorno.put("resul", "sin_datos");
//                        } else {
//                            Gestion gestion = this.sigaa.getGestionActivo();
//                            retorno.put("gestion", gestion);
//                            int anio_actual = Integer.parseInt(id_gestion);
//                            retorno.put("resultados", resultados);
//                            Familia family = (Familia) resultados.get(0);
//                            retorno.put("family", family);
//                            retorno.put("gestion", gestion);
//                            retorno.put("anio_actual", anio_actual);
//                            Inscripcion inscripcion = new Inscripcion();
//                            inscripcion.setId_familia(search);
//                            inscripcion.setId_gestion(anio_actual - 1);
//                            retorno.put("_alumnos", this.sigaa.getAlumnosById_familiaAndGestion(inscripcion));
//                            inscripcion.setId_familia(search);
//                            inscripcion.setId_gestion(anio_actual);
//                            List alumnos = this.sigaa.getAlumnosById_familiaAndGestion(inscripcion);
//                            retorno.put("alumnos", alumnos);
//
//                            PagoPension pagopension = new PagoPension();
//                            pagopension.setId_familia(search);
//                            pagopension.setId_gestion(anio_actual);
//                            List pagoPensiones = this.sigaa.getPagoPensionesByIdFamiliaAndIdGestion(pagopension);
//                            retorno.put("pagoPensiones", pagoPensiones);
//                            if (pagoPensiones.size() == 0) {
//                                retorno.put("noServicio", "no");
//                            }
//                            PagoServicio pagoservicio = new PagoServicio();
//                            pagoservicio.setId_familia(search);
//                            pagoservicio.setId_gestion(anio_actual);
//                            List pagoServicios = this.sigaa.getPagoServiciosByIdFamiliaAndIdGestion(pagoservicio);
//                            retorno.put("pagoServicios", pagoServicios);
//
//                            retorno.put("newPagos", "_newPagos");
//                            retorno.put("cuotas", this.sigaa.getListaCuotas());
//                            List descuentos = this.sigaa.getListaDescuentos();
//                            retorno.put("descuentos", descuentos);
//                            retorno.put("servicios", this.sigaa.getServicioByID(Integer.parseInt(id_gestion)));
//                            retorno.put("pagos_colegio", this.sigaa.getPagoColegioByIdInscripcion(alumnos));
//                            retorno.put("dma", new Date());
//                        }
//                        retorno.put("intro", "intro");
//                        return new ModelAndView(this.perfectView, retorno);
//                    }
//
//                    if (regPagos != null) {
//                        Gestion gestion = this.sigaa.getGestionActivo();
//                        retorno.put("gestion", gestion);
//                        String id_fam = request.getParameter("id_fam");
//                        retorno.put("search", id_fam);
////                        int anio_actual = Integer.parseInt(sdf.format(fecha));
//                        try {
//                            List resultados = this.sigaa.getBuscarFamilia(id_fam.toUpperCase());
//                            retorno.put("resultados", resultados);
//                            Familia family = (Familia) resultados.get(0);
//                            retorno.put("family", family);
//                            retorno.put("gestion", this.sigaa.getGestionActivo());
//                            Inscripcion inscripcion = new Inscripcion();
//                            inscripcion.setId_familia(id_fam);
//                            inscripcion.setId_gestion(Integer.parseInt(id_gestion));
//                            List alumnos = this.sigaa.getAlumnosById_familiaAndGestion(inscripcion);
//                            retorno.put("alumnos", alumnos);
//                            retorno.put("pagos_colegio", this.sigaa.getPagoColegioByIdInscripcion(alumnos));
//                            retorno.put("intro", "intro");
//
//                            for (int i = 0; i < alumnos.size(); i++) {
//                                Inscripcion ins = (Inscripcion) alumnos.get(i);
//                                String descuento = request.getParameter("descuento-" + ins.getId_estudiante());
//                                String cuota = request.getParameter("cuota_" + ins.getId_estudiante());
//                                String beca = request.getParameter("beca-" + ins.getId_estudiante());
//                                if (descuento != null && cuota != null && beca != null) {
//                                    String id_pago_pension = request.getParameter("id_pago_pension-" + ins.getId_estudiante());
//                                    PagoPension pagopension = new PagoPension();
//                                    pagopension.setId_familia(id_fam);
//                                    pagopension.setId_inscripcion(ins.getId_inscripcion());
//                                    pagopension.setId_gestion(Integer.parseInt(id_gestion));
//                                    List tiposPensiones = this.sigaa.getTiposPensionesByIdGestion(Integer.parseInt(id_gestion));
//                                    for (int j = 0; j < tiposPensiones.size(); j++) {
//                                        Tipo_pension tipo_pension = (Tipo_pension) tiposPensiones.get(j);
//                                        if (tipo_pension.getId_curso().equals(ins.getId_curso())) {
//                                            pagopension.setMonto_inicial(tipo_pension.getCuota_inicial());
//                                            pagopension.setMonto_total(tipo_pension.getCuota_total());
//                                        }
//                                    }
//                                    if (descuento.equals("")) {
//                                        descuento = "0";
//                                    }
//                                    pagopension.setDescuento(Integer.parseInt(descuento));
//                                    if (beca.equals("")) {
//                                        beca = "0";
//                                    }
//                                    pagopension.setBeca(Integer.parseInt(beca));
//                                    double monto_total_a_operar = pagopension.getMonto_inicial() + pagopension.getMonto_total();
//                                    double monto_x_pension = Math.round((monto_total_a_operar/10) * Math.pow(10, 0)) / Math.pow(10, 0);
//                                    double diferencia=monto_x_pension-pagopension.getMonto_inicial();
//                                    double monto_a_operar=monto_x_pension*9;
//                                    double pension_total = (monto_a_operar - (monto_a_operar * (pagopension.getDescuento() + pagopension.getBeca()) / 100));
//                                    double redondeado = Math.round(pension_total * Math.pow(10,0)) / Math.pow(10,0);
//                                    pagopension.setPension_total(redondeado+diferencia);
//                                    pagopension.setCuota(Integer.parseInt(cuota));
//                                    pagopension.setEditable(false);
//                                    pagopension.setId_usuario(personal.getId_personal());
//                                    pagopension.setId_pago_pension(Integer.parseInt(id_pago_pension));
//                                    pagopension.setId_gestion(Integer.parseInt(id_gestion));
//                                    pagopension.setCodigo(ins.getCodigo());
//                                    this.sigaa.setUpdatePagoPensiones(pagopension,diferencia);
//                                }
//                            }
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                            mensaje = "false";
//                        }
//                        PagoPension pagopension = new PagoPension();
//                        pagopension.setId_familia(id_fam);
//                        pagopension.setId_gestion(Integer.parseInt(id_gestion));
//                        List pagoPensiones = this.sigaa.getPagoPensionesByIdFamiliaAndIdGestion(pagopension);
//                        for (int i = 0; i < pagoPensiones.size(); i++) {
//                            PagoPension pago_pension = (PagoPension) pagoPensiones.get(i);
//                            for (int j = 0; j < pago_pension.getDepositosAsignadas().size(); j++) {
//                                DepositoAsignada depasignada = (DepositoAsignada) pago_pension.getDepositosAsignadas().get(j);
//                                String monto = request.getParameter("monto-" + depasignada.getId_dep_asignada());
//                                String m_d = request.getParameter("monto_dep-" + depasignada.getId_dep_asignada());
//                                String id_dep_asignada = request.getParameter("id-" + depasignada.getId_dep_asignada());
//                                String ec = request.getParameter("estado_cuota-" + depasignada.getId_dep_asignada());
////                                String estado_cuota = depasignada.getEstado_cuota();
////                                if (ec != null) {
////                                    estado_cuota = "cancelado";
////                                }
//                                if (monto != null) {
//                                    double monto_dep = Double.parseDouble(m_d);
//                                    Double newMonto = Double.parseDouble(monto);
////                                    System.out.println(newMonto+":::::::::::::::::::::"+depasignada.getMonto());
//                                    if (depasignada.getId_dep_asignada() == Integer.parseInt(id_dep_asignada) && (newMonto != depasignada.getMonto() || ec != null || monto_dep != depasignada.getMonto_dep())) {
////                                        System.out.println(":::::::::::::::::::::" + j);
//                                        depasignada.setMonto_dep(monto_dep);
//                                        if (monto_dep > 0) {
//                                            depasignada.setEstado_cuota("cancelado");
//                                            depasignada.setObservacion("Pago realizado en el colegio");
//                                            depasignada.setFecha_dep(new Date());
//                                            depasignada.setMonto_dep(monto_dep);
//                                        }
//                                        if (ec != null) {
//                                            depasignada.setEstado_cuota("cancelado");
//                                        }
//                                        depasignada.setMonto(newMonto);
//                                        this.sigaa.getUpdateMontoDepositoAsignadaByIdDepAsignada(depasignada);
//                                    }
//
//                                }
//                            }
//                        }
//                        pagoPensiones = this.sigaa.getPagoPensionesByIdFamiliaAndIdGestion(pagopension);
//                        retorno.put("pagoPensiones", pagoPensiones);
//                        if (pagoPensiones.size() == 0) {
//                            retorno.put("noServicio", "no");
//                        }
//                        PagoServicio pagoservicio = new PagoServicio();
//                        pagoservicio.setId_familia(id_fam);
//                        pagoservicio.setId_gestion(Integer.parseInt(id_gestion));
//                        List pagoServicios = this.sigaa.getPagoServiciosByIdFamiliaAndIdGestion(pagoservicio);
//                        retorno.put("pagoServicios", pagoServicios);
//                        retorno.put("newPagos", "_newPagos");
//                        retorno.put("cuotas", this.sigaa.getListaCuotas());
//                        List descuentos = this.sigaa.getListaDescuentos();
//                        retorno.put("descuentos", descuentos);
//                        retorno.put("servicios", this.sigaa.getServicioByID(Integer.parseInt(id_gestion)));
//                        retorno.put("dma", new Date());
//                        retorno.put("mensaje", mensaje);
//                        return new ModelAndView(this.perfectView, retorno);
//                    }
//                    Curso curso = new Curso();
//                    curso.setId_curso(id_curso); 
//                    curso.setId_gestion(Integer.parseInt(id_gestion));
//                    curso = this.sigaa.getCursoByIdCursoAndIdGestion(curso);
//                    retorno.put("curso", curso);
//                    return new ModelAndView(this.perfectView, retorno);
//                }
                retorno.put("cursoMaterias", this.sigaa.getListaCursoMateriasByIdGestion(Integer.parseInt(id_gestion)));
                retorno.put("cursos", this.sigaa.getListaCursos(Integer.parseInt(id_gestion)));
//                retorno.put("cursos", this.sigaa.getListaCursos(Integer.parseInt(id_gestion)));
//                retorno.put("cursoMaterias", this.sigaa.getListaCursoMateriasByIdGestion(Integer.parseInt(id_gestion)));
                return new ModelAndView(this.perfectView, retorno);
            }
            retorno.put("gestiones", this.sigaa.getGestionActivo());
            return new ModelAndView(this.perfectView, retorno);
        } else {
            return new ModelAndView(this.ecxessTimeView, "url", url);
        }
    }
}
