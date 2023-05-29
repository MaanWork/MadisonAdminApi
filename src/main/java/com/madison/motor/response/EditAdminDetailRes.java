package com.madison.motor.response;

import lombok.Builder;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EditAdminDetailRes {
	
	@JsonProperty("LoginId")
	private String loginId;
	@JsonProperty("UserName")
	private String userName;
	@JsonProperty("OnlineYn")
	private String onlineYn;
	@JsonProperty("UserType")
	private String userType;
	@JsonProperty("Status")
	private String status;
	@JsonProperty("BranchName")
	private String branchName;
	@JsonProperty("MobileNo")
	private String mobileNo;
	@JsonProperty("BranchCode")
	private String branchCode;
	@JsonProperty("ProductId")
	private String productId;
	@JsonProperty("MenuId")
	private String menuId;
	@JsonProperty("BrokerCode")
	private String brokerCode;
	@JsonProperty("UserMail")
	private String UserMail;
	@JsonProperty("AttachedUw")
	private String attachedUw;
	@JsonProperty("AttachedBranch")
	private String attachedBranch;
	@JsonProperty("FirstName")
	private String firstName;
	@JsonProperty("LastName")
	private String lastName;
	@JsonProperty("MiddleName")
	private String middleName;
	@JsonProperty("UsertypeId")
	private String usertypeId;
	
	
	

}
