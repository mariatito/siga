package spring.sysmaqv.domain.logic;

import java.util.List;
import spring.sysmaqv.dao.AdministracionDao;
import spring.sysmaqv.dao.AsistenciasDao;
import spring.sysmaqv.dao.PensionesDao;
import spring.sysmaqv.dao.PersonalDao;
import spring.sysmaqv.dao.ProfesoresDao;
import spring.sysmaqv.dao.RegistrosDao;
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
import spring.sysmaqv.domain.Menu;
import spring.sysmaqv.domain.Nota;
import spring.sysmaqv.domain.PagoCole;
import spring.sysmaqv.domain.PagoPension;
import spring.sysmaqv.domain.PagoServicio;
import spring.sysmaqv.domain.PeriodoCurso;
import spring.sysmaqv.domain.Persona;
import spring.sysmaqv.domain.Personal;
import spring.sysmaqv.domain.PlanEvaluacion;
import spring.sysmaqv.domain.Servicio;
import spring.sysmaqv.domain.Tipo_pension;

/**
 *
 * @author Marco Antonio Quenta Velasco
 * @since 14.5.2009
 */
public class SigaaImpl implements SigaaInterface {

    private AdministracionDao administracionDao;
    private PersonalDao personalDao;
    private RegistrosDao registrosDao;
    private AsistenciasDao asistenciasDao;
    private ProfesoresDao profesoresDao;
    private PensionesDao pensionesDao;
    //-------------------------------------------------------------------------
    // Setter methods for dependency injection
    //-------------------------------------------------------------------------

    public void setAdministracionDao(AdministracionDao administracionDao) {
        this.administracionDao = administracionDao;
    }

    public void setPersonalDao(PersonalDao personalDao) {
        this.personalDao = personalDao;
    }

    public void setRegistrosDao(RegistrosDao registrosDao) {
        this.registrosDao = registrosDao;
    }

    public void setAsistenciasDao(AsistenciasDao asistenciasDao) {
        this.asistenciasDao = asistenciasDao;
    }

    public void setProfesoresDao(ProfesoresDao profesoresDao) {
        this.profesoresDao = profesoresDao;
    }

    public void setPensionesDao(PensionesDao pensionesDao) {
        this.pensionesDao = pensionesDao;
    }
    //-------------------------------------------------------------------------
    // Operation methods, implementing the PetStoreFacade interface
    //-------------------------------------------------------------------------

    public void setCambiarPassword(Personal personal) {
        this.personalDao.setCambiarPassword(personal);
    }

    public boolean personalActivo(Personal personal) {
        return this.personalDao.personalActivo(personal);
    }

    public Personal descripcionPersonal(Personal personal) {
        return this.personalDao.descripcionPersonal(personal);
    }

    public Menu getMenuPersonal(Personal personal) {
        return this.personalDao.getMenuPersonal(personal);
    }

    public boolean personalAutorizado(Personal personal, String url) {
        return this.personalDao.personalAutorizado(personal, url);
    }

    public Estudiante getEstudianteByID(String id_estudiante) {
        return this.personalDao.getEstudianteByID(id_estudiante);
    }

    public Administrativo getAdministrativoByID(String id_administrativo) {
        return this.administracionDao.getAdministrativoByID(id_administrativo);
    }

    public Docente getDocenteByID(String id_docente) {
        return this.personalDao.getDocenteByID(id_docente);
    }

    // SIGAA
    public List getTiposSexos() {
        return this.asistenciasDao.getTiposSexos();
    }

    public List getCargosAcademicos() {
        return this.asistenciasDao.getCargosAcademicos();
    }

    public List getTiposTitulos() {
        return this.asistenciasDao.getTiposTitulos();
    }

    public List getTiposCargos() {
        return this.asistenciasDao.getTiposCargos();
    }

    public List getTiposCategorias() {
        return this.asistenciasDao.getTiposCategorias();
    }

    public void setRegistrarPersona(Persona persona) {
        this.administracionDao.setRegistrarPersona(persona);
    }

    public void setRegistrarDocente(Docente docente) {
        this.profesoresDao.setRegistrarDocente(docente);
    }

    public void setRegistrarAdministrativo(Administrativo administrativo) {
        this.administracionDao.setRegistrarAdministrativo(administrativo);
    }

