/*
 * Java domain class for entity "MotorPolicyCoverData" 
 * Created on 2023-02-13 ( Date ISO 2023-02-13 - Time 15:56:42 )
 * Generated by Telosys Tools Generator ( version 3.3.0 )
 */
 
 /*
 * Created on 2023-02-13 ( 15:56:42 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.3.0
 */


package com.madison.motor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.madison.motor.entity.MotorPolicyCoverData;
import com.madison.motor.entity.MotorPolicyCoverDataId;
/**
 * <h2>MotorPolicyCoverDataRepository</h2>
 *
 * createdAt : 2023-02-13 - Time 15:56:42
 * <p>
 * Description: "MotorPolicyCoverData" Repository
 */
 
 
 
public interface MotorPolicyCoverDataRepository  extends JpaRepository<MotorPolicyCoverData,MotorPolicyCoverDataId > , JpaSpecificationExecutor<MotorPolicyCoverData> {

}
