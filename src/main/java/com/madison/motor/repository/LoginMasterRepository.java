/*
 * Java domain class for entity "LoginMaster" 
 * Created on 2023-02-13 ( Date ISO 2023-02-13 - Time 15:54:39 )
 * Generated by Telosys Tools Generator ( version 3.3.0 )
 */
 
 /*
 * Created on 2023-02-13 ( 15:54:39 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.3.0
 */


package com.madison.motor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.madison.motor.entity.BranchMaster;
import com.madison.motor.entity.LoginMaster;
import com.madison.motor.entity.LoginMasterId;
/**
 * <h2>LoginMasterRepository</h2>
 *
 * createdAt : 2023-02-13 - Time 15:54:39
 * <p>
 * Description: "LoginMaster" Repository
 */
 
 
 
public interface LoginMasterRepository  extends JpaRepository<LoginMaster,LoginMasterId > , JpaSpecificationExecutor<LoginMaster> {

	LoginMaster findByLoginIdAndAgencyCode(String loginId, String agencyCode);

	LoginMaster findByAgencyCode(String agencyCode);
	
	

}