    public List getBuscarPersona(String search) {
        return this.asistenciasDao.getBuscarPersona(search);
    }

    public Persona getPersonaById(String id_persona) {
        return this.asistenciasDao.getPersonaById(id_persona);
    }

    public void setActualizarPersona(Persona persona) {
        this.administracionDao.setActualizarPersona(persona);
    }

    public Docente getDocenteById(String id_docente) {
        return this.asistenciasDao.getDocenteById(id_docente);
    }

    public Administrativo getAdministrativoById(String id_administrativo) {
        return this.asistenciasDao.getAdministrativoById(id_administrativo);
    }

    public void setDeletePersona(Persona persona) {
        this.administracionDao.setDeletePersona(persona);
    }

    public void setRegistrarFamilia(Familia familia) {
        this.administracionDao.setRegistrarFamilia(familia);
    }

    public List getBuscarFamilia(String search) {
        return this.asistenciasDao.getBuscarFamilia(search);
    }

    public List getListaPersonas() {
        return this.asistenciasDao.getListaPersonas();
    }

    public List getListaFamilias(String search) {
        return this.asistenciasDao.getListaFamilias(search);
    }
    public List getListaFamiliasEst(String search){
        return this.asistenciasDao.getListaFamiliasEst(search);
    }

    public void setRegistrarCurso(Curso curso) {
        this.administracionDao.setRegistrarCurso(curso);
    }

    public List getTipoDias() {
        return this.asistenciasDao.getTipoDias();
    }

    public List getTipoMeses() {
        return this.asistenciasDao.getTipoMeses();
    }

    public List getListaCursos(int id_gestion) {
        return this.asistenciasDao.getListaCursos(id_gestion);
    }

    public List getListaMaterias() {
        return this.asistenciasDao.getListaMaterias();
    }

    public List getListaDocentes() {
        return this.asistenciasDao.getListaDocentes();
    }

    public void setRegistrarCursoMaterias(CursoMateria curso_materia) {
        this.administracionDao.setRegistrarCursoMaterias(curso_materia);
    }

    public List getListaCursoMateriasByIdGestion(int id_gestion) {
        return this.asistenciasDao.getListaCursoMateriasByIdGestion(id_gestion);
    }

    public void setDeleteCursoMateriaById(String param) {
        this.administracionDao.setDeleteCursoMateriaById(param);
    }

    public void setDeleteCursoMateriasByIdCursoAndIdGestion(CursoMateria cursomateria) {
        this.administracionDao.setDeleteCursoMateriasByIdCursoAndIdGestion(cursomateria);
    }

    public void setAsignarDocenteTutorCurso(CursoMateria cursomateria) {
        this.administracionDao.setAsignarDocenteTutorCurso(cursomateria);
    }
     public void setAsignarDocenteCoordinadorCurso(CursoMateria cursomateria) {
        this.administracionDao.setAsignarDocenteCoordinadorCurso(cursomateria);
    }

    public List getTipoAnios() {
        return this.asistenciasDao.getTipoAnios();
    }

    public void setRegistrarGestionAcademica(Gestion gestion) {
        this.administracionDao.setRegistrarGestionAcademica(gestion);
    }

    public void setRegistrarMaterias(Materia materias) {
        this.administracionDao.setRegistrarMaterias(materias);
    }

    public void setAsignarDocenteMateria(CursoMateria curmat) {
        this.administracionDao.setAsignarDocenteMateria(curmat);
    }

    public List getBuscarCurso(String search) {
        return asistenciasDao.getBuscarCurso(search);
    }

    public void setActualizarCurso(Curso cur) {
        this.administracionDao.setActualizarCurso(cur);
    }

    public List getNroHijoEstudiante(String id_familia) {
        return asistenciasDao.getNroHijoEstudiante(id_familia);
    }

    public void setRegistrarEstudiante(Estudiante estudiante) {
        this.administracionDao.setRegistrarEstudiante(estudiante);
    }

    public Gestion getGestionActivo() {
        return asistenciasDao.getGestionActivo();
    }

    public void setInscripcionEstudiante(Inscripcion inscripcion) {
        this.registrosDao.setInscripcionEstudiante(inscripcion);
    }

