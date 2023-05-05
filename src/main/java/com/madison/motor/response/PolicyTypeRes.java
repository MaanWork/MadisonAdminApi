package com.madison.motor.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PolicyTypeRes {
	
	@JsonProperty("PolicyTypeId")
	private String policyTypeId;
	@JsonProperty("MinSiValue")
	private String minSiValue;
	@JsonProperty("MaxSiValue")
	private String maxSiValue;
	@JsonProperty("PolicyFee")
	private String policyFee;
	@JsonProperty("OtherFee")
	private String otherFee;


}
