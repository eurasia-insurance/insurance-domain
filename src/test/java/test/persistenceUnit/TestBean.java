package test.persistenceUnit;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.lapsa.insurance.jpa.InsuranceConstants;

@Startup
@Singleton
public class TestBean {

    @PersistenceContext(unitName = InsuranceConstants.PERSISTENCE_UNIT_NAME)
    protected EntityManager em;

    public EntityManager getEm() {
	return em;
    }
}
