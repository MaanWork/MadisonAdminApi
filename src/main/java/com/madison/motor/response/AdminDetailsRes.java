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
public class AdminDetailsRes {
	
	@JsonProperty("LoginId")
	private String loginId;
	@JsonProperty("UserName")
	private String userName;
	@JsonProperty("UserType")
	private String userType;
	@JsonProperty("Status")
	private String status;
	@JsonProperty("BranchName")
	private String branchName;
}
