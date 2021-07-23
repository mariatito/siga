/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spring.sysmaqv.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Marco Antonio
 */
public class Estudiante extends Persona {

    private String id_estudiante;
    private String id_familia;
    private Date fecha_nacimiento;
    private int nro_hijo;
    private CursoMateria cursomateria = new CursoMateria();
    private List evaluaciones;
    private int codigo;
    private int id_gestion;
    private String id_curso;
    private List conductas;
    private List faltas;
    private List materias;
    private Date sfecha_nacimiento;
    private int nroEvasDefinidos;
    private String nombre_foto;
    private String id_gondola;
    private String id_estado;
    private String estado;
    private String curso;
    private double trim1;
    private double trim2;
    private double trim3;
    private double total;
    private int cant_materias;
    private List notas;
    private List profesiones;
    private List servicios;
    private int id_inscripcion;
    private String ciclo;
    private Familia familia = new Familia();
    private String estado_asignacion_pensiones;
    private PagoPension pagoPension = new PagoPension();
    private List depAsignadas;
    private int nroCuotas;
    
    private String desc_curso;
    private List cursomaterias;
    private int notapromedio;
    /*2014*/
    private int e1;
    private int e2;
    private int e3;
    private int e4;
    private String eva1;
    private String eva2;
    private String eva3;
    private String eva4;
    private String gestion;
    /*rude*/
    private String pais;
    private String departamento;
    private String provincia;
    private String localidad;
    private String codigo_rude;
    private String doc_identidad;
    private String oficialia;
    private String libro;
    private String partida;
    private String folio;
    private String nivel;
    private String resago;
    private String paralelo;
    private String turno_m;
    private String turno_t;
    private String turno_n;
    private String est_provincia;
    private String est_seccion;
    private String est_localidad;
    private String est_zona;
    private String est_calle;
    private String est_telefono;
    private String est_nro;
    private String idioma1;
    private String idioma2;
    private String idioma3;
    private String idioma4;
    private String nacion;
    private String nacion_otro;
    private String centro_salud;
    private String centrosalud_asistido;
    private String discapacidad_sensorial;
    private String discapacidad_motriz;
    private String discapacidad_mental;
    private String discapacidad_otro;
    private String discapacidad_tipo;
    private String agua;
    private String electricidad;
    private String water;
    private String actividad;
    private String actividad_pasada;
    private String actividad_pago;
    private String internet;
    private String internet_frecuencia;
    private String transporte;
    private String transporte_otro;
    private String demora;
    private String tutor_ci;
    private String tutor_apellidos;
    private String tutor_nombres;
    private String tutor_idioma;
    private String tutor_ocupacion;
    private String tutor_grado;
    private String tutor_parentesco;
    private String madre_ci;
    private String madre_apellidos;
    private String madre_nombres;
    private String madre_idioma;
    private String madre_ocupacion;
    private String madre_grado;
    private String lugar;
    private Date fecha;
    private String codigo_cie_ue;
    private String nombre_unidad_educativa;
    private String nro;
    private String id_usuario;
    
    private String carnet;
    private String complemento;
    private String expedido;
    private String discapacidad;
    private String ibc;
    private String discapacidad_grado;
    private String est_departamento;
    private String est_celular;
    private String acudio1;
    private String acudio2;
    private String acudio3;
    private String acudio4;
    private String acudio5;
    private String acudio6;
    private String tiene_seguro;
    private String tiene_agua;
    private String tiene_banio;
    private String tiene_alcantarillado;
    private String tiene_electricidad;
    private String tiene_basura;
    private String vivienda;
    private String frecuencia;
    private String trabajo;
    private String trabajo_meses;
    private String desc_actividad;
    private String turno;
    private String trabajo_frecuancia;
    private String remuneracion;
    private String remuneracion_tipo;
    private String abandono;
    private String abandono_por;
    private String abandono_otro;
    private String vive_con;
    private String tutor_complemento;
    private String tutor_expedido;
    private String tutor_materno;
    private String tutor_fecha;
    private String madre_complemento;
    private String madre_expedido;
    private String madre_materno;
    private String madre_fecha;
        private String t_ci;
    private String t_apellidos;
    private String t_nombres;
    private String t_idioma;
    private String t_ocupacion;
    private String t_grado;
      private String t_complemento;
    private String t_expedido;
    private String t_materno;
    private String t_fecha;
    

