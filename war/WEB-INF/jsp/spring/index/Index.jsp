<%-- 
    Document   : Index
    Created on : 14-abr-2009, 18:21:22
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
        <link rel="stylesheet" type="text/css" href="css/menu.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="css/menu_despl.css" media="screen" />
    </head>
    <body>
        
        <table style="width: 100%; cursor: pointer" border="0" cellspacing="0" cellpadding="0" id="menuHead" align="center">
            <tr>
                <td align="center"><img src="imagenes/iconos_sigaa/menu.png" width="250"><br><br></td>
            </tr>
            <tr>
                <td align="center">
                    <ul class="menu_sigaa">
                        <c:forEach var="item" items="${personal.menu.modalidades}">
                            <li><img src="${item.icono}">
                                <ul>
                                    <c:forEach var="tar" items="${item.tareas}">
                                        <li><img src="imagenes/iconos_sigaa/flechita2.png">&nbsp;&nbsp;<a href="<c:url value="${tar.url}"/>" target="main" title=" ${tar.descripcion} ">${tar.tarea}</a></li>
                                        </c:forEach>
                                </ul>
                            </li>
                        </c:forEach>
                    </ul>
                </td>
                
            </tr>
     
         </table>
        <table>
            <td  style="font-size:10px; font:bold;  color:black"> <script type="text/javascript">

semana=new Array ("Domingo","Lunes","Martes","Miercoles","Jueves","Viernes","Sabado");
meses= new Array ("Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre");
f=new Date();
ds=f.getDay();//6
dm=f.getDate();//15
m=f.getMonth();//9
a=f.getFullYear();//2011
document.write("Hoy es: "+semana[ds]+", "+dm+" de "+meses[m]+" de "+a); //mostrando la fecha completa

</script></td>
        </table>
    </body>
    
</html>
