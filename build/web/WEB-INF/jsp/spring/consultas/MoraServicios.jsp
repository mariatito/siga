<%-- 
    Document   : MoraServicios
    Created on : 01-jul-2010, 8:43:25
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
        <link rel="stylesheet" type="text/css" href="css/st.css" media="screen" />
        <script type="text/javascript">
            function onRowover(elem) { elem.className='colover'; }
            function onRowout(elem) { elem.className='colout'; }

            function openWindow(form,title,w,h){
                var modalwin=dhtmlmodal.open('win'+form, 'div', form, title, 'width='+w+'px,height='+h+'px,left=100px,top=100px,resize=1,scrolling=1');
                modalwin.moveTo('middle', 'middle');
                return modalwin;
            }
            function selectGestion(id_ges){
                window.location = '<c:url value="/moraServicios.do"/>?id_gestion='+id_ges;
            }
            function popupWindow(u) {
                window.open(u,'','toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes,copyhistory=no,width=800,height=530,screenX=50,screenY=50,top=50,left=350');
            }
            function setImprimirExcelMora(){
                window.location = '<c:url value="/moraServicios.do"/>?id_gestion=${id_gestion}&id_curso=${id_curso}&categoria=${categoria}&opcion=MORA&accion=_imprimir';
            }
        </script>
    </head>
    <body>
        <div class="headercontent">
            <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                <tr>
                    <td class="tab_init">&nbsp;</td>
                    <td style="width:20%">
                        <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                            <td class="tab_current" style="border-right:1px solid #7D909E">Mora en sercicios</td>
                        </table>
                    </td>
                    <td class="tab_fin">&nbsp;</td>
                </tr>
            </table
            <div class="titlepage">MORA EN SERVICIOS</div>
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
                                        <td align="center" style="cursor:pointer" valign="top" class="gridData">
                                            <c:if test="${item.estado=='true'}"><img src="imagenes/iconos_sigaa/activo_si.png" border="0" title="Estado activo"></c:if>
                                            <c:if test="${item.estado=='false'}"><img src="imagenes/iconos_sigaa/activo_no.png" border="0" title="Estado inactivo"></c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </td>
                    </tr>
                </table>
            </c:if>

            <c:if test="${!empty question}">
                <form action="<c:url value="/moraServicios.do"/>" method="post" name="formConsultas">
                    <table width="100%" cellpadding="0" cellspacing="0" border="0" class="gridContent">
                        <tr>
                            <td width="15%">GESTI&Oacute;N</td>
                            <td width="30%" class="opcionConsulta">${id_gestion}</td>
                            <td width="5%"></td>
                            <td width="15%">TIPO DE CONSULTA</td>
                            <td width="35%" class="opcionConsulta">Mora en servicios de estudiantes</td>
                        </tr>
                        <tr>
                            <td>CURSO</td>
                            <td class="opcionConsulta">
                                <select name="id_curso" class="text-field" style="width:150px">
                                    <option value="C" <c:if test="${id_curso=='C'}">selected</c:if>> Colegio (general)
                                        <c:forEach var="item" items="${cursos}">
                                        <option value="${item.id_curso}" <c:if test="${item.id_curso == id_curso}"> selected </c:if>> ${item.curso} de ${item.ciclo}
                                        </c:forEach>
                                </select>
                            </td>
                            <td colspan="2"></td>
                            <td class="opcionConsulta"></td>
                        </tr>
                        <tr>
                            <td>G&Eacute;NERO</td>
                            <td class="opcionConsulta">
                                <select name="categoria" class="text-field" style="width:150px">
                                    <c:forEach var="item" items="${tipos_sexos}">
                                        <option value="${item.id}"<c:if test="${item.id == categoria  }"> selected </c:if>><c:if test="${item.id =='A'}">MIXTO</c:if><c:if test="${item.id !='A'}">${item.valor}</c:if>
                                        </c:forEach>
                                </select>
                            </td>
                            <td colspan="2"></td>
                            <td class="opcionConsulta"></td>
                        </tr>
                        <tr>
                            <td colspan="5" align="center">
                                <input type="hidden" name="opcion" value="MORA">
                                <input class="button-submit" type="submit" value="Generar consulta">
                                <input type="hidden" name="id_gestion" value="${id_gestion}">
                            </td>
                        </tr>
                    </table>
                </form>
            </c:if>            
            <c:if test="${!empty mora}">
                <table cellpadding="0" cellspacing="0" border="0" style="width: 100% ; cursor:pointer" class="gridContent">
                    <tr>
                        <td class="tableHeader"><strong>Resultados de la consulta</strong></td>
                    </tr>
                    <tr>
                        <td>
                            <table cellpadding="0" cellspacing="0" border="0">
                                <tr>
                                    <td class="text-label"><button class="button-normal"  onclick="setImprimirExcelMora()" title="Imprimir documento en excel">Imprimir Excel</button>&nbsp;</td>
                                    <td class="text-label">&nbsp;&nbsp;Curso(s):</td>
                                    <c:if test="${id_curso!='C'}"><td style="color:gray">${curso.curso} de ${curso.ciclo}</td></c:if>
                                    <c:if test="${id_curso=='C'}"><td style="color:gray">KINDER a 4to de secundaria</td></c:if>
                                    <td class="text-label">&nbsp;&nbsp;Inscripcion:</td>
                                    <td style="color:gray">${id_gestion}</td>
                                    <td class="text-label">&nbsp;&nbsp;Categoria:</td>
                                    <td style="color:gray"><c:if test="${categoria=='A'}">Mixto</c:if><c:if test="${categoria=='F'}">Femenino</c:if><c:if test="${categoria=='M'}">Masculino</c:if></td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div id="resultlayer1">
                                <table width="100%" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF">
                                    <tr>
                                        <td class="gridHeaders" width="5%">Nro.</td>
                                        <td class="gridHeaders" width="10%">C&oacute;digo</td>
                                        <c:if test="${id_curso!='C'}">
                                            <td class="gridHeaders" width="85%">Apellidos y nombres</td>
                                        </c:if>
                                        <c:if test="${id_curso=='C'}">
                                            <td class="gridHeaders" width="55%">Apellidos y nombres</td>
                                            <td class="gridHeaders" width="30%">Curso</td>
                                        </c:if>
                                    </tr>
                                    <c:if test="${empty curso.estudiantes}">
                                        <tr>
                                            <td class="gridData" colspan="4">No se han encontrado elementos.</td>
                                        </tr>
                                    </c:if>
                                    <% int contm = 1;%>
                                    <c:forEach varStatus="i" var="item" items="${curso.estudiantes}">
                                        <c:if test="${categoria=='A'}">
                                            <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                                <td align="center" class="gridData" valign="top"><c:out value="${i.count}"/> </td>
                                                <td class="gridData" valign="top">${item.codigo}</td>
                                                <c:if test="${id_curso!='C'}">
                                                    <td class="gridData" valign="top">${item.paterno} ${item.materno} ${item.nombres}
                                                        <table width="100%" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF">
                                                            <tr>
                                                                <td style="color: teal" colspan="3">Servicios pendientes</td>
                                                            </tr>
                                                            <c:forEach varStatus="j" var="it" items="${item.servicios}">
                                                                <tr>
                                                                    <td width="5%">&nbsp;&nbsp;&nbsp;${j.count}</td>
                                                                    <td width="80%">${it.tipo_servicio}</td>
                                                                    <td width="15%">${it.monto_servicio} Bs.</td>
                                                                </tr>
                                                            </c:forEach>
                                                        </table>
                                                    </td>
                                                </c:if>
                                                <c:if test="${id_curso=='C'}">
                                                    <td class="gridData" valign="top">${item.paterno} ${item.materno} ${item.nombres}
                                                        <table width="100%" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF">
                                                            <tr>
                                                                <td style="color: teal" colspan="3">Servicios pendientes</td>
                                                            </tr>
                                                            <c:forEach varStatus="j" var="it" items="${item.servicios}">
                                                                <tr>
                                                                    <td width="5%">&nbsp;&nbsp;&nbsp;${j.count}</td>
                                                                    <td width="80%">${it.tipo_servicio}</td>
                                                                    <td width="15%">${it.monto_servicio} Bs.</td>
                                                                </tr>
                                                            </c:forEach>
                                                        </table>
                                                    </td>
                                                    <td class="gridData" valign="top">${item.curso}</td>
                                                </c:if>
                                            </tr>
                                        </c:if>
                                        <c:if test="${categoria=='F' && item.id_sexo==categoria}">
                                            <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                                <td align="center" class="gridData" valign="top"><% out.print(contm++);%></td>
                                                <td class="gridData" valign="top">${item.codigo}</td>
                                                <c:if test="${id_curso!='C'}">
                                                    <td class="gridData" valign="top">${item.paterno} ${item.materno} ${item.nombres}
                                                        <table width="100%" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF">
                                                            <tr>
                                                                <td style="color: teal" colspan="3">Servicios pendientes</td>
                                                            </tr>
                                                            <c:forEach varStatus="j" var="it" items="${item.servicios}">
                                                                <tr>
                                                                    <td width="5%">&nbsp;&nbsp;&nbsp;${j.count}</td>
                                                                    <td width="80%">${it.tipo_servicio}</td>
                                                                    <td width="15%">${it.monto_servicio} Bs.</td>
                                                                </tr>
                                                            </c:forEach>
                                                        </table>
                                                    </td>
                                                </c:if>
                                                <c:if test="${id_curso=='C'}">
                                                    <td class="gridData" valign="top">${item.paterno} ${item.materno} ${item.nombres}
                                                        <table width="100%" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF">
                                                            <tr>
                                                                <td style="color: teal" colspan="3">Servicios pendientes</td>
                                                            </tr>
                                                            <c:forEach varStatus="j" var="it" items="${item.servicios}">
                                                                <tr>
                                                                    <td width="5%">&nbsp;&nbsp;&nbsp;${j.count}</td>
                                                                    <td width="80%">${it.tipo_servicio}</td>
                                                                    <td width="15%">${it.monto_servicio} Bs.</td>
                                                                </tr>
                                                            </c:forEach>
                                                        </table>
                                                    </td>
                                                    <td class="gridData" valign="top">${item.curso}</td>
                                                </c:if>

                                            </tr>
                                        </c:if>
                                        <c:if test="${categoria=='M' && item.id_sexo==categoria}">
                                            <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                                <td align="center" class="gridData" valign="top"><% out.print(contm++);%></td>
                                                <td class="gridData" valign="top">${item.codigo}</td>
                                                <c:if test="${id_curso!='C'}">
                                                    <td class="gridData" valign="top">${item.paterno} ${item.materno} ${item.nombres}
                                                        <table width="100%" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF">
                                                            <tr>
                                                                <td style="color: teal" colspan="3">Servicios pendientes</td>
                                                            </tr>
                                                            <c:forEach varStatus="j" var="it" items="${item.servicios}">
                                                                <tr>
                                                                    <td width="5%">&nbsp;&nbsp;&nbsp;${j.count}</td>
                                                                    <td width="80%">${it.tipo_servicio}</td>
                                                                    <td width="15%">${it.monto_servicio} Bs.</td>
                                                                </tr>
                                                            </c:forEach>
                                                        </table>
                                                    </td>
                                                </c:if>
                                                <c:if test="${id_curso=='C'}">
                                                    <td class="gridData" valign="top">${item.paterno} ${item.materno} ${item.nombres}
                                                        <table width="100%" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF">
                                                            <tr>
                                                                <td style="color: teal" colspan="3">Servicios pendientes</td>
                                                            </tr>
                                                            <c:forEach varStatus="j" var="it" items="${item.servicios}">
                                                                <tr>
                                                                    <td width="5%">&nbsp;&nbsp;&nbsp;${j.count}</td>
                                                                    <td width="80%">${it.tipo_servicio}</td>
                                                                    <td width="15%">${it.monto_servicio} Bs.</td>
                                                                </tr>
                                                            </c:forEach>
                                                        </table>
                                                    </td>
                                                    <td class="gridData" valign="top">${item.curso}</td>
                                                </c:if>
                                            </tr>
                                        </c:if>
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