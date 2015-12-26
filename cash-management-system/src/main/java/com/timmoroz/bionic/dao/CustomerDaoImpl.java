package com.timmoroz.bionic.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

import com.timmoroz.bionic.model.Customer;;


@Repository
public class CustomerDaoImpl implements CustomerDao {
    
	private static final Logger log = LogManager.getLogger();
	
	@PersistenceContext
    private EntityManager em;
    
    @Override
	public Customer findById(int id) {
		return em.find(Customer.class, id);
	}
	
    @Override
	public void save(Customer customer) {
		if (customer.getId() == 0){
    		em.persist(customer);
    		log.debug("The customer with id=" + customer.getId() + " has been persisted.");
    	} else{
    		em.merge(customer);
    		log.debug("The customer with id=" + customer.getId() + " has been merged.");
    	}
	}
	
    @Override
	public void remove(int id) {
		Customer customer = em.find(Customer.class, id);
		if (customer != null) {
			em.remove(customer);
    		log.debug("The customer with id=" + customer.getId() + " has been removed.");
		}
	}
	
    @Override
	public List<Customer> findAll() {
        TypedQuery<Customer> query = 	  
                em.createQuery("SELECT c FROM Customer c", Customer.class);
        List<Customer> listC = query.getResultList();
   		log.debug("The query selecting all customers has been created.");
        return listC;
	}
}
