package com.madison.motor.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EditProductRes {
	
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
	private List<PolicyTypeRes> policyType;

}
