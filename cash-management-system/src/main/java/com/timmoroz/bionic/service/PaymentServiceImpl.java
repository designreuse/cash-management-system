package com.timmoroz.bionic.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.timmoroz.bionic.dao.MerchantDao;
import com.timmoroz.bionic.dao.PaymentDao;
import com.timmoroz.bionic.model.Merchant;
import com.timmoroz.bionic.model.Payment;

@Named
public class PaymentServiceImpl implements PaymentService {
    
	@Inject
    private PaymentDao paymentDao;
	
	@Inject
	private MerchantDao merchantDao;
	
    @Override
	public Payment findById(int id) {
		return paymentDao.findById(id);
	}
	
    @Override
	@Transactional
	public void save(Payment payment) {
		paymentDao.save(payment);
	}
	
    @Override
	@Transactional
	public void remove(int id) {
		paymentDao.remove(id);
	}
	
    @Override
	public List<Payment> findAll() {
		return paymentDao.findAll();
	}
	
    @Override
    public List<Payment> findByMerchantId(int id){
    	return paymentDao.findByMerchantId(id);
    }
    
    @Override
    @Transactional
	public void addPayment(Payment payment) {
		int merchantId = payment.getMerchantId();
		Merchant merchant = merchantDao.findById(merchantId);
		payment.setDt(Timestamp.valueOf(LocalDateTime.now()));
		payment.setChargePayed(payment.getSumPayed() * merchant.getCharge() 
				/ 100);
		
		/* CHARGE GOES TO OUR BANK ACCOUNT */

		paymentDao.save(payment);
		merchantDao.addToNeedToSend(merchantId,
				payment.getSumPayed() - payment.getChargePayed());
	}
	
	@Override
    @Transactional
	public void removePayment(String id) {
		Payment payment = paymentDao.findById(Integer.parseInt(id));
		java.sql.Date lastInvoiceFormedDate =
				merchantDao.
				findById(payment.getMerchantId()).
				getLastInvoiceFormed();
		LocalDate paymentDate = payment.getDt().toLocalDateTime().toLocalDate();
		LocalDate lastInvoiceDate = lastInvoiceFormedDate.toLocalDate();

		if (paymentDate.isAfter(lastInvoiceDate)) {
			merchantDao.addToNeedToSend(payment.getMerchantId(),
					payment.getSumPayed() * (-1));
			paymentDao.remove(payment.getId());
		} else {
			throw new RuntimeException("The payment has been made before or "
					+ "in the same day of the last invoice forming, and so it "
					+ "cannot be deleted.");
		}
	}
}
