/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.sysmaqv.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Marco Antonio Quenta Velasco
 * @Copyright(c) mqav
 * @año 2009
 */
public class Falta {

    private int id_falta;
    private String id_estudiante;
    private String id_curso;
    private String id_eva;
    private int fcl;
    private int fsl;
    private int retraso;
    private int id_gestion;
    private String tipo;
    private String id_usuario_reg;
    private String id_usuario_modific;
    private List faltas;
    private Date fecha_falta;
    private String sfecha_falta;
    private String nombre_reg;
    private String nombre_mod;
    private Date fecha_modific;
    private String sfecha_modific;
    private int id_fecha_falta;
    private String status;
    private boolean estado;
    private int uniforme;
    
    private String sffalta;    
    private String sfmodific;

    public int getId_falta() {
        return id_falta;
    }

    public void setId_falta(int id_falta) {
        this.id_falta = id_falta;
    }

    public String getId_estudiante() {
        return id_estudiante;
    }

    public void setId_estudiante(String id_estudiante) {
        this.id_estudiante = id_estudiante;
    }

    public String getId_curso() {
        return id_curso;
    }

    public void setId_curso(String id_curso) {
        this.id_curso = id_curso;
    }

    public String getId_eva() {
        return id_eva;
    }

    public void setId_eva(String id_eva) {
        this.id_eva = id_eva;
    }

    public int getFcl() {
        return fcl;
    }

    public void setFcl(int fcl) {
        this.fcl = fcl;
    }

    public int getFsl() {
        return fsl;
    }

    public void setFsl(int fsl) {
        this.fsl = fsl;
    }

    public int getRetraso() {
        return retraso;
    }

    public void setRetraso(int retraso) {
        this.retraso = retraso;
    }

    public int getId_gestion() {
        return id_gestion;
    }

    public void setId_gestion(int id_gestion) {
        this.id_gestion = id_gestion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getId_usuario_reg() {
        return id_usuario_reg;
    }

    public void setId_usuario_reg(String id_usuario_reg) {
        this.id_usuario_reg = id_usuario_reg;
    }

    public String getId_usuario_modific() {
        return id_usuario_modific;
    }

    public void setId_usuario_modific(String id_usuario_modific) {
        this.id_usuario_modific = id_usuario_modific;
    }

    public List getFaltas() {
        return faltas;
    }

    public void setFaltas(List faltas) {
        this.faltas = faltas;
    }

    public Date getFecha_falta() {
        return fecha_falta;
    }

    public void setFecha_falta(Date fecha_falta) {
        this.fecha_falta = fecha_falta;
    }

    public String getSfecha_falta() {
        if (fecha_falta == null) {
            return null;
        } else {
            String nuevo = new String();
            SimpleDateFormat formato = new SimpleDateFormat("EEEE, d 'de' MMMM");
            nuevo = formato.format(fecha_falta);
            return nuevo;
        }
    }

    public Date getFecha_modific() {
        return fecha_modific;
    }

    public void setFecha_modific(Date fecha_modific) {
        this.fecha_modific = fecha_modific;
    }

    public String getSfecha_modific() {
        if (fecha_modific == null) {
            return null;
        } else {
            String nuevo = new String();
            SimpleDateFormat formato = new SimpleDateFormat("EEEE, d 'de' MMMM");
            nuevo = formato.format(fecha_modific);
            return nuevo;
        }
    }

    public String getNombre_reg() {
        return nombre_reg;
    }

    public String getNombre_mod() {
        return nombre_mod;
    }

    public int getId_fecha_falta() {
        return id_fecha_falta;
    }

    public void setId_fecha_falta(int id_fecha_falta) {
        this.id_fecha_falta = id_fecha_falta;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUniforme() {
        return uniforme;
    }

    public void setUniforme(int uniforme) {
        this.uniforme = uniforme;
    }
    
    public String getSffalta() {
        if (fecha_falta == null) {
            return null;
        } else {
            String nuevo = new String();
            SimpleDateFormat formato = new SimpleDateFormat("EEE, d 'de' MMM");
            nuevo = formato.format(fecha_falta);
            return nuevo;
        }
    }
    
    public String getSfmodific() {
        if (fecha_modific == null) {
            return null;
        } else {
            String nuevo = new String();
            SimpleDateFormat formato = new SimpleDateFormat("EEE, d 'de' MMM");
            nuevo = formato.format(fecha_modific);
            return nuevo;
        }
    }
}
