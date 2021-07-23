package spring.sysmaqv.dao.ibatis;

import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import spring.sysmaqv.dao.AsistenciasDao;
import spring.sysmaqv.domain.Administrativo;
import spring.sysmaqv.domain.Chaside;
import spring.sysmaqv.domain.Curso;
import spring.sysmaqv.domain.CursoMateria;
import spring.sysmaqv.domain.Deposito;
import spring.sysmaqv.domain.DepositoAsignada;
import spring.sysmaqv.domain.Docente;
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
import spring.sysmaqv.domain.PagoCole;
import spring.sysmaqv.domain.PagoPension;
import spring.sysmaqv.domain.PagoServicio;
import spring.sysmaqv.domain.PeriodoCurso;
import spring.sysmaqv.domain.Persona;
import spring.sysmaqv.domain.PlanEvaluacion;
import spring.sysmaqv.domain.Servicio;

/*
 * @author MARCO ANTONIO QUENTA VELASCO
 * @since 18.6.2010
 */
public class SqlMapAsistenciasDao extends SqlMapClientDaoSupport implements AsistenciasDao {

    public List getTiposSexos() throws DataAccessException {
        return getSqlMapClientTemplate().queryForList("getTiposSexos");
    }

    public List getCargosAcademicos() throws DataAccessException {
        return getSqlMapClientTemplate().queryForList("getCargosAcademicos");
    }

    public List getTiposTitulos() throws DataAccessException {
        return getSqlMapClientTemplate().queryForList("getTiposTitulos");
    }

    public List getTiposCargos() throws DataAccessException {
        return getSqlMapClientTemplate().queryForList("getTiposCargos");
    }

    public List getTiposCategorias() throws DataAccessException {
        return getSqlMapClientTemplate().queryForList("getTiposCategorias");
    }

    public List getBuscarPersona(String search) throws DataAccessException {
//        List evas=getSqlMapClientTemplate().queryForList("getListaEvas");
//        System.out.println("_________________________________"+evas.size());
//        for (int i = 0; i < evas.size(); i++) {
//            Evaluacion e=(Evaluacion)evas.get(i);
//            getSqlMapClientTemplate().update("updateEvas", e);
//        }
        return getSqlMapClientTemplate().queryForList("getBuscarPersona", search);
    }

    public Persona getPersonaById(String id_persona) throws DataAccessException {
        return (Persona) getSqlMapClientTemplate().queryForObject("getPersonaById", id_persona);
    }

    public Docente getDocenteById(String id_docente) throws DataAccessException {
        return (Docente) getSqlMapClientTemplate().queryForObject("getDocenteById", id_docente);
    }

    public Administrativo getAdministrativoById(String id_administrativo) throws DataAccessException {
        return (Administrativo) getSqlMapClientTemplate().queryForObject("getAdministrativoById", id_administrativo);
    }

    public List getBuscarFamilia(String search) {
        return getSqlMapClientTemplate().queryForList("getBuscarFamilia", search);
    }

    public List getListaPersonas() {
        return getSqlMapClientTemplate().queryForList("getListaPersonas");
    }

    public List getListaFamilias(String search) {
        List newFamilias = new ArrayList();
        Gestion gestion = (Gestion) getSqlMapClientTemplate().queryForObject("getGestionActivo");
        List familias = getSqlMapClientTemplate().queryForList("getListaFamilias", search);
        for (int i = 0; i < familias.size(); i++) {
            Familia familia = (Familia) familias.get(i);
            Familia fam = (Familia) getSqlMapClientTemplate().queryForObject("getBuscarFamilia", familia.getId_familia());
            Inscripcion inscripcion = new Inscripcion();
            inscripcion.setId_familia(familia.getId_familia());
            inscripcion.setId_gestion(gestion.getId_gestion());
            fam.setEstado("activo");
            fam.setEstudiantes(getSqlMapClientTemplate().queryForList("getEstudiantesByIdFamiliaAndIdGestion", inscripcion));
            if (fam.getEstudiantes().size() == 0) {
                fam.setEstado("inactivo");
            }
            newFamilias.add(fam);
        }
        return newFamilias;
    }

    public List getListaFamiliasEst(String search) {
        List newFamilias = new ArrayList();
        Gestion gestion = (Gestion) getSqlMapClientTemplate().queryForObject("getGestionActivo");
        List familias = getSqlMapClientTemplate().queryForList("getListaFamiliasEst", search);
        for (int i = 0; i < familias.size(); i++) {
            Familia familia = (Familia) familias.get(i);
            Familia fam = (Familia) getSqlMapClientTemplate().queryForObject("getBuscarFamilia", familia.getId_familia());
            Inscripcion inscripcion = new Inscripcion();
            inscripcion.setId_familia(familia.getId_familia());
            inscripcion.setId_gestion(gestion.getId_gestion());
            fam.setEstado("activo");
            fam.setEstudiantes(getSqlMapClientTemplate().queryForList("getEstudiantesByIdFamiliaAndIdGestion", inscripcion));
            if (fam.getEstudiantes().size() == 0) {
                fam.setEstado("inactivo");
            }
            newFamilias.add(fam);
        }
        return newFamilias;
    }

    public List getTipoDias() {
        return getSqlMapClientTemplate().queryForList("getTipoDias");
    }

    public List getTipoMeses() {
        return getSqlMapClientTemplate().queryForList("getTipoMeses");
    }

    public List getListaCursos(int id_gestion) {
        List newCursos = new ArrayList();
        List cursos = getSqlMapClientTemplate().queryForList("getListaCursos");
        for (int i = 0; i < cursos.size(); i++) {
            Curso curso = (Curso) cursos.get(i);
            Inscripcion inscripcion = new Inscripcion();
            inscripcion.setId_gestion(id_gestion);
            inscripcion.setId_curso(curso.getId_curso());
            List estudiantes = getSqlMapClientTemplate().queryForList("getEstudiantesByIdCursoAndIdGestion", inscripcion);
//            curso.setEstudiantes(estudiantes);
            curso.setNumEstudiantes(estudiantes.size());
            newCursos.add(curso);
        }
        return newCursos;
    }

    public List getListaMaterias() {
        return getSqlMapClientTemplate().queryForList("getListaMaterias");
    }

    public List getListaDocentes() {
        return getSqlMapClientTemplate().queryForList("getListaDocentes");
    }

