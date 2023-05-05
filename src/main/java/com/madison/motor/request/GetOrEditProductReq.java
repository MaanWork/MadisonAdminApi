package com.madison.motor.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GetOrEditProductReq {
	
	@JsonProperty("AgencyCode")
	private String agencyCode;
	
	@JsonProperty("ProductId")
	private String productId;

}
