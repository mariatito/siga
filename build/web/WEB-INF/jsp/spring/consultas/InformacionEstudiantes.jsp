<%-- 
    Document   : InformacionEstudiantes
    Created on : 02-jul-2010, 14:43:08
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
            animatedcollapse.addDiv('success', 'fade=1');
            animatedcollapse.addDiv('failure', 'fade=1');
            animatedcollapse.init();
        </script>

        <script type="text/javascript">
            function openWindow(form,title,w,h){
                var addFormwin=dhtmlmodal.open('win'+form, 'div', form, title, 'width='+w+'px,height='+h+'px,left=100px,top=100px,resize=1,scrolling=1');
                addFormwin.moveTo('middle', 'middle');
                return addFormwin;
            }
            function onRowover(elem) {
                elem.className='colover';
            }
            function onRowout(elem) {
                elem.className='colout';
            }
            function selectGestion(id_gestion){
                window.location = '<c:url value="/informacionEstudiantes.do"/>?id_gestion='+id_gestion;
            }
            function setImprimirResultado(){
                var printResultado=dhtmlmodal.open('windowMAQV', 'iframe', '<c:url value="/informacionEstudiantes.do"/>?&imprimirPdf=_imprimirPdf&buscar=${buscar}&id_gestion=${id_gestion}', 'Lista de resultados', 'width=650px,height=340px,left=100px,top=100px,resize=1,scrolling=1,center=1');
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
                                <td class="tab_current">Informaci&oacute;n del alumnado</td>
                                <td class="tab_normal" onclick="javascript:window.location='<c:url value="/informacionEstudiantesCurso.do"/>'">Informaci&oacute;n del alumnado por curso</td>
                                <td class="tab_normal" onclick="javascript:window.location='<c:url value="/informacionFamilias.do"/>'">Informaci&oacute;n de familias</td>
                            </tr>
                        </table>
                    </td>
                    <td class="tab_fin">&nbsp;</td>
                </tr>
            </table>
            <div class="titlepage">INFORMACI&Oacute;N DEL ALUMNADO ${id_gestion}</div>
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
            <c:if test="${!empty search}">
                <table  style="cursor: pointer" >
                    <form action="<c:url value="/informacionEstudiantes.do"/>" method="post" name="frmSeach">
                        <td class="text-label">Buscar estudiante</td>
                        <td class="text-label"><input type="text" name="buscar" value="${buscar}" class="text-field"></td>
                        <td class="text-label"><img src="imagenes/iconos_sigaa/buscar.gif" border="0" width="10px">&nbsp;&nbsp;
                            <input type="hidden" name="opcion" value="_buscar">
                            <button class="button-normal" type="submit" onclick=""><img src="imagenes/iconos_sigaa/buscar.png" border="0" width="10px">&nbsp;&nbsp;Buscar...</button>
                            <input type="hidden" name="id_gestion" value="${id_gestion}">
                        </td>
                    </form>
                </table>
                <table cellpadding="0" cellspacing="0" border="0" style="width:100%; cursor: pointer" class="gridContent">
                    <tr>
                        <td class="tableHeader">Resultados de la b&uacute;squeda</td>
                    </tr>
                    <c:if test="${!empty estudiantes}">
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
                        <td>
                            <div id="resultlayer1">
                                <table width="100%" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF">
                                    <tr>
                                        <td class="gridHeaders" style="width:3%">Nro.</td>
                                        <td class="gridHeaders" style="width:97%">Nombres y apellidos</td>
                                    </tr>
                                    <c:if test="${empty estudiantes}">
                                        <tr>
                                            <td class="gridData" colspan="3">No se han encontrado registros.</td>
                                        </tr>
                                    </c:if>
                                    <c:forEach varStatus="i" var="item" items="${estudiantes}">
                                        <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                            <td class="gridData" valign="top"><c:out value="${i.count}"/> </td>
                                            <td class="gridData"><strong>${item.paterno} ${item.materno} ${item.nombres}</strong>
                                                <table width="100%">
                                                    <tr>
                                                        <td width="45%" valign="top">
                                                            <table width="100%" cellpadding="0" cellspacing="0" border="0" class="menuHeader">
                                                                <tr>
                                                                    <td colspan="4" class="tableHeader"><strong>Informaci&oacute;n del estudiante</strong></td>
                                                                </tr>
                                                                <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                                                    <td width="33%">C&Oacute;DIGO</td>
                                                                    <td width="2%">:</td>
                                                                    <td width="40%">${item.codigo}</td>
                                                                    <td width="25%" rowspan="7" align="center">
                                                                        <c:if test="${item.nombre_foto!=null}"><img src="documentos/fotos/${item.nombre_foto}" border="0" width="90px"></c:if>
                                                                        <c:if test="${item.nombre_foto==null}"><img src="imagenes/iconos_sigaa/ojo_marco.gif" border="0" width="70px"></c:if>
                                                                    </td>
                                                                </tr>
                                                                <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                                                    <td>NOMBRE</td>
                                                                    <td>:</td>
                                                                    <td>${item.nombres}</td>
                                                                </tr>
                                                                <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                                                    <td>PATERNO</td>
                                                                    <td>:</td>
                                                                    <td>${item.paterno}</td>
                                                                </tr>
                                                                <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                                                    <td>MATERNO</td>
                                                                    <td>:</td>
                                                                    <td>${item.materno}</td>
                                                                </tr>
                                                                <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                                                    <td>SEXO</td>
                                                                    <td>:</td>
                                                                    <td>
                                                                        <c:if test="${item.id_sexo=='A'}"></c:if>
                                                                        <c:if test="${item.id_sexo=='F'}">FEMENINO</c:if>
                                                                        <c:if test="${item.id_sexo=='M'}">MASCULINO</c:if>
                                                                    </td>
                                                                </tr>
                                                                <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                                                    <td>FECHA DE NACIMIENTO</td>
                                                                    <td>:</td>
                                                                    <td>${item.sfecha_nacimiento}</td>
                                                                </tr>
                                                                <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                                                    <td>CURSO</td>
                                                                    <td>:</td>
                                                                    <td>${item.curso} de ${item.ciclo}</td>
                                                                </tr>
                                                                <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                                                    <td>ESTADO</td>
                                                                    <td>:</td>
                                                                    <td>
                                                                        <c:if test="${item.id_estado=='A'}">ACTIVO</c:if>
                                                                        <c:if test="${item.id_estado=='R'}">RETIRADO</c:if>
                                                                        <c:if test="${item.id_estado=='NI'}">NO INCORPORADO</c:if>
                                                                    </td> 
                                                                </tr>
                                                            </table>
                                                            <br>
                                                            <!--table width="100%" cellpadding="0" cellspacing="0" border="0" align="center">
                                                                <tr>
                                                                    <td width="50%" align="center">
                                                                        <button class="button-normal" onclick="" title="Imprimir informaci&oacute;n actual"><img src="imagenes/iconos_sigaa/print.gif" border="0" width="10px">&nbsp;&nbsp;Imprimir...</button>
                                                                    </td>
                                                                    <td width="50%" align="center">
                                                                        <button class="button-normal" onclick="" title="M&aacute;s informaci&oacute;n de la familia"><img src="imagenes/iconos_sigaa/mas.png" border="0" width="8px">&nbsp;&nbsp;M&aacute;s informaci&oacute;n...</button>
                                                                    </td>
                                                                </tr>
                                                            </table-->
                                                        </td>
                                                        <td width="55%" valign="top">
                                                            <table width="100%" cellpadding="0" cellspacing="0" border="0" class="menuHeader">
                                                                <tr>
                                                                    <td colspan="4" class="tableHeader"><strong>Informaci&oacute;n de la familia</strong></td>
                                                                </tr>
                                                                <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                                                    <td width="10%">C&Oacute;D. FAMILIA : </td>
                                                                    <td width="30%" class="textColor">${item.familia.id_familia}</td>
                                                                    <td width="3%"></td>
                                                                    <td width="7%"></td>
                                                                </tr>
                                                                <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                                                    <td>Tutor (1) :</td>
                                                                    <td class="textColor">${item.familia.nombre_apellidos_tutor_1} <c:if test="${item.familia.ci_tutor_1==item.familia.ci_resp_pago}"><font style="color:red">(Resp. pago)</font></c:if></td>
                                                                    <td>C.I. :</td>
                                                                    <td  class="textColor">${item.familia.ci_tutor_1}</td>
                                                                </tr>
                                                                <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                                                    <td>Tutor (2) :</td>
                                                                    <td class="textColor">${item.familia.nombre_apellidos_tutor_2} <c:if test="${item.familia.ci_tutor_2==item.familia.ci_resp_pago}"><font style="color:red">(Resp. pago)</font></c:if></td>
                                                                    <td>C.I. :</td>
                                                                    <td class="textColor">${item.familia.ci_tutor_2}</td>
                                                                </tr>
                                                                <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                                                    <td>Tutor (3) :</td>
                                                                    <td class="textColor">${item.familia.nombre_apellidos_tutor_3} <c:if test="${item.familia.ci_tutor_3==item.familia.ci_resp_pago}"><font style="color:red">(Resp. pago)</font></c:if></td>
                                                                    <td>C.I. :</td>
                                                                    <td class="textColor">${item.familia.ci_tutor_3}</td>
                                                                </tr>
                                                                <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                                                    <td>Direcci&oacute;n (1) :</td>
                                                                    <td class="textColor">${item.familia.direccion_1}</td>
                                                                    <td></td>
                                                                    <td></td>
                                                                </tr>
                                                                <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                                                    <td>Direcci&oacute;n (2) :</td>
                                                                    <td class="textColor">${item.familia.direccion_2}</td>
                                                                    <td></td>
                                                                    <td></td>
                                                                </tr>
                                                                <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                                                    <td>Tel&eacute;fono (1) :</td>
                                                                    <td class="textColor">${item.familia.telefono_1}</td>
                                                                    <td></td>
                                                                    <td></td>
                                                                </tr>
                                                                <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                                                    <td>Tel&eacute;fono (2) :</td>
                                                                    <td class="textColor">${item.familia.telefono_2}</td>
                                                                    <td></td>
                                                                    <td></td>
                                                                </tr>
                                                                <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                                                    <td>Otros telefonos :</td>
                                                                    <td class="textColor" colspan="3">${item.familia.telefonos}</td>
                                                                </tr>
                                                                <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                                                    <td>NIT: </td>
                                                                    <td class="textColor" colspan="3">${item.familia.e_mail}</a></td>
                                                                </tr>
                                                                <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                                                    <td>E-mail resp. pago : </td>
                                                                    <td class="textColor" colspan="3"><a href="mailto:${item.familia.e_mailRP}">${item.familia.e_mailRP}</a></td>
                                                                </tr>
                                                                <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                                                    <td>Observaci&oacute;n :</td>
                                                                    <td class="textColor" colspan="3">${item.familia.observacion}</td>
                                                                </tr>
                                                            </table>
                                                        </td>
                                                    </tr>
                                                </table>                                                
                                            </td>
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
