<%-- 
    Document   : RegistrarCalificaciones
    Created on : 06-sep-2009, 19:13:04 
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
        <link rel="stylesheet" type="text/css" href="css/module.css" media="screen" />
        <script type="text/javascript" src="js/prototype.js"></script>
        <script type="text/javascript" src="js/jquery-1.7.2.js"></script>
        <script type="text/javascript" src="js/Tips.js"></script>
        <link rel="stylesheet" href="ui/themes/base/jquery.ui.all.css">
        <script src="ui/jquery-ui-1.8.21.custom.js"></script>
        <script type="text/javascript">
            window.onload=function(){enableTooltips()};
        </script>
        <script type="text/javascript">
            var ests = new Array();
            <c:forEach varStatus="i" var="est" items="${paralelo.estudiantes}">ests[${i.index}]='${est.id_estudiante}';</c:forEach>
                var evas = new Array();
            <c:forEach varStatus="i" var="eva" items="${curso.evaluaciones}">evas[${i.index}]='${eva.id_evaluacion}';</c:forEach>
                var __evas = new Array();
            <c:forEach var="eva" items="${paralelo.evaluaciones}">__evas['${eva.id_evaluacion}']='${eva.nota}';</c:forEach>

                function onRowover(elem) {
                    elem.className='colover';
                }
                function onRowout(elem) {
                    elem.className='colout';
                }
                                
                function setObservacionEst(e) {
                    var sum = 0;
                    var suma = 0;
                    for (j=0; j<evas.length; j++) {
                        sum=0;
                        nota = $(evas[j]+'-'+e).value;
                        if(j!=3){
                            dps = $('dps-'+evas[j]+'-'+e).value;
                            suma = suma + parseInt(nota)+parseInt(dps);
                        }else{
                            dps='0';
                        }
                        if(j>=3){
                            if(parseInt(nota)> 70 || parseInt(dps)> 10 || isNaN(parseInt(nota))  || isNaN(parseInt(dps))){
                                $('sum-'+e).innerHTML = '<span style="color:RED;cursor:pointer" title="Debe Ingresar valores definidos para cada evaluaci&oacute;n">ERROR!!!</b></span>';
                                $('sum-'+evas[j]+'-'+e).innerHTML = '<span style="color:RED;cursor:pointer" title="error"><b>&notin;</b></span>';
                                return false;
                            }
                        }else{
                            if(parseInt(nota)> 60 || parseInt(dps)> 10 || isNaN(parseInt(nota))  || isNaN(parseInt(dps))){
                                $('sum-'+e).innerHTML = '<span style="color:RED;cursor:pointer" title="Debe Ingresar valores definidos para cada evaluaci&oacute;n">ERROR!!!</b></span>';
                                $('sum-'+evas[j]+'-'+e).innerHTML = '<span style="color:RED;cursor:pointer" title="error"><b>&notin;</b></span>';
                                return false;
                            }
                        }

                        sum = sum + parseInt(nota)+parseInt(dps);
                        if (sum < 26 || sum > 70) {
                            $('sum-'+evas[j]+'-'+e).innerHTML = '<span style="color:GREEN;cursor:pointer">Mayor a 25</span>';
                        } else if (sum == 0) {
                            $('sum-'+evas[j]+'-'+e).innerHTML = '<span style="color:SILVER;cursor:pointer" title="abandono"><b>'+sum+'</b></span>';
                        } else if (sum < 36) {
                            $('sum-'+evas[j]+'-'+e).innerHTML = '<span style="color:RED;cursor:pointer" title="reprobado"><b>'+sum+'</b></span>';
                        } else if (sum >= 36) {
                            $('sum-'+evas[j]+'-'+e).innerHTML = '<span style="color:GREEN;cursor:pointer" title="aprobado"><b>'+sum+'</b></span>';
                        }
                    }
                    var suma_pf=0;
                    if(j>=4){
                        if(parseInt($(evas[3]+'-'+e).value)>0){
                            nota = $(evas[3]+'-'+e).value;
                            suma_pf=suma/3;
                            suma_pf=(suma_pf+parseInt(nota)+parseInt("0"))/2;
                            $('dps-'+evas[3]+'-'+e).innerHTML = '<span>0</span>';
                            $('sum-'+evas[3]+'-'+e).innerHTML = '<span style="color:black;cursor:pointer"><b>'+(parseInt(nota)+parseInt(dps))+'</b></span>';
                        }else{
                            $('dps-'+evas[3]+'-'+e).innerHTML = '<span style="color:black">0</span>';
                        }
                    }
                    if(evas.length==4){
                        suma=suma/3;
                    }else{
                        suma=suma/evas.length;
                    }
                    
                    if (Math.round(suma) < 26 || Math.round(suma) > 70) {
                        $('sum-'+e).innerHTML = '<span style="color:GREEN;cursor:pointer"  title="error">Mayor a 25</span>';
                    } else if (Math.round(suma) == 0) {
                        $('sum-'+e).innerHTML = '<span style="color:#CCCCCC;cursor:pointer" title="abandono"><b>'+Math.round(suma)+'</b></span>';
                    } else if (Math.round(suma) < 36) {
                        $('sum-'+e).innerHTML = '<span style="color:RED;cursor:pointer" title="reprobado">'+Math.round(suma)+'</span>';
                    } else if (Math.round(suma) >= 36) {
                        $('sum-'+e).innerHTML = '<span style="color:GREEN;cursor:pointer" title="aprobado">'+Math.round(suma)+'</span>';
                    }

                    if(j>=4){
                        if (Math.round(suma_pf) < 26 || Math.round(suma_pf) > 70) {
                            $('obs-'+e).innerHTML='<span style="color:GREEN;cursor:pointer">Mayor a 25</span>';
                        } else if (Math.round(suma_pf) == 0) {
                            $('obs-'+e).innerHTML='<span style="color:#CCCCCC;cursor:pointer"><b>'+Math.round(suma_pf)+'</b></span>';
                        } else if (Math.round(suma_pf) < 36) {
                            $('obs-'+e).innerHTML='<span style="color:RED;cursor:pointer" title="reprobado"><b>'+Math.round(suma_pf)+'</b></span>';
                        } else if (Math.round(suma_pf) >= 36) {
                            $('obs-'+e).innerHTML='<span style="color:GREEN;cursor:pointer" title="aprobado"><b>'+Math.round(suma_pf)+'</b></span>';
                        }
                    }
                }
                function openWindow(form,title,w,h){
                    var addFormwin=dhtmlmodal.open('win'+form, 'div', form, title, 'width='+w+'px,height='+h+'px,left=100px,top=100px,resize=1,scrolling=1');
                    addFormwin.moveTo('middle', 'middle');
                    return addFormwin;
                }
                var printWin = null;
                function openPrintableDocument() {
                    printWin = openWindow('documentoPdF','Imprimir...','650','340');
                    document.getElementById('documentview').src = 'documentos/docentes/${pdf}';
                    printWin.moveTo('middle', 'middle');
                }
                function imprimirNotasPdf(){
                    var printResultado=dhtmlmodal.open('windowMAQV', 'iframe', '<c:url value="/registrarCalificaciones.do"/>?&imprimirPdf=_imprimirPdf&id_curso=${curso.id_curso}&id_materia=${materia.id_materia}&id_curso_materia=${id_curso_materia}', 'Calificaciones del curso ${curso.curso} de ${curso.ciclo}', 'width=650px,height=340px,left=100px,top=100px,resize=1,scrolling=1,center=1');
                    printResultado.moveTo('middle', 'middle');
                }
                function imprimirListaPdf(){
                    var printResultado=dhtmlmodal.open('windowMAQV', 'iframe', '<c:url value="/registrarCalificaciones.do"/>?&imprimirListaPdf=_imprimirListaPdf&id_curso=${curso.id_curso}&id_materia=${materia.id_materia}&id_curso_materia=${id_curso_materia}', 'Nómina de estudiantes curso ${curso.curso} de ${curso.ciclo}', 'width=650px,height=340px,left=100px,top=100px,resize=1,scrolling=1,center=1');
                    printResultado.moveTo('middle', 'middle');
                }
                var _inputs = new Array();
                var _counter = 0;

                function selectMateria(id_materia,id_curso,id_curso_materia) {
                    window.location='<c:url value="/registrarCalificaciones.do"/>?id_curso='+id_curso+'&id_materia='+id_materia+'&id_curso_materia='+id_curso_materia;
                }
                function updateEvaluacion(id,id_curso) {
                    openWindow('definirEvaluacionForm','Definir evaluacion','780','180');
                    var o = __evas[id];
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
                    $('addevaform').id_evaluacion.value = id;
                }
                function saveNotas(){
                    document.formNotas.submit()
                }

                var closeDialog = null
                function openCloseDialog(form,title,w,h) {
                    closeDialog=dhtmlmodal.open('win'+form, 'div', form, title, 'width='+w+'px,height='+h+'px,left=100px,top=100px,resize=0,scrolling=0');
                    closeDialog.moveTo('middle', 'middle');
                }
                function cancelClose() {
                    closeDialog.hide();
                }
                function confirmClose() {
                    window.location='<c:url value="/registrarCalificaciones.do"/>?id_curso_materia=${curso.cursomateria.id_curso_materia}&id_gestion=${curso.cursomateria.id_gestion}&id_curso=${curso.cursomateria.id_curso}&id_materia=${curso.cursomateria.id_materia}&cerrarmateria=_cerrarmateria';
                }
                function downloadExcel() {
                    window.location='<c:url value="/registrarCalificaciones.do"/>?&downloadExcel=_downloadExcel&id_curso=${curso.id_curso}&id_materia=${materia.id_materia}&id_curso_materia=${id_curso_materia}&periodo=${periodo}&cant_dps=${cant_dps}';
                }

                function setActualizaNotaPFIni(idestudiante){
                    var periodo='${periodo}';
                    var cant_eva='${cant_evaluaciones}';
                    var idcursomateria='${id_curso_materia}';
                    if(cant_eva>0){
                        var pa=0;
                        var ref=0;
                        for(var i=1;i<= parseInt(cant_eva);i++){
                            var nota_s=$("input[name='"+idcursomateria+"-E"+i+"-"+idestudiante+"']").val();
                            var dps_s=$("input[name='dps-"+idcursomateria+"-E"+i+"-"+idestudiante+"']").val();
                            //console.log("input[name='"+idcursomateria+"E"+i+"-"+idestudiante+"']=>"+nota_s);
                            //console.log("dps="+dps_s);
                            if(parseInt(periodo)==2){
                                if(i<=4)
                                    pa=pa+parseInt(nota_s)+parseInt(dps_s);
                                if(i==5)
                                    ref=parseInt(nota_s)+parseInt(dps_s);
                            }else if(parseInt(periodo)==3){
                                if(i<=3)
                                    pa=pa+parseInt(nota_s)+parseInt(dps_s);
                                if(i==4)
                                    ref=parseInt(nota_s)+parseInt(dps_s);
                            }
                        }
                        if(parseInt(periodo)==2){
                            if(cant_eva<=4)
                                pa=(pa/cant_eva).round();
                            else
                                pa=(pa/4).round();
                            //color
                            if(pa<=50)
                                $("#pa-"+idestudiante).html("<span style='color:red' title='Reprobado'>"+pa+"</span>");
                            else
                                $("#pa-"+idestudiante).html("<span style='color:green' title='Aprobado'>"+pa+"</span>");
                        
                            if(ref>0)
                                ref=((pa+ref)/2).round();
                            else
                                ref=pa;
                            //color
                            if(ref<=50)
                                $("#pf-"+idestudiante).html("<span style='color:red' title='Reprobado'>"+ref+"</span>");
                            else
                                $("#pf-"+idestudiante).html("<span style='color:green' title='Aprobado'>"+ref+"</span>");
                        }else if(parseInt(periodo)==3){
                            if(cant_eva<=3)
                                pa=(pa/cant_eva).round();
                            else
                                pa=(pa/3).round();
                            //color
                            if(pa<=35)
                                $("#pa-"+idestudiante).html("<span style='color:red' title='Reprobado'>"+pa+"</span>");
                            else
                                $("#pa-"+idestudiante).html("<span style='color:green' title='Aprobado'>"+pa+"</span>");
                        
                            if(ref>0)
                                ref=((pa+ref)/2).round();
                            else
                                ref=pa;
                            //color
                            if(ref<=35)
                                $("#pf-"+idestudiante).html("<span style='color:red' title='Reprobado'>"+ref+"</span>");
                            else
                                $("#pf-"+idestudiante).html("<span style='color:green' title='Aprobado'>"+ref+"</span>");
                        }
                        
                    }
                }

                $(function() { 
                    $(".classbutton" ).button();
                    

            <c:if test="${periodo==2}">
                    var alerta='${mensaje}';
                    var vec_alerta=alerta.split("|");
                    var men="La siguiente lista de estudiantes y calificaciones no fueron registrados correctamente, puesto que ";
                    men+="no cumplen con los requisitos definidos, en caso de las cuantitativas, la calificacion debe ser mayor ";
                    men+="o igual a 35, menor o igual a 100 y diferente de 50 puntos.";
                    if(vec_alerta.length>1){
                        for(var i=0;i<vec_alerta.length;i++){
                            men=men+""+vec_alerta[i]+"\n";
                        }
                        alert(men);
                    }
                  
                    $(".block-dps").attr({'onfocus' : 'this.blur()'});
            </c:if>
                <c:if test="${periodo==3 && cant_dps==0}">
                    var alerta='${mensaje}';
                    var vec_alerta=alerta.split("|");
                    var men="La siguiente lista de estudiantes y calificaciones no fueron registrados correctamente, puesto que ";
                    men+="no cumplen con los requisitos definidos, en caso de las cuantitativas, la calificacion debe ser mayor ";
                    men+="o igual a 35 pts. y menor o igual a 70 pts., en caso de las cualitativas el texto debe contener a lo mucho 250 caracteres (se toma en cuenta los espacios)\n";
                    if(vec_alerta.length>1){
                        for(var i=0;i<vec_alerta.length;i++){
                            men=men+""+vec_alerta[i]+"\n";
                        }
                        alert(men);
                    }
                  
                    $(".block-dps").attr({'onfocus' : 'this.blur()'});
            </c:if>
            <c:if test="${periodo==3 && cant_dps>0}">
                        var alerta='${mensaje}';
                        var vec_alerta=alerta.split("|");
                        var men="La siguiente lista de estudiantes y calificaciones no fueron registrados correctamente, puesto que ";
                        men+="no cumplen con los requisitos definidos, en caso de las cuantitativas, la calificacion debe ser mayor ";
                        men+="o igual a 15 pts. y menor o igual a 60 pts., en caso del DPS el la nota minima es de 5pts. y menor o igual a 10pts.\n";
                        if(vec_alerta.length>1){
                            for(var i=0;i<vec_alerta.length;i++){
                                men=men+""+vec_alerta[i]+"\n";
                            }
                            alert(men);
                        }
                  
                        //$(".block-dps").attr({'onfocus' : 'this.blur()'});
            </c:if>
                    $(".colcar_valor").blur(function(){
                        var periodo='${periodo}';
                        var name=$(this).attr('name');
                        var nota=$(this).val();
                        var dps=$("input[name='dps-"+name+"']").val();
                        var cant_eva='${cant_evaluaciones}';
                        //P1-COM-2013-E1-ABJ2705700-3e
                        var n_s=name.split('-');
                        //alert(name_separado[3]);
                        var idestudiante=n_s[4]+"-"+n_s[5];
                        var eva=n_s[3];
                        var pa=0;
                        var ref=0;
                        for(var i=1;i<= parseInt(cant_eva);i++){
                            //actualizacion de la nota Evaluacion (E1,E2,E3,E4,E5)
                            if(("E"+i)==eva){
                                var nota_limite=$("input[name='"+eva+"']").val();
                                if(isNaN(parseInt(nota))){
                                    alert("La calificación debe ser numérico menor o igual a "+nota_limite+", mayor o igual a 35 y diferente de 50 puntos. Intente nuevamente.");
                                    $(this).val(0);
                                }else if(parseInt(nota)<=parseInt(nota_limite) && parseInt(nota)>=35 && parseInt(nota)!=50){
                                    $(this).val(parseInt(nota));
                                }else if(parseInt(nota)>parseInt(nota_limite) || parseInt(nota)<35 || parseInt(nota)==50){
                                    alert("La calificación debe ser numérico menor o igual a "+nota_limite+", mayor o igual a 35 y diferente de 50 puntos. Intente nuevamente.");
                                    $(this).val(0);
                                }                                
                            }
                            //promedio anual
                            var nota_s=$("input[name='"+n_s[0]+"-"+n_s[1]+"-"+n_s[2]+"E"+i+"-"+n_s[4]+"-"+n_s[5]+"']").val();
                            var dps_s=$("input[name='dps-"+n_s[0]+"-"+n_s[1]+"-"+n_s[2]+"E"+i+"-"+n_s[4]+"-"+n_s[5]+"']").val();
                            if(parseInt(periodo)==2){
                                if(i<=4)
                                    pa=pa+parseInt(nota_s)+parseInt(dps_s);
                                if(i==5)
                                    ref=parseInt(nota_s)+parseInt(dps_s);
                            }else if(parseInt(periodo)==3){
                                if(i<=3)
                                    pa=pa+parseInt(nota_s)+parseInt(dps_s);
                                if(i==4)
                                    ref=parseInt(nota_s)+parseInt(dps_s);
                            }
                        }
                        //alert(name.substring(13, 14));                        
                        var nota=$(this).val();
                        $("#sum-"+name).html(parseInt(nota)+parseInt(dps));
                        
                        if(parseInt(periodo)==2){
                            if(cant_eva<=4)
                                pa=(pa/cant_eva).round();
                            else
                                pa=(pa/4).round();
                            //color
                            if(pa<=50)
                                $("#pa-"+idestudiante).html("<span style='color:red' title='Reprobado'>"+pa+"</span>");
                            else
                                $("#pa-"+idestudiante).html("<span style='color:green' title='Aprobado'>"+pa+"</span>");
                        
                            if(ref>0)
                                ref=((pa+ref)/2).round();
                            else
                                ref=pa;
                            //color
                            if(ref<=50)
                                $("#pf-"+idestudiante).html("<span style='color:red' title='Reprobado'>"+ref+"</span>");
                            else
                                $("#pf-"+idestudiante).html("<span style='color:green' title='Aprobado'>"+ref+"</span>");
                        }else if(parseInt(periodo)==3){
                            if(cant_eva<=3)
                                pa=(pa/cant_eva).round();
                            else
                                pa=(pa/3).round();
                            //color
                            if(pa<=35)
                                $("#pa-"+idestudiante).html("<span style='color:red' title='Reprobado'>"+pa+"</span>");
                            else
                                $("#pa-"+idestudiante).html("<span style='color:green' title='Aprobado'>"+pa+"</span>");
                        
                            if(ref>0)
                                ref=((pa+ref)/2).round();
                            else
                                ref=pa;
                            //color
                            if(ref<=35)
                                $("#pf-"+idestudiante).html("<span style='color:red' title='Reprobado'>"+ref+"</span>");
                            else
                                $("#pf-"+idestudiante).html("<span style='color:green' title='Aprobado'>"+ref+"</span>");
                        }
                            
                        
                    });
                    $("#dialog_insert_cualitativa" ).dialog({
                        autoOpen: false,
                        height: 300,
                        width: 350,
                        modal: true
                    });
                    $(".item_cualitativa").click(function(){
                        var id=$(this).attr("id");
                        var valor=$("input[name='"+id+"']").val();
                        $("textarea[name='txt_cualitativa']").val(valor);
                        $("#nombre").html("Est: "+$("input[name='"+id+"_name']").val());
                        /*idestudiante*/
                        var idestudiante=$("input[name='"+id+"_idest']").val();
                        $("input[name='idest']").val(idestudiante);
                        /*idevaluacion*/                        
                        var idevaluacion=$("input[name='"+id+"_ideva']").val();
                        $("input[name='ideva']").val(idevaluacion);
                        //src="imagenes/iconos_sigaa/sin_cualitativa.gif"
                        
                        $("#dialog_insert_cualitativa").dialog("open");
                    });
                    $("#cancelar_cualitativa").click(function(){
                        $("#dialog_insert_cualitativa").dialog("close");
                    });
                    $("#actualizar_cualitativa").click(function(){
                        var cualitativa=$("textarea[name='txt_cualitativa']").val();
                        var idest=$("input[name='idest']").val();
                        var ideva=$("input[name='ideva']").val();
                        var id="cualitativa_"+ideva+"_"+idest;
                        if(cualitativa.length>50 && cualitativa.length<=250){
                            $("#"+id).attr('src','imagenes/iconos_sigaa/con_cualitativa.gif');
                        }else{
                            $("#"+id).attr('src','imagenes/iconos_sigaa/sin_cualitativa.gif');
                        }
                        $("#"+id).attr('title',cualitativa.toUpperCase());
                        $("input[name='"+id+"']").val(cualitativa.toUpperCase());
                        $.post("registrarCalificaciones.do", {
                            cualitativa:cualitativa,
                            idest:idest,
                            ideva:ideva,
                            nueva_opcion:'_actualizar_cualitativa'
                        }, function(data){
                            alert(data);
                        });
                        $("#dialog_insert_cualitativa").dialog("close");
                    });
                    
                    $(".colocar_dps").blur(function(){
                        var periodo='${periodo}';
                        var name=$(this).attr('name');
                        var n_s=name.split('-');
                        //dps-P1-COM-2013-E1-ABJ2705700-3e
                        var id_nota=n_s[1]+"-"+n_s[2]+"-"+n_s[3]+"-"+n_s[4]+"-"+n_s[5]+"-"+n_s[6];
                        var idestudiante=n_s[5]+"-"+n_s[6];
                        var nota=$("input[name='"+id_nota+"']").val();
                        var dps=$(this).val();
                         var cant_eva='${cant_evaluaciones}';
                        var eva=n_s[4];
                        var pa=0;
                        var ref=0;
                        for(var i=1;i<= parseInt(cant_eva);i++){
                            //actualizacion de la nota Evaluacion (E1,E2,E3,E4,E5)
                            if(("E"+i)==eva){
                                //var nota_limite=$("input[name='"+eva+"']").val();
                                if(isNaN(parseInt(dps))){
                                    alert("El dps debe ser numérico menor o igual a 10 pts. y mayor o igual a 5 pts. Intente nuevamente.");
                                    $(this).val(0);
                                }else if(parseInt(dps)<=10 && parseInt(dps)>=5){
                                    $(this).val(parseInt(dps));
                                }else if(parseInt(dps)>10 || parseInt(dps)<5){
                                    alert("El dps debe ser numérico menor o igual a 10 pts. y mayor o igual a 5 pts. Intente nuevamente.");
                                    $(this).val(0);
                                }                                
                            }
                            //promedio anual
                            var nota_s=$("input[name='"+n_s[1]+"-"+n_s[2]+"-"+n_s[3]+"E"+i+"-"+n_s[5]+"-"+n_s[6]+"']").val();
                            var dps_s=$("input[name='dps-"+n_s[1]+"-"+n_s[2]+"-"+n_s[3]+"E"+i+"-"+n_s[5]+"-"+n_s[6]+"']").val();
                            if(parseInt(periodo)==2){
                                if(i<=4)
                                    pa=pa+parseInt(nota_s)+parseInt(dps_s);
                                if(i==5)
                                    ref=parseInt(nota_s)+parseInt(dps_s);
                            }else if(parseInt(periodo)==3){
                                if(i<=3)
                                    pa=pa+parseInt(nota_s)+parseInt(dps_s);
                                if(i==4)
                                    ref=parseInt(nota_s)+parseInt(dps_s);
                            }
                        }
                        //alert(name.substring(13, 14));                        
                        var nota=$("input[name='"+id_nota+"']").val();
                        var dps=$(this).val();
                        //alert(parseInt(nota)+parseInt(dps));
                        $("#sum-"+id_nota).html(parseInt(nota)+parseInt(dps));
                        
                        if(parseInt(periodo)==2){
                            if(cant_eva<=4)
                                pa=(pa/cant_eva).round();
                            else
                                pa=(pa/4).round();
                            //color
                            if(pa<=50)
                                $("#pa-"+idestudiante).html("<span style='color:red' title='Reprobado'>"+pa+"</span>");
                            else
                                $("#pa-"+idestudiante).html("<span style='color:green' title='Aprobado'>"+pa+"</span>");
                        
                            if(ref>0)
                                ref=((pa+ref)/2).round();
                            else
                                ref=pa;
                            //color
                            if(ref<=50)
                                $("#pf-"+idestudiante).html("<span style='color:red' title='Reprobado'>"+ref+"</span>");
                            else
                                $("#pf-"+idestudiante).html("<span style='color:green' title='Aprobado'>"+ref+"</span>");
                        }else if(parseInt(periodo)==3){
                            if(cant_eva<=3)
                                pa=(pa/cant_eva).round();
                            else
                                pa=(pa/3).round();
                            //color
                            if(pa<=35)
                                $("#pa-"+idestudiante).html("<span style='color:red' title='Reprobado'>"+pa+"</span>");
                            else
                                $("#pa-"+idestudiante).html("<span style='color:green' title='Aprobado'>"+pa+"</span>");
                        
                            if(ref>0)
                                ref=((pa+ref)/2).round();
                            else
                                ref=pa;
                            //color
                            if(ref<=35)
                                $("#pf-"+idestudiante).html("<span style='color:red' title='Reprobado'>"+ref+"</span>");
                            else
                                $("#pf-"+idestudiante).html("<span style='color:green' title='Aprobado'>"+ref+"</span>");
                        }
                    });
                });
        </script>

    </head>
    <body>
        <div class="headercontent">
            <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                <tr>
                    <td class="tab_init">&nbsp;</td>
                    <td style="width:50%">
                        <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                            <td class="tab_current">Registrar calificaciones</td>
                            <td class="tab_normal" onclick="javascript:window.location='<c:url value="/notasCualitativas.do"/>'">Registrar calificaciones cualitativas</td>
                            <td class="tab_normal" onclick="javascript:window.location='<c:url value="/tutorCurso.do"/>'">Tutor curso</td>
                        </table>
                    </td>
                    <td class="tab_fin">&nbsp;</td>
                </tr>
            </table>
            <div class="titlepage">REGISTRAR CALIFICACIONES</div>
        </div>
        <div class="maincontent">
            <c:if test="${!empty elemento}">
                <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                    <tr>
                        <td class="tableHeader">Mis cursos y materias (${gestion.id_gestion})</td>
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
                                    <c:forEach varStatus="i" var="item" items="${cursos}">
                                        <tr style="cursor:pointer" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                            <td valign="top" align="center"><c:out value="${i.count}"/> </td>
                                            <td valign="top" colspan="2"><b><img src="imagenes/iconos_sigaa/indicador.png">&nbsp;&nbsp;<c:out value="${item.curso}"/></b>
                                                <table width="80%" cellpadding="0" cellspacing="0" border="0" align="center">
                                                    <tr>
                                                        <td colspan="2" style="color:gray"><img src="imagenes/iconos_sigaa/etiqueta_verde.png">&nbsp;&nbsp;<strong>Materias</strong></td>
                                                    </tr>
                                                    <c:forEach varStatus="i" var="items" items="${item.cursomaterias}">
                                                        <tr onclick="selectMateria('${items.id_materia}','${item.id_curso}','${items.id_curso_materia}')" style="cursor:pointer; color:#003366" onmouseover="onRowover(this)" onmouseout="onRowout(this)" bgcolor="white">
                                                            <td width="10%" align="right"><img src="imagenes/iconos_sigaa/etiqueta_azul.png"></td>
                                                            <td width="90%"><b>&nbsp;&nbsp;&nbsp;<c:out value="${items.materia}"/></b></td>
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

            <c:if test="${!empty curso}">
                <table cellpadding="0" cellspacing="0" border="0" style="width: 100%; cursor:pointer">
                    <tr>
                        <td class="tableHeader">Evaluaciones CURSO : <strong> ${curso.curso} de ${curso.ciclo} </strong>&nbsp;&nbsp;&nbsp;&nbsp;MATERIA : <strong> ${materia.materia}</strong> (${gestion.id_gestion})</td>
                    </tr>
                    <tr>
                        <td class="gridContent">
                            <table width="100%" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF">
                                <tr>
                                    <td class="gridHeaders" style="width:6%">Nro.</td>
                                    <td class="gridHeaders" style="width:15%">Evaluaci&oacute;n</td>
                                    <c:if test="${curso.id_curso!='K'&&curso.id_curso!='K1'&&curso.id_curso!='K12'&&curso.id_curso!='K21'&&curso.id_curso!='P1'&&curso.id_curso!='P12'}">
                                        <td class="gridHeaders" style="width:4%" title="Nota sobre un total de 60 Pts.">Nota</td>
                                        <td class="gridHeaders" style="width:4%" title="DPS sobre un total de 10 Pts.">DPS</td>
                                    </c:if>
                                    <td class="gridHeaders" style="width:23%" title="Fecha limite de ingreso de calificaciones">Fecha limite de entrega</td>
                                    <td class="gridHeaders" style="width:45%">Subir notas</td>
                                </tr>
                                <c:if test="${empty plan_evaluaciones}">
                                    <tr>
                                        <td class="gridData" colspan="8">No se han encontrado elementos.</td>
                                    </tr>
                                </c:if>
                                <c:forEach varStatus="i" var="item" items="${plan_evaluaciones}">
                                    <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                        <td class="gridData" valign="top">${i.count} <!--img onclick="updateEvaluacion('${item.id_evaluacion}','${curso.id_curso}')" src="imagenes/edit.gif" border="0" alt="Modificar" title=" Modificar fechas de evaluaci&oacute;n y su contenido "--></td>
                                        <td class="gridData" valign="top">${item.evaluacion}</td>
                                        <c:if test="${curso.id_curso!='K'&&curso.id_curso!='K1'&&curso.id_curso!='K12'&&curso.id_curso!='K21'&&curso.id_curso!='P1'&&curso.id_curso!='P12'}">
                                            <td class="gridData" valign="top" align="center">${item.nota}</td>
                                            <td class="gridData" valign="top" align="center">${item.dps}</td>
                                        </c:if>
                                        <c:if test="${item.fec_evaluacion.year+1900==item.fec_limite.year+1900}">
                                            <td class="gridData" valign="top">${item.sfec_limite}</td>
                                        </c:if>
                                        <c:if test="${item.fec_evaluacion.year+1900!=item.fec_limite.year+1900}">
                                            <td class="gridData" valign="top" style="color:red"><strong>No definido!...</strong></td>
                                        </c:if>
                                        <c:if test="${item.estado=='activo'}">
                                            <td class="gridData">
                                                <form action="<c:url value="/registrarCalificaciones.do"/>" method="post" name="formNotasExcel" enctype="multipart/form-data">
                                                    <input type="hidden" name="id_evaluacion" value="${item.id_evaluacion}">
                                                    <input type="hidden" name="id_curso" value="${curso.id_curso}">
                                                    <input type="hidden" name="id_materia" value="${materia.id_materia}">
                                                    <input type="hidden" name="id_curso_materia" value="${id_curso_materia}">
                                                    <input type="hidden" name="columna" value="${i.count}">
                                                    <input type="hidden" name="periodo" value="${periodo}">
                                                    <input type="hidden" name="cant_dps" value="${cant_dps}">
                                                    <input type="file" name="fichero" size="50" accept="jxlx/jxl" class="button-normal">
                                                    <button type="submit" class="button-normal"><img src="imagenes/iconos_sigaa/guardar_exc.png" width="12px">&nbsp;&nbsp;Finalizar</button>
                                                </form>
                                            </td>
                                        </c:if>
                                        <c:if test="${item.estado=='inactivo'}">
                                            <td class="gridData" align="center" style="color:red">Inactivo...</td>
                                        </c:if>
                                    </tr>
                                    <script type="text/javascript">
                                        seval = {evaluacion:'${item.evaluacion}',nota:'${item.nota}',dps:'${item.dps}',day:'${item.fec_evaluacion.date}',month:'${item.fec_evaluacion.month+1}',year:'${item.fec_evaluacion.year+1900}',descripcion:'${item.descripcion}'}
                                        __evas['${item.id_evaluacion}'] = seval;
                                    </script>
                                </c:forEach>
                            </table>
                        </td>
                    </tr>
                    <!--tr>
                        <td class="tableHeader">Estudiantes CURSO : <strong> ${curso.curso} de ${curso.ciclo} </strong>&nbsp;&nbsp;&nbsp;&nbsp;MATERIA : <strong> ${materia.materia}</strong> (${gestion.id_gestion})</td>
                    </tr-->
                    <tr>
                        <td class="gridContent">
                            <div >
                                <button class="button-normal" onclick="javascript:saveNotas()"><img src="imagenes/iconos_sigaa/guardar.png" width="11px">&nbsp;&nbsp;Guardar</button>&nbsp;
                                <button class="button-normal" onclick="javascript:imprimirListaPdf()" title="N&oacute;mina de estudiantes en formato pdf"><img src="imagenes/iconos_sigaa/pdf.png" width="11px">&nbsp;&nbsp;Imprimir lista</button>&nbsp;
                                <button class="button-normal" onclick="javascript:imprimirNotasPdf()" title="Calificaciones en formato pdf"><img src="imagenes/iconos_sigaa/pdf.png" width="11px">&nbsp;&nbsp;Imprimir pdf</button>&nbsp;
                                <button class="button-normal" onclick="downloadExcel()" title="Calificaciones en formato excel"><img src="imagenes/iconos_sigaa/excel.png" width="11px">&nbsp;&nbsp;Descargar</button>&nbsp;
                                <!--button class="button-normal" onclick="javascript:openCloseDialog('Cerrar','Concluci&oacute;n de materia','450','125')"><img src="imagenes/iconos_sigaa/close.png" width="10px">&nbsp;&nbsp;Cerrar materia</button-->
                            </div>
                                <form action="<c:url value="/registrarCalificaciones.do"/>" method="post" name="formNotas">
                                    <c:if test="${periodo==1}">
                                        <!-- trmestral-->    
                                        <table width="100%" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF" style="cursor: pointer">
                                            <tr>
                                                <td rowspan="2" class="gridHeaders" style="width:40px">Nro.</td>
                                                <td rowspan="2" class="gridHeaders">Apellidos y nombres</td>
                                                <c:forEach varStatus="i" var="item" items="${plan_evaluaciones}">
                                                    <c:if test="${i.count==1}"><td class="gridUno" colspan="3" align="center" style="width:18px;cursor:pointer" title="Trimestre ${i.count}">Trimestre ${i.count}</td></c:if>
                                                    <c:if test="${i.count==2}"><td class="gridDos" colspan="3" align="center" style="width:18px;cursor:pointer" title="Trimestre ${i.count}">Trimestre ${i.count}</td></c:if>
                                                    <c:if test="${i.count==3}"><td class="gridTres" colspan="3" align="center" style="width:18px;cursor:pointer" title="Trimestre ${i.count}">Trimestre ${i.count}</td></c:if>
                                                </c:forEach>
                                                <c:if test="${curso.id_curso!='K'&& curso.id_curso!='K1'&&curso.id_curso!='K12'&&curso.id_curso!='K21'&&curso.id_curso!='P1'&&curso.id_curso!='P12'}">
                                                    <td rowspan="2" class="gridPA" style="cursor:pointer;width:50px" title=" Promedio Anual : 70 (puntos)">P.A.</td>
                                                    <c:forEach varStatus="i" var="item" items="${plan_evaluaciones}">
                                                        <c:if test="${i.count==4}"><td class="gridCuatro" colspan="3" align="center" style="width:18px;cursor:pointer" title="Reforzamiento">Reforzamiento</td></c:if>
                                                    </c:forEach>
                                                    <td rowspan="2" class="gridPF" style="cursor:pointer;width:50px" title=" Promedio Final : 70 (puntos)">P.F.</td>
                                                </c:if>
                                            </tr>
                                            <tr>
                                                <c:if test="${curso.id_curso!='K'&&curso.id_curso!='K1'&&curso.id_curso!='K12'&&curso.id_curso!='K21'&&curso.id_curso!='P1'&&curso.id_curso!='P12'}">
                                                    <c:forEach varStatus="i" var="item" items="${plan_evaluaciones}">
                                                        <c:if test="${i.count==1}">
                                                            <td class="gridUno" style="width:18px;cursor:pointer;text-decoration:underline;color:#00369A" title="${item.evaluacion}: ${item.nota} (puntos) ">E${i.count}</td>
                                                            <td class="gridUno" style="width:18px;cursor:pointer;text-decoration:underline;color:#00369A" title="Dps ${item.evaluacion}: ${item.dps} (puntos) ">dps</td>
                                                            <td class="gridUno" style="cursor:pointer; width:23px;text-decoration:underline;color:#00369A" title="Nota promedio ${item.evaluacion}: ${item.nota + item.dps} (puntos) ">PT${i.count}</td>
                                                        </c:if>
                                                        <c:if test="${i.count==2}">
                                                            <td class="gridDos" style="width:18px;cursor:pointer;text-decoration:underline;color:#00369A" title=" ${item.evaluacion}: ${item.nota} (puntos) ">E${i.count}</td>
                                                            <td class="gridDos" style="width:18px;cursor:pointer;text-decoration:underline;color:#00369A" title="Dps ${item.evaluacion}: ${item.dps} (puntos) ">dps</td>
                                                            <td class="gridDos" style="cursor:pointer; width:23px;text-decoration:underline;color:#00369A" title="Nota promedio ${item.evaluacion}: ${item.nota + item.dps} (puntos) ">PT${i.count}</td>
                                                        </c:if>
                                                        <c:if test="${i.count==3}">
                                                            <td class="gridTres" style="width:18px;cursor:pointer;text-decoration:underline;color:#00369A" title=" ${item.evaluacion}: ${item.nota} (puntos) ">E${i.count}</td>
                                                            <td class="gridTres" style="width:18px;cursor:pointer;text-decoration:underline;color:#00369A" title="Dps ${item.evaluacion}: ${item.dps} (puntos) ">dps</td>
                                                            <td class="gridTres" style="cursor:pointer; width:23px;text-decoration:underline;color:#00369A" title="Nota promedio ${item.evaluacion}: ${item.nota + item.dps} (puntos) ">PT${i.count}</td>
                                                        </c:if>
                                                        <c:if test="${i.count>=4}">
                                                            <td class="gridCuatro" style="width:18px;cursor:pointer;text-decoration:underline;color:#00369A" title=" ${item.evaluacion}: ${item.nota} (puntos) ">E${i.count}</td>
                                                            <td class="gridCuatro" style="width:18px;cursor:pointer;text-decoration:underline;color:#00369A" title="Dps ${item.evaluacion}: ${item.dps} (puntos) ">dps</td>
                                                            <td class="gridCuatro" style="cursor:pointer; width:23px;text-decoration:underline;color:#00369A" title="Nota promedio ${item.evaluacion}: ${item.nota + item.dps} (puntos) ">PR${i.count}</td>
                                                        </c:if>
                                                    </c:forEach>
                                                </c:if>
                                            </tr>
                                            <c:if test="${empty curso.estudiantes}">
                                                <tr>
                                                    <td class="gridData" colspan="7">No se han encontrado elementos.</td>
                                                </tr>
                                            </c:if>
                                            <c:forEach varStatus="i" var="item" items="${curso.estudiantes}">
                                                <tr id="row-${item.id_estudiante}" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                                    <td rowspan="2" class="gridData" align="center"><c:out value="${i.count}"/> </td>
                                                    <td rowspan="2" class="gridData"><c:out value="${item.paterno} ${item.materno} ${item.nombres}" /></td>
                                                    <c:forEach varStatus="j" var="eva" items="${item.evaluaciones}">
                                                        <c:if test="${eva.estado=='activo' && j.count!=4}" >
                                                            <c:if test="${curso.id_curso!='K'&& curso.id_curso!='K1'&&curso.id_curso!='K12'&&curso.id_curso!='K21'&&curso.id_curso!='P1'&&curso.id_curso!='P12'}">
                                                                <td class="gridDataInput">
                                                                    <input name="nota-ant-${eva.id_evaluacion}-${item.id_estudiante}" type="hidden" value="${eva.nota}">
                                                                    <input name="dps-ant-${eva.id_evaluacion}-${item.id_estudiante}" type="hidden" value="${eva.dps}">
                                                                    <input id="${eva.id_evaluacion}-${item.id_estudiante}" name="${eva.id_evaluacion}-${item.id_estudiante}" type="text" class="gridInput" style="width:23px;text-align:right" value="${eva.nota}" onfocus="onRowover($('row-${item.id_estudiante}'))" onblur=" onRowout($('row-${item.id_estudiante}'));setObservacionEst('${item.id_estudiante}'); " maxlength="2">
                                                                    <div style="position:relative;bottom:17px;left:15px;width:0px;height:0px">
                                                                        <div id="${eva.id_evaluacion}${item.id_estudiante}_unique_id" style="position:absolute;bottom:0px;right:-50px;overflow:visible"></div>
                                                                    </div>
                                                                    <script type="text/javascript">notin = {name:'${eva.id_evaluacion}-${item.id_estudiante}',eval:'${eva.id_evaluacion}',error:'${eva.id_evaluacion}${item.id_estudiante}_unique_id'}; _inputs[_counter]=notin;_counter++;</script>
                                                                </td>
                                                                <c:if test="${j.count!=4}">
                                                                    <c:if test="${curso.id_curso=='S1'||curso.id_curso=='S2'||curso.id_curso=='S3'||curso.id_curso=='S4'||curso.id_curso=='P6'||curso.id_curso=='P7'||curso.id_curso=='P8'||curso.id_curso=='P2'||curso.id_curso=='P2T'||curso.id_curso=='P21'||curso.id_curso=='P3'||curso.id_curso=='P31'||curso.id_curso=='P41'||curso.id_curso=='P51'||curso.id_curso=='P61'||curso.id_curso=='P4'||curso.id_curso=='P5'}">
                                                                        <td class="gridDataInput">
                                                                            <input id="dps-${eva.id_evaluacion}-${item.id_estudiante}" name="dps-${eva.id_evaluacion}-${item.id_estudiante}" type="text" class="gridInput" style="width:23px;text-align:right" value="${eva.dps}" onfocus="onRowover($('row-${item.id_estudiante}'))" onblur=" onRowout($('row-${item.id_estudiante}'));setObservacionEst('${item.id_estudiante}'); " maxlength="2">
                                                                            <div style="position:relative;bottom:17px;left:15px;width:0px;height:0px">
                                                                                <div id="${eva.id_evaluacion}${item.id_estudiante}_unique_id" style="position:absolute;bottom:0px;right:-50px;overflow:visible"></div>
                                                                            </div>
                                                                            <script type="text/javascript">notin = {name:'${eva.id_evaluacion}-${item.id_estudiante}',eval:'${eva.id_evaluacion}',error:'${eva.id_evaluacion}${item.id_estudiante}_unique_id'}; _inputs[_counter]=notin;_counter++;</script>
                                                                        </td>
                                                                    </c:if>

                                                                </c:if>
                                                            </c:if>
                                                            <c:if test="${curso.id_curso=='K'&& curso.id_curso=='K1'&&curso.id_curso=='K12'&&curso.id_curso=='K21'&&curso.id_curso=='P1'&&curso.id_curso=='P12'}">
                                                                <td rowspan="2" colspan="3" width="18px" align="center" ><textarea name="cualitativa-${eva.id_evaluacion}-${item.id_estudiante}" class="text-field" style="width:95%; height:110px; text-align:justify" >${eva.cualitativa}</textarea> </td>
                                                            </c:if>
                                                        </c:if>
                                                        <c:if test="${eva.estado!='activo' && j.count!=4}">
                                                            <c:if test="${curso.id_curso!='K'&& curso.id_curso!='K1'&&curso.id_curso!='K12'&&curso.id_curso!='K21'&&curso.id_curso!='P1'&&curso.id_curso!='P12'}">
                                                                <td class="gridDataInput">
                                                                    <input name="nota-ant-${eva.id_evaluacion}-${item.id_estudiante}" type="hidden" value="${eva.nota}">
                                                                    <input name="dps-ant-${eva.id_evaluacion}-${item.id_estudiante}" type="hidden" value="${eva.dps}">
                                                                    <input id="${eva.id_evaluacion}-${item.id_estudiante}" name="${eva.id_evaluacion}-${item.id_estudiante}" type="text" class="gridInput" style="width:23px;text-align:right" value="${eva.nota}" onfocus="this.blur();onRowover($('row-${item.id_estudiante}'))" onblur=" onRowout($('row-${item.id_estudiante}'));setObservacionEst('${item.id_estudiante}'); " maxlength="2">
                                                                    <div style="position:relative;bottom:17px;left:15px;width:0px;height:0px">
                                                                        <div id="${eva.id_evaluacion}${item.id_estudiante}_unique_id" style="position:absolute;bottom:0px;right:-50px;overflow:visible"></div>
                                                                    </div>
                                                                    <script type="text/javascript">notin = {name:'${eva.id_evaluacion}-${item.id_estudiante}',eval:'${eva.id_evaluacion}',error:'${eva.id_evaluacion}${item.id_estudiante}_unique_id'}; _inputs[_counter]=notin;_counter++;</script>
                                                                </td>
                                                                <c:if test="${j.count!=4}">
                                                                    <td class="gridDataInput">
                                                                        <input id="dps-${eva.id_evaluacion}-${item.id_estudiante}" name="dps-${eva.id_evaluacion}-${item.id_estudiante}" type="text" class="gridInput" style="width:23px;text-align:right" value="${eva.dps}" onfocus=" this.blur(); onRowover($('row-${item.id_estudiante}'))" onblur=" onRowout($('row-${item.id_estudiante}'));setObservacionEst('${item.id_estudiante}'); " maxlength="2">
                                                                        <div style="position:relative;bottom:17px;left:15px;width:0px;height:0px">
                                                                            <div id="${eva.id_evaluacion}${item.id_estudiante}_unique_id" style="position:absolute;bottom:0px;right:-50px;overflow:visible"></div>
                                                                        </div>
                                                                        <script type="text/javascript">notin = {name:'${eva.id_evaluacion}-${item.id_estudiante}',eval:'${eva.id_evaluacion}',error:'${eva.id_evaluacion}${item.id_estudiante}_unique_id'}; _inputs[_counter]=notin;_counter++;</script>
                                                                    </td>
                                                                </c:if>
                                                            </c:if>

                                                                    <c:if test="${curso.id_curso=='K'&& curso.id_curso=='K1'&&curso.id_curso=='K12'&&curso.id_curso=='K21'&&curso.id_curso=='P1'&&curso.id_curso=='P12'}">
                                                                <td rowspan="2" colspan="3" width="18px" align="center" ><textarea class="text-field" name="cualitativa-${eva.id_evaluacion}-${item.id_estudiante}" style="width:95%; height:110px; text-align:justify" onfocus=" this.blur()">${eva.cualitativa}</textarea> </td>
                                                            </c:if>
                                                        </c:if>
                                                        <c:if test="${curso.id_curso!='K'&& curso.id_curso!='K1'&&curso.id_curso!='K12'&&curso.id_curso!='K21'&&curso.id_curso!='P1'&&curso.id_curso!='P12'}">
                                                            <c:if test="${j.count==1}">
                                                                <td class="gridUnon" align="center" id="sum-${eva.id_evaluacion}-${item.id_estudiante}"></td>
                                                            </c:if>
                                                            <c:if test="${j.count==2}">
                                                                <td class="gridDosn" align="center" id="sum-${eva.id_evaluacion}-${item.id_estudiante}"></td>
                                                            </c:if>
                                                            <c:if test="${j.count==3}">
                                                                <td class="gridTresn" align="center" id="sum-${eva.id_evaluacion}-${item.id_estudiante}"></td>
                                                            </c:if>
                                                        </c:if>
                                                    </c:forEach>
                                                    <c:if test="${curso.id_curso!='K'&& curso.id_curso!='K1'&&curso.id_curso!='K12'&&curso.id_curso!='K21'&&curso.id_curso!='P1'&&curso.id_curso!='P12'}">
                                                        <td valign="top" rowspan="2" class="gridPAn" align="right" id="sum-${item.id_estudiante}"></td>
                                                        <c:forEach varStatus="j" var="eva" items="${item.evaluaciones}">
                                                            <c:if test="${eva.estado=='activo' && j.count==4}">
                                                                <td class="gridDataInput">
                                                                    <input name="nota-ant-${eva.id_evaluacion}-${item.id_estudiante}" type="hidden" value="${eva.nota}">
                                                                    <input name="dps-ant-${eva.id_evaluacion}-${item.id_estudiante}" type="hidden" value="${eva.dps}">
                                                                    <input id="${eva.id_evaluacion}-${item.id_estudiante}" name="${eva.id_evaluacion}-${item.id_estudiante}" type="text" class="gridInput" style="width:23px;text-align:right" value="${eva.nota}" onfocus="onRowover($('row-${item.id_estudiante}'))" onblur=" onRowout($('row-${item.id_estudiante}'));setObservacionEst('${item.id_estudiante}'); " maxlength="2">
                                                                    <div style="position:relative;bottom:17px;left:15px;width:0px;height:0px">
                                                                        <div id="${eva.id_evaluacion}${item.id_estudiante}_unique_id" style="position:absolute;bottom:0px;right:-50px;overflow:visible"></div>
                                                                    </div>
                                                                    <script type="text/javascript">notin = {name:'${eva.id_evaluacion}-${item.id_estudiante}',eval:'${eva.id_evaluacion}',error:'${eva.id_evaluacion}${item.id_estudiante}_unique_id'}; _inputs[_counter]=notin;_counter++;</script>
                                                                </td>
                                                                <c:if test="${j.count==4}">
                                                                    <td align="center" class="gridDataInput" id="dps-${eva.id_evaluacion}-${item.id_estudiante}"></td>
                                                                </c:if>
                                                            </c:if>
                                                            <c:if test="${eva.estado!='activo' && j.count==4}">
                                                                <td class="gridDataInput">
                                                                    <input name="nota-ant-${eva.id_evaluacion}-${item.id_estudiante}" type="hidden" value="${eva.nota}">
                                                                    <input name="dps-ant-${eva.id_evaluacion}-${item.id_estudiante}" type="hidden" value="${eva.dps}">
                                                                    <input id="${eva.id_evaluacion}-${item.id_estudiante}" name="${eva.id_evaluacion}-${item.id_estudiante}" type="text" class="gridInput" style="width:23px;text-align:right" value="${eva.nota}" onfocus="this.blur();onRowover($('row-${item.id_estudiante}'))" onblur=" onRowout($('row-${item.id_estudiante}'));setObservacionEst('${item.id_estudiante}'); " maxlength="2">
                                                                    <div style="position:relative;bottom:17px;left:15px;width:0px;height:0px">
                                                                        <div id="${eva.id_evaluacion}${item.id_estudiante}_unique_id" style="position:absolute;bottom:0px;right:-50px;overflow:visible"></div>
                                                                    </div>
                                                                    <script type="text/javascript">notin = {name:'${eva.id_evaluacion}-${item.id_estudiante}',eval:'${eva.id_evaluacion}',error:'${eva.id_evaluacion}${item.id_estudiante}_unique_id'}; _inputs[_counter]=notin;_counter++;</script>
                                                                </td>
                                                                <c:if test="${j.count==4}">
                                                                    <td align="center" class="gridDataInput" id="dps-${eva.id_evaluacion}-${item.id_estudiante}"></td>
                                                                </c:if>
                                                            </c:if>
                                                            <c:if test="${j.count==4}">
                                                                <td valign="top" class="gridCuatron" align="center" id="sum-${eva.id_evaluacion}-${item.id_estudiante}"></td>
                                                            </c:if>
                                                        </c:forEach>
                                                        <td valign="top" rowspan="2" class="gridPFn" id="obs-${item.id_estudiante}">&nbsp;</td>
                                                    </c:if>
                                                </tr>                       
                                                <tr>
                                                    <c:if test="${ curso.id_curso=='P2' || curso.id_curso=='P21' || curso.id_curso=='P2T'|| curso.id_curso=='P3' || curso.id_curso=='P31' || curso.id_curso=='P41' || curso.id_curso=='P4' || curso.id_curso=='P5' || curso.id_curso=='P51' || curso.id_curso=='P61' || curso.id_curso=='P6' || curso.id_curso=='P7' || curso.id_curso=='P8'}">
                                                        <c:forEach varStatus="j" var="eva" items="${item.evaluaciones}">
                                                            <c:if test="${j.count!=4 && eva.estado=='activo'}">
                                                                <td colspan="3" class="gridData">
                                                                    <select name="id_cualitativa-${eva.id_evaluacion}-${item.id_estudiante}" class="text-field" style="width:100%">
                                                                        <option value="0">
                                                                            <c:forEach var="itc" items="${cualitativas}">
                                                                            <option value="${itc.id_cualitativa}" title="${itc.descripcion}"<c:if test="${itc.id_cualitativa == eva.id_cualitativa}"> selected </c:if>> ${itc.categoria} 
                                                                            </c:forEach>
                                                                    </select>
                                                                </td>
                                                            </c:if>
                                                            <c:if test="${j.count!=4 && eva.estado!='activo'}">
                                                                <!-- cambiar parael año ------------------------onfocus="this.blur()" -->
                                                                <td colspan="3" class="gridData">
                                                                    <c:if test="${eva.id_cualitativa!=0}">
                                                                        <a title="${eva.cualitativa_p}"><img src="imagenes/iconos_sigaa/con_cualitativa.gif"></a>
                                                                        </c:if>
                                                                        <c:if test="${eva.id_cualitativa==0}">
                                                                        <a title="${eva.cualitativa_p}"><img src="imagenes/iconos_sigaa/sin_cualitativa.gif"></a>
                                                                        </c:if>
                                                                        <!--select name="id_cualitativa-${eva.id_evaluacion}-${item.id_estudiante}" class="text-field" style="width:100%">
                                                                            <option value="0">
                                                                    <c:forEach var="itc" items="${cualitativas}">
                                                                    <option value="${itc.id_cualitativa}" title="${itc.descripcion}"<c:if test="${itc.id_cualitativa == eva.id_cualitativa}"> selected </c:if>> ${itc.categoria}
                                                                    </c:forEach>
                                                            </select-->
                                                                </td>
                                                            </c:if>
                                                            <c:if test="${j.count==4}">
                                                                <td colspan="3" class="gridCuatron">
                                                                    <input type="hidden" name="id_cualitativa-${eva.id_evaluacion}-${item.id_estudiante}" value="0">
                                                                </td>
                                                            </c:if>
                                                        </c:forEach>
                                                    </c:if>
                                                </tr>
                                                <script type="text/javascript">setObservacionEst('${item.id_estudiante}');</script>
                                            </c:forEach>
                                        </table>
                                    </c:if>
                                    <!-- bimestral-->
                                    <c:if test="${periodo==2}">
                                        <table width="100%" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF" style="cursor: pointer">
                                            <tr>
                                                <td rowspan="2" class="gridHeaders" style="width:40px">Nro.</td>
                                                <td rowspan="2" class="gridHeaders">Apellidos y nombres</td>
                                                <c:forEach varStatus="i" var="item" items="${plan_evaluaciones}">
                                                    <c:if test="${i.count==1}">
                                                        <td class="gridUno" colspan="3" align="center" style="width:18px;cursor:pointer" title="${item.evaluacion}">
                                                            ${item.evaluacion}<input name="E1" type="hidden" value="${item.nota}"/>
                                                        </td>
                                                    </c:if>
                                                    <c:if test="${i.count==2}">
                                                        <td class="gridDos" colspan="3" align="center" style="width:18px;cursor:pointer" title="${item.evaluacion}">
                                                            ${item.evaluacion}<input name="E2" type="hidden" value="${item.nota}"/>
                                                        </td>
                                                    </c:if>
                                                    <c:if test="${i.count==3}">
                                                        <td class="gridTres" colspan="3" align="center" style="width:18px;cursor:pointer" title="${item.evaluacion}">
                                                            ${item.evaluacion}<input name="E3" type="hidden" value="${item.nota}"/>
                                                        </td>
                                                    </c:if>
                                                    <c:if test="${i.count==4 && periodo==2}">
                                                        <td class="gridUno" colspan="3" align="center" style="width:18px;cursor:pointer" title="${item.evaluacion}">
                                                            ${item.evaluacion}<input name="E4" type="hidden" value="${item.nota}"/>
                                                        </td>
                                                    </c:if>
                                                </c:forEach>
                                                <c:if test="${periodo==3 && curso.id_curso!='K'&& curso.id_curso!='K1'&&curso.id_curso!='K12'&&curso.id_curso!='K21'&&curso.id_curso!='P1'&&curso.id_curso!='P12'}">
                                                    <td rowspan="2" class="gridPA" style="cursor:pointer;width:50px" title=" Promedio Anual : ${item.nota + item.dps} (puntos)">P.A.</td>
                                                    <c:forEach varStatus="i" var="item" items="${plan_evaluaciones}">
                                                        <c:if test="${i.count==4}">
                                                            <td class="gridCuatro" colspan="3" align="center" style="width:18px;cursor:pointer" title="${item.evaluacion}">
                                                                ${item.evaluacion}<input name="E4" type="hidden" value="${item.nota}"/>
                                                            </td>
                                                        </c:if>                                                            
                                                    </c:forEach>
                                                    <td rowspan="2" class="gridPF" style="cursor:pointer;width:50px" title=" Promedio Final : ${item.nota + item.dps} (puntos)">P.F.</td>
                                                </c:if>
                                                <c:if test="${periodo==2 && curso.id_curso!='K'&& curso.id_curso!='K1'&&curso.id_curso!='K12'&&curso.id_curso!='K21'&&curso.id_curso!='P1'&&curso.id_curso!='P12'}">
                                                    <td rowspan="2" class="gridPA" style="cursor:pointer;width:50px" title=" Promedio Anual : ${item.nota + item.dps} (puntos)">P.A.</td>
                                                    <c:forEach varStatus="i" var="item" items="${plan_evaluaciones}">
                                                        <c:if test="${i.count==5}">
                                                            <td class="gridCuatro" colspan="3" align="center" style="width:18px;cursor:pointer" title="${item.evaluacion}">
                                                                ${item.evaluacion}<input name="E5" type="hidden" value="${item.nota}"/>
                                                            </td>
                                                        </c:if>                                                            
                                                    </c:forEach>
                                                    <td rowspan="2" class="gridPF" style="cursor:pointer;width:50px" title=" Promedio Final : ${item.nota + item.dps} (puntos)">P.F.</td>
                                                </c:if>
                                            </tr>
                                            <tr>
                                                <c:if test="${curso.id_curso!='K'&& curso.id_curso!='K1'&&curso.id_curso!='K12'&&curso.id_curso!='K21'&&curso.id_curso!='P1'&&curso.id_curso!='P12'}">
                                                    <c:forEach varStatus="i" var="item" items="${plan_evaluaciones}">
                                                        <c:if test="${i.count==1}">
                                                            <td class="gridUno" style="width:18px;cursor:pointer;text-decoration:underline;color:#00369A" title="${item.evaluacion}: ${item.nota} (puntos) ">E${i.count}</td>
                                                            <td class="gridUno" style="width:18px;cursor:pointer;text-decoration:underline;color:#00369A"></td>
                                                            <td class="gridUno" style="cursor:pointer; width:23px;text-decoration:underline;color:#00369A" title="Nota promedio ${item.evaluacion}: ${item.nota + item.dps} (puntos) ">PT${i.count}</td>
                                                        </c:if>
                                                        <c:if test="${i.count==2}">
                                                            <td class="gridDos" style="width:18px;cursor:pointer;text-decoration:underline;color:#00369A" title=" ${item.evaluacion}: ${item.nota} (puntos) ">E${i.count}</td>
                                                            <td class="gridDos" style="width:18px;cursor:pointer;text-decoration:underline;color:#00369A"></td>
                                                            <td class="gridDos" style="cursor:pointer; width:23px;text-decoration:underline;color:#00369A" title="Nota promedio ${item.evaluacion}: ${item.nota + item.dps} (puntos) ">PT${i.count}</td>
                                                        </c:if>
                                                        <c:if test="${i.count==3}">
                                                            <td class="gridTres" style="width:18px;cursor:pointer;text-decoration:underline;color:#00369A" title=" ${item.evaluacion}: ${item.nota} (puntos) ">E${i.count}</td>
                                                            <td class="gridTres" style="width:18px;cursor:pointer;text-decoration:underline;color:#00369A"></td>
                                                            <td class="gridTres" style="cursor:pointer; width:23px;text-decoration:underline;color:#00369A" title="Nota promedio ${item.evaluacion}: ${item.nota + item.dps} (puntos) ">PT${i.count}</td>
                                                        </c:if>
                                                        <c:if test="${i.count==4}">
                                                            <td class="gridUno" style="width:18px;cursor:pointer;text-decoration:underline;color:#00369A" title=" ${item.evaluacion}: ${item.nota} (puntos) ">E${i.count}</td>
                                                            <td class="gridUno" style="width:18px;cursor:pointer;text-decoration:underline;color:#00369A"></td>
                                                            <td class="gridUno" style="cursor:pointer; width:23px;text-decoration:underline;color:#00369A" title="Nota promedio ${item.evaluacion}: ${item.nota + item.dps} (puntos) ">PT${i.count}</td>
                                                        </c:if>
                                                        <c:if test="${i.count==5}">
                                                            <td class="gridCuatro" style="width:18px;cursor:pointer;text-decoration:underline;color:#00369A" title=" ${item.evaluacion}: ${item.nota} (puntos) ">E${i.count}</td>
                                                            <td class="gridCuatro" style="width:18px;cursor:pointer;text-decoration:underline;color:#00369A"></td>
                                                            <td class="gridCuatro" style="cursor:pointer; width:23px;text-decoration:underline;color:#00369A" title="Nota promedio ${item.evaluacion}: ${item.nota + item.dps} (puntos) ">PT${i.count}</td>
                                                        </c:if> 
                                                    </c:forEach>
                                                </c:if>
                                            </tr>
                                            <c:if test="${empty curso.estudiantes}">
                                                <tr>
                                                    <td class="gridData" colspan="7">No se han encontrado elementos.</td>
                                                </tr>
                                            </c:if>
                                            <c:forEach varStatus="i" var="item" items="${curso.estudiantes}">
                                                <tr id="row-${item.id_estudiante}" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                                    <td rowspan="2" class="gridData" align="center"><c:out value="${i.count}"/></td>
                                                    <td rowspan="2" class="gridData"><c:out value="${item.paterno} ${item.materno} ${item.nombres}" /></td>
                                                    <c:forEach varStatus="j" var="eva" items="${item.evaluaciones}">
                                                        <c:if test="${eva.estado=='activo' && j.count!=5}" >
                                                            <c:if test="${curso.id_curso!='K' && curso.id_curso!='K1' && curso.id_curso!='K12' && curso.id_curso!='K21'&&curso.id_curso!='P1'&&curso.id_curso!='P12'}">
                                                                <td class="gridDataInput">
                                                                    <input name="${eva.id_evaluacion}-${item.id_estudiante}" type="text" class="gridInput colcar_valor" style="width:23px;text-align:right" value="${eva.nota}" maxlength="3">
                                                                </td>
                                                                <td class="gridDataInput">
                                                                    <input name="dps-${eva.id_evaluacion}-${item.id_estudiante}" <c:if test="${eva.dps_plan>0}">type="text"</c:if> <c:if test="${eva.dps_plan==0}">type="hidden"</c:if> class="gridInput block-dps colocar_dps" style="width:23px;text-align:right" value="${eva.dps}" maxlength="2">
                                                                </td>
                                                            </c:if>
                                                            <c:if test="${curso.id_curso=='K' || curso.id_curso=='K1' || curso.id_curso=='K12' || curso.id_curso=='K21'|| curso.id_curso=='P1'|| curso.id_curso=='P12'}">
                                                                <td rowspan="2" colspan="3" width="18px" align="center" ><textarea name="cualitativa-${eva.id_evaluacion}-${item.id_estudiante}" class="text-field" style="width:95%; height:110px; text-align:justify" >${eva.cualitativa}</textarea> </td>
                                                            </c:if>
                                                        </c:if>
                                                        <c:if test="${eva.estado!='activo' && j.count!=5}">
                                                            <c:if test="${curso.id_curso!='K'&& curso.id_curso!='K1'&&curso.id_curso!='K12'&&curso.id_curso!='K21'&&curso.id_curso!='P1'&&curso.id_curso!='P12'}">
                                                                <td class="gridDataInput">
                                                                    <input name="${eva.id_evaluacion}-${item.id_estudiante}" type="text" class="gridInput colcar_valor" style="width:23px;text-align:right" value="${eva.nota}" maxlength="3" onfocus="this.blur();">
                                                                </td>
                                                                <td class="gridDataInput">
                                                                    <input name="dps-${eva.id_evaluacion}-${item.id_estudiante}" <c:if test="${eva.dps_plan>0}">type="text"</c:if> <c:if test="${eva.dps_plan==0}">type="hidden"</c:if> class="gridInput block-dps colocar_dps" style="width:23px;text-align:right" value="${eva.dps}" maxlength="2" onfocus="this.blur();">
                                                                </td>
                                                            </c:if>
                                                            <c:if test="${curso.id_curso=='K'||curso.id_curso=='K1'||curso.id_curso=='K12' ||curso.id_curso=='K21'||curso.id_curso=='P1'||curso.id_curso=='P12'}">
                                                                <td rowspan="2" colspan="3" width="18px" align="center" ><textarea class="text-field" name="cualitativa-${eva.id_evaluacion}-${item.id_estudiante}" style="width:95%; height:110px; text-align:justify" onfocus=" this.blur()">${eva.cualitativa}</textarea> </td>
                                                            </c:if>
                                                        </c:if>
                                                        <c:if test="${curso.id_curso!='K'&&  curso.id_curso!='K1'&&curso.id_curso!='K12'&&curso.id_curso!='K21'&&curso.id_curso!='P1'&&curso.id_curso!='P12'}">
                                                            <c:if test="${j.count==1}">
                                                                <td class="gridUnon" align="center" id="sum-${eva.id_evaluacion}-${item.id_estudiante}"> ${eva.nota+eva.dps}</td>
                                                            </c:if>
                                                            <c:if test="${j.count==2}">
                                                                <td class="gridDosn" align="center" id="sum-${eva.id_evaluacion}-${item.id_estudiante}">${eva.nota+eva.dps}</td>
                                                            </c:if>
                                                            <c:if test="${j.count==3}">
                                                                <td class="gridTresn" align="center" id="sum-${eva.id_evaluacion}-${item.id_estudiante}">${eva.nota+eva.dps}</td>
                                                            </c:if>
                                                            <c:if test="${j.count==4}">
                                                                <td class="gridUnon" align="center" id="sum-${eva.id_evaluacion}-${item.id_estudiante}">${eva.nota+eva.dps}</td>
                                                            </c:if>
                                                        </c:if>
                                                    </c:forEach>
                                                    <c:if test="${curso.id_curso!='K'&& curso.id_curso!='K1'&&curso.id_curso!='K12'&&curso.id_curso!='K21'&&curso.id_curso!='P1'&&curso.id_curso!='P12'}">
                                                        <td valign="top" rowspan="2" class="gridPAn" align="right" id="pa-${item.id_estudiante}">
                                                            <script></script>
                                                        </td>
                                                        <c:forEach varStatus="j" var="eva" items="${item.evaluaciones}">
                                                            <c:if test="${eva.estado=='activo' && j.count==5}">
                                                                <td class="gridDataInput">
                                                                    <input name="${eva.id_evaluacion}-${item.id_estudiante}" type="text" class="gridInput colcar_valor" style="width:23px;text-align:right" value="${eva.nota}" maxlength="3">
                                                                </td>
                                                                <td align="center" class="gridDataInput">
                                                                    <input name="dps-${eva.id_evaluacion}-${item.id_estudiante}" type="hidden" class="gridInput block-dps colocar_dps" style="width:23px;text-align:right" value="${eva.dps}" maxlength="2">
                                                                </td>
                                                                <td align="center" class="gridCuatron" align="center" id="sum-${eva.id_evaluacion}-${item.id_estudiante}">${eva.nota+eva.dps}</td>
                                                            </c:if>
                                                            <c:if test="${eva.estado!='activo' && j.count==5}">
                                                                <td class="gridDataInput">
                                                                    <input name="${eva.id_evaluacion}-${item.id_estudiante}" type="text" class="gridInput" style="width:23px;text-align:right" value="${eva.nota}" maxlength="3" onfocus="this.blur();">
                                                                </td>
                                                                <td align="center" class="gridDataInput">
                                                                    <input name="dps-${eva.id_evaluacion}-${item.id_estudiante}" type="hidden" class="gridInput block-dps colocar_dps" style="width:23px;text-align:right" value="${eva.dps}" maxlength="2">
                                                                </td>
                                                                <td valign="top" class="gridCuatron" align="center" id="sum-${eva.id_evaluacion}-${item.id_estudiante}">${eva.nota+eva.dps}</td>
                                                            </c:if>
                                                        </c:forEach>
                                                        <td valign="top" rowspan="2" class="gridPFn" id="pf-${item.id_estudiante}">
                                                            <script>setActualizaNotaPFIni('${item.id_estudiante}');</script>
                                                        </td>
                                                    </c:if>
                                                </tr>                       
                                                <tr>
                                                    <c:if test="${curso.id_curso=='P2' || curso.id_curso=='P2T'|| curso.id_curso=='P21'|| curso.id_curso=='P3' ||curso.id_curso=='P31'|| curso.id_curso=='P41'  || curso.id_curso=='P51' || curso.id_curso=='P4' || curso.id_curso=='P5' || curso.id_curso=='P6' || curso.id_curso=='P7' || curso.id_curso=='P8'}">
                                                        <c:forEach varStatus="j" var="eva" items="${item.evaluaciones}">
                                                            <c:if test="${j.count!=5 && eva.estado=='activo'}">
                                                                <td colspan="3" class="gridData">
                                                                    <input type="hidden" name="cualitativa_${eva.id_evaluacion}_${item.id_estudiante}" value="${eva.cualitativa}">
                                                                    <input type="hidden" name="cualitativa_${eva.id_evaluacion}_${item.id_estudiante}_name" value="${item.paterno} ${item.materno} ${item.nombres}">
                                                                    <input type="hidden" name="cualitativa_${eva.id_evaluacion}_${item.id_estudiante}_idest" value="${item.id_estudiante}">
                                                                    <input type="hidden" name="cualitativa_${eva.id_evaluacion}_${item.id_estudiante}_ideva" value="${eva.id_evaluacion}">
                                                                    <c:if test="${eva.tamanio>=50 && eva.tamanio<=250}">
                                                                        <img class="item_cualitativa" title="${eva.cualitativa}" id="cualitativa_${eva.id_evaluacion}_${item.id_estudiante}" src="imagenes/iconos_sigaa/con_cualitativa.gif"/>
                                                                    </c:if>
                                                                    <c:if test="${eva.tamanio>250 || eva.tamanio<50}">
                                                                        <img class="item_cualitativa" title="${eva.cualitativa}" id="cualitativa_${eva.id_evaluacion}_${item.id_estudiante}" src="imagenes/iconos_sigaa/sin_cualitativa.gif"/>
                                                                    </c:if>
                                                                </td>
                                                            </c:if>
                                                            <c:if test="${j.count!=5 && eva.estado!='activo'}">
                                                                <td colspan="3" class="gridData">
                                                                    <c:if test="${eva.tamanio>=50 && eva.tamanio<=250}">
                                                                        <img src="imagenes/iconos_sigaa/con_cualitativa.gif"/>
                                                                    </c:if>
                                                                    <c:if test="${eva.tamanio>250 || eva.tamanio<50}">
                                                                        <img title="${eva.cualitativa}" src="imagenes/iconos_sigaa/sin_cualitativa.gif"/>
                                                                    </c:if>
                                                                </td>
                                                            </c:if>
                                                            <c:if test="${j.count==5}">
                                                                <td colspan="3" class="gridCuatron">

                                                                </td>
                                                            </c:if>
                                                        </c:forEach>
                                                    </c:if>
                                                </tr>
                                            </c:forEach>
                                        </table>
                                    </c:if>
                                    <!-- trimestral-->
                                    <c:if test="${periodo==3}">
                                        <table width="100%" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF" style="cursor: pointer">
                                            <tr>
                                                <td rowspan="2" class="gridHeaders" style="width:40px">Nro.</td>
                                                <td rowspan="2" class="gridHeaders">Apellidos y nombres</td>
                                                <c:forEach varStatus="i" var="item" items="${plan_evaluaciones}">
                                                    <c:if test="${i.count==1}">
                                                        <td class="gridUno" colspan="3" align="center" style="width:18px;cursor:pointer" title="${item.evaluacion}">
                                                            ${item.evaluacion}<input name="E1" type="hidden" value="${item.nota}"/>
                                                        </td>
                                                    </c:if>
                                                    <c:if test="${i.count==2}">
                                                        <td class="gridDos" colspan="3" align="center" style="width:18px;cursor:pointer" title="${item.evaluacion}">
                                                            ${item.evaluacion}<input name="E2" type="hidden" value="${item.nota}"/>
                                                        </td>
                                                    </c:if>
                                                    <c:if test="${i.count==3}">
                                                        <td class="gridTres" colspan="3" align="center" style="width:18px;cursor:pointer" title="${item.evaluacion}">
                                                            ${item.evaluacion}<input name="E3" type="hidden" value="${item.nota}"/>
                                                        </td>
                                                    </c:if>
                                                    <c:if test="${i.count==4 && periodo==2}">
                                                        <td class="gridUno" colspan="3" align="center" style="width:18px;cursor:pointer" title="${item.evaluacion}">
                                                            ${item.evaluacion}<input name="E4" type="hidden" value="${item.nota}"/>
                                                        </td>
                                                    </c:if>
                                                </c:forEach>
                                                <c:if test="${periodo==3 && curso.id_curso!='K'&&curso.id_curso!='K1'&&curso.id_curso!='K12'&&curso.id_curso!='K21'&&curso.id_curso!='P1'&&curso.id_curso!='P12'}">
                                                    <td rowspan="2" class="gridPA" style="cursor:pointer;width:50px" title=" Promedio Anual : ${item.nota + item.dps} (puntos)">P.A.</td>
                                                    <c:forEach varStatus="i" var="item" items="${plan_evaluaciones}">
                                                        <c:if test="${i.count==4}">
                                                            <td class="gridCuatro" colspan="3" align="center" style="width:18px;cursor:pointer" title="${item.evaluacion}">
                                                                ${item.evaluacion}<input name="E4" type="hidden" value="${item.nota}"/>
                                                            </td>
                                                        </c:if>                                                            
                                                    </c:forEach>
                                                    <td rowspan="2" class="gridPF" style="cursor:pointer;width:50px" title=" Promedio Final : ${item.nota + item.dps} (puntos)">P.F.</td>
                                                </c:if>
                                                <c:if test="${periodo==2 && curso.id_curso!='K'&& curso.id_curso!='K1'&&curso.id_curso!='K12'&&curso.id_curso!='K21'&&curso.id_curso!='P1'&&curso.id_curso!='P12'}">
                                                    <td rowspan="2" class="gridPA" style="cursor:pointer;width:50px" title=" Promedio Anual : ${item.nota + item.dps} (puntos)">P.A.</td>
                                                    <c:forEach varStatus="i" var="item" items="${plan_evaluaciones}">
                                                        <c:if test="${i.count==5}">
                                                            <td class="gridCuatro" colspan="3" align="center" style="width:18px;cursor:pointer" title="${item.evaluacion}">
                                                                ${item.evaluacion}<input name="E5" type="hidden" value="${item.nota}"/>
                                                            </td>
                                                        </c:if>                                                            
                                                    </c:forEach>
                                                    <td rowspan="2" class="gridPF" style="cursor:pointer;width:50px" title=" Promedio Final : ${item.nota + item.dps} (puntos)">P.F.</td>
                                                </c:if>
                                            </tr>
                                            <tr>
                                                <c:if test="${curso.id_curso!='K'&& curso.id_curso!='K1'&&curso.id_curso!='K12'&&curso.id_curso!='K21'&&curso.id_curso!='P1'&&curso.id_curso!='P12'}">
                                                    <c:forEach varStatus="i" var="item" items="${plan_evaluaciones}">
                                                        <c:if test="${i.count==1}">
                                                            <td class="gridUno" style="width:18px;cursor:pointer;text-decoration:underline;color:#00369A" title="${item.evaluacion}: ${item.nota} (puntos) ">E${i.count}</td>
                                                            <td class="gridUno" style="width:18px;cursor:pointer;text-decoration:underline;color:#00369A">
                                                                <c:if test="${item.dps>0}">
                                                                    <span title="Dps ${item.evaluacion}: ${item.dps} (puntos)">dps</span>
                                                                </c:if>
                                                            </td>
                                                            <td class="gridUno" style="cursor:pointer; width:23px;text-decoration:underline;color:#00369A" title="Nota promedio ${item.evaluacion}: ${item.nota + item.dps} (puntos) ">PT${i.count}</td>
                                                        </c:if>
                                                        <c:if test="${i.count==2}">
                                                            <td class="gridDos" style="width:18px;cursor:pointer;text-decoration:underline;color:#00369A" title=" ${item.evaluacion}: ${item.nota} (puntos) ">E${i.count}</td>
                                                            <td class="gridDos" style="width:18px;cursor:pointer;text-decoration:underline;color:#00369A">
                                                                <c:if test="${item.dps>0}">
                                                                    <span title="Dps ${item.evaluacion}: ${item.dps} (puntos)">dps</span>
                                                                </c:if>
                                                            </td>
                                                            <td class="gridDos" style="cursor:pointer; width:23px;text-decoration:underline;color:#00369A" title="Nota promedio ${item.evaluacion}: ${item.nota + item.dps} (puntos) ">PT${i.count}</td>
                                                        </c:if>
                                                        <c:if test="${i.count==3}">
                                                            <td class="gridTres" style="width:18px;cursor:pointer;text-decoration:underline;color:#00369A" title=" ${item.evaluacion}: ${item.nota} (puntos) ">E${i.count}</td>
                                                            <td class="gridTres" style="width:18px;cursor:pointer;text-decoration:underline;color:#00369A">
                                                                <c:if test="${item.dps>0}">
                                                                    <span title="Dps ${item.evaluacion}: ${item.dps} (puntos)">dps</span>
                                                                </c:if>
                                                            </td>
                                                            <td class="gridTres" style="cursor:pointer; width:23px;text-decoration:underline;color:#00369A" title="Nota promedio ${item.evaluacion}: ${item.nota + item.dps} (puntos) ">PT${i.count}</td>
                                                        </c:if>
                                                        <c:if test="${i.count==4}">
                                                            <td class="gridUno" style="width:18px;cursor:pointer;text-decoration:underline;color:#00369A" title=" ${item.evaluacion}: ${item.nota} (puntos) ">E${i.count}</td>
                                                            <td class="gridUno" style="width:18px;cursor:pointer;text-decoration:underline;color:#00369A">
                                                                <c:if test="${item.dps>0}">
                                                                    <span title="Dps ${item.evaluacion}: ${item.dps} (puntos)">dps</span>
                                                                </c:if>
                                                            </td>
                                                            <td class="gridUno" style="cursor:pointer; width:23px;text-decoration:underline;color:#00369A" title="Nota promedio ${item.evaluacion}: ${item.nota + item.dps} (puntos) ">PT${i.count}</td>
                                                        </c:if>
                                                        <c:if test="${i.count==5}">
                                                            <td class="gridCuatro" style="width:18px;cursor:pointer;text-decoration:underline;color:#00369A" title=" ${item.evaluacion}: ${item.nota} (puntos) ">E${i.count}</td>
                                                            <td class="gridCuatro" style="width:18px;cursor:pointer;text-decoration:underline;color:#00369A">
                                                                <c:if test="${item.dps>0}">
                                                                    <span title="Dps ${item.evaluacion}: ${item.dps} (puntos)">dps</span>
                                                                </c:if>
                                                            </td>
                                                            <td class="gridCuatro" style="cursor:pointer; width:23px;text-decoration:underline;color:#00369A" title="Nota promedio ${item.evaluacion}: ${item.nota + item.dps} (puntos) ">PT${i.count}</td>
                                                        </c:if> 
                                                    </c:forEach>
                                                </c:if>
                                            </tr>
                                            <c:if test="${empty curso.estudiantes}">
                                                <tr>
                                                    <td class="gridData" colspan="7">No se han encontrado elementos.</td>
                                                </tr>
                                            </c:if>
                                            <c:forEach varStatus="i" var="item" items="${curso.estudiantes}">
                                                <tr id="row-${item.id_estudiante}" onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                                                    <td rowspan="2" class="gridData" align="center"><c:out value="${i.count}"/></td>
                                                    <td rowspan="2" class="gridData"><c:out value="${item.paterno} ${item.materno} ${item.nombres}" /></td>
                                                    <c:forEach varStatus="j" var="eva" items="${item.evaluaciones}">
                                                        <!-- para bimestral -->
                                                        <c:if test="${eva.estado=='activo' && j.count!=5 && periodo==2}" >
                                                            <c:if test="${curso.id_curso!='K' && curso.id_curso!='K1' && curso.id_curso!='K12' && curso.id_curso!='K21'&&curso.id_curso!='P1'&&curso.id_curso!='P12'}">
                                                                <td class="gridDataInput">
                                                                    <input name="${eva.id_evaluacion}-${item.id_estudiante}" type="text" class="gridInput colcar_valor" style="width:23px;text-align:right" value="${eva.nota}" maxlength="3">
                                                                </td>
                                                                <td class="gridDataInput">
                                                                    <input name="dps-${eva.id_evaluacion}-${item.id_estudiante}" <c:if test="${eva.dps_plan>0}">type="text"</c:if> <c:if test="${eva.dps_plan==0}">type="hidden"</c:if> class="gridInput block-dps colocar_dps" style="width:23px;text-align:right" value="${eva.dps}" maxlength="2">
                                                                </td>
                                                            </c:if>
                                                                
                                                            <c:if test="${curso.id_curso=='K' || curso.id_curso=='K1' || curso.id_curso=='K12' || curso.id_curso=='K21'|| curso.id_curso=='P1'|| curso.id_curso=='P12'}">
                                                                <td rowspan="2" colspan="3" width="18px" align="center" ><textarea name="cualitativa-${eva.id_evaluacion}-${item.id_estudiante}" class="text-field" style="width:95%; height:110px; text-align:justify" >${eva.cualitativa}</textarea> </td>
                                                            </c:if>
                                                        </c:if>
                                                        <!-- para bimestral -->
                                                        <c:if test="${eva.estado!='activo' && j.count!=5 && periodo==2}">
                                                            <c:if test="${curso.id_curso!='K'}">
                                                                <td class="gridDataInput">
                                                                    <input name="${eva.id_evaluacion}-${item.id_estudiante}" type="text" class="gridInput colcar_valor" style="width:23px;text-align:right" value="${eva.nota}" maxlength="3" onfocus="this.blur();">
                                                                </td>
                                                                <td class="gridDataInput">
                                                                    <input name="dps-${eva.id_evaluacion}-${item.id_estudiante}" <c:if test="${eva.dps_plan>0}">type="text"</c:if> <c:if test="${eva.dps_plan==0}">type="hidden"</c:if> class="gridInput block-dps colocar_dps" style="width:23px;text-align:right" value="${eva.dps}" maxlength="2" onfocus="this.blur();">
                                                                </td>
                                                            </c:if>
                                                            <c:if test="${curso.id_curso=='K'||curso.id_curso=='K1'||curso.id_curso!='K12'||curso.id_curso!='K21'|| curso.id_curso=='P1'|| curso.id_curso=='P12'}">
                                                                <td rowspan="2" colspan="3" width="18px" align="center" ><textarea class="text-field" name="cualitativa-${eva.id_evaluacion}-${item.id_estudiante}" style="width:95%; height:110px; text-align:justify" onfocus=" this.blur()">${eva.cualitativa}</textarea> </td>
                                                            </c:if>
                                                        </c:if>
                                                        <!-- para trimestral -->
                                                        <c:if test="${eva.estado=='activo' && j.count!=4 && periodo==3}" >
                                                            <c:if test="${curso.id_curso!='K'&&curso.id_curso!='K1'&&curso.id_curso!='K12'&&curso.id_curso!='K21'&&curso.id_curso!='P1'&&curso.id_curso!='P12'}">
                                                                <td class="gridDataInput">
                                                                    <input name="${eva.id_evaluacion}-${item.id_estudiante}" type="text" class="gridInput colcar_valor" style="width:23px;text-align:right" value="${eva.nota}" maxlength="3">
                                                                </td>
                                                                <td class="gridDataInput">
                                                                    <input name="dps-${eva.id_evaluacion}-${item.id_estudiante}" <c:if test="${eva.dps_plan>0}">type="text"</c:if> <c:if test="${eva.dps_plan==0}">type="hidden"</c:if> class="gridInput block-dps colocar_dps" style="width:23px;text-align:right" value="${eva.dps}" maxlength="2">
                                                                </td>
                                                            </c:if>
                                                            <c:if test="${curso.id_curso=='K' || curso.id_curso=='K1' || curso.id_curso=='K12' || curso.id_curso=='K21'|| curso.id_curso=='P1'|| curso.id_curso=='P12'}">
                                                                <td rowspan="2" colspan="3" width="18px" align="center" ><textarea name="cualitativa-${eva.id_evaluacion}-${item.id_estudiante}" class="text-field" style="width:95%; height:110px; text-align:justify" >${eva.cualitativa}</textarea> </td>
                                                            </c:if>
                                                        </c:if>
                                                        <!-- para trimestral -->
                                                        <c:if test="${eva.estado!='activo' && j.count!=4 && periodo==3}">
                                                            <c:if test="${curso.id_curso!='K'&&curso.id_curso!='K1'&&curso.id_curso!='K12'&&curso.id_curso!='K21'&&curso.id_curso!='P1'&&curso.id_curso!='P12'}">
                                                                <td class="gridDataInput">
                                                                    <input name="${eva.id_evaluacion}-${item.id_estudiante}" type="text" class="gridInput" style="width:23px;text-align:right" value="${eva.nota}" maxlength="3" onfocus="this.blur();">
                                                                </td>
                                                                <td class="gridDataInput">
                                                                    <input name="dps-${eva.id_evaluacion}-${item.id_estudiante}" <c:if test="${eva.dps_plan>0}">type="text"</c:if> <c:if test="${eva.dps_plan==0}">type="hidden"</c:if> class="gridInput block-dps colocar_dps" style="width:23px;text-align:right" value="${eva.dps}" maxlength="2" onfocus="this.blur();">
                                                                </td>
                                                            </c:if>
                                                            <c:if test="${curso.id_curso=='K'||curso.id_curso=='K1'||curso.id_curso=='K12'||curso.id_curso=='K21'|| curso.id_curso=='P1'|| curso.id_curso=='P12'}">
                                                                <td rowspan="2" colspan="3" width="18px" align="center" ><textarea class="text-field" name="cualitativa-${eva.id_evaluacion}-${item.id_estudiante}" style="width:95%; height:110px; text-align:justify" onfocus=" this.blur()">${eva.cualitativa}</textarea> </td>
                                                            </c:if>
                                                        </c:if>
                                                        <c:if test="${curso.id_curso!='K'&&curso.id_curso!='K1'&&curso.id_curso!='K12'&&curso.id_curso!='K21'&&curso.id_curso!='P1'&&curso.id_curso!='P12'}">
                                                            <c:if test="${j.count==1}">
                                                                <td class="gridUnon" align="center" id="sum-${eva.id_evaluacion}-${item.id_estudiante}"> ${eva.nota+eva.dps}</td>
                                                            </c:if>
                                                            <c:if test="${j.count==2}">
                                                                <td class="gridDosn" align="center" id="sum-${eva.id_evaluacion}-${item.id_estudiante}">${eva.nota+eva.dps}</td>
                                                            </c:if>
                                                            <c:if test="${j.count==3}">
                                                                <td class="gridTresn" align="center" id="sum-${eva.id_evaluacion}-${item.id_estudiante}">${eva.nota+eva.dps}</td>
                                                            </c:if>
                                                            <!-- para bimestral -->
                                                            <c:if test="${j.count==4 && periodo==2}">
                                                                <td class="gridUnon" align="center" id="sum-${eva.id_evaluacion}-${item.id_estudiante}">${eva.nota+eva.dps}</td>
                                                            </c:if>
                                                        </c:if>
                                                    </c:forEach>
                                                    <c:if test="${curso.id_curso!='K'&&curso.id_curso!='K1'&&curso.id_curso!='K12'&&curso.id_curso!='K21'|| curso.id_curso=='P12'|| curso.id_curso=='P1'}">
                                                        <td valign="top" rowspan="2" class="gridPAn" align="right" id="pa-${item.id_estudiante}">
                                                            <script></script>
                                                        </td>
                                                        <c:forEach varStatus="j" var="eva" items="${item.evaluaciones}">
                                                            <!-- para bimestral -->
                                                            <c:if test="${eva.estado=='activo' && j.count==5}">
                                                                <td class="gridDataInput">
                                                                    <input name="${eva.id_evaluacion}-${item.id_estudiante}" type="text" class="gridInput colcar_valor" style="width:23px;text-align:right" value="${eva.nota}" maxlength="3">
                                                                </td>
                                                                <td align="center" class="gridDataInput">
                                                                    <input name="dps-${eva.id_evaluacion}-${item.id_estudiante}" type="hidden" class="gridInput block-dps colocar_dps" style="width:23px;text-align:right" value="${eva.dps}" maxlength="2">
                                                                </td>
                                                                <td align="center" class="gridCuatron" align="center" id="sum-${eva.id_evaluacion}-${item.id_estudiante}">${eva.nota+eva.dps}</td>
                                                            </c:if>
                                                            <!-- para bimestral -->
                                                            <c:if test="${eva.estado!='activo' && j.count==5}">
                                                                <td class="gridDataInput">
                                                                    <input name="${eva.id_evaluacion}-${item.id_estudiante}" type="text" class="gridInput" style="width:23px;text-align:right" value="${eva.nota}" maxlength="3" onfocus="this.blur();">
                                                                </td>
                                                                <td align="center" class="gridDataInput">
                                                                    <input name="dps-${eva.id_evaluacion}-${item.id_estudiante}" type="hidden" class="gridInput block-dps colocar_dps" style="width:23px;text-align:right" value="${eva.dps}" maxlength="2">
                                                                </td>
                                                                <td valign="top" class="gridCuatron" align="center" id="sum-${eva.id_evaluacion}-${item.id_estudiante}">${eva.nota+eva.dps}</td>
                                                            </c:if>
                                                            <!-- para trimestral -->
                                                            <c:if test="${eva.estado=='activo' && j.count==4}">
                                                                <td class="gridDataInput">
                                                                    <input name="${eva.id_evaluacion}-${item.id_estudiante}" type="text" class="gridInput colcar_valor" style="width:23px;text-align:right" value="${eva.nota}" maxlength="3">
                                                                </td>
                                                                <td align="center" class="gridDataInput">
                                                                    <input name="dps-${eva.id_evaluacion}-${item.id_estudiante}" type="hidden" class="gridInput block-dps colocar_dps" style="width:23px;text-align:right" value="${eva.dps}" maxlength="2">
                                                                </td>
                                                                <td align="center" class="gridCuatron" align="center" id="sum-${eva.id_evaluacion}-${item.id_estudiante}">${eva.nota+eva.dps}</td>
                                                            </c:if>
                                                            <!-- para trimestral -->
                                                            <c:if test="${eva.estado!='activo' && j.count==4}">
                                                                <td class="gridDataInput">
                                                                    <input name="${eva.id_evaluacion}-${item.id_estudiante}" type="text" class="gridInput" style="width:23px;text-align:right" value="${eva.nota}" maxlength="3" onfocus="this.blur();">
                                                                </td>
                                                                <td align="center" class="gridDataInput">
                                                                    <input name="dps-${eva.id_evaluacion}-${item.id_estudiante}" type="hidden" class="gridInput block-dps colocar_dps" style="width:23px;text-align:right" value="${eva.dps}" maxlength="2">
                                                                </td>
                                                                <td valign="top" class="gridCuatron" align="center" id="sum-${eva.id_evaluacion}-${item.id_estudiante}">${eva.nota+eva.dps}</td>
                                                            </c:if>
                                                        </c:forEach>
                                                        <td valign="top" rowspan="2" class="gridPFn" id="pf-${item.id_estudiante}">
                                                            <script>setActualizaNotaPFIni('${item.id_estudiante}');</script>
                                                        </td>
                                                    </c:if>
                                                </tr>                       
                                                <tr>
                                                    <c:if test="${curso.id_curso=='P2'|| curso.id_curso=='P21'|| curso.id_curso=='P2T'|| curso.id_curso=='P3' || curso.id_curso=='P4'|| curso.id_curso=='P41' || curso.id_curso=='P5'|| curso.id_curso=='P31' || curso.id_curso=='P51' || curso.id_curso=='P6' || curso.id_curso=='P7' || curso.id_curso=='P8'}">
                                                        <c:forEach varStatus="j" var="eva" items="${item.evaluaciones}">
                                                            <!-- bimestral-->
                                                            <c:if test="${periodo==2}">
                                                                <c:if test="${j.count!=5 && eva.estado=='activo'}">
                                                                    <td colspan="3" class="gridData">
                                                                        <input type="hidden" name="cualitativa_${eva.id_evaluacion}_${item.id_estudiante}" value="${eva.cualitativa}">
                                                                        <input type="hidden" name="cualitativa_${eva.id_evaluacion}_${item.id_estudiante}_name" value="${item.paterno} ${item.materno} ${item.nombres}">
                                                                        <input type="hidden" name="cualitativa_${eva.id_evaluacion}_${item.id_estudiante}_idest" value="${item.id_estudiante}">
                                                                        <input type="hidden" name="cualitativa_${eva.id_evaluacion}_${item.id_estudiante}_ideva" value="${eva.id_evaluacion}">
                                                                        <c:if test="${eva.tamanio>=50 && eva.tamanio<=250}">
                                                                            <img class="item_cualitativa" title="${eva.cualitativa}" id="cualitativa_${eva.id_evaluacion}_${item.id_estudiante}" src="imagenes/iconos_sigaa/con_cualitativa.gif"/>
                                                                        </c:if>
                                                                        <c:if test="${eva.tamanio>250 || eva.tamanio<50}">
                                                                            <img class="item_cualitativa" title="${eva.cualitativa}" id="cualitativa_${eva.id_evaluacion}_${item.id_estudiante}" src="imagenes/iconos_sigaa/sin_cualitativa.gif"/>
                                                                        </c:if>
                                                                    </td>
                                                                </c:if>
                                                                <c:if test="${j.count!=5 && eva.estado!='activo'}">
                                                                    <td colspan="3" class="gridData">
                                                                        <c:if test="${eva.tamanio>=50 && eva.tamanio<=250}">
                                                                            <img src="imagenes/iconos_sigaa/con_cualitativa.gif"/>
                                                                        </c:if>
                                                                        <c:if test="${eva.tamanio>250 || eva.tamanio<50}">
                                                                            <img title="${eva.cualitativa}" src="imagenes/iconos_sigaa/sin_cualitativa.gif"/>
                                                                        </c:if>
                                                                    </td>
                                                                </c:if>
                                                                <c:if test="${j.count==5}">
                                                                    <td colspan="3" class="gridCuatron">

                                                                    </td>
                                                                </c:if>
                                                            </c:if>
                                                            <!-- trimestral-->
                                                            <c:if test="${periodo==3333333}">
                                                                <c:if test="${j.count!=4 && eva.estado=='activo'}">
                                                                    <td colspan="3" class="gridData">
                                                                        <input type="hidden" name="cualitativa_${eva.id_evaluacion}_${item.id_estudiante}" value="${eva.cualitativa}">
                                                                        <input type="hidden" name="cualitativa_${eva.id_evaluacion}_${item.id_estudiante}_name" value="${item.paterno} ${item.materno} ${item.nombres}">
                                                                        <input type="hidden" name="cualitativa_${eva.id_evaluacion}_${item.id_estudiante}_idest" value="${item.id_estudiante}">
                                                                        <input type="hidden" name="cualitativa_${eva.id_evaluacion}_${item.id_estudiante}_ideva" value="${eva.id_evaluacion}">
                                                                        <c:if test="${eva.tamanio>=50 && eva.tamanio<=250}">
                                                                            <img class="item_cualitativa" title="${eva.cualitativa}" id="cualitativa_${eva.id_evaluacion}_${item.id_estudiante}" src="imagenes/iconos_sigaa/con_cualitativa.gif"/>
                                                                        </c:if>
                                                                        <c:if test="${eva.tamanio>250 || eva.tamanio<50}">
                                                                            <img class="item_cualitativa" title="${eva.cualitativa}" id="cualitativa_${eva.id_evaluacion}_${item.id_estudiante}" src="imagenes/iconos_sigaa/sin_cualitativa.gif"/>
                                                                        </c:if>
                                                                    </td>
                                                                </c:if>
                                                                <c:if test="${j.count!=4 && eva.estado!='activo'}">
                                                                    <td colspan="3" class="gridData">
                                                                        <c:if test="${eva.tamanio>=50 && eva.tamanio<=250}">
                                                                            <img src="imagenes/iconos_sigaa/con_cualitativa.gif"/>
                                                                        </c:if>
                                                                        <c:if test="${eva.tamanio>250 || eva.tamanio<50}">
                                                                            <img title="${eva.cualitativa}" src="imagenes/iconos_sigaa/sin_cualitativa.gif"/>
                                                                        </c:if>
                                                                    </td>
                                                                </c:if>
                                                                <c:if test="${j.count==4}">
                                                                    <td colspan="3" class="gridCuatron">

                                                                    </td>
                                                                </c:if>
                                                            </c:if>

                                                        </c:forEach>
                                                    </c:if>
                                                </tr>
                                            </c:forEach>
                                        </table>
                                    </c:if>
                                    <input type="hidden" name="id_curso" value="${curso.id_curso}">
                                    <input type="hidden" name="id_materia" value="${materia.id_materia}">
                                    <input type="hidden" name="guardar_notas" value="_guardar_notas">
                                    <input type="hidden" name="id_curso_materia" value="${id_curso_materia}">
                                    <input type="hidden" name="cant_dps" value="${cant_dps}">
                                </form>
                        </td>
                    </tr>
                </table>
            </c:if>
        </div>
        <div id="definirEvaluacionForm"  style="display:none">
            <div class="headercontent">
                <table style="width:100%" cellspacing="0" cellpadding="0">
                    <tr>
                        <td class="menuHead"><b>Evaluaciones&nbsp;>&nbsp;</b>Definir fechas de evaluaciones</td>
                    </tr>
                </table>
            </div><br/>
            <div class="formPanel">
                <form id="addevaform" action="<c:url value="/registrarCalificaciones.do"/>" method="post" name="form" onsubmit="return setValidar(this)">
                    <table width="100%" border="0" style="cursor:pointer">
                        <tr class="gridHeaders">
                            <td width="5%"></td>
                            <td width="15%">Evaluaci&oacute;n</td>
                            <c:if test="${curso.id_curso!='K'&&curso.id_curso!='K1'&&curso.id_curso!='K12'&&curso.id_curso!='K21'&&curso.id_curso!='P1'&&curso.id_curso!='P12'}">
                                <td width="10%">Nota</td>
                                <td width="10%">Dps</td>
                            </c:if>
                            <td width="30%">Fecha evaluacion</td>
                            <td width="30%">Descricpi&oacute;n</td>
                        </tr>
                        <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)">
                            <td valign="top"><img src="imagenes/cxc.png"></td>
                            <td valign="top"><input class="text-field" type="text" name="evaluacion" style="width:100px" ></td>
                                <c:if test="${curso.id_curso!='K'&& curso.id_curso!='K1'&&curso.id_curso!='K12'&&curso.id_curso!='K21'}">
                                <td valign="top" align="center"><strong><input class="text-field" type="text" name="nota" value="60" style="width:25px" onfocus="this.blur()"></strong> Pts.</td>
                                <td valign="top" align="center"><strong><input class="text-field" type="text" name="dps" value="10" style="width:25px" onfocus="this.blur()"></strong> Pts.</td>
                                    </c:if>
                            <td valign="top" align="center">
                                <select name="dia" class="text-field" style="width:40px">
                                    <option>
                                        <c:forEach begin="1" end="31" varStatus="i">
                                        <option value="${i.count}">${i.count}
                                        </c:forEach>
                                </select>
                                <select name="mes" class="text-field" style="width:105px">
                                    <option>
                                        <c:forEach var="item" items="${tipo_meses}">
                                        <option value="${item.id}">${item.valor}
                                        </c:forEach>
                                </select>
                                <select name="anio" class="text-field" style="width:55px">
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
                                <input type="hidden" name="guardar" value="_guardar">
                                <input type="hidden" name="id_materia" value="${materia.id_materia}">
                                <input type="hidden" name="id_curso" value="${curso.id_curso}">
                                <input type="hidden" name="id_evaluacion">
                                <input type="hidden" name="id_curso_materia" value="${id_curso_materia}">
                                <button class="button-normal" type="submit"><img src="imagenes/iconos_sigaa/guardar.png" width="11px">&nbsp;&nbsp;Guardar</button>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
        <div id="documentoPdF" style="display:none">
            <div style="width:100%;height:99%">
                <iframe id="documentview" frameborder="0" style="width:100%;height:100%"></iframe>
            </div>
        </div>
        <div id="Cerrar" style="display:none">
            <div class="formPanel">
                <p>¿Confirma que desea cerrar la materia?.&nbsp;&nbsp;Una vez cerrado ya no tendr&aacute; acceso a la misma.
                    y solo podra ser restituido por un administrativo autorizado.</p>
                <div class="PwdMeterBase" style="width:100%">
                    <table align="center">
                        <td>
                            <button class="button-normal" style="width:70px" onclick="javascript:confirmClose()">Aceptar</button>
                        </td>
                        <td><button class="button-normal" style="width:70px" onclick="javascript:cancelClose()">Cancelar</button>
                        </td>
                    </table>
                </div>
            </div>
        </div>

        <div id="dialog_insert_cualitativa" title="Calificacion Cualitativa">
            <span style="" id="nombre"></span>
            <input type="hidden" name="idest">
            <input type="hidden" name="ideva">
            <textarea name="txt_cualitativa" class="text-field" style="width: 100%;height: 160px"></textarea>
            <p style="text-align:  center">
                <button id="actualizar_cualitativa" class="classbutton">Actualizar</button>
                <button id="cancelar_cualitativa" class="classbutton">Cancelar</button> 
            </p>                   
        </div>
    </body>
</html>