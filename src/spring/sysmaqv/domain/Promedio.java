/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.sysmaqv.domain;

/**
 *
 * @author MARCO ANTONIO QUENTA VELASCO
 */
public class Promedio {
    private String id_estudiante;
    private int id_gestion;
    private String id_curso;
    private double  trim1;
    private double trim2;
    private double trim3;
    private double total;

    public String getId_estudiante() {
        return id_estudiante;
    }

    public void setId_estudiante(String id_estudiante) {
        this.id_estudiante = id_estudiante;
    }

    public int getId_gestion() {
        return id_gestion;
    }

    public void setId_gestion(int id_gestion) {
        this.id_gestion = id_gestion;
    }

    public String getId_curso() {
        return id_curso;
    }

    public void setId_curso(String id_curso) {
        this.id_curso = id_curso;
    }

    public double getTrim1() {
        return trim1;
    }

    public void setTrim1(double trim1) {
        this.trim1 = trim1;
    }

    public double getTrim2() {
        return trim2;
    }

    public void setTrim2(double trim2) {
        this.trim2 = trim2;
    }

    public double getTrim3() {
        return trim3;
    }

    public void setTrim3(double trim3) {
        this.trim3 = trim3;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
