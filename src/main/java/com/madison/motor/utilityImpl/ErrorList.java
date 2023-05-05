package com.madison.motor.utilityImpl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorList {
	
	private String code;
	
	private String key;
	
	private String errorDesc;

}
