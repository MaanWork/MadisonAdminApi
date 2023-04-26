package com.madison.motor.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReferalSearchQuoteRes {
	
	
	@JsonProperty("EntryDate")
	private String entryDate;
	@JsonProperty("TotalRecordsCount")
	private String totalRecordsCount;

}
