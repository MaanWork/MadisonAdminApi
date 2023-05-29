package com.madison.motor.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetAdminProductReq {

	@JsonProperty("BranchCode")
	private String branchCode;
	
	@JsonProperty("AgencyCode")
	private String agencyCode;
	
}
