package com.madison.motor.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuotePremiumInfoRes {
	
	@JsonProperty("QuoteNo")
	private String quoteNo;
	@JsonProperty("ApplicationNo")
	private String applicationNo;
	@JsonProperty("BranchCode")
	private String branchCode;
	@JsonProperty("ProductId")
	private String productId;
	@JsonProperty("QuoteCreatedDate")
	private String quoteCreatedDate;
	@JsonProperty("Currency")
	private String currency;
	@JsonProperty("Email")
	private String email;
	@JsonProperty("ProductName")
	private String productName;
	@JsonProperty("PolicyType")
	private String policyType;
	@JsonProperty("CustomerName")
	private String customerName;
	@JsonProperty("Premium")
	private String premium;
	@JsonProperty("PremiumTax")
	private String premiumTax;
	@JsonProperty("ExcessAmount")
	private String excessAmount;
	@JsonProperty("ExcessSign")
	private String excessSign;
	@JsonProperty("NetPremium")
	private String netPremium;

}
