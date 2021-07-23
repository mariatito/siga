/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.sysmaqv.domain;

/**
 *
 * @author Administrador
 */
public class Servicio {

    private int id_servicio;
    private String tipo_servicio;
    private double monto_servicio;
    private int id_gestion;

    public int getId_servicio() {
        return id_servicio;
    }

    public void setId_servicio(int id_servicio) {
        this.id_servicio = id_servicio;
    }

    public String getTipo_servicio() {
        return tipo_servicio;
    }

    public void setTipo_servicio(String tipo_servicio) {
        this.tipo_servicio = tipo_servicio;
    }

    public double getMonto_servicio() {
        return monto_servicio;
    }

    public void setMonto_servicio(double monto_servicio) {
        this.monto_servicio = monto_servicio;
    }

    public int getId_gestion() {
        return id_gestion;
    }

    public void setId_gestion(int id_gestion) {
        this.id_gestion = id_gestion;
    }
}
