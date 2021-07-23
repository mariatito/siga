package spring.sysmaqv.dao.ibatis;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import spring.sysmaqv.dao.PensionesDao;
import spring.sysmaqv.domain.Deposito;

/*
 * @author MARCO ANTONIO QUENTA VELASCO
 * @since 18.6.2010
 */
public class SqlMapPensionesDao extends SqlMapClientDaoSupport implements PensionesDao{
    public void setRegistrarDeposito(Deposito deposito) {
        getSqlMapClientTemplate().insert("setRegistrarDeposito", deposito);
    }

    public Deposito getDepositoByIdDeposito(Deposito deposito) {
        return (Deposito) getSqlMapClientTemplate().queryForObject("getDepositoByIdDeposito", deposito);
    }
}
