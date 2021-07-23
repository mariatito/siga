/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.sysmaqv.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author MARCO ANTONIO QUENTA VELASCO
 */
public class DepositoAsignada extends PagoPension {

    private int id_dep_asignada;
    private String mes;
    private double monto;
    private Date fecha_ini;
    private Date fecha_fin;
    private int nro_cuota;
    private String estado;
    String sfecha_ini;
    String sfecha_fin;
    private Date fecha_dep;
    private double monto_dep;
    private double interes;
    private double monto_dep_cuota;
    private String estado_cuota;
    String sfecha_dep;
    String sdfecha_dep;
    private int factura;
    private String observacion;
    private int control;

    public int getId_dep_asignada() {
        return id_dep_asignada;
    }

    public void setId_dep_asignada(int id_dep_asignada) {
        this.id_dep_asignada = id_dep_asignada;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Date getFecha_ini() {
        return fecha_ini;
    }

    public void setFecha_ini(Date fecha_ini) {
        this.fecha_ini = fecha_ini;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public int getNro_cuota() {
        return nro_cuota;
    }

    public void setNro_cuota(int nro_cuota) {
        this.nro_cuota = nro_cuota;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getSfecha_ini() {
        if (fecha_ini == null) {
            return null;
        } else {
            String nuevo = new String();
            SimpleDateFormat formato = new SimpleDateFormat(" d 'de' MMMM 'de' yyyy");
            nuevo = formato.format(fecha_ini);
            return nuevo;
        }
    }

    public String getSdfecha_dep() {
        if (fecha_dep == null) {
            return null;
        } else {
            String nuevo = new String();
            SimpleDateFormat formato = new SimpleDateFormat(" d 'de' MMMM 'de' yyyy");
            nuevo = formato.format(fecha_dep);
            return nuevo;
        }
    }

    public String getSfecha_fin() {
        if (fecha_fin == null) {
            return null;
        } else {
            String nuevo = new String();
            SimpleDateFormat formato = new SimpleDateFormat(" d 'de' MMMM 'de' yyyy");
            nuevo = formato.format(fecha_fin);
            return nuevo;
        }
    }

    public String getSfecha_dep() {
        if (fecha_dep == null) {
            return null;
        } else {
            String nuevo = new String();
            SimpleDateFormat formato = new SimpleDateFormat("dd'-'MM'-'yyyy");
            nuevo = formato.format(fecha_dep);
            return nuevo;
        }
    }

    public Date getFecha_dep() {
        return fecha_dep;
    }

    public void setFecha_dep(Date fecha_dep) {
        this.fecha_dep = fecha_dep;
    }

    public double getMonto_dep() {
        return monto_dep;
    }

    public void setMonto_dep(double monto_dep) {
        this.monto_dep = monto_dep;
    }

    public double getInteres() {
        return interes;
    }

    public void setInteres(double interes) {
        this.interes = interes;
    }

    public double getMonto_dep_cuota() {
        return monto_dep_cuota;
    }

    public void setMonto_dep_cuota(double monto_dep_cuota) {
        this.monto_dep_cuota = monto_dep_cuota;
    }

    public String getEstado_cuota() {
        return estado_cuota;
    }

    public void setEstado_cuota(String estado_cuota) {
        this.estado_cuota = estado_cuota;
    }

    public int getFactura() {
        return factura;
    }

    public void setFactura(int factura) {
        this.factura = factura;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public int getControl() {
        return control;
    }

    public void setControl(int control) {
        this.control = control;
    }
}
