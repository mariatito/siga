/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.sysmaqv.domain;

import java.util.Date;

/**
 * @ Company : M&M
 * @ Author  : Marco Antonio Quenta Velasco
 * @ Gestion : 2010
 * @ La Paz - Bolivia
 */
public class Nota {

    private String id_estudiante;
    private String id_curso;
    private String id_materia;
    private int id_gestion;
    private int nota1;
    private int nota2;
    private int nota3;
    private int nota4;
    private int dps1;
    private int dps2;
    private int dps3;
    private String cualitativa1;
    private String cualitativa2;
    private String cualitativa3;
    private String diagnostico1;
    private String diagnostico2;
    private int notaanual;
    private int notafinal;
    private String observacion;
    private String id_docente;
    private Date fec_registro;

    public String getId_estudiante() {
        return id_estudiante;
    }

    public void setId_estudiante(String id_estudiante) {
        this.id_estudiante = id_estudiante;
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

    public int getId_gestion() {
        return id_gestion;
    }

    public void setId_gestion(int id_gestion) {
        this.id_gestion = id_gestion;
    }

    public int getNota1() {
        return nota1;
    }

    public void setNota1(int nota1) {
        this.nota1 = nota1;
    }

    public int getNota2() {
        return nota2;
    }

    public void setNota2(int nota2) {
        this.nota2 = nota2;
    }

    public int getNota3() {
        return nota3;
    }

    public void setNota3(int nota3) {
        this.nota3 = nota3;
    }

    public int getNota4() {
        return nota4;
    }

    public void setNota4(int nota4) {
        this.nota4 = nota4;
    }

    public int getDps1() {
        return dps1;
    }

    public void setDps1(int dps1) {
        this.dps1 = dps1;
    }

    public int getDps2() {
        return dps2;
    }

    public void setDps2(int dps2) {
        this.dps2 = dps2;
    }

    public int getDps3() {
        return dps3;
    }

    public void setDps3(int dps3) {
        this.dps3 = dps3;
    }

    public String getCualitativa1() {
        return cualitativa1;
    }

    public void setCualitativa1(String cualitativa1) {
        this.cualitativa1 = cualitativa1;
    }

    public String getCualitativa2() {
        return cualitativa2;
    }

    public void setCualitativa2(String cualitativa2) {
        this.cualitativa2 = cualitativa2;
    }

    public String getCualitativa3() {
        return cualitativa3;
    }

    public void setCualitativa3(String cualitativa3) {
        this.cualitativa3 = cualitativa3;
    }

    public String getDiagnostico1() {
        return diagnostico1;
    }

    public void setDiagnostico1(String diagnostico1) {
        this.diagnostico1 = diagnostico1;
    }

    public String getDiagnostico2() {
        return diagnostico2;
    }

    public void setDiagnostico2(String diagnostico2) {
        this.diagnostico2 = diagnostico2;
    }

    public int getNotaanual() {
        return notaanual;
    }

    public void setNotaanual(int notaanual) {
        this.notaanual = notaanual;
    }

    public int getNotafinal() {
        return notafinal;
    }

    public void setNotafinal(int notafinal) {
        this.notafinal = notafinal;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getId_docente() {
        return id_docente;
    }

    public void setId_docente(String id_docente) {
        this.id_docente = id_docente;
    }

    public Date getFec_registro() {
        return fec_registro;
    }

    public void setFec_registro(Date fec_registro) {
        this.fec_registro = fec_registro;
    }

}
