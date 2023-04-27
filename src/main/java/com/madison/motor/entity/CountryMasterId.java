package com.madison.motor.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CountryMasterId implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//ENTER INTO PRIMARY KEY
	private Long countryid;
	
	private Long amendid;

}
