/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package spring.sysmaqv.domain;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author MARCO
 */
public class Modalidad implements Serializable {
    private String id_modalidad;
    private String modalidad;
    private String icono;
    private int orden;
    private List tareas;

    public String getId_modalidad() {
        return id_modalidad;
    }

    public void setId_modalidad(String id_modalidad) {
        this.id_modalidad = id_modalidad;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public List getTareas() {
        return tareas;
    }

    public void setTareas(List tareas) {
        this.tareas = tareas;
    }

  
}
