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
public class EditBrokerRes {
	

	@JsonProperty("Title")
	private String title;
	@JsonProperty("FirstName")
	private String firstName;
	@JsonProperty("LastName")
	private String lastName;
	@JsonProperty("Gender")
	private String gender;
	@JsonProperty("Nationality")
	private String nationality;
	@JsonProperty("DateOfBirth")
	private String dateOfBirth;
	@JsonProperty("TelePhone")
	private String telePhone;
	@JsonProperty("Mobile")
	private String mobile;
	@JsonProperty("Fax")
	private String fax;
	@JsonProperty("Email")
	private String email;
	@JsonProperty("Address1")
	private String address1;
	@JsonProperty("Address2")
	private String address2;
	@JsonProperty("Occupation")
	private String occupation;
	@JsonProperty("Emirate")
	private String emirate;
	@JsonProperty("Country")
	private String country;
	@JsonProperty("PoBox")
	private String poBox;
	@JsonProperty("AgencyCode")
	private String agencyCode;
	@JsonProperty("EntryDate")
	private String entryDate;
	@JsonProperty("Status")
	private String status;
	@JsonProperty("City")
	private String city;
	@JsonProperty("BranchCode")
	private String branchCode;
	@JsonProperty("LoginId")
	private String loginId;
	@JsonProperty("UserType")
	private String userType;
	@JsonProperty("AttachedBranch")
	private String attachedBranch;
	@JsonProperty("SubBranch")
	private String subBranch;
	@JsonProperty("CompanyName")
	private String companyName;
	@JsonProperty("UserName")
	private String userName;
	@JsonProperty("CountryId")
	private String countryId;
	

}
