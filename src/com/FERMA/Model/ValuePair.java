/*
 * FERMA Model Package
 * © Vipula Dissanayake
 * 
 * Data Model of ValuePair is represent in ValuePair.java
 * Simply this model use to create responds to user request through the API
 */
package com.FERMA.Model;

public class ValuePair {
	private String bankCode;
	private Double rate;

	public ValuePair(String code, Double value) {
		this.setBankCode(code);
		this.setRate(value);
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

}
