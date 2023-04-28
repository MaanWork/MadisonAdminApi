package com.madison.motor.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "COUNTRY_MASTER")
@IdClass(CountryMasterId.class)
public class CountryMaster {
	
	@Id
	@Column(name = "COUNTRY_ID")
	private Long countryId; 
	
	@Id
	@Column(name ="AMEND_ID")
	private Long amendId;
	
	@Column(name ="SNO__")
	private Long sno;
	@Column(name ="COUNTRY_NAME")
	private String countryName;
	@Column(name ="COUNTRY_SHORT_NAME")
	private String  countryShortName;
	@Column(name ="STATUS")
	private String  status;
	@Column(name ="RSACODE")
	private String  rsaCode;
	@Column(name ="NATIONALITY_NAME")
	private String  nationalityName;
	@Column(name ="EFFECTIVE_DATE")
	private Date  effectiveDate;
	@Column(name ="GEO_RATE")
	private Long  geoRate;
	@Column(name ="REMARKS")
	private String  remarks;
	@Column(name ="INT_PORT_CODE")
	private String  intPortCode;
	


}
