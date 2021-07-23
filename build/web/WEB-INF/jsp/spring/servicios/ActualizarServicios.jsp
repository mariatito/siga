<%-- 
    Document   : ActualizarServicios
    Created on : 11-mar-2010, 23:27:30
    Author     : MARCO
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

            function selectGestion(id_ges){
                window.location = '<c:url value="/actualizarServicios.do"/>?id_gestion='+id_ges;
            }

            function selectServicio(id_servicio){
                window.location = '<c:url value="/actualizarServicios.do"/>?id_gestion=${id_gestion}&id_servicio='+id_servicio;
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
                            <td class="tab_normal" onclick="javascript:window.location='<c:url value="/registrarServicios.do"/>'">Registrar datos</td>
                            <td class="tab_current">Actualizar servicio</td>
                        </table>
                    </td>
                    <td class="tab_fin">&nbsp;</td>
                </tr>
            </table>
             <div class="titlepage">ACTUALIZAR DATOS</div>
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
            <c:if test="${!empty servicios}">
                <!--form action="<c:url value="/actualizarCursos.do"/>" method="post" name="frmSeach" onsubmit="return setBuscar(this)">
                    <table>
                        <td class="text-label">Buscar curso</td>
                        <td class="text-label"><input type="text" name="search" value="${search}" class="text-field"></td>
                        <td class="text-label"><input class="button-submit" type="submit" name="opcion" value="Buscar"></td>
                    </table>
                </form-->
                <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                    <tr>
                        <td class="tableHeader">Lista de servicios registrados en ${id_gestion}</td>
                    </tr>
                    <tr>
                        <td class="gridContent">
                            <div>
                                Selecciona para abrir el registro.
                            </div>
                            <div id="resultlayer1">
                                <table width="100%" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF">
                                    <tr>
                                        <td class="gridHeaders" width="10%">Nro.</td>
                                        <td class="gridHeaders" width="70%">Descripci&oacute;n</td>
                                        <td class="gridHeaders" width="20%">Monto [Bs.]</td>
                                    </tr>
                                    <c:if test="${empty servicios}">
                                        <tr>
                                            <td class="gridData" colspan="3">No se han encontrado elementos.</td>
                                        </tr>
                                    </c:if>
                                    <c:forEach varStatus="i" var="item" items="${servicios}">
                                        <tr onclick="selectServicio('<c:out value="${item.id_servicio}"/>')" style="cursor: pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                            <td class="gridData" align="center"><c:out value="${i.count}"/> </td>
                                            <td class="gridData">[${item.tipo_servicio}</td>
                                            <td class="gridData">${item.monto_servicio}</td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </div>
                        </td>
                    </tr>
                </table>
            </c:if>

            <c:if test="${!empty servicio}">
                
                    <form action="<c:url value="/actualizarServicios.do"/>" method="post" name="MiForm">
                        <table width="800" border="0" cellpadding="0" cellspacing="0">

                        <tr>
                            <td align="center"colspan="7"><p><strong>ACTUALIZACI&Oacute;N DEL SERVICIO</strong></p></td>
                        </tr>
                        <tr>
                            <td colspan="7"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                        </tr>
                        <tr>
                            <td width="14%"></td>
                            <td colspan="1">DESCRIPCION DEL SERVICIO ( <img src="imagenes/icons/required.gif"/> )</td>
                            <td colspan="1">:</td>
                            <td colspan="4"><input class="text-field" type="text" name="tipo_servicio" value="${servicio.tipo_servicio}" maxlength="100" style="width:250px"/></td>
                        </tr>
                        <tr>
                            <td colspan="7"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                        </tr>
                        <tr>
                            <td width="14%"></td>
                            <td colspan="1">MONTO ( <img src="imagenes/icons/required.gif"/> )</td>
                            <td colspan="1">:</td>
                            <td colspan="4"><input class="text-field" type="text" name="monto_servicio" value="${servicio.monto_servicio}" maxlength="4" style="width:80px"/></td>
                        </tr>
                        <tr>
                            <td colspan="7"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                        </tr>
                        <tr>
                            <td width="14%"></td>
                            <td colspan="1">GESTION</td>
                            <td colspan="1">:</td>
                            <td colspan="4"><select name="anio"  class="text-field" style="width:80px" >
                             <c:forEach var="item" items="${tipo_anios}">
                                        <option value="${item.id}" <c:if test="${item.id==servicio.id_gestion}"> selected </c:if>> ${item.id}
                                    </c:forEach>
                                </select>
                        </tr>
                        <tr>
                            <td colspan="7"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                        </tr>
                    </table>
                        <input type="hidden" value="_actualizar" name="opcion">
                        <input type="hidden" value="${servicio.id_servicio}" name="id_servicio">
                        <input type="hidden" value="${id_gestion}" name="id_gestion">
                        <div id="finalizarRegistro" style="display:none">
                            <div class="formPanel">
                                <p>Confirma guardar las modificaciones realizadas?</p>
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
                
            </c:if>
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