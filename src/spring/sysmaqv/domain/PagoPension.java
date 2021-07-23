/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.sysmaqv.domain;

import java.util.List;

/**
 *
 * @author Marco Antonio
 */
public class PagoPension extends Persona {

    private String id_familia;
    private int id_inscripcion;
    private int id_gestion;
    private double monto_inicial;
    private double monto_total;
    private int descuento;
    private int beca;
    private double pension_total;
//    private int id_cuota;
    private int cuota;
    private String curso; 
//    private double pension_mensual;
    private boolean editable;
    private int nro_hijo;
    private String id_estudiante;
    private String id_curso;
    private String id_usuario;
    private String nombre_foto;
    private int codigo;
    private String id_usuario_reg;
    private int id_pago_pension;
    private List depositosAsignadas;
    private double monto_pagar;
    private double monto_dep;
    private double interes;
    private String nombres;
    private int nroCuotas;

    public String getId_familia() {
        return id_familia;
    }

    public void setId_familia(String id_familia) {
        this.id_familia = id_familia;
    }

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

    public double getMonto_inicial() {
        return monto_inicial;
    }

    public void setMonto_inicial(double monto_inicial) {
        this.monto_inicial = monto_inicial;
    }

    public double getMonto_total() {
        return monto_total;
    }

    public void setMonto_total(double monto_total) {
        this.monto_total = monto_total;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public int getBeca() {
        return beca;
    }

    public void setBeca(int beca) {
        this.beca = beca;
    }

    public double getPension_total() {
        return pension_total;
    }

    public void setPension_total(double pension_total) {
        this.pension_total = pension_total;
    }

//    public int getId_cuota() {
//        return id_cuota;
//    }
//
//    public void setId_cuota(int id_cuota) {
//        this.id_cuota = id_cuota;
//    }
    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

//    public double getPension_mensual() {
//        return pension_mensual;
//    }
//
//    public void setPension_mensual(double pension_mensual) {
//        this.pension_mensual = pension_mensual;
//    }
    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public int getNro_hijo() {
        return nro_hijo;
    }

    public void setNro_hijo(int nro_hijo) {
        this.nro_hijo = nro_hijo;
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

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
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

    public String getId_usuario_reg() {
        return id_usuario_reg;
    }

    public void setId_usuario_reg(String id_usuario_reg) {
        this.id_usuario_reg = id_usuario_reg;
    }

    public int getCuota() {
        return cuota;
    }

    public void setCuota(int cuota) {
        this.cuota = cuota;
    }

    public int getId_pago_pension() {
        return id_pago_pension;
    }

    public void setId_pago_pension(int id_pago_pension) {
        this.id_pago_pension = id_pago_pension;
    }

    public List getDepositosAsignadas() {
        return depositosAsignadas;
    }

    public void setDepositosAsignadas(List depositosAsignadas) {
        this.depositosAsignadas = depositosAsignadas;
    }

    public double getMonto_pagar() {
        return monto_pagar;
    }

    public void setMonto_pagar(double monto_pagar) {
        this.monto_pagar = monto_pagar;
    }

    public double getMonto_dep() {
        return monto_dep;
    }

    public void setMonto_dep(double monto_dep) {
        this.monto_dep = monto_dep;
    }

    public double getInteres() {
        return interes;
    }

    public void setInteres(double interes) {
        this.interes = interes;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public int getNroCuotas() {
        return nroCuotas;
    }

    public void setNroCuotas(int nroCuotas) {
        this.nroCuotas = nroCuotas;
    }
}
