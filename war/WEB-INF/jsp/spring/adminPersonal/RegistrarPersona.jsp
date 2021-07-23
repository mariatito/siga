<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%--
    Document   : RegistrarPersona
    Created on : 14-may-2009, 13:29:22
    Author     : Marco Antonio Quenta Velasco
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
        <link rel="stylesheet" type="text/css" href="css/module.css" media="screen" />
        <script type="text/javascript">
            animatedcollapse.addDiv('success', 'fade=1');
            animatedcollapse.addDiv('failure', 'fade=1');
            animatedcollapse.init();
        </script>
        <script type="text/javascript">

            function setRegistrar(form) {
                form.guardar.disabled = true;
                form.guardar.value = 'Enviando los datos...';
                form.submit();
                /*si los datos no son correctos */
                return false
            }

            function setCargoAcademico(obj) {
                if(obj.value == 'A') {
                    document.getElementById('item-detalle').style.display = 'inline';
                    document.getElementById('item-administrador').style.display = 'none';
                    document.getElementById('item-docente').style.display = 'none';
                    document.getElementById('item-system').style.display = 'none';
                }
                if(obj.value == 'ADM') {
                    document.getElementById('item-detalle').style.display = 'none';
                    document.getElementById('item-administrador').style.display = 'inline';
                    document.getElementById('item-docente').style.display = 'none';
                    document.getElementById('item-system').style.display = 'none';
                }
                if(obj.value == 'DOC') {
                    document.getElementById('item-detalle').style.display = 'none';
                    document.getElementById('item-administrador').style.display = 'none';
                    document.getElementById('item-docente').style.display = 'inline';
                    document.getElementById('item-system').style.display = 'none';
                }
                if(obj.value == 'SYS') {
                    document.getElementById('item-detalle').style.display = 'none';
                    document.getElementById('item-administrador').style.display = 'none';
                    document.getElementById('item-docente').style.display = 'none';
                    document.getElementById('item-system').style.display = 'inline';
                }
            }

            function openDialogWindow(form,title,w,h){
                var addFormwin=dhtmlmodal.open('win'+form, 'div', form, title, 'width='+w+'px,height='+h+'px,left=100px,top=100px,resize=0,scrolling=0');
                addFormwin.moveTo('middle', 'middle');
                return addFormwin;
            }

            var confirmwin = null;
            function finalizarReg() {
                confirmwin = openDialogWindow('finalizarRegistro','Finalizar...','400','120');
            }

            function cancelReg() {
                confirmwin.hide();
            }

            function confirmReg() {
                document.MiForm.submit()
            }

        </script>


    </head>
    <body>
        <div class="headercontent">
            <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                <tr>
                    <td class="tab_init">&nbsp;</td>
                    <td style="width:50%">
                        <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                            <td class="tab_current">Registro &uacute;nico</td>
                            <td class="tab_normal" onclick="javascript:window.location='<c:url value="/actualizarDatosPersona.do"/>'">Actualizar datos</td>
                            <td class="tab_normal" onclick="javascript:window.location='<c:url value="/buscarPersonas.do"/>'">B&uacute;squeda y eliminaci&oacute;n</td>
                        </table>
                    </td>
                    <td class="tab_fin">&nbsp;</td>
                </tr>
            </table>
            <div class="titlepage">REGISTRO &Uacute;NICO DEL PERSONAL</div>
        </div>
        <div class="maincontent">
            <div id="failure" style="position: absolute; width: 300px; border: 1px solid red; padding: 0px; background-color: #FFC1C1; display: none">
                <table>
                    <td><img src="imagenes/iconos_sigaa/activo_pe.png" border="0" width="15px"></td>
                    <td><span style="color: #000000; font-size: 10pt; font-weight: lighter">&nbsp;Datos inv&aacute;lidos, intente de nuevamente.</span></td>
                </table>
            </div>

            <div id="success" style="position: absolute; width: 300px; border: 1px solid green; padding: 0px; background-color: #DEF5E6; display: none">
                <table>
                    <td><img src="imagenes/iconos_sigaa/activo_si.png" border="0" width="15px"></td>
                    <td><span style="color: #000000; font-size: 10pt; font-weight: lighter">&nbsp;Datos registrado correctamente.</span></td>
                </table>
            </div>

            <div align="center">
                <form action="<c:url value="/registrarPersona.do"/>" method="post" name="MiForm" onsubmit="return setRegistrar(this)">
                    <table width="80%" border="0" cellpadding="0" cellspacing="0" class="gridContent" align="center">
                        <tr>
                            <td align="center" colspan="7"><p><strong>REGISTRO &Uacute;NICO DEL PERSONAL</strong></p></td>
                        </tr>
                        <tr>
                            <td width="14%"></td>
                            <td colspan="1">NOMBRE(S)</td>
                            <td colspan="1">:</td>
                            <td colspan="4"><input class="text-field" type="text" name="nombres" value="${nombres}" maxlength="70" style="width:250px"/></td>
                        </tr>
                        <tr>
                            <td colspan="7"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                        </tr>
                        <tr>
                            <td width="14%"></td>
                            <td colspan="1">APELLIDO PATERNO</td>
                            <td colspan="1">:</td>
                            <td colspan="4"><input class="text-field" type="text" name="paterno" value="${paterno}" maxlength="70" style="width:250px"/></td>
                        </tr>
                        <tr>
                            <td colspan="7"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                        </tr>
                        <tr>
                            <td width="14%"></td>
                            <td colspan="1">APELLIDO MATERNO</td>
                            <td colspan="1">:</td>
                            <td colspan="4"><input class="text-field" type="text" name="materno" value="${materno}" maxlength="70" style="width:250px"/></td>
                        </tr>
                        <tr>
                            <td colspan="7"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                        </tr>
                        <tr>
                            <td width="14%"></td>
                            <td width="23%">C.I.</td>
                            <td width="2%">:</td>
                            <td colspan="4"><input class="text-field" type="text" name="ci" maxlength="10" value="${ci}" style="width:120px"/></td>
                        </tr>
                        <tr>
                            <td colspan="7"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                        </tr>
                        <tr>
                            <td width="14%"></td>
                            <td width="23%">DIRECCION</td>
                            <td width="2%">:</td>
                            <td colspan="4"><input class="text-field" type="text" name="direccion" maxlength="250" value="${direccion}" style="width:250px"/></td>
                        </tr>
                        <tr>
                            <td colspan="7"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                        </tr>
                        <tr>
                            <td width="14%"></td>
                            <td width="23%">TELEFONO(1)</td>
                            <td width="2%">:</td>
                            <td colspan="4"><input class="text-field" type="text" name="telefono1" maxlength="10" value="${telefono1}" style="width:120px"/></td>
                        </tr>
                        <tr>
                            <td colspan="7"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                        </tr>
                        <tr>
                            <td width="14%"></td>
                            <td width="23%">TELEFONO (2)</td>
                            <td width="2%">:</td>
                            <td colspan="4"><input class="text-field" type="text" name="telefono2" maxlength="10" value="${telefono2}" style="width:120px"/></td>
                        </tr>
                        <tr>
                            <td colspan="7"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                        </tr>
                        <tr>
                            <td width="14%"></td>
                            <td width="23%">SEXO</td>
                            <td width="2%">:</td>
                            <td colspan="4">
                                <select name="id_sexo" id="item-sexo" class="text-field" style="width:120px">
                                    <option value="A">
                                        <c:forEach var="item" items="${tipos_sexos}">
                                        <option value="${item.id}"<c:if test="${item.id == id_sexo  }"> selected </c:if>> ${item.valor}
                                        </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="7"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                        </tr>
                        <tr>
                            <td width="14%"></td>
                            <td width="23%">CARGO ACAD&Eacute;MICO</td>
                            <td width="2%">:</td>
                            <td colspan="4">
                                <select name="id_tipo_usuario" id="item-cargo" class="text-field" style="width:120px" onchange="setCargoAcademico(this)">
                                    <option value="A">
                                        <c:forEach var="item" items="${cargos_academicos}">
                                            <c:if test="${item.id != 'EST'}">
                                            <option value="${item.id}" <c:if test="${item.id==id_tipo_usuario}"> selected </c:if>> ${item.valor}
                                            </c:if>
                                        </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="7"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                        </tr>
                        <tr>
                            <td colspan="7" align="right">
                                <div id="item-detalle" style="display:inline">
                                    <table width="795" border="0">
                                        <tr>
                                            <td colspan="7" align="center"></td>
                                        </tr>
                                    </table>
                                </div>
                                <div id="item-administrador" style="display:none">
                                    <table width="795" border="0" >
                                        <tr>
                                            <td width="14%"></td>
                                            <td width="23%">FECHA DE INGRESO</td>
                                            <td width="2%">:</td>
                                            <td colspan="4">
                                                <select name="dia" class="text-field" style="width:45px">
                                                    <c:forEach begin="1" end="31" varStatus="i">
                                                        <option <c:if test="${i.count==fecha_actual.date}"> Selected="selected" </c:if>>${i.count}
                                                        </c:forEach>
                                                </select>
                                                <select name="mes" class="text-field" style="width:90px">
                                                    <c:forEach var="itemm" items="${meses}">
                                                        <option value="${itemm.id}" <c:if test="${itemm.id==(fecha_actual.month+1)}"> Selected="selected" </c:if>>${itemm.valor}
                                                        </c:forEach>
                                                </select>
                                                <select name="anio" class="text-field" style="width:60px">
                                                    <c:forEach begin="2008" end="2020" varStatus="j">
                                                        <option <c:if test="${j.index==(fecha_actual.year+1900)}"> Selected="selected" </c:if>>${j.index}
                                                        </c:forEach>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td width="14%"></td>
                                            <td width="23%">T&Iacute;TULO PROFESIONAL</td>
                                            <td width="2%">:</td>
                                            <td colspan="4">
                                                <select name="id_titulo_adm" id="item-titulo_adm" class="text-field" style="width:200px" >
                                                    <c:forEach var="item" items="${titulos_profesionales}">
                                                        <option value="${item.id}" <c:if test="${item.id==id_titulo_adm}"> selected </c:if>> ${item.valor} (${item.abreviatura})
                                                        </c:forEach>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td width="14%"></td>
                                            <td width="23%">CARGO </td>
                                            <td width="2%">:</td>
                                            <td colspan="4">
                                                <select name="id_cargo_adm" id="item-cargo_adm" class="text-field" style="width:200px" >
                                                    <c:forEach var="item" items="${tipos_cargos}">
                                                        <option value="${item.id}" <c:if test="${item.id==id_cargo_adm}"> selected </c:if>> ${item.valor}
                                                        </c:forEach>
                                                </select>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                                <div id="item-docente" style="display:none">
                                    <table width="795" border="0" >
                                        <tr>
                                            <td width="14%"></td>
                                            <td width="23%">T&Iacute;TULO PROFESIONAL</td>
                                            <td width="2%">:</td>
                                            <td colspan="4">
                                                <select name="id_titulo_doc" id="item-titulo_doc" class="text-field" style="width:200px" >
                                                    <c:forEach var="item" items="${titulos_profesionales}">
                                                        <option value="${item.id}" <c:if test="${item.id==id_titulo_doc}"> selected </c:if>> ${item.valor} (${item.abreviatura})
                                                        </c:forEach>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td width="14%"></td>
                                            <td width="23%">CATEGORIA </td>
                                            <td width="2%">:</td>
                                            <td colspan="4">
                                                <select name="id_categoria_doc" id="item-categoria_doc" class="text-field" style="width:100px" >
                                                    <c:forEach var="item" items="${tipos_categorias}">
                                                        <option value="${item.id}" <c:if test="${item.id==id_categoria_doc}"> selected </c:if>> ${item.valor}
                                                        </c:forEach>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td width="14%"></td>
                                            <td width="23%">CARGA HORARIA </td>
                                            <td width="2%">:</td>
                                            <td colspan="4">
                                                <input class="text-field" type="text" name="cargahoraria" id="fecha" value="${cargahoraria}" style="width: 50px"/>Hrs.
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                                <div id="item-system" style="display:none">
                                    <table width="795" border="0" >
                                        <tr>
                                            <td colspan="7" align="center">Buena suerte!!! ya no hay ideas????¿?¿?¿?¿?!"·%$&/()()=)?¿/&%$"·$</td>
                                        </tr>
                                    </table>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="7"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                        </tr>

                    </table>

                    <script type="text/javascript">setCargoAcademico(document.getElementById('item-cargo'));</script>

                    <input type="hidden" value="_reg" name="opcion">

                    <div id="finalizarRegistro" style="display:none">
                        <div class="formPanel">
                            <p>Confirma para establecer el registro de la persona.</p>
                            <div class="PwdMeterBase" style="width:100%">
                                <table align="center">
                                    <td>
                                        <button class="topformbutton" style="width:70px" onclick="confirmReg()">Aceptar</button>
                                    </td>
                                    <td>
                                        <button class="topformbutton" style="width:70px" onclick="cancelReg()">Cancelar</button>
                                    </td>
                                </table>
                            </div>
                        </div>
                    </div>
                </form>
                <button class="topformbutton" onclick="finalizarReg()">Guardar registro</button>
            </div>
        </div>




        <c:if test="${!empty mensaje}">
            <c:if test="${mensaje == 'true'}">
                <script type="text/javascript">
                    document.getElementById('success').style.display = 'inline';
                    setTimeout('animatedcollapse.hide(\'success\')',2500);
                </script>
            </c:if>
            <c:if test="${mensaje == 'false'}">
                <script type="text/javascript">
                    document.getElementById('failure').style.display = 'inline';
                    setTimeout('animatedcollapse.hide(\'failure\')',2500);
                </script>
            </c:if>
        </c:if>



    </body>
</html>
