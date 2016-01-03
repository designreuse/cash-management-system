package com.timmoroz.bionic.web;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.timmoroz.bionic.model.Customer;
import com.timmoroz.bionic.model.Invoice;
import com.timmoroz.bionic.model.Merchant;
import com.timmoroz.bionic.model.Payment;
import com.timmoroz.bionic.service.CustomerService;
import com.timmoroz.bionic.service.InvoiceService;
import com.timmoroz.bionic.service.MerchantService;
import com.timmoroz.bionic.service.PaymentService;

@Controller
public class GeneralController {

	@Autowired
	MerchantService merchantService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	PaymentService paymentService;
	
	@Autowired
	InvoiceService invoiceService;
	
	@RequestMapping(value = "/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/merchants")
	public ModelAndView showMerchants() {
		List<Merchant> merchants = merchantService.findAll();
		return new ModelAndView("merchant/merchantList", "merchantList", merchants);
	}
	
	@RequestMapping(value = "/addmerchant", method = RequestMethod.GET)
	public String addMerchant(Model model) {
		return "merchant/createMerchant";
	}
	
	@RequestMapping(value = "/addmerchant", method = RequestMethod.POST)
	public ModelAndView addMerchant(@ModelAttribute Merchant merchant) {
		merchant.setLastInvoiceFormed(Date.valueOf(LocalDate.ofEpochDay(0))); // TODO check this!
		merchantService.save(merchant);
		return showMerchants();
	}
	
	@RequestMapping(value = "/removemerchant", method = RequestMethod.POST)
	public ModelAndView removeMerchant(String id) {
		merchantService.remove(Integer.parseInt(id));
		return showMerchants();
	}
	
	@RequestMapping("/customers")
	public ModelAndView showCustomers() {
		List<Customer> customers = customerService.findAll();
		return new ModelAndView("customer/customerList", "customerList", customers);
	}
	
	@RequestMapping(value = "/addcustomer", method = RequestMethod.GET)
	public String addCustomer(Model model) {
		return "customer/createCustomer";
	}
	
	@RequestMapping(value = "/addcustomer", method = RequestMethod.POST)
	public ModelAndView addCustomer(@ModelAttribute Customer customer) {
		customerService.save(customer);
		return showCustomers();
	}
	
	@RequestMapping(value = "/removecustomer", method = RequestMethod.POST)
	public ModelAndView removeCustomer(String id) {
		customerService.remove(Integer.parseInt(id));
		return showCustomers();
	}
	
	@RequestMapping("/payments")
	public ModelAndView showPayments() {
		List<Payment> payments = paymentService.findAll();
		return new ModelAndView("payment/paymentList", "paymentList", payments);
	}
	
	@RequestMapping(value = "/addpayment", method = RequestMethod.GET)
	public String addPayment(Model model) {
		return "payment/createPayment";
	}
	
	@RequestMapping(value = "/addpayment", method = RequestMethod.POST)
	public ModelAndView addPayment(@ModelAttribute Payment payment) {
		int merchantId = payment.getMerchantId();
		Merchant merchant = merchantService.findById(merchantId);
		payment.setDt(Timestamp.valueOf(LocalDateTime.now()));
		payment.setChargePayed(payment.getSumPayed() * merchant.getCharge() 
				/ 100);
		
		/* CHARGE GOES TO OUR BANK ACCOUNT */

		paymentService.save(payment);
		merchantService.addToNeedToSend(merchantId,
				payment.getSumPayed() - payment.getChargePayed());
		return showPayments();
	}
	
	// TODO method is prone to concurrent problems!
	@RequestMapping(value = "/removepayment", method = RequestMethod.POST)
	public ModelAndView removePayment(String id) {
		Payment payment = paymentService.findById(Integer.parseInt(id));
		java.sql.Date lastInvoiceFormedDate =
				merchantService.
				findById(payment.getMerchantId()).
				getLastInvoiceFormed();
		LocalDate paymentDate = payment.getDt().toLocalDateTime().toLocalDate();
		LocalDate lastInvoiceDate = lastInvoiceFormedDate.toLocalDate();

		if (paymentDate.isAfter(lastInvoiceDate)) {
			merchantService.addToNeedToSend(payment.getMerchantId(),
					payment.getSumPayed() * (-1));
			paymentService.remove(payment.getId());
			return showPayments();
		} else {
			throw new RuntimeException("The payment has been made before or "
					+ "in the same day of the last invoice forming, and so it "
					+ "cannot be deleted.");
		}
	}
	
	@RequestMapping("/invoices")
	public ModelAndView showInvoices() {
		List<Invoice> invoices = invoiceService.findAll();
		return new ModelAndView("invoice/invoiceList", "invoiceList", invoices);
	}
	
	@RequestMapping("/forminvoices")
	public ModelAndView formInvoices() {
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
		return showInvoices();
	}
	
	@RequestMapping(value = "/payinvoices", method = RequestMethod.POST)
	public ModelAndView payInvoices(String availableFunds, Model model) {
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
		
		return showInvoices();
	}
}