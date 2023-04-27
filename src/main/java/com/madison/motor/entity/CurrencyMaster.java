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
@Table(name = "CURRENCY_MASTER")
@IdClass(CurrencyMasterId.class)
public class CurrencyMaster implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//ENTER INTO PRIMERY_KEY
	
	@Id
	@Column(name = "CURRENCY_ID", nullable=false, length=15)
	private Long currencyid;
	
	@Id
	@Column(name = "AMEND_ID", nullable=false, length=10)
	private Long amendid;
	
	@Id
	@Column(name = "COUNTRY_ID", nullable=false, length=15)
	private Long countryid;
	
	//ENTER INTO TABLE COLUMN'S
	
	@Column(name = "CURRENCY_NAME")
	private String currencyname;
	
	@Column(name = "INCEPTION_DATE")
	private Date inceptiondate;
	
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
	
	@Column(name = "RSACODE", nullable = false)
	private String rsacode;
	
	@Column(name = "SNO__")
	private Long sno;
	
	@Column(name = "DISPLAY_ORDER")
	private Long displayorder;
	
	@Column(name = "SHORT_NAME")
	private String shortname;
	
	@Column(name = "RFACTOR")
	private Long rfactor;
	
	@Column(name = "SUB_CURRENCY")
	private String sbcurrency;
	
	@Column(name = "EX_MINLMT")
	private Long exminlmt;
	
	@Column(name = "EX_MAXLMT")
	private Long exmaxlmt;

}
