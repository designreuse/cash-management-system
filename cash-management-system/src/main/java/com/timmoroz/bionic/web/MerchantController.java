package com.timmoroz.bionic.web;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.timmoroz.bionic.model.Merchant;
import com.timmoroz.bionic.service.InvoiceService;
import com.timmoroz.bionic.service.MerchantService;

@Controller
@RequestMapping({"/merchant"})
public class MerchantController {
	
	@Autowired
	private MerchantService merchantService;
	
	@Autowired
	private InvoiceService invoiceService;
	
	@RequestMapping({"", "/"})
	public String findAll(Model model) {
		model.addAttribute("merchantList", merchantService.findAll());
		return "merchant/merchantList";
	}
	
	@RequestMapping("/create")
	public String createMerchant() {
		return "merchant/createMerchant";
	}
	
	@RequestMapping(value = "/put", method = RequestMethod.POST)
	public String putMerchant(@ModelAttribute Merchant merchant) {
		merchant.setLastInvoiceFormed(Date.valueOf(LocalDate.ofEpochDay(0))); // TODO check this!
		merchantService.save(merchant);
		return "redirect:/merchant";
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String removeMerchant(String id) {
		merchantService.remove(Integer.parseInt(id));
		return "redirect:/merchant";
	}
}
