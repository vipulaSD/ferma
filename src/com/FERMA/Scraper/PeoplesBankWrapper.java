/*
 * FERMA Scraper Package
 * © Vipula Dissanayake
 * 
 * Scraping from bank website is done here.
 * This class use Jsoup library which is under MIT licence
 * 
 */
package com.FERMA.Scraper;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.FERMA.Controller.App;
import com.FERMA.Model.Bank;
import com.FERMA.Model.Currency;

public class PeoplesBankWrapper {
	private static Bank bank;
	private static Currency currency;
	private static Document doc;
	private static Elements table;

	public static void update() {
		bank = new Bank("Peoples");

		try {
			doc = Jsoup.connect("http://www.peoplesbank.lk/exchangerates")
					.get();
			table = doc.select("tr");
			
			//System.out.println(table.get(5).child(3).text());

			// Australian Dollar (AUD)
			grabValue("AUD", 5);

			// Canadian Dollar (CAD)
			grabValue("CAD", 6);

			// Danish Kroner(DKK)
			grabValue("DKK", 7);

			// Euro (EUR)
			grabValue("EUR", 8);

			// Pounds Sterling (GBP)
			grabValue("GBP", 9);

			// Hong Kong Dollar (HKD)
			grabValue("HKD", 10);

			// Japanese Yen (JPY)
			grabValue("JPY", 11);

			// New Zealand Dollar(NZD)
			grabValue("NZD", 12);

			// Norwegian Kroner(NOK)
			grabValue("NOK", 13);

			// Saudi Riyal (SAR)
			grabValue("SAR", 14);

			// Singapore Dollar (SGD)
			grabValue("SGD", 15);

			// Swedish Kroner (SEK)
			grabValue("SEK", 16);

			// Swiss Franc (CHF)
			grabValue("CHF", 17);

			// UAE Dirams (AED)
			grabValue("AED", 18);

			// US Dollar (USD)
			grabValue("USD", 19);

			App.updateBank(bank);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void grabValue(String currencyCode, int position) {
		App.setCurrencyCode(currencyCode);
		currency = new Currency(currencyCode);
		currency.setBuyingRate(Double.valueOf(table.get(position).child(2).text()));
		currency.setSellingRate(Double.valueOf(table.get(position).child(3).text()));
		bank.setRates(currency);
	}
}
