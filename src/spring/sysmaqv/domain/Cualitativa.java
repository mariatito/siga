/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package spring.sysmaqv.domain;

/**
 *
 * @author MARCO ANTONIO QUENTA VELASCO
 */
public class Cualitativa {

    private int id_cualitativa;
    private String id_curso_materia;
    private String categoria;
    private String descripcion;

    public int getId_cualitativa() {
        return id_cualitativa;
    }

    public void setId_cualitativa(int id_cualitativa) {
        this.id_cualitativa = id_cualitativa;
    }

    public String getId_curso_materia() {
        return id_curso_materia;
    }

    public void setId_curso_materia(String id_curso_materia) {
        this.id_curso_materia = id_curso_materia;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
