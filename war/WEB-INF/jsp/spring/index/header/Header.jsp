<%-- 
    Document   : Header
    Created on : 14-abril-2009, 18:21:22
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
        <link rel="stylesheet" type="text/css" href="css/header.css" />
    </head>
    <body>
        <table cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
                <td width="5%" align="center">
                    <img src="imagenes/iconos_sigaa/st_ban.png">
                </td>
                <td width="25%">
                    <table cellpadding="0" cellspacing="0" width="100%">
                        <tr>
                            <td width="15%" class="tdlabel">Usuario: </td>
                            <td width="85" class="tdvalue"><c:out value="${personal.id_personal}"/></td>
                        </tr>
                        <tr>
                            <td class="tdlabel" title=" Nombre Completo ">Nombre: </td>
                            <td class="tdvalue"><c:out value="${personal.nombres}"/>&nbsp;<c:out value="${personal.paterno}"/>&nbsp;<c:out value="${personal.materno}"/></td>
                        </tr>
                        <tr>
                            <td class="tdlabel">Cargo: </td>
                            <td class="tdvalue"><c:out value="${personal.tpersonal}"/></td>
                        </tr>
                    </table>
                </td>
                <td width="63%" align="center">
                    <img src="imagenes/iconos_sigaa/santa_teresa10.png" border="0">
                </td>
                <td width="7%" align="center">
                    <img  onclick="javascript: window.open('./','_top');" title="inicio" style="cursor:pointer" src="imagenes/iconos_sigaa/home_sigaa.png">
                    <img  onclick="javascript:window.open('<c:url value="/logout.do"/>','_top');" title="Cerrar sesi&oacute;n" style="cursor:pointer" src="imagenes/iconos_sigaa/salir.png">
                    <img  onclick="javascript: window.open('./','_top');" title="Ayuda" style="cursor:pointer" src="imagenes/iconos_sigaa/ayuda.png">
                </td>
            </tr>
            
        </table>
    </body>
</html>
