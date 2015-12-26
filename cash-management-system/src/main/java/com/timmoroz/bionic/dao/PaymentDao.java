package com.timmoroz.bionic.dao;

import java.util.List;

import com.timmoroz.bionic.model.Payment;

public interface PaymentDao {
	
	public Payment findById(int id);
	
	public void save(Payment payment);
	
	public void remove(int id);
	
	public List<Payment> findAll();
	
	public List<Payment> findByMerchantId(int merchantId);
}
