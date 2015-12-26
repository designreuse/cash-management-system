package com.timmoroz.bionic.web;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.timmoroz.bionic.model.Merchant;
import com.timmoroz.bionic.model.Payment;
import com.timmoroz.bionic.service.MerchantService;
import com.timmoroz.bionic.service.PaymentService;

@Controller
@RequestMapping("/payment")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private MerchantService merchantService;

	@RequestMapping({"", "/"})
	public String findAll(Model model) {
		model.addAttribute("paymentList", paymentService.findAll());
		return "payment/paymentList";
	}
	
	@RequestMapping("/create")
	public String createPayment() {
		return "payment/createPayment";
	}
	
	@RequestMapping(value = "/put", method = RequestMethod.POST)
	public String putPayment(@ModelAttribute Payment payment) {
		int merchantId = payment.getMerchantId();
		Merchant merchant = merchantService.findById(merchantId);
		payment.setDt(Timestamp.valueOf(LocalDateTime.now()));
		payment.setChargePayed(payment.getSumPayed() * merchant.getCharge() 
				/ 100);
		
		/* CHARGE GOES TO OUR BANK ACCOUNT */

		paymentService.save(payment);
		merchantService.addToNeedToSend(merchantId,
				payment.getSumPayed() - payment.getChargePayed());
		return "redirect:/payment";
	}
	
	// TODO method is prone to concurrent problems!
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String removePayment(String id) {
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
			return "redirect:/payment";
		} else {
			throw new RuntimeException("The payment has been made before or "
					+ "in the same day of the last invoice forming, and so it "
					+ "cannot be deleted.");
		}
	}
}
