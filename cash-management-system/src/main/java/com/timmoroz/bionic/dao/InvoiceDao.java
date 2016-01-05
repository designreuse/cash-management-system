package com.timmoroz.bionic.dao;

import java.util.List;

import com.timmoroz.bionic.model.Invoice;

public interface InvoiceDao {
	
	public Invoice findById(int id);
	
	public void save(Invoice invoice);
		
	public List<Invoice> findAll();
	
	public List<Invoice> findUnpaid();
	
	public void markAsPaid(int id);
}
