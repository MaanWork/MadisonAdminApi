package com.madison.motor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.madison.motor.entity.BrokerCompanyMaster;

@Repository
public interface BrokerCompanyMasterRepository extends JpaRepository<BrokerCompanyMaster, String> {

}
