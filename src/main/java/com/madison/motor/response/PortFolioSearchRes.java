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
public class PortFolioSearchRes {
	
	
	@JsonProperty("EntryDate")
	private String entryDate;
	@JsonProperty("TotalRecordsCount")
	private String totalRecordsCount;
	@JsonProperty("ProductId")
	private String ProductId;

}
