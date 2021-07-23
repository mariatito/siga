<%-- 
    @ Company    : M&M
    @ Document   : TutorCurso
    @ Created on : 14-11-2009, 12:29:23 PM
    @ Author     : Marco Antonio Quenta Velasco
    @ La Paz - Bolivia
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
        <link rel="stylesheet" type="text/css" href="css/module.css" media="screen" />
        <script type="text/javascript" src="js/prototype.js"></script>
        <script type="text/javascript">

            function onRowover(elem) {
                elem.className = 'colover';
            }
            function onRowout(elem) {
                elem.className = 'colout';
            }
            function openWindow(form, title, w, h) {
                docentesWin = dhtmlmodal.open('win' + form, 'div', form, title, 'width=' + w + 'px,height=' + h + 'px,left=100px,top=100px,resize=1,scrolling=1');
                docentesWin.moveTo('middle', 'middle');
                return docentesWin;
            }
            function selectCurso(id_curso, curso) {
                window.location = '<c:url value="/tutorCurso.do"/>?id_curso=' + id_curso + '&curso=' + curso;
            }
            function saveConductas() {
                document.formConductas.submit()
            }
            var printWin = null;
            function openPrintableDocument() {
                printWin = openWindow('documentoPdF', 'Imprimir...', '650', '340');
                document.getElementById('documentview').src = 'documentos/docentes/${pdf}';
                printWin.moveTo('middle', 'middle');
            }
        </script>
    </head>
    <body>
        <div class="headercontent">
            <table id="tabs_menu" cellpadding="0" cellspacing="0" border="0" style="width:100%">
                <tr>
                    <td class="tab_init">&nbsp;</td>
                    <td style="width:50%">
                        <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                            <td class="tab_normal" onclick="javascript:window.location = '<c:url value="/registrarCalificaciones.do"/>'">Registrar calificaciones</td>
                            <td class="tab_normal" onclick="javascript:window.location = '<c:url value="/notasCualitativas.do"/>'">Registrar calificaciones cualitativas</td>
                            <td class="tab_current">Tutor curso</td>
                        </table>
                    </td>
                    <td class="tab_fin">&nbsp;</td>
                </tr>
            </table>
            <div class="titlepage">RESPONSABLE DEL CURSO ${curso.curso} de ${curso.ciclo}</div>
        </div>
        <div class="maincontent">
            <c:if test="${!empty cursos}">
                <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                    <tr>
                        <td class="tableHeader">Mis cursos</td>
                    </tr>
                    <tr>
                        <td class="gridContent">
                            <div id="resultlayer1">
                                <table width="100%" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF">
                                    <tr class="gridHeaders">
                                        <td width="10%" >NRO.</td>
                                        <td width="25%" >CURSO</td>
                                        <td width="65%" ></td>
                                    </tr>
                                    <c:if test="${empty cursos}">
                                        <tr>
                                            <td class="gridData" colspan="3" style="cursor:pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">No se han encontrado elementos.</td>
                                        </tr>
                                    </c:if>
                                    <% int indexc = 1;%>
                                    <c:forEach varStatus="i" var="item" items="${cursos}">
                                        <tr style="cursor:pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)" onclick="selectCurso('${item.id_curso}', '${item.curso} de ${item.ciclo}')">
                                            <td valign="top" align="center"><% out.print(indexc++);%></td>
                                            <td valign="top" colspan="2">&nbsp;&nbsp;<img src="imagenes/iconos_sigaa/indicador.png">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${item.curso} de ${item.ciclo}
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </div>
                        </td>
                    </tr>
                </table>
            </c:if>
            <c:if test="${!empty curso && curso.id_curso!='K'}">
                <!--table cellpadding="0" cellspacing="0" border="0" style="width: 100%; cursor:pointer">
                    <tr>
                        <td class="gridContent">
                            <table width="100%" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF">
                                <tr>
                                    <td class="gridHeaders" style="width:20%">Trimestre</td>
                                    <td class="gridHeaders" style="width:70%" title="Intervalo de fechas para realizar el registro de conductas">Intervalo de fechas para realizar el regisro de conductas</td>
                                    <td class="gridHeaders" style="width:10%">Estado</td>
                                </tr>
                                <c:if test="${empty curso.estudiantes}">
                                    <tr>
                                        <td class="gridData" colspan="3">No se han encontrado elementos.</td>
                                    </tr>
                                </c:if>
                                <c:forEach varStatus="i" var="item" items="${curso.fecRegNotas}">
                                    <c:if test="${i.count!=4}">
                                        <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                            <td class="gridData" style="color:teal"><strong>${i.count}<c:if test="${i.count==1 || i.count==3}">er.</c:if><c:if test="${i.count==2}">do.</c:if> TRIMESTRE</strong></td>
                                            <td class="gridData" style="color:teal">&nbsp;&nbsp;&nbsp;Desde: <font style="color:orange"><strong> ${item.sfec_inicio}</strong></font>&nbsp;&nbsp;&nbsp;Hasta: <font style="color:orange"><strong> ${item.sfec_limite}</strong></font></td>
                                            <td class="gridData">
                                                <font style="color: green"><c:if test="${item.estado=='activo'}">ACTIVO</c:if></font>
                                                <font style="color: red"><c:if test="${item.estado==null}">INACTIVO</c:if></font>
                                                </td>
                                            </tr>
                                    </c:if>
                                </c:forEach>
                            </table>
                        </td>
                    </tr>
                </table>
                <br-->
                <table cellpadding="0" cellspacing="0" border="0" style="width: 100%; cursor:pointer">
                    <tr>
                        <td class="tableHeader">Registro de conductas a estudiantes del CURSO : <strong> ${curso.curso} de ${curso.ciclo}</strong></td>
                    </tr>
                    <tr>
                        <td class="gridContent">
                            <button class="button-normal"  onclick="javascript:saveConductas()"><img src="imagenes/iconos_sigaa/guardar.png" width="11px">&nbsp;&nbsp;Guardar</button>&nbsp;
                            <button class="button-normal"  onclick="javascript:openPrintableDocument()" ><img width="10px" src="imagenes/iconos_sigaa/print.gif">&nbsp;&nbsp;Imprimir</button>&nbsp;

                            <form action="<c:url value="/tutorCurso.do"/>" method="post" name="formConductas">
                                <table width="100%" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF">
                                    <tr>
                                        <td class="gridHeaders" width="5%">Nro.</td>
                                        <td class="gridHeaders" width="41%">Apellidos y nombres</td>
                                        <% int indice = 0;%>
                                        <c:forEach varStatus="i" var="itemh" items="${curso.fecRegNotas}">
                                            <c:if test="${itemh.periodos==2}">
                                                <% if (++indice <= 4) {%>
                                                <c:if test="${itemh.estado!='activo'}">
                                                    <td class="gridUno" align="center" style="cursor:pointer" title="${itemh.sfec_inicio} al ${itemh.sfec_limite}">${itemh.descripcion}</td>
                                                </c:if>
                                                <c:if test="${itemh.estado=='activo'}">
                                                    <td class="gridUno" align="center" style="cursor:pointer;background: rgb(156, 247, 156)" title="${itemh.sfec_inicio} al ${itemh.sfec_limite}">${itemh.descripcion}</td>
                                                </c:if>
                                                <% } %>
                                            </c:if>
                                            <c:if test="${itemh.periodos==3}">
                                                <% if (++indice <= 3) {%>
                                                <td class="gridUno" align="center" style="cursor:pointer" title="${itemh.sfec_inicio} al ${itemh.sfec_limite}">${itemh.descripcion}</td>
                                                <% }%>
                                            </c:if>
                                        </c:forEach>
                                    </tr>
                                    <c:if test="${empty curso.estudiantes}">
                                        <tr>
                                            <td class="gridData" colspan="8">No se han encontrado elementos.</td>
                                        </tr>
                                    </c:if>
                                    <c:forEach varStatus="i" var="item" items="${curso.estudiantes}">
                                        <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                            <td class="gridData" align="center"><c:out value="${i.count}"/> </td>
                                            <td class="gridData"><c:out value="${item.paterno} ${item.materno} ${item.nombres}" /></td>
                                            <c:forEach varStatus="j" var="cond" items="${item.conductas}">
                                                    <td class="gridData" align="center">
                                                        <select name="id_tipo_conducta-${cond.id_conducta}-${item.id_estudiante}" class="text-field" style="width:120px" <c:if test="${cond.status!='activo'}">  onfocus="this.defaultIndex=this.selectedIndex;" onchange="this.selectedIndex=this.defaultIndex;" </c:if>>
                                                            <c:forEach var="it" items="${tipos_conductas}">
                                                                <c:if test="${it.abreviatura=='A'}">
                                                                    <option value="${it.id}" title="${it.valor}" <c:if test="${it.id==cond.id_tipo_conducta}"> selected </c:if>> ${it.valor}
                                                                    </c:if>
                                                                </c:forEach>
                                                        </select>
                                                    </td>
                                            </c:forEach>
                                        </tr>
                                    </c:forEach>
                                </table>
                                <input type="hidden" name="update" value="_update">
                                <input type="hidden" name="id_curso" value="${curso.id_curso}">
                            </form>
                        </td>
                    </tr>
                </table>
            </c:if>
            <c:if test="${curso.id_curso=='K'}">
                <table cellpadding="0" cellspacing="0" border="0" style="width: 100%; cursor:pointer">
                    <tr>
                        <td class="tableHeader">Ingreso de conductas a estudiantes del CURSO : <strong> ${curso.curso} de ${curso.ciclo}</strong></td>
                    </tr>
                    <tr>
                        <td class="gridContent">
                            <div>
                                <button class="button-normal"  onclick="javascript:saveConductas()"><img width="11px" src="imagenes/iconos_sigaa/guardar.png"> Guardar...</button>&nbsp;
                            </div>
                            <div>
                                <form action="<c:url value="/tutorCurso.do"/>" method="post" name="formConductas">
                                    <table width="100%" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF">
                                        <tr>
                                            <td class="gridHeaders" width="5%">Nro.</td>
                                            <td class="gridHeaders" width="41%">Apellidos y nombres</td>
                                            <td class="gridUno" width="27%" align="center" style="cursor:pointer">Diagn&oacute;stico inicial</td>
                                            <td class="gridDos" width="27%" align="center" style="cursor:pointer">Diagn&oacute;stico final</td>
                                        </tr>
                                        <c:if test="${empty curso.estudiantes}">
                                            <tr>
                                                <td class="gridData" colspan="8">No se han encontrado elementos.</td>
                                            </tr>
                                        </c:if>
                                        <c:forEach varStatus="i" var="item" items="${curso.estudiantes}">
                                            <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                                <td class="gridData" align="center"><c:out value="${i.count}"/> </td>
                                                <td class="gridData"><c:out value="${item.paterno} ${item.materno} ${item.nombres}" /></td>
                                                <c:forEach varStatus="j" var="cond" items="${item.conductas}">
                                                    <td class="gridData" align="center">
                                                        <textarea name="diagnostico-${cond.id_conducta}-${item.id_estudiante}" class="text-field"  style="width:100%">${cond.diagnostico}</textarea>
                                                    </td>
                                                </c:forEach>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                    <input type="hidden" name="update" value="_update">
                                    <input type="hidden" name="id_curso" value="${curso.id_curso}">
                                </form>
                            </div>
                        </td>
                    </tr>
                </table>
            </c:if>
        </div>
        <div id="documentoPdF" style="display:none">
            <div style="width:100%;height:99%">
                <iframe id="documentview" frameborder="0" style="width:100%;height:100%"></iframe>
            </div>
        </div>
    </body>
</html>