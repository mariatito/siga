package spring.sysmaqv.dao.ibatis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import spring.sysmaqv.dao.AdministracionDao;
import spring.sysmaqv.domain.*;

/*
 * @author MARCO ANTONIO QUENTA VELASCO @since 18.6.2010
 */
public class SqlMapAdministracionDao extends SqlMapClientDaoSupport implements AdministracionDao {

    public Administrativo getAdministrativoByID(String id_adminitrativo) throws DataAccessException {
        return (Administrativo) getSqlMapClientTemplate().queryForObject("getAdministrativoByID", id_adminitrativo);
    }

    public void setRegistrarPersona(Persona persona) throws DataAccessException {
        getSqlMapClientTemplate().insert("setRegistrarPersona", persona);
    }

    public void setRegistrarAdministrativo(Administrativo administrativo) throws DataAccessException {
        getSqlMapClientTemplate().insert("setRegistrarAdministrativo", administrativo);
        Personal personal = new Personal();
        personal.setId_personal(administrativo.getId_administrativo());
        personal.setId_persona(administrativo.getId_persona());
        personal.setClave(administrativo.getDip());
        personal.setId_tpersonal(administrativo.getId_tpersonal());
        getSqlMapClientTemplate().insert("setRegistrarPersonal", personal);
    }

    public void setActualizarPersona(Persona persona) throws DataAccessException {
        getSqlMapClientTemplate().update("setActualizarPersona", persona);
    }

    public void setDeletePersona(Persona persona) throws DataAccessException {
        getSqlMapClientTemplate().delete("setDeletePersonal", persona.getId_persona());
        getSqlMapClientTemplate().delete("setDeleteDocente", persona.getId_persona());
        getSqlMapClientTemplate().delete("setDeleteAdministrativo", persona.getId_persona());
        getSqlMapClientTemplate().delete("setDeletePersona", persona);
    }

    public void setRegistrarFamilia(Familia familia) {
        getSqlMapClientTemplate().insert("setRegistrarFamilia", familia);
    }

    public void setRegistrarCurso(Curso curso) {
        getSqlMapClientTemplate().insert("setRegistrarCurso", curso);
    }

    public void setRegistrarCursoMaterias(CursoMateria curso_materia) {
        List materia = getSqlMapClientTemplate().queryForList("getSearchCursoMateriasById", curso_materia.getId_curso_materia());
        if (materia.size() <= 0) {
            getSqlMapClientTemplate().insert("setRegistrarCursoMaterias", curso_materia);
        }
    }

    public void setDeleteCursoMateriaById(String param) {
        getSqlMapClientTemplate().delete("setDeleteCursoMateriaById", param);
    }

    public void setDeleteCursoMateriasByIdCursoAndIdGestion(CursoMateria cursomateria) {
        getSqlMapClientTemplate().delete("setDeleteCursoMateriasByIdCursoAndIdGestion", cursomateria);
    }

    public void setAsignarDocenteTutorCurso(CursoMateria cursomateria) {
        getSqlMapClientTemplate().update("setAsignarDocenteTutorCurso", cursomateria);
    }

    public void setAsignarDocenteCoordinadorCurso(CursoMateria cursomateria) {
        getSqlMapClientTemplate().update("setAsignarDocenteCoordinadorCurso", cursomateria);
    }

    public void setRegistrarGestionAcademica(Gestion gestion) {
        gestion.setEstado(false);
        getSqlMapClientTemplate().update("setUpdateEstadoGestionAcademica", gestion);
        getSqlMapClientTemplate().insert("setRegistrarGestionAcademica", gestion);
    }

    public void setRegistrarMaterias(Materia materias) {
        getSqlMapClientTemplate().insert("setRegistrarMaterias", materias);
    }

    public void setAsignarDocenteMateria(CursoMateria curmat) {
        getSqlMapClientTemplate().update("setAsignarDocenteMateria", curmat);
    }

    public void setActualizarCurso(Curso cur) {
        getSqlMapClientTemplate().update("setActualizarCurso", cur);
    }

    public void setRegistrarEstudiante(Estudiante estudiante) throws DataAccessException {
        getSqlMapClientTemplate().insert("setRegistrarEstudiante", estudiante);
    }

    public Estudiante getEstudianteByIdPersonaAndIdCarrera(Estudiante estudiante) throws DataAccessException {
        return (Estudiante) getSqlMapClientTemplate().queryForObject("getEstudianteByIdPersonaAndIdCarrera", estudiante);
    }

    public void setEliminarCurso(String param) {
        getSqlMapClientTemplate().delete("setEliminarCurso", param);
    }

    public void setRegistrarGondola(Gondola gondola) {
        getSqlMapClientTemplate().insert("setRegistrarGondola", gondola);
    }

    public void setActualizarMateria(Materia materias) {
        getSqlMapClientTemplate().update("setActualizarMateria", materias);
    }

    public void setActualizarGestionAcademica(Gestion gestion) {
        gestion.setEstado(false);
        getSqlMapClientTemplate().update("setUpdateEstadoGestionAcademica", gestion);
        getSqlMapClientTemplate().update("setActualizarGestionAcademica", gestion);
    }

    public void setRegistrarServicio(Servicio servicio) {
        getSqlMapClientTemplate().insert("setRegistrarServicio", servicio);
    }

