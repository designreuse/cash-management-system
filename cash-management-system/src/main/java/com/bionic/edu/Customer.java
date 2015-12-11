package com.bionic.edu;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private String address;
	private String email;
	private String ccno;
	private String cctype;
	private java.sql.Date maturity;
	
	public Customer() {}

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCcno() {
		return ccno;
	}

	public void setCcno(String ccno) {
		this.ccno = ccno;
	}

	public String getCctype() {
		return cctype;
	}

	public void setCctype(String cctype) {
		this.cctype = cctype;
	}

	public java.sql.Date getMaturity() {
		return maturity;
	}

	public void setMaturity(java.sql.Date maturity) {
		this.maturity = maturity;
	}
}
