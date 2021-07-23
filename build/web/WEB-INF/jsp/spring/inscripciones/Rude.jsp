<%-- 
    Document   : Rude
    Created on : 13-Ene-2014, 21:09:51
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
        <script type="text/javascript" src="js/jquery-1.10.1.min.js"></script>
        <script src="jquery_ui/jquery-ui-1.10.3.js" type="text/javascript"></script>
        <link href="jquery_ui/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
        <script src="jquery_ui/ui/jquery.ui.datepicker-es.js" type="text/javascript"></script>
        <style>
            body{
                background: rgb(245,245,245);
                font-family: sans-serif;
                font-size: 9pt;
                color: #002c7d;
            }
            h2{font-size: 15pt;}
            h3{font-size: 12pt;}
            .edit input{font-weight: bold;background:rgb(245,245,245)!important}
            .noedit input{color: rgb(128,128,128)}
            .input-small{ width: 70px}
            .input-medium{width: 150px}
            .input-large{width: 200px}
            .input-xxxlarge{width: 300px}
            .input-wrap{width: 100%}
            .input-letter{width: 18px;text-align: center;padding: initial;}
            .subtitulo1{font-weight: bold;font-size: 11pt}
            .subtitulo2{font-weight: bold;font-size: 10pt}
            .table{border: 1px solid rgb(180,180,180);padding: 5px;background: #fff}
            .botones{
                background: #002c7d;
    color: white;
            }
        </style>
        <script>
            $(function () {
                $("#datepicker1").datepicker();
                $("#datepicker2").datepicker();
                $("#datepicker3").datepicker();
                $("#datepicker4").datepicker();
                $("#datepicker5").datepicker();
                $(".guardar").button({
                    icons: {
                        primary: "ui-icon-disk"
                    }
                });
                $(".imprimir").button({
                    icons: {
                        primary: "ui-icon-print"
                    }
                });
            });
        </script>
    </head>
    <body>
        <h2 style="text-align: center">Registro Unico del Estudiante</h2>
                <c:if test="${post=='si'}">
            <form action="" method="POST">
                <input name="opcion" value="imprimir" type="hidden"/>
                <input name="id_est" value="${estudiante.id_estudiante}" type="hidden"/>
                <center><button class="imprimir botones">Imprimir</button></center>
            </form>
        </c:if>
        <c:if test="${edit=='si'}">
            <form action="" method="POST" class="edit">
                <div style="width: 98%;margin: 0 auto;padding: 10px;min-width: 1000px" class="ui-corner-all">
                    <input type="hidden" name="id_estudiante" value="${id_estudiante}">
                    <table style="width: 95%;margin: 0 auto;">
                        <tr>
                            <td style="width: 50%;text-align: right;display: none">Nro</td>
                            <td style="width: 50%"><input type="hidden" name="nro" value="${estudiante.nro}" class="text ui-widget-content ui-corner-all input-small"></td>
                        </tr>
                        <tr>
                            <td style="text-align: right">Codigo SIE de la Unidad Educativa</td>
                            <td>
                                <input type="text" name="cie1" value="${cie1}" disabled class="text ui-widget-content ui-corner-all input-letter" maxlength="1">
                                <input type="text" name="cie2" value="${cie2}" disabled class="text ui-widget-content ui-corner-all input-letter" maxlength="1">
                                <input type="text" name="cie3" value="${cie3}" disabled class="text ui-widget-content ui-corner-all input-letter" maxlength="1">
                                <input type="text" name="cie4" value="${cie4}" disabled class="text ui-widget-content ui-corner-all input-letter" maxlength="1">
                                <input type="text" name="cie5" value="${cie5}" disabled class="text ui-widget-content ui-corner-all input-letter" maxlength="1">
                                <input type="text" name="cie6" value="${cie6}" disabled class="text ui-widget-content ui-corner-all input-letter" maxlength="1">
                                <input type="text" name="cie7" value="${cie7}" disabled class="text ui-widget-content ui-corner-all input-letter" maxlength="1">
                                <input type="text" name="cie8" value="${cie8}" disabled class="text ui-widget-content ui-corner-all input-letter" maxlength="1">
                            </td>
                        </tr>
                    </table>

                    <h3>I. DATOS DE LA UNIDAD EDUCATIVA</h3>
                    <table style="width: 50%;margin: 0 auto" class="table">
                        <tr>
                            <td>
                                <span class="subtitulo1"> 1.1 Nombre de la Unidad Educativa</span>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <input type="text" name="colegio" value="${informacion.colegio}" disabled class="text ui-widget-content ui-corner-all input-wrap">
                            </td>
                        </tr>
                    </table><h3>II. DATOS DE LA O EL ESTUDIANTE (Solo debe ser llenado para estudiantes nuevos en la Unidad Educativa)</h3>
                    <table style="width: 50%;margin: 0 auto" class="table">
                        <tr>
                            <td colspan="2">
                                <span class="subtitulo1"> 2.1 Apellido(s) y Nombre(s)</span>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: right">Apellido Paterno</td>
                            <td>
                                <input type="text" name="paterno" value="${estudiante.paterno}" class="text ui-widget-content ui-corner-all input-wrap">
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: right">Apellido Materno</td>
                            <td>
                                <input type="text" name="materno" value="${estudiante.materno}" class="text ui-widget-content ui-corner-all input-wrap">
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: right">Nombre(s)</td>
                            <td>
                                <input type="text" name="nombres" value="${estudiante.nombres}" class="text ui-widget-content ui-corner-all input-wrap">
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <span class="subtitulo1"> 2.2 Lugar de Nacimiento</span>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: right">Pa&iacute;s</td>
                            <td>
                                <input type="text" name="pais" value="${estudiante.pais}" class="text ui-widget-content ui-corner-all input-wrap">
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: right">Departamento</td>
                            <td>
                                <input type="text" name="departamento" value="${estudiante.departamento}" class="text ui-widget-content ui-corner-all input-wrap">
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: right">Provincia</td>
                            <td>
                                <input type="text" name="provincia" value="${estudiante.provincia}" class="text ui-widget-content ui-corner-all input-wrap">
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: right">Localidad</td>
                            <td>
                                <input type="text" name="localidad" value="${estudiante.localidad}" class="text ui-widget-content ui-corner-all input-wrap">
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <span class="subtitulo1"> (**)2.3 Certificado de Nacimiento</span>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: right">Oficial&iacute;a N&deg;</td>
                            <td>
                                <input type="text" name="oficialia" value="${estudiante.oficialia}" class="text ui-widget-content ui-corner-all input-wrap">
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: right">Libro N&deg;</td>
                            <td>
                                <input type="text" name="libro" value="${estudiante.libro}" class="text ui-widget-content ui-corner-all input-wrap">
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: right">Partida N&deg;</td>
                            <td>
                                <input type="text" name="partida" value="${estudiante.partida}" class="text ui-widget-content ui-corner-all input-wrap">
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: right">Folio N&deg;</td>
                            <td>
                                <input type="text" name="folio" value="${estudiante.folio}" class="text ui-widget-content ui-corner-all input-wrap">
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <span class="subtitulo1"> 2.4 Fecha de Nacimiento</span>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: right">Fecha</td>
                            <td>
                                <input type="text" name="fecha_nacimiento" id="datepicker2" runat="server" value="${fecha_nacimiento}" class="text ui-widget-content ui-corner-all input-medium" readonly/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <span class="subtitulo1"> (?)2.5 Documento de identificaci&oacute;n</span>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: right">Carnet de Identidad</td>
                            <td>
                                <input type="text" name="carnet" value="${estudiante.carnet}" class="text ui-widget-content ui-corner-all input-medium">
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: right">Complemento</td>
                            <td>
                                <input type="text" name="complemento" value="${estudiante.complemento}" class="text ui-widget-content ui-corner-al input-small">

                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: right">Expedido</td>
                            <td>
                                <select name="expedido" class="text ui-widget-content ui-corner-all input-medium">
                                    <option value="LP"<c:if test="${estudiante.expedido=='LP'}"> selected </c:if>>La Paz</option>
                                    <option value="SC"<c:if test="${estudiante.expedido=='SC'}"> selected </c:if>>Santa Cruz</option>
                                    <option value="CB"<c:if test="${estudiante.expedido=='CB'}"> selected </c:if>>Cochabamba</option>
                                    <option value="PT"<c:if test="${estudiante.expedido=='PT'}"> selected </c:if>>Potosi</option>
                                    <option value="TJ"<c:if test="${estudiante.expedido=='TJ'}"> selected </c:if>>Tarija</option>
                                    <option value="OR"<c:if test="${estudiante.expedido=='OR'}"> selected </c:if>>Oruro</option>
                                    <option value="CH"<c:if test="${estudiante.expedido=='CH'}"> selected </c:if>>Chuquisaca</option>
                                    <option value="BE"<c:if test="${estudiante.expedido=='BE'}"> selected </c:if>>Beni</option>
                                    <option value="PD"<c:if test="${estudiante.expedido=='PD'}"> selected </c:if>>Pando</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <span class="subtitulo1"> (*)2.6 C&oacute;digo Rude (C&oacute;digoautomático generado por el sistema)</span>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: right">Código</td>
                            <td>
                                <input type="text" name="codigo_rude" value="${estudiante.codigo_rude}" class="text ui-widget-content ui-corner-all input-wrap">
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <span class="subtitulo1"> 2.7 Sexo</span>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: right">Femenino</td>
                            <td>
                                <input type="radio" name="id_sexo" value="F" <c:if test="${estudiante.id_sexo=='F'}"> checked </c:if> style="vertical-align: text-bottom"/>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: right">Masculino</td>
                                <td>
                                    <input type="radio" name="id_sexo" value="M" <c:if test="${estudiante.id_sexo=='M'}"> checked </c:if> style="vertical-align: text-bottom"/>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <span class="subtitulo1"> (?)2.8 ¿El/la estudiante presenta alguna discapacidad?</span>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: right">Si (Pase a 2.9)</td>
                                <td>
                                    <input type="radio" name="discapacidad" value="SI" <c:if test="${estudiante.discapacidad=='SI'}"> checked </c:if> style="vertical-align: text-bottom"/>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: right">No (Pase a III)</td>
                                <td>
                                    <input type="radio" name="discapacidad" value="NO" <c:if test="${estudiante.discapacidad=='NO'}"> checked </c:if> style="vertical-align: text-bottom"/>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <span class="subtitulo1"> (**)2.9 Nro de registro de discapacidad o IBC</span>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: right">Nro</td>
                                <td>
                                    <input type="text" name="ibc" value="${estudiante.ibc}" class="text ui-widget-content ui-corner-all input-small">
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <span class="subtitulo1"> 2.10 Tipo de discapacidad</span>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: right">Psíquica</td>
                            <td>
                                <input type="radio" name="discapacidad_tipo" value="1" <c:if test="${estudiante.discapacidad_tipo=='1'}"> checked </c:if> style="vertical-align: text-bottom"/>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: right">Autismo</td>
                                <td>
                                    <input type="radio" name="discapacidad_tipo" value="2" <c:if test="${estudiante.discapacidad_tipo=='2'}"> checked </c:if> style="vertical-align: text-bottom"/>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: right">Sindrome de Down</td>
                                <td>
                                    <input type="radio" name="discapacidad_tipo" value="3" <c:if test="${estudiante.discapacidad_tipo=='3'}"> checked </c:if> style="vertical-align: text-bottom"/>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: right">Intelectual</td>
                                <td>
                                    <input type="radio" name="discapacidad_tipo" value="4" <c:if test="${estudiante.discapacidad_tipo=='4'}"> checked </c:if> style="vertical-align: text-bottom"/>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: right">Auditiva</td>
                                <td>
                                    <input type="radio" name="discapacidad_tipo" value="5" <c:if test="${estudiante.discapacidad_tipo=='5'}"> checked </c:if> style="vertical-align: text-bottom"/>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: right">Física Motora</td>
                                <td>
                                    <input type="radio" name="discapacidad_tipo" value="6" <c:if test="${estudiante.discapacidad_tipo=='6'}"> checked </c:if> style="vertical-align: text-bottom"/>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: right">Sordoceguera</td>
                                <td>
                                    <input type="radio" name="discapacidad_tipo" value="7" <c:if test="${estudiante.discapacidad_tipo=='7'}"> checked </c:if> style="vertical-align: text-bottom"/>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: right">Múltiple</td>
                                <td>
                                    <input type="radio" name="discapacidad_tipo" value="8" <c:if test="${estudiante.discapacidad_tipo=='8'}"> checked </c:if> style="vertical-align: text-bottom"/>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: right;text-decoration: underline">Visual</td>
                                <td>
                                    <input type="radio" name="discapacidad_tipo" value="9" <c:if test="${estudiante.discapacidad_tipo=='9'}"> checked </c:if> style="vertical-align: text-bottom"/>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <span class="subtitulo1"> 2.11 Grado de discapacidad</span>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: right">Leve</td>
                                <td>
                                    <input type="radio" name="discapacidad_grado" value="1" <c:if test="${estudiante.discapacidad_grado=='1'}"> checked </c:if> style="vertical-align: text-bottom"/>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: right">Moderado</td>
                                <td>
                                    <input type="radio" name="discapacidad_grado" value="2" <c:if test="${estudiante.discapacidad_grado=='2'}"> checked </c:if> style="vertical-align: text-bottom"/>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: right">Grave</td>
                                <td>
                                    <input type="radio" name="discapacidad_grado" value="3" <c:if test="${estudiante.discapacidad_grado=='3'}"> checked </c:if> style="vertical-align: text-bottom"/>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: right">Muy Grave</td>
                                <td>
                                    <input type="radio" name="discapacidad_grado" value="4" <c:if test="${estudiante.discapacidad_grado=='4'}"> checked </c:if> style="vertical-align: text-bottom"/>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: right;text-decoration: underline">Ceguera total</td>
                                <td>
                                    <input type="radio" name="discapacidad_grado" value="5" <c:if test="${estudiante.discapacidad_grado=='5'}"> checked </c:if> style="vertical-align: text-bottom"/>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: right;text-decoration: underline">Baja Visión</td>
                                <td>
                                    <input type="radio" name="discapacidad_grado" value="6" <c:if test="${estudiante.discapacidad_grado=='6'}"> checked </c:if> style="vertical-align: text-bottom"/>
                                </td>
                            </tr>
                        </table>

                        <h3>III. DIRECCI&Oacute;N ACTUAL DE LA O EL ESTUDIANTE (Informaci&oacute;n para uso exclusivo de la Unidad Educativa)</h3>
                        <table style="width: 50%; margin: auto" class="table">
                            <tr>
                                <td style="text-align: right">Departamento</td>
                                <td><input type="text" name="est_departamento" value="${estudiante.est_departamento}" class="text ui-widget-content ui-corner-all input-wrap"></td>
                        </tr>
                        <tr>
                            <td style="text-align: right">Provincia</td>
                            <td><input type="text" name="est_provincia" value="${estudiante.est_provincia}" class="text ui-widget-content ui-corner-all input-wrap"></td>
                        </tr>
                        <tr>
                            <td style="text-align: right">Secci&oacute;n / Municipio</td>
                            <td style="width: 30%"><input type="text" name="est_seccion" value="${estudiante.est_seccion}" class="text ui-widget-content ui-corner-all input-wrap"></td>
                        </tr>
                        <tr>
                            <td style="width: 15%;text-align: right">Localidad / Comunidad</td>
                            <td style="width: 30%"><input type="text" name="est_localidad" value="${estudiante.est_localidad}" class="text ui-widget-content ui-corner-all input-wrap"></td>
                        </tr>
                        <tr>
                            <td style="text-align: right">Zona / Villa</td>
                            <td colspan="3"><input type="text" name="est_zona" value="${estudiante.est_zona}" class="text ui-widget-content ui-corner-all input-wrap"></td>
                        </tr>
                        <tr>
                            <td style="text-align: right">Avenida / Calle</td>
                            <td colspan="3"><input type="text" name="est_calle" value="${estudiante.est_calle}" class="text ui-widget-content ui-corner-all input-wrap"></td>
                        </tr>
                        <tr>
                            <td style="width: 15%;text-align: right">N&uacute;mero de Vivienda</td>
                            <td style="width: 10%"><input type="text" name="est_nro" value="${estudiante.est_nro}" class="text ui-widget-content ui-corner-all input-small"></td>
                        </tr>
                        <tr>
                            <td style="width: 15%;text-align: right">Tel&eacute;fono Fijo</td>
                            <td style="width: 15%"><input type="text" name="est_telefono" value="${estudiante.est_telefono}" class="text ui-widget-content ui-corner-all input-small"></td>
                        </tr>
                        <tr>
                            <td style="width: 15%;text-align: right">Celular de contacto</td>
                            <td style="width: 15%"><input type="text" name="est_celular" value="${estudiante.est_celular}" class="text ui-widget-content ui-corner-all input-small"></td>
                        </tr>
                    </table>

                    <h3>IV. ASPECTOS SOCIOECONOMICOS DE LA O EL ESTUDIANTE</h3>
                    <table style="width: 50%; margin: auto" class="table">
                        <tr>
                            <td colspan="2">
                                <span class="subtitulo1"> 4.1 Idioma y permanencia cultural de la o el estudiante</span>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: left">(?)4.1.1 ¿Cúal es el idioma en el que aprendió a hablar en su niñez?</td>
                        </tr>
                        <tr>
                            <td><input type="text" name="idioma1" value="${estudiante.idioma1}" class="text ui-widget-content ui-corner-all input-medium"></td>
                        </tr>
                        <tr>
                            <td style="text-align: left">(?)4.1.2 ¿Que idioma(s) habla frecuentemente?</td>
                        </tr>
                        <tr>
                            <td>
                                <input type="text" name="idioma2" value="${estudiante.idioma2}" class="text ui-widget-content ui-corner-all input-medium"><br>
                                <input type="text" name="idioma3" value="${estudiante.idioma3}" class="text ui-widget-content ui-corner-all input-medium"><br>
                                <input type="text" name="idioma4" value="${estudiante.idioma4}" class="text ui-widget-content ui-corner-all input-medium">
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: left">(?)4.1.3 ¿Pertenece a una nación, pueblo indigena originario campesino o afroamericano?</td>
                        </tr>
                        <tr>
                            <td>
                                <table style="width: 100%" class="table">
                                    <tr>
                                        <td style="width: 20%;text-align: right">Ninguno</td>
                                        <td style="width: 5%"><input type="checkbox" name="nacion1" value="1" <c:if test="${nacion1==1}"> checked </c:if>/></td>
                                            <td style="width: 20%;text-align: right">Chiman</td>
                                            <td style="width: 5%"><input type="checkbox" name="nacion2" value="2" <c:if test="${nacion2==2}"> checked </c:if>/></td>
                                            <td style="width: 20%;text-align: right">Moje&ntilde;o-Ignaciano</td>
                                            <td style="width: 5%"><input type="checkbox" name="nacion3" value="3" <c:if test="${nacion3==3}"> checked </c:if>/></td>
                                            <td style="width: 20%;text-align: right">Tapiete</td>
                                            <td style="width: 5%"><input type="checkbox" name="nacion4" value="4" <c:if test="${nacion4==4}"> checked </c:if>/></td>
                                        </tr>
                                        <tr>
                                            <td style="text-align: right">Afroboliviano</td>
                                            <td><input type="checkbox" name="nacion5" value="5" <c:if test="${nacion5==5}"> checked </c:if>/></td>
                                            <td style="text-align: right">EseEjja</td>
                                            <td><input type="checkbox" name="nacion6" value="6" <c:if test="${nacion6==6}"> checked </c:if>/></td>
                                            <td style="text-align: right">Moje&ntilde;o-Trinitario</td>
                                            <td><input type="checkbox" name="nacion7" value="7" <c:if test="${nacion7==7}"> checked </c:if>/></td>
                                            <td style="text-align: right">Toromona</td>
                                            <td><input type="checkbox" name="nacion8" value="8" <c:if test="${nacion8==8}"> checked </c:if>/></td>
                                        </tr>
                                        <tr>
                                            <td style="text-align: right">Araona</td>
                                            <td><input type="checkbox" name="nacion9" value="9" <c:if test="${nacion9==9}"> checked </c:if>/></td>
                                            <td style="text-align: right">Guaran&iacute;</td>
                                            <td><input type="checkbox" name="nacion10" value="10" <c:if test="${nacion10==10}"> checked </c:if>/></td>
                                            <td style="text-align: right">Mor&eacute;</td>
                                            <td><input type="checkbox" name="nacion11" value="11" <c:if test="${nacion11==11}"> checked </c:if>/></td>
                                            <td style="text-align: right">Uru-Chipaya</td>
                                            <td><input type="checkbox" name="nacion12" value="12" <c:if test="${nacion12==12}"> checked </c:if>/></td>
                                        </tr>
                                        <tr>
                                            <td style="text-align: right">Aymara</td>
                                            <td><input type="checkbox" name="nacion13" value="13" <c:if test="${nacion13==13}"> checked </c:if>/></td>
                                            <td style="text-align: right">Guarasuawe</td>
                                            <td><input type="checkbox" name="nacion14" value="14" <c:if test="${nacion14==14}"> checked </c:if>/></td>
                                            <td style="text-align: right">Moset&eacute;n</td>
                                            <td><input type="checkbox" name="nacion15" value="15" <c:if test="${nacion15==15}"> checked </c:if>/></td>
                                            <td style="text-align: right">Weenhayek</td>
                                            <td><input type="checkbox" name="nacion16" value="16" <c:if test="${nacion16==16}"> checked </c:if>/></td>
                                        </tr>
                                        <tr>
                                            <td style="text-align: right">Baure</td>
                                            <td><input type="checkbox" name="nacion17" value="17" <c:if test="${nacion17==17}"> checked </c:if>/></td>
                                            <td style="text-align: right">Guarayo</td>
                                            <td><input type="checkbox" name="nacion18" value="18" <c:if test="${nacion18==18}"> checked </c:if>/></td>
                                            <td style="text-align: right">Movima</td>
                                            <td><input type="checkbox" name="nacion19" value="19" <c:if test="${nacion19==19}"> checked </c:if>/></td>
                                            <td style="text-align: right">Yaminawa</td>
                                            <td><input type="checkbox" name="nacion20" value="20" <c:if test="${nacion20==20}"> checked </c:if>/></td>
                                        </tr>
                                        <tr>
                                            <td style="text-align: right">B&eacute;siro</td>
                                            <td><input type="checkbox" name="nacion21" value="21" <c:if test="${nacion21==21}"> checked </c:if>/></td>
                                            <td style="text-align: right">Itonoma</td>
                                            <td><input type="checkbox" name="nacion22" value="22" <c:if test="${nacion22==22}"> checked </c:if>/></td>
                                            <td style="text-align: right">Tacawara</td>
                                            <td><input type="checkbox" name="nacion23" value="23" <c:if test="${nacion23==23}"> checked </c:if>/></td>
                                            <td style="text-align: right">Yuki</td>
                                            <td><input type="checkbox" name="nacion24" value="24" <c:if test="${nacion24==24}"> checked </c:if>/></td>
                                        </tr>
                                        <tr>
                                            <td style="text-align: right">Canichana</td>
                                            <td><input type="checkbox" name="nacion25" value="25" <c:if test="${nacion25==25}"> checked </c:if>/></td>
                                            <td style="text-align: right">Leco</td>
                                            <td><input type="checkbox" name="nacion26" value="26" <c:if test="${nacion26==26}"> checked </c:if>/></td>
                                            <td style="text-align: right">Puquina</td>
                                            <td><input type="checkbox" name="nacion27" value="27" <c:if test="${nacion27==27}"> checked </c:if>/></td>
                                            <td style="text-align: right">Yuracar&eacute;</td>
                                            <td><input type="checkbox" name="nacion28" value="28" <c:if test="${nacion28==28}"> checked </c:if>/></td>
                                        </tr>
                                        <tr>
                                            <td style="text-align: right">Cavine&ntilde;o</td>
                                            <td><input type="checkbox" name="nacion29" value="29" <c:if test="${nacion29==29}"> checked </c:if>/></td>
                                            <td style="text-align: right">Machajuyai-Kallawaya</td>
                                            <td><input type="checkbox" name="nacion30" value="30" <c:if test="${nacion30==30}"> checked </c:if>/></td>
                                            <td style="text-align: right">Quechua</td>
                                            <td><input type="checkbox" name="nacion31" value="31" <c:if test="${nacion31==31}"> checked </c:if>/></td>
                                            <td style="text-align: right">Zamuco</td>
                                            <td><input type="checkbox" name="nacion32" value="32" <c:if test="${nacion32==32}"> checked </c:if>/></td>
                                        </tr>
                                        <tr>
                                            <td style="text-align: right">Cayubaba</td>
                                            <td><input type="checkbox" name="nacion33" value="33" <c:if test="${nacion33==33}"> checked </c:if>/></td>
                                            <td style="text-align: right">Machineri</td>
                                            <td><input type="checkbox" name="nacion34" value="34" <c:if test="${nacion34==34}"> checked </c:if>/></td>
                                            <td style="text-align: right">Sirion&oacute;</td>
                                            <td><input type="checkbox" name="nacion35" value="35" <c:if test="${nacion35==35}"> checked </c:if>/></td>
                                            <td style="text-align: right" colspan="2"></td>
                                        </tr>
                                        <tr>
                                            <td style="text-align: right">Chacobo</td>
                                            <td><input type="checkbox" name="nacion36" value="36" <c:if test="${nacion36==36}"> checked </c:if>/></td>
                                            <td style="text-align: right">Maropa</td>
                                            <td><input type="checkbox" name="nacion37" value="37" <c:if test="${nacion37==37}"> checked </c:if>/></td>
                                            <td style="text-align: right">Tacana</td>
                                            <td><input type="checkbox" name="nacion38" value="38" <c:if test="${nacion38==38}"> checked </c:if>/></td>
                                            <td colspan="2">

                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <span class="subtitulo1"> 4.2 Salud de la o el estudiante</span>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: left">4.2.1 ¿Existe algun centro de salud/posta/hospital en su comunidad/barrio/zona?</td>
                            </tr>
                            <tr>
                                <td style="padding-left: 20px">
                                    <label>SI <input type="radio" name="centro_salud" value="si" <c:if test="${estudiante.centro_salud=='si'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br>
                                <label>NO <input type="radio" name="centro_salud" value="no" <c:if test="${estudiante.centro_salud=='no'}"> checked </c:if> style="vertical-align: text-bottom"/></label>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: left">4.2.2 El año pasado, por problemas de salud, ¿acudió o se atendió en ...</td>
                            </tr>
                            <tr>
                                <td style="padding-left: 20px">
                                    <label>1. Caja o seguro de salud <input type="checkbox" name="acudio1" value="1" <c:if test="${estudiante.acudio1=='1'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br>
                                <label>2. Establecimientos de salud publica <input type="checkbox" name="acudio2" value="2" <c:if test="${estudiante.acudio2=='2'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br>
                                <label>3. Establecimientos de salud privados <input type="checkbox" name="acudio3" value="3" <c:if test="${estudiante.acudio3=='3'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br>
                                <label>4. En su vivienda <input type="checkbox" name="acudio4" value="4" <c:if test="${estudiante.acudio4=='4'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br>
                                <label>5. Medicina tradicional <input type="checkbox" name="acudio5" value="5" <c:if test="${estudiante.acudio5=='5'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br>
                                <label>6. La farmacia sin receta medica (automedicación)<input type="checkbox" name="acudio6" value="6" <c:if test="${estudiante.acudio6=='6'}"> checked </c:if> style="vertical-align: text-bottom"/></label>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <br>
                                    Si respondio las opciones 1,2 y/o de la pregunta 4.2.2
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: left">4.2.3 El año pasado, ¿Cuantas veces fue al centro de salud?</td>
                            </tr>
                            <tr>
                                <td style="padding-left: 20px">
                                    <label>1 a 2 veces <input type="radio" name="centrosalud_asistido" value="1" <c:if test="${estudiante.centrosalud_asistido==1}"> checked </c:if> style="vertical-align: text-bottom"/></label><br>
                                <label>3 a 5 veces <input type="radio" name="centrosalud_asistido" value="2" <c:if test="${estudiante.centrosalud_asistido==2}"> checked </c:if> style="vertical-align: text-bottom"/></label><br>
                                <label>6 o m&aacute;s veces <input type="radio" name="centrosalud_asistido" value="3" <c:if test="${estudiante.centrosalud_asistido==3}"> checked </c:if> style="vertical-align: text-bottom"/></label><br>
                                <label>Ninguna <input type="radio" name="centrosalud_asistido" value="4" <c:if test="${estudiante.centrosalud_asistido==4}"> checked </c:if> style="vertical-align: text-bottom"/></label>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: left">4.2.4 ¿Tiene seguro de salud?</td>
                            </tr>
                            <tr>
                                <td style="padding-left: 20px">
                                    <label>SI <input type="radio" name="tiene_seguro" value="SI" <c:if test="${estudiante.tiene_seguro=='SI'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br>
                                <label>NO <input type="radio" name="tiene_seguro" value="NO" <c:if test="${estudiante.tiene_seguro=='NO'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <span class="subtitulo1"> 4.3 Acceso de la o el estudiante a servicio básicos</span>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: left">4.3.1 ¿Tiene acceso a agua por cañeria de red?</td>
                            </tr>
                            <tr>
                                <td style="padding-left: 20px">
                                    <label>SI <input type="radio" name="tiene_agua" value="SI" <c:if test="${estudiante.tiene_agua=='SI'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br>
                                <label>NO <input type="radio" name="tiene_agua" value="NO" <c:if test="${estudiante.tiene_agua=='NO'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: left">4.3.2 ¿Tiene baño en su vivienda?</td>
                            </tr>
                            <tr>
                                <td style="padding-left: 20px">
                                    <label>SI <input type="radio" name="tiene_banio" value="SI" <c:if test="${estudiante.tiene_banio=='SI'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br>
                                <label>NO <input type="radio" name="tiene_banio" value="NO" <c:if test="${estudiante.tiene_banio=='NO'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: left">4.3.3 ¿Tiene red de alcantarillado?</td>
                            </tr>
                            <tr>
                                <td style="padding-left: 20px">
                                    <label>SI <input type="radio" name="tiene_alcantarillado" value="SI" <c:if test="${estudiante.tiene_alcantarillado=='SI'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br>
                                <label>NO <input type="radio" name="tiene_alcantarillado" value="NO" <c:if test="${estudiante.tiene_alcantarillado=='NO'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: left">4.3.4 ¿Usa energia eléctrica para alumbrar su vivienda?</td>
                            </tr>
                            <tr>
                                <td style="padding-left: 20px">
                                    <label>SI <input type="radio" name="tiene_electricidad" value="SI" <c:if test="${estudiante.tiene_electricidad=='SI'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>NO <input type="radio" name="tiene_electricidad" value="NO" <c:if test="${estudiante.tiene_electricidad=='NO'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: left">4.3.5 ¿Cuenta con servicio de recojo de basura?</td>
                            </tr>
                            <tr>
                                <td style="padding-left: 20px">
                                    <label>SI <input type="radio" name="tiene_basura" value="SI" <c:if test="${estudiante.tiene_basura=='SI'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>NO <input type="radio" name="tiene_basura" value="NO" <c:if test="${estudiante.tiene_basura=='NO'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: left">4.3.6 La vivienda que ocupa el hogar es:</td>
                            </tr>
                            <tr>
                                <td style="padding-left: 20px">
                                    <label>Propia <input type="radio" name="vivienda" value="1" <c:if test="${estudiante.vivienda=='1'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>Alquilada<input type="radio" name="vivienda" value="2" <c:if test="${estudiante.vivienda=='2'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>Anticretico<input type="radio" name="vivienda" value="3" <c:if test="${estudiante.vivienda=='3'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>Sedida por servicio<input type="radio" name="vivienda" value="4" <c:if test="${estudiante.vivienda=='4'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>Prestada por parientes o amigos<input type="radio" name="vivienda" value="5" <c:if test="${estudiante.vivienda=='5'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>Contrato mixto (alquiler anticretico)<input type="radio" name="vivienda" value="6" <c:if test="${estudiante.vivienda=='6'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <span class="subtitulo1"> 4.4 Acceso a internet de la o el estudiante</span>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: left">4.4.1 El estudiante accede a internet en:</td>
                            </tr>
                            <tr>
                                <td style="padding-left: 20px">
                                    <label>Su Vivienda <input type="checkbox" name="internet1" value="1" <c:if test="${internet1==1}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>La Unidad Educativa <input type="checkbox" name="internet2" value="2" <c:if test="${internet2==2}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>Lugares P&uacute;blicos <input type="checkbox" name="internet3" value="3" <c:if test="${internet3==3}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>Teléfono celular <input type="checkbox" name="internet4" value="4" <c:if test="${internet4==4}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>No accede a internet <input type="checkbox" name="internet5" value="5" <c:if test="${internet5==5}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: left">4.4.2 ¿Con que frecuencia accede a internet?</td>
                            </tr>
                            <tr>
                                <td style="padding-left: 20px">
                                    <label>Diariamente <input type="radio" name="frecuencia" value="1" <c:if test="${estudiante.frecuencia==1}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>Una vez a la semana <input type="radio" name="frecuencia" value="2" <c:if test="${estudiante.frecuencia==2}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>Mas de una vez a la semana <input type="radio" name="frecuencia" value="3" <c:if test="${estudiante.frecuencia==3}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>Una vez al mes <input type="radio" name="frecuencia" value="4" <c:if test="${estudiante.frecuencia==4}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <span class="subtitulo1"> 4.5 Actividad laboral de la o el estudiante</span>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: left">4.5.1 En la pasada gestión ¿El estudiante trabajó?</td>
                            </tr>
                            <tr>
                                <td style="padding-left: 20px">
                                    <label>SI <input type="radio" name="trabajo" value="SI" <c:if test="${estudiante.trabajo=='SI'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>NO <input type="radio" name="trabajo" value="NO" <c:if test="${estudiante.trabajo=='NO'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                </td>
                            </tr>
                            <tr>
                                <td style="padding-left: 40px">
                                    <label>enero <input type="checkbox" name="mes1" value="1" <c:if test="${mes1=='1'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>febrero <input type="checkbox" name="mes2" value="2" <c:if test="${mes2=='2'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>marzo <input type="checkbox" name="mes3" value="3" <c:if test="${mes3=='3'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>abril <input type="checkbox" name="mes4" value="4" <c:if test="${mes4=='4'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>mayo <input type="checkbox" name="mes5" value="5" <c:if test="${mes5=='5'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>junio <input type="checkbox" name="mes6" value="6" <c:if test="${mes6=='6'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>julio <input type="checkbox" name="mes7" value="7" <c:if test="${mes7=='7'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>agosto <input type="checkbox" name="mes8" value="8" <c:if test="${mes8=='8'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>septiembre <input type="checkbox" name="mes9" value="9" <c:if test="${mes9=='9'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>octubre <input type="checkbox" name="mes10" value="10" <c:if test="${mes10=='10'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>noviembre <input type="checkbox" name="mes11" value="11" <c:if test="${mes11=='11'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>diciembre <input type="checkbox" name="mes12" value="12" <c:if test="${mes12=='12'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>Ns/Nr <input type="checkbox" name="mes13" value="13" <c:if test="${mes13=='13'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: left">4.5.2 En la pasada gectión ¿En que actividad trabajo el estudiente?</td>
                            </tr>
                            <tr>
                                <td style="padding-left: 20px">
                                    <label>Agricultura<input type="checkbox" name="actividad1" value="1" <c:if test="${actividad1=='1'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>Ganadería o pesca<input type="checkbox" name="actividad2" value="2" <c:if test="${actividad2=='2'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>Mineria <input type="checkbox" name="actividad3" value="3" <c:if test="${actividad3=='3'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>Construcción <input type="checkbox" name="actividad4" value="4" <c:if test="${actividad4=='4'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>Zafra <input type="checkbox" name="actividad5" value="5" <c:if test="${actividad5=='5'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>Vendedor dependiente <input type="checkbox" name="actividad6" value="6" <c:if test="${actividad6=='6'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>Vendedor por cuenta propia <input type="checkbox" name="actividad7" value="7" <c:if test="${actividad7=='7'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>Transporte o mecánica <input type="checkbox" name="actividad8" value="8" <c:if test="${actividad8=='8'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>Lustrabotas <input type="checkbox" name="actividad9" value="9" <c:if test="${actividad9=='9'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>Trabajador(a) del hogar o niñero(a) <input type="checkbox" name="actividad10" value="10" <c:if test="${actividad10=='10'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>Ayudante familiar o comunitario en agricultura o ganaderia o pesca <input type="checkbox" name="actividad11" value="11" <c:if test="${actividad11=='11'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>Ayudante en el hogar en comercio o ventas<input type="checkbox" name="actividad12" value="12" <c:if test="${actividad12=='12'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>Otro trabajo<input type="checkbox" name="actividad13" value="13" <c:if test="${actividad13=='13'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>(especifique)<input type="text" name="desc_actividad" value="${estudiante.desc_actividad}" style="vertical-align: text-bottom"/></label><br/>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: left">4.5.3 ¿En que turno trabajo el estudiente?</td>
                            </tr>
                            <tr>
                                <td style="padding-left: 20px">
                                    <label>Mañana<input type="checkbox" name="turno1" value="1" <c:if test="${turno1=='1'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>Tarde<input type="checkbox" name="turno2" value="2" <c:if test="${turno2=='2'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>Noche <input type="checkbox" name="turno3" value="3" <c:if test="${turno3=='3'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: left">4.5.4 ¿Con que frecuencia trabajó?</td>
                            </tr>
                            <tr>
                                <td style="padding-left: 20px">
                                    <label>Todos los dias<input type="radio" name="trabajo_frecuancia" value="1" <c:if test="${estudiante.trabajo_frecuancia=='1'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>Fines de semana<input type="radio" name="trabajo_frecuancia" value="2" <c:if test="${estudiante.trabajo_frecuancia=='2'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>Dias festivos <input type="radio" name="trabajo_frecuancia" value="3" <c:if test="${estudiante.trabajo_frecuancia=='3'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>Dias habiles <input type="radio" name="trabajo_frecuancia" value="4" <c:if test="${estudiante.trabajo_frecuancia=='4'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>Eventual / esporádico <input type="radio" name="trabajo_frecuancia" value="5" <c:if test="${estudiante.trabajo_frecuancia=='5'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>En vacaciones <input type="radio" name="trabajo_frecuancia" value="6" <c:if test="${estudiante.trabajo_frecuancia=='6'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: left">4.5.5 ¿Recibió algún pago?</td>
                            </tr>
                            <tr>
                                <td style="padding-left: 20px">
                                    <label>NO<input type="radio" name="remuneracion" value="1" <c:if test="${estudiante.remuneracion=='1'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>Ns/Nr<input type="radio" name="remuneracion" value="2" <c:if test="${estudiante.remuneracion=='2'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>SI <input type="radio" name="remuneracion" value="3" <c:if test="${estudiante.remuneracion=='3'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                </td>
                            </tr>
                            <tr>
                                <td style="padding-left: 50px">
                                    <label>En especie <input type="radio" name="remuneracion_tipo" value="1" <c:if test="${estudiante.remuneracion_tipo=='1'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>Dinero<input type="radio" name="remuneracion_tipo" value="2" <c:if test="${estudiante.remuneracion_tipo=='2'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <span class="subtitulo1"> 4.6 Medio de transporte para llegar a la unidad educativa</span>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: left">4.6.1 Generalmente, ¿Como llega el estudiante a la unidad educativa?</td>
                            </tr>
                            <tr>
                                <td style="padding-left: 20px">
                                    <label>A p&iacute;e <input type="radio" name="transporte" value="1" <c:if test="${estudiante.transporte==1}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>En veh&iacute;culo de transporte terrestre <input type="radio" name="transporte" value="2" <c:if test="${estudiante.transporte==2}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>Fluvial<input type="radio" name="transporte" value="3" <c:if test="${estudiante.transporte==3}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>Otro (especifique)<input type="radio" name="transporte" value="4" <c:if test="${estudiante.transporte==4}"> checked </c:if> style="vertical-align: text-bottom"/> <input type="text" name="transporte_otro" value="${estudiante.transporte_otro}" class="text ui-widget-content ui-corner-all input-medium"></label><br/>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: left">4.6.2 Según el medio de transporte señalado, ¿Cúal es el tiempo máximo que demora el estudiante desde su vivienda hast la unidad educativa?</td>
                            </tr>
                            <tr>
                                <td style="padding-left: 20px">
                                    <label>Menos de media hora <input type="radio" name="demora" value="1" <c:if test="${estudiante.demora==1}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>Entre media hora y una hora <input type="radio" name="demora" value="2" <c:if test="${estudiante.demora==2}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>Entre una a dos horas <input type="radio" name="demora" value="3" <c:if test="${estudiante.demora==3}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>Dos horas o m&aacute;s <input type="radio" name="demora" value="4" <c:if test="${estudiante.demora==4}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <span class="subtitulo1"> 4.7 Abandono escolar correspondiente a la gestión anterior</span>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: left">4.7.1 ¿El estudiante abandonó la unidad educativa el año pasado?</td>
                            </tr>
                            <tr>
                                <td style="padding-left: 20px">
                                    <label>SI<input type="radio" name="abandono" value="SI" <c:if test="${estudiante.abandono=='SI'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>NO<input type="radio" name="abandono" value="NO" <c:if test="${estudiante.abandono=='NO'}"> checked </c:if> style="vertical-align: text-bottom"/>(Pase a la sección V)</label><br/>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: left">4.7.2 ¿Cuál o cuales fueron las razones de abandono escolar?</td>
                            </tr>
                            <tr>
                                <td style="padding-left: 20px">
                                    <label>Tuvo que ayudar a sus padres en su trabajo<input type="checkbox" name="abandono_por1" value="1" <c:if test="${abandono_por1=='1'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>Tuvo trabajo remunerado<input type="checkbox" name="abandono_por2" value="2" <c:if test="${abandono_por2=='2'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>Falta de dinero<input type="checkbox" name="abandono_por3" value="3" <c:if test="${abandono_por3=='3'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>Edad temprana (opcional)/edad tardía (rezago)<input type="checkbox" name="abandono_por4" value="4" <c:if test="${abandono_por4=='4'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>La unidad educativa era distante<input type="checkbox" name="abandono_por5" value="5" <c:if test="${abandono_por5=='5'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>Labores de casa/cuidado de niños(as)<input type="checkbox" name="abandono_por6" value="6" <c:if test="${abandono_por6=='6'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>Embarazo o paternidad<input type="checkbox" name="abandono_por7" value="7" <c:if test="${abandono_por7=='7'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>Por enfermedad/accidente/discapacidad<input type="checkbox" name="abandono_por8" value="8" <c:if test="${abandono_por8=='8'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>Viaje o accidente<input type="checkbox" name="abandono_por9" value="9" <c:if test="${abandono_por9=='9'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>Falta de interés<input type="checkbox" name="abandono_por10" value="10" <c:if test="${abandono_por10=='10'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>Bullying o discriminación<input type="checkbox" name="abandono_por11" value="11" <c:if test="${abandono_por11=='11'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>Otra (especifique)<input type="text" name="abandono_otro" value="${estudiante.abandono_otro}" class="text ui-widget-content ui-corner-all input-medium"></label><br/>
                            </td>
                        </tr>
                    </table>
                    <h3>VI. DATOS DEL PADRE, MADRE O TUTOR(a) DE LA O EL ESTUDIANTE</h3>
                    <table style="width: 50%; margin: auto" class="table">
                        <tr>
                            <td style="text-align: left" colspan="2">4.1 La o el estudiante vive habitualmente con:</td>
                        </tr>
                        <tr>
                            <td style="padding-left: 20px">
                                <label>Padre y madre<input type="radio" name="vive_con" value="1" <c:if test="${estudiante.vive_con=='1'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>Solo padre<input type="radio" name="vive_con" value="2" <c:if test="${estudiante.vive_con=='2'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>Solo madre<input type="radio" name="vive_con" value="3" <c:if test="${estudiante.vive_con=='3'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>Tutor(a)<input type="radio" name="vive_con" value="4" <c:if test="${estudiante.vive_con=='4'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                <label>Solo(a)<input type="radio" name="vive_con" value="5" <c:if test="${estudiante.vive_con=='5'}"> checked </c:if> style="vertical-align: text-bottom"/></label><br/>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2"><span class="subtitulo1"> 5.2. Datos del Padre</span></td>
                            </tr>
                            <tr>
                                <td style="text-align: right; width: 50%">C&eacute;dula de Identidad</td>
                                <td style=" width: 50%"><input type="text" name="tutor_ci" value="${estudiante.tutor_ci}" class="text ui-widget-content ui-corner-all input-medium">

                            </td>
                        </tr>
                            <tr>
                                <td style="text-align: right">Complemento</td>
                                <td>
                                <input type="text" name="tutor_complemento" value="${estudiante.tutor_complemento}" class="text ui-widget-content ui-corner-all input-small">

                            </td>
                        </tr>
                            <tr>
                                <td style="text-align: right">Expedido</td>
                                <td>
                                <select name="tutor_expedido" class="text ui-widget-content ui-corner-all input-medium">
                                    <option value="LP"<c:if test="${estudiante.tutor_expedido=='LP'}"> selected </c:if>>La Paz</option>
                                    <option value="SC"<c:if test="${estudiante.tutor_expedido=='SC'}"> selected </c:if>>Santa Cruz</option>
                                    <option value="CB"<c:if test="${estudiante.tutor_expedido=='CB'}"> selected </c:if>>Cochabamba</option>
                                    <option value="PT"<c:if test="${estudiante.tutor_expedido=='PT'}"> selected </c:if>>Potosi</option>
                                    <option value="TJ"<c:if test="${estudiante.tutor_expedido=='TJ'}"> selected </c:if>>Tarija</option>
                                    <option value="OR"<c:if test="${estudiante.tutor_expedido=='OR'}"> selected </c:if>>Oruro</option>
                                    <option value="CH"<c:if test="${estudiante.tutor_expedido=='CH'}"> selected </c:if>>Chuquisaca</option>
                                    <option value="BE"<c:if test="${estudiante.tutor_expedido=='BE'}"> selected </c:if>>Beni</option>
                                    <option value="PD"<c:if test="${estudiante.tutor_expedido=='PD'}"> selected </c:if>>Pando</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: right">Apellido Paterno</td>
                            <td><input type="text" name="tutor_apellidos" value="${estudiante.tutor_apellidos}" class="text ui-widget-content ui-corner-all input-medium"></td>
                        </tr>
                        <tr>
                            <td style="text-align: right">Apellido Materno</td>
                            <td><input type="text" name="tutor_materno" value="${estudiante.tutor_materno}" class="text ui-widget-content ui-corner-all input-medium"></td>
                        </tr>
                        <tr>
                            <td style="text-align: right">Nombres</td>
                            <td><input type="text" name="tutor_nombres" value="${estudiante.tutor_nombres}" class="text ui-widget-content ui-corner-all input-medium"></td>
                        </tr>
                        <tr>
                            <td style="text-align: right">Idioma que Habla Frecuentemente</td>
                            <td><input type="text" name="tutor_idioma" value="${estudiante.tutor_idioma}" class="text ui-widget-content ui-corner-all input-medium"></td>
                        </tr>
                        <tr>
                            <td style="text-align: right">Ocupaci&oacute;n Laboral Actual</td>
                            <td><input type="text" name="tutor_ocupacion" value="${estudiante.tutor_ocupacion}" class="text ui-widget-content ui-corner-all input-medium"></td>
                        </tr>
                        <tr>
                            <td style="text-align: right">Mayor Grado de Instrucci&oacute;n Alcanzado</td>
                            <td><input type="text" name="tutor_grado" value="${estudiante.tutor_grado}" class="text ui-widget-content ui-corner-all input-medium"></td>
                        </tr>
                        <tr>
                            <td style="text-align: right">Fecha Nacimiento</td>
                            <td><input type="text" name="tutor_fecha" id="datepicker3" runat="server" value="${estudiante.tutor_fecha}" class="text ui-widget-content ui-corner-all input-medium"/></td>
                        </tr>
                        <tr>
                            <td colspan="2"><span class="subtitulo1"> 5.3. Datos de la Madre</span></td>
                        </tr>
                        <tr>
                            <td style="text-align: right">C&eacute;dula de Identidad</td>
                            <td><input type="text" name="madre_ci" value="${estudiante.madre_ci}" class="text ui-widget-content ui-corner-all input-medium">
                            </td>

                        </tr>
                        <tr>
                            <td style="text-align: right">Complemento</td>
                            <td>
                                <input type="text" name="madre_complemento" value="${estudiante.madre_complemento}" class="text ui-widget-content ui-corner-all input-small">
                               
                            </td>

                        </tr>
                        <tr>
                            <td style="text-align: right">Expedido</td>
                            <td> 
                                <select name="madre_expedido" class="text ui-widget-content ui-corner-all input-medium">
                                    <option value="LP"<c:if test="${estudiante.madre_expedido=='LP'}"> selected </c:if>>La Paz</option>
                                    <option value="SC"<c:if test="${estudiante.madre_expedido=='SC'}"> selected </c:if>>Santa Cruz</option>
                                    <option value="CB"<c:if test="${estudiante.madre_expedido=='CB'}"> selected </c:if>>Cochabamba</option>
                                    <option value="PT"<c:if test="${estudiante.madre_expedido=='PT'}"> selected </c:if>>Potosi</option>
                                    <option value="TJ"<c:if test="${estudiante.madre_expedido=='TJ'}"> selected </c:if>>Tarija</option>
                                    <option value="OR"<c:if test="${estudiante.madre_expedido=='OR'}"> selected </c:if>>Oruro</option>
                                    <option value="CH"<c:if test="${estudiante.madre_expedido=='CH'}"> selected </c:if>>Chuquisaca</option>
                                    <option value="BE"<c:if test="${estudiante.madre_expedido=='BE'}"> selected </c:if>>Beni</option>
                                    <option value="PD"<c:if test="${estudiante.madre_expedido=='PD'}"> selected </c:if>>Pando</option>
                                </select>
                            </td>

                        </tr>
                        <tr>
                            <td style="text-align: right">Apellido paterno</td>
                            <td><input type="text" name="madre_apellidos" value="${estudiante.madre_apellidos}" class="text ui-widget-content ui-corner-all input-medium"></td>
                        </tr>
                        <tr>
                            <td style="text-align: right">Apellido materno</td>
                            <td><input type="text" name="madre_materno" value="${estudiante.madre_materno}" class="text ui-widget-content ui-corner-all input-medium"></td>
                        </tr>
                        <tr>
                            <td style="text-align: right">Nombres</td>
                            <td><input type="text" name="madre_nombres" value="${estudiante.madre_nombres}" class="text ui-widget-content ui-corner-all input-medium"></td>
                        </tr>
                        <tr>
                            <td style="text-align: right">Idioma que Habla Frecuentemente</td>
                            <td><input type="text" name="madre_idioma" value="${estudiante.madre_idioma}" class="text ui-widget-content ui-corner-all input-medium"></td>
                        </tr>
                        <tr>
                            <td style="text-align: right">Ocupaci&oacute;n Laboral Actual</td>
                            <td><input type="text" name="madre_ocupacion" value="${estudiante.madre_ocupacion}" class="text ui-widget-content ui-corner-all input-medium"></td>
                        </tr>
                        <tr>
                            <td style="text-align: right">Mayor Grado de Instrucci&oacute;n Alcanzado</td>
                            <td><input type="text" name="madre_grado" value="${estudiante.madre_grado}" class="text ui-widget-content ui-corner-all input-medium"></td>
                        </tr>
                        <tr>
                            <td style="text-align: right">Fecha Nacimiento</td>
                            <td><input type="text" name="madre_fecha" id="datepicker4" runat="server" value="${estudiante.madre_fecha}" class="text ui-widget-content ui-corner-all input-medium"/></td>
                        </tr>
                        <tr>
                            <td colspan="2"><span class="subtitulo1"> 5.3. Datos del tutor(a)</span></td>
                        </tr>
                        <tr>
                            <td style="text-align: right">C&eacute;dula de Identidad</td>
                            <td><input type="text" name="t_ci" value="${estudiante.t_ci}" class="text ui-widget-content ui-corner-all input-medium">     
                            </td>

                        </tr>
                        <tr>
                            <td style="text-align: right">Complemento</td>
                            <td><input type="text" name="t_complemento" value="${estudiante.t_complemento}" class="text ui-widget-content ui-corner-all input-small">
    
                            </td>

                        </tr>
                        <tr>
                            <td style="text-align: right">Expedido</td>
                            <td>
                                <select name="t_expedido" class="text ui-widget-content ui-corner-all input-medium">
                                    <option value="LP"<c:if test="${estudiante.t_expedido=='LP'}"> selected </c:if>>La Paz</option>
                                    <option value="SC"<c:if test="${estudiante.t_expedido=='SC'}"> selected </c:if>>Santa Cruz</option>
                                    <option value="CB"<c:if test="${estudiante.t_expedido=='CB'}"> selected </c:if>>Cochabamba</option>
                                    <option value="PT"<c:if test="${estudiante.t_expedido=='PT'}"> selected </c:if>>Potosi</option>
                                    <option value="TJ"<c:if test="${estudiante.t_expedido=='TJ'}"> selected </c:if>>Tarija</option>
                                    <option value="OR"<c:if test="${estudiante.t_expedido=='OR'}"> selected </c:if>>Oruro</option>
                                    <option value="CH"<c:if test="${estudiante.t_expedido=='CH'}"> selected </c:if>>Chuquisaca</option>
                                    <option value="BE"<c:if test="${estudiante.t_expedido=='BE'}"> selected </c:if>>Beni</option>
                                    <option value="PD"<c:if test="${estudiante.t_expedido=='PD'}"> selected </c:if>>Pando</option>
                                </select>
                                        
                            </td>

                        </tr>
                        <tr>
                            <td style="text-align: right">Apellido paterno</td>
                            <td><input type="text" name="t_apellidos" value="${estudiante.t_apellidos}" class="text ui-widget-content ui-corner-all input-medium"></td>
                        </tr>
                        <tr>
                            <td style="text-align: right">Apellido materno</td>
                            <td><input type="text" name="t_materno" value="${estudiante.t_materno}" class="text ui-widget-content ui-corner-all input-medium"></td>
                        </tr>
                        <tr>
                            <td style="text-align: right">Nombres</td>
                            <td><input type="text" name="t_nombres" value="${estudiante.t_nombres}" class="text ui-widget-content ui-corner-all input-medium"></td>
                        </tr>
                        <tr>
                            <td style="text-align: right">Idioma que Habla Frecuentemente</td>
                            <td><input type="text" name="t_idioma" value="${estudiante.t_idioma}" class="text ui-widget-content ui-corner-all input-medium"></td>
                        </tr>
                        <tr>
                            <td style="text-align: right">Ocupaci&oacute;n Laboral Actual</td>
                            <td><input type="text" name="t_ocupacion" value="${estudiante.t_ocupacion}" class="text ui-widget-content ui-corner-all input-medium"></td>
                        </tr>
                        <tr>
                            <td style="text-align: right">Mayor Grado de Instrucci&oacute;n Alcanzado</td>
                            <td><input type="text" name="t_grado" value="${estudiante.t_grado}" class="text ui-widget-content ui-corner-all input-medium"></td>
                        </tr>
                        <tr>
                            <td style="text-align: right">Fecha Nacimiento</td>
                            <td><input type="text" name="t_fecha" id="datepicker5" runat="server" value="${estudiante.t_fecha}" class="text ui-widget-content ui-corner-all input-medium"/></td>
                        </tr>
                    </table>

                    <hr/>
                    <table style="width: 50%;margin: auto">
                        <tr>
                            <td style="width: 50%;vertical-align: top;">
                                <table style="width: 90%;margin: 0 auto">
                                    <tr>
                                        <td style="text-align: right">Lugar</td>
                                        <td><input type="text" name="lugar" value="${estudiante.lugar}" class="text ui-widget-content ui-corner-all input-medium"></td>
                                    </tr>
                                </table>                         
                            </td>
                            <td style="width: 50%;vertical-align: top">
                                <table>
                                    <tr>
                                        <td> Fecha</td>
                                        <td><input type="text" name="fecha" id="datepicker1" runat="server" value="${fecha}"  class="text ui-widget-content ui-corner-all input-medium" readonly/></td>
                                    </tr>
                                </table>                            
                            </td>
                        </tr>
                    </table>
                    <center><button class="guardar botones">Guardar Cambios</button></center>
                </div>
            </form>
        </c:if>
    </body>
</html>
