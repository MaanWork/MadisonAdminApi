package com.madison.motor.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoadSideAssistantDetailsId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//PRIMARY KEY
	
	private Long refNo;

}
