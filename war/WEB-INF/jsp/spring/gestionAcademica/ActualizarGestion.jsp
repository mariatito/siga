<%-- 
    Document   : ActualizarGestion
    Created on : 15-jul-2009, 23:09:36
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
        <link rel="stylesheet" type="text/css" href="css/module.css" media="screen" />
        <script type="text/javascript">
            animatedcollapse.addDiv('success', 'fade=1');
            animatedcollapse.addDiv('failure', 'fade=1');
            animatedcollapse.init();
        </script>

        <script type="text/javascript">
            function onRowover(elem) {
                elem.className='colover'; 
            }
            function onRowout(elem) {
                elem.className='colout';
            }
            function setBuscar(form) {
                if (form.search.value.length < 3) {
                    alert('Debes ingresar algun dato para buscar.');
                    return false;
                }
                form.opcion.value = 'Conectando...';
                form.opcion.disabled = true;
                return true;
            }
            function selectGestion(id) {
                window.location='<c:url value="/actualizarGestionAcademico.do"/>?id_gestion='+id;
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
            <table id="tabs_menu" cellpadding="0" cellspacing="0" border="0" style="width:100%">
                <tr>
                    <td class="tab_init">&nbsp;</td>
                    <td style="width:50%">
                        <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                            <td class="tab_normal" onclick="javascript:window.location='<c:url value="/registrarGestionAcademico.do"/>'">Registro del cronograma acad&eacute;mico</td>
                            <td class="tab_current" >Actualizar gesti&oacute;n acad&eacute;mico</td>
                        </table>
                    </td>
                    <td class="tab_fin">&nbsp;</td>
                </tr>
            </table>
            <div class="titlepage">ACTUALIZAR GESTI&Oacute;N ACAD&Eacute;MICO</div>
        </div>
        <div class="maincontent">
            <div id="failure" style="position: absolute; width: 230px; border: 1px solid #FFCC00; padding: 5px; background-color: #FFFFAE; display: none">
                <table>
                    <td><img src="imagenes/alert.gif" border="0"></td>
                    <td><span style="color: #000000; font-size: 10pt; font-weight: bold">&nbsp;Fallo en la solicitud.</span></td>
                </table>
            </div>

            <div id="success" style="position: absolute; width: 230px; border: 1px solid #009933; padding: 5px; background-color: #DEF5E6; display: none">
                <span style="color: #000000; font-size: 10pt; font-weight: bold">Registro modificado correctamente.</span>
            </div>

            <c:if test="${empty gestion}">
                <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                    <tr>
                        <td class="tableHeader">Lista de gestiones</td>
                    </tr>
                    <tr>
                        <td class="gridContent">
                            <div id="resultlayer1">
                                <table width="100%" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF">
                                    <tr>
                                        <td class="gridHeaders" style="width:3%">Nro.</td>
                                        <td class="gridHeaders" style="width:7%">Gesti&oacute;n</td>
                                        <td class="gridHeaders" style="width:25%">Directora</td>
                                        <td class="gridHeaders" style="width:55%">Lema</td>
                                        <td class="gridHeaders" style="width:10%">Estado</td>
                                    </tr>
                                    <c:if test="${empty gestiones}">
                                        <tr>
                                            <td class="gridData" colspan="5">No se han encontrado elementos.</td>
                                        </tr>
                                    </c:if>
                                    <c:forEach varStatus="i" var="item" items="${gestiones}">

                                        <c:if test="${item.id_gestion==anio_actual}">
                                            <tr onclick="selectGestion('<c:out value="${item.id_gestion}"/>')" style="cursor: pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                                <td class="gridData"><c:out value="${i.count}"/> </td>
                                                <td class="gridData" align="center"><c:out value="${item.id_gestion}"/></td>
                                                <td class="gridData">${item.titulo} ${item.nombre}</td>
                                                <td class="gridData">${item.lema}</td>
                                                <c:if test="${item.estado==true}">
                                                    <td class="gridData" align="center"><img src="imagenes/iconos_sigaa/activo_si.png" border="0" title="Estado activo"></td>
                                                    </c:if>
                                                    <c:if test="${item.estado==false}">
                                                    <td class="gridData" align="center"><img src="imagenes/iconos_sigaa/activo_pe.png" border="0" title="Activaci&oacute;n incorrecta"></td>
                                                    </c:if>
                                            </tr>
                                        </c:if>
                                        <c:if test="${item.id_gestion!=anio_actual}">
                                            <tr style="cursor: pointer">
                                                <td class="gridData"><c:out value="${i.count}"/> </td>
                                                <td class="gridData" align="center"><c:out value="${item.id_gestion}"/></td>
                                                <td class="gridData">${item.titulo} ${item.nombre}</td>
                                                <td class="gridData">${item.lema}</td>
                                                <c:if test="${item.estado==true}">
                                                    <td class="gridData" align="center"><img src="imagenes/iconos_sigaa/activo_pe.png" border="0" title="Activaci&oacute;n incorrecta"></td>
                                                    </c:if>
                                                    <c:if test="${item.estado==false}">
                                                    <td class="gridData" align="center"><img src="imagenes/iconos_sigaa/activo_no.png" border="0" title="Estado inactivo"></td>
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

            <c:if test="${!empty gestion}">
                <div id="formRegistroPersona" class="formPanel">
                    <form action="<c:url value="/actualizarGestionAcademico.do"/>" method="post" name="MiForm" onsubmit="return setRegistrar(this)" style="cursor: pointer">
                        <table width="800" border="0" cellpadding="0" cellspacing="0" align="center" class="gridContent">
                            <tr>
                                <td class="tableHeader" colspan="7">Gesti&oacute;n acad&eacute;mico</td>
                            </tr>
                            <tr>
                                <td align="center" colspan="7"><p><strong>ACTUALIZACI&Oacute;N DE GESTI&Oacute;N ACAD&Eacute;MICO</strong></p></td>
                            </tr>
                            <tr>
                                <td colspan="7"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                            </tr>
                            <tr>
                                <td width="14%"></td>
                                <td width="23%">GESTION</td>
                                <td width="2%">:</td>
                                <td colspan="4">
                                    <select name="id_gestion"  class="text-field" style="width:100px" onfocus="this.blur()">
                                        <c:forEach var="item" items="${tipo_anios}">
                                            <option value="${item.id}" <c:if test="${item.id==gestion.id_gestion}"> selected </c:if>> ${item.id}
                                            </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="7"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                            </tr>

                            <tr>
                                <td width="14%"></td>
                                <td colspan="1">COLEGIO</td>
                                <td colspan="1">:</td>
                                <td colspan="4"><input class="text-field" type="text" name="colegio" value="${gestion.colegio}" maxlength="100" style="width:250px" onfocus="this.blur()"/></td>
                            </tr>
                            <tr>
                                <td colspan="7"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                            </tr>
                            <tr>
                                <td width="14%"></td>
                                <td colspan="1">DIRECCION</td>
                                <td colspan="1">:</td>
                                <td colspan="4"><input class="text-field" type="text" name="direccion" value="${gestion.direccion}" maxlength="200" style="width:250px"/></td>
                            </tr>
                            <tr>
                                <td colspan="7"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                            </tr>
                            <tr>
                                <td width="14%"></td>
                                <td colspan="1">DIRECTORA</td>
                                <td colspan="1">:</td>
                                <td colspan="4">
                                    <select name="id_administrativo"  class="text-field" style="width:250px" >
                                        <c:forEach var="item" items="${directores}">
                                            <option value="${item.id_administrativo}" <c:if test="${item.id_administrativo==gestion.directora}"> selected </c:if>> ${item.titulo} ${item.nombre}
                                            </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="7"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                            </tr>
                            <tr>

                            <tr>
                                <td width="14%"></td>
                                <td colspan="1">TELEFONO</td>
                                <td colspan="1">:</td>
                                <td colspan="4"><input class="text-field" type="text" name="telefono" value="${gestion.telefono}" maxlength="15" style="width:100px"/></td>
                            </tr>
                            <tr>
                                <td colspan="7"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                            </tr>
                            <tr>
                                <td width="14%"></td>
                                <td colspan="1">FAX</td>
                                <td colspan="1">:</td>
                                <td colspan="4"><input class="text-field" type="text" name="fax" value="${gestion.fax}" maxlength="15" style="width:100px"/></td>
                            </tr>
                            <tr>
                                <td colspan="7"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                            </tr>
                            <tr>
                                <td width="14%"></td>
                                <td width="23%">LEMA</td>
                                <td width="2%">:</td>
                                <td colspan="4"><textarea name="lema" class="text-field" style="width:550px">${gestion.lema}</textarea></td>                        </tr>
                            <tr>
                                <td colspan="7"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                            </tr>
                            <tr>
                                <td width="14%"></td>
                                <td width="23%">CORREO ELECTRONICO</td>
                                <td width="2%">:</td>
                                <td colspan="4"><input class="text-field" type="text" name="mail" maxlength="150" value="${gestion.mail}" style="width:250px"/></td>
                            </tr>
                            <tr>
                                <td colspan="7"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                            </tr>
                            <tr>
                                <td class="tableHeader" colspan="7">PENSIONES POR CURSO</td>
                            </tr>
                            <tr>
                                <td colspan="7">
                                    <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
                                        <tr align="center">
                                            <td width="5%" class="gridHeaders">Nro</td>
                                            <td width="35%" class="gridHeaders">CURSO</td>
                                            <td width="20%" class="gridHeaders">MOTO TOTAL (Bs.)</td>
                                            <td width="20%" class="gridHeaders">CUOTA INICIAL (Bs.)</td>
                                            <td width="20%" class="gridHeaders">CUOTA ANUAL (Bs.)</td>
                                        </tr>
                                        <c:forEach varStatus="i" var="item" items="${pensiones}">
                                            <tr style="cursor: pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                                <td>${i.count}</td>
                                                <td><strong>${item.curso} de ${item.ciclo}</strong> (${item.id_curso})</td>
                                                <td align="center">${item.cuota_total + item.cuota_inicial}</td>
                                                <td align="center">${item.cuota_inicial}</td>
                                                <td align="center">${item.cuota_total}</td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="7"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                            </tr>
                            <tr>
                                <td class="tableHeader" colspan="7">SERVICIOS</td>
                            </tr>
                            <tr>
                                <td colspan="7">
                                    <table width="80%" border="0" cellpadding="0" cellspacing="0" align="center" bgcolor="#FFFFFF">
                                        <tr>
                                            <td class="gridHeaders" width="5%">Nro.</td>
                                            <td class="gridHeaders" width="80%">Descripcion</td>
                                            <td class="gridHeaders" width="15%">Monto</td>

                                        </tr>
                                        <c:if test="${empty servicios}">
                                            <tr>
                                                <td class="gridData" colspan="6">No se han encontrado elementos.</td>
                                            </tr>
                                        </c:if>
                                        <c:forEach varStatus="j" var="item" items="${servicios}">
                                            <tr style="cursor:pointer">
                                                <td class="gridData">${j.count}</td>
                                                <td class="gridData">${item.tipo_servicio}</td>
                                                <td class="gridData"><b>${item.monto_servicio} Bs.</b></td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="7"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                            </tr>
                        </table>

                        <script type="text/javascript">setCargoAcademico(document.getElementById('item-cargo'));</script>

                        <input type="hidden" value="_guardar" name="guardar">

                        <div id="finalizarRegistro" style="display:none">
                            <div class="formPanel">
                                <p>Confirma guardar los cambios realizados a la gestion actual?</p>
                                <div class="PwdMeterBase" style="width:100%">
                                    <table align="center">
                                        <td>
                                            <button class="button-normal" style="width:70px" onclick="cancelReg()"><img width="11px" src="imagenes/iconos_sigaa/activo_no.png">&nbsp;&nbsp;Cancelar</button>
                                        </td>
                                        <td>
                                            <button class="button-normal" style="width:70px" onclick="confirmReg()"><img width="11px" src="imagenes/iconos_sigaa/guardar.png">&nbsp;&nbsp;Guardar</button>
                                        </td>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </form>
                    <div align="center">
                        <button class="button-normal" onclick="finalizarReg()"><img width="11px" src="imagenes/iconos_sigaa/guardar.png">&nbsp;&nbsp;Guardar</button>
                    </div>
                </div>

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
