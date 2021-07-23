/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.sysmaqv.web.spring.sigaa.consultas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import spring.sysmaqv.domain.Estudiante;
import spring.sysmaqv.domain.Inscripcion;
import spring.sysmaqv.domain.Nota;
import spring.sysmaqv.domain.Personal;
import spring.sysmaqv.domain.Profesion;
import spring.sysmaqv.domain.logic.SigaaInterface;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.util.WebUtils;

/**
 *
 * @author MARCO
 */
public class Profesiones implements Controller {

    private SigaaInterface sigaa;
    private String ecxessTimeView;
    private String perfectView;

    public void setSigaa(SigaaInterface sigaa) {
        this.sigaa = sigaa;
    }

    public void setEcxessTimeView(String ecxessTimeView) {
        this.ecxessTimeView = ecxessTimeView;
    }

    public void setPerfectView(String perfectView) {
        this.perfectView = perfectView;
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String url = request.getServletPath();
        Personal personal = (Personal) WebUtils.getSessionAttribute(request, "___personal");
        if (personal != null) {
            if (!this.sigaa.personalAutorizado(personal, url)) {
                return new ModelAndView("seguridad/Error", null);
            }
            Map retorno = new HashMap();

            String id_gestion = request.getParameter("id_gestion");
            if (id_gestion != null) {
                Inscripcion inscripcion = new Inscripcion();
                inscripcion.setId_curso("S4");
                inscripcion.setId_gestion(Integer.parseInt(id_gestion));
                List estudiantes = this.sigaa.getNotasFinalesOfEstudiantes(inscripcion);
                List newEstudiantes = new ArrayList();
                for (int i = 0; i < estudiantes.size(); i++) {
                    Estudiante estudiante = (Estudiante) estudiantes.get(i);
                    List profesiones = new ArrayList();
                    for (int j = 1; j <= 12; j++) {
                        Profesion profesion = new Profesion();
                        int bio = 0;
                        int civ = 0;
                        int com = 0;
                        int edf = 0;
                        int fil = 0;
                        int fis = 0;
                        int geo = 0;
                        int his = 0;
                        int ing = 0;
                        int lit = 0;
                        int mat = 0;
                        int mus = 0;
                        int pla = 0;
                        int qui = 0;
                        int rel = 0;
                        double promedio = 0;
                        for (int h = 0; h < estudiante.getNotas().size(); h++) {
                            Nota nota = (Nota) estudiante.getNotas().get(h);
                            promedio = promedio + nota.getNotaanual();
                            if (j == 1) {//Agronomia
                                if (nota.getId_materia().equals("MAT")) {
                                    mat = nota.getNotaanual();
                                } else if (nota.getId_materia().equals("FIS")) {
                                    fis = nota.getNotaanual();
                                } else if (nota.getId_materia().equals("QUI")) {
                                    qui = nota.getNotaanual();
                                } else if (nota.getId_materia().equals("BIO")) {
                                    bio = nota.getNotaanual();
                                }
                            }
                            if (j == 2) {//Ingenieria
                                if (nota.getId_materia().equals("MAT")) {
                                    mat = nota.getNotaanual();
                                } else if (nota.getId_materia().equals("FIS")) {
                                    fis = nota.getNotaanual();
                                } else if (nota.getId_materia().equals("QUI")) {
                                    qui = nota.getNotaanual();
                                }
                            }
                            if (j == 3) {//Informatica
                                if (nota.getId_materia().equals("MAT")) {
                                    mat = nota.getNotaanual();
                                } else if (nota.getId_materia().equals("FIS")) {
                                    fis = nota.getNotaanual();
                                } else if (nota.getId_materia().equals("COM")) {
                                    com = nota.getNotaanual();
                                }
                            }
                            if (j == 4) {//Arquitectura
                                if (nota.getId_materia().equals("MAT")) {
                                    mat = nota.getNotaanual();
                                } else if (nota.getId_materia().equals("FIS")) {
                                    fis = nota.getNotaanual();
                                } else if (nota.getId_materia().equals("PLA")) {
                                    pla = nota.getNotaanual();
                                }
                            }
                            if (j == 5) {//Medicina
                                if (nota.getId_materia().equals("MAT")) {
                                    mat = nota.getNotaanual();
                                } else if (nota.getId_materia().equals("QUI")) {
                                    qui = nota.getNotaanual();
                                } else if (nota.getId_materia().equals("BIO")) {
                                    bio = nota.getNotaanual();
                                }
                            }
                            if (j == 6) {//Economia
                                if (nota.getId_materia().equals("MAT")) {
                                    mat = nota.getNotaanual();
                                }
                            }
                            if (j == 7) {//Musica
//                                if (nota.getId_materia().equals("LIT")) {
//                                    lit = nota.getNotaanual();
//                                } else
                                if (nota.getId_materia().equals("MUS")) {
                                    mus = nota.getNotaanual();
                                }
                            }
                            if (j == 8) {//religion
//                                if (nota.getId_materia().equals("LIT")) {
//                                    lit = nota.getNotaanual();
//                                } else
                                if (nota.getId_materia().equals("REL")) {
                                    rel = nota.getNotaanual();
                                }
                            }
                            if (j == 9) {//Derecho
                                if (nota.getId_materia().equals("CIV")) {
                                    civ = nota.getNotaanual();
                                }
                            }
                            if (j == 10) {//Lingüistica
                                if (nota.getId_materia().equals("LIT")) {
                                    lit = nota.getNotaanual();
                                } else if (nota.getId_materia().equals("ING")) {
                                    ing = nota.getNotaanual();
                                }
                            }
                            if (j == 11) {//Sociologia
                                if (nota.getId_materia().equals("LIT")) {
                                    lit = nota.getNotaanual();
                                } else if (nota.getId_materia().equals("FIL")) {
                                    fil = nota.getNotaanual();
                                } else if (nota.getId_materia().equals("HIS")) {
                                    his = nota.getNotaanual();
                                }
                            }
                            if (j == 12) {//Turismo
                                if (nota.getId_materia().equals("GEO")) {
                                    geo = nota.getNotaanual();
                                } else if (nota.getId_materia().equals("HIS")) {
                                    his = nota.getNotaanual();
                                }
                            }
                        }
                        promedio = Math.round(((double) promedio / estudiante.getNotas().size()) * Math.pow(10, 0)) / Math.pow(10, 0); // 100%
                        promedio = (92 * promedio) / 100;// 95%
                        /* ---------------------- arbol de decisiones*/
                        String prof = "";
                        if (mat > promedio) {
                            if (fis > promedio) {
                                if (bio > promedio) {
                                    if (qui > promedio) {
                                        prof = "AGRONOMIA";
                                    }
                                } else {
                                    if (qui > promedio) {
                                        prof = "INGENIERIA";
                                    } else {
                                        if (com > promedio) {
                                            prof = "INFORMATICA";
                                        } else {
                                            if (pla > promedio) {
                                                prof = "ARQUITECTURA";
                                            }
                                        }
                                    }
                                }
                            } else {
                                if (qui > promedio) {
                                    if (bio > promedio) {
                                        prof = "MEDICINA";
                                    }
                                } else {
                                    prof = "ECONOMIA";
                                }
                            }
                        } else {
                            if (lit > promedio) {
                                if (ing > promedio) {
                                    prof = "LINGÜISTICA";
                                } else {
                                    if (fil > promedio) {
                                        if (his > promedio) {
                                            prof = "SOCIOLOGIA";
                                        }
                                    }
                                }
                            } else {
                                if (civ > promedio) {
                                    prof = "DERECHO";
                                } else {
                                    if (geo > promedio) {
                                        if (his > promedio) {
                                            prof = "TURISMO";
                                        }
                                    } else {
                                        if (mus > promedio) {
                                            prof = "ED. MUSICAL";
                                        } else {
                                            if (rel > promedio) {
                                                prof = "ED. RELIGIOSO";
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        /*----------------------*/
                        if (!prof.equals("")) {
                            profesion.setProfesion(prof);
                            profesiones.add(profesion);
                        }
                        bio = civ = com = edf = fil = fis = geo = his = ing = lit = mat = mus = pla = qui = rel = 0;

                    }
                    estudiante.setProfesiones(profesiones);
                    newEstudiantes.add(estudiante);
                }
                retorno.put("estudiantes", estudiantes);
                retorno.put("id_gestion", id_gestion);
                retorno.put("lista", "_lista");
                return new ModelAndView(this.perfectView, retorno);
            }
            retorno.put("gestion", this.sigaa.getGestionActivo());
            return new ModelAndView(this.perfectView, retorno);
        } else {
            return new ModelAndView(this.ecxessTimeView, "url", url);
        }
    }
}
