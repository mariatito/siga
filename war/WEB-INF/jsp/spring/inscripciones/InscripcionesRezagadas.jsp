<%-- 
    Document   : InscripcionesRezagadas
    Created on : 16-jul-2010, 23:42:25
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
        <script type="text/javascript" src="js/jquery-1.2.2.pack.js"></script>
        <script type="text/javascript" src="js/animatedcollapse.js"></script>
        <link rel="stylesheet" type="text/css" href="css/module.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="css/st.css" media="screen" />
        <script type="text/javascript" src="js/prototype.js"></script>
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
            function selectGestion(id_gestion){
                window.location = '<c:url value="/inscripcionRezagada.do"/>?id_gestion='+id_gestion+'&search=';
            }
            function selectFamilia(id_familia) {
                window.location='<c:url value="/inscripcionRezagada.do"/>?id_familia='+id_familia+'&id_gestion=${id_gestion}';
            }
            function selectEstudiante(id_estudiante,id_familia,id_curso){ 
                // window.location='<c:url value="/inscripcionRezagada.do"/>?id_estudiante='+id_estudiante+'&id_familia='+id_familia+'&id_curso='+id_curso+'&id_gestion=${id_gestion}';
            }
            function verificaNota(id_evaluacion){
                var nota=parseInt($('nota-'+id_evaluacion).value);
                var dps=parseInt($('dps-'+id_evaluacion).value);
                if(!isNaN(parseInt(nota)) && !isNaN(parseInt(nota))){
                    if(nota<=60 && nota>=0 && dps<=10 && dps>=0){
                        if((nota+dps)>35){
                            $('promedio-'+id_evaluacion).innerHTML='<span style="color:green;cursor:pointer">'+(nota+dps)+'</span>';
                        }else{
                            $('promedio-'+id_evaluacion).innerHTML = '<span style="color:red;cursor:pointer">'+(nota+dps)+'</span>';
                        }
                    }else{
                        $('promedio-'+id_evaluacion).innerHTML = '<span style="color:RED;cursor:pointer" title="Error"><b>&notin;rror</b></span>';
                    }
                }else{
                    $('promedio-'+id_evaluacion).innerHTML = '<span style="color:RED;cursor:pointer" title="Error"><b>&notin;rror</b></span>';
                }
            }
            function selectEstudianteAntiguo(id_estudiante,id_familia,id_curso){
                window.location='<c:url value="/inscripcionRezagada.do"/>?id_estudiante='+id_estudiante+'&id_familia='+id_familia+'&id_curso='+id_curso+'&id_gestion=${id_gestion}&estNuevo=_estNuevo';
            }
            var cid_curso = new Array();
            var cmonto_i=new Array();
            <c:forEach varStatus="i" var="cuo" items="${tiposPensiones}">cid_curso[${i.index}]='${cuo.id_curso}';cmonto_i[${i.index}]='${cuo.cuota_inicial}';</c:forEach>
                var cmonto_a=new Array();
            <c:forEach varStatus="i" var="cuo" items="${tiposPensiones}">cmonto_a[${i.index}]='${cuo.cuota_total}';</c:forEach>

                function selectCurso(id_curso){
                    for(i=0;i<cid_curso.length;i++){
                        if(cid_curso[i]==id_curso){
                            var id_cuota=$('unico_id_cuota').value;
                            $('monto_by_cuota').innerHTML = '<br><span style="color:navy;cursor:pointer"><b>'+Math.round((parseInt(cmonto_a[i])/parseInt(id_cuota))*100)/100+' Bs.</b></span>';
                            $('monto_total').innerHTML = '<br><span style="color:navy;cursor:pointer"><b>'+(parseInt(cmonto_i[i])+parseInt(cmonto_a[i]))+' Bs.</b></span>';
                            $('monto_inicial').innerHTML = '<br><span style="color:navy;cursor:pointer"><b>'+parseInt(cmonto_i[i])+' Bs.</b></span>';
                            $('monto_anual').innerHTML = '<br><span style="color:navy;cursor:pointer"><b>'+parseInt(cmonto_a[i])+' Bs.</b></span>';
                        }
                    }
                }

                function selectCuota(cuota){
                    var id_curso=$('unico_id_curso').value;
                    if(cuota==1){
                        $('item-meses').innerHTML = '<table width="100%" cellpadding="0" cellspacing="0"  style="color:gray"><tr><td><img src="imagenes/cxc.png">Febrero</td></tr></table>';
                    }
                    if(cuota==3){
                        $('item-meses').innerHTML = '<table width="100%" cellpadding="0" cellspacing="0"  style="color:gray"><tr><td><img src="imagenes/cxc.png">Abril</td></tr>\n\
                         <tr><td><img src="imagenes/cxc.png">Julio</td></tr><tr><td><img src="imagenes/cxc.png">Octubre</td></tr></table>';
                    }
                    if(cuota==9){
                        $('item-meses').innerHTML = '<table width="100%" cellpadding="0" cellspacing="0"  style="color:gray">\n\
                        <tr><td><img src="imagenes/cxc.png">Marzo</td></tr>\n\
                        <tr><td><img src="imagenes/cxc.png">Abril</td></tr><tr><td><img src="imagenes/cxc.png">Mayo</td></tr>\n\
                        <tr><td><img src="imagenes/cxc.png">Junio</td></tr><tr><td><img src="imagenes/cxc.png">Julio</td></tr>\n\
                        <tr><td><img src="imagenes/cxc.png">Agosto</td></tr><tr><td><img src="imagenes/cxc.png">Septiembre</td></tr>\n\
                        <tr><td><img src="imagenes/cxc.png">Octubre</td></tr><tr><td><img src="imagenes/cxc.png">Noviembre</td></tr></table>';
                    }
                    if(cuota==10){
                        $('item-meses').innerHTML = '<table width="100%" cellpadding="0" cellspacing="0" style="color:gray">\n\
                        <tr><td><img src="imagenes/cxc.png">Febrero</td></tr><tr><td><img src="imagenes/cxc.png">Marzo</td></tr>\n\
                        <tr><td><img src="imagenes/cxc.png">Abril</td></tr><tr><td><img src="imagenes/cxc.png">Mayo</td></tr>\n\
                        <tr><td><img src="imagenes/cxc.png">Junio</td></tr><tr><td><img src="imagenes/cxc.png">Julio</td></tr>\n\
                        <tr><td><img src="imagenes/cxc.png">Agosto</td></tr><tr><td><img src="imagenes/cxc.png">Septiembre</td></tr>\n\
                        <tr><td><img src="imagenes/cxc.png">Octubre</td></tr><tr><td><img src="imagenes/cxc.png">Noviembre</td></tr></table>';
                    }

                    for(i=0;i<cid_curso.length;i++){
                        if(cid_curso[i]==id_curso){
                            $('monto_by_cuota').innerHTML = '<br><span style="color:navy;cursor:pointer"><b>'+Math.round((parseInt(cmonto_a[i])/parseInt(cuota))*100)/100+' Bs.</b></span>';
                            $('monto_total').innerHTML = '<br><span style="color:navy;cursor:pointer"><b>'+Math.round(((parseInt(cmonto_i[i])+parseInt(cmonto_a[i])))*100)/100+' Bs.</b></span>';
                            $('monto_inicial').innerHTML = '<br><span style="color:navy;cursor:pointer"><b>'+Math.round((parseInt(cmonto_i[i]))*100)/100+' Bs.</b></span>';
                            $('monto_anual').innerHTML = '<br><span style="color:navy;cursor:pointer"><b>'+Math.round((parseInt(cmonto_a[i]))*100)/100+' Bs.</b></span>';
                        }
                    }
                }

                function selectCheck(id_servicio){
                    if($('serv-'+id_servicio).checked==true){
                        $('visible-'+id_servicio).style.visibility = 'visible';
                        $('estado-'+id_servicio).innerHTML ='<span style="color:red;cursor:pointer">Pendiente</span>';
                    }else{
                        $('visible-'+id_servicio).style.visibility = 'hidden';
                        $('estado-'+id_servicio).innerHTML ="";
                    }
                }
                function selectCheckCancelar(id_servicio){
                    if($('cancelar-'+id_servicio).checked==true){
                        $('estado-'+id_servicio).innerHTML ='<span style="color:green;cursor:pointer">Cancelado</span>';
                    }else{
                        $('estado-'+id_servicio).innerHTML ='<span style="color:red;cursor:pointer">Pendiente</span>';
                    }
                }
        </script>

    </head>
    <body>
        <div class="headercontent">
            <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                <tr>
                    <td class="tab_init">&nbsp;</td>
                    <td style="width:80%">
                        <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                            <tr>
                                <td class="tab_normal" onclick="javascript:window.location='<c:url value="/inscripcion.do"/>'">Inscripciones</td>
                                <td class="tab_normal" onclick="javascript:window.location='<c:url value="/asignacionPagos.do"/>'">Asignaci&oacute;n de pagos</td>
                                <td class="tab_normal" onclick="javascript:window.location='<c:url value="/depositosBancarios.do"/>'">Adm. dep&oacute;sitos bancarios</td>
                                <td class="tab_normal" onclick="javascript:window.location='<c:url value="/historialAcademico.do"/>'">Historial Acad&eacute;mico</td>
                                <td class="tab_current" style="border-right:1px solid #7D909E">Inscripciones rezagadas</td>
                            </tr>
                        </table>
                    </td>
                    <td class="tab_fin">&nbsp;</td>
                </tr>
            </table>
            <div class="titlepage">INSCRIPCIONES REZAGADAS ${id_gestion}</div>
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
            <c:if test="${!empty gestion}">
                <table cellpadding="0" cellspacing="0" border="0" style="width:100%"> 
                    <tr>
                        <td class="tableHeader">Gestiones acad&eacute;micas</td>
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
                                    <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)" <c:if test="${item.estado==true}">onclick="selectGestion('${item.id_gestion}')"</c:if>>
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
            <c:if test="${!empty id_gestion}">
                <table>
                    <form action="<c:url value="/inscripcionRezagada.do"/>" method="post">
                        <td class="text-label">Buscar familia</td>
                        <td>
                            <input type="text" name="search" value="${search}" class="text-field">
                            <button class="button-normal" type="submit"><img width="11px" src="imagenes/iconos_sigaa/buscar.png">&nbsp;&nbsp;Buscar</button>
                            <input type="hidden" name="id_gestion" value="${id_gestion}">
                        </td>
                    </form>
                    <form action="<c:url value="/inscripcionRezagada.do"/>" method="post">                        
                        <td colspan="2">
                            <input type="hidden" name="regNuevo" value="_regNuevo">
                            <input type="hidden" name="id_gestion" value="${id_gestion}">
                            <button class="button-normal" type="submit"><img width="11px" src="imagenes/iconos_sigaa/nuevo.png">&nbsp;&nbsp;Nuevo registro</button>
                        </td>
                    </form>
                </table>
                <c:if test="${!empty busq}">
                    <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                        <tr>
                            <td class="tableHeader"> Tutores de estudiantes</td>
                        </tr>
                        <tr>
                            <td class="gridContent">
                                <div id="resultlayer1">
                                    <table width="100%" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF" class="menuHead">
                                        <tr>
                                            <td class="gridHeaders" style="width:5%">Nro.</td>
                                            <td class="gridHeaders" style="width:45%">Tutores</td>
                                            <td class="gridHeaders" style="width:50%">Estudiantes y cursos (Gesti&oacute;n ${id_gestion})</td>
                                        </tr>
                                        <c:if test="${empty listafamilia}">
                                            <tr>
                                                <td class="gridData" colspan="3">No se han encontrado familias con la caracteristica <strong>${search}</strong>.</td>
                                            </tr>
                                        </c:if>
                                        <c:forEach varStatus="i" var="item" items="${listafamilia}">
                                            <tr onclick="selectFamilia('<c:out value="${item.id_familia}"/>')" bgcolor="#FFFFFF" style="cursor: pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                                <td class="gridData" valign="top"><c:out value="${i.count}"/> </td>
                                                <td class="gridData">
                                                    <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                                                        <tr>
                                                            <td width="15%"><strong>Tutor 1 :</strong></td>
                                                            <td width="85%" style="color:navy"><strong>${item.nombre_apellidos_tutor_1} <c:if test="${item.ci_tutor_1==item.ci_resp_pago}"><font style="color:red">(Resp. pago)</font></c:if></strong></td>
                                                        </tr>
                                                        <tr>
                                                            <td><strong>Tutor 2 :</strong></td>
                                                            <td style="color:navy"><strong>${item.nombre_apellidos_tutor_2} <c:if test="${item.ci_tutor_2==item.ci_resp_pago}"><font style="color:red">(Resp. pago)</font></c:if></strong></td>
                                                        </tr>
                                                        <tr>
                                                            <td><strong>Tutor 3 :</strong></td>
                                                            <td style="color:navy"><strong>${item.nombre_apellidos_tutor_3} <c:if test="${item.ci_tutor_3==item.ci_resp_pago}"><font style="color:red">(Resp. pago)</font></c:if></strong></td>
                                                        </tr>
                                                    </table>
                                                </td>
                                                <td class="gridData" valign="top">
                                                    <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                                                        <c:forEach varStatus="i" var="it" items="${item.estudiantes}">
                                                            <tr>
                                                                <td style="color:navy">
                                                                    ${i.count} .- ${it.nombres} ${it.paterno} ${it.materno} (
                                                                    <c:if test="${it.id_estado=='A'}">
                                                                        <span style="color: green">Activo</span>
                                                                    </c:if>
                                                                    <c:if test="${it.id_estado=='R'}">
                                                                        <span style="color: red">Retirado</span>
                                                                    </c:if>
                                                                    <c:if test="${it.id_estado=='NI'}">
                                                                        <span style="color: orange">No incorporado</span>
                                                                    </c:if>) 
                                                                    <span style="color: teal">(${it.curso} de ${it.ciclo})</span>
                                                                </td>
                                                            </tr>
                                                        </c:forEach>
                                                    </table>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </div>
                            </td>
                        </tr>
                    </table>
                </c:if>
                <c:if test="${!empty registroNuevo}">
                    <p>Llena todos los campos tomando en cuenta los marcados con (<img src="imagenes/icons/required.gif"/> ) que
                        corresponde a datos obligatorios.</p>
                    <form action="<c:url value="/inscripcionRezagada.do"/>" method="post">
                        <table cellpadding="0" cellspacing="0" border="0" style="width:99%" align="center" class="gridContent">
                            <tr>
                                <td class="tableHeader" colspan="6">Registro &uacute;nico del tutor/es del estudiante</td>
                            </tr>
                            <tr>
                                <td width="15%"> TUTOR 1 <img src="imagenes/icons/required.gif"/> :</td>
                                <td width="15%" align="center"><input class="text-field" type="text" name="nombres1" value="${nombres1}" maxlength="250" style="width:120px"/></td>
                                <td width="15%" align="center"><input class="text-field" type="text" name="paterno1" value="${paterno1}" maxlength="250" style="width:120px"/></td>
                                <td width="15%" align="center"><input class="text-field" type="text" name="materno1" value="${materno1}" maxlength="250" style="width:120px"/></td>
                                <td width="15%" align="center"><input class="text-field" type="text" name="ci1" value="${ci1}" maxlength="250" style="width:100px"/></td>
                                <td width="25%" align="center" style="color:gray"><input type="radio" name="resp" value="tutor1">&nbsp;Responsable de pagos</td>
                            </tr>
                            <tr>
                                <td></td>
                                <td align="center" style="color:gray">Nombre</td>
                                <td align="center" style="color:gray">Apellido Paterno</td>
                                <td align="center" style="color:gray">Apellido Materno</td>
                                <td align="center" style="color:gray">c.i.</td>
                                <td></td> 
                            </tr>
                            <tr>
                                <td> TUTOR 2 : </td>
                                <td align="center"><input class="text-field" type="text" name="nombres2" value="${nombres2}" maxlength="250" style="width:120px"/></td>
                                <td align="center"><input class="text-field" type="text" name="paterno2" value="${paterno2}" maxlength="250" style="width:120px"/></td>
                                <td align="center"><input class="text-field" type="text" name="materno2" value="${materno2}" maxlength="250" style="width:120px"/></td>
                                <td align="center"><input class="text-field" type="text" name="ci2" value="${ci2}" maxlength="250" style="width:100px"/></td>
                                <td align="center" style="color:gray"><input type="radio" name="resp" value="tutor2">&nbsp;Responsable de pagos</td>
                            </tr>
                            <tr>
                                <td></td>
                                <td align="center" style="color:gray">Nombre</td>
                                <td align="center" style="color:gray">Apellido Paterno</td>
                                <td align="center" style="color:gray">Apellido Materno</td>
                                <td align="center" style="color:gray">c.i.</td>
                                <td></td>
                            </tr>
                            <tr>
                                <td> TUTOR 3 : </td>
                                <td align="center"><input class="text-field" type="text" name="nombres3" value="${nombres3}" maxlength="250" style="width:120px"/></td>
                                <td align="center"><input class="text-field" type="text" name="paterno3" value="${paterno3}" maxlength="250" style="width:120px"/></td>
                                <td align="center"><input class="text-field" type="text" name="materno3" value="${materno3}" maxlength="250" style="width:120px"/></td>
                                <td align="center"><input class="text-field" type="text" name="ci3" value="${ci3}" maxlength="250" style="width:100px"/></td>
                                <td align="center" style="color:gray"><input type="radio" name="resp" value="tutor3">&nbsp;Responsable de pagos</td>
                            </tr>
                            <tr>
                                <td></td>
                                <td align="center" style="color:gray">Nombre</td>
                                <td align="center" style="color:gray">Apellido Paterno</td>
                                <td align="center" style="color:gray">Apellido Materno</td>
                                <td align="center" style="color:gray">c.i.</td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>DIRECCI&Oacute;N (1) <img src="imagenes/icons/required.gif"/> :</td>
                                <td colspan="4" ><input class="text-field" type="text" name="dir1" value="${dir1}" maxlength="500" style="width:500px"/></td>
                                <td>TELEFONO (1) <img src="imagenes/icons/required.gif"/> : <input class="text-field" type="text" name="tf1" value="${tf1}" maxlength="25" style="width:100px"/></td>
                            </tr>
                            <tr>
                                <td>DIRECCI&Oacute;N (2) : </td>
                                <td colspan="4" ><input class="text-field" type="text" name="dir2" value="${dir2}" maxlength="500" style="width:500px"/></td>
                                <td>TELEFONO (2) :&nbsp;&nbsp;&nbsp;&nbsp;<input class="text-field" type="text" name="tf2" value="${tf2}" maxlength="25" style="width:100px"/></td>
                            </tr>
                            <tr>
                                <td>OTROS TELEFONOS : </td>
                                <td colspan="5" ><input class="text-field" type="text" name="telefonos" value="${telefonos}" maxlength="500" style="width:500px"/></td>
                            </tr>
                            <tr>
                                <td>E-MAIL TUTOR : </td>
                                <td colspan="5" ><input class="text-field" type="text" name="e_mail" value="${e_mail}" maxlength="300" style="width:500px"/></td>
                            </tr>
                            <tr>
                                <td>E-MAIL RESP. PAGO : </td>
                                <td colspan="5" ><input class="text-field" type="text" name="e_mailRP" value="${e_mailRP}" maxlength="300" style="width:500px"/></td>
                            </tr>
                            <tr>
                                <td valign="top">OBSERVACIONES : </td>
                                <td colspan="5" ><textarea name="observacion" class="text-field" style="width:500px">${observacion}</textarea></td>
                            </tr>
                            <tr>
                                <td colspan="6" align="center">
                                    <button class="button-normal" type="submit"><img width="11px" src="imagenes/iconos_sigaa/guardar.png">&nbsp;&nbsp;Guardar</button>
                                    <input type="hidden" name="guardarNuevo" value="_regNuevos">
                                    <input type="hidden" name="id_gestion" value="${id_gestion}">
                                </td>
                            </tr>
                        </table>
                    </form>
                </c:if>
                <c:if test="${!empty resultados}">
                    <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                        <tr>
                            <td align="center" colspan="5" class="tableHeader">INFORMACI&Oacute;N DE LA FAMILIA</td>
                        </tr>
                        <tr>
                            <td width="50%"rowspan="7" valign="top" class="gridContent">
                                <table cellpadding="0" cellspacing="0" border="0" style="width:100%" class="menuHead">
                                    <tr>
                                        <td colspan="4" class="tableHeader">Tutor o tutores</td>
                                    </tr>
                                    <tr bgcolor="#ffffff" style="cursor:pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                        <td width="10%">C&Oacute;D. FAMILIA : </td>
                                        <td width="30%" class="textColor">${family.id_familia}</td>
                                        <td width="3%"></td>
                                        <td width="7%"></td>
                                    </tr>
                                    <tr bgcolor="#ffffff" style="cursor:pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                        <td>Tutor (1) :</td>
                                        <td class="textColor">${family.nombre_apellidos_tutor_1} <c:if test="${family.ci_tutor_1==family.ci_resp_pago}"><font style="color:red">(Resp. pago)</font></c:if></td>
                                        <td>C.I. :</td>
                                        <td  class="textColor">${family.ci_tutor_1}</td>
                                    </tr>
                                    <tr bgcolor="#ffffff" style="cursor:pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                        <td>Tutor (2) :</td>
                                        <td class="textColor">${family.nombre_apellidos_tutor_2} <c:if test="${family.ci_tutor_2==family.ci_resp_pago}"><font style="color:red">(Resp. pago)</font></c:if></td>
                                        <td>C.I. :</td>
                                        <td class="textColor">${family.ci_tutor_2}</td>
                                    </tr>
                                    <tr bgcolor="#ffffff" style="cursor:pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                        <td>Tutor (3) :</td>
                                        <td class="textColor">${family.nombre_apellidos_tutor_3} <c:if test="${family.ci_tutor_3==family.ci_resp_pago}"><font style="color:red">(Resp. pago)</font></c:if></td>
                                        <td>C.I. :</td>
                                        <td class="textColor">${family.ci_tutor_3}</td>
                                    </tr>
                                    <tr bgcolor="#ffffff" style="cursor:pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                        <td>Direcci&oacute;n (1) :</td>
                                        <td class="textColor">${family.direccion_1}</td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr bgcolor="#ffffff" style="cursor:pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                        <td>Direcci&oacute;n (2) :</td>
                                        <td class="textColor">${family.direccion_2}</td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr bgcolor="#ffffff" style="cursor:pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                        <td>Tel&eacute;fono (1) :</td>
                                        <td class="textColor">${family.telefono_1}</td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr bgcolor="#ffffff" style="cursor:pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                        <td>Tel&eacute;fono (2) :</td>
                                        <td class="textColor">${family.telefono_2}</td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr bgcolor="#ffffff" style="cursor:pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                        <td>Otros telefonos :</td>
                                        <td class="textColor" colspan="3">${family.telefonos}</td>
                                    </tr>
                                    <tr bgcolor="#ffffff" style="cursor:pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                        <td>E-mail tutor : </td>
                                        <td class="textColor" colspan="3"><a href="mailto:${family.e_mail}">${family.e_mail}</a></td>
                                    </tr>
                                    <tr bgcolor="#ffffff" style="cursor:pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                        <td>E-mail resp. pago : </td>
                                        <td class="textColor" colspan="3"><a href="mailto:${family.e_mailRP}">${family.e_mailRP}</a></td>
                                    </tr>
                                    <tr bgcolor="#ffffff" style="cursor:pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                        <td>Observaci&oacute;n :</td>
                                        <td class="textColor" colspan="3">${family.observacion}</td>
                                    </tr>
                                </table>
                            </td> 
                            <td width="50%"rowspan="7" valign="top" class="gridContent">
                                <table cellpadding="0" cellspacing="0" border="0" style="width:100%" class="menuHead">
                                    <tr>
                                        <td colspan="3" class="tableHeader" style="color:orange">Estudiantes Inscritos (Gesti&oacute;n ${id_gestion-1})</td>
                                    </tr>
                                    <tr>
                                        <td class="gridHeaders" width="15%">Nro. HIJO</td>
                                        <td class="gridHeaders" width="60%">NOMBRES Y APELLIDOS</td>
                                        <td class="gridHeaders" width="25%">NIVEL</td>
                                    </tr>
                                    <c:if test="${empty _alumnos}">
                                        <tr>
                                            <td class="gridData" colspan="3" align="center" style="color:red">No se realizo ninguna inscripci&oacute;n.</td>
                                        </tr>
                                    </c:if>
                                    <c:forEach varStatus="i" var="item" items="${_alumnos}">
                                        <tr onclick="selectEstudianteAntiguo('${item.id_estudiante}','${item.id_familia}','${item.id_curso}')" bgcolor="#ffffff" style="cursor:pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                            <td class="gridData" align="center">${item.nro_hijo}</td>
                                            <td class="gridData"><c:out value="${item.nombres} ${item.paterno} ${item.materno}"/></td>
                                            <td class="gridData">${item.curso}</td>
                                        </tr>
                                    </c:forEach>
                                    <tr>
                                        <td colspan="3" class="tableHeader" style="color:silver">Estudiantes Inscritos (Gesti&oacute;n ${id_gestion})</td>
                                    </tr>
                                    <tr>
                                        <td class="gridHeaders" width="15%">Nro. HIJO</td>
                                        <td class="gridHeaders" width="60%">NOMBRES Y APELLIDOS</td>
                                        <td class="gridHeaders" width="25%">NIVEL</td>
                                    </tr>
                                    <c:if test="${empty alumnos}">
                                        <tr>
                                            <td class="gridData" colspan="3" align="center" style="color:red">No se realizo ninguna inscripci&oacute;n.</td>
                                        </tr>
                                    </c:if>
                                    <c:forEach varStatus="i" var="item" items="${alumnos}">
                                        <tr  bgcolor="#ffffff" onclick="selectEstudiante('${item.id_estudiante}','${item.id_familia}','${item.id_curso}')"  style="cursor:pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                            <td class="gridData" align="center">${item.nro_hijo}</td>
                                            <td class="gridData">${item.nombres} ${item.paterno} ${item.materno}</td>
                                            <td class="gridData">${item.curso}</td>
                                        </tr>
                                    </c:forEach>
                                    <!--form action="<c :url value="/inscripcionRezagada.do"/>" method="post">
                                        <input type="hidden" name="id_familia" value=${family.id_familia}>
                                        <input type="hidden" name="id_gestion" value=${id_gestion}>
                                        <input type="hidden" name="estNuevo" value="_estNuevo">
                                        <td colspan="3" align="center">
                                            <button class="button-normal" type="submit"><img width="11px" src="imagenes/iconos_sigaa/nuevo.png">&nbsp;&nbsp;Nueva inscripci&oacute;n</button>
                                        </td>
                                    </form-->
                                </table>
                            </td>
                        </tr>
                    </table>
                </c:if>
                <c:if test="${!empty ins}">
                    <form action="<c:url value="/inscripcionRezagada.do"/>" method="post">
                        <table border="1" cellpadding="0" cellspacing="0" width="100%" align="center">
                            <tr>
                                <td>
                                    <table border="0" cellpadding="0" cellspacing="0" width="100%" class="gridContent" align="center">
                                        <tr>
                                            <td colspan="6" class="tableHeader" align="center">INSCRIPCI&Oacute;N DEL ESTUDIANTE</td>
                                        </tr> 
                                        <tr>
                                            <td colspan="6">
                                                <b>Tramite de inscripciones</b>, llenar cuidadosamente todos los campos
                                                tomando muy en cuenta los marcados con ( <img src="imagenes/icons/required.gif"/> ) que
                                                corresponde a datos obligatorios. Recuerde que la inscripci&oacute;n se realiza una sola vez al a&ntilde;o.
                                            </td>
                                        </tr>
                                        <tr>
                                            <td align="center"colspan="6"><p><strong>INSCRIPCIONES</strong></p></td>
                                        </tr>
                                        <tr>
                                            <td colspan="1">C&Oacute;DIGO<img src="imagenes/icons/required.gif"/></td>
                                            <td colspan="1">:</td>
                                            <td colspan="4"><input class="text-field" type="text" name="codigo" value="${estudiante.codigo}" maxlength="5" style="width:50px"/></td>
                                        </tr>
                                        <tr>
                                            <td colspan="1">NOMBRE(S)<img src="imagenes/icons/required.gif"/></td>
                                            <td colspan="1">:</td>
                                            <td colspan="4"><input class="text-field" type="text" name="nombres" value="${estudiante.nombres}" maxlength="70" style="width:250px"/></td>
                                        </tr>
                                        <tr>
                                            <td colspan="1">APELLIDO PATERNO<img src="imagenes/icons/required.gif"/></td>
                                            <td colspan="1">:</td>
                                            <td colspan="4"><input class="text-field" type="text" name="paterno" value="${estudiante.paterno}" maxlength="70" style="width:250px"/></td>
                                        </tr>
                                        <tr>
                                            <td colspan="1">APELLIDO MATERNO<img src="imagenes/icons/required.gif"/></td>
                                            <td colspan="1">:</td>
                                            <td colspan="4"><input class="text-field" type="text" name="materno" value="${estudiante.materno}" maxlength="70" style="width:250px"/></td>
                                        </tr>
                                        <tr>
                                            <td width="23%">SEXO</td>
                                            <td width="2%">:</td>
                                            <td colspan="4">
                                                <select name="id_sexo" class="text-field" style="width:120px">
                                                    <c:forEach var="item" items="${tipos_sexos}">
                                                        <option value="${item.id}"<c:if test="${item.id == estudiante.id_sexo  }"> selected </c:if>> ${item.valor}
                                                        </c:forEach>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>FECHA DE NACIMIENTO<img src="imagenes/icons/required.gif"/></td>
                                            <td>:</td>
                                            <td valign="top" colspan="4">
                                                <select name="dia" class="text-field" style="width:40px">
                                                    <option>
                                                        <c:forEach begin="1" end="31" varStatus="i">
                                                        <option value="${i.count}" <c:if test="${i.count == estudiante.fecha_nacimiento.date}"> selected </c:if>>${i.count}
                                                        </c:forEach>
                                                </select>
                                                <select name="mes" class="text-field" style="width:105px">
                                                    <option>
                                                        <c:forEach var="item" items="${tipo_meses}">
                                                        <option value="${item.id}" <c:if test="${item.id == estudiante.fecha_nacimiento.month+1}"> selected </c:if>>${item.valor}
                                                        </c:forEach>
                                                </select>
                                                <select name="anio" class="text-field" style="width:55px">
                                                    <option>
                                                        <c:forEach begin="1950" end="2020" varStatus="i">
                                                        <option value="${i.index}" <c:if test="${i.index ==estudiante.fecha_nacimiento.year+1900}"> selected </c:if>>${i.index}
                                                        </c:forEach>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>CURSO</td>
                                            <td>:</td>
                                            <td colspan="4">
                                                <select id="unico_id_curso" name="id_curso" class="text-field" style="width:250px" onchange="selectCurso(this.value)">
                                                    <c:forEach var="item" items="${cursos}">
                                                        <option value="${item.id_curso}"<c:if test="${item.id_curso == id_curso}"> selected </c:if>> ${item.curso} ${item.ciclo}
                                                        </c:forEach>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td width="23%">G&Oacute;NDOLA</td>
                                            <td width="2%">:</td>
                                            <td colspan="4">
                                                <select name="id_gondola" class="text-field" style="width:500px" >
                                                    <option value="">Ninguno...
                                                        <c:forEach var="item" items="${gondolas}">
                                                        <option value="${item.placa}">${item.empresa}, Nro. ${item.nro_gondola} y Ruta(${item.ruta})
                                                        </c:forEach>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="1">GESTI&Oacute;N</td>
                                            <td colspan="1">:</td>
                                            <td colspan="4">
                                                <input class="text-field" type="text" name="id_gestion" value="${id_gestion}" maxlength="5" style="width:120px" onfocus="this.blur()"/>
                                                <input type="hidden" name="id_estudiante_ant" value="${estudiante.id_estudiante}">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="1" valign="top">PENSIONES</td>
                                            <td colspan="1" valign="top">:</td>
                                            <td colspan="4" valign="top">Seleccionar cuota :
                                                <select id="unico_id_cuota" name="cuota" class="text-field" style="width:100px" onchange="selectCuota(this.value,'${id_curso}')" <c:if test="${pagoPension.editable==false}"> onfocus="this.blur()" </c:if>>
                                                    <c:forEach var="it" items="${cuotas}">
                                                        <option value="${it.id}"<c:if test="${it.id== pagoPension.cuota}"> selected </c:if>/><c:if test="${it.id==1}"> ${it.id} Cuota </c:if><c:if test="${it.id>1}"> ${it.id} Cuotas </c:if>
                                                    </c:forEach>
                                                </select>
                                                <table border="0" cellpadding="0" cellspacing="0" width="100%"  align="center" class="menuHead" style="cursor:pointer">
                                                    <tr>
                                                        <td class="gridHeaders" width="25%"<strong>Mes/es de pago</strong></td>
                                                        <td class="gridHeaders" width="30%"><strong>Montos a cancelar</strong></td>
                                                        <td class="gridHeaders" width="30%"><strong>Concepto de pago</strong></td>
                                                        <td class="gridHeaders" width="15%"><strong>Monto total</strong></td>
                                                    </tr>
                                                    <tr bgcolor="#ffffff">
                                                        <td class="gridData" style="color:gray">
                                                            <img src="imagenes/cxc.png"> FEBRERO
                                                        </td>
                                                        <td class="gridData" align="center" valign="middle"><div id="monto_inicial"/></td>
                                                        <td class="gridData" style="color:navy"  valign="middle"> Mensualidad (Cuota Inicial)</td>
                                                        <td class="gridData" align="center" valign="middle" rowspan="2"><div id="monto_total"/></td>
                                                    </tr>
                                                    <tr bgcolor="#ffffff">
                                                        <td class="gridData" align="center">
                                                            <div id="item-meses"></div>
                                                        </td>
                                                        <td class="gridData" valign="middle">
                                                            <div id="monto_by_cuota"></div>
                                                            <div id="monto_anual"></div>
                                                        </td>
                                                        <td class="gridData" style="color:navy" valign="middle">Mensualidad/es</td>
                                                    </tr>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="6"><img src="imagenes/pixel_trans.gif" height="10"/></td>
                                        </tr>
                                        <tr>
                                            <td colspan="1" valign="top">SERVICIOS</td>
                                            <td colspan="1" valign="top">:</td>
                                            <td colspan="4" valign="top">
                                                <table width="100%" border="0" style="color:navy">
                                                    <tr>
                                                        <td class="gridHeaders" width="10%"><strong>Nro.</strong></td>
                                                        <td class="gridHeaders" width="40%"><strong>Servicio</strong></td>
                                                        <td class="gridHeaders" width="15%"><strong>Monto</strong></td>
                                                        <td class="gridHeaders" width="10%"><strong>Asignar</strong></td>
                                                        <td class="gridHeaders" width="10%"><strong>Cancelar</strong></td>
                                                        <td class="gridHeaders" width="15%"><strong>Estado</strong></td>
                                                    </tr>
                                                    <c:if test="${empty servicios}">
                                                        <tr>
                                                            <td colspan="6">No se han encontrado elementos.</td>
                                                        </tr>
                                                    </c:if>
                                                    <c:forEach varStatus="j" var="items" items="${servicios}">
                                                        <tr style="cursor:pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)" bgcolor="#ffffff">
                                                            <td align="center">${j.count}</td>
                                                            <td><img src="imagenes/cxc.png">&nbsp;&nbsp;${items.tipo_servicio}</td>
                                                            <td align="center"><strong>${items.monto_servicio} Bs.</strong></td>
                                                            <td align="center"><input type="checkbox" onclick="selectCheck('${items.id_servicio}')" id="serv-${items.id_servicio}" name="serv-${items.id_servicio}"></td>
                                                            <td align="center"><div id="visible-${items.id_servicio}" style="visibility:hidden"><input type="checkbox" onclick="selectCheckCancelar('${items.id_servicio}')" id="cancelar-${items.id_servicio}" name="cancel-${items.id_servicio}"></div></td>
                                                            <td align="center" id="estado-${items.id_servicio}"></td>
                                                        </tr>
                                                    </c:forEach>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="6"><img src="imagenes/pixel_trans.gif" height="10"/></td>
                                        </tr>
                                        <tr>
                                            <td colspan="6" align="center">
                                                <input type="hidden" name="ci" value="${family.ci_resp_pago}">
                                                <input type="hidden" name="id_familia" value="${family.id_familia}">
                                                <input type="hidden" name="regEst" value="_regEst">
                                                <button class="button-normal">Siguiente...<img width="20px" src="imagenes/iconos_sigaa/siguiente.png"></button>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <script type="text/javascript">selectCuota('1')</script>
                        </table>
                    </form>
                </c:if>

                <c:if test="${!empty calificaciones}">
                    <form action="<c:url value="/inscripcionRezagada.do"/>" method="post">
                        <table border="0" cellpadding="0" cellspacing="0" width="100%" class="gridContent" style="cursor:pointer">
                            <tr>
                                <td colspan="3" class="tableHeader" align="center">REGISTRO DE CALIFICACIONES, CONDUCTAS Y DIAGN&Oacute;STICOS</td>
                            </tr>
                            <tr>
                                <td colspan="3">
                                    <c:forEach varStatus="i" var="item" items="${alumnos}">
                                        <c:if test="${item.id_estudiante==id_estudiante}">
                                            <strong>Estudiante&nbsp;&nbsp;:&nbsp;&nbsp;</strong> ${item.nombres} ${item.paterno} ${item.materno}&nbsp;&nbsp;&nbsp;&nbsp;
                                            <strong>Curso&nbsp;&nbsp;:&nbsp;&nbsp;</strong> ${item.curso}
                                        </c:if> 
                                    </c:forEach>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="3" align="center"><strong>REGISTRO DE CALIFICACIONES</strong></td>
                            </tr>
                            <tr>
                                <td class="gridHeaders" width="3%">Nro</td>
                                <td class="gridHeaders" width="20%">Materia</td>
                                <td class="gridHeaders" width="77%">Evaluaciones</td>
                            </tr>
                            <c:forEach varStatus="i" var="it" items="${cursoMaterias}">
                                <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)" bgcolor="#ffffff">
                                    <td class="gridData" align="center">${i.count}</td>
                                    <td class="gridData"><img src="imagenes/cxc.png">${it.materia}</td>
                                    <td class="gridData">
                                        <table width="100%">
                                            <c:if test="${id_curso=='K'}">
                                                <tr>
                                                    <td class="gridHeaders" width="30%">Trimestre</td>
                                                    <td class="gridHeaders" width="70%" title="Solo para niveles kinder y primaria">Cualitativa</td>
                                                </tr>
                                            </c:if>
                                            <c:if test="${id_curso!='K' && id_curso!='S1'&& id_curso!='S2'&& id_curso!='S3'&& id_curso!='S4'}">
                                                <tr>
                                                    <td class="gridHeaders" width="35%">Trimestre</td>
                                                    <td class="gridHeaders" width="5%" title="Nota sobre 60 Pts">Nota</td>
                                                    <td class="gridHeaders" width="5%" title="Dps sobre 10 Pts">Dps</td>
                                                    <td class="gridHeaders" width="5%" title="Promedio triemstral sobre 70 Pts">%</td>
                                                    <td class="gridHeaders" width="50%" title="Solo para niveles kinder y primaria">Cualitativa</td>
                                                </tr>
                                            </c:if>
                                            <c:if test="${id_curso=='S1'|| id_curso=='S2'|| id_curso=='S3'|| id_curso=='S4'}">
                                                <tr>
                                                    <td class="gridHeaders" width="85%">Trimestre</td>
                                                    <td class="gridHeaders" width="5%" title="Nota sobre 60 Pts">Nota</td>
                                                    <td class="gridHeaders" width="5%" title="Dps sobre 10 Pts">Dps</td>
                                                    <td class="gridHeaders" width="5%" title="Promedio triemstral sobre 70 Pts">%</td>
                                                </tr>
                                            </c:if>                                            
                                            <c:forEach varStatus="j" var="its" items="${it.planEvaluaciones}">
                                                <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)" bgcolor="#ffffff">
                                                    <td>${its.evaluacion}</td>
                                                    <c:if test="${id_curso!='K'}">
                                                        <td><input name="nota-${its.id_evaluacion}" id="nota-${its.id_evaluacion}" onblur="verificaNota('${its.id_evaluacion}')" type="text" style="width:23px;text-align:right" maxlength="2" value="0"></td>
                                                        <td><input name="dps-${its.id_evaluacion}" id="dps-${its.id_evaluacion}" onblur="verificaNota('${its.id_evaluacion}')" type="text" style="width:23px;text-align:right" maxlength="2" value="0"></td>
                                                        <td id="promedio-${its.id_evaluacion}">0</td>
                                                    </c:if>
                                                    <c:if test="${id_curso!='S1'&& id_curso!='S2'&& id_curso!='S3'&& id_curso!='S4'}">
                                                        <td><textarea name="cualitativa-${its.id_evaluacion}" class="text-field" style="width:99%; height:40px; text-align:justify" ></textarea></td>
                                                    </c:if> 
                                                </tr>
                                            </c:forEach>
                                        </table>
                                    </td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td colspan="3" align="center"><strong>REGISTRO DE&nbsp;
                                        <c:if test="${id_curso=='K'}">DIAGN&Oacute;STICOS</c:if>
                                        <c:if test="${id_curso!='K'}">CONDUCTAS</c:if></strong>
                                </td>
                            </tr>
                            <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)" bgcolor="#ffffff">
                                <td colspan="2">CONDUCTAS</td>
                                <td>
                                    <table width="100%">
                                        <c:if test="${id_curso=='K'}">
                                            <tr>
                                                <td class="gridHeaders" width="50%">Diagn&oacute;stico inicial</td>
                                                <td class="gridHeaders" width="50%">Diagn&oacute;stico final</td>
                                            </tr>
                                        </c:if>
                                        <c:if test="${id_curso!='K'}">
                                            <tr>
                                                <td class="gridHeaders" width="30%">1er. Trimestre</td>
                                                <td class="gridHeaders" width="30%">2do. Trimestre</td>
                                                <td class="gridHeaders" width="30%">3er. Trimestre</td>
                                            </tr>
                                        </c:if>
                                        <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)" bgcolor="#ffffff">
                                            <c:forEach varStatus="k" var="ite" items="${conductas}">
                                                <td align="center">
                                                    <c:if test="${id_curso!='K'}">
                                                        <select name="id_tipo_conducta-${ite.id_conducta}" style="width:150px">
                                                            <c:forEach var="it" items="${tipos_conductas}">
                                                                <option value="${it.id}" title="${it.valor}"> ${it.valor}
                                                                </c:forEach>
                                                        </select>
                                                    </c:if>
                                                    <c:if test="${id_curso=='K'}">
                                                        <textarea name="diagnostico-${ite.id_conducta}" class="text-field"  style="width:95%"></textarea>
                                                    </c:if>
                                                </td> 
                                            </c:forEach>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="3" align="center">
                                    <input type="hidden" name="id_familia" value="${family.id_familia}">
                                    <input type="hidden" name="id_gestion" value="${id_gestion}">
                                    <input type="hidden" name="id_curso" value="${id_curso}">
                                    <input type="hidden" name="id_estudiante" value="${id_estudiante}">
                                    <input type="hidden" name="regNotas" value="_regNotas">
                                    <button class="button-normal"><img width="11px" src="imagenes/iconos_sigaa/guardar.png">&nbsp;&nbsp;Guardar</button>
                                </td>
                            </tr>
                        </table>
                    </form>
                </c:if>
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