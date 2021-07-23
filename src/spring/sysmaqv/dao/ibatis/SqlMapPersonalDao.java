package spring.sysmaqv.dao.ibatis;

import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import spring.sysmaqv.dao.PersonalDao;
import spring.sysmaqv.domain.Docente;
import spring.sysmaqv.domain.Estudiante;
import spring.sysmaqv.domain.Menu;
import spring.sysmaqv.domain.Modalidad;
import spring.sysmaqv.domain.Personal;
import spring.sysmaqv.domain.Tarea;

/*
 * @author MARCO ANTONIO QUENTA VELASCO
 * @since 18.6.2010
 */
public class SqlMapPersonalDao extends SqlMapClientDaoSupport implements PersonalDao {

    public void setCambiarPassword(Personal personal) throws DataAccessException {
        getSqlMapClientTemplate().update("setCambiarPassword", personal);
    }

    public boolean personalActivo(Personal personal) throws DataAccessException {
        String is = (String) getSqlMapClientTemplate().queryForObject("personalActivo", personal);
        if ("si".equals(is)) {
            return true;
        } else {
            return false;
        }
    }

    public Personal descripcionPersonal(Personal personal) throws DataAccessException {
        return (Personal) getSqlMapClientTemplate().queryForObject("descripcionPersonal", personal);
    }

    public Menu getMenuPersonal(Personal personal) throws DataAccessException {
        Menu menu = new Menu();
        List modalidades = getSqlMapClientTemplate().queryForList("getModalidadesByIdPersonal", personal.getId_personal());
        List tareas = getSqlMapClientTemplate().queryForList("getTareasByIdPersonal", personal.getId_personal());
        List newModalidades = new ArrayList();
        for (int i = 0; i < modalidades.size(); i++) {
            Modalidad  mod = (Modalidad) modalidades.get(i);
            List newTareas = new ArrayList();
            for (int j = 0; j < tareas.size(); j++) {
                Tarea tar = (Tarea) tareas.get(j);
                if (tar.getId_modalidad() != null) {
                    if (tar.getId_modalidad().equals(mod.getId_modalidad())) {
                        newTareas.add(tar);
                    }
                }
            }
            mod.setTareas(newTareas);
            newModalidades.add(mod);
        }
        menu.setModalidades(newModalidades);
        menu.setTareas(tareas);
        return menu;
    }
     public boolean personalAutorizado(Personal personal, String url) throws DataAccessException {
        personal.getMenu().getTarea().setUrl(url);
        Boolean var= (Boolean) getSqlMapClientTemplate().queryForObject("personalAutorizado", personal);
        return var.booleanValue();
    }

      public Estudiante getEstudianteByID(String id_estudiante) throws DataAccessException {
        Estudiante estudiante = (Estudiante) getSqlMapClientTemplate().queryForObject("getEstudianteByID", id_estudiante);
        return estudiante;
    }
       public Docente getDocenteByID(String id_docente) throws DataAccessException {
        return (Docente) getSqlMapClientTemplate().queryForObject("getDocenteByID", id_docente);
    }
}
