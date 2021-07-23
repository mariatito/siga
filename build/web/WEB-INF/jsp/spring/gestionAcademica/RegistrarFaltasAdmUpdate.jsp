<%-- 
    Document   : RegistrarFaltasAdmUpdate
    Created on : 15-02-2010, 04:22:36 PM
    Author     : Marco Antonio
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

        <link rel="stylesheet" type="text/css" href="css/style2014.css" media="screen" />
        <script type="text/javascript" src="js/jquery-1.10.1.min.js"></script>
        <script type="text/javascript" src="js/script.js"></script>
        <script src="jquery-ui/jquery-ui-1.10.3.js" type="text/javascript"></script>
        <link href="jquery-ui/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />

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
            function popupWindow(u) {
                window.open(u, '', 'toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes,copyhistory=no,width=800,height=530,screenX=50,screenY=50,top=50,left=350');
            }
            function selectFalta(id_curso, id_estudiante, id_fecha_falta) {
                window.location = '<c:url value="/registrarFaltasAdm.do"/>?id_curso=' + id_curso + '&id_estudiante=' + id_estudiante + '&id_fecha_falta=' + id_fecha_falta + '&opcion=_updateFalta';
            }
            function deleteFalta(id_curso, id_estudiante, id_fecha_falta) {
                window.location = '<c:url value="/registrarFaltasAdm.do"/>?id_curso=' + id_curso + '&id_estudiante=' + id_estudiante + '&id_fecha_falta=' + id_fecha_falta + '&opcion=_updateFalta&eliminar=_eliminar';
            }
        </script>
    </head>
    <body>
        <table class="headerPage" cellpadding="0" cellspacing="0">
            <tr>
                <td>
                    <h1>${estudiante.nombres} ${estudiante.paterno} ${estudiante.materno} (${curso.curso} de ${curso.ciclo} - ${id_gestion})</h1>
                </td>
                <td style="text-align: right">
                </td>
            </tr>
        </table>

        <div class="maincontent">
            <table cellpadding="0" cellspacing="0" border="0" style="width: 100%">
                <tr>
                    <td style="width: 60%">
                        <table class="gridST" cellpadding="0" cellspacing="0" border="0" style="width: 100%">
                            <thead>
                                <tr>
                                    <th>Nro.</th>
                                    <th>Fecha</th>
                                    <th style="cursor:pointer">Tipo</th>
                                    <th>Modificaci&oacute;n</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <c:forEach varStatus="i" var="item" items="${faltas}">
                                <c:if test="${!empty item.faltas}">
                                    <thead>
                                        <tr>
                                            <th colspan="5">${i.count}<c:if test="${i.count==1 || i.count==3}">er. </c:if><c:if test="${i.count==2}">do. </c:if>Bimestre</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach varStatus="i" var="it" items="${item.faltas}">
                                            <tr <c:if test="${item.estado==false}">style="/*hacer lo*/"</c:if> >
                                                <td>${i.count}</td>
                                                <td title="Autor: ${it.nombre_reg}">${it.sffalta}</td>
                                                <td>
                                                    <c:if test="${it.tipo=='ret'}">Retraso</c:if>
                                                    <c:if test="${it.tipo=='fcl'}">Con licencia</c:if>
                                                    <c:if test="${it.tipo=='fsl'}">Sin licencia</c:if>
                                                    <c:if test="${it.tipo=='sun'}">Sin uniforme</c:if>
                                                    </td>
                                                    <td title="<c:if test="${it.id_usuario_modific!=null}">,Autor: ${it.nombre_mod}</c:if>">${it.sfmodific}</td>
                                                <td style="text-align: center;">
                                                    <img src="imagenes/edit.gif" onclick="selectFalta('${curso.id_curso}', '${estudiante.id_estudiante}', '${it.id_fecha_falta}')" title="Modificar" style="width: 30%;cursor: pointer">
                                                    <img src="imagenes/delete.gif" onclick="deleteFalta('${curso.id_curso}', '${estudiante.id_estudiante}', '${it.id_fecha_falta}')" title="Eliminar" style="width: 30%;cursor: pointer">
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </c:if>
                            </c:forEach>
                        </table>
                    </td>
                    <td style="width: 40%">
                        <c:if test="${!empty falta}">
                            <form action="<c:url value="/registrarFaltasAdm.do"/>" method="post">
                                <table width="100%" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF" style="cursor:pointer">
                                    <tr>
                                        <td class="tableHeader" colspan="3">Actualizar falta...</td>
                                    </tr>
                                    <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                        <td width="25%">Fecha</td>
                                        <td width="5%">:</td>
                                        <td width="70%">${falta.sfecha_falta}</td>
                                    </tr>
                                    <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                        <td width="25%">Tipo</td>
                                        <td width="5%">:</td>
                                        <td width="70%">
                                            <table width="100%">
                                                <tr>
                                                    <td>Falta sin licencia</td>
                                                    <td align="center"><input type="radio" name="tipo_falta" value="fsl" <c:if test="${falta.tipo=='fsl'}">checked</c:if>/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Falta con licencia</td>
                                                        <td align="center"><input type="radio" name="tipo_falta" value="fcl" <c:if test="${falta.tipo=='fcl'}">checked</c:if>/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Retraso</td>
                                                        <td align="center"><input type="radio" name="tipo_falta" value="ret" <c:if test="${falta.tipo=='ret'}">checked</c:if>/></td>
                                                    </tr>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="3" align="center">
                                                <button class="button-normal"><img src="imagenes/iconos_sigaa/diskette.gif" width="11px"> &nbsp;Actualizar...</button>
                                                <input type="hidden" name="guardar" value="_guardar">
                                                <input type="hidden" name="opcion" value="_updateFalta">
                                                <input type="hidden" name="id_fecha_falta" value="${falta.id_fecha_falta}">
                                            <input type="hidden" name="id_falta" value="${falta.id_falta}">
                                            <input type="hidden" name="id_estudiante" value="${estudiante.id_estudiante}">
                                            <input type="hidden" name="id_curso" value="${curso.id_curso}">
                                            <input type="hidden" name="post_tipo" value="${falta.tipo}">
                                        </td>
                                    </tr>
                                </table>
                            </form>
                        </c:if>
                    </td>
                </tr>
            </table>
        </div>
    </body>
</html>