package spring.sysmaqv.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import spring.sysmaqv.domain.*;

/*
 * @author MARCO ANTONIO QUENTA VELASCO
 * @since 18.6.2010
 */
public interface AsistenciasDao {

    List getTiposSexos() throws DataAccessException;

    List getCargosAcademicos() throws DataAccessException;

    List getTiposTitulos() throws DataAccessException;

    List getTiposCargos() throws DataAccessException;

    List getTiposCategorias() throws DataAccessException;

    Persona getPersonaById(String id_persona) throws DataAccessException;

    List getBuscarPersona(String search) throws DataAccessException;

    Docente getDocenteById(String id_docente) throws DataAccessException;

    Administrativo getAdministrativoById(String id_administrativo) throws DataAccessException;

    List getBuscarFamilia(String search);

    List getListaPersonas();

    List getListaFamilias(String search);
    List getListaFamiliasEst(String search);

    List getTipoDias();

    List getTipoMeses();

    List getTipoAnios();

    List getListaCursos(int id_gestion);

    List getListaMaterias();

    List getListaDocentes();

    List getListaCursoMateriasByIdGestion(int id_gestion);

    public List getBuscarCurso(String search);

    public List getNroHijoEstudiante(String id_familia);

    public Gestion getGestionActivo();

    public List getAlumnosById_familiaAndGestion(Inscripcion inscripcion);

    public Estudiante getEstudianteByIdEstudiante(String id_estudiante);

    public List getBuscarMateria(String search);

    public List getBuscarGestion(int id_gestion);

    List getListaGestiones();

    List getAdministradorById_cargo(String id_cargo);

    List getListaGondolas();

    public List getServicioByID(int i);

    public Curso getCursoById(CursoMateria cursomateria);

    public Administrativo getAdministrativoByIdPersona(String id_persona);

    public Docente getDocenteByIdPersona(String id_persona);

    public List getListaCuotas();

    public List getListaCuotasInicialesAndTotalesByGestion(int id_gestion);

    public List getListaDescuentos();

    public List getPagoPensionesByIdFamiliaAndIdGestion(PagoPension pagopension);

    public List getPagoServiciosByIdFamiliaAndIdGestion(PagoServicio pagoservicio);

    public Materia getMateriaById(String id_materia);

    public List getListaPlanEvaluacionesByIdCursoMateria(String id_curso_materia);

    public Gondola getGondolaByIdGondola(String id_gondola);

    public List getTiposPensionesByIdGestion(int id_gestion);

    public List getNotasCualitativasById_curso_materia(String id_curso_materia);

    public List getEstudiantesByIdCursoAndIdGestion(Inscripcion inscripcion);

    public List getFechaRegNotaByIdGestion(int id_gestion);

    public Fecha_regnota getFechaRegNotaByIdEva(String id_eva);

    public List getTiposConductas();

    public List getNotasCualitativasByIdDocenteAndIdcurso(CursoMateria cm);

    public Curso getCursoByIdCursoAndIdEstudiantesAndIdGestion(Curso curso);

    public Curso getCursoByIdCursoLibreta(Curso curso);

    public List getEvaluacionesEstudianteCursoMateria(Materia materia);

    public Persona getPersonaByIdEstudiante(String id_est);

    public Inscripcion getInscripcionByIdInscripcion(int id_inscripcion);

    public PagoPension getPensionByIdEstudiante(int id_inscripcion);

    public List getServiciosByIdEstudiante(int id_inscripcion);

    public Falta getFaltaByIdFalta(int id_falta);

    public Estudiante getDatosActualesEstudianteByIdEstudianteIdCursoIdGestion(Estudiante estudiante);

    public List getFaltasByIdestudianteAndIdGestion(Falta falta);

    public Falta getFechaFaltaById(int id_fecha_falta);

    public Servicio getServicioByIdServicio(int id_servicio);

    public List getEstadosOfEstudiantes();

    public List getNotasFinalesOfEstudiantes(Inscripcion inscripcion);

    public List getEstudiantesMorososServicioByIdGestion(Curso curso);

    public Curso getEstudiantesMorososServicioByIdCursoIdGestion(Curso curso);

    public List getPreguntasChaside();

    public Inscripcion getEstudianteByIdEstudianteAndGestion(Inscripcion inscripcion);

    public List getInteresesByIdEstudianteAndIdGestion(Inscripcion inscripcion);

    public List getAptitudesByIdEstudianteAndIdGestion(Inscripcion inscripcion);

    public List getBuscarEstudianteByIdGestion(Estudiante estudiante);

    public List getMateriasAndPlanEvaluacionesByIdCurso(Curso curso);

    public List getConductasByIdEstudianteAndIdGestion(Inscripcion inscripcion);

    public PagoPension getPagoPensionByCodigoAndIdGestion(Deposito deposito);

    public List getDepositosAsignados(int id_gestion);

    public List getCuotasByCuota(int cuotaMax);

    public List getDetallesPagoPensionesCursos(int id_gestion, List cursos);

    public List getPagoColegioByIdInscripcion(List alumnos);

    public List getDepositosByIdGestionYOAnio(String anio);

    public List getDepositosAsignadasByCodigoAndIdGestion(Deposito deposito);

    public Persona getPersonaByIdCargo(String id_persona);

    public List getListaKardex(String id_curso_materia);

    public int getMaxIdkardex();

//    public Kardex getKardexByIdkardex(int idkardex);

    public PeriodoCurso getPeriodoByIdcursoAndGestion(Curso curso);

    public List getPeriodoCursoByIdgestion(int id_gestion);

    public Evaluacion getEvaluacionByIdestudianteAndIdevaluacion(Evaluacion evaluacion);

    public Estudiante getEstudianteKardexByIdEstudianteAndIdcursoAndIdgestion(String id_estudiante, String id_curso, int id_gestion);

    public List getEvaluacionesByIdestudianteAndIdCursomateria(Evaluacion evaluacion);

    public Informacion getInformacionColegio(int i);

    public Estudiante getEstudianteRudeById(String id_estudiante);
/*2014*/
    
    public List getSigaaGestiones();
    public List getSigaaCursos();

    public List getSigaaPeriodoCursoByIdCursoAndIdgestion(PeriodoCurso pc);

    public List getSigaaGetEstudiantesByCursoAndGestionT(Estudiante estudiante);

    public List getSigaaGetEstudiantesByCursoAndGestionB(Estudiante estudiante);
/*2015*/
    public List getDetalleDepositosBancarios(Deposito deposito);

    public List getKardexGeneral(KardexGeneralClass kardexGeneralClass);

    public List getKardexDetalle(KardexDetalle kardexDetalle);
}
