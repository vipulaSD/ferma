/*
 * FERMA API Package
 * © Vipula Dissanayake
 * 
 * Client request handled in FermaApi.java
 */
package com.FERMA.API;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import com.FERMA.Controller.App;
import com.FERMA.Model.ValuePair;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Nullable;
import com.google.api.server.spi.response.CollectionResponse;

@Api(name = "fermaApi", version = "v1", namespace = @ApiNamespace(ownerDomain = "com.FERMA.API", ownerName = "vipula", packagePath = ""))
public class FermaApi {

	/*
	 * A endpoint method that takes a Currency Code and returns offering for
	 * each bank
	 */
	@ApiMethod(name = "getBuyingRates")
	public CollectionResponse<ValuePair> buyingRates(
			@Nullable @Named("Currency Code") String code) {
		List<ValuePair> list = new ArrayList<ValuePair>();
		list = App.getBuyingRates(code);

		return CollectionResponse.<ValuePair> builder().setItems(list)
				.setNextPageToken(null).build();
	}

	/*
	 * A endpoint method that takes a Currency Code and returns offering from
	 * each bank
	 */

	@ApiMethod(name = "getSellingRates")
	public CollectionResponse<ValuePair> sellingRates(
			@Nullable @Named("Currency Code") String code) {
		List<ValuePair> list = new ArrayList<ValuePair>();
		list = App.getSellingRates(code);

		return CollectionResponse.<ValuePair> builder().setItems(list)
				.setNextPageToken(null).build();
	}

	/*
	 * an API method to update data.this will be called by a special program
	 * hourly to keep data upto date
	 */
	public void updateData() {
		App.updateDate();

	}
}