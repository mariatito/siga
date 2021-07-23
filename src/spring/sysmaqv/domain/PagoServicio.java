/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.sysmaqv.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Marco Antonio
 */
public class PagoServicio extends Servicio{
    private String id_familia;
    private int id_inscripcion;
    private int id_servicio;
    private int id_gestion;
    private Date fec_registro;
    private String estado;
    private Date fec_pago;
    private Date sfec_pago;
    private String nombres;
    private String curso;
    private boolean editable;
    private String id_usuario;
    

    public String getId_familia() {
        return id_familia;
    }

    public void setId_familia(String id_familia) {
        this.id_familia = id_familia;
    }

    public int getId_inscripcion() {
        return id_inscripcion;
    }

    public void setId_inscripcion(int id_inscripcion) {
        this.id_inscripcion = id_inscripcion;
    }

    public int getId_servicio() {
        return id_servicio;
    }

    public void setId_servicio(int id_servicio) {
        this.id_servicio = id_servicio;
    }

    public int getId_gestion() {
        return id_gestion;
    }

    public void setId_gestion(int id_gestion) {
        this.id_gestion = id_gestion;
    }

    public Date getFec_registro() {
        return fec_registro;
    }

    public void setFec_registro(Date fec_registro) {
        this.fec_registro = fec_registro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombres() {
        return nombres;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public Date getFec_pago() {
        return fec_pago;
    }

    public void setFec_pago(Date fec_pago) {
        this.fec_pago = fec_pago;
    }
    public String getSfec_pago() {
        if (fec_pago == null) {
            return null;
        } else {
            String nuevo = new String();
            SimpleDateFormat formato = new SimpleDateFormat("EEEE, d 'de' MMMM 'de' yyyy");
            nuevo = formato.format(fec_pago);
            return nuevo;
        }
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public String getId_usuario() {
        return id_usuario;
    }

//    public void setId_usuario(String id_usuario) {
//        this.id_usuario = id_usuario;
//    }
}
