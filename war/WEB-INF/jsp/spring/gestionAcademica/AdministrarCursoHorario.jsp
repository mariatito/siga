<%-- 
    Document   : AdministrarCursoHorario
    Created on : 12-sep-2009, 11:36:04
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
        <link rel="stylesheet" type="text/css" href="css/module.css" media="screen" />

        <script type="text/javascript">
            function onRowover(elem) { elem.className='colover'; }
            function onRowout(elem) { elem.className='colout'; }

            function setImprimirListaPdf(id_curso,id_materia,id_curso_materia) {
                //if(doc == '_prnacta') { if (e == 'A') return; }
                // alert(id_curso);
                var updateMateria=dhtmlmodal.open('win${paralelo.id_paralelo}', 'iframe', '<c:url value="/administrarCurso.do"/>?id_curso=${curso.id_curso}&opcion=DatosGenerales&id_gestion=${id_gestion}&id_materia=${curso.materia.id_materia}&id_curso_materia=${curso.cursomateria.id_curso_materia}&opcionInterno=_imprimirlista', 'Imprimir lista de la materia...', 'width=650px,height=340px,left=100px,top=100px,resize=1,scrolling=1,center=1');
                updateMateria.moveTo('middle', 'middle');
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
                            <td class="tab_normal" onclick="javascript:window.location='<c:url value="/administrarCurso.do"/>?id_curso=${curso.id_curso}&opcion=Evaluaciones&id_gestion=${id_gestion}&id_materia=${curso.materia.id_materia}&id_curso_materia=${curso.cursomateria.id_curso_materia}'">Fecha de evaluaciones</td>
                            <td class="tab_current">Horario</td>
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
                <img src="imagenes/pixel_trans.gif" width="100%" height="1">
                <table cellpadding="0" cellspacing="0" border="0">
                    <tr>
                        <td class="text-label">Docente : </td>
                        <td>${curso.docenteOBJ.abreviatura} ${curso.docenteOBJ.nombres} ${curso.docenteOBJ.paterno} ${curso.docenteOBJ.materno}</td>
                        <td class="text-label">&nbsp;&nbsp;&nbsp;&nbsp;Estado:</td>
                        <td align="right">&nbsp;
                        <c:if test="${curso.cursomateria.estado==false}"><td><img src="imagenes/icons/no.gif" border="0" title=" Cerrado ">&nbsp;&nbsp;</td><td>Cerrado<td></c:if>
                        <c:if test="${curso.cursomateria.estado==true}"><td><img src="imagenes/icons/yes.gif" border="0" title=" En linea ">&nbsp;&nbsp;</td><td>En linea<td></c:if>
                        </td>
                    </tr>
                </table>
                <img src="imagenes/pixel_gry.gif" width="100%" height="1">
                <table cellpadding="0" cellspacing="0" border="0" style="width: 100% ; cursor:pointer">
                    <tr>
                        <td class="tableHeader">Módulo de Horario</td>
                    </tr>
                    <tr>
                        <td align="center">
                            <font size="20px" color="red"><em>Módulo en construcción!!!</em></font>
                        </td>
                    </tr>
                </table>
            </div>
        </c:if>
    </body>
</html>
