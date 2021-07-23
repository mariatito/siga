<%-- 
    Document   : AsignarDocenteCursoMateria
    Created on : 06-sep-2009, 0:11:48
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
        <link rel="stylesheet" href="imagenes/dhtmlmodal/windowfiles/dhtmlwindow.css" type="text/css" />
        <script type="text/javascript" src="imagenes/dhtmlmodal/windowfiles/dhtmlwindow.js"></script>
        <link rel="stylesheet" href="imagenes/dhtmlmodal/modalfiles/modal.css" type="text/css" />
        <script type="text/javascript" src="imagenes/dhtmlmodal/modalfiles/modal.js"></script>
        <script type="text/javascript" src="js/prototype.js"></script>
        <link rel="stylesheet" type="text/css" href="css/module.css" media="screen" />

        <script type="text/javascript">
            function onRowover(elem) { elem.className='colover'; }
            function onRowout(elem) { elem.className='colout'; }
            function popupWindow(u) {
                window.open(u,'','toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes,copyhistory=no,width=800,height=530,screenX=50,screenY=50,top=50,left=350');
            }
            function openWindow(form,title,w,h){
                docentesWin=dhtmlmodal.open('win'+form, 'div', form, title, 'width='+w+'px,height='+h+'px,left=100px,top=100px,resize=1,scrolling=1');
                docentesWin.moveTo('middle', 'middle');
                return docentesWin;
            }

            function selectMateria(mtr) { _loadParInfo(mtr); return false; }
            function paraleloRowOver(e) { e.className='parcolover'; }
            function paraleloRowOut(e) { e.className='parcolout'; }
            function doGridEvent(img,layer) {
                if($(layer).style.display=='none') {
                    $(layer).style.display='inline';
                    img.src= 'imagenes/icons/arrow-up.gif';
                } else {
                    $(layer).style.display='none';
                    img.src= 'imagenes/icons/arrow-down.gif';
                }
            }
            var mindex=-1;
            var _materias = new Array();
            var _allmats = new Array();
            var _id_materia_open;
            var _id_paralelo_open;

            var docentesWin = null;
            function __openDList(id_curso,curso,ciclo) {
                docentesWin = openWindow('docentesList','Docentes de la Unidad Educativa','600','200')
                $('curso').innerHTML ="CURSO : "+curso+" de "+ciclo;
                document.getElementById('id_curso').setValue(id_curso);
            }
            function __closeDList() {
                docentesWin.hide();
            }
             var docentesCoo = null;
            function __openDListCoo(id_curso,curso,ciclo) {
                docentesCoo = openWindow('docentesListCoo','Docentes de la Unidad Educativa','600','200')
                $('curso').innerHTML ="CURSO : "+curso+" de "+ciclo;
                document.getElementById('id_curso').setValue(id_curso);
            }
            function __closeDListCoo() {
                docentesCoo.hide();
            }
            var docentesCurMat=null;
            function __openDListCM(id_cur_mat,curso,materia,ciclo){
                docentesCurMat=openWindow('docentesCurMat','Docentes de la Unidad Educativa','600','200')
                $('curso').innerHTML ="CURSO : "+curso+" de "+ciclo;
                $('materia').innerHTML ="MATERIA : "+materia;
                document.getElementById('id_cur_mat').setValue(id_cur_mat);
            }
            function selectGestion(id_gestion){
                window.location = '<c:url value="/asignarDocentes.do"/>?id_gestion='+id_gestion;
            }
            function __openDListRF(id_curso,curso,ciclo) {
                docentesWin = openWindow('docentesListRF','Docentes de la Unidad Educativa','600','200')
                $('curso').innerHTML ="CURSO : "+curso+" de "+ciclo;
                document.getElementById('id_curso').setValue(id_curso);
            }
        </script>
    </head>
    <body>
        <div class="headercontent">
            <table id="tabs_menu" cellpadding="0" cellspacing="0" border="0" style="width:100%">
                <tr>
                    <td class="tab_init">&nbsp;</td>
                    <td style="width:90%">
                        <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                            <td class="tab_normal" onclick="javascript:window.location='<c:url value="/adminPlan.do"/>'">Plan de estudio</td>
                            <td class="tab_current">Asignaci&oacute;n curso-docente</td>
                            <td class="tab_normal" onclick="javascript:window.location='<c:url value="/administrarCurso.do"/>'">Administrar cursos</td>
                            <td class="tab_normal" onclick="javascript:window.location='<c:url value="/administrarEvaluaciones.do"/>'">Fechas de evaluaciones general</td>
                            <td class="tab_normal" onclick="javascript:window.location='<c:url value="/boletinesTrimestrales.do"/>'">Boletines trimestrales</td>
                        </table>
                    </td>
                    <td class="tab_fin">&nbsp;</td>
                </tr>
            </table>
            <div class="titlepage">ASIGNACI&Oacute;N DE DOCENTES A CURSO Y MATERIAS</div>
        </div>
        <div class="maincontent">
            <c:if test="${!empty gestion}">
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
                <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                    <tr>
                    <tr>
                        <td class="tableHeader">Todos los cursos y materias</td>
                    </tr>
                    <td class="gridContent">
                        <table width="100%" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF">
                            <tr>
                                <td class="gridHeaders" style="width:40px">Nro.</td>
                                <td class="gridHeaders" style="width:70px">C&oacute;digo</td>
                                <td class="gridHeaders">Curso</td>
                            </tr>
                            <% int indexc = 0;%>
                            <!--  <tr>
                                            <td class="gridDataGroup" style="color:#FFFFFF;background:url(imagenes/mod_lr.gif)" colspan="3"><b>Nivel <c:out value="${i.count}"/></b></td>
                                        </tr> -->
                            <c:forEach varStatus="j" var="item" items="${cursos}">

                                <script type="text/javascript">
                                    mindex++;_materias[mindex]='${item.id_curso}';
                                    materia = {"id_materia":"${item.id_curso}","sigla":"${item.curso}","materia":"${item.curso}"};_allmats['${item.id_curso}'] = materia;
                                </script>
                                <% indexc++;%>
                                <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                    <td align="center" style="cursor:pointer" valign="top" class="gridData"><% out.print(indexc);%></td>
                                    <td align="center" style="cursor:pointer" valign="top" class="gridData">${item.desc_curso}</td>
                                    <td valign="top" class="gridData"><strong>${item.curso} de ${item.ciclo}</strong>
                                        <% int sw = 0;%>
                                        <table border="0" class="gridContent" width="100%">
                                            <c:forEach varStatus="i" var="it" items="${cursoMaterias}">
                                                <% if (sw == 0) {%>
                                                <c:if test="${item.id_curso==it.id_curso}">
                                                    <tr>
                                                        <c:if test="${it.id_docente_tutor==null}">
                                                            <td colspan="4" style="cursor:pointer; padding-left:10px">Docente Tutor:
                                                                <span style="color:red; cursor:pointer">&nbsp;&nbsp;Sin docente&nbsp;&nbsp;</span>
                                                                <span style="color:navy" onclick="javascript:__openDList('${item.id_curso}','${item.curso}','${item.ciclo}')"><strong><em><u>Designar</u></em></strong></span>
                                                            </td>
                                                        </c:if>
                                                             <c:if test="${it.id_docente_coordinador==null}">
                                                            <td colspan="4" style="cursor:pointer; padding-left:10px">Docente Coordinador:
                                                                <span style="color:red; cursor:pointer">&nbsp;&nbsp;Sin docente&nbsp;&nbsp;</span>
                                                                <span style="color:navy" onclick="javascript:__openDListCoo('${item.id_curso}','${item.curso}','${item.ciclo}')"><strong><em><u>Designar</u></em></strong></span>
                                                            </td>
                                                        </c:if>
                                                        <c:if test="${it.id_docente_tutor!=null}">
                                                            <td colspan="4" style="cursor:pointer; padding-left:10px">Docente Tutor:
                                                                <c:forEach varStatus="ij" var="ite" items="${docentes}">
                                                                    <c:if test="${it.id_docente_tutor==ite.id_docente}">
                                                                        <span style="color:gray; cursor:pointer">&nbsp;&nbsp;${ite.titulo} ${ite.nombres}&nbsp;&nbsp;</span>
                                                                    </c:if>
                                                                </c:forEach>
                                                                <span style="color:navy" onclick="javascript:__openDList('${item.id_curso}','${item.curso}','${item.ciclo}');"><strong><em><u>Cambiar</u></em></strong></span>
                                                            </td>
                                                        </c:if>
                                                            <c:if test="${it.id_docente_coordinador!=null}">
                                                            <td colspan="4" style="cursor:pointer; padding-left:10px">Docente Coordinador:
                                                                <c:forEach varStatus="ij" var="ita" items="${docentes}">
                                                                    <c:if test="${it.id_docente_coordinador==ita.id_docente}">
                                                                        <span style="color:gray; cursor:pointer">&nbsp;&nbsp;${ita.titulo} ${ita.nombres}&nbsp;&nbsp;</span>
                                                                    </c:if>
                                                                </c:forEach>
                                                                <span style="color:navy" onclick="javascript:__openDListCoo('${item.id_curso}','${item.curso}','${item.ciclo}');"><strong><em><u>Cambiar</u></em></strong></span>
                                                            </td>
                                                        </c:if>
                                                    </tr>
                                                    <c:if test="${it.id_curso=='S1' ||it.id_curso=='S2'||it.id_curso=='S3'||it.id_curso=='S4'}">
                                                        <tr>
                                                            <c:if test="${it.id_docente_regfaltas==null}">
                                                                <td colspan="4" style="cursor:pointer; padding-left:10px">Docente encargado de registrar faltas:
                                                                    <span style="color:red; cursor:pointer">&nbsp;&nbsp;Sin docente&nbsp;&nbsp;</span>
                                                                    <span style="color:navy" onclick="javascript:__openDListRF('${item.id_curso}','${item.curso}','${item.ciclo}');"><strong><em><u>Designar</u></em></strong></span>
                                                                </td>
                                                            </c:if>
                                                            <c:if test="${it.id_docente_regfaltas!=null}">
                                                                <td colspan="4" style="cursor:pointer; padding-left:10px">Docente encargado de registrar faltas:
                                                                    <c:forEach varStatus="ij" var="ite" items="${docentes}">
                                                                        <c:if test="${it.id_docente_regfaltas==ite.id_docente}">
                                                                            <span style="color:gray; cursor:pointer">&nbsp;&nbsp;${ite.titulo} ${ite.nombres}&nbsp;&nbsp;</span>
                                                                        </c:if>
                                                                    </c:forEach>
                                                                    <span style="color:navy" onclick="javascript:__openDListRF('${item.id_curso}','${item.curso}','${item.ciclo}');"><strong><em><u>Cambiar</u></em></strong></span>
                                                                </td>
                                                            </c:if>
                                                        </tr>
                                                    </c:if>
                                                    <%sw = 1;%>
                                                </c:if>
                                                <%}%>
                                            </c:forEach>
                                            <c:forEach varStatus="i" var="it" items="${cursoMaterias}">
                                                <c:if test="${item.id_curso==it.id_curso}">
                                                    <tr onclick="selectPersona('<c:out value="${it.id_curso}"/>')" style="cursor: pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                                        <td style="width:20%"><img src="imagenes/cxc.png"><c:out value="${it.materia}"/></td>
                                                        <td style="width:2%"><img src="imagenes/fact_good.png"></td>
                                                            <c:if test="${it.docente!=null}">
                                                            <td title="Haga clic para cambiar el docente de la materia" style="width:40%; color:teal" onclick="javascript:__openDListCM('${it.id_curso_materia}','${item.curso}','${it.materia}','${item.ciclo}');">${it.titulo} ${it.docente}</td>
                                                        </c:if>
                                                        <c:if test="${it.docente==null}">
                                                            <td title="Materia sin docente, haga clic para asignar docente" style="width:40%; color:red" onclick="javascript:__openDListCM('${it.id_curso_materia}','${item.curso}','${it.materia}','${item.ciclo}');">Sin docente</td>
                                                        </c:if>
                                                    </tr>
                                                </c:if>
                                            </c:forEach>
                                        </table>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>

                    </td>
                    </tr>
                </table>

            </c:if>
        </div>
        <c:if test="${empty inscripcion}">
            <div id="fixme" style="color:black;border:1px solid rgb(255,255,153);border-top:none;background-color:rgb(255,255,153);text-align:left;padding:3px;display:none;font-size:8pt">
                <!--<img src="imagenes/loading.gif">&nbsp;--><span id="fixmeValue"></span>
                <div class="PwdMeterBase" title=" Cargando... " style="width:200px">
                    <div class="PwdBack">
                        <div id="progresspar" class="PwdMeter" style="width:0%"></div>
                    </div>
                </div>
            </div>

            <!-- Lista docentes para tutor de curso-->
            <div id="docentesList" style="display:none">
                <div id="curso"></div>
                <form action="<c:url value="/asignarDocentes.do"/>" method="post">
                    <table cellpadding="0" cellspacing="0" border="0" style="width:98%">
                        <tr>
                            <td class="gridContent">
                                <div>
                                    Selecciona un <b>docente</b> de la lista para asignar a este curso como tutor.
                                </div>
                                <div class="resultlayer1">
                                    <table width="100%" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF">
                                        <tr>
                                            <td class="gridHeaders">Nro.</td>
                                            <td class="gridHeaders">C&eacute;dula</td>
                                            <td class="gridHeaders">Nombres</td>
                                        </tr>
                                        <c:if test="${empty docentes}">
                                            <tr>
                                                <td class="gridData" colspan="4">No se han encontrado elementos.</td>
                                            </tr>
                                        </c:if>
                                        <c:forEach varStatus="i" var="ite" items="${docentes}">
                                            <tr style="cursor:pointer">
                                                <td class="gridData" align="center">
                                                    <input type="radio" name="tutor" value="${ite.id_docente}">${i.count}
                                                </td>
                                                <td class="gridData"><b>${ite.dip}</b></td>
                                                <td class="gridData">${ite.titulo} ${ite.nombres}</td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </div>
                            </td>
                        <tr>
                            <td align="center" ><button name="aceptar" class="button-normal" style="width:70px">Aceptar</button>
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                <button class="button-normal" style="width:70px" onclick="javascript:__closeDList()">Cancelar</button></td>
                        </tr>
                    </table>
                    <input type="hidden" name="id_cursoTutor" id="id_curso">
                    <input type="hidden" name="id_gestion" value="${id_gestion}">
                </form>
            </div>
                 <!-- Lista docentes para Coordinador de curso-->
            <div id="docentesListCoo" style="display:none">
                <div id="curso"></div>
                <form action="<c:url value="/asignarDocentes.do"/>" method="post">
                    <table cellpadding="0" cellspacing="0" border="0" style="width:98%">
                        <tr>
                            <td class="gridContent">
                                <div>
                                    Selecciona un <b>docente</b> de la lista para asignar a este curso como coordinador.
                                </div>
                                <div class="resultlayer1">
                                    <table width="100%" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF">
                                        <tr>
                                            <td class="gridHeaders">Nro.</td>
                                            <td class="gridHeaders">C&eacute;dula</td>
                                            <td class="gridHeaders">Nombres</td>
                                        </tr>
                                        <c:if test="${empty docentes}">
                                            <tr>
                                                <td class="gridData" colspan="4">No se han encontrado elementos.</td>
                                            </tr>
                                        </c:if>
                                        <c:forEach varStatus="i" var="ita" items="${docentes}">
                                            <tr style="cursor:pointer">
                                                <td class="gridData" align="center">
                                                    <input type="radio" name="coordinador" value="${ita.id_docente}">${i.count}
                                                </td>
                                                <td class="gridData"><b>${ita.dip}</b></td>
                                                <td class="gridData">${ita.titulo} ${ita.nombres}</td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </div>
                            </td>
                        <tr>
                            <td align="center" ><button name="aceptar" class="button-normal" style="width:70px">Aceptar</button>
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                <button class="button-normal" style="width:70px" onclick="javascript:__closeDListCoo()">Cancelar</button></td>
                        </tr>
                    </table>
                    <input type="hidden" name="id_cursoCoo" id="id_curso">
                    <input type="hidden" name="id_gestion" value="${id_gestion}">
                </form>
            </div>
            <div id="docentesListRF" style="display:none">
                <div id="curso"></div>
                <form action="<c:url value="/asignarDocentes.do"/>" method="post">
                    <table cellpadding="0" cellspacing="0" border="0" style="width:98%">
                        <tr>
                            <td class="gridContent">
                                <div>
                                    Selecciona un <b>docente</b> de la lista para asignar a este curso como tutor.
                                </div>
                                <div class="resultlayer1">
                                    <table width="100%" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF">
                                        <tr>
                                            <td class="gridHeaders">Nro.</td>
                                            <td class="gridHeaders">C&eacute;dula</td>
                                            <td class="gridHeaders">Nombres</td>
                                        </tr>
                                        <c:if test="${empty docentes}">
                                            <tr>
                                                <td class="gridData" colspan="4">No se han encontrado elementos.</td>
                                            </tr>
                                        </c:if>
                                        <c:forEach varStatus="i" var="ite" items="${docentes}">
                                            <tr style="cursor:pointer">
                                                <td class="gridData" align="center">
                                                    <input type="radio" name="id_docente_regfaltas" value="${ite.id_docente}">${i.count}
                                                </td>
                                                <td class="gridData"><b>${ite.dip}</b></td>
                                                <td class="gridData">${ite.titulo} ${ite.nombres}</td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </div>
                            </td>
                        <tr>
                            <td align="center" ><button name="aceptar" class="button-normal" style="width:70px">Aceptar</button>
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                <button class="button-normal" style="width:70px" onclick="javascript:__closeDList()">Cancelar</button></td>
                        </tr>
                    </table>
                    <input type="hidden" name="id_curso" id="id_curso">
                    <input type="hidden" name="id_gestion" value="${id_gestion}">
                </form>
            </div>

            <!-- Lista docentes para asignar docente a materia-->
            <div id="docentesCurMat" style="display:none">
                <div id="curso"></div>
                <div id="materia"></div>
                <form action="<c:url value="/asignarDocentes.do"/>" method="post">
                    <table cellpadding="0" cellspacing="0" border="0" style="width:98%">
                        <tr>
                            <td class="gridContent">
                                <div>
                                    Selecciona un <b>docente</b> de la lista para asignar a esta materia.
                                </div>
                                <div class="resultlayer1">
                                    <table width="100%" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF">
                                        <tr>
                                            <td class="gridHeaders">Nro.</td>
                                            <td class="gridHeaders">C&eacute;dula</td>
                                            <td class="gridHeaders">Nombres</td>
                                        </tr>
                                        <c:if test="${empty docentes}">
                                            <tr>
                                                <td class="gridData" colspan="4">No se han encontrado elementos.</td>
                                            </tr>
                                        </c:if>
                                        <c:forEach varStatus="i" var="ite" items="${docentes}">
                                            <tr style="cursor:pointer" >
                                                <td class="gridData" align="center">
                                                    <input type="radio" name="docmat" value="${ite.id_docente}">${i.count}
                                                </td>
                                                <td class="gridData"><b>${ite.dip}</b></td>
                                                <td class="gridData">${ite.titulo} ${ite.nombres}</td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </div>
                            </td>
                        <tr>
                            <td align="center" ><button name="aceptar" class="button-normal" style="width:70px">Aceptar</button>
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                <button class="button-normal" style="width:70px" onclick="javascript:__closeDList()">Cancelar</button></td>
                        </tr>
                    </table>
                    <input type="hidden" name="id_materiaCurso" id="id_cur_mat">
                    <input type="hidden" name="id_gestion" value="${id_gestion}">
                </form>
            </div>
        </c:if>
    </body>
</html>
