package com.timmoroz.bionic.dao;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

import com.timmoroz.bionic.model.Merchant;

@Repository
public class MerchantDaoImpl implements MerchantDao {
    
	private static final Logger log = LogManager.getLogger();
	
	@PersistenceContext
    private EntityManager em;
    	
    @Override
    public Merchant findById(int id) {
    	return em.find(Merchant.class, id);
    }

    @Override
    public void save(Merchant merchant) {
    	if (merchant.getId() == 0) {
    		em.persist(merchant);
    		log.debug("The merchant with id=" + merchant.getId() + " has been persisted.");
    	} else {
    		em.merge(merchant);
    		log.debug("The merchant with id=" + merchant.getId() + " has been merged.");
    	}
    }
    
    @Override
    public void remove(int id) {
        Merchant merchant = em.find(Merchant.class, id);
        if (merchant != null){
        	em.remove(merchant);
    		log.debug("The merchant with id=" + merchant.getId() + " has been removed.");
        }
    }
    
    @Override
    public List<Merchant> findAll() {
        TypedQuery<Merchant> query = 	  
              em.createQuery("SELECT m FROM Merchant m", Merchant.class);
         List<Merchant> listM = query.getResultList();     
 		log.debug("The query selecting all merchants has been created.");
         return listM;
    }
    
    @Override
    public List<Merchant> findReadyForInvoice() {
    	TypedQuery<Merchant> query = em.createQuery("SELECT m FROM Merchant m "
    			+ "WHERE m.needToSend > m.minSum",
				Merchant.class);
		List<Merchant> listM = query.getResultList();
 		log.debug("List of merchants ready for making out of an invoice has been selected.");
		return listM;
    }
	
    @Override
	public void addToNeedToSend(int id, double needToSendMore) {
		Merchant merchant = em.find(Merchant.class, id);
		merchant.setNeedToSend(merchant.getNeedToSend() + needToSendMore);
		//em.merge(merchant); // my fault
 		log.debug("Merchant with id=" + merchant.getId() +" has been merged.");
	}
	
    @Override
	public void updateWhenFormingInvoice(int id, double sumSent, java.sql.Date lastInvoiceFormed) {
		Merchant merchant = em.find(Merchant.class, id);
		merchant.setNeedToSend(merchant.getNeedToSend() - sumSent);
		merchant.setSent(merchant.getSent() + sumSent);
		merchant.setLastInvoiceFormed(lastInvoiceFormed);
		//em.merge(merchant); // my fault
 		log.debug("Merchant with id=" + merchant.getId() +" has been merged.");
	}
}
