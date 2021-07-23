package spring.sysmaqv.domain.logic;

import java.util.List;
import spring.sysmaqv.domain.Administrativo;
import spring.sysmaqv.domain.ChasideRespuesta;
import spring.sysmaqv.domain.Conducta;
import spring.sysmaqv.domain.Cualitativa;
import spring.sysmaqv.domain.Curso;
import spring.sysmaqv.domain.CursoMateria;
import spring.sysmaqv.domain.Deposito;
import spring.sysmaqv.domain.DepositoAsignada;
import spring.sysmaqv.domain.Docente;
import spring.sysmaqv.domain.Documento;
import spring.sysmaqv.domain.Estudiante;
import spring.sysmaqv.domain.Evaluacion;
import spring.sysmaqv.domain.Falta;
import spring.sysmaqv.domain.Familia;
import spring.sysmaqv.domain.Fecha_regnota;
import spring.sysmaqv.domain.Gestion;
import spring.sysmaqv.domain.Gondola;
import spring.sysmaqv.domain.Informacion;
import spring.sysmaqv.domain.Inscripcion;
import spring.sysmaqv.domain.Kardex;
import spring.sysmaqv.domain.KardexDetalle;
import spring.sysmaqv.domain.KardexGeneralClass;
import spring.sysmaqv.domain.Materia;
import spring.sysmaqv.domain.Nota;
import spring.sysmaqv.domain.PagoCole;
import spring.sysmaqv.domain.PagoPension;
import spring.sysmaqv.domain.PagoServicio;
import spring.sysmaqv.domain.PeriodoCurso;
import spring.sysmaqv.domain.Persona;
import spring.sysmaqv.domain.Promedio;
import spring.sysmaqv.domain.Servicio;
import spring.sysmaqv.domain.Tipo_pension;
import spring.sysmaqv.domain.Menu;
import spring.sysmaqv.domain.Personal;
import spring.sysmaqv.domain.PlanEvaluacion;

/**
 *
 * @author Marco Antonio Quenta Velasco
 * @since 12.6.2009
 */
public interface SigaaInterface {

    void setCambiarPassword(Personal personal);

    boolean personalActivo(Personal personal);

    Personal descripcionPersonal(Personal personal);

    Menu getMenuPersonal(Personal personal);

    boolean personalAutorizado(Personal personal, String url);

    Estudiante getEstudianteByID(String id_estudiante);

    Administrativo getAdministrativoByID(String id_administrativo);

    Docente getDocenteByID(String id_docente);

    // SIGAA
    List getTiposSexos();

    List getCargosAcademicos();

    List getTiposTitulos();

    List getTiposCargos();

    List getTiposCategorias();

    void setRegistrarPersona(Persona persona);

    void setRegistrarDocente(Docente docente);

    void setRegistrarAdministrativo(Administrativo administrativo);

    List getBuscarPersona(String search);

    Persona getPersonaById(String id_persona);

    void setActualizarPersona(Persona persona);

    Docente getDocenteById(String id_docente);

    Administrativo getAdministrativoById(String id_administrativo);

    void setDeletePersona(Persona persona);

    void setRegistrarFamilia(Familia familia);

    List getBuscarFamilia(String search);

    List getListaPersonas();

    List getListaFamilias(String search);

    List getListaFamiliasEst(String search);

    void setRegistrarCurso(Curso curso);

    List getTipoDias();

    List getTipoMeses();

    List getTipoAnios();

    List getListaCursos(int id_gestion);

    List getListaMaterias();

    List getListaDocentes();

    void setRegistrarCursoMaterias(CursoMateria curso_materia);

    List getListaCursoMateriasByIdGestion(int id_gestion);

    void setDeleteCursoMateriaById(String param);

    void setDeleteCursoMateriasByIdCursoAndIdGestion(CursoMateria cursomateria);

    void setAsignarDocenteTutorCurso(CursoMateria cursomateria);

