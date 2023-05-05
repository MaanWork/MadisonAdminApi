/*
 * Java domain class for entity "PersonalInfo" 
 * Created on 2023-02-13 ( Date ISO 2023-02-13 - Time 15:58:59 )
 * Generated by Telosys Tools Generator ( version 3.3.0 )
 */
 
 /*
 * Created on 2023-02-13 ( 15:58:59 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.3.0
 */


package com.madison.motor.repository;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.madison.motor.entity.PersonalInfo;
/**
 * <h2>PersonalInfoRepository</h2>
 *
 * createdAt : 2023-02-13 - Time 15:58:59
 * <p>
 * Description: "PersonalInfo" Repository
 */
 
 
@Repository 
public interface PersonalInfoRepository  extends JpaRepository<PersonalInfo,BigDecimal > , JpaSpecificationExecutor<PersonalInfo> {

	
	@Query(value="SELECT CUSTOMER_NUMBER_SEQ.NEXTVAL FROM DUAL",nativeQuery=true)
	Long getCustomerId();
	
	@Query(value ="select detail_name from constant_detail where CATEGORY_ID=37 and CATEGORY_DETAIL_ID=1 and status='Y' and branch_code=?1",nativeQuery=true)
	String getAgencyCode(String branchCode);
	
	@Transactional
	@Modifying
	@Query(value ="update constant_detail set detail_name=?1 where CATEGORY_ID=37 and CATEGORY_DETAIL_ID=1 and status='Y' and branch_code=?2",nativeQuery=true)
	int updateAgencyCode(Long oaCode,String branchCode);

	PersonalInfo findByCustomerId(Long customerId);
	
	@Query(value ="SELECT BROKER_CODE_SEQ.NEXTVAL FROM DUAL",nativeQuery=true)
	public String getBrokerCode();		
	
}
