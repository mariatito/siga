<%-- 
    Document   : EnterPointView
    Created on : 17-may-2009, 16:21:22
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
        <script type="text/javascript" src="js/jquery-1.2.2.pack.js"></script>
        <script type="text/javascript" src="js/animatedcollapse.js"></script>
        <script type="text/javascript">
            animatedcollapse.addDiv('errormesage', 'fade=1');
            animatedcollapse.init();
        
            function setLogin(form) {
                form.accion.value = "Conectando..."
                form.accion.disabled = true;
                setTimeout(sendForm, 3000);
                return false;
            }
            
            function sendForm() {
                var form = document.Login;
                if (form.login.value.length == '' || form.password.value.length == '') {
                    showMessageError();  
                    form.login.focus();
                    form.accion.value = 'Intentar nuevamente';
                    form.accion.disabled = false;              
                } else {
                    form.submit();
                }
            }
            
            function showMessageError() {
                animatedcollapse.show('errormesage');
                setTimeout('animatedcollapse.hide(\'errormesage\')',5000);
            }
        
        </script>
    </head>
    <body>
        <div class="headercontent"><br>
            <div class="titlepage">Seguridad</div>
        </div>
        
        <div class="titlepage">
            <h2>¡Identif&iacute;cate!</h2>
        </div>
        
        <div class="maincontent">
            <form name="Login" action="login.do" onsubmit="return setLogin(this)" method="post" target="main">
                <table border="0" cellpadding="2" class="gridContent" align="center">
                    <tr>
                        <td><label for="login" class="labelTxt">Nombre de usuario: </label></td>
                        <td><input class="txtField" type="text" name="login"></td>
                    </tr>
                    <tr>
                        <td><label for="password" class="labelTxt">Contrase&ntilde;a: </label></td>
                        <td><input class="txtField" type="password" name="password"></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center"><input class="button-normal" type="submit" value="Inicio de sesi&oacute;n" name="accion"></td>
                    </tr>
                </table> 
                <input name="url" type="hidden" value="<c:url value="${url}"/>"/> 
                <input name="servletPath" type="hidden" value="<c:out value="${url}"/>"/>
            </form> 

            <div id="errormesage" style="position: absolute; width: 300px; border: 1px solid red; padding: 0px; background-color: #FFC1C1; display: none">
                <table>
                    <td><img src="imagenes/iconos_sigaa/activo_pe.png" border="0" width="15px"></td>
                    <td><span style="color: #000000; font-size: 10pt; font-weight: lighter">&nbsp;Datos inv&aacute;lidos, intente de nuevamente.</span></td>
                </table>            
            </div>           
        </div>
        <c:if test="${!empty key && key == 'failure'}">
            <script type="text/javascript">
                document.getElementById('errormesage').style.display = 'inline';
                setTimeout('animatedcollapse.hide(\'errormesage\')',5000);
            </script>  
        </c:if>      
    </body>
</html>
