/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.sysmaqv.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author marco
 */
public class PlanEvaluacion {

    private String id_evaluacion;
    private String id_curso_materia;
    private String evaluacion;
    private int nota;
    private int dps;
    private Date fec_evaluacion;
    private String descripcion;
    private Date fec_limite;
    private Date fec_registro;
    private String estado;
    private Date sfec_evaluacion;
    private Date sfec_limite;
    private String id_curso;
    private String id_eva;
    private int id_gestion;

    public String getId_evaluacion() {
        return id_evaluacion;
    }

    public void setId_evaluacion(String id_evaluacion) {
        this.id_evaluacion = id_evaluacion;
    }

    public String getId_curso_materia() {
        return id_curso_materia;
    }

    public void setId_curso_materia(String id_curso_materia) {
        this.id_curso_materia = id_curso_materia;
    }

    public String getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(String evaluacion) {
        this.evaluacion = evaluacion;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public int getDps() {
        return dps;
    }

    public void setDps(int dps) {
        this.dps = dps;
    }

    public Date getFec_evaluacion() {
        return fec_evaluacion;
    }

    public void setFec_evaluacion(Date fec_evaluacion) {
        this.fec_evaluacion = fec_evaluacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFec_limite() {
        return fec_limite;
    }

    public void setFec_limite(Date fec_limite) {
        this.fec_limite = fec_limite;
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

    public String getSfec_evaluacion() {
        if (fec_evaluacion == null) {
            return null;
        } else {
            String nuevo = new String();
            SimpleDateFormat formato = new SimpleDateFormat("EEEE, d 'de' MMMM 'de' yyyy");
            nuevo = formato.format(fec_evaluacion);
            return nuevo;
        }
    }

    public String getSfec_limite() {
        if (fec_limite == null) {
            return null;
        } else {
            String nuevo = new String();
            SimpleDateFormat formato = new SimpleDateFormat("EEEE, d 'de' MMMM 'de' yyyy");
            nuevo = formato.format(fec_limite);
            return nuevo;
        }
    }

    public String getId_curso() {
        return id_curso;
    }

    public void setId_curso(String id_curso) {
        this.id_curso = id_curso;
    }

    public String getId_eva() {
        return id_eva;
    }

    public void setId_eva(String id_eva) {
        this.id_eva = id_eva;
    }

    public int getId_gestion() {
        return id_gestion;
    }

    public void setId_gestion(int id_gestion) {
        this.id_gestion = id_gestion;
    }
}
