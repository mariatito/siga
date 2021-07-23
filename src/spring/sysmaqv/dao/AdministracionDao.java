package spring.sysmaqv.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import spring.sysmaqv.domain.Administrativo;
import spring.sysmaqv.domain.ChasideRespuesta;
import spring.sysmaqv.domain.Conducta;
import spring.sysmaqv.domain.Cualitativa;
import spring.sysmaqv.domain.Curso;
import spring.sysmaqv.domain.CursoMateria;
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
import spring.sysmaqv.domain.Inscripcion;
import spring.sysmaqv.domain.Kardex;
import spring.sysmaqv.domain.KardexDetalle;
import spring.sysmaqv.domain.Materia;
import spring.sysmaqv.domain.Nota;
import spring.sysmaqv.domain.PagoCole;
import spring.sysmaqv.domain.PagoPension;
import spring.sysmaqv.domain.PagoServicio;
import spring.sysmaqv.domain.Persona;
import spring.sysmaqv.domain.PlanEvaluacion;
import spring.sysmaqv.domain.Servicio;
import spring.sysmaqv.domain.Tipo_pension;

/*
 * @author MARCO ANTONIO QUENTA VELASCO
 * @since 18.6.2010
 */
public interface AdministracionDao {

    Administrativo getAdministrativoByID(String id_administrativo) throws DataAccessException;

    void setRegistrarPersona(Persona persona) throws DataAccessException;

    void setRegistrarAdministrativo(Administrativo administrativo) throws DataAccessException;

    void setActualizarPersona(Persona persona) throws DataAccessException;

    void setDeletePersona(Persona persona);

    void setRegistrarFamilia(Familia familia);

    public void setRegistrarCurso(Curso curso);

    public void setRegistrarCursoMaterias(CursoMateria curso_materia);

    public void setDeleteCursoMateriaById(String param);

    void setDeleteCursoMateriasByIdCursoAndIdGestion(CursoMateria cursomateria);

    void setAsignarDocenteTutorCurso(CursoMateria cursomaterias);
    void setAsignarDocenteCoordinadorCurso(CursoMateria cursomaterias);

    public void setRegistrarGestionAcademica(Gestion gestion);

    public void setRegistrarMaterias(Materia materias);

    public void setAsignarDocenteMateria(CursoMateria curmat);

    public void setActualizarCurso(Curso cur);

    void setRegistrarEstudiante(Estudiante estudiante) throws DataAccessException;

    Estudiante getEstudianteByIdPersonaAndIdCarrera(Estudiante estudiante) throws DataAccessException;

    public void setEliminarCurso(String param);

    public void setRegistrarGondola(Gondola gondola);

    public void setActualizarMateria(Materia materias);

    public void setActualizarGestionAcademica(Gestion gestion);

    public void setRegistrarServicio(Servicio servicio);

    public void setRegistrarPensiones(Tipo_pension tipo_pension);

    public void setRegistrarPagoPensiones(PagoPension pagopension);

    public void setRegistrarPagoServicios(PagoServicio pagoservicio);

    public void setUpdatePagoServicio(PagoServicio pagoservicio);

    public void setRegistrarPlanEvaluaciones(PlanEvaluacion planevaluacion);

    public void setUpdatePlanEvaluaciones(PlanEvaluacion planevaluacion);

    public void setRegistrarEvaluacionesByCursoMateria(Evaluacion eva);

    public void setActualizarGondola(Gondola gondola);

    public void setUpdateEvaluacionByIdEstudianteAndIdEvaluacion(Evaluacion evaluacion);

    public void setUpdateEvaluacionByIdEstudianteAndIdEvaluacionKinder(Evaluacion evaluacion);

    public void setUpdateEvaluacionByIdEvaluacion(PlanEvaluacion p_eva);

    public void setGuardarCualitativa(Cualitativa cualit);

    public void setDeleteCualitativaById(String id_cualitativa);

    public void setUpdateCualitativaById(Cualitativa cualitativa);

