package com.madison.motor.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetCustomerByCustomerIdRes {

	@JsonProperty("Firstname")
	private String firstname;
	
	@JsonProperty("LastName")
	private String lastName;
	
	@JsonProperty("Gender")
	private String gender;
	
	@JsonProperty("Nationality")
	private String nationality;
	
	@JsonProperty("Dob")
	private String dob;
	
	@JsonProperty("Phone")
	private String phone;
	
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
	
	@JsonProperty("Country")
	private String country;
	
	@JsonProperty("Pobox")
	private String pobox;
	
	@JsonProperty("AgencyCode")
	private String agencyCode;
	
	@JsonProperty("City")
	private String city;
}
