package spring.sysmaqv.dao.ibatis;

import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import spring.sysmaqv.dao.ProfesoresDao;
import spring.sysmaqv.domain.CursoMateria;
import spring.sysmaqv.domain.Docente;
import spring.sysmaqv.domain.Personal;

/*
 * @author MARCO ANTONIO QUENTA VELASCO
 * @since 18.6.2010
 */
public class SqlMapProfesoresDao extends SqlMapClientDaoSupport implements ProfesoresDao {

    public void setRegistrarDocente(Docente docente) throws DataAccessException {
        getSqlMapClientTemplate().insert("setRegistrarDocente", docente);
        Personal personal = new Personal();
        personal.setId_personal(docente.getId_docente());
        personal.setId_persona(docente.getId_persona());
        personal.setClave(docente.getDip());
        personal.setId_tpersonal(docente.getId_tpersonal());
        getSqlMapClientTemplate().insert("setRegistrarPersonal",personal);
    }

    public List getCursosByIdDocenteAndIdGestion(CursoMateria cursomateria) {
        List cursomaterias = getSqlMapClientTemplate().queryForList("getCursosByIdDocenteAndIdGestion", cursomateria);
        ArrayList newCursoMaterias = new ArrayList();
        for (int i = 0; i < cursomaterias.size(); i++) {
            CursoMateria cm = (CursoMateria) cursomaterias.get(i);
            cm.setCursomaterias(getSqlMapClientTemplate().queryForList("getMateriasByIdCursoAndIdGestionAndIdDocente", cm));
            newCursoMaterias.add(cm);
        }
        return newCursoMaterias;
    }

    public List getCursosTutorByIdDocenteAndIdGestion(CursoMateria cursomateria) {
        return getSqlMapClientTemplate().queryForList("getCursosTutorByIdDocenteAndIdGestion", cursomateria);
    }

//    public List getCursosAsignadosRegistrarFaltasByIdDocenteAndIdGestion(CursoMateria cursomateria) {
//        return getSqlMapClientTemplate().queryForList("getCursosAsignadosRegistrarFaltasByIdDocenteAndIdGestion", cursomateria);
//    }
}
