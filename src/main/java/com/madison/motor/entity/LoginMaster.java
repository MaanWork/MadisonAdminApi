/* 
*  Copyright (c) 2019. All right reserved
 * Created on 2023-02-13 ( Date ISO 2023-02-13 - Time 15:54:38 )
 * Generated by Telosys Tools Generator ( version 3.3.0 )
 */

/*
 * Created on 2023-02-13 ( 15:54:38 )
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
import javax.persistence.*;




/**
* Domain class for entity "LoginMaster"
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
@IdClass(LoginMasterId.class)
@Table(name="LOGIN_MASTER")


public class LoginMaster implements Serializable {
 
private static final long serialVersionUID = 1L;
 
    //--- ENTITY PRIMARY KEY 
    @Id
    @Column(name="LOGIN_ID", nullable=false, length=50)
    private String     loginId ;

    @Id
    @Column(name="AGENCY_CODE", nullable=false, length=25)
    private String     agencyCode ;

    //--- ENTITY DATA FIELDS 
    @Column(name="PASSWORD", nullable=false, length=50)
    private String     password ;

    @Column(name="USERTYPE", nullable=false, length=100)
    private String     usertype ;

    @Column(name="USERNAME", length=100)
    private String     username ;

    @Column(name="USERID")
    private BigDecimal userid ;

    @Column(name="OA_CODE", length=25)
    private String     oaCode ;

    @Column(name="ACCESSTYPE", length=15)
    private String     accesstype ;

    @Column(name="RIGHTS", length=100)
    private String     rights ;

    @Column(name="LPASS1", length=50)
    private String     lpass1 ;

    @Column(name="LPASS2", length=50)
    private String     lpass2 ;

    @Column(name="LPASS3", length=50)
    private String     lpass3 ;

    @Temporal(TemporalType.DATE)
    @Column(name="PASSDATE")
    private Date       passdate ;

    @Column(name="COMPANY_ID", nullable=false, length=10)
    private String     companyId ;

    @Column(name="CREATED_BY", length=30)
    private String     createdBy ;

    @Column(name="USER_ID_CREATION", length=1)
    private String     userIdCreation ;

    @Column(name="AC_EXECUTIVE_CREATION", length=1)
    private String     acExecutiveCreation ;

    @Column(name="REMARKS", length=100)
    private String     remarks ;

    @Column(name="STATUS", nullable=false, length=1)
    private String     status ;

    @Column(name="REFERAL", length=25)
    private String     referal ;

    @Column(name="REGION_CODE", nullable=false, length=8)
    private String     regionCode ;

    @Column(name="STATE_CODE", length=8)
    private String     stateCode ;

    @Column(name="BRANCH_CODE", nullable=false, length=20)
    private String     branchCode ;

    @Column(name="COUNTRY_ID")
    private BigDecimal countryId ;

    @Column(name="LPASS4", length=50)
    private String     lpass4 ;

    @Column(name="LPASS5", length=50)
    private String     lpass5 ;

    @Column(name="MENU_ID", length=500)
    private String     menuId ;

    @Column(name="PRODUCT_ID", length=100)
    private String     productId ;

    @Temporal(TemporalType.DATE)
    @Column(name="ENTRY_DATE")
    private Date       entryDate ;

    @Temporal(TemporalType.DATE)
    @Column(name="INCEPTION_DATE")
    private Date       inceptionDate ;

    @Temporal(TemporalType.DATE)
    @Column(name="EXPIRY_DATE")
    private Date       expiryDate ;

    @Column(name="BROKER_CODES", length=2000)
    private String     brokerCodes ;

    @Column(name="FD_CODE", length=25)
    private String     fdCode ;

    @Column(name="CORE_LOGIN_ID", nullable=false)
    private BigDecimal coreLoginId ;

    @Column(name="APP_ID", length=50)
    private String     appId ;

    @Column(name="PWD_COUNT", length=50)
    private String     pwdCount ;

    @Column(name="USER_MAIL", length=50)
    private String     userMail ;

    @Column(name="ATTACHED_UW", length=200)
    private String     attachedUw ;

    @Column(name="ATTACHED_BRANCH", length=100)
    private String     attachedBranch ;

    @Column(name="MOBILE_NO", length=20)
    private String     mobileNo ;

    @Column(name="RESTICT_IP_STATUS", length=100)
    private String     restictIpStatus ;

    @Column(name="ONLINE_YN", length=5)
    private String     onlineYn ;

    @Column(name="ALLOWED_IP_ADDR", length=100)
    private String     allowedIpAddr ;

    @Column(name="EMPLOYE_CODE", length=50)
    private String     employeCode ;

    @Column(name="MAIL_LOGIN", length=50)
    private String     mailLogin ;

    @Column(name="MOBILE_NUMBER", length=100)
    private String     mobileNumber ;

    @Column(name="SUMINSURED_START")
    private BigDecimal suminsuredStart ;

    @Column(name="FIRST_NAME", length=35)
    private String     firstName ;

    @Column(name="MIDDLE_NAME", length=30)
    private String     middleName ;

    @Column(name="SUMINSURED_END")
    private BigDecimal suminsuredEnd ;

    @Column(name="SUB_USER_TYPE", length=50)
    private String     subUserType ;

    @Column(name="LAST_NAME", length=30)
    private String     lastName ;

    @Column(name="WAJAH_YN", length=10)
    private String     wajahYn ;

    @Column(name="IS_B2C", length=10)
    private String     isB2c ;

    @Column(name="SHORT_URL", length=1000)
    private String     shortUrl ;

    @Column(name="ENCRYPT_PASSWORD", length=1000)
    private String     encryptPassword ;

    @Column(name="TOKEN_PASSWORD", length=2000)
    private String     tokenPassword ;

    @Column(name="SUB_BRANCH", length=100)
    private String     subBranch ;

    @Column(name="LOSSTYPEIDLIST", length=300)
    private String     losstypeidlist ;


    //--- ENTITY LINKS ( RELATIONSHIP )


}


