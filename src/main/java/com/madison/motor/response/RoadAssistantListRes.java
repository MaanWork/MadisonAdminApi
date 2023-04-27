package com.madison.motor.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RoadAssistantListRes {
	
	@JsonProperty("RefNo")
	private String refNo;
	
	@JsonProperty("MobileNo")
	private String mobileNo;
	
	@JsonProperty("PolicyNo")
	private String policyNo;
	
	@JsonProperty("CustomerName")
	private String customerName;
	
	@JsonProperty("AssistantType")
	private String assistantType;
	
	@JsonProperty("Description")
	private String description;
	
	@JsonProperty("Longitude")
	private String longitude;
	
	@JsonProperty("Latitude")
	private String latitude;
	
	@JsonProperty("DeviceId")
	private String deviceId;
	
	@JsonProperty("EntryDate")
	private String entryDate;
	
	@JsonProperty("Location")
	private String location;
	
	@JsonProperty("Status")
	private String status;
	
	@JsonProperty("Remarks")
	private String remarks;
	
	@JsonProperty("MailId")
	private String mailId;
	
	@JsonProperty("UpdateDate")
	private String updateDate;
	
	@JsonProperty("CustomerFeedback")
	private String customerFeedback;
	
}