    void setAsignarDocenteCoordinadorCurso(CursoMateria cursomateria);

    public void setRegistrarGestionAcademica(Gestion gestion);

    public void setRegistrarMaterias(Materia materias);

    public void setAsignarDocenteMateria(CursoMateria curmat);

    List getBuscarCurso(String search);

    List getBuscarMateria(String search);

    public void setActualizarCurso(Curso cur);

    public List getNroHijoEstudiante(String id_familia);

    void setRegistrarEstudiante(Estudiante estudiante);

    Gestion getGestionActivo();

    void setInscripcionEstudiante(Inscripcion inscripcion);

    public List getAlumnosById_familiaAndGestion(Inscripcion inscripcion);

    Estudiante getEstudianteByIdEstudiante(String id_estudiante);

    public void setEliminarCurso(String param);

    public void setRegistrarGondola(Gondola gondola);

    public void setActualizarMateria(Materia materias);

    List getBuscarGestion(int id_gestion);

    List getListaGestiones();

    List getAdministradorById_cargo(String id_cargo);

    public void setActualizarGestionAcademica(Gestion gestion);

    List getListaGondolas();

    public void setRegistrarServicio(Servicio servicio);

    public void setRegistrarPensiones(Tipo_pension tipo_pension);

    public List getServicioByID(int i);

    public List getCursosByIdDocenteAndIdGestion(CursoMateria cursomateria);

    public Curso getCursoById(CursoMateria cursomateria);

    public Administrativo getAdministrativoByIdPersona(String id_persona);

    public Docente getDocenteByIdPersona(String id_persona);

    public List getListaCuotas();

    public List getListaCuotasInicialesAndTotalesByGestion(int id_gestion);

    public List getListaDescuentos();

    public void setRegistrarPagoPensiones(PagoPension pagopension);

    public List getPagoPensionesByIdFamiliaAndIdGestion(PagoPension pagopension);

    public void setRegistrarPagoServicios(PagoServicio pagoservicio);

    public List getPagoServiciosByIdFamiliaAndIdGestion(PagoServicio pagoservicio);

    public void setUpdatePagoServicio(PagoServicio pagoservicio);

    public Materia getMateriaById(String id_materia);

    public void setRegistrarPlanEvaluaciones(PlanEvaluacion planevaluacion);

    public List getListaPlanEvaluacionesByIdCursoMateria(String id_curso_materia);

    public void setUpdatePlanEvaluaciones(PlanEvaluacion planevaluacion);

    public void setRegistrarEvaluacionesByCursoMateria(Evaluacion eva);

    public Gondola getGondolaByIdGondola(String id_gondola);

    public void setActualizarGondola(Gondola gondola);

    public void setUpdateEvaluacionByIdEstudianteAndIdEvaluacion(Evaluacion evaluacion);

    public void setUpdateEvaluacionByIdEstudianteAndIdEvaluacionKinder(Evaluacion evaluacion);

    public void setUpdateEvaluacionByIdEvaluacion(PlanEvaluacion p_eva);

    public List getTiposPensionesByIdGestion(int id_gestion);

    public List getNotasCualitativasById_curso_materia(String id_curso_materia);

    public void setGuardarCualitativa(Cualitativa cualit);

    public void setDeleteCualitativaById(String id_cualitativa);

    public void setUpdateCualitativaById(Cualitativa cualitativa);

    public List getEstudiantesByIdCursoAndIdGestion(Inscripcion inscripcion);

    public void setUpdateFamilia(Familia familia);

    public List getFechaRegNotaByIdGestion(int id_gestion);

    public Fecha_regnota getFechaRegNotaByIdEva(String id_eva);

    public void setUpdateFechaRegNota(Fecha_regnota fecharegnota);

    public List getCursosTutorByIdDocenteAndIdGestion(CursoMateria cursomateria);

    public Curso getCursoByIdCursoAndIdGestion(Curso curso);

    public List getTiposConductas();