    public List getListaCursoMateriasByIdGestion(int id_gestion) {
        return getSqlMapClientTemplate().queryForList("getListaCursoMateriasByIdGestion", id_gestion);
    }

    public List getTipoAnios() {
        return getSqlMapClientTemplate().queryForList("getTipoAnios");
    }

    public List getBuscarCurso(String search) {
        return getSqlMapClientTemplate().queryForList("getBuscarCurso", search);
    }

    public List getNroHijoEstudiante(String id_familia) {
        return getSqlMapClientTemplate().queryForList("getNroHijoEstudiante", id_familia);
    }

    public Gestion getGestionActivo() {
        Gestion gestion = new Gestion();
        gestion = (Gestion) getSqlMapClientTemplate().queryForObject("getGestionActivo");
        gestion.setGestiones(getSqlMapClientTemplate().queryForList("getListaGestiones"));
        return gestion;
    }

    public List getAlumnosById_familiaAndGestion(Inscripcion inscripcion) {
        return getSqlMapClientTemplate().queryForList("getAlumnosById_familiaAndGestion", inscripcion);
    }

    public Estudiante getEstudianteByIdEstudiante(String id_estudiante) {
        Estudiante estudiante = (Estudiante) getSqlMapClientTemplate().queryForObject("getEstudianteByIdEstudiante", id_estudiante);
        Gestion gestion = (Gestion) getSqlMapClientTemplate().queryForObject("getGestionActivo");
        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setId_estudiante(id_estudiante);
        inscripcion.setId_gestion(gestion.getId_gestion());
        estudiante.setNroEvasDefinidos((Integer) getSqlMapClientTemplate().queryForObject("getNroEvasDefinidos", inscripcion));
        return estudiante;
    }

    public List getBuscarMateria(String search) {
        return getSqlMapClientTemplate().queryForList("getBuscarMateria", search);
    }

    public List getBuscarGestion(int id_gestion) {
        return getSqlMapClientTemplate().queryForList("getBuscarGestion", id_gestion);
    }

    public List getListaGestiones() {
        return getSqlMapClientTemplate().queryForList("getListaGestiones");
    }

    public List getAdministradorById_cargo(String id_cargo) {
        return getSqlMapClientTemplate().queryForList("getAdministradorById_cargo", id_cargo);
    }

    public List getListaGondolas() {
        return getSqlMapClientTemplate().queryForList("getListaGondolas");
    }

    public List getServicioByID(int i) {
        return getSqlMapClientTemplate().queryForList("getServicioByID", i);
    }

    public Curso getCursoById(CursoMateria cursomateria) {//toma en cuenta solo una materia
        Curso curso = (Curso) getSqlMapClientTemplate().queryForObject("getCursoById", cursomateria.getId_curso());
        curso.setCursomateria((CursoMateria) getSqlMapClientTemplate().queryForObject("getCursoMateriaByIdCursoMateria", cursomateria));
        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setId_gestion(cursomateria.getId_gestion());
        inscripcion.setId_curso(cursomateria.getId_curso());
        List estudiantes = getSqlMapClientTemplate().queryForList("getEstudiantesByIdCursoAndIdGestion", inscripcion);
        curso.setMateria((Materia) getSqlMapClientTemplate().queryForObject("getMateriaById", cursomateria.getId_materia()));
        curso.setEstudiantes(estudiantes);
        List newEstudiantes = new ArrayList();

        List planevaluaciones = getSqlMapClientTemplate().queryForList("getListaPlanEvaluacionesByIdCursoMateria", cursomateria.getId_curso_materia());
        Evaluacion eva = new Evaluacion();
        // eva.setId_estudiante(est.getId_estudiante());
        eva.setDps(0);
        eva.setId_gestion(cursomateria.getId_gestion());

        for (int i = 0; i < estudiantes.size(); i++) {
            Estudiante est = (Estudiante) estudiantes.get(i);

            // Evaluacion eva=new Evaluacion();
            eva.setId_estudiante(est.getId_estudiante());
            //    eva.setDps(0);
            // eva.setId_gestion(cursomateria.getId_gestion());
            for (int h = 0; h < planevaluaciones.size(); h++) {
                PlanEvaluacion planevaluacion = (PlanEvaluacion) planevaluaciones.get(h);
                eva.setId_evaluacion(planevaluacion.getId_evaluacion());
                List evas = getSqlMapClientTemplate().queryForList("getEvaluacionByIdestudianteAndIdevaluacion", eva);
                if (evas.isEmpty()) {
                    getSqlMapClientTemplate().insert("setRegistrarEvaluacionesByCursoMateria", eva);
                }
                /*else {
                 System.out.println("error por insercion" + est.getId_estudiante());
                 }*/

            }
            //List planevaluaciones = getSqlMapClientTemplate().queryForList("getListaPlanEvaluacionesByIdCursoMateria", cursomateria.getId_curso_materia());
            //getSqlMapClientTemplate().update("setUpdateEstadoFaltaByIdEvaAndIdCursoAndIdGestion", falta);

            est.getCursomateria().setId_curso_materia(cursomateria.getId_curso_materia());
            est.setId_gestion(cursomateria.getId_gestion());
            est.setEvaluaciones(getSqlMapClientTemplate().queryForList("getEvaluacionesEstudianteCursoMateria", est));
            est.setConductas(getSqlMapClientTemplate().queryForList("getConductasByIdEstudianteAndIdGestion", est));
            newEstudiantes.add(est);
        }
        curso.setEstudiantes(newEstudiantes);
        curso.setEvaluaciones(getSqlMapClientTemplate().queryForList("getListaPlanEvaluacionesByIdCursoMateria", cursomateria.getId_curso_materia()));
        curso.setDocenteOBJ((Docente) getSqlMapClientTemplate().queryForObject("getDocenteById", curso.getCursomateria().getId_docente()));
        return curso;
    }

    public Administrativo getAdministrativoByIdPersona(String id_persona) {
        return (Administrativo) getSqlMapClientTemplate().queryForObject("getAdministrativoByIdPersona", id_persona);
    }

    public Docente getDocenteByIdPersona(String id_persona) {
        return (Docente) getSqlMapClientTemplate().queryForObject("getDocenteByIdPersona", id_persona);
    }

    public List getListaCuotas() {
        return getSqlMapClientTemplate().queryForList("getListaCuotas");
    }

