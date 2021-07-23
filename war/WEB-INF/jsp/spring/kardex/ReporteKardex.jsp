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
        <link rel="stylesheet" href="imagenes/dhtmlmodal/windowfiles/dhtmlwindow.css" type="text/css" />
        <script type="text/javascript" src="imagenes/dhtmlmodal/windowfiles/dhtmlwindow.js"></script>
        <link rel="stylesheet" href="imagenes/dhtmlmodal/modalfiles/modal.css" type="text/css" />
        <script type="text/javascript" src="imagenes/dhtmlmodal/modalfiles/modal.js"></script>
        <script type="text/javascript" src="js/jquery-1.2.2.pack.js"></script>
        <script type="text/javascript" src="js/animatedcollapse.js"></script>    
        <script type="text/javascript" src="js/prototype.js"></script>
        <link rel="stylesheet" type="text/css" href="css/module.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="css/st.css" media="screen" />
        <script type="text/javascript" src="js/Tips.js"></script>

        <link rel="stylesheet" type="text/css" href="css/calendar-blue.css" media="screen" />
        <script type="text/javascript" src="js/calendario/calendar.js"></script>
        <script type="text/javascript" src="js/calendario/calendar-es.js"></script>
        <script type="text/javascript" src="js/calendario/calendar-setup.js"></script>

        <script type="text/javascript">
            window.onload = function() {
                enableTooltips();
            };

            animatedcollapse.addDiv('success', 'fade=1');
            animatedcollapse.addDiv('failure', 'fade=1');
            animatedcollapse.init();

            function onRowover(elem) {
                elem.className = 'colover';
            }
            function onRowout(elem) {
                elem.className = 'colout';
            }
            function selectGestion(id_gestion) {
                window.location = '<c:url value="/reporteKardex.do"/>?id_gestion=' + id_gestion;
            }
            function gridEvent(layer) {
                if ($(layer).style.display === 'none') {
                    $(layer).style.display = 'inline';
                } else {
                    $(layer).style.display = 'none';
                }
            }
            function selectCurso(id_curso, cant) {
                window.location = '<c:url value="/reporteKardex.do"/>?id_curso=' + id_curso + '&id_gestion=${id_gestion}&opcion=__reporteKardex&cant=' + cant;
            }
            /*function setVerReq(form) {
             if(form.temario.value.length<2){
             alert("Ingrese TEMA DE ACUERDO A PROGRAMA (Temario)");
             form.temario.focus();
             return false; 
             }
             }*/
            function selectEstudiante(id_curso, id_estudiante) {
                window.location = '<c:url value="/reporteKardex.do"/>?id_curso=' + id_curso + '&id_gestion=${id_gestion}&opcion=__repHtml&id_estudiante=' + id_estudiante;
            }
            function setImprimirKardex(id_eva) {
                //alert(f_ini+"___"+f_fin);
                var printKardex = dhtmlmodal.open('windowMAQV', 'iframe', '<c:url value="/reporteKardex.do"/>?id_curso=${curso.id_curso}&id_gestion=${id_gestion}&opcion=_kardex_pdf&id_eva='+id_eva, 'Seguimiento Integral Diario del Estudiante curso ${curso.curso} de ${curso.ciclo}', 'width=650px,height=340px,left=100px,top=100px,resize=1,scrolling=1,center=1');
                printKardex.moveTo('middle', 'middle');
            }
        </script>
        <style>
            .desc_materias1{ font-weight: bold;text-align: left;padding:0px 2px;border-bottom: 1px solid #ccc;background-color: #aaf}
            .desc_materias11{ text-align: left;padding:0px 2px;border-bottom: 1px solid #ccc;background-color: #aaf}
            .desc_materias2{ font-weight: bold;background-color: #fff;padding:0px 2px;border-bottom: 1px solid #ccc;border-right: 1px solid #ccc }
            .desc_materias21{ background-color: #fff;padding:0px 2px;border-bottom: 1px solid #ccc;border-right: 1px solid #ccc }
        </style>
    </head>
    <body>
        <div class="headercontent">
            <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                <tr>
                    <td class="tab_init">&nbsp;</td>
                    <td style="width:20%"> 
                        <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                            <tr>
                                <td class="tab_normal" onclick="javascript:window.location = '<c:url value="/kardexEstudiante.do"/>';">Kardex</td>
                                <td class="tab_current" style="border-right:1px solid #7D909E">Reporte Kardex</td>
                            </tr>
                        </table>
                    </td>
                    <td class="tab_fin">&nbsp;</td>
                </tr>
            </table>
            <div class="titlepage" style="cursor: pointer">
                <font size="1px" onclick="javascript:window.location = '<c:url value="/reporteKardex.do"/>?id_gestion=${id_gestion}';">
                    <c:if test="${id_gestion!=null}">${id_gestion}&nbsp;<img src="imagenes/iconos_sigaa/back.png" title="Volver a la lista de cursos ${id_gestion}"></c:if>
                    </font>
                    Reporte Seguimiento Integral Diario del Estudiante<br/>
                <c:if test="${!empty curso}">
                    ${curso.curso} de ${curso.ciclo}
                </c:if>
            </div>
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
            <c:if test="${!empty gestiones}">
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
                                <c:forEach varStatus="j" var="item" items="${gestiones.gestiones}">
                                    <!-- c : if test="$ {item.estado==true}" >onclick="selectGestion ('$ {item.id_gestion}')"</ c :if -->
                                    <tr onmouseover="onRowover(this);" onmouseout="onRowout(this);" onclick="selectGestion('${item.id_gestion}');">
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
                <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                    <tr>
                        <td class="tableHeader">CURSOS (${id_gestion})</td>
                    </tr>
                    <tr>
                        <td class="gridContent">
                            <table width="100%" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF">
                                <tr>
                                    <td class="gridHeaders" width="5%">Nro.</td>
                                    <td class="gridHeaders" width="10%">Cod.</td>
                                    <td class="gridHeaders" width="85%">Curso</td>
                                </tr>
                                <c:forEach varStatus="j" var="item" items="${cursos}">
                                    <tr style="cursor:pointer" onmouseover="onRowover(this);" onmouseout="onRowout(this);" onclick="selectCurso('${item.id_curso}',${item.numEstudiantes});">
                                        <td align="center" class="gridData">${j.count}</td>
                                        <td align="center" class="gridData">${item.desc_curso}</td>
                                        <td valign="top" class="gridData">
                                            <table border="0" width="100%">
                                                <tr>
                                                    <td valign="top" width="50%"><span style="cursor:pointer; color:teal"><img src="imagenes/iconos_sigaa/etiqueta_azul.png">&nbsp;&nbsp;<strong>${item.curso} de ${item.ciclo}</strong></span></td>
                                                    <td valign="top" width="50%"><span style="cursor:pointer; color:gray; font-size:10px">Cantidad de inscritos : &nbsp;${item.numEstudiantes}</span></td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </td>
                    </tr>
                </table>
            </c:if>
            <c:if test="${!empty curso}"> <img src="imagenes/pixel_trans.gif" width="100%" height="1">
                <table cellpadding="0" cellspacing="0" border="0" style="width: 100% ; cursor:pointer">
                    <tr>
                        <td class="tableHeader"><strong>Lista de estudiantes</strong></td>
                    </tr>
                    <tr>
                        <td class="gridContent">
                            <table cellpadding="0" cellspacing="0" border="0">
                                <tr>
                                    <td style="color:gray">${curso.curso} de ${curso.ciclo}</td>
                                    <td class="text-label">&nbsp;&nbsp;Inscripcion:</td>
                                    <td style="color:gray">${id_gestion}</td>
                                    <td class="text-label">&nbsp;&nbsp;Cantidad de inscritos:</td>
                                    <td style="color:gray">${curso.numEstudiantes}</td>
                                </tr>
                            </table>
                            <div id="resultlayer2">
                                <table width="100%" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF">
                                    <tr>
                                        <td class="gridHeaders">Nro.</td>
                                        <td class="gridHeaders">C&oacute;digo</td>
                                        <td class="gridHeaders">Apellidos y nombres</td>
                                        <c:forEach varStatus="k" var="item_frn" items="${curso.fecRegNotas}">
                                            <td class="gridHeaders" >
                                                <button class="button-normal"  onclick="setImprimirKardex('${item_frn.id_eva}');" title="${item_frn.sfec_inicio} a ${item_frn.sfec_limite}">${item_frn.descripcion}</button>
                                            </td>
                                        </c:forEach>
                                        <td class="gridHeaders">Total</td>
                                    </tr>
                                    <c:if test="${empty curso.estudiantes}">
                                        <tr>
                                            <td class="gridData" colspan="3">No se han encontrado elementos.</td>
                                        </tr>
                                    </c:if>
                                    <c:forEach varStatus="i" var="item" items="${curso.estudiantes}">
                                        <tr onmouseover="onRowover(this);" onmouseout="onRowout(this);" onclick="selectEstudiante('${curso.id_curso}', '${item.id_estudiante}');">
                                            <td align="center" class="gridData"><c:out value="${i.count}"/> </td>
                                            <td class="gridData">${item.codigo}</td>
                                            <td class="gridData">${item.paterno}&nbsp;${item.materno}&nbsp;${item.nombres}</td>
                                            <c:forEach varStatus="k" var="item_frn" items="${curso.fecRegNotas}">
                                                <td class="gridData">

                                                </td>
                                            </c:forEach>
                                            <td class="gridData" style="text-align: center">
                                                <% int cont = 0;%>
                                                <c:forEach varStatus="j" var="item_cm" items="${item.cursomaterias}">
                                                    <c:forEach varStatus="k" var="item_k" items="${item_cm.kardex}">
                                                        <% cont++;%>
                                                    </c:forEach>
                                                </c:forEach>
                                                <% out.print(cont);%>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table> 
                            </div>
                        </td>
                    </tr>
                </table>
            </c:if>
            <c:if test="${!empty estudiante}">
                <table cellpadding="0" cellspacing="0" border="0" style="width:100%" class="gridContent">
                    <tr>
                        <td style="width: 15%" class="campos">Estudiante : </td>
                        <td style="width: 85%" class="datos">${estudiante.paterno}&nbsp;${estudiante.materno}&nbsp;${estudiante.nombres}</td>
                    </tr>
                    <tr>
                        <td class="campos">Curso : </td>
                        <td class="datos">${estudiante.curso}</td>
                    </tr>
                    <tr>
                        <td class="campos">Bimestre/Trimestre : </td>
                        <td class="datos">
                            <c:forEach varStatus="j" var="item_per" items="${periodo_cursos}">
                                <div id="evas_${item_per.id_curso}" class="evas" <c:if test="${item_per.id_curso!=id_curso}"> style="display: none "</c:if>>
                                    <select class="text-field" name="id_eva${item_per.id_curso}" style="width: 200px;padding: 0">
                                        <c:forEach varStatus="j" var="item_fec_notas" items="${item_per.fecnotas}">
                                            <option value="${item_fec_notas.id_eva}" <c:if test="${item_fec_notas.id_eva==id_eva && item_per.id_curso==id_curso}"> selected </c:if>>${item_fec_notas.descripcion}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </c:forEach>
                        </td>
                    </tr>
                    <tr>
                        <td class="campos">Gestion : </td>
                        <td class="datos">${id_gestion}</td>
                    </tr>
                    <tr>
                        <td colspan="2">&nbsp;</td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <div id="resultlayer1_m" style="width: 100%">
                                <table width="100%" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF">
                                    <!--tr>
                                        <td class="gridHeaders">Materias</td>
                                        <td class="gridHeaders" title="Faltas Sin Licencia">FSL</td>
                                        <td class="gridHeaders" title="Atrasos">A</td>
                                        <td class="gridHeaders" title="Tareas No Realizadas">TNR</td>
                                        <td class="gridHeaders" title="Avandono Arbitrario">AA</td>
                                        <td class="gridHeaders" title="Sin Uniforme">SU</td>
                                        <td class="gridHeaders">Otras Faltas</td>
                                        <td class="gridHeaders">Aspectos Positivos</td>
                                        <td class="gridHeaders">Obs.</td>
                                    </tr -->
                                    <tr>
                                        <td class="gridHeaders">Materias</td>
                                        <td class="gridHeaders">Tipo de Falta</td>
                                        <td class="gridHeaders">Detalle</td>
                                    </tr>
                                    <c:forEach varStatus="i" var="item_cm" items="${estudiante.cursomaterias}">
                                        <tr>
                                            <td <c:if test="${i.count%2!=0}">class="desc_materias2"</c:if><c:if test="${i.count%2==0}"> class="desc_materias1"</c:if> rowspan="8" style="width: 25%">${item_cm.materia} (${item_cm.id_materia})</td>
                                            <td <c:if test="${i.count%2!=0}">class="desc_materias2"</c:if><c:if test="${i.count%2==0}"> class="desc_materias1"</c:if> style="width: 25%;font-weight: bold">Faltas Sin Licencia</td>
                                            <td <c:if test="${i.count%2!=0}">class="desc_materias21"</c:if><c:if test="${i.count%2==0}"> class="desc_materias11"</c:if> style="width: 50%">
                                                <c:forEach varStatus="j" var="item_k" items="${item_cm.kardex}">
                                                    <c:if test="${item_k.fsl==1}">
                                                        <strong>&nbsp;|&nbsp;${item_k.sfecha}</strong>
                                                    </c:if>
                                                </c:forEach>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td <c:if test="${i.count%2!=0}">class="desc_materias2"</c:if><c:if test="${i.count%2==0}">class="desc_materias1"</c:if>>No enciende la cámara</td>
                                            <td <c:if test="${i.count%2!=0}">class="desc_materias21"</c:if><c:if test="${i.count%2==0}">class="desc_materias11"</c:if>>
                                                <c:forEach varStatus="j" var="item_k" items="${item_cm.kardex}">
                                                    <c:if test="${item_k.a==1}">
                                                        <strong>&nbsp;|&nbsp;${item_k.sfecha}</strong>
                                                    </c:if>
                                                </c:forEach>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td <c:if test="${i.count%2!=0}">class="desc_materias2"</c:if><c:if test="${i.count%2==0}">class="desc_materias1"</c:if>>Tareas No Realizadas</td>
                                            <td <c:if test="${i.count%2!=0}">class="desc_materias21"</c:if><c:if test="${i.count%2==0}">class="desc_materias11"</c:if>>
                                                <c:forEach varStatus="j" var="item_k" items="${item_cm.kardex}">
                                                    <c:if test="${item_k.tnr==1}">
                                                        <strong>&nbsp;|&nbsp;${item_k.sfecha}</strong>
                                                    </c:if>
                                                </c:forEach>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td <c:if test="${i.count%2!=0}">class="desc_materias2"</c:if><c:if test="${i.count%2==0}">class="desc_materias1"</c:if>>Abandono Arbitrario</td>
                                            <td <c:if test="${i.count%2!=0}">class="desc_materias21"</c:if><c:if test="${i.count%2==0}">class="desc_materias11"</c:if>>
                                                <c:forEach varStatus="j" var="item_k" items="${item_cm.kardex}">
                                                    <c:if test="${item_k.aa==1}">
                                                        <strong>&nbsp;|&nbsp;${item_k.sfecha}</strong>
                                                    </c:if>
                                                </c:forEach>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td <c:if test="${i.count%2!=0}">class="desc_materias2"</c:if><c:if test="${i.count%2==0}">class="desc_materias1"</c:if>>Notificado a la familia</td>
                                            <td <c:if test="${i.count%2!=0}">class="desc_materias21"</c:if><c:if test="${i.count%2==0}">class="desc_materias11"</c:if>>
                                                <c:forEach varStatus="j" var="item_k" items="${item_cm.kardex}">
                                                    <c:if test="${item_k.su==1}">
                                                        <strong>&nbsp;|&nbsp;${item_k.sfecha}</strong>
                                                    </c:if>
                                                </c:forEach>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td <c:if test="${i.count%2!=0}">class="desc_materias2"</c:if><c:if test="${i.count%2==0}">class="desc_materias1"</c:if>>Otras Faltas</td>
                                            <td <c:if test="${i.count%2!=0}">class="desc_materias21"</c:if><c:if test="${i.count%2==0}">class="desc_materias11"</c:if>>
                                                <c:forEach varStatus="j" var="item_k" items="${item_cm.kardex}">
                                                    <c:if test="${item_k.otrasfaltas!=''}">
                                                        <strong>${item_k.sfecha}</strong> : ${item_k.otrasfaltas}<br/>                                                        
                                                    </c:if>
                                                </c:forEach>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td <c:if test="${i.count%2!=0}">class="desc_materias2"</c:if><c:if test="${i.count%2==0}">class="desc_materias1"</c:if>>Aspectos Positivos</td>
                                            <td <c:if test="${i.count%2!=0}">class="desc_materias21"</c:if><c:if test="${i.count%2==0}">class="desc_materias11"</c:if>>
                                                <c:forEach varStatus="j" var="item_k" items="${item_cm.kardex}">
                                                    <c:if test="${item_k.aspectospositivos!=''}">
                                                        <strong>${item_k.sfecha}</strong> : ${item_k.aspectospositivos}<br/>                                                        
                                                    </c:if>
                                                </c:forEach>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td <c:if test="${i.count%2!=0}">class="desc_materias2"</c:if><c:if test="${i.count%2==0}">class="desc_materias1"</c:if>>Observacion</td>
                                            <td <c:if test="${i.count%2!=0}">class="desc_materias21"</c:if><c:if test="${i.count%2==0}">class="desc_materias11"</c:if>>
                                                <c:forEach varStatus="j" var="item_k" items="${item_cm.kardex}">
                                                    <c:if test="${item_k.observaciones!=''}">
                                                        <strong>${item_k.sfecha}</strong> : ${item_k.observaciones}<br/>                                                        
                                                    </c:if>
                                                </c:forEach>
                                            </td>
                                        </tr>
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
