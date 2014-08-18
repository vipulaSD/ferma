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

public class BOCWrapper {
	private static Bank bank;
	private static Currency currency;
	private static Document doc;
	private static Elements table;

	public static void update()	 {

		bank = new Bank("BOC");

		try {
			doc = Jsoup.connect("https://www.boc.web.lk/ExRates").get();
			table = doc.select("tr");

			// Australian Dollar (AUD)
			grabValue("AUD", 3);

			// Bahrain Dinars (BHD)
			grabValue("BHD", 4);
			
			// Canadian Dollar (CAD)
			grabValue("CAD", 5);

			// Danish Kroner(DKK)
			grabValue("DKK", 6);
			
			// Euro (EUR)
			grabValue("EUR", 7);

			// Hong Kong Dollar (HKD)
			grabValue("HKD", 8);

			// Japanese Yen (JPY)
			grabValue("JPY", 9);

			// Kuwaiti Dinar(KWD)
			grabValue("KWD", 10);

			// New Zealand Dollar(NZD)
			grabValue("NZD", 11);

			// Norwegian Kroner(NOK)
			grabValue("NOK", 12);

			// Omanian Riyal(OMR)
			grabValue("OMR", 13);

			// Pounds Sterling (GBP)
			grabValue("GBP", 14);

			// Saudi Riyal (SAR)
			grabValue("SAR", 15);

			// Singapore Dollar (SGD)
			grabValue("SGD", 16);

			// South African Rand (ZAR)
			grabValue("ZAR", 17);

			// Swedish Kroner (SEK)
			grabValue("SEK", 18);

			// Swiss Franc (CHF)
			grabValue("CHF", 19);

			// UAE Dirams (AED)
			grabValue("AED", 20);

			// Chinese Renminbi (CNY)
			grabValue("CNY", 21);

			// US Dollar (USD)
			grabValue("USD", 22);

			App.updateBank(bank);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	private static void grabValue(String currancyCode, int position) {
		App.setCurrencyCode(currancyCode);
		currency = new Currency(currancyCode);
		currency.setBuyingRate(Double.valueOf(table.get(position).child(1)
				.child(0).text()));
		currency.setSellingRate(Double.valueOf(table.get(position).child(2)
				.child(0).text()));
		bank.setRates(currency);
	}
}
