package spring.sysmaqv.dao;

import spring.sysmaqv.domain.Deposito;

/*
 * @author MARCO ANTONIO QUENTA VELASCO
 * @since 18.6.2010
 */
public interface PensionesDao {
        public void setRegistrarDeposito(Deposito deposito);

    public Deposito getDepositoByIdDeposito(Deposito deposito);
}
