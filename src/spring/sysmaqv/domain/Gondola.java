/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.sysmaqv.domain;

/**
 *
 * @author Marco Antonio
 */
public class Gondola {

    private String id_gondola;
    private String placa;
    private int nro_gondola;
    private String conductor;
    private String ruta;
    private String color;
    private String empresa;

    public String getId_gondola() {
        return id_gondola;
    }

    public void setId_gondola(String id_gondola) {
        this.id_gondola = id_gondola;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getNro_gondola() {
        return nro_gondola;
    }

    public void setNro_gondola(int nro_gondola) {
        this.nro_gondola = nro_gondola;
    }

    public String getConductor() {
        return conductor;
    }

    public void setConductor(String conductor) {
        this.conductor = conductor;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
}
