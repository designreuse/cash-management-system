package com.timmoroz.bionic.web;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.timmoroz.bionic.model.Invoice;
import com.timmoroz.bionic.model.Merchant;
import com.timmoroz.bionic.service.InvoiceService;
import com.timmoroz.bionic.service.MerchantService;

@Controller
@RequestMapping("/invoice")
public class InvoiceController {
	
	@Autowired
	private InvoiceService invoiceService;
	
	@Autowired
	private MerchantService merchantService;
	
	@RequestMapping({"", "/"})
	public String findAll(Model model) {
		model.addAttribute("invoiceList", invoiceService.findAll());
		return "invoice/invoiceList";
	}
	
	@RequestMapping("/form")
	public String formInvoices() {
		LocalDateTime ldt = null;
		List<Merchant> readyMerchants =  merchantService.findReadyForInvoice();
		for (Merchant merchant : readyMerchants) {
			ldt = LocalDateTime.now();
			Invoice invoice = new Invoice();
			invoice.setMerchantId(merchant.getId());
			invoice.setFormedDate(Timestamp.valueOf(ldt));
			invoice.setStatus('P'); // 'P' for Pending
			invoice.setSumSent(merchant.getNeedToSend());
			invoiceService.save(invoice);
			merchantService.updateWhenFormingInvoice(merchant.getId(),
					invoice.getSumSent(), java.sql.Date.valueOf(ldt.toLocalDate()));
		}
		return "redirect:/invoice";
	}
	
	@RequestMapping(value = "/pay", method = RequestMethod.POST)
	public String payInvoices(String availableFunds, Model model) {
		double funds = Double.parseDouble(availableFunds);
		double sumOfCurrentPayments = 0;
		List<Invoice> unpaidInvoices = invoiceService.findUnpaid();
		for (Invoice invoice : unpaidInvoices) {
			double invoiceSum = invoice.getSumSent();
			if (sumOfCurrentPayments + invoiceSum > funds) {
				continue;
			}

			// TRANSACTION STARTS
			invoiceService.payInvoices(invoice.getId());
			sumOfCurrentPayments += invoiceSum;
			// TRANSACTION ENDS
		}
		
		return "redirect:/invoice";
	}
}
