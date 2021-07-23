<%-- 
    Document   : DatosPersonales
    Created on : 02-dic-2009, 10:09:03
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
            <div class="titlepage">Mis datos personales</div>
            <table id="tabs_menu" cellpadding="0" cellspacing="0" border="0" style="width:100%">
                <tr>
                    <td class="tab_init">&nbsp;</td>
                    <td style="width:50%">
                        <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                            <tr>
                                 <td class="tab_normal" onclick="javascript:window.location='<c:url value="/misFechaExamenes.do"/>'">Mis materias y fechas de evaluaciones</td>
                                <td class="tab_normal" onclick="javascript:window.location='<c:url value="/misCalificaciones.do"/>'">Mis calificaciones</td>
                                <td class="tab_current" style="border-right:1px solid #7D909E">Datos personales</td>
                            </tr>
                        </table>
                    </td>
                    <td class="tab_fin">&nbsp;</td>
                </tr>
            </table>
        </div>
        <div class="maincontent">
            <table cellpadding="0" cellspacing="0" border="0" style="width: 100% ; cursor:pointer">
                <tr>
                    <td class="tableHeader" colspan="4" align="center"><strong>INFORMACI&Oacute;N DE LA FAMILIA</strong></td>
                </tr>
                <tr bgcolor="#ffffff" style="cursor:pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                    <td width="10%">C&Oacute;D. FAMILIA : </td>
                    <td width="30%" class="textColor">${family.id_familia}</td>
                    <td width="3%"></td>
                    <td width="7%"></td>
                </tr>
                <tr bgcolor="#ffffff" style="cursor:pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                    <td>Tutor (1) :</td>
                    <td class="textColor">${family.nombre_apellidos_tutor_1} <c:if test="${family.ci_tutor_1==family.ci_resp_pago}"><font style="color:red">(Resp. pago)</font></c:if></td>
                    <td>C.I. :</td>
                    <td  class="textColor">${family.ci_tutor_1}</td>
                </tr>
                <tr bgcolor="#ffffff" style="cursor:pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                    <td>Tutor (2) :</td>
                    <td class="textColor">${family.nombre_apellidos_tutor_2} <c:if test="${family.ci_tutor_2==family.ci_resp_pago}"><font style="color:red">(Resp. pago)</font></c:if></td>
                    <td>C.I. :</td>
                    <td class="textColor">${family.ci_tutor_2}</td>
                </tr>
                <tr bgcolor="#ffffff" style="cursor:pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                    <td>Tutor (3) :</td>
                    <td class="textColor">${family.nombre_apellidos_tutor_3} <c:if test="${family.ci_tutor_3==family.ci_resp_pago}"><font style="color:red">(Resp. pago)</font></c:if></td>
                    <td>C.I. :</td>
                    <td class="textColor">${family.ci_tutor_3}</td>
                </tr>
                <tr bgcolor="#ffffff" style="cursor:pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                    <td>Direcci&oacute;n (1) :</td>
                    <td class="textColor">${family.direccion_1}</td>
                    <td></td>
                    <td></td>
                </tr>
                <tr bgcolor="#ffffff" style="cursor:pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                    <td>Direcci&oacute;n (2) :</td>
                    <td class="textColor">${family.direccion_2}</td>
                    <td></td>
                    <td></td>
                </tr>
                <tr bgcolor="#ffffff" style="cursor:pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                    <td>Tel&eacute;fono (1) :</td>
                    <td class="textColor">${family.telefono_1}</td>
                    <td></td>
                    <td></td>
                </tr>
                <tr bgcolor="#ffffff" style="cursor:pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                    <td>Tel&eacute;fono (2) :</td>
                    <td class="textColor">${family.telefono_2}</td>
                    <td></td>
                    <td></td>
                </tr>
                <tr bgcolor="#ffffff" style="cursor:pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                    <td>Otros telefonos :</td>
                    <td class="textColor" colspan="3">${family.telefonos}</td>
                </tr>
                <tr bgcolor="#ffffff" style="cursor:pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                    <td>E-mail tutor : </td>
                    <td class="textColor" colspan="3"><a href="mailto:${family.e_mail}">${family.e_mail}</a></td>
                </tr>
                <tr bgcolor="#ffffff" style="cursor:pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                    <td>E-mail resp. pago : </td>
                    <td class="textColor" colspan="3"><a href="mailto:${family.e_mailRP}">${family.e_mailRP}</a></td>
                </tr>
                <tr bgcolor="#ffffff" style="cursor:pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                    <td>Observaci&oacute;n :</td>
                    <td class="textColor" colspan="3">${family.observacion}</td>
                </tr>
                <tr>
                    <td colspan="4">
                        <table border="0" cellpadding="0" cellspacing="0" width="100%" class="gridContent">
                            <tr>
                                <td align="center"colspan="3"><strong><h2>DATOS PERSONALES</h2></strong></td>
                            </tr>
                            <tr>
                                <td width="20%">C&Oacute;DIGO<img src="imagenes/icons/required.gif"/></td>
                                <td width="50%"><input class="text-field" type="text" name="codigo" value="${estudiante.codigo}" maxlength="5" style="width:50px" onfocus="this.blur()"/></td>
                                <td width="30%" rowspan="11"><img src="documentos/fotos/${estudiante.codigo}-${gestion.id_gestion}.jpg" height="150px"/></td>
                            </tr>
                            <tr>
                                <td colspan="2"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                            </tr>
                            <tr>
                                <td>NOMBRE(S) <img src="imagenes/icons/required.gif"/> : </td>
                                <td><input class="text-field" type="text" name="nombres" value="${estudiante.nombres}" maxlength="70" style="width:250px" onfocus="this.blur()"/></td>
                            </tr>
                            <tr>
                                <td colspan="2"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                            </tr>
                            <tr>
                                <td>APELLIDO PATERNO <img src="imagenes/icons/required.gif"/> : </td>
                                <td><input class="text-field" type="text" name="paterno" value="${estudiante.paterno}" maxlength="70" style="width:250px" onfocus="this.blur()"/></td>
                            </tr>
                            <tr>
                                <td colspan="2"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                            </tr>
                            <tr>
                                <td>APELLIDO MATERNO <img src="imagenes/icons/required.gif"/> : </td>
                                <td><input class="text-field" type="text" name="materno" value="${estudiante.materno}" maxlength="70" style="width:250px" onfocus="this.blur()"/></td>
                            </tr>
                            <tr>
                                <td colspan="2"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                            </tr>                            
                            <tr>
                                <td>SEXO : </td>
                                <td>
                                    <select name="id_sexo" class="text-field" style="width:120px" onfocus="this.blur()">
                                        <c:forEach var="item" items="${tipos_sexos}">
                                            <option value="${item.id}"<c:if test="${item.id == estudiante.id_sexo  }"> selected </c:if>> ${item.valor}
                                            </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                            </tr>
                            <tr>
                                <td>FECHA DE NACIMIENTO : </td>
                                <td valign="top">
                                    <select name="dia" class="text-field" style="width:40px" onfocus="this.blur()">
                                        <option>
                                            <c:forEach begin="1" end="31" varStatus="i">
                                            <option value="${i.count}" <c:if test="${i.count == estudiante.fecha_nacimiento.date}"> selected </c:if>>${i.count}
                                            </c:forEach>
                                    </select>
                                    <select name="mes" class="text-field" style="width:105px" onfocus="this.blur()">
                                        <option>
                                            <c:forEach var="item" items="${tipo_meses}">
                                            <option value="${item.id}" <c:if test="${item.id == (estudiante.fecha_nacimiento.month+1)}"> selected </c:if>>${item.valor}
                                            </c:forEach>
                                    </select>
                                    <select name="anio" class="text-field" style="width:55px" onfocus="this.blur()">
                                        <option>
                                            <c:forEach begin="1950" end="2020" varStatus="i">
                                            <option value="${i.index}" <c:if test="${i.index == (estudiante.fecha_nacimiento.year+1900)}"> selected </c:if>>${i.index}
                                            </c:forEach>
                                    </select>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>

            </table>
        </div>
    </body>
</html>
