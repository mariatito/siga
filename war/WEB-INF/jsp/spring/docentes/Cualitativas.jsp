<%-- 
    Document   : Cualitativas
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
                    window.location = '<c:url value="/cualitativas.do"/>';
                    return false;
                });
                $("button.Excel").button({
                    icons: {
                        primary: " ui-icon-print"
                    }
                }).click(function () {
                    window.location='<c:url value="/cualitativas.do"/>?id_curso=${curso.id_curso}&opcion=_excel';
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

                $("button.SubirExcel").button({
                    icons: {
                        primary: "ui-icon-disk"
                    }
                });
            });
            function openWindow(form, title, w, h) {
                docentesWin = dhtmlmodal.open('win' + form, 'div', form, title, 'width=' + w + 'px,height=' + h + 'px,left=100px,top=100px,resize=1,scrolling=1');
                docentesWin.moveTo('middle', 'middle');
                return docentesWin;
            }
            function selectCurso(id_curso) {
                window.location = '<c:url value="/cualitativas.do"/>?id_curso=' + id_curso;
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
                        <h1>Calificaciones Cualitativas</h1>
                        <h3>Registro de calificaciones cualitativas</h3>
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
                            <th>Curso</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach varStatus="j" var="item" items="${cursos}">
                            <tr>
                                <td style="text-align: center;width: 18px" onclick="selectCurso('${item.id_curso}')"><i class="details"></i></td>
                                <td>
                                    ${item.curso} de ${item.ciclo}
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
                        <h1>Apreciacion cualitativa de ${curso.curso} de ${curso.ciclo} (${id_gestion})</h1>
                        <h3>Registro de la apreciación cualitativa de los esttudiantes</h3>
                    </td>
                    <td style="text-align: right">
                        <button class="Guardar"  style="display: none">
                            Guardar
                        </button>
                        <button class="Excel">
                            Excel
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
                <table class="gridST" width="100%" cellpadding="0" cellspacing="0" border="0">
                    <thead>
                        <tr>
                            <th>Evaluaci&oacute;n</th>
                            <th>Fecha registro</th>
                            <th>Subir</th>
                        </tr>
                    </thead>
                    <% int indexc = 0;%>
                    <c:forEach varStatus="i" var="itemh" items="${curso.fecRegNotas}">
                        <tr>
                            <c:if test="${itemh.periodos==2}">
                                <% if (++indexc <= 4) {%>
                                <td title="${itemh.sfec_inicio} al ${itemh.sfec_limite}">${itemh.descripcion}</td>                                
                                <% } %>
                            </c:if>
                            <c:if test="${itemh.periodos==3}">
                                <% if (++indexc <= 3) {%>
                                <td title="${itemh.sfec_inicio} al ${itemh.sfec_limite}">${itemh.descripcion}</td>
                                <% }%>
                            </c:if>
                            <td>${itemh.sfec_inicio} al ${itemh.sfec_limite}</td>
                            <td>
                                <c:if test="${itemh.estado=='activo'}">
                                    <form action="<c:url value="/cualitativas.do"/>" method="post" name="formExcel" enctype="multipart/form-data">
                                        <input type="hidden" name="id_curso" value="${curso.id_curso}">
                                        <input type="hidden" name="columna" value="${i.count}">
                                        <input type="file" name="fichero" size="50" accept="jxlx/jxl">
                                        <!--button type="submit"><img src="imagenes/iconos_sigaa/guardar_exc.png" width="12px">&nbsp;&nbsp;Finalizar</button-->
                                        <button class="SubirExcel">
                                            Guardar
                                        </button>
                                    </form>
                                </c:if> 
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                <br>
                <!--form action="<c :url value="/registrarFaltasAdm.do"/>" method="post" name="formFaltas"-->
                    <table  class="gridST" width="100%" cellpadding="0" cellspacing="0" border="0">
                        <thead>
                            <tr>
                                <th>Nro.</th>
                                <th>Apellidos y nombres</th>
                                    <% indexc = 0;%>
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
                                <td style="text-align: center;width: 4%"><c:out value="${i.count}"/></td>
                                <td style="width: 35%">${item.paterno} ${item.materno} ${item.nombres}</td>
                                <c:forEach varStatus="j" var="cond" items="${item.conductas}">
                                    <td style="width: 15%">
                                        ${cond.diagnostico}
                                    </td>
                                </c:forEach>
                            </tr>
                        </c:forEach>
                    </table>
                <!--/form-->
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