<%-- 
    Document   : MejoresEstudiantes
    Created on : 01-jul-2010, 9:57:44
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
        <link rel="stylesheet" type="text/css" href="css/style2014.css" media="screen" />
        <!--link rel="stylesheet" type="text/css" href="css/st.css" media="screen" /-->

        <script type="text/javascript" src="js/jquery-1.10.1.min.js"></script>
        <script type="text/javascript" src="js/script.js"></script>
        <script src="jquery-ui/jquery-ui-1.10.3.js" type="text/javascript"></script>
        <link href="jquery-ui/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript">
            $(function () {
                $("#trimestral").buttonset();
                $("#bimestral").buttonset();

                $("button.Aceptar").button({
                    icons: {
                        primary: "ui-icon-check"
                    }
                }).click(function () {
                    $.get('mejorEstudiante.do',
                            {
                                opcion: "getResultado",
                                gestion_id: $("select[name='gestion']").val(),
                                curso_id: $("select[name='id_curso']").val(),
                                tipo: $("input[name='valor']").val(),
                                id_sexo: $("select[name='categoria']").val(),
                                t1: ($("#trimestre1").is(":checked")) ? "E1" : "",
                                t2: ($("#trimestre2").is(":checked")) ? "E2" : "",
                                t3: ($("#trimestre3").is(":checked")) ? "E3" : "",
                                b1: ($("#bimestre1").is(":checked")) ? "E1" : "",
                                b2: ($("#bimestre2").is(":checked")) ? "E2" : "",
                                b3: ($("#bimestre3").is(":checked")) ? "E3" : "",
                                b4: ($("#bimestre4").is(":checked")) ? "E4" : ""
                            },
                    function (data) {
                        $('#html').html(data);
                    });
                });

                $("select[name='gestion']").change(function () {
                    $.get('mejorEstudiante.do', {opcion: "getTrimestreBimestre", gestion_id: $("select[name='gestion']").val(), curso_id: $("select[name='id_curso']").val()}, function (data) {
                        var datos = data.split("|");
                        $('input[name="texto"]').val(datos[0]);
                        $('input[name="valor"]').val(parseInt(datos[1]));
                        $('#bimestral').css("display", "none");
                        $('#trimestral').css("display", "none");
                        if (parseInt(datos[1]) == 1) {
                            $('#bimestral').css("display", "inline");
                            $('#trimestral').css("display", "inline");
                        } else if (parseInt(datos[1]) == 2) {
                            $('#bimestral').css("display", "inline");
                        } else if (parseInt(datos[1]) == 3) {
                            $('#trimestral').css("display", "inline");
                        }
                    });
                });

                $("select[name='id_curso']").change(function () {
                    $.get('mejorEstudiante.do', {opcion: "getTrimestreBimestre", gestion_id: $("select[name='gestion']").val(), curso_id: $("select[name='id_curso']").val()}, function (data) {
                        var datos = data.split("|");
                        $('input[name="texto"]').val(datos[0]);
                        $('input[name="valor"]').val(parseInt(datos[1]));
                        $('#bimestral').css("display", "none");
                        $('#trimestral').css("display", "none");
                        if (parseInt(datos[1]) == 1) {
                            $('#bimestral').css("display", "inline");
                            $('#trimestral').css("display", "inline");
                        } else if (parseInt(datos[1]) == 2) {
                            $('#bimestral').css("display", "inline");
                        } else if (parseInt(datos[1]) == 3) {
                            $('#trimestral').css("display", "inline");
                        }
                    });
                });
            });
        </script>
        <style>
            .table { border-collapse: collapse; width: 100%; }
            .table th{ border: 1px solid #eee; padding: 5px 2px; cursor: pointer}
            .table td { border: 1px solid #eee; padding: 3px 2px; text-align: left; }
        </style>
    </head>
    <body>
        <table class="headerPage" cellpadding="0" cellspacing="0">
            <tr>
                <td>
                    <h1>Estudiantes Por Mejor Promedio</h1>
                </td>
                <td style="text-align: right">
                    <button class="Aceptar">
                        Aceptar
                    </button>
                </td>
            </tr>
        </table>
        <table class="form">
            <tr>
                <td class="prompt" style="width: 50%"> Gestión : </td>
                <td class="value" style="width: 50%">
                    <select name="gestion" class="inputUI width-small">
                        <c:forEach varStatus="j" var="item" items="${gestiones}">
                            <option value="${item.id_gestion}">${item.id_gestion}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td class="prompt" style="width: 50%"> Cursos : </td>
                <td class="value" style="width: 50%">
                    <select name="id_curso" class="inputUI width-large">
                        <!--option value="C" <c :if test="$ {id_curso=='C'}">selected</c :if>> Colegio (general)</option-->
                        <c:forEach var="item" items="${cursos}">
                            <c:if test="${item.id_curso != 'K'}">
                                <option value="${item.id_curso}" <c:if test="${item.id_curso == id_curso}"> selected </c:if>> ${item.curso} de ${item.ciclo}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td class="prompt"> Evaluacion : </td>
                <td class="value">
                    <input type="text" name="texto" value="${texto}" class="inputUI width-medium" readonly/>
                    <input type="hidden" name="valor" value="${valor}" class="inputUI width-medium"/>
                </td>
            </tr>
        </tr>
        <tr>
            <td class="prompt"> Trimestral/Bimestral : </td>
            <td class="value">
                <div id='bimestral' style='float: left;margin-bottom: 5px;display: inline'>
                    <input type='checkbox' id='bimestre1'><label for='bimestre1'>1er Bimestre</label>
                    <input type='checkbox' id='bimestre2'><label for='bimestre2'>2do Bimestre</label>
                    <input type='checkbox' id='bimestre3'><label for='bimestre3'>3er Bimestre</label>
                    <input type='checkbox' id='bimestre4'><label for='bimestre4'>4to Bimestre</label>
                </div>
                <div id='trimestral' style='float: left;display: none;'>
                    <input type='checkbox' id='trimestre1'><label for='trimestre1'>1er Trimestre</label>
                    <input type='checkbox' id='trimestre2'><label for='trimestre2'>2do Trimestre</label>
                    <input type='checkbox' id='trimestre3'><label for='trimestre3'>3er Trimestre</label>
                </div>
            </td>
        </tr>
        <tr>
            <td class="prompt" style="width: 50%"> G&eacute;nero : </td>
            <td class="value" style="width: 50%">
                <select name="categoria" id="categoria" class="inputUI width-small">
                    <c:forEach var="item" items="${tipos_sexos}">
                        <option value="${item.id}"<c:if test="${item.id == categoria  }"> selected </c:if>><c:if test="${item.id =='A'}">MIXTO</c:if><c:if test="${item.id !='A'}">${item.valor}</c:if>
                        </c:forEach>
                </select>
            </td>
        </tr>
    </table>
    <div class="maincontent">
        <c:if test="${!empty question}">
            <form action="<c:url value="/mejorEstudiante.do"/>" method="post" name="formConsultas">
                <table style="width: 100%">
                    <tr>
                        <td style="width: 50%" class="prompt">Gestion</td>
                        <td style="width: 50%" class="value"><input type="text" name="idgestion" disabled value="${id_gestion}" class="inputUI" /></td>
                    </tr>
                </table>
                <fieldset>                        
                    <label for="id_curso" class="block">Curso</label>
                    <select name="id_curso" id="id_curso" class="block text ui-widget-content ui-corner-all" style="width: 200px;padding: 0">
                        <option value="C" <c:if test="${id_curso=='C'}">selected</c:if>> Colegio (general)
                            <c:forEach var="item" items="${cursos}">
                                <c:if test="${item.id_curso != 'K'}">
                                <option value="${item.id_curso}" <c:if test="${item.id_curso == id_curso}"> selected </c:if>> ${item.curso} de ${item.ciclo}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                    <label for="categoria" class="block">Genero</label>

                    <label for="te" class="block">Tipo Evaluacion</label>
                    <c:forEach varStatus="j" var="item_per" items="${periodo_cursos}">
                        <div id="evas1_${item_per.id_curso}" class="evas1" <c:if test="${item_per.id_curso!=id_curso}"> style="display: none "</c:if>>
                            <c:forEach varStatus="j" var="item_fec_notas" items="${item_per.fecnotas}">
                                <label>
                                    <input type="checkbox" name="check_${item_per.id_curso}_${j.index}" value="${item_fec_notas.id_eva}"/>
                                    ${item_fec_notas.descripcion}&nbsp;&nbsp;
                                </label>
                            </c:forEach>
                        </div>
                    </c:forEach>
                    <div id="format">
                        <input type="checkbox" id="check1" /><label for="check1">B</label>
                        <input type="checkbox" id="check2" /><label for="check2">I</label>
                        <input type="checkbox" id="check3" /><label for="check3">U</label>
                    </div>
                    <br/>
                    <button class="generarConsulta" style="margin: 0 auto">Generar Consulta</button>                        
                    <input type="hidden" name="opcion" value="PROMEDIO">
                    <input type="hidden" name="id_gestion" value="${id_gestion}">
                </fieldset>
            </form>
        </c:if>
        <div id="html">

        </div>
        <c:if test="${!empty promedio}">
            <table cellpadding="0" cellspacing="0" border="0" style="width: 100% ; cursor:pointer" class="gridContent">
                <tr>
                    <td class="tableHeader"><strong>Lista de estudiantes</strong></td>
                </tr>
                <tr>
                    <td>
                        <table cellpadding="0" cellspacing="0" border="0">
                            <tr>
                                <td class="text-label"><button class="button-normal"  onclick="setImprimirExcelPromedio()" title="Imprimir documento en excel">Imprimir Excel</button>&nbsp;</td>
                                <td class="text-label">&nbsp;&nbsp;Curso(s):</td>
                                <c:if test="${id_curso!='C'}"><td style="color:gray">${curso.curso} de ${curso.ciclo}</td></c:if>
                                <c:if test="${id_curso=='C'}"><td style="color:gray">1ro de primaria a 4to de secundaria</td></c:if>
                                    <td class="text-label">&nbsp;&nbsp;Inscripcion:</td>
                                    <td style="color:gray">${id_gestion}</td>
                                <td class="text-label">&nbsp;&nbsp;Categoria:</td>
                                <td style="color:gray"><c:if test="${categoria=='A'}">Mixto</c:if><c:if test="${categoria=='F'}">Femenino</c:if><c:if test="${categoria=='M'}">Masculino</c:if></td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div id="resultlayer1">
                                <table width="100%" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF">
                                    <tr>
                                        <td class="gridHeaders" width="5%">Nro.</td>
                                        <td class="gridHeaders" width="10%">C&oacute;digo</td>
                                    <c:if test="${id_curso!='C'}">
                                        <c:if test="${id_trimestre=='T1'}">
                                            <td class="gridHeaders" width="75%">Apellidos y nombres</td>
                                            <td class="gridHeaders" width="10%">1er TRIM (%)</td>
                                        </c:if>
                                        <c:if test="${id_trimestre=='T2'}">
                                            <td class="gridHeaders" width="75%">Apellidos y nombres</td>
                                            <td class="gridHeaders" width="10%">2do TRIM (%)</td>
                                        </c:if>
                                        <c:if test="${id_trimestre=='T3'}">
                                            <td class="gridHeaders" width="75%">Apellidos y nombres</td>
                                            <td class="gridHeaders" width="10%">3er TRIM (%)</td>
                                        </c:if>
                                        <c:if test="${id_trimestre=='A'}">
                                            <td class="gridHeaders" width="45%">Apellidos y nombres</td>
                                            <td class="gridHeaders" width="10%" title="Promedio 1er trimestre (%)">1er TRIM</td>
                                            <td class="gridHeaders" width="10%" title="Promedio 2do trimestre (%)">2do TRIM</td>
                                            <td class="gridHeaders" width="10%" title="Promedio 3er trimestre (%)">3er TRIM</td>
                                            <td class="gridHeaders" width="10%" title="Promedio total (%)">TOTAL</td>
                                        </c:if>
                                    </c:if>
                                    <c:if test="${id_curso=='C'}">
                                        <c:if test="${id_trimestre=='T1'}">
                                            <td class="gridHeaders" width="60%">Apellidos y nombres</td>
                                            <td class="gridHeaders" width="15%">Curso</td>
                                            <td class="gridHeaders" width="10%">1er TRIM (%)</td>
                                        </c:if>
                                        <c:if test="${id_trimestre=='T2'}">
                                            <td class="gridHeaders" width="60%">Apellidos y nombres</td>
                                            <td class="gridHeaders" width="15%">Curso</td>
                                            <td class="gridHeaders" width="10%">2do TRIM (%)</td>
                                        </c:if>
                                        <c:if test="${id_trimestre=='T3'}">
                                            <td class="gridHeaders" width="60%">Apellidos y nombres</td>
                                            <td class="gridHeaders" width="15%">Curso</td>
                                            <td class="gridHeaders" width="10%">3er TRIM (%)</td>
                                        </c:if>
                                        <c:if test="${id_trimestre=='A'}">
                                            <td class="gridHeaders" width="38%">Apellidos y nombres</td>
                                            <td class="gridHeaders" width="15%">Curso</td>
                                            <td class="gridHeaders" width="8%" title="Promedio 1er trimestre (%)">1er TRIM</td>
                                            <td class="gridHeaders" width="8%" title="Promedio 2do trimestre (%)">2do TRIM</td>
                                            <td class="gridHeaders" width="8%" title="Promedio 3er trimestre (%)">3er TRIM</td>
                                            <td class="gridHeaders" width="8%" title="Promedio total (%)">TOTAL</td>
                                        </c:if>
                                    </c:if>

                                </tr>
                                <c:if test="${empty curso.estudiantes}">
                                    <tr>
                                        <td class="gridData" colspan="4">No se han encontrado elementos.</td>
                                    </tr>
                                </c:if>
                                <% int c = 1;%>
                                <c:forEach varStatus="i" var="item" items="${curso.estudiantes}">
                                    <c:if test="${categoria=='A'}">
                                        <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                            <td align="center" class="gridData"><c:out value="${i.count}"/> </td>
                                            <td class="gridData">${item.codigo}</td>
                                            <td class="gridData" onclick="selectEstudiante('${item.id_curso}', '${item.id_estudiante}')">${item.paterno} ${item.materno} ${item.nombres}</td>
                                            <c:if test="${id_curso!='C'}">
                                                <c:if test="${id_trimestre=='T1'}">
                                                    <td class="gridData" align="right" style="color: navy">${item.trim1}</td>
                                                </c:if>
                                                <c:if test="${id_trimestre=='T2'}">
                                                    <td class="gridData" align="right" style="color: navy">${item.trim2}</td>
                                                </c:if>
                                                <c:if test="${id_trimestre=='T3'}">
                                                    <td class="gridData" align="right" style="color: navy">${item.trim3}</td>
                                                </c:if>
                                                <c:if test="${id_trimestre=='A'}">
                                                    <td class="gridData" align="right">${item.trim1}</td>
                                                    <td class="gridData" align="right">${item.trim2}</td>
                                                    <td class="gridData" align="right">${item.trim3}</td>
                                                    <td class="gridData" align="right" style="color: navy">${item.total}</td>
                                                </c:if>
                                            </c:if>
                                            <c:if test="${id_curso=='C'}">
                                                <c:if test="${id_trimestre=='T1'}">
                                                    <td class="gridData">${item.curso}</td>
                                                    <td class="gridData" align="right" style="color: navy">${item.trim1}</td>
                                                </c:if>
                                                <c:if test="${id_trimestre=='T2'}">
                                                    <td class="gridData">${item.curso}</td>
                                                    <td class="gridData" align="right" style="color: navy">${item.trim2}</td>
                                                </c:if>
                                                <c:if test="${id_trimestre=='T3'}">
                                                    <td class="gridData">${item.curso}</td>
                                                    <td class="gridData" align="right" style="color: navy">${item.trim3}</td>
                                                </c:if>
                                                <c:if test="${id_trimestre=='A'}">
                                                    <td class="gridData">${item.curso}</td>
                                                    <td class="gridData" align="right">${item.trim1}</td>
                                                    <td class="gridData" align="right">${item.trim2}</td>
                                                    <td class="gridData" align="right">${item.trim3}</td>
                                                    <td class="gridData" align="right" style="color: navy" >${item.total}</td>
                                                </c:if>
                                            </c:if>
                                        </tr>
                                    </c:if>
                                    <c:if test="${categoria=='F' && item.id_sexo==categoria}">
                                        <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                            <td align="center" class="gridData"><% out.print(c++);%></td>
                                            <td class="gridData">${item.codigo}</td>
                                            <td class="gridData" onclick="selectEstudiante('${item.id_curso}', '${item.id_estudiante}')">${item.paterno} ${item.materno} ${item.nombres}</td>
                                            <c:if test="${id_curso!='C'}">
                                                <c:if test="${id_trimestre=='T1'}">
                                                    <td class="gridData" align="right" style="color: navy">${item.trim1}</td>
                                                </c:if>
                                                <c:if test="${id_trimestre=='T2'}">
                                                    <td class="gridData" align="right" style="color: navy">${item.trim2}</td>
                                                </c:if>
                                                <c:if test="${id_trimestre=='T3'}">
                                                    <td class="gridData" align="right" style="color: navy">${item.trim3}</td>
                                                </c:if>
                                                <c:if test="${id_trimestre=='A'}">
                                                    <td class="gridData" align="right">${item.trim1}</td>
                                                    <td class="gridData" align="right">${item.trim2}</td>
                                                    <td class="gridData" align="right">${item.trim3}</td>
                                                    <td class="gridData" align="right" style="color: navy" >${item.total}</td>
                                                </c:if>
                                            </c:if>
                                            <c:if test="${id_curso=='C'}">
                                                <c:if test="${id_trimestre=='T1'}">
                                                    <td class="gridData">${item.curso}</td>
                                                    <td class="gridData" align="right" style="color: navy">${item.trim1}</td>
                                                </c:if>
                                                <c:if test="${id_trimestre=='T2'}">
                                                    <td class="gridData">${item.curso}</td>
                                                    <td class="gridData" align="right" style="color: navy">${item.trim2}</td>
                                                </c:if>
                                                <c:if test="${id_trimestre=='T3'}">
                                                    <td class="gridData">${item.curso}</td>
                                                    <td class="gridData" align="right" style="color: navy">${item.trim3}</td>
                                                </c:if>
                                                <c:if test="${id_trimestre=='A'}">
                                                    <td class="gridData">${item.curso}</td>
                                                    <td class="gridData" align="right">${item.trim1}</td>
                                                    <td class="gridData" align="right">${item.trim2}</td>
                                                    <td class="gridData" align="right">${item.trim3}</td>
                                                    <td class="gridData" align="right" style="color: navy" >${item.total}</td>
                                                </c:if>
                                            </c:if>
                                        </tr>
                                    </c:if>
                                    <c:if test="${categoria=='M' && item.id_sexo==categoria}">
                                        <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                            <td align="center" class="gridData"><% out.print(c++);%></td>
                                            <td class="gridData">${item.codigo}</td>
                                            <td class="gridData" onclick="selectEstudiante('${item.id_curso}', '${item.id_estudiante}')">${item.paterno} ${item.materno} ${item.nombres}</td>
                                            <c:if test="${id_curso!='C'}">
                                                <c:if test="${id_trimestre=='T1'}">

                                                    <td class="gridData" align="right" style="color: navy">${item.trim1}</td>
                                                </c:if>
                                                <c:if test="${id_trimestre=='T2'}">
                                                    <td class="gridData" align="right" style="color: navy">${item.trim2}</td>
                                                </c:if>
                                                <c:if test="${id_trimestre=='T3'}">
                                                    <td class="gridData" align="right" style="color: navy">${item.trim3}</td>
                                                </c:if>
                                                <c:if test="${id_trimestre=='A'}">
                                                    <td class="gridData" align="right">${item.trim1}</td>
                                                    <td class="gridData" align="right">${item.trim2}</td>
                                                    <td class="gridData" align="right">${item.trim3}</td>
                                                    <td class="gridData" align="right" style="color: navy" >${item.total}</td>
                                                </c:if>
                                            </c:if>
                                            <c:if test="${id_curso=='C'}">
                                                <c:if test="${id_trimestre=='T1'}">
                                                    <td class="gridData">${item.curso}</td>
                                                    <td class="gridData" align="right" style="color: navy">${item.trim1}</td>
                                                </c:if>
                                                <c:if test="${id_trimestre=='T2'}">
                                                    <td class="gridData">${item.curso}</td>
                                                    <td class="gridData" align="right" style="color: navy">${item.trim2}</td>
                                                </c:if>
                                                <c:if test="${id_trimestre=='T3'}">
                                                    <td class="gridData">${item.curso}</td>
                                                    <td class="gridData" align="right" style="color: navy">${item.trim3}</td>
                                                </c:if>
                                                <c:if test="${id_trimestre=='A'}">
                                                    <td class="gridData">${item.curso}</td>
                                                    <td class="gridData" align="right">${item.trim1}</td>
                                                    <td class="gridData" align="right">${item.trim2}</td>
                                                    <td class="gridData" align="right">${item.trim3}</td>
                                                    <td class="gridData" align="right" style="color: navy" >${item.total}</td>
                                                </c:if>
                                            </c:if>
                                        </tr>
                                    </c:if>
                                </c:forEach>
                            </table>
                        </div>
                    </td>
                </tr>
            </table>
        </c:if>
    </div>
</body>
</html>