package spring.sysmaqv.domain;

import java.util.List;

public class Materia {

    private String id_materia;
    private String materia;
    private boolean promocionable;
    private List evaluaciones;
    private CursoMateria cursomateria = new CursoMateria();
    private String id_estudiante;
    private String id_curso_materia;
    private int id_gestion;

    public String getId_materia() {
        return id_materia;
    }

    public void setId_materia(String id_materia) {
        this.id_materia = id_materia;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public boolean isPromocionable() {
        return promocionable;
    }

    public void setPromocionable(boolean promocionable) {
        this.promocionable = promocionable;
    }

    public List getEvaluaciones() {
        return evaluaciones;
    }

    public void setEvaluaciones(List evaluaciones) {
        this.evaluaciones = evaluaciones;
    }

    public CursoMateria getCursomateria() {
        return cursomateria;
    }

    public void setCursomateria(CursoMateria cursomateria) {
        this.cursomateria = cursomateria;
    }

    public String getId_estudiante() {
        return id_estudiante;
    }

    public void setId_estudiante(String id_estudiante) {
        this.id_estudiante = id_estudiante;
    }

    public String getId_curso_materia() {
        return id_curso_materia;
    }

    public void setId_curso_materia(String id_curso_materia) {
        this.id_curso_materia = id_curso_materia;
    }

    public int getId_gestion() {
        return id_gestion;
    }

    public void setId_gestion(int id_gestion) {
        this.id_gestion = id_gestion;
    }

}