    public void setRegistrarPensiones(Tipo_pension tipo_pension) {
        getSqlMapClientTemplate().insert("setRegistrarPensiones", tipo_pension);
    }

    public void setRegistrarPagoPensiones(PagoPension pagopension) {
        getSqlMapClientTemplate().insert("setRegistrarPagoPensiones", pagopension);
    }

    public void setRegistrarPagoServicios(PagoServicio pagoservicio) {
        getSqlMapClientTemplate().insert("setRegistrarPagoServicios", pagoservicio);
    }

    public void setUpdatePagoServicio(PagoServicio pagoservicio) {
        getSqlMapClientTemplate().insert("setUpdatePagoServicio", pagoservicio);
    }

    public void setRegistrarPlanEvaluaciones(PlanEvaluacion planevaluacion) {
        /*
         * Inecesario 17 mar 2013
         */
//        Fecha_regnota fecha_regnota = (Fecha_regnota) getSqlMapClientTemplate().queryForObject("getFechaRegNotaByIdEva", planevaluacion.getId_eva());
//        planevaluacion.setFec_limite(fecha_regnota.getFec_limite());
        getSqlMapClientTemplate().insert("setRegistrarPlanEvaluaciones", planevaluacion);
    }

    public void setUpdatePlanEvaluaciones(PlanEvaluacion planevaluacion) {
        getSqlMapClientTemplate().insert("setUpdatePlanEvaluaciones", planevaluacion);
    }

    public void setRegistrarEvaluacionesByCursoMateria(Evaluacion eva) {
        getSqlMapClientTemplate().insert("setRegistrarEvaluacionesByCursoMateria", eva);
        if (!eva.getId_curso().equals("K")) {
            Promedio prom = new Promedio();
            prom.setId_estudiante(eva.getId_estudiante());
            prom.setId_gestion(eva.getId_gestion());
            prom.setId_curso(eva.getId_curso());
            Promedio promedio = (Promedio) getSqlMapClientTemplate().queryForObject("getPromediosByIdEstudianteAndGestion", prom);
            if (promedio == null) {
                getSqlMapClientTemplate().insert("setRegistrarPromediosByIdEstudianteAndIdGestion", eva);
            }
        }
    }

    public void setActualizarGondola(Gondola gondola) {
        getSqlMapClientTemplate().update("setActualizarGondola", gondola);
    }

    public void setUpdateEvaluacionByIdEstudianteAndIdEvaluacion(Evaluacion evaluacion) {
        getSqlMapClientTemplate().update("setUpdateEvaluacionByIdEstudianteAndIdEvaluacion", evaluacion);
    }

    public void setUpdateEvaluacionByIdEstudianteAndIdEvaluacionKinder(Evaluacion evaluacion) {
        getSqlMapClientTemplate().update("setUpdateEvaluacionByIdEstudianteAndIdEvaluacionKinder", evaluacion);
    }

    public void setUpdateEvaluacionByIdEvaluacion(PlanEvaluacion p_eva) {
        getSqlMapClientTemplate().update("setUpdateEvaluacionByIdEvaluacion", p_eva);
    }

    public void setGuardarCualitativa(Cualitativa cualit) {
        getSqlMapClientTemplate().insert("setGuardarCualitativa", cualit);
    }

    public void setDeleteCualitativaById(String id_cualitativa) {
        getSqlMapClientTemplate().delete("setDeleteCualitativaById", id_cualitativa);
    }

    public void setUpdateCualitativaById(Cualitativa cualitativa) {
        getSqlMapClientTemplate().update("setUpdateCualitativaById", cualitativa);
    }

    public void setUpdateFamilia(Familia familia) {
        getSqlMapClientTemplate().update("setUpdateFamilia", familia);
    }

    public void setUpdateFechaRegNota(Fecha_regnota fecharegnota) {
        getSqlMapClientTemplate().update("setUpdateFechaRegNota", fecharegnota);
        getSqlMapClientTemplate().update("setUpdateFechaRegNotaAll", fecharegnota);
    }

