package com.madison.motor.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehiclePremiumInfoRes {
	
	@JsonProperty("VehicleUsage")
	private String vehicleUsage;
	
	@JsonProperty("Make")
	private String make;
	
	@JsonProperty("Model")
	private String model;
	
	@JsonProperty("TypeOfBody")
	private String typeOfBody;
	
	@JsonProperty("VehcileId")
	private String VehcileId;
	
	@JsonProperty("ApplicationNo")
	private String applicationNo;
	
	@JsonProperty("Premium")
	private List<PremiumRes> premium;

}
