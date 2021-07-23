/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.sysmaqv.domain;

import java.util.List;

/**
 *
 * @author MARCO
 */
public class Chaside {

    private String id_chaside;
    private String area;
    private List aptitudes;
    private List intereses;

    public String getId_chaside() {
        return id_chaside;
    }

    public void setId_chaside(String id_chaside) {
        this.id_chaside = id_chaside;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public List getAptitudes() {
        return aptitudes;
    }

    public void setAptitudes(List aptitudes) {
        this.aptitudes = aptitudes;
    }

    public List getIntereses() {
        return intereses;
    }

    public void setIntereses(List intereses) {
        this.intereses = intereses;
    }
}
