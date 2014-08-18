/*
 * FERMA Model Package
 * © Vipula Dissanayake
 * 
 * Data Model of Currency is represent in Bank.java
 */
package com.FERMA.Model;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

/*
 * persistance capability will be added as an extension to the system 
 * if any focasting system developed in future versions
 */
@PersistenceCapable
public class Currency {
	@Persistent
	private String code;
	@Persistent
	private Double buyingRate;
	@Persistent
	private Double sellingRate;

	public Currency(String type) {
		this.setCode(type);
		this.setBuyingRate(0.00);
		this.setSellingRate(0.00);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String type) {
		this.code = type;
	}

	public Double getBuyingRate() {
		return buyingRate;
	}

	public void setBuyingRate(Double buyingRate) {
		this.buyingRate = buyingRate;
	}

	public Double getSellingRate() {
		return sellingRate;
	}

	public void setSellingRate(Double sellingRate) {
		this.sellingRate = sellingRate;
	}
}
