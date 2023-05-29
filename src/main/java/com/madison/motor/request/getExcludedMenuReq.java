package com.madison.motor.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class getExcludedMenuReq {

	@JsonProperty("SelProducts")
	private String selProducts;
	
	@JsonProperty("BranchCode")
	private String branchCode;
	
}
