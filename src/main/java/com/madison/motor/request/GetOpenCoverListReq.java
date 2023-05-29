package com.madison.motor.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GetOpenCoverListReq {
	
	@JsonProperty("AgencyCode")
	private String agencyCode;
	
	@JsonProperty("CustomerId")
	private String customerId;
	
	@JsonProperty("SearchBy")
	private String searchBy;
	
	@JsonProperty("SearchValue")
	private String searchValue;

}
