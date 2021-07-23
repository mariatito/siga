package spring.sysmaqv.dao;

import org.springframework.dao.DataAccessException;
import spring.sysmaqv.domain.Inscripcion;

/*
 * @author MARCO ANTONIO QUENTA VELASCO
 * @since 18.6.2010
 */
public interface RegistrosDao {
void setInscripcionEstudiante(Inscripcion inscripcion) throws DataAccessException;
}
