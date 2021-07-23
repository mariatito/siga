<%-- 
    Document   : ActualizarEstados
    Created on : 01-jul-2010, 20:30:46
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
        <script type="text/javascript" src="js/ajax.js"></script>
        <script type="text/javascript" src="js/prototype.js"></script>
        <link rel="stylesheet" type="text/css" href="css/module.css" media="screen" />
        <script type="text/javascript">
            function onRowover(elem) { elem.className='colover'; }
            function onRowout(elem) { elem.className='colout'; }

            function openWindow(form,title,w,h){
                var modalwin=dhtmlmodal.open('win'+form, 'div', form, title, 'width='+w+'px,height='+h+'px,left=100px,top=100px,resize=1,scrolling=1');
                modalwin.moveTo('middle', 'middle');
                return modalwin;
            }
            function selectCurso(id_curso){
                window.location = '<c:url value="/actualizarEstados.do"/>?id_curso='+id_curso;
            }
            function selectGestion(id_ges){
                window.location = '<c:url value="/actualizarEstados.do"/>?id_gestion='+id_ges;
            }
            function popupWindow(u) {
                window.open(u,'','toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes,copyhistory=no,width=800,height=530,screenX=50,screenY=50,top=50,left=350');
            }
            function selectEstudiantes(id_curso){
                window.location = '<c:url value="/actualizarEstados.do"/>?id_gestion=${id_gestion}&id_curso='+id_curso+'&opcion=_updateEstado';
            }
            function setUpdateEstados(){
                document.formEstados.submit()
            }

        </script>
    </head>
    <body>
        <div class="headercontent">
            <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                <tr>
                    <td class="tab_init">&nbsp;</td>
                    <td style="width:40%">
                        <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                            <td class="tab_current" >Actualizar estados de estudiantes</td>
                            <td class="tab_normal" onclick="javascript:window.location='<c:url value="/estadoEstudiante.do"/>'">Estado de estudiantes</td>
                        </table>
                    </td>
                    <td class="tab_fin">&nbsp;</td>
                </tr>
            </table>
            <div class="titlepage">ACTUALIZAR ESTADOS DE ESTUDIANTES</div>
        </div>
        <div class="maincontent">
            <c:if test="${!empty gestion}">
                <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                    <tr>
                        <td class="tableHeader">Gestiones acad&eacute;micas</td>
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

            <c:if test="${!empty cursos}">
                <table cellpadding="0" cellspacing="0" border="0" style="width:100%" class="gridContent">
                    <tr>
                        <td class="tableHeader">Cursos (gesti&oacute;n ${id_gestion})</td>
                    </tr>
                    <tr>
                        <td>
                            <table width="100%" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF">
                                <tr>
                                    <td class="gridHeaders" width="5%">Nro.</td>
                                    <td class="gridHeaders" width="10%">C&oacute;digo</td>
                                    <td class="gridHeaders" width="85%">Curso</td>
                                </tr>
                                <c:forEach varStatus="j" var="item" items="${cursos}">
                                    <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)" onclick="selectEstudiantes('${item.id_curso}')" style="cursor:pointer">
                                        <td align="center" style="cursor:pointer" valign="top" class="gridData">${j.count}</td>
                                        <td align="center" style="cursor:pointer" valign="top" class="gridData">${item.desc_curso}</td>
                                        <td valign="top" class="gridData">
                                            <table border="0" width="100%">
                                                <tr>
                                                    <td valign="top" width="50%">
                                                        <img src="imagenes/iconos_sigaa/indicador.png">&nbsp;&nbsp;&nbsp;&nbsp;<strong>${item.curso} de ${item.ciclo}</strong>
                                                    </td>
                                                    <td valign="top" width="50%">
                                                        <span style="color:gray; font-size:10px">Cantidad de inscritos : &nbsp;${item.numEstudiantes}</span>
                                                    </td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </td>
                    </tr>
                </table>
            </c:if>
            <c:if test="${!empty curso}">
                <table cellpadding="0" cellspacing="0" border="0" style="width: 100% ; cursor:pointer" class="gridContent">
                    <tr>
                        <td class="tableHeader"><strong>Lista de estudiantes</strong></td>
                    </tr>
                    <tr>
                        <td>
                            <table cellpadding="0" cellspacing="0" border="0">
                                <tr>
                                    <td class="text-label"><button class="button-normal"  onclick="javascript:setUpdateEstados()" title="Guardar y  volver">Guardar y volver</button>&nbsp;</td>
                                    <td class="text-label">&nbsp;&nbsp;Curso:</td>
                                    <td style="color:gray">${curso.curso} de ${curso.ciclo}</td>
                                    <td class="text-label">&nbsp;&nbsp;Inscripcion:</td>
                                    <td style="color:gray">${id_gestion}</td>
                                    <td class="text-label">&nbsp;&nbsp;Cantidad de inscritos:</td>
                                    <td style="color:gray">${curso.numEstudiantes}</td>
                                </tr>
                            </table>
                            <div id="resultlayer1">
                                <form action="<c:url value="/actualizarEstados.do"/>" method="post" name="formEstados">
                                    <table width="100%" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF">
                                        <tr>
                                            <td class="gridHeaders" width="10%">Nro.</td>
                                            <td class="gridHeaders" width="10%">C&oacute;digo</td>
                                            <td class="gridHeaders" width="60%">Apellidos y nombres</td>
                                            <td class="gridHeaders" width="20%">Estado</td>
                                        </tr>
                                        <c:if test="${empty curso.estudiantes}">
                                            <tr>
                                                <td class="gridData" colspan="4">No se han encontrado elementos.</td>
                                            </tr>
                                        </c:if>
                                        <c:forEach varStatus="i" var="item" items="${curso.estudiantes}">
                                            <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)" onclick="selectEstudiante('${curso.id_curso}','${item.id_estudiante}')">
                                                <td align="center" class="gridData"><c:out value="${i.count}"/> </td>
                                                <td class="gridData">${item.codigo}</td>
                                                <td class="gridData">${item.paterno} ${item.materno} ${item.nombres}</td>
                                                <td class="gridData">
                                                    <select name="id_estado-${item.id_estudiante}" class="text-field" style="width:150px">
                                                        <c:forEach var="items" items="${estados}">
                                                            <option value="${items.id}"<c:if test="${items.id == item.id_estado}"> selected </c:if>> ${items.valor}
                                                            </c:forEach>
                                                    </select>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                    <input type="hidden" name="id_gestion" value="${id_gestion}">
                                    <input type="hidden" name="id_curso" value="${curso.id_curso}">
                                    <input type="hidden" name="newEstado" value="_newEstado">

                                </form>
                            </div>
                        </td>
                    </tr>
                </table>
            </c:if>
        </div>
    </body>
</html>