    public Curso getCursoByIdCursoAndIdGestion(Curso curso) {
        Curso cur = (Curso) getSqlMapClientTemplate().queryForObject("getCursoByIdCurso", curso.getId_curso());
        cur.setMaterias(getSqlMapClientTemplate().queryForList("getMateriasByIdCursoAndIdGestion", curso));
        Conducta cond = new Conducta();
        cond.setId_curso(curso.getId_curso());
        cond.setId_gestion(curso.getId_gestion());
        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setId_gestion(curso.getId_gestion());
        inscripcion.setId_curso(curso.getId_curso());
        List estudiantes = getSqlMapClientTemplate().queryForList("getEstudiantesByIdCursoAndIdGestion", inscripcion);
        PeriodoCurso periodocurso = (PeriodoCurso) getSqlMapClientTemplate().queryForObject("getPeriodoByIdcursoAndGestion", curso);
        String idperiodo = Integer.toString(periodocurso.getIdperiodo());
        int periodo = Integer.parseInt(idperiodo.substring(4, 5));
        Fecha_regnota frn = new Fecha_regnota();
        frn.setId_gestion(curso.getId_gestion());
        frn.setPeriodos(periodo);
        List fec_regnotas = getSqlMapClientTemplate().queryForList("getFechaRegNotaByIdGestion2", frn);
        // me parece que este codigo no es necesario
        Conducta conducta = new Conducta();
        Falta falta = new Falta();
        for (int i = 0; i < fec_regnotas.size(); i++) {
            Fecha_regnota fecharegnota = (Fecha_regnota) fec_regnotas.get(i);
            conducta.setId_eva(fecharegnota.getId_eva());
            conducta.setId_gestion(fecharegnota.getId_gestion());
            conducta.setId_curso(curso.getId_curso());
            falta.setId_eva(fecharegnota.getId_eva());
            falta.setId_gestion(fecharegnota.getId_gestion());
            falta.setId_curso(curso.getId_curso());
            if (fecharegnota.getEstado() == null) {
                conducta.setEstado(false);
                getSqlMapClientTemplate().update("setUpdateEstadoConductaByIdEvaAndIdCursoAndIdGestion", conducta);
                falta.setEstado(false);
                getSqlMapClientTemplate().update("setUpdateEstadoFaltaByIdEvaAndIdCursoAndIdGestion", falta);
            } else {
                conducta.setEstado(true);
                getSqlMapClientTemplate().update("setUpdateEstadoConductaByIdEvaAndIdCursoAndIdGestion", conducta);
                falta.setEstado(true);
                getSqlMapClientTemplate().update("setUpdateEstadoFaltaByIdEvaAndIdCursoAndIdGestion", falta);
            }
        }
        List newEstudiantes = new ArrayList();
        for (int i = 0; i < estudiantes.size(); i++) {
            Estudiante est = (Estudiante) estudiantes.get(i);
            est.setId_gestion(curso.getId_gestion());
            List conductas = getSqlMapClientTemplate().queryForList("getConductasByIdEstudianteAndIdGestion", est);
            /*borrar este pedazo de codigo para la gestion 2015, porque para 2015 va a resgitrar cuatro evaluaciones por defecto en la inscripcion*/
            /*desde aqui*/
            //System.out.println("???????"+conductas.size());
            if (conductas.size() < 4) {
                //System.out.println("???????========="+conductas.size());
                int e4 = 0;
                for (int j = 0; j < conductas.size(); j++) {
                    Conducta con = (Conducta) conductas.get(j);
                    if (con.getId_eva().substring(0, 1).equals("E4")) {
                        e4 = 1;
                    }
                }
                if (e4 == 0) {
                    Conducta co = new Conducta();
                    co.setId_estudiante(est.getId_estudiante());
                    co.setId_curso(inscripcion.getId_curso());
                    co.setId_eva("E4-" + inscripcion.getId_gestion());
                    co.setId_gestion(inscripcion.getId_gestion());
                    if (!cur.getId_curso().equals("K")) {
                        getSqlMapClientTemplate().insert("setNuevaConductaByIdEstudianteAndIdCurso", co);
                        conductas = getSqlMapClientTemplate().queryForList("getConductasByIdEstudianteAndIdGestion", est);
                    }
                }
            }
            /*hasta aqui*/
            
            est.setConductas(conductas);
            falta.setId_gestion(curso.getId_gestion());
            falta.setId_estudiante(est.getId_estudiante());
            List faltas = getSqlMapClientTemplate().queryForList("getFaltasByIdEstudianteAndIdGestion", falta);
            /*borrar este pedazo de codigo para la gestion 2015, porque para 2015 va a resgitrar cuatro evaluaciones por defecto en la inscripcion*/
            /*desde aqui*/
            //System.out.println("???????"+faltas.size());
            if (faltas.size() < 4) {
                //System.out.println("???????//////|||||"+faltas.size());
                int e4 = 0;
                for (int j = 0; j < faltas.size(); j++) {
                    Falta fal = (Falta) faltas.get(j);
                    if (fal.getId_eva().substring(0, 1).equals("E4")) {
                        e4 = 1;
                    }
                }
                if (e4 == 0) {
                    Falta f = new Falta();
                    f.setId_estudiante(est.getId_estudiante());
                    f.setId_curso(inscripcion.getId_curso());
                    f.setId_eva("E4-" + inscripcion.getId_gestion());
                    f.setFcl(0);
                    f.setFsl(0);
                    f.setRetraso(0);
                    f.setId_gestion(inscripcion.getId_gestion());
                    getSqlMapClientTemplate().insert("setRegistrarFaltaByIdEstudiante", f);
                    faltas = getSqlMapClientTemplate().queryForList("getFaltasByIdEstudianteAndIdGestion", falta);
                }
            }
            /*hasta aqui*/
            est.setFaltas(faltas);
            newEstudiantes.add(est);
           /* for (int j = 0; j < est.getFaltas().size(); j++) {
                Falta fa = (Falta) est.getFaltas().get(j);
                System.out.println(fa.getId_eva() + "???" + fa.getStatus());
            }*/

        }
        cur.setEstudiantes(newEstudiantes);
        cur.setNumEstudiantes(newEstudiantes.size());
        cur.setFecRegNotas(getSqlMapClientTemplate().queryForList("getFechaRegNotaByIdGestion2", frn));
        return cur;
    }

    public void setSaveConductaByIdConducta(Conducta conducta) {
        getSqlMapClientTemplate().update("setSaveConductaByIdConducta", conducta);
    }

