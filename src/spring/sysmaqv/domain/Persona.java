package spring.sysmaqv.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Persona implements Serializable {

  /* Private Fields */
  private String id_persona;
  private String dip;
  private String paterno;
  private String materno;
  private String nombres;
  private String direccion;
  private String telefono1;
  private String telefono2;
  private String id_sexo;
  private String id_tipo_usuario;
  private Date fecha_registro;
  private String tipo_usuario;
  private String id_tpersonal;
  private String tpersonal;
  public String id_tipo_cargo;
  public String cargo;


    public String getId_persona() {
        return id_persona;
    }

    public void setId_persona(String id_persona) {
        this.id_persona = id_persona;
    }

    public String getDip() {
        return dip;
    }

    public void setDip(String dip) {
        this.dip = dip;
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

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public String getId_sexo() {
        return id_sexo;
    }

    public void setId_sexo(String id_sexo) {
        this.id_sexo = id_sexo;
    }

    public String getId_tipo_usuario() {
        return id_tipo_usuario;
    }

    public void setId_tipo_usuario(String id_tipo_usuario) {
        this.id_tipo_usuario = id_tipo_usuario;
    }
    public Date getFecha_registro() {
        return fecha_registro;
    }
    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public String getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(String tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }

    public String getId_tpersonal() {
        return id_tpersonal;
    }

    public void setId_tpersonal(String id_tpersonal) {
        this.id_tpersonal = id_tpersonal;
    }

    public String getTpersonal() {
        return tpersonal;
    }

    public void setTpersonal(String tpersonal) {
        this.tpersonal = tpersonal;
    }

    public String getId_tipo_cargo() {
        return id_tipo_cargo;
    }

    public void setId_tipo_cargo(String id_tipo_cargo) {
        this.id_tipo_cargo = id_tipo_cargo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

  
}

