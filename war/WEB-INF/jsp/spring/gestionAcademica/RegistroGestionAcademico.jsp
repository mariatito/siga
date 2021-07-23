<%-- 
    Document   : RegistroGestionAcademico
    Created on : 06-jun-2009, 12:16:52
    Author     : Marco Antonio
--%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
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
        <link rel="stylesheet" type="text/css" href="css/st.css" media="screen" />
        <script type="text/javascript" src="js/prototype.js"></script>

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
            function openWindow(form,title,w,h){
                serviciosWin=dhtmlmodal.open('win'+form, 'div', form, title, 'width='+w+'px,height='+h+'px,left=100px,top=100px,resize=1,scrolling=1');
                serviciosWin.moveTo('middle', 'middle');
                return serviciosWin;
            }
            var serviciosWin=null;
            function selectServicio(id) {
                window.location='<c:url value="/inscripcionEstudiantes.do"/>?id_servicio='+id;
            }
            function __closeDList() {
                serviciosWin.hide();
            }

            function selectCurso(id_curso){
                var monto_total= $('monto-total-'+id_curso).value;
                var monto_inicial= $('monto-inicial-'+id_curso).value;
                //var monto_anual=Math.round(parseFloat(()*100)/100);
                //alert(monto_anual);
                //redondeo
                $('monto-anual-'+id_curso).innerHTML = redondeo(parseFloat(monto_total)-parseFloat(monto_inicial),2);;
            }
            function redondeo(numero, decimales){
                var flotante=parseFloat(numero);
                var res=Math.round(flotante*Math.pow(10,decimales))/Math.pow(10,decimales);
                return res;
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
                            <td class="tab_current">Registro del cronograma acad&eacute;mico</td>
                            <td class="tab_normal" onclick="javascript:window.location='<c:url value="/actualizarGestionAcademico.do"/>'">Actualizar gesti&oacute;n acad&eacute;mico</td>
                        </table>
                    </td>
                    <td class="tab_fin">&nbsp;</td>
                </tr>
            </table>
            <div class="titlepage">REGISTRO DEL CRONOGRAMA ACAD&Eacute;MICO</div>
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
                <form action="<c:url value="/registrarGestionAcademico.do"/>" method="post" name="MiForm" onsubmit="return setRegistrar(this)">
                    <table width="800" border="0" cellpadding="0" cellspacing="0" align="center" class="gridContent" style="cursor: pointer">
                        <tr>
                            <td class="tableHeader" colspan="7">Gesti&oacute;n acad&eacute;mica</td>
                        </tr>
                        <tr>
                            <td align="center" colspan="7"><p><strong>REGISTRO DE GESTI&Oacute;N ACAD&Eacute;MICO</strong></p></td>
                        </tr>
                        <tr>
                            <td width="14%"></td>
                            <td colspan="6"><strong>DATOS A REGISTRAR</strong></td>
                        </tr>
                        <tr>
                            <td width="14%"></td>
                            <td width="23%">GESTION</td>
                            <td width="2%">:</td>
                            <td colspan="4">
                                <select name="id_gestion"  class="text-field" style="width:100px" >
                                    <c:forEach var="item" items="${tipo_anios}">
                                        <option value="${item.id}" <c:if test="${item.id==id_gestion}"> selected </c:if>> ${item.id}
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
                            <td colspan="4"><input class="text-field" type="text" name="colegio" value="SANTA TERESA" maxlength="100" style="width:250px" onfocus="this.blur()"/></td>
                        </tr>
                        <tr>
                            <td colspan="7"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                        </tr>
                        <tr>
                            <td width="14%"></td>
                            <td colspan="1">DIRECCION</td>
                            <td colspan="1">:</td>
                            <td colspan="4"><input class="text-field" type="text" name="direccion" value="${direccion}" maxlength="200" style="width:250px"/></td>
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
                                        <option value="${item.id_administrativo}" <c:if test="${item.id_administrativo==id_administrativo}"> selected </c:if>> ${item.titulo} ${item.nombre}
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
                            <td colspan="4"><input class="text-field" type="text" name="telefono" value="${telefono}" maxlength="15" style="width:100px"/></td>
                        </tr>
                        <tr>
                            <td colspan="7"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                        </tr>
                        <tr>
                            <td width="14%"></td>
                            <td colspan="1">FAX</td>
                            <td colspan="1">:</td>
                            <td colspan="4"><input class="text-field" type="text" name="fax" value="${fax}" maxlength="15" style="width:100px"/></td>
                        </tr>
                        <tr>
                            <td colspan="7"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                        </tr>
                        <tr>
                            <td width="14%"></td>
                            <td width="23%">LEMA</td>
                            <td width="2%">:</td>
                            <td colspan="4"><textarea name="lema" class="text-field" style="width:550px">${lema}</textarea></td>                        </tr>
                        <tr>
                            <td colspan="7"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                        </tr>
                        <tr>
                            <td width="14%"></td>
                            <td width="23%">CORREO ELECTRONICO</td>
                            <td width="2%">:</td>
                            <td colspan="4"><input class="text-field" type="text" name="mail" maxlength="150" value="${mail}" style="width:250px"/></td>
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
                                    <c:forEach varStatus="i" var="item" items="${cursos}">
                                        <tr style="cursor: pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                            <td>${i.count}</td>
                                            <td><strong>${item.curso} de ${item.ciclo}</strong> (${item.id_curso})</td>
                                            <td align="center"><input tabindex="${i.count}" onblur="selectCurso('${item.id_curso}')" class="text-field" type="text" id="monto-total-${item.id_curso}" name="monto-total-${item.id_curso}" value="0" maxlength="9" style="width:90%"/></td>
                                            <td align="center"><input tabindex="${i.count}"onblur="selectCurso('${item.id_curso}')" class="text-field" type="text" id="monto-inicial-${item.id_curso}" name="monto-inicial-${item.id_curso}" value="0" maxlength="9" style="width:90%"/></td>
                                            <td id="monto-anual-${item.id_curso}" style="text-align: right; font-weight: bold;padding-right: 5px"></td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </td>
                        </tr>
                    </table>
                    <input type="hidden" value="_reg" name="opcion">
                    <div id="finalizarRegistro" style="display:none" >
                        <div class="formPanel">
                            Confirma para establecer el registro de la nueva gesti&oacute;n acad&eacute;mica?, Si es así, tenga muy en cuenta lo siguiente:<br>
                            - No se podr&aacute;n realizar ningun tipo de cambio o modificaciones en los datos(evaluaciones, pensiones, servicios,...) de las gestiones pasadas.
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
