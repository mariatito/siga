/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.sysmaqv.domain;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author Pluks
 */
public class KardexGeneralClass implements Serializable {

    private String id_estudiante ;
    private String id_persona ;
    private String paterno ;
    private String materno ;
    private String nombres ;
    private String curso ;
    private String id_curso;
    private int no_notificado;
    private int id_gestion;

    public String getId_estudiante() {
        return id_estudiante;
    }

    public void setId_estudiante(String id_estudiante) {
        this.id_estudiante = id_estudiante;
    }

    public String getId_persona() {
        return id_persona;
    }

    public void setId_persona(String id_persona) {
        this.id_persona = id_persona;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getNombres() {
        return nombres;
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

    public String getId_curso() {
        return id_curso;
    }

    public void setId_curso(String id_curso) {
        this.id_curso = id_curso;
    }

    public int getNo_notificado() {
        return no_notificado;
    }

    public void setNo_notificado(int no_notificado) {
        this.no_notificado = no_notificado;
    }

    public int getId_gestion() {
        return id_gestion;
    }

    public void setId_gestion(int id_gestion) {
        this.id_gestion = id_gestion;
    }
}
