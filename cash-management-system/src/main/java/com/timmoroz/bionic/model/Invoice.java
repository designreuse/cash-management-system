package com.timmoroz.bionic.model;

import javax.persistence.*;

@Entity
public class Invoice {
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    private int merchantId;
    
    private double sumSent;
    
    private java.sql.Timestamp sentDate;
    
    private java.sql.Timestamp formedDate;
    
    private char status;
    
    public Invoice() {
        
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getMerchantId() {
        return merchantId;
    }
    
    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }
    
    public double getSumSent() {
        return sumSent;
    }
    
    public void setSumSent(double sumSent) {
        this.sumSent = sumSent;
    }
    
    public java.sql.Timestamp getSentDate() {
        return sentDate;
    }
    
    public void setSentDate(java.sql.Timestamp sentDate) {
        this.sentDate = sentDate;
    }
    
    public java.sql.Timestamp getFormedDate() {
        return formedDate;
    }
    
    public void setFormedDate(java.sql.Timestamp formedDate) {
        this.formedDate = formedDate;
    }
    
    public char getStatus() {
        return status;
    }
    
    public void setStatus(char status) {
        this.status = status;
    }

	@Override
	public String toString() {
		return "Invoice [id=" + id + ", merchantId=" + merchantId
				+ ", sumSent=" + sumSent + ", sentDate=" + sentDate
				+ ", formedDate=" + formedDate + ", status=" + status + "]";
	}
}
