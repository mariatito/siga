<%-- 
    Document   : AdministrarPlanes
    Created on : 15-jun-2009, 14:56:53
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
           
            /* Administrar paralelos */
            var mindex=-1;
            var _materias = new Array();
            var _allmats = new Array();
            var _id_materia_open;
            var _id_paralelo_open;
            var _selectMaterias = null;
            function nuevasMaterias(id_curso,curso,ciclo) {
                _selectMaterias = openWindow('_selectMaterias','Asignación de Docentes a Materias y Materias a Curso','650','340');
                $('curso').innerHTML = "CURSO : "+curso+" de "+ciclo;
                document.getElementById('id_curso').setValue(id_curso);
                return false;
            }
            function cancelSave(val) {
                if(val == '_selectMaterias')  {
                    _selectMaterias.hide();
                }
            }
            var _deleteMaterias = null;
            function deleteMaterias(id_curso,curso) {
                _deleteMaterias = dhtmlmodal.open('w_deleteMaterias', 'div', '_deleteMaterias', 'Aviso', 'width=330px,height=100px,left=100px,top=100px,resize=0,scrolling=0');
                _deleteMaterias.moveTo('middle', 'middle');
                $('curso').innerHTML ="¿Confirma que desea borrar todas las materias del curso \""+curso+"\"?, por que se perderan todos los datos...";
                document.getElementById('id_curso').setValue(id_curso);
                return false;
            }
            function cancelDelete() {
                _deleteMaterias.hide();
            }

            function selectGestion(id_gestion){
                window.location = '<c:url value="/adminPlan.do"/>?id_gestion='+id_gestion;
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
                            <td class="tab_current" >Plan de estudio</td>
                            <td class="tab_normal" onclick="javascript:window.location='<c:url value="/asignarDocentes.do"/>'">Asignaci&oacute;n curso-docente</td>
                            <td class="tab_normal" onclick="javascript:window.location='<c:url value="/administrarCurso.do"/>'">Administrar cursos</td>
                            <td class="tab_normal" onclick="javascript:window.location='<c:url value="/administrarEvaluaciones.do"/>'">Fechas de evaluaciones general</td>
                            <td class="tab_normal" onclick="javascript:window.location='<c:url value="/boletinesTrimestrales.do"/>'">Boletines trimestrales</td>
                        </table>
                    </td>
                    <td class="tab_fin">&nbsp;</td>
                </tr>
            </table
            <div class="titlepage">PLAN DE ESTUDIO</div>
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
                        <td class="tableHeader">Todos los cursos</td>
                    </tr>
                    <td class="gridContent">
                        <table width="100%" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF">
                            <tr>
                                <td class="gridHeaders" style="width:40px">Nro.</td>
                                <td class="gridHeaders" style="width:70px">C&oacute;digo</td>
                                <td class="gridHeaders">Curso</td>
                            </tr>
                            <% int indexc = 0;%>
                            <c:forEach varStatus="j" var="item" items="${cursos}">

                                <script type="text/javascript">
                                    mindex++;_materias[mindex]='${item.id_curso}';
                                    materia = {"id_materia":"${item.id_curso}","sigla":"${item.curso}","materia":"${item.curso}"};_allmats['${item.id_curso}'] = materia;
                                </script>
                                <% indexc++;%>
                                <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                    <td align="center" style="cursor:pointer" valign="top" class="gridData"><% out.print(indexc);%></td>
                                    <td align="center" style="cursor:pointer" valign="top" class="gridData">${item.desc_curso}</td>
                                    <td valign="top" class="gridData">
                                        <table border="0" width="100%">
                                            <tr>
                                                <td colspan="2">
                                                    <span style="cursor:pointer"><img src="imagenes/bulletarrow.gif"><strong>${item.curso} de ${item.ciclo}</strong></span>
                                                </td>
                                            </tr>
                                            <!--c:if test="${item.id_curso!='K'}"-->
                                            <tr>
                                                <td align="center" style="cursor:pointer; color:navy" valign="top" onclick="nuevasMaterias('${item.id_curso}','${item.curso}','${item.ciclo}')">Asignar y/o actualizar materias</td>
                                                <td align="center" style="cursor:pointer; color:navy" valign="top" onclick="deleteMaterias('${item.id_curso}','${item.curso}')">Eliminar todas las materias</td>
                                            </tr>
                                            <!--/c:if-->
                                        </table>
                                        <table border="0" class="gridContent" width="100%" cellpadding="0" cellspacing="0">
                                            <c:forEach varStatus="i" var="it" items="${cursoMaterias}">
                                                <c:if test="${item.id_curso==it.id_curso}">
                                                    <tr style="cursor: pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                                        <td style="width:20%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="imagenes/cxc.png">&nbsp;&nbsp;&nbsp;&nbsp; <c:out value="${it.materia}"/></td>
                                                        <td style="width:38%"><a href="adminPlan.do?param=${it.id_curso_materia}&id_gestion=${id_gestion}">Eliminar</a></td>
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

            <div id="_selectMaterias"  style="display:none">
                <div class="headercontent">
                    <table style="width:100%" cellspacing="0" cellpadding="0">
                        <tr>
                            <td class="menuHead"><b>ADMINISTRACI&Oacute;N DEL PLAN DE ESTUDIOS&nbsp;>&nbsp;Asignar y/o Actualizar Materias y Docentes</b></td>
                        </tr>
                    </table>
                </div>

                <div class="formPanel">
                    <form action="<c:url value="/adminPlan.do"/>" method="post">
                        <table border="0" width="100%">
                            <tr>
                                <td class="menuHead" colspan="2" id="curso"></td>
                            </tr>
                        </table>

                        <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                            <tr>
                                <td class="tableHeader">Todas las Materias</td>
                            </tr>
                            <tr>
                                <td class="gridContent">
                                    <div id="resultlayer1">
                                        <table width="100%" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF">
                                            <tr>
                                                <td class="gridHeaders" style="width:10%">Nro.</td>
                                                <td class="gridHeaders" style="width:10%">Asignar</td>
                                                <td class="gridHeaders" style="width:80%">Materia</td>
                                            </tr>
                                            <c:if test="${empty materias}">
                                                <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                                    <td class="gridData" colspan="3">No se han encontrado elementos.</td>
                                                </tr>
                                            </c:if>
                                            <c:forEach varStatus="j" var="item" items="${materias}">
                                                <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                                    <td class="gridData" align="center">${j.count}</td>
                                                    <td class="gridData" align="center"><input type="checkbox" name="${item.id_materia}" value="${item.id_materia}"></td>
                                                    <td class="gridData"><img src="imagenes/cxc.png">&nbsp;&nbsp;&nbsp;<b>${item.materia}</b></td>
                                                </tr>
                                            </c:forEach>
                                        </table>
                                    </div>
                                </td>
                            </tr>
                        </table>
                        <table align="center" width="100%">
                            <tr>
                                <td width="25%"></td>
                                <td width="25%"align="center" ><input class="button-submit" type="submit" name="_guardarMatDoc" value="Guardar"></td>
                                <td width="25%"align="center" ><input class="button-submit" type="button" value="Cancelar" onclick="javascript:cancelSave('_selectMaterias')"></td>
                                <td width="25%"></td>
                            </tr>
                        </table>
                        <input type="hidden" name="id_curso" id="id_curso">
                        <input type="hidden" name="id_gestion" value="${id_gestion}">
                    </form>
                </div>
            </div>

            <div id="_deleteMaterias" style="display:none">
                <form action="<c:url value="/adminPlan.do"/>" method="post">
                    <div class="formPanel">
                        <div class="PwdMeterBase" style="width:100%">
                            <table align="center">
                                <tr>
                                    <td colspan="2" id="curso"></td>
                                </tr>
                                <tr>
                                    <td align="right" ><button class="button-submit" style="width:70px">Aceptar</button></td>
                                    <td align="left" ><button class="button-normal" style="width:70px" onclick="javascript:cancelDelete()">Cancelar</button></td>
                                </tr>
                                <input type="hidden" name="id_cursoDelete" id="id_curso">
                                <input type="hidden" name="id_gestion" value="${id_gestion}">
                            </table>
                        </div>
                    </div>
                </form>
            </div>
        </c:if>
    </body>
</html>
