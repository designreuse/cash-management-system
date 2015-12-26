package com.timmoroz.bionic.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.timmoroz.bionic.dao.CustomerDao;
import com.timmoroz.bionic.model.Customer;

@Named
public class CustomerServiceImpl implements CustomerService {
	
	@Inject
    private CustomerDao customerDao;
    
	@Override
	public Customer findById(int id) {
    	return customerDao.findById(id); 
	}
	
	@Override
	@Transactional
	public void save(Customer customer) {
    	customerDao.save(customer);
	}
	
	@Override
	@Transactional
	public void remove(int id) {
		customerDao.remove(id);
	}
	
	@Override
	public List<Customer> findAll() {
		return customerDao.findAll();
	}
}
