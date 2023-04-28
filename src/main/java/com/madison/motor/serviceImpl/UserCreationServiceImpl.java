package com.madison.motor.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Tuple;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.madison.motor.response.AdminDetailsRes;
import com.madison.motor.response.EditAdminDetailRes;
import com.madison.motor.response.EditUserDetailsRes;
import com.madison.motor.response.MadisonCommonRes;
import com.madison.motor.response.UserDetailsRes;
import com.madison.motor.service.UserCreationService;
import com.madison.motor.utilityImpl.CriteriaQueryImpl;


@Service
public class UserCreationServiceImpl implements UserCreationService {
	
	
	Logger log =LogManager.getLogger(UserCreationServiceImpl.class);
	
	@Autowired
	private CriteriaQueryImpl query;

	@Override
	public MadisonCommonRes getUserDetailsByBranchCode(String branchCode) {
		MadisonCommonRes response =new MadisonCommonRes();
		List<UserDetailsRes> userRes =new ArrayList<>(50);
		try {
			List<Tuple> list =query.getUserListByBranchCode(branchCode);
			if(!CollectionUtils.isEmpty(list)) {
				list.forEach(p->{
					UserDetailsRes userDetailsRes =UserDetailsRes.builder()
							.agencyCode(p.get("agencyCode")==null?"":p.get("agencyCode").toString())
							.applicationId(p.get("applicationId")==null?"":p.get("applicationId").toString())
							.brokerName(p.get("brokerName")==null?"":p.get("brokerName").toString())
							.customerName(p.get("customerName")==null?"":p.get("customerName").toString())
							.entryDate(p.get("entryDate")==null?"":p.get("entryDate").toString())
							.loginId(p.get("loginId")==null?"":p.get("loginId").toString())
							.status(p.get("status")==null?"":p.get("status").toString())
							.userName(p.get("username")==null?"":p.get("username").toString())
							.userType(p.get("usertype")==null?"":p.get("usertype").toString())
							.build();
					userRes.add(userDetailsRes);
				});
				response.setMessage("SUCCESS");
				response.setResponse(userRes);
			}else {
				response.setMessage("FAILED");
				response.setResponse(null);
			}
		}catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return response;
	}

	@Override
	public MadisonCommonRes editUserByAgencyCode(String agencyCode) {
		MadisonCommonRes response = new MadisonCommonRes();
		try {
			Tuple user =query.editUserDetailsByAgencyCode(agencyCode);
			if(user!=null) {
				EditUserDetailsRes detailsRes =EditUserDetailsRes.builder()
						.address1(user.get("address1")==null?"":user.get("address1").toString())
						.address2(user.get("address2")==null?"":user.get("address2").toString())
						.agencyCode(user.get("agencyCode")==null?"":user.get("agencyCode").toString())
						.attachedBranch(user.get("attachedBranch")==null?"":user.get("attachedBranch").toString())
						.branchCode(user.get("branchCode")==null?"":user.get("branchCode").toString())
						.city(user.get("city")==null?"":user.get("city").toString())
						.country(user.get("countryName")==null?"":user.get("countryName").toString())
						.countryId(user.get("countryId")==null?"":user.get("countryId").toString())
						.dateOfBirth(user.get("dob")==null?"":user.get("dob").toString())
						.email(user.get("email")==null?"":user.get("email").toString())
						.emirate(user.get("emirate")==null?"":user.get("emirate").toString())
						.entryDate(user.get("entryDate")==null?"":user.get("entryDate").toString())
						.fax(user.get("fax")==null?"":user.get("fax").toString())
						.firstName(user.get("firstName")==null?"":user.get("firstName").toString())
						.gender(user.get("gender")==null?"":user.get("gender").toString())
						.isB2c(user.get("isB2c")==null?"":user.get("isB2c").toString())
						.lastName(user.get("lastName")==null?"":user.get("lastName").toString())
						.loginId(user.get("loginId")==null?"":user.get("loginId").toString())
						.mobile(user.get("mobile")==null?"":user.get("mobile").toString())
						.nationality(user.get("nationality")==null?"":user.get("nationality").toString())
						.occupation(user.get("occupation")==null?"":user.get("occupation").toString())
						.poBox(user.get("pobox")==null?"":user.get("pobox").toString())
						.status(user.get("status")==null?"":user.get("status").toString())
						.subBranch(user.get("subBranch")==null?"":user.get("subBranch").toString())
						.telePhone(user.get("telephone")==null?"":user.get("telephone").toString())
						.title(user.get("title")==null?"":user.get("title").toString())
						.userName(user.get("userName")==null?"":user.get("userName").toString())
						.userType(user.get("usertype")==null?"":user.get("usertype").toString())
						.build();
				
				response.setMessage("SUCCESS");
				response.setResponse(detailsRes);
			}else {
				response.setMessage("FAILED");
				response.setResponse(null);
			}
		}catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return response;
	}

