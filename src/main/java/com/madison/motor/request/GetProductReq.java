package com.madison.motor.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GetProductReq {
	
	@JsonProperty("AgencyCode")
	private String agencyCode;
	
	@JsonProperty("OaCode")
	private String oaCode;
	
	@JsonProperty("BranchCode")
	private String branchCode;

}
