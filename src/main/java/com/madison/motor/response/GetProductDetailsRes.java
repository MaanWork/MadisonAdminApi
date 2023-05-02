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
public class GetProductDetailsRes {
	
	
	@JsonProperty("ProductName")
	private String productName;
	@JsonProperty("ProductId")
	private String productId;
	@JsonProperty("Commission")
	private String commission;
	@JsonProperty("InsuranceEndLimit")
	private String insuranceEndLimit;
	@JsonProperty("SpecialDiscount")
	private String specialDiscount;
	@JsonProperty("Referal")
	private String referal;
	@JsonProperty("Status")
	private String status;
	@JsonProperty("DiscountOfPremium")
	private String discountOfPremium;
	@JsonProperty("MinPremiumAmount")
	private String minPremiumAmount;
	@JsonProperty("BackDateAllowed")
	private String backDateAllowed;
	@JsonProperty("ProCommission")
	private String proCommission;
	@JsonProperty("ProStartDate")
	private String proStartDate;
	@JsonProperty("ProEndDate")
	private String proEndDate;
	@JsonProperty("LoadingOfPremium")
	private String loadingOfPremium;
	@JsonProperty("PayReceiptStatus")
	private String payReceiptStatus;
	@JsonProperty("ReceiptStatus")
	private String receiptStatus;
	@JsonProperty("FreightDebitOption")
	private String freightDebitOption;
	@JsonProperty("ProvisionForPremium")
	private String provisionForPremium;
	

}
