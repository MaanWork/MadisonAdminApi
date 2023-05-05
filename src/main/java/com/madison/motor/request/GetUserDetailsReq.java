package com.madison.motor.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GetUserDetailsReq {
	
	@JsonProperty("BranchCode")
	private String branchCode;
	@JsonProperty("BrokerCode")
	private String BrokerCode;
}