    public void setUpdateEvaluaciones(Conducta conducta) {
        getSqlMapClientTemplate().update("setUpdateEvaluaciones", conducta);
    }

    public List setConductasByIdCursoAndIdEva(Conducta conducta) {
        return getSqlMapClientTemplate().queryForList("setConductasByIdCursoAndIdEva", conducta);
    }

    public void getUpdateEvaluacionesByIdEstudianteAndIdEvaluacion(Evaluacion evaluacion) {
        if (evaluacion.getId_cur().equals("P1") || evaluacion.getId_cur().equals("P2") || evaluacion.getId_cur().equals("P3") || evaluacion.getId_cur().equals("P4") || evaluacion.getId_cur().equals("P5") || evaluacion.getId_cur().equals("P6") || evaluacion.getId_cur().equals("P7") || evaluacion.getId_cur().equals("P8") || evaluacion.getId_cur().equals("S1") || evaluacion.getId_cur().equals("S2") || evaluacion.getId_cur().equals("S3") || evaluacion.getId_cur().equals("S4") || evaluacion.getId_cur().equals("K")|| evaluacion.getId_cur().equals("P21")|| evaluacion.getId_cur().equals("P12")|| evaluacion.getId_cur().equals("P31")|| evaluacion.getId_cur().equals("P2T")|| evaluacion.getId_cur().equals("P41")|| evaluacion.getId_cur().equals("P51")|| evaluacion.getId_cur().equals("P61")|| evaluacion.getId_cur().equals("P71")|| evaluacion.getId_cur().equals("P81")|| evaluacion.getId_cur().equals("K1")|| evaluacion.getId_cur().equals("K12")|| evaluacion.getId_cur().equals("K21")) {
            getSqlMapClientTemplate().update("getUpdateEvaluacionesByIdEstudianteAndIdEvaluacion", evaluacion);
        } else {
            //getSqlMapClientTemplate().update("getUpdateEvaluacionesByIdEstudianteAndIdEvaluacionPrimaria", evaluacion);
        }
    }

    public void getUpdateFaltasByIdEstudianteAndIdEva(Falta falta) {
        //System.out.println(""+falta.getId_fecha_falta());
        if (falta.getId_fecha_falta() != 0) {
            getSqlMapClientTemplate().update("getUpdateFaltasByIdEstudianteAndIdEva", falta);
            getSqlMapClientTemplate().update("setUpdateFechasFaltaById", falta);
        } else {
            getSqlMapClientTemplate().update("getUpdateFaltasByIdEstudianteAndIdEva", falta);
            getSqlMapClientTemplate().insert("setRegistrarFechasFaltaById", falta);
        }

    }

    public void setUpdatePersona(Persona persona) {
        getSqlMapClientTemplate().update("setUpdatePersona", persona);
    }

    public void setUpdateEstudiante(Estudiante estudiante) {
        getSqlMapClientTemplate().update("setUpdateEstudiante", estudiante);
    }

    public void setUpdateInscripcion(Inscripcion inscripcion) {
        getSqlMapClientTemplate().update("setUpdateInscripcion", inscripcion);
    }

    public void setUpdatePagoPensiones(PagoPension pagopension, double diferencia) {
        PagoPension pp = (PagoPension) getSqlMapClientTemplate().queryForObject("getPagoPensionByIdPagoPension", pagopension.getId_pago_pension());
        getSqlMapClientTemplate().update("setUpdatePagoPensiones", pagopension);

        if (pp.isEditable() == true) {
            List cuotas = getSqlMapClientTemplate().queryForList("getCuotasByCuota", pagopension.getCuota());
            DepositoAsignada depositoasignada = new DepositoAsignada();
            for (int i = 0; i < cuotas.size(); i++) {
                try {
                    Cuota cuota = (Cuota) cuotas.get(i);
                    depositoasignada.setId_pago_pension(pagopension.getId_pago_pension());
                    depositoasignada.setMes(cuota.getMes());
                    double monto = pagopension.getMonto_inicial();
                    if (i == 2 || i == 3 || i == 4 || i == 5 || i == 6 || i == 7 || i == 8 || i == 9) {
                        monto = Math.round(((pagopension.getPension_total() - diferencia) / pagopension.getCuota()) * Math.pow(10, 1)) / Math.pow(10, 1);
                    } else if (i == 1) {
                        monto = (Math.round(((pagopension.getPension_total() - diferencia) / pagopension.getCuota()) * Math.pow(10, 1)) / Math.pow(10, 1)) + diferencia;
                    }
                    depositoasignada.setMonto(monto);
                    SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
                    Date fecha_ini = formato.parse(cuota.getDia_mes_ini() + "-" + pagopension.getId_gestion());
                    depositoasignada.setFecha_ini(fecha_ini);
                    Date fecha_fin = formato.parse(cuota.getDia_mes_fin() + "-" + pagopension.getId_gestion());
                    depositoasignada.setFecha_fin(fecha_fin);
                    depositoasignada.setNro_cuota((i + 1));
                    depositoasignada.setCodigo(pagopension.getCodigo());
                    getSqlMapClientTemplate().insert("setRegistrarDepositoAsignada", depositoasignada);
                } catch (ParseException ex) {
                }
            }
        }
    }

    public void setDeletePagoServicio(PagoServicio pagoservicio) {
        getSqlMapClientTemplate().delete("setDeletePagoServicio", pagoservicio);
    }

