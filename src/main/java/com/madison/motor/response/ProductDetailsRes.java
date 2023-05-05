package com.madison.motor.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailsRes {
	
	@JsonProperty("ProductId")
	private String productId;
	@JsonProperty("ProductName")
	private String productName;
	@JsonProperty("SpecialDiscount")
	private String specialDiscount;
	@JsonProperty("SumInsured")
	private String sumInsured;
	@JsonProperty("Status")
	private String status;
	@JsonProperty("CreditYn")
	private String creditYn;
	

}
