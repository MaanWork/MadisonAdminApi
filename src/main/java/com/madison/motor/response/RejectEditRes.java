package com.madison.motor.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RejectEditRes {

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
	
	@JsonProperty("Status")
	private String status;
	
	@JsonProperty("EntryDate")
	private String entryDate;
	
	@JsonProperty("Remarks")
	private String remarks;
	
	@JsonProperty("ClaimId")
	private String claimId;
	
	@JsonProperty("ClaimRef")
	private String claimRef;
	
}
