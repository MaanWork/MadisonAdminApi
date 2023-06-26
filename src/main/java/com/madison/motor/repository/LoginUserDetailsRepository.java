/*
 * Java domain class for entity "LoginUserDetails" 
 * Created on 2023-02-13 ( Date ISO 2023-02-13 - Time 15:54:44 )
 * Generated by Telosys Tools Generator ( version 3.3.0 )
 */
 
 /*
 * Created on 2023-02-13 ( 15:54:44 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.3.0
 */


package com.madison.motor.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.madison.motor.entity.LoginUserDetails;
import com.madison.motor.entity.LoginUserDetailsId;
/**
 * <h2>LoginUserDetailsRepository</h2>
 *
 * createdAt : 2023-02-13 - Time 15:54:44
 * <p>
 * Description: "LoginUserDetails" Repository
 */
 
 

@Repository
public interface LoginUserDetailsRepository  extends JpaRepository<LoginUserDetails,LoginUserDetailsId > , JpaSpecificationExecutor<LoginUserDetails> {

	@Query(value="SELECT NVL(MAX(USER_ID)+1,'1') FROM LOGIN_USER_DETAILS",nativeQuery=true)
	BigDecimal getUserId();
	
	@Query(value="SELECT NVL(MAX(AMEND_ID)+1,0) FROM BROKER_COMMISSION_DETAIL WHERE AGENCY_CODE=?1 AND PRODUCT_ID=?2",nativeQuery=true)
	BigDecimal getAmendIdByAgencyCodeAndProductId(String agencyCode,String productId);
}