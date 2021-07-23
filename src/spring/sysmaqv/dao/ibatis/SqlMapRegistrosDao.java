package spring.sysmaqv.dao.ibatis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import spring.sysmaqv.dao.RegistrosDao;
import spring.sysmaqv.domain.Conducta;
import spring.sysmaqv.domain.Falta;
import spring.sysmaqv.domain.Fecha_regnota;
import spring.sysmaqv.domain.Inscripcion;

/*
 * @author MARCO ANTONIO QUENTA VELASCO
 * @since 18.6.2010
 */
public class SqlMapRegistrosDao extends SqlMapClientDaoSupport implements RegistrosDao {

    public void setInscripcionEstudiante(Inscripcion inscripcion) throws DataAccessException {
        getSqlMapClientTemplate().insert("setInscripcionEstudiante", inscripcion);

        Conducta conducta = new Conducta();
        Falta falta = new Falta();
        for (int j = 1; j <= 4; j++) {
            conducta.setId_estudiante(inscripcion.getId_estudiante());
            conducta.setId_curso(inscripcion.getId_curso());
            conducta.setId_eva("E" + j + "-" + inscripcion.getId_gestion());
            conducta.setId_gestion(inscripcion.getId_gestion());
            if (inscripcion.getId_curso().equals("K")) {
                if (j != 2 && j != 4) {
                    getSqlMapClientTemplate().insert("setNuevaConductaByIdEstudianteAndIdCurso", conducta);
                }
            } else {
                getSqlMapClientTemplate().insert("setNuevaConductaByIdEstudianteAndIdCurso", conducta);
            }
            falta.setId_estudiante(inscripcion.getId_estudiante());
            falta.setId_curso(inscripcion.getId_curso());
            falta.setId_eva("E" + j + "-" + inscripcion.getId_gestion());
            falta.setFcl(0);
            falta.setFsl(0);
            falta.setRetraso(0);
            falta.setId_gestion(inscripcion.getId_gestion());
            getSqlMapClientTemplate().insert("setRegistrarFaltaByIdEstudiante", falta);

        }


    }
}
