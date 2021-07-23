/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.sysmaqv.web.spring.sigaa.inscripciones;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.util.WebUtils;
import spring.sysmaqv.domain.Curso;
import spring.sysmaqv.domain.Deposito;
import spring.sysmaqv.domain.Personal;
import spring.sysmaqv.domain.logic.SigaaInterface;

/**
 *
 * @author Mi PC, Marco Antonio Quenta Velasco
 */
public class DetalleDepositosBancarios implements Controller {

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

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String url = request.getServletPath();
        Personal personal = (Personal) WebUtils.getSessionAttribute(request, "___personal");
        if (personal != null) {
            Map retorno = new HashMap();
            String opcion = request.getParameter("opcion");
            int gestion = new Date().getYear();
            List cursos = this.sigaa.getSigaaCursos();
            StringBuilder sb = new StringBuilder();
            String html = "";
            String cursosSeleccionados = "";
            for (int k = 0; k < cursos.size(); k++) {
                Curso curso = (Curso) cursos.get(k);
                if (request.getParameter("cu" + curso.getId_curso()) != null) {
                    html = "<input type='checkbox' id='curso" + curso.getId_curso() + "' class='accion' name='cu" + curso.getId_curso() + "' checked style='width: 180px;'><label for='curso" + curso.getId_curso() + "'>" + curso.getCurso() + " de " + curso.getCiclo() + "</label>";
                    cursosSeleccionados += curso.getId_curso() + ",";
                } else {
                    html = "<input type='checkbox' id='curso" + curso.getId_curso() + "' class='accion' name='cu" + curso.getId_curso() + "'  style='width: 180px;'><label for='curso" + curso.getId_curso() + "'>" + curso.getCurso() + " de " + curso.getCiclo() + "</label>";
                }
                html += ((k + 1) % 2 == 0) ? "<br/>" : "";
                sb.append(html);
            }
            int estado = 1;
            int c1 = 0;
            int c2 = 0;
            int c3 = 0;
            int c4 = 0;
            int c5 = 0;
            int c6 = 0;
            int c7 = 0;
            int c8 = 0;
            int c9 = 0;
            int c10 = 0;
            if (opcion != null) {
                gestion = Integer.parseInt(request.getParameter("gestion"));
                estado = Integer.parseInt(request.getParameter("estado"));
                c1 = (request.getParameter("c1") == null) ? 0 : 1;
                c2 = (request.getParameter("c2") == null) ? 0 : 1;
                c3 = (request.getParameter("c3") == null) ? 0 : 1;
                c4 = (request.getParameter("c4") == null) ? 0 : 1;
                c5 = (request.getParameter("c5") == null) ? 0 : 1;
                c6 = (request.getParameter("c6") == null) ? 0 : 1;
                c7 = (request.getParameter("c7") == null) ? 0 : 1;
                c8 = (request.getParameter("c8") == null) ? 0 : 1;
                c9 = (request.getParameter("c9") == null) ? 0 : 1;
                c10 = (request.getParameter("c10") == null) ? 0 : 1;
            }
            retorno.put("selectGestion", gestion);
            retorno.put("selectEstado", estado);
            retorno.put("selectC1", c1);
            retorno.put("selectC2", c2);
            retorno.put("selectC3", c3);
            retorno.put("selectC4", c4);
            retorno.put("selectC5", c5);
            retorno.put("selectC6", c6);
            retorno.put("selectC7", c7);
            retorno.put("selectC8", c8);
            retorno.put("selectC9", c9);
            retorno.put("selectC10", c10);
            Deposito deposito = new Deposito();
            deposito.setId_gestion(gestion);
            deposito.LLenarCursosSQL(cursosSeleccionados);
            int codigo = 0;
            List depositos = this.sigaa.getDetalleDepositosBancarios(deposito);
            List depositoAux = new ArrayList();
            int cont = 0;
            for (int i = 0; i < depositos.size(); i++) {
                Deposito dep = (Deposito) depositos.get(i);
                if (dep.getCodigo() != codigo) {
                    if (codigo != 0) {
                        cont = 0;
                        if (estado == 1) {
                            cont++;
                        } else if (estado == 2) {
                            cont += (c1 == 1 && deposito.getCuota1D() <= 0) ? 1 : 0;
                            cont += (c2 == 1 && deposito.getCuota2D() <= 0) ? 1 : 0;
                            cont += (c3 == 1 && deposito.getCuota3D() <= 0) ? 1 : 0;
                            cont += (c4 == 1 && deposito.getCuota4D() <= 0) ? 1 : 0;
                            cont += (c5 == 1 && deposito.getCuota5D() <= 0) ? 1 : 0;
                            cont += (c6 == 1 && deposito.getCuota6D() <= 0) ? 1 : 0;
                            cont += (c7 == 1 && deposito.getCuota7D() <= 0) ? 1 : 0;
                            cont += (c8 == 1 && deposito.getCuota8D() <= 0) ? 1 : 0;
                            cont += (c9 == 1 && deposito.getCuota9D() <= 0) ? 1 : 0;
                            cont += (c10 == 1 && deposito.getCuota10D() <= 0) ? 1 : 0;
                        } else if (estado == 3) {
                            cont += (c1 == 1 && deposito.getCuota1D() > 0) ? 1 : 0;
                            cont += (c2 == 1 && deposito.getCuota2D() > 0) ? 1 : 0;
                            cont += (c3 == 1 && deposito.getCuota3D() > 0) ? 1 : 0;
                            cont += (c4 == 1 && deposito.getCuota4D() > 0) ? 1 : 0;
                            cont += (c5 == 1 && deposito.getCuota5D() > 0) ? 1 : 0;
                            cont += (c6 == 1 && deposito.getCuota6D() > 0) ? 1 : 0;
                            cont += (c7 == 1 && deposito.getCuota7D() > 0) ? 1 : 0;
                            cont += (c8 == 1 && deposito.getCuota8D() > 0) ? 1 : 0;
                            cont += (c9 == 1 && deposito.getCuota9D() > 0) ? 1 : 0;
                            cont += (c10 == 1 && deposito.getCuota10D() > 0) ? 1 : 0;
                        }
                        if (cont > 0) {
                            depositoAux.add(deposito);
                        }
                    }
                    codigo = dep.getCodigo();
                    deposito = new Deposito();
                    deposito = dep;
                    deposito.setCuota1A(dep.getMonto());
                    deposito.setCuota1D(dep.getMonto_dep());
                }
                switch (dep.getNro_cuota()) {
                    case 1:
                        deposito.setCuota1A(dep.getMonto());
                        deposito.setCuota1D(dep.getMonto_dep());
                        break;
                    case 2:
                        deposito.setCuota2A(dep.getMonto());
                        deposito.setCuota2D(dep.getMonto_dep());
                        break;
                    case 3:
                        deposito.setCuota3A(dep.getMonto());
                        deposito.setCuota3D(dep.getMonto_dep());
                        break;
                    case 4:
                        deposito.setCuota4A(dep.getMonto());
                        deposito.setCuota4D(dep.getMonto_dep());
                        break;
                    case 5:
                        deposito.setCuota5A(dep.getMonto());
                        deposito.setCuota5D(dep.getMonto_dep());
                        break;
                    case 6:
                        deposito.setCuota6A(dep.getMonto());
                        deposito.setCuota6D(dep.getMonto_dep());
                        break;
                    case 7:
                        deposito.setCuota7A(dep.getMonto());
                        deposito.setCuota7D(dep.getMonto_dep());
                        break;
                    case 8:
                        deposito.setCuota8A(dep.getMonto());
                        deposito.setCuota8D(dep.getMonto_dep());
                        break;
                    case 9:
                        deposito.setCuota9A(dep.getMonto());
                        deposito.setCuota9D(dep.getMonto_dep());
                        break;
                    case 10:
                        deposito.setCuota10A(dep.getMonto());
                        deposito.setCuota10D(dep.getMonto_dep());
                        break;
                }
                if ((i + 1) == depositos.size()) {
                    cont = 0;
                    if (estado == 1) {
                        cont++;
                    } else if (estado == 2) {
                        cont += (c1 == 1 && deposito.getCuota1D() <= 0) ? 1 : 0;
                        cont += (c2 == 1 && deposito.getCuota2D() <= 0) ? 1 : 0;
                        cont += (c3 == 1 && deposito.getCuota3D() <= 0) ? 1 : 0;
                        cont += (c4 == 1 && deposito.getCuota4D() <= 0) ? 1 : 0;
                        cont += (c5 == 1 && deposito.getCuota5D() <= 0) ? 1 : 0;
                        cont += (c6 == 1 && deposito.getCuota6D() <= 0) ? 1 : 0;
                        cont += (c7 == 1 && deposito.getCuota7D() <= 0) ? 1 : 0;
                        cont += (c8 == 1 && deposito.getCuota8D() <= 0) ? 1 : 0;
                        cont += (c9 == 1 && deposito.getCuota9D() <= 0) ? 1 : 0;
                        cont += (c10 == 1 && deposito.getCuota10D() <= 0) ? 1 : 0;
                    } else if (estado == 3) {
                        cont += (c1 == 1 && deposito.getCuota1D() > 0) ? 1 : 0;
                        cont += (c2 == 1 && deposito.getCuota2D() > 0) ? 1 : 0;
                        cont += (c3 == 1 && deposito.getCuota3D() > 0) ? 1 : 0;
                        cont += (c4 == 1 && deposito.getCuota4D() > 0) ? 1 : 0;
                        cont += (c5 == 1 && deposito.getCuota5D() > 0) ? 1 : 0;
                        cont += (c6 == 1 && deposito.getCuota6D() > 0) ? 1 : 0;
                        cont += (c7 == 1 && deposito.getCuota7D() > 0) ? 1 : 0;
                        cont += (c8 == 1 && deposito.getCuota8D() > 0) ? 1 : 0;
                        cont += (c9 == 1 && deposito.getCuota9D() > 0) ? 1 : 0;
                        cont += (c10 == 1 && deposito.getCuota10D() > 0) ? 1 : 0;
                    }
                    if (cont > 0) {
                        depositoAux.add(deposito);
                    }
                }
            }
            retorno.put("depositos", depositoAux);
            String opcionImp = request.getParameter("opcionImp");
                if (opcionImp != null) {
                    if (opcionImp.equals("_excel")) {
                        String archivo = Deposito.GenerarReporteExcel(depositoAux,personal, gestion);
                        response.sendRedirect("documentos/depositos/repExcel/" + archivo);
                    }
                }
                
            retorno.put("gestiones", this.sigaa.getSigaaGestiones());
            retorno.put("cursosHtml", sb);
            retorno.put("nroRegistros", depositoAux.size());
            retorno.put("nroRegistros", String.format("(%s) Registros", depositoAux.size()));
            return new ModelAndView(this.perfectView, retorno);
        } else {
            return new ModelAndView(this.ecxessTimeView, "url", url);
        }
    }
}
