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

public class SampathWrapper {
	private static Bank bank;
	private static Currency currency;
	private static Document doc;
	private static Elements table;

	public static void update() {

		bank = new Bank("Sampath");

		try {
			doc = Jsoup.connect("http://www.sampath.lk/en/exchange-rates")
					.get();
			table = doc.getElementsByClass("curr-rates");

			for (int i = 0; i < 24; i++) {
				String code = table.get(0).child(0).child(1 + i).child(0)
						.text().substring(0, 3);
				Double buyingRate = Double.valueOf(table.get(0).child(0).child(1 + i).child(3).text());
				currency = new Currency(code);
				App.setCurrencyCode(code);
				currency.setBuyingRate(buyingRate);
				bank.setRates(currency);
			}
			App.updateBank(bank);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}
}
