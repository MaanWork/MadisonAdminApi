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

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "OPEN_COVER_POSITION_MASTER")
@IdClass(OpenCoverPositionMasterId.class)

public class OpenCoverPositionMaster implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//ENTER PRIMARY KEY
	
	@Id
	@Column(name = "PROPOSAL_NO", nullable = false)
	private String proposalno;
	
	//ENTER DATA FIELD
	
	@Column(name = "OPEN_COVER_NO")
	private String opencoverno;
	
	@Column(name = "INCEPTION_DATE")
	private Date inceptiondate;
	
	@Column(name = "EXPIRY_DATE")
	private Date expirydate;
	
	@Column(name = "EFFECTIVE_DATE")
	private Date effectivedate;
	
	@Column(name = "ENTRY_DATE")
	private Date entrydate;
	
	@Column(name = "AMEND_ID", nullable = false)
	private Long amendId;
	
	@Column(name = "REMARKS")
	private String remarks;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "CUSTOMER_SCHEDULE")
	private String customerschedule;
	
	@Column(name = "CUSTOMER_DEBIT")
	private String customerdebit;
	
	@Column(name = "CUSTOMER_CUSTOMERDEBIT")
	private String customercustomerdebit;
	
	@Column(name = "ADMIN_STATUS")
	private String adminstatus;
	
	@Column(name = "BRANCH_CODE")
	private String branchCode;
	
	@Column(name = "RATE_PRINT_STATUS")
	private String rateprintstatus;
	
	@Column(name = "CANCELLATION_CLAUSE")
	private String cancellationclause;
	
	@Column(name = "AMENDED_CLAUSE_PRINT_STATUS")
	private String amendedclauseprintstatus;
	
	@Column(name = "RENEWAL_STATUS")
	private String renewalstatus;
	
	@Column(name = "RENEWAL_ERROR")
	private String renewalerror;
	
	@Column(name = "CKEY")
	private String ckey;
	
	@Column(name = "INTEGRATION_STATUS")
	private String integrationstatus;
	
	@Column(name = "INTEGRATION_ERROR")
	private String integrationerror;
	
	@Column(name = "ENDT_STATUS")
	private String endtstatus;
	
	@Column(name = "ORIGINAL_POLICY_NO")
	private String originalpolicyno;
	
	@Column(name = "ENDT_TYPE")
	private String endttype;
	
	@Column(name = "CERT_NO")
	private Long certno;
	
}