    public List getAlumnosById_familiaAndGestion(Inscripcion inscripcion) {
        return asistenciasDao.getAlumnosById_familiaAndGestion(inscripcion);
    }

    public Estudiante getEstudianteByIdEstudiante(String id_estudiante) {
        return asistenciasDao.getEstudianteByIdEstudiante(id_estudiante);
    }

    public void setEliminarCurso(String param) {
        this.administracionDao.setEliminarCurso(param);
    }

    public void setRegistrarGondola(Gondola gondola) {
        this.administracionDao.setRegistrarGondola(gondola);
    }

    public List getBuscarMateria(String search) {
        return asistenciasDao.getBuscarMateria(search);
    }

    public void setActualizarMateria(Materia materias) {
        this.administracionDao.setActualizarMateria(materias);
    }

    public List getBuscarGestion(int id_gestion) {
        return asistenciasDao.getBuscarGestion(id_gestion);
    }

    public List getListaGestiones() {
        return asistenciasDao.getListaGestiones();
    }

    public List getAdministradorById_cargo(String id_cargo) {
        return asistenciasDao.getAdministradorById_cargo(id_cargo);
    }

    public void setActualizarGestionAcademica(Gestion gestion) {
        this.administracionDao.setActualizarGestionAcademica(gestion);
    }

    public List getListaGondolas() {
        return asistenciasDao.getListaGondolas();
    }

    public void setRegistrarServicio(Servicio servicio) {
        this.administracionDao.setRegistrarServicio(servicio);

    }

    public void setRegistrarPensiones(Tipo_pension tipo_pension) {
        this.administracionDao.setRegistrarPensiones(tipo_pension);
    }

    public List getServicioByID(int i) {
        return asistenciasDao.getServicioByID(i);
    }

    public List getCursosByIdDocenteAndIdGestion(CursoMateria cursomateria) {
        return profesoresDao.getCursosByIdDocenteAndIdGestion(cursomateria);
    }

    public Curso getCursoById(CursoMateria cursomateria) {
        return asistenciasDao.getCursoById(cursomateria);
    }

    public Administrativo getAdministrativoByIdPersona(String id_persona) {
        return asistenciasDao.getAdministrativoByIdPersona(id_persona);
    }

    public Docente getDocenteByIdPersona(String id_persona) {
        return asistenciasDao.getDocenteByIdPersona(id_persona);
    }

    public List getListaCuotas() {
        return asistenciasDao.getListaCuotas();
    }

    public List getListaCuotasInicialesAndTotalesByGestion(int id_gestion) {
        return asistenciasDao.getListaCuotasInicialesAndTotalesByGestion(id_gestion);
    }

    public List getListaDescuentos() {
        return asistenciasDao.getListaDescuentos();
    }

    public void setRegistrarPagoPensiones(PagoPension pagopension) {
        this.administracionDao.setRegistrarPagoPensiones(pagopension);
    }

    public List getPagoPensionesByIdFamiliaAndIdGestion(PagoPension pagopension) {
        return asistenciasDao.getPagoPensionesByIdFamiliaAndIdGestion(pagopension);
    }

    public void setRegistrarPagoServicios(PagoServicio pagoservicio) {
        this.administracionDao.setRegistrarPagoServicios(pagoservicio);
    }

    public List getPagoServiciosByIdFamiliaAndIdGestion(PagoServicio pagoservicio) {
        return asistenciasDao.getPagoServiciosByIdFamiliaAndIdGestion(pagoservicio);
    }

    public void setUpdatePagoServicio(PagoServicio pagoservicio) {
        this.administracionDao.setUpdatePagoServicio(pagoservicio);
    }

    public Materia getMateriaById(String id_materia) {
        return this.asistenciasDao.getMateriaById(id_materia);
    }

    public void setRegistrarPlanEvaluaciones(PlanEvaluacion planevaluacion) {
        this.administracionDao.setRegistrarPlanEvaluaciones(planevaluacion);
    }

    public List getListaPlanEvaluacionesByIdCursoMateria(String id_curso_materia) {
        return this.asistenciasDao.getListaPlanEvaluacionesByIdCursoMateria(id_curso_materia);
    }

    public void setUpdatePlanEvaluaciones(PlanEvaluacion planevaluacion) {
        this.administracionDao.setUpdatePlanEvaluaciones(planevaluacion);
    }

