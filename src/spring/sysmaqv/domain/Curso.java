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
 * @author Marco Antonio
 */
public class Curso implements Serializable {

    private String id_curso;
    private String curso;
    private String ciclo;
//    private String titulo;
//    private String docente;
    private List estudiantes;
    private List evaluaciones;
    private Docente docenteOBJ;
    private String id_estudiante;
    private List materias;
    private int orden_k;
    private int orden_p;
    private int orden_s;
    private int id_gestion;
    private List faltas;
    private Gestion gestion = new Gestion();
    private List conductas;
    private List cursomaterias;
    private Materia materia = new Materia();
    private CursoMateria cursomateria = new CursoMateria();
    private List fecRegNotas;
    private int numEstudiantes;
    private int ptrim1;
    private int ptrim2;
    private int ptrim3;
    private int ptrim4;
    private String id_trimestre;
    private String evas;
    private String cursomats;
    private int cant;
    
    private String desc_curso;
    private Date fecha1;
    private Date fecha2;

    public String getId_curso() {
        return id_curso;
    }

    public void setId_curso(String id_curso) {
        this.id_curso = id_curso;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

//    public String getDocente() {
//        return docente;
//    }
//
//    public void setDocente(String docente) {
//        this.docente = docente;
//    }
//
//    public String getTitulo() {
//        return titulo;
//    }
//
//    public void setTitulo(String titulo) {
//        this.titulo = titulo;
//    }
    public List getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List estudiantes) {
        this.estudiantes = estudiantes;
    }

    public List getEvaluaciones() {
        return evaluaciones;
    }

    public void setEvaluaciones(List evaluaciones) {
        this.evaluaciones = evaluaciones;
    }

    public Docente getDocenteOBJ() {
        return docenteOBJ;
    }

    public void setDocenteOBJ(Docente docenteOBJ) {
        this.docenteOBJ = docenteOBJ;
    }

    public String getId_estudiante() {
        return id_estudiante;
    }

    public void setId_estudiante(String id_estudiante) {
        this.id_estudiante = id_estudiante;
    }

    public List getMaterias() {
        return materias;
    }

    public void setMaterias(List materias) {
        this.materias = materias;
    }

    public int getOrden_k() {
        return orden_k;
    }

    public void setOrden_k(int orden_k) {
        this.orden_k = orden_k;
    }

    public int getOrden_p() {
        return orden_p;
    }

    public void setOrden_p(int orden_p) {
        this.orden_p = orden_p;
    }

    public int getOrden_s() {
        return orden_s;
    }

    public void setOrden_s(int orden_s) {
        this.orden_s = orden_s;
    }

    public int getId_gestion() {
        return id_gestion;
    }

    public void setId_gestion(int id_gestion) {
        this.id_gestion = id_gestion;
    }

    public List getFaltas() {
        return faltas;
    }

    public void setFaltas(List faltas) {
        this.faltas = faltas;
    }

    public Gestion getGestion() {
        return gestion;
    }

    public void setGestion(Gestion gestion) {
        this.gestion = gestion;
    }

    public List getConductas() {
        return conductas;
    }

    public void setConductas(List conductas) {
        this.conductas = conductas;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public CursoMateria getCursomateria() {
        return cursomateria;
    }

    public void setCursomateria(CursoMateria cursomateria) {
        this.cursomateria = cursomateria;
    }


    public int getNumEstudiantes() {
        return numEstudiantes;
    }

    public void setNumEstudiantes(int numEstudiantes) {
        this.numEstudiantes = numEstudiantes;
    }

    public int getPtrim1() {
        return ptrim1;
    }

    public void setPtrim1(int ptrim1) {
        this.ptrim1 = ptrim1;
    }

    public int getPtrim2() {
        return ptrim2;
    }

    public void setPtrim2(int ptrim2) {
        this.ptrim2 = ptrim2;
    }

    public int getPtrim3() {
        return ptrim3;
    }

    public void setPtrim3(int ptrim3) {
        this.ptrim3 = ptrim3;
    }
    public int getPtrim4() {
        return ptrim4;
    }

    public void setPtrim4(int ptrim4) {
        this.ptrim4 = ptrim4;
    }

    public String getId_trimestre() {
        return id_trimestre;
    }

    public void setId_trimestre(String id_trimestre) {
        this.id_trimestre = id_trimestre;
    }

    public List getFecRegNotas() {
        return fecRegNotas;
    }

    public void setFecRegNotas(List fecRegNotas) {
        this.fecRegNotas = fecRegNotas;
    }

    public String getDesc_curso() {
        return desc_curso;
    }

    public void setDesc_curso(String desc_curso) {
        this.desc_curso = desc_curso;
    }

    public List getCursomaterias() {
        return cursomaterias;
    }

    public void setCursomaterias(List cursomaterias) {
        this.cursomaterias = cursomaterias;
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

    public String getEvas() {
        return evas;
    }

    public void setEvas(String evas) {
        this.evas = evas;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    public String getCursomats() {
        return cursomats;
    }

    public void setCursomats(String cursomats) {
        this.cursomats = cursomats;
    }
}
