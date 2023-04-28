package com.madison.motor.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryMasterId implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3537959716101080753L;

	private Long countryId;
	
	private Long amendId;

}
