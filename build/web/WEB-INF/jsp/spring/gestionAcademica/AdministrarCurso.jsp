<%-- 
    Document   : AdministrarCurso
    Created on : 12-sep-2009, 10:55:53
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
                window.location = '<c:url value="/administrarCurso.do"/>?id_curso='+id_curso;
            }
            function selectGestion(id_ges){
                window.location = '<c:url value="/administrarCurso.do"/>?id_gestion='+id_ges;
            }
            function gridEvent(layer) {
                if($(layer).style.display=='none') {
                    $(layer).style.display='inline';
                } else {
                    $(layer).style.display='none';
                }
            }
            function popupWindow(u) {
                window.open(u,'','toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes,copyhistory=no,width=800,height=530,screenX=50,screenY=50,top=50,left=350');
            }
            function selectMateria(id_materia,id_curso,id_curso_materia){
                popupWindow('<c:url value="/administrarCurso.do"/>?id_materia='+id_materia+'&id_curso='+id_curso+'&id_gestion=${id_gestion}&opcion=DatosGenerales&id_curso_materia='+id_curso_materia);
            }
            function descargarListaExcel(){
                alert("falta el módulo");
            }
            var printWin = null;
            function imprimirListaPdf(){
                printWin = openWindow('documentoPdF','Imprimir...','650','340');
                document.getElementById('documentview').src = 'documentos/docentes/${pdf}';
                printWin.moveTo('middle', 'middle');
            }
        </script> 
    </head>
    <body>
        <div class="headercontent">
            <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                <tr>
                    <td class="tab_init">&nbsp;</td>
                    <td style="width:90%">
                        <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                            <td class="tab_normal" onclick="javascript:window.location='<c:url value="/adminPlan.do"/>'">Plan de estudio</td>
                            <td class="tab_normal" onclick="javascript:window.location='<c:url value="/asignarDocentes.do"/>'">Asignaci&oacute;n curso-docente</td>
                            <td class="tab_current" >Administrar cursos</td>
                            <td class="tab_normal" onclick="javascript:window.location='<c:url value="/administrarEvaluaciones.do"/>'">Fechas de evaluaciones general</td>
                            <td class="tab_normal" onclick="javascript:window.location='<c:url value="/boletinesTrimestrales.do"/>'">Boletines trimestrales</td>
                        </table>
                    </td>
                    <td class="tab_fin">&nbsp;</td>
                </tr>
            </table>
            <div class="titlepage">ADMINISTRACI&Oacute;N DE CURSO</div>
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

            <c:if test="${!empty cursos}">
                <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                    <tr>
                        <td class="tableHeader">Cursos y materias (${id_gestion})</td>
                    </tr>
                    <tr>
                        <td class="gridContent">
                            <table width="100%" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF">
                                <tr>
                                    <td class="gridHeaders" width="5%">Nro.</td>
                                    <td class="gridHeaders" width="10%">C&oacute;digo</td>
                                    <td class="gridHeaders" width="85%">Curso</td>
                                </tr>
                                <c:forEach varStatus="j" var="item" items="${cursos}">
                                    <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)" onclick="javascript:gridEvent('${item.id_curso}')">
                                        <td align="center" style="cursor:pointer" valign="top" class="gridData">${j.count}</td>
                                        <td align="center" style="cursor:pointer" valign="top" class="gridData">${item.desc_curso}</td>
                                        <td valign="top" class="gridData">
                                            <table border="0" width="100%">
                                                <tr>
                                                    <td valign="top" width="70%"><span style="cursor:pointer"><img src="imagenes/bulletarrow.gif"><strong>${item.curso} de ${item.ciclo}</strong></span></td>
                                                </tr>
                                            </table>
                                            <div id="${item.id_curso}" style="display:none">
                                                <table border="0" class="gridContent" width="100%">
                                                    <c:forEach varStatus="i" var="it" items="${cursoMaterias}">
                                                        <c:if test="${item.id_curso==it.id_curso}">
                                                            <tr onclick="selectMateria('${it.id_materia}','${item.id_curso}','${it.id_curso_materia}'); gridEvent('${item.id_curso}')" style="cursor:pointer">
                                                                <td style="width:20%"><img src="imagenes/cxc.png"><c:out value="${it.materia}"/></td>
                                                            </tr>
                                                        </c:if>
                                                    </c:forEach>
                                                </table>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </td>
                    </tr>
                </table>
            </c:if>
        </div>
    </body>
</html>
