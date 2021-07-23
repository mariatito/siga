<%-- 
    Document   : RegistrarFaltasAdm
    Created on : 15-02-2010, 03:12:20 PM
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

        <link rel="stylesheet" type="text/css" href="css/style2014.css" media="screen" />
        <script type="text/javascript" src="js/jquery-1.10.1.min.js"></script>
        <script type="text/javascript" src="js/script.js"></script>
        <script src="jquery-ui/jquery-ui-1.10.3.js" type="text/javascript"></script>
        <link href="jquery-ui/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
        <script src="jquery-ui/ui/jquery.ui.datepicker-es.js" type="text/javascript"></script>
        <script type="text/javascript">
            $(function () {
                $("button.Guardar").button({
                    icons: {
                        primary: "ui-icon-disk"
                    }
                }).click(function () {
                    $("form[name='formFaltas']").submit();
                    return false;
                });
                $("#datepicker").datepicker({
                    changeMonth: true,
                    changeYear: true,
                });
                $("button.Atras").button({
                    icons: {
                        primary: " ui-icon-carat-1-w"
                    }
                }).click(function () {
                    window.location = '<c:url value="/registrarFaltasAdm.do"/>';
                    return false;
                });
                $("button.Editar").button({
                    icons: {
                        primary: " ui-icon-pencil"
                    }
                }).click(function () {
                    $(".editable").css("display", "inline");
                    $(".fechaReg").css("display", "inline");
                    $("button.Atras").css("display", "none");
                    $("button.Editar").css("display", "none");
                    $("button.Calcelar").css("display", "inline");
                    $("button.Guardar").css("display", "inline");
                    return false;
                });
                $("button.Calcelar").button({
                    icons: {
                        primary: " ui-icon-close"
                    }
                }).click(function () {
                    $(".editable").css("display", "none");
                    $(".fechaReg").css("display", "none");
                    $("button.Atras").css("display", "inline");
                    $("button.Editar").css("display", "inline");
                    $("button.Calcelar").css("display", "none");
                    $("button.Guardar").css("display", "none");
                    return false;
                });
            });
            function openWindow(form, title, w, h) {
                docentesWin = dhtmlmodal.open('win' + form, 'div', form, title, 'width=' + w + 'px,height=' + h + 'px,left=100px,top=100px,resize=1,scrolling=1');
                docentesWin.moveTo('middle', 'middle');
                return docentesWin;
            }
            function selectCurso(id_curso) {
                window.location = '<c:url value="/registrarFaltasAdm.do"/>?id_curso=' + id_curso;
            }
            function popupWindow(u) {
                window.open(u, '', 'toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes,copyhistory=no,width=800,height=530,screenX=50,screenY=50,top=50,left=350');
            }
            function selectEstudiante(id_curso, id_estudiante) {
                popupWindow('<c:url value="/registrarFaltasAdm.do"/>?id_curso=' + id_curso + '&id_estudiante=' + id_estudiante + '&opcion=_updateFalta');
            }
        </script>
    </head>
    <body>
        <c:if test="${!empty cursos}">
            <table class="headerPage" cellpadding="0" cellspacing="0">
                <tr>
                    <td>
                        <h1>Faltas ${id_gestion}</h1>
                        <h3>Registro de Faltas Con Licencia, Faltas Sin Licencia, Atrasos y Sin Uniforme</h3>
                    </td>
                    <td style="text-align: right">
                    </td>
                </tr>
            </table>
            <div class='maincontent'>
                <table class="gridST" cellpadding="0" cellspacing="0" border="0" style="width: 100%">
                    <thead>
                        <tr>
                            <th></th>
                            <th>Nro.</th>
                            <th>C&oacute;digo</th>
                            <th>Curso</th>
                            <th>Estudiantes</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach varStatus="j" var="item" items="${cursos}">
                            <tr>
                                <td style="text-align: center;width: 18px" onclick="selectCurso('${item.id_curso}')"><i class="details"></i></td>
                                <td style="text-align: center">${j.count}</td>
                                <td style="text-align: center">${item.desc_curso}</td>
                                <td>
                                    ${item.curso} de ${item.ciclo}
                                </td>
                                <td style="text-align: center">
                                    ${item.numEstudiantes}
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>
        <c:if test="${!empty curso}">
            <table class="headerPage" cellpadding="0" cellspacing="0">
                <tr>
                    <td>
                        <h1>Faltas ${curso.curso} de ${curso.ciclo} (${id_gestion})</h1>
                        <h3>Registro de Faltas Con Licencia, Faltas Sin Licencia, Atrasos y Sin Uniforme</h3>
                    </td>
                    <td style="text-align: right">
                        <button class="Guardar"  style="display: none">
                            Guardar
                        </button>
                        <button class="Editar">
                            Editar
                        </button>
                        <button class="Calcelar" style="display: none">
                            Cancelar
                        </button>
                        <button class="Atras">
                            Atras
                        </button>
                    </td>
                </tr>
            </table>
            <div class='maincontent'>                
                <form action="<c:url value="/registrarFaltasAdm.do"/>" method="post" name="formFaltas">
                    <div class="fechaReg" style="display: none">
                    <table class="form" style="width: 100%;margin: 0 auto">
                        <tr>
                            <td class="prompt" style="width: 50%">Fecha : </td>
                            <td class="value" style="width: 50%">
                                <input type="text" name="fechaFalta" id="datepicker" class="inputUI width-small" readonly value="${fechaActual}"/>
                            </td>
                        </tr>
                    </table>
                            </div>
                    <table  class="gridST" width="100%" cellpadding="0" cellspacing="0" border="0">
                        <thead>
                            <tr>
                                <th></th>
                                <th>Nro.</th>
                                <th>Apellidos y nombres</th>
                                    <% int indexc = 0;%>
                                    <c:forEach varStatus="i" var="itemh" items="${curso.fecRegNotas}">
                                        <c:if test="${itemh.periodos==2}">
                                            <% if (++indexc <= 4) {%>
                                            <c:if test="${itemh.estado!='activo'}">
                                            <th title="${itemh.sfec_inicio} al ${itemh.sfec_limite}">${itemh.descripcion}</th>
                                            </c:if>
                                            <c:if test="${itemh.estado=='activo'}">
                                            <th style="background: rgb(156, 247, 156)" title="${itemh.sfec_inicio} al ${itemh.sfec_limite}">${itemh.descripcion}</th>
                                            </c:if>
                                            <% } %>
                                        </c:if>
                                        <c:if test="${itemh.periodos==3}">
                                            <% if (++indexc <= 3) {%>
                                        <th title="${itemh.sfec_inicio} al ${itemh.sfec_limite}">${itemh.descripcion}</th>
                                            <% }%>
                                        </c:if>
                                    </c:forEach>
                            </tr>
                        </thead>
                        <c:forEach varStatus="i" var="item" items="${curso.estudiantes}">
                            <tr>
                                <td style="text-align: center;width: 18px" onclick="selectEstudiante('${curso.id_curso}', '${item.id_estudiante}')"><i class="details"></i></td>
                                <td style="text-align: center"><c:out value="${i.count}"/></td>
                                <td>${item.paterno} ${item.materno} ${item.nombres}</td>
                                <c:forEach varStatus="j" var="falt" items="${item.faltas}">
                                    <td>
                                        <table style="border: 0;width: 100%;" cellpadding="0" cellspacing="0">
                                            <tr title="Faltas sin licencia">
                                                <td>Fsl</td>
                                                <td style="width: 15px">${falt.fsl}</td>
                                                <td style="width: 15px">
                                                    <input type="radio" name="falta-${item.id_estudiante}" value="fsl-${falt.id_falta}" style="display: none" <c:if test="${falt.status=='activo'}"> class="editable" </c:if> />
                                                    </td>

                                                </tr>
                                                <tr title="Faltas con licencia">
                                                    <td>Fcl</td>
                                                    <td style="width: 15px">${falt.fcl}</td>
                                                <td style="width: 15px">
                                                    <input type="radio" name="falta-${item.id_estudiante}" value="fcl-${falt.id_falta}" style="display: none" <c:if test="${falt.status=='activo'}"> class="editable" </c:if> />
                                                    </td>
                                                </tr>
                                                <tr title="Retrasos">
                                                    <td>Atraso</td>
                                                    <td style="width: 15px">${falt.retraso}</td>
                                                <td style="width: 15px">
                                                    <input type="radio" name="falta-${item.id_estudiante}" value="ret-${falt.id_falta}" style="display: none" <c:if test="${falt.status=='activo'}"> class="editable" </c:if> />
                                                    </td>
                                                </tr>                                            
                                                <tr title="Sin Uniforme escolar">
                                                    <td>No Unif.</td>
                                                    <td style="width: 15px">${falt.uniforme}</td>
                                                <td style="width: 15px">
                                                    <input type="checkbox" name="sunif-${item.id_estudiante}" value="sun-${falt.id_falta}" style="display: none" <c:if test="${falt.status=='activo'}"> class="editable" </c:if> />
                                                    </td>
                                                </tr>
                                            </table>
                                        </td>
                                </c:forEach>
                            </tr>
                        </c:forEach>
                    </table>
                    <input type="hidden" name="opcion" value="_update">
                    <input type="hidden" name="id_curso" value="${curso.id_curso}">
                </form>
            </div>
        </c:if>
        <div id="documentoPdF" style="display:none">
            <!--div class="headercontent">
                <button class="button-submit" onclick="javascript:openExt()">Pantalla completa</button>
            </div-->
            <div style="width:100%;height:99%">
                <iframe id="documentview" frameborder="0" style="width:100%;height:100%"></iframe>
            </div>
        </div>
    </body>
</html>