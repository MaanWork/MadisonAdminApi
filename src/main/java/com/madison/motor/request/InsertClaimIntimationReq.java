package com.madison.motor.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsertClaimIntimationReq {
	
	@JsonProperty("Name")
	private String name;
	
	@JsonProperty("NrcPassportNo")
	private String nrcPassportNo;
	
	@JsonProperty("PhoneNo")
	private String phoneNo;
	
	@JsonProperty("PolicyNumber")
	private String policyNumber;
	
	@JsonProperty("VehicleRegNo")
	private String vehicleRegNo;
	
	@JsonProperty("DateofAccident")
	private String dateofAccident;
	
	@JsonProperty("ClaimId")
	private String claimId;
	
	@JsonProperty("Status")
	private String status;
	
	@JsonProperty("ClaimRef")
	private String claimRef;
	
	@JsonProperty("Remarks")
	private String remarks;
	
}
