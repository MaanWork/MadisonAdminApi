package com.madison.motor.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetPortfolioRecordRes {
	
	
	@JsonProperty("BrokerName")
	private String brokerName;
	
	@JsonProperty("QuoteCreatedBy")
	private String quoteCreatedBy;
	
	@JsonProperty("CustomerName")
	private String customerName;
	
	@JsonProperty("QuoteNo")
	private String quoteNo;
	
	@JsonProperty("PolicyStartDate")
	private String policyStartDate;
	
	@JsonProperty("PolicyEndDate")
	private String policyEndDate;
	
	@JsonProperty("PolicyType")
	private String policyType;
	
	@JsonProperty("Premium")
	private String premium;
	
	@JsonProperty("PolicyNo")
	private String policyNo;
	
	@JsonProperty("PaymentType")
	private String paymentType;
}
