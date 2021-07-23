<%-- 
    Document   : HistorialAcademico
    Created on : 08-02-2010, 02:28:35 PM
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
        <link rel="stylesheet" type="text/css" href="css/module.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="css/st.css" media="screen" />
        <script type="text/javascript" src="js/prototype.js"></script>

        <script type="text/javascript">
            function onRowover(elem) { elem.className='colover'; }
            function onRowout(elem) { elem.className='colout'; }

            function selectGestion(id_gestion){
                window.location = '<c:url value="/historialAcademico.do"/>?id_gestion='+id_gestion;
            }
            function selectCurso(id_curso){
                window.location = '<c:url value="/historialAcademico.do"/>?id_curso='+id_curso+'&id_gestion=${id_gestion}';
            }
            function popupWindow(u) {
                window.open(u,'','toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes,copyhistory=no,width=800,height=530,screenX=50,screenY=50,top=50,left=350');
            }
            function selectEstudiante(id_curso,id_estudiante,id_familia){
                popupWindow('<c:url value="/historialAcademico.do"/>?id_curso='+id_curso+'&id_estudiante='+id_estudiante+'&id_gestion=${id_gestion}&opcion=HistorialEstudiante&id_familia='+id_familia);
            }
            function setImprimirExcel() {
                window.location = '<c:url value="/historialAcademico.do"/>?id_curso=${curso.id_curso}&id_gestion=${id_gestion}&opcion=_imprimeExcel';
            }
        </script>

    </head>
    <body>
        <div class="headercontent">
            <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                <tr>
                    <td class="tab_init">&nbsp;</td>
                    <td style="width:80%">
                        <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                            <tr>
                                <td class="tab_normal" onclick="javascript:window.location='<c:url value="/inscripcion.do"/>'">Inscripciones</td>
                                <td class="tab_normal" onclick="javascript:window.location='<c:url value="/asignacionPagos.do"/>'">Asignaci&oacute;n de pagos</td>
                                <td class="tab_normal" onclick="javascript:window.location='<c:url value="/depositosBancarios.do"/>'">Adm. dep&oacute;sitos bancarios</td>
                                <td class="tab_current" style="border-right:1px solid #7D909E">Historial acad&eacute;mico</td>
                                <td class="tab_normal" onclick="javascript:window.location='<c:url value="/inscripcionRezagada.do"/>'">Inscripciones rezagadas</td>
                            </tr>
                        </table>
                    </td>
                    <td class="tab_fin">&nbsp;</td>
                </tr>
            </table>
            <div class="titlepage">HISTORIAL ACAD&Eacute;MICO</div>
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
                                    <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)" <c:if test="${item.estado==true}">onclick="selectGestion('${item.id_gestion}')"</c:if>>
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
                <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                    <tr>
                        <td class="tableHeader">CURSOS Y MATERIAS (${id_gestion})</td>
                    </tr>
                    <tr>
                        <td class="gridContent">
                            <table width="100%" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF">
                                <tr>
                                    <td class="gridHeaders" width="5%">Nro.</td>
                                    <td class="gridHeaders" width="10%">Cod.</td>
                                    <td class="gridHeaders" width="85%">Curso</td>
                                </tr>
                                <c:forEach varStatus="j" var="item" items="${cursos}">
                                    <tr style="cursor:pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)" onclick="selectCurso('${item.id_curso}')">
                                        <td align="center" class="gridData">${j.count}</td>
                                        <td align="center" class="gridData">${item.desc_curso}</td>
                                        <td valign="top" class="gridData">
                                            <table border="0" width="100%">
                                                <tr>
                                                    <td valign="top" width="50%"><span style="cursor:pointer; color:teal"><img src="imagenes/cxc.png">&nbsp;&nbsp;<strong>${item.curso} de ${item.ciclo}</strong></span></td>
                                                    <td valign="top" width="50%"><span style="cursor:pointer; color:gray; font-size:10px">Cantidad de inscritos : &nbsp;${item.numEstudiantes}</span></td>
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
            <c:if test="${!empty curso}"> <img src="imagenes/pixel_trans.gif" width="100%" height="1">
                <table cellpadding="0" cellspacing="0" border="0" style="width: 100% ; cursor:pointer">
                    <tr>
                        <td class="tableHeader"><strong>Lista de estudiantes</strong></td>
                    </tr>
                    <tr>
                        <td class="gridContent">
                            <table cellpadding="0" cellspacing="0" border="0">
                                <tr>
                                    <!--td class="text-label"><button class="button-normal"  onclick="javascript:setImprimirExcel()" title="Imprime la lista en formato excel">Imprimir Excel</button>&nbsp;</td-->
                                    <td class="text-label">&nbsp;&nbsp;Curso:</td>
                                    <td style="color:gray">${curso.curso} de ${curso.ciclo}</td>
                                    <td class="text-label">&nbsp;&nbsp;Inscripcion:</td>
                                    <td style="color:gray">${id_gestion}</td>
                                    <td class="text-label">&nbsp;&nbsp;Cantidad de inscritos:</td>
                                    <td style="color:gray">${curso.numEstudiantes}</td>
                                </tr>
                            </table>
                            <div id="resultlayer2">
                                <table width="100%" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF">
                                    <tr>
                                        <td class="gridHeaders" width="10%">Nro.</td>
                                        <td class="gridHeaders" width="10%">C&oacute;digo</td>
                                        <td class="gridHeaders" width="80%">Apellidos y nombres</td>
                                        <!--td class="gridHeaders" width="30%">Fecha de nacimiento</td-->
                                    </tr>
                                    <c:if test="${empty curso.estudiantes}">
                                        <tr>
                                            <td class="gridData" colspan="3">No se han encontrado elementos.</td>
                                        </tr>
                                    </c:if>
                                    <c:forEach varStatus="i" var="item" items="${curso.estudiantes}">
                                        <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)" onclick="selectEstudiante('${curso.id_curso}','${item.id_estudiante}','${item.id_familia}')">
                                            <td align="center" class="gridData"><c:out value="${i.count}"/> </td>
                                            <td class="gridData">${item.codigo}</td>
                                            <td class="gridData">${item.paterno} ${item.materno} ${item.nombres}</td>
                                            <!--td class="gridData">${item.sfecha_nacimiento}</td-->
                                        </tr>
                                    </c:forEach>
                                </table> 
                            </div>
                        </td>
                    </tr>
                </table>
            </c:if>
        </div>
    </body>
</html>