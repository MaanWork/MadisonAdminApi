package com.madison.motor.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EditForeignExchangeRes {
	
	@JsonProperty("ExchangeId")
	private String exchangeId;
	
	@JsonProperty("ExchangeRate")
	private String exchangeRate;
	
	@JsonProperty("Currencyid")
	private String currencyid;
	
	@JsonProperty("Currencyname")
	private String currencyname;
	
	@JsonProperty("Amendid")
	private String amendid;
	
	@JsonProperty("Inceptiondate")
	private String inceptiondate;
	
	@JsonProperty("Expirydate")
	private String expirydate;
	
	@JsonProperty("Effectivedate")
	private String effectivedate;
	
	@JsonProperty("Entrydate")
	private String entrydate;
	
	@JsonProperty("Remarks")
	private String remarks;
	
	@JsonProperty("Status")
	private String status;
	
	@JsonProperty("Rsacode")
	private String rsacode;
	
	@JsonProperty("Sno")
	private String sno;
	
	@JsonProperty("Countryid")
	private String countryid;
	
	@JsonProperty("Countryname")
	private String countryname;
	
	@JsonProperty("Displayorder")
	private String displayorder;

}
