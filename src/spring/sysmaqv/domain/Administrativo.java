package spring.sysmaqv.domain;

import java.io.Serializable;
import java.util.Date;

public class Administrativo extends Persona implements Serializable{

    private String id_administrativo;
    private Date fecha_asume;
    private Date fecha_termina;
    private String id_titulo;
    private String id_tipo_cargo;
    
    private String nombre;
    private String titulo;

    public String getId_administrativo() {
        return id_administrativo;
    }

    public void setId_administrativo(String id_administrativo) {
        this.id_administrativo = id_administrativo;
    }

    public Date getFecha_asume() {
        return fecha_asume;
    }

    public void setFecha_asume(Date fecha_asume) {
        this.fecha_asume = fecha_asume;
    }

    public Date getFecha_termina() {
        return fecha_termina;
    }

    public void setFecha_termina(Date fecha_termina) {
        this.fecha_termina = fecha_termina;
    }

    public String getId_titulo() {
        return id_titulo;
    }

    public void setId_titulo(String id_titulo) {
        this.id_titulo = id_titulo;
    }

    public String getId_tipo_cargo() {
        return id_tipo_cargo;
    }

    public void setId_tipo_cargo(String id_tipo_cargo) {
        this.id_tipo_cargo = id_tipo_cargo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}

