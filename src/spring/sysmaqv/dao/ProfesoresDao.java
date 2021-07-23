package spring.sysmaqv.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import spring.sysmaqv.domain.CursoMateria;
import spring.sysmaqv.domain.Docente;

/*
 * @author MARCO ANTONIO QUENTA VELASCO
 * @since 18.6.2010
 */
public interface ProfesoresDao {
    void setRegistrarDocente(Docente docente) throws DataAccessException;

    public List getCursosByIdDocenteAndIdGestion(CursoMateria cursomateria);

    public List getCursosTutorByIdDocenteAndIdGestion(CursoMateria cursomateria);

//    public List getCursosAsignadosRegistrarFaltasByIdDocenteAndIdGestion(CursoMateria cursomateria);
}
