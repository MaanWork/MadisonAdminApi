package com.madison.motor.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ModifyRateReq {
	
	@JsonProperty("ApplicationNo")
	private String applicationNo;
	@JsonProperty("VehicleId")
	private String vehicleId;
	@JsonProperty("CurrencyType")
	private String currencyType;
	@JsonProperty("ModifyRate")
	private List<ModifyRateDetReq> modifyRate;
}
