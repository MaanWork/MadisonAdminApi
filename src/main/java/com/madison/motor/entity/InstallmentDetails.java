package com.madison.motor.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Builder
@Table(name = "INSTALLMENT_DETAILS")
@IdClass(InstallmentDetailsId.class)
@ToString
public class InstallmentDetails implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//ENTER INTO PRIMERY KEY
	
	@Id
	@Column(name = "QUOTE_NO")
	private Long quoteNo;
	
	@Id
	@Column(name = "APPLICATION_NO")
	private Long applicationNo;
	
	@Id
	@Column(name = "INSTALLMENT_NO")
	private Long installmentNo;
	
	//ENTER INTO TABLE COLUMN'S
	
	@Column(name = "CUST_ID")
	private String custId;
	
	@Column(name = "OVERALL_PREMIUM")
	private Long overallpremium;
	
	@Column(name = "PREMIUM_AMOUNT")
	private Long premiumamount;
	
	@Column(name = "PREMIUM_DATE")
	private Date premiumdate;
	
	@Column(name = "NO_OF_MONTHS")
	private Long noofmonths;
	
	@Column(name = "NO_OF_EMI")
	private Long noofemi;
	
	@Column(name = "NO_OF_TERMS")
	private Long loofterms;
	
	@Column(name = "REMARKS")
	private String remarks;
	
	@Column(name = "CURRENCY_TYPE")
	private String currencyType;
	
	@Column(name = "USD_PREMIUM_AMOUNT")
	private Long usdpremiumamount;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "BALANCE_AMOUNT")
	private Long balanceamount;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "ZMW_PREMIUM_AMOUNT")
	private Long zmwpremiumamount;
	
	@Column(name = "PRODUCT_ID")
	private Long productId;
	
	@Column(name = "PAYMENT_STATUS")
	private String paymentstatus;

}
