/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.sysmaqv.domain;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author Pluks
 */
public class KardexDetalle implements Serializable {

    private int idkardexdetalle;
    private int idkardex;
    private String id_estudiante;
    private int fsl;
    private int a;
    private int tnr;
    private int aa;
    private int su;
    private String otrasfaltas;
    private String aspectospositivos;
    private String observaciones;
    private String estudiante;
    private String id_curso_materia;
    private Date fecha;
    private String sfecha;
    private String sfecha2;
    private String actividades;
    private String docente;
    private int id_gestion;
    private String id_curso;
    private String materia;
    private Date fecreg;

    public int getIdkardexdetalle() {
        return idkardexdetalle;
    }

    public void setIdkardexdetalle(int idkardexdetalle) {
        this.idkardexdetalle = idkardexdetalle;
    }

    public int getIdkardex() {
        return idkardex;
    }

    public void setIdkardex(int idkardex) {
        this.idkardex = idkardex;
    }

    public String getId_estudiante() {
        return id_estudiante;
    }

    public void setId_estudiante(String id_estudiante) {
        this.id_estudiante = id_estudiante;
    }

    public int getFsl() {
        return fsl;
    }

    public void setFsl(int fsl) {
        this.fsl = fsl;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getTnr() {
        return tnr;
    }

    public void setTnr(int tnr) {
        this.tnr = tnr;
    }

    public int getAa() {
        return aa;
    }

    public void setAa(int aa) {
        this.aa = aa;
    }

    public int getSu() {
        return su;
    }

    public void setSu(int su) {
        this.su = su;
    }

    public String getOtrasfaltas() {
        return otrasfaltas;
    }

    public void setOtrasfaltas(String otrasfaltas) {
        this.otrasfaltas = otrasfaltas;
    }

    public String getAspectospositivos() {
        return aspectospositivos;
    }

    public void setAspectospositivos(String aspectospositivos) {
        this.aspectospositivos = aspectospositivos;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(String estudiante) {
        this.estudiante = estudiante;
    }

    public String getId_curso_materia() {
        return id_curso_materia;
    }

    public void setId_curso_materia(String id_curso_materia) {
        this.id_curso_materia = id_curso_materia;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getSfecha() {
        if (getFecha() == null) {
            return null;
        } else {
            String nuevo = new String();
            SimpleDateFormat formato = new SimpleDateFormat(" d 'de' MMMM 'de' yyyy");
            nuevo = formato.format(getFecha());
            return nuevo;
        }
    }

    public String getSfecha2() {
        if (getFecha() == null) {
            return null;
        } else {
            String nuevo = new String();
            SimpleDateFormat formato = new SimpleDateFormat("dd'/'MM'/'yyyy");
            nuevo = formato.format(getFecha());
            return nuevo;
        }
    }

    public void setSfecha(String sfecha) {
        this.sfecha = sfecha;
    }

    public void setSfecha2(String sfecha2) {
        this.sfecha2 = sfecha2;
    }

    public String getActividades() {
        return actividades;
    }

    public void setActividades(String actividades) {
        this.actividades = actividades;
    }

    public String getDocente() {
        return docente;
    }

    public void setDocente(String docente) {
        this.docente = docente;
    }

    public int getId_gestion() {
        return id_gestion;
    }

    public void setId_gestion(int id_gestion) {
        this.id_gestion = id_gestion;
    }

    public String getId_curso() {
        return id_curso;
    }

    public void setId_curso(String id_curso) {
        this.id_curso = id_curso;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public Date getFecreg() {
        return fecreg;
    }

    public void setFecreg(Date fecreg) {
        this.fecreg = fecreg;
    }
}
