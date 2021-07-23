/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.sysmaqv.web.spring.sigaa.inscripciones;

import com.lowagie.text.Chunk;
import java.lang.Math.*;
import spring.sysmaqv.domain.Informacion;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import spring.sysmaqv.domain.Estudiante;
import spring.sysmaqv.domain.Personal;
import spring.sysmaqv.domain.logic.SigaaInterface;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.util.WebUtils;
import sun.font.FontFamily;

/**
 *
 * @author Marco Antonio Quenya Velasco
 */
public class Rude implements Controller {

    private SigaaInterface sigaa;
    private String ecxessTimeView;
    private String perfectView;

    public void setSigaa(SigaaInterface sigaa) {
        this.sigaa = sigaa;
    }

    public void setEcxessTimeView(String ecxessTimeView) {
        this.ecxessTimeView = ecxessTimeView;
    }

    public void setPerfectView(String perfectView) {
        this.perfectView = perfectView;
    }

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String url = request.getServletPath();
        Personal personal = (Personal) WebUtils.getSessionAttribute(request, "___personal");
        if (personal != null) {

            Map retorno = new HashMap();
            retorno.put("edit", "si");
            if (request.getParameter("opcion") != null) {
                String opcion = request.getParameter("opcion");
                String id_est = request.getParameter("id_est");
                if (opcion.equals("imprimir")) {
                    String pdf = this.getReporteRude(id_est);
                    response.sendRedirect("documentos/rude/" + pdf);
                }
            }retorno.put("post", "no");
            if (request.getParameter("id_estudiante") != null) {
                String id_estudiante = request.getParameter("id_estudiante");
                retorno.put("edit", "si");
                retorno.put("post", "si");
                String paterno = request.getParameter("paterno");
                String materno = request.getParameter("materno");
                String nombres = request.getParameter("nombres");
                String pais = request.getParameter("pais");
                String departamento = request.getParameter("departamento");
                String provincia = request.getParameter("provincia");
                String localidad = request.getParameter("localidad");
                String codigo_rude = request.getParameter("codigo_rude");
                String doc_identidad = request.getParameter("doc_identidad");
                String fecha_nacimiento = request.getParameter("fecha_nacimiento");
                fecha_nacimiento = fecha_nacimiento.replace("/", "-");
                SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
                Date fecha_nac = formato.parse(fecha_nacimiento);
                String id_sexo = request.getParameter("id_sexo");
                String oficialia = request.getParameter("oficialia");
                String libro = request.getParameter("libro");
                String partida = request.getParameter("partida");
                String folio = request.getParameter("folio");
                String codigo_cie_ue = "---";//request.getParameter("codigo_cie_ue");
                String nombre_unidad_educativa = "---";//request.getParameter("nombre_unidad_educativa");
                String nivel = "---";//request.getParameter("nivel");
                String resago = "---";//request.getParameter("resago");
                String paralelo = "---";//request.getParameter("paralelo");
                /*verificar*/
                String turno_m = "---";//request.getParameter("turno_m");
                String turno_t = "---";//request.getParameter("turno_t");
                String turno_n = "---";//request.getParameter("turno_n");
                String est_provincia = request.getParameter("est_provincia");
                String est_zona = request.getParameter("est_zona");
                String est_seccion = request.getParameter("est_seccion");
                String est_calle = request.getParameter("est_calle");
                String est_localidad = request.getParameter("est_localidad");
                String est_telefono = request.getParameter("est_telefono");
                String est_nro = request.getParameter("est_nro");
                String idioma1 = request.getParameter("idioma1");
                String idioma2 = request.getParameter("idioma2");
                String idioma3 = request.getParameter("idioma3");
                String idioma4 = request.getParameter("idioma4");
                /*verificar*/
                String n1 = request.getParameter("nacion1");
                String n2 = request.getParameter("nacion2");
                String n3 = request.getParameter("nacion3");
                String n4 = request.getParameter("nacion4");
                String n5 = request.getParameter("nacion5");
                String n6 = request.getParameter("nacion6");
                String n7 = request.getParameter("nacion7");
                String n8 = request.getParameter("nacion8");
                String n9 = request.getParameter("nacion9");
                String n10 = request.getParameter("nacion10");
                String n11 = request.getParameter("nacion11");
                String n12 = request.getParameter("nacion12");
                String n13 = request.getParameter("nacion13");
                String n14 = request.getParameter("nacion14");
                String n15 = request.getParameter("nacion15");
                String n16 = request.getParameter("nacion16");
                String n17 = request.getParameter("nacion17");
                String n18 = request.getParameter("nacion18");
                String n19 = request.getParameter("nacion19");
                String n20 = request.getParameter("nacion20");
                String n21 = request.getParameter("nacion21");
                String n22 = request.getParameter("nacion22");
                String n23 = request.getParameter("nacion23");
                String n24 = request.getParameter("nacion24");
                String n25 = request.getParameter("nacion25");
                String n26 = request.getParameter("nacion26");
                String n27 = request.getParameter("nacion27");
                String n28 = request.getParameter("nacion28");
                String n29 = request.getParameter("nacion29");
                String n30 = request.getParameter("nacion30");
                String n31 = request.getParameter("nacion31");
                String n32 = request.getParameter("nacion32");
                String n33 = request.getParameter("nacion33");
                String n34 = request.getParameter("nacion34");
                String n35 = request.getParameter("nacion35");
                String n36 = request.getParameter("nacion36");
                String n37 = request.getParameter("nacion37");
                String n38 = request.getParameter("nacion38");
                String nacion = n1 + "|" + n2 + "|" + n3 + "|" + n4 + "|" + n5 + "|" + n6 + "|" + n7 + "|" + n8 + "|" + n9 + "|" + n10 + "|"
                        + n11 + "|" + n12 + "|" + n13 + "|" + n14 + "|" + n15 + "|" + n16 + "|" + n17 + "|" + n18 + "|" + n19 + "|" + n20 + "|" + n21 + "|"
                        + n22 + "|" + n23 + "|" + n24 + "|" + n25 + "|" + n26 + "|" + n27 + "|" + n28 + "|" + n29 + "|" + n30 + "|" + n31 + "|" + n32 + "|"
                        + n33 + "|" + n34 + "|" + n35 + "|" + n36 + "|" + n37 + "|" + n38 + "|";
                String nacion_otro = "---";//request.getParameter("nacion_otro");
                String centro_salud = request.getParameter("centro_salud");
                String centrosalud_asistido = request.getParameter("centrosalud_asistido");
                String discapacidad_sensorial = request.getParameter("discapacidad_sensorial");
                String discapacidad_motriz = request.getParameter("discapacidad_motriz");
                String discapacidad_mental = request.getParameter("discapacidad_mental");
                String discapacidad_otro = "---";//request.getParameter("discapacidad_otro");
                String discapacidad_tipo = request.getParameter("discapacidad_tipo");
                String agua = request.getParameter("agua");
                String electricidad = request.getParameter("electricidad");
                String water = request.getParameter("water");
               // String actividad = request.getParameter("actividad");
                String actividad_pasada = "---";//request.getParameter("actividad_pasada");
                String actividad_pago = request.getParameter("actividad_pago");
                /*verificar*/
                String i1 = request.getParameter("internet1");
                String i2 = request.getParameter("internet2");
                String i3 = request.getParameter("internet3");
                String i4 = request.getParameter("internet4");
                String i5 = request.getParameter("internet5");
                String internet = i1 + "|" + i2 + "|" + i3 + "|" + i4 + "|"+ i5 + "";
                String internet_frecuencia = request.getParameter("internet_frecuencia");
                String transporte = request.getParameter("transporte");
                String transporte_otro = request.getParameter("transporte_otro");
                String demora = request.getParameter("demora");
                String tutor_ci = request.getParameter("tutor_ci");
                String tutor_apellidos = request.getParameter("tutor_apellidos");
                String tutor_nombres = request.getParameter("tutor_nombres");
                String tutor_idioma = request.getParameter("tutor_idioma");
                String tutor_ocupacion = request.getParameter("tutor_ocupacion");
                String tutor_grado = request.getParameter("tutor_grado");
                String tutor_parentesco = "---";//request.getParameter("tutor_parentesco");
                String madre_ci = request.getParameter("madre_ci");
                String madre_apellidos = request.getParameter("madre_apellidos");
                String madre_nombres = request.getParameter("madre_nombres");
                String madre_idioma = request.getParameter("madre_idioma");
                String madre_ocupacion = request.getParameter("madre_ocupacion");
                String madre_grado = request.getParameter("madre_grado");
                String lugar = request.getParameter("lugar");
                String fecha = request.getParameter("fecha");
                String nro = request.getParameter("nro");
                fecha = fecha.replace("/", "-");
                Date fec = formato.parse(fecha);
                
                String carnet = request.getParameter("carnet");
                String complemento = request.getParameter("complemento");
                String expedido = request.getParameter("expedido");
                
                Estudiante estudiante = new Estudiante();
                estudiante.setId_estudiante(id_estudiante);
                estudiante.setPaterno(paterno.toUpperCase());
                estudiante.setMaterno(materno.toUpperCase());
                estudiante.setNombres(nombres.toUpperCase());
                estudiante.setId_sexo(id_sexo);
                estudiante.setPais(pais.toUpperCase());
                estudiante.setDepartamento(departamento.toUpperCase());
                estudiante.setProvincia(provincia.toUpperCase());
                estudiante.setLocalidad(localidad.toUpperCase());
                estudiante.setCodigo_rude(codigo_rude);
                estudiante.setDoc_identidad(doc_identidad);
                estudiante.setOficialia(oficialia);
                estudiante.setLibro(libro);
                estudiante.setPartida(partida);
                estudiante.setFolio(folio);
                estudiante.setFecha_nacimiento(fecha_nac);
                estudiante.setCodigo_cie_ue(codigo_cie_ue.toUpperCase());
                estudiante.setNombre_unidad_educativa(nombre_unidad_educativa.toUpperCase());
                estudiante.setNivel(nivel);
                estudiante.setResago(resago);
                estudiante.setParalelo(paralelo);
                estudiante.setTurno_m(turno_m);
                estudiante.setTurno_t(turno_t);
                estudiante.setTurno_n(turno_n);
                estudiante.setEst_provincia(est_provincia.toUpperCase());
                estudiante.setEst_seccion(est_seccion.toUpperCase());
                estudiante.setEst_localidad(est_localidad.toUpperCase());
                estudiante.setEst_zona(est_zona.toUpperCase());
                estudiante.setEst_calle(est_calle.toUpperCase());
                estudiante.setEst_telefono(est_telefono.toUpperCase());
                estudiante.setEst_nro(est_nro.toUpperCase());
                estudiante.setIdioma1(idioma1.toUpperCase());
                estudiante.setIdioma2(idioma2.toUpperCase());
                estudiante.setIdioma3(idioma3.toUpperCase());
                estudiante.setIdioma4(idioma4.toUpperCase());
                estudiante.setNacion(nacion);
                estudiante.setNacion_otro(nacion_otro.toUpperCase());
                estudiante.setCentro_salud(centro_salud);
                estudiante.setCentrosalud_asistido(centrosalud_asistido);
                estudiante.setDiscapacidad_sensorial(discapacidad_sensorial);
                estudiante.setDiscapacidad_motriz(discapacidad_motriz);
                estudiante.setDiscapacidad_mental(discapacidad_mental);
                estudiante.setDiscapacidad_otro(discapacidad_otro.toUpperCase());
                estudiante.setDiscapacidad_tipo(discapacidad_tipo);
                estudiante.setAgua(agua);
                estudiante.setElectricidad(electricidad);
                estudiante.setWater(water);
                //estudiante.setActividad(actividad);
                estudiante.setActividad_pasada(actividad_pasada.toUpperCase());
                estudiante.setActividad_pago(actividad_pago);
                estudiante.setInternet(internet);
                estudiante.setInternet_frecuencia(internet_frecuencia);
                estudiante.setTransporte(transporte);
                estudiante.setTransporte_otro(transporte_otro.toUpperCase());
                estudiante.setDemora(demora);
                estudiante.setTutor_ci(tutor_ci);
                estudiante.setTutor_apellidos(tutor_apellidos.toUpperCase());
                estudiante.setTutor_nombres(tutor_nombres.toUpperCase());
                estudiante.setTutor_idioma(tutor_idioma.toUpperCase());
                estudiante.setTutor_ocupacion(tutor_ocupacion.toUpperCase());
                estudiante.setTutor_grado(tutor_grado.toUpperCase());
                estudiante.setTutor_parentesco(tutor_parentesco.toUpperCase());
                estudiante.setMadre_ci(madre_ci.toUpperCase());
                estudiante.setMadre_apellidos(madre_apellidos.toUpperCase());
                estudiante.setMadre_nombres(madre_nombres.toUpperCase());
                estudiante.setMadre_idioma(madre_idioma.toUpperCase());
                estudiante.setMadre_ocupacion(madre_ocupacion.toUpperCase());
                estudiante.setMadre_grado(madre_grado.toUpperCase());
                estudiante.setLugar(lugar.toUpperCase());
                estudiante.setFecha(fec);
                estudiante.setFecha_registro(new Date());
                estudiante.setId_usuario(personal.getId_personal());
                estudiante.setNro(nro);
                
                estudiante.setCarnet(carnet);
                estudiante.setComplemento(complemento);
                estudiante.setExpedido(expedido);
                String discapacidad = request.getParameter("discapacidad");
                String ibc = request.getParameter("ibc");
                estudiante.setDiscapacidad(discapacidad);
                estudiante.setIbc(ibc);
                String discapacidad_grado = request.getParameter("discapacidad_grado");
                estudiante.setDiscapacidad_grado(discapacidad_grado);
                String est_departamento = request.getParameter("est_departamento");
                estudiante.setEst_departamento(est_departamento);
                String est_celular = request.getParameter("est_celular");
                estudiante.setEst_celular(est_celular);
                String acudio1 = request.getParameter("acudio1");
                String acudio2 = request.getParameter("acudio2");
                String acudio3 = request.getParameter("acudio3");
                String acudio4 = request.getParameter("acudio4");
                String acudio5 = request.getParameter("acudio5");
                String acudio6 = request.getParameter("acudio6");
                estudiante.setAcudio1(acudio1);
                estudiante.setAcudio2(acudio2);
                estudiante.setAcudio3(acudio3);
                estudiante.setAcudio4(acudio4);
                estudiante.setAcudio5(acudio5);
                estudiante.setAcudio6(acudio6);
                String tiene_seguro = request.getParameter("tiene_seguro");
                estudiante.setTiene_seguro(tiene_seguro);
                String tiene_agua = request.getParameter("tiene_agua");
                estudiante.setTiene_agua(tiene_agua);
                String tiene_banio = request.getParameter("tiene_banio");
                estudiante.setTiene_banio(tiene_banio);
                String tiene_alcantarillado = request.getParameter("tiene_alcantarillado");
                estudiante.setTiene_alcantarillado(tiene_alcantarillado);
                String tiene_electricidad= request.getParameter("tiene_electricidad");
                estudiante.setTiene_electricidad(tiene_electricidad);
                String tiene_basura = request.getParameter("tiene_basura");
                estudiante.setTiene_basura(tiene_basura);
                String vivienda = request.getParameter("vivienda");
                estudiante.setVivienda(vivienda);
                String frecuencia = request.getParameter("frecuencia");
                estudiante.setFrecuencia(frecuencia);
                String trabajo = request.getParameter("trabajo");
                estudiante.setTrabajo(trabajo);                
                String trabajo_meses = request.getParameter("mes1")+ "|" 
                        +request.getParameter("mes2")+ "|" 
                        +request.getParameter("mes3")+ "|" 
                        +request.getParameter("mes4")+ "|" 
                        +request.getParameter("mes5")+ "|" 
                        +request.getParameter("mes6")+ "|" 
                        +request.getParameter("mes7")+ "|" 
                        +request.getParameter("mes8")+ "|" 
                        +request.getParameter("mes9")+ "|" 
                        +request.getParameter("mes10")+ "|" 
                        +request.getParameter("mes11")+ "|" 
                        +request.getParameter("mes12")+ "|"
                        +request.getParameter("mes13");
                estudiante.setTrabajo_meses(trabajo_meses);
                String actividad = request.getParameter("actividad1")+ "|" 
                        +request.getParameter("actividad2")+ "|" 
                        +request.getParameter("actividad3")+ "|" 
                        +request.getParameter("actividad4")+ "|" 
                        +request.getParameter("actividad5")+ "|" 
                        +request.getParameter("actividad6")+ "|" 
                        +request.getParameter("actividad7")+ "|" 
                        +request.getParameter("actividad8")+ "|" 
                        +request.getParameter("actividad9")+ "|" 
                        +request.getParameter("actividad10")+ "|" 
                        +request.getParameter("actividad11")+ "|" 
                        +request.getParameter("actividad12")+ "|"
                        +request.getParameter("actividad13");
                estudiante.setActividad(actividad);
                String desc_actividad = request.getParameter("desc_actividad");
                estudiante.setDesc_actividad(desc_actividad);
                String turno = request.getParameter("turno1")+ "|" 
                        +request.getParameter("turno2")+ "|" 
                        +request.getParameter("turno3");
                estudiante.setTurno(turno);
                String trabajo_frecuancia = request.getParameter("trabajo_frecuancia");
                estudiante.setTrabajo_frecuancia(trabajo_frecuancia);
                String remuneracion = request.getParameter("remuneracion");
                estudiante.setRemuneracion(remuneracion);
                String remuneracion_tipo = request.getParameter("remuneracion_tipo");
                estudiante.setRemuneracion_tipo(remuneracion_tipo);
                String abandono = request.getParameter("abandono");
                estudiante.setAbandono(abandono);
                String abandono_por = request.getParameter("abandono_por1")+ "|" 
                        +request.getParameter("abandono_por2")+ "|" 
                        +request.getParameter("abandono_por3")+ "|" 
                        +request.getParameter("abandono_por4")+ "|" 
                        +request.getParameter("abandono_por5")+ "|" 
                        +request.getParameter("abandono_por6")+ "|" 
                        +request.getParameter("abandono_por7")+ "|" 
                        +request.getParameter("abandono_por8")+ "|" 
                        +request.getParameter("abandono_por9")+ "|" 
                        +request.getParameter("abandono_por10")+ "|" 
                        +request.getParameter("abandono_por11");
                estudiante.setAbandono_por(abandono_por);
                String abandono_otro = request.getParameter("abandono_otro");
                estudiante.setAbandono_otro(abandono_otro);
                String vive_con = request.getParameter("vive_con");
                estudiante.setVive_con(vive_con);
                String tutor_complemento = request.getParameter("tutor_complemento");
                estudiante.setTutor_complemento(tutor_complemento);
                String tutor_expedido = request.getParameter("tutor_expedido");
                estudiante.setTutor_expedido(tutor_expedido);
                String tutor_materno = request.getParameter("tutor_materno");
                estudiante.setTutor_materno(tutor_materno.toUpperCase());
                String tutor_fecha = request.getParameter("tutor_fecha");
                estudiante.setTutor_fecha(tutor_fecha);
                String madre_complemento = request.getParameter("madre_complemento");
                estudiante.setMadre_complemento(madre_complemento);
                String madre_expedido = request.getParameter("madre_expedido");
                estudiante.setMadre_expedido(madre_expedido);
                String madre_materno = request.getParameter("madre_materno");
                estudiante.setMadre_materno(madre_materno.toUpperCase());
                String madre_fecha = request.getParameter("madre_fecha");
                estudiante.setMadre_fecha(madre_fecha);
                String t_ci = request.getParameter("t_ci");
                estudiante.setT_ci(t_ci);
                String t_apellidos = request.getParameter("t_apellidos");
                estudiante.setT_apellidos(t_apellidos.toUpperCase());
                String t_nombres = request.getParameter("t_nombres");
                estudiante.setT_nombres(t_nombres.toUpperCase());
                String t_idioma = request.getParameter("t_idioma");
                estudiante.setT_idioma(t_idioma.toUpperCase());
                String t_ocupacion = request.getParameter("t_ocupacion");
                estudiante.setT_ocupacion(t_ocupacion.toUpperCase());
                String t_grado = request.getParameter("t_grado");
                estudiante.setT_grado(t_grado.toUpperCase());                
                String t_complemento = request.getParameter("t_complemento");
                estudiante.setT_complemento(t_complemento);
                String t_expedido = request.getParameter("t_expedido");
                estudiante.setT_expedido(t_expedido);
                String t_materno = request.getParameter("t_materno");
                estudiante.setT_materno(t_materno.toUpperCase());
                String t_fecha = request.getParameter("t_fecha");
                estudiante.setT_fecha(t_fecha);
                this.sigaa.setActualizarPersonaEstudiante(estudiante);
            }
            Informacion informacion = this.sigaa.getInformacionColegio(1);
            char[] cie = informacion.getSie().toCharArray();
            retorno.put("cie1", cie[0]);
            retorno.put("cie2", cie[1]);
            retorno.put("cie3", cie[2]);
            retorno.put("cie4", cie[3]);
            retorno.put("cie5", cie[4]);
            retorno.put("cie6", cie[5]);
            retorno.put("cie7", cie[6]);
            retorno.put("cie8", cie[7]);
            retorno.put("informacion", informacion);
            String id = request.getParameter("id");
            retorno.put("id_estudiante", id);
            Estudiante estudiante = this.sigaa.getEstudianteRudeById(id);
            retorno.put("estudiante", estudiante);
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date fecha_nacimiento = new Date();
            if (estudiante.getFecha_nacimiento() != null) {
                fecha_nacimiento = estudiante.getFecha_nacimiento();
                retorno.put("fecha_nacimiento", formato.format(fecha_nacimiento));
            } else {
                retorno.put("fecha_nacimiento", formato.format(fecha_nacimiento));
            }
            formato = new SimpleDateFormat("dd");
            String dia = formato.format(fecha_nacimiento);
            formato = new SimpleDateFormat("MM");
            String mes = formato.format(fecha_nacimiento);
            formato = new SimpleDateFormat("yyyy");
            String anio = formato.format(fecha_nacimiento);
            char[] diaa = dia.toCharArray();
            retorno.put("dia1", diaa[0]);
            retorno.put("dia2", diaa[1]);
            char[] mesa = mes.toCharArray();
            retorno.put("mes1", mesa[0]);
            retorno.put("mes2", mesa[1]);
            char[] anioa = anio.toCharArray();
            retorno.put("anio1", anioa[0]);
            retorno.put("anio2", anioa[1]);
            retorno.put("anio3", anioa[2]);
            retorno.put("anio4", anioa[3]);
            if (estudiante.getNacion() != null) {
                String[] naciones = estudiante.getNacion().split("\\|");
                for (int i = 0; i < naciones.length; i++) {
                    retorno.put("nacion" + naciones[i], naciones[i]);
                }
            }
            if (estudiante.getInternet() != null) {
                String[] internet = estudiante.getInternet().split("\\|");
                for (int i = 0; i < internet.length; i++) {
                    retorno.put("internet" + internet[i], internet[i]);
                }
            }
            formato = new SimpleDateFormat("dd/MM/yyyy");
            Date fecha = new Date();
            if (estudiante.getFecha() != null) {
                fecha = estudiante.getFecha();
                retorno.put("fecha", formato.format(fecha));
            } else {
                retorno.put("fecha", formato.format(fecha));
            }
            formato = new SimpleDateFormat("dd");
            dia = formato.format(fecha);
            formato = new SimpleDateFormat("MM");
            mes = formato.format(fecha);
            formato = new SimpleDateFormat("yyyy");
            anio = formato.format(fecha);
            diaa = dia.toCharArray();
            retorno.put("dia_1", diaa[0]);
            retorno.put("dia_2", diaa[1]);
            mesa = mes.toCharArray();
            retorno.put("mes_1", mesa[0]);
            retorno.put("mes_2", mesa[1]);
            anioa = anio.toCharArray();
            retorno.put("anio_1", anioa[0]);
            retorno.put("anio_2", anioa[1]);
            retorno.put("anio_3", anioa[2]);
            retorno.put("anio_4", anioa[3]);
            if (estudiante.getTrabajo_meses() != null) {
                String[] mestrabajo = estudiante.getTrabajo_meses().split("\\|");
                for (int i = 0; i < mestrabajo.length; i++) {
                    retorno.put("mes" + mestrabajo[i], mestrabajo[i]);
                }
            }
             if (estudiante.getActividad()!= null) {
                String[] actividad = estudiante.getActividad().split("\\|");
                for (int i = 0; i < actividad.length; i++) {
                    retorno.put("actividad" + actividad[i], actividad[i]);
                }
            }
             if (estudiante.getTurno()!= null) {
                String[] turno = estudiante.getTurno().split("\\|");
                for (int i = 0; i < turno.length; i++) {
                    retorno.put("turno" + turno[i], turno[i]);
                }
            }
              if (estudiante.getAbandono_por()!= null) {
                String[] por = estudiante.getAbandono_por().split("\\|");
                for (int i = 0; i < por.length; i++) {
                    retorno.put("abandono_por" + por[i], por[i]);
                }
            }
            return new ModelAndView(this.perfectView, retorno);
        } else {
            return new ModelAndView(this.ecxessTimeView, "url", url);
        }
    }

    private String formatoFechaNom(Date fecha) {
        String nuevo = new String();
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        nuevo = formato.format(fecha);
        return nuevo;
    }

    private String getReporteRude(String id_estudiante) {
        String dir = System.getenv("SIGAA_HOME1") + "/documentos/rude/";

        String archivo = "Rude_" + id_estudiante + "_" + this.formatoFechaNom(new Date()) + ".pdf";
//        String archivo = "Boletines_" + curso_gen.getCurso() + "_de_" + curso_gen.getCiclo() + "(" + this.formatoFechaNom(new Date()) + ").pdf";
        Font font8 = FontFactory.getFont(FontFactory.HELVETICA, 8);
        Font font8c = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE, 8);
        Font font8b = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8);
        Font font7b = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 7);
        Font font6 = FontFactory.getFont(FontFactory.HELVETICA, 7);
        Font font12 = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10);
        Font font6_n = FontFactory.getFont(FontFactory.HELVETICA, 6);

        Document document = new Document(PageSize.LEGAL);
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dir + archivo));
            float width = document.getPageSize().getWidth();
            document.open();
            Informacion informacion = this.sigaa.getInformacionColegio(1);
            Estudiante estudiante = this.sigaa.getEstudianteRudeById(id_estudiante);
            Image png = Image.getInstance(System.getenv("SIGAA_HOME1") + "/imagenes/rude.png");
            png.setAbsolutePosition(5, 45);
            png.scalePercent(24);
            document.add(png);
            Font helvetica = FontFactory.getFont(FontFactory.HELVETICA, 6);
            BaseFont bf_helv = helvetica.getCalculatedBaseFont(false);
            PdfContentByte canvas = writer.getDirectContent();
            canvas.saveState();
            canvas.stroke();
            canvas.restoreState();
            canvas.beginText();
            canvas.setFontAndSize(bf_helv, 10);
            //canvas.showTextAligned(Element.ALIGN_LEFT, "", 552, 938, 0);
            char[] cie = informacion.getSie().toCharArray();
            canvas.showTextAligned(Element.ALIGN_LEFT, Character.toString(cie[0]), 496, 878, 0);
            canvas.showTextAligned(Element.ALIGN_LEFT, Character.toString(cie[1]), 510, 878, 0);
            canvas.showTextAligned(Element.ALIGN_LEFT, Character.toString(cie[2]), 522, 878, 0);
            canvas.showTextAligned(Element.ALIGN_LEFT, Character.toString(cie[3]), 537, 878, 0);
            canvas.showTextAligned(Element.ALIGN_LEFT, Character.toString(cie[4]), 551, 878, 0);
            canvas.showTextAligned(Element.ALIGN_LEFT, Character.toString(cie[5]), 563, 878, 0);
            canvas.showTextAligned(Element.ALIGN_LEFT, Character.toString(cie[6]), 576, 878, 0);
            canvas.showTextAligned(Element.ALIGN_LEFT, Character.toString(cie[7]), 590, 878, 0);
          /*  if (informacion.getDependencia() != null) {
                if (informacion.getDependencia().equals("PUBLICO")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 42, 882, 0);
                } else if (informacion.getDependencia().equals("COMUNITARIA")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 104, 882, 0);
                } else if (informacion.getDependencia().equals("CONVENIO")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 165, 882, 0);
                } else if (informacion.getDependencia().equals("PRIVADA")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 213, 882, 0);
                }
            }*/
            canvas.showTextAligned(Element.ALIGN_LEFT, informacion.getColegio(), 200, 878, 0);
           // canvas.showTextAligned(Element.ALIGN_LEFT, informacion.getDistrito1(), 144, 865, 0);
           // canvas.showTextAligned(Element.ALIGN_LEFT, informacion.getDistrito2(), 192, 865, 0);
            char[] aux = estudiante.getPaterno().toCharArray();
            int sep = 0;
            for (int i = 0; i < aux.length; i++) {
                canvas.showTextAligned(Element.ALIGN_LEFT, Character.toString(aux[i]), 87 + sep, 833, 0);
                sep = (sep % 4 == 0) ? sep + 14 : sep + 13;
            }
            aux = estudiante.getMaterno().toCharArray();
            sep = 0;
            for (int i = 0; i < aux.length; i++) {
                canvas.showTextAligned(Element.ALIGN_LEFT, Character.toString(aux[i]), 87 + sep, 818, 0);
                sep = (sep % 4 == 0) ? sep + 14 : sep + 13;
            }
            aux = estudiante.getNombres().toCharArray();
            sep = 0;
            for (int i = 0; i < aux.length; i++) {
                canvas.showTextAligned(Element.ALIGN_LEFT, Character.toString(aux[i]), 87 + sep, 803, 0);
                sep = (sep % 4 == 0) ? sep + 14 : sep + 13;
            }
            canvas.showTextAligned(Element.ALIGN_LEFT, estudiante.getPais(), 52, 777, 0);
            canvas.showTextAligned(Element.ALIGN_LEFT, estudiante.getDepartamento(), 52, 761, 0);
            canvas.showTextAligned(Element.ALIGN_LEFT, estudiante.getProvincia(), 232, 777, 0);
            canvas.showTextAligned(Element.ALIGN_LEFT, estudiante.getLocalidad(), 232, 761, 0);
            canvas.showTextAligned(Element.ALIGN_LEFT, estudiante.getCodigo_rude(), 360, 833, 0);
            if (estudiante.getDoc_identidad() != null) {
                if (estudiante.getDoc_identidad().equals("ci")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 522, 805, 0);
                } else if (estudiante.getDoc_identidad().equals("pasaporte")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 580, 805, 0);
                }
            }
            canvas.showTextAligned(Element.ALIGN_LEFT, estudiante.getCarnet(), 492, 790, 0);
            Date fecha_nacimiento = new Date();
            if (estudiante.getFecha_nacimiento() != null) {
                fecha_nacimiento = estudiante.getFecha_nacimiento();
            }
            SimpleDateFormat formato = new SimpleDateFormat("dd");
            String dia = formato.format(fecha_nacimiento);
            formato = new SimpleDateFormat("MM");
            String mes = formato.format(fecha_nacimiento);
            formato = new SimpleDateFormat("yyyy");
            String anio = formato.format(fecha_nacimiento);
            aux = dia.toCharArray();
            canvas.showTextAligned(Element.ALIGN_LEFT, Character.toString(aux[0]), 370, 758, 0);
            canvas.showTextAligned(Element.ALIGN_LEFT, Character.toString(aux[1]), 383, 758, 0);
            aux = mes.toCharArray();
            canvas.showTextAligned(Element.ALIGN_LEFT, Character.toString(aux[0]), 406, 758, 0);
            canvas.showTextAligned(Element.ALIGN_LEFT, Character.toString(aux[1]), 417, 758, 0);
            aux = anio.toCharArray();
            canvas.showTextAligned(Element.ALIGN_LEFT, Character.toString(aux[0]), 438, 758, 0);
            canvas.showTextAligned(Element.ALIGN_LEFT, Character.toString(aux[1]), 451, 758, 0);
            canvas.showTextAligned(Element.ALIGN_LEFT, Character.toString(aux[2]), 464, 758, 0);
            canvas.showTextAligned(Element.ALIGN_LEFT, Character.toString(aux[3]), 477, 758, 0);
            if (estudiante.getId_sexo() != null) {
                if (estudiante.getId_sexo().equals("M")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 574, 748, 0);
                } else if (estudiante.getId_sexo().equals("F")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 574, 762, 0);
                }
            }
            canvas.showTextAligned(Element.ALIGN_LEFT, estudiante.getOficialia(), 363, 705, 0);
            canvas.showTextAligned(Element.ALIGN_LEFT, estudiante.getLibro(), 410, 705, 0);
            canvas.showTextAligned(Element.ALIGN_LEFT, estudiante.getPartida(), 508, 705, 0);
            canvas.showTextAligned(Element.ALIGN_LEFT, estudiante.getFolio(), 555, 705, 0);
            aux = estudiante.getCodigo_cie_ue().toCharArray();
            sep = 0;
            for (int i = 0; i < aux.length; i++) {
                canvas.showTextAligned(Element.ALIGN_LEFT, Character.toString(aux[i]), 92 + sep, 677, 0);
                sep = (sep % 4 == 0) ? sep + 14 : sep + 13;
            }
            canvas.showTextAligned(Element.ALIGN_LEFT, estudiante.getNombre_unidad_educativa(), 310, 677, 0);
            if (estudiante.getNivel() != null) {
                if (estudiante.getNivel().equals("inicial1")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 17, 628, 0);
                } else if (estudiante.getNivel().equals("inicial2")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 33, 628, 0);
                } else if (estudiante.getNivel().equals("primaria1")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 62, 628, 0);
                } else if (estudiante.getNivel().equals("primaria2")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 77, 628, 0);
                } else if (estudiante.getNivel().equals("primaria3")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 91, 628, 0);
                } else if (estudiante.getNivel().equals("primaria4")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 106, 628, 0);
                } else if (estudiante.getNivel().equals("primaria5")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 120, 628, 0);
                } else if (estudiante.getNivel().equals("primaria6")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 134, 628, 0);
                } else if (estudiante.getNivel().equals("secundaria1")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 158, 628, 0);
                } else if (estudiante.getNivel().equals("secundaria2")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 173, 628, 0);
                } else if (estudiante.getNivel().equals("secundaria3")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 187, 628, 0);
                } else if (estudiante.getNivel().equals("secundaria4")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 202, 628, 0);
                } else if (estudiante.getNivel().equals("secundaria5")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 216, 628, 0);
                } else if (estudiante.getNivel().equals("secundaria6")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 231, 628, 0);
                }
            }
            if (estudiante.getResago() != null) {
                if (estudiante.getResago().equals("resago1")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 269, 628, 0);
                } else if (estudiante.getResago().equals("resago2")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 283, 628, 0);
                } else if (estudiante.getResago().equals("resago3")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 298, 628, 0);
                } else if (estudiante.getResago().equals("resago4")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 312, 628, 0);
                }
            }

            if (estudiante.getParalelo() != null) {
                if (estudiante.getParalelo().equals("A")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 358, 623, 0);
                } else if (estudiante.getParalelo().equals("B")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 372, 623, 0);
                } else if (estudiante.getParalelo().equals("C")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 385, 623, 0);
                } else if (estudiante.getParalelo().equals("D")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 399, 623, 0);
                } else if (estudiante.getParalelo().equals("E")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 413, 623, 0);
                } else if (estudiante.getParalelo().equals("F")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 426, 623, 0);
                } else if (estudiante.getParalelo().equals("G")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 441, 623, 0);
                } else if (estudiante.getParalelo().equals("H")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 455, 623, 0);
                } else if (estudiante.getParalelo().equals("I")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 468, 623, 0);
                } else if (estudiante.getParalelo().equals("J")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 482, 623, 0);
                } else if (estudiante.getParalelo().equals("K")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 496, 623, 0);
                } else if (estudiante.getParalelo().equals("L")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 509, 623, 0);
                }
            }
            if (estudiante.getTurno_m() != null) {
                if (estudiante.getTurno_m().equals("M")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 540, 628, 0);
                }
            }
            if (estudiante.getTurno_t() != null) {
                if (estudiante.getTurno_t().equals("T")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 560, 628, 0);
                }
            }
            if (estudiante.getTurno_n() != null) {
                if (estudiante.getTurno_n().equals("N")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 580, 628, 0);
                }
            }
            canvas.showTextAligned(Element.ALIGN_LEFT, estudiante.getEst_provincia(), 95, 583, 0);
            canvas.showTextAligned(Element.ALIGN_LEFT, estudiante.getEst_seccion(), 95, 566, 0);
            canvas.showTextAligned(Element.ALIGN_LEFT, estudiante.getEst_localidad(), 95, 548, 0);
            canvas.showTextAligned(Element.ALIGN_LEFT, estudiante.getEst_zona(), 332, 583, 0);
            canvas.showTextAligned(Element.ALIGN_LEFT, estudiante.getEst_calle(), 332, 566, 0);
            canvas.showTextAligned(Element.ALIGN_LEFT, estudiante.getEst_telefono(), 332, 548, 0);
            canvas.showTextAligned(Element.ALIGN_LEFT, estudiante.getEst_nro(), 542, 548, 0);
            canvas.showTextAligned(Element.ALIGN_LEFT, estudiante.getIdioma1(), 20, 475, 0);
            canvas.showTextAligned(Element.ALIGN_LEFT, estudiante.getIdioma2(), 20, 442, 0);
            canvas.showTextAligned(Element.ALIGN_LEFT, estudiante.getIdioma3(), 20, 428, 0);
            canvas.showTextAligned(Element.ALIGN_LEFT, estudiante.getIdioma4(), 20, 415, 0);

            if (estudiante.getNacion() != null) {
                String[] naciones = estudiante.getNacion().split("\\|");
                for (int i = 0; i < naciones.length; i++) {
                    if (naciones[i] != null) {
                        if (naciones[i].equals("1")) {
                            canvas.showTextAligned(Element.ALIGN_LEFT, "x", 173, 486, 0);
                        } else if (naciones[i].equals("2")) {
                            canvas.showTextAligned(Element.ALIGN_LEFT, "x", 247, 486, 0);
                        } else if (naciones[i].equals("3")) {
                            canvas.showTextAligned(Element.ALIGN_LEFT, "x", 311, 486, 0);
                        } else if (naciones[i].equals("4")) {
                            canvas.showTextAligned(Element.ALIGN_LEFT, "x", 366, 486, 0);
                        } else if (naciones[i].equals("5")) {
                            canvas.showTextAligned(Element.ALIGN_LEFT, "x", 173, 478, 0);
                        } else if (naciones[i].equals("6")) {
                            canvas.showTextAligned(Element.ALIGN_LEFT, "x", 247, 478, 0);
                        } else if (naciones[i].equals("7")) {
                            canvas.showTextAligned(Element.ALIGN_LEFT, "x", 311, 478, 0);
                        } else if (naciones[i].equals("8")) {
                            canvas.showTextAligned(Element.ALIGN_LEFT, "x", 366, 478, 0);
                        } else if (naciones[i].equals("9")) {
                            canvas.showTextAligned(Element.ALIGN_LEFT, "x", 173, 470, 0);
                        } else if (naciones[i].equals("10")) {
                            canvas.showTextAligned(Element.ALIGN_LEFT, "x", 247, 470, 0);
                        } else if (naciones[i].equals("11")) {
                            canvas.showTextAligned(Element.ALIGN_LEFT, "x", 311, 470, 0);
                        } else if (naciones[i].equals("12")) {
                            canvas.showTextAligned(Element.ALIGN_LEFT, "x", 366, 470, 0);
                        } else if (naciones[i].equals("13")) {
                            canvas.showTextAligned(Element.ALIGN_LEFT, "x", 173, 462, 0);
                        } else if (naciones[i].equals("14")) {
                            canvas.showTextAligned(Element.ALIGN_LEFT, "x", 247, 462, 0);
                        } else if (naciones[i].equals("15")) {
                            canvas.showTextAligned(Element.ALIGN_LEFT, "x", 311, 462, 0);
                        } else if (naciones[i].equals("16")) {
                            canvas.showTextAligned(Element.ALIGN_LEFT, "x", 366, 462, 0);
                        } else if (naciones[i].equals("17")) {
                            canvas.showTextAligned(Element.ALIGN_LEFT, "x", 173, 454, 0);
                        } else if (naciones[i].equals("18")) {
                            canvas.showTextAligned(Element.ALIGN_LEFT, "x", 247, 454, 0);
                        } else if (naciones[i].equals("19")) {
                            canvas.showTextAligned(Element.ALIGN_LEFT, "x", 311, 454, 0);
                        } else if (naciones[i].equals("20")) {
                            canvas.showTextAligned(Element.ALIGN_LEFT, "x", 366, 454, 0);
                        } else if (naciones[i].equals("21")) {
                            canvas.showTextAligned(Element.ALIGN_LEFT, "x", 173, 446, 0);
                        } else if (naciones[i].equals("22")) {
                            canvas.showTextAligned(Element.ALIGN_LEFT, "x", 247, 446, 0);
                        } else if (naciones[i].equals("23")) {
                            canvas.showTextAligned(Element.ALIGN_LEFT, "x", 311, 446, 0);
                        } else if (naciones[i].equals("24")) {
                            canvas.showTextAligned(Element.ALIGN_LEFT, "x", 366, 446, 0);
                        } else if (naciones[i].equals("25")) {
                            canvas.showTextAligned(Element.ALIGN_LEFT, "x", 173, 438, 0);
                        } else if (naciones[i].equals("26")) {
                            canvas.showTextAligned(Element.ALIGN_LEFT, "x", 247, 438, 0);
                        } else if (naciones[i].equals("27")) {
                            canvas.showTextAligned(Element.ALIGN_LEFT, "x", 311, 438, 0);
                        } else if (naciones[i].equals("28")) {
                            canvas.showTextAligned(Element.ALIGN_LEFT, "x", 366, 438, 0);
                        } else if (naciones[i].equals("29")) {
                            canvas.showTextAligned(Element.ALIGN_LEFT, "x", 173, 430, 0);
                        } else if (naciones[i].equals("30")) {
                            canvas.showTextAligned(Element.ALIGN_LEFT, "x", 247, 430, 0);
                        } else if (naciones[i].equals("31")) {
                            canvas.showTextAligned(Element.ALIGN_LEFT, "x", 311, 430, 0);
                        } else if (naciones[i].equals("32")) {
                            canvas.showTextAligned(Element.ALIGN_LEFT, "x", 366, 430, 0);
                        } else if (naciones[i].equals("33")) {
                            canvas.showTextAligned(Element.ALIGN_LEFT, "x", 173, 422, 0);
                        } else if (naciones[i].equals("34")) {
                            canvas.showTextAligned(Element.ALIGN_LEFT, "x", 247, 422, 0);
                        } else if (naciones[i].equals("35")) {
                            canvas.showTextAligned(Element.ALIGN_LEFT, "x", 311, 422, 0);
                        } else if (naciones[i].equals("36")) {
                            canvas.showTextAligned(Element.ALIGN_LEFT, "x", 173, 414, 0);
                        } else if (naciones[i].equals("37")) {
                            canvas.showTextAligned(Element.ALIGN_LEFT, "x", 247, 414, 0);
                        } else if (naciones[i].equals("38")) {
                            canvas.showTextAligned(Element.ALIGN_LEFT, "x", 311, 414, 0);
                        }
                    }
                }
            }
            canvas.showTextAligned(Element.ALIGN_LEFT, estudiante.getNacion_otro(), 330, 414, 0);
            if (estudiante.getCentro_salud() != null) {
                if (estudiante.getCentro_salud().equals("si")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 555, 495, 0);
                } else if (estudiante.getCentro_salud().equals("no")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 583, 495, 0);
                }
            }
            if (estudiante.getCentrosalud_asistido() != null) {
                if (estudiante.getCentrosalud_asistido().equals("1")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 426, 474, 0);
                } else if (estudiante.getCentrosalud_asistido().equals("2")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 477, 474, 0);
                } else if (estudiante.getCentrosalud_asistido().equals("3")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 536, 474, 0);
                } else if (estudiante.getCentrosalud_asistido().equals("4")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 576, 474, 0);
                }
            }
            if (estudiante.getDiscapacidad_sensorial() != null) {
                if (estudiante.getDiscapacidad_sensorial().equals("si")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 451, 445, 0);
                } else if (estudiante.getDiscapacidad_sensorial().equals("no")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 492, 445, 0);
                }
            }
            if (estudiante.getDiscapacidad_motriz() != null) {
                if (estudiante.getDiscapacidad_motriz().equals("si")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 451, 435, 0);
                } else if (estudiante.getDiscapacidad_motriz().equals("no")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 492, 435, 0);
                }
            }
            if (estudiante.getDiscapacidad_mental() != null) {
                if (estudiante.getDiscapacidad_mental().equals("si")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 451, 425, 0);
                } else if (estudiante.getDiscapacidad_mental().equals("no")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 492, 425, 0);
                }
            }
            canvas.showTextAligned(Element.ALIGN_LEFT, estudiante.getDiscapacidad_otro(), 410, 414, 0);
            if (estudiante.getDiscapacidad_tipo() != null) {
                if (estudiante.getDiscapacidad_tipo().equals("1")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 565, 445, 0);
                } else if (estudiante.getDiscapacidad_tipo().equals("2")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 565, 435, 0);
                } else if (estudiante.getDiscapacidad_tipo().equals("3")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 565, 425, 0);
                }
            }
            if (estudiante.getAgua() != null) {
                if (estudiante.getAgua().equals("1")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 118, 379, 0);
                } else if (estudiante.getAgua().equals("2")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 118, 370, 0);
                } else if (estudiante.getAgua().equals("3")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 118, 361, 0);
                } else if (estudiante.getAgua().equals("4")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 118, 352, 0);
                } else if (estudiante.getAgua().equals("5")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 118, 342, 0);
                } else if (estudiante.getAgua().equals("6")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 118, 325, 0);
                }
            }
            if (estudiante.getElectricidad() != null) {
                if (estudiante.getElectricidad().equals("si")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 97, 302, 0);
                } else if (estudiante.getElectricidad().equals("no")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 122, 302, 0);
                }
            }
            if (estudiante.getWater() != null) {
                if (estudiante.getWater().equals("1")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 95, 276, 0);
                } else if (estudiante.getWater().equals("2")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 95, 266, 0);
                } else if (estudiante.getWater().equals("3")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 95, 256, 0);
                } else if (estudiante.getWater().equals("4")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 95, 246, 0);
                } else if (estudiante.getWater().equals("5")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 95, 236, 0);
                }
            }
            if (estudiante.getActividad() != null) {
                if (estudiante.getActividad().equals("1")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 324, 369, 0);
                } else if (estudiante.getActividad().equals("2")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 324, 359, 0);
                } else if (estudiante.getActividad().equals("3")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 324, 350, 0);
                } else if (estudiante.getActividad().equals("4")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 324, 340, 0);
                } else if (estudiante.getActividad().equals("5")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 324, 331, 0);
                } else if (estudiante.getActividad().equals("6")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 324, 321, 0);
                } else if (estudiante.getActividad().equals("7")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 324, 312, 0);
                } else if (estudiante.getActividad().equals("8")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 324, 302, 0);
                } else if (estudiante.getActividad().equals("9")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 324, 293, 0);
                }
            }
            canvas.showTextAligned(Element.ALIGN_LEFT, estudiante.getActividad_pasada(), 160, 263, 0);
            if (estudiante.getActividad_pago() != null) {
                if (estudiante.getActividad_pago().equals("si")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 230, 238, 0);
                } else if (estudiante.getActividad_pago().equals("no")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 256, 238, 0);
                }
            }
            if (estudiante.getNacion() != null) {
                String[] internet = estudiante.getInternet().split("\\|");
                for (int i = 0; i < internet.length; i++) {
                    if (internet[i] != null) {
                        if (internet[i].equals("1")) {
                            canvas.showTextAligned(Element.ALIGN_LEFT, "x", 432, 369, 0);
                        } else if (internet[i].equals("2")) {
                            canvas.showTextAligned(Element.ALIGN_LEFT, "x", 432, 359, 0);
                        } else if (internet[i].equals("3")) {
                            canvas.showTextAligned(Element.ALIGN_LEFT, "x", 432, 349, 0);
                        } else if (internet[i].equals("4")) {
                            canvas.showTextAligned(Element.ALIGN_LEFT, "x", 432, 339, 0);
                        }
                    }
                }
            }
            if (estudiante.getInternet_frecuencia() != null) {
                if (estudiante.getInternet_frecuencia().equals("1")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 418, 293, 0);
                } else if (estudiante.getInternet_frecuencia().equals("2")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 418, 275, 0);
                } else if (estudiante.getInternet_frecuencia().equals("3")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 418, 256, 0);
                }
            }
            if (estudiante.getTransporte() != null) {
                if (estudiante.getTransporte().equals("1")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 570, 369, 0);
                } else if (estudiante.getTransporte().equals("2")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 570, 358, 0);
                }
            }
            canvas.showTextAligned(Element.ALIGN_LEFT, estudiante.getTransporte_otro(), 508, 334, 0);
            if (estudiante.getDemora() != null) {
                if (estudiante.getDemora().equals("1")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 559, 274, 0);
                } else if (estudiante.getDemora().equals("2")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 559, 265, 0);
                } else if (estudiante.getDemora().equals("3")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 559, 253, 0);
                } else if (estudiante.getDemora().equals("4")) {
                    canvas.showTextAligned(Element.ALIGN_LEFT, "x", 559, 243, 0);
                }
            }
            canvas.showTextAligned(Element.ALIGN_LEFT, estudiante.getTutor_ci(), 112, 194, 0);
            canvas.showTextAligned(Element.ALIGN_LEFT, estudiante.getTutor_apellidos(), 112, 181, 0);
            canvas.showTextAligned(Element.ALIGN_LEFT, estudiante.getTutor_nombres(), 112, 168, 0);
            canvas.showTextAligned(Element.ALIGN_LEFT, estudiante.getTutor_idioma(), 112, 154, 0);
            canvas.showTextAligned(Element.ALIGN_LEFT, estudiante.getTutor_ocupacion(), 112, 141, 0);
            canvas.showTextAligned(Element.ALIGN_LEFT, estudiante.getTutor_grado(), 112, 128, 0);
            canvas.showTextAligned(Element.ALIGN_LEFT, estudiante.getTutor_parentesco(), 130, 115, 0);
            canvas.showTextAligned(Element.ALIGN_LEFT, estudiante.getMadre_ci(), 423, 191, 0);
            canvas.showTextAligned(Element.ALIGN_LEFT, estudiante.getMadre_apellidos(), 423, 178, 0);
            canvas.showTextAligned(Element.ALIGN_LEFT, estudiante.getMadre_nombres(), 423, 164, 0);
            canvas.showTextAligned(Element.ALIGN_LEFT, estudiante.getMadre_idioma(), 423, 150, 0);
            canvas.showTextAligned(Element.ALIGN_LEFT, estudiante.getMadre_ocupacion(), 423, 137, 0);
            canvas.showTextAligned(Element.ALIGN_LEFT, estudiante.getMadre_grado(), 423, 124, 0);
            aux = estudiante.getLugar().toCharArray();
            sep = 0;
            for (int i = 0; i < aux.length; i++) {
                canvas.showTextAligned(Element.ALIGN_LEFT, Character.toString(aux[i]), 70 + sep, 100, 0);
                sep = (sep % 4 == 0) ? sep + 14 : sep + 13;
            }
            //canvas.showTextAligned(Element.ALIGN_LEFT, estudiante.getLugar(), 70, 100, 0);
            //canvas.showTextAligned(Element.ALIGN_LEFT, estudiante.getFecha().toString(), 350, 100, 0);
            Date fecha = new Date();
            if (estudiante.getFecha() != null) {
                fecha = estudiante.getFecha();
            }
            formato = new SimpleDateFormat("dd");
            dia = formato.format(fecha);
            formato = new SimpleDateFormat("MM");
            mes = formato.format(fecha);
            formato = new SimpleDateFormat("yyyy");
            anio = formato.format(fecha);
            aux = dia.toCharArray();
            canvas.showTextAligned(Element.ALIGN_LEFT, Character.toString(aux[0]), 350, 98, 0);
            canvas.showTextAligned(Element.ALIGN_LEFT, Character.toString(aux[1]), 365, 98, 0);
            aux = mes.toCharArray();
            canvas.showTextAligned(Element.ALIGN_LEFT, Character.toString(aux[0]), 405, 98, 0);
            canvas.showTextAligned(Element.ALIGN_LEFT, Character.toString(aux[1]), 420, 98, 0);
            aux = anio.toCharArray();
            canvas.showTextAligned(Element.ALIGN_LEFT, Character.toString(aux[0]), 465, 98, 0);
            canvas.showTextAligned(Element.ALIGN_LEFT, Character.toString(aux[1]), 480, 98, 0);
            canvas.showTextAligned(Element.ALIGN_LEFT, Character.toString(aux[2]), 493, 98, 0);
            canvas.showTextAligned(Element.ALIGN_LEFT, Character.toString(aux[3]), 508, 98, 0);
            canvas.endText();
        } catch (Exception de) {
            de.printStackTrace();
        }
        document.close();
        return archivo;
    }
}
