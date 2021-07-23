<%-- 
    Document   : LibretasEscolares
    Created on : 27-10-2009, 09:35:43 PM
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
        <script type="text/javascript" src="js/prototype.js"></script>
        <link rel="stylesheet" type="text/css" href="css/module.css" media="screen" />
        <script type="text/javascript">

            function popupWindow(u) {
                window.open(u,'','toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes,copyhistory=no,width=800,height=530,screenX=50,screenY=50,top=50,left=350');
            }
            function selectEstudiante(id_estudiante,id_curso,id_gestion) {
                //popupWindow('<c:url value="/libretaEstudiante.do"/>?id_estudiante='+id_estudiante+'&id_curso='+id_curso+'&id_gestion='+id_gestion);
            }
            
            function onRowover(elem) { elem.className='colover'; }
            function onRowout(elem) { elem.className='colout'; }

            function openWindow(form,title,w,h){
                var modalwin=dhtmlmodal.open('win'+form, 'div', form, title, 'width='+w+'px,height='+h+'px,left=100px,top=100px,resize=1,scrolling=1');
                modalwin.moveTo('middle', 'middle');
                return modalwin;
            }
           
            var docentesWin = null;
            function __openDList() {
                docentesWin = openWindow('docentesList','Docentes de la facultad','620','270')
            }
            function __closeDList() {
                docentesWin.hide();
            }
            function selectDocente(id,docente) {
                asignarDocenteParalelo(id,docente);
            }
          

            function openPrintableTarjeta(){
                var tarjeta = dhtmlmodal.open('Tarjeta de Asistencia', 'iframe', '<c:url value="/reportePDFAsistencia.do"/>?carrera=${paralelo.carrera}&gestion=${paralelo.inscripcion.nombre}&docente=${paralelo.docente.nombres}&materia=${paralelo.materia}&sigla=${paralelo.sigla}&paralelo=${paralelo.paralelo}&ins=${paralelo.nro_inscritos}', 'Imprimir...', 'width=650px,height=340px,left=100px,top=100px,resize=1,scrolling=1,center=1');
                tarjeta.moveTo('middle','middle');
            }

            function selectCurso(id_curso){
                window.location = '<c:url value="/libretaEscolar.do"/>?id_curso='+id_curso+'&id_gestion='+'${id_gestion}';
            }
            function selectGestion(id_ges){
                window.location = '<c:url value="/libretaEscolar.do"/>?id_gestion='+id_ges;
            }
            function generarExcelCurso(id_curso,id_gestion){ 
                window.location = '<c:url value="/libretaEscolar.do"/>?gecxel=_gexcel&id_curso='+id_curso+'&id_gestion='+id_gestion;
            }
        </script>
    </head>
    <body>
        <div class="headercontent">
            <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                <tr>
                    <td class="tab_init">&nbsp;</td>
                    <td style="width:15%">
                        <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                            <td class="tab_current" >Libretas escolares</td>
                        </table>
                    </td>
                    <td class="tab_fin">&nbsp;</td>
                </tr>
            </table>
            <div class="titlepage">LIBRETAS ESCOLARES</div>
        </div>
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
                    <td class="tableHeader">Cursos y materias</td>
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
                                <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)" onclick="selectCurso('${item.id_curso}')">
                                    <td align="center" style="cursor:pointer" class="gridData">${j.count}</td>
                                    <td align="center" style="cursor:pointer" class="gridData">${item.desc_curso}</td>
                                    <td valign="top" class="gridData">
                                        <table border="0" width="100%">
                                            <tr>
                                                <td valign="top"><span style="cursor:pointer; color:teal"><img src="imagenes/cxc.png">&nbsp;&nbsp;<strong>${item.curso} de ${item.ciclo}</strong></span></td>
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

        <c:if test="${!empty estudiantes}">
            <table cellpadding="0" cellspacing="0" border="0" style="width: 100% ; cursor:pointer">
                <tr>
                    <td class="tableHeader">Lista de estudiantes CURSO : <strong> ${curso.curso} de ${curso.ciclo}</strong></td>
                </tr>
                <tr>
                    <td class="gridContent">
                        <button class="button-normal"  onclick="generarExcelCurso('${curso.id_curso}','${id_gestion}')">Generar archivo Excel de ${curso.curso} de ${curso.ciclo}</button>&nbsp;
                        <div id="resultlayer2">
                            <table width="100%" cellpadding="0" cellspacing="0" border="0" bgcolor="#FFFFFF">
                                <tr>
                                    <td class="gridHeaders" width="10%">Nro.</td>
                                    <td class="gridHeaders" width="10%">C&oacute;digo</td>
                                    <td class="gridHeaders" width="80%">Apellidos y nombres</td>
                                </tr>
                                <c:if test="${empty estudiantes}">
                                    <tr>
                                        <td class="gridData" colspan="3">No se han encontrado elementos.</td>
                                    </tr>
                                </c:if>
                                <c:forEach varStatus="i" var="item" items="${estudiantes}">
                                    <tr onmouseover="onRowover(this)" onmouseout="onRowout(this)" onclick="selectEstudiante('${item.id_estudiante}','${curso.id_curso}','${id_gestion}')">
                                        <td align="center" class="gridData"><c:out value="${i.count}"/> </td>
                                        <td class="gridData">${item.codigo}</td>
                                        <td class="gridData">${item.paterno} ${item.materno} ${item.nombres}</td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </td>
                </tr>
            </table>
        </c:if>
    </body>
</html>