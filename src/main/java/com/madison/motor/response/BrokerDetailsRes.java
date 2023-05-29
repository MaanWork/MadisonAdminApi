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
public class BrokerDetailsRes {
	
	@JsonProperty("CustomerId")
	private String customerId;
	@JsonProperty("ContactPerson")
	private String contactPerson;
	@JsonProperty("AgencyCode")
	private String agencyCode;
	@JsonProperty("RsaBrokerCode")
	private String rsaBrokerCode;
	@JsonProperty("Status")
	private String status;
	@JsonProperty("LoginId")
	private String loginId;
	@JsonProperty("CompanyName")
	private String companyName;
	@JsonProperty("CreatedDate")
	private String createdDate;

}
