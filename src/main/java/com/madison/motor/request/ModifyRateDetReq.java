package com.madison.motor.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ModifyRateDetReq {
	
	@JsonProperty("SumInsured")
	private String sumInsured;
	@JsonProperty("Rate")
	private String rate;
	@JsonProperty("PolicyTypeCoverId")
	private String policyTypeCoverId;
}
