<%-- 
    Document   : AsignacionPagos
    Created on : 15-ago-2009, 21:09:51
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
        <script type="text/javascript" src="js/jquery-1.2.2.pack.js"></script>
        <script type="text/javascript" src="js/animatedcollapse.js"></script>    
        <script type="text/javascript" src="js/prototype.js"></script>
        <link rel="stylesheet" type="text/css" href="css/module.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="css/st.css" media="screen" />
        <script type="text/javascript" src="js/Tips.js"></script>
        <script type="text/javascript">
            window.onload=function(){enableTooltips()};
        </script>
        <script type="text/javascript">
            animatedcollapse.addDiv('success', 'fade=1');
            animatedcollapse.addDiv('failure', 'fade=1');
            animatedcollapse.init();
        </script>

        <script type="text/javascript">

            var serv_id = new Array();
            var serv_monto=new Array();
            <c:forEach varStatus="i" var="ser" items="${servicios}">serv_id[${i.index}]='${ser.id_servicio}';serv_monto[${i.index}]='${ser.monto_servicio}';</c:forEach>
                var desc_id = new Array();
                var desc_valor=new Array();
                var desc_descr=new Array();
            <c:forEach varStatus="i" var="desc" items="${descuentos}">desc_id[${i.index}]='${desc.id}';desc_valor[${i.index}]='${desc.valor}';desc_descr[${i.index}]='${desc.abreviatura}';</c:forEach>
                var e=new Array();
            <c:forEach varStatus="i" var="es" items="${alumnos}">e[${i.index}]='${es.id_inscripcion}';</c:forEach>
                var serv_id_ins=new Array();
                var serv_montoo=new Array();
                var serv_estado=new Array();
            <c:forEach varStatus="i" var="serv" items="${pagoServicios}">serv_id_ins[${i.index}]='${serv.id_inscripcion}';serv_montoo[${i.index}]='${serv.monto_servicio}';serv_estado[${i.index}]='${serv.estado}';</c:forEach>

                function getTotales(){
                    for(t=0;t<e.length;t++){
                        var total=0;
                        var total_cancelado=0;
                        var total_saldo=0;
                        for(j=0;j<serv_montoo.length;j++){
                            if(e[t]==serv_id_ins[j]){
                                total=total+parseInt(serv_montoo[j]);
                                if(serv_estado[j]=='cancelado'){
                                    total_cancelado=total_cancelado+parseInt(serv_montoo[j]);
                                }else{
                                    total_saldo=total_saldo+parseInt(serv_montoo[j]);
                                }
                            }
                        }
                        document.getElementById('tmonto-'+e[t]).innerHTML = total+' Bs.';
                        document.getElementById('tcancelado-'+e[t]).innerHTML = '<table width="100%"><tr><td style="color:green" width="50%"><strong>Cancelado</strong></td><td width="50%">: <strong>'+total_cancelado+'</strong> Bs.</td></tr></table>';
                        document.getElementById('tsaldo-'+e[t]).innerHTML = '<table width="100%"><tr><td style="color:red" width="50%"><strong>Saldo</strong></td><td width="50%">: <strong>'+total_saldo+'</strong> Bs.</td></tr></table>';
                    }
                }
                function onRowover(elem) {
                    elem.className='colover';
                }
                function onRowout(elem) {
                    elem.className='colout';
                }
                
                function selectEstudiante(id) {
                    window.location='<c:url value="/asignacionPagos.do"/>?search='+id+'&id_curso=${id_curso}&id_gestion=${id_gestion}';
                }

                /*function setRegistrar(form) {
                    form.guardar.disabled = true;
                    form.guardar.value = 'Enviando los datos...';
                    form.submit();
                    //si los datos no son correctos
                    return false
                }*/

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


                function setDescuentos(cuota,nro_hijo,id_est){
                    n_hijo=nro_hijo;
                    id_desc=cuota;
                    if(cuota>2){
                        id_desc='3';
                    }
                    if(nro_hijo>4){
                        nro_hijo='4';
                    }
                    codi=id_desc+nro_hijo;
                    for(i=0;i<desc_id.length;i++){
                        if(desc_id[i]==codi){
                            m=i;
                        }
                    }
                    document.getElementById('desc-'+id_est).innerHTML = 'Ref. : '+desc_valor[m]+" %";
                    if(cuota==1){
                        document.getElementById('item-meses-'+id_est).innerHTML = '<table width="100%" cellpadding="0" cellspacing="0" style="color:gray">\n\
                        <tr><td><img src="imagenes/cxc.png" width="12px"> FEBRERO</td></tr>\n\
                        </table>';
                    }
                    if(cuota==3){
                        document.getElementById('item-meses-'+id_est).innerHTML = '<table width="100%" cellpadding="0" cellspacing="0" style="color:gray">\n\
                        <tr><td><img src="imagenes/cxc.png" width="12px"> ABRIL</td></tr>\n\
                        <tr><td><img src="imagenes/cxc.png" width="12px"> JULIO</td></tr>\n\
                        <tr><td><img src="imagenes/cxc.png" width="12px"> OCTUBRE</td></tr></table>';
                    }
                    if(cuota==9){
                        document.getElementById('item-meses-'+id_est).innerHTML = '<table width="100%" cellpadding="0" cellspacing="0" style="color:gray">\n\
                        <tr><td><img src="imagenes/cxc.png" width="12px"> MARZO</td></tr>\n\
                        <tr><td><img src="imagenes/cxc.png" width="12px"> ABRIL</td></tr><tr><td><img src="imagenes/cxc.png" width="12px"> MAYO</td></tr>\n\
                        <tr><td><img src="imagenes/cxc.png" width="12px"> JUNIO</td></tr><tr><td><img src="imagenes/cxc.png" width="12px"> JULIO</td></tr>\n\
                        <tr><td><img src="imagenes/cxc.png" width="12px"> AGOSTO</td></tr><tr><td><img src="imagenes/cxc.png" width="12px"> SEPTIEMBRE</td></tr>\n\
                        <tr><td><img src="imagenes/cxc.png" width="12px"> OCTUBRE</td></tr><tr><td><img src="imagenes/cxc.png" width="12px"> NOVIEMBRE</td></tr></table>';
                    }
                }
                function setObservacion(id_estudiante){                                         
                    var cInicial=descuento=document.getElementById('cInicial-'+id_estudiante).value;
                    var cTotal=descuento=document.getElementById('cTotal-'+id_estudiante).value;
                    var descuento=document.getElementById('descuento-'+id_estudiante).value;
                    var beca=document.getElementById('beca-'+id_estudiante).value;
                    if(descuento==''){
                        descuento=0;
                    }
                    if(beca==''){
                        beca=0;
                    }
                    var nro_cuota=document.getElementById('id_cuota_'+id_estudiante).value;
                    var monto_op=parseInt(cTotal)+parseInt(cInicial);
                    var monto_pen=Math.round((monto_op/10)*10)/10
                    var diferencia=monto_pen-parseInt(cInicial);
                    var total_op=monto_pen*9;
                    var total_pago=parseInt(total_op)-(parseInt(total_op)*(parseInt(descuento)+(parseInt(beca))))/100;//parseInt(descuento)+parseInt(beca);
                    if(nro_cuota==1){
                        document.getElementById('total_pago-'+id_estudiante).innerHTML =
                            '<table width="100%" cellpadding="0" cellspacing="0">\n\
                        <tr><td align="center">'+(Math.round((total_pago/parseInt(nro_cuota))*10)/10)+'+'+diferencia+' = '+((Math.round((total_pago/parseInt(nro_cuota))*10)/10)+diferencia)+' Bs.</td></tr></table>';
                    }else if(nro_cuota==3){
                        document.getElementById('total_pago-'+id_estudiante).innerHTML =
                            '<table width="100%" cellpadding="0" cellspacing="0">\n\
                        <tr><td align="center">'+(Math.round((total_pago/parseInt(nro_cuota))*10)/10)+'+'+diferencia+' = '+((Math.round((total_pago/parseInt(nro_cuota))*10)/10)+diferencia)+' Bs.</td></tr>\n\
                        <tr><td align="center">'+(Math.round((total_pago/parseInt(nro_cuota))*10)/10)+' Bs.</td></tr>\n\
                        <tr><td align="center">'+(Math.round((total_pago/parseInt(nro_cuota))*10)/10)+' Bs.</td></tr></table>';
                    }else if(nro_cuota==9){
                        document.getElementById('total_pago-'+id_estudiante).innerHTML =
                            '<table width="100%" cellpadding="0" cellspacing="0">\n\
                        <tr><td align="center">'+(Math.round((total_pago/parseInt(nro_cuota))*10)/10)+'+'+diferencia+' = '+((Math.round((total_pago/parseInt(nro_cuota))*10)/10)+diferencia)+' Bs.</td></tr>\n\
                        <tr><td align="center">'+(Math.round((total_pago/parseInt(nro_cuota))*10)/10)+' Bs.</td></tr>\n\
                        <tr><td align="center">'+(Math.round((total_pago/parseInt(nro_cuota))*10)/10)+' Bs.</td></tr>\n\
                        <tr><td align="center">'+(Math.round((total_pago/parseInt(nro_cuota))*10)/10)+' Bs.</td></tr>\n\
                        <tr><td align="center">'+(Math.round((total_pago/parseInt(nro_cuota))*10)/10)+' Bs.</td></tr>\n\
                        <tr><td align="center">'+(Math.round((total_pago/parseInt(nro_cuota))*10)/10)+' Bs.</td></tr>\n\
                        <tr><td align="center">'+(Math.round((total_pago/parseInt(nro_cuota))*10)/10)+' Bs.</td></tr>\n\
                        <tr><td align="center">'+(Math.round((total_pago/parseInt(nro_cuota))*10)/10)+' Bs.</td></tr>\n\
                        <tr><td align="center">'+(Math.round((total_pago/parseInt(nro_cuota))*10)/10)+' Bs.</td></tr></table>';
                    }
                    document.getElementById('total_pago_anual-'+id_estudiante).innerHTML =Math.round((total_pago+diferencia)*10)/10+' Bs.';
                    document.getElementById('total_anual-'+id_estudiante).innerHTML =Math.round((total_pago+diferencia+parseInt(cInicial))*10)/10+' Bs.';
                }
                
                function openWindow(form,title,w,h){
                    docentesWin=dhtmlmodal.open('win'+form, 'div', form, title, 'width='+w+'px,height='+h+'px,left=100px,top=100px,resize=1,scrolling=1');
                    docentesWin.moveTo('middle', 'middle');
                    return docentesWin;
                }
                var _selectServicios = null;
                function setPagarServicio(id_fam,id_insc,id_serv) {
                    var dd=document.getElementById('dd-'+id_insc+id_serv).value;
                    var mm=document.getElementById('mm-'+id_insc+id_serv).value;
                    var aaaa=document.getElementById('aaaa-'+id_insc+id_serv).value;
                    window.location='<c:url value="/asignacionPagos.do"/>?search='+id_fam+'&id_insc='+id_insc+'&id_serv='+id_serv+'&fec_reg='+dd+'-'+mm+'-'+aaaa+'&opcion=_update&id_curso=${id_curso}&id_gestion=${id_gestion}';
                }
                function setDeleteServicio(id_fam,id_insc,id_serv) {
                    window.location='<c:url value="/asignacionPagos.do"/>?search='+id_fam+'&id_insc='+id_insc+'&id_serv='+id_serv+'&opcion=_delete&id_curso=${id_curso}&id_gestion=${id_gestion}';
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
                    document.getElementById('addform').search.value = id_fam;
                    document.getElementById('addform').id_insc.value = id_insc;
                    
                }
                function selectGestion(id_gestion){
                    window.location = '<c:url value="/asignacionPagos.do"/>?id_gestion='+id_gestion;
                }
                function selectCurso(id_curso){
                    window.location = '<c:url value="/asignacionPagos.do"/>?id_curso='+id_curso+'&id_gestion=${id_gestion}';
                }
                var obsWin = null;
                function openObservacion(id_dep_asignada,obs) {
                    obsWin = openWindow('openObservacion','Observaciones','600','180')
                    document.getElementById('formAdd').observacion.value = obs;
                    document.getElementById('formAdd').id_dep_asignada.value = id_dep_asignada;
                }
                function _closeObs() {
                    obsWin.hide();
                }
                var confirmwin = null;
                function setAnularAsignacionPagoPensiones() {
                    confirmwin = openDialogWindow('confirmarAnular','Confirmación','400','120');
                }

                function cancelReg() {
                    confirmwin.hide();
                }

                function confirmReg() {
                    window.location = '<c:url value="/asignacionPagos.do"/>?id_gestion=${id_gestion}&search=${family.id_familia}&id_curso=${id_curso}&opcion=_reAnularAsignacionPagos';
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
                                <td class="tab_current" style="border-right:1px solid #7D909E">Asignaci&oacute;n de pagos</td>
                                <td class="tab_normal" onclick="javascript:window.location='<c:url value="/depositosBancarios.do"/>'">Adm. dep&oacute;sitos bancarios</td>
                                <td class="tab_normal" onclick="javascript:window.location='<c:url value="/historialAcademico.do"/>'">Historial Acad&eacute;mico</td>
                                <td class="tab_normal" onclick="javascript:window.location='<c:url value="/inscripcionRezagada.do"/>'">Inscripciones rezagadas</td>
                            </tr>
                        </table>
                    </td>
                    <td class="tab_fin">&nbsp;</td>
                </tr>
            </table>
            <div class="titlepage" style="cursor: pointer">
                <font size="1px" onclick="javascript:window.location='<c:url value="/asignacionPagos.do"/>?id_gestion=${id_gestion}'">
                    <c:if test="${id_gestion!=null}">${id_gestion}&nbsp;<img src="imagenes/iconos_sigaa/back.png" title="Volver a la lista de cursos ${id_gestion}"></c:if>
                </font>
                <font size="1px" onclick="javascript:window.location='<c:url value="/asignacionPagos.do"/>?id_curso=${id_curso}&id_gestion=${id_gestion}'">
                    <c:if test="${id_curso!=null}">${curso_act.curso} de ${curso_act.ciclo}&nbsp;<img src="imagenes/iconos_sigaa/back.png" title="Volver al curso anterior ${curso_act.curso} de ${curso_act.ciclo}"></c:if>
                </font>
                ASIGNACI&Oacute;N DE PENSIONES
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
                                    <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)" onclick="selectGestion ('${item.id_gestion}')">
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
                                    <tr style="cursor:pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)" onclick="selectCurso('${item.id_curso}')">
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
            <c:if test="${!empty curso}"> 
                <table cellpadding="0" cellspacing="0" border="0" style="width: 100% ; cursor:pointer">
                    <tr>
                        <td class="tableHeader"><strong>Lista de estudiantes</strong></td>
                    </tr>
                    <tr>
                        <td class="gridContent">
                            <table cellpadding="0" cellspacing="0" border="0">
                                <tr>
                                    <td class="text-label"><button class="button-normal"  onclick="javascript:setImprimirExcel()" title="Imprime la lista en formato excel">Imprimir Excel</button>&nbsp;</td>
                                    <td class="text-label">&nbsp;&nbsp;Curso:</td>
                                    <td style="color:gray">${curso.curso} de ${curso.ciclo}</td>
                                    <td class="text-label">&nbsp;&nbsp;Inscripcion:</td>
                                    <td style="color:gray">${id_gestion}</td>
                                    <td class="text-label">&nbsp;&nbsp;Cantidad de inscritos:</td>
                                    <td style="color:gray">${curso.numEstudiantes}</td>
                                </tr>
                            </table>
                            <div id="resultlayer1">
                                <table width="100%" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF">
                                    <tr>
                                        <td class="gridHeaders" width="10%">Nro.</td>
                                        <td class="gridHeaders" width="10%">C&oacute;digo</td>
                                        <td class="gridHeaders" width="50%">Apellidos y nombres</td>
                                        <td class="gridHeaders" width="50%">Estado de pensiones</td>
                                    </tr>
                                    <c:if test="${empty curso.estudiantes}">
                                        <tr>
                                            <td class="gridData" colspan="4">No se han encontrado elementos.</td>
                                        </tr>
                                    </c:if>
                                    <c:forEach varStatus="i" var="item" items="${curso.estudiantes}">
                                        <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)" onclick="selectEstudiante('${item.id_familia}')">
                                            <td align="center" class="gridData"><c:out value="${i.count}"/> </td>
                                            <td class="gridData">${item.codigo}</td>
                                            <td class="gridData">${item.paterno} ${item.materno} ${item.nombres}</td>
                                            <td class="gridData" align="center">
                                                <c:if test="${item.estado_asignacion_pensiones=='asignada'}"><img src="imagenes/iconos_sigaa/asignada.png"></c:if>
                                                <c:if test="${item.estado_asignacion_pensiones==null}"><img src="imagenes/iconos_sigaa/sinasignar.png"></c:if>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </div>
                        </td>
                    </tr>
                </table>
            </c:if>

            <c:if test="${empty persona}">
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
                                        <td>E-mail resp. pago: </td>
                                        <td class="textColor" colspan="3"><a href="mailto:${family.e_mailRP}">${family.e_mailRP}</a></td>
                                    </tr>
                                    <tr bgcolor="#ffffff" style="cursor:pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                        <td>Observaci&oacute;n :</td>
                                        <td class="textColor" colspan="3">${family.observacion}</td>
                                    </tr>
                                </table>
                            </td>
                            <td width="50%"rowspan="7" valign="top" class="gridContent">
                                <table cellpadding="0" cellspacing="0" border="0" style="width:100%" class="menuHead"  style="cursor:pointer" bgcolor="#ffffff">
                                    <tr>
                                        <td colspan="3" class="tableHeader" >Estudiantes Inscritos (Gesti&oacute;n ${gestion.id_gestion})</td>
                                    </tr>
                                    <tr>
                                        <td class="gridHeaders" width="15%">Nro. Hijo</td>
                                        <td class="gridHeaders" width="60%">NOMBRES Y APELLIDOS</td>
                                        <td class="gridHeaders" width="25%">NIVEL</td>
                                    </tr>
                                    <c:if test="${empty alumnos}">
                                        <tr>
                                            <td class="gridData" colspan="3" align="center" style="color:red">No se han encontrado elementos.</td>
                                        </tr>
                                    </c:if>
                                    <c:forEach varStatus="i" var="item" items="${alumnos}">
                                        <tr onclick="selectPersona('<c:out value="${item.id_inscripcion}"/>')" style="cursor: pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                            <td class="gridData" align="center">${i.count}</td>
                                            <td class="gridData"><c:out value="${item.nombres} ${item.paterno} ${item.materno}"/></td>
                                            <td class="gridData">${item.curso}</td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </td>
                        </tr>
                    </table>
                </c:if>
            </c:if>

            <c:if test="${!empty newPagos}">
                <table border="0" cellpadding="0" cellspacing="0" width="100%"  align="center" style="cursor:pointer">
                    <tr>
                        <td class="tableHeader">MENSUALIDADES (Gesti&oacute;n ${gestion.id_gestion})</td>
                    </tr>
                    <tr>
                        <td class="gridData" align="right">
                            <button class="button-normal" onclick="setAnularAsignacionPagoPensiones()"><img width="11px" src="imagenes/iconos_sigaa/eliminar.png">&nbsp;&nbsp;Anular asignaci&oacute;n actual de pensiones.</button>
                        </td>
                    </tr>
                    <tr>
                        <td class="gridData">
                            <form action="<c:url value="/asignacionPagos.do"/>" method="post" name="frmRegistro">
                                <table border="0" cellpadding="0" cellspacing="0" width="100%"  align="center" style="cursor:pointer">
                                    <!--tr>
                                        <td colspan="3" class="tableHeader">MENSUALIDADES (Gesti&oacute;n ${gestion.id_gestion})</td>
                                    </tr-->
                                    <c:if test="${empty pagoPensiones}">
                                        <tr>
                                            <td colspan="3"><font style="color:red"> Ning&uacute;n estudiante cuenta con pensiones ...</font></td>
                                        </tr>
                                    </c:if>
                                    <c:if test="${!empty pagoPensiones}">
                                        <tr>
                                            <td class="gridHeaders" width="3%">NRO.</td>
                                            <td class="gridHeaders" width="30%">ESTUDIANTE</td>
                                            <td class="gridHeaders" width="67%">NIVEL</td>
                                        </tr>
                                        <c:forEach varStatus="i" var="item" items="${pagoPensiones}">
                                            <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)" bgcolor="#ffffff">
                                                <td class="gridData" align="center">${i.count}</td>
                                                <td class="gridData">
                                                    <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                                                        <tr>
                                                            <td align="center" style="color:red"><c:if test="${item.nombre_foto!=null}"><img src="documentos/fotos/${item.nombre_foto}" height="100px"/></c:if><c:if test="${item.nombre_foto=='' || item.nombre_foto==null}"><img src="imagenes/iconos_sigaa/ojo_marco.gif" height="100px"/></c:if></td>
                                                        </tr>
                                                        <tr>
                                                            <td align="center" title="C&oacute;digo"><strong>${item.codigo}</strong></td>
                                                        </tr>
                                                        <tr>
                                                            <td align="center" title="Nombres y apellidos">${item.nombres} ${item.paterno} ${item.materno}</td>
                                                        </tr>
                                                    </table>
                                                </td>
                                                <td class="gridData" valign="top">&nbsp;&nbsp;<img src="imagenes/iconos_sigaa/indicador.png">&nbsp;&nbsp;<strong> ${item.curso} </strong>
                                                    <c:if test="${item.editable==true}">
                                                        <table width="100%" border="0">
                                                            <tr class="gridHeaders">
                                                                <td width="20%"></td>
                                                                <td width="35%"></td>
                                                                <td width="35%"></td>
                                                                <td width="10%"></td>
                                                            </tr>
                                                            <tr class="gridHeaders">
                                                                <td><strong>Cuotas</strong></td>
                                                                <td><strong>T. Anual</strong></td>
                                                                <td colspan="2">
                                                                    <table width="100%" border="0">
                                                                        <tr>
                                                                            <td width="50%" title="Descuentos y referencia"><strong>Desc/Ref</strong></td>
                                                                            <td width="50%" title="Descuento por beca"><strong>Beca</strong></td>
                                                                        </tr>
                                                                    </table>
                                                                </td>
                                                            </tr>
                                                            <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)" bgcolor="#ffffff">
                                                                <td valign="top">
                                                                    <select name="cuota_${item.id_estudiante}" id="id_cuota_${item.id_estudiante}" class="text-field" style="width:100px" onchange="setDescuentos(this.value,'${i.count}','${item.id_estudiante}');setObservacion('${item.id_estudiante}')">
                                                                        <c:forEach var="it" items="${cuotas}">
                                                                            <option value="${it.id}"<c:if test="${it.id== item.cuota}"> selected </c:if>/> <c:if test="${it.id==1}">${it.id} cuota</c:if><c:if test="${it.id!=1}">${it.id} cuotas</c:if>
                                                                        </c:forEach>
                                                                    </select>
                                                                </td>
                                                                <td valign="top" align="center">
                                                                    <input class="text-field" type="text" value="${item.monto_inicial+item.monto_total}" maxlength="4" style="width:40px" onfocus="this.blur()">Bs.
                                                                    <input type="hidden" id="cInicial-${item.id_estudiante}" name="cInicial-${item.id_estudiante}" value="${item.monto_inicial}">
                                                                    <input type="hidden" id="cTotal-${item.id_estudiante}" name="cTotal-${item.id_estudiante}" value="${item.monto_total}">
                                                                </td>
                                                                <td valign="top" colspan="2">
                                                                    <table width="100%" border="0">
                                                                        <tr>
                                                                            <td width="25%" align="center"><input class="text-field" type="text" id="descuento-${item.id_estudiante}" name="descuento-${item.id_estudiante}" value="${item.descuento}" maxlength="2" style="width:15px" onblur="setObservacion('${item.id_estudiante}')"/>%</td>
                                                                            <td width="25%" align="center"><div style="color:red" id="desc-${item.id_estudiante}">0%</div></td>
                                                                            <td width="50%" align="center"><input class="text-field" type="text" id="beca-${item.id_estudiante}" name="beca-${item.id_estudiante}" value="${item.beca}" maxlength="3" style="width:23px" onblur="setObservacion('${item.id_estudiante}')"/>%</td>
                                                                        </tr>
                                                                    </table>
                                                                </td>
                                                            </tr>
                                                            <tr class="gridHeaders">
                                                                <td><strong>Meses</strong></td>
                                                                <td><strong>Monto por cuota/s</strong></td>
                                                                <td><strong>Tipo de cuota/s</strong></td>
                                                                <td><strong>Total</strong></td>
                                                            </tr>
                                                            <tr bgcolor="silver" align="center">
                                                                <td align="left" style="color:gray"><img src="imagenes/cxc.png" width="12px"> FEBRERO</td>
                                                                <td style="color:navy">${item.monto_inicial} Bs.</td>
                                                                <td style="color:navy"> Cuota inicial<br> ${item.monto_inicial} Bs.</td>
                                                                <td rowspan="2"><div style="color:navy" id="total_anual-${item.id_estudiante}"/></td>
                                                            </tr>
                                                            <tr bgcolor="silver">
                                                                <td><div id="item-meses-${item.id_estudiante}"></div></td>
                                                                <td>
                                                                    <div style="color:navy" id="total_pago-${item.id_estudiante}"></div>
                                                                    <!-- div style="color:navy" id="total_pago_anual-${item.id_estudiante}"></div-->
                                                                </td>
                                                                <td align="center" style="color:navy"> Mensualidad/es<br><div style="color:navy" id="total_pago_anual-${item.id_estudiante}"></div></td>
                                                            </tr>
                                                        </table>
                                                        <script type="text/javascript">setObservacion('${item.id_estudiante}')</script>
                                                        <script type="text/javascript">setDescuentos('${item.cuota}','${i.count}','${item.id_estudiante}')</script>
                                                        <input type="hidden" name="id_pago_pension-${item.id_estudiante}" value="${item.id_pago_pension}">
                                                    </c:if>
                                                    <c:if test="${item.editable==false}">
                                                        <table width="100%" border="0">
                                                            <tr class="gridHeaders">
                                                                <td width="20%"><strong>Cuotas</strong></td>
                                                                <td width="40%"><strong>Pensiones sin descuento</strong></td>
                                                                <td width="40%"><strong>Pensiones con descuentos iniciales</strong></td>
                                                            </tr>
                                                            <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)" bgcolor="#ffffff">
                                                                <td align="center">${item.cuota}</td>
                                                                <td><strong>Monto anual</strong> : ${item.monto_total+item.monto_inicial} Bs</td>
                                                                <td>
                                                                    <strong>Descuento</strong> : ${item.descuento} %<br>
                                                                    <strong>Beca</strong> : ${item.beca} %<br>
                                                                    <strong>Monto anual</strong> : ${item.pension_total+item.monto_inicial} Bs <br>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td colspan="3">
                                                                    <table width="100%" bgcolor="#FFFFFF">
                                                                        <tr class="gridHeaders">
                                                                            <td width="25%" title="Meses de pago establecida"><strong>Cuotas</strong></td>
                                                                            <td width="15%" title="Montos establecidas"><strong>Monto</strong></td>
                                                                            <td width="15%" title="Montos depositadas"><strong>Monto dep</strong></td>
                                                                            <td width="10%" title="Interes"><strong>Interes</strong></td>
                                                                            <td width="10%" title="Observaciones"><strong>Obs.</strong></td>
                                                                            <td width="15%" title="Fecha de deposito"><strong>Fecha deposito</strong></td>
                                                                            <td width="10%" title="Estado de los pagos"><strong>Estado</strong></td>
                                                                        </tr>
                                                                        <c:forEach varStatus="k" var="dep" items="${item.depositosAsignadas}">
                                                                            <c:if test="${dep.estado=='activo'}">
                                                                                <tr bgcolor="#E8F3FF" onmouseover="onRowover(this)" onmouseout="onRowout(this)" >
                                                                                    <td title="${dep.sfecha_ini} - ${dep.sfecha_fin}">${dep.nro_cuota}&nbsp;${dep.mes}</td>
                                                                                    <td>
                                                                                        <input type="hidden" name="id-${dep.id_dep_asignada}" value="${dep.id_dep_asignada}">
                                                                                        <input type="text" name="monto-${dep.id_dep_asignada}" value="${dep.monto}" style="width: 50px">
                                                                                    </td>
                                                                                    <td align="center">
                                                                                        <input type="text" name="monto_dep-${dep.id_dep_asignada}" value="${dep.monto_dep}" style="width: 50px" <c:if test="${dep.monto_dep>0 || dep.estado_cuota=='cancelado'}">onfocus="this.blur()"</c:if>>
                                                                                    </td>
                                                                                    <td align="center">${dep.interes}</td>
                                                                                    <td align="center" style="font-size: 11px">
                                                                                        <a title="${dep.observacion}"><img onclick="openObservacion('${dep.id_dep_asignada}','${dep.observacion}')" width="15px" src="imagenes/iconos_sigaa/notas.png"></a>
                                                                                    </td>
                                                                                    <td align="center">${dep.sfecha_dep}</td>
                                                                                    <td align="center" style="font-size: 11px">
                                                                                        <c:if test="${dep.estado_cuota=='cancelado'}">
                                                                                            <img title="Cancelado" width="15px" src="imagenes/iconos_sigaa/activo_si.png">
                                                                                        </c:if>
                                                                                        <c:if test="${dep.estado_cuota=='pendiente'}">
                                                                                            <img title="No cancelado" width="15px" src="imagenes/iconos_sigaa/activo_no.png">&nbsp;&nbsp;
                                                                                            <!--input type="checkbox" name="estado_cuota-${dep.id_dep_asignada}"-->
                                                                                        </c:if>
                                                                                    </td>
                                                                                </tr>
                                                                            </c:if>
                                                                            <c:if test="${dep.estado==null}">
                                                                                <tr bgcolor="#FDE1E3" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                                                                    <td  title="${dep.sfecha_ini} - ${dep.sfecha_fin}">${dep.nro_cuota}.&nbsp;${dep.mes}</td>
                                                                                    <td>
                                                                                        <input type="hidden" name="id-${dep.id_dep_asignada}" value="${dep.id_dep_asignada}">
                                                                                        <input type=text" name="monto-${dep.id_dep_asignada}" value="${dep.monto}" style="width: 50px" >
                                                                                    </td><!--disabled-->
                                                                                    <td align="center">
                                                                                        <input type="text" name="monto_dep-${dep.id_dep_asignada}" value="${dep.monto_dep}" style="width: 50px" <c:if test="${dep.monto_dep>0|| dep.estado_cuota=='cancelado'}">onfocus="this.blur()"</c:if>>
                                                                                    </td>
                                                                                    <td align="center">${dep.interes}</td>
                                                                                    <td align="center" style="font-size: 11px">
                                                                                        <a title="${dep.observacion}"><img onclick="openObservacion('${dep.id_dep_asignada}','${dep.observacion}')" width="15px" src="imagenes/iconos_sigaa/notas.png"></a>
                                                                                    </td>
                                                                                    <td align="center" title="${dep.sdfecha_dep}">${dep.sfecha_dep}</td>
                                                                                    <td align="center" style="font-size: 11px">
                                                                                        <c:if test="${dep.estado_cuota=='cancelado'}">
                                                                                            <img title="Cancelado" width="15px" src="imagenes/iconos_sigaa/activo_si.png">
                                                                                        </c:if>
                                                                                        <c:if test="${dep.estado_cuota=='pendiente'}">
                                                                                            <img title="No cancelado" width="15px" src="imagenes/iconos_sigaa/activo_no.png">&nbsp;&nbsp;
                                                                                            <!--input type="checkbox" name="estado_cuota-${dep.id_dep_asignada}"-->
                                                                                        </c:if>
                                                                                    </td>
                                                                                </tr>
                                                                            </c:if>
                                                                        </c:forEach>
                                                                        <tr style="color:white" bgcolor="gray" align="center">
                                                                            <td align="center"><strong>TOTALES</strong></td>
                                                                            <td><strong>${item.monto_pagar}</strong></td>
                                                                            <td><strong>${item.monto_dep}</strong></td>
                                                                            <td><strong>${item.interes}</strong></td>
                                                                            <td colspan="3" bgcolor="white"></td>
                                                                        </tr>
                                                                    </table>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </c:if>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        <tr>
                                            <td colspan="3" align="center">
                                                <input type="hidden" name="id_fam" value=${family.id_familia}>
                                                <input type="hidden" name="regPagos" value="_regPagos">
                                                <input type="hidden" name="id_gestion" value="${id_gestion}">
                                                <input type="hidden" name="id_curso" value="${id_curso}">
                                                <button class="button-normal" type="submit"><img width="11px" src="imagenes/iconos_sigaa/guardar.png">&nbsp;&nbsp;Guardar cambios</button>
                                            </td>
                                        </tr>
                                    </c:if>
                                </table>
                            </form>

                            <table border="0" cellpadding="0" cellspacing="0" width="100%"  align="center" style="cursor:pointer">
                                <tr>
                                    <td colspan="3" class="tableHeader">SERVICIOS (Gesti&oacute;n ${gestion.id_gestion})</td>
                                </tr>
                                <tr>
                                    <td colspan="3">
                                        <table border="0" cellpadding="0" cellspacing="0" width="100%" class="menuHead" align="center">
                                            <tr>
                                                <td class="gridHeaders" width="3%">NRO.</td>
                                                <td class="gridHeaders"width="30%">ESTUDIANTE</td>
                                                <td class="gridHeaders" width="67%" colspan="7">NIVEL</td>
                                            </tr>
                                            <c:forEach varStatus="j" var="item_est" items="${alumnos}">
                                                <tr style="cursor:pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)" bgcolor="#ffffff">
                                                    <td class="gridData">${item_est.nro_hijo}
                                                    <td class="gridData">
                                                        <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                                                            <tr>
                                                                <td align="center" style="color:red"><c:if test="${item_est.nombre_foto!=null}"><img src="documentos/fotos/${item_est.nombre_foto}" height="100px"/></c:if><c:if test="${item_est.nombre_foto=='' || item_est.nombre_foto==null}"><img src="imagenes/iconos_sigaa/ojo_marco.gif" height="100px"/></c:if></td>
                                                            </tr>
                                                            <tr>
                                                                <td align="center" title="C&oacute;digo"><strong>${item_est.codigo}</strong></td>
                                                            </tr>
                                                            <tr>
                                                                <td align="center" title="Nombres y apellidos">${item_est.nombres} ${item_est.paterno} ${item_est.materno}</td>
                                                            </tr>
                                                        </table>
                                                    </td>
                                                    <td class="gridData" valign="top">&nbsp;&nbsp;<img src="imagenes/iconos_sigaa/indicador.png">&nbsp;&nbsp;<strong>${item_est.curso}</strong>&nbsp;&nbsp;&nbsp;&nbsp;
                                                        <c:if test="${empty noServicio}">
                                                            <button class="button-normal" type="button" onclick="NewsServicios('${item_est.id_inscripcion}','${family.id_familia}','${item_est.nombres}','${item_est.curso}')"><img width="11px" src="imagenes/iconos_sigaa/nuevo.png">&nbsp;&nbsp;Agregar servicio</button>
                                                            </c:if>
                                                        <table border="0" width="100%" onmouseover="onRowover(this)" onmouseout="onRowout(this)" bgcolor="#ffffff">
                                                            <tr class="gridHeaders">
                                                                <td width="40%" colspan="2" style="color:black" align="center"><strong>SERVICIOS</strong></td>
                                                                <td width="10%" style="color:black" align="center"><strong>MONTO</strong></td>
                                                                <td width="50%" style="color:black" align="center"><strong>ESTADO</strong></td>
                                                            </tr>
                                                            <c:forEach varStatus="j" var="item" items="${pagoServicios}">
                                                                <c:if test="${item_est.id_inscripcion==item.id_inscripcion}">
                                                                    <tr style="cursor:pointer"  onmouseover="onRowover(this)" onmouseout="onRowout(this)" bgcolor="#ffffff">
                                                                        <td colspan="2" valign="top">&nbsp;<img src="imagenes/iconos_sigaa/etiqueta_azul.png">&nbsp; ${item.tipo_servicio}</td>
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
                                                                                            <button class="button-normal" type="button" onclick="setPagarServicio('${item.id_familia}','${item.id_inscripcion}','${item.id_servicio}')">
                                                                                                <img width="11px" src="imagenes/iconos_sigaa/guardar.png">&nbsp;&nbsp;Pagar servicio
                                                                                            </button>&nbsp;
                                                                                            <button class="button-normal" title="Eliminar este servicio" type="button" onclick="setDeleteServicio('${item.id_familia}','${item.id_inscripcion}','${item.id_servicio}')">
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
                                                            <tr><td colspan="4"><img src="imagenes/pixel_gry.gif" height="1" width="100%"></td></tr>
                                                            <tr style="cursor:pointer">
                                                                <td align="right" valign="top" colspan="2" style="color:teal">Monto total&nbsp;&nbsp;:&nbsp;&nbsp;</td>
                                                                <td style="color:black;" valign="top" align="center"><strong><div id="tmonto-${item_est.id_inscripcion}"></div></strong></td>
                                                                <td style="color:black;">
                                                                    <table width="100%">
                                                                        <tr>
                                                                            <td><div id="tcancelado-${item_est.id_inscripcion}"></div></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td><div id="tsaldo-${item_est.id_inscripcion}"></div></td>
                                                                        </tr>
                                                                    </table>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            <script type="text/javascript">getTotales()</script>
                                        </table>
                                    </td>
                                </tr>
                            </table>
                            <table border="0" cellpadding="0" cellspacing="0" width="100%"  align="center" style="cursor:pointer">
                                <tr>
                                    <td colspan="3" class="tableHeader">PAGOS EN COLEGIO (Pagos pendientes de la gesti&oacute;n ${gestion.id_gestion-1})</td>
                                </tr>
                                <tr>
                                    <td colspan="3">
                                        <table border="0" cellpadding="0" cellspacing="0" width="100%" class="menuHead" align="center">
                                            <tr>
                                                <td class="gridHeaders" width="3%">NRO.</td>
                                                <td class="gridHeaders"width="30%">ESTUDIANTE</td>
                                                <td class="gridHeaders" width="67%" colspan="7">NIVEL</td>
                                            </tr>
                                            <c:forEach varStatus="j" var="item_est" items="${pagos_colegio}">
                                                <tr style="cursor:pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)" bgcolor="#ffffff">
                                                    <td class="gridData">${item_est.nro_hijo}
                                                    <td class="gridData">
                                                        <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                                                            <tr>
                                                                <td align="center" style="color:red"><c:if test="${item_est.nombre_foto!=null}"><img src="documentos/fotos/${item_est.nombre_foto}" height="100px"/></c:if><c:if test="${item_est.nombre_foto=='' || item_est.nombre_foto==null}"><img src="imagenes/iconos_sigaa/ojo_marco.gif" height="100px"/></c:if></td>
                                                            </tr>
                                                            <tr>
                                                                <td align="center" title="C&oacute;digo"><strong>${item_est.codigo}</strong></td>
                                                            </tr>
                                                            <tr>
                                                                <td align="center" title="Nombres y apellidos">${item_est.nombres} ${item_est.paterno} ${item_est.materno}</td>
                                                            </tr>
                                                        </table>
                                                    </td>
                                                    <td class="gridData" valign="top">&nbsp;&nbsp;<img src="imagenes/iconos_sigaa/indicador.png">&nbsp;&nbsp;<strong>${item_est.curso}</strong>&nbsp;&nbsp;&nbsp;&nbsp;
                                                        <c:if test="${item_est.pagoCole.id_inscripcion!=null}">
                                                            <table border="0" width="100%" onmouseover="onRowover(this)" onmouseout="onRowout(this)" bgcolor="#ffffff" style="color: black">
                                                                <tr class="gridHeaders">
                                                                    <td width="40%" align="center"><strong>DESCRIPCION</strong></td>
                                                                    <td width="10%" align="center"><strong>MONTO</strong></td>
                                                                    <td width="25%" align="center"><strong>FACTURA</strong></td>
                                                                    <td width="25%" align="center"><strong>ESTADO</strong></td>
                                                                </tr>
                                                                <tr>
                                                                    <td>${item_est.pagoCole.descripcion}</td>
                                                                    <td align="right">${item_est.pagoCole.monto}</td>
                                                                    <td align="right">${item_est.pagoCole.nro_factura}</td>
                                                                    <td align="center">
                                                                        <c:if test="${item_est.pagoCole.cancelado==true}"><font color="green"><strong>Cancelado</strong></font><br>${item_est.pagoCole.sfecha_pago}</c:if>
                                                                        <c:if test="${item_est.pagoCole.cancelado==false}"><font color="red"><strong>Pendiente</strong></font></c:if>
                                                                    </td>
                                                                </tr>
                                                            </table>
                                                        </c:if>
                                                        <c:if test="${item_est.pagoCole.id_inscripcion==null}">
                                                            <table border="0" width="100%" onmouseover="onRowover(this)" onmouseout="onRowout(this)" bgcolor="#ffffff" style="color: black">
                                                                <tr>
                                                                    <td width="100%" align="center" style="color: green"><strong>Sin deudas pendientes....</strong></td>
                                                                </tr>
                                                            </table>
                                                        </c:if>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            <script type="text/javascript">getTotales()</script>
                                        </table>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
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
        <div id="selectNewsServicios" style="display:none">
            SERVICIOS DISPONIBLES
            <div id="datos-est"></div>
            <form id="addform" action="<c:url value="/asignacionPagos.do"/>" method="post">
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
                            <td><img src="imagenes/iconos_sigaa/etiqueta_azul.png">&nbsp;&nbsp;${items.tipo_servicio}</td>
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
                <input type="hidden" name="id_gestion" value="${id_gestion}">
                <input type="hidden" name="id_curso" value="${id_curso}">
                <input type="hidden" name="id_insc">
                <input type="hidden" name="search">
                <input type="hidden" name="opcion" value="_newService">
            </form>
        </div>
        <div id="openObservacion" style="display:none">
            <form id="formAdd" action="<c:url value="/asignacionPagos.do"/>" method="post">
                <table align="center" style="width:100%" class="gridContent">
                    <tr>
                        <td> 
                            Este m&oacute;dulo permite modificar las observaciones de la cuota.<font color="red"> Nota: No usar la tecla ENTER.</font>
                        </td>
                    </tr>
                    <tr>
                        <td align="center"><textarea style="width: 80%" rows="5" name="observacion"></textarea></td>
                    </tr>
                    <tr>
                        <td align="center" >
                            <button name="aceptar" class="button-normal" style="width:70px"><img width="11px" src="imagenes/iconos_sigaa/guardar.png"> Aceptar</button>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <button class="button-normal" style="width:70px" onclick="javascript:_closeObs()"><img width="11px" src="imagenes/iconos_sigaa/close.png"> Cancelar</button>
                        </td>
                    </tr>
                    <input type="hidden" name="id_curso" value="${id_curso}">
                    <input type="hidden" name="id_gestion" value="${id_gestion}">
                    <input type="hidden" name="search" value="${search}">
                    <input type="hidden" name="opcion" value="_regObs">
                    <input type="hidden" name="id_dep_asignada">
                </table>
            </form>
        </div>
        <div id="confirmarAnular" style="display:none">
            <p>Confirma anular lo establecido en la asignacion de pago de pensiones?. Si es asi pulse el boton aceptar y si no cancelar.</p>
            <img width="15px" src="imagenes/iconos_sigaa/activo_pe.png">NOTA: Al confirmar esta operacion, se borrara toda la informacion referente al pago de pensiones de esta gestion.
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
    </body>
</html>
