package com.madison.motor.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GetOpenCoverListRes {

	@JsonProperty("ProposalNo")
	private String proposalNo;
	
	@JsonProperty("OpenCoverNo")
	private String openCoverNo;
	
	@JsonProperty("Status")
	private String status;
	
	@JsonProperty("PolicyStartDate")
	private String policyStartDate;
	
	@JsonProperty("PolicyEndDate")
	private String policyEndDate;
	
	@JsonProperty("Name")
	private String name;
	
	@JsonProperty("Renewalstatus")
	private String renewalstatus;
	
	@JsonProperty("Missippiopencoverno")
	private String missippiopencoverno;
	
}
