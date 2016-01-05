package com.timmoroz.bionic.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.timmoroz.bionic.dao.InvoiceDao;
import com.timmoroz.bionic.dao.MerchantDao;
import com.timmoroz.bionic.model.Invoice;
import com.timmoroz.bionic.model.Merchant;

@Named
public class InvoiceServiceImpl implements InvoiceService {
    
	@Inject
	private InvoiceDao invoiceDao;
	
	@Inject
	private MerchantDao merchantDao;
	
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
	public void formInvoices() {
		LocalDateTime ldt = null;
		List<Merchant> readyMerchants =  merchantDao.findReadyForInvoice();
		for (Merchant merchant : readyMerchants) {
			ldt = LocalDateTime.now();
			Invoice invoice = new Invoice();
			invoice.setMerchantId(merchant.getId());
			invoice.setFormedDate(Timestamp.valueOf(ldt));
			invoice.setStatus('P'); // 'P' for Pending
			invoice.setSumSent(merchant.getNeedToSend());
			invoiceDao.save(invoice);
			merchantDao.updateWhenFormingInvoice(merchant.getId(),
					invoice.getSumSent(), java.sql.Date.valueOf(ldt.toLocalDate()));
		}
	}
	
    @Override
    @Transactional
	public void payInvoices(String availableFunds) {
		double funds = Double.parseDouble(availableFunds);
		double sumOfCurrentPayments = 0;
		List<Invoice> unpaidInvoices = invoiceDao.findUnpaid();
		for (Invoice invoice : unpaidInvoices) {
			double invoiceSum = invoice.getSumSent();
			if (sumOfCurrentPayments + invoiceSum > funds) {
				continue;
			}

			// TRANSACTION STARTS
			invoiceDao.markAsPaid(invoice.getId());
			sumOfCurrentPayments += invoiceSum;
			// TRANSACTION ENDS
		}
	}
}
