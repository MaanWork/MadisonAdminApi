package com.madison.motor.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GetPortFolioReq {
	
	@JsonProperty("EntryDate")
	private String entryDate;
	@JsonProperty("BranchCode")
	private String branchCode;
	@JsonProperty("Type")
	private String type;
	@JsonProperty("ProductId")
	private String productId;

}