    public List getListaCuotasInicialesAndTotalesByGestion(int id_gestion) {
        return getSqlMapClientTemplate().queryForList("getListaCuotasInicialesAndTotalesByGestion", id_gestion);
    }

    public List getListaDescuentos() {
        return getSqlMapClientTemplate().queryForList("getListaDescuentos");
    }

    public List getPagoPensionesByIdFamiliaAndIdGestion(PagoPension pagopension) {
        List pagoPensiones = getSqlMapClientTemplate().queryForList("getPagoPensionesByIdFamiliaAndIdGestion", pagopension);
        List newPagoPensiones = new ArrayList();
        for (int i = 0; i < pagoPensiones.size(); i++) {
            PagoPension pago_pension = (PagoPension) pagoPensiones.get(i);
            List depositos = getSqlMapClientTemplate().queryForList("getDepositosAsignadasByIdPagoPension", pago_pension.getId_pago_pension());
            pago_pension.setDepositosAsignadas(depositos);
            double monto_pagar = 0;
            double monto_dep = 0;
            double interes = 0;
            for (int j = 0; j < depositos.size(); j++) {
                DepositoAsignada deposito = (DepositoAsignada) depositos.get(j);
                monto_pagar = monto_pagar + deposito.getMonto();
                monto_dep = monto_dep + deposito.getMonto_dep();
                interes = interes + deposito.getInteres();
            }
            pago_pension.setMonto_pagar(Math.round(monto_pagar * Math.pow(10, 0)) / Math.pow(10, 0));
            pago_pension.setMonto_dep(Math.round(monto_dep * Math.pow(10, 1)) / Math.pow(10, 1));
            pago_pension.setInteres(Math.round(interes * Math.pow(10, 1)) / Math.pow(10, 1));
            newPagoPensiones.add(pago_pension);
        }
        return newPagoPensiones;
    }

    public List getPagoServiciosByIdFamiliaAndIdGestion(PagoServicio pagoservicio) {
        return getSqlMapClientTemplate().queryForList("getPagoServiciosByIdFamiliaAndIdGestion", pagoservicio);
    }

    public Materia getMateriaById(String id_materia) {
        return (Materia) getSqlMapClientTemplate().queryForObject("getMateriaById", id_materia);
    }

    public List getListaPlanEvaluacionesByIdCursoMateria(String id_curso_materia) {
        return getSqlMapClientTemplate().queryForList("getListaPlanEvaluacionesByIdCursoMateria", id_curso_materia);
    }

    public Gondola getGondolaByIdGondola(String id_gondola) {
        return (Gondola) getSqlMapClientTemplate().queryForObject("getGondolaByIdGondola", id_gondola);
    }

    public List getTiposPensionesByIdGestion(int id_gestion) {
        return getSqlMapClientTemplate().queryForList("getTiposPensionesByIdGestion", id_gestion);
    }

    public List getNotasCualitativasById_curso_materia(String id_curso_materia) {
        return getSqlMapClientTemplate().queryForList("getNotasCualitativasById_curso_materia", id_curso_materia);
    }

    public List getEstudiantesByIdCursoAndIdGestion(Inscripcion inscripcion) {
        List estudiantes = getSqlMapClientTemplate().queryForList("getEstudiantesByIdCursoAndIdGestion", inscripcion);
        List newEstudiantes = new ArrayList();
        for (int i = 0; i < estudiantes.size(); i++) {
            Estudiante estudiante = (Estudiante) estudiantes.get(i);
            List familias = getSqlMapClientTemplate().queryForList("getBuscarFamilia", estudiante.getId_familia());
            estudiante.setFamilia((Familia) familias.get(0));
            Curso curso = (Curso) getSqlMapClientTemplate().queryForObject("getCursoByIdCurso", inscripcion.getId_curso());
            estudiante.setCurso(curso.getCurso());
            estudiante.setCiclo(curso.getCiclo());
            newEstudiantes.add(estudiante);
        }
        return newEstudiantes;
    }

    public List getFechaRegNotaByIdGestion(int id_gestion) {
        List fechasnotas = getSqlMapClientTemplate().queryForList("getFechaRegNotaByIdGestion", id_gestion);
        List newFechasnotas = new ArrayList();
        for (int i = 0; i < fechasnotas.size(); i++) {
            Fecha_regnota fechasnota = (Fecha_regnota) fechasnotas.get(i);
            fechasnota.setPeriodocursos(getSqlMapClientTemplate().queryForList("getPeriodoCursoByIdperiodo", fechasnota.getIdperiodo()));
//            System.out.println(fechasnota.getPeriodocursos().size());
            newFechasnotas.add(fechasnota);
        }
        return newFechasnotas;
    }

    public Fecha_regnota getFechaRegNotaByIdEva(String id_eva) {
        return (Fecha_regnota) getSqlMapClientTemplate().queryForObject("getFechaRegNotaByIdEva", id_eva);
    }

    public List getTiposConductas() {
        return getSqlMapClientTemplate().queryForList("getTiposConductas");
    }

    public List getNotasCualitativasByIdDocenteAndIdcurso(CursoMateria cm) {
        return getSqlMapClientTemplate().queryForList("getNotasCualitativasByIdDocenteAndIdcurso", cm);
    }

