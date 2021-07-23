<%-- 
    Document   : ActualizarPassword
    Created on : 17-jun-2009, 16:21:22
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
        <link rel="stylesheet" type="text/css" href="css/module.css" media="screen" />
        <script type="text/javascript">
            animatedcollapse.addDiv('success', 'fade=1');
            animatedcollapse.addDiv('failure', 'fade=1');
            animatedcollapse.init();   
        </script>        
    </head>
    <body>
        <div class="headercontent">
            <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                <tr>
                    <td class="tab_init">&nbsp;</td>
                    <td style="width:30%">
                        <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                            <td class="tab_current">Cambiar la clave</td>      
                            <!--td class="tab_normal" style="border-right:1px solid #7D909E" onclick="//javascript:window.location='<c:url value="/administrarInscripcion.do"/>'">Avanzado</td-->
                        </table> 
                    </td>
                    <td class="tab_fin">&nbsp;</td>
                </tr>
            </table> 
            <div class="titlepage">CAMBIAR MI CLAVE DE ACCESO</div>
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
            <p>
                Es importante actualizar la <b>palabra clave de acceso</b> a fin de evitar suplantaci&oacute;n de identidad 
                en  el sistema.
            </p>   
            <form name="form" action="<c:url value="/changePassword.do"/>" method="post">
                <table align="center" class="gridContent" width="60%">
                    <tr>
                        <td><label for="old_password">Ingrese su contraseña anterior</label></td>
                        <td>: <input name="old_password" type="password"/></td>
                    </tr>
                    <tr>
                        <td><label for="new_password1">Ingrese su nueva contraseña</label></td>
                        <td>: <input name="new_password1" type="password"/></td>
                    </tr>
                    <tr>
                        <td><label for="new_password2">Ingrese nuevamente su nueva contaseña</label></td>
                        <td>: <input  name="new_password2" type="password"/></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <button class="button-normal" type="submit"><img src="imagenes/iconos_sigaa/guardar.png" width="11px">&nbsp;&nbsp;Guardar</button>
                    </tr>
                </table>
                
            </form>
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
