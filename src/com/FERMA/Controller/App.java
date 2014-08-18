/*
 * FERMA Controller Package
 * © Vipula Dissanayake
 * 
 * Other modules work around this App.Java module
 */
package com.FERMA.Controller;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import com.FERMA.Model.Bank;
import com.FERMA.Model.Currency;
import com.FERMA.Model.ValuePair;
import com.FERMA.Scraper.BOCWrapper;
import com.FERMA.Scraper.COMBankWrapper;
import com.FERMA.Scraper.HNBWrapper;
import com.FERMA.Scraper.PeoplesBankWrapper;
import com.FERMA.Scraper.SampathWrapper;

public class App {
	private static HashMap<String, Bank> banks;

	static ArrayList<String> codes;
	static ArrayList<String> bankList;

	public static void main() {
		banks = new HashMap<String, Bank>();
		bankList = new ArrayList<String>();
		codes = new ArrayList<String>();
	}

	/*
	 * This method to update values of each bank after hourly web scraping ubank
	 * will act as updated bank details
	 */
	public static void updateBank(Bank ubank) {
		String name = ubank.getName();
		Bank bank = banks.get(name);
		bankList.add(name);

		if (bank == null) {
			banks.put(name, ubank);
		} else {
			bank.update(ubank.getRates());
		}

	}

	/*
	 * This method used to verify the correctness of the scraping manually, this
	 * will be not used in production version of the app
	 */
	public static void showRates(String code) {
		Bank bank;
		Currency cur;
		for (int i = 0; i < bankList.size(); i++) {
			bank = banks.get(bankList.get(i));
			if (bank != null) {

				cur = bank.getRates(code);
				if (cur == null) {
					System.out.println("buying : 0.0");
					System.out.println("selling: 0.0");
				} else {
					System.out.println("buying : " + cur.getBuyingRate());
					System.out.println("selling : " + cur.getSellingRate());
				}
			}

		}
	}

	/*
	 * This method use to provid service to API, this will directly handle user
	 * requests which comes through the API Method takes Currency Code as input
	 * and output a list of ValuePairs which represent buying rate and bank code
	 */
	public static List<ValuePair> getBuyingRates(String code) {
		Bank bank;
		Currency cur;
		List<ValuePair> vpList = new ArrayList<ValuePair>();
		ValuePair vp = null;
		Double rate;
		for (int i = 0; i < bankList.size(); i++) {

			bank = banks.get(bankList.get(i));
			if (bank != null) {
				
				cur = bank.getRates(code);

				if (cur == null) {
					rate = 0.0;
				} else {
					rate = cur.getBuyingRate();
				}

				vp = new ValuePair(bank.getName(), rate);
			}
			vpList.add(vp);
		}
		return vpList;
	}

	/*
	 * This method automatically learn new currency codes. whenever each bank
	 * update new currency code this will learn it and this data will be used to
	 * provide good service to customers in the client app.
	 */
	public static void setCurrencyCode(String code) {
		if (codes.indexOf(code) == -1)
			codes.add(code);
	}

	/*
	 * This method used to verify the correctness of the scraping manually, this
	 * will be not used in production version of the app
	 */
	public static void showBank(String name) {
		Bank bank = banks.get(name);
		if (bank != null) {
			for (int i = 0; i < codes.size(); i++) {
				System.out.println(codes.get(i) + " :");
				if (bank.getRates(codes.get(i)) == null) {
					System.out.println("buying : --");
					System.out.println("selling: --");
				} else {
					System.out.println("buying : "
							+ bank.getRates(codes.get(i)).getBuyingRate());
					System.out.println("selling : "
							+ bank.getRates(codes.get(i)).getSellingRate());
				}
			}

		} else {
			System.out.println("No Bank Found");
		}
	}

	/*
	 * This method will update as script call this hourly
	 */
	public static void updateDate() {
		App.main();
		BOCWrapper.update();
		PeoplesBankWrapper.update();
		HNBWrapper.update();
		SampathWrapper.update();
		COMBankWrapper.update();
	}

	/*
	 * This method will used to verify random (as developer / tester request)
	 * entries about the correctness of data
	 */
	public static Double getBuyingRate(String bCode, String cCode) {
		Bank bank = banks.get(bCode);
		Currency currency;
		if (bank == null) {
			return null;
		} else {
			currency = bank.getRates(cCode);
		}

		if (currency == null) {
			return null;
		} else {
			return currency.getBuyingRate();
		}
	}

	/*
	 * This method will used to verify random (as developer / tester request)
	 * entries about the correctness of data
	 */
	public static Double getSellingRate(String bCode, String cCode) {
		Bank bank = banks.get(bCode);
		Currency currency;
		if (bank == null) {
			return null;
		} else {
			currency = bank.getRates(cCode);
		}

		if (currency == null) {
			return null;
		} else {
			return currency.getSellingRate();
		}
	}

	/*
	 * This method use to provid service to API, this will directly handle user
	 * requests which comes through the API Method takes Currency Code as input
	 * and output a list of ValuePairs which represent selling rate and bank
	 * code
	 */
	public static List<ValuePair> getSellingRates(String code) {
		Bank bank;
		Currency cur;
		List<ValuePair> vpList = new ArrayList<ValuePair>();
		ValuePair vp = null;
		Double rate;
		for (int i = 0; i < bankList.size(); i++) {

			bank = banks.get(bankList.get(i));
			if (bank != null) {
				
				cur = bank.getRates(code);

				if (cur == null) {
					rate = 0.0;
				} else {
					rate = cur.getSellingRate();
				}

				vp = new ValuePair(bank.getName(), rate);
			}
			vpList.add(vp);
		}
		return vpList;
	}
}
