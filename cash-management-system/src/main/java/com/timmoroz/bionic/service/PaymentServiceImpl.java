package com.timmoroz.bionic.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.timmoroz.bionic.dao.PaymentDao;
import com.timmoroz.bionic.model.Payment;

@Named
public class PaymentServiceImpl implements PaymentService {
    
	@Inject
    private PaymentDao paymentDao;
	
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
}
