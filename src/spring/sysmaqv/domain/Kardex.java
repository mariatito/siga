/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.sysmaqv.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Pluks
 */
public class Kardex implements Serializable {

    private int idkardex;
    private String id_curso_materia;
    private String id_estudiante;
    private int periodo;
    private String id_docente;
    private String temario;
    private Date fecha;
    private Date sfecha;
    private Date fecreg;
    private String usrreg;
    private Date fecmod;
    private String usrmod;
    private String docente;
//    private KardexDetalle kardexdetalle = new KardexDetalle();
    private List kardexdetalles;
    private int cant_kardexdetalle;

    public int getIdkardex() {
        return idkardex;
    }

    public void setIdkardex(int idkardex) {
        this.idkardex = idkardex;
    }

    public String getId_curso_materia() {
        return id_curso_materia;
    }

    public void setId_curso_materia(String id_curso_materia) {
        this.id_curso_materia = id_curso_materia;
    }

    public String getId_estudiante() {
        return id_estudiante;
    }

    public void setId_estudiante(String id_estudiante) {
        this.id_estudiante = id_estudiante;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public String getId_docente() {
        return id_docente;
    }

    public void setId_docente(String id_docente) {
        this.id_docente = id_docente;
    }

    public String getTemario() {
        return temario;
    }

    public void setTemario(String temario) {
        this.temario = temario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFecreg() {
        return fecreg;
    }

    public void setFecreg(Date fecreg) {
        this.fecreg = fecreg;
    }

    public String getUsrreg() {
        return usrreg;
    }

    public void setUsrreg(String usrreg) {
        this.usrreg = usrreg;
    }

    public Date getFecmod() {
        return fecmod;
    }

    public void setFecmod(Date fecmod) {
        this.fecmod = fecmod;
    }

    public String getUsrmod() {
        return usrmod;
    }

    public void setUsrmod(String usrmod) {
        this.usrmod = usrmod;
    }

    public String getSfecha() {
        if (fecha == null) {
            return null;
        } else {
            String nuevo = new String();
            SimpleDateFormat formato = new SimpleDateFormat(" d 'de' MMMM 'de' yyyy");
            nuevo = formato.format(fecha);
            return nuevo;
        }
    }

    public String getDocente() {
        return docente;
    }

    public void setDocente(String docente) {
        this.docente = docente;
    }

    public int getCant_kardexdetalle() {
        return cant_kardexdetalle;
    }

    public void setCant_kardexdetalle(int cant_kardexdetalle) {
        this.cant_kardexdetalle = cant_kardexdetalle;
    }

    public List getKardexdetalles() {
        return kardexdetalles;
    }

    public void setKardexdetalles(List kardexdetalles) {
        this.kardexdetalles = kardexdetalles;
    }
}
