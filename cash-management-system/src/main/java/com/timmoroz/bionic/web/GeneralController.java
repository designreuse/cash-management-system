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
		paymentService.addPayment(payment);
		return showPayments();
	}
	
	// TODO method is prone to concurrent problems!
	@RequestMapping(value = "/removepayment", method = RequestMethod.POST)
	public ModelAndView removePayment(String id) {
		paymentService.removePayment(id);
		return showPayments();
	}
	
	@RequestMapping("/invoices")
	public ModelAndView showInvoices() {
		List<Invoice> invoices = invoiceService.findAll();
		return new ModelAndView("invoice/invoiceList", "invoiceList", invoices);
	}
	
	@RequestMapping("/forminvoices")
	public ModelAndView formInvoices() {
		invoiceService.formInvoices();
		return showInvoices();
	}
	
	@RequestMapping(value = "/payinvoices", method = RequestMethod.POST)
	public ModelAndView payInvoices(String availableFunds, Model model) {
		invoiceService.payInvoices(availableFunds);
		return showInvoices();
	}
}
