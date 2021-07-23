<%-- 
    Document   : DepositosBancarios
    Created on : 08-02-2010, 02:28:19 PM
    Author     : Marco Antonio
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
        <link rel="stylesheet" href="imagenes/dhtmlmodal/windowfiles/dhtmlwindow.css" type="text/css" />
        <script type="text/javascript" src="imagenes/dhtmlmodal/windowfiles/dhtmlwindow.js"></script>
        <link rel="stylesheet" href="imagenes/dhtmlmodal/modalfiles/modal.css" type="text/css" />
        <script type="text/javascript" src="imagenes/dhtmlmodal/modalfiles/modal.js"></script>
        <script type="text/javascript" src="js/jquery-1.2.2.pack.js"></script>
        <script type="text/javascript" src="js/prototype.js"></script>
        <script type="text/javascript" src="js/Tips.js"></script>
        <script type="text/javascript">
            window.onload=function(){enableTooltips()};
        </script>
        <script type="text/javascript">
            animatedcollapse.addDiv('success', 'fade=1');
            animatedcollapse.addDiv('failure', 'fade=1');
            animatedcollapse.init();
        </script>
        <script type="text/javascript" src="js/animatedcollapse.js"></script>
        <link rel="stylesheet" type="text/css" href="css/module.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="css/st.css" media="screen" />
        <script type="text/javascript">
            function onRowover(elem) {
                elem.className='colover';
            }
            function onRowout(elem) {
                elem.className='colout';
            }
            function selectGestion(id_gestion){
                window.location = '<c:url value="/depositosBancarios.do"/>?id_gestion='+id_gestion;
            }
            function setGenerarExcel(){
                window.location = '<c:url value="/depositosBancarios.do"/>?id_gestion=${id_gestion}&imprimir=imprimirGen';
            }
            function selectCurso(opcion){
                if(opcion == 1) {
                    $('item-cursos').style.display = 'none';
                }
                if(opcion == 2) {
                    $('item-cursos').style.display = 'inline';
                }
            }
            function selectEstados(opcion){
                if(opcion == 1) {
                    $('item-estados').style.display = 'none';
                    $('item-allEstados').style.display = 'inline';
                    $('item-nrocuotas').style.display = 'none';
                }
                if(opcion == 2 || opcion==4) {
                    $('item-estados').style.display = 'inline';
                    $('item-allEstados').style.display = 'none';
                    $('item-nrocuotas').style.display = 'none';
                }
                if(opcion == 3 || opcion==5) {
                    $('item-estados').style.display = 'none';
                    $('item-allEstados').style.display = 'none';
                    $('item-nrocuotas').style.display = 'inline';
                }
            }
            function setReporteExcel(){
                window.location = '<c:url value="/depositosBancarios.do"/>?id_gestion=${id_gestion}&imprimir=imprimirGen';
            }
        </script>

    </head>
    <body>
        <div class="headercontent">
            <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                <tr>
                    <td class="tab_init">&nbsp;</td>
                    <td style="width:80%">
                        <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                            <tr>
                                <td class="tab_normal" onclick="javascript:window.location='<c:url value="/inscripcion.do"/>'">Inscripciones</td>
                                <td class="tab_normal" onclick="javascript:window.location='<c:url value="/asignacionPagos.do"/>'">Asignaci&oacute;n de pagos</td>
                                <td class="tab_current" style="border-right:1px solid #7D909E">Adm. dep&oacute;sitos bancarios</td>
                                <td class="tab_normal" onclick="javascript:window.location='<c:url value="/historialAcademico.do"/>'">Historial Acad&eacute;mico</td>
                                <td class="tab_normal" onclick="javascript:window.location='<c:url value="/inscripcionRezagada.do"/>'">Inscripciones rezagadas</td>
                            </tr>
                        </table>
                    </td>
                    <td class="tab_fin">&nbsp;</td>
                </tr>
            </table>
            <div class="titlepage">ESTADO DE DEP&Oacute;SITOS BANCARIOS ${id_gestion}</div>
        </div>
        <div class="maincontent">
            <c:if test="${!empty gestion}">
                <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                    <tr>
                        <td class="tableHeader">Inscripciones por gesti&oacute;n</td>
                    </tr>
                    <tr>
                        <td class="gridContent">
                            <table width="100%" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF">
                                <tr>
                                    <td class="gridHeaders" width="5%">Nro.</td>
                                    <td class="gridHeaders" width="10%">Gesti&oacute;n</td>
                                    <td class="gridHeaders" width="45%">Lema</td>
                                    <td class="gridHeaders" width="30%">Directora</td>
                                    <td class="gridHeaders" width="10%">Estado</td>
                                </tr>
                                <c:forEach varStatus="j" var="item" items="${gestion.gestiones}">
                                    <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)"onclick="selectGestion('${item.id_gestion}')"> <!--c:if test="$ {item.estado==true}"></c :if-->
                                        <td align="center" style="cursor:pointer" valign="top" class="gridData">${j.count}</td>
                                        <td align="center" style="cursor:pointer" valign="top" class="gridData">${item.id_gestion}</td>
                                        <td style="cursor:pointer" valign="top" class="gridData">${item.lema}</td>
                                        <td style="cursor:pointer" valign="top" class="gridData">${item.titulo} ${item.nombre}</td>
                                        <td align="center" style="cursor:pointer" valign="top" class="gridData"><c:if test="${item.estado=='true'}"><img src="imagenes/iconos_sigaa/activo_si.png" border="0" title="Estado activo"></c:if><c:if test="${item.estado=='false'}"><img src="imagenes/iconos_sigaa/activo_no.png" border="0" title="Estado inactivo"></c:if></td>
                                    </tr>
                                </c:forEach>
                            </table>

                        </td>
                    </tr>
                </table>
            </c:if>
            <c:if test="${!empty cargar}">
                <table cellpadding="0" cellspacing="0" border="0" style="width:100%" class="gridContent">
                    <tr>
                        <td width="70%">
                            <form action="<c:url value="/depositosBancarios.do"/>" method="post" name="formDepositos" enctype="multipart/form-data">
                                <input type="hidden" name="id_gestion" value="${id_gestion}">
                                Seleccione el archivo bancario a cargar(*.txt) : &nbsp;
                                <input type="file" name="archivo" size="50" accept="txt" class="button-normal">
                                <button type="submit" class="button-normal"><img src="imagenes/iconos_sigaa/guardar.png" width="11px">&nbsp;&nbsp;Guardar</button>
                            </form>
                            <form action="<c:url value="/depositosBancarios.do"/>" method="post">
                                <input type="hidden" name="id_gestion" value="${id_gestion}">
                                <input type="hidden" name="update" value="_update">
                                <button type="submit" class="button-normal"><img src="imagenes/iconos_sigaa/refresh.gif" width="11px">&nbsp;&nbsp;Actualizar</button>
                            </form>
                        </td>
                        <td width="30%" valign="top">
                            <div id="success" style="position: absolute; width: 250px; border: 0px solid green; padding: 0px; display: none">
                                <table>
                                    <tr style="background-color: #DEF5E6">
                                        <td>
                                            <img src="imagenes/iconos_sigaa/activo_si.png" border="0" width="15px">
                                            <span style="color: #000000; font-size: 10pt; font-weight: lighter">N&uacute;mero de datos registrados ${registrados}</span>
                                        </td>
                                    </tr>
                                    <tr style="background-color: #FFC1C1">
                                        <td>
                                            <img src="imagenes/iconos_sigaa/activo_pe.png" border="0" width="15px">
                                            <span style="color: #000000; font-size: 10pt; font-weight: lighter">N&uacute;mero de datos rechazados ${rechazados}</span>
                                        </td>
                                    </tr> 
                                </table>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <table width="100%">
                                <tr>
                                    <td class="tableHeader" width="100%">PERSONALIZADO</td>
                                </tr>
                                <tr>
                                    <td align="center">
                                        <form action="<c:url value="/depositosBancarios.do"/>" method="post">
                                            <table width="100%" bgcolor="#FFFFFF" style="font-size: 10px">
                                                <tr>
                                                    <td colspan="3">Para que la consulta sea exitosa, seleccionar a una opcion de:
                                                        Curso/s, Estado y Cuota/s</td>
                                                </tr>
                                                <tr>
                                                    <td width="55%" class="gridHeaders">Curso/s</td>
                                                    <td width="20%" class="gridHeaders">Estado</td>
                                                    <td width="25%" class="gridHeaders">Cuota/s</td>
                                                </tr>
                                                <tr>
                                                    <td valign="top">
                                                        <table width="100%">
                                                            <tr>
                                                                <td width="20%">Todos</td>
                                                                <td width="80%"><input type="radio" name="cursos" value="1" <c:if test="${cursos==1}"> checked </c:if> onchange="selectCurso(1)"></td>
                                                            </tr>
                                                            <tr>
                                                                <td>Cursos</td>
                                                                <td><input type="radio" name="cursos" value="2" <c:if test="${cursos==2}"> checked </c:if> onchange="selectCurso(2)"></td>
                                                            </tr>
                                                            <tr>
                                                                <td colspan="2">
                                                                    <div id="item-cursos" style="display:none">
                                                                        <table width="100%">
                                                                            <tr>

                                                                                <td width="5%" bgcolor="#F5FFF2"><input type="checkbox" name="K1" value="K1" <c:if test="${K=='K1'}"> checked </c:if>></td>
                                                                                <td width="20%" bgcolor="#F5FFF2"> Pre-Kinder</td>
                                                                                <td width="5%" bgcolor="#F5FFF2"><input type="checkbox" name="K" value="K" <c:if test="${K=='K'}"> checked </c:if>></td>
                                                                                <td width="20%" bgcolor="#F5FFF2">Kinder</td>
                                                                                <td width="5%" bgcolor="#F3F2FF"><input type="checkbox" name="P1" value="P1" <c:if test="${P1=='P1'}"> checked </c:if>></td>
                                                                                <td width="20%" bgcolor="#F3F2FF">1ro primaria</td>
                                                                                <td width="5%" bgcolor="#FFFFF2"><input type="checkbox" name="P6" value="P6" <c:if test="${P6=='P6'}"> checked </c:if>></td>
                                                                                <td width="20%" bgcolor="#FFFFF2">6to primaria</td>
                                                                                <td width="5%" bgcolor="#FFF3F2"><input type="checkbox" name="S1" value="S1" <c:if test="${S1=='S1'}"> checked </c:if>></td>
                                                                                <td width="20%" bgcolor="#FFF3F2">3ro secundaria</td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td colspan="2" bgcolor="#F5FFF2"></td>
                                                                                <td bgcolor="#F3F2FF"><input type="checkbox" name="P2" value="P2" <c:if test="${P2=='P2'}"> checked </c:if>></td>
                                                                                <td bgcolor="#F3F2FF">2do primaria</td>
                                                                                <td bgcolor="#FFFFF2"><input type="checkbox" name="P7" value="P7" <c:if test="${P7=='P7'}"> checked </c:if>></td>
                                                                                <td bgcolor="#FFFFF2">1ro secundaria</td>
                                                                                <td bgcolor="#FFF3F2"><input type="checkbox" name="S2" value="S2" <c:if test="${S2=='S2'}"> checked </c:if>></td>
                                                                                <td bgcolor="#FFF3F2">4to secundaria</td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td colspan="2" bgcolor="#F5FFF2"></td>
                                                                                <td bgcolor="#F3F2FF"><input type="checkbox" name="P3" value="P3" <c:if test="${P3=='P3'}"> checked </c:if>></td>
                                                                                <td bgcolor="#F3F2FF">3ro primaria</td>
                                                                                <td bgcolor="#FFFFF2"><input type="checkbox" name="P8" value="P8" <c:if test="${P8=='P8'}"> checked </c:if>></td>
                                                                                <td bgcolor="#FFFFF2">2do secundaria</td>
                                                                                <td bgcolor="#FFF3F2"><input type="checkbox" name="S3" value="S3" <c:if test="${S3=='S3'}"> checked </c:if>></td>
                                                                                <td bgcolor="#FFF3F2">5ro secundaria</td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td colspan="2" bgcolor="#F5FFF2"></td>                                                                                
                                                                                <td bgcolor="#F3F2FF"><input type="checkbox" name="P4" value="P4" <c:if test="${P4=='P4'}"> checked </c:if>></td>
                                                                                <td bgcolor="#F3F2FF">4to primaria</td>
                                                                                <td bgcolor="#FFFFF2" colspan="2"></td>                                                                               
                                                                                <td bgcolor="#FFF3F2"><input type="checkbox" name="S4" value="S4" <c:if test="${S4=='S4'}"> checked </c:if>></td>
                                                                                <td bgcolor="#FFF3F2">6to secundaria</td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td colspan="2" bgcolor="#F5FFF2"></td>                                                                                
                                                                                <td bgcolor="#F3F2FF"><input type="checkbox" name="P5" value="P5" <c:if test="${P5=='P5'}"> checked </c:if>></td>
                                                                                <td bgcolor="#F3F2FF">5to primaria</td>
                                                                                <td colspan="2" bgcolor="#FFFFF2"></td>
                                                                                <td colspan="2" bgcolor="#FFF3F2"></td>
                                                                            </tr>
                                                                        </table>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </td>
                                                    <td valign="top">
                                                        <table width="100%">
                                                            <tr>
                                                                <td width="60%">Todos</td>
                                                                <td width="40%"><input type="radio" name="estados" value="1" <c:if test="${estados==1}"> checked </c:if> onchange="selectEstados(1)"></td>
                                                            </tr>
                                                            <tr>
                                                                <td>Deudores</td>
                                                                <td><input type="radio" name="estados" value="2" <c:if test="${estados==2}"> checked </c:if> onchange="selectEstados(2)"></td>
                                                            </tr>
                                                            <tr>
                                                                <td>Deudores x NroCuota/s</td>
                                                                <td><input type="radio" name="estados" value="3" <c:if test="${estados==3}"> checked </c:if> onchange="selectEstados(3)"></td>
                                                            </tr>
                                                            <tr>
                                                                <td>Regulares</td>
                                                                <td><input type="radio" name="estados" value="4" <c:if test="${estados==4}"> checked </c:if> onchange="selectEstados(4)"></td>
                                                            </tr>
                                                            <tr>
                                                                <td>Regulares x NroCuota/s</td>
                                                                <td><input type="radio" name="estados" value="5" <c:if test="${estados==5}"> checked </c:if> onchange="selectEstados(5)"></td>
                                                            </tr>
                                                        </table>
                                                    </td>
                                                    <td valign="top">
                                                        <table width="100%">
                                                            <tr>
                                                                <td colspan="2">
                                                                    <div id="item-estados" style="display:none">
                                                                        <table width="100%" bgcolor="#FFF3F2">
                                                                            <tr>                                                                                
                                                                                <td width="5%"><input type="checkbox" name="C1" <c:if test="${C1=='C1'}"> checked </c:if>></td>
                                                                                <td width="45%">Cuota 1</td>
                                                                                <td width="5%"><input type="checkbox" name="C6" <c:if test="${C6=='C6'}"> checked </c:if>></td>
                                                                                <td width="45%">Cuota 6</td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td><input type="checkbox" name="C2" <c:if test="${C2=='C2'}"> checked </c:if>></td>
                                                                                <td>Cuota 2</td>
                                                                                <td><input type="checkbox" name="C7" <c:if test="${C7=='C7'}"> checked </c:if>></td>
                                                                                <td>Cuota 7</td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td><input type="checkbox" name="C3" <c:if test="${C3=='C3'}"> checked </c:if>></td>
                                                                                <td>Cuota 3</td>
                                                                                <td><input type="checkbox" name="C8" <c:if test="${C8=='C8'}"> checked </c:if>></td>
                                                                                <td>Cuota 8</td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td><input type="checkbox" name="C4" <c:if test="${C4=='C4'}"> checked </c:if>></td>
                                                                                <td>Cuota 4</td>
                                                                                <td><input type="checkbox" name="C9" <c:if test="${C9=='C9'}"> checked </c:if>></td>
                                                                                <td>Cuota 9</td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td><input type="checkbox" name="C5" <c:if test="${C5=='C5'}"> checked </c:if>></td>
                                                                                <td>Cuota 5</td>
                                                                                <td><input type="checkbox" name="C10" <c:if test="${C10=='C10'}"> checked </c:if>></td>
                                                                                <td>Cuota 10</td>
                                                                            </tr>
                                                                        </table>
                                                                    </div>
                                                                    <div id="item-allEstados" style="display:none">
                                                                        <table width="100%" bgcolor="#FFF3F2">
                                                                            <tr>
                                                                                <td width="5%"><img width="12px" src="imagenes/iconos_sigaa/chek_no.png"></td>
                                                                                <td width="45%">Cuota 1</td>
                                                                                <td width="5%"><img width="12px" src="imagenes/iconos_sigaa/chek_no.png"></td>
                                                                                <td width="45%">Cuota 6</td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td><img width="12px" src="imagenes/iconos_sigaa/chek_no.png"></td>
                                                                                <td>Cuota 2</td>
                                                                                <td><img width="12px" src="imagenes/iconos_sigaa/chek_no.png"></td>
                                                                                <td>Cuota 7</td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td><img width="12px" src="imagenes/iconos_sigaa/chek_no.png"></td>
                                                                                <td>Cuota 3</td>
                                                                                <td><img width="12px" src="imagenes/iconos_sigaa/chek_no.png"></td>
                                                                                <td>Cuota 8</td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td><img width="12px" src="imagenes/iconos_sigaa/chek_no.png"></td>
                                                                                <td>Cuota 4</td>
                                                                                <td><img width="12px" src="imagenes/iconos_sigaa/chek_no.png"></td>
                                                                                <td>Cuota 9</td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td><img width="12px" src="imagenes/iconos_sigaa/chek_no.png"></td>
                                                                                <td>Cuota 5</td>
                                                                                <td><img width="12px" src="imagenes/iconos_sigaa/chek_no.png"></td>
                                                                                <td>Cuota 10</td>
                                                                            </tr>
                                                                        </table>
                                                                    </div>
                                                                    <div id="item-nrocuotas" style="display:none">
                                                                        <table width="100%" bgcolor="#FFF3F2">
                                                                            <tr>
                                                                                <td width="75%">Ingrese Nro de Cuota/s : </td>
                                                                                <td width="25%"><input type="text" class="text-field" name="nrocuota" maxlength="2" style="width: 25px" value="${nrocuotas}"></td>
                                                                            </tr>
                                                                        </table>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="center" colspan="3">
                                                        <button class="button-normal" type="submit" name="tipo" value="consulta">Generar reporte</button>
                                                        <c:if test="${!empty consulta}">
                                                            <button class="button-normal" type="submit" name="tipo" value="impresion"><img width="11px" src="imagenes/iconos_sigaa/print.gif">Imprimir reporte...</button>
                                                            </c:if>
                                                        <input type="hidden" name="id_gestion" value="${id_gestion}">
                                                        <input type="hidden" name="opcion" value="_opcion1">
                                                    </td>
                                                </tr>
                                            </table>
                                            <script type="text/javascript">selectCurso('${cursos}');selectEstados('${estados}')</script>
                                        </form>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </c:if>
            <c:if test="${!empty reporteAll}">
                <table cellpadding="0" cellspacing="0" border="0" style="width:100%; cursor: pointer" class="gridContent">
                    <tr>
                        <td class="tableHeader" colspan="14">PLAN DE PAGOS (Expresado en bolivianos "Bs")</td>
                    </tr>
                    <tr>
                        <td colspan="14">
                            <button class="button-normal" onclick="setGenerarExcel()"><img width="11px" src="imagenes/iconos_sigaa/print.gif"> Imprimir archivo excel (Factura Bancaria)</button>
                            <input type="hidden" name="id_gestion" value="${id_gestion}">
                            <input type="hidden" name="opcion" value="_imprimir">
                        </td>
                    </tr>
                    <tr>
                        <td class="gridHeaders">Nro</td>
                        <td class="gridHeaders">C&oacute;digo</td>
                        <td class="gridHeaders">Apellidos y nombres</td>
                        <td class="gridHeaders">Curso</td>
                        <td class="gridHeaders" title="FEBRERO">Cuota1</td>
                        <td class="gridHeaders" title="MARZO">Cuota2</td>
                        <td class="gridHeaders" title="ABRIL">Cuota3</td>
                        <td class="gridHeaders" title="MAYO">Cuota4</td>
                        <td class="gridHeaders" title="JUNIO">Cuota5</td>
                        <td class="gridHeaders" title="JULIO">Cuota6</td>
                        <td class="gridHeaders" title="AGOSTO">Cuota7</td>
                        <td class="gridHeaders" title="SEPTIEMBRE">Cuota8</td>
                        <td class="gridHeaders" title="OCTUBRE">Cuota9</td>
                        <td class="gridHeaders" title="NOVIEMBRE">Cuota10</td>
                    </tr>
                    <c:forEach varStatus="j" var="item" items="${depositos}">
                        <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)" bgcolor="#FFFFFF">
                            <td class="gridData">${j.count}</td>
                            <td class="gridData">${item.codigo}</td>
                            <td class="gridData">${item.nombres}</td>
                            <td class="gridData" align="center">${item.id_curso}</td>
                            <c:if test="${item.nroCuotas==0}">
                                <td class="gridData" align="center" bgcolor="#FDC8C8" colspan="10">Falta comfirmar el plan de pagos...!</td>
                            </c:if>
                            <c:if test="${item.nroCuotas==2}">
                                <c:forEach varStatus="i" var="it" items="${item.depositosAsignadas}">
                                    <c:if test="${i.count==1}">
                                        <td class="gridData" align="right" title="${it.sfecha_fin}">${it.monto}</td>
                                    </c:if>
                                    <c:if test="${i.count==2}">
                                        <td class="gridData" align="right" title="${it.sfecha_fin}">${it.monto}</td>
                                        <td class="gridData" align="right"></td>
                                        <td class="gridData" align="right"></td>
                                        <td class="gridData" align="right"></td>
                                        <td class="gridData" align="right"></td>
                                        <td class="gridData" align="right"></td>
                                        <td class="gridData" align="right"></td>
                                        <td class="gridData" align="right"></td>
                                        <td class="gridData" align="right"></td>
                                    </c:if>
                                </c:forEach>
                            </c:if>
                            <c:if test="${item.nroCuotas==4}">
                                <c:forEach varStatus="i" var="it" items="${item.depositosAsignadas}">
                                    <c:if test="${i.count==1}">
                                        <td class="gridData" align="right" title="${it.sfecha_fin}">${it.monto}</td>
                                        <td class="gridData" align="right"></td>
                                    </c:if>
                                    <c:if test="${i.count==2}">
                                        <td class="gridData" align="right" title="${it.sfecha_fin}">${it.monto}</td>
                                        <td class="gridData" align="right"></td>
                                        <td class="gridData" align="right"></td>
                                    </c:if>
                                    <c:if test="${i.count==3}">
                                        <td class="gridData" align="right" title="${it.sfecha_fin}">${it.monto}</td>
                                        <td class="gridData" align="right"></td>
                                        <td class="gridData" align="right"></td>
                                    </c:if>
                                    <c:if test="${i.count==4}">
                                        <td class="gridData" align="right" title="${it.sfecha_fin}">${it.monto}</td>
                                        <td class="gridData" align="right"></td>
                                    </c:if>
                                </c:forEach>
                            </c:if>
                            <c:if test="${item.nroCuotas==10}">
                                <c:forEach varStatus="i" var="it" items="${item.depositosAsignadas}">
                                    <td class="gridData" align="right" title="${it.sfecha_fin}">${it.monto}</td>
                                </c:forEach>
                            </c:if>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
            <c:if test="${!empty reporte1}">
                <table cellpadding="0" cellspacing="0" border="0" style="width:100%; cursor: pointer" class="gridContent">
                    <tr>
                        <td class="tableHeader" colspan="13">PLAN DE PAGOS (Expresado en bolivianos "Bs")</td>
                    </tr>
                    <tr>
                        <td colspan="13">
                            <table cellpadding="0" cellspacing="0" style="width:100%;cursor: pointer; font-size: 8px" bgcolor="#FFFFFF">
                                <tr>
                                    <td width="10"></td>
                                    <td width="5%" bgcolor="#B7FFB7"></td>
                                    <td width="40%"> : Cuota cancelada.</td>
                                    <td width="5%" bgcolor="#FFFF91"></td>
                                    <td width="40%"> : Cuota cancelada en otra cuota.</td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td bgcolor="#FFCECE"></td>
                                    <td> : Cuota en mora.</td>
                                    <td align="center"><img width="8px" src="imagenes/iconos_sigaa/inf.gif"></td>
                                    <td> : Nota realizada sobre la cuota.</td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <c:if test="${empty consulta}">
                        <tr>
                            <td colspan="13" style="color:red" align="center">Verifique la consulta...</td>
                        </tr>
                    </c:if>
                    <c:forEach varStatus="i" var="item" items="${consulta}">
                        <tr>
                            <td class="gridHeaders" colspan="13" style="color: navy">CURSO : ${item.curso} de ${item.ciclo} <font size="1">(Numero de estudiantes :${item.numEstudiantes})</font></td>
                        </tr>
                        <tr  style="font-size: 10px">
                            <td class="gridTres">Nro</td>
                            <td class="gridTres">C&oacute;digo</td>
                            <td class="gridTres">Apellidos y nombres</td>
                            <td class="gridTres" title="FEBRERO">Cuota1</td>
                            <td class="gridTres" title="MARZO">Cuota2</td>
                            <td class="gridTres" title="ABRIL">Cuota3</td>
                            <td class="gridTres" title="MAYO">Cuota4</td>
                            <td class="gridTres" title="JUNIO">Cuota5</td>
                            <td class="gridTres" title="JULIO">Cuota6</td>
                            <td class="gridTres" title="AGOSTO">Cuota7</td>
                            <td class="gridTres" title="SEPTIEMBRE">Cuota8</td>
                            <td class="gridTres" title="OCTUBRE">Cuota9</td>
                            <td class="gridTres" title="NOVIEMBRE">Cuota10</td>
                        </tr>
                        <c:forEach varStatus="j" var="it" items="${item.estudiantes}">
                            <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)" bgcolor="#FFFFFF" style="font-size: 10px">
                                <td class="gridData">${j.count}</td>
                                <td class="gridData">${it.codigo}</td>
                                <td class="gridData">${it.paterno} ${it.materno}, ${it.nombres}</td>
                                <c:if test="${empty it.depAsignadas}">
                                    <td class="gridData" align="center" bgcolor="#FDC8C8" colspan="10">Falta comfirmar el plan de pagos...!</td>
                                </c:if>
                                <c:if test="${it.nroCuotas==2}">
                                    <c:forEach varStatus="l" var="ite" items="${it.depAsignadas}">
                                        <c:if test="${l.count==1}">
                                            <td class="gridData" <c:if test="${ite.estado_cuota=='cancelado' && ite.monto_dep==0}">bgcolor="#FFFF91"</c:if> <c:if test="${ite.estado_cuota=='cancelado' && ite.monto_dep!=0}">bgcolor="#B7FFB7"</c:if> bgcolor="#FFCECE" style="font-size: 8px">
                                                ${ite.monto_dep} / ${ite.monto}
                                                <a title="${ite.observacion}<c:if test="${ite.sfecha_dep!=null}"> [Fecha_deposito:${ite.sfecha_dep}]</c:if>">
                                                    <img onclick="openObservacion('')" width="8px" src="imagenes/iconos_sigaa/inf.gif">
                                                </a>
                                            </td>
                                        </c:if>
                                        <c:if test="${l.count==2}">
                                            <td class="gridData" <c:if test="${ite.estado_cuota=='cancelado' && ite.monto_dep==0}">bgcolor="#FFFF91"</c:if> <c:if test="${ite.estado_cuota=='cancelado' && ite.monto_dep!=0}">bgcolor="#B7FFB7"</c:if> bgcolor="#FFCECE" style="font-size: 8px">
                                                ${ite.monto_dep} / ${ite.monto}
                                                <a title="${ite.observacion}<c:if test="${ite.sfecha_dep!=null}"> [Fecha_deposito:${ite.sfecha_dep}]</c:if>">
                                                    <img onclick="openObservacion('')" width="8px" src="imagenes/iconos_sigaa/inf.gif">
                                                </a>
                                            </td>
                                            <td class="gridData" align="right"></td>
                                            <td class="gridData" align="right"></td>
                                            <td class="gridData" align="right"></td>
                                            <td class="gridData" align="right"></td>
                                            <td class="gridData" align="right"></td>
                                            <td class="gridData" align="right"></td>
                                            <td class="gridData" align="right"></td>
                                            <td class="gridData" align="right"></td>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${it.nroCuotas==4}">
                                    <c:forEach varStatus="l" var="ite" items="${it.depAsignadas}">
                                        <c:if test="${l.count==1}">
                                            <td class="gridData" <c:if test="${ite.estado_cuota=='cancelado' && ite.monto_dep==0}">bgcolor="#FFFF91"</c:if> <c:if test="${ite.estado_cuota=='cancelado' && ite.monto_dep!=0}">bgcolor="#B7FFB7"</c:if> bgcolor="#FFCECE" style="font-size: 8px">
                                                ${ite.monto_dep} / ${ite.monto}
                                                <a title="${ite.observacion}<c:if test="${ite.sfecha_dep!=null}"> [Fecha_deposito:${ite.sfecha_dep}]</c:if>">
                                                    <img onclick="openObservacion('')" width="8px" src="imagenes/iconos_sigaa/inf.gif">
                                                </a>
                                            </td>
                                            <td class="gridData" align="right"></td>
                                        </c:if>
                                        <c:if test="${l.count==2}">
                                            <td class="gridData" <c:if test="${ite.estado_cuota=='cancelado' && ite.monto_dep==0}">bgcolor="#FFFF91"</c:if> <c:if test="${ite.estado_cuota=='cancelado' && ite.monto_dep!=0}">bgcolor="#B7FFB7"</c:if> bgcolor="#FFCECE" style="font-size: 8px">
                                                ${ite.monto_dep} / ${ite.monto}
                                                <a title="${ite.observacion}<c:if test="${ite.sfecha_dep!=null}"> [Fecha_deposito:${ite.sfecha_dep}]</c:if>">
                                                    <img onclick="openObservacion('')" width="8px" src="imagenes/iconos_sigaa/inf.gif">
                                                </a>
                                            </td>
                                            <td class="gridData" align="right"></td>
                                            <td class="gridData" align="right"></td>
                                        </c:if>
                                        <c:if test="${l.count==3}">
                                            <td class="gridData" <c:if test="${ite.estado_cuota=='cancelado' && ite.monto_dep==0}">bgcolor="#FFFF91"</c:if> <c:if test="${ite.estado_cuota=='cancelado' && ite.monto_dep!=0}">bgcolor="#B7FFB7"</c:if> bgcolor="#FFCECE" style="font-size: 8px">
                                                ${ite.monto_dep} / ${ite.monto}
                                                <a title="${ite.observacion}<c:if test="${ite.sfecha_dep!=null}"> [Fecha_deposito:${ite.sfecha_dep}]</c:if>">
                                                    <img onclick="openObservacion('')" width="8px" src="imagenes/iconos_sigaa/inf.gif">
                                                </a>
                                            </td>
                                            <td class="gridData" align="right"></td>
                                            <td class="gridData" align="right"></td>
                                        </c:if>
                                        <c:if test="${l.count==4}">
                                            <td class="gridData" <c:if test="${ite.estado_cuota=='cancelado' && ite.monto_dep==0}">bgcolor="#FFFF91"</c:if> <c:if test="${ite.estado_cuota=='cancelado' && ite.monto_dep!=0}">bgcolor="#B7FFB7"</c:if> bgcolor="#FFCECE" style="font-size: 8px">
                                                ${ite.monto_dep} / ${ite.monto}
                                                <a title="${ite.observacion}<c:if test="${ite.sfecha_dep!=null}"> [Fecha_deposito:${ite.sfecha_dep}]</c:if>">
                                                    <img onclick="openObservacion('')" width="8px" src="imagenes/iconos_sigaa/inf.gif">
                                                </a>
                                            </td>
                                            <td class="gridData" align="right"></td>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${it.nroCuotas==10}">
                                    <c:forEach varStatus="l" var="ite" items="${it.depAsignadas}">
                                        <td class="gridData" <c:if test="${ite.estado_cuota=='cancelado' && ite.monto_dep==0}">bgcolor="#FFFF91"</c:if> <c:if test="${ite.estado_cuota=='cancelado' && ite.monto_dep!=0}">bgcolor="#B7FFB7"</c:if> bgcolor="#FFCECE" style="font-size: 8px">
                                            ${ite.monto_dep} / ${ite.monto}
                                            <a title="${ite.observacion}<c:if test="${ite.sfecha_dep!=null}"> [Fecha_deposito:${ite.sfecha_dep}]</c:if>">
                                                <img onclick="openObservacion('')" width="8px" src="imagenes/iconos_sigaa/inf.gif">
                                            </a>
                                        </td>
                                    </c:forEach>
                                </c:if>
                            </tr>
                        </c:forEach>
                    </c:forEach>
                    <tr>
                        <td>
                            <br><br><br><br>
                        </td>
                    </tr>
                </table>
            </c:if>
            <c:if test="${!empty reporte2}">
                <table cellpadding="0" cellspacing="0" border="0" style="width:100%; cursor: pointer" class="gridContent">
                    <tr>
                        <td class="tableHeader" colspan="13">PLAN DE PAGOS (Expresado en bolivianos "Bs")</td>
                    </tr>
                    <tr>
                        <td colspan="3">
                            <button class="button-normal" onclick="setGenerarExcel()"><img width="11px" src="imagenes/iconos_sigaa/print.gif">Imprimir</button>
                        </td>
                        <td colspan="10">
                            <table cellpadding="0" cellspacing="0" style="width:100%;cursor: pointer; font-size: 8px" bgcolor="#FFFFFF">
                                <tr>
                                    <td width="10"></td>
                                    <td width="5%" bgcolor="#B7FFB7"></td>
                                    <td width="40%"> : Cuota cancelada.</td>
                                    <td width="5%" bgcolor="#FFFF91"></td>
                                    <td width="40%"> : Cuota cancelada en otra cuota.</td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td bgcolor="#FFCECE"></td>
                                    <td> : Cuota en mora.</td>
                                    <td align="center"><img width="8px" src="imagenes/iconos_sigaa/inf.gif"></td>
                                    <td> : Nota realizada sobre la cuota.</td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <c:if test="${empty consulta}">
                        <tr>
                            <td colspan="13" style="color:red" align="center">Verifique la consulta...</td>
                        </tr>
                    </c:if>
                    <c:forEach varStatus="i" var="item" items="${consulta}">
                        <tr>
                            <td class="gridHeaders" colspan="13" style="color: navy">CURSO : ${item.curso} de ${item.ciclo} <font size="1">(Numero de estudiantes :${item.numEstudiantes})</font></td>
                        </tr>
                        <tr  style="font-size: 10px">
                            <td class="gridTres">Nro</td>
                            <td class="gridTres">C&oacute;digo</td>
                            <td class="gridTres">Apellidos y nombres</td>
                            <td class="gridTres" title="FEBRERO">Cuota1</td>
                            <td class="gridTres" title="MARZO">Cuota2</td>
                            <td class="gridTres" title="ABRIL">Cuota3</td>
                            <td class="gridTres" title="MAYO">Cuota4</td>
                            <td class="gridTres" title="JUNIO">Cuota5</td>
                            <td class="gridTres" title="JULIO">Cuota6</td>
                            <td class="gridTres" title="AGOSTO">Cuota7</td>
                            <td class="gridTres" title="SEPTIEMBRE">Cuota8</td>
                            <td class="gridTres" title="OCTUBRE">Cuota9</td>
                            <td class="gridTres" title="NOVIEMBRE">Cuota10</td>
                        </tr>
                        <c:forEach varStatus="j" var="it" items="${item.estudiantes}">
                            <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)" bgcolor="#FFFFFF" style="font-size: 10px">
                                <td class="gridData">${j.count}</td>
                                <td class="gridData">${it.codigo}</td>
                                <td class="gridData">${it.paterno} ${it.materno}, ${it.nombres}</td>
                                <c:if test="${empty it.depAsignadas}">
                                    <td class="gridData" align="center" bgcolor="#FDC8C8" colspan="10">Falta comfirmar el plan de pagos...!</td>
                                </c:if>
                                <c:if test="${it.nroCuotas==2}">
                                    <c:forEach varStatus="l" var="ite" items="${it.depAsignadas}">
                                        <c:if test="${l.count==1}">
                                            <td class="gridData" <c:if test="${ite.estado_cuota=='cancelado' && ite.monto_dep==0}">bgcolor="#FFFF91"</c:if> <c:if test="${ite.estado_cuota=='cancelado' && ite.monto_dep!=0}">bgcolor="#B7FFB7"</c:if> bgcolor="#FFCECE" style="font-size: 8px">
                                                ${ite.monto_dep} / ${ite.monto}
                                                <a title="${ite.observacion}<c:if test="${ite.sfecha_dep!=null}"> [Fecha_deposito:${ite.sfecha_dep}]</c:if>">
                                                    <img onclick="openObservacion('')" width="8px" src="imagenes/iconos_sigaa/inf.gif">
                                                </a>
                                            </td>
                                        </c:if>
                                        <c:if test="${l.count==2}">
                                            <td class="gridData" <c:if test="${ite.estado_cuota=='cancelado' && ite.monto_dep==0}">bgcolor="#FFFF91"</c:if> <c:if test="${ite.estado_cuota=='cancelado' && ite.monto_dep!=0}">bgcolor="#B7FFB7"</c:if> bgcolor="#FFCECE" style="font-size: 8px">
                                                ${ite.monto_dep} / ${ite.monto}
                                                <a title="${ite.observacion}<c:if test="${ite.sfecha_dep!=null}"> [Fecha_deposito:${ite.sfecha_dep}]</c:if>">
                                                    <img onclick="openObservacion('')" width="8px" src="imagenes/iconos_sigaa/inf.gif">
                                                </a>
                                            </td>
                                            <td class="gridData" align="right"></td>
                                            <td class="gridData" align="right"></td>
                                            <td class="gridData" align="right"></td>
                                            <td class="gridData" align="right"></td>
                                            <td class="gridData" align="right"></td>
                                            <td class="gridData" align="right"></td>
                                            <td class="gridData" align="right"></td>
                                            <td class="gridData" align="right"></td>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${it.nroCuotas==4}">
                                    <c:forEach varStatus="l" var="ite" items="${it.depAsignadas}">
                                        <c:if test="${l.count==1}">
                                            <td class="gridData" <c:if test="${ite.estado_cuota=='cancelado' && ite.monto_dep==0}">bgcolor="#FFFF91"</c:if> <c:if test="${ite.estado_cuota=='cancelado' && ite.monto_dep!=0}">bgcolor="#B7FFB7"</c:if> bgcolor="#FFCECE" style="font-size: 8px">
                                                ${ite.monto_dep} / ${ite.monto}
                                                <a title="${ite.observacion}<c:if test="${ite.sfecha_dep!=null}"> [Fecha_deposito:${ite.sfecha_dep}]</c:if>">
                                                    <img onclick="openObservacion('')" width="8px" src="imagenes/iconos_sigaa/inf.gif">
                                                </a>
                                            </td>
                                            <td class="gridData" align="right"></td>
                                        </c:if>
                                        <c:if test="${l.count==2}">
                                            <td class="gridData" <c:if test="${ite.estado_cuota=='cancelado' && ite.monto_dep==0}">bgcolor="#FFFF91"</c:if> <c:if test="${ite.estado_cuota=='cancelado' && ite.monto_dep!=0}">bgcolor="#B7FFB7"</c:if> bgcolor="#FFCECE" style="font-size: 8px">
                                                ${ite.monto_dep} / ${ite.monto}
                                                <a title="${ite.observacion}<c:if test="${ite.sfecha_dep!=null}"> [Fecha_deposito:${ite.sfecha_dep}]</c:if>">
                                                    <img onclick="openObservacion('')" width="8px" src="imagenes/iconos_sigaa/inf.gif">
                                                </a>
                                            </td>
                                            <td class="gridData" align="right"></td>
                                            <td class="gridData" align="right"></td>
                                        </c:if>
                                        <c:if test="${l.count==3}">
                                            <td class="gridData" <c:if test="${ite.estado_cuota=='cancelado' && ite.monto_dep==0}">bgcolor="#FFFF91"</c:if> <c:if test="${ite.estado_cuota=='cancelado' && ite.monto_dep!=0}">bgcolor="#B7FFB7"</c:if> bgcolor="#FFCECE" style="font-size: 8px">
                                                ${ite.monto_dep} / ${ite.monto}
                                                <a title="${ite.observacion}<c:if test="${ite.sfecha_dep!=null}"> [Fecha_deposito:${ite.sfecha_dep}]</c:if>">
                                                    <img onclick="openObservacion('')" width="8px" src="imagenes/iconos_sigaa/inf.gif">
                                                </a>
                                            </td>
                                            <td class="gridData" align="right"></td>
                                            <td class="gridData" align="right"></td>
                                        </c:if>
                                        <c:if test="${l.count==4}">
                                            <td class="gridData" <c:if test="${ite.estado_cuota=='cancelado' && ite.monto_dep==0}">bgcolor="#FFFF91"</c:if> <c:if test="${ite.estado_cuota=='cancelado' && ite.monto_dep!=0}">bgcolor="#B7FFB7"</c:if> bgcolor="#FFCECE" style="font-size: 8px">
                                                ${ite.monto_dep} / ${ite.monto}
                                                <a title="${ite.observacion}<c:if test="${ite.sfecha_dep!=null}"> [Fecha_deposito:${ite.sfecha_dep}]</c:if>">
                                                    <img onclick="openObservacion('')" width="8px" src="imagenes/iconos_sigaa/inf.gif">
                                                </a>
                                            </td>
                                            <td class="gridData" align="right"></td>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${it.nroCuotas==10}">
                                    <c:forEach varStatus="l" var="ite" items="${it.depAsignadas}">
                                        <td class="gridData" <c:if test="${ite.estado_cuota=='cancelado' && ite.monto_dep==0}">bgcolor="#FFFF91"</c:if> <c:if test="${ite.estado_cuota=='cancelado' && ite.monto_dep!=0}">bgcolor="#B7FFB7"</c:if> bgcolor="#FFCECE" style="font-size: 8px">
                                            ${ite.monto_dep} / ${ite.monto}
                                            <a title="${ite.observacion}<c:if test="${ite.sfecha_dep!=null}"> [Fecha_deposito:${ite.sfecha_dep}]</c:if>">
                                                <img onclick="openObservacion('')" width="8px" src="imagenes/iconos_sigaa/inf.gif">
                                            </a>
                                        </td>
                                    </c:forEach>
                                </c:if>
                            </tr>
                        </c:forEach>
                    </c:forEach>
                    <tr>
                        <td>
                            <br><br><br><br>
                        </td>
                    </tr>
                </table>
            </c:if>
        </div>
        <c:if test="${!empty mensaje}">
            <script type="text/javascript">
                document.getElementById('success').style.display = 'inline';
                setTimeout('animatedcollapse.hide(\'success\')',1000);
            </script>
        </c:if>
    </body>
</html>