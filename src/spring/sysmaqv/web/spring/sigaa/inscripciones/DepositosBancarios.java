/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.sysmaqv.web.spring.sigaa.inscripciones;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
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
import spring.sysmaqv.domain.Deposito;
import spring.sysmaqv.domain.Personal;
import spring.sysmaqv.domain.logic.SigaaInterface;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.util.WebUtils;
import spring.sysmaqv.domain.Cuota;
import spring.sysmaqv.domain.Curso;
import spring.sysmaqv.domain.DepositoAsignada;
import spring.sysmaqv.domain.Estudiante;
import spring.sysmaqv.domain.PagoPension;

/**
 * @ Company : M&M
 * @ Author  : Marco Antonio Quenta Velasco
 * @ Gestion : 2010
 * @ La Paz - Bolivia
 */
public class DepositosBancarios implements Controller {

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

            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            if (isMultipart == true) {
                String arch = "";
                String id_gestion = "";
                try {
                    FileItemFactory factory = new DiskFileItemFactory();
                    ServletFileUpload upload = new ServletFileUpload(factory);
                    List fileItems = upload.parseRequest(request);
                    Iterator it = fileItems.iterator();
                    FileItem fi;
                    String direccion = System.getenv("SIGAA_HOME") + "/documentos/depositos/import/";
                    String nombre_archivo = "";
                    while (it.hasNext()) {
                        fi = (FileItem) it.next();
                        if (fi.getFieldName().equals("archivo")) {
                            arch = fi.getString();
                            if (!fi.getString().equals("")) {
                                nombre_archivo = "Deposito-" + this.formatearFecha(new Date()) + "(" + fi.getName() + ")";
                                fi.write(new File(direccion + nombre_archivo));
                            }
                        }
                        if (fi.getFieldName().equals("id_gestion")) {
                            id_gestion = fi.getString();
                        }
                    }

                    if (!arch.equals("")) {
                        BufferedReader archivo = new BufferedReader(new FileReader(System.getenv("SIGAA_HOME") + "/documentos/depositos/import/" + nombre_archivo));
                        String registro = archivo.readLine();
                        int c = 1;
                        int sw = 1;
                        int registrados = 0;
                        int rechazados = 0;
                        while (registro != null) {
                            StringTokenizer contenido = new StringTokenizer(registro);
                            int atributo = 1;
                            String fecha_dep = "";
                            Deposito deposito = new Deposito();
                            while (contenido.hasMoreTokens()) {
                                String cadena_i = contenido.nextToken("|");
                                if (c == 1 && sw == 1) {
                                    cadena_i = cadena_i.substring((cadena_i.length() - 3), cadena_i.length());
                                    sw = 0;
                                }
                                if (atributo == 1) {
                                    deposito.setConcepto(cadena_i);
                                }
                                if (atributo == 2) {
                                    deposito.setCodigo(Integer.parseInt(cadena_i));
                                }
                                if (atributo == 3) {
                                    String newNombre = "";
                                    for (int i = 0; i < cadena_i.length(); i++) {
                                        if (cadena_i.substring(i, (i + 1)).equals("Ã")) {
                                            i++;
                                            if (cadena_i.substring(i, (i + 1)).equals("‘")) {
                                                newNombre = newNombre + "Ñ";
                                            } else if (cadena_i.substring(i, (i + 1)).equals("‰")) {
                                                newNombre = newNombre + "É";
                                            } else if (cadena_i.substring(i, (i + 1)).equals("“")) {
                                                newNombre = newNombre + "Ó";
                                            } else if (cadena_i.substring(i, (i + 1)).equals("š")) {
                                                newNombre = newNombre + "Ú";
                                            } else {
                                                newNombre = newNombre + "(Í/Á)";
                                            }
                                        } else {
                                            newNombre = newNombre + cadena_i.substring(i, (i + 1));
                                        }
                                    }
                                    deposito.setNombre_completo(newNombre);
                                }
                                if (atributo == 4) {
                                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                                    Date fec_dep = format.parse(cadena_i);
                                    deposito.setFec_deposito(fec_dep);
                                    fecha_dep = cadena_i.substring(0, 2) + cadena_i.substring(3, 5) + cadena_i.substring(6, 10);
                                }
                                if (atributo == 5) {
                                    deposito.setAnio(cadena_i);
                                }
                                if (atributo == 6) {
                                    deposito.setMonto(Double.parseDouble(cadena_i));
                                }
                                if (atributo == 7) {
                                    deposito.setFactura(Integer.parseInt(cadena_i));
                                }
                                if (atributo == 8) {
                                    deposito.setCuota(Integer.parseInt(cadena_i));
                                }
                                atributo++;
                            }
                            String id_deposito = deposito.getConcepto() + deposito.getCodigo() + fecha_dep + deposito.getFactura()+deposito.getCuota();
                            deposito.setId_deposito(id_deposito);
                            deposito.setId_gestion(Integer.parseInt(id_gestion));
                            Deposito dep = this.sigaa.getDepositoByIdDeposito(deposito);
                            if (dep == null) {
                                this.sigaa.setRegistrarDeposito(deposito);
                                registrados++;
//                                PagoPension pagopension = this.sigaa.getPagoPensionByCodigoAndIdGestion(deposito);
//                                if (pagopension != null) {
//                                    for (int k = 0; k < pagopension.getDepositosAsignadas().size(); k++) {
//                                        DepositoAsignada depositoasignada = (DepositoAsignada) pagopension.getDepositosAsignadas().get(k);
//                                        if (deposito.getConcepto().equals("MEN")) {
//                                            if (pagopension.getCodigo() == deposito.getCodigo() && depositoasignada.getNro_cuota() == deposito.getCuota()) {
//                                                depositoasignada.setMonto_dep(deposito.getMonto());
//                                                depositoasignada.setFecha_dep(deposito.getFec_deposito());
//                                                depositoasignada.setInteres(0);
//                                                depositoasignada.setMonto_dep_cuota(0);
//                                                depositoasignada.setEstado_cuota("cancelado");
//                                                depositoasignada.setFactura(deposito.getFactura());
//                                                this.sigaa.setUpdateDepositoAsignadaByIdDepositoAsignada(depositoasignada);
//                                            }
//                                        }
//                                        if (deposito.getConcepto().equals("INT")) {
//                                            if (pagopension.getCodigo() == deposito.getCodigo() //&& depositoasignada.getFecha_dep() == deposito.getFec_deposito()
//                                                    && depositoasignada.getFactura() == deposito.getFactura()) {
//                                                depositoasignada.setInteres(deposito.getMonto());
//                                                this.sigaa.setUpdateDepositoAsignadaByIdDepositoAsignada(depositoasignada);
//                                            }
//                                        }
//                                    }
//                                }
                            } else {
                                
                                
                                System.out.println(deposito.getConcepto()+"@"+deposito.getCodigo()+"@"+deposito.getNombre_completo()+"@"+deposito.getFec_deposito()+"@"+deposito.getAnio()+"@"+deposito.getMonto()+"@"+deposito.getFactura()+"@"+deposito.getCuota());
                                        
                                    
                                    
                                rechazados++;
                            }
                            c++;
                            registro = archivo.readLine();
                        }
                        retorno.put("mensaje", "_mensaje");
                        retorno.put("registrados", registrados);
                        retorno.put("rechazados", rechazados);
                    }
                    retorno.put("cursos", 1);
                    retorno.put("estados", 1);
                    retorno.put("cuotas", 1);
                    retorno.put("id_gestion", id_gestion);
                    retorno.put("cargar", "cargar");
                    return new ModelAndView(this.perfectView, retorno);
                } catch (Exception de) {
                    de.printStackTrace();
                    retorno.put("cursos", 1);
                    retorno.put("estados", 1);
                    retorno.put("cuotas", 1);
                    retorno.put("id_gestion", id_gestion);
                    retorno.put("cargar", "cargar");
                    return new ModelAndView(this.perfectView, retorno);
                }
            }
            String id_gestion = request.getParameter("id_gestion");
            if (id_gestion != null) {
                retorno.put("id_gestion", id_gestion);
                retorno.put("cargar", "cargar");
                retorno.put("cursos", this.sigaa.getListaCursos(Integer.parseInt(id_gestion)));
                String update = request.getParameter("update");
               // System.out.println("este es el CURSO"+cursos);

                /*updateeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee*/
                if (update != null) {
                    List depositos = this.sigaa.getDepositosByIdGestionYOAnio(id_gestion);
                    System.out.println("tamaño de lista " + depositos.size());
                    int cod_virtual = 0000000000;
                    List dep_asignadas = null;
                    for (int i = 0; i < depositos.size(); i++) {
                        System.out.println("------------------------------------------------" + i);
                        Deposito deposito = (Deposito) depositos.get(i);
                        if (cod_virtual != deposito.getCodigo()) {
                            cod_virtual = deposito.getCodigo();
                            deposito.setId_gestion(Integer.parseInt(deposito.getAnio()));
                            dep_asignadas = this.sigaa.getDepositosAsignadasByCodigoAndIdGestion(deposito);
                        }
                        if (dep_asignadas != null && deposito.isAsignado() == false) {
                            for (int j = 0; j < dep_asignadas.size(); j++) {
                                DepositoAsignada depositoasignada = (DepositoAsignada) dep_asignadas.get(j);
                                if (depositoasignada.getNro_cuota() == deposito.getCuota() && deposito.getConcepto().equals("MEN") && depositoasignada.getCodigo() == deposito.getCodigo()) {
                                    if (depositoasignada.getEstado_cuota().equals("pendiente")) {
                                        depositoasignada.setMonto_dep(deposito.getMonto());
                                        depositoasignada.setFecha_dep(deposito.getFec_deposito());
                                        depositoasignada.setInteres(0);
                                        depositoasignada.setMonto_dep_cuota(0);
                                        depositoasignada.setEstado_cuota("cancelado");
                                        depositoasignada.setFactura(deposito.getFactura());
                                        System.out.println("MENiddepositoasignada" + depositoasignada.getId_dep_asignada());
                                        this.sigaa.setUpdateDepositoAsignadaByIdDepositoAsignada(depositoasignada);
                                        this.sigaa.setUpdateDepositoByIdDeposito(deposito.getId_deposito());
                                    }
                                }
                                if (deposito.getConcepto().equals("INT")) {
                                    if (depositoasignada.getCodigo() == deposito.getCodigo()
                                            && depositoasignada.getFactura() == deposito.getFactura()) {
                                        depositoasignada.setInteres(deposito.getMonto());
                                        System.out.println("INTiddepositoasignada" + depositoasignada.getId_dep_asignada());
                                        this.sigaa.setUpdateDepositoAsignadaByIdDepositoAsignada(depositoasignada);
                                        this.sigaa.setUpdateDepositoByIdDeposito(deposito.getId_deposito());
                                    }
                                }
                            }
                        }
//                        if (deposito.getConcepto().equals("MEN")) {// solo se actualizara cuando la mensualidad sea  de tipo MEN
                        Deposito dep = new Deposito();
                        if ((i + 1) == depositos.size()) {
                            dep = (Deposito) depositos.get(i);
                        } else {
                            dep = (Deposito) depositos.get(i + 1);
                        }
                        if (dep.getCodigo() != deposito.getCodigo() || (i + 1) == depositos.size()) {
                            deposito.setId_gestion(Integer.parseInt(deposito.getAnio()));
                            dep_asignadas = this.sigaa.getDepositosAsignadasByCodigoAndIdGestion(deposito);
                            if (dep_asignadas != null) {
                                for (int k = 0; k < dep_asignadas.size(); k++) {
                                    int iddepasignadaaprocesar = 0;
                                    System.out.println(k + "------------------------------------------------" + i);
                                    DepositoAsignada da = (DepositoAsignada) dep_asignadas.get(k);
                                    System.out.println(da.getMonto_dep() + "____MAYOR______" + da.getMonto());
                                    if (da.getMonto_dep() > da.getMonto() && da.getControl() != 0) {
                                        double monto_res = da.getMonto_dep() - da.getMonto();
                                        int k_ini = k;
                                        int k_fin = k;
                                        iddepasignadaaprocesar = da.getId_dep_asignada();
                                        this.sigaa.setUpdateDepositoAsignadaByIdDepositoasignadaACero(iddepasignadaaprocesar);
                                        System.out.println("------------------------------------------------>>>>>>>>>>>>>>>>>>>>>" + iddepasignadaaprocesar);
                                        boolean sw = true;
                                        while (monto_res > 100) {
//                                            System.out.println(sw+"__________monto restante: "+monto_res);
                                            if (sw == true) {
                                                k_ini--;
                                                DepositoAsignada depAsig = (DepositoAsignada) dep_asignadas.get(k_ini);
                                                System.out.println("TRUE_" + depAsig.getId_dep_asignada() + "_" + depAsig.getEstado_cuota());
                                                if (depAsig.getEstado_cuota().equals("pendiente")) {
                                                    depAsig.setFecha_dep(da.getFecha_dep());
                                                    if (monto_res - depAsig.getMonto() <= 100) {
                                                        depAsig.setMonto_dep_cuota(monto_res);
                                                    } else {
                                                        depAsig.setMonto_dep_cuota(depAsig.getMonto());
                                                    }
                                                    depAsig.setEstado_cuota("cancelado");
                                                    depAsig.setFactura(0);
                                                    depAsig.setObservacion("Cancelado en la cuota " + da.getNro_cuota() + ", por un monto de " + depAsig.getMonto_dep_cuota());
                                                    depAsig.setControl(0);
                                                    System.out.println(sw + "_-_" + depAsig.getId_dep_asignada() + "________monto restante: " + monto_res);
                                                    this.sigaa.setUpdateDepositoAsignadaByIdDepositoAsignada(depAsig);
                                                    monto_res = Math.round((monto_res - depAsig.getMonto()) * Math.pow(10, 1)) / Math.pow(10, 1);
                                                } else {
                                                    sw = false;
                                                }
                                            }
                                            if (sw == false) {
                                                k_fin++;
                                                DepositoAsignada depAsig = (DepositoAsignada) dep_asignadas.get(k_fin);
                                                System.out.println("FALSE___" + depAsig.getId_dep_asignada() + "_" + depAsig.getEstado_cuota());
                                                if (depAsig.getEstado_cuota().equals("pendiente")) {
                                                    depAsig.setFecha_dep(da.getFecha_dep());
                                                    if (monto_res - depAsig.getMonto() <= 100) {
                                                        depAsig.setMonto_dep_cuota(monto_res);
                                                    } else {
                                                        depAsig.setMonto_dep_cuota(depAsig.getMonto());
                                                    }
                                                    depAsig.setEstado_cuota("cancelado");
                                                    depAsig.setFactura(0);
                                                    depAsig.setObservacion("Cancelado en la cuota " + da.getNro_cuota() + ", por un monto de " + depAsig.getMonto_dep_cuota());
                                                    depAsig.setControl(0);
                                                    System.out.println(sw + "_+_" + depAsig.getId_dep_asignada() + "________monto restante: " + monto_res);
                                                    this.sigaa.setUpdateDepositoAsignadaByIdDepositoAsignada(depAsig);
                                                    monto_res = Math.round((monto_res - depAsig.getMonto()) * Math.pow(10, 1)) / Math.pow(10, 1);
                                                } else {
                                                    monto_res = 0;// caso extremo, fin del while
                                                }
                                            }
                                        }

                                        /*Cambio de control a 0*/
                                    } else {
                                        if (da.getMonto_dep()>0 && da.getEstado_cuota().equals("cancelado") && da.getControl()!=0) {
                                            this.sigaa.setUpdateDepositoAsignadaByIdDepositoasignadaACero(da.getId_dep_asignada());
                                        }
                                    }
                                }
                            }
                        }
//                        }
                    }
                }


                String opcion = request.getParameter("opcion");
                String cursos = request.getParameter("cursos");
                String estados = request.getParameter("estados");
                if (cursos == null) {
                    retorno.put("cursos", 1);
                } else {
                    retorno.put("cursos", cursos);
                }
                if (estados == null) {
                    retorno.put("estados", 1);
                } else {
                    retorno.put("estados", estados);
                }
                if (opcion != null && cursos != null && estados != null) {
                    List newDetallesPagosCursos = new ArrayList();
                    if (opcion.equals("_opcion1")) {
                        if (cursos.equals("1") && estados.equals("1")) {
                            List depositos = this.sigaa.getDepositosAsignados(Integer.parseInt(id_gestion));
                            retorno.put("depositos", depositos);
                            retorno.put("reporteAll", "_reporte");
                        }
                        if (cursos.equals("1") && (estados.equals("2") || estados.equals("4"))) {
                            List curs = this.sigaa.getListaCursos(Integer.parseInt(id_gestion));
                            boolean sw1 = false;
                            for (int i = 1; i <= 10; i++) {
                                String cuota = request.getParameter("C" + i);
                                if (cuota != null) {
                                    retorno.put("C" + i, "C" + i);
                                    sw1 = true;
                                }
                            }
                            List detallesPagosCursos = this.sigaa.getDetallesPagoPensionesCursos(Integer.parseInt(id_gestion), curs);
//                            List newDetallesPagosCursos = new ArrayList();
                            for (int i = 0; i < detallesPagosCursos.size(); i++) {
                                Curso curso = (Curso) detallesPagosCursos.get(i);
                                List newEstudiantes = new ArrayList();
                                for (int j = 0; j < curso.getEstudiantes().size(); j++) {
                                    Estudiante estudiante = (Estudiante) curso.getEstudiantes().get(j);
                                    boolean sw = false;
                                    int cont = 0;
                                    for (int k = 0; k < estudiante.getDepAsignadas().size(); k++) {
                                        DepositoAsignada depositoasignada = (DepositoAsignada) estudiante.getDepAsignadas().get(k);
                                        if (estados.equals("2")) {
                                            if (sw1 == true) {
                                                String cuota = request.getParameter("C" + depositoasignada.getNro_cuota());
                                                if (cuota != null && depositoasignada.getEstado_cuota().equals("pendiente")) {
                                                    sw = true;
                                                }
                                            } else {
                                                if (depositoasignada.getEstado_cuota().equals("cancelado")) {
                                                    cont++;
                                                }
                                                if (cont == estudiante.getDepAsignadas().size()) {
                                                    sw = true;
                                                }
                                            }
                                        } else {
                                            if (sw1 == true) {
                                                String cuota = request.getParameter("C" + depositoasignada.getNro_cuota());
                                                if (cuota != null && depositoasignada.getEstado_cuota().equals("cancelado")) {
                                                    sw = true;
                                                }
                                            } else {
                                                if (depositoasignada.getEstado_cuota().equals("pendiente")) {
                                                    cont++;
                                                }
                                                if (cont == estudiante.getDepAsignadas().size()) {
                                                    sw = true;
                                                }
                                            }
                                        }

                                    }
                                    if (sw == true) {
                                        newEstudiantes.add(estudiante);
                                    }
                                }
                                curso.setEstudiantes(newEstudiantes);
                                newDetallesPagosCursos.add(curso);
                            }
                            retorno.put("consulta", newDetallesPagosCursos);
                            retorno.put("reporte1", "_reporte");
                        }
                        if (cursos.equals("1") && (estados.equals("3") || estados.equals("5"))) {
                            List curs = this.sigaa.getListaCursos(Integer.parseInt(id_gestion));
                            String nrocuotas = request.getParameter("nrocuota");
                            if (nrocuotas.equals("")) {
                                nrocuotas = "0";
                            }
                            retorno.put("nrocuotas", nrocuotas);
                            if (Integer.parseInt(nrocuotas) >= 0 && Integer.parseInt(nrocuotas) <= 10) {
                                List detallesPagosCursos = this.sigaa.getDetallesPagoPensionesCursos(Integer.parseInt(id_gestion), curs);
                                for (int i = 0; i < detallesPagosCursos.size(); i++) {
                                    Curso curso = (Curso) detallesPagosCursos.get(i);
                                    List newEstudiantes = new ArrayList();
                                    for (int j = 0; j < curso.getEstudiantes().size(); j++) {
                                        Estudiante estudiante = (Estudiante) curso.getEstudiantes().get(j);
                                        int cont = 0;
                                        for (int k = 0; k < estudiante.getDepAsignadas().size(); k++) {
                                            DepositoAsignada depositoasignada = (DepositoAsignada) estudiante.getDepAsignadas().get(k);
                                            if (estados.equals("3")) {
                                                if (depositoasignada.getEstado_cuota().equals("pendiente")) {
                                                    cont++;
                                                }
                                            } else {
                                                if (depositoasignada.getEstado_cuota().equals("cancelado")) {
                                                    cont++;
                                                }
                                            }
                                        }
                                        if (cont == Integer.parseInt(nrocuotas) && estudiante.getNroCuotas() != 0) {
                                            newEstudiantes.add(estudiante);
                                        }
                                    }
                                    curso.setEstudiantes(newEstudiantes);
                                    newDetallesPagosCursos.add(curso);
                                }
                                retorno.put("consulta", newDetallesPagosCursos);
                            }
                            retorno.put("reporte1", "_reporte");
                        }
                        if (cursos.equals("2") && estados.equals("1")) {
                            List curs = this.sigaa.getListaCursos(Integer.parseInt(id_gestion));
                            List newCurs = new ArrayList();
                            for (int i = 0; i < curs.size(); i++) {
                                Curso curso = (Curso) curs.get(i);
                                String c = request.getParameter(curso.getId_curso());
                                if (c != null) {
                                    newCurs.add(curso);
                                    retorno.put(curso.getId_curso(), curso.getId_curso());
                                }
                            }
                            List detallesPagosCursos = this.sigaa.getDetallesPagoPensionesCursos(Integer.parseInt(id_gestion), newCurs);
                            for (int i = 0; i < detallesPagosCursos.size(); i++) {
                                Curso curso = (Curso) detallesPagosCursos.get(i);
                                List newEstudiantes = new ArrayList();
                                for (int j = 0; j < curso.getEstudiantes().size(); j++) {
                                    Estudiante estudiante = (Estudiante) curso.getEstudiantes().get(j);
                                    if (estudiante.getNroCuotas() != 0) {
                                        newEstudiantes.add(estudiante);
                                    }
                                }
                                curso.setEstudiantes(newEstudiantes);
                                newDetallesPagosCursos.add(curso);
                            }
                            retorno.put("consulta", newDetallesPagosCursos);
                            retorno.put("reporte1", "_reporte");
                        }
                        if (cursos.equals("2") && (estados.equals("2") || estados.equals("4"))) {
                            List curs = this.sigaa.getListaCursos(Integer.parseInt(id_gestion));
                            List newCurs = new ArrayList();
                            for (int i = 0; i < curs.size(); i++) {
                                Curso curso = (Curso) curs.get(i);
                                String c = request.getParameter(curso.getId_curso());
                                if (c != null) {
                                    newCurs.add(curso);
                                    retorno.put(curso.getId_curso(), curso.getId_curso());
                                }
                            }
                            boolean sw1 = false;
                            for (int i = 1; i <= 10; i++) {
                                String cuota = request.getParameter("C" + i);
                                if (cuota != null) {
                                    retorno.put("C" + i, "C" + i);
                                    sw1 = true;
                                }
                            }
                            List detallesPagosCursos = this.sigaa.getDetallesPagoPensionesCursos(Integer.parseInt(id_gestion), newCurs);
//                            List newDetallesPagosCursos = new ArrayList();
                            for (int i = 0; i < detallesPagosCursos.size(); i++) {
                                Curso curso = (Curso) detallesPagosCursos.get(i);
                                List newEstudiantes = new ArrayList();
                                for (int j = 0; j < curso.getEstudiantes().size(); j++) {
                                    Estudiante estudiante = (Estudiante) curso.getEstudiantes().get(j);
                                    boolean sw = false;
                                    int cont = 0;
                                    for (int k = 0; k < estudiante.getDepAsignadas().size(); k++) {
                                        DepositoAsignada depositoasignada = (DepositoAsignada) estudiante.getDepAsignadas().get(k);
                                        if (estados.equals("2")) {
                                            if (sw1 == true) {
                                                String cuota = request.getParameter("C" + depositoasignada.getNro_cuota());
                                                if (cuota != null && depositoasignada.getEstado_cuota().equals("pendiente")) {
                                                    sw = true;
                                                }
                                            } else {
                                                if (depositoasignada.getEstado_cuota().equals("cancelado")) {
                                                    cont++;
                                                }
                                                if (cont == estudiante.getDepAsignadas().size()) {
                                                    sw = true;
                                                }
                                            }
                                        } else {
                                            if (sw1 == true) {
                                                String cuota = request.getParameter("C" + depositoasignada.getNro_cuota());
                                                if (cuota != null && depositoasignada.getEstado_cuota().equals("cancelado")) {
                                                    sw = true;
                                                }
                                            } else {
                                                if (depositoasignada.getEstado_cuota().equals("pendiente")) {
                                                    cont++;
                                                }
                                                if (cont == estudiante.getDepAsignadas().size()) {
                                                    sw = true;
                                                }
                                            }
                                        }
                                    }
                                    if (sw == true) {
                                        newEstudiantes.add(estudiante);
                                    }
                                }
                                curso.setEstudiantes(newEstudiantes);
                                newDetallesPagosCursos.add(curso);
                            }

                            retorno.put("consulta", newDetallesPagosCursos);
                            retorno.put("reporte1", "_reporte");
                        }
                        if (cursos.equals("2") && (estados.equals("3") || estados.equals("5"))) {
                            List curs = this.sigaa.getListaCursos(Integer.parseInt(id_gestion));
                            List newCurs = new ArrayList();
                            for (int i = 0; i < curs.size(); i++) {
                                Curso curso = (Curso) curs.get(i);
                                String c = request.getParameter(curso.getId_curso());
                                if (c != null) {
                                    newCurs.add(curso);
                                    retorno.put(curso.getId_curso(), curso.getId_curso());
                                }
                            }
                            String nrocuotas = request.getParameter("nrocuota");
                            if (nrocuotas.equals("")) {
                                nrocuotas = "0";
                            }
                            retorno.put("nrocuotas", nrocuotas);
                            if (Integer.parseInt(nrocuotas) >= 0 && Integer.parseInt(nrocuotas) <= 10) {
                                List detallesPagosCursos = this.sigaa.getDetallesPagoPensionesCursos(Integer.parseInt(id_gestion), newCurs);
//                                List newDetallesPagosCursos = new ArrayList();
                                for (int i = 0; i < detallesPagosCursos.size(); i++) {
                                    Curso curso = (Curso) detallesPagosCursos.get(i);
                                    List newEstudiantes = new ArrayList();
                                    for (int j = 0; j < curso.getEstudiantes().size(); j++) {
                                        Estudiante estudiante = (Estudiante) curso.getEstudiantes().get(j);
                                        int cont = 0;
                                        for (int k = 0; k < estudiante.getDepAsignadas().size(); k++) {
                                            DepositoAsignada depositoasignada = (DepositoAsignada) estudiante.getDepAsignadas().get(k);
                                            if (estados.equals("3")) {
                                                if (depositoasignada.getEstado_cuota().equals("pendiente")) {
                                                    cont++;
                                                }
                                            } else {
                                                if (depositoasignada.getEstado_cuota().equals("cancelado")) {
                                                    cont++;
                                                }
                                            }
                                        }
                                        if (cont == Integer.parseInt(nrocuotas) && estudiante.getNroCuotas() != 0) {
                                            newEstudiantes.add(estudiante);
                                        }
                                    }
                                    curso.setEstudiantes(newEstudiantes);
                                    newDetallesPagosCursos.add(curso);
                                }
                                retorno.put("consulta", newDetallesPagosCursos);
                            }
                            retorno.put("reporte1", "_reporte");
                        }
                    }
                    String tipo = request.getParameter("tipo");
                    if (tipo.equals("impresion")) {
                        String excel = this.setGenerarReporteExcel(id_gestion, newDetallesPagosCursos);
                        response.sendRedirect("documentos/depositos/repExcel/" + excel);
                    }
                }
                String imprimir = request.getParameter("imprimir");
                if (imprimir != null) {
                    if (imprimir.equals("imprimirGen")) {
                        String excel = this.setGenerarReporte(id_gestion);
                        response.sendRedirect("documentos/depositos/repExcel/" + excel);
                    }
                }
                return new ModelAndView(this.perfectView, retorno);
            }
            retorno.put("gestion", this.sigaa.getGestionActivo());
            return new ModelAndView(this.perfectView, retorno);
        } else {
            return new ModelAndView(this.ecxessTimeView, "url", url);
        }
    }

    private String formatearFecha(Date fecha) {
        String nuevo = new String();
        SimpleDateFormat formato = new SimpleDateFormat("dd'-'MM'-'yyyy");
        nuevo = formato.format(fecha);
        return nuevo;
    }

    private String formatearFecha2(Date fecha) {
        String nuevo = new String();
        SimpleDateFormat formato = new SimpleDateFormat("dd'/'MM'/'yyyy");
        nuevo = formato.format(fecha);
        return nuevo;
    }

    private String setGenerarReporte(String id_gestion) {
        String archivo = "Rep" + formatearFecha(new Date()) + ".xls";
        try {
            String dirDestino = System.getenv("SIGAA_HOME") + "/documentos/depositos/repExcel/";
            String dirPlantilla = System.getenv("SIGAA_HOME") + "/documentos/plantillas_excel/plantilla_factura_bancaria.xls";
            Workbook plantilla = Workbook.getWorkbook(new File(dirPlantilla));
            WritableWorkbook workbook = Workbook.createWorkbook(new File(dirDestino + archivo), plantilla);
            WritableFont arial_10 = new WritableFont(WritableFont.ARIAL, 10);
            WritableCellFormat arial10 = new WritableCellFormat(arial_10);
            WritableSheet sheet = workbook.getSheet("Mensualidades");
            List depositos = this.sigaa.getDepositosAsignados(Integer.parseInt(id_gestion));

            for (int x = 0; x < depositos.size(); x++) {
                PagoPension pagopension = (PagoPension) depositos.get(x);
                sheet.addCell(new jxl.write.Number(0, 6 + x, pagopension.getCodigo()));
                sheet.addCell(new Label(1, 6 + x, pagopension.getNombres(), arial10));
                sheet.addCell(new Label(2, 6 + x, pagopension.getId_curso(), arial10));
                sheet.addCell(new Label(3, 6 + x, "MEN", arial10));
                if (pagopension.getDepositosAsignadas().size() != 0) {
                    List fechasCuotaMax = this.sigaa.getCuotasByCuota(9);
                    int cont = 0;
                    for (int i = 0; i < fechasCuotaMax.size(); i++) {
                        Cuota cuota = (Cuota) fechasCuotaMax.get(i);
                        sheet.addCell(new jxl.write.Number(4 + cont, 6 + x, 0, arial10));
                        sheet.addCell(new Label(5 + cont, 6 + x, cuota.getDia_mes_fin().substring(0, 2) + "/" + cuota.getDia_mes_fin().substring(3, 5) + "/" + id_gestion, arial10));
                        cont = cont + 2;
                    }
                }
                if (pagopension.getCuota() == 1) {
                    int cont = 0;
                    for (int i = 0; i < pagopension.getDepositosAsignadas().size(); i++) {
                        DepositoAsignada depositoasignada = (DepositoAsignada) pagopension.getDepositosAsignadas().get(i);
                        sheet.addCell(new jxl.write.Number(4 + cont, 6 + x, depositoasignada.getMonto(), arial10));
                        sheet.addCell(new Label(5 + cont, 6 + x, formatearFecha2(depositoasignada.getFecha_fin()), arial10));
                        cont = cont + 2;
                    }
                }
                if (pagopension.getCuota() == 3) {
                    int cont = 0;
                    for (int i = 0; i < pagopension.getDepositosAsignadas().size(); i++) {
                        DepositoAsignada depositoasignada = (DepositoAsignada) pagopension.getDepositosAsignadas().get(i);
                        sheet.addCell(new jxl.write.Number(4 + cont, 6 + x, depositoasignada.getMonto(), arial10));
                        sheet.addCell(new Label(5 + cont, 6 + x, formatearFecha2(depositoasignada.getFecha_fin()), arial10));
                        if (i == 0) {
                            cont = cont + 4;
                        } else {
                            cont = cont + 6;
                        }
                    }
                }
                if (pagopension.getCuota() == 9) {
                    int cont = 0;
                    for (int i = 0; i < pagopension.getDepositosAsignadas().size(); i++) {
                        DepositoAsignada depositoasignada = (DepositoAsignada) pagopension.getDepositosAsignadas().get(i);
                        sheet.addCell(new jxl.write.Number(4 + cont, 6 + x, depositoasignada.getMonto(), arial10));
                        sheet.addCell(new Label(5 + cont, 6 + x, formatearFecha2(depositoasignada.getFecha_fin()), arial10));
                        cont = cont + 2;
                    }
                }
            }
            workbook.write();
            workbook.close();
        } catch (Exception de) {
            de.printStackTrace();
        }
        return archivo;
    }

    private String formatearFechaLong(Date fecha) {
        String nuevo = new String();
        SimpleDateFormat formato = new SimpleDateFormat("EEEE, d 'de' MMMM 'de' yyyy");
        nuevo = formato.format(fecha);
        return nuevo;
    }

    private String setGenerarReporteExcel(String id_gestion, List detallesPagosCursos) {
        String archivo = "Reporte" + formatearFecha(new Date()) + ".xls";
        try {
            String dirDestino = System.getenv("SIGAA_HOME") + "/documentos/depositos/repExcel/";
            String dirPlantilla = System.getenv("SIGAA_HOME") + "/documentos/plantillas_excel/plantilla_estado_pago_pensiones.xls";
            Workbook plantilla = Workbook.getWorkbook(new File(dirPlantilla));
            WritableWorkbook workbook = Workbook.createWorkbook(new File(dirDestino + archivo), plantilla);
            WritableFont arial_8 = new WritableFont(WritableFont.ARIAL, 8);
            WritableCellFormat arial8 = new WritableCellFormat(arial_8);
            arial8.setBorder(Border.ALL, BorderLineStyle.DASHED);
            WritableCellFormat arial8green = new WritableCellFormat(arial_8);
            arial8green.setBorder(Border.ALL, BorderLineStyle.DASHED, Colour.GRAY_80);
            arial8green.setBackground(Colour.LIGHT_GREEN);
            WritableCellFormat arial8blue = new WritableCellFormat(arial_8);
            arial8blue.setBorder(Border.ALL, BorderLineStyle.DASHED, Colour.GRAY_80);
            arial8blue.setBackground(Colour.LIGHT_BLUE);
            WritableCellFormat arial8orange = new WritableCellFormat(arial_8);
            arial8orange.setBackground(Colour.LIGHT_ORANGE);
            arial8orange.setBorder(Border.ALL, BorderLineStyle.DASHED, Colour.GRAY_80);
            WritableFont arial_10 = new WritableFont(WritableFont.ARIAL, 10);
            WritableCellFormat arial10 = new WritableCellFormat(arial_10);
            WritableSheet sheet = workbook.getSheet("DETALLES");
            sheet.addCell(new Label(1, 1, "GESTION ACADEMICA " + id_gestion, arial10));
            sheet.addCell(new Label(3, 5, this.formatearFechaLong(new Date()), arial10));
            int cont = 0;
            for (int x = 0; x < detallesPagosCursos.size(); x++) {
                Curso curso = (Curso) detallesPagosCursos.get(x);
                for (int i = 0; i < curso.getEstudiantes().size(); i++) {
                    Estudiante estudiante = (Estudiante) curso.getEstudiantes().get(i);
                    cont++;
                    sheet.addCell(new jxl.write.Number(1, 9 + cont, cont, arial8));
                    sheet.addCell(new jxl.write.Number(2, 9 + cont, estudiante.getCodigo(), arial8));
                    sheet.addCell(new Label(3, 9 + cont, estudiante.getDesc_curso(), arial8));
                    sheet.addCell(new Label(4, 9 + cont, estudiante.getPaterno(), arial8));
                    sheet.addCell(new Label(5, 9 + cont, estudiante.getMaterno(), arial8));
                    sheet.addCell(new Label(6, 9 + cont, estudiante.getNombres(), arial8));
                    sheet.addCell(new jxl.write.Number(7, 9 + cont, estudiante.getNroCuotas() - 1, arial8));
                    int c = 0;
                    for (int j = 0; j < estudiante.getDepAsignadas().size(); j++) {
                        DepositoAsignada depositoasignada = (DepositoAsignada) estudiante.getDepAsignadas().get(j);
                        if (estudiante.getNroCuotas() == 4) {
                            if (depositoasignada.getMonto_dep() == 0 && depositoasignada.getEstado_cuota().equals("cancelado")) {
                                sheet.addCell(new jxl.write.Number(8 + c, 9 + cont, depositoasignada.getMonto_dep_cuota(), arial8blue));
                            } else {
                                if (depositoasignada.getEstado_cuota().equals("cancelado")) {
                                    sheet.addCell(new jxl.write.Number(8 + c, 9 + cont, depositoasignada.getMonto_dep(), arial8green));
                                } else {
                                    sheet.addCell(new jxl.write.Number(8 + c, 9 + cont, depositoasignada.getMonto_dep(), arial8orange));
                                }
                            }
                            if (j == 0) {
                                c = c + 2;
                            } else {
                                c = c + 3;
                            }
                        }
                        if (estudiante.getNroCuotas() == 10 || estudiante.getNroCuotas() == 2) {
                            if (depositoasignada.getMonto_dep() == 0 && depositoasignada.getEstado_cuota().equals("cancelado")) {
                                sheet.addCell(new jxl.write.Number(8 + j, 9 + cont, depositoasignada.getMonto_dep_cuota(), arial8blue));
                            } else {
                                if (depositoasignada.getEstado_cuota().equals("cancelado")) {
                                    sheet.addCell(new jxl.write.Number(8 + j, 9 + cont, depositoasignada.getMonto_dep(), arial8green));
                                } else {
                                    sheet.addCell(new jxl.write.Number(8 + j, 9 + cont, depositoasignada.getMonto_dep(), arial8orange));
                                }
                            }

                        }
                    }
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