    public void setSaveConductaByIdConducta(Conducta conducta);

    public void setUpdateEvaluaciones(Conducta conducta);

    public List setConductasByIdCursoAndIdEva(Conducta conducta);

    public void getUpdateEvaluacionesByIdEstudianteAndIdEvaluacion(Evaluacion evaluacion);

    public void getUpdateFaltasByIdEstudianteAndIdEva(Falta falta);

    public List getNotasCualitativasByIdDocenteAndIdcurso(CursoMateria cm);

    public Curso getCursoByIdCursoAndIdEstudiantesAndIdGestion(Curso curso);

    public Curso getCursoByIdCursoLibreta(Curso curso);

    public List getEvaluacionesEstudianteCursoMateria(Materia materia);

    public Persona getPersonaByIdEstudiante(String id_est);

    public void setUpdatePersona(Persona persona);

    public void setUpdateEstudiante(Estudiante estudiante);

    public void setUpdateInscripcion(Inscripcion inscripcion);

    public Inscripcion getInscripcionByIdInscripcion(int id_inscripcion);

    public PagoPension getPensionByIdEstudiante(int id_inscripcion);

    public List getServiciosByIdEstudiante(int id_inscripcion);

    public void setUpdatePagoPensiones(PagoPension pagopension, double diferencia);

    public void setDeletePagoServicio(PagoServicio pagoservicio);

    public void setAsignarDocenteRegFaltasCurso(CursoMateria cursomateria);

//    public List getCursosAsignadosRegistrarFaltasByIdDocenteAndIdGestion(CursoMateria cursomateria);
    public Falta getFaltaByIdFalta(int id_falta);

    public void setUpdatePlanEvaluacionesByIdEvaluacion(PlanEvaluacion planevaluacion);

    public void setInsertOrUpdateNotas(Nota nota);

    public Estudiante getDatosActualesEstudianteByIdEstudianteIdCursoIdGestion(Estudiante estudiante);

    public void setUpdateInscripcionFotografia(Inscripcion inscripcion);

    public List getFaltasByIdestudianteAndIdGestion(Falta falta);

    public Falta getFechaFaltaById(int id_fecha_falta);

    public Servicio getServicioByIdServicio(int id_servicio);

    public void setActualizarServicio(Servicio servicio);

    public List getEstadosOfEstudiantes();

    public Curso getConsultaEstudiantesAllByIdCursoAndIdGestion(Curso curso);

    public Curso getConsultaEstudiantesActivosByIdCursoAndIdGestion(Curso curso);

    public void setUpdateEstadoByIdEstudianteAndIdGestion(Estudiante estudiante);

    public List getEstudiantesAllByIdGestion(Curso curso);

    public List getEstudiantesActivosByIdGestion(Curso curso);

    public Curso getConsultasPromedioByIdCursoAndIdGestion(Curso curso);

    public List getNotasFinalesOfEstudiantes(Inscripcion inscripcion);

    public List getEstudiantesMorososServicioByIdGestion(Curso curso);

    public Curso getEstudiantesMorososServicioByIdCursoIdGestion(Curso curso);

    public List getPreguntasChaside();

    public Inscripcion getEstudianteByIdEstudianteAndGestion(Inscripcion inscripcion);

    public void setRegistrarChasideRespuesta(ChasideRespuesta chasiderespuesta);

    public void setActualizarChasideRespuesta(ChasideRespuesta chasiderespuesta);

    public List getInteresesByIdEstudianteAndIdGestion(Inscripcion inscripcion);

    public List getAptitudesByIdEstudianteAndIdGestion(Inscripcion inscripcion);

    public void setUpdateDocente(Docente docente);

    public void setUpdateAdministrativo(Administrativo administrativo);

    public void setUpdateAllPromedioByIdGestion(int id_gestion);

    public List getBuscarEstudianteByIdGestion(Estudiante estudiante);

    public void setDeleteFaltaByIdFaltaAndTipo(Falta falta);