    public String getId_estudiante() {
        return id_estudiante;
    }

    public void setId_estudiante(String id_estudiante) {
        this.id_estudiante = id_estudiante;
    }

    public String getId_familia() {
        return id_familia;
    }

    public void setId_familia(String id_familia) {
        this.id_familia = id_familia;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public int getNro_hijo() {
        return nro_hijo;
    }

    public void setNro_hijo(int nro_hijo) {
        this.nro_hijo = nro_hijo;
    }

    public List getEvaluaciones() {
        return evaluaciones;
    }

    public void setEvaluaciones(List evaluaciones) {
        this.evaluaciones = evaluaciones;
    }

    public CursoMateria getCursomateria() {
        return cursomateria;
    }

    public void setCursomateria(CursoMateria cursomateria) {
        this.cursomateria = cursomateria;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getId_gestion() {
        return id_gestion;
    }

    public void setId_gestion(int id_gestion) {
        this.id_gestion = id_gestion;
    }

    public String getId_curso() {
        return id_curso;
    }

    public void setId_curso(String id_curso) {
        this.id_curso = id_curso;
    }

    public List getConductas() {
        return conductas;
    }

    public void setConductas(List conductas) {
        this.conductas = conductas;
    }

    public List getFaltas() {
        return faltas;
    }

    public void setFaltas(List faltas) {
        this.faltas = faltas;
    }

    public List getMaterias() {
        return materias;
    }

    public void setMaterias(List materias) {
        this.materias = materias;
    }

    public String getSfecha_nacimiento() {
        if (fecha_nacimiento == null) {
            return null;
        } else {
            String nuevo = new String();
            SimpleDateFormat formato = new SimpleDateFormat(" d 'de' MMMM 'de' yyyy");
            nuevo = formato.format(fecha_nacimiento);
            return nuevo;
        }
    }

    public int getNroEvasDefinidos() {
        return nroEvasDefinidos;
    }

    public void setNroEvasDefinidos(int nroEvasDefinidos) {
        this.nroEvasDefinidos = nroEvasDefinidos;
    }

    public String getNombre_foto() {
        return nombre_foto;
    }

    public void setNombre_foto(String nombre_foto) {
        this.nombre_foto = nombre_foto;
    }

    public String getId_gondola() {
        return id_gondola;
    }

    public void setId_gondola(String id_gondola) {
        this.id_gondola = id_gondola;
    }

    public String getId_estado() {
        return id_estado;
    }

    public void setId_estado(String id_estado) {
        this.id_estado = id_estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public double getTrim1() {
        return trim1;
    }

    public void setTrim1(double trim1) {
        this.trim1 = trim1;
    }

    public double getTrim2() {
        return trim2;
    }

    public void setTrim2(double trim2) {
        this.trim2 = trim2;
    }

    public double getTrim3() {
        return trim3;
    }

    public void setTrim3(double trim3) {
        this.trim3 = trim3;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getCant_materias() {
        return cant_materias;
    }

    public void setCant_materias(int cant_materias) {
        this.cant_materias = cant_materias;
    }

    public List getNotas() {
        return notas;
    }

    public void setNotas(List notas) {
        this.notas = notas;
    }

    public List getProfesiones() {
        return profesiones;
    }

    public void setProfesiones(List profesiones) {
        this.profesiones = profesiones;
    }

    public List getServicios() {
        return servicios;
    }

    public void setServicios(List servicios) {
        this.servicios = servicios;
    }

    public int getId_inscripcion() {
        return id_inscripcion;
    }

    public void setId_inscripcion(int id_inscripcion) {
        this.id_inscripcion = id_inscripcion;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public Familia getFamilia() {
        return familia;
    }

    public void setFamilia(Familia familia) {
        this.familia = familia;
    }

    public String getEstado_asignacion_pensiones() {
        return estado_asignacion_pensiones;
    }

    public void setEstado_asignacion_pensiones(String estado_asignacion_pensiones) {
        this.estado_asignacion_pensiones = estado_asignacion_pensiones;
    }

    public PagoPension getPagoPension() {
        return pagoPension;
    }

    public void setPagoPension(PagoPension pagoPension) {
        this.pagoPension = pagoPension;
    }

    public List getDepAsignadas() {
        return depAsignadas;
    }

    public void setDepAsignadas(List depAsignadas) {
        this.depAsignadas = depAsignadas;
    }

    public int getNroCuotas() {
        return nroCuotas;
    }

    public void setNroCuotas(int nroCuotas) {
        this.nroCuotas = nroCuotas;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getDesc_curso() {
        return desc_curso;
    }

    public void setDesc_curso(String desc_curso) {
        this.desc_curso = desc_curso;
    }

    public List getCursomaterias() {
        return cursomaterias;
    }

    public void setCursomaterias(List cursomaterias) {
        this.cursomaterias = cursomaterias;
    }

    public int getNotapromedio() {
        return notapromedio;
    }

    public void setNotapromedio(int notapromedio) {
        this.notapromedio = notapromedio;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getCodigo_rude() {
        return codigo_rude;
    }

    public void setCodigo_rude(String codigo_rude) {
        this.codigo_rude = codigo_rude;
    }

    public String getDoc_identidad() {
        return doc_identidad;
    }

    public void setDoc_identidad(String doc_identidad) {
        this.doc_identidad = doc_identidad;
    }

    public String getOficialia() {
        return oficialia;
    }

    public void setOficialia(String oficialia) {
        this.oficialia = oficialia;
    }

    public String getLibro() {
        return libro;
    }

    public void setLibro(String libro) {
        this.libro = libro;
    }

    public String getPartida() {
        return partida;
    }

    public void setPartida(String partida) {
        this.partida = partida;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getResago() {
        return resago;
    }

    public void setResago(String resago) {
        this.resago = resago;
    }

    public String getParalelo() {
        return paralelo;
    }

    public void setParalelo(String paralelo) {
        this.paralelo = paralelo;
    }

    public String getTurno_m() {
        return turno_m;
    }

    public void setTurno_m(String turno_m) {
        this.turno_m = turno_m;
    }

    public String getTurno_t() {
        return turno_t;
    }

    public void setTurno_t(String turno_t) {
        this.turno_t = turno_t;
    }

    public String getTurno_n() {
        return turno_n;
    }

    public void setTurno_n(String turno_n) {
        this.turno_n = turno_n;
    }

    public String getEst_provincia() {
        return est_provincia;
    }

    public void setEst_provincia(String est_provincia) {
        this.est_provincia = est_provincia;
    }

    public String getEst_seccion() {
        return est_seccion;
    }

    public void setEst_seccion(String est_seccion) {
        this.est_seccion = est_seccion;
    }

    public String getEst_localidad() {
        return est_localidad;
    }

    public void setEst_localidad(String est_localidad) {
        this.est_localidad = est_localidad;
    }

    public String getEst_zona() {
        return est_zona;
    }

    public void setEst_zona(String est_zona) {
        this.est_zona = est_zona;
    }

    public String getEst_calle() {
        return est_calle;
    }

    public void setEst_calle(String est_calle) {
        this.est_calle = est_calle;
    }

    public String getEst_telefono() {
        return est_telefono;
    }

    public void setEst_telefono(String est_telefono) {
        this.est_telefono = est_telefono;
    }

    public String getEst_nro() {
        return est_nro;
    }

    public void setEst_nro(String est_nro) {
        this.est_nro = est_nro;
    }

    public String getIdioma1() {
        return idioma1;
    }

    public void setIdioma1(String idioma1) {
        this.idioma1 = idioma1;
    }

    public String getIdioma2() {
        return idioma2;
    }

    public void setIdioma2(String idioma2) {
        this.idioma2 = idioma2;
    }

    public String getIdioma3() {
        return idioma3;
    }

    public void setIdioma3(String idioma3) {
        this.idioma3 = idioma3;
    }

    public String getIdioma4() {
        return idioma4;
    }

    public void setIdioma4(String idioma4) {
        this.idioma4 = idioma4;
    }

    public String getNacion() {
        return nacion;
    }

    public void setNacion(String nacion) {
        this.nacion = nacion;
    }

    public String getNacion_otro() {
        return nacion_otro;
    }

    public void setNacion_otro(String nacion_otro) {
        this.nacion_otro = nacion_otro;
    }

    public String getCentro_salud() {
        return centro_salud;
    }

    public void setCentro_salud(String centro_salud) {
        this.centro_salud = centro_salud;
    }

    public String getCentrosalud_asistido() {
        return centrosalud_asistido;
    }

    public void setCentrosalud_asistido(String centrosalud_asistido) {
        this.centrosalud_asistido = centrosalud_asistido;
    }

    public String getDiscapacidad_sensorial() {
        return discapacidad_sensorial;
    }

    public void setDiscapacidad_sensorial(String discapacidad_sensorial) {
        this.discapacidad_sensorial = discapacidad_sensorial;
    }

    public String getDiscapacidad_motriz() {
        return discapacidad_motriz;
    }

    public void setDiscapacidad_motriz(String discapacidad_motriz) {
        this.discapacidad_motriz = discapacidad_motriz;
    }

    public String getDiscapacidad_mental() {
        return discapacidad_mental;
    }

    public void setDiscapacidad_mental(String discapacidad_mental) {
        this.discapacidad_mental = discapacidad_mental;
    }

    public String getDiscapacidad_otro() {
        return discapacidad_otro;
    }

    public void setDiscapacidad_otro(String discapacidad_otro) {
        this.discapacidad_otro = discapacidad_otro;
    }

    public String getDiscapacidad_tipo() {
        return discapacidad_tipo;
    }

    public void setDiscapacidad_tipo(String discapacidad_tipo) {
        this.discapacidad_tipo = discapacidad_tipo;
    }

    public String getAgua() {
        return agua;
    }

    public void setAgua(String agua) {
        this.agua = agua;
    }

    public String getElectricidad() {
        return electricidad;
    }

    public void setElectricidad(String electricidad) {
        this.electricidad = electricidad;
    }

    public String getWater() {
        return water;
    }

    public void setWater(String water) {
        this.water = water;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getActividad_pasada() {
        return actividad_pasada;
    }

    public void setActividad_pasada(String actividad_pasada) {
        this.actividad_pasada = actividad_pasada;
    }

    public String getActividad_pago() {
        return actividad_pago;
    }

    public void setActividad_pago(String actividad_pago) {
        this.actividad_pago = actividad_pago;
    }

    public String getInternet() {
        return internet;
    }

    public void setInternet(String internet) {
        this.internet = internet;
    }

    public String getInternet_frecuencia() {
        return internet_frecuencia;
    }

    public void setInternet_frecuencia(String internet_frecuencia) {
        this.internet_frecuencia = internet_frecuencia;
    }

    public String getTransporte() {
        return transporte;
    }

    public void setTransporte(String transporte) {
        this.transporte = transporte;
    }

    public String getTransporte_otro() {
        return transporte_otro;
    }

    public void setTransporte_otro(String transporte_otro) {
        this.transporte_otro = transporte_otro;
    }

    public String getDemora() {
        return demora;
    }

    public void setDemora(String demora) {
        this.demora = demora;
    }

    public String getTutor_ci() {
        return tutor_ci;
    }

    public void setTutor_ci(String tutor_ci) {
        this.tutor_ci = tutor_ci;
    }

    public String getTutor_apellidos() {
        return tutor_apellidos;
    }

    public void setTutor_apellidos(String tutor_apellidos) {
        this.tutor_apellidos = tutor_apellidos;
    }

    public String getTutor_nombres() {
        return tutor_nombres;
    }

    public void setTutor_nombres(String tutor_nombres) {
        this.tutor_nombres = tutor_nombres;
    }

    public String getTutor_idioma() {
        return tutor_idioma;
    }

    public void setTutor_idioma(String tutor_idioma) {
        this.tutor_idioma = tutor_idioma;
    }

    public String getTutor_ocupacion() {
        return tutor_ocupacion;
    }

    public void setTutor_ocupacion(String tutor_ocupacion) {
        this.tutor_ocupacion = tutor_ocupacion;
    }

    public String getTutor_grado() {
        return tutor_grado;
    }

    public void setTutor_grado(String tutor_grado) {
        this.tutor_grado = tutor_grado;
    }

    public String getTutor_parentesco() {
        return tutor_parentesco;
    }

    public void setTutor_parentesco(String tutor_parentesco) {
        this.tutor_parentesco = tutor_parentesco;
    }

    public String getMadre_ci() {
        return madre_ci;
    }

    public void setMadre_ci(String madre_ci) {
        this.madre_ci = madre_ci;
    }

    public String getMadre_apellidos() {
        return madre_apellidos;
    }

    public void setMadre_apellidos(String madre_apellidos) {
        this.madre_apellidos = madre_apellidos;
    }

    public String getMadre_nombres() {
        return madre_nombres;
    }

    public void setMadre_nombres(String madre_nombres) {
        this.madre_nombres = madre_nombres;
    }

    public String getMadre_idioma() {
        return madre_idioma;
    }

    public void setMadre_idioma(String madre_idioma) {
        this.madre_idioma = madre_idioma;
    }

    public String getMadre_ocupacion() {
        return madre_ocupacion;
    }

    public void setMadre_ocupacion(String madre_ocupacion) {
        this.madre_ocupacion = madre_ocupacion;
    }

    public String getMadre_grado() {
        return madre_grado;
    }

    public void setMadre_grado(String madre_grado) {
        this.madre_grado = madre_grado;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCodigo_cie_ue() {
        return codigo_cie_ue;
    }

    public void setCodigo_cie_ue(String codigo_cie_ue) {
        this.codigo_cie_ue = codigo_cie_ue;
    }

    public String getNombre_unidad_educativa() {
        return nombre_unidad_educativa;
    }

    public void setNombre_unidad_educativa(String nombre_unidad_educativa) {
        this.nombre_unidad_educativa = nombre_unidad_educativa;
    }

    public String getNro() {
        return nro;
    }

    public void setNro(String nro) {
        this.nro = nro;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getE1() {
        return e1;
    }

    public void setE1(int e1) {
        this.e1 = e1;
    }

    public int getE2() {
        return e2;
    }

    public void setE2(int e2) {
        this.e2 = e2;
    }

    public int getE3() {
        return e3;
    }

    public void setE3(int e3) {
        this.e3 = e3;
    }

    public int getE4() {
        return e4;
    }

    public void setE4(int e4) {
        this.e4 = e4;
    }

    public String getEva1() {
        return eva1;
    }

    public void setEva1(String eva1) {
        this.eva1 = eva1;
    }

    public String getEva2() {
        return eva2;
    }

    public void setEva2(String eva2) {
        this.eva2 = eva2;
    }

    public String getEva3() {
        return eva3;
    }

    public void setEva3(String eva3) {
        this.eva3 = eva3;
    }

    public String getEva4() {
        return eva4;
    }

    public void setEva4(String eva4) {
        this.eva4 = eva4;
    }

    public String getGestion() {
        return gestion;
    }

    public void setGestion(String gestion) {
        this.gestion = gestion;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getExpedido() {
        return expedido;
    }

    public void setExpedido(String expedido) {
        this.expedido = expedido;
    }

    public String getDiscapacidad() {
        return discapacidad;
    }

    public void setDiscapacidad(String discapacidad) {
        this.discapacidad = discapacidad;
    }

    public String getIbc() {
        return ibc;
    }

    public void setIbc(String ibc) {
        this.ibc = ibc;
    }

    public String getDiscapacidad_grado() {
        return discapacidad_grado;
    }

    public void setDiscapacidad_grado(String discapacidad_grado) {
        this.discapacidad_grado = discapacidad_grado;
    }

    public String getEst_departamento() {
        return est_departamento;
    }

    public void setEst_departamento(String est_departamento) {
        this.est_departamento = est_departamento;
    }

    public String getEst_celular() {
        return est_celular;
    }

    public void setEst_celular(String est_celular) {
        this.est_celular = est_celular;
    }

    public String getAcudio1() {
        return acudio1;
    }

    public void setAcudio1(String acudio1) {
        this.acudio1 = acudio1;
    }

    public String getAcudio2() {
        return acudio2;
    }

    public void setAcudio2(String acudio2) {
        this.acudio2 = acudio2;
    }

    public String getAcudio3() {
        return acudio3;
    }

    public void setAcudio3(String acudio3) {
        this.acudio3 = acudio3;
    }

    public String getAcudio4() {
        return acudio4;
    }

    public void setAcudio4(String acudio4) {
        this.acudio4 = acudio4;
    }

    public String getAcudio5() {
        return acudio5;
    }

    public void setAcudio5(String acudio5) {
        this.acudio5 = acudio5;
    }

    public String getAcudio6() {
        return acudio6;
    }

    public void setAcudio6(String acudio6) {
        this.acudio6 = acudio6;
    }

    public String getTiene_seguro() {
        return tiene_seguro;
    }

    public void setTiene_seguro(String tiene_seguro) {
        this.tiene_seguro = tiene_seguro;
    }

    public String getTiene_agua() {
        return tiene_agua;
    }

    public void setTiene_agua(String tiene_agua) {
        this.tiene_agua = tiene_agua;
    }

    public String getTiene_banio() {
        return tiene_banio;
    }

    public void setTiene_banio(String tiene_banio) {
        this.tiene_banio = tiene_banio;
    }

    public String getTiene_alcantarillado() {
        return tiene_alcantarillado;
    }

    public void setTiene_alcantarillado(String tiene_alcantarillado) {
        this.tiene_alcantarillado = tiene_alcantarillado;
    }

    public String getTiene_electricidad() {
        return tiene_electricidad;
    }

    public void setTiene_electricidad(String tiene_electricidad) {
        this.tiene_electricidad = tiene_electricidad;
    }

    public String getTiene_basura() {
        return tiene_basura;
    }

    public void setTiene_basura(String tiene_basura) {
        this.tiene_basura = tiene_basura;
    }

    public String getVivienda() {
        return vivienda;
    }

    public void setVivienda(String vivienda) {
        this.vivienda = vivienda;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    public String getTrabajo() {
        return trabajo;
    }

    public void setTrabajo(String trabajo) {
        this.trabajo = trabajo;
    }

    public String getTrabajo_meses() {
        return trabajo_meses;
    }

    public void setTrabajo_meses(String trabajo_meses) {
        this.trabajo_meses = trabajo_meses;
    }

    public String getDesc_actividad() {
        return desc_actividad;
    }

    public void setDesc_actividad(String desc_actividad) {
        this.desc_actividad = desc_actividad;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getTrabajo_frecuancia() {
        return trabajo_frecuancia;
    }

    public void setTrabajo_frecuancia(String trabajo_frecuancia) {
        this.trabajo_frecuancia = trabajo_frecuancia;
    }

    public String getRemuneracion() {
        return remuneracion;
    }

    public void setRemuneracion(String remuneracion) {
        this.remuneracion = remuneracion;
    }

    public String getRemuneracion_tipo() {
        return remuneracion_tipo;
    }

    public void setRemuneracion_tipo(String remuneracion_tipo) {
        this.remuneracion_tipo = remuneracion_tipo;
    }

    public String getAbandono() {
        return abandono;
    }

    public void setAbandono(String abandono) {
        this.abandono = abandono;
    }

    public String getAbandono_por() {
        return abandono_por;
    }

    public void setAbandono_por(String abandono_por) {
        this.abandono_por = abandono_por;
    }

    public String getAbandono_otro() {
        return abandono_otro;
    }

    public void setAbandono_otro(String abandono_otro) {
        this.abandono_otro = abandono_otro;
    }

    public String getVive_con() {
        return vive_con;
    }

    public void setVive_con(String vive_con) {
        this.vive_con = vive_con;
    }

    public String getTutor_complemento() {
        return tutor_complemento;
    }

    public void setTutor_complemento(String tutor_complemento) {
        this.tutor_complemento = tutor_complemento;
    }

    public String getTutor_expedido() {
        return tutor_expedido;
    }

    public void setTutor_expedido(String tutor_expedido) {
        this.tutor_expedido = tutor_expedido;
    }

    public String getTutor_materno() {
        return tutor_materno;
    }

    public void setTutor_materno(String tutor_materno) {
        this.tutor_materno = tutor_materno;
    }

    public String getTutor_fecha() {
        return tutor_fecha;
    }

    public void setTutor_fecha(String tutor_fecha) {
        this.tutor_fecha = tutor_fecha;
    }

    public String getMadre_complemento() {
        return madre_complemento;
    }

    public void setMadre_complemento(String madre_complemento) {
        this.madre_complemento = madre_complemento;
    }

    public String getMadre_expedido() {
        return madre_expedido;
    }

    public void setMadre_expedido(String madre_expedido) {
        this.madre_expedido = madre_expedido;
    }

    public String getMadre_materno() {
        return madre_materno;
    }

    public void setMadre_materno(String madre_materno) {
        this.madre_materno = madre_materno;
    }

    public String getMadre_fecha() {
        return madre_fecha;
    }

    public void setMadre_fecha(String madre_fecha) {
        this.madre_fecha = madre_fecha;
    }

    public String getT_ci() {
        return t_ci;
    }

    public void setT_ci(String t_ci) {
        this.t_ci = t_ci;
    }

    public String getT_apellidos() {
        return t_apellidos;
    }

    public void setT_apellidos(String t_apellidos) {
        this.t_apellidos = t_apellidos;
    }

    public String getT_nombres() {
        return t_nombres;
    }

    public void setT_nombres(String t_nombres) {
        this.t_nombres = t_nombres;
    }

    public String getT_idioma() {
        return t_idioma;
    }

    public void setT_idioma(String t_idioma) {
        this.t_idioma = t_idioma;
    }

    public String getT_ocupacion() {
        return t_ocupacion;
    }

    public void setT_ocupacion(String t_ocupacion) {
        this.t_ocupacion = t_ocupacion;
    }

    public String getT_grado() {
        return t_grado;
    }

    public void setT_grado(String t_grado) {
        this.t_grado = t_grado;
    }

    public String getT_complemento() {
        return t_complemento;
    }

    public void setT_complemento(String t_complemento) {
        this.t_complemento = t_complemento;
    }

    public String getT_expedido() {
        return t_expedido;
    }

    public void setT_expedido(String t_expedido) {
        this.t_expedido = t_expedido;
    }

    public String getT_materno() {
        return t_materno;
    }

    public void setT_materno(String t_materno) {
        this.t_materno = t_materno;
    }

    public String getT_fecha() {
        return t_fecha;
    }

    public void setT_fecha(String t_fecha) {
        this.t_fecha = t_fecha;
    }

}
