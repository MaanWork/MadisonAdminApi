package com.madison.motor.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "COUNTRY_MASTER")
@IdClass(CountryMasterId.class)
public class CountryMaster implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//ENTER INTO PIMARY KEY
	@Id
	@Column(name = "COUNTRY_ID", nullable= false, length=15)
	private Long countryid;
	
	@Id
	@Column(name = "AMEND_ID", nullable=false, length=10)
	private Long amendid;
	
	// ENTER INTO COLUMN'S
	
	@Column(name = "SNO__")
	private Long sno;
	
	@Column(name = "COUNTRY_NAME")
	private String countryname;
	
	@Column(name = "COUNTRY_SHORT_NAME")
	private String countryshortname;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "RSACODE")
	private String rsacode;
	
	@Column(name = "NATIONALITY_NAME")
	private String nationalityname;
	
	@Column(name = "EFFECTIVE_DATE")
	private Date effectivedate;
	
	@Column(name = "GEO_RATE")
	private Long georate;
	
	@Column(name = "REMARKS")
	private String remarks;
	
	@Column(name = "INT_PORT_CODE")
	private String intportcode;

}
