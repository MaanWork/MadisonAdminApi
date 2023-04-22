package com.madison.motor.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MadisonCommonRes {
	
	
	@JsonProperty("Response")
	private Object response;
	
	@JsonProperty("ResponseStatus")
	private Object responseStatus;
	
	@JsonProperty("Errors")
	private Object errors;

}
