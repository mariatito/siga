<%-- 
    Document   : BoletinesTrimestrales
    Created on : 27-01-2010, 11:09:27 PM
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
        <link rel="stylesheet" href="imagenes/dhtmlmodal/windowfiles/dhtmlwindow.css" type="text/css" />
        <script type="text/javascript" src="imagenes/dhtmlmodal/windowfiles/dhtmlwindow.js"></script>
        <link rel="stylesheet" href="imagenes/dhtmlmodal/modalfiles/modal.css" type="text/css" />
        <script type="text/javascript" src="imagenes/dhtmlmodal/modalfiles/modal.js"></script>
        <script type="text/javascript" src="js/jquery-1.2.2.pack.js"></script>
        <script type="text/javascript" src="js/animatedcollapse.js"></script>    
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

            function selectGestion(id_ges){
                window.location = '<c:url value="/boletinesTrimestrales.do"/>?id_gestion='+id_ges;
            }

            function popupWindow(u) {
                window.open(u,'','toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes,copyhistory=no,width=800,height=530,screenX=50,screenY=50,top=50,left=350');
            }

            function selectEstudiante(id_curso,id_estudiante){
                popupWindow('<c:url value="/boletinesTrimestrales.do"/>?id_curso='+id_curso+'&id_estudiante='+id_estudiante+'&id_gestion=${id_gestion}&opcion=BoletinEstudiante&eval=1');
            }
            function selectCurso(id_curso){
                window.location = '<c:url value="/boletinesTrimestrales.do"/>?id_curso='+id_curso+'&id_gestion=${id_gestion}';
            }
            function openWindow(form,title,w,h){
                var addFormwin=dhtmlmodal.open('win'+form, 'div', form, title, 'width='+w+'px,height='+h+'px,left=100px,top=100px,resize=1,scrolling=1');
                addFormwin.moveTo('middle', 'middle');
                return addFormwin;
            }
            var printWin = null;
            function setImprimirPdf() {
                printWin = openWindow('documentoPdF','Imprimir...','650','340');
                document.getElementById('documentview').src = 'documentos/nominas/pdf/${pdf}';
                printWin.moveTo('middle', 'middle');
            }
            function setImprimirExcel() {
                window.location = '<c:url value="/boletinesTrimestrales.do"/>?id_curso=${curso.id_curso}&id_gestion=${id_gestion}&opcion=_imprimeExcel';
            }
            function setImprimirBoletines(){
                var printBoletin=dhtmlmodal.open('windowMAQV', 'iframe', '<c:url value="/boletinesTrimestrales.do"/>?id_curso=${curso.id_curso}&id_gestion=${id_gestion}&opcion=_boletines', 'Boletines triemstrales del curso ${curso.curso} de ${curso.ciclo}', 'width=650px,height=340px,left=100px,top=100px,resize=1,scrolling=1,center=1');
                printBoletin.moveTo('middle', 'middle');
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
                            <td class="tab_normal" onclick="javascript:window.location='<c:url value="/adminPlan.do"/>';">Plan de estudio</td>
                            <td class="tab_normal" onclick="javascript:window.location='<c:url value="/asignarDocentes.do"/>';">Asignaci&oacute;n curso-docente</td>
                            <td class="tab_normal" onclick="javascript:window.location='<c:url value="/administrarCurso.do"/>';">Administrar cursos</td>
                            <td class="tab_normal" onclick="javascript:window.location='<c:url value="/administrarEvaluaciones.do"/>';">Fechas de evaluaciones general</td>
                            <td class="tab_current">Boletines trimestrales</td>
                        </table>
                    </td>
                    <td class="tab_fin">&nbsp;</td>
                </tr>
            </table>
            <div class="titlepage">BOLETINES TRIMESTRALES</div>
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
                                    <tr onmouseover="onRowover(this);" onmouseout="onRowout(this);" onclick="selectGestion('${item.id_gestion}');">
                                        <td align="center" style="cursor:pointer" valign="top" class="gridData">${j.count}</td>
                                        <td align="center" style="cursor:pointer" valign="top" class="gridData">${item.id_gestion}</td>
                                        <td style="cursor:pointer" valign="top" class="gridData">${item.lema}</td>
                                        <td style="cursor:pointer" valign="top" class="gridData">${item.titulo} ${item.nombre}</td>
                                        <td align="center" style="cursor:pointer" valign="top" class="gridData"><c:if test="${item.estado=='true'}">
                                            <img src="imagenes/iconos_sigaa/activo_si.png" border="0" title="Estado activo"></c:if><c:if test="${item.estado=='false'}">
                                            <img src="imagenes/iconos_sigaa/activo_no.png" border="0" title="Estado inactivo"></c:if></td>
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
                                    <tr style="cursor:pointer" onmouseover="onRowover(this);" onmouseout="onRowout(this);" onclick="selectCurso('${item.id_curso}');">
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
            <c:if test="${!empty curso}">
                <table cellpadding="0" cellspacing="0" border="0" style="width: 100% ; cursor:pointer">
                    <tr>
                        <td class="tableHeader"><strong>Lista de estudiantes</strong></td>
                    </tr>
                    <tr>
                        <td class="gridContent">
                            <table cellpadding="0" cellspacing="0" border="0">
                                <tr>
                                    <td class="text-label"><button class="button-normal"  onclick="javascript:setImprimirPdf();" title="Imprime la lista en formato pdf">Imprimir Pdf</button>&nbsp;</td>
                                    <td class="text-label"><button class="button-normal"  onclick="javascript:setImprimirExcel();" title="Imprime la lista en formato excel">Imprimir Excel</button>&nbsp;</td>
                                    <td class="text-label"><button class="button-normal"  onclick="javascript:setImprimirBoletines();" title="Imprimir boletines del curso ${curso.curso} de ${curso.ciclo}">Imprimir boletines</button>&nbsp;</td>
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
                                    </tr>
                                    <c:if test="${empty curso.estudiantes}">
                                        <tr>
                                            <td class="gridData" colspan="3">No se han encontrado elementos.</td>
                                        </tr>
                                    </c:if>
                                    <c:forEach varStatus="i" var="item" items="${curso.estudiantes}">
                                        <tr onmouseover="onRowover(this);" onmouseout="onRowout(this);" onclick="selectEstudiante('${curso.id_curso}','${item.id_estudiante}');">
                                            <td align="center" class="gridData"><c:out value="${i.count}"/> </td>
                                            <td class="gridData">${item.codigo}</td>
                                            <td class="gridData">${item.paterno} ${item.materno} ${item.nombres}</td>
                                        </tr>
                                    </c:forEach>
                                </table>
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