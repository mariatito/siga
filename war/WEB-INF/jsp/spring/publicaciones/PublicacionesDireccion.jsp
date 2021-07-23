<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : Publicacionesdireccion
    Created on : 22-sep-2011, 23:15:49
    Author     : Antonio
--%>
<%@page contentType="text/html" pageEncoding="LATIN1"%>
<%@taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=LATIN1">
        <title>SIGAA - Santa Teresa</title>
        <link rel="stylesheet" href="imagenes/dhtmlmodal/windowfiles/dhtmlwindow.css" type="text/css" />
        <script type="text/javascript" src="imagenes/dhtmlmodal/windowfiles/dhtmlwindow.js"></script>
        <link rel="stylesheet" href="imagenes/dhtmlmodals/modalfiles/modal.css" type="text/css" />
        <script type="text/javascript" src="imagenes/dhtmlmodal/modalfiles/modal.js"></script>
        <script type="text/javascript" src="js/jquery-1.2.2.pack.js"></script>
        <script type="text/javascript" src="js/animatedcollapse.js"></script>    
        <link rel="stylesheet" type="text/css" href="css/module.css" media="screen" />


        <link rel="stylesheet" type="text/css" href="css/calendar-blue.css" media="screen" />
        <script type="text/javascript" src="js/calendario/calendar.js"></script>
        <script type="text/javascript" src="js/calendario/calendar-es.js"></script>
        <script type="text/javascript" src="js/calendario/calendar-setup.js"></script>
        <script type="text/javascript">
            animatedcollapse.addDiv('success', 'fade=1');
            animatedcollapse.addDiv('failure', 'fade=1');
            animatedcollapse.init();
            
          /*  function downloadDoc(url) {
                window.location='<c:url value="/publicacionesdir.do"/>?url='+url+'&opcion=_descarga';
            }*/
            function downloadDoc(url){
                var printResultado=dhtmlmodal.open('windowMAQV', 'iframe', '<c:url value="/publicacionesdir.do"/>?url='+url+'&opcion=_descarga', 'Reporte', 'width=650px,height=340px,left=100px,top=100px,resize=1,scrolling=1,center=1');
                printResultado.moveTo('middle', 'middle');
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
        <div class="headercontent">
            <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                <tr>
                    <td class="tab_init">&nbsp;</td>
                    <td style="width:50%">
                        <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                            <td class="tab_current">Publicar Documentos</td>
                        </table>
                    </td>
                    <td class="tab_fin">&nbsp;</td>
                </tr>
            </table>
            <div class="titlepage">PUBLICACIONES</div>
           
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

            <div align="center">

                <table width="100%" border="0">
                    <tr>
                        <td style="width: 30%" valign="top">
                            <form action="<c:url value="/publicacionesdir.do"/>" method="post" name="regForm" enctype="multipart/form-data">
                                <table width="100%" border="0" cellpadding="0" cellspacing="0" class="gridCohntent" align="center">
                                    <tr>
                                        <td>
                                            <div id="frm" style="width: 95%; padding: 3px;">
                                                <fieldset>
                                                    <legend style="font-weight: bold">Agregar nueva Publicacion</legend>

                                                    <c:if test="${!empty tpersona}">
                                                        <label>Cargo o Area:<br/>

                                                            <input class="text-field" type="text" name="persona" value="${tpersona.cargo}" maxlength="70" style="width:150px" onfocus="this.blur()"/>

                                                        </label>
                                                    </c:if>
                                                    <label>Descripcion:<br/>
                                                        <input class="text-field" type="text" name="descripcion" required placeholder="Descripcion..." autocomplete="off" maxlength="60"/>
                                                    </label>
                                                    <label>Observacion<br/>
                                                        <textarea class="text-field" name="nota" cols="50" rows="3" placeholder="Detalle u observaciones..."></textarea>
                                                    </label>
                                                    <label>Vigente hasta<br/>
                                                        <!--input class="text-field" type="date" name="dateexp"/-->
                                                        <input type="text" name="fec" id="fecha" readonly="readonly" value="${fec}"/>
                                                        <img src="imagenes/calendar.png" id="selector"/>
                                                        <script type="text/javascript">
                                                            Calendar.setup(
                                                            {
                                                                inputField : "fecha", // ID of the input field
                                                                ifFormat : "%d-%m-%Y", // the date format
                                                                button : "selector" // ID of the button
                                                            });
                                                        </script> 
                                                    </label>
                                                    <label>Archivo:<br/>
                                                        <input class="text-field" class="button-normal" type="file" name="fichero" required/>
                                                    </label>
                                                    <label style="text-align: center">
                                                        <button type="submit" class="button-normal"><img src="imagenes/iconos_sigaa/guardar_exc.png" width="12px">&nbsp;&nbsp;Guardar</button>
                                                    </label>
                                                </fieldset>
                                            </div> 
                                        </td>
                                    </tr>
                                </table>
                            </form>
                        </td>
                        <td style="width: 70%"  valign="top">
                            <table width="90%" border="0" cellpadding="0" cellspacing="0" class="gridContent" align="center">
                                <tr>
                                    <td style="font-weight: bold" colspan="6" class="tableHeader">LISTA DE PUBLICACIONES</td>
                                </tr>
                                <tr>
                                    <td class="gridHeaders" style="width:5%">Nº</td>
                                    <td class="gridHeaders" style="width:10%">Area</td>
                                    <td class="gridHeaders" style="width:35%">Descripcion</td>
                                    <td class="gridHeaders" style="width:40%">Nota</td>
                                    <td class="gridHeaders" style="width:10%">Vigencia</td>

                                    <c:forEach varStatus="i" var="item" items="${documentos}">
                                    <tr>
                                        <td class="gridData">${i.count}</td>
                                        <td class="gridData">${item.area}</td>
                                        <td class="gridData"><a href="documentos/docs/${item.url}" target="_blank">${item.descripcion}</a><!--span onclick="downloadDoc('${item.url}')" onmouseover="onRowover(this)" onmouseout="onRowout(this)">[DOC]</span>${item.descripcion}</td-->
                                        <td class="gridData">${item.nota}</td>
                                        <td class="gridData">${item.sfec_exp}</td>




                                    </tr>
                                </c:forEach>

                            </table>
                            <input type="hidden" name="url" value="${item.url}">
                        </td>
                    </tr>
                </table>
            </div>
        </div>




        <c:if test="${!empty mensaje}">
            <c:if test="${mensaje == 'true'}">
                <script type="text/javascript">
                    document.getElementById('success').style.display = 'inline';
                    setTimeout('animatedcollapse.hide(\'success\')',2500);
                </script>
            </c:if>
            <c:if test="${mensaje == 'false'}">
                <script type="text/javascript">
                    document.getElementById('failure').style.display = 'inline';
                    setTimeout('animatedcollapse.hide(\'failure\')',2500);
                </script>
            </c:if>
        </c:if>
    </body>
</html>