    public Curso getCursoByIdCursoAndIdEstudiantesAndIdGestion(Curso curso) {
        Curso cur = (Curso) getSqlMapClientTemplate().queryForObject("getCursoByIdCurso", curso.getId_curso());
        List materias = null;
        if (curso.getId_curso().equals("K") || curso.getId_curso().equals("K1") || curso.getId_curso().equals("K21") || curso.getId_curso().equals("K12")) {
            materias = getSqlMapClientTemplate().queryForList("getMateriasByIdCursoAndOrdenadoByK", curso);
        } else if (curso.getId_curso().equals("P1") || curso.getId_curso().equals("P12") || curso.getId_curso().equals("P2") || curso.getId_curso().equals("P21") || curso.getId_curso().equals("P2T") || curso.getId_curso().equals("P3") || curso.getId_curso().equals("P31") || curso.getId_curso().equals("P4") || curso.getId_curso().equals("P41") || curso.getId_curso().equals("P5") || curso.getId_curso().equals("P51") || curso.getId_curso().equals("P6") || curso.getId_curso().equals("P61") || curso.getId_curso().equals("P71") || curso.getId_curso().equals("P81") || curso.getId_curso().equals("P7") || curso.getId_curso().equals("P8")) {
            materias = getSqlMapClientTemplate().queryForList("getMateriasByIdCursoAndOrdenadoByP", curso);
        } else if (curso.getId_curso().equals("S1") || curso.getId_curso().equals("S2") || curso.getId_curso().equals("S3") || curso.getId_curso().equals("S4")) {
            materias = getSqlMapClientTemplate().queryForList("getMateriasByIdCursoAndOrdenadoByS", curso);
        }
        List newMaterias = new ArrayList();
        int ptrim1 = 0;
        int ptrim2 = 0;
        int ptrim3 = 0;
        int ptrim4 = 0;
        for (int i = 0; i < materias.size(); i++) {
            Materia materia = (Materia) materias.get(i);
            Estudiante est = new Estudiante();
            est.getCursomateria().setId_curso_materia(materia.getId_curso_materia());
            est.setId_gestion(curso.getId_gestion());
            est.setId_estudiante(curso.getId_estudiante());
            List evaluaciones = (getSqlMapClientTemplate().queryForList("getEvaluacionesEstudianteCursoMateria", est));
            List newEvaluaciones = new ArrayList();
            int pa = 0;
            int pf = 0;
            for (int j = 0; j < evaluaciones.size(); j++) {
                Evaluacion evaluacion = (Evaluacion) evaluaciones.get(j);
                if (j == 0) {
                    pa = pa + (evaluacion.getNota() + evaluacion.getDps());
//                    P[0] = P[0] + evaluacion.getNota() + evaluacion.getDps();
                    ptrim1 = ptrim1 + evaluacion.getNota() + evaluacion.getDps();
                }
                if (j == 1) {
                    pa = pa + (evaluacion.getNota() + evaluacion.getDps());
//                    P[1] = P[1] + evaluacion.getNota() + evaluacion.getDps();
                    ptrim2 = ptrim2 + evaluacion.getNota() + evaluacion.getDps();
                }
                if (j == 2) {
                    pa = pa + (evaluacion.getNota() + evaluacion.getDps());
//                    P[2] = P[2] + evaluacion.getNota() + evaluacion.getDps();
                    pa = (int) (Math.round(((double) pa / 3) * Math.pow(10, 0)) / Math.pow(10, 0));
                    ptrim3 = ptrim3 + evaluacion.getNota() + evaluacion.getDps();
                }
                if (j == 3) {
                    pa = pa + (evaluacion.getNota() + evaluacion.getDps());
//                    P[2] = P[2] + evaluacion.getNota() + evaluacion.getDps();
                    pa = (int) (Math.round(((double) pa / 4) * Math.pow(10, 0)) / Math.pow(10, 0));
                    ptrim4 = ptrim4 + evaluacion.getNota() + evaluacion.getDps();
                }
                if (j == 4) {
                    pf = (int) (Math.round(((double) (pa + evaluacion.getNota()) / 2) * Math.pow(10, 0)) / Math.pow(10, 0));
                }
                evaluacion.setPf(pf);
                evaluacion.setPa(pa);
                newEvaluaciones.add(evaluacion);
            }
            materia.setEvaluaciones(newEvaluaciones);
            newMaterias.add(materia);
        }
        cur.setPtrim1((int) (Math.round(((double) ptrim1 / materias.size()) * Math.pow(10, 0)) / Math.pow(10, 0)));
        cur.setPtrim2((int) (Math.round(((double) ptrim2 / materias.size()) * Math.pow(10, 0)) / Math.pow(10, 0)));
        cur.setPtrim3((int) (Math.round(((double) ptrim3 / materias.size()) * Math.pow(10, 0)) / Math.pow(10, 0)));
        cur.setPtrim4((int) (Math.round(((double) ptrim4 / materias.size()) * Math.pow(10, 0)) / Math.pow(10, 0)));
        cur.setMaterias(newMaterias);

        /* faltas desxde fechas_faltas*/
        List faltas = getSqlMapClientTemplate().queryForList("getFaltasByIdestudianteAndIdGestion", curso);
        List newFaltas = new ArrayList();
        for (int j = 0; j < faltas.size(); j++) {
            Falta falta = (Falta) faltas.get(j);
            falta.setId_eva(falta.getId_eva() + "B");
            List faltas_detalle = getSqlMapClientTemplate().queryForList("getFechasFaltasDetalle", falta);
//        System.out.println("moooooooooooooooooo="+faltas_detalle.size());
//        System.out.println("Id eva="+falta.getId_eva());
//        System.out.println("Id estudiante="+falta.getId_estudiante());
//        System.out.println("gestion="+falta.getId_gestion());
            int fcl = 0;
            int fsl = 0;
            int ret = 0;
            for (int i = 0; i < faltas_detalle.size(); i++) {
                Falta falta_aux = (Falta) faltas_detalle.get(i);
                if (falta_aux.getTipo().equals("fsl")) {
                    fsl++;
                } else if (falta_aux.getTipo().equals("fcl")) {
                    fcl++;
                } else if (falta_aux.getTipo().equals("ret")) {
                    ret++;
                }
            }
            falta.setFsl(fsl);
            falta.setFcl(fcl);
            falta.setRetraso(ret);
            newFaltas.add(falta);
        }

        cur.setFaltas(newFaltas);
        cur.setConductas(getSqlMapClientTemplate().queryForList("getConductasByIdEstudianteAndIdGestion", curso));
        return cur;
    }

