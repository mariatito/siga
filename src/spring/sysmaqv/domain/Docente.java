package spring.sysmaqv.domain;

import java.io.Serializable;

public class Docente  extends Persona implements Serializable {

    private String id_docente;
//    private String id_persona;
    private String id_titulo;
    private String id_tipo_categoria;
    private String cargahoraria;
    private String salario;
    private String titulo;
    private String abreviatura;
    
    public String getId_docente() {
        return id_docente;
    }

    public void setId_docente(String id_docente) {
        this.id_docente = id_docente;
    }

    public String getId_titulo() {
        return id_titulo;
    }

    public void setId_titulo(String id_titulo) {
        this.id_titulo = id_titulo;
    }

    public String getId_tipo_categoria() {
        return id_tipo_categoria;
    }

    public void setId_tipo_categoria(String id_tipo_categoria) {
        this.id_tipo_categoria = id_tipo_categoria;
    }

    public String getCargahoraria() {
        return cargahoraria;
    }

    public void setCargahoraria(String cargahoraria) {
        this.cargahoraria = cargahoraria;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }


}

