package com.madison.motor.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "MENU_MASTER")
@IdClass(MenuMasterId.class)

public class MenuMaster implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//PRIMERY KEY
	
	@Id
	@Column(name = "MENU_ID")
	private Long menuid;
	
	@Id
	@Column(name = "PRODUCT_ID")
	private Long productid;
	
	//DATE FIELD
	
	@Column(name = "MENU_NAME")
	private String menuname;
	
	@Column(name = "MENU_URL")
	private String menuurl;
	
	@Column(name = "PARENT_MENU")
	private String parentmenu;
	
	@Column(name = "BRANCH_CODE")
	private String branchCode;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "RSACODE")
	private String rsacode;
	
	@Column(name = "USERTYPE")
	private String usertype;
	
	@Column(name = "ISCLICK")
	private String isClick;

}
