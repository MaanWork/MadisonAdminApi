package com.madison.motor.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrencyMasterId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//ENTER INTO PRIMARY_KEY
	private Long currencyid;
	
	private Long amendid;
	
	private Long countryid;

}
