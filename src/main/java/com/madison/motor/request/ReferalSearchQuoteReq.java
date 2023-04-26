package com.madison.motor.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ReferalSearchQuoteReq {
	
	
	@JsonProperty("ProductId")
	private String productId;
	@JsonProperty("isAllBranch")
	private String isAllBranch;
	@JsonProperty("BranchCode")
	private String branchCode;
	@JsonProperty("QuoteStatus")
	private String quoteStatus;


}
