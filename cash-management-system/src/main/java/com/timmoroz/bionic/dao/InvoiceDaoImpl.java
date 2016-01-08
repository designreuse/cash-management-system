package com.timmoroz.bionic.dao;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.timmoroz.bionic.model.Invoice;

@Repository
public class InvoiceDaoImpl implements InvoiceDao {
    
	private static final Logger log = LogManager.getLogger();
	
	@PersistenceContext
	private EntityManager em; 
	
	@Override
	public Invoice findById(int id) {
		return em.find(Invoice.class, id);
	}

	@Override
	public void save(Invoice invoice) {
    	if (invoice.getId() == 0) {
    		em.persist(invoice);
    		log.debug("The invoice with id=" + invoice.getId() + " has been persisted.");
    	} else {
    		em.merge(invoice);
    		log.debug("The invoice with id=" + invoice.getId() + " has been merged.");
    	}
	}
	
	@Override
	public List<Invoice> findAll() {
		TypedQuery<Invoice> query =
				em.createQuery("SELECT i FROM Invoice i ORDER BY i.formedDate",
						Invoice.class);
		log.debug("The query selecting all invoices  has been created.");
		List<Invoice> listI = query.getResultList();
		return listI;
	}
	
	@Override
	public List<Invoice> findUnpaid() {
		TypedQuery<Invoice> query =
				em.createQuery("SELECT i FROM Invoice i WHERE i.status = 'P' "
						+ "ORDER BY i.formedDate", Invoice.class);
		log.debug("The query selecting all unpaid invoices has been created.");
		List<Invoice> listI = query.getResultList();
		return listI;
	}

	@Override
	public void markAsPaid(int id) {
		Invoice invoice = em.find(Invoice.class, id);
		invoice.setStatus('C'); // 'C' for completed
		invoice.setSentDate(Timestamp.valueOf(LocalDateTime.now()));
//		em.merge(invoice); // my fault
		log.debug("The invoice with id=" + invoice.getId() + " has been paid and.");
	}
}
