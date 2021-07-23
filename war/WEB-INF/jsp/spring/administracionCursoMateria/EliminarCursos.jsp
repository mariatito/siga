<%-- 
    Document   : EliminarCursos
    Created on : 29-jun-2009, 17:11:14
    Author     : Marco Antonio
--%>


<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@page contentType="text/html" pageEncoding="LATIN1"%>
<%@taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=LATIN1">
        <title>SANTA TERESA - Web 1.0</title>
        <script type="text/javascript" src="js/ajax.js"></script>
        <link rel="stylesheet" type="text/css" href="css/module.css" media="screen" />
        <script type="text/javascript">
            animatedcollapse.addDiv('success', 'fade=1');
            animatedcollapse.addDiv('failure', 'fade=1');
            animatedcollapse.init();
        </script>
        <script type="text/javascript">

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
            }

            function openDialogWindow(form,title,w,h){
                var addFormwin=dhtmlmodal.open('win'+form, 'div', form, title, 'width='+w+'px,height='+h+'px,left=100px,top=100px,resize=0,scrolling=0');
                addFormwin.moveTo('middle', 'middle');
                return addFormwin;
            }

            var confirmwin = null;
            function finalizarReg() {
                confirmwin = openDialogWindow('finalizarRegistro','Finalizar...','400','120');
            }

            function cancelReg() {
                confirmwin.hide();
            }

            function confirmReg() {
                document.MiForm.submit()
            }
             function selectCurso(id) {
                window.location='<c:url value="/eliminarCursos.do"/>?id_curso='+id;
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
                            <td class="tab_normal" onclick="javascript:window.location='<c:url value="/registrarCursos.do"/>'">Registrar cursos</td>
                            <td class="tab_normal" style="border-right:1px solid #7D909E" onclick="javascript:window.location='<c:url value="/actualizarCursos.do"/>'">Actualizar datos</td>
                            <td  class="tab_current">B&uacute;squeda y eliminaci&oacute;n</td>
                        </table>
                    </td>
                    <td class="tab_fin">&nbsp;</td>
                </tr>
            </table>
            <div class="titlepage">ELIMINAR DATOS</div>
        </div>

        <div class="maincontent">

            <div id="failure" style="position: absolute; width: 230px; border: 1px solid #FFCC00; padding: 5px; background-color: #FFFFAE; display: none">
                <table>
                    <td><img src="imagenes/alert.gif" border="0"></td>
                    <td><span style="color: #000000; font-size: 10pt; font-weight: bold">&nbsp;Fallo en la solicitud.</span></td>
                </table>
            </div>

            <div id="success" style="position: absolute; width: 230px; border: 1px solid #009933; padding: 5px; background-color: #DEF5E6; display: none">
                <span style="color: #000000; font-size: 10pt; font-weight: bold">Registro eliminado correctamente</span>
            </div>


            <c:if test="${empty cursos}">
                <form action="<c:url value="/actualizarCursos.do"/>" method="post" name="frmSeach" onsubmit="return setBuscar(this)">
                    <table>
                        <td class="text-label">Buscar curso</td>
                        <td class="text-label"><input type="text" name="search" value="${search}" class="text-field"></td>
                        <td class="text-label"><input class="button-submit" type="submit" name="opcion" value="Buscar"></td>
                    </table>
                </form>
                <br/>
                <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                    <tr>
                        <td class="tableHeader">Lista de cursos</td>
                    </tr>
                    <tr>
                        <td class="gridContent">
                            <div id="resultlayer1">
                                <table width="100%" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF">
                                    <tr>
                                        <td class="gridHeaders" style="width:40px">Nro.</td>
                                        <td class="gridHeaders" style="width:60px">C&oacute;digo</td>
                                        <td class="gridHeaders" style="width:400px">Descripci&oacute;n</td>
                                        <td class="gridHeaders" style="width:100px">Eliminar</td>

                                    </tr>
                                    <c:if test="${empty curso}">
                                        <tr>
                                            <td class="gridData" colspan="3">No se han encontrado elementos.</td>
                                        </tr>
                                    </c:if>
                                    <c:forEach varStatus="i" var="item" items="${curso}">
                                        <tr onclick="selectCurso('<c:out value="${item.id_curso}"/>')" style="cursor: pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                            <td class="gridData"><c:out value="${i.count}"/> </td>
                                            <td class="gridData"><c:out value="${item.desc_curso}"/></td>
                                            <td class="gridData">${item.curso} de ${item.ciclo}</td>
                                             <td class="gridData" align="center"><a href="eliminarCursos.do?param=${item.id_curso}">Eliminar</a></td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </div>
                        </td>
                    </tr>
                </table>
            </c:if>

            <c:if test="${!empty visualizar}">

                 <div id="formRegistroPersona" class="formPanel">
                    <form action="<c:url value="/eliminarCursos.do"/>" method="post" name="MiForm" onsubmit="return setRegistrar(this)">
                        <table width="800" border="0" cellpadding="0" cellspacing="0">

                            <tr>
                                <td align="center"colspan="7"><p><strong>REGISTRO DE CURSOS</strong></p></td>
                            </tr>
                            <tr>
                                <td width="14%"></td>
                                <td colspan="6"><strong>DATOS A REGISTRAR</strong></td>
                            </tr>
                            <tr>
                                <td width="14%"></td>
                                <td colspan="1">CODIGO CURSO</td>
                                <td colspan="1">:</td>
                                <td colspan="4"><input class="text-field" type="text" name="id_curso" value="${visualizar.id_curso}" maxlength="2" style="width:50px" onfocus="this.blur()"/></td>
                            </tr>
                            <tr>
                                <td colspan="7"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                            </tr>
                            <tr>
                                <td width="14%"></td>
                                <td colspan="1">DESCRIPCION</td>
                                <td colspan="1">:</td>
                                <td colspan="4"><input class="text-field" type="text" name="curso" value="${visualizar.curso}" maxlength="100" style="width:200px"disabled/></td>
                            </tr>
                            <tr>
                                <td colspan="7"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                            </tr>
                            <tr>
                                <td width="14%"></td>
                                <td colspan="1">CICLO</td>
                                <td colspan="1">:</td>
                                <td colspan="4"><input class="text-field" type="text" name="ciclo" value="${visualizar.ciclo}" maxlength="15" style="width:100px"disabled/></td>
                            </tr>
                            <tr>
                                <td colspan="7"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                            </tr>


                        </table>

                        <script type="text/javascript">setCargoAcademico(document.getElementById('item-cargo'));</script>

                        <input type="hidden" value="_reg" name="opcion1">

                        <div id="finalizarRegistro" style="display:none">
                            <div class="formPanel">
                                <div class="PwdMeterBase" style="width:100%">
                                    <table align="center">
                                        <td>
                                            <button class="topformbutton" style="width:70px" onclick="confirmReg()">Aceptar</button>
                                        </td>
                                        <td>
                                            <button class="topformbutton" style="width:70px" onclick="cancelReg()">Cancelar</button>
                                        </td>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </form>
                    <button class="topformbutton" onclick="finalizarReg()">Eliminar...</button>
                </div>

            </c:if>

        </div>




        <c:if test="${!empty mensaje}">
            <c:if test="${mensaje == 'true'}">
                <script type="text/javascript">
                    document.getElementById('success').style.display = 'inline';
                    setTimeout('animatedcollapse.hide(\'success\')',5000);
                </script>
            </c:if>
            <c:if test="${mensaje == 'false'}">
                <script type="text/javascript">
                    document.getElementById('failure').style.display = 'inline';
                    setTimeout('animatedcollapse.hide(\'failure\')',5000);
                </script>
            </c:if>
        </c:if>



    </body>
</html>