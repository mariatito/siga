

<%@page contentType="text/html" pageEncoding="LATIN1"%>
<%@taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>

<!--html xmlns="http://www.w3.org/1999/xhtml">
    <head><title>SIGAA - Santa Teresa</title>
        <link rel="stylesheet" type="text/css" href="css/v3.css" media="screen" />
        <script type="text/javascript" src="js/jquery-1.2.2.pack.js"></script>
        <script type="text/javascript" src="js/animatedcollapse.js"></script>
        <script type="text/javascript">
            animatedcollapse.addDiv('errormesage', 'fade=1');
            animatedcollapse.init();

            function setLogin(form) {
                form.accion.value = "Verificando datos..."
                form.accion.disabled = true;
                setTimeout(sendForm, 3000);
                return false;
            }
            function sendForm() {
                var form = document.Login;
                if (form.login.value.length == '' || form.password.value.length == '') {
                    showMessageError();
                    form.login.focus();
                    form.accion.value = 'Ingrese nuevamente';
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

        <table border="0" cellspacing="0" width="100%" cellpadding="0" id="loginsa">
            <tr>
                <td valign="middle" align="center" width="30%">
                </td>
                <td width="50%"><img src="imagenes/iconos_sigaa/nom_sis.png" width="100%" height="100px"/></td>
                <td width="20%"></td>
            </tr>
            <tr> 
                <td valign="middle" align="center">
                    <img src="imagenes/iconos_sigaa/san.png" width="100%" height="300px"/>
                </td>
                <td align="center" >
                    <img src="imagenes/iconos_sigaa/sigaa_oficial.png" width="250px" height="80px"/>
                    <form name="Login" action="login.do" onsubmit="return setLogin(this)" method="get">
                        <table border="0" cellpadding="2">
                            <tr>
                                <td><br><br><br>
                                    <table style="width:100%" align="center" >
                                        <tr>
                                            <td><label for="login" class="labelTxt" style="font-size:7pt">Nombre de usuario: </label></td>
                                            <td><input class="text-field" type="text" name="login" style="font-size:7pt"></td>
                                        </tr>
                                        <tr>
                                            <td><label for="password" class="labelTxt" style="font-size:7pt">Contrase&ntilde;a: </label></td>
                                            <td><input class="text-field" type="password" name="password" style="font-size:7pt"></td>
                                        </tr>
                                        <tr>
                                            <td colspan="2" align="center"><input class="button" type="submit" value="Iniciar sesi&oacute;n" name="accion"></td>
                                        </tr>
                                    </table>

                                </td>
                            </tr>
                            <tr>
                                <td valign="middle" align="center">
                                    <div id="errormesage" style="position: absolute;width:500px; border:0px solid #000000; padding:1px;  display:none">
                                        <table border="0">
                                            <td><span style="color: red; font-size: 12pt"><strong>Nombre de usuario o contraseña incorrecta!!!</strong></span></td>
                                        </table>
                                    </div>
                                    <br><br><br>
                                    <table style="width:100%" align="center" border="0">
                                        <td style="height:10%" align="center">
                                            <img src="imagenes/iconos_sigaa/sun.gif" border="0">&nbsp;<img src="imagenes/iconos_sigaa/spring.gif" border="0">&nbsp;<img src="imagenes/iconos_sigaa/postgresql.png" border="0">&nbsp;<img src="imagenes/iconos_sigaa/glassfish.png" border="0">&nbsp;<img src="imagenes/iconos_sigaa/java.png" border="0">&nbsp;<img src="imagenes/iconos_sigaa/netbeans.png" border="0">
                                        </td>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </form>
                </td>
                <td style="height:500px;width:25%;" valign="top"></td>
            </tr>

        </table>

        <div id="logo"></div>
<c:if test="${!empty key && key == 'failure'}">
    <script type="text/javascript">
        document.getElementById('errormesage').style.display = 'inline';
        setTimeout('animatedcollapse.hide(\'errormesage\')',5000);
    </script>
</c:if>

</body>
</html-->
<html xmlns="http://www.w3.org/1999/xhtml">
    <head><title>SIGAA - Santa Teresa</title>
        <link rel="stylesheet" href="imagenes/dhtmlmodal/windowfiles/dhtmlwindow.css" type="text/css" />
        <script type="text/javascript" src="imagenes/dhtmlmodal/windowfiles/dhtmlwindow.js"></script>
        <link rel="stylesheet" href="imagenes/dhtmlmodal/modalfiles/modal.css" type="text/css" />
        <script type="text/javascript" src="imagenes/dhtmlmodal/modalfiles/modal.js"></script>
        <link rel="stylesheet" type="text/css" href="css/v3.css" media="screen" />
        <script type="text/javascript" src="js/jquery-1.2.2.pack.js"></script>
        <script type="text/javascript" src="js/animatedcollapse.js"></script>
        <script type="text/javascript">
            animatedcollapse.addDiv('errormesage', 'fade=1');
            animatedcollapse.init();

            function setLogin(form) {
                form.accion.value = "Conectando..."
                form.accion.disabled = true;
                setTimeout(sendForm, 0);
                return false;
            }
            function sendForm() {
                var form = document.Login;
                if (form.login.value.length == '' || form.password.value.length == '') {
                    showMessageError();
                    form.login.focus();
                    form.accion.value = 'Intenta nuevamente';
                    form.accion.disabled = false;
                } else {
                    form.submit();
                }
            }
            function showMessageError() {
                animatedcollapse.show('errormesage');
                setTimeout('animatedcollapse.hide(\'errormesage\')',0);
            }
        </script>
    </head>
    <body bgcolor="#FFFFFF" onload="document.Login.login.focus()">
        <div id="loginpanel"> 
            <table border="0" cellspacing="0" width="100%" cellpadding="0">
                <tr>
                    <td rowspan="4" style="height:400px;width:25%;background-color:#ffffff" valign="top"><img src="imagenes/iconos_sigaa/iz_header.png" width="100%" height="610px"/></td>
                    <td colspan="2" id="header_center" style="height:70px;"></td>
                </tr>
                <tr>
                    <td style="color:#666666;height:70px;width:50%;padding-left:50px" align="center">
                        <img src="imagenes/iconos_sigaa/nom_sis.png" width="80%" height="70px"/><br/>
                        <img src="imagenes/iconos_sigaa/sigaa_oficial.png" width="200px" height="40px"/><span style="font-size:6pt">TM</span>
                    </td>
                    <td rowspan="3" style="height:400px;width:25%;" valign="middle">
                    <img src="imagenes/iconos_sigaa/san.gif" width="100%" height="250px"/>
                    </td>
                </tr>
                <tr>
                    <td style="height:130px; width:420px;padding-left:20px" align="center">
                        <div id="errormesage" style="position: absolute; width: 300px; border: 1px solid red; padding: 0px; background-color: #FFC1C1; display: none">
                            <table>
                                <td><img src="imagenes/iconos_sigaa/activo_pe.png" border="0" width="15px"></td>
                                <td><span style="color: #000000; font-size: 10pt; font-weight: lighter">&nbsp;Datos inv&aacute;lidos, intente de nuevamente.</span></td>
                            </table>
                        </div>
                        <br><br>
                        <form name="Login" action="login.do" onsubmit="return setLogin(this)" method="get">
                            <table border="0" cellpadding="2">
                                <tr>
                                    <td><label for="login" class="labelTxt" style="font-size:7pt">Nombre de usuario: </label></td>
                                    <td><input class="text-field" type="text" name="login" style="font-size:7pt" autocomplete="off"></td><!--sys-->
                                </tr>
                                <tr>
                                    <td><label for="password" class="labelTxt" style="font-size:7pt">Contrase&ntilde;a: </label></td>
                                    <td><input class="text-field" type="password" name="password" style="font-size:7pt"></td><!--prefac-->
                                </tr>
                                <tr>
                                    <td colspan="2" align="center">
                                        <input class="button-normal" type="submit" value="Inicio de sesi&oacute;n" name="accion">
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </td>
                </tr>
                <tr>
                    <td style="height:150px;width:420px" valign="top">

                        <table style="width:100%" align="center">
                            <td style="height:10%">
                                <br><br><br><br><br><br>
                                <img src="imagenes/iconos_sigaa/sun.gif" border="0">&nbsp;<img src="imagenes/iconos_sigaa/spring.gif" border="0">&nbsp;<img src="imagenes/iconos_sigaa/postgresql.png" border="0">&nbsp;<img src="imagenes/iconos_sigaa/glassfish.png" border="0">&nbsp;<img src="imagenes/iconos_sigaa/java.png" border="0">&nbsp;<img src="imagenes/iconos_sigaa/netbeans.png" border="0">
                                <br>
                                <p align="justify">
                                    <span style="color: #000000; font-size: 8pt;">Copyright @ 2009; COLEGIO SANTA TERESA. Desarrolladores: M<font style="font-size:10pt">&</font>M: Maria Ang&eacute;lica & Marco Antonio, Cel. 79131293-70622234, La Paz - Bolivia. Todos los derechos reservados. Sin publicar: derechos reservados bajo las leyes de copyright de Bolivia.</span>
                                </p>
                            </td>
                        </table>
                    </td>
                </tr>
            </table>
        </div>

        <div id="logo"></div>
        <c:if test="${!empty key && key == 'failure'}">
            <script type="text/javascript">
                document.getElementById('errormesage').style.display = 'inline';
                setTimeout('animatedcollapse.hide(\'errormesage\')',5000);
            </script>
        </c:if>

        <div id="fcpnlocation" style="display:none"></div>
    </body>
</html>

