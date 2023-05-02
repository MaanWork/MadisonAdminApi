package com.madison.motor.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GetConditionReq {
	
	@JsonProperty("PolicyTypeId")
	private String policyTypeId;
	@JsonProperty("QuoteNo")
	private String QuoteNo;
	@JsonProperty("Type")
	private String type;

}
