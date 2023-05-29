package com.madison.motor.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GetReferalByQuoteRes {

	@JsonProperty("QuoteCreated")
	private String quoteCreated;
	
	@JsonProperty("ApplicationNo")
	private String applicationNo;
	
	@JsonProperty("LoginId")
	private String loginId;
	
	@JsonProperty("QuoteNo")
	private String quoteNo;
	
	@JsonProperty("Remarks")
	private String remarks;
	
	@JsonProperty("ReferralDescription")
	private String referralDescription;
	
	@JsonProperty("SummaryRemarks")
	private String summaryRemarks;
	
	@JsonProperty("SchemeId")
	private String schemeId;
	
	@JsonProperty("ApplicationId")
	private String applicationId;
	
	@JsonProperty("Status")
	private String status;
	
	@JsonProperty("Statusdesc")
	private String statusdesc;
	
	@JsonProperty("CustomerId")
	private String customerId;
	
	@JsonProperty("Custname")
	private String custname;
	
	@JsonProperty("Brokername")
	private String brokername;
}
