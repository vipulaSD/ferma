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

public class COMBankWrapper {
	public static void update() {
		Bank bank = new Bank("COMBank");
		Currency currency;

		try {
			Document doc = Jsoup
					.connect(
							"http://www.combank.lk/newweb/rates-tariffs/exchange-rates")
					.get();
			Elements table = doc.select("tr");
			for (int i = 0; i < 14; i++) {
				String code = table
						.get(i + 3)
						.child(0)
						.text()
						.substring(
								table.get(i + 3).child(0).text().indexOf('(') + 1,
								table.get(i + 3).child(0).text().indexOf(')'));
				Double buyingRate = Double.valueOf(table.get(i + 3).child(1)
						.text());
				Double sellingRate;
				if (table.get(i + 3).child(2).text().equals("-")) {
					sellingRate = 0.0;
				} else {
					sellingRate = Double.valueOf(table.get(i + 3).child(2)
							.text());
				}
				App.setCurrencyCode(code);
				currency = new Currency(code);
				currency.setBuyingRate(buyingRate);
				currency.setSellingRate(sellingRate);
				bank.setRates(currency);
			}
			App.updateBank(bank);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}
}
