package com.madison.motor.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class RoadAssistantListReq {
	
	@JsonProperty("FromDate")
	private String fromDate;
	
	@JsonProperty("ToDate")
	private String toDate;

}