    public Curso getCursoByIdCursoLibreta(Curso curso) {
        int id_gestion = curso.getId_gestion();
        curso = (Curso) getSqlMapClientTemplate().queryForObject("getCursoById", curso.getId_curso());
        curso.setId_gestion(id_gestion);
        List materias = null;
//        System.out.println("_______________________ = "+curso.getId_curso()+"    y   "+curso.getId_gestion());
        if (curso.getId_curso().equals("K")) {
            materias = getSqlMapClientTemplate().queryForList("getMateriasByIdCursoAndOrdenadoByK", curso);
        } else if (curso.getId_curso().equals("P1") || curso.getId_curso().equals("P2") || curso.getId_curso().equals("P12") || curso.getId_curso().equals("P21") || curso.getId_curso().equals("P2T") || curso.getId_curso().equals("P3") || curso.getId_curso().equals("P31") || curso.getId_curso().equals("P4") || curso.getId_curso().equals("P41") || curso.getId_curso().equals("P5") || curso.getId_curso().equals("P51") || curso.getId_curso().equals("P6") || curso.getId_curso().equals("P61") || curso.getId_curso().equals("P7") || curso.getId_curso().equals("P71") || curso.getId_curso().equals("P81") || curso.getId_curso().equals("P8")) {
            materias = getSqlMapClientTemplate().queryForList("getMateriasByIdCursoAndOrdenadoByP", curso);
        } else if (curso.getId_curso().equals("S1") || curso.getId_curso().equals("S2") || curso.getId_curso().equals("S3") || curso.getId_curso().equals("S4")) {
            materias = getSqlMapClientTemplate().queryForList("getMateriasByIdCursoAndOrdenadoByS", curso);
        }
        curso.setMaterias(materias);
        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setId_gestion(id_gestion);
        inscripcion.setId_curso(curso.getId_curso());
        List estudiantes = getSqlMapClientTemplate().queryForList("getEstudiantesByIdCursoAndIdGestion", inscripcion);
        List newEstudiantes = new ArrayList();
        for (int i = 0; i < estudiantes.size(); i++) {
            Estudiante estudiante = (Estudiante) estudiantes.get(i);
            estudiante.setId_gestion(id_gestion);
            estudiante.setConductas(getSqlMapClientTemplate().queryForList("getConductasByIdEstudianteAndIdGestion", estudiante));
            Falta falta = new Falta();
            falta.setId_estudiante(estudiante.getId_estudiante());
            falta.setId_gestion(id_gestion);
            estudiante.setFaltas(getSqlMapClientTemplate().queryForList("getFaltasByIdEstudianteAndIdGestion", falta));
            estudiante.setMaterias(materias);
            newEstudiantes.add(estudiante);
        }
        curso.setEstudiantes(newEstudiantes);
        return curso;
    }

    public List getEvaluacionesEstudianteCursoMateria(Materia materia) {
        return getSqlMapClientTemplate().queryForList("getEvaluacionesEstudianteCursoMateria", materia);
    }

    public Persona getPersonaByIdEstudiante(String id_est) {
        return (Persona) getSqlMapClientTemplate().queryForObject("getPersonaByIdEstudiante", id_est);
    }

    public Inscripcion getInscripcionByIdInscripcion(int id_inscripcion) {
        return (Inscripcion) getSqlMapClientTemplate().queryForObject("getInscripcionByIdInscripcion", id_inscripcion);
    }

    public PagoPension getPensionByIdEstudiante(int id_inscripcion) {
        PagoPension pagopension = (PagoPension) getSqlMapClientTemplate().queryForObject("getPensionByIdEstudiante", id_inscripcion);
        return pagopension;
    }

    public List getServiciosByIdEstudiante(int id_inscripcion) {
        return getSqlMapClientTemplate().queryForList("getServiciosByIdEstudiante", id_inscripcion);

    }

    public Falta getFaltaByIdFalta(int id_falta) {
        return (Falta) getSqlMapClientTemplate().queryForObject("getFaltaByIdFalta", id_falta);
    }

    public Estudiante getDatosActualesEstudianteByIdEstudianteIdCursoIdGestion(Estudiante estudiante) {
        return (Estudiante) getSqlMapClientTemplate().queryForObject("getDatosActualesEstudianteByIdEstudianteIdCursoIdGestion", estudiante);
    }

    public List getFaltasByIdestudianteAndIdGestion(Falta falta) {
        List faltas = getSqlMapClientTemplate().queryForList("getFaltasByIdestudianteAndIdGestion", falta);
        List newFaltas = new ArrayList();
        for (int i = 0; i < faltas.size(); i++) {
            Falta f = (Falta) faltas.get(i);
            f.setFaltas(getSqlMapClientTemplate().queryForList("getDetallesOfIdfalta", f.getId_falta()));
            newFaltas.add(f);
        }
        return newFaltas;
    }

    public Falta getFechaFaltaById(int id_fecha_falta) {
        return (Falta) getSqlMapClientTemplate().queryForObject("getFechaFaltaById", id_fecha_falta);
    }

    public Servicio getServicioByIdServicio(int id_servicio) {
        return (Servicio) getSqlMapClientTemplate().queryForObject("getServicioByIdServicio", id_servicio);
    }

    public List getEstadosOfEstudiantes() {
        return getSqlMapClientTemplate().queryForList("getEstadosOfEstudiantes");
    }

    public List getNotasFinalesOfEstudiantes(Inscripcion inscripcion) {
        List estudiantes = getSqlMapClientTemplate().queryForList("getEstudiantesByIdCursoAndIdGestion", inscripcion);
        List newEstudiantes = new ArrayList();
        for (int i = 0; i < estudiantes.size(); i++) {
            Estudiante estudiante = (Estudiante) estudiantes.get(i);
            estudiante.setId_gestion(inscripcion.getId_gestion());
            estudiante.setNotas(getSqlMapClientTemplate().queryForList("getNotasByIdEstudianteAndIdGestionAndIdCurso", estudiante));
            newEstudiantes.add(estudiante);
        }
        return estudiantes;
    }

    public List getEstudiantesMorososServicioByIdGestion(Curso curso) {
        List estudiantes = getSqlMapClientTemplate().queryForList("getEstudiantesMorososServicioByIdGestion", curso);
        List newEstudiantes = new ArrayList();
        for (int i = 0; i < estudiantes.size(); i++) {
            Estudiante estudiante = (Estudiante) estudiantes.get(i);
            estudiante.setServicios(getSqlMapClientTemplate().queryForList("setServiciosByIdInscripcion", estudiante.getId_inscripcion()));
            newEstudiantes.add(estudiante);
        }
        return estudiantes;
    }

    public Curso getEstudiantesMorososServicioByIdCursoIdGestion(Curso curso) {
        Curso cur = (Curso) getSqlMapClientTemplate().queryForObject("getCursoById", curso.getId_curso());
        cur.setId_gestion(curso.getId_gestion());
        List estudiantes = getSqlMapClientTemplate().queryForList("getEstudiantesMorososServicioByIdCursoIdGestion", curso);
        List newEstudiantes = new ArrayList();
        for (int i = 0; i < estudiantes.size(); i++) {
            Estudiante estudiante = (Estudiante) estudiantes.get(i);
            estudiante.setServicios(getSqlMapClientTemplate().queryForList("setServiciosByIdInscripcion", estudiante.getId_inscripcion()));
            newEstudiantes.add(estudiante);
        }
        cur.setEstudiantes(newEstudiantes);
        return cur;
    }

