<%-- 
    Document   : TestChaside
    Created on : 01-may-2010, 18:09:46
    Author     : MARCO
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
        <script type="text/javascript" src="js/ajax.js"></script>
        <link rel="stylesheet" type="text/css" href="css/module.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="css/st.css" media="screen" />
        <script type="text/javascript" src="js/prototype.js"></script>

        <script type="text/javascript">
            function onRowover(elem) { elem.className='colover'; }
            function onRowout(elem) { elem.className='colout'; }

            function setNuevoTest(){
                window.location = '<c:url value="/testChaside.do"/>?nuevo=_nuevo';
            }
            /*
            function selectCurso(id_curso){
                window.location = '<c:url value="/historialAcademico.do"/>?id_curso='+id_curso+'&id_gestion=${id_gestion}';
            }
            function popupWindow(u) {
                window.open(u,'','toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes,copyhistory=no,width=800,height=530,screenX=50,screenY=50,top=50,left=350');
            }
            function selectEstudiante(id_curso,id_estudiante,id_familia){
                popupWindow('<c:url value="/historialAcademico.do"/>?id_curso='+id_curso+'&id_estudiante='+id_estudiante+'&id_gestion=${id_gestion}&opcion=HistorialEstudiante&id_familia='+id_familia);
            }
            function setImprimirExcel() {
                window.location = '<c:url value="/historialAcademico.do"/>?id_curso=${curso.id_curso}&id_gestion=${id_gestion}&opcion=_imprimeExcel';
            }*/
            function setActivarRadio(id){
                if($('si-'+id).checked==true || $('no-'+id).checked==true){
                    $('res-'+id).innerHTML='';
                }else{
                    $('res-'+id).innerHTML='<font style=" font-size:smaller;color: green">Debe responer la pregunta...</font>';
                }
            }
        </script>

    </head>
    <body>
        <div class="headercontent">
            <div class="titlepage">Orientaci&oacute;n vocacional</div>
        </div>
        <div class="maincontent">
            <c:if test="${!empty resultados}">
                <table cellpadding="0" cellspacing="0" border="0" style="width: 50% ; cursor:pointer" align="center">
                    <tr>
                        <td  colspan="4" align="center" style="color: black; font-size: large">RESULTADOS DEL TEST DE ORIENTACI&Oacute;N VOCACIONAL OCUPACIONAL <strong>"CHASIDE"</strong></td>
                    </tr>
                    <tr>
                        <td colspan="4"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                    </tr>
                    <tr>
                        <td colspan="4"><button class="button-normal"  onclick="setNuevoTest()" title="Repetir el test vocacional chaside">Nuevo test</button></td>
                    </tr>
                    <tr>
                        <td colspan="4" bgcolor="#008C8C" style="color: navy; font-size: large">&Aacute;reas e intereses</td>
                    </tr>
                    <c:forEach var="item" items="${intereses}" varStatus="i">
                        <tr style=" color: black">
                            <td width="5%" align="center" valign="top"><strong>${i.count}</strong></td>
                            <td style=" font-size:larger; color: black" colspan="3"width="95%"><strong>${item.area}</strong>
                                <table cellpadding="0" cellspacing="0" border="0" style="width: 100% ; cursor:pointer">
                                    <tr>
                                        <td style="color: teal" colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;INTERESES</td>
                                    </tr>
                                    <c:forEach var="it" items="${item.intereses}" varStatus="i">
                                        <tr>
                                            <td width="5%"></td>
                                            <td width="95%"><img src="imagenes/cxc.png">&nbsp;&nbsp;${it.valor}</td>
                                        </tr>
                                    </c:forEach>

                                </table>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="4"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                    </tr>
                    <tr>
                        <td colspan="4" bgcolor="#008C8C" style="color: navy; font-size: large">&Aacute;reas y aptitudes</td>
                    </tr>
                    <c:forEach var="item" items="${aptitudes}" varStatus="i">
                        <tr style=" color: black">
                            <td width="5%" align="center" valign="top"><strong>${i.count}</strong></td>
                            <td style=" font-size:larger; color: black" colspan="3"width="95%"><strong>${item.area}</strong>
                                <table cellpadding="0" cellspacing="0" border="0" style="width: 100% ; cursor:pointer">
                                    <tr>
                                        <td style="color: teal" colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;INTERESES</td>
                                    </tr>
                                    <c:forEach var="it" items="${item.aptitudes}" varStatus="i">
                                        <tr>
                                            <td width="5%"></td>
                                            <td width="95%"><img src="imagenes/cxc.png">&nbsp;&nbsp;${it.valor}</td>
                                        </tr>
                                    </c:forEach>

                                </table>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
            <c:if test="${!empty preguntas_chaside}">
                <form action="<c:url value="/testChaside.do"/>" method="post">
                    <table cellpadding="0" cellspacing="0" border="0" style="width: 100% ; cursor:pointer">
                        <tr>
                            <td  colspan="3" align="center" style="color: black; font-size: large">TEST DE ORIENTACI&Oacute;N VOCACIONAL OCUPACIONAL <strong>"CHASIDE"</strong></td>
                        </tr>
                        <tr>
                            <td colspan="3">a) Lee atentamente cada pregunta</td>
                        </tr>
                        <tr>
                            <td colspan="3">b) En las columnas de la derecha responda realizando un clic en la opci&oacute;n para las respuestas afirmativas, y las casillas no marcadas para las respuestas negativas.</td>
                        </tr>
                        <tr>
                            <td colspan="3">c) Conteste a todas las preguntas sin omitir ninguna.</td>
                        </tr>
                        <tr>
                            <td width="5%" class="gridHeaders">NRO</td>
                            <td width="89%" class="gridHeaders">PREGUNTA</td>
                            <td width="6%" class="gridHeaders">SI</td>
                            <!--td width="3%" class="gridHeaders">NO</td-->
                        </tr>
                        <c:forEach var="item" items="${preguntas_chaside}" varStatus="i">
                            <tr bgcolor="#ffffff" style="cursor:pointer; color: black;" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                <td>${item.id} . - </td>
                                <td style=" font-size:larger">${item.valor}
                                    <!--input type="text" name="id_pregunta-${item.id}" value="${item.id}"/-->
                                    <input type="hidden" name="id_chaside-${item.id}" value="${item.abreviatura}"/>
                                </td>
                                <!--td style=" font-size:smaller;color: red" id="res-${item.id}" width="10%">Debe responer...</td-->
                                <td bgcolor="#A6FFAC" title="SI" align="center"><input type="checkbox" id="si-${item.id}" name="res-${item.id}"/></td>
                                <!--td bgcolor="#FDA8A8" title="NO" align="center"><input type="radio" id="no-${item.id}" name="res-${item.id}" onclick="setActivarRadio('${item.id}')"/></td-->
                            </tr>
                        </c:forEach>
                        <tr>
                            <td colspan="3" align="center"><input type="submit" name="evaluacion" value="Ver resultado.."></td>
                        </tr>
                    </table>
                </form>
            </c:if>
        </div>
    </body>
</html>
