<%-- 
    Document   : InformacionFamilias
    Created on : 10-jul-2010, 19:31:57
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
        <link rel="stylesheet" href="imagenes/dhtmlmodal/windowfiles/dhtmlwindow.css" type="text/css" />
        <script type="text/javascript" src="imagenes/dhtmlmodal/windowfiles/dhtmlwindow.js"></script>
        <link rel="stylesheet" href="imagenes/dhtmlmodal/modalfiles/modal.css" type="text/css" />
        <script type="text/javascript" src="imagenes/dhtmlmodal/modalfiles/modal.js"></script>
        <script type="text/javascript" src="js/jquery-1.2.2.pack.js"></script>
        <script type="text/javascript" src="js/animatedcollapse.js"></script>
        <script type="text/javascript" src="js/ajax.js"></script>
        <link rel="stylesheet" type="text/css" href="css/module.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="css/st.css" media="screen" />
        <script type="text/javascript" src="js/prototype.js"></script>

        <script type="text/javascript">
            function onRowover(elem) {
                elem.className='colover';
            }
            function onRowout(elem) {
                elem.className='colout';
            }
            function setImprimirResultado(){
                var printResultado=dhtmlmodal.open('windowMAQV', 'iframe', '<c:url value="/informacionFamilias.do"/>?&imprimirPdf=_imprimirPdf&search=${search}', 'Lista de resultados', 'width=650px,height=340px,left=100px,top=100px,resize=1,scrolling=1,center=1');
                printResultado.moveTo('middle', 'middle');
            }
        </script>

    </head>
    <body>
        <div class="headercontent">
            <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                <tr>
                    <td class="tab_init">&nbsp;</td>
                    <td style="width:60%">
                        <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                            <tr>
                                <td class="tab_normal" onclick="javascript:window.location='<c:url value="/informacionEstudiantes.do"/>'">Informaci&oacute;n del alumnado</td>
                                <td class="tab_normal" onclick="javascript:window.location='<c:url value="/informacionEstudiantesCurso.do"/>'">Informaci&oacute;n del alumnado por curso</td>
                                <td class="tab_current">Informaci&oacute;n de familias</td>
                            </tr>
                        </table>
                    </td>
                    <td class="tab_fin">&nbsp;</td>
                </tr>
            </table>
            <div class="titlepage">INFORMACI&Oacute;N DE FAMILIAS ${gestion.id_gestion}</div>
        </div>
        <div class="maincontent">
            <c:if test="${empty persona}">
                <table>
                    <form action="<c:url value="/informacionFamilias.do"/>" method="post" name="frmSeach" onsubmit="return setBuscar(this)">
                        <td class="text-label">Buscar familia</td>
                        <td class="text-label"><input type="text" name="search" value="${search}" class="text-field"></td>
                        <td class="text-label"><input class="button-submit" type="submit" name="opcion" value="Buscar"></td>
                    </form>
                </table>
                <c:if test="${empty intro}">
                    <table cellpadding="0" cellspacing="0" border="0" style="width:100%" class="gridContent">
                        <tr>
                            <td class="tableHeader"> Tutores de los Estudiantes</td>
                        </tr>
                        <c:if test="${!empty listafamilia}">
                            <tr>
                                <td>
                                    <table cellpadding="0" cellspacing="0" border="0">
                                        <tr>
                                            <td>
                                                <button class="button-normal"  onclick="setImprimirResultado()">
                                                    <img src="imagenes/iconos_sigaa/print.gif" border="0" width="10px">
                                                    &nbsp;&nbsp;Imprimir informaci&oacute;n desplegada
                                                </button>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </c:if>
                        <tr>
                            <td class="gridContent">
                                <div id="resultlayer1">
                                    <table width="100%" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF" class="menuHead">
                                        <tr>
                                            <td class="gridHeaders" style="width:5%">Nro.</td>
                                            <td class="gridHeaders" style="width:55%">Tutor/es</td>
                                            <td class="gridHeaders" style="width:40%">Estudiantes y cursos (Gesti&oacute;n ${gestion.id_gestion})</td>
                                        </tr>
                                        <c:if test="${empty listafamilia}">
                                            <tr>
                                                <td class="gridData" colspan="3">No se han encontrado elementos.</td>
                                            </tr>
                                        </c:if>
                                        <% int cont = 1;%>
                                        <c:forEach varStatus="i" var="item" items="${listafamilia}">
                                            <c:if test="${item.estado=='activo'}">
                                                <tr onclick="selectFamilia('${item.id_familia}')" style="cursor: pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                                    <td class="gridData" valign="top"><% out.print(cont++);%></td>
                                                    <td class="gridData">
                                                        <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                                                            <tr>
                                                                <td width="15%"><strong>C&oacute;digo :</strong></td>
                                                                <td width="85%" style="color:navy">${item.id_familia}</td>
                                                            </tr>
                                                            <c:if test="${item.nombre_apellidos_tutor_1!=null&&item.nombre_apellidos_tutor_1!=' '&&item.nombre_apellidos_tutor_1!='  '}">
                                                                <tr>
                                                                    <td width="15%"><strong>Tutor 1 :</strong></td>
                                                                    <td width="85%" style="color:navy">
                                                                        <strong>
                                                                            ${item.nombre_apellidos_tutor_1}
                                                                            <c:if test="${item.ci_tutor_1==item.ci_resp_pago}">
                                                                                <font style="color:red">(Resp. pago)</font>
                                                                            </c:if>
                                                                        </strong>
                                                                        ,  CI: ${item.ci_tutor_1}
                                                                    </td>
                                                                </tr>
                                                            </c:if>
                                                            <c:if test="${item.nombre_apellidos_tutor_2!=null&&item.nombre_apellidos_tutor_2!=' '&&item.nombre_apellidos_tutor_2!='  '}">
                                                                <tr>
                                                                    <td><strong>Tutor 2 :</strong></td>
                                                                    <td style="color:navy">
                                                                        <strong>
                                                                            ${item.nombre_apellidos_tutor_2}
                                                                            <c:if test="${item.ci_tutor_2==item.ci_resp_pago}">
                                                                                <font style="color:red">(Resp. pago)</font>
                                                                            </c:if>
                                                                        </strong>
                                                                        ,  CI: ${item.ci_tutor_2}
                                                                    </td>
                                                                </tr>
                                                            </c:if>
                                                            <c:if test="${item.nombre_apellidos_tutor_3!=null&&item.nombre_apellidos_tutor_3!=' '&&item.nombre_apellidos_tutor_3!='  '}">
                                                                <tr>
                                                                    <td><strong>Tutor 3 :</strong></td>
                                                                    <td style="color:navy">
                                                                        <strong>
                                                                            ${item.nombre_apellidos_tutor_3}
                                                                            <c:if test="${item.ci_tutor_3==item.ci_resp_pago}">
                                                                                <font style="color:red">(Resp. pago)</font>
                                                                            </c:if>
                                                                        </strong>
                                                                        ,  CI: ${item.ci_tutor_3}
                                                                    </td>
                                                                </tr>
                                                            </c:if>
                                                            <tr>
                                                                <td><strong>Direcci&oacute;n1 :</strong></td>
                                                                <td style="color:navy">${item.direccion_1}</td>
                                                            </tr>
                                                            <tr>
                                                                <td><strong>Direcci&oacute;n2 :</strong></td>
                                                                <td style="color:navy">${item.direccion_2}</td>
                                                            </tr>
                                                            <tr>
                                                                <td><strong>Tel&eacute;fono1 :</strong></td>
                                                                <td style="color:navy">${item.telefono_1}</td>
                                                            </tr>
                                                            <tr>
                                                                <td><strong>Tel&eacute;fono2 :</strong></td>
                                                                <td style="color:navy">${item.telefono_2}</td>
                                                            </tr>
                                                            <tr>
                                                                <td><strong>Tel&eacute;fonos :</strong></td>
                                                                <td style="color:navy">${item.telefonos}</td>
                                                            </tr>
                                                            <tr>
                                                                <td><strong>E-mail :</strong></td>
                                                                <td style="color:navy">${item.e_mail}</td>
                                                            </tr>
                                                            <tr>
                                                                <td><strong>E-mail(RP):</strong></td>
                                                                <td style="color:navy">${item.e_mailRP}</td>
                                                            </tr>
                                                            <tr>
                                                                <td><strong>Obs:</strong></td>
                                                                <td style="color:navy">${item.observacion}</td>
                                                            </tr>
                                                        </table>
                                                    </td>
                                                    <td class="gridData" valign="top">
                                                        <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                                                            <c:forEach varStatus="i" var="it" items="${item.estudiantes}">
                                                                <tr>
                                                                    <td style="color:navy">
                                                                        ${i.count} .- ${it.nombres} ${it.paterno} ${it.materno}
                                                                        <span style="color: teal">(${it.curso} de ${it.ciclo})</span>
                                                                    </td>
                                                                </tr>
                                                            </c:forEach>
                                                        </table>
                                                    </td>
                                                </tr>
                                            </c:if>
                                        </c:forEach>
                                    </table>
                                </div>
                            </td>
                        </tr>
                    </table>
                </c:if>                
            </c:if>
        </div>
    </body>
</html>