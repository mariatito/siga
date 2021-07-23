/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.sysmaqv.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author marco
 */
public class Familia implements Serializable {

    private String id_familia;
    private String nombre_apellidos_tutor_1;
    private String nombre_apellidos_tutor_2;
    private String nombre_apellidos_tutor_3;
    private String ci_tutor_1;
    private String ci_tutor_2;
    private String ci_tutor_3;
    private String ci_resp_pago;
    private String direccion_1;
    private String direccion_2;
    private String telefono_1;
    private String telefono_2;
    private Date fecha_registro;
    private String telefonos;
    private String e_mail;
    private String e_mailRP;
    private String observacion;
    private List estudiantes;
    private String estado;
     private String lugtrab;

    public String getId_familia() {
        return id_familia;
    }

    public void setId_familia(String id_familia) {
        this.id_familia = id_familia;
    }

    public String getNombre_apellidos_tutor_1() {
        return nombre_apellidos_tutor_1;
    }

    public void setNombre_apellidos_tutor_1(String nombre_apellidos_tutor_1) {
        this.nombre_apellidos_tutor_1 = nombre_apellidos_tutor_1;
    }

    public String getNombre_apellidos_tutor_2() {
        return nombre_apellidos_tutor_2;
    }

    public void setNombre_apellidos_tutor_2(String nombre_apellidos_tutor_2) {
        this.nombre_apellidos_tutor_2 = nombre_apellidos_tutor_2;
    }

    public String getNombre_apellidos_tutor_3() {
        return nombre_apellidos_tutor_3;
    }

    public void setNombre_apellidos_tutor_3(String nombre_apellidos_tutor_3) {
        this.nombre_apellidos_tutor_3 = nombre_apellidos_tutor_3;
    }

    public String getCi_tutor_1() {
        return ci_tutor_1;
    }

    public void setCi_tutor_1(String ci_tutor_1) {
        this.ci_tutor_1 = ci_tutor_1;
    }

    public String getCi_tutor_2() {
        return ci_tutor_2;
    }

    public void setCi_tutor_2(String ci_tutor_2) {
        this.ci_tutor_2 = ci_tutor_2;
    }

    public String getCi_tutor_3() {
        return ci_tutor_3;
    }

    public void setCi_tutor_3(String ci_tutor_3) {
        this.ci_tutor_3 = ci_tutor_3;
    }

    public String getCi_resp_pago() {
        return ci_resp_pago;
    }

    public void setCi_resp_pago(String ci_resp_pago) {
        this.ci_resp_pago = ci_resp_pago;
    }

    public String getDireccion_1() {
        return direccion_1;
    }

    public void setDireccion_1(String direccion_1) {
        this.direccion_1 = direccion_1;
    }

    public String getDireccion_2() {
        return direccion_2;
    }

    public void setDireccion_2(String direccion_2) {
        this.direccion_2 = direccion_2;
    }

    public String getTelefono_1() {
        return telefono_1;
    }

    public void setTelefono_1(String telefono_1) {
        this.telefono_1 = telefono_1;
    }

    public String getTelefono_2() {
        return telefono_2;
    }

    public void setTelefono_2(String telefono_2) {
        this.telefono_2 = telefono_2;
    }

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public String getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(String telefonos) {
        this.telefonos = telefonos;
    }

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getE_mailRP() {
        return e_mailRP;
    }

    public void setE_mailRP(String e_mailRP) {
        this.e_mailRP = e_mailRP;
    }

    public List getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List estudiantes) {
        this.estudiantes = estudiantes;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getLugtrab() {
        return lugtrab;
    }

    public void setLugtrab(String lugtrab) {
        this.lugtrab = lugtrab;
    }
}
