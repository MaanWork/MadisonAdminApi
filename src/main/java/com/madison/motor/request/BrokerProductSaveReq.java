package com.madison.motor.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class BrokerProductSaveReq {
	
	@JsonProperty("UserId")
	private String userId;
	@JsonProperty("ProductId")
	private String productId;
	@JsonProperty("SumInsured")
	private String sumInsured;
	@JsonProperty("Commission")
	private String commission;
	@JsonProperty("MinimumPre")
	private String minimumPre;
	@JsonProperty("EffectiveDate")
	private String effectiveDate;
	@JsonProperty("EndDate")
	private String endDate;
	@JsonProperty("AgencyCode")
	private String agencyCode;
	@JsonProperty("BackDateAllowed")
	private String backDateAllowed;
	@JsonProperty("Status")
	private String status;
	@JsonProperty("PolicyType")
	private List<PolicyTypeReq> policyType;

}