	@Override
	public MadisonCommonRes getAdminDetailsByBranchCode(String branchCode) {
		MadisonCommonRes response = new MadisonCommonRes();
		List<AdminDetailsRes> adminRes = new ArrayList<AdminDetailsRes>();
		try {
			List<Tuple> list =query.getAdminDeatilsByBranchCode(branchCode);
			if(!CollectionUtils.isEmpty(list)) {
				list.forEach(p ->{
					AdminDetailsRes adminDetailsRes =AdminDetailsRes.builder()
							.branchName(p.get("branch_name")==null?"":p.get("branch_name").toString())
							.loginId(p.get("loginId")==null?"":p.get("loginId").toString())
							.status(p.get("status")==null?"":p.get("status").toString())
							.userName(p.get("userName")==null?"":p.get("userName").toString())
							.userType(p.get("usertype")==null?"":p.get("usertype").toString())
							.build();
					adminRes.add(adminDetailsRes);
				});
			
				response.setMessage("SUCCESS");
				response.setResponse(adminRes);
			}else {
				response.setMessage("FAILED");
				response.setResponse(null);
			}
		}catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return response;
	}

	@Override
	public MadisonCommonRes editAdminDetailsByLoginId(String loginId, String branchCode) {
		MadisonCommonRes response = new MadisonCommonRes();
		try {
			Tuple admin =query.editAdminDetailsByLoginId(loginId,branchCode);
			if(admin!=null) {
				EditAdminDetailRes adminDetailRes = EditAdminDetailRes.builder()
						.attachedBranch(admin.get("attachedBranch")==null?"":admin.get("attachedBranch").toString())
						.attachedUw(admin.get("attachedUw")==null?"":admin.get("attachedUw").toString())
						.branchCode(admin.get("branchCode")==null?"":admin.get("branchCode").toString())
						.branchName(admin.get("branch_name")==null?"":admin.get("branch_name").toString())
						.brokerCode(admin.get("brokerName")==null?"":admin.get("brokerName").toString())
						.firstName(admin.get("firstName")==null?"":admin.get("firstName").toString())
						.middleName(admin.get("middleName")==null?"":admin.get("middleName").toString())
						.lastName(admin.get("lastName")==null?"":admin.get("lastName").toString())
						.loginId(admin.get("loginId")==null?"":admin.get("loginId").toString())
						.menuId(admin.get("menuId")==null?"":admin.get("menuId").toString())
						.mobileNo(admin.get("mobileNo")==null?"":admin.get("mobileNo").toString())
						.onlineYn(admin.get("onlineYn")==null?"":admin.get("onlineYn").toString())
						.productId(admin.get("productId")==null?"":admin.get("productId").toString())
						.status(admin.get("status")==null?"":admin.get("status").toString())
						.UserMail(admin.get("userMail")==null?"":admin.get("userMail").toString())
						.userName(admin.get("userName")==null?"":admin.get("userName").toString())
						.userType(admin.get("usertype")==null?"":admin.get("usertype").toString())
					.build();
				
				response.setMessage("SUCCESS");
				response.setResponse(adminDetailRes);
			}else {
				response.setMessage("FAILED");
				response.setResponse(null);
			}
		}catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return response;
	}

}
