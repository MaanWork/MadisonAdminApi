package com.madison.motor.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

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
@Entity
@Table(name = "OPEN_COVER_MASTER")
@IdClass(OpenCoverMasterId.class)
@DynamicInsert
@DynamicUpdate

public class OpenCoverMaster implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//ENTER PRIMERY KEY
	
	@Id
	@Column(name = "PROPOSAL_NO", nullable = false)
	private String proposalno;
	
	@Id
	@Column(name = "AMEND_ID", nullable = false)
	private Long amendId;
	
	//ENTER DATA FEILD
	
	@Column(name = "PRODUCT_ID")
	private Long productId;
	
	@Column(name = "BROKER_ID")
	private String brokerId;
	
	@Column(name = "BROKER_USER_ID")
	private String brokeruserId;
	
	@Column(name = "CUSTOMER_ID")
	private Long customerId;
	
	@Column(name = "BRANCH_CODE", nullable = false)
	private String branchCode;
	
	@Column(name = "POLICY_START_DATE")
	private Date policystartdate;
	
	@Column(name = "POLICY_END_DATE")
	private Date policyenddate;
	
	@Column(name = "CROSS_VOYAGE")
	private String crossvoyage;
	
	@Column(name = "CROSS_VOYAGE_TURNOVER")
	private Long crossvoyageturnover;
	
	@Column(name = "CROSS_VOYAGE_SUMINSURED_LIMIT")
	private Long crossvoyagesuminsuredlimit;
	
	@Column(name = "BACK_DATE_DAYS")
	private Long backdatedays;
	
	@Column(name = "COMMISSION")
	private Long commission;
	
	@Column(name = "FREE_TEXT_ALLOWED")
	private String freetextallowed;
	
	@Column(name = "NO_OF_INSURANCE_COMPANY")
	private Long noofinsurancycompany;
	
	@Column(name = "REMARKS")
	private String remarks;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "CROSS_VOYAGE_PERCENTAGE")
	private Long crossvoyagepercentage;
	
	@Column(name = "EFFECTIVE_DATE")
	private Date effectivedate;
	
	@Column(name = "RSA_SHARED_PERCENTAGE")
	private Long rsasharedpercentage;
	
	@Column(name = "TYPE")
	private String type;
	
	@Column(name = "MIN_PREMIUM")
	private Long minpremium;
	
	@Column(name = "MISSIPPI_CODE")
	private Long missippicode;
	
	@Column(name = "MISSIPPI_OPENCOVER_NO")
	private String missippiopencoverno;
	
	@Column(name = "BUSINESS_TYPE")
	private Long  businesstype;
	
	@Column(name = "MISSISSIPI_OPEN_POLICY_ID")
	private String mississipiopenpolicyno;
	
	@Column(name = "IMPORT_MIN_PREMIUM_AMOUNT")
	private Long importminpremiumamount;
	
	@Column(name = "EXPORT_MIN_PREMIUM_AMOUNT")
	private Long exportminpremiumamount;
	
	@Column(name = "PRO_COMMISSION")
	private Long procommission;
	
	@Column(name = "PRO_START_DATE")
	private Date prostartdate;
	
	@Column(name = "PRO_EXPIRY_DATE")
	private Date proexpirydate;
	
	@Column(name = "WRSC_YN")
	private String wrscyn;
	
	@Column(name = "CROSS_MIN_PREMIUM_AMOUNT")
	private Long crossminpremiumamount;
	
	@Column(name = "COUNTRY_REMARKS")
	private String countryremarks;
	
	@Column(name = "CURRENCY_ID")
	private Long currencyId;
	
	@Column(name = "EXCHANGE_RATE")
	private Long exchangerate;
	
	@Column(name = "FOREIGN_TURNOVER")
	private Long foreignturnover;
	
	@Column(name = "ISSUANCE_FEE")
	private Long issuancefee;
	
	@Column(name = "MIN_PREMIUM_ISSUANCE_FEE")
	private Long minpremiumissuancefee;
	
	@Column(name = "DEPOSIT_PREMIUM_YN")
	private String depositpremiumyn;
	
	@Column(name = "DEPOSIT_TYPE")
	private String deposittype;
	
	@Column(name = "INSTALLMENT_TYPE")
	private String installmenttype;
	
	@Column(name = "DEPOSIT_AMOUNT")
	private Long depositamount;
	
	@Column(name = "DEBIT_NOTE_NO")
	private String debitnoteno;
	
	@Column(name = "DEBIT_NOTE_DATE")
	private Date debitnotedate;
	
	@Column(name = "CREDIT_NOTE_NO")
	private String creditnoteno;
	
	@Column(name = "CREDIT_NOTE_DATE")
	private Date creditnotedate;
	
	@Column(name = "DEBIT_NOTE_NAME")
	private String debitnotename;
	
	@Column(name = "ISSUANCE_FEE_DEBIT")
	private Long issuancefeedebit;
	
	@Column(name = "MIN_PRE_MUL_TYPE")
	private String minpremultype;
	
	@Column(name = "MIN_PRE_MUL")
	private Long minpremul;
	
	@Column(name = "ADDITIONAL_INSURED")
	private String additionalinsured;
	
	@Column(name = "ADDITIONAL_INSURED_NAME")
	private String additionalinsuredname;
	
	@Column(name = "EXISTING_DEPOSIT_YN")
	private String existingdeposityn;
	
	@Column(name = "MARINE_PREMIUM")
	private Long marinepremium;
	
	@Column(name = "WAR_PREMIUM")
	private Long warpremium;
	
	@Column(name = "LOGIN_ID")
	private String loginId;
	
	@Column(name = "BROKER_AR_NO")
	private String brokerarno;
	
	@Column(name = "BROKER_MISSIPPI_ID")
	private String brokermissippiId;
	
	@Column(name = "LOSS_DETAIL")
	private String lossdetail;
	
	@Column(name = "CLAIM_RATIO")
	private String claimratio;
	
	@Column(name = "ADDITIONAL_INFO")
	private String additionalinfo;
	
	@Column(name = "GUEST_LOGIN_STATUS")
	private String guestloginstatus;
	
	@Column(name = "CONFIRM_STATUS")
	private String confirmstatus;
	
	@Column(name = "PROPOSAL_STATUS")
	private String proposalstatus;
	
	@Column(name = "CLAUSES_YN")
	private String clausesyn;
	
	@Column(name = "EXECUTIVE_ID")
	private Long executiveId;
	
	@Column(name = "DEBIT_NOTE")
	private String debitnote;
	
	@Column(name = "UTILIZED_AMOUNT")
	private String utilizedamount;
	
	@Column(name = "DECLARATIONTYPE")
	private String declarationtype;
	
	@Column(name = "NO_OF_VEHICLES")
	private Long noofvehicles;
	
	@Column(name = "HAULIER_PREMIUM")
	private Long haulierpremium;
	
	@Column(name = "DEBIT_TO_ID")
	private String debittoId;
	
	@Column(name = "CREDIT_TO_ID")
	private String credittoId;
	
}