    public void setRegistrarEvaluacionesByCursoMateria(Evaluacion eva) {
        this.administracionDao.setRegistrarEvaluacionesByCursoMateria(eva);
    }

    public Gondola getGondolaByIdGondola(String id_gondola) {
        return this.asistenciasDao.getGondolaByIdGondola(id_gondola);
    }

    public void setActualizarGondola(Gondola gondola) {
        this.administracionDao.setActualizarGondola(gondola);
    }

    public void setUpdateEvaluacionByIdEstudianteAndIdEvaluacion(Evaluacion evaluacion) {
        this.administracionDao.setUpdateEvaluacionByIdEstudianteAndIdEvaluacion(evaluacion);
    }
    public void setUpdateEvaluacionByIdEstudianteAndIdEvaluacionKinder(Evaluacion evaluacion) {
        this.administracionDao.setUpdateEvaluacionByIdEstudianteAndIdEvaluacionKinder(evaluacion);
    }

    public void setUpdateEvaluacionByIdEvaluacion(PlanEvaluacion p_eva) {
        this.administracionDao.setUpdateEvaluacionByIdEvaluacion(p_eva);
    }

    public List getTiposPensionesByIdGestion(int id_gestion) {
        return this.asistenciasDao.getTiposPensionesByIdGestion(id_gestion);
    }

    public List getNotasCualitativasById_curso_materia(String id_curso_materia) {
        return this.asistenciasDao.getNotasCualitativasById_curso_materia(id_curso_materia);
    }

    public void setGuardarCualitativa(Cualitativa cualit) {
        this.administracionDao.setGuardarCualitativa(cualit);
    }

    public void setDeleteCualitativaById(String id_cualitativa) {
        this.administracionDao.setDeleteCualitativaById(id_cualitativa);
    }

    public void setUpdateCualitativaById(Cualitativa cualitativa) {
        this.administracionDao.setUpdateCualitativaById(cualitativa);
    }

    public List getEstudiantesByIdCursoAndIdGestion(Inscripcion inscripcion) {
        return this.asistenciasDao.getEstudiantesByIdCursoAndIdGestion(inscripcion);
    }

    public void setUpdateFamilia(Familia familia) {
        this.administracionDao.setUpdateFamilia(familia);
    }

    public List getFechaRegNotaByIdGestion(int id_gestion) {
        return this.asistenciasDao.getFechaRegNotaByIdGestion(id_gestion);
    }

    public Fecha_regnota getFechaRegNotaByIdEva(String id_eva) {
        return this.asistenciasDao.getFechaRegNotaByIdEva(id_eva);
    }

    public void setUpdateFechaRegNota(Fecha_regnota fecharegnota) {
        this.administracionDao.setUpdateFechaRegNota(fecharegnota);
    }

    public List getCursosTutorByIdDocenteAndIdGestion(CursoMateria cursomateria) {
        return this.profesoresDao.getCursosTutorByIdDocenteAndIdGestion(cursomateria);
    }

    public Curso getCursoByIdCursoAndIdGestion(Curso curso) {
        return this.administracionDao.getCursoByIdCursoAndIdGestion(curso);
    }

    public List getTiposConductas() {
        return this.asistenciasDao.getTiposConductas();
    }

    public void setSaveConductaByIdConducta(Conducta conducta) {
        this.administracionDao.setSaveConductaByIdConducta(conducta);
    }

    public void setUpdateEvaluaciones(Conducta conducta) {
        this.administracionDao.setUpdateEvaluaciones(conducta);
    }

    public List setConductasByIdCursoAndIdEva(Conducta conducta) {
        return this.administracionDao.setConductasByIdCursoAndIdEva(conducta);
    }

    public void getUpdateEvaluacionesByIdEstudianteAndIdEvaluacion(Evaluacion evaluacion) {
        this.administracionDao.getUpdateEvaluacionesByIdEstudianteAndIdEvaluacion(evaluacion);
    }

    public void getUpdateFaltasByIdEstudianteAndIdEva(Falta falta) {
        this.administracionDao.getUpdateFaltasByIdEstudianteAndIdEva(falta);
    }

