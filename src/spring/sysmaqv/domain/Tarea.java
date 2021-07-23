/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.sysmaqv.domain;

import java.util.Date;

/**
 *
 * @author MARCO
 */
public class Tarea extends Modalidad {

    private String id_tarea;
    private String tarea;
    private String url;
    private boolean activo;
    private String id_tpersonal;
    private Date fec_inicio;
    private Date Fec_final;
    private String descripcion;

    public String getId_tarea() {
        return id_tarea;
    }

    public void setId_tarea(String id_tarea) {
        this.id_tarea = id_tarea;
    }

    public String getTarea() {
        return tarea;
    }

    public void setTarea(String tarea) {
        this.tarea = tarea;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getId_tpersonal() {
        return id_tpersonal;
    }

    public void setId_tpersonal(String id_tpersonal) {
        this.id_tpersonal = id_tpersonal;
    }

    public Date getFec_inicio() {
        return fec_inicio;
    }

    public void setFec_inicio(Date fec_inicio) {
        this.fec_inicio = fec_inicio;
    }

    public Date getFec_final() {
        return Fec_final;
    }

    public void setFec_final(Date Fec_final) {
        this.Fec_final = Fec_final;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
