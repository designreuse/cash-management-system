package com.timmoroz.bionic.model;

import javax.persistence.*;

@Entity
public class Merchant {
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    private String name;
    
    private String bankName;
    
    private String swift;
    
    private String account;
    
    private double charge;
    
    private short period;
    
    private double minSum;
    
    private double needToSend;
    
    private double sent;
    
    private java.sql.Date lastInvoiceFormed;

    public Merchant(){
        
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getBankName() {
        return bankName;
    }
    
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    
    public String getSwift() {
        return swift;
    }
    
    public void setSwift(String swift) {
        this.swift = swift;
    }
    
    public String getAccount() {
        return account;
    }
    
    public void setAccount(String account) {
        this.account = account;
    }
    
    public double getCharge() {
        return charge;
    }
    
    public void setCharge(double charge) {
        this.charge = charge;
    }
    
    public short getPeriod() {
        return period;
    }
    
    public void setPeriod(short period) {
        this.period = period;
    }
    
    public double getMinSum() {
        return minSum;
    }
    
    public void setMinSum(double minSum) {
        this.minSum = minSum;
    }
    
    public double getNeedToSend() {
        return needToSend;
    }
    
    public void setNeedToSend(double needToSend) {
        this.needToSend = needToSend;
    }
    
    public double getSent() {
        return sent;
    }
    
    public void setSent(double sent) {
        this.sent = sent;
    }
    
    public java.sql.Date getLastInvoiceFormed() {
        return lastInvoiceFormed;
    }
    
    public void setLastInvoiceFormed(java.sql.Date lastInvoiceFormed) {
        this.lastInvoiceFormed = lastInvoiceFormed;
    }

    @Override
    public String toString() {
        return "Merchant [id=" + id + ", name=" + name + ", bankName=" + 
    bankName + ", swift=" + swift + ", account="+ account + ", charge=" +
        		charge + ", period=" + period + ", minSum=" + minSum +
        		", needToSend=" + needToSend + ", sent=" + sent +
        		", lastInvoiceFormed=" + lastInvoiceFormed + "]";
    }
}
