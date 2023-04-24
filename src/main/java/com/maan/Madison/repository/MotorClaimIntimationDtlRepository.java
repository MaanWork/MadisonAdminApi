package com.maan.Madison.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.madison.motor.entity.MotorClaimIntimationDtl;
import com.madison.motor.entity.MotorClaimIntimationDtlId;



@Repository
public interface MotorClaimIntimationDtlRepository extends JpaRepository<MotorClaimIntimationDtl, MotorClaimIntimationDtlId> {
	
}