/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.sysmaqv.domain;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Marco Antonio
 */
public class Inscripcion extends Curso {

    private int id_inscripcion;
    private int id_gestion;
    private String id_estudiante;
    private Date fecha_registro;
    private String id_gondola;
    private String id_familia;
    private String nombres;
    private String paterno;
    private String materno;
    private int nro_hijo;
    private String nombre_foto;
    private int codigo;
    private String id_estado;
    private List chasideRespuestas;
    private PagoCole pagoCole=new PagoCole();

    public int getId_inscripcion() {
        return id_inscripcion;
    }

    public void setId_inscripcion(int id_inscripcion) {
        this.id_inscripcion = id_inscripcion;
    }

    public int getId_gestion() {
        return id_gestion;
    }

    public void setId_gestion(int id_gestion) {
        this.id_gestion = id_gestion;
    }

    public String getId_estudiante() {
        return id_estudiante;
    }

    public void setId_estudiante(String id_estudiante) {
        this.id_estudiante = id_estudiante;
    }

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public String getId_gondola() {
        return id_gondola;
    }

    public void setId_gondola(String id_gondola) {
        this.id_gondola = id_gondola;
    }

    public String getId_familia() {
        return id_familia;
    }

    public void setId_familia(String id_familia) {
        this.id_familia = id_familia;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public int getNro_hijo() {
        return nro_hijo;
    }

    public void setNro_hijo(int nro_hijo) {
        this.nro_hijo = nro_hijo;
    }

    public String getNombre_foto() {
        return nombre_foto;
    }

    public void setNombre_foto(String nombre_foto) {
        this.nombre_foto = nombre_foto;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getId_estado() {
        return id_estado;
    }

    public void setId_estado(String id_estado) {
        this.id_estado = id_estado;
    }

    public List getChasideRespuestas() {
        return chasideRespuestas;
    }

    public void setChasideRespuestas(List chasideRespuestas) {
        this.chasideRespuestas = chasideRespuestas;
    }

    public PagoCole getPagoCole() {
        return pagoCole;
    }

    public void setPagoCole(PagoCole pagoCole) {
        this.pagoCole = pagoCole;
    }
}

