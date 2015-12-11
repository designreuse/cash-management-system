package com.bionic.edu;

public class Result {
	private String name;
	private double sum;
	public Result(){   }
	public Result(String name, double sum){
		this.name = name;
		this.sum = sum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getSum() {
		return sum;
	}
	public void setSum(double sum) {
		this.sum = sum;
	}

}
