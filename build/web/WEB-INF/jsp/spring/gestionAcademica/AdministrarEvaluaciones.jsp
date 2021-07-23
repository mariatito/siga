<%-- 
    Document   : AdministrarEvaluaciones
    Created on : 03-11-2009, 04:14:52 PM
    Author     : marco
--%>

<%@page contentType="text/html" pageEncoding="LATIN1"%>
<%@taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=LATIN1">
        <title>SIGAA - Santa Teresa</title>
        <link rel="stylesheet" href="imagenes/dhtmlmodal/windowfiles/dhtmlwindow.css" type="text/css" />
        <script type="text/javascript" src="imagenes/dhtmlmodal/windowfiles/dhtmlwindow.js"></script>
        <link rel="stylesheet" href="imagenes/dhtmlmodal/modalfiles/modal.css" type="text/css" />
        <script type="text/javascript" src="imagenes/dhtmlmodal/modalfiles/modal.js"></script>
        <script type="text/javascript" src="js/jquery-1.2.2.pack.js"></script>
        <script type="text/javascript" src="js/animatedcollapse.js"></script>    
        <script type="text/javascript" src="js/prototype.js"></script>
        <link rel="stylesheet" type="text/css" href="css/module.css" media="screen" />
        <script type="text/javascript">
            function onRowover(elem) {
                elem.className = 'colover';
            }
            function onRowout(elem) {
                elem.className = 'colout';
            }

            function openWindow(form, title, w, h) {
                var modalwin = dhtmlmodal.open('win' + form, 'div', form, title, 'width=' + w + 'px,height=' + h + 'px,left=100px,top=100px,resize=1,scrolling=1');
                modalwin.moveTo('middle', 'middle');
                return modalwin;
            }

            function setModificarFechaEva(id_evaluacion, id_gestion) {
                window.location = '<c:url value="/administrarEvaluaciones.do"/>?id_eva=' + id_evaluacion + '&id_gestion=' + id_gestion;
            }
            function selectGestion(id_gestion) {
                window.location = '<c:url value="/administrarEvaluaciones.do"/>?id_gestion=' + id_gestion;
            }
        </script>
    </head>
    <body>
        <div class="headercontent">
            <table id="tabs_menu" cellpadding="0" cellspacing="0" border="0" style="width:100%">
                <tr>
                    <td class="tab_init">&nbsp;</td>
                    <td style="width:90%">
                        <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                            <tr>
                                <td class="tab_normal" onclick="javascript:window.location = '<c:url value="/adminPlan.do"/>'">Plan de estudio</td>
                                <td class="tab_normal" onclick="javascript:window.location = '<c:url value="/asignarDocentes.do"/>'">Asignaci&oacute;n curso-docente</td>
                                <td class="tab_normal" onclick="javascript:window.location = '<c:url value="/administrarCurso.do"/>'">Administrar cursos</td>
                                <td class="tab_current">Fechas de evaluaciones general</td>
                                <td class="tab_normal" onclick="javascript:window.location = '<c:url value="/boletinesTrimestrales.do"/>'">Boletines trimestrales</td>
                            </tr>
                        </table>
                    </td>
                    <td class="tab_fin">&nbsp;</td>
                </tr>
            </table>
            <div class="titlepage">FECHAS DE EVALUACIONES GENERAL</div>
        </div>
        <div class="maincontent">
            <c:if test="${!empty gestion}">
                <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                    <tr>
                        <td class="tableHeader">Inscripciones por gesti&oacute;n</td>
                    </tr>
                    <tr>
                        <td class="gridContent">
                            <table width="100%" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF">
                                <tr>
                                    <td class="gridHeaders" width="5%">Nro.</td>
                                    <td class="gridHeaders" width="10%">Gesti&oacute;n</td>
                                    <td class="gridHeaders" width="45%">Lema</td>
                                    <td class="gridHeaders" width="30%">Directora</td>
                                    <td class="gridHeaders" width="10%">Estado</td>
                                </tr>
                                <c:forEach varStatus="j" var="item" items="${gestion.gestiones}">
                                    <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)" onclick="selectGestion('${item.id_gestion}')">
                                        <td align="center" style="cursor:pointer" valign="top" class="gridData">${j.count}</td>
                                        <td align="center" style="cursor:pointer" valign="top" class="gridData">${item.id_gestion}</td>
                                        <td style="cursor:pointer" valign="top" class="gridData">${item.lema}</td>
                                        <td style="cursor:pointer" valign="top" class="gridData">${item.titulo} ${item.nombre}</td>
                                        <td align="center" style="cursor:pointer" valign="top" class="gridData"><c:if test="${item.estado=='true'}"><img src="imagenes/iconos_sigaa/activo_si.png" border="0" title="Estado activo"></c:if><c:if test="${item.estado=='false'}"><img src="imagenes/iconos_sigaa/activo_no.png" border="0" title="Estado inactivo"></c:if></td>
                                        </tr>
                                </c:forEach>
                            </table>

                        </td>
                    </tr>
                </table>
            </c:if>
            <c:if test="${!empty fecha_regnota}">
                <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                    <tr>
                        <td class="tableHeader">Definici&oacute;n de fechas l&iacute;mite de ingreso de calificaciones (TRIMESTRAL)</td>
                    </tr>
                    <tr>
                        <td class="gridContent">
                            <table width="100%" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF">
                                <div>
                                    Seleccione un registro para acualizar la evaluaci&oacute;n.
                                </div>
                                <tr>
                                    <td class="gridHeaders" width="5%">Nro.</td>
                                    <td class="gridHeaders" width="25%">Evaluaci&oacute;n</td>
                                    <td class="gridHeaders" width="35%">Fecha l&iacute;mite de ingreso de notas</td>
                                    <td class="gridHeaders" width="35%">Cursos</td>
                                </tr>
                                <% int c1 = 0;%>
                                <c:forEach varStatus="j" var="item" items="${fecha_regnota}">
                                    <c:if test="${item.periodos==3}">
                                        <tr style="cursor: pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)" onclick="setModificarFechaEva('${item.id_eva}', '${item.id_gestion}')">
                                            <td align="center" style="cursor:pointer" valign="top" class="gridData"><% out.print(++c1);%></td>
                                            <td style="cursor:pointer" valign="top" class="gridData">${item.descripcion}
                                            </td>
                                            <td style="cursor:pointer" valign="top" class="gridData">&nbsp;&nbsp;&nbsp;${item.sfec_limite}</td>
                                            <td style="cursor:pointer" valign="top" class="gridData">
                                                <c:forEach varStatus="ij" var="it" items="${item.periodocursos}">
                                                    <c:if test="${ij.index==0}">
                                                        ${it.id_curso_alias} 
                                                    </c:if>
                                                    <c:if test="${ij.index!=0}">
                                                        , ${it.id_curso_alias} 
                                                    </c:if>
                                                </c:forEach> 
                                            </td> 
                                        </tr>
                                    </c:if>                                    
                                </c:forEach>
                            </table>

                        </td>
                    </tr>
                </table>
                <br/>
                <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                    <tr>
                        <td class="tableHeader">Definici&oacute;n de fechas l&iacute;mite de ingreso de calificaciones (BIMESTRAL)</td>
                    </tr>
                    <tr>
                        <td class="gridContent">
                            <table width="100%" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF">
                                <div>
                                    Seleccione un registro para acualizar la evaluaci&oacute;n. 
                                </div>
                                <tr>
                                    <td class="gridHeaders" width="5%">Nro.</td>
                                    <td class="gridHeaders" width="25%">Evaluaci&oacute;n</td>
                                    <td class="gridHeaders" width="35%">Fecha l&iacute;mite de ingreso de notas</td>
                                    <td class="gridHeaders" width="35%">Cursos</td>
                                </tr>
                                <% int c2 = 0;%>
                                <c:forEach varStatus="g" var="item" items="${fecha_regnota}">
                                    <c:if test="${item.periodos==2}">
                                        <tr style="cursor: pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)" onclick="setModificarFechaEva('${item.id_eva}', '${item.id_gestion}')">
                                            <td align="center" style="cursor:pointer" valign="top" class="gridData"><% out.print(++c2);%></td> 
                                            <td style="cursor:pointer" valign="top" class="gridData">${item.descripcion}
                                            </td>
                                            <td style="cursor:pointer" valign="top" class="gridData">&nbsp;&nbsp;&nbsp;${item.sfec_limite}</td>
                                            <td style="cursor:pointer" valign="top" class="gridData">
                                                <c:forEach varStatus="ij" var="it" items="${item.periodocursos}">
                                                    <c:if test="${ij.index==0}">
                                                        ${it.id_curso_alias} 
                                                    </c:if>
                                                    <c:if test="${ij.index!=0}">
                                                        , ${it.id_curso_alias} 
                                                    </c:if>
                                                </c:forEach>
                                            </td>
                                        </tr>
                                    </c:if>
                                </c:forEach>
                            </table>

                        </td>
                    </tr>
                </table>
            </c:if>
            <br>
            <c:if test="${!empty eva}">
                <form action="<c:url value="/administrarEvaluaciones.do"/>" method="post" name="frmRegistro" >
                    <table class="gridContent" width="80%" cellpadding="0" cellspacing="0" border="0" align="center">
                        <tr>
                            <td class="tableHeader" colspan="5">Caracteristicas de la evaluacion</td>
                        </tr>
                        <tr>
                            <td colspan="5" style="color:red">Nota: Tenga muy en cuenta que la modificaci&oacute;n de la fecha de ingreso de calificaciones
                                es para todos los cursos, incluye evaluaciones, conductas y faltas<br></td>
                        </tr>
                        <tr class="gridData">
                            <td align="center" colspan="5"><strong>ACTUALIZACI&Oacute; DE FECHA L&Iacute;MITE DE INGRESON DE NOTAS</strong></td>
                        </tr>
                        <tr>
                            <td colspan="5"><img src="imagenes/pixel_trans.gif" height="10"/></td>
                        </tr>
                        <tr style="cursor: pointer" class="gridData" valign="top">
                            <td></td>
                            <td>FECHA L&Iacute;MITE DE INGRESO NOTAS</td>
                            <td>:</td>
                            <td colspan="2">&nbsp;&nbsp;
                                <select name="dia" class="text-field" style="width:45px">
                                    <c:forEach begin="1" end="31" varStatus="i">
                                        <option <c:if test="${i.count==eva.fec_limite.date}"> Selected="selected" </c:if>>${i.count}
                                        </c:forEach>
                                </select>
                                <select name="mes" class="text-field" style="width:110px">
                                    <c:forEach var="ite" items="${meses}">
                                        <option value="${ite.id}" <c:if test="${ite.id==(eva.fec_limite.month+1)}"> Selected="selected" </c:if>>${ite.valor}
                                        </c:forEach>
                                </select>
                                <select name="anio" class="text-field" style="width:60px">
                                    <c:forEach begin="2008" end="2030" varStatus="j">
                                        <option <c:if test="${j.index==(eva.fec_limite.year+1900)}"> Selected="selected" </c:if>>${j.index}
                                        </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="5"><img src="imagenes/pixel_trans.gif" height="10"/></td>
                        </tr>
                        <tr>
                            <td colspan="5" align="center">
                                <input name="save" type="hidden" value="_save"/>
                                <input name="id_eva" type="hidden" value="${eva.id_eva}"/>
                                <input name="id_gestion" type="hidden" value="${id_gestion}"/>
                                <input name="guardar" class="button-submit" type="submit" value="Guardar cambios..."/>
                            </td>
                        </tr>
                    </table>
                </form>
            </c:if>
        </div>
    </body>
</html>