    public List getNotasCualitativasByIdDocenteAndIdcurso(CursoMateria cm) {
        return asistenciasDao.getNotasCualitativasByIdDocenteAndIdcurso(cm);
    }

    public Curso getCursoByIdCursoAndIdEstudiantesAndIdGestion(Curso curso) {
        return asistenciasDao.getCursoByIdCursoAndIdEstudiantesAndIdGestion(curso);
    }

    public Curso getCursoByIdCursoLibreta(Curso curso) {
        return asistenciasDao.getCursoByIdCursoLibreta(curso);
    }

    public List getEvaluacionesEstudianteCursoMateria(Materia materia) {
        return asistenciasDao.getEvaluacionesEstudianteCursoMateria(materia);
    }

    public Persona getPersonaByIdEstudiante(String id_est) {
        return asistenciasDao.getPersonaByIdEstudiante(id_est);
    }

    public void setUpdatePersona(Persona persona) {
        this.administracionDao.setUpdatePersona(persona);
    }

    public void setUpdateEstudiante(Estudiante estudiante) {
        this.administracionDao.setUpdateEstudiante(estudiante);
    }

    public void setUpdateInscripcion(Inscripcion inscripcion) {
        this.administracionDao.setUpdateInscripcion(inscripcion);
    }

    public Inscripcion getInscripcionByIdInscripcion(int id_inscripcion) {
        return asistenciasDao.getInscripcionByIdInscripcion(id_inscripcion);
    }

    public PagoPension getPensionByIdEstudiante(int id_inscripcion) {
        return asistenciasDao.getPensionByIdEstudiante(id_inscripcion);
    }

    public List getServiciosByIdEstudiante(int id_inscripcion) {
        return asistenciasDao.getServiciosByIdEstudiante(id_inscripcion);
    }

    public void setUpdatePagoPensiones(PagoPension pagopension, double diferencia) {
        this.administracionDao.setUpdatePagoPensiones(pagopension, diferencia);
    }

    public void setDeletePagoServicio(PagoServicio pagoservicio) {
        this.administracionDao.setDeletePagoServicio(pagoservicio);
    }

    public void setAsignarDocenteRegFaltasCurso(CursoMateria cursomateria) {
        this.administracionDao.setAsignarDocenteRegFaltasCurso(cursomateria);
    }

//    public List getCursosAsignadosRegistrarFaltasByIdDocenteAndIdGestion(CursoMateria cursomateria) {
//        return this.profesoresDao.getCursosAsignadosRegistrarFaltasByIdDocenteAndIdGestion(cursomateria);
//    }
    public Falta getFaltaByIdFalta(int id_falta) {
        return this.asistenciasDao.getFaltaByIdFalta(id_falta);
    }

    public void setUpdatePlanEvaluacionesByIdEvaluacion(PlanEvaluacion planevaluacion) {
        this.administracionDao.setUpdatePlanEvaluacionesByIdEvaluacion(planevaluacion);
    }

    public void setInsertOrUpdateNotas(Nota nota) {
        this.administracionDao.setInsertOrUpdateNotas(nota);
    }

    public Estudiante getDatosActualesEstudianteByIdEstudianteIdCursoIdGestion(Estudiante estudiante) {
        return this.asistenciasDao.getDatosActualesEstudianteByIdEstudianteIdCursoIdGestion(estudiante);
    }

    public void setUpdateInscripcionFotografia(Inscripcion inscripcion) {
        this.administracionDao.setUpdateInscripcionFotografia(inscripcion);
    }

    public List getFaltasByIdestudianteAndIdGestion(Falta falta) {
        return this.asistenciasDao.getFaltasByIdestudianteAndIdGestion(falta);
    }

    public Falta getFechaFaltaById(int id_fecha_falta) {
        return this.asistenciasDao.getFechaFaltaById(id_fecha_falta);
    }

    public Servicio getServicioByIdServicio(int id_servicio) {
        return this.asistenciasDao.getServicioByIdServicio(id_servicio);
    }

    public void setActualizarServicio(Servicio servicio) {
        this.administracionDao.setActualizarServicio(servicio);
    }

    public List getEstadosOfEstudiantes() {
        return this.asistenciasDao.getEstadosOfEstudiantes();
    }

