/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.sysmaqv.domain;
 
/**
 * @ Company : M&M
 * @ Author  : Marco Antonio Quenta Velasco
 * @ Gestion : 2009
 * @ La Paz - Bolivia
 */
public class Conducta {

    private int id_conducta;
    private String id_estudiante;
    private String id_curso;
    private String id_tipo_conducta;
    private int dps;
    private String id_eva;
    private boolean estado;
    private String tipo_conducta;
    private String diagnostico;
    private int promedio;
    private int id_gestion;
    private String status;

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

    public int getDps() {
        return dps;
    }

    public void setDps(int dps) {
        this.dps = dps;
    }

    public String getId_eva() {
        return id_eva;
    }

    public void setId_eva(String id_eva) {
        this.id_eva = id_eva;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getId_conducta() {
        return id_conducta;
    }

    public void setId_conducta(int id_conducta) {
        this.id_conducta = id_conducta;
    }

    public String getId_tipo_conducta() {
        return id_tipo_conducta;
    }

    public void setId_tipo_conducta(String id_tipo_conducta) {
        this.id_tipo_conducta = id_tipo_conducta;
    }

    public String getTipo_conducta() {
        return tipo_conducta;
    }

    public void setTipo_conducta(String tipo_conducta) {
        this.tipo_conducta = tipo_conducta;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public int getPromedio() {
        return promedio;
    }

    public void setPromedio(int promedio) {
        this.promedio = promedio;
    }

    public int getId_gestion() {
        return id_gestion;
    }

    public void setId_gestion(int id_gestion) {
        this.id_gestion = id_gestion;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
