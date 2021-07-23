/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.sysmaqv.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author MARCO
 */
public class PagoCole {
    private int id_pago_cole;
    private int id_inscripcion;
    private String descripcion;
    private String nro_factura;
    private double monto;
    private Date fecha_pago;
    private boolean cancelado;
    String sfecha_pago;

    public int getId_pago_cole() {
        return id_pago_cole;
    }

    public void setId_pago_cole(int id_pago_cole) {
        this.id_pago_cole = id_pago_cole;
    }

    public int getId_inscripcion() {
        return id_inscripcion;
    }

    public void setId_inscripcion(int id_inscripcion) {
        this.id_inscripcion = id_inscripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNro_factura() {
        return nro_factura;
    }

    public void setNro_factura(String nro_factura) {
        this.nro_factura = nro_factura;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Date getFecha_pago() {
        return fecha_pago;
    }

    public void setFecha_pago(Date fecha_pago) {
        this.fecha_pago = fecha_pago;
    }

    public boolean isCancelado() {
        return cancelado;
    }

    public void setCancelado(boolean cancelado) {
        this.cancelado = cancelado;
    }

    public String getSfecha_pago() {
        if (fecha_pago == null) {
            return null;
        } else {
            String nuevo = new String();
            SimpleDateFormat formato = new SimpleDateFormat("DD', 'MMMM' de 'yyyy");
            nuevo = formato.format(fecha_pago);
            return nuevo;
        }
    }
}
