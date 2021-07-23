<%--
    Document   : BuscarPersona
    Created on : 14-may-2009, 13:29:22
    Author     : Marco Antonio Quenta Velasco
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
        <link rel="stylesheet" type="text/css" href="css/module.css" media="screen" />
        <script type="text/javascript">
            animatedcollapse.addDiv('success', 'fade=1');
            animatedcollapse.addDiv('failure', 'fade=1');
            animatedcollapse.init();
        </script>

        <script type="text/javascript">
            function onRowover(elem) {
                elem.className='colover';
            }
            function onRowout(elem) {
                elem.className='colout';
            }
            function setBuscar(form) {
                if (form.search.value.length < 3) {
                    alert('Debes ingresar algun dato para buscar.');
                    return false;
                }
                form.opcion.value = 'Conectando...';
                form.opcion.disabled = true;
                return true;
            }
            function selectPersona(id) {
                window.location='<c:url value="/buscarPersonas.do"/>?id_persona='+id;
            }

            function setRegistrar(form) {
                form.guardar.disabled = true;
                form.guardar.value = 'Enviando los datos...';
                form.submit();
                /*si los datos no son correctos */
                return false
            }

            function setCargoAcademico(obj) {
                if(obj.value == 'AAA') {
                    document.getElementById('item-detalle').style.display = 'inline';
                    document.getElementById('item-administrador').style.display = 'none';
                    document.getElementById('item-docente').style.display = 'none';
                    document.getElementById('item-system').style.display = 'none';
                }
                if(obj.value == 'ADM') {
                    document.getElementById('item-detalle').style.display = 'none';
                    document.getElementById('item-administrador').style.display = 'inline';
                    document.getElementById('item-docente').style.display = 'none';
                    document.getElementById('item-system').style.display = 'none';
                }
                if(obj.value == 'DOC') {
                    document.getElementById('item-detalle').style.display = 'none';
                    document.getElementById('item-administrador').style.display = 'none';
                    document.getElementById('item-docente').style.display = 'inline';
                    document.getElementById('item-system').style.display = 'none';
                }
                if(obj.value == 'SYS') {
                    document.getElementById('item-detalle').style.display = 'none';
                    document.getElementById('item-administrador').style.display = 'none';
                    document.getElementById('item-docente').style.display = 'none';
                    document.getElementById('item-system').style.display = 'inline';
                }
                if(obj.value == 'EST') {
                    document.getElementById('item-detalle').style.display = 'none';
                    document.getElementById('item-administrador').style.display = 'none';
                    document.getElementById('item-docente').style.display = 'none';
                    document.getElementById('item-system').style.display = 'none';
                }
            }


        </script>

    </head>
    <body>
        <div class="headercontent">
            <table id="tabs_menu" cellpadding="0" cellspacing="0" border="0" style="width:100%">
                <tr>
                    <td class="tab_init">&nbsp;</td>
                    <td style="width:50%">
                        <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                            <td class="tab_normal" onclick="javascript:window.location='<c:url value="/registrarPersona.do"/>'">Registro &uacute;nico</td>
                            <td class="tab_normal" onclick="javascript:window.location='<c:url value="/actualizarDatosPersona.do"/>'">Actualizar datos</td>
                            <td class="tab_current">B&uacute;squeda y eliminaci&oacute;n</td>
                        </table>
                    </td>
                    <td class="tab_fin">&nbsp;</td>
                </tr>
            </table>
            <div class="titlepage">B&Uacute;SQUEDA Y ELIMINACI&Oacute;N </div>
        </div>
        <div class="maincontent">
            <div id="failure" style="position: absolute; width: 300px; border: 1px solid red; padding: 0px; background-color: #FFC1C1; display: none">
                <table>
                    <td><img src="imagenes/iconos_sigaa/activo_pe.png" border="0" width="15px"></td>
                    <td><span style="color: #000000; font-size: 10pt; font-weight: lighter">&nbsp;No se puede eliminar el registro.</span></td>
                </table>
            </div>

            <div id="success" style="position: absolute; width: 300px; border: 1px solid green; padding: 0px; background-color: #DEF5E6; display: none">
                <table>
                    <td><img src="imagenes/iconos_sigaa/activo_si.png" border="0" width="15px"></td>
                    <td><span style="color: #000000; font-size: 10pt; font-weight: lighter">&nbsp;La eliminaci&oacute;n se realiz&oacute; correctamente.</span></td>
                </table>
            </div>

            <c:if test="${empty persona}">
                <form action="<c:url value="/buscarPersonas.do"/>" method="post" name="frmSeach" onsubmit="return setBuscar(this)">
                    <table>
                        <td class="text-label">Buscar persona : </td>
                        <td class="text-label"><input type="text" name="search" value="${search}" class="text-field"></td>
                        <td class="text-label"><input class="button-submit" type="submit" name="opcion" value="Buscar"></td>
                    </table>
                </form>
                <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                    <tr>
                        <td class="tableHeader">Listado del personal</td>
                    </tr>
                    <tr>
                        <td class="gridContent">
                            <div id="resultlayer1">
                                <table width="100%" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF">
                                    <tr>
                                        <td class="gridHeaders" style="width:5%">Nro.</td>
                                        <td class="gridHeaders" style="width:10%">CI</td>
                                        <td class="gridHeaders" style="width:60%">Apellidos y nombres</td>
                                        <td class="gridHeaders" style="width:15%">Tipo de usuario</td>
                                        <td class="gridHeaders" style="width:10%">Eliminar</td>
                                    </tr>
                                    <c:if test="${empty resultados}">
                                        <tr>
                                            <td class="gridData" colspan="4">No se han encontrado elementos.</td>
                                        </tr>
                                    </c:if>
                                    <c:forEach varStatus="i" var="item" items="${resultados}">
                                        <tr onclick="selectPersona('<c:out value="${item.id_persona}"/>')" style="cursor: pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                            <td class="gridData"><c:out value="${i.count}"/> </td>
                                            <td class="gridData"><c:if test="${item.id_tipo_usuario=='EST'}">........</c:if><c:if test="${item.id_tipo_usuario!='EST'}">${item.dip}</c:if></td>
                                            <td class="gridData">${item.paterno} ${item.materno} ${item.nombres}</td>
                                            <td class="gridData" align="center" style="color:navy">
                                                <c:if test="${item.id_tipo_usuario=='EST'}">Estudiante</c:if>
                                                <c:if test="${item.id_tipo_usuario=='ADM'}">Administrativo</c:if>
                                                <c:if test="${item.id_tipo_usuario=='DOC'}">Docente</c:if>
                                            </td>
                                            <td class="gridData" align="center"><a href="buscarPersonas.do?param=${item.id_persona}">Eliminar</a></td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </div>
                        </td>
                    </tr>
                </table>
            </c:if>
            <c:if test="${!empty persona}">
                <div align="center">
                    <form action="<c:url value="/buscarPersonas.do"/>" method="post" name="frmRegistro" onsubmit="return setRegistrar(this)">
                        <table width="80%" border="0" cellpadding="0" cellspacing="0" class="gridContent" align="center">
                        <tr>
                            <td align="center" colspan="7"><p><strong>ELIMINAR DATOS DEL PERSONAL</strong></p></td>
                        </tr>
                            <tr>
                                <td width="14%"></td>
                                <td colspan="1">NOMBRE(S)</td>
                                <td colspan="1">:</td>
                                <td colspan="4"><input class="text-field" type="text" name="nombres" value="${persona.nombres}" maxlength="70" style="width:250px" disabled/></td>
                            </tr>
                            <tr>
                                <td colspan="7"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                            </tr>
                            <tr>
                                <td width="14%"></td>
                                <td colspan="1">APELLIDO PATERNO</td>
                                <td colspan="1">:</td>
                                <td colspan="4"><input class="text-field" type="text" name="paterno" value="${persona.paterno}" maxlength="70" style="width:250px" disabled/></td>
                            </tr>
                            <tr>
                                <td colspan="7"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                            </tr>
                            <tr>
                                <td width="14%"></td>
                                <td colspan="1">APELLIDO MATERNO</td>
                                <td colspan="1">:</td>
                                <td colspan="4"><input class="text-field" type="text" name="materno" value="${persona.materno}" maxlength="70" style="width:250px" disabled/></td>
                            </tr>
                            <tr>
                                <td colspan="7"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                            </tr>
                            <c:if test="${persona.id_tipo_usuario!='EST'}">
                                <tr>
                                    <td width="14%"></td>
                                    <td width="23%">C.I.<img src="imagenes/icons/required.gif"/></td>
                                    <td width="2%">:</td>
                                    <td colspan="4"><input class="text-field" type="text" name="ci" maxlength="10" value="${persona.dip}" style="width:120px" disabled/></td>
                                </tr>
                            </c:if>
                            <tr>
                                <td colspan="7"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                            </tr>
                            <tr>
                                <td width="14%"></td>
                                <td width="23%">DIRECCION</td>
                                <td width="2%">:</td>
                                <td colspan="4"><input class="text-field" type="text" name="direccion" maxlength="250" value="${persona.direccion}" style="width:250px" disabled/></td>
                            </tr>
                            <tr>
                                <td colspan="7"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                            </tr>
                            <tr>
                                <td width="14%"></td>
                                <td width="23%">TELEFONO(1)</td>
                                <td width="2%">:</td>
                                <td colspan="4"><input class="text-field" type="text" name="telefono1" maxlength="10" value="${persona.telefono1}" style="width:120px" disabled/></td>
                            </tr>
                            <tr>
                                <td colspan="7"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                            </tr>
                            <tr>
                                <td width="14%"></td>
                                <td width="23%">TELEFONO (2)</td>
                                <td width="2%">:</td>
                                <td colspan="4"><input class="text-field" type="text" name="telefono2" maxlength="10" value="${persona.telefono2}" style="width:120px" disabled/></td>
                            </tr>
                            <tr>
                                <td colspan="7"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                            </tr>
                            <tr>
                                <td width="14%"></td>
                                <td width="23%">SEXO</td>
                                <td width="2%">:</td>
                                <td colspan="4">
                                    <select name="id_sexo" id="item-sexo" class="text-field" style="width:120px" disabled>
                                        <c:forEach var="item" items="${tipos_sexos}">
                                            <option value="${item.id}"<c:if test="${item.id == persona.id_sexo  }"> selected </c:if>> ${item.valor}
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="7"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                            </tr>
                            <tr>
                                <td width="14%"></td>
                                <td width="23%">CARGO ACAD&Eacute;MICO</td>
                                <td width="2%">:</td>
                                <td colspan="4">
                                    <select name="id_tipo_usuario" id="item-cargo" class="text-field" style="width:120px" onchange="setCargoAcademico(this)" disabled>
                                        <c:forEach var="item" items="${cargos_academicos}">
                                            <option value="${item.id}" <c:if test="${item.id==persona.id_tipo_usuario}"> selected </c:if>> ${item.valor}
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="7"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                            </tr>
                            <tr>
                                <td colspan="7" align="right">
                                    <div id="item-detalle" style="display:inline">
                                        <table width="795" border="0">
                                            <tr>
                                                <td colspan="7" align="center">Detalle...</td>
                                            </tr>
                                        </table>
                                    </div>
                                    <div id="item-administrador" style="display:none">
                                        <table width="795" border="0" >
                                            <tr>
                                                <td width="14%"></td>
                                                <td width="23%">FECHA DE INGRESO</td>
                                                <td width="2%">:</td>
                                                <td colspan="4">
                                                    <input class="text-field" type="text" name="fecha_asume_adm" value="${fec_asum}" style="width:120px" disabled/>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td width="14%"></td>
                                                <td width="23%">T&Iacute;TULO PROFESIONAL</td>
                                                <td width="2%">:</td>
                                                <td colspan="4">
                                                    <select name="id_titulo_adm" id="item-titulo_adm" class="text-field" style="width:200px" disabled >
                                                        <c:forEach var="item" items="${titulos_profesionales}">
                                                            <option value="${item.id}" <c:if test="${item.id==administrativo.id_titulo}"> selected </c:if>> ${item.valor} (${item.abreviatura})
                                                        </c:forEach>
                                                    </select>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td width="14%"></td>
                                                <td width="23%">CARGO </td>
                                                <td width="2%">:</td>
                                                <td colspan="4">
                                                    <select name="id_cargo_adm" id="item-cargo_adm" class="text-field" style="width:200px" disabled >
                                                        <c:forEach var="item" items="${tipos_cargos}">
                                                            <option value="${item.id}" <c:if test="${item.id==administrativo.id_tipo_cargo}"> selected </c:if>> ${item.valor}
                                                        </c:forEach>
                                                    </select>
                                                </td>
                                            </tr>
                                        </table>
                                    </div>
                                    <div id="item-docente" style="display:none">
                                        <table width="795" border="0" >
                                            <tr>
                                                <td width="14%"></td>
                                                <td width="23%">T&Iacute;TULO PROFESIONAL</td>
                                                <td width="2%">:</td>
                                                <td colspan="4">
                                                    <select name="id_titulo_doc" id="item-titulo_doc" class="text-field" style="width:200px" disabled>
                                                        <c:forEach var="item" items="${titulos_profesionales}">
                                                            <option value="${item.id}" <c:if test="${item.id==docente.id_titulo}"> selected </c:if>> ${item.valor} (${item.abreviatura})
                                                        </c:forEach>
                                                    </select>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td width="14%"></td>
                                                <td width="23%">CATEGORIA </td>
                                                <td width="2%">:</td>
                                                <td colspan="4">
                                                    <select name="id_categoria_doc" id="item-categoria_doc" class="text-field" style="width:100px" disabled>
                                                        <c:forEach var="item" items="${tipos_categorias}">
                                                            <option value="${item.id}" <c:if test="${item.id==docente.id_tipo_categoria}"> selected </c:if>> ${item.valor}
                                                        </c:forEach>
                                                    </select>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td width="14%"></td>
                                                <td width="23%">CARGA HORARIA </td>
                                                <td width="2%">:</td>
                                                <td colspan="4">
                                                    <input class="text-field" type="text" name="cargahoraria" id="fecha" value="${docente.cargahoraria}" style="width: 50px" disabled/>Hrs.
                                                </td>
                                            </tr>
                                        </table>
                                    </div>
                                    <div id="item-system" style="display:none">
                                        <table width="795" border="0" >
                                            <tr>
                                                <td colspan="7" align="center">Buena suerte!!! ya no hay ideas????¿?¿?¿?¿?!"·%$&/()()=)?¿/&%$"·$</td>
                                            </tr>
                                        </table>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="7"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                            </tr>
                        </table>
                        <script type="text/javascript">setCargoAcademico(document.getElementById('item-cargo'));</script>
                        <input name="guardar" class="button-submit" type="submit" value="Eliminar registro"/>
                        <input type="hidden" name="id_persona" value="${persona.id_persona}">
                        <input type="hidden" name="id_docente" value="${docente.id_docente}">
                        <input type="hidden" name="id_administrativo" value="${administrativo.id_administrativo}">
                        <input type="hidden" name="opcion" value="_delete">
                    </form>
                </div>

            </c:if>
        </div>
        <c:if test="${!empty mensaje}">
            <c:if test="${mensaje == 'true'}">
                <script type="text/javascript">
                    document.getElementById('success').style.display = 'inline';
                    setTimeout('animatedcollapse.hide(\'success\')',2500);
                </script>
            </c:if>
            <c:if test="${mensaje == 'false'}">
                <script type="text/javascript">
                    document.getElementById('failure').style.display = 'inline';
                    setTimeout('animatedcollapse.hide(\'failure\')',2500);
                </script>
            </c:if>
        </c:if>
    </body>
</html>
