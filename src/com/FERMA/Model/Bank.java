/*
 * FERMA Model Package
 * © Vipula Dissanayake
 * 
 * Data Model of Bank is represent in Bank.java
 */
package com.FERMA.Model;

import java.util.HashMap;

//Represent a bank

public class Bank {

	private HashMap<String, Currency> currencyRates;
	private String name;

	public Bank(String name) {
		currencyRates = new HashMap<String, Currency>();
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	public Currency getRates(String key) {
		if (currencyRates.containsKey(key)) {
			return currencyRates.get(key);
		} else {
			return null;
		}
	}

	public HashMap<String, Currency> getRates() {
		return this.currencyRates;
	}

	public void setRates(Currency value) {
		String key = value.getCode();
		Currency cur = currencyRates.get(key);
		if (cur == null) {
			cur = value;
			currencyRates.put(key, value);
		} else {
			cur.setBuyingRate(value.getBuyingRate());
			cur.setSellingRate(value.getSellingRate());
		}
	}

	public void update(HashMap<String, Currency> rates) {
		this.currencyRates = rates;

	}
}
