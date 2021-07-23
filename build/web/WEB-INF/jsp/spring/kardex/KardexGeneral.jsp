<%-- 
    Document   : KardexEstudiantes
    Created on : 16-feb-2013, 12:28:51
    Author     : Pluks Solutions
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


        <script type="text/javascript">
            $(function () {
                $("#detalle_kardex").dialog({
                    autoOpen: false, 
                    height: 400,
                    width: 800,
                    modal: false,
                    close:false,
                    buttons: {
                        "Salir": function () {
                            Listar($(".changeGestion").val(), $("#id_curso").val())
                            $(this).dialog("close");
                        }
                    }
                });
               $(".ui-dialog-titlebar-close").click(function(){
                   Listar($(".changeGestion").val(), $("#id_curso").val())
               });
                $(".changeGestion").change(function () {
                    Listar($(".changeGestion").val(), $("#id_curso").val())
                });

                $("#id_curso").change(function () {
                    Listar($(".changeGestion").val(), $("#id_curso").val())
                });

                var Listar = function (gestion, id_curso) {
                    $.get('kardexGeneral.do',
                            {
                                opcion: "getListarKardexGeneral",
                                gestion: gestion,
                                id_curso: id_curso
                            },
                    function (data) {
                        $('#html').html(data);
                    });
                };
                if (!$.trim($('#html').html()).length) {
                    Listar($(".changeGestion").val(), $("#id_curso").val())
                }

                $("button.Aceptar").button({
                    icons: {
                        primary: "ui-icon-check"
                    }
                }).click(function () {
                    alert(234242)
                });
            });

            function selectEstudiante(id_estudiante, id_curso, id_gestion, nombres, curso) {
                $("#detalle_kardex").dialog("open");
                $(".nombres_est").html(nombres);
                $(".curso_est").html(curso);
                $(".gestion_est").html(id_gestion);
                $.get('kardexGeneral.do',
                        {
                            opcion: "getKardexDetalle",
                            id_estudiante:id_estudiante,
                            id_gestion:id_gestion,
                            id_curso: id_curso
                        },
                function (data) {
                    $('#detalle').html(data);
                });
            }
            function Actualizar(idkardexdetalle,id_estudiante, id_curso, id_gestion) {
                var su=0;
                if($('#su-'+idkardexdetalle).is(':checked')){
                    su=1;
                }
                $.get('kardexGeneral.do',
                        {
                            opcion: "getKardexDetalle",
                            idkardexdetalle:idkardexdetalle,
                            id_estudiante:id_estudiante,
                            id_gestion:id_gestion,
                            id_curso: id_curso,
                            su:su,
                            actividades:$('#seguimiento-'+idkardexdetalle).val()
                        },
                function (data) {
                    $('#detalle').html(data);
                });
            }
        </script>
    </head>
    <body>
        <table class="headerPage" cellpadding="0" cellspacing="0">
            <tr>
                <td>
                    <h1>Kardex General</h1>
                </td>
                <td style="text-align: right">
                    <!--button class="Aceptar">
                        Aceptar
                    </button-->
                </td>
            </tr>
        </table>
        <table class="form">
            <tr>
                <td class="prompt" style="width: 50%"> Gestión : </td>
                <td class="value" style="width: 50%">
                    <select name="gestion" class="inputUI width-small changeGestion">
                        <c:forEach varStatus="j" var="item" items="${gestiones}">
                            <option value="${item.id_gestion}">${item.id_gestion}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td class="prompt" style="width: 50%"> Cursos : </td>
                <td class="value" style="width: 50%">
                    <select name="id_curso" id="id_curso" class="inputUI width-large">
                        <option value="0">Todos</option>
                        <c:forEach var="item" items="${cursos}">
                            <option value="${item.id_curso}"> ${item.curso} de ${item.ciclo}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
        </table>
        <div class="maincontent">
            <div id="html"></div>  
        </div>
        <div id="detalle_kardex" title="Detalle Kardex">
            <table class="form">
                <tr>
                    <td class="prompt" style="width: 50%"> Estudiante : </td>
                    <td class="value nombres_est" style="width: 50%"></td>
                </tr>
                <tr>
                    <td class="prompt" style="width: 50%"> Cursos : </td>
                    <td class="value curso_est" style="width: 50%"></td>
                </tr>
                <tr>
                    <td class="prompt" style="width: 50%"> Gestion : </td>
                    <td class="value gestion_est" style="width: 50%"></td>
                </tr>
            </table>
            <div id="detalle"></div>
        </div>
    </body>
</html>
