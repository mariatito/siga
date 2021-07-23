package spring.sysmaqv.dao;

import org.springframework.dao.DataAccessException;
import spring.sysmaqv.domain.Docente;
import spring.sysmaqv.domain.Estudiante;
import spring.sysmaqv.domain.Menu;
import spring.sysmaqv.domain.Personal;

/*
 * @author MARCO ANTONIO QUENTA VELASCO
 * @since 18.6.2010
 */
public interface PersonalDao {

    void setCambiarPassword(Personal personal) throws DataAccessException;

    boolean personalActivo(Personal personal) throws DataAccessException;

    Personal descripcionPersonal(Personal personal) throws DataAccessException;

    Menu getMenuPersonal(Personal personal) throws DataAccessException;

    boolean personalAutorizado(Personal personal, String url) throws DataAccessException;

    Estudiante getEstudianteByID(String id_estudiante) throws DataAccessException;

    Docente getDocenteByID(String id_docente) throws DataAccessException;
}
