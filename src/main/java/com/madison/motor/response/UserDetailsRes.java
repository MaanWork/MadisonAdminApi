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
public class UserDetailsRes {
	
	
	@JsonProperty("CustomerName")
	private String customerName;
	@JsonProperty("UserType")
	private String userType;
	@JsonProperty("LoginId")
	private String loginId;
	@JsonProperty("ApplicationId")
	private String applicationId;
	@JsonProperty("AgencyCode")
	private String agencyCode;
	@JsonProperty("Status")
	private String status;
	@JsonProperty("EntryDate")
	private String entryDate;
	@JsonProperty("UserName")
	private String userName;
	@JsonProperty("BrokerName")
	private String brokerName;
	@JsonProperty("OaCode")
	private String oaCode;
	@JsonProperty("CustomerId")
	private String customerId;
	
}