    public List getPreguntasChaside() {
        return getSqlMapClientTemplate().queryForList("getPreguntasChaside");
    }

    public Inscripcion getEstudianteByIdEstudianteAndGestion(Inscripcion inscripcion) {
        Inscripcion ins = (Inscripcion) getSqlMapClientTemplate().queryForObject("getEstudianteByIdEstudianteAndGestion", inscripcion);
        ins.setChasideRespuestas(getSqlMapClientTemplate().queryForList("getChasideRespuestasByIdEstudianteAndIdGestion", inscripcion));
        return ins;
    }

    public List getInteresesByIdEstudianteAndIdGestion(Inscripcion inscripcion) {
        int cantC_INT = (Integer) getSqlMapClientTemplate().queryForObject("getCantidadC_INT", inscripcion);
        int cantH_INT = (Integer) getSqlMapClientTemplate().queryForObject("getCantidadH_INT", inscripcion);
        int cantA_INT = (Integer) getSqlMapClientTemplate().queryForObject("getCantidadA_INT", inscripcion);
        int cantS_INT = (Integer) getSqlMapClientTemplate().queryForObject("getCantidadS_INT", inscripcion);
        int cantI_INT = (Integer) getSqlMapClientTemplate().queryForObject("getCantidadI_INT", inscripcion);
        int cantD_INT = (Integer) getSqlMapClientTemplate().queryForObject("getCantidadD_INT", inscripcion);
        int cantE_INT = (Integer) getSqlMapClientTemplate().queryForObject("getCantidadE_INT", inscripcion);
        int v[] = new int[10];
        int mayor = 0;
        if (cantC_INT > mayor) {
            mayor = cantC_INT;
        }
        if (cantH_INT > mayor) {
            mayor = cantH_INT;
        }
        if (cantA_INT > mayor) {
            mayor = cantA_INT;
        }
        if (cantS_INT > mayor) {
            mayor = cantS_INT;
        }
        if (cantI_INT > mayor) {
            mayor = cantI_INT;
        }
        if (cantD_INT > mayor) {
            mayor = cantD_INT;
        }
        if (cantE_INT > mayor) {
            mayor = cantE_INT;
        }
        List areas = getSqlMapClientTemplate().queryForList("getAreasChaside");
        List newAreas = new ArrayList();
        for (int i = 0; i < areas.size(); i++) {
            Chaside chaside = (Chaside) areas.get(i);
            chaside.setIntereses(getSqlMapClientTemplate().queryForList("getAptitudesByIdChaside", chaside.getId_chaside()));
            if (cantC_INT == mayor && chaside.getId_chaside().equals("C_INT")) {
                newAreas.add(chaside);
            }
            if (cantH_INT == mayor && chaside.getId_chaside().equals("H_INT")) {
                newAreas.add(chaside);
            }
            if (cantA_INT == mayor && chaside.getId_chaside().equals("A_INT")) {
                newAreas.add(chaside);
            }
            if (cantS_INT == mayor && chaside.getId_chaside().equals("S_INT")) {
                newAreas.add(chaside);
            }
            if (cantI_INT == mayor && chaside.getId_chaside().equals("I_INT")) {
                newAreas.add(chaside);
            }
            if (cantD_INT == mayor && chaside.getId_chaside().equals("D_INT")) {
                newAreas.add(chaside);
            }
            if (cantE_INT == mayor && chaside.getId_chaside().equals("E_INT")) {
                newAreas.add(chaside);
            }
        }
        return newAreas;
    }

    public List getAptitudesByIdEstudianteAndIdGestion(Inscripcion inscripcion) {
        int cantC_APT = (Integer) getSqlMapClientTemplate().queryForObject("getCantidadC_APT", inscripcion);
        int cantH_APT = (Integer) getSqlMapClientTemplate().queryForObject("getCantidadH_APT", inscripcion);
        int cantA_APT = (Integer) getSqlMapClientTemplate().queryForObject("getCantidadA_APT", inscripcion);
        int cantS_APT = (Integer) getSqlMapClientTemplate().queryForObject("getCantidadS_APT", inscripcion);
        int cantI_APT = (Integer) getSqlMapClientTemplate().queryForObject("getCantidadI_APT", inscripcion);
        int cantD_APT = (Integer) getSqlMapClientTemplate().queryForObject("getCantidadD_APT", inscripcion);
        int cantE_APT = (Integer) getSqlMapClientTemplate().queryForObject("getCantidadE_APT", inscripcion);
        int v[] = new int[10];
        int mayor = 0;
        if (cantC_APT > mayor) {
            mayor = cantC_APT;
        }
        if (cantH_APT > mayor) {
            mayor = cantH_APT;
        }
        if (cantA_APT > mayor) {
            mayor = cantA_APT;
        }
        if (cantS_APT > mayor) {
            mayor = cantS_APT;
        }
        if (cantI_APT > mayor) {
            mayor = cantI_APT;
        }
        if (cantD_APT > mayor) {
            mayor = cantD_APT;
        }
        if (cantE_APT > mayor) {
            mayor = cantE_APT;
        }
        List areas = getSqlMapClientTemplate().queryForList("getAreasChaside");
        List newAreas = new ArrayList();
        for (int i = 0; i < areas.size(); i++) {
            Chaside chaside = (Chaside) areas.get(i);
            chaside.setAptitudes(getSqlMapClientTemplate().queryForList("getAptitudesByIdChaside", chaside.getId_chaside()));
            if (cantC_APT == mayor && chaside.getId_chaside().equals("C_APT")) {
                newAreas.add(chaside);
            }
            if (cantH_APT == mayor && chaside.getId_chaside().equals("H_APT")) {
                newAreas.add(chaside);
            }
            if (cantA_APT == mayor && chaside.getId_chaside().equals("A_APT")) {
                newAreas.add(chaside);
            }
            if (cantS_APT == mayor && chaside.getId_chaside().equals("S_APT")) {
                newAreas.add(chaside);
            }
            if (cantI_APT == mayor && chaside.getId_chaside().equals("I_APT")) {
                newAreas.add(chaside);
            }
            if (cantD_APT == mayor && chaside.getId_chaside().equals("D_APT")) {
                newAreas.add(chaside);
            }
            if (cantE_APT == mayor && chaside.getId_chaside().equals("E_APT")) {
                newAreas.add(chaside);
            }
        }
        return newAreas;
    }

