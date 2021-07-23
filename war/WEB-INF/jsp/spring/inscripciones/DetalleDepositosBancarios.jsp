<%-- 
    Document   : DetalleDepositosBancarios
    Created on : 15-jun-2015, 20:46:34
    Author     : Mi PC, Marco Antonio Quenta Velasco
--%>
<%@page contentType="text/html" pageEncoding="LATIN1"%>
<%@taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=LATIN1">
        <title>SIGAA - Santa Teresa</title>
        <link rel="stylesheet" type="text/css" href="css/style2014.css" media="screen" />
        <!--link rel="stylesheet" type="text/css" href="css/st.css" media="screen" /-->

        <script type="text/javascript" src="js/jquery-1.10.1.min.js"></script>
        <script type="text/javascript" src="js/script.js"></script>
        <script src="jquery-ui/jquery-ui-1.10.3.js" type="text/javascript"></script>
        <link href="jquery-ui/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript">
            $(function () {
                $("#cuotas").buttonset();
                $("#cursos").buttonset();

                $("button.btnImprimir").button({
                    icons: {
                        primary: "ui-icon-print"
                    }
                }).click(function () {
                    $("input[name='opcionImp']").val("_excel");
                    $("form[name='formDepositos']").submit();
                    return false;
                });
                $(".accion").change(function () {
                    $("input[name='opcionImp']").val("");
                    $("form[name='formDepositos']").submit();
                });
                $("#cuotasTR").hide();
                $("#cursosTR").hide();

            });
        </script>
        <style>
            .table { border-collapse: collapse; width: 100%; }
            .table th{ border: 1px solid #eee; padding: 5px 2px; cursor: pointer}
            .table td { border: 1px solid #eee; padding: 3px 2px; text-align: left; }
            .adm{font-size: 9px;opacity: 0.5}
            .dep{font-size: 12px}
            .adm,.dep{text-align: right} 
            .cancelado{background: rgb(159, 252, 156);}

            #cursos label {width: 180px}
        </style>
    </head>
    <body class="bodyClass" style="display: none">
        <table class="headerPage" cellpadding="0" cellspacing="0">
            <tr>
                <td>
                    <h1>Detalle de Depositos Bancarios</h1>
                </td>
                <td style="text-align: right">
                    <button class="btnImprimir">
                        Excel
                    </button>
                </td>
            </tr>
        </table>
        <form action="" method="post" name="formDepositos">
            <input name="opcion" type="hidden" value="_filtro"/>
            <input name="opcionImp" type="hidden" value=""/>
            <table class="form">
                <tr>
                    <td class="prompt" style="width: 50%"> Gestión : </td>
                    <td class="value" style="width: 50%">
                        <select name="gestion" class="inputUI width-small accion">
                            <c:forEach varStatus="j" var="item" items="${gestiones}">
                                <option value="${item.id_gestion}" <c:if test="${item.id_gestion == selectGestion}"> selected </c:if>>${item.id_gestion}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="prompt"> Cursos : </td>
                    <td class="value">
                        <div id='cursos' style='float: left;margin-bottom: 5px'>
                            ${cursosHtml}
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="prompt"> Estado : </td>
                    <td class="value">
                        <select name="estado" class="inputUI width-large accion">
                            <option value="1" <c:if test="${1 == selectEstado}"> selected </c:if>>Todos</option>
                            <option value="2" <c:if test="${2 == selectEstado}"> selected </c:if>>Deudores</option>
                            <option value="3" <c:if test="${3 == selectEstado}"> selected </c:if>>Regulares</option>
                            </select>
                        </td>
                    </tr>
                    <tr <c:if test="${selectEstado==1}"> id="cuotasTR" </c:if>>
                        <td class="prompt"> Cuotas : </td>
                        <td class="value">
                            <div id='cuotas' style='float: left;margin-bottom: 5px'>
                                <input type='checkbox' id='cuotas1' class="accion" name="c1" <c:if test="${1 == selectC1}"> checked </c:if>><label for='cuotas1'>C1</label>
                            <input type='checkbox' id='cuotas2' class="accion" name="c2" <c:if test="${1 == selectC2}"> checked </c:if>><label for='cuotas2'>C2</label>
                            <input type='checkbox' id='cuotas3' class="accion" name="c3" <c:if test="${1 == selectC3}"> checked </c:if>><label for='cuotas3'>C3</label>
                            <input type='checkbox' id='cuotas4' class="accion" name="c4" <c:if test="${1 == selectC4}"> checked </c:if>><label for='cuotas4'>C4</label>
                            <input type='checkbox' id='cuotas5' class="accion" name="c5" <c:if test="${1 == selectC5}"> checked </c:if>><label for='cuotas5'>C5</label>
                            <input type='checkbox' id='cuotas6' class="accion" name="c6" <c:if test="${1 == selectC6}"> checked </c:if>><label for='cuotas6'>C6</label>
                            <input type='checkbox' id='cuotas7' class="accion" name="c7" <c:if test="${1 == selectC7}"> checked </c:if>><label for='cuotas7'>C7</label>
                            <input type='checkbox' id='cuotas8' class="accion" name="c8" <c:if test="${1 == selectC8}"> checked </c:if>><label for='cuotas8'>C8</label>
                            <input type='checkbox' id='cuotas9' class="accion" name="c9" <c:if test="${1 == selectC9}"> checked </c:if>><label for='cuotas9'>C9</label>
                            <input type='checkbox' id='cuotas10' class="accion" name="c10" <c:if test="${1 == selectC10}"> checked </c:if>><label for='cuotas10'>C10</label>
                            </div>
                        </td>
                    </tr>
                </table>
            </form>
            <div class='maincontent'>
                <div class="nroRegistros">${nroRegistros}</div>
            <table  class="gridST" width="100%" cellpadding="0" cellspacing="0" border="0">
                <thead>
                    <tr>
                        <th></th>
                        <th>Código</th>
                        <th>Apellidos y nombres</th>
                        <th>Curso</th>
                        <th title="Cuota 1">C1</th>
                        <th title="Cuota 2">C2</th>
                        <th title="Cuota 3">C3</th>
                        <th title="Cuota 4">C4</th>
                        <th title="Cuota 5">C5</th>
                        <th title="Cuota 6">C6</th>
                        <th title="Cuota 7">C7</th>
                        <th title="Cuota 8">C8</th>
                        <th title="Cuota 9">C9</th>
                        <th title="Cuota 10">C10</th>
                    </tr>
                </thead>
                <c:forEach varStatus="i" var="item" items="${depositos}">
                    <tr>
                        <td style="text-align: center;width: 18px" onclick="selectEstudiante('${item.id_estudiante}')"><i class="details"></i></td>
                        <td style="text-align: center">${item.codigo}</td>
                        <td>${item.paterno} ${item.materno} ${item.nombres}${item.desc_curso}</td>
                        <td>${item.desc_curso}</td>
                        <td <c:if test="${item.cuota1D>0}"> class="cancelado" </c:if>><p class="adm">${item.cuota1A}</p><p class="dep">${item.cuota1D}</p></td>
                        <td <c:if test="${item.cuota2D>0}"> class="cancelado" </c:if>><p class="adm">${item.cuota2A}</p><p class="dep">${item.cuota2D}</p></td>
                        <td <c:if test="${item.cuota3D>0}"> class="cancelado" </c:if>><p class="adm">${item.cuota3A}</p><p class="dep">${item.cuota3D}</p></td>
                        <td <c:if test="${item.cuota4D>0}"> class="cancelado" </c:if>><p class="adm">${item.cuota4A}</p><p class="dep">${item.cuota4D}</p></td>
                        <td <c:if test="${item.cuota5D>0}"> class="cancelado" </c:if>><p class="adm">${item.cuota5A}</p><p class="dep">${item.cuota5D}</p></td>
                        <td <c:if test="${item.cuota6D>0}"> class="cancelado" </c:if>><p class="adm">${item.cuota6A}</p><p class="dep">${item.cuota6D}</p></td>
                        <td <c:if test="${item.cuota7D>0}"> class="cancelado" </c:if>><p class="adm">${item.cuota7A}</p><p class="dep">${item.cuota7D}</p></td>
                        <td <c:if test="${item.cuota8D>0}"> class="cancelado" </c:if>><p class="adm">${item.cuota8A}</p><p class="dep">${item.cuota8D}</p></td>
                        <td <c:if test="${item.cuota9D>0}"> class="cancelado" </c:if>><p class="adm">${item.cuota9A}</p><p class="dep">${item.cuota9D}</p></td>
                        <td <c:if test="${item.cuota10D>0}"> class="cancelado" </c:if>><p class="adm">${item.cuota10A}</p><p class="dep">${item.cuota10D}</p></td>
                        </tr>
                </c:forEach>
            </table>
            <div class="nroRegistros">${nroRegistros}</div>
        </div>
    </body>
    <script>
        $(function(){
            $(".bodyClass").show();
        });
    </script>
</html>
