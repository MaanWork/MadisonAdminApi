package com.madison.motor.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PolicyTypeReq {
	
	@JsonProperty("PolicyTypeId")
	private String policyTypeId;
	@JsonProperty("MinSiValue")
	private String minSiValue;
	@JsonProperty("MaxSiValue")
	private String maxSiValue;

}
