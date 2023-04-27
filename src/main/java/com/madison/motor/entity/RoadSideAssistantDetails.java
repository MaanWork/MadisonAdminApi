package com.madison.motor.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Builder
@Table(name = "ROAD_SIDE_ASSISTANT_DETAILS")
@IdClass(RoadSideAssistantDetailsId.class)
@ToString
public class RoadSideAssistantDetails implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//ENTER INTO PRIMARY KEY
	
	@Id
	@Column(name = "REF_NO", nullable = false)
	private Long refNo;
	
	//ENTER COLUMN NAME
	
	@Column(name = "MOBILE_NO")
	private Long mobileno;
	
	@Column(name = "POLICY_NO")
	private String policyNo;
	
	@Column(name = "CUSTOMER_NAME")
	private String customerName;
	
	@Column(name = "ASSISTANT_TYPE")
	private String assistantType;
	
	@Column(name = "DESCRIPTION")
	private String descripition;
	
	@Column(name = "LONGITUDE")
	private String logitude;
	
	@Column(name = "LATITUDE")
	private String latitude;
	
	@Column(name = "DEVICE_ID")
	private String deviceId;
	
	@Column(name = "ENTRY_DATE")
	private Date entrydate;
	
	@Column(name = "LOCATION")
	private String location;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "REMARKS")
	private String remarks;
	
	@Column(name = "MAIL_ID")
	private String mailId;
	
	@Column(name = "UPDATE_DATE")
	private Date updatedate;
	
	@Column(name = "CUSTOMER_FEEDBACK")
	private String customerfeedback;
	
	@Column(name = "LOGIN_ID")
	private String loginId;
	
	@Column(name = "UPDATE_BY")
	private String updateby;

}
