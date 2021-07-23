<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : Portal
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
        <link rel="stylesheet" type="text/css" href="css/portal.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="css/module.css" media="screen" />
        <script type="text/javascript" src="js/calendario/calendar.js"></script>
        <script type="text/javascript" src="js/calendario/calendar-es.js"></script>
        <script type="text/javascript" src="js/calendario/calendar-setup.js"></script>
        <script type="text/javascript">
            animatedcollapse.addDiv('success', 'fade=1');
            animatedcollapse.addDiv('failure', 'fade=1');
            animatedcollapse.init();
            function downloadDoc(url) {
                window.location='<c:url value="/publicacionesdir.do"/>?url='+url+'&opcion=_descarga';
            }
            function onRowover(elem) {
                elem.className='colover';
            }
            function onRowout(elem) {
                elem.className='colout';
            }
        </script>

        <style type="text/css">
            #frm fieldset {
                border: 1px solid #999;width: 100%;
                border-radius:15px;
                -moz-box-shadow:0px 0px 20px #000000;
                padding: 5px;
            }
            #frm legend {
                font-size: 14px;
                padding: 0px 5px;
                margin-left: 15px;

            }
            #frm label {
                display: block;
                width: 100%;
                float: left;
            }
        </style>

    </head>
    <body>
        <div class="headercontent" align="center"><br>
            <font style="font-family: Arial Black, Helvetica, sans-serif; font-size: large;color: #003366">
                Colegio Santa Teresa
            </font>
        </div> 
        <table align="center" width="80%">
            <td style="width: 90%"  valign="top" align="center">
                <table width="100%" border="0" cellpadding="0" cellspacing="0" class="gridContent" align="center">



                    <tr>
                        <td style="font-weight: bold; size:13px" colspan="5" class="tableHeader" align="center">LISTA DE PUBLICACIONES</td>
                    </tr>
                    <td class="tableHeader" style="width:5%">Nº</td>
                        <td class="tableHeader" style="width:30%">Descripcion</td>
                        <td class="tableHeader" style="width:55%">Nota</td>
                        <td class="tableHeader" style="width:10%">Vigencia</td>
                    <c:forEach varStatus="j" var="it" items="${distintos}">
                        <tr>

                            <td style="font-weight: bold" colspan="5" class="gridHeaders" align="center">AREA:  ${it.area}</td>

                        </tr>
                        

                        <c:forEach varStatus="i" var="item" items="${documentos}">
                            <c:if test='${item.area==it.area}'>
                                <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)" bgcolor="#FFF">
                                    <td class="gridData">${i.count}</td>
                                    <td class="gridData"><a href="documentos/docs/${item.url}" target="_blank">${item.descripcion}</a></td>
                                    <td class="gridData">${item.nota}</td>
                                    <td class="gridData">${item.sfec_exp}</td>
                                </tr>
                            </c:if>

                        </c:forEach>
                    </c:forEach>
                </table>

            </td>
        </table>
    </body>
</html>
