package com.timmoroz.bionic.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.timmoroz.bionic.dao.MerchantDao;
import com.timmoroz.bionic.model.Merchant;

@Named
public class MerchantServiceImpl implements MerchantService{
	
	public static final int[] PAYMENT_PERIODS = {7, 10, 30};

	@Inject
    private MerchantDao merchantDao;		
    
	@Override
    public Merchant findById(int id) { 
    	return merchantDao.findById(id); 
    }
    
	@Override
    @Transactional
    public void save(Merchant merchant){
    	merchantDao.save(merchant);
    }
    
	@Override
    @Transactional
    public void remove(int id){
        merchantDao.remove(id);
    }
    
	@Override
    public List<Merchant> findAll(){
    	return merchantDao.findAll();
    }
    
	@Override
    public List<Merchant> findReadyForInvoice() {
    	List<Merchant> merchants = merchantDao.findReadyForInvoice();
    	List<Merchant> merchantsToInvoice = new ArrayList<>();
    	for (Merchant m : merchants) {
    		LocalDate todayDate = LocalDate.now();
    		LocalDate lastSent = m.getLastInvoiceFormed().toLocalDate();
    		if (m.getPeriod() > 0 && m.getPeriod() <= PAYMENT_PERIODS.length) {
    			if (lastSent.plusDays(PAYMENT_PERIODS[m.getPeriod() - 1]).
    					isBefore(todayDate)) {
    				merchantsToInvoice.add(m);
    			}
    		} else {
    			throw new IllegalArgumentException(String.format(
    					"Merchant's payment period value '%d' is not "
    					+ "within the range [1;%d]", m.getPeriod(),
    					PAYMENT_PERIODS.length));
    		}
    	}
    	return merchantsToInvoice;
    }
    
	@Override
	@Transactional
	public void addToNeedToSend(int id, double needToSendMore) {
		merchantDao.addToNeedToSend(id, needToSendMore);
	}
	
	@Override
	@Transactional
	public void updateWhenFormingInvoice(int id, double sumSent,
			java.sql.Date lastInvoiceFormed) {
		merchantDao.updateWhenFormingInvoice(id, sumSent,
				lastInvoiceFormed);
	}
}
