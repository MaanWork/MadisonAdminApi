package com.madison.motor.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GetEmiReportCountRes {

	@JsonProperty("EmiDueCount")
	private String emiDueCount;
	
	@JsonProperty("DueAmount")
	private String dueAmount;
}
