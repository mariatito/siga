/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.sysmaqv.domain;

/**
 *
 * @author MARCO ANTONIO QUENTA VELASCO
 */
public class Cuota {
    private int id_cuota;
    private int cuota;
    private String mes;
    private String dia_mes_ini;
    private String dia_mes_fin;

    public int getId_cuota() {
        return id_cuota;
    }

    public void setId_cuota(int id_cuota) {
        this.id_cuota = id_cuota;
    }

    public int getCuota() {
        return cuota;
    }

    public void setCuota(int cuota) {
        this.cuota = cuota;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getDia_mes_ini() {
        return dia_mes_ini;
    }

    public void setDia_mes_ini(String dia_mes_ini) {
        this.dia_mes_ini = dia_mes_ini;
    }

    public String getDia_mes_fin() {
        return dia_mes_fin;
    }

    public void setDia_mes_fin(String dia_mes_fin) {
        this.dia_mes_fin = dia_mes_fin;
    }
}