    public Curso getConsultaEstudiantesAllByIdCursoAndIdGestion(Curso curso) {
        return this.administracionDao.getConsultaEstudiantesAllByIdCursoAndIdGestion(curso);
    }

    public Curso getConsultaEstudiantesActivosByIdCursoAndIdGestion(Curso curso) {
        return this.administracionDao.getConsultaEstudiantesActivosByIdCursoAndIdGestion(curso);
    }

    public void setUpdateEstadoByIdEstudianteAndIdGestion(Estudiante estudiante) {
        this.administracionDao.setUpdateEstadoByIdEstudianteAndIdGestion(estudiante);
    }

    public List getEstudiantesAllByIdGestion(Curso curso) {
        return this.administracionDao.getEstudiantesAllByIdGestion(curso);
    }

    public List getEstudiantesActivosByIdGestion(Curso curso) {
        return this.administracionDao.getEstudiantesActivosByIdGestion(curso);
    }

    public Curso getConsultasPromedioByIdCursoAndIdGestion(Curso curso) {
        return this.administracionDao.getConsultasPromedioByIdCursoAndIdGestion(curso);
    }

    public List getNotasFinalesOfEstudiantes(Inscripcion inscripcion) {
        return this.asistenciasDao.getNotasFinalesOfEstudiantes(inscripcion);
    }

    public List getEstudiantesMorososServicioByIdGestion(Curso curso) {
        return this.asistenciasDao.getEstudiantesMorososServicioByIdGestion(curso);
    }

    public Curso getEstudiantesMorososServicioByIdCursoIdGestion(Curso curso) {
        return this.asistenciasDao.getEstudiantesMorososServicioByIdCursoIdGestion(curso);
    }

    public List getPreguntasChaside() {
        return this.asistenciasDao.getPreguntasChaside();
    }

    public Inscripcion getEstudianteByIdEstudianteAndGestion(Inscripcion inscripcion) {
        return this.asistenciasDao.getEstudianteByIdEstudianteAndGestion(inscripcion);
    }

    public void setRegistrarChasideRespuesta(ChasideRespuesta chasiderespuesta) {
        this.administracionDao.setRegistrarChasideRespuesta(chasiderespuesta);
    }

    public void setActualizarChasideRespuesta(ChasideRespuesta chasiderespuesta) {
        this.administracionDao.setActualizarChasideRespuesta(chasiderespuesta);
    }

    public List getInteresesByIdEstudianteAndIdGestion(Inscripcion inscripcion) {
        return this.asistenciasDao.getInteresesByIdEstudianteAndIdGestion(inscripcion);
    }

    public List getAptitudesByIdEstudianteAndIdGestion(Inscripcion inscripcion) {
        return this.asistenciasDao.getAptitudesByIdEstudianteAndIdGestion(inscripcion);
    }

    public void setUpdateDocente(Docente docente) {
        this.administracionDao.setUpdateDocente(docente);
    }

    public void setUpdateAdministrativo(Administrativo administrativo) {
        this.administracionDao.setUpdateAdministrativo(administrativo);
    }

    public void setUpdateAllPromedioByIdGestion(int id_gestion) {
        this.administracionDao.setUpdateAllPromedioByIdGestion(id_gestion);
    }

    public List getBuscarEstudianteByIdGestion(Estudiante estudiante) {
        return this.asistenciasDao.getBuscarEstudianteByIdGestion(estudiante);
    }

    public void setDeleteFaltaByIdFaltaAndTipo(Falta falta) {
        this.administracionDao.setDeleteFaltaByIdFaltaAndTipo(falta);
    }

    public void setRegistrarDeposito(Deposito deposito) {
        this.pensionesDao.setRegistrarDeposito(deposito);
    }

    public Deposito getDepositoByIdDeposito(Deposito deposito) {
        return this.pensionesDao.getDepositoByIdDeposito(deposito);
    }

    public List getMateriasAndPlanEvaluacionesByIdCurso(Curso curso) {
        return this.asistenciasDao.getMateriasAndPlanEvaluacionesByIdCurso(curso);
    }

    public List getConductasByIdEstudianteAndIdGestion(Inscripcion inscripcion) {
        return this.asistenciasDao.getConductasByIdEstudianteAndIdGestion(inscripcion);
    }

