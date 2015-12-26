package com.timmoroz.bionic.service;

import java.util.List;

import com.timmoroz.bionic.model.Customer;

public interface CustomerService {
	
	public Customer findById(int id);
	
	public void save(Customer customer);
	
	public void remove(int id);
	
	public List<Customer> findAll();
}
