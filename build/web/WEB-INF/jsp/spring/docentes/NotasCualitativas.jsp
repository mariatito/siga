<%-- 
    Document   : NotasCualitativas
    Created on : 12-oct-2009, 22:40:07
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
        <link rel="stylesheet" type="text/css" href="css/module.css" media="screen" />
        <script type="text/javascript" src="js/ajax.js"></script>
        <script type="text/javascript" src="js/prototype.js"></script>
        <script type="text/javascript">

            function openWindow(form,title,w,h){
                docentesWin=dhtmlmodal.open('win'+form, 'div', form, title, 'width='+w+'px,height='+h+'px,left=100px,top=100px,resize=1,scrolling=1');
                docentesWin.moveTo('middle', 'middle');
                return docentesWin;
            }
            function selectMateria(id_curso_materia,materia,curso) {
                window.location='<c:url value="/notasCualitativas.do"/>?id_curso_materia='+id_curso_materia+'&materia='+materia+'&curso='+curso;
            }
            function setDeleteCualitativa(id_cualitativa, id_curso_materia){
                window.location='<c:url value="/notasCualitativas.do"/>?id_cualitativa='+id_cualitativa+'&delete=_delete&id_curso_materia='+id_curso_materia+'&curso=${curso}&materia=${materia}';
            }
            function setUpdateCualitativa(id_curso_materia,id_cualitativa, categoria, descripcion) {
                _updateCualitativa = openWindow('_updateCualitativa','Editar calificación cuantitativa est&aacute;','600','150');
                $('addevaform').categoria.value = categoria;
                $('addevaform').descripcion.value = descripcion;
                document.getElementById('id_cualitativa').setValue(id_cualitativa);
                document.getElementById('id_curso_materia').setValue(id_curso_materia);
                return false;
            }
            function cancelUpdateCualitativa(val) {
                if(val == '_updateCualitativa')  {
                    _updateCualitativa.hide();
                }
            }
        </script>
    </head>
    <body>
        <div class="headercontent">
            <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                <tr>
                    <td class="tab_init">&nbsp;</td>
                    <td style="width:50%">
                        <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                            <td class="tab_normal" onclick="javascript:window.location='<c:url value="/registrarCalificaciones.do"/>'">Subir calificaciones</td>
                            <td class="tab_current" >Registrar calificaciones cualitativas</td>
                            <td class="tab_normal" onclick="javascript:window.location='<c:url value="/tutorCurso.do"/>'">Tutor curso</td>
                        </table>
                    </td>
                    <td class="tab_fin">&nbsp;</td>
                </tr>
            </table>
            <div class="titlepage">REGISTRAR CALIFICACIONES CUALITATIVAS</div>
        </div>
        <div class="maincontent">
            <c:if test="${!empty elemento}">
                <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                    <tr>
                        <td class="tableHeader">Mis cursos y materias</td>
                    </tr>
                    <tr>
                        <td class="gridContent">
                            <div id="resultlayer1">
                                <table width="100%" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF">
                                    <tr class="gridHeaders">
                                        <td width="10%" >NRO.</td>
                                        <td width="25%" >CURSO</td>
                                        <td width="65%" ></td>
                                    </tr>
                                    <c:if test="${empty cursos}">
                                        <tr>
                                            <td class="gridData" colspan="3" style="cursor:pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">No se han encontrado elementos.</td>
                                        </tr>
                                    </c:if>
                                    <% int indexc = 1;%>
                                    <c:forEach varStatus="i" var="item" items="${cursos}">
                                        <tr style="cursor:pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                            <c:if test="${item.id_curso=='P1' || item.id_curso=='P2' || item.id_curso=='P3' || item.id_curso=='P4' || item.id_curso=='P5' || item.id_curso=='P6' || item.id_curso=='P7' || item.id_curso=='P8'}">
                                                <td valign="top" align="center"><% out.print(indexc++);%></td>
                                                <td valign="top" colspan="2"><b><img src="imagenes/iconos_sigaa/indicador.png">&nbsp;&nbsp;<c:out value="${item.curso}"/></b>
                                                    <table width="80%" cellpadding="0" cellspacing="0" border="0" align="center">
                                                        <tr>
                                                            <td colspan="2" style="color:gray"><img src="imagenes/iconos_sigaa/etiqueta_verde.png">&nbsp;&nbsp;<strong>Materias</strong></td>
                                                        </tr>
                                                        <c:forEach varStatus="i" var="items" items="${item.cursomaterias}">
                                                            <tr onclick="selectMateria('${items.id_curso_materia}','${items.materia}','${item.curso}')" style="cursor:pointer; color:#003366" onmouseover="onRowover(this)" onmouseout="onRowout(this)" bgcolor="white">
                                                                <td width="10%" align="right"><img src="imagenes/iconos_sigaa/etiqueta_azul.png"></td>
                                                                <td width="90%"><b>&nbsp;&nbsp;&nbsp;<c:out value="${items.materia}"/></b></td>
                                                            </tr>
                                                        </c:forEach>
                                                    </table>
                                                </td>
                                            </c:if>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </div>
                        </td>
                    </tr>
                </table>
            </c:if>
            <c:if test="${empty elemento}">
                <table cellpadding="0" cellspacing="0" border="0" style="width:100%" class="gridContent">
                    <tr>
                        <td class="tableHeader">Mis calificaciones definidas</td>
                    </tr>
                    <tr>
                        <td><p></td>
                    </tr>
                    <tr>
                        <td>
                            <table width="100%">
                                <tr>
                                    <td width="30%" align="center" valign="top">
                                        <table width="100%" cellpadding="0" cellspacing="0" border="0">
                                            <tr >
                                                <td width="30%">Curso</td>
                                                <td width="5%">:</td>
                                                <td width="65%"><strong>${curso}</strong></td>
                                            </tr>
                                            <tr>
                                                <td>Materia</td>
                                                <td>:</td>
                                                <td><strong>${materia}</strong></td>
                                            </tr>
                                        </table>
                                    </td>
                                    <td width="70%">
                                        <form action="<c:url value="/notasCualitativas.do"/>" method="post">
                                            <table width="60%" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF" class="gridData">
                                                <tr class="gridHeaders">
                                                    <td colspan="3" align="center"><strong>Nueva calificaci&oacute;n cualitativa</strong></td>
                                                </tr>
                                                <tr >
                                                    <td width="27%">Categoria</td>
                                                    <td width="3%">:</td>
                                                    <td width="70%"><input class="text-field" type="text" name="categoria" maxlength="25" style="width:100px"/></td>
                                                </tr>
                                                <tr>
                                                    <td>Descripci&oacute;n</td>
                                                    <td>:</td>
                                                    <td><textarea name="descripcion" class="text-field"  style="width:400px"></textarea></td>
                                                </tr>
                                                <tr>
                                                    <td colspan="3" align="center">
                                                        <input type="hidden" name="id_curso_materia" value="${id_curso_materia}"/>
                                                        <input type="hidden" name="nuevo" value="_nuevo"/>
                                                        <input type="hidden" name="curso" value="${curso}"/>
                                                        <input type="hidden" name="materia" value="${materia}"/>
                                                        <button class="topformbutton" style="width:70px" onclick="cancelReg()">Guardar</button>
                                                    </td>
                                                </tr>
                                            </table>
                                        </form> 
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td><p></td>
                    </tr>
                    <tr>
                        <td class="gridContent">
                            <div id="resultlayer1">
                                <table width="100%" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF">
                                    <tr class="gridHeaders">
                                        <td width="5%">Nro.</td>
                                        <td width="20%">Categoria</td>
                                        <td width="55%">Descripci&oacute;n</td>
                                        <td width="10%">Editar</td>
                                        <td width="10%">Eliminar</td>

                                    </tr>
                                    <c:if test="${empty cualitativas}">
                                        <tr>
                                            <td class="gridData" colspan="5" style="cursor:pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">No se han encontrado elementos.</td>
                                        </tr>
                                    </c:if>
                                    <c:forEach varStatus="i" var="items" items="${cualitativas}">
                                        <tr style="cursor:pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                            <td class="gridData" valign="top" align="center"><c:out value="${i.count}"/> </td>
                                            <td class="gridData" valign="top"><strong>${items.categoria}</strong></td>
                                            <td class="gridData">${items.descripcion}</td>
                                            <td class="gridData" align="center"><img src="imagenes/iconos_sigaa/editar.png" width="15px" title="Edita/Modificar" onclick="setUpdateCualitativa('${items.id_curso_materia}','${items.id_cualitativa}','${items.categoria}','${items.descripcion}')"></td>
                                            <td class="gridData" align="center"><img src="imagenes/iconos_sigaa/eliminar.png" width="15px" title="Eliminar/Borrar" onclick="setDeleteCualitativa('${items.id_cualitativa}','${items.id_curso_materia}')"></td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </div>
                        </td>
                    </tr>
                </table>

                <div id="_updateCualitativa"  style="display:none">
                    <div class="headercontent">
                        <table style="width:100%" cellspacing="0" cellpadding="0">
                            <tr>
                                <td class="menuHead"><b>MIS MATERIAS&nbsp;>&nbsp;Calificaciones cualitativas</b></td>
                            </tr>
                        </table>
                    </div>

                    <div class="formPanel">
                        <form id="addevaform" action="<c:url value="/notasCualitativas.do"/>" method="post">
                            <table width="100%" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF" class="gridData">
                                <tr class="gridHeaders">
                                    <td colspan="3" align="center"><strong>Editar calificaci&oacute;n cualitativa</strong></td>
                                </tr>
                                <tr >
                                    <td width="27%">Categoria</td>
                                    <td width="3%">:</td>
                                    <td width="70%"><input class="text-field" type="text" name="categoria" maxlength="25" style="width:100px"/></td>
                                </tr>
                                <tr>
                                    <td>Descripci&oacute;n</td>
                                    <td>:</td>
                                    <td><textarea name="descripcion" class="text-field" style="width:400px"></textarea></td>
                                </tr>
                                <tr>
                                    <td colspan="3" align="center">
                                        <input type="hidden" name="id_cualitativa" id="id_cualitativa"/>
                                        <input type="hidden" name="id_curso_materia" id="id_curso_materia"/>
                                        <input type="hidden" name="update" value="_update"/>
                                        <input type="hidden" name="curso" value="${curso}"/>
                                        <input type="hidden" name="materia" value="${materia}"/>
                                        <button class="button-normal" style="width:70px">Guardar</button>
                                        <button class="button-normal" style="width:70px" onclick="javascript:cancelUpdateCualitativa('_updateCualitativa')">Cancelar</button>
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div>
                </div>
            </c:if>
        </div>
    </body>
</html>