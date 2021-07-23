package spring.sysmaqv.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Gestion implements Serializable {

    private int id_gestion;
    private String colegio;
    private String direccion;
    private String telefono;
    private String fax;
    private String directora;
    private String lema;
    private String mail;
    private Date fecha_registro;
    private boolean estado;
    private List gestiones;
    private int dt1;
    private int dt2;
    private int dt3;
    private String nombre;
    private String titulo;

    public int getId_gestion() {
        return id_gestion;
    }

    public void setId_gestion(int id_gestion) {
        this.id_gestion = id_gestion;
    }

    public String getColegio() {
        return colegio;
    }

    public void setColegio(String colegio) {
        this.colegio = colegio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getDirectora() {
        return directora;
    }

    public void setDirectora(String directora) {
        this.directora = directora;
    }

    public String getLema() {
        return lema;
    }

    public void setLema(String lema) {
        this.lema = lema;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    public Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
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

    public List getGestiones() {
        return gestiones;
    }

    public void setGestiones(List gestiones) {
        this.gestiones = gestiones;
    }

    public int getDt1() {
        return dt1;
    }

    public void setDt1(int dt1) {
        this.dt1 = dt1;
    }

    public int getDt2() {
        return dt2;
    }

    public void setDt2(int dt2) {
        this.dt2 = dt2;
    }

    public int getDt3() {
        return dt3;
    }

    public void setDt3(int dt3) {
        this.dt3 = dt3;
    }
}