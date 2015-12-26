package com.timmoroz.bionic.dao;

import java.util.List;

import javax.persistence.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.timmoroz.bionic.model.Payment;

@Repository
public class PaymentDaoImpl implements PaymentDao {
    
	private static final Logger log = LogManager.getLogger();
	
	@PersistenceContext
    private EntityManager em;
    
    @Override
    public Payment findById(int id) {
        return em.find(Payment.class, id);
    }

    @Override
    public void save(Payment payment) {
    	if (payment.getId() == 0) {
    		em.persist(payment);
    		log.debug("The payment with id=" + payment.getId() + " has been persisted.");
    	} else {
    		em.merge(payment);
    		log.debug("The payment with id=" + payment.getId() + " has been merged.");
    	}
    }
    
    @Override
    public void remove(int id) {
    	Payment payment = em.find(Payment.class, id);
    	if (payment != null) {
    		em.remove(payment);
    		log.debug("The payment with id=" + payment.getId() + " has been removed.");
    	}
    }
    
    @Override
    public List<Payment> findAll() {
    	TypedQuery<Payment> query =
    			em.createQuery("SELECT p FROM Payment p", Payment.class);
		log.debug("The query selecting all payments has been created.");
    	List<Payment> listP = query.getResultList();
    	return listP;
    }
    
    @Override
    public List<Payment> findByMerchantId(int merchantId) {
        TypedQuery<Payment> query = em.createQuery("SELECT p FROM Payment p"
        		+ "WHERE p.merchantId = :id", Payment.class);
        query.setParameter("id", merchantId);
		log.debug("The query selecting all payments of a merchant with id=" + merchantId + " has been created.");
        return query.getResultList();
    }
}
