/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.sysmaqv.domain;

/**
 * @ Company : M&M
 * @ Author : Marco Antonio Quenta Velasco
 * @ Gestion : 2009
 * @ La Paz - Bolivia
 */
public class Evaluacion extends PlanEvaluacion {

    private String id_estudiante;
    private int id_gestion;
    private int id_cualitativa;
    private String cualitativa;
    private String categoria;
    private String id_cur;
    private String cualitativa_p;
    private String id_docente;
    private int pa;
    private int pf;
    private int tamanio;
    private int nota_plan;
    private int dps_plan;
    private int notasuma;

    public String getId_estudiante() {
        return id_estudiante;
    }

    public void setId_estudiante(String id_estudiante) {
        this.id_estudiante = id_estudiante;
    }

    public int getId_cualitativa() {
        return id_cualitativa;
    }

    public void setId_cualitativa(int id_cualitativa) {
        this.id_cualitativa = id_cualitativa;
    }

    public String getCualitativa() {
        return cualitativa;
    }

    public void setCualitativa(String cualitativa) {
        this.cualitativa = cualitativa;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getId_cur() {
        return id_cur;
    }

    public void setId_cur(String id_cur) {
        this.id_cur = id_cur;
    }

    public String getCualitativa_p() {
        return cualitativa_p;
    }

    public void setCualitativa_p(String cualitativa_p) {
        this.cualitativa_p = cualitativa_p;
    }

    public String getId_docente() {
        return id_docente;
    }

    public void setId_docente(String id_docente) {
        this.id_docente = id_docente;
    }

    public int getPa() {
        return pa;
    }

    public void setPa(int pa) {
        this.pa = pa;
    }

    public int getPf() {
        return pf;
    }

    public void setPf(int pf) {
        this.pf = pf;
    }

    public int getTamanio() {
        int v = 0;
        if (cualitativa != null) {
            v = cualitativa.length();
        }
        return v;
    }

    public int getNota_plan() {
        return nota_plan;
    }

    public void setNota_plan(int nota_plan) {
        this.nota_plan = nota_plan;
    }

    public int getDps_plan() {
        return dps_plan;
    }

    public void setDps_plan(int dps_plan) {
        this.dps_plan = dps_plan;
    }

    public int getNotasuma() {
        return notasuma;
    }

    public void setNotasuma(int notasuma) {
        this.notasuma = notasuma;
    }
}
