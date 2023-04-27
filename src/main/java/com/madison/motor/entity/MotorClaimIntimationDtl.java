package com.madison.motor.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@Entity
@DynamicInsert
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MOTOR_CLAIM_INTIMATION_DTL")
@IdClass(MotorClaimIntimationDtlId.class)

public class MotorClaimIntimationDtl implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "CLAIM_REF", nullable = false)
	private Long claimref;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "NRC_PASSPORT_NO")
	private String nrcPassportNo;
	
	@Column(name = "PHONE_NO")
	private String phoneno;
	
	@Column(name = "POLICY_NUMBER")
	private String policynumber;
	
	@Column(name = "VEHICLE_REG_NO")
	private String vehicleRefno;
	
	@Column(name = "DATE_OF_ACCIDENT")
	private Date dateofaccident;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "ENTRY_DATE")
	private Date entrydate;
	
	@Column(name = "REMARKS")
	private String remarks;
	
	@Column(name = "CLAIM_ID")
	private Long claimid;
	
	@Column(name = "STATUS_UPDATE")
	private Date statusupdate;

}
