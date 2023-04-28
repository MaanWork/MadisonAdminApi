package com.madison.motor.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PremiumRes {
	
	@JsonProperty("SumInsured")
	private String sumInsured;
	@JsonProperty("Rate")
	private String rate;
	@JsonProperty("Premium")
	private String premium;
	@JsonProperty("CalcType")
	private String calcType;
	@JsonProperty("VehicleId")
	private String vehicleId;
	@JsonProperty("GroupId")
	private String groupId;
	@JsonProperty("PolicyTypeCoverId")
	private String policyTypeCoverId;
	@JsonProperty("Description")
	private String description;
	@JsonProperty("OpcoverDesc")
	private String opcoverDesc;

}
