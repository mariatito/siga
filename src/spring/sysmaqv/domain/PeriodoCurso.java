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
public class PeriodoCurso implements Serializable {

    private int idperiodo;
    private String id_curso;
    private String id_curso_alias;
    private int gestion;
    private List fecnotas;

    public int getIdperiodo() {
        return idperiodo;
    }

    public void setIdperiodo(int idperiodo) {
        this.idperiodo = idperiodo;
    }

    public String getId_curso() {
        return id_curso;
    }

    public void setId_curso(String id_curso) {
        this.id_curso = id_curso;
    }

    public String getId_curso_alias() {
        return id_curso_alias;
    }

    public void setId_curso_alias(String id_curso_alias) {
        this.id_curso_alias = id_curso_alias;
    }

    public int getGestion() {
        return gestion;
    }

    public void setGestion(int gestion) {
        this.gestion = gestion;
    }

    public List getFecnotas() {
        return fecnotas;
    }

    public void setFecnotas(List fecnotas) {
        this.fecnotas = fecnotas;
    }
}
