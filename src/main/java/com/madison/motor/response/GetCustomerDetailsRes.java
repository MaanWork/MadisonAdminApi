package com.madison.motor.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GetCustomerDetailsRes {

	@JsonProperty("Companyname")
	private String companyname;
	
	@JsonProperty("CustomerloginId")
	private String customerloginId;
	
	@JsonProperty("Brokername")
	private String brokername;
	
	@JsonProperty("EntryDate")
	private String entryDate;
	
	@JsonProperty("AgencyCode")
	private String agencyCode;
	
	@JsonProperty("FdCode")
	private String fdCode;
	
	@JsonProperty("Pobox")
	private String pobox;
	
	@JsonProperty("Mobile")
	private String mobile;
	
	@JsonProperty("Cityname")
	private String cityname;
	
	@JsonProperty("CustomerId")
	private String customerId;
	
	@JsonProperty("MissippiCustomerCode")
	private String missippiCustomerCode;
	
	@JsonProperty("Title")
	private String title;
	
	@JsonProperty("Address1")
	private String address1;
	
	@JsonProperty("Address2")
	private String address2;
	
	@JsonProperty("CustArNo")
	private String custArNo;
	
	@JsonProperty("Address")
	private String address;
}
