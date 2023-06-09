/* 
*  Copyright (c) 2019. All right reserved
 * Created on 2023-02-13 ( Date ISO 2023-02-13 - Time 15:52:51 )
 * Generated by Telosys Tools Generator ( version 3.3.0 )
 */

/*
 * Created on 2023-02-13 ( 15:52:51 )
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
import javax.persistence.*;




/**
* Domain class for entity "BrokerBranchMaster"
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
@IdClass(BrokerBranchMasterId.class)
@Table(name="BROKER_BRANCH_MASTER")


public class BrokerBranchMaster implements Serializable {
 
private static final long serialVersionUID = 1L;
 
    //--- ENTITY PRIMARY KEY 
    @Id
    @Column(name="BROKER_ID", nullable=false, length=15)
    private String     brokerId ;

    @Id
    @Column(name="BRANCH_CODE", nullable=false, length=10)
    private String     branchCode ;

    @Id
    @Column(name="MGEN_BRANCH_ID", nullable=false, length=8)
    private String     mgenBranchId ;

    //--- ENTITY DATA FIELDS 
    @Column(name="SNO")
    private BigDecimal sno ;

    @Column(name="BRANCH_ID")
    private BigDecimal branchId ;

    @Column(name="BRANCH_NAME", length=100)
    private String     branchName ;

    @Column(name="STATUS", length=1)
    private String     status ;

    @Column(name="REMARKS", length=100)
    private String     remarks ;


    //--- ENTITY LINKS ( RELATIONSHIP )


}



