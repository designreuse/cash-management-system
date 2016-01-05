package com.timmoroz.bionic.service;

import java.util.List;

import com.timmoroz.bionic.model.Payment;

public interface PaymentService {

	public Payment findById(int id);
	
	public void save(Payment payment);

	public void remove(int id);
	
	public List<Payment> findAll();

	public List<Payment> findByMerchantId(int id);
	
	public void addPayment(Payment payment);
	
	public void removePayment(String id);
}
