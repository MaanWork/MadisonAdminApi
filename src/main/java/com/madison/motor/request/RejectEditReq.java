package com.madison.motor.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RejectEditReq {

	@JsonProperty("PolicyNumber")
	private String policyNumber;
	
	@JsonProperty("ClaimRef")
	private String claimref;
	
}
