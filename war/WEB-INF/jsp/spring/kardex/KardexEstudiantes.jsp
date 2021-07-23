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
            // window.onload=function(){enableTooltips()};

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
                window.location = '<c:url value="/kardexEstudiante.do"/>?id_gestion=' + id_gestion;
            }
            function gridEvent(layer) {
                if ($(layer).style.display === 'none') {
                    $(layer).style.display = 'inline';
                } else {
                    $(layer).style.display = 'none';
                }
            }
            function selectMateria(id_materia, id_curso, id_curso_materia, cant) {
                window.location = '<c:url value="/kardexEstudiante.do"/>?id_materia=' + id_materia + '&id_curso=' + id_curso + '&id_gestion=${id_gestion}&opcion=__nuevoKardex&id_curso_materia=' + id_curso_materia + '&cant=' + cant;
            }
            /*function setVerReq(form) {
             if(form.temario.value.length<2){
             alert("Ingrese TEMA DE ACUERDO A PROGRAMA (Temario)");
             form.temario.focus();
             return false; 
             }
             }*/
            function setEditKardex(id_materia, id_curso, id_curso_materia, cant, idkardex) {
                window.location = '<c:url value="/kardexEstudiante.do"/>?id_materia=' + id_materia + '&id_curso=' + id_curso + '&id_gestion=${id_gestion}&opcion=__editKardex&id_curso_materia=' + id_curso_materia + '&cant=' + cant + '&idkardex=' + idkardex;
            }
        </script>

        <style>
            .colorrojo{
                background: red;
            }
            .colorverde{
                background: green;
            }
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
                                <td class="tab_current" style="border-right:1px solid #7D909E">Kardex</td>
                                <td class="tab_normal" onclick="javascript:window.location = '<c:url value="/reporteKardex.do"/>';">Reporte Kardex</td>
                            </tr>
                        </table>
                    </td>
                    <td class="tab_fin">&nbsp;</td>
                </tr>
            </table>
            <div class="titlepage" style="cursor: pointer">
                <font size="1px" onclick="javascript:window.location = '<c:url value="/kardexEstudiante.do"/>?id_gestion=${id_gestion}';">
                    <c:if test="${id_gestion!=null}">${id_gestion}&nbsp;<img src="imagenes/iconos_sigaa/back.png" title="Volver a la lista de cursos ${id_gestion}"></c:if>
                    </font>
                    <!--font size="1px" onclick="javascript:window.location='<c:url value="/kardexEstudiante.do"/>?id_curso=${id_curso}&id_gestion=${id_gestion}'">
                <c:if test="${id_curso!=null}">${curso_act.curso} de ${curso_act.ciclo}&nbsp;<img src="imagenes/iconos_sigaa/back.png" title="Volver al curso anterior ${curso_act.curso} de ${curso_act.ciclo}"></c:if>
                </font-->
                    Registro de Seguimiento Integral Diario de los Estudiantes<br/>
                <c:if test="${!empty curso}">
                    ${curso.curso} de ${curso.ciclo} (${materia.materia})
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
                        <td class="tableHeader">CURSOS Y MATERIAS (${id_gestion})</td>
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
                                    <tr style="cursor:pointer" onmouseover="onRowover(this);" onmouseout="onRowout(this);" onclick="javascript:gridEvent('${item.id_curso}');">
                                        <td align="center" class="gridData">${j.count}</td>
                                        <td align="center" class="gridData">${item.desc_curso}</td>
                                        <td valign="top" class="gridData">
                                            <table border="0" width="100%">
                                                <tr>
                                                    <td valign="top" width="50%"><span style="cursor:pointer; color:teal"><img src="imagenes/iconos_sigaa/etiqueta_azul.png">&nbsp;&nbsp;<strong>${item.curso} de ${item.ciclo}</strong></span></td>
                                                    <td valign="top" width="50%"><span style="cursor:pointer; color:gray; font-size:10px">Cantidad de inscritos : &nbsp;${item.numEstudiantes}</span></td>
                                                </tr>
                                            </table>
                                            <div id="${item.id_curso}" style="display: none">
                                                <table border="0" class="gridContent" width="100%">
                                                    <c:forEach varStatus="i" var="it" items="${cursoMaterias}">
                                                        <c:if test="${item.id_curso==it.id_curso}">
                                                            <tr onclick="selectMateria('${it.id_materia}', '${item.id_curso}', '${it.id_curso_materia}', '${item.numEstudiantes}');
                                                                    gridEvent('${item.id_curso}');" style="cursor:pointer">
                                                                <td style="width:20%"><img src="imagenes/cxc.png"><c:out value="${it.materia}"/></td>
                                                            </tr>
                                                        </c:if>
                                                    </c:forEach>
                                                </table>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </td>
                    </tr>
                </table>
            </c:if>
            <c:if test="${!empty curso}">
                <form method="POST" action="" onsubmit="//return setVerReq(this)">
                    <input type="hidden" name="id_gestion" value="${id_gestion}"/>
                    <input type="hidden" name="id_curso" value="${id_curso}"/>
                    <input type="hidden" name="id_materia" value="${id_materia}"/>
                    <input type="hidden" name="id_gestion" value="${id_gestion}"/>
                    <input type="hidden" name="idkardex" value="${idkardex}"/>
                    <input type="hidden" name="id_curso_materia" value="${id_curso_materia}"/>                                    
                    <input type="hidden" name="opcion" value="${opcion}"/>
                    <input type="hidden" name="opcion2" value="${opcion2}"/>
                    <table cellpadding="0" cellspacing="0" border="0" style="width: 100% ; border: 1px solid #ccc;margin-bottom: 5px">
                        <tr>
                            <td style="text-align: center" colspan="8"><strong>NUEVO REGISTRO</strong></td>
                        </tr>
                        <tr>
                            <td style="text-align: right">
                                <strong>Periodo : </strong> 
                            </td> 
                            <td>
                                <select name="periodo" class="text-field" style="width: 50px">
                                    <c:forEach begin="1" end="11" varStatus="h">
                                        <option value="${h.count}" <c:if test="${periodo==h.count}"> selected </c:if>>${h.count}º</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td style="text-align: right">
                                <strong>Fecha : </strong>
                            </td>
                            <td>
                                <input type="text" name="fecha" id="fecha" readonly="readonly" value="${fecha}" style="width: 100px"/>
                                <img src="imagenes/calendar.png" id="selector"/>
                                <script type="text/javascript">
                                    Calendar.setup(
                                            {
                                                inputField: "fecha", // ID of the input field
                                                ifFormat: "%d-%m-%Y", // the date format
                                                button: "selector" // ID of the button
                                            });
                                </script> 
                            </td>
                            <td style="text-align: right">
                                <strong>Docente : </strong>
                            </td>
                            <td>
                                <select name="id_docente" class="text-field" style="width: 250px">
                                    <c:forEach varStatus="ij" var="item_doc" items="${docentes}">
                                        <c:if test="${!empty iddocente}">
                                            <option value="${item_doc.id_docente}" <c:if test="${iddocente==item_doc.id_docente}"> selected </c:if>>${item_doc.nombres}</option>
                                        </c:if>
                                        <c:if test="${empty iddocente}">
                                            <option value="${item_doc.id_docente}" <c:if test="${curso.cursomateria.id_docente==item_doc.id_docente}"> selected </c:if>>${item_doc.nombres}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </td>
                            <td style="text-align: right">
                                <strong>Temario : </strong>
                            </td>
                            <td>
                                <textarea name="temario" style="width: 250px">${temario}</textarea> 
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: center" colspan="8">
                                <div style="min-height: 150px;max-height: 150px;overflow: auto;margin: 0 auto">
                                    <table width="99%" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF">
                                        <tr>
                                            <td class="gridHeaders" width="3%">Nro.</td> 
                                            <td class="gridHeaders" width="25%" title="Apellidos y Nombres">Estudiante</td>
                                            <td class="gridHeaders" width="3%" title="Faltas Sin Licencia">FSL</td>
                                            <td class="gridHeaders" width="3%" title="No enciende la cámara">NEC</td>
                                            <td class="gridHeaders" width="3%" title="Tareas No Realizadas">TNR</td>
                                            <td class="gridHeaders" width="3%" title="Atrasos">AA</td>
                                            <td class="gridHeaders" width="3%" title="Notificado a la familia">NF</td>
                                            <td class="gridHeaders" width="15%" title="Otras Faltas">Otras Faltas</td> 
                                            <td class="gridHeaders" width="15%" title="Aspectos Positivos Sobresalientes">Aspectos Positivos</td>
                                            <td class="gridHeaders" width="15%" title="Observaciones">Observaciones</td>
                                        </tr>
                                        <c:if test="${!empty detalleskardex}">
                                            <c:forEach varStatus="g" var="item_d" items="${detalleskardex}">
                                                <tr class="colout" onclick="selectEstudiante();">
                                                    <td align="center" class="gridData">
                                                        <c:out value="${g.index+1}"/>
                                                        <input name="idkardexdetalle_${g.index+1}" type="hidden" value="${item_d.idkardexdetalle}"/>
                                                    </td>
                                                    <td class="gridData">
                                                        <select name="id_estudiante_${g.index+1}" class="text-field" style="width: 100%">
                                                            <option value="0">Seleccionar Estudiante...</option>
                                                            <c:forEach varStatus="i" var="item_e" items="${curso.estudiantes}">
                                                                <option value="${item_e.id_estudiante}" <c:if test="${item_d.id_estudiante==item_e.id_estudiante}"> selected </c:if>>${item_e.paterno} ${item_e.materno} ${item_e.nombres}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </td> 
                                                    <td class="gridData" align="center"><input name="fsl_${g.index+1}" type="checkbox" <c:if test="${item_d.fsl==1}"> checked </c:if>/></td>
                                                    <td class="gridData" align="center"><input name="a_${g.index+1}" type="checkbox" <c:if test="${item_d.a==1}"> checked </c:if>/></td>
                                                    <td class="gridData" align="center"><input name="tnr_${g.index+1}" type="checkbox" <c:if test="${item_d.tnr==1}"> checked </c:if>/></td>
                                                    <td class="gridData" align="center"><input name="aa_${g.index+1}" type="checkbox" <c:if test="${item_d.aa==1}"> checked </c:if>/></td>
                                                    <td class="gridData" align="center"><input name="su_${g.index+1}" type="checkbox" <c:if test="${item_d.su==1}"> checked </c:if>/></td>
                                                    <td class="gridData" align="center"><textarea name="otrasfaltas_${g.index+1}" style="width: 100%;height: 25px;">${item_d.otrasfaltas}</textarea></td>
                                                    <td class="gridData" align="center"><textarea name="aspectospositivos_${g.index+1}" style="width: 100%;height: 25px;">${item_d.aspectospositivos}</textarea></td>
                                                    <td class="gridData" align="center"><textarea name="observaciones_${g.index+1}" style="width: 100%;height: 25px;">${item_d.observaciones}</textarea></td>
                                                </tr>
                                            </c:forEach>
                                        </c:if>
                                        <c:forEach begin="${desde+1}" end="20" varStatus="ind">
                                            <tr  onmouseover="onRowover(this);" onmouseout="onRowout(this);">
                                                <td align="center" class="gridData">
                                                    <c:out value="${ind.index}"/>
                                                    <input name="idkardexdetalle_${ind.index}" type="hidden" value="0"/>
                                                </td>
                                                <td class="gridData">
                                                    <select name="id_estudiante_${ind.index}" class="text-field" style="width: 100%">
                                                        <option value="0">Seleccionar Estudiante...</option>
                                                        <c:forEach varStatus="i" var="item" items="${curso.estudiantes}">
                                                            <option value="${item.id_estudiante}">${item.paterno} ${item.materno} ${item.nombres}</option>
                                                        </c:forEach>
                                                    </select>
                                                </td>
                                                <td class="gridData" align="center"><input name="fsl_${ind.index}" type="checkbox"/></td>
                                                <td class="gridData" align="center"><input name="a_${ind.index}" type="checkbox"/></td>
                                                <td class="gridData" align="center"><input name="tnr_${ind.index}" type="checkbox"/></td>
                                                <td class="gridData" align="center"><input name="aa_${ind.index}" type="checkbox"/></td>
                                                <td class="gridData" align="center"><input name="su_${ind.index}" type="checkbox"/></td>
                                                <td class="gridData" align="center"><textarea name="otrasfaltas_${ind.index}" style="width: 100%;height: 25px;"></textarea></td>
                                                <td class="gridData" align="center"><textarea name="aspectospositivos_${ind.index}" style="width: 100%;height: 25px;"></textarea></td>
                                                <td class="gridData" align="center"><textarea name="observaciones_${ind.index}" style="width: 100%;height: 25px;"></textarea></td>
                                            </tr>
                                        </c:forEach>
                                    </table> 
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="8" style="text-align: center">
                                <input type="submit" value="${btn_text}" class="button-normal"/>
                            </td>
                        </tr>
                    </table>
                </form>
                <table cellpadding="0" cellspacing="0" border="0" style="width: 100% ; cursor:pointer">
                    <tr>
                        <td class="tableHeader"><strong>Listado Kardex</strong></td>
                    </tr>
                    <tr>
                        <td class="gridContent">
                            <table cellpadding="0" cellspacing="0" border="0">
                                <tr>
                                    <td class="text-label">
                                        <!--button class="button-normal"  onclick="javascript:setImprimirExcel()" title="Imprime la lista en formato excel">Imprimir Excel</button>&nbsp;-->
                                    </td>
                                    <td class="text-label">&nbsp;&nbsp;Curso:</td>
                                    <td style="color:gray">${curso.curso} de ${curso.ciclo}</td>
                                    <td class="text-label">&nbsp;&nbsp;Inscripcion:</td>
                                    <td style="color:gray">${id_gestion}</td>
                                    <td class="text-label">&nbsp;&nbsp;Cantidad de inscritos:</td>
                                    <td style="color:gray">${cant}</td>
                                </tr>
                            </table>
                            <div id="resultlayer1">
                                <table width="100%" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF"  style="font-size: 10px">
                                    <tr>
                                        <td class="gridHeaders" width="3%">Nro.</td>
                                        <td class="gridHeaders" width="24%">Detalles</td> 
                                        <td class="gridHeaders" width="20%" title="Apellidos y Nombres">Estudiante</td>
                                        <td class="gridHeaders" width="3%" title="Faltas Sin Licencia">FSL</td>
                                        <td class="gridHeaders" width="3%" title="No enciende la cámara">NEC</td>
                                        <td class="gridHeaders" width="3%" title="Tareas No Realizadas">TNR</td>
                                        <td class="gridHeaders" width="3%" title="Atrasos">AA</td>
                                        <td class="gridHeaders" width="3%" title="Notificado a la familia">NF</td>
                                        <td class="gridHeaders" width="12%" title="Otras Faltas">Otras Faltas</td> 
                                        <td class="gridHeaders" width="12%" title="Aspectos Positivos Sobresalientes">Aspectos Positivos</td>
                                        <td class="gridHeaders" width="12%" title="Observaciones">Observaciones</td>
                                    </tr>
                                    <c:if test="${empty curso.estudiantes}">
                                        <tr>
                                            <td class="gridData" colspan="13">No se han encontrado elementos.</td>
                                        </tr>
                                    </c:if>
                                    <c:forEach varStatus="i" var="item" items="${listakardex}">
                                        <c:if test="${i.count%2==0}">
                                            <c:forEach varStatus="j" var="item_detalles" items="${item.kardexdetalles}">
                                                <c:if test="${j.count==1}">
                                                    <tr class="colover" onclick="selectEstudiante();">
                                                        <td align="center" class="gridData" rowspan="${item.cant_kardexdetalle}">
                                                            <c:out value="${i.count}"/><br/>
                                                            <img title="Editar"  onclick="setEditKardex('${id_materia}', '${id_curso}', '${id_curso_materia}', '${cant}',${item.idkardex});" src="imagenes/iconos_sigaa/adm_personal.png">
                                                        </td>
                                                        <td class="gridData" rowspan="${item.cant_kardexdetalle}">
                                                            <strong>Periodo: </strong> ${item.periodo}<br/>
                                                            <strong>Fecha: </strong> ${item.sfecha}<br/>
                                                            <strong>Docente: </strong> ${item.docente}<br/>
                                                            <strong>Temario: </strong> ${item.temario}<br/>
                                                        </td>
                                                        <td class="gridData">${j.count}. ${item_detalles.estudiante}</td>
                                                        <td class="gridData" align="center"><input type="checkbox" disabled <c:if test="${item_detalles.fsl==1}"> checked </c:if>/></td>
                                                        <td class="gridData" align="center"><input type="checkbox" disabled <c:if test="${item_detalles.a==1}"> checked </c:if>/></td> 
                                                        <td class="gridData" align="center"><input type="checkbox" disabled <c:if test="${item_detalles.tnr==1}"> checked </c:if>/></td>
                                                        <td class="gridData" align="center"><input type="checkbox" disabled <c:if test="${item_detalles.aa==1}"> checked </c:if>/></td>
                                                        <td class="gridData" align="center"><div <c:if test="${item_detalles.su==0}"> class="colorrojo" </c:if><c:if test="${item_detalles.su==1}"> class="colorverde" </c:if>><input type="checkbox" disabled <c:if test="${item_detalles.su==1}"> checked </c:if>/></div></td>
                                                        <td class="gridData">${item_detalles.otrasfaltas}</td>
                                                        <td class="gridData">${item_detalles.aspectospositivos}</td>
                                                        <td class="gridData">${item_detalles.observaciones}</td>
                                                    </tr>
                                                </c:if> 
                                                <c:if test="${j.count!=1}">
                                                    <tr class="colover" onclick="selectEstudiante();">
                                                        <td class="gridData">${j.count}. ${item_detalles.estudiante}</td>
                                                        <td class="gridData" align="center"><input type="checkbox" disabled <c:if test="${item_detalles.fsl==1}"> checked </c:if>/></td>
                                                        <td class="gridData" align="center"><input type="checkbox" disabled <c:if test="${item_detalles.a==1}"> checked </c:if>/></td> 
                                                        <td class="gridData" align="center"><input type="checkbox" disabled <c:if test="${item_detalles.tnr==1}"> checked </c:if>/></td>
                                                        <td class="gridData" align="center"><input type="checkbox" disabled <c:if test="${item_detalles.aa==1}"> checked </c:if>/></td>
                                                        <td class="gridData" align="center"><div <c:if test="${item_detalles.su==0}"> class="colorrojo" </c:if><c:if test="${item_detalles.su==1}"> class="colorverde" </c:if>><input type="checkbox" disabled <c:if test="${item_detalles.su==1}"> checked </c:if>/></div></td>
                                                        <td class="gridData">${item_detalles.otrasfaltas}</td>
                                                        <td class="gridData">${item_detalles.aspectospositivos}</td>
                                                        <td class="gridData">${item_detalles.observaciones}</td>
                                                    </tr>
                                                </c:if>
                                            </c:forEach>
                                        </c:if>
                                        <c:if test="${i.count%2!=0}">
                                            <c:forEach varStatus="j" var="item_detalles" items="${item.kardexdetalles}">
                                                <c:if test="${j.count==1}">
                                                    <tr class="colout" onclick="selectEstudiante();">
                                                        <td align="center" class="gridData" rowspan="${item.cant_kardexdetalle}">
                                                            <c:out value="${i.count}"/><br/>
                                                            <img title="Editar"  onclick="setEditKardex('${id_materia}', '${id_curso}', '${id_curso_materia}', '${cant}',${item.idkardex});" src="imagenes/iconos_sigaa/adm_personal.png">
                                                        </td>
                                                        <td class="gridData" rowspan="${item.cant_kardexdetalle}">
                                                            <strong>Periodo: </strong> ${item.periodo}<br/>
                                                            <strong>Fecha: </strong> ${item.sfecha}<br/>
                                                            <strong>Docente: </strong> ${item.docente}<br/>
                                                            <strong>Temario: </strong> ${item.temario}<br/>
                                                        </td>
                                                        <td class="gridData">${j.count}. ${item_detalles.estudiante}</td>
                                                        <td class="gridData" align="center"><input type="checkbox" disabled <c:if test="${item_detalles.fsl==1}"> checked </c:if>/></td>
                                                        <td class="gridData" align="center"><input type="checkbox" disabled <c:if test="${item_detalles.a==1}"> checked </c:if>/></td> 
                                                        <td class="gridData" align="center"><input type="checkbox" disabled <c:if test="${item_detalles.tnr==1}"> checked </c:if>/></td>
                                                        <td class="gridData" align="center"><input type="checkbox" disabled <c:if test="${item_detalles.aa==1}"> checked </c:if>/></td>
                                                        <td class="gridData" align="center"><div <c:if test="${item_detalles.su==0}"> class="colorrojo" </c:if><c:if test="${item_detalles.su==1}"> class="colorverde" </c:if>><input type="checkbox" disabled <c:if test="${item_detalles.su==1}"> checked </c:if>/></div></td>
                                                        <td class="gridData">${item_detalles.otrasfaltas}</td>
                                                        <td class="gridData">${item_detalles.aspectospositivos}</td>
                                                        <td class="gridData">${item_detalles.observaciones}</td>
                                                    </tr>
                                                </c:if>
                                                <c:if test="${j.count!=1}">
                                                    <tr class="colout" onclick="selectEstudiante();">
                                                        <td class="gridData">${j.count}. ${item_detalles.estudiante}</td>
                                                        <td class="gridData" align="center"><input type="checkbox" disabled <c:if test="${item_detalles.fsl==1}"> checked </c:if>/></td>
                                                        <td class="gridData" align="center"><input type="checkbox" disabled <c:if test="${item_detalles.a==1}"> checked </c:if>/></td> 
                                                        <td class="gridData" align="center"><input type="checkbox" disabled <c:if test="${item_detalles.tnr==1}"> checked </c:if>/></td>
                                                        <td class="gridData" align="center"><input type="checkbox" disabled <c:if test="${item_detalles.aa==1}"> checked </c:if>/></td>
                                                        <td class="gridData" align="center"><div <c:if test="${item_detalles.su==0}"> class="colorrojo" </c:if><c:if test="${item_detalles.su==1}"> class="colorverde" </c:if>><input type="checkbox" disabled <c:if test="${item_detalles.su==1}"> checked </c:if>/></div></td>
                                                        <td class="gridData" >${item_detalles.otrasfaltas}</td>
                                                        <td class="gridData">${item_detalles.aspectospositivos}</td>
                                                        <td class="gridData">${item_detalles.observaciones}</td>
                                                    </tr>
                                                </c:if>
                                            </c:forEach>
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
