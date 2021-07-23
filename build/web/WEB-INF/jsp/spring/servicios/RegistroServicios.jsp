<%--
    Document   : RegistroServicios
    Created on : 24-07-2009, 09:17:37 AM
    Author     : Administrador
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
                            <td class="tab_current">Registrar servicio</td>
                            <td class="tab_normal" onclick="javascript:window.location='<c:url value="/actualizarServicios.do"/>'">Actualizar servicio</td>
                            <!--td class="tab_normal" style="border-right:1px solid #7D909E" onclick="javascript:window.location='<c:url value="/actualizarDatosPersona.do"/>'">Actualizar datos</td-->
                        </table>
                    </td>
                    <td class="tab_fin">&nbsp;</td>
                </tr>
            </table>
            <div class="titlepage">REGISTRAR SERVICIOS</div>
        </div>
        <div class="maincontent">
            <div id="failure" style="position: absolute; width: 230px; border: 1px solid #FFCC00; padding: 5px; background-color: #FFFFAE; display: none">
                <table>
                    <td><img src="imagenes/alert.gif" border="0"></td>
                    <td><span style="color: #000000; font-size: 10pt; font-weight: bold">&nbsp;Fallo en la solicitud.</span></td>
                </table>
            </div>
            <div id="success" style="position: absolute; width: 230px; border: 1px solid #009933; padding: 5px; background-color: #DEF5E6; display: none">
                <span style="color: #000000; font-size: 10pt; font-weight: bold">Enviado correctamente</span>
            </div>

            <div id="formRegistroPersona" class="formPanel">
                <form action="<c:url value="/registrarServicios.do"/>" method="post" name="MiForm" onsubmit="return setRegistrar(this)">
                    <table width="800" border="0" cellpadding="0" cellspacing="0">

                        <tr>
                            <td align="center"colspan="7"><p><strong>REGISTRO DE SERVICIOS</strong></p></td>
                        </tr>
                        <tr>
                            <td colspan="7"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                        </tr>
                        <tr>
                            <td width="14%"></td>
                            <td colspan="1">DESCRIPCION DEL SERVICIO ( <img src="imagenes/icons/required.gif"/> )</td>
                            <td colspan="1">:</td>
                            <td colspan="4"><input class="text-field" type="text" name="tipo_servicio" value="${tipo_servicio}" maxlength="100" style="width:250px"/></td>
                        </tr>
                        <tr>
                            <td colspan="7"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                        </tr>
                        <tr>
                            <td width="14%"></td>
                            <td colspan="1">MONTO ( <img src="imagenes/icons/required.gif"/> )</td>
                            <td colspan="1">:</td>
                            <td colspan="4"><input class="text-field" type="text" name="monto_servicio" value="${monto_servicio}" maxlength="4" style="width:80px"/></td>
                        </tr>
                        <tr>
                            <td colspan="7"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                        </tr>
                        <tr>
                            <td width="14%"></td>
                            <td colspan="1">GESTION</td>
                            <td colspan="1">:</td>
                            <td colspan="4"><select name="id_gestion"  class="text-field" style="width:80px" >
                             <c:forEach var="item" items="${tipo_anios}">
                                        <option value="${item.id}" <c:if test="${item.id==gestion.id_gestion}"> selected </c:if>> ${item.id}
                                    </c:forEach>
                                </select>
                        </tr>
                        <tr>
                            <td colspan="7"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                        </tr>
                    </table>
                    <input type="hidden" value="_reg" name="opcion">
                    <div id="finalizarRegistro" style="display:none">
                        <div class="formPanel">
                            <p>Confirmar el registro de un nuevo servicio.</p>
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
                <button class="topformbutton" onclick="finalizarReg()">Guardar...</button>
            </div>
        </div>
        <c:if test="${!empty mensaje}">
            <c:if test="${mensaje == 'true'}">
                <script type="text/javascript">
                    document.getElementById('success').style.display = 'inline';
                    setTimeout('animatedcollapse.hide(\'success\')',5000);
                </script>
            </c:if>
            <c:if test="${mensaje == 'false'}">
                <script type="text/javascript">
                    document.getElementById('failure').style.display = 'inline';
                    setTimeout('animatedcollapse.hide(\'failure\')',5000);
                </script>
            </c:if>
        </c:if>
    </body>
</html>