    public void setUpdateFamilia(Familia familia);

    public void setUpdateFechaRegNota(Fecha_regnota fecharegnota);

    public Curso getCursoByIdCursoAndIdGestion(Curso curso);

    public void setSaveConductaByIdConducta(Conducta conducta);

    public void setUpdateEvaluaciones(Conducta conducta);

    public List setConductasByIdCursoAndIdEva(Conducta conducta);

    public void getUpdateEvaluacionesByIdEstudianteAndIdEvaluacion(Evaluacion evaluacion);

    public void getUpdateFaltasByIdEstudianteAndIdEva(Falta falta);

    public void setUpdatePersona(Persona persona);

    public void setUpdateEstudiante(Estudiante estudiante);

    public void setUpdateInscripcion(Inscripcion inscripcion);

    public void setUpdatePagoPensiones(PagoPension pagopension, double diferencia);

    public void setDeletePagoServicio(PagoServicio pagoservicio);

    public void setAsignarDocenteRegFaltasCurso(CursoMateria cursomateria);

    public void setUpdatePlanEvaluacionesByIdEvaluacion(PlanEvaluacion planevaluacion);

    public void setInsertOrUpdateNotas(Nota nota);

    public void setUpdateInscripcionFotografia(Inscripcion inscripcion);

    public void setActualizarServicio(Servicio servicio);

    public Curso getConsultaEstudiantesAllByIdCursoAndIdGestion(Curso curso);

    public Curso getConsultaEstudiantesActivosByIdCursoAndIdGestion(Curso curso);

    public void setUpdateEstadoByIdEstudianteAndIdGestion(Estudiante estudiante);

    public List getEstudiantesAllByIdGestion(Curso curso);

    public List getEstudiantesActivosByIdGestion(Curso curso);

    public Curso getConsultasPromedioByIdCursoAndIdGestion(Curso curso);

    public void setRegistrarChasideRespuesta(ChasideRespuesta chasiderespuesta);

    public void setActualizarChasideRespuesta(ChasideRespuesta chasiderespuesta);

    public void setUpdateDocente(Docente docente);

    public void setUpdateAdministrativo(Administrativo administrativo);

    public void setUpdateAllPromedioByIdGestion(int id_gestion);

    public void setDeleteFaltaByIdFaltaAndTipo(Falta falta);

    public void getUpdateMontoDepositoAsignadaByIdDepAsignada(DepositoAsignada depasignada);

    public void setUpdateDepositoAsignadaByIdDepositoAsignada(DepositoAsignada depositoasignada);

    public void setUpdateObservacionByIdDepAsignada(DepositoAsignada depositoasignada);

    public void setUpdateEstadoPagoPensionesByIdFamiliaAndIdGestion(PagoPension pagopension);

    public void setRegistrarPagoColeByIdInscripcion(PagoCole pagocole);

    public void setUpdateDepositoByIdDeposito(String id_deposito);

    public void setUpdateDepositoAsignadaByIdDepositoasignadaACero(int iddepasignadaaprocesar);

    public void setRegistrarDocumento(Documento documento);

    public List getListarDocumentosByArea(String area);

    public List getListarTodosDocumentosByArea();

    public List getListarDistintosDocumentosByArea();

    public void setUpdateEstudianteByIdest(Estudiante est);

    public void setGuardarKardex(Kardex kardex);

    public void setRegistrarKardexDetalle(KardexDetalle kardexdetalle);

    public void setGuardarEditKardex(Kardex kardex);

    public void setRegistrarEditKardexDetalle(KardexDetalle kardexdetalle);

    public void setDeleteKardexDetalleById(int idkardexdetalle);

    public void setActualizarCualitativa(Evaluacion evaluacion);

    public Curso getCursoByIdCursoAndIdGestionSimple(Curso curso);

    public void setActualizarPersonaEstudiante(Estudiante estudiante);

    public void setUpdateDiagnostico(Conducta conducta);

    public void setActualizarKardex(KardexDetalle kardexDetalle);

    
}
