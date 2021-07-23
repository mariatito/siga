/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.sysmaqv.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Marco Antonio
 */
public class CursoMateria {

    private String id_curso_materia;
    private String id_curso;
    private String id_materia;
    private String id_docente;
    private String materia;
    private String titulo;
    private String docente;
    private String curso;
    private int id_gestion;
    private boolean estado;
    private String id_docente_tutor;
    private String id_docente_coordinador;
    private List cursomaterias;
    private String id_docente_regfaltas;
    private List planEvaluaciones;
    private int nota;
    private int dps;
    private int nota_min;
    private String cualitativa;    
    private List kardex;
    private String id_estudiante;
    private Date fecha;
    private int promedio;
    private int aprobados;
    private int reprobados;
    private Date fecha1;
    private Date fecha2;
    private List evaluaciones;

    public String getId_curso_materia() {
        return id_curso_materia;
    }

    public void setId_curso_materia(String id_curso_materia) {
        this.id_curso_materia = id_curso_materia;
    }

    public String getId_curso() {
        return id_curso;
    }

    public void setId_curso(String id_curso) {
        this.id_curso = id_curso;
    }

    public String getId_materia() {
        return id_materia;
    }

    public void setId_materia(String id_materia) {
        this.id_materia = id_materia;
    }

    public String getId_docente() {
        return id_docente;
    }

    public void setId_docente(String id_docente) {
        this.id_docente = id_docente;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDocente() {
        return docente;
    }

    public void setDocente(String docente) {
        this.docente = docente;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public int getId_gestion() {
        return id_gestion;
    }

    public void setId_gestion(int id_gestion) {
        this.id_gestion = id_gestion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getId_docente_tutor() {
        return id_docente_tutor;
    }

    public void setId_docente_tutor(String id_docente_tutor) {
        this.id_docente_tutor = id_docente_tutor;
    }

    public List getCursomaterias() {
        return cursomaterias;
    }

    public void setCursomaterias(List cursomaterias) {
        this.cursomaterias = cursomaterias;
    }

    public String getId_docente_regfaltas() {
        return id_docente_regfaltas;
    }

    public void setId_docente_regfaltas(String id_docente_regfaltas) {
        this.id_docente_regfaltas = id_docente_regfaltas;
    }

    public List getPlanEvaluaciones() {
        return planEvaluaciones;
    }

    public void setPlanEvaluaciones(List planEvaluaciones) {
        this.planEvaluaciones = planEvaluaciones;
    }

    public String getId_docente_coordinador() {
        return id_docente_coordinador;
    }

    public void setId_docente_coordinador(String id_docente_coordinador) {
        this.id_docente_coordinador = id_docente_coordinador;
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

    public String getCualitativa() {
        return cualitativa;
    }

    public void setCualitativa(String cualitativa) {
        this.cualitativa = cualitativa;
    }

    public List getKardex() {
        return kardex;
    }

    public void setKardex(List kardex) {
        this.kardex = kardex;
    }

    public String getId_estudiante() {
        return id_estudiante;
    }

    public void setId_estudiante(String id_estudiante) {
        this.id_estudiante = id_estudiante;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getPromedio() {
        return promedio;
    }

    public void setPromedio(int promedio) {
        this.promedio = promedio;
    }

    public int getAprobados() {
        return aprobados;
    }

    public void setAprobados(int aprobados) {
        this.aprobados = aprobados;
    }

    public int getReprobados() {
        return reprobados;
    }

    public void setReprobados(int reprobados) {
        this.reprobados = reprobados;
    }

    public int getNota_min() {
        return nota_min;
    }

    public void setNota_min(int nota_min) {
        this.nota_min = nota_min;
    }

    public Date getFecha1() {
        return fecha1;
    }

    public void setFecha1(Date fecha1) {
        this.fecha1 = fecha1;
    }

    public Date getFecha2() {
        return fecha2;
    }

    public void setFecha2(Date fecha2) {
        this.fecha2 = fecha2;
    }

    public List getEvaluaciones() {
        return evaluaciones;
    }

    public void setEvaluaciones(List evaluaciones) {
        this.evaluaciones = evaluaciones;
    }




}
