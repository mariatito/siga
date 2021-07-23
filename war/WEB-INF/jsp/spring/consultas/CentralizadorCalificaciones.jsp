<%-- 
    Document   : CentralizadorCalificaciones
    Created on : 06-abr-2013, 11:19:46
    Author     : MARCO ANTONIO QUENTA VELASCO
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
        <script type="text/javascript" src="js/prototype.js"></script>
        <link rel="stylesheet" type="text/css" href="css/module.css" media="screen" />
        <script type="text/javascript" src="js/jquery-1.10.1.min.js"></script>
        <script src="jquery_ui/jquery-ui-1.10.3.js" type="text/javascript"></script>
        <link href="jquery_ui/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript">
            function onRowover(elem) {
                elem.className = 'colover';
            }
            function onRowout(elem) {
                elem.className = 'colout';
            }

            function openWindow(form, title, w, h) {
                var modalwin = dhtmlmodal.open('win' + form, 'div', form, title, 'width=' + w + 'px,height=' + h + 'px,left=100px,top=100px,resize=1,scrolling=1');
                modalwin.moveTo('middle', 'middle');
                return modalwin;
            }
            function selectGestion(id_ges) {
                window.location = '<c:url value="/centralizadorCalificaciones.do"/>?id_gestion=' + id_ges;
            }
            /*function selectCurso(id_curso){
             
             }
             function popupWindow(u) {
             window.open(u,'','toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes,copyhistory=no,width=800,height=530,screenX=50,screenY=50,top=50,left=350');
             }
             function selectEstudiantes(id_curso){
             window.location = '<c:url value="/actualizarEstados.do"/>?id_gestion=${id_gestion}&id_curso='+id_curso+'&opcion=_updateEstado';
             }
             function setUpdateEstados(){
             document.formEstados.submit()
             }*/
            $(function() {
                $(".classbutton").button();

                $("select[name='id_curso']").change(function() {
                    var ideva = $(this).val();
                    $(".evas").css("display", "none");
                    $("#evas_" + ideva + "").css("display", "inline");
                });
                $("button[name='generar_reporte']").click(function() {
                    var id_curso = $("select[name='id_curso']").val();
                    var id_eva = $("select[name='id_eva" + id_curso + "']").val();
                    window.location = '<c:url value="/centralizadorCalificaciones.do"/>?id_gestion=${id_gestion}&id_curso=' + id_curso + "&id_eva=" + id_eva;
                });
            });
            function setPrintExcel() {
                window.location = '<c:url value="/centralizadorCalificaciones.do"/>?id_curso=${curso.id_curso}&id_gestion=${id_gestion}&opcion=_imprimeExcel&id_eva=${id_eva}';
            }

        </script>
    </head>
    <body>
        <div class="headercontent">
            <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                <tr>
                    <td class="tab_init">&nbsp;</td>
                    <td style="width:40%">
                        <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                            <td class="tab_current" >Centralizador de calificaciones</td>
                            <!-- td class="tab_normal" onclick="javascript:window.location='<c:url value="/estadoEstudiante.do"/>'">Estado de estudiantes</td-->
                        </table>
                    </td>
                    <td class="tab_fin">&nbsp;</td>
                </tr>
            </table>
            <div class="titlepage">CENTRALIZADOR DE CALIFICACIONES</div>
        </div>
        <div class="maincontent">
            <c:if test="${!empty gestion}">
                <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                    <tr>
                        <td class="tableHeader">Gestiones acad&eacute;micas</td>
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
                                    <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)" onclick="selectGestion('${item.id_gestion}')">
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

            <c:if test="${!empty cursos}">
                <table cellpadding="0" cellspacing="0" border="0" style="width:100%" class="gridContent">
                    <tr>
                        <td style="width: 15%" class="campos">Gestion : </td>
                        <td style="width: 85%" class="datos">${id_gestion}</td>
                    </tr>
                    <tr>
                        <td class="campos">Curso : </td>
                        <td class="datos">
                            <select class="text-field" name="id_curso" style="width: 200px;padding: 0">
                                <c:forEach varStatus="j" var="item" items="${cursos}">
                                    <option value="${item.id_curso}" <c:if test="${id_curso==item.id_curso}"> selected </c:if>>${item.curso} de ${item.ciclo}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="campos">Bimestre/Trimestre : </td>
                        <td class="datos">
                            <c:forEach varStatus="j" var="item_per" items="${periodo_cursos}">
                                <div id="evas_${item_per.id_curso}" class="evas" <c:if test="${item_per.id_curso!=id_curso}"> style="display: none "</c:if>>
                                    <select id="id_eva_sel" class="text-field" name="id_eva${item_per.id_curso}" style="width: 200px;padding: 0">
                                        <c:forEach varStatus="j" var="item_fec_notas" items="${item_per.fecnotas}">
                                            <option value="${item_fec_notas.id_eva}" <c:if test="${item_fec_notas.id_eva==id_eva && item_per.id_curso==id_curso}"> selected </c:if>>${item_fec_notas.descripcion}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </c:forEach>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align: center">
                            <button name="generar_reporte" class="classbutton">Generar Reporte</button>
                        </td>
                    </tr>
                </table>
            </c:if>
            <c:if test="${!empty curso}">
                <table cellpadding="0" cellspacing="0" border="0" style="width: 100% ; cursor:pointer" class="gridContent">
                    <tr>
                        <td class="tableHeader"><strong>Reporte</strong></td>
                    </tr>
                    <tr>
                        <td>
                            <table cellpadding="0" cellspacing="0" border="0">
                                <tr>
                                    <td class="text-label"><button class="button-normal"  onclick="setPrintExcel()" title="Imprimir en Excel">Reporte en Excel</button>&nbsp;</td>
                                    <td class="text-label">&nbsp;&nbsp;Curso:</td>
                                    <td style="color:gray">${curso.curso} de ${curso.ciclo}</td>
                                    <td class="text-label">&nbsp;&nbsp;Inscripcion:</td>
                                    <td style="color:gray">${id_gestion}</td>
                                    <td class="text-label">&nbsp;&nbsp;Cantidad de inscritos:</td>
                                    <td style="color:gray">${curso.numEstudiantes}</td>
                                </tr>
                            </table>
                            <div id="resultlayer1_m">
                                <form action="<c:url value="/actualizarEstados.do"/>" method="post" name="formEstados">
                                    <table width="100%" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF">
                                        <tr>
                                            <td class="gridHeaders">Nro.BBB</td>
                                            <td class="gridHeaders">C&oacute;digo</td>
                                            <td class="gridHeaders">Apellidos&nbsp;y&nbsp;nombres</td>
                                            <c:forEach varStatus="i" var="item_cm" items="${curso.cursomaterias}">
                                                <td class="gridHeaders" colspan="3" title="${item_cm.materia}">${item_cm.id_materia}</td>
                                            </c:forEach>
                                            <c:if test="${curso.id_curso!='K'||curso.id_curso!='K1'||curso.id_curso!='K2'}">
                                                <td class="gridHeaders">P</td>
                                            </c:if>
                                        </tr>
                                        <c:if test="${empty curso.estudiantes}">
                                            <tr>
                                                <td class="gridData" colspan="10">No se han encontrado elementos.</td>
                                            </tr>
                                        </c:if>

                                        <c:forEach varStatus="i" var="item" items="${curso.estudiantes}">
                                            <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)" onclick="selectEstudiante('${curso.id_curso}', '${item.id_estudiante}')">
                                                <td align="center" class="gridData"><c:out value="${i.count}"/> </td>
                                                <td class="gridData">${item.codigo}</td>
                                                <td class="gridData">${item.paterno}&nbsp;${item.materno}&nbsp;${item.nombres}</td>
                                                <c:set var="promedio_reg" value="0" />
                                                <c:set var="cant_mat" value="0" />
                                                <c:forEach varStatus="j" var="item_cm_e" items="${item.cursomaterias}">
                                                    <c:if test="${curso.id_curso!='K'&&curso.id_curso!='K1'&&curso.id_curso!='K21'&&curso.id_curso!='K12'&&curso.id_curso!='P1'&&curso.id_curso!='P12'}">
                                                        <td class="gridData" style="text-align: right">${item_cm_e.nota}</td>
                                                        <td class="gridData" style="text-align: right">${item_cm_e.dps}</td>
                                                        <td class="gridData" style="background-color: rgb(210, 233, 237);font-weight: bold;text-align: right" >
                                                            <c:if test="${(item_cm_e.nota + item_cm_e.dps) < item_cm_e.nota_min}">
                                                                <span style="color: red">${item_cm_e.nota + item_cm_e.dps}</span>
                                                            </c:if>
                                                            <c:if test="${(item_cm_e.nota + item_cm_e.dps) >=item_cm_e.nota_min}">
                                                                ${item_cm_e.nota + item_cm_e.dps}
                                                            </c:if>
                                                        </td>
                                                    </c:if>
                                                    <c:if test="${curso.id_curso=='K'||curso.id_curso=='K1'||curso.id_curso=='K21'||curso.id_curso=='K12'||curso.id_curso=='P1'||curso.id_curso=='P12'}">
                                                        <td class="gridData" colspan="3" style="background-color: rgb(210, 233, 237);font-weight: bold" >${item_cm_e.cualitativa}</td>
                                                    </c:if>
                                                </c:forEach>
                                                <c:if test="${curso.id_curso!='K'&&curso.id_curso!='K1'&&curso.id_curso!='K21'&&curso.id_curso!='K12'&&curso.id_curso!='P1'&&curso.id_curso!='P12'}">
                                                    <td class="gridData" style="background-color: rgb(255, 225, 213);font-weight: bold">${item.notapromedio}</td>    
                                                </c:if>
                                            </tr>
                                        </c:forEach>
                                        <c:if test="${curso.id_curso!='K'&&curso.id_curso!='K1'&&curso.id_curso!='K21'&&curso.id_curso!='K12'&&curso.id_curso!='P1'&&curso.id_curso!='P12'}">
                                            <tr style="background-color: rgb(255, 225, 213);font-weight: bold;text-align: right">
                                                <td class="gridData" colspan="3">Promedio por Materias&nbsp;&nbsp;&nbsp;</td>
                                                <c:forEach varStatus="i" var="item_cm" items="${curso.cursomaterias}">
                                                    <td class="gridData" colspan="3">${item_cm.promedio}</td>
                                                </c:forEach>
                                                <c:if test="${curso.id_curso!='K'&&curso.id_curso!='K1'&&curso.id_curso!='K21'&&curso.id_curso!='K12'&&curso.id_curso!='P1'&&curso.id_curso!='P12'}">
                                                    <td class="gridData"></td>
                                                </c:if>
                                            </tr>
                                            <tr style="background-color: rgb(255, 225, 213);font-weight: bold;text-align: right">
                                                <td class="gridData" colspan="3">Aprobados&nbsp;&nbsp;&nbsp;</td>
                                                <c:forEach varStatus="i" var="item_cm" items="${curso.cursomaterias}">
                                                    <td class="gridData" colspan="3">${item_cm.aprobados}</td>
                                                </c:forEach>
                                                <c:if test="${curso.id_curso!='K'&&curso.id_curso!='K1'&&curso.id_curso!='K21'&&curso.id_curso!='K12'&&curso.id_curso!='P1'&&curso.id_curso!='P12'}">
                                                    <td class="gridData"></td>
                                                </c:if>
                                            </tr>
                                            <tr style="background-color: rgb(255, 225, 213);font-weight: bold;text-align: right">
                                                <td class="gridData" colspan="3">Reprobados&nbsp;&nbsp;&nbsp;</td>
                                                <c:forEach varStatus="i" var="item_cm" items="${curso.cursomaterias}">
                                                    <td class="gridData" colspan="3">${item_cm.reprobados}</td>
                                                </c:forEach>
                                                <c:if test="${curso.id_curso!='K'&&curso.id_curso!='K1'&&curso.id_curso!='K21'&&curso.id_curso!='K12'&&curso.id_curso!='P1'&&curso.id_curso!='P12'}">
                                                    <td class="gridData"></td>
                                                </c:if>
                                            </tr>
                                        </c:if>
                                    </table>
                                    <input type="hidden" name="id_gestion" value="${id_gestion}">

                                    <input type="hidden" name="newEstado" value="_newEstado">

                                </form>
                            </div>
                        </td>
                    </tr>
                </table>
            </c:if>
        </div>
    </body>
</html>
