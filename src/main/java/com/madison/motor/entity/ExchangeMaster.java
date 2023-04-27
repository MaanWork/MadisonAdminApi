package com.madison.motor.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

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
@Entity
@Table(name = "EXCHANGE_MASTER")
@IdClass(ExchangeMasterId.class)
public class ExchangeMaster implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	// PRIMERY KEY
	@Id()
	@Column(name = "SNO__")
	private Long sno;
	
	//ENTERING INTO COLUMN'S
	
	@Column(name = "EXCHANGE_ID")
	private Long exchId;
	
	@Column(name = "EXCHANGE_RATE")
	private Long exchRate;
	
	@Column(name = "CURRENCY_ID")
	private Long currencyId;
	
	@Column(name = "AMEND_ID")
	private Long amendid;
	
	@Column(name = "INCEPTION_DATE")
	private Date incepDate;
	
	@Column(name = "EXPIRY_DATE")
	private Date expirydate;
	
	@Column(name = "EFFECTIVE_DATE")
	private Date effectivedate;
	
	@Column(name = "ENTRY_DATE")
	private Date entrydate;
	
	@Column(name = "REMARKS")
	private String remarks;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "RSACODE")
	private String rsacode;
	
	@Column(name = "COUNTRY_ID")
	private String countryId;
	
	@Column(name = "DISPLAY_ORDER")
	private Long displayorder;
	
	@Column(name = "CURRENCY_TYPE")
	private String currencytype;
	
	@Column(name = "SUB_CURRENCY")
	private String subcurrency;

}
