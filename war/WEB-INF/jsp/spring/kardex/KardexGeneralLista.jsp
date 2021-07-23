<%-- 
    Document   : KardexGeneralLista
    Created on : 08-05-2021, 12:06:18 AM
    Author     : Mi PC, Marco Antonio Quenta Velasco
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${!empty lista}">
    <c:if test="${!empty kardexGeneral}">
        <div style="text-align: right;font-weight: bold; padding: 5px">(${kardexGeneral.size()}) Registros</div>
        <table class="table ui-widget ui-widget-content" cellspacing="0" cellpadding="0">
            <thead>
                <tr class="ui-widget-header ">
                    <th>Nro</th>
                    <th>Paterno</th>
                    <th>Materno</th>
                    <th>Nombres</th>
                    <th>Curso</th>
                    <th>Incidencias</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach varStatus="j" var="item" items="${kardexGeneral}">
                    <tr style="cursor: pointer" onclick="selectEstudiante('${item.id_estudiante}', '${item.id_curso}', '${item.id_gestion}','${item.paterno} ${item.materno} ${item.nombres}','${item.curso}')">
                        <td style="text-align:center" >${j.count}</td>
                        <td>${item.paterno}</td>
                        <td>${item.materno}</td>
                        <td>${item.nombres}</td>
                        <td>${item.curso}</td>
                        <td style="text-align: center">${item.no_notificado}</td>
                    </tr>                        
                </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty kardexGeneral}">
        <div style="text-align: center;border: 1px solid #ccc;padding: 5px">No se encontaron registros...</div>
    </c:if>
</c:if>

<c:if test="${!empty detalle}">
    <c:if test="${!empty kardex}">
        <div style="text-align: right;font-weight: bold; padding: 5px">(${kardex.size()}) Registros</div>
        <table class="table ui-widget ui-widget-content" cellspacing="0" cellpadding="0"  style="font-size: 11px">
            <thead>
                <tr class="ui-widget-header ">
                    <th>Nro</th>
                    <th>Fecha</th>
                    <th>Docente</th>
                    <th>Kardex</th>
                    <th>SU</th>
                    <th>Seguimiento</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach varStatus="j" var="item" items="${kardex}">
                    <tr>
                        <td style="text-align:center" >${j.count}</td>
                        <td>${item.fecreg}</td>
                        <td><p>${item.docente}</p> <p style="font-size: 10px">${item.materia}</p></td>
                        <td>
                            <ol style="font-size: 11px">
                                <c:if test="${item.fsl==1}"><li>Faltas Sin Licencia</li></c:if>
                                <c:if test="${item.a==1}"><li>No enciende la cámara</li></c:if>
                                <c:if test="${item.tnr==1}"><li>Tareas No Realizadas</li></c:if>
                                <c:if test="${item.aa==1}"><li>Abandono Arbitrario</li></c:if>
                                <c:if test="${item.otrasfaltas!=''}"><li>Otras Faltas: ${item.otrasfaltas}</li></c:if>
                                <c:if test="${item.aspectospositivos!=''}"><li>Aspectos Positivos: ${item.aspectospositivos}</li></c:if>
                                <c:if test="${item.observaciones!=''}"><li>Observaciones: ${item.observaciones}</i> </c:if>
                                </ol>
                            </td>
                            <td style="text-align: center"><input id="su-${item.idkardexdetalle}" type="checkbox"/></td>
                            <td style="text-align: center"><textarea id="seguimiento-${item.idkardexdetalle}" placeholder="Seguimiento"></textarea></td>
                            <td style="text-align: center">
                                <button onclick="Actualizar('${item.idkardexdetalle}','${item.id_estudiante}', '${item.id_curso}', '${item.id_gestion}')" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-icon-primary" role="button" aria-disabled="false">
                                    <span class="ui-button-icon-primary ui-icon ui-icon-disk"></span>
                                    <span class="ui-button-text">Guardar</span>
                                </button>
                            </td>
                        </tr>  
                </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty kardex}">
        <div style="text-align: center;border: 1px solid #ccc;padding: 5px">No se encontaron registros...</div>
    </c:if>
</c:if>