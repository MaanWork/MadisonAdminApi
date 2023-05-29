package com.madison.motor.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GetReferalByQuoteReq {

	@JsonProperty("EntryDate")
	private String entryDate;
	
	@JsonProperty("ProductId")
	private String productId;
	
	@JsonProperty("QuoteNo")
	private String quoteNo;
}
