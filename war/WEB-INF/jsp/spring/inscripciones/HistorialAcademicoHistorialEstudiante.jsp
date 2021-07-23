<%-- 
    Document   : HistorialAcademicoHistorialEstudiante
    Created on : 08-02-2010, 03:05:11 PM
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
            function setUpdateEstudiante(){
                $('updateEstudiante').style.display = 'inline';
            }
            function cancelarAccion(){
                $('updateEstudiante').style.display = 'none';
            }
        </script>

    </head>
    <body>
        <div class="headercontent">
            <table style="width:100%" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="menuHead"><h2>Historial de ${estudiante.nombres} ${estudiante.paterno} ${estudiante.materno}</h2></td>
                </tr>
            </table>
        </div>
        <div class="maincontent">
            <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                <tr>
                    <td align="center" colspan="2" class="tableHeader">INFORMACI&Oacute;N GENERAL (${estudiante.id_gestion})</td>
                </tr>
                <tr>
                    <td width="70%" valign="top" class="gridContent">
                        <table cellpadding="0" cellspacing="0" border="0" style="width:100%" class="menuHead">
                            <tr>
                                <td colspan="4" class="tableHeader" align="center">Tutor o tutores</td>
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
                            <!--tr>
                                <td class="textColor" colspan="4" align="center"><input class="button-submit" type="submit" name="est_nue" onclick="updateFamilia()" value="Editar familia"></td>
                            </tr-->
                        </table>
                    </td>
                    <td width="30%" valign="top" class="gridContent">
                        <table cellpadding="0" cellspacing="0" border="0" style="width:100%" class="menuHead">
                            <tr>
                                <td class="tableHeader" align="center">Fotografia actual del estudiante</td>
                            </tr>
                            <tr>
                                <td align="center" style="color:red"><c:if test="${estudiante.nombre_foto!=null}"><img src="documentos/fotos/${estudiante.nombre_foto}" height="150px"/></c:if><c:if test="${estudiante.nombre_foto=='' || estudiante.nombre_foto==null}"><img src="imagenes/iconos_sigaa/ojo_marco.gif" height="150px"/></c:if></td>
                            </tr>
                            <tr>
                                <td align="center"><input class="button-submit" type="submit" name="est_nue" onclick="setUpdateEstudiante()" value="Actualizar informaci&oacute;n"></td>
                            </tr>
                            <tr>
                                <td align="center"><input class="button-submit" type="submit" name="est_nue" onclick="verPensiones()" value="Historial de pagos"></td>
                            </tr>
                            <tr>
                                <td align="center"><input class="button-submit" type="submit" name="est_nue" onclick="verHistorial()" value="Historial acad&eacute;mico"></td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
            <div id="updateEstudiante"  style="display:none">
                <form action="<c:url value="/historialAcademico.do"/>" method="post" enctype="multipart/form-data">
                    <table border="0" cellpadding="0" cellspacing="0" width="100%" class="gridContent">
                        <tr>
                            <td class="tableHeader" colspan="2" align="center">ACTUALIZAR DATOS DEL ESTUDIANTE</td>
                        </tr>
                        <tr>
                            <td align="center"colspan="2"><p><strong>INFORMACI&Oacute;N DEL ESTUDIANTE</strong></p></td>
                        </tr>
                        <tr>
                            <td width="20%">C&Oacute;DIGO<img src="imagenes/icons/required.gif"/></td>
                            <td width="80%"><input class="text-field" type="text" name="codigo" value="${estudiante.codigo}" maxlength="5" style="width:50px"/></td>
                        </tr>
                        <tr>
                            <td colspan="2"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                        </tr>
                        <tr>
                            <td>CEDULA DE IDENTIDAD: </td>
                            <td><input class="text-field" type="text" name="carnet" value="${estudiante.carnet}" maxlength="10" style="width:80px"/></td>
                        </tr>
                        <tr>
                            <td colspan="2"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                        </tr>
                        <tr>
                            <td>NOMBRE(S) <img src="imagenes/icons/required.gif"/> : </td>
                            <td><input class="text-field" type="text" name="nombres" value="${estudiante.nombres}" maxlength="70" style="width:250px"/></td>
                        </tr>
                        <tr>
                            <td colspan="2"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                        </tr>
                        <tr>
                            <td>APELLIDO PATERNO <img src="imagenes/icons/required.gif"/> : </td>
                            <td><input class="text-field" type="text" name="paterno" value="${estudiante.paterno}" maxlength="70" style="width:250px"/></td>
                        </tr>
                        <tr>
                            <td colspan="2"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                        </tr>
                        <tr>
                            <td>APELLIDO MATERNO <img src="imagenes/icons/required.gif"/> : </td>
                            <td><input class="text-field" type="text" name="materno" value="${estudiante.materno}" maxlength="70" style="width:250px"/></td>
                        </tr>
                        <tr>
                            <td colspan="2"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                        </tr>
                        <tr>
                            <td>DIR. FOTOGRAFIA<img src="imagenes/icons/required.gif"/> : </td>
                            <td><input class="button-normal" type="file" size="30" name="archivo"></td>
                        </tr>
                        <tr>
                            <td colspan="2"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                        </tr>
                        <tr>
                            <td>SEXO : </td>
                            <td>
                                <select name="id_sexo" class="text-field" style="width:120px">
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
                                <select name="dia" class="text-field" style="width:40px">
                                    <option>
                                        <c:forEach begin="1" end="31" varStatus="i">
                                        <option value="${i.count}" <c:if test="${i.count == estudiante.fecha_nacimiento.date}"> selected </c:if>>${i.count}
                                        </c:forEach>
                                </select>
                                <select name="mes" class="text-field" style="width:105px">
                                    <option>
                                        <c:forEach var="item" items="${tipo_meses}">
                                        <option value="${item.id}" <c:if test="${item.id == (estudiante.fecha_nacimiento.month+1)}"> selected </c:if>>${item.valor}
                                        </c:forEach>
                                </select>
                                <select name="anio" class="text-field" style="width:55px">
                                    <option>
                                        <c:forEach begin="1950" end="2020" varStatus="i">
                                        <option value="${i.index}" <c:if test="${i.index == (estudiante.fecha_nacimiento.year+1900)}"> selected </c:if>>${i.index}
                                        </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                        </tr>
                        <tr>
                            <td>CURSO : </td>
                            <td>
                                <select id="unico_id_curso" name="id_curso" class="text-field" style="width:250px"  onchange="selectCurso(this.value)" onfocus="this.blur()">
                                    <c:forEach var="item" items="${cursos}">
                                        <option value="${item.id_curso}"<c:if test="${item.id_curso == estudiante.id_curso  }"> selected </c:if>> ${item.curso} de ${item.ciclo}
                                        </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                        </tr>
                        <tr>
                            <td>GONDOLA : </td>
                            <td>
                                <select name="id_gondola" class="text-field" style="width:500px">
                                    <option value="">Ninguno...
                                        <c:forEach var="item" items="${gondolas}">
                                        <option value="${item.placa}"<c:if test="${item.placa==insc.id_gondola}"> selected </c:if>>${item.empresa}, Nro. ${item.nro_gondola} y Ruta(${item.ruta})
                                        </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                        </tr>
                        <tr>
                            <td>GESTI&Oacute;N : </td>
                            <td><input class="text-field" type="text" name="id_gestion" value="${estudiante.id_gestion}" maxlength="5" style="width:120px" onfocus="this.blur()"/></td>
                        </tr>
                        <tr>
                            <td colspan="2"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                        </tr>
                        <tr>
                            <td colspan="2" align="center">
                                <input type="hidden" name="id_familia" value="${family.id_familia}">
                                <input type="hidden" name="id_estudiante" value="${estudiante.id_estudiante}">
                                <input type="hidden" name="id_persona" value="${estudiante.id_persona}">
                                <input name="guardar" class="button-submit" type="submit" value="Guardar cambios"/>
                                <input type="button" class="button-submit" onclick="cancelarAccion()" name="cancel" value="Cancelar">
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </body>
</html>