    public void getUpdateMontoDepositoAsignadaByIdDepAsignada(DepositoAsignada depasignada) {
        this.administracionDao.getUpdateMontoDepositoAsignadaByIdDepAsignada(depasignada);
    }

    public PagoPension getPagoPensionByCodigoAndIdGestion(Deposito deposito) {
        return this.asistenciasDao.getPagoPensionByCodigoAndIdGestion(deposito);
    }

    public void setUpdateDepositoAsignadaByIdDepositoAsignada(DepositoAsignada depositoasignada) {
        this.administracionDao.setUpdateDepositoAsignadaByIdDepositoAsignada(depositoasignada);
    }

    public void setUpdateObservacionByIdDepAsignada(DepositoAsignada depositoasignada) {
        this.administracionDao.setUpdateObservacionByIdDepAsignada(depositoasignada);
    }

    public List getDepositosAsignados(int id_gestion) {
        return this.asistenciasDao.getDepositosAsignados(id_gestion);
    }

    public void setUpdateEstadoPagoPensionesByIdFamiliaAndIdGestion(PagoPension pagopension) {
        this.administracionDao.setUpdateEstadoPagoPensionesByIdFamiliaAndIdGestion(pagopension);
    }

    public List getCuotasByCuota(int cuotaMax) {
        return this.asistenciasDao.getCuotasByCuota(cuotaMax);
    }

    public List getDetallesPagoPensionesCursos(int id_gestion, List cursos) {
        return this.asistenciasDao.getDetallesPagoPensionesCursos(id_gestion, cursos);
    }

    public void setRegistrarPagoColeByIdInscripcion(PagoCole pagocole) {
        this.administracionDao.setRegistrarPagoColeByIdInscripcion(pagocole);
    }

    public List getPagoColegioByIdInscripcion(List alumnos) {
        return this.asistenciasDao.getPagoColegioByIdInscripcion(alumnos);
    }

    public List getDepositosByIdGestionYOAnio(String anio) {
        return this.asistenciasDao.getDepositosByIdGestionYOAnio(anio);
    }

    public List getDepositosAsignadasByCodigoAndIdGestion(Deposito deposito) {
        return this.asistenciasDao.getDepositosAsignadasByCodigoAndIdGestion(deposito);
    }

    public void setUpdateDepositoByIdDeposito(String id_deposito) {
        this.administracionDao.setUpdateDepositoByIdDeposito(id_deposito);
    }

    @Override
    public void setUpdateDepositoAsignadaByIdDepositoasignadaACero(int iddepasignadaaprocesar) {
         this.administracionDao.setUpdateDepositoAsignadaByIdDepositoasignadaACero(iddepasignadaaprocesar);
    }

    @Override
    public void setRegistrarDocumento(Documento documento) {
        this.administracionDao.setRegistrarDocumento(documento);
    }

    @Override
    public List getListarDocumentosByArea(String area) {
        return this.administracionDao.getListarDocumentosByArea(area);
    }

    public Persona getPersonaByIdCargo(String id_persona) {
        return this.asistenciasDao.getPersonaByIdCargo(id_persona);
    }

    public List getListarTodosDocumentosByArea() {
       return this.administracionDao.getListarTodosDocumentosByArea();
    }

    public List getListarDistintosDocumentosByArea() {
       return this.administracionDao.getListarDistintosDocumentosByArea();
    }

    public void setUpdateEstudianteByIdest(Estudiante est) {
        this.administracionDao.setUpdateEstudianteByIdest(est);
    }

    @Override
    public void setGuardarKardex(Kardex kardex) {
        this.administracionDao.setGuardarKardex(kardex);
    }

    @Override
    public List getListaKardex( String id_curso_materia) {
        return this.asistenciasDao.getListaKardex(id_curso_materia);
    }

    @Override
    public int getMaxIdkardex() {
        return this.asistenciasDao.getMaxIdkardex();
    }

    @Override
    public void setRegistrarKardexDetalle(KardexDetalle kardexdetalle) {
        this.administracionDao.setRegistrarKardexDetalle(kardexdetalle);
    }

//    @Override
//    public Kardex getKardexByIdkardex(int idkardex) {
//        return this.asistenciasDao.getKardexByIdkardex(idkardex);
//    }

