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
public class ReferalQuoteRes {
	
	@JsonProperty("BrokerName")
	private String brokerName;
	@JsonProperty("QuoteCreated")
	private String quoteCreated;
	@JsonProperty("CustomerName")
	private String customerName;
	@JsonProperty("QuoteNo")
	private String quoteNo;
	@JsonProperty("Remarks")
	private String remarks;
	@JsonProperty("Status")
	private String status;
	@JsonProperty("ApplicationNo")
	private String applicationNo;
	@JsonProperty("CustomerId")
	private String customerId;
	

}
