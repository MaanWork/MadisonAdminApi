package com.madison.motor.request;

import java.io.File;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class BrokerSaveReq {
	
	@JsonProperty("CustomerId")
	private String customerId;
	@JsonProperty("CoreCustomerCode")
	private String coreCustomerCode;
	@JsonProperty("LoginId")
	private String loginId;
	@JsonProperty("AgencyCode")
	private String agencyCode;
	@JsonProperty("Title")
	private String title;
	@JsonProperty("FirstName")
	private String firstName;
	@JsonProperty("LastName")
	private String lastName;
	@JsonProperty("Nationality")
	private String nationality;
	@JsonProperty("DateOfBirth")
	private String dateOfBirth;
	@JsonProperty("Gender")
	private String gender;
	@JsonProperty("PhoneNo")
	private String phoneNo;
	@JsonProperty("MobileNo")
	private String mobileNo;
	@JsonProperty("Fax")
	private String fax;
	@JsonProperty("EmailId")
	private String emailId;
	@JsonProperty("Address1")
	private String address1;
	@JsonProperty("Address2")
	private String address2;
	@JsonProperty("Occupation")
	private String occupation;
	@JsonProperty("PoBox")
	private String poBox;
	@JsonProperty("CountryId")
	private String countryId;
	@JsonProperty("CityId")
	private String cityId;
	@JsonProperty("UserId")
	private String userId;
	@JsonProperty("Password")
	private String password;
	@JsonProperty("ConfirmPassword")
	private String confirmPassword;
	@JsonProperty("UserType")
	private String userType;
	@JsonProperty("IsB2c")
	private String isB2c;
	@JsonProperty("BranchCode")
	private String branchCode;
	@JsonProperty("SubBranchId")
	private String subBranchId;
	@JsonProperty("Status")
	private String status;
	@JsonProperty("BrokerCode")
	private String brokerCode;
	@JsonProperty("AttachedBranch")
	private String attachedBranch;
	@JsonProperty("CustArNo")
	private String custArNo;
	@JsonProperty("BrokerOrganization")
	private String brokerOrganization;
	@JsonProperty("OtherCity")
	private String otherCity;
	@JsonProperty("ApprovedBy")
	private String approvedBy;
	@JsonProperty("ExeutiveBDM")
	private String exeutiveBDM;
	@JsonProperty("File")
	private File file;;
	
}
