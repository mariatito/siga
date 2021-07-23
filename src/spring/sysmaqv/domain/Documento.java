/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.sysmaqv.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Antonio
 */
public class Documento {
    private int iddocumento;
    private String descripcion;
    private String url;
    private String nota;
    private String idpersona;
    private String area;
    private Date fec_exp;
    private String sfec_exp;
    private Date fec_reg;

    public int getIddocumento() {
        return iddocumento;
    }

    public void setIddocumento(int iddocumento) {
        this.iddocumento = iddocumento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(String idpersona) {
        this.idpersona = idpersona;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Date getFec_exp() {
        return fec_exp;
    }

    public void setFec_exp(Date fec_exp) {
        this.fec_exp = fec_exp;
    }

    public Date getFec_reg() {
        return fec_reg;
    }

    public void setFec_reg(Date fec_reg) {
        this.fec_reg = fec_reg;
    }
    
    public String getSfec_exp() {
        if (fec_exp == null) {
            return null;
        } else {
            String nuevo = new String();
            SimpleDateFormat formato = new SimpleDateFormat("dd'-'MM'-'yyyy");
            nuevo = formato.format(fec_exp);
            return nuevo;
        }
    }
}