    public void setAsignarDocenteRegFaltasCurso(CursoMateria cursomateria) {
        getSqlMapClientTemplate().update("setAsignarDocenteRegFaltasCurso", cursomateria);
    }

    public void setUpdatePlanEvaluacionesByIdEvaluacion(PlanEvaluacion planevaluacion) {
        getSqlMapClientTemplate().update("setUpdatePlanEvaluacionesByIdEvaluacion", planevaluacion);
    }

    public void setInsertOrUpdateNotas(Nota nota) {
        Nota n = (Nota) getSqlMapClientTemplate().queryForObject("getNotasByIdEstudianteIdCursoIdMateriaIdGestion", nota);
        if (n == null) {
            getSqlMapClientTemplate().insert("setInsertNotas", nota);
        } else {
            getSqlMapClientTemplate().update("setUpdateNotas", nota);
        }
        getSqlMapClientTemplate().update("setUpdateEstadoCursoMaterias", nota);
    }

    public void setUpdateInscripcionFotografia(Inscripcion inscripcion) {
        getSqlMapClientTemplate().update("setUpdateInscripcionFotografia", inscripcion);
    }

    public void setActualizarServicio(Servicio servicio) {
        getSqlMapClientTemplate().update("setActualizarServicio", servicio);
    }

    public Curso getConsultaEstudiantesAllByIdCursoAndIdGestion(Curso curso) {
        Curso cur = (Curso) getSqlMapClientTemplate().queryForObject("getCursoByIdCurso", curso.getId_curso());
        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setId_gestion(curso.getId_gestion());
        inscripcion.setId_curso(curso.getId_curso());
        List estudiantes = getSqlMapClientTemplate().queryForList("getEstudiantesAllByIdCursoAndIdGestion", inscripcion);
        cur.setEstudiantes(estudiantes);
        cur.setNumEstudiantes(estudiantes.size());
        List cursomaterias = null;
        if (curso.getId_curso().equals("K") || curso.getId_curso().equals("K1")|| curso.getId_curso().equals("K21")|| curso.getId_curso().equals("K12")) {
            cursomaterias = getSqlMapClientTemplate().queryForList("getCursoMateriasByIdCursoAndIdgestionK", curso);
        } else if (curso.getId_curso().equals("P12") || curso.getId_curso().equals("P1") || curso.getId_curso().equals("P2")|| curso.getId_curso().equals("P21")|| curso.getId_curso().equals("P12") || curso.getId_curso().equals("P2T")|| curso.getId_curso().equals("P31")|| curso.getId_curso().equals("P41")|| curso.getId_curso().equals("P61")|| curso.getId_curso().equals("P51")|| curso.getId_curso().equals("P3") || curso.getId_curso().equals("P4") || curso.getId_curso().equals("P5") || curso.getId_curso().equals("P6")|| curso.getId_curso().equals("P61")  || curso.getId_curso().equals("P7") || curso.getId_curso().equals("P8") || curso.getId_curso().equals("P71") || curso.getId_curso().equals("P81")) {
            cursomaterias = getSqlMapClientTemplate().queryForList("getCursoMateriasByIdCursoAndIdgestionP", curso);
        }
        if (curso.getId_curso().equals("S1") || curso.getId_curso().equals("S2") || curso.getId_curso().equals("S3") || curso.getId_curso().equals("S4")) {
            cursomaterias = getSqlMapClientTemplate().queryForList("getCursoMateriasByIdCursoAndIdgestionS", curso);
        }
        cur.setCursomaterias(cursomaterias);
        return cur;
    }

    public Curso getConsultaEstudiantesActivosByIdCursoAndIdGestion(Curso curso) {
        Curso cur = (Curso) getSqlMapClientTemplate().queryForObject("getCursoByIdCurso", curso.getId_curso());
        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setId_gestion(curso.getId_gestion());
        inscripcion.setId_curso(curso.getId_curso());
        List estudiantes = getSqlMapClientTemplate().queryForList("getEstudiantesByIdCursoAndIdGestion", inscripcion);
        cur.setEstudiantes(estudiantes);
        cur.setNumEstudiantes(estudiantes.size());
        return cur;
    }

    public void setUpdateEstadoByIdEstudianteAndIdGestion(Estudiante estudiante) {
        getSqlMapClientTemplate().update("setUpdateEstadoByIdEstudianteAndIdGestion", estudiante);
    }

    public List getEstudiantesAllByIdGestion(Curso curso) {
        return getSqlMapClientTemplate().queryForList("getEstudiantesAllByIdGestion", curso);
    }

    public List getEstudiantesActivosByIdGestion(Curso curso) {
        return getSqlMapClientTemplate().queryForList("getEstudiantesActivosByIdGestion", curso);
    }

