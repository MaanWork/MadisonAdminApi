package com.madison.motor.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AdminSaveReq {
	
	@JsonProperty("FirstName")
	private String firstName;
	@JsonProperty("LastName")
	private String lastName;
	@JsonProperty("EmailId")
	private String Emailid;
	@JsonProperty("AttachedBranch")
	private String attachedBranch;
	@JsonProperty("MiddleName")
	private String middleName;
	@JsonProperty("Status")
	private String Status;
	@JsonProperty("UserType")
	private String userType;
	@JsonProperty("LoginId")
	private String loginId;
	@JsonProperty("Password")
	private String password;
	@JsonProperty("ProductId")
	private String productId;
	@JsonProperty("OnlineYn")
	private String onlineYn;
	@JsonProperty("MenuId")
	private String menuId;
	@JsonProperty("MobileNo")
	private String mobileNo;
	@JsonProperty("BrokerCode")
	private String brokerCode;
	@JsonProperty("CountryId")
	private String countryId;
	@JsonProperty("BranchCode")
	private String branchCode;
	@JsonProperty("AttachedUw")
	private String attachedUw;

}
