package com.madison.motor.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GetEmiReportListRes {

	@JsonProperty("QuoteNo")
	private String quoteNo;
	
	@JsonProperty("PaymentMode")
	private String paymentMode;
	
	@JsonProperty("CustomerName")
	private String customerName;
	
	@JsonProperty("OverallPremium")
	private String overallPremium;
	
	@JsonProperty("PremiumAmount")
	private String premiumAmount;
	
	@JsonProperty("PremiumDate")
	private String premiumDate;
	
	@JsonProperty("NoofMonths")
	private String noofMonths;
	
	@JsonProperty("NoofEmi")
	private String noofEmi;
	
	@JsonProperty("ApplicationNo")
	private String applicationNo;
	
	@JsonProperty("NoofTerms")
	private String noofTerms;
	
	@JsonProperty("Remarks")
	private String remarks;
	
	@JsonProperty("InstallmentNo")
	private String installmentNo;
	
	@JsonProperty("Status")
	private String status;
	
	@JsonProperty("BalanceAmount")
	private String balanceAmount;
	
	@JsonProperty("Description")
	private String description;
	
	@JsonProperty("ProductId")
	private String productId;
	
	@JsonProperty("PolicyNo")
	private String policyNo;
	
	@JsonProperty("PaymentStatus")
	private String paymentStatus;
	
}
