/*
 * FERMA Testing Package
 * © Vipula Dissanayake
 * Simply Testing during development. this can run as 
 * java application other than acting as a webservice 
 * 
 */

package com.FERMA.Testing;

import java.util.Scanner;

import com.FERMA.Controller.App;

public class Tester {

	public static void main(String[] args) {
		App.main();
		App.updateDate();
		App.showBank("BOC");
		App.showRates("USD");
		
		randomValueVerification();
	}

	/*
	 * Semi automated process to check whether data scraping is correctly done
	 */
	private static void randomValueVerification() {
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter Bank Code : ");
		String bCode = scan.next();
		System.out.print("Enter Currency Code : ");
		String cCode = scan.next();
		System.out.print("Enter Current Buying Rate: ");
		Double currentBuyingRate = scan.nextDouble();
		System.out.println("Enter Current Selling Rate: ");
		Double currentSellingRate = scan.nextDouble();
		scan.close();
		Double buyingRate = App.getBuyingRate(bCode, cCode);
		Double sellingRate = App.getSellingRate(bCode, cCode);

		if (currentBuyingRate.equals(buyingRate)
				&& currentSellingRate.equals(sellingRate)) {
			System.out.println("Data upto-date");
		} else {
			System.out.println("Data Should be Updated");
			System.out.println("buying rate : " + buyingRate + " vs. "
					+ currentBuyingRate);
			System.out.println("selling rate : " + sellingRate + " vs. "
					+ currentSellingRate);
		}

	}

}
