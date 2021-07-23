<%-- 
    Document   : MejoresEstudiantesDetalleCalificaciones
    Created on : 08-jul-2010, 21:51:30
    Author     : MARCO ANTONIO QUENTA VELASCO
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
        <link href='http://localhost:8084/sigaa/imagenes/iconos_sigaa/sigaa.ico' rel='icon'/>
        <link href='http://localhost:8084/sigaa/imagenes/iconos_sigaa/sigaa.ico' rel='shortcut icon'/>
        <link rel="shortcut icon" href="http://localhost:8084/sigaa/imagenes/iconos_sigaa/sigaa.ico" type="image/x-icon" />
        <link rel="stylesheet" href="imagenes/dhtmlmodal/windowfiles/dhtmlwindow.css" type="text/css" />
        <script type="text/javascript" src="imagenes/dhtmlmodal/windowfiles/dhtmlwindow.js"></script>
        <link rel="stylesheet" href="imagenes/dhtmlmodal/modalfiles/modal.css" type="text/css" />
        <script type="text/javascript" src="imagenes/dhtmlmodal/modalfiles/modal.js"></script>
        <script type="text/javascript" src="js/jquery-1.2.2.pack.js"></script>
        <script type="text/javascript" src="js/animatedcollapse.js"></script>
        <script type="text/javascript" src="js/prototype.js"></script>
        <link rel="stylesheet" type="text/css" href="css/module.css" media="screen" />
        <script type="text/javascript">
            function onRowover(elem) { elem.className='colover'; }
            function onRowout(elem) { elem.className='colout'; }
            function openWindow(form,title,w,h){
                var addFormwin=dhtmlmodal.open('win'+form, 'div', form, title, 'width='+w+'px,height='+h+'px,left=100px,top=100px,resize=1,scrolling=1');
                addFormwin.moveTo('middle', 'middle');
                return addFormwin;
            }
        </script>
    </head>
    <body>
        <div class="headercontent">
            <table style="width:100%" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="menuHead"><h2>Boletin Trimestral</h2></td>
                </tr>
            </table>
        </div>
        <div class="maincontent">
            <span>
                <table border="0" width="100%">
                    <tr>
                        <td>Alumno/a : <strong>${estudiante.paterno} ${estudiante.materno} ${estudiante.nombres}</strong>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;C&oacute;digo : <strong>${estudiante.codigo}</strong>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Curso : <strong>${curso.curso} de ${curso.ciclo}</strong>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Gestion : <strong>${gestion.id_gestion}</strong></td>
                    </tr>
                </table>
                <table border="0" cellpadding="0" cellspacing="0" width="100%" class="gridDatS">
                    <c:if test="${curso.id_curso=='K'}">
                        <c:forEach varStatus="j" var="con" items="${curso.conductas}">
                            <c:if test="${j.count==1}">
                                <tr style="color:navy">
                                    <td valign="top"><strong>Diagn&oacute;stico inicial : </strong></td>
                                    <td align="justify" colspan="9">${con.diagnostico}</td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </c:if>
                    <tr>
                        <td width="25%" class="gridCuatron">ASIGNATURAS</td>
                        <td width="25%" colspan="3">
                            <table border="0" width="100%" style="cursor:pointer" cellpadding="0" cellspacing="0">
                                <tr>
                                    <td align="center" colspan="3" class="gridUno"><font style="font-size:11px">1er. TRIMESTRE</font></td>
                                </tr>
                                <c:if test="${curso.id_curso!='K'}">
                                    <tr>
                                        <td align="center" width="5%" title="Promedio Conociemiento" class="gridUno"><font style="font-size:10px">PC</font></td>
                                        <td align="center" width="5%" title="Puntaje DPS" class="gridUno"><font style="font-size:10px">DPS</font></td>
                                        <td align="center" width="5%" title="Puntaje Trimestral" class="gridUno"><font style="font-size:10px">PT</font></td>
                                    </tr>
                                </c:if>
                            </table>
                        </td>
                        <td width="25%" colspan="3">
                            <table border="0" width="100%" style="cursor:pointer" cellpadding="0" cellspacing="0">
                                <tr>
                                    <td align="center" colspan="3" class="gridDos"><font style="font-size:11px">2do. TRIMESTRE</font></td>
                                </tr>
                                <c:if test="${curso.id_curso!='K'}">
                                    <tr>
                                        <td align="center" width="5%" title="Promedio Conociemiento" class="gridDos"><font style="font-size:10px">PC</font></td>
                                        <td align="center" width="5%" title="Puntaje DPS" class="gridDos"><font style="font-size:10px">DPS</font></td>
                                        <td align="center" width="5%" title="Puntaje Trimestral" class="gridDos"><font style="font-size:10px">PT</font></td>
                                    </tr>
                                </c:if>
                            </table>
                        </td>
                        <td width="25%" colspan="3">
                            <table border="0" width="100%" style="cursor:pointer" cellpadding="0" cellspacing="0">
                                <tr>
                                    <td align="center" colspan="3" class="gridTres"><font style="font-size:11px">3er. TRIMESTRE</font></td>
                                </tr>
                                <c:if test="${curso.id_curso!='K'}">
                                    <tr>
                                        <td align="center" width="5%" title="Promedio Conociemiento" class="gridTres"><font style="font-size:10px">PC</font></td>
                                        <td align="center" width="5%" title="Puntaje DPS" class="gridTres"><font style="font-size:10px">DPS</font></td>
                                        <td align="center" width="5%" title="Puntaje Trimestral" class="gridTres"><font style="font-size:10px">PT</font></td>
                                    </tr>
                                </c:if>
                            </table>
                        </td>
                        <c:if test="${curso.id_curso!='K'}">
                            <td width="25%" colspan="3">
                                <table border="0" width="100%" style="height:33px;cursor:pointer" cellpadding="0" cellspacing="0">
                                    <tr>
                                        <td align="center" width="5%" title="Promedio Anual" class="gridCuatron"><font style="font-size:10px">PA</font></td>
                                        <td align="center" width="5%" title="Promedio Reforzamiento" class="gridCuatron"><font style="font-size:10px">PR</font></td>
                                        <td align="center" width="5%" title="Promedio Final" class="gridCuatron"><font style="font-size:10px">PF</font></td>
                                    </tr>
                                </table>
                            </td>
                        </c:if>
                    </tr>
                    <c:forEach varStatus="i" var="item" items="${curso.materias}">
                        <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)" style="cursor: pointer">
                            <td bgcolor="#FFDFEF" style="color:navy"><strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${item.materia}</strong></td>
                            <c:forEach varStatus="j" var="eva" items="${item.evaluaciones}">
                                <c:if test="${j.count==1}">
                                    <c:if test="${curso.id_curso!='K'}">
                                        <td align="center" width="5%" class="gridData" id="${eva.id_evaluacion}-${estudiante.id_estudiante}">${eva.nota}</td>
                                        <td align="center" width="5%" class="gridData">${eva.dps}</td>
                                        <td align="center" width="5%" class="gridUnon"><strong><c:if test="${(eva.nota+eva.dps)>=36}">${eva.nota+eva.dps}</c:if><c:if test="${(eva.nota+eva.dps)<=35}"><font style="color:red">${eva.nota+eva.dps}</font></c:if></strong></td>
                                    </c:if>
                                    <c:if test="${curso.id_curso=='K'}">
                                        <td align="justify" width="15%" class="gridData" colspan="3">${eva.cualitativa}</td>
                                    </c:if>
                                </c:if>
                                <c:if test="${j.count==2}">
                                    <c:if test="${curso.id_curso!='K'}">
                                        <td align="center" width="5%" class="gridData">${eva.nota}</td>
                                        <td align="center" width="5%" class="gridData">${eva.dps}</td>
                                        <td align="center" width="5%" class="gridDosn"><strong><c:if test="${(eva.nota+eva.dps)>=36}">${eva.nota+eva.dps}</c:if><c:if test="${(eva.nota+eva.dps)<=35}"><font style="color:red">${eva.nota+eva.dps}</font></c:if></strong></td>
                                    </c:if>
                                    <c:if test="${curso.id_curso=='K'}">
                                        <td align="justify" width="15%" class="gridData" colspan="3">${eva.cualitativa}</td>
                                    </c:if>
                                </c:if>
                                <c:if test="${j.count==3}">
                                    <c:if test="${curso.id_curso!='K'}">
                                        <% int cont = 0;%>
                                        <td align="center" width="5%" class="gridData">${eva.nota}</td>
                                        <td align="center" width="5%" class="gridData">${eva.dps}</td>
                                        <td align="center" width="5%" class="gridTresn"><strong><c:if test="${(eva.nota+eva.dps)>=36}">${eva.nota+eva.dps}</c:if><c:if test="${(eva.nota+eva.dps)<=35}"><font style="color:red">${eva.nota+eva.dps}</font></c:if></strong></td>
                                        <td align="left" width="5%">&nbsp;&nbsp;&nbsp;&nbsp;<strong><c:if test="${eva.pa>=36}">${eva.pa}</c:if><c:if test="${eva.pa<=35}"><font style="color:red">${eva.pa}</font></c:if></strong></td>
                                        <c:forEach varStatus="m" var="ev" items="${item.evaluaciones}">
                                            <c:if test="${m.count==4}">
                                                <% cont = 1;%>
                                                <td align="center" width="5%"><c:if test="${ev.nota!=0}">${ev.nota}</c:if></td>
                                                <td align="center" width="5%"><strong><c:if test="${ev.pf>=36}">${ev.pf}</c:if></strong></td>
                                            </c:if>
                                        </c:forEach>
                                        <% if (cont == 0) {%>
                                        <td width="5%"></td>
                                        <td width="5%"></td>
                                        <% }%>
                                    </c:if>
                                    <c:if test="${curso.id_curso=='K'}">
                                        <td align="justify" width="25%" class="gridData" colspan="3">${eva.cualitativa}</td>
                                    </c:if>
                                </c:if>
                            </c:forEach>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td class="gridCuatron" align="center"><font style="font-size:10px">INFORMACION DE ASISTENCIA DEL ALUMNO/A</font></td>
                        <td colspan="3">
                            <table border="0" width="100%" style="height:40px; cursor:pointer" cellpadding="0" cellspacing="0">
                                <tr>
                                    <td align="center" colspan="3" class="gridUno"><font style="font-size:11px">1er. TRIMESTRE</font></td>
                                </tr>
                                <tr>
                                    <td align="center" width="5%" title="Dias trabajados" class="gridUno"><font style="font-size:10px">D.T.</font></td>
                                    <td align="center" width="5%" title="Retrasos" class="gridUno"><font style="font-size:10px">Ret.</font></td>
                                    <td align="center" width="5%" title="Faltas con licencia y sin licencias" class="gridUno"><font style="font-size:10px">Aus.</font></td>
                                </tr>
                                <tr>
                                    <c:forEach varStatus="i" var="fal" items="${curso.faltas}">
                                        <c:if test="${i.count==1}">
                                            <td class="gridData" align="center"><strong>${gestion.dt1}</strong></td>
                                            <td class="gridData" align="center"><strong>${fal.retraso}</strong></td>
                                            <td class="gridData" align="center"><strong>${fal.fcl+fal.fsl}</strong></td>
                                        </c:if>
                                    </c:forEach>
                                </tr>
                            </table>
                        </td>
                        <td colspan="3">
                            <table border="0" width="100%" style="height:40px; cursor:pointer" cellpadding="0" cellspacing="0">
                                <tr>
                                    <td align="center" colspan="3" class="gridDos"><font style="font-size:11px">2do. TRIMESTRE</font></td>
                                </tr>
                                <tr>
                                    <td align="center" width="5%" title="Dias trabajados" class="gridDos"><font style="font-size:10px">D.T.</font></td>
                                    <td align="center" width="5%" title="Retrasos" class="gridDos"><font style="font-size:10px">Ret.</font></td>
                                    <td align="center" width="5%" title="Faltas con licencia y sin licencias" class="gridDos"><font style="font-size:10px">Aus.</font></td>
                                </tr>
                                <tr>
                                    <c:forEach varStatus="i" var="fal" items="${curso.faltas}">
                                        <c:if test="${i.count==2}">
                                            <td class="gridData" align="center"><strong>${gestion.dt2}</strong></td>
                                            <td class="gridData" align="center"><strong>${fal.retraso}</strong></td>
                                            <td class="gridData" align="center"><strong>${fal.fcl+fal.fsl}</strong></td>
                                        </c:if>
                                    </c:forEach>
                                </tr>
                            </table>
                        </td>
                        <td colspan="3">
                            <table border="0" width="100%" style="height:40px; cursor:pointer" cellpadding="0" cellspacing="0">
                                <tr>
                                    <td align="center" colspan="3" class="gridTres"><font style="font-size:11px">3er. TRIMESTRE</font></td>
                                </tr>
                                <tr>
                                    <td align="center" width="5%" title="Dias trabajados" class="gridTres"><font style="font-size:10px">D.T.</font></td>
                                    <td align="center" width="5%" title="Retrasos" class="gridTres"><font style="font-size:10px">Ret.</font></td>
                                    <td align="center" width="5%" title="Faltas con licencia y sin licencias" class="gridTres"><font style="font-size:10px">Aus.</font></td>
                                </tr>
                                <tr>
                                    <c:forEach varStatus="i" var="fal" items="${curso.faltas}">
                                        <c:if test="${i.count==3}">
                                            <td class="gridData" align="center"><strong>${gestion.dt3}</strong></td>
                                            <td class="gridData" align="center"><strong>${fal.retraso}</strong></td>
                                            <td class="gridData" align="center"><strong>${fal.fcl+fal.fsl}</strong></td>
                                        </c:if>
                                    </c:forEach>
                                </tr>
                            </table>
                        </td>
                        <c:if test="${curso.id_curso!='K'}">
                            <td colspan="3">
                                <table border="0" width="100%" style="height:40px; cursor:pointer" cellpadding="0" cellspacing="0">
                                    <tr>
                                        <td align="center" colspan="3" class="gridCuatro"><font style="font-size:11px">ANUAL</font></td>
                                    </tr>
                                    <tr>
                                        <td align="center" width="5%" title="Dias trabajados" class="gridCuatro"><font style="font-size:10px">D.T.</font></td>
                                        <td align="center" width="5%" title="Retrasos" class="gridCuatro"><font style="font-size:10px">Ret.</font></td>
                                        <td align="center" width="5%" title="Ausencias" class="gridCuatro"><font style="font-size:10px">Aus.</font></td>
                                    </tr>
                                    <tr>
                                        <c:forEach varStatus="i" var="fal" items="${curso.faltas}">
                                            <c:if test="${i.count==3}">
                                                <td class="gridData" align="center"><strong>${gestion.dt1+gestion.dt2+gestion.dt3}</strong></td>
                                                <td class="gridData" align="center"><strong>${tRetraso}</strong></td>
                                                <td class="gridData" align="center"><strong>${tFalta}</strong></td>
                                            </c:if>
                                        </c:forEach>
                                    </tr>
                                </table>
                            </td>
                        </c:if>
                    </tr>
                    <c:if test="${curso.id_curso=='K'}">
                        <c:forEach varStatus="j" var="con" items="${curso.conductas}">
                            <c:if test="${j.count==2}">
                                <tr style="color:navy">
                                    <td valign="top"><strong>Diagn&oacute;stico final : </strong></td>
                                    <td align="justify" colspan="9">${con.diagnostico}</td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </c:if>
                    <tr>
                        <td align="center">
                            <table border="0" width="100%" style="cursor:pointer" cellpadding="0" cellspacing="0">
                                <c:if test="${curso.id_curso!='K'}">
                                    <tr>
                                        <td rowspan="5" align="center"><font style="font-size:10px">INFORMACION <br> DETALLADA</font></td>
                                        <td><font style="font-size:10px">Promedio : </font></td>
                                    </tr>
                                    <tr>
                                        <td><font style="font-size:10px">Conducta : </font></td>
                                    </tr>
                                </c:if>
                                <tr>
                                    <td><font style="font-size:10px">Faltas : </font></td>
                                </tr>
                                <tr>
                                    <td><font style="font-size:10px">Atrasos : </font></td>
                                </tr>
                                <tr>
                                    <td><font style="font-size:10px">Licencias : </font></td>
                                </tr>
                            </table>
                        </td>
                        <c:forEach varStatus="i" var="fal" items="${curso.faltas}">
                            <td colspan="3">
                                <table border="0" width="100%" style="cursor:pointer" cellpadding="0" cellspacing="0">
                                    <c:forEach varStatus="j" var="con" items="${curso.conductas}">
                                        <c:if test="${i.count==j.count && curso.id_curso!='K'}">
                                            <c:if test="${i.count==1}">
                                                <tr>
                                                    <td align="center"><font style="font-size:12px"><strong>${curso.ptrim1}</strong></font></td>
                                                </tr>
                                            </c:if>
                                            <c:if test="${i.count==2}">
                                                <tr>
                                                    <td align="center"><font style="font-size:12px"><strong>${curso.ptrim2}</strong></font></td>
                                                </tr>
                                            </c:if>
                                            <c:if test="${i.count==3}">
                                                <tr>
                                                    <td align="center"><font style="font-size:12px"><strong>${curso.ptrim3}</strong></font></td>
                                                </tr>
                                            </c:if>
                                            <tr>
                                                <td align="center"><font style="font-size:10px">${con.tipo_conducta}</font></td>
                                            </tr>
                                        </c:if>
                                    </c:forEach>
                                    <tr>
                                        <td align="center"><font style="font-size:10px">${fal.fsl}</font></td>
                                    </tr>
                                    <tr>
                                        <td align="center"><font style="font-size:10px">${fal.retraso}</font></td>
                                    </tr>
                                    <tr>
                                        <td align="center"><font style="font-size:10px">${fal.fcl}</font></td>
                                    </tr>
                                </table>
                            </td>
                        </c:forEach>
                    </tr>
                </table>
            </span>
        </div>
    </body>
</html>