    public void setUpdateAllPromedioByIdGestion(int id_gestion) {
        List promedios = getSqlMapClientTemplate().queryForList("getEstudiantesByIdGestionOfPromedios", id_gestion);
        for (int i = 0; i < promedios.size(); i++) {
            Promedio promedio = (Promedio) promedios.get(i);
            Evaluacion evaluacion = new Evaluacion();
            evaluacion.setId_estudiante(promedio.getId_estudiante());
            evaluacion.setId_gestion(promedio.getId_gestion());
            evaluacion.setId_evaluacion("%" + promedio.getId_curso() + "%E1%");
            if (getSqlMapClientTemplate().queryForObject("getSumNotaDpsByIdEstudianteAndIdGestion", evaluacion) != null) {
                promedio.setTrim1((Integer) getSqlMapClientTemplate().queryForObject("getSumNotaDpsByIdEstudianteAndIdGestion", evaluacion));
            }
            evaluacion.setId_evaluacion("%" + promedio.getId_curso() + "%E2%");
            if (getSqlMapClientTemplate().queryForObject("getSumNotaDpsByIdEstudianteAndIdGestion", evaluacion) != null) {
                promedio.setTrim2((Integer) getSqlMapClientTemplate().queryForObject("getSumNotaDpsByIdEstudianteAndIdGestion", evaluacion));
            }
            evaluacion.setId_evaluacion("%" + promedio.getId_curso() + "%E3%");
            if (getSqlMapClientTemplate().queryForObject("getSumNotaDpsByIdEstudianteAndIdGestion", evaluacion) != null) {
                promedio.setTrim3((Integer) getSqlMapClientTemplate().queryForObject("getSumNotaDpsByIdEstudianteAndIdGestion", evaluacion));
            }
            promedio.setTotal((Math.round(((double) (promedio.getTrim1() + promedio.getTrim2() + promedio.getTrim3()) / 3) * Math.pow(10, 2)) / Math.pow(10, 2)));
            getSqlMapClientTemplate().update("setUpdatePromedioByIdestudianteAndIdCursoAndIdGestion", promedio);
        }
    }

    public Curso getConsultasPromedioByIdCursoAndIdGestion(Curso curso) {
        Curso cur = new Curso();
//        if (!curso.getId_curso().equals("C")) {
//            cur = (Curso) getSqlMapClientTemplate().queryForObject("getCursoByIdCurso", curso.getId_curso());
//        }
        cur.setMaterias(getSqlMapClientTemplate().queryForList("getMateriasByIdCursoAndIdGestion", curso));// cursomaterias
        CursoMateria cm;
        String cmat = "'0'";
        for (int i = 0; i < cur.getMaterias().size(); i++) {
            cm = (CursoMateria) cur.getMaterias().get(i);
            cmat = cmat + ",'" + cm.getId_curso_materia() + "'";
        }
        //System.out.println(cmat);
        curso.setCursomats(cmat);
        List planevaluaciones = getSqlMapClientTemplate().queryForList("getCursoMateriasByEvasCursomatsIdgestion", curso);
        String idplanevas = "'0'";
        PlanEvaluacion pe;
        System.out.println(curso.getCursomats());
        for (int i = 0; i < planevaluaciones.size(); i++) {
            pe = (PlanEvaluacion) planevaluaciones.get(i);
            idplanevas = idplanevas + ",'" + pe.getId_evaluacion() + "'";
        }
        System.out.println(idplanevas);
//        List estudiantes = null;
//        List newEstudiantes = new ArrayList();
//        if (curso.getId_trimestre().equals("T1")) {
//            if (curso.getId_curso().equals("C")) {
//                estudiantes = getSqlMapClientTemplate().queryForList("getPromedioByIdGestionAndOrderByTrim1", curso);
//            } else {
//                estudiantes = getSqlMapClientTemplate().queryForList("getPromedioByIdCursoAndIdGestionAndOrderByTrim1", curso);
//            }
//        } else if (curso.getId_trimestre().equals("T2")) {
//            if (curso.getId_curso().equals("C")) {
//                estudiantes = getSqlMapClientTemplate().queryForList("getPromedioByIdGestionAndOrderByTrim2", curso);
//            } else {
//                estudiantes = getSqlMapClientTemplate().queryForList("getPromedioByIdCursoAndIdGestionAndOrderByTrim2", curso);
//            }
//        } else if (curso.getId_trimestre().equals("T3")) {
//            if (curso.getId_curso().equals("C")) {
//                estudiantes = getSqlMapClientTemplate().queryForList("getPromedioByIdGestionAndOrderByTrim3", curso);
//            } else {
//                estudiantes = getSqlMapClientTemplate().queryForList("getPromedioByIdCursoAndIdGestionAndOrderByTrim3", curso);
//            }
//        } else if (curso.getId_trimestre().equals("A")) {
//            if (curso.getId_curso().equals("C")) {
//                estudiantes = getSqlMapClientTemplate().queryForList("getPromedioByIdGestionAndOrderByTotal", curso);
//            } else {
//                estudiantes = getSqlMapClientTemplate().queryForList("getPromedioByIdCursoAndIdGestionAndOrderByTotal", curso);
//            }
//        }
//        for (int i = 0; i < estudiantes.size(); i++) {
//            Estudiante estudiante = (Estudiante) estudiantes.get(i);
//            estudiante.setTrim1((Math.round(((double) estudiante.getTrim1() / estudiante.getCant_materias()) * Math.pow(10, 2)) / Math.pow(10, 2)));
//            estudiante.setTrim2((Math.round(((double) estudiante.getTrim2() / estudiante.getCant_materias()) * Math.pow(10, 2)) / Math.pow(10, 2)));
//            estudiante.setTrim3((Math.round(((double) estudiante.getTrim3() / estudiante.getCant_materias()) * Math.pow(10, 2)) / Math.pow(10, 2)));
//            estudiante.setTotal((Math.round(((double) estudiante.getTotal() / estudiante.getCant_materias()) * Math.pow(10, 2)) / Math.pow(10, 2)));
//            newEstudiantes.add(estudiante);
//        }
//        cur.setEstudiantes(newEstudiantes);
        return cur;
    }

    public void setRegistrarChasideRespuesta(ChasideRespuesta chasiderespuesta) {
        getSqlMapClientTemplate().insert("setRegistrarChasideRespuesta", chasiderespuesta);
    }

    public void setActualizarChasideRespuesta(ChasideRespuesta chasiderespuesta) {
        getSqlMapClientTemplate().update("setActualizarChasideRespuesta", chasiderespuesta);
    }

    public void setUpdateDocente(Docente docente) {
        getSqlMapClientTemplate().update("setUpdateDocente", docente);
    }

    public void setUpdateAdministrativo(Administrativo administrativo) {
        getSqlMapClientTemplate().update("setUpdateAdministrativo", administrativo);
    }

    public void setDeleteFaltaByIdFaltaAndTipo(Falta falta) {
        getSqlMapClientTemplate().delete("setDeleteFechaFaltaByIdFechaFalta", falta.getId_fecha_falta());
        falta.setFcl(0);
        falta.setFsl(0);
        falta.setRetraso(0);
        if (falta.getTipo().equals("fcl")) {
            falta.setFcl(1);
        } else if (falta.getTipo().equals("fsl")) {
            falta.setFsl(1);
        } else if (falta.getTipo().equals("ret")) {
            falta.setRetraso(1);
        }
        getSqlMapClientTemplate().update("setUpdateFaltaByIdFaltaAndTipo", falta);
    }

    public void getUpdateMontoDepositoAsignadaByIdDepAsignada(DepositoAsignada depasignada) {
        getSqlMapClientTemplate().update("getUpdateMontoDepositoAsignadaByIdDepAsignada", depasignada);
    }

    public void setUpdateDepositoAsignadaByIdDepositoAsignada(DepositoAsignada depositoasignada) {
        getSqlMapClientTemplate().update("setUpdateDepositoAsignadaByIdDepositoAsignada", depositoasignada);
    }

    public void setUpdateObservacionByIdDepAsignada(DepositoAsignada depositoasignada) {
        getSqlMapClientTemplate().update("setUpdateObservacionByIdDepAsignada", depositoasignada);
    }

    public void setUpdateEstadoPagoPensionesByIdFamiliaAndIdGestion(PagoPension pagopension) {
        getSqlMapClientTemplate().update("setUpdateEstadoPagoPensionesByIdFamiliaAndIdGestion", pagopension);
        List pagoPensiones = getSqlMapClientTemplate().queryForList("getPagoPensionesByIdFamiliaAndIdGestion", pagopension);
        int cod = 0;
        for (int i = 0; i < pagoPensiones.size(); i++) {
            PagoPension pp = (PagoPension) pagoPensiones.get(i);
            if (cod != pp.getCodigo()) {
                Deposito deposito = new Deposito();
                deposito.setAnio(Integer.toString(pagopension.getId_gestion()));
                deposito.setCodigo(pp.getCodigo());
                getSqlMapClientTemplate().update("setUpdateAsignadoByIdGestionAndCodigo", deposito);
                cod = pp.getCodigo();
            }
            getSqlMapClientTemplate().delete("setDeleteDepAsignadasByIdPagoPension", pp);
        }
    }

    public void setRegistrarPagoColeByIdInscripcion(PagoCole pagocole) {
        getSqlMapClientTemplate().insert("setRegistrarPagoColeByIdInscripcion", pagocole);
    }

    public void setUpdateDepositoByIdDeposito(String id_deposito) {
        getSqlMapClientTemplate().update("setUpdateDepositoByIdDeposito", id_deposito);
    }

    @Override
    public void setUpdateDepositoAsignadaByIdDepositoasignadaACero(int iddepasignadaaprocesar) {
        getSqlMapClientTemplate().update("setUpdateDepositoAsignadaByIdDepositoasignadaACero", iddepasignadaaprocesar);
    }

    @Override
    public void setRegistrarDocumento(Documento documento) {
        getSqlMapClientTemplate().insert("setRegistrarDocumento", documento);
    }

    @Override
    public List getListarDocumentosByArea(String area) {
        return getSqlMapClientTemplate().queryForList("getListarDocumentosByArea", area);
    }

    public List getListarTodosDocumentosByArea() {
        return getSqlMapClientTemplate().queryForList("getListarTodosDocumentosByArea");
    }

    public List getListarDistintosDocumentosByArea() {
        return getSqlMapClientTemplate().queryForList("getListarDistintosDocumentosByArea");
    }

    public void setUpdateEstudianteByIdest(Estudiante est) {
        getSqlMapClientTemplate().update("setUpdateEstudianteByIdest", est);
    }

    @Override
    public void setGuardarKardex(Kardex kardex) {
        getSqlMapClientTemplate().insert("setGuardarKardex", kardex);
    }

    public List getListarKardex() {
        return getSqlMapClientTemplate().queryForList("getListarDistintosDocumentosByArea");
    }

