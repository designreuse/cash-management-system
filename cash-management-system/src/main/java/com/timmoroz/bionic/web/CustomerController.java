package com.timmoroz.bionic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.timmoroz.bionic.model.Customer;
import com.timmoroz.bionic.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@RequestMapping({"", "/"})
	public String findAll(Model model) {
		model.addAttribute("customerList", customerService.findAll());
		return "customer/customerList";
	}
	
	@RequestMapping("/create")
	public String createCustomer() {
		return "customer/createCustomer";
	}
	
	@RequestMapping(value = "/put", method = RequestMethod.POST)
	public String putCustomer(@ModelAttribute Customer customer) {
		customerService.save(customer);
		return "redirect:/customer";
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String removeCustomer(String id) {
		customerService.remove(Integer.parseInt(id));
		return "redirect:/customer";
	}
}
