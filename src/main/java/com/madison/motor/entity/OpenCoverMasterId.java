package com.madison.motor.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OpenCoverMasterId implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// ENTER PRIMERY KEY 
	
	private String proposalno;
	
	private Long amendId;
}
