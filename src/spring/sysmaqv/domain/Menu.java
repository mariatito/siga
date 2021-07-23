package spring.sysmaqv.domain;

import java.io.Serializable;
import java.util.List;

public class Menu implements Serializable {

    private List modalidades;
    private List tareas;
    private Tarea tarea = new Tarea();

    public List getModalidades() {
        return modalidades;
    }

    public void setModalidades(List modalidades) {
        this.modalidades = modalidades;
    }

    public List getTareas() {
        return tareas;
    }

    public void setTareas(List tareas) {
        this.tareas = tareas;
    }

    public Tarea getTarea() {
        return tarea;
    }

    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
    }
}

