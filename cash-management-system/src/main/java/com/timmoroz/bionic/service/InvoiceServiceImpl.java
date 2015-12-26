package com.timmoroz.bionic.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.timmoroz.bionic.dao.InvoiceDao;
import com.timmoroz.bionic.model.Invoice;

@Named
public class InvoiceServiceImpl implements InvoiceService {
    
	@Inject
	private InvoiceDao invoiceDao;
	
    @Override
	public Invoice findById(int id) {
		return invoiceDao.findById(id);
	}
	
    @Override
	@Transactional
	public void save(Invoice invoice) {
		invoiceDao.save(invoice);
	}
	
    @Override
	public List<Invoice> findAll() {
		return invoiceDao.findAll();
	}
	
    @Override
	public List<Invoice> findUnpaid() {
		return invoiceDao.findUnpaid();
	}
	
    @Override
    @Transactional
	public void payInvoices(int id) {
		invoiceDao.payInvoice(id);
	}
}
