<%-- 
    Document   : FatalErrorView
    Created on : 19-jul-2009, 11:54:22
    Author     : Marco Antonio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SIGAA - Santa teresa</title>
        <link rel="stylesheet" type="text/css" href="css/module.css" media="screen" />
    </head>
    <body>
        <div class="headercontent">
            <br><br>
            <div class="titlepage">SEGURIDAD</div>
        </div>
        
        <div class="titlepage">
            <h2 style="color: RED">Acceso no Autorizado</h2>
            <p>
                El <b>sistema</b> ha registrado un intento de acceso a recurso no autorizado, 
                por favor desista.&nbsp;&nbsp;Si est&aacute; seguro que no debe ver este aviso 
                favor comuniquese con la <b>Unidad de Sistemas</b>.
            </p>
        </div>
        
        <script type="text/javascript">
            var secs = 30;
            function stopApp() {
                document.getElementById('counter').innerHTML = 'Por razones \n\
                              de seguridad el sistema cerrar&aacute; los vinculos en: \n\
                              <span style="font-size: 16pt">0.'+secs+'seg </span><br><button class="button-submit" onclick="javascript:history.go(-1)">Cancelar</button>';
                setTimeout(stopApp,1000); secs --; 
                if (secs < 0)  window.open('<c:url value="/logout.do"/>','_top'); 
            }
            setTimeout(stopApp,1000);
        </script>
        <div class="maincontent">
            <div id="counter"></div>
        </div>
        
    </body>
</html>