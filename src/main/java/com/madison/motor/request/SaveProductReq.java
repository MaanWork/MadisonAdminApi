package com.madison.motor.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SaveProductReq {
	
	@JsonProperty("AgencyCode")
	private String agencyCode;
	@JsonProperty("CustomerId")
	private String customerId;
	@JsonProperty("LoginId")
	private String loginId;
	@JsonProperty("Product")
	private List<ProductReq> product;
	
}
