package com.madison.motor.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ProductReq {
	
	@JsonProperty("ProductId")
	private String productId;
	@JsonProperty("SpecialDiscount")
	private String specialDiscount;
	@JsonProperty("SumInsured")
	private String sumInsured;
	@JsonProperty("CreditYn")
	private String creditYn;
	@JsonProperty("OpenCoverNo")
	private String openCoverNo;
}
