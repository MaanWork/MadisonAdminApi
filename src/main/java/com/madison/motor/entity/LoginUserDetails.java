/* 
*  Copyright (c) 2019. All right reserved
 * Created on 2023-02-13 ( Date ISO 2023-02-13 - Time 15:54:43 )
 * Generated by Telosys Tools Generator ( version 3.3.0 )
 */

/*
 * Created on 2023-02-13 ( 15:54:43 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.3.0
 */


package com.madison.motor.entity;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;




/**
* Domain class for entity "LoginUserDetails"
*
* @author Telosys Tools Generator
*
*/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@DynamicInsert
@DynamicUpdate
@Builder
@IdClass(LoginUserDetailsId.class)
@Table(name="LOGIN_USER_DETAILS")


public class LoginUserDetails implements Serializable {
 
private static final long serialVersionUID = 1L;
 
    //--- ENTITY PRIMARY KEY 
    @Id
    @Column(name="USER_ID", nullable=false)
    private BigDecimal userId ;

    @Id
    @Column(name="PRODUCT_ID", nullable=false)
    private BigDecimal productId ;

    //--- ENTITY DATA FIELDS 
    @Column(name="LOGIN_ID", nullable=false, length=50)
    private String     loginId ;

    @Column(name="USER_NAME", length=100)
    private String     userName ;

    @Column(name="COMPANY_ID", nullable=false, length=10)
    private String     companyId ;

    @Column(name="AGENCY_CODE", nullable=false, length=25)
    private String     agencyCode ;

    @Column(name="OA_CODE", length=25)
    private String     oaCode ;

    @Column(name="COMMISSION")
    private BigDecimal commission ;

    @Column(name="INSURANCE_START_LIMIT")
    private BigDecimal insuranceStartLimit ;

    @Column(name="INSURANCE_END_LIMIT")
    private BigDecimal insuranceEndLimit ;

    @Column(name="SPECIAL_DISCOUNT")
    private BigDecimal specialDiscount ;

    @Temporal(TemporalType.DATE)
    @Column(name="ENTRY_DATE")
    private Date       entryDate ;

    @Column(name="RELATIVE_USER_ID")
    private BigDecimal relativeUserId ;

    @Temporal(TemporalType.DATE)
    @Column(name="EXPIRY_DATE")
    private Date       expiryDate ;

    @Column(name="STATUS", length=1)
    private String     status ;

    @Column(name="AMEND_ID", nullable=false)
    private BigDecimal amendId ;

    @Column(name="REMARKS", length=100)
    private String     remarks ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="INCEPTION_DATE")
    private Date       inceptionDate ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="EFFECTIVE_DATE")
    private Date       effectiveDate ;

    @Column(name="CUSTOMER_ID")
    private BigDecimal customerId ;

    @Column(name="MIN_PREMIUM_AMOUNT")
    private BigDecimal minPremiumAmount ;

    @Column(name="BACK_DATE_ALLOWED")
    private BigDecimal backDateAllowed ;

    @Column(name="LOADING_OF_PREMIUM")
    private BigDecimal loadingOfPremium ;

    @Column(name="PROVISION_FOR_PREMIUM", length=1)
    private String     provisionForPremium ;

    @Column(name="DISCOUNT_OF_PREMIUM")
    private BigDecimal discountOfPremium ;

    @Column(name="FREIGHT_RATE_OPTION", length=1)
    private String     freightRateOption ;

    @Column(name="FREIGHT_SCHEDULE_OPTION", length=1)
    private String     freightScheduleOption ;

    @Column(name="CUSTOMER_QUOTE", length=15)
    private String     customerQuote ;

    @Column(name="CUSTOMER_SCHEDULE", length=1)
    private String     customerSchedule ;

    @Column(name="CUSTOMER_DEBIT", length=1)
    private String     customerDebit ;

    @Column(name="CUSTOMER_CUSTOMERDEBIT", length=1)
    private String     customerCustomerdebit ;

    @Column(name="CUSTOMER_POLICY", length=1)
    private String     customerPolicy ;

    @Column(name="IMPORT_MIN_PREMIUM_AMOUNT")
    private BigDecimal importMinPremiumAmount ;

    @Column(name="EXPORT_MIN_PREMIUM_AMOUNT")
    private BigDecimal exportMinPremiumAmount ;

    @Column(name="FREIGHT_DEBIT_OPTION", length=1)
    private String     freightDebitOption ;

    @Column(name="PRO_COMMISSION")
    private BigDecimal proCommission ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="PRO_START_DATE")
    private Date       proStartDate ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="PRO_EXPIRY_DATE")
    private Date       proExpiryDate ;

    @Column(name="FREIGHT_ADMIN_OPTION", length=1)
    private String     freightAdminOption ;

    @Column(name="FREIGHT_AUOTO_OPTION", length=1)
    private String     freightAuotoOption ;

    @Column(name="PAY_RECEIPT_STATUS", length=1)
    private String     payReceiptStatus ;

    @Column(name="OPEN_COVER_NO", length=1000)
    private String     openCoverNo ;

    @Column(name="SCHEME_ID")
    private BigDecimal schemeId ;

    @Column(name="CUSTOMER_CERTIFICATE", length=1)
    private String     customerCertificate ;

    @Column(name="FD_CODE", length=25)
    private String     fdCode ;

    @Column(name="MEMBERSHIP_STATUS", length=1)
    private String     membershipStatus ;

    @Column(name="RECEIPT_STATUS", length=1)
    private String     receiptStatus ;

    @Column(name="ENDORSEMENT_STATUS", length=1)
    private String     endorsementStatus ;

    @Column(name="RENEWAL_STATUS", length=1)
    private String     renewalStatus ;

    @Column(name="POL_CUSTOMER_ID")
    private BigDecimal polCustomerId ;

    @Column(name="PROVISION_FOR_RATE", length=1)
    private String     provisionForRate ;

    @Column(name="MIN_DISCOUNT")
    private BigDecimal minDiscount ;

    @Column(name="MAX_DISCOUNT")
    private BigDecimal maxDiscount ;

    @Column(name="MIN_LOADING")
    private BigDecimal minLoading ;

    @Column(name="MAX_LOADING")
    private BigDecimal maxLoading ;

    @Column(name="VOLUME_DISCOUNT_LIMIT")
    private BigDecimal volumeDiscountLimit ;

    @Column(name="CORPORATE_DISCOUNT_LIMIT")
    private BigDecimal corporateDiscountLimit ;

    @Column(name="SPECIAL_DISCOUNT_LIMIT")
    private BigDecimal specialDiscountLimit ;

    @Column(name="ISSUERTYPE", length=10)
    private String     issuertype ;

    @Column(name="IS_B2C", length=10)
    private String     isB2c ;

    @Column(name="PAYMENT_TYPE", length=100)
    private String     paymentType ;


    //--- ENTITY LINKS ( RELATIONSHIP )

  //  @ManyToMany()
  //  private List<ProductMaster> productMaster ;

}