    @Override
    public void setRegistrarKardexDetalle(KardexDetalle kardexdetalle) {
        getSqlMapClientTemplate().insert("setRegistrarKardexDetalle", kardexdetalle);
    }

    @Override
    public void setGuardarEditKardex(Kardex kardex) {
        getSqlMapClientTemplate().update("setGuardarEditKardex", kardex);
    }

    @Override
    public void setRegistrarEditKardexDetalle(KardexDetalle kardexdetalle) {
        getSqlMapClientTemplate().update("setRegistrarEditKardexDetalle", kardexdetalle);
    }

    @Override
    public void setDeleteKardexDetalleById(int idkardexdetalle) {
        getSqlMapClientTemplate().delete("setDeleteKardexDetalleById", idkardexdetalle);
    }

    @Override
    public void setActualizarCualitativa(Evaluacion evaluacion) {
        getSqlMapClientTemplate().update("setActualizarCualitativa", evaluacion);
    }

    public Curso getCursoByIdCursoAndIdGestionSimple(Curso curso) {
        Curso cur = (Curso) getSqlMapClientTemplate().queryForObject("getCursoByIdCurso", curso.getId_curso());
        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setId_gestion(curso.getId_gestion());
        inscripcion.setId_curso(curso.getId_curso());
        List estudiantes = getSqlMapClientTemplate().queryForList("getEstudiantesByIdCursoAndIdGestion", inscripcion);
        List newEstudiantes = new ArrayList();
        for (int i = 0; i < estudiantes.size(); i++) {
            Estudiante est = (Estudiante) estudiantes.get(i);
            est.setId_gestion(curso.getId_gestion());
            List new_cursomaterias = new ArrayList();
            List cmaterias = null;
            
            
            System.out.println("->>>"+curso.getId_curso());
            if (curso.getId_curso().equals("K")||curso.getId_curso().equals("K1")||curso.getId_curso().equals("K21")||curso.getId_curso().equals("K12")) {
                System.out.println("->>>entro");
                cmaterias = getSqlMapClientTemplate().queryForList("getCursoMateriasByIdCursoAndIdgestionK", curso);
                System.out.println("->>>"+cmaterias.size());
            } else if (curso.getId_curso().equals("P1") || curso.getId_curso().equals("P2") || curso.getId_curso().equals("P21") || curso.getId_curso().equals("P2T")||curso.getId_curso().equals("P12")|| curso.getId_curso().equals("P3")|| curso.getId_curso().equals("P31") || curso.getId_curso().equals("P41")|| curso.getId_curso().equals("P51")|| curso.getId_curso().equals("P4") || curso.getId_curso().equals("P5") || curso.getId_curso().equals("P6") || curso.getId_curso().equals("P61") || curso.getId_curso().equals("P7") || curso.getId_curso().equals("P8")|| curso.getId_curso().equals("P71") || curso.getId_curso().equals("P81")) {
                cmaterias = getSqlMapClientTemplate().queryForList("getCursoMateriasByIdCursoAndIdgestionP", curso);
            }
            if (curso.getId_curso().equals("S1") || curso.getId_curso().equals("S2") || curso.getId_curso().equals("S3") || curso.getId_curso().equals("S4")) {
                cmaterias = getSqlMapClientTemplate().queryForList("getCursoMateriasByIdCursoAndIdgestionS", curso);
            }
            for (int j = 0; j < cmaterias.size(); j++) {
                CursoMateria cm = (CursoMateria) cmaterias.get(j);
                cm.setId_estudiante(est.getId_estudiante());
                cm.setFecha1(curso.getFecha1());
                cm.setFecha2(curso.getFecha2());
                List f = getSqlMapClientTemplate().queryForList("getKardexByIdcursomateriaAndIdCursoAndFechas", cm);
                cm.setKardex(f);
                new_cursomaterias.add(cm);
            }
            est.setCursomaterias(new_cursomaterias);
            newEstudiantes.add(est);
        }
        cur.setEstudiantes(newEstudiantes);
        cur.setNumEstudiantes(newEstudiantes.size());

        PeriodoCurso periodocurso = (PeriodoCurso) getSqlMapClientTemplate().queryForObject("getPeriodoByIdcursoAndGestion", curso);
        List fechas_regnotas = getSqlMapClientTemplate().queryForList("getFechaRegNotaByIdperiodo", periodocurso.getIdperiodo());
        cur.setFecRegNotas(fechas_regnotas);
        return cur;
    }

    @Override
    public void setActualizarPersonaEstudiante(Estudiante estudiante) {
        Estudiante est = (Estudiante) getSqlMapClientTemplate().queryForObject("getEstudianteByIdEstudiante", estudiante.getId_estudiante());
        /*Actualizamos Nombres y sexo Persona*/
        estudiante.setId_persona(est.getId_persona());
        getSqlMapClientTemplate().update("setActualizarPersonaEstudiante", estudiante);
        /*Actualizamos la informacion del estudiante*/
        getSqlMapClientTemplate().update("setActualizarEstudiante", estudiante);
    }

    @Override
    public void setUpdateDiagnostico(Conducta conducta) {
        getSqlMapClientTemplate().update("setUpdateDiagnostico", conducta);
    }

    @Override
    public void setActualizarKardex(KardexDetalle kardexDetalle) {
        getSqlMapClientTemplate().update("setActualizarKardex", kardexDetalle);
    }
}
