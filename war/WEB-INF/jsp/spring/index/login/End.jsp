<%-- 
    Document   : End
    Created on : 17-may-2009, 16:21:22
    Author     : Marco Antonio Quenta Velasco
--%>

<%@page contentType="text/html" pageEncoding="LATIN1"%>
<%@taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<!--html xmlns="http://www.w3.org/1999/xhtml">
    <HEAD><TITLE>SIGAA - Santa Teresa</TITLE>

    </HEAD>
    <link rel="stylesheet" type="text/css" href="css/v3.css" media="screen" />

    <body bgcolor="#FFFFFF">
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
                                    <table style="width:100%" align="center">
                                        <tr>
                                            <td style="height: 10px; width: 30px" valign="top" align="center"><br><br><br><br>
                                                <div id="errormesage" style="position: absolute;width:500px; border:0px solid #000000; padding:1px;">
                                                    <span style="color: blue; font-size: 10pt; font-weight: bold"><a href=".">Click para ingresar nuevamente</a></span>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td style="height:10px;width:30px" valign="top">
                                                <div id="errormesage" style="position: absolute;width:500px; border:0px solid #000000; padding:1px;  display:none">
                                                    <table>
                                                        <td><span style="color: red; font-size: 12pt"><strong>Nombre de usuario o contraseña incorrecta!!!</strong></span></td>
                                                    </table>
                                                </div>
                                            </td>
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
    </body>
</html-->
<html xmlns="http://www.w3.org/1999/xhtml">
    <HEAD><TITLE>SIGAA - Santa Teresa</TITLE>

    </HEAD>
    <link rel="stylesheet" type="text/css" href="css/v3.css" media="screen" />

    <body bgcolor="#FFFFFF">
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
                    <td style="height: 150px; width: 400px" valign="middle">
                        <div id="errormesage" style="position: absolute; width: 220; border: 1px solid #086767; padding: 5px; background-color: #B0F9F9;">
                            <span style="color: #000000; font-size: 10pt; font-weight: bold">Aplicaci&oacute;n terminada, click <a href=".">aqui</a> para volver a ingresar.</span>
                        </div>
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
                                    <span style="color: #000000; font-size: 8pt;">Copyright @ 2009;COLEGIO SANTA TERESA. Desarrolladores:M<font style="font-size:10pt">&</font>M: Maria Ang&eacute;lica & Marco Antonio, Cel. 79131293-70622234, La Paz - Bolivia. Todos los derechos reservados. Sin publicar: derechos reservados bajo las leyes de copyright de Bolivia.</span></p>
                            </td>
                        </table>
                    </td>
                </tr>
            </table>
        </div>
    </body>

</html>

