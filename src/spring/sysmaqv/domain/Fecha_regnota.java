package spring.sysmaqv.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Marco Antonio
 */
public class Fecha_regnota {

    private String id_eva;
    private Date fec_limite;
    private Date sfec_limite;
    private int id_gestion;
    private Date fec_inicio;
    private String estado;
    private Date sfec_inicio;
    private int periodos;
    private String descripcion;
    private int idperiodo;
    private List periodocursos;
    private int nota;
    private int dps;

    public String getId_eva() {
        return id_eva;
    }

    public void setId_eva(String id_eva) {
        this.id_eva = id_eva;
    }

    public Date getFec_limite() {
        return fec_limite;
    }

    public void setFec_limite(Date fec_limite) {
        this.fec_limite = fec_limite;
    }

    public String getSfec_limite() {
        if (fec_limite == null) {
            return null;
        } else {
            String nuevo = new String();
            SimpleDateFormat formato = new SimpleDateFormat("EEEE, d 'de' MMMM 'de' yyyy");
            nuevo = formato.format(fec_limite);
            return nuevo;
        }
    }

    public int getId_gestion() {
        return id_gestion;
    }

    public void setId_gestion(int id_gestion) {
        this.id_gestion = id_gestion;
    }

    public Date getFec_inicio() {
        return fec_inicio;
    }

    public void setFec_inicio(Date fec_inicio) {
        this.fec_inicio = fec_inicio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getSfec_inicio() {
        if (fec_inicio == null) {
            return null;
        } else {
            String nuevo = new String();
            SimpleDateFormat formato = new SimpleDateFormat("EEEE, d 'de' MMMM 'de' yyyy");
            nuevo = formato.format(fec_inicio);
            return nuevo;
        }
    }

    public int getPeriodos() {
        return periodos;
    }

    public void setPeriodos(int periodos) {
        this.periodos = periodos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List getPeriodocursos() {
        return periodocursos;
    }

    public void setPeriodocursos(List periodocursos) {
        this.periodocursos = periodocursos;
    }

    public int getIdperiodo() {
        return idperiodo;
    }

    public void setIdperiodo(int idperiodo) {
        this.idperiodo = idperiodo;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public int getDps() {
        return dps;
    }

    public void setDps(int dps) {
        this.dps = dps;
    }
}
