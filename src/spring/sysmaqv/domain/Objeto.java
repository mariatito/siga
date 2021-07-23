
package spring.sysmaqv.domain;

import java.io.Serializable;

public class Objeto implements Serializable  {
    
    private String id;
    private String valor;
    private String abreviatura;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }


}