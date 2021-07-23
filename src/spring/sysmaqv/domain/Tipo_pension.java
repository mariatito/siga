/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package spring.sysmaqv.domain;

/**
 *
 * @author Marco Antonio
 */
public class Tipo_pension {
    private int id_pension;
    private int id_gestion;
    private String id_curso;
    private double cuota_inicial;
    private double cuota_total;
    private String curso;
    private String ciclo;

    public int getId_pension() {
        return id_pension;
    }

    public void setId_pension(int id_pension) {
        this.id_pension = id_pension;
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

    public double getCuota_inicial() {
        return cuota_inicial;
    }

    public void setCuota_inicial(double cuota_inicial) {
        this.cuota_inicial = cuota_inicial;
    }

    public double getCuota_total() {
        return cuota_total;
    }

    public void setCuota_total(double cuota_total) {
        this.cuota_total = cuota_total;
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
   
}
