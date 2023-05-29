package com.madison.motor.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UpdateQuotePremiumReq {
	
	@JsonProperty("ApplicationNo")
	private String applicationNo;
	@JsonProperty("LoginId")
	private String loginId;
	@JsonProperty("Premium")
	private String premium;
	@JsonProperty("ExcessAmount")
	private String excessAmount;
	@JsonProperty("ExcessSign")
	private String excessSign;
	@JsonProperty("PolicyTax")
	private String policyTax;
	@JsonProperty("NetPremium")
	private String netPremium;
	@JsonProperty("QuoteStatus")
	private String quoteStatus;
	@JsonProperty("Remarks")
	private String remarks;
	@JsonProperty("InstallmentYN")
	private String installmentYN;

}
