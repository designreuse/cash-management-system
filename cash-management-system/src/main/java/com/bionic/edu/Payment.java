package com.bionic.edu;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Payment {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private double chargePayed;
private double sumPayed;
private String goods;
private java.sql.Timestamp dt;
private int customerId;
@ManyToOne
@JoinColumn(name="merchantId")
private Merchant merchant;

// Constructors & accessors
public Payment() {}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public double getChargePayed() {
	return chargePayed;
}
public void setChargePayed(double chargePayed) {
	this.chargePayed = chargePayed;
}
public double getSumPayed() {
	return sumPayed;
}
public void setSumPayed(double sumPayed) {
	this.sumPayed = sumPayed;
}
public String getGoods() {
	return goods;
}
public void setGoods(String goods) {
	this.goods = goods;
}
public java.sql.Timestamp getDt() {
	return dt;
}
public void setDt(java.sql.Timestamp dt) {
	this.dt = dt;
}
public int getCustomerId() {
	return customerId;
}
public void setCustomerId(int customerId) {
	this.customerId = customerId;
}
public Merchant getMerchant() {
	return merchant;
}
public void setMerchant(Merchant merchant) {
	this.merchant = merchant;
}
@Override
public String toString() {
	return "Payment [chargePayed=" + chargePayed + ", sumPayed=" + sumPayed
			+ ", goods=" + goods + "]";
}

}
