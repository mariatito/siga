<%-- 
    Document   : AdministrarCursoEvaluaciones
    Created on : 12-sep-2009, 11:35:09
    Author     : Marco Antonio
--%>

<%@taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=LATIN1">
        <title>SIGAA - Santa Teresa</title>
        <link href='http://localhost:8084/sigaa/imagenes/iconos_sigaa/sigaa.ico' rel='icon'/>
        <link href='http://localhost:8084/sigaa/imagenes/iconos_sigaa/sigaa.ico' rel='shortcut icon'/>
        <link rel="shortcut icon" href="http://localhost:8084/sigaa/imagenes/iconos_sigaa/sigaa.ico" type="image/x-icon" />
        <link rel="stylesheet" href="imagenes/dhtmlmodal/windowfiles/dhtmlwindow.css" type="text/css" />
        <script type="text/javascript" src="imagenes/dhtmlmodal/windowfiles/dhtmlwindow.js"></script>
        <link rel="stylesheet" href="imagenes/dhtmlmodal/modalfiles/modal.css" type="text/css" />
        <script type="text/javascript" src="imagenes/dhtmlmodal/modalfiles/modal.js"></script>
        <script type="text/javascript" src="js/jquery-1.2.2.pack.js"></script>
        <script type="text/javascript" src="js/animatedcollapse.js"></script>    
        <script type="text/javascript" src="js/prototype.js"></script>
        <link rel="stylesheet" type="text/css" href="css/module.css" media="screen" />

        <script type="text/javascript">
            function onRowover(elem) { elem.className='colover'; }
            function onRowout(elem) { elem.className='colout'; }

            function openWindow(form,title,w,h){
                var addFormwin=dhtmlmodal.open('win'+form, 'div', form, title, 'width='+w+'px,height='+h+'px,left=100px,top=100px,resize=1,scrolling=1');
                addFormwin.moveTo('middle', 'middle');
                return addFormwin;
            }
            var __evas = new Array();
            function updateEvaluacion(id_evaluacion,id_curso) {
                $('definirEvaluacionForm').style.display = 'inline';
                var o = __evas[id_evaluacion];
                $('addevaform').evaluacion.value = o.evaluacion;
                if(id_curso!='K'){
                    $('addevaform').nota.value = o.nota;
                    $('addevaform').dps.value = o.dps;
                }
                if(o.day.length != 0) {
                    $('addevaform').dia.value = o.day;
                    $('addevaform').mes.value = o.month;
                    $('addevaform').anio.value = o.year;
                }
                $('addevaform').descripcion.value = o.descripcion;
                $('addevaform').id_evaluacion.value = id_evaluacion;
            }
        </script>
    </head>
    <body>
        <div class="headercontent">
            <table id="tabs_menu" cellpadding="0" cellspacing="0" border="0" style="width:100%">
                <tr>
                    <td class="tab_init">&nbsp;</td>
                    <td style="width:60%">
                        <table cellpadding="0" cellspacing="0" border="0" width="100%">
                            <td class="tab_normal" onclick="javascript:window.location='<c:url value="/administrarCurso.do"/>?id_curso=${curso.id_curso}&opcion=DatosGenerales&id_gestion=${id_gestion}&id_materia=${curso.materia.id_materia}&id_curso_materia=${curso.cursomateria.id_curso_materia}'">Datos Generales</td>
                            <td class="tab_current">Fecha de evaluaciones</td>
                            <td class="tab_normal" onclick="javascript:window.location='<c:url value="/administrarCurso.do"/>?id_curso=${curso.id_curso}&opcion=Horario&id_gestion=${id_gestion}&id_materia=${curso.materia.id_materia}&id_curso_materia=${curso.cursomateria.id_curso_materia}'">Horario</td>
                            <td class="tab_normal" onclick="javascript:window.location='<c:url value="/administrarCurso.do"/>?id_curso=${curso.id_curso}&opcion=Estadisticas&id_gestion=${id_gestion}&id_materia=${curso.materia.id_materia}&id_curso_materia=${curso.cursomateria.id_curso_materia}'">Estadisticas</td>
                        </table>
                    </td>
                    <td class="tab_fin">&nbsp;</td>
                </tr>
            </table>
            <div class="titlepage">Administraci&oacute;n del curso ${curso.curso} de ${curso.ciclo}</div>
        </div>
        <c:if test="${!empty curso}">
            <div class="maincontent">
                <table cellpadding="0" cellspacing="0" border="0">
                    <tr>
                        <td class="text-label">Docente : </td>
                        <td>${curso.docenteOBJ.abreviatura} ${curso.docenteOBJ.nombres} ${curso.docenteOBJ.paterno} ${curso.docenteOBJ.materno}</td>
                        <td class="text-label">&nbsp;&nbsp;&nbsp;&nbsp;Estado:</td>
                        <td align="right">&nbsp;
                        <c:if test="${curso.cursomateria.estado==false}"><td><img src="imagenes/iconos_sigaa/activo_no.png" width="12px" title=" Cerrado ">&nbsp;&nbsp;</td><td>   <td></c:if>
                        <c:if test="${curso.cursomateria.estado==true}"><td><img src="imagenes/iconos_sigaa/activo_si.png" width="12px" title=" En linea ">&nbsp;&nbsp;</td><td>   <td></c:if>
                            </td>
                        </tr>
                    </table>
                    <div class="gridButtonsInfo">
                        <button class="button-normal" onclick="javascript:openWindow('definirEvaluacion','Definir fechas de evaluaciones','600','250')" <c:if test="${nro_evas>=5}"> disabled </c:if>><img width="11px" src="imagenes/iconos_sigaa/nuevo.png">&nbsp;&nbsp;Definir nueva evaluaci&oacute;n</button>
                    </div>

                    <table cellpadding="0" cellspacing="0" border="0" style="width: 100%">
                        <tr>
                            <td class="tableHeader" colspan="7">Lista de evaluaciones definidas</td>
                        </tr>
                        <tr style="cursor: pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                            <td class="gridHeaders" width="5%">Nro.</td>
                            <td class="gridHeaders" width="20%">Evaluaci&oacute;n</td>
                            <td class="gridHeaders" width="5%">Nota</td>
                            <td class="gridHeaders" width="5%">Dps</td>
                            <td class="gridHeaders" width="30%">Fecha de evaluaci&oacute;n</td>
                            <td class="gridHeaders" width="30%">Fecha limite ingreso de notas</td>
                            <td class="gridHeaders" width="5%">Estado</td>
                        </tr>
                    <c:if test="${empty curso.evaluaciones}">
                        <tr>
                            <td class="gridData" colspan="7">No se han encontrado elementos.</td>
                        </tr>
                    </c:if>
                    <c:forEach varStatus="i" var="item" items="${curso.evaluaciones}">
                        <tr style="cursor: pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)" onclick="updateEvaluacion('${item.id_evaluacion}','${curso.id_curso}')">
                            <td class="gridData"><c:out value="${i.count}"/> </td>
                            <td class="gridData"><strong>${item.evaluacion}</strong></td>
                                <td class="gridData" align="center" title="Sobre ${item.nota} puntos">${item.nota}</td>
                                <td class="gridData" align="center" title="Sobre ${item.dps} puntos">${item.dps}</td>
                            <td class="gridData">${item.sfec_evaluacion}</td>
                            <td class="gridData">${item.sfec_limite}</td>
                            <td class="gridData" align="center">
                                <c:if test="${item.estado=='activo'}"><img src="imagenes/iconos_sigaa/activo_si.png" width="12px" title="Activo"></c:if>
                                <c:if test="${item.estado=='inactivo'}"><img src="imagenes/iconos_sigaa/activo_no.png" width="12px" title="Inactivo"></c:if>
                                </td>
                            </tr>
                            <script type="text/javascript">
                                seval = {evaluacion:'${item.evaluacion}',nota:'${item.nota}',dps:'${item.dps}',day:'${item.fec_limite.date}',month:'${item.fec_limite.month+1}',year:'${item.fec_limite.year+1900}',descripcion:'${item.descripcion}'}
                                __evas['${item.id_evaluacion}'] = seval;
                        </script>
                    </c:forEach>
                    <tr>
                        <td colspan="4"><img src="imagenes/pixel_trans.gif" height="10"/></td>
                    </tr> 
                </table>
            </div>
        </c:if>
        <div id="definirEvaluacionForm"  style="display:none">
            <form id="addevaform" action="<c:url value="/administrarCurso.do"/>" method="post" name="form">
                <table width="100%" border="0" style="cursor:pointer">
                    <tr>
                        <td class="menuHead" colspan="6" align="center"><b>MODIFICAR FECHA L&Iacute;MITE DE INGRESO DE CALIFICACIONES</b></td>
                    </tr>
                    <tr class="gridHeaders">
                        <td width="5%"></td>
                        <td width="20%">Evaluaci&oacute;n</td>
                        <c:if test="${curso.id_curso!='K'}">
                            <td width="5%">Nota</td>
                            <td width="5%">Dps</td>
                        </c:if>
                        <td width="35%">Fecha l&iacute;mite ingreso de notas</td>
                        <td width="30%">Descricpi&oacute;n</td>
                    </tr>
                    <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                        <td valign="top"><img src="imagenes/iconos_sigaa/etiqueta_azul.png"></td>
                        <td valign="top"><input class="text-field" type="text" name="evaluacion" style="width:140px" ></td>
                            <c:if test="${curso.id_curso!='K'}">
                            <td valign="top" align="center"><input class="text-field" type="text" name="nota" value="60" style="width:25px" onfocus="this.blur()"></td>
                            <td valign="top" align="center"><input class="text-field" type="text" name="dps" value="10" style="width:25px" onfocus="this.blur()"></td>
                            </c:if>
                        <td valign="top" align="center">
                            <select name="dia" class="text-field" style="width:45px">
                                <option>
                                    <c:forEach begin="1" end="31" varStatus="i">
                                    <option value="${i.count}">${i.count}
                                    </c:forEach>
                            </select>
                            <select name="mes" class="text-field" style="width:110px">
                                <option>
                                    <c:forEach var="item" items="${tipo_meses}">
                                    <option value="${item.id}">${item.valor}
                                    </c:forEach>
                            </select>
                            <select name="anio" class="text-field" style="width:60px">
                                <option>
                                    <c:forEach begin="2008" end="2020" varStatus="i">
                                    <option value="${i.index}">${i.index}
                                    </c:forEach>
                            </select>
                        </td>
                        <td valign="top"><textarea class="text-field" name="descripcion"></textarea></td>
                    </tr>
                    <tr>
                        <td colspan="6"><img src="imagenes/pixel_gry.gif" height="1" width="100%"></td>
                    </tr>
                    <tr>
                        <td colspan="6" align="center">
                            <input type="hidden" name="opcionInterno" value="modificar">
                            <input type="hidden" name="id_materia" value="${curso.materia.id_materia}">
                            <input type="hidden" name="id_curso" value="${curso.id_curso}">
                            <input type="hidden" name="id_evaluacion">
                            <input type="hidden" name="opcion" value="Evaluaciones">
                            <input type="hidden" name="id_gestion" value="${id_gestion}">
                            <input type="hidden" name="id_curso_materia" value="${curso.cursomateria.id_curso_materia}">
                            <input class="button-submit" type="submit" value="Guardar cambios">
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <div id="definirEvaluacion"  style="display:none">
            <table style="width:100%" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="titlepage">Definici&oacute;n de nueva evaluaci&oacute;n</td>
                </tr>
            </table>
            <form id="addevaform" action="<c:url value="/administrarCurso.do"/>" method="post" name="form">
                <table width="80%" border="0" style="cursor:pointer" align="center">                    
                    <c:forEach var="tm" items="${fecha_regnota}">
                        <c:if test="${tm.id_eva==id_eva_ant}">
                            <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)"> 
                                <td width="30%">Evaluaci&oacute;n</td>
                                <td width="5%">:</td>
                                <td width="65%">
                                    <input class="text-field" type="text" name="evaluacion" style="width: 140px" value="${tm.descripcion}">
                                <input class="text-field" type="hidden" name="id_eva" style="width: 30px" value="${tm.id_eva}">
                                </td>
                            </tr>
                            <c:if test="${curso.id_curso!='K'}">
                                <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                    <td>Nota</td>
                                    <td>:</td>
                                    <td>
                                            <input class="text-field" type="text" name="nota" style="width: 30px" value="${tm.nota}">
                                    </td>
                                </tr>
                                <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                    <td>Dps</td>
                                    <td>:</td> 
                                    <td>
                                            <input class="text-field" type="text" name="dps" style="width: 30px" value="${tm.dps}">
                                    </td>
                                </tr>
                            </c:if> 
                            <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                <td>Fecha inicio</td>
                                <td>:</td>
                                <td>
                                    <select name="dia_ini" class="text-field" style="width:50px">
                                        <option>
                                            <c:forEach begin="1" end="31" varStatus="i">
                                            <option value="${i.count}" <c:if test="${i.count == tm.fec_inicio.date}"> selected </c:if>>${i.count}
                                            </c:forEach>
                                    </select>
                                    <select name="mes_ini" class="text-field" style="width:120px">
                                        <option>
                                            <c:forEach var="item" items="${tipo_meses}">
                                            <option value="${item.id}" <c:if test="${item.id == tm.fec_inicio.month+1}"> selected </c:if>>${item.valor}
                                            </c:forEach>
                                    </select>
                                    <select name="anio_ini" class="text-field" style="width:60px">
                                        <option>
                                            <c:forEach begin="2000" end="2030" varStatus="i">
                                            <option value="${i.index}" <c:if test="${i.index == tm.fec_inicio.year+1900}"> selected </c:if>>${i.index}
                                            </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                <td>Fecha l&iacute;mite</td>
                                <td>:</td>
                                <td>
                                    <select name="dia_fin" class="text-field" style="width:50px">
                                        <option>
                                            <c:forEach begin="1" end="31" varStatus="i">
                                            <option value="${i.count}" <c:if test="${i.count == tm.fec_limite.date}"> selected </c:if>>${i.count}
                                            </c:forEach>
                                    </select>
                                    <select name="mes_fin" class="text-field" style="width:120px">
                                        <option>
                                            <c:forEach var="item" items="${tipo_meses}">
                                            <option value="${item.id}" <c:if test="${item.id == tm.fec_limite.month+1}"> selected </c:if>>${item.valor}
                                            </c:forEach>
                                    </select>
                                    <select name="anio_fin" class="text-field" style="width:60px">
                                        <option>
                                            <c:forEach begin="2000" end="2030" varStatus="i">
                                            <option value="${i.index}" <c:if test="${i.index == tm.fec_limite.year+1900}"> selected </c:if>>${i.index}
                                            </c:forEach>
                                    </select>   
                                </td>
                            </tr>
                            <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                <td>Descricpi&oacute;n</td>
                                <td>:</td>
                                <td><textarea class="text-field" name="descripcion">TEMAS REFERENTES DE LA EVALUACION</textarea></td>
                            </tr>
                            <tr>
                                <td colspan="3" align="center">
                                    <input type="hidden" name="id_gestion" value="${id_gestion}">
                                    <input type="hidden" name="opcion" value="Evaluaciones">
                                    <input type="hidden" name="opcionInterno" value="reg_new_eva">
                                    <input type="hidden" name="nro_evas" value="${nro_evas+1}">
                                    <input type="hidden" name="id_materia" value="${curso.materia.id_materia}">
                                    <input type="hidden" name="id_curso" value="${curso.id_curso}">
                                    <input type="hidden" name="id_curso_materia" value="${curso.cursomateria.id_curso_materia}">
                                    <button class="button-normal" type="submit"><img width="11px" src="imagenes/iconos_sigaa/guardar.png">&nbsp;&nbsp;Guardar</button>
                                </td>
                            </tr>                             
                        </c:if>
                    </c:forEach>                    
                </table>
            </form>
        </div>
    </body>
</html>