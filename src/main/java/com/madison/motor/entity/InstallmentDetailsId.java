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
public class InstallmentDetailsId implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//ENTER INTO PRIMERY KEY
	
	private Long quoteNo;
	
	private Long applicationNo;
	
	private Long installmentNo;
}
