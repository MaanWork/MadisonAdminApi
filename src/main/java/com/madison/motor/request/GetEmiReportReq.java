package com.madison.motor.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GetEmiReportReq {
	
	@JsonProperty("StartDate")
	private String startDate;

	@JsonProperty("EndDate")
	private String endDate;
	
	@JsonProperty("ProductId")
	private String productId;
}
