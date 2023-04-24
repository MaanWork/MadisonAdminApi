/* 
*  Copyright (c) 2019. All right reserved
 * Created on 2023-02-13 ( Date ISO 2023-02-13 - Time 15:59:32 )
 * Generated by Telosys Tools Generator ( version 3.3.0 )
 */

/*
 * Created on 2023-02-13 ( 15:59:32 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.3.0
 */


package com.madison.motor.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Table;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.*;




/**
* Domain class for entity "ProductMaster"
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
@Table(name="PRODUCT_MASTER")


public class ProductMaster implements Serializable {
 
private static final long serialVersionUID = 1L;
 
    //--- ENTITY PRIMARY KEY 
    @Id
    @Column(name="PRODUCT_ID", nullable=false)
    private BigDecimal productId ;

    //--- ENTITY DATA FIELDS 
    @Column(name="PRODUCT_NAME", length=50)
    private String     productName ;

    @Column(name="AMEND_ID", nullable=false)
    private BigDecimal amendId ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="INCEPTION_DATE")
    private Date       inceptionDate ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="EXPIRY_DATE")
    private Date       expiryDate ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="EFFECTIVE_DATE", nullable=false)
    private Date       effectiveDate ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="ENTRY_DATE")
    private Date       entryDate ;

    @Column(name="COMPANY_ID", nullable=false)
    private BigDecimal companyId ;

    @Column(name="REMARKS", length=100)
    private String     remarks ;

    @Column(name="STATUS", length=1)
    private String     status ;

    @Column(name="RSACODE", length=10)
    private String     rsacode ;

    @Column(name="BRANCH_CODE", nullable=false, length=8)
    private String     branchCode ;

    @Column(name="DISPLAY_ORDER")
    private BigDecimal displayOrder ;

    @Column(name="PRODUCT_CATEGORY", length=25)
    private String     productCategory ;

    @Column(name="COREAPPCODE")
    private BigDecimal coreappcode ;

    @Column(name="PAYMENT_REDIR_URL", length=1000)
    private String     paymentRedirUrl ;

    @Column(name="APP_LOGIN_URL", length=100)
    private String     appLoginUrl ;


 /*   //--- ENTITY LINKS ( RELATIONSHIP )
    @OneToMany(mappedBy="productMaster")
    private List<MotorDataDetail> listOfMotorDataDetail ; 

    @OneToMany(mappedBy="productMaster")
    private List<HomePositionMaster> listOfHomePositionMaster ; */



}


