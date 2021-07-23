<%-- 
    Document   : Inscripcion
    Created on : 29-may-2009, 17:54:22
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
        <link rel="stylesheet" type="text/css" href="css/st.css" media="screen" />
        <script type="text/javascript" src="js/prototype.js"></script>
        <script type="text/javascript">
            animatedcollapse.addDiv('success', 'fade=1');
            animatedcollapse.addDiv('failure', 'fade=1');
            animatedcollapse.init();
        </script>

        <script type="text/javascript">
            function openWindow(form,title,w,h){
                var addFormwin=dhtmlmodal.open('win'+form, 'div', form, title, 'width='+w+'px,height='+h+'px,left=100px,top=100px,resize=1,scrolling=1');
                addFormwin.moveTo('middle', 'middle');
                return addFormwin;
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
                            $('monto_by_cuota').innerHTML = '<br><span style="color:navy;cursor:pointer">* Monto por cuota : <b>'+Math.round((parseInt(cmonto_a[i])/parseInt(id_cuota))*100)/100+' Bs.</b></span>';
                            $('monto_total').innerHTML = '<span style="color:navy;cursor:pointer"><b>'+(parseInt(cmonto_i[i])+parseInt(cmonto_a[i]))+' Bs.</b></span>';
                            $('monto_inicial').innerHTML = '<span style="color:navy;cursor:pointer"><b>'+parseInt(cmonto_i[i])+' Bs.</b></span>';
                            $('monto_anual').innerHTML = '<span style="color:navy;cursor:pointer">* Monto total de cuota/s : <b>'+parseInt(cmonto_a[i])+' Bs.</b></span>';
                        }
                    }
                }

                function selectCuota(cuota){
                    var id_curso=$('unico_id_curso').value;
                    if(cuota==1){
                        $('item-meses').innerHTML = '<table width="100%" cellpadding="0" cellspacing="0" style="color:gray">\n\
                        <tr>\n\
                        <td><img src="imagenes/cxc.png"> FEBRERO</td>\n\
                        </tr>\n\
                        </table>';
                    }
                    if(cuota==3){
                        $('item-meses').innerHTML = '<table width="100%" cellpadding="0" cellspacing="0" style="color:gray">\n\
                        <tr><td><img src="imagenes/cxc.png"> ABRIL</td></tr>\n\
                        <tr><td><img src="imagenes/cxc.png"> JULIO</td></tr>\n\
                        <tr><td><img src="imagenes/cxc.png"> OCTUBRE</td></tr>\n\
                        </table>';
                    }
                    if(cuota==9){
                        $('item-meses').innerHTML = '<table width="100%" cellpadding="0" cellspacing="0" style="color:gray">\n\
                        <tr><td><img src="imagenes/cxc.png"> MARZO</td></tr>\n\
                        <tr><td><img src="imagenes/cxc.png"> ABRIL</td></tr>\n\
                        <tr><td><img src="imagenes/cxc.png"> MAYO</td></tr>\n\
                        <tr><td><img src="imagenes/cxc.png"> JUNIO</td></tr>\n\
                        <tr><td><img src="imagenes/cxc.png"> JULIO</td></tr>\n\
                        <tr><td><img src="imagenes/cxc.png"> AGOSTO</td></tr>\n\
                        <tr><td><img src="imagenes/cxc.png"> SEPTIEMBRE</td></tr>\n\
                        <tr><td><img src="imagenes/cxc.png"> OCTUBRE</td></tr>\n\
                        <tr><td><img src="imagenes/cxc.png"> NOVIEMBRE</td></tr>\n\
                        </table>';
                    }                    
                    for(i=0;i<cid_curso.length;i++){
                        if(cid_curso[i]==id_curso){
                            $('monto_by_cuota').innerHTML = '<br><span style="color:navy;cursor:pointer">* Monto por cuota : <b>'+Math.round((parseInt(cmonto_a[i])/parseInt(cuota))*100)/100+' Bs.</b></span>';
                            $('monto_total').innerHTML = '<span style="color:navy;cursor:pointer"><b>'+Math.round(((parseInt(cmonto_i[i])+parseInt(cmonto_a[i])))*100)/100+' Bs.</b></span>';
                            $('monto_inicial').innerHTML = '<span style="color:navy;cursor:pointer"><b>'+Math.round((parseInt(cmonto_i[i]))*100)/100+' Bs.</b></span>';
                            $('monto_anual').innerHTML = '<span style="color:navy;cursor:pointer">* Monto total de cuota/s : <b>'+Math.round((parseInt(cmonto_a[i]))*100)/100+' Bs.</b></span>';
                        }
                    }
                }

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
                    form.opcion.disabled = true;
                    return true;
                }
                function selectFamilia(id) {
                    window.location='<c:url value="/inscripcion.do"/>?search='+id;
                }

                function setRegistrar(form) {
                    form.guardar.disabled = true;
                    form.guardar.value = 'Enviando los datos...';
                    form.submit();
                    /*si los datos no son correctos */
                    return false
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

                function selectEstudiante(id_estudiante,id_familia,id_curso) {
                    window.location='<c:url value="/inscripcion.do"/>?id_estudiante='+id_estudiante+'&busca='+id_familia+'&id_curso='+id_curso;
                }

                function openWindow(form,title,w,h){
                    var addFormwin=dhtmlmodal.open('win'+form, 'div', form, title, 'width='+w+'px,height='+h+'px,left=100px,top=100px,resize=1,scrolling=1');
                    addFormwin.moveTo('middle', 'middle');
                    return addFormwin;
                }

                function updateFamilia() {
                    openWindow('updateFamily','Actualizar familia','700','400');
                }

                function selectEstudianteModific(id_estudiante,id_familia,id_curso,id_inscripcion){
                    //window.location='<c:url value="/inscripcion.do"/>?id_estudiante='+id_estudiante+'&busca='+id_familia+'&id_curso='+id_curso+'&id_inscripcion='+id_inscripcion;
                }

                var printWin = null;
                function openDocument(id_estudiante,gestion) {
                    printWin = openWindow('documentoPdF','Imprimir...','650','340');
                    document.getElementById('documentview').src = 'documentos/boleta_inscripcion/'+id_estudiante+'&'+gestion+'.pdf';
                    printWin.moveTo('middle', 'middle');
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
                var serviciosR=null;
                function NewsServicios(id_insc,id_fam,nombres,curso){
                    serviciosR=openWindow('selectNewsServicios','Servicios...','600','200');
                    document.getElementById('datos-est').innerHTML ='<table width="100%"style="cursor:pointer">\n\
                    <tr>\n\
                    <td width="15%">Estudiante : </td>\n\
                    <td width="85%"><strong>'+nombres+'</strong></td>\n\
                    </tr>\n\
                    <tr>\n\
                    <td>Curso : </td>\n\
                    <td><strong>'+curso+'</strong></td>\n\
                    </tr>\n\
                    </table>';
                    //document.getElementById('addform').busca.value = id_fam;
                    document.getElementById('addform').id_inscripcion.value = id_insc;

                }
                function setPagarServicio(id_fam,id_insc,id_serv,id_est,id_curso) {
                    var dd=$('dd-'+id_insc+id_serv).value;
                    var mm=$('mm-'+id_insc+id_serv).value;
                    var aaaa=$('aaaa-'+id_insc+id_serv).value;
                    var fec_reg=dd+'-'+mm+'-'+aaaa;
                    window.location='<c:url value="/inscripcion.do"/>?busca='+id_fam+'&id_inscripcion='+id_insc+'&id_estudiante='+id_est+'&id_curso='+id_curso+'&id_servicio='+id_serv+'&fec_reg='+dd+'-'+mm+'-'+aaaa+'&opcion=_update';
                }
                function setDeleteServicio(id_fam,id_insc,id_serv,id_est,id_curso) {
                    window.location='<c:url value="/inscripcion.do"/>?busca='+id_fam+'&id_inscripcion='+id_insc+'&id_estudiante='+id_est+'&id_curso='+id_curso+'&id_servicio='+id_serv+'&opcion=_delete';
                }
                function selectPagoCole(){
                    if($('pagoCole').checked==true){
                        $('item-pagoCole').style.display = 'inline';
                    }else{
                        $('item-pagoCole').style.display = 'none';
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
                                <td class="tab_current" style="border-right:1px solid #7D909E">Inscripciones</td>
                                <td class="tab_normal" onclick="javascript:window.location='<c:url value="/asignacionPagos.do"/>'">Asignaci&oacute;n de pagos</td>
                                <td class="tab_normal" onclick="javascript:window.location='<c:url value="/depositosBancarios.do"/>'">Adm. dep&oacute;sitos bancarios</td>
                                <td class="tab_normal" onclick="javascript:window.location='<c:url value="/historialAcademico.do"/>'">Historial Acad&eacute;mico</td>
                                <td class="tab_normal" onclick="javascript:window.location='<c:url value="/inscripcionRezagada.do"/>'">Inscripciones rezagadas</td>
                            </tr>
                        </table>
                    </td>
                    <td class="tab_fin">&nbsp;</td>
                </tr> 
            </table>
            <div class="titlepage">INSCRIPCI&Oacute;N DE ESTUDIANTES</div>
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

            <c:if test="${empty persona}">
                <table>
                    <form action="<c:url value="/inscripcion.do"/>" method="post" name="frmSeach" onsubmit="return setBuscar(this)">
                        <td class="text-label">Buscar familia</td>
                        <td>
                            <input type="text" name="search1" value="${search}" class="text-field">
                            Familia: <input type="radio" name="tipobus" <c:if test="${sel=='fam'}">checked</c:if> value="fam"/>&nbsp;&nbsp;&nbsp;
                            Estudiante: <input type="radio" name="tipobus" <c:if test="${sel=='est'}">checked</c:if> value="est"/>
                                <button class="button-normal" type="submit"><img width="11px" src="imagenes/iconos_sigaa/buscar.png">&nbsp;&nbsp;Buscar</button>
                            </td>
                        </form>
                        <form action="<c:url value="/inscripcion.do"/>" method="post">
                        <input type="hidden" name="regNuevo" value="_regNuevo">
                        <td>
                            <button class="button-normal" type="submit"><img width="11px" src="imagenes/iconos_sigaa/nuevo.png">&nbsp;&nbsp;Nuevo registro</button>
                        </td>
                    </form>
                </table>
                <c:if test="${empty intro}">
                    <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                        <tr>
                            <td class="tableHeader"> Tutores de los Estudiantes</td>
                        </tr>
                        <tr>
                            <td class="gridContent">
                                <div id="resultlayer1">
                                    <table width="100%" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF" class="menuHead">
                                        <tr>
                                            <td class="gridHeaders" style="width:5%">Nro.</td>
                                            <td class="gridHeaders" style="width:45%">Tutores</td>
                                            <td class="gridHeaders" style="width:50%">Estudiantes y cursos (Gesti&oacute;n ${gestion.id_gestion})</td>
                                        </tr>
                                        <c:if test="${empty listafamilia}">
                                            <tr>
                                                <td class="gridData" colspan="3">No se han encontrado elementos.</td>
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
                    <p>Llena todos los campos tomando en cuenta los marcados con ( <img src="imagenes/icons/required.gif"/> ) que
                        corresponde a datos obligatorios.</p>
                    <form action="<c:url value="/inscripcion.do"/>" method="post">
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
                                <td>TELEFONO (2) :&nbsp;&nbsp;&nbsp;&nbsp;<input class="text-field" type="text" name="tf2" value="${tf2}" maxlength="50" style="width:150px"/></td>
                            </tr>
                            <tr>
                                <td>NOMBRE DE LA FACTURA: </td>
                                <td colspan="5" ><input class="text-field" type="text" name="telefonos" value="${telefonos}" maxlength="400" style="width:400px"/></td>
                            </tr>
                            <tr>
                                <td>NRO. NIT : </td>
                                <td colspan="5" ><input class="text-field" type="text" name="e_mail" value="${e_mail}" maxlength="400" style="width:400px"/></td>
                            </tr>
                            <tr>
                                <td>E-MAIL (Tutor/Resp Pago) : </td>
                                <td colspan="5" ><input class="text-field" type="text" name="e_mailRP" value="${e_mailRP}" maxlength="300" style="width:500px"/></td>
                            </tr>
                            <tr>
                                <td>LUGAR DE TRABAJO (Resp Pago) : </td>
                                <td colspan="5" ><input class="text-field" type="text" name="lugtrab" value="${lugtrab}" maxlength="300" style="width:500px"/></td>
                            </tr>
                            <tr>
                                <td valign="top">OBSERVACIONES : </td>
                                <td colspan="5" ><textarea name="observacion" class="text-field" style="width:500px">${observacion}</textarea></td>
                            </tr>
                            <tr>
                                <td colspan="6" align="center">
                                    <button class="button-normal" type="submit"><img width="11px" src="imagenes/iconos_sigaa/guardar.png">&nbsp;&nbsp;Guardar</button>
                                    <input type="hidden" name="guardarNuevo" value="_regNuevos">
                                </td>
                            </tr>
                        </table>
                    </form>
                </c:if>
                <br/>

                <c:if test="${empty resultados}">
                    <c:if test="${resul=='sin_datos'}">
                        <table cellpadding="0" cellspacing="0" border="0" style="width:100%" class="menuHead">
                            <tr>
                                <td>No se han encontrado elementos...</td>
                            </tr>
                        </table>
                    </c:if>
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
                                        <td>Fatura/Nit : </td>
                                        <td class="textColor" colspan="l3">${family.e_mail}</td>
                                    </tr>
                                    <tr bgcolor="#ffffff" style="cursor:pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                        <td>E-mail Tutot/resp. pago : </td>
                                        <td class="textColor" colspan="3"><a href="mailto:${family.e_mailRP}">${family.e_mailRP}</a></td>
                                    </tr>
                                    <tr bgcolor="#ffffff" style="cursor:pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                        <td>Lugar de trabajo resp. pago : </td>
                                        <td class="textColor" colspan="3">${family.lugtrab}</td>
                                    </tr>
                                    <tr bgcolor="#ffffff" style="cursor:pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                        <td>Observaci&oacute;n :</td>
                                        <td class="textColor" colspan="3">${family.observacion}</td>
                                    </tr>
                                    <tr>
                                        <td colspan="4" align="center">
                                            <button class="button-normal" onclick="updateFamilia()"><img width="11px" src="imagenes/iconos_sigaa/nuevo.png">&nbsp;&nbsp;Editar familia</button>
                                        </td>
                                    </tr>
                                </table>
                            </td> 
                            <td width="50%"rowspan="7" valign="top" class="gridContent">
                                <table cellpadding="0" cellspacing="0" border="0" style="width:100%" class="menuHead">
                                    <tr>
                                        <td colspan="3" class="tableHeader" style="color:orange">Estudiantes Inscritos (Gesti&oacute;n ${anio_actual-1})</td>
                                    </tr>
                                    <tr>
                                        <td class="gridHeaders" width="15%">Nro. HIJO</td>
                                        <td class="gridHeaders" width="60%">NOMBRES Y APELLIDOS</td>
                                        <td class="gridHeaders" width="25%">NIVEL</td>
                                    </tr>
                                    <c:if test="${empty _alumnos}">
                                        <tr>
                                            <td class="gridData" colspan="3" align="center" style="color:red">No se han encontrado elementos.</td>
                                        </tr>
                                    </c:if>
                                    <c:forEach varStatus="i" var="item" items="${_alumnos}">
                                        <tr onclick="selectEstudiante('${item.id_estudiante}','${item.id_familia}','${item.id_curso}')" bgcolor="#ffffff" style="cursor:pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                            <td class="gridData" align="center">${i.count}</td>
                                            <td class="gridData"><c:out value="${item.nombres} ${item.paterno} ${item.materno}"/></td>
                                            <td class="gridData">${item.curso}</td>
                                        </tr>
                                    </c:forEach>
                                    <tr>
                                        <td colspan="3" class="tableHeader" style="color:silver">Estudiantes Inscritos (Gesti&oacute;n ${gestion.id_gestion})</td>
                                    </tr>
                                    <tr>
                                        <td class="gridHeaders" width="15%">Nro. HIJO</td>
                                        <td class="gridHeaders" width="60%">NOMBRES Y APELLIDOS</td>
                                        <td class="gridHeaders" width="25%">NIVEL</td>
                                    </tr>
                                    <c:if test="${empty alumnos}">
                                        <tr>
                                            <td class="gridData" colspan="3" align="center" style="color:red">No se han encontrado elementos.</td>
                                        </tr>
                                    </c:if>
                                    <c:forEach varStatus="i" var="item" items="${alumnos}">
                                        <tr  bgcolor="#ffffff" style="cursor:pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                            <!--td class="gridData" onclick="openDocument('${item.id_estudiante}','${item.id_gestion}')"align="center">${item.nro_hijo} &nbsp;<img src="imagenes/iconos_sigaa/pdf.gif" title="Boleta de Inscripci&oacute;n"></td-->
                                            <td class="gridData" onclick="selectEstudianteModific('${item.id_estudiante}','${item.id_familia}','${item.id_curso}','${item.id_inscripcion}')" align="center">${i.count}</td>
                                            <td class="gridData" onclick="selectEstudianteModific('${item.id_estudiante}','${item.id_familia}','${item.id_curso}','${item.id_inscripcion}')"><a href="rude.do?id=${item.id_estudiante}" target="_blank">RUDE</a><c:out value="${item.nombres} ${item.paterno} ${item.materno}"/></td>
                                            <td class="gridData" onclick="selectEstudianteModific('${item.id_estudiante}','${item.id_familia}','${item.id_curso}','${item.id_inscripcion}')">${item.curso}</td>
                                        </tr>
                                    </c:forEach>
                                    <form action="<c:url value="/inscripcion.do"/>" method="post">
                                        <input type="hidden" name="busca" value=${family.id_familia}>
                                        <input type="hidden" name="estNuevo" value="_estNuevo">
                                        <td colspan="3" align="center">
                                            <button class="button-normal" type="submit"><img width="11px" src="imagenes/iconos_sigaa/nuevo.png">&nbsp;&nbsp;Nueva inscripci&oacute;n</button>
                                        </td>
                                    </form>
                                </table>
                            </td>
                        </tr>
                    </table>
                </c:if>
            </c:if>

            <c:if test="${!empty ins}">
                <div id="formRegistroPersona" class="formPanel">
                    <form action="<c:url value="/inscripcion.do"/>" method="post" name="frmRegistro" onsubmit="return setRegistrar(this)">
                        <table border="0" cellpadding="0" cellspacing="0" width="100%" align="center">
                            <c:if test="${gestion.id_gestion!=anio_actual}">
                                <tr>
                                    <td style="color:red">
                                        No se puede efectuar las inscripciones correspondientes a la gesti&oacute;n <%
                                            java.util.Date now = new java.util.Date();
                                            java.text.SimpleDateFormat fd = new java.text.SimpleDateFormat("yyyy");
                                            out.println(fd.format(now));
                                        %> , revise el cronograma acad&eacute;mico anual para la gesti&oacute;n en curso.
                                    </td>
                                </tr>
                                <tr>
                                    <td align="center"><h2 style="color:red">Verifique Gesti&oacute;n Acad&eacute;mica!...</h2></td>
                                </tr>
                            </c:if>
                            <c:if test="${gestion.id_gestion==anio_actual}">
                                <tr>
                                    <td>
                                        <table border="0" cellpadding="0" cellspacing="0" width="100%" class="gridContent" align="center">
                                            <tr>
                                                <td colspan="6" class="tableHeader" align="center">INSCRIPCI&Oacute;N DEL ESTUDIANTE</td>
                                            </tr>
                                            <tr>
                                                <td colspan="6">
                                                    Llene cuidadosamente todos los campos
                                                    tomando muy en cuenta los marcados con ( <img src="imagenes/icons/required.gif"/> ) que
                                                    corresponde a datos obligatorios.
                                                </td>
                                            </tr>
                                            <tr>
                                                <td align="center"colspan="6"><p><strong>INSCRIPCIONES</strong></p></td>
                                            </tr>
                                            <tr>
                                                <td colspan="1">C&Oacute;DIGO<img src="imagenes/icons/required.gif"/></td>
                                                <td colspan="1">:</td>
                                                <td colspan="4"><input class="text-field" type="text" name="codigo" value="${codigo}" maxlength="5" style="width:50px"/></td>
                                            </tr>
                                            <tr>
                                                <td>C&Eacute;DULA DE IDENTIDAD</td>
                                                <td>:</td>
                                                <td colspan="5"><input class="text-field" type="text" name="carnet" value="${carnet}" maxlength="10" style="width:80px"/><span style="color:red">Ej: 6020032</span></td>
                                            </tr>
                                            <tr>
                                                <td colspan="1">NOMBRE(S)<img src="imagenes/icons/required.gif"/></td>
                                                <td colspan="1">:</td>
                                                <td colspan="4"><input class="text-field" type="text" name="nombres" value="${nombres}" maxlength="70" style="width:250px"/></td>
                                            </tr>
                                            <tr>
                                                <td colspan="1">APELLIDO PATERNO<img src="imagenes/icons/required.gif"/></td>
                                                <td colspan="1">:</td>
                                                <td colspan="4"><input class="text-field" type="text" name="paterno" value="${paterno}" maxlength="70" style="width:250px"/></td>
                                            </tr>
                                            <tr>
                                                <td colspan="1">APELLIDO MATERNO<img src="imagenes/icons/required.gif"/></td>
                                                <td colspan="1">:</td>
                                                <td colspan="4"><input class="text-field" type="text" name="materno" value="${materno}" maxlength="70" style="width:250px"/></td>
                                            </tr>
                                            <tr>
                                                <td width="23%">SEXO</td>
                                                <td width="2%">:</td>
                                                <td colspan="4">
                                                    <select name="id_sexo" class="text-field" style="width:120px">
                                                        <c:forEach var="item" items="${tipos_sexos}">
                                                            <option value="${item.id}"<c:if test="${item.id == id_sexo  }"> selected </c:if>> ${item.valor}
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
                                                            <option value="${i.count}" <c:if test="${i.count == dia  }"> selected </c:if>>${i.count}
                                                            </c:forEach>
                                                    </select>
                                                    <select name="mes" class="text-field" style="width:105px">
                                                        <option>
                                                            <c:forEach var="item" items="${tipo_meses}">
                                                            <option value="${item.id}" <c:if test="${item.id == mes  }"> selected </c:if>>${item.valor}
                                                            </c:forEach>
                                                    </select>
                                                    <select name="anio" class="text-field" style="width:55px">
                                                        <option>
                                                            <c:forEach begin="1950" end="2020" varStatus="i">
                                                            <option value="${i.index}" <c:if test="${i.index == anio  }"> selected </c:if>>${i.index}
                                                            </c:forEach>
                                                    </select>
                                                </td>
                                            </tr>
                                           
                                             <tr>
                                               
                                            </tr>
                                                 
                                                      
                                                
                                             
                                             <tr>
                                                <td>CURSO</td>
                                                <td>:</td>
                                                <td colspan="4">
                                                    <select id="unico_id_curso" name="id_curso" class="text-field" style="width:250px" onchange="selectCurso(this.value)">
                                                        <c:forEach var="item" items="${cursos}">
                                                            <option value="${item.id_curso}"<c:if test="${item.id_curso == id_curso  }"> selected </c:if>> ${item.curso} ${item.ciclo}
                                                            </c:forEach>
                                                    </select>
                                                    <span style="color:red">Alerta: Actualizar el curso</span>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td width="23%">G&Oacute;NDOLA</td>
                                                <td width="2%">:</td>
                                                <td colspan="4">
                                                    <select name="id_gondola" class="text-field" style="width:500px" >
                                                        <option value="">Ninguno...
                                                            <c:forEach var="item" items="${gondolas}">
                                                            <option value="${item.placa}"<c:if test="${item.placa == id_dongola  }"> selected </c:if>>${item.empresa}, Nro. ${item.nro_gondola} y Ruta(${item.ruta})
                                                            </c:forEach>
                                                    </select>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td colspan="1">GESTI&Oacute;N</td>
                                                <td colspan="1">:</td>
                                                <td colspan="4"><input class="text-field" type="text" name="id_gestion" value="${gestion.id_gestion}" maxlength="5" style="width:120px" onfocus="this.blur()"/></td>
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
                                                <td colspan="1" valign="top">PAGO EN COLEGIO</td>
                                                <td colspan="1" valign="top">:</td>
                                                <td colspan="4" valign="top">
                                                    <input type="checkbox" name="pago_cole" value="pago_cole" id="pagoCole" onchange="selectPagoCole()">
                                                    <div id="item-pagoCole" style="display:none">
                                                        <table width="100%" border="0" cellpadding="0" cellspacing="0" align="center">
                                                            <tr>
                                                                <td class="gridHeaders" colspan="3"><strong>Pagos pendientes realizados en el colegio</strong></td>
                                                            </tr>
                                                            <tr>
                                                                <td class="gridData" width="20%"><strong>Descripci&oacute;n</strong></td>
                                                                <td class="gridData" width="5%"><strong>:</strong></td>
                                                                <td class="gridData" width="75%"><input type="text" class="text-field" style="width: 400px" name="desc_pagoCole"></td>
                                                            </tr>
                                                            <tr>
                                                                <td class="gridData"><strong>Nro. Factura</strong></td>
                                                                <td class="gridData"><strong>:</strong></td>
                                                                <td class="gridData"><input type="text" class="text-field" maxlength="25" style="width: 100px" name="nro_factura"></td>
                                                            </tr>
                                                            <tr>
                                                                <td class="gridData"><strong>Monto (Bs)</strong></td>
                                                                <td class="gridData"><strong>:</strong></td>
                                                                <td class="gridData"><input type="text" class="text-field" style="width: 100px" name="monto_cole" value="0"> NOTA: Valor ingresado debe ser num&eacute;rico.</td>
                                                            </tr>
                                                        </table>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td colspan="6" align="center">
                                                    <input type="hidden" name="ci" value="${family.ci_resp_pago}">
                                                    <input type="hidden" name="id_familia" value=${family.id_familia}>
                                                    <input type="hidden" name="regEst" value="_regEst">
                                                    <button class="button-normal" type="submit"><img width="11px" src="imagenes/iconos_sigaa/guardar.png">&nbsp;&nbsp;Guardar</button>
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </c:if>
                            <script type="text/javascript">selectCuota('1')</script>
                        </table>
                    </form>
                </div>
            </c:if>

            <c:if test="${!empty ins2}">
                <div id="formRegistroPersona" class="formPanel">
                    <form action="<c:url value="/inscripcion.do"/>" method="post" name="frmRegistro" onsubmit="return setRegistrar(this)">
                        <table border="0" cellpadding="0" cellspacing="0" width="100%" class="gridContent">
                            <tr>
                                <td colspan="6" class="tableHeader" align="center">RE-INSCRIPCI&Oacute;N DEL ESTUDIANTE</td>
                            </tr>
                            <c:if test="${gestion.id_gestion!=anio_actual}">
                                <tr>
                                    <td colspan="6" style="color:red">
                                        No se puede efectuar las inscripciones correspondientes a la gesti&oacute;n <%
                                            java.util.Date now1 = new java.util.Date();
                                            java.text.SimpleDateFormat fd1 = new java.text.SimpleDateFormat("yyyy");
                                            out.println(fd1.format(now1));
                                        %> , no se realiz&oacute; el registro del cronograma acad&eacute;mico anual para la gesti&oacute;n en curso.
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="6" align="center"><h2 style="color:red">Verifique Gesti&oacute;n Acad&eacute;mica!...</h2></td>
                                </tr>
                            </c:if>
                            <c:if test="${gestion.id_gestion==anio_actual}">
                                <tr>
                                    <td colspan="6">
                                        Llene cuidadosamente todos los campos
                                        tomando muy en cuenta los marcados con ( <img src="imagenes/icons/required.gif"/> ) que
                                        corresponde a datos obligatorios.
                                    </td>
                                </tr>
                                <tr>
                                    <td align="center"colspan="6"><p><strong>INSCRIPCIONES</strong></p></td>
                                </tr>
                                <tr>
                                    <td>C&Oacute;DIGO<img src="imagenes/icons/required.gif"/></td>
                                    <td colspan="5"><input class="text-field" type="text" name="codigo" value="${estudiante.codigo}" maxlength="5" style="width:50px" onfocus="this.blur()"/></td>
                                </tr>
                                <tr>
                                    <td>C&Eacute;DULA DE IDENTIDAD</td>
                                    <td colspan="5"><input class="text-field" type="text" name="carnet" value="${estudiante.carnet}" maxlength="10" style="width:80px"/><span style="color:red">Ej: 6020032</span></td>
                                </tr>
                                <tr>
                                    <td colspan="1">NOMBRE(S) <img src="imagenes/icons/required.gif"/> : </td>
                                    <td colspan="5"><input class="text-field" type="text" name="nombres" value="${estudiante.nombres}" maxlength="70" style="width:250px" onfocus="this.blur()"/></td>
                                </tr>
                                <tr>
                                    <td>APELLIDO PATERNO <img src="imagenes/icons/required.gif"/> : </td>
                                    <td colspan="5"><input class="text-field" type="text" name="paterno" value="${estudiante.paterno}" maxlength="70" style="width:250px" onfocus="this.blur()"/></td>
                                </tr>
                                <tr>
                                    <td>APELLIDO MATERNO <img src="imagenes/icons/required.gif"/> : </td>
                                    <td colspan="5"><input class="text-field" type="text" name="materno" value="${estudiante.materno}" maxlength="70" style="width:250px" onfocus="this.blur()"/></td>
                                </tr>
                                <tr>
                                    <td>SEXO : </td>
                                    <td colspan="5">
                                        <select name="id_sexo" class="text-field" style="width:120px" onfocus="this.blur()">
                                            <c:forEach var="item" items="${tipos_sexos}">
                                                <option value="${item.id}"<c:if test="${item.id == estudiante.id_sexo  }"> selected </c:if>> ${item.valor}
                                                </c:forEach>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>FECHA DE NACIMIENTO : </td>
                                    <td colspan="5">
                                        <!--input class="text-field" type="text" name="fecha_nac" value="${estudiante.sfecha_nacimiento}" style="width:250px" onfocus="this.blur()"/-->
                                        <select name="dia" class="text-field" style="width:45px">
                                            <c:forEach begin="1" end="31" varStatus="i">
                                                <option <c:if test="${i.count==estudiante.fecha_nacimiento.date}"> Selected="selected" </c:if>>${i.count}
                                                </c:forEach>
                                        </select>
                                        <select name="mes" class="text-field" style="width:90px">
                                            <c:forEach var="itemm" items="${tipo_meses}">
                                                <option value="${itemm.id}" <c:if test="${itemm.id==(estudiante.fecha_nacimiento.month+1)}"> Selected="selected" </c:if>>${itemm.valor}
                                                </c:forEach>
                                        </select>
                                        <select name="anio" class="text-field" style="width:60px">
                                            <c:forEach begin="1990" end="2020" varStatus="j">
                                                <option <c:if test="${j.index==(estudiante.fecha_nacimiento.year+1900)}"> Selected="selected" </c:if>>${j.index}
                                                </c:forEach>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>CURSO : </td>
                                    <td colspan="5">
                                        <select id="unico_id_curso" name="id_curso" class="text-field" style="width:250px"  onchange="selectCurso(this.value)">
                                            <c:forEach var="item" items="${cursos}">
                                                <option value="${item.id_curso}"<c:if test="${item.id_curso == id_curso  }"> selected </c:if>> ${item.curso} ${item.ciclo}
                                                </c:forEach>
                                        </select>
                                        <span style="color:red">Alerta: Actualizar el curso</span>
                                    </td>
                                </tr>
                                <tr>
                                    <td>GONDOLA : </td>
                                    <td colspan="5">
                                        <select name="id_gondola" class="text-field" style="width:500px" >
                                            <option value="">Ninguno...
                                                <c:forEach var="item" items="${gondolas}">
                                                <option value="${item.placa}"<c:if test="${item.placa == id_dongola  }"> selected </c:if>>${item.empresa}, Nro. ${item.nro_gondola} y Ruta(${item.ruta})
                                                </c:forEach>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>GESTI&Oacute;N : </td>
                                    <td colspan="5"><input class="text-field" type="text" name="id_gestion" value="${gestion.id_gestion}" maxlength="5" style="width:120px" onfocus="this.blur()"/></td>
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
                                    <td colspan="1" valign="top">PAGO EN COLEGIO</td>
                                    <td colspan="1" valign="top">:</td>
                                    <td colspan="4" valign="top">
                                        <input type="checkbox" name="pago_cole" value="pago_cole" id="pagoCole" onchange="selectPagoCole()">
                                        <div id="item-pagoCole" style="display:none">
                                            <table width="100%" border="0" cellpadding="0" cellspacing="0" align="center">
                                                <tr>
                                                    <td class="gridHeaders" colspan="3"><strong>Pagos pendientes realizados en el colegio</strong></td>
                                                </tr>
                                                <tr>
                                                    <td class="gridData" width="20%"><strong>Descripci&oacute;n</strong></td>
                                                    <td class="gridData" width="5%"><strong>:</strong></td>
                                                    <td class="gridData" width="75%"><input type="text" class="text-field" style="width: 400px" name="desc_pagoCole"></td>
                                                </tr>
                                                <tr>
                                                    <td class="gridData"><strong>Nro. Factura</strong></td>
                                                    <td class="gridData"><strong>:</strong></td>
                                                    <td class="gridData"><input type="text" class="text-field" maxlength="25" style="width: 100px" name="nro_factura"></td>
                                                </tr>
                                                <tr>
                                                    <td class="gridData"><strong>Monto (Bs)</strong></td>
                                                    <td class="gridData"><strong>:</strong></td>
                                                    <td class="gridData"><input type="text" class="text-field" style="width: 100px" name="monto_cole" value="0"> NOTA: Valor ingresado debe ser num&eacute;rico.</td>
                                                </tr>
                                            </table>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="6" align="center">
                                        <input type="hidden" name="ci" value="${family.ci_resp_pago}">
                                        <input type="hidden" name="id_familia" value=${family.id_familia}>
                                        <input type="hidden" name="id_est" value="${estudiante.id_estudiante}">
                                        <input type="hidden" name="reInsEst" value="_reInsEst">
                                        <button class="button-normal" type="submit"><img width="11px" src="imagenes/iconos_sigaa/guardar.png">&nbsp;&nbsp;Guardar</button>
                                    </td>
                                </tr>
                            </c:if>
                            <script type="text/javascript">selectCuota('${cuota}')</script>
                        </table>
                    </form>
                </div>
            </c:if>

            <c:if test="${!empty edit}">
                <div id="formRegistroPersona" class="formPanel">
                    <form action="<c:url value="/inscripcion.do"/>" method="post" name="frmRegistro" onsubmit="return setRegistrar(this)">
                        <table border="0" cellpadding="0" cellspacing="0" width="100%" class="gridContent">
                            <tr>
                                <td colspan="6" class="tableHeader" align="center">ATUALIZAR DATOS DEL ESTUDIANTE</td>
                            </tr>
                            <c:if test="${gestion.id_gestion!=anio_actual}">
                                <tr>
                                    <td colspan="6" style="color:red">
                                        No se puede efectuar las inscripciones correspondientes a la gesti&oacute;n <%
                                            java.util.Date now2 = new java.util.Date();
                                            java.text.SimpleDateFormat fd2 = new java.text.SimpleDateFormat("yyyy");
                                            out.println(fd2.format(now2));
                                        %> , no se realiz&oacute; el registro del cronograma acad&eacute;mico anual para la gesti&oacute;n en curso.
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="6" align="center"><h2 style="color:red">Verifique Gesti&oacute;n Acad&eacute;mica!...</h2></td>
                                </tr>
                            </c:if>
                            <c:if test="${gestion.id_gestion==anio_actual}">
                                <tr>
                                    <td colspan="6">
                                        <b>Formulario de actualizaci&oacute;n de datos personales y acad&eacute;micos bajo ciertas condiciones</b>.
                                        La actualizaci&oacute;n se realizar&aacute; correctamente siempre y cuando no se hayan asignado las evaluaciones 
                                        a las materias correspondientes al curso. <font style="color:red">NOTA: Tenga muy en cuenta,
                                            que cualquier cambio afectar&aacute; automaticamente en otras &aacute;reas como las pensiones, servicios,...</font>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="center"colspan="6"><p><strong>INFORMACI&Oacute;N DEL ESTUDIANTE</strong></p></td>
                                </tr>
                                <tr>
                                    <td>C&Oacute;DIGO<img src="imagenes/icons/required.gif"/></td>
                                    <td colspan="5"><input class="text-field" type="text" name="codigo" value="${estudiante.codigo}" maxlength="5" style="width:50px"/></td>
                                </tr>
                                <tr>
                                    <td colspan="6"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                                </tr>
                                <tr>
                                    <td colspan="1">NOMBRE(S) <img src="imagenes/icons/required.gif"/> : </td>
                                    <td colspan="5"><input class="text-field" type="text" name="nombres" value="${estudiante.nombres}" maxlength="70" style="width:250px"/></td>
                                </tr>
                                <tr>
                                    <td colspan="6"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                                </tr>
                                <tr>
                                    <td>APELLIDO PATERNO <img src="imagenes/icons/required.gif"/> : </td>
                                    <td colspan="5"><input class="text-field" type="text" name="paterno" value="${estudiante.paterno}" maxlength="70" style="width:250px"/></td>
                                </tr>
                                <tr>
                                    <td colspan="6"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                                </tr>
                                <tr>
                                    <td>APELLIDO MATERNO <img src="imagenes/icons/required.gif"/> : </td>
                                    <td colspan="5"><input class="text-field" type="text" name="materno" value="${estudiante.materno}" maxlength="70" style="width:250px"/></td>
                                </tr>
                                <tr>
                                    <td colspan="6"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                                </tr>
                                <tr>
                                    <td>SEXO : </td>
                                    <td colspan="5">
                                        <select name="id_sexo" class="text-field" style="width:120px">
                                            <c:forEach var="item" items="${tipos_sexos}">
                                                <option value="${item.id}"<c:if test="${item.id == estudiante.id_sexo  }"> selected </c:if>> ${item.valor}
                                                </c:forEach>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="6"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                                </tr>
                                <tr>
                                    <td>FECHA DE NACIMIENTO : </td>
                                    <td valign="top" colspan="5">
                                        <select name="dia" class="text-field" style="width:40px">
                                            <option>
                                                <c:forEach begin="1" end="31" varStatus="i">
                                                <option value="${i.count}" <c:if test="${i.count == estudiante.fecha_nacimiento.date}"> selected </c:if>>${i.count}
                                                </c:forEach>
                                        </select>
                                        <select name="mes" class="text-field" style="width:105px">
                                            <option>
                                                <c:forEach var="item" items="${tipo_meses}">
                                                <option value="${item.id}" <c:if test="${item.id == (estudiante.fecha_nacimiento.month+1)}"> selected </c:if>>${item.valor}
                                                </c:forEach>
                                        </select>
                                        <select name="anio" class="text-field" style="width:55px">
                                            <option>
                                                <c:forEach begin="1950" end="2020" varStatus="i">
                                                <option value="${i.index}" <c:if test="${i.index == (estudiante.fecha_nacimiento.year+1900)}"> selected </c:if>>${i.index}
                                                </c:forEach>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="6"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                                </tr>
                                <tr>
                                    <td>CURSO : </td>
                                    <td colspan="5">
                                        <select id="unico_id_curso" name="id_curso" class="text-field" style="width:250px"  onchange="selectCurso(this.value)" <c:if test="${pagoPension.editable==false}"> onfocus="this.blur()" </c:if>>
                                            <c:forEach var="item" items="${cursos}">
                                                <option value="${item.id_curso}"<c:if test="${item.id_curso == id_curso  }"> selected </c:if>> ${item.curso} ${item.ciclo}
                                                </c:forEach>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="6"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                                </tr>
                                <tr>
                                    <td>GONDOLA : </td>
                                    <td colspan="5">
                                        <select name="id_gondola" class="text-field" style="width:500px" >
                                            <option value="">Ninguno...
                                                <c:forEach var="item" items="${gondolas}">
                                                <option value="${item.placa}"<c:if test="${item.placa==insc.id_gondola}"> selected </c:if>>${item.empresa}, Nro. ${item.nro_gondola} y Ruta(${item.ruta})
                                                </c:forEach>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="6"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                                </tr>
                                <tr>
                                    <td>GESTI&Oacute;N : </td>
                                    <td colspan="5">
                                        <input class="text-field" type="text" name="id_gestion" value="${gestion.id_gestion}" maxlength="5" style="width:120px" onfocus="this.blur()"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="6"><img src="imagenes/pixel_trans.gif" height="5"/></td>
                                </tr>
                                <tr>
                                    <td colspan="1" valign="top">PENSIONES : </td>
                                    <td colspan="1" valign="top"></td>
                                    <td colspan="4" valign="top">
                                        <table border="0" cellpadding="0" cellspacing="0" width="100%"  align="center" class="menuHead" style="cursor:pointer">
                                            <tr>
                                                <td class="gridHeaders" width="30%" title="Cantidad de cuotas"><strong>Nro. cuotas y meses de pago</strong></td>
                                                <td class="gridHeaders" width="10%" title="Monto a cancelar por cuota"><strong>Monto/Cuota</strong></td>
                                                <td class="gridHeaders" width="20%" title="Monto anual (Definido para la gestion)"><strong>Monto Anual</strong></td>
                                                <td class="gridHeaders" width="10%" title="Cuota inicial"><strong>C. Inicial</strong></td>
                                                <td class="gridHeaders" width="20%" title="Cuota anual (Definido para la gestion)"><strong>Cuota Anual</strong></td>
                                                <td class="gridHeaders" width="5%" title="Descuento"><strong>Desc.</strong></td>
                                                <td class="gridHeaders" width="5%" title="Beca"><strong>Beca</strong></td>
                                            </tr>
                                            <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)" bgcolor="#ffffff">
                                                <td class="gridData" align="center"x>
                                                    <table width="100%">
                                                        <tr style="cursor:pointer">
                                                            <td valign="top">
                                                                <select id="unico_id_cuota" name="cuota" class="text-field" style="width:100px" onchange="selectCuota(this.value)" <c:if test="${pagoPension.editable==false}"> onfocus="this.blur()" </c:if>>
                                                                    <c:forEach var="it" items="${cuotas}">
                                                                        <option value="${it.id}"<c:if test="${it.id== pagoPension.cuota}"> selected </c:if>/><c:if test="${it.id==1}"> ${it.id} Cuota </c:if><c:if test="${it.id>1}"> ${it.id} Cuotas </c:if>
                                                                    </c:forEach>
                                                                </select>
                                                            </td>
                                                            <td valign="top">
                                                                <div id="item-meses"></div>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </td>
                                                <td class="gridData" id="monto_by_cuota" align="center" valign="top" style="color:navy;cursor:pointer" bgcolor="#FDE1E3"><br></td>
                                                <td class="gridData" id="monto_total" align="center" valign="top" style="color:navy;cursor:pointer"><br></td>
                                                <td class="gridData" id="monto_inicial" align="center" valign="top" style="color:navy;cursor:pointer"><br></td>
                                                <td class="gridData" id="monto_anual" align="center" valign="top" style="color:navy;cursor:pointer"><br></td>
                                                <td class="gridData" align="center" valign="top" style="color:navy;cursor:pointer"><br>0 %</td>
                                                <td class="gridData" align="center" valign="top" style="color:navy;cursor:pointer"><br>0 %</td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="6"><img src="imagenes/pixel_trans.gif" height="10"/></td>
                                </tr>
                                <tr>
                                    <td colspan="1" valign="top">SERVICIOS : </td>
                                    <td colspan="5" valign="top">
                                        <table border="0" width="100%" onmouseover="onRowover(this)" onmouseout="onRowout(this)" bgcolor="#ffffff">
                                            <tr>
                                                <td colspan="4">
                                                    <button class="button-normal" type="button" title="Nuevo servicio"onclick="NewsServicios('${insc.id_inscripcion}','${family.id_familia}','${estudiante.nombres} ${estudiante.paterno} ${estudiante.materno}','${curso.curso} de ${curso.ciclo}')">
                                                        <img width="11px" src="imagenes/iconos_sigaa/guardar.png">&nbsp;&nbsp;Nuevo servicio
                                                    </button>
                                                </td>
                                            </tr>
                                            <tr class="gridHeaders">
                                                <td width="40%" colspan="2" style="color:black" align="center"><strong>SERVICIOS</strong></td>
                                                <td width="10%" style="color:black" align="center"><strong>MONTO</strong></td>
                                                <td width="50%" style="color:black" align="center"><strong>ESTADO</strong></td>
                                            </tr>
                                            <c:forEach varStatus="j" var="item" items="${pagoServicios}">
                                                <c:if test="${insc.id_inscripcion==item.id_inscripcion}">
                                                    <tr style="cursor:pointer"  onmouseover="onRowover(this)" onmouseout="onRowout(this)" bgcolor="#ffffff">
                                                        <td colspan="2" valign="top">&nbsp;<img src="imagenes/cxc.png">&nbsp; ${item.tipo_servicio}</td>
                                                        <td align="center">${item.monto_servicio} Bs.</td>
                                                        <c:if test="${item.estado=='cancelado'}">
                                                            <td style="color:green" align="center">Cancelado
                                                                <br> <font style="color:gray">(${item.sfec_pago})</font>
                                                            </td>
                                                        </c:if>
                                                        <c:if test="${item.estado!='cancelado'}">
                                                            <td>
                                                                <table width="100%" border="0">
                                                                    <tr>
                                                                        <td align="center" colspan="2" style="color:red">Pago pendiente</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td width="15%">Fecha : </td>
                                                                        <td width="85%">
                                                                            <select id="dd-${item.id_inscripcion}${item.id_servicio}" class="text-field" style="width:45px">
                                                                                <c:forEach begin="1" end="31" varStatus="i">
                                                                                    <option <c:if test="${i.count==dma.date}"> Selected="selected" </c:if>>${i.count}
                                                                                    </c:forEach>
                                                                            </select>
                                                                            <select id="mm-${item.id_inscripcion}${item.id_servicio}" class="text-field" style="width:90px">
                                                                                <c:forEach var="itemm" items="${meses}">
                                                                                    <option value="${itemm.id}" <c:if test="${itemm.id==(dma.month+1)}"> Selected="selected" </c:if>>${itemm.valor}
                                                                                    </c:forEach>
                                                                            </select>
                                                                            <select id="aaaa-${item.id_inscripcion}${item.id_servicio}" class="text-field" style="width:60px">
                                                                                <c:forEach begin="2008" end="2020" varStatus="j">
                                                                                    <option <c:if test="${j.index==(dma.year+1900)}"> Selected="selected" </c:if>>${j.index}
                                                                                    </c:forEach>
                                                                            </select>
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td align="center" colspan="2">
                                                                            <button class="button-normal" type="button" onclick="setPagarServicio('${item.id_familia}','${item.id_inscripcion}','${item.id_servicio}','${estudiante.id_estudiante}','${curso.id_curso}')">
                                                                                <img width="11px" src="imagenes/iconos_sigaa/guardar.png">&nbsp;&nbsp;Pagar servicio
                                                                            </button>                                                                                                                                                        &nbsp;
                                                                            <button class="button-normal" title="Eliminar este servicio" type="button" onclick="setDeleteServicio('${item.id_familia}','${item.id_inscripcion}','${item.id_servicio}','${estudiante.id_estudiante}','${curso.id_curso}')">
                                                                                <img width="11px" src="imagenes/iconos_sigaa/eliminar.png">&nbsp;&nbsp;Quitar
                                                                            </button>
                                                                        </td>
                                                                    </tr>
                                                                </table>
                                                            </td>
                                                        </c:if>
                                                    </tr>
                                                </c:if>
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
                                        <input type="hidden" name="id_persona" value="${estudiante.id_persona}">
                                        <input type="hidden" name="id_familia" value=${family.id_familia}>
                                        <input type="hidden" name="id_est" value="${estudiante.id_estudiante}">
                                        <input type="hidden" name="id_curso_ant" value="${id_curso}">
                                        <input type="hidden" name="id_inscripcion" value="${insc.id_inscripcion}">
                                        <input type="hidden" name="modific" value="_modific">
                                        <button class="button-normal" type="submit"><img width="11px" src="imagenes/iconos_sigaa/guardar.png">&nbsp;&nbsp;Guardar</button>
                                    </td>
                                </tr>
                            </c:if>
                        </table>
                        <script type="text/javascript">selectCuota('${cuota}')</script>
                    </form>
                </div>
            </c:if>

        </div>

        <div id="updateFamily"  style="display:none">
            <div class="headercontent">
                <table style="width:100%" cellspacing="0" cellpadding="0">
                    <tr>
                        <td class="menuHead"><b>Inscripciones</b>Actualizaci&oacute;n de datos</td>
                    </tr>
                </table>
            </div>
            <div class="formPanel">
                <form action="<c:url value="/inscripcion.do"/>" method="post" name="form">
                    <table width="100%" border="0" style="cursor:pointer">
                        <tr style="cursor:pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                            <td >C&Oacute;D. FAMILIA : </td>
                            <td colspan="5" class="textColor"><input class="text-field" type="text" name="id_familia" value="${family.id_familia}" maxlength="9" style="width:75px" onfocus="this.blur()"/></td>
                        </tr>
                        <tr style="cursor:pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                            <td width="15%"> TUTOR 1 <img src="imagenes/icons/required.gif"/> :</td>
                            <td width="45%" colspan="3"><input class="text-field" type="text" name="nombres1" value="${family.nombre_apellidos_tutor_1}" maxlength="250" style="width:300px"/></td>
                            <td width="15%" align="center"><input class="text-field" type="text" name="ci1" value="${family.ci_tutor_1}" maxlength="9" style="width:75px"/></td>
                            <td width="25%" align="center" style="color:gray"><input type="radio" name="resp" value="tutor1" <c:if test="${family.ci_tutor_1==family.ci_resp_pago}">checked</c:if>>&nbsp;Resp. pago</td>
                            </tr>
                            <tr style="cursor:pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                <td></td>
                                <td colspan="3" align="center" style="color:gray">Nombre/s y Apellidos</td>
                                <td align="center" style="color:gray">c.i.</td>
                            </tr>
                            <tr style="cursor:pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                <td width="15%"> TUTOR 2 <img src="imagenes/icons/required.gif"/> :</td>
                                <td width="45%" colspan="3"><input class="text-field" type="text" name="nombres2" value="${family.nombre_apellidos_tutor_2}" maxlength="250" style="width:300px"/></td>
                            <td width="15%" align="center"><input class="text-field" type="text" name="ci2" value="${family.ci_tutor_2}" maxlength="9" style="width:75px"/></td>
                            <td width="25%" align="center" style="color:gray"><input type="radio" name="resp" value="tutor2" <c:if test="${family.ci_tutor_2==family.ci_resp_pago}">checked</c:if>>&nbsp;Resp. pago</td>
                            </tr>
                            <tr style="cursor:pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                <td></td>
                                <td colspan="3" align="center" style="color:gray">Nombre/s y Apellidos</td>
                                <td align="center" style="color:gray">c.i.</td>
                            </tr>
                            <tr style="cursor:pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                <td width="15%"> TUTOR 3 <img src="imagenes/icons/required.gif"/> :</td>
                                <td width="45%" colspan="3"><input class="text-field" type="text" name="nombres3" value="${family.nombre_apellidos_tutor_3}" maxlength="250" style="width:300px"/></td>
                            <td width="15%" align="center"><input class="text-field" type="text" name="ci3" value="${family.ci_tutor_3}" maxlength="9" style="width:75px"/></td>
                            <td width="25%" align="center" style="color:gray"><input type="radio" name="resp" value="tutor3" <c:if test="${family.ci_tutor_3==family.ci_resp_pago}">checked</c:if>>&nbsp;Resp. pago</td>
                            </tr>
                            <tr style="cursor:pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                <td></td>
                                <td colspan="3" align="center" style="color:gray">Nombre/s y Apellidos</td>
                                <td align="center" style="color:gray">c.i.</td>
                            </tr>
                            <tr style="cursor:pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                <td>Direcci&oacute;n(1)<img src="imagenes/icons/required.gif"/>:</td>
                                <td colspan="3" ><input class="text-field" type="text" name="dir1" value="${family.direccion_1}" maxlength="500" style="width:300px"/></td>
                            <td colspan="2">Telef(1) <img src="imagenes/icons/required.gif"/> : <input class="text-field" type="text" name="tf1" value="${family.telefono_1}" maxlength="25" style="width:100px"/></td>
                        </tr>
                        <tr style="cursor:pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                            <td>Direcci&oacute;n (2): </td>
                            <td colspan="3" ><input class="text-field" type="text" name="dir2" value="${family.direccion_2}" maxlength="500" style="width:300px"/></td>
                            <td colspan="2">Telef(2) :&nbsp;&nbsp;&nbsp;&nbsp;<input class="text-field" type="text" name="tf2" value="${family.telefono_2}" maxlength="25" style="width:100px"/></td>
                        </tr>
                        <tr>
                            <td colspan="6">
                                <table style="width: 100%">
                                    <tr style="cursor:pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                        <td style="width:30%">Otros Telef: </td>
                                        <td style="width:70%"><input class="text-field" type="text" name="telefonos" value="${family.telefonos}" maxlength="500" style="width:400px"/></td>
                                    </tr>
                                    <tr style="cursor:pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                        <td>Factura/Nit : </td>
                                        <td><input class="text-field" type="text" name="e_mail" value="${family.e_mail}" maxlength="300" style="width:400px"/></td>
                                    </tr>
                                    <tr style="cursor:pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                        <td>E-Mail Tutor/Resp Pago : </td>
                                        <td><input class="text-field" type="text" name="e_mailRP" value="${family.e_mailRP}" maxlength="300" style="width:400px"/></td>
                                    </tr>                        
                                    <tr style="cursor:pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                        <td>Lugar de Trabajo(Resp pago): </td>
                                        <td><input class="text-field" type="text" name="lugtrab" value="${family.lugtrab}" maxlength="500" style="width:400px"/></td>
                                    </tr>
                                    <tr style="cursor:pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                        <td valign="top">Otras observaciones : </td>
                                        <td><textarea name="observacion" class="text-field" style="width:400px">${family.observacion}</textarea></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <!--tr style="cursor:pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                            <td>Otros Telef: </td>
                            <td colspan="5" ><input class="text-field" type="text" name="telefonos" value="${family.telefonos}" maxlength="500" style="width:400px"/></td>
                        </tr-->

                        <tr style="cursor:pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                            <td colspan="6" align="center">
                                <button class="button-normal" type="submit"><img width="11px" src="imagenes/iconos_sigaa/guardar.png">&nbsp;&nbsp;Guardar</button>
                            </td>
                        </tr>
                        <input type="hidden" name="updateFamily" value="_updateFamily">
                    </table>
                </form>
            </div>
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
        <div id="documentoPdF" style="display:none">
            <div style="width:100%;height:99%">
                <iframe id="documentview" frameborder="0" style="width:100%;height:100%"></iframe>
            </div>
        </div>
        <div id="selectNewsServicios" style="display:none">
            SERVICIOA DISPONIBLES
            <div id="datos-est"></div>
            <form id="addform" action="<c:url value="/inscripcion.do"/>" method="post">
                <table width="100%" border="0" style="color:navy">
                    <tr>
                        <td class="gridHeaders" width="10%"><strong>Nro.</strong></td>
                        <td class="gridHeaders" width="60%"><strong>Servicio</strong></td>
                        <td class="gridHeaders" width="20%"><strong>Monto</strong></td>
                        <td class="gridHeaders" width="10%"><strong>Asignar</strong></td>
                    </tr>
                    <c:if test="${empty servicios}">
                        <tr>
                            <td colspan="4">No se han encontrado elementos.</td>
                        </tr>
                    </c:if>
                    <c:forEach varStatus="j" var="items" items="${servicios}">
                        <tr style="cursor:pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)" bgcolor="#ffffff">
                            <td align="center">${j.count}</td>
                            <td><img src="imagenes/cxc.png">&nbsp;&nbsp;${items.tipo_servicio}</td>
                            <td align="center"><strong>${items.monto_servicio} Bs.</strong></td>
                            <td align="center"><input type="checkbox" name="serv-${items.id_servicio}"></td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="4" align="center">
                            <button class="button-normal" type="submit"><img width="11px" src="imagenes/iconos_sigaa/guardar.png">&nbsp;&nbsp;Guardar</button>
                        </td>
                    </tr>
                </table>
                <input type="hidden" name="id_estudiante" value="${estudiante.id_estudiante}">
                <input type="hidden" name="id_inscripcion">
                <input type="hidden" name="busca" value=${family.id_familia}>
                <input type="hidden" name="opcion" value="_newService">
                <input type="hidden" name="id_curso" value="${curso.id_curso}">
            </form>
        </div>
    </body>
</html>
