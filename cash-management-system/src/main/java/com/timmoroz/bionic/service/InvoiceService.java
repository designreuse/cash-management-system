package com.timmoroz.bionic.service;

import java.util.List;

import com.timmoroz.bionic.model.Invoice;

public interface InvoiceService {
	
	public Invoice findById(int id);
	
	public void save(Invoice invoice);
		
	public List<Invoice> findAll();
	
	public List<Invoice> findUnpaid();
	
	public void formInvoices();
	
	public void payInvoices(String availableFunds);
}
