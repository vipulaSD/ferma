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
import org.jsoup.nodes.Element;

import com.FERMA.Controller.App;
import com.FERMA.Model.Bank;
import com.FERMA.Model.Currency;

public class HNBWrapper {
	public static void update() {
		Bank bank = new Bank("HNB");
		Currency currency;
		try {
			Document doc = Jsoup.connect(
					"http://www.hnb.net/data/tools/exchange_rates.php").get();
			Element table = doc.getElementById("middle");
			for (int i = 3; i < 14; i++) {
				String code = table.child(i).child(2).text();
				String buying = table.child(i).child(3).text();
				String selling = table.child(i).child(4).text();
				currency = new Currency(code);
				App.setCurrencyCode(code);
				currency.setBuyingRate(Double.valueOf(buying));
				currency.setSellingRate(Double.valueOf(selling));
				bank.setRates(currency);

			}

			App.updateBank(bank);

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}
}
