/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.sysmaqv.domain;

import java.io.Serializable;

/**
 *
 * @author Marco Antonio
 */
public class Informacion implements Serializable {

    private int informacion_id;
    private String colegio;
    private String sie;
    private String dependencia;
    private String distrito1;
    private String distrito2;

    public String getColegio() {
        return colegio;
    }

    public void setColegio(String colegio) {
        this.colegio = colegio;
    }

    public String getSie() {
        return sie;
    }

    public void setSie(String sie) {
        this.sie = sie;
    }

    public String getDependencia() {
        return dependencia;
    }

    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
    }

    public String getDistrito1() {
        return distrito1;
    }

    public void setDistrito1(String distrito1) {
        this.distrito1 = distrito1;
    }

    public String getDistrito2() {
        return distrito2;
    }

    public void setDistrito2(String distrito2) {
        this.distrito2 = distrito2;
    }

    public int getInformacion_id() {
        return informacion_id;
    }

    public void setInformacion_id(int informacion_id) {
        this.informacion_id = informacion_id;
    }
}