    public void setRegistrarDeposito(Deposito deposito);

    public Deposito getDepositoByIdDeposito(Deposito deposito);

    public List getMateriasAndPlanEvaluacionesByIdCurso(Curso curso);

    public List getConductasByIdEstudianteAndIdGestion(Inscripcion inscripcion);

    public void getUpdateMontoDepositoAsignadaByIdDepAsignada(DepositoAsignada depasignada);

    public PagoPension getPagoPensionByCodigoAndIdGestion(Deposito deposito);

    public void setUpdateDepositoAsignadaByIdDepositoAsignada(DepositoAsignada depositoasignada);

    public void setUpdateObservacionByIdDepAsignada(DepositoAsignada depositoasignada);

    public List getDepositosAsignados(int id_gestion);

    public void setUpdateEstadoPagoPensionesByIdFamiliaAndIdGestion(PagoPension pagopension);

    public List getCuotasByCuota(int cuotaMax);

    public List getDetallesPagoPensionesCursos(int id_gestion, List cursos);

    public void setRegistrarPagoColeByIdInscripcion(PagoCole pagocole);

    public List getPagoColegioByIdInscripcion(List alumnos);

    public List getDepositosByIdGestionYOAnio(String anio);

    public List getDepositosAsignadasByCodigoAndIdGestion(Deposito deposito);

    public void setUpdateDepositoByIdDeposito(String id_deposito);

    public void setUpdateDepositoAsignadaByIdDepositoasignadaACero(int iddepasignadaaprocesar);

    public void setRegistrarDocumento(Documento documento);

    public List getListarDocumentosByArea(String string);

    public Persona getPersonaByIdCargo(String id_persona);

    public List getListarTodosDocumentosByArea();

    public List getListarDistintosDocumentosByArea();

    public void setUpdateEstudianteByIdest(Estudiante est);

    public void setGuardarKardex(Kardex kardex);

    public List getListaKardex(String id_curso_materia);

    public int getMaxIdkardex();

    public void setRegistrarKardexDetalle(KardexDetalle kardexdetalle);

//    public Kardex getKardexByIdkardex(int parseInt);
    public void setGuardarEditKardex(Kardex kardex);

    public void setRegistrarEditKardexDetalle(KardexDetalle kardexdetalle);

    public void setDeleteKardexDetalleById(int parseInt);

    public PeriodoCurso getPeriodoByIdcursoAndGestion(Curso curso);

    public void setActualizarCualitativa(Evaluacion evaluacion);

    public List getPeriodoCursoByIdgestion(int id_gestion);

    public Evaluacion getEvaluacionByIdestudianteAndIdevaluacion(Evaluacion evaluacion);

    public Estudiante getEstudianteKardexByIdEstudianteAndIdcursoAndIdgestion(String id_estudiante, String id_curso, int id_gestion);

    public Curso getCursoByIdCursoAndIdGestionSimple(Curso curso);

    public List getEvaluacionesByIdestudianteAndIdCursomateria(Evaluacion evaluacion);

    public Informacion getInformacionColegio(int i);

    public Estudiante getEstudianteRudeById(String id_estudiante);

    public void setActualizarPersonaEstudiante(Estudiante estudiante);
    /*2014*/

    public List getSigaaGestiones();

    public List getSigaaCursos();

    public List getSigaaPeriodoCursoByIdCursoAndIdgestion(PeriodoCurso pc);

    public List getSigaaGetEstudiantesByCursoAndGestionT(Estudiante estudiante);

    public List getSigaaGetEstudiantesByCursoAndGestionB(Estudiante estudiante);

    public void setUpdateDiagnostico(Conducta conducta);

    public List getDetalleDepositosBancarios(Deposito deposito);

    public List getKardexGeneral(KardexGeneralClass kardexGeneralClass);

    public List getKardexDetalle(KardexDetalle kardexDetalle);

    public void setActualizarKardex(KardexDetalle kardexDetalle);
}
