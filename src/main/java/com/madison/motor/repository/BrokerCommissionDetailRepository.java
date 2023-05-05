package com.madison.motor.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.madison.motor.entity.BrokerCommissionDetail;
import com.madison.motor.entity.BrokerCommissionDetailId;

@Repository
public interface BrokerCommissionDetailRepository extends JpaRepository<BrokerCommissionDetail, BrokerCommissionDetailId> {

	
	@Modifying
	@Transactional
	@Query(value ="update policyno_generate set current_no=?1,remarks=?1 where type_id=3 and status='Y' and BRANCH_CODE=?2",nativeQuery=true)
	int  updateBrokerCode(Long customerId, String branchCode);

}
