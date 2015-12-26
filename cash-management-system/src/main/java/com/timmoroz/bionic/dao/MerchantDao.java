package com.timmoroz.bionic.dao;

import java.util.List;

import com.timmoroz.bionic.model.Merchant;

public interface MerchantDao {
	
	public Merchant findById(int id);
	
	public void save(Merchant merchant);
	
	public void remove(int id);
	
	public List<Merchant> findAll();
	
	public List<Merchant> findReadyForInvoice();
	
	public void addToNeedToSend(int id, double needToSendMore);

	public void updateWhenFormingInvoice(int id, double sumSent, java.sql.Date lastInvoiceFormed);
}
