package com.bionic.edu;

import java.util.List;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
public class CustomerDaoImpl implements CustomerDao {
    @PersistenceContext
    private EntityManager em;
    
	public Customer findById(int id) {
		return em.find(Customer.class, id);
	}
	
	public void save(Customer customer) {
		em.persist(customer);
	}
	
	public List<String> getNames(double sumPayed){
	      String txt = "SELECT DISTINCT c.name FROM ";   
	      txt += "Payment p, Customer c " ;
	      txt += "WHERE c.id = p.customerId AND p.sumPayed > :limit";
	      TypedQuery<String> query = em.createQuery(txt, String.class);
	      query.setParameter("limit", sumPayed);
	      return query.getResultList();
	}
	
	public List<Customer> findAll() {
        TypedQuery<Customer> query = 	  
                em.createQuery("SELECT c FROM Customer c", Customer.class);
           List<Customer> listC = query.getResultList();
           return listC;
	}
}