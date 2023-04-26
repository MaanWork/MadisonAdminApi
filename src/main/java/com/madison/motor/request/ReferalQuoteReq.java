package com.madison.motor.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReferalQuoteReq {
	
	@JsonProperty("EntryDate")
	private String entryDate;
	@JsonProperty("ProductId")
	private String productId;
	@JsonProperty("QuoteStatus")
	private String quoteStatus;
     
}