    public List getBuscarEstudianteByIdGestion(Estudiante estudiante) {
        List estudiantes = getSqlMapClientTemplate().queryForList("getBuscarEstudianteByIdGestion", estudiante);
        List newEstudiantes = new ArrayList();
        for (int i = 0; i < estudiantes.size(); i++) {
            estudiante = (Estudiante) estudiantes.get(i);
            List familias = getSqlMapClientTemplate().queryForList("getBuscarFamilia", estudiante.getId_familia());
            estudiante.setFamilia((Familia) familias.get(0));
            newEstudiantes.add(estudiante);
        }
        return newEstudiantes;
    }

    public List getMateriasAndPlanEvaluacionesByIdCurso(Curso curso) {
        List cursoMaterias = getSqlMapClientTemplate().queryForList("getMateriasByIdCursoAndIdGestion", curso);
        List newCursoMaterias = new ArrayList();
        for (int i = 0; i < cursoMaterias.size(); i++) {
            CursoMateria cursomateria = (CursoMateria) cursoMaterias.get(i);
            cursomateria.setPlanEvaluaciones(getSqlMapClientTemplate().queryForList("getListaPlanEvaluacionesByIdCursoMateria", cursomateria.getId_curso_materia()));
            newCursoMaterias.add(cursomateria);
        }
        return newCursoMaterias;
    }

    public List getConductasByIdEstudianteAndIdGestion(Inscripcion inscripcion) {
        return getSqlMapClientTemplate().queryForList("getConductasByIdEstudianteAndIdGestion", inscripcion);
    }

    public PagoPension getPagoPensionByCodigoAndIdGestion(Deposito deposito) {
        PagoPension pagopension = (PagoPension) getSqlMapClientTemplate().queryForObject("getPagoPensionByCodigoAndIdGestion", deposito);
        if (pagopension != null) {
            pagopension.setDepositosAsignadas(getSqlMapClientTemplate().queryForList("getDepositosAsignadasByIdPagoPension", pagopension.getId_pago_pension()));
        }
        return pagopension;
    }

    public List getDepositosAsignados(int id_gestion) {
        List pagopensiones = getSqlMapClientTemplate().queryForList("getDepositosAsignados", id_gestion);
        List newPagopensiones = new ArrayList();
        for (int i = 0; i < pagopensiones.size(); i++) {
            PagoPension pagopension = (PagoPension) pagopensiones.get(i);
            List depAsignadas = getSqlMapClientTemplate().queryForList("getDepositosAsignadasByIdPagoPension", pagopension.getId_pago_pension());
            pagopension.setDepositosAsignadas(depAsignadas);
            pagopension.setNroCuotas(depAsignadas.size());
            newPagopensiones.add(pagopension);
        }
        return newPagopensiones;
    }

    public List getCuotasByCuota(int cuotaMax) {
        return getSqlMapClientTemplate().queryForList("getCuotasByCuota", cuotaMax);
    }

    public List getDetallesPagoPensionesCursos(int id_gestion, List cursos) {
        List newCursos = new ArrayList();
        for (int i = 0; i < cursos.size(); i++) {
            Curso curso = (Curso) cursos.get(i);
            Inscripcion inscripcion = new Inscripcion();
            inscripcion.setId_gestion(id_gestion);
            inscripcion.setId_curso(curso.getId_curso());
            List estudiantes = getSqlMapClientTemplate().queryForList("getEstudiantesByIdCursoAndIdGestion", inscripcion);
            List newEstudiantes = new ArrayList();
            for (int j = 0; j < estudiantes.size(); j++) {
                Estudiante est = (Estudiante) estudiantes.get(j);
                PagoPension pagopension = (PagoPension) getSqlMapClientTemplate().queryForObject("getPensionByIdEstudiante", est.getId_inscripcion());
                est.setPagoPension(pagopension);
                List depAsignadas = getSqlMapClientTemplate().queryForList("getDepositosAsignadasByIdPagoPension", pagopension.getId_pago_pension());
                est.setDepAsignadas(depAsignadas);
                est.setNroCuotas(depAsignadas.size());
                newEstudiantes.add(est);
            }
            curso.setEstudiantes(newEstudiantes);
            curso.setNumEstudiantes(estudiantes.size());
            newCursos.add(curso);
        }
        return newCursos;
    }

    public List getPagoColegioByIdInscripcion(List alumnos) {
        List newPagoColes = new ArrayList();
        for (int j = 0; j < alumnos.size(); j++) {
            Inscripcion inscripcion = (Inscripcion) alumnos.get(j);
            inscripcion.setPagoCole((PagoCole) getSqlMapClientTemplate().queryForObject("getPagoColegioByIdInscripcion", inscripcion.getId_inscripcion()));
            newPagoColes.add(inscripcion);
//            System.out.println(inscripcion.getCodigo() + "__:_:_:____ " + inscripcion.getId_inscripcion());
//                    getSqlMapClientTemplate().queryForList(null, alumnos
        }
//        List pago_cole=
        return newPagoColes;
    }

    public List getDepositosByIdGestionYOAnio(String anio) {
        return getSqlMapClientTemplate().queryForList("getDepositosByIdGestionYOAnio", anio);
    }

    public List getDepositosAsignadasByCodigoAndIdGestion(Deposito deposito) {
        return getSqlMapClientTemplate().queryForList("getDepositosAsignadasByCodigoAndIdGestion", deposito);
    }

    public Persona getPersonaByIdCargo(String id_persona) {
        return (Persona) getSqlMapClientTemplate().queryForObject("getPersonaByIdCargo", id_persona);
    }

    @Override
    public List getListaKardex(String id_curso_materia) {

        List kardex_lista = getSqlMapClientTemplate().queryForList("getListaKardex", id_curso_materia);
        List new_kardex_lista = new ArrayList();
        for (int i = 0; i < kardex_lista.size(); i++) {
            Kardex kardex = (Kardex) kardex_lista.get(i);
            List kardexdetalles = getSqlMapClientTemplate().queryForList("getKardexDetalleByIdkardex", kardex.getIdkardex());
            kardex.setKardexdetalles(kardexdetalles);
            kardex.setCant_kardexdetalle(kardexdetalles.size());
            new_kardex_lista.add(kardex);
        }
        return new_kardex_lista;

//        return getSqlMapClientTemplate().queryForList("getListaKardex",id_curso_materia);
    }

    @Override
    public int getMaxIdkardex() {
        return (Integer) getSqlMapClientTemplate().queryForObject("getMaxIdkardex");
    }

//    @Override
//    public Kardex getKardexByIdkardex(int idkardex) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
    @Override
    public PeriodoCurso getPeriodoByIdcursoAndGestion(Curso curso) {
        return (PeriodoCurso) getSqlMapClientTemplate().queryForObject("getPeriodoByIdcursoAndGestion", curso);
    }

    @Override
    public List getPeriodoCursoByIdgestion(int id_gestion) {
        List periodos = getSqlMapClientTemplate().queryForList("getPeriodoCursoByIdgestion", id_gestion);
        List new_periodos = new ArrayList();
        for (int i = 0; i < periodos.size(); i++) {
            PeriodoCurso periodocurso = (PeriodoCurso) periodos.get(i);
            periodocurso.setFecnotas(getSqlMapClientTemplate().queryForList("getFechaRegNotaByIdperiodo", periodocurso.getIdperiodo()));
            new_periodos.add(periodocurso);
        }
        return periodos;
    }

    @Override
    public Evaluacion getEvaluacionByIdestudianteAndIdevaluacion(Evaluacion evaluacion) {
        return (Evaluacion) getSqlMapClientTemplate().queryForObject("getEvaluacionByIdestudianteAndIdevaluacion", evaluacion);
    }

    @Override
    public Estudiante getEstudianteKardexByIdEstudianteAndIdcursoAndIdgestion(String id_estudiante, String id_curso, int id_gestion) {
        Estudiante estudiante = (Estudiante) getSqlMapClientTemplate().queryForObject("getEstudianteByIdEstudiante", id_estudiante);
        Curso curso = (Curso) getSqlMapClientTemplate().queryForObject("getCursoById", id_curso);
        estudiante.setCurso(curso.getCurso() + " de " + curso.getCiclo());
        curso.setId_gestion(id_gestion);
        List cursomaterias = null;
        if (curso.getId_curso().equals("K") || curso.getId_curso().equals("K1") || curso.getId_curso().equals("K12") || curso.getId_curso().equals("K21")) {
            cursomaterias = getSqlMapClientTemplate().queryForList("getCursoMateriasByIdCursoAndIdgestionK", curso);
        } else if (curso.getId_curso().equals("P1") || curso.getId_curso().equals("P2") || curso.getId_curso().equals("P21") || curso.getId_curso().equals("P12") || curso.getId_curso().equals("P2T") || curso.getId_curso().equals("P3") || curso.getId_curso().equals("P31") || curso.getId_curso().equals("P41") || curso.getId_curso().equals("P4") || curso.getId_curso().equals("P5") || curso.getId_curso().equals("P51") || curso.getId_curso().equals("P6") || curso.getId_curso().equals("P61") || curso.getId_curso().equals("P71") || curso.getId_curso().equals("P81") || curso.getId_curso().equals("P7") || curso.getId_curso().equals("P8")) {
            cursomaterias = getSqlMapClientTemplate().queryForList("getCursoMateriasByIdCursoAndIdgestionP", curso);
        }
        if (curso.getId_curso().equals("S1") || curso.getId_curso().equals("S2") || curso.getId_curso().equals("S3") || curso.getId_curso().equals("S4")) {
            cursomaterias = getSqlMapClientTemplate().queryForList("getCursoMateriasByIdCursoAndIdgestionS", curso);
        }
        List new_cursomaterias = new ArrayList();
        for (int i = 0; i < cursomaterias.size(); i++) {
            CursoMateria cm = (CursoMateria) cursomaterias.get(i);
            cm.setId_estudiante(id_estudiante);
            cm.setKardex(getSqlMapClientTemplate().queryForList("getKardexByIdcursomateriaAndIdCursoAndFechas", cm));
            new_cursomaterias.add(cm);

        }
        estudiante.setCursomaterias(new_cursomaterias);

        return estudiante;
    }

    @Override
    public List getEvaluacionesByIdestudianteAndIdCursomateria(Evaluacion evaluacion) {
        return getSqlMapClientTemplate().queryForList("getEvaluacionesByIdestudianteAndIdCursomateria", evaluacion);
    }

    @Override
    public Informacion getInformacionColegio(int i) {
        return (Informacion) getSqlMapClientTemplate().queryForObject("getInformacionColegio", i);
    }

    @Override
    public Estudiante getEstudianteRudeById(String id_estudiante) {
        return (Estudiante) getSqlMapClientTemplate().queryForObject("getEstudianteRudeById", id_estudiante);
    }
    /*2014*/

    @Override
    public List getSigaaGestiones() {
        return getSqlMapClientTemplate().queryForList("getSigaaGestiones");
    }

    @Override
    public List getSigaaCursos() {
        return getSqlMapClientTemplate().queryForList("getSigaaCursos");
    }

    @Override
    public List getSigaaPeriodoCursoByIdCursoAndIdgestion(PeriodoCurso pc) {
        return getSqlMapClientTemplate().queryForList("getSigaaPeriodoCursoByIdCursoAndIdgestion", pc);
    }

    @Override
    public List getSigaaGetEstudiantesByCursoAndGestionT(Estudiante estudiante) {
        return getSqlMapClientTemplate().queryForList("getSigaaGetEstudiantesByCursoAndGestionT", estudiante);
    }

    @Override
    public List getSigaaGetEstudiantesByCursoAndGestionB(Estudiante estudiante) {
        return getSqlMapClientTemplate().queryForList("getSigaaGetEstudiantesByCursoAndGestionB", estudiante);
    }

    @Override
    public List getDetalleDepositosBancarios(Deposito deposito) {
        return getSqlMapClientTemplate().queryForList("getDetalleDepositosBancarios", deposito);
    }

    public void gege() {
    }

    @Override
    public List getKardexGeneral(KardexGeneralClass kardexGeneralClass) {
        if (kardexGeneralClass.getId_curso().equals("0")) {
            return getSqlMapClientTemplate().queryForList("KardexGeneralClassGestion", kardexGeneralClass);
        } else {
            return getSqlMapClientTemplate().queryForList("KardexGeneralClassGestionCurso", kardexGeneralClass);
        }
    }

    @Override
    public List getKardexDetalle(KardexDetalle kardexDetalle) {
        return getSqlMapClientTemplate().queryForList("getKardexDetalle", kardexDetalle);
    }
}