    @Override
    public void setGuardarEditKardex(Kardex kardex) {
        this.administracionDao.setGuardarEditKardex(kardex);
    }

    @Override
    public void setRegistrarEditKardexDetalle(KardexDetalle kardexdetalle) {
        this.administracionDao.setRegistrarEditKardexDetalle(kardexdetalle);
    }

    @Override
    public void setDeleteKardexDetalleById(int idkardexdetalle) {
        this.administracionDao.setDeleteKardexDetalleById(idkardexdetalle);
    }

    @Override
    public PeriodoCurso getPeriodoByIdcursoAndGestion(Curso curso) {
        return this.asistenciasDao.getPeriodoByIdcursoAndGestion(curso);
    }

    @Override
    public void setActualizarCualitativa(Evaluacion evaluacion) {
        this.administracionDao.setActualizarCualitativa(evaluacion);
    }
@Override
    public List getPeriodoCursoByIdgestion(int id_gestion) {
        return this.asistenciasDao.getPeriodoCursoByIdgestion(id_gestion);
    }

    @Override
    public Evaluacion getEvaluacionByIdestudianteAndIdevaluacion(Evaluacion evaluacion) {
        return this.asistenciasDao.getEvaluacionByIdestudianteAndIdevaluacion(evaluacion);
    }

    @Override
    public Estudiante getEstudianteKardexByIdEstudianteAndIdcursoAndIdgestion(String id_estudiante, String id_curso, int id_gestion) {
        return this.asistenciasDao.getEstudianteKardexByIdEstudianteAndIdcursoAndIdgestion(id_estudiante, id_curso, id_gestion);
    }

    @Override
    public Curso getCursoByIdCursoAndIdGestionSimple(Curso curso) {
        return this.administracionDao.getCursoByIdCursoAndIdGestionSimple(curso);
    }

    @Override
    public List getEvaluacionesByIdestudianteAndIdCursomateria(Evaluacion evaluacion) {
        return this.asistenciasDao.getEvaluacionesByIdestudianteAndIdCursomateria(evaluacion);
    }

    @Override
    public Informacion getInformacionColegio(int i) {
        return this.asistenciasDao.getInformacionColegio(i);
    }

    @Override
    public Estudiante getEstudianteRudeById(String id_estudiante) {
        return this.asistenciasDao.getEstudianteRudeById(id_estudiante);
    }

    @Override
    public void setActualizarPersonaEstudiante(Estudiante estudiante) {
        this.administracionDao.setActualizarPersonaEstudiante(estudiante);
    }
/*2014*/
    @Override
    public List getSigaaGestiones() {
        return this.asistenciasDao.getSigaaGestiones();
    }
    @Override
    public List getSigaaCursos() {
        return this.asistenciasDao.getSigaaCursos();
    }

    @Override
    public List getSigaaPeriodoCursoByIdCursoAndIdgestion(PeriodoCurso pc) {
        return this.asistenciasDao.getSigaaPeriodoCursoByIdCursoAndIdgestion(pc);
    }

    @Override
    public List getSigaaGetEstudiantesByCursoAndGestionT(Estudiante estudiante) {
        return this.asistenciasDao.getSigaaGetEstudiantesByCursoAndGestionT(estudiante);
    }

    @Override
    public List getSigaaGetEstudiantesByCursoAndGestionB(Estudiante estudiante) {
        return this.asistenciasDao.getSigaaGetEstudiantesByCursoAndGestionB(estudiante);
    }

    @Override
    public void setUpdateDiagnostico(Conducta conducta) {
        this.administracionDao.setUpdateDiagnostico(conducta);
    }

    @Override
    public List getDetalleDepositosBancarios(Deposito deposito) {
        return this.asistenciasDao.getDetalleDepositosBancarios(deposito);
    }

    @Override
    public List getKardexGeneral(KardexGeneralClass kardexGeneralClass) {
        return this.asistenciasDao.getKardexGeneral(kardexGeneralClass);
    }

    @Override
    public List getKardexDetalle(KardexDetalle kardexDetalle) {
       return this.asistenciasDao.getKardexDetalle(kardexDetalle);
    }

    @Override
    public void setActualizarKardex(KardexDetalle kardexDetalle) {
        this.administracionDao.setActualizarKardex(kardexDetalle);
    }


      
}
