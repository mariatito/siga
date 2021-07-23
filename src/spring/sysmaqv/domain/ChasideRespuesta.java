/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.sysmaqv.domain;

import java.util.Date;

/**
 *
 * @author MARCO
 */
public class ChasideRespuesta {
    private int id_chaside_respuesta;
    private String id_estudiante;
    private int id_gestion;
    private String id_curso;
    private String id_chaside_pregunta;
    private String respuesta;
    private String id_chaside;
    private Date fec_registro;

    public int getId_chaside_respuesta() {
        return id_chaside_respuesta;
    }

    public void setId_chaside_respuesta(int id_chaside_respuesta) {
        this.id_chaside_respuesta = id_chaside_respuesta;
    }

    public String getId_estudiante() {
        return id_estudiante;
    }

    public void setId_estudiante(String id_estudiante) {
        this.id_estudiante = id_estudiante;
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

 
   

    public String getId_chaside() {
        return id_chaside;
    }

    public void setId_chaside(String id_chaside) {
        this.id_chaside = id_chaside;
    }

    public Date getFec_registro() {
        return fec_registro;
    }

    public void setFec_registro(Date fec_registro) {
        this.fec_registro = fec_registro;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getId_chaside_pregunta() {
        return id_chaside_pregunta;
    }

    public void setId_chaside_pregunta(String id_chaside_pregunta) {
        this.id_chaside_pregunta = id_chaside_pregunta;
    }
}
