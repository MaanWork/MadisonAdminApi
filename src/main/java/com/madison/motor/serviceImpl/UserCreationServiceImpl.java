package com.madison.motor.serviceImpl;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Tuple;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.madison.motor.entity.BranchMaster;
import com.madison.motor.entity.BrokerCommissionDetail;
import com.madison.motor.entity.BrokerCompanyMaster;
import com.madison.motor.entity.LoginMaster;
import com.madison.motor.entity.LoginUserDetails;
import com.madison.motor.entity.PersonalInfo;
import com.madison.motor.repository.BranchMasterRepository;
import com.madison.motor.repository.BrokerCommissionDetailRepository;
import com.madison.motor.repository.BrokerCompanyMasterRepository;
import com.madison.motor.repository.LoginMasterRepository;
import com.madison.motor.repository.LoginUserDetailsRepository;
import com.madison.motor.repository.PersonalInfoRepository;
import com.madison.motor.request.AdminSaveReq;
import com.madison.motor.request.BrokerProductSaveReq;
import com.madison.motor.request.BrokerSaveReq;
import com.madison.motor.request.GetOpenCoverListReq;
import com.madison.motor.request.GetOrEditProductReq;
import com.madison.motor.request.GetProductReq;
import com.madison.motor.request.GetUserDetailsReq;
import com.madison.motor.request.PolicyTypeReq;
import com.madison.motor.request.ProductReq;
import com.madison.motor.request.SaveProductReq;
import com.madison.motor.request.UpdatePasswordReq;
import com.madison.motor.request.UserSaveReq;
import com.madison.motor.request.getExcludedMenuReq;
import com.madison.motor.response.AdminDetailsRes;
import com.madison.motor.response.BrokerDetailsRes;
import com.madison.motor.response.EditAdminDetailRes;
import com.madison.motor.response.EditBrokerRes;
import com.madison.motor.response.EditProductRes;
import com.madison.motor.response.EditUserDetailsRes;
import com.madison.motor.response.GetCustomerByCustomerIdRes;
import com.madison.motor.response.GetCustomerDetailsRes;
import com.madison.motor.response.GetOpenCoverListRes;
import com.madison.motor.response.GetProductDetailsRes;
import com.madison.motor.response.MadisonCommonRes;
import com.madison.motor.response.PolicyTypeRes;
import com.madison.motor.response.ProductDetailsRes;
import com.madison.motor.response.SaveUserRes;
import com.madison.motor.response.UserDetailsRes;
import com.madison.motor.service.UserCreationService;
import com.madison.motor.utilityImpl.CommonService;
import com.madison.motor.utilityImpl.CriteriaQueryImpl;
import com.madison.motor.utilityImpl.ErrorList;
import com.madison.motor.utilityImpl.MadisonAdminValidation;


@Service
public class UserCreationServiceImpl implements UserCreationService {
	
	
	Logger log =LogManager.getLogger(UserCreationServiceImpl.class);
	
	@Autowired
	private CriteriaQueryImpl query;
	
	@Autowired
	private CommonService cs;
	
	@Autowired
	private PersonalInfoRepository personalRepo;
	
	@Autowired
	private LoginMasterRepository loginMasterRepo;
	
	@Autowired
	private MadisonAdminValidation validation;
	
	@Autowired
	private LoginUserDetailsRepository userRepo;

	@Autowired
	private BranchMasterRepository branchRepo;
	
	@Autowired
	private BrokerCompanyMasterRepository bcmRepo;
	
	@Autowired
	private BrokerCommissionDetailRepository commisionRepo;
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public String DateFormat(Object input) {
		return new SimpleDateFormat("dd/MM/yyyy").format(input).toString();
	}
	
	@Override
	public MadisonCommonRes getUserDetailsByBranchCode(GetUserDetailsReq req) {
		MadisonCommonRes response =new MadisonCommonRes();
		List<UserDetailsRes> userRes =new ArrayList<>(50);
		try {
			List<Tuple> list =query.getUserListByBranchCode(req);
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
							.oaCode(p.get("oaCode")==null?"":p.get("oaCode").toString())
							.customerId(p.get("customerId")==null?"":p.get("customerId").toString())
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
	public MadisonCommonRes editUserByAgencyCode(String agencyCode,String customerId) {
		MadisonCommonRes response = new MadisonCommonRes();
		try {
			Tuple user =query.editUserDetailsByAgencyCode(agencyCode,Long.valueOf(customerId));
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
						.customerId(user.get("customerId")==null?"":user.get("customerId").toString())
						.nationalityName(user.get("nationalityName")==null?"":user.get("nationalityName").toString())
						.brokerCode(user.get("oaCode")==null?"":user.get("oaCode").toString())
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
						.brokerCode(admin.get("brokerCodes")==null?"":admin.get("brokerCodes").toString())
						.firstName(admin.get("firstName")==null?"":admin.get("firstName").toString())
						.middleName(admin.get("middleName")==null?"":admin.get("middleName").toString())
						.lastName(admin.get("lastName")==null?"":admin.get("lastName").toString())
						.loginId(admin.get("loginId")==null?"":admin.get("loginId").toString())
						.menuId(admin.get("menuId")==null?"":admin.get("menuId").toString())
						.mobileNo(admin.get("mobileNo")==null?"":admin.get("mobileNo").toString())
						.onlineYn(admin.get("onlineYn")==null?"":admin.get("onlineYn").toString())
						.productId(admin.get("productId")==null?"":admin.get("productId").toString())
						.status(admin.get("statusId")==null?"":admin.get("statusId").toString())
						.UserMail(admin.get("userMail")==null?"":admin.get("userMail").toString())
						.userName(admin.get("userName")==null?"":admin.get("userName").toString())
						.userType(admin.get("usertype")==null?"":admin.get("usertype").toString())
						.usertypeId(branchRepo.getuserIdByName(branchCode,admin.get("usertype").toString()))
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

	@Override
	public MadisonCommonRes getBrokerByBranchCode(String branchCode, String appId) {
		MadisonCommonRes response =new MadisonCommonRes();
		List<BrokerDetailsRes> brokerRes = new ArrayList<BrokerDetailsRes>();
		try {
			List<Tuple> list =query.getBrokerDetailsByBranchCode(branchCode,appId);
			if(!CollectionUtils.isEmpty(list)) {
				list.forEach(d ->{
					BrokerDetailsRes brokerDetailsRes =BrokerDetailsRes.builder()
							.agencyCode(d.get("agencyCode")==null?"":d.get("agencyCode").toString())
							.contactPerson(d.get("contactPerson")==null?"":d.get("contactPerson").toString())
							.customerId(d.get("customerId")==null?"":d.get("customerId").toString())
							.loginId(d.get("loginId")==null?"":d.get("loginId").toString())
							.rsaBrokerCode(d.get("rsaBrokerCode")==null?"":d.get("rsaBrokerCode").toString())
							.status(d.get("status")==null?"":d.get("status").toString())
							.companyName(d.get("companyName")==null?"":d.get("companyName").toString())
							.createdDate(d.get("entryDate")==null?"":DateFormat(d.get("entryDate")).toString())
							.build();
					brokerRes.add(brokerDetailsRes);
				});
				response.setMessage("SUCCESS");
				response.setResponse(brokerRes);
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
	public MadisonCommonRes editBrokerByAgencyCode(String agencyCode,String branchCode) {
		MadisonCommonRes response = new MadisonCommonRes();
		try {
			Tuple user =query.editBrokerByAgencyCode(agencyCode,branchCode);
			if(user!=null) {
				EditBrokerRes detailsRes =EditBrokerRes.builder()
						.address1(user.get("address1")==null?"":user.get("address1").toString())
						.address2(user.get("address2")==null?"":user.get("address2").toString())
						.agencyCode(user.get("agencyCode")==null?"":user.get("agencyCode").toString())
						.attachedBranch(user.get("attachedBranch")==null?"":user.get("attachedBranch").toString())
						.branchCode(user.get("branchCode")==null?"":user.get("branchCode").toString())
						.city(user.get("city")==null?"":user.get("city").toString())
						.country(user.get("countryName")==null?"":user.get("countryName").toString())
						.countryId(user.get("country")==null?"":user.get("country").toString())
						.dateOfBirth(user.get("dob")==null?"":user.get("dob").toString())
						.email(user.get("email")==null?"":user.get("email").toString())
						.emirate(user.get("emirate")==null?"":user.get("emirate").toString())
						.entryDate(user.get("entryDate")==null?"":user.get("entryDate").toString())
						.fax(user.get("fax")==null?"":user.get("fax").toString())
						.firstName(user.get("firstName")==null?"":user.get("firstName").toString())
						.gender(user.get("gender")==null?"":user.get("gender").toString())
						.companyName(user.get("companyName")==null?"":user.get("companyName").toString())
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
						.nationalityName(user.get("nationalityname")==null?"":user.get("nationalityname").toString())
						.branchName(user.get("branchName")==null?"":user.get("branchName").toString())
						.customerId(user.get("CustId")==null?"":user.get("CustId").toString())
						//.userName(user.get("userName")==null?"":user.get("userName").toString())
						.userType(user.get("userid")==null?"":user.get("userid").toString())
						.executiveId(user.get("acExecutiveId")==null?"":user.get("acExecutiveId").toString())
						.approvedBy(user.get("approvedPreparedBy")==null?"":user.get("approvedPreparedBy").toString())
						.rsaBrokerCode(user.get("rsaBrokerCode")==null?"":user.get("rsaBrokerCode").toString())
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
	public MadisonCommonRes getProductDetailsByAgencyCode(String agencyCode) {
		MadisonCommonRes response = new MadisonCommonRes();
		List<Object> proList =new ArrayList<Object>();
		try {
			List<Tuple> list =query.getProductDetailsByAgencyCode(agencyCode);
			if(!CollectionUtils.isEmpty(list)) {
				list.forEach(d->{
					GetProductDetailsRes productDetailsRes =GetProductDetailsRes.builder()
							.backDateAllowed(d.get("backDateAllowed")==null?"":d.get("backDateAllowed").toString())
							.commission(d.get("commission")==null?"":d.get("commission").toString())
							.discountOfPremium(d.get("discountOfPremium")==null?"":d.get("discountOfPremium").toString())
							.freightDebitOption(d.get("freightDebitOption")==null?"":d.get("freightDebitOption").toString())
							.insuranceEndLimit(d.get("insuranceEndLimit")==null?"":d.get("insuranceEndLimit").toString())
							.loadingOfPremium(d.get("loadingOfPremium")==null?"":d.get("loadingOfPremium").toString())
							.minPremiumAmount(d.get("minPremiumAmount")==null?"":d.get("minPremiumAmount").toString())
							.payReceiptStatus(d.get("payReceiptStatus")==null?"":d.get("payReceiptStatus").toString())
							.proCommission(d.get("proCommission")==null?"":d.get("proCommission").toString())
							.productId(d.get("productId")==null?"":d.get("productId").toString())
							.productName(d.get("productName")==null?"":d.get("productName").toString())
							.proEndDate(d.get("proExpiryDate")==null?"":d.get("proExpiryDate").toString())
							.proStartDate(d.get("proStartDate")==null?"":d.get("proStartDate").toString())
							.provisionForPremium(d.get("provisionForPremium")==null?"":d.get("provisionForPremium").toString())
							.receiptStatus(d.get("receiptStatus")==null?"":d.get("receiptStatus").toString())
							.referal(d.get("referal")==null?"":d.get("referal").toString())
							.specialDiscount(d.get("specialDiscount")==null?"":d.get("specialDiscount").toString())
							.status(d.get("status")==null?"":d.get("status").toString())
							.build();
					proList.add(productDetailsRes);
				});
				response.setMessage("SUCCESS");
				response.setResponse(proList);
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
	public MadisonCommonRes saveUser(UserSaveReq req) {
		MadisonCommonRes response =new MadisonCommonRes();
		List<ErrorList> error = new ArrayList<ErrorList>();
		log.info("saveUser request :"+cs.printReq(req));
		try {
				error =validation.validateUser(req);
		if("new".equalsIgnoreCase(req.getMode())) {
			if(req.getLoginId().equalsIgnoreCase(query.getLoginByLoginId(req.getLoginId()))) {
				error.add(new ErrorList("1","Login","LoginId Already Exist"));
			}
		}
			if(CollectionUtils.isEmpty(error)) {
				String agencyCode="";
				if(StringUtils.isBlank(req.getAgencyCode())) {
					agencyCode =personalRepo.getAgencyCode(req.getBranchCode());
					personalRepo.updateAgencyCode(Long.valueOf(agencyCode)+1,req.getBranchCode());
				}
				PersonalInfo personalInfo =PersonalInfo.builder()
						.customerId(StringUtils.isBlank(req.getCustomerId())?personalRepo.getCustomerId():Long.valueOf(req.getCustomerId()))
						.applicationId(StringUtils.isBlank(req.getApplicationId())?"2":req.getApplicationId())
						.title(req.getTitle())
						.firstName(req.getFirstName())
						.lastName(StringUtils.isBlank(req.getLastName())?"":req.getLastName())
						.amendId(new BigDecimal(1))
						.nationality(req.getNationality())
						.dob(StringUtils.isBlank(req.getDateOfBirth())?null:sdf.parse(req.getDateOfBirth()))
						.gender(StringUtils.isBlank(req.getGender())?"":req.getGender())
						.telephone(StringUtils.isBlank(req.getPhoneNo())?"":req.getPhoneNo())
						.mobile(req.getMobileNo())
						.email(req.getEmailId())
						.fax(StringUtils.isBlank(req.getFax())?"":req.getFax())
						.address1(StringUtils.isBlank(req.getAddress1())?"":req.getAddress1())
						.address2(StringUtils.isBlank(req.getAddress2())?"":req.getAddress2())
						.occupation(StringUtils.isBlank(req.getOccupation())?"":req.getOccupation())
						.pobox(req.getPoBox())
						.country(req.getCountryId())
						.emirate(req.getCityId())
						.status(StringUtils.isBlank(req.getStatus())?"":req.getStatus())
						.entryDate(new Date())
						.loginId(req.getLoginId())
						.city(req.getCityId())
						.agencyCode(StringUtils.isBlank(req.getAgencyCode())?agencyCode:req.getAgencyCode())
						.fdCode("")
						.oaCode(StringUtils.isBlank(req.getOaCode())?req.getBrokerCode():req.getOaCode())
						.build();
				PersonalInfo pi =personalRepo.save(personalInfo);
						
				String password="";
				if(StringUtils.isBlank(req.getPassword())) {
					password = loginMasterRepo.getPasswordByLoginId(req.getLoginId());
				}
				LocalDate localDate =LocalDate.now().plusDays(45);
				Date pass_date =Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
				LoginMaster loginMaster =LoginMaster.builder()
						.oaCode(pi.getOaCode())
						.agencyCode(pi.getAgencyCode())
						.loginId(pi.getLoginId())
						.usertype(req.getUserType())
						.username(pi.getFirstName())
						.password(StringUtils.isBlank(req.getPassword())?password:CommonService.encrypt(req.getPassword()))
						.userid(new BigDecimal(1))
						.accesstype("BOTH")
						.rights("")
						.lpass1("")
						.lpass2("")
						.lpass3("")
						.passdate(pass_date)
						.companyId("1")
						.createdBy(pi.getAgencyCode())
						.status(StringUtils.isBlank(req.getStatus())?"":req.getStatus())
						.userIdCreation("N")
						.acExecutiveCreation("N")
						.referal("Y")
						.appId("16")
						.pwdCount("0")
						.userMail(pi.getEmail())
						.branchCode(req.getBranchCode())
						.countryId(new BigDecimal(req.getCountryId()))
						.mobileNo(req.getMobileNo())
						.attachedBranch(req.getAttachedBranch())
						.subBranch(req.getSubBranchId())
						.isB2c(StringUtils.isBlank(req.getIsB2c())?"N":req.getIsB2c())
						.build();
				LoginMaster lm =loginMasterRepo.save(loginMaster);
				
				SaveUserRes userRes =SaveUserRes.builder()
						.agencyCode(pi.getAgencyCode())
						.customerId(pi.getCustomerId().toString())
						.loginId(lm.getLoginId())						
						.build();
				
				response.setMessage("SUCCESS");
				response.setResponse(userRes);
			}else {
				response.setMessage("Error");
				response.setErrors(error);
			}
		}catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			response.setMessage("FAILED");
			error.add(new ErrorList("1","UserCreation","User creation failed. Contact Admin..!"));
			response.setErrors(error);
		}
		return response;
	}

	@Override
	public MadisonCommonRes saveProduct(SaveProductReq req) {
		MadisonCommonRes response = new MadisonCommonRes();
		try {
			List<ProductReq> product =req.getProduct();
			LoginMaster lm=loginMasterRepo.findByLoginIdAndAgencyCode(req.getLoginId(),req.getAgencyCode());
			PersonalInfo pi =personalRepo.findByCustomerId(Long.valueOf(req.getCustomerId()));
			for(ProductReq p : product) {
				Long count =query.isProductExits(p.getProductId(), req.getAgencyCode());
				if(count==0) {
					LoginUserDetails lud =LoginUserDetails.builder()
							.userId(userRepo.getUserId())
							.productId(new BigDecimal(p.getProductId()))
							.userName(pi.getFirstName())
							.loginId(lm.getLoginId())
							.agencyCode(pi.getAgencyCode())
							.oaCode(pi.getOaCode())
							.companyId("1")
							.insuranceEndLimit(StringUtils.isBlank(p.getSumInsured())?null:new BigDecimal(p.getSumInsured()))
							.relativeUserId(new BigDecimal(0))
							.amendId(new BigDecimal(1))
							.inceptionDate(new Date())
							.entryDate(new Date())
							.status("Y")
							.customerId(new BigDecimal(pi.getCustomerId()))
							.freightDebitOption(StringUtils.isBlank(p.getCreditYn())?"N":p.getCreditYn())
							.payReceiptStatus("N")
							.receiptStatus("N")
							.openCoverNo("11".equals(p.getProductId())?p.getOpenCoverNo():"")
							.specialDiscount(StringUtils.isBlank(p.getSpecialDiscount())?null:new BigDecimal(p.getSpecialDiscount()))
							.schemeId("30".equals(p.getProductId())?new BigDecimal(7):null)
							.issuertype("Y".equals(lm.getIsB2c())?"90011":"8888")
							.isB2c(lm.getIsB2c())
							.build();
					userRepo.save(lud);
				}else if (count!=0) {
					query.updateProductByAgencyCodeAndProductId(p, req.getAgencyCode(),lm,pi);
				}
				
			}
			response.setMessage("SUCCESS");
			response.setResponse("Product added successfully");
		}catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			response.setMessage("FAILED");
			response.setResponse("Product add failed");
		}
		return response;
	}

	@Override
	public MadisonCommonRes getProduct(GetProductReq req) {
		MadisonCommonRes response = new MadisonCommonRes();
		List<ProductDetailsRes> proList =new ArrayList<ProductDetailsRes>();
		try {
			List<Tuple> list =query.getPoductDetails(req);
			if(!CollectionUtils.isEmpty(list)) {
				list.forEach(d ->{
					ProductDetailsRes productDetailsRes =ProductDetailsRes.builder()
							.creditYn(d.get("freightDebitOption")==null?"N":d.get("freightDebitOption").toString())
							.productId(d.get("productId")==null?"":d.get("productId").toString())
							.productName(d.get("product")==null?"":d.get("product").toString())
							.specialDiscount(d.get("specialDiscount")==null?"":d.get("specialDiscount").toString())
							.status(d.get("status")==null?"N":d.get("status").toString())
							.sumInsured(d.get("insuranceEndLimit")==null?"":d.get("insuranceEndLimit").toString())
							.build();
					proList.add(productDetailsRes);
				});
				response.setMessage("SUCCESS");
				response.setResponse(proList);
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
	public MadisonCommonRes createAdmin(AdminSaveReq req) {
		MadisonCommonRes response =new MadisonCommonRes();
		try {
			BranchMaster bm =branchRepo.findByBranchCode(req.getBranchCode());
			String userName =req.getFirstName()+(StringUtils.isBlank(req.getMiddleName())?"":req.getMiddleName())+req.getLastName();
			LocalDate localDate =LocalDate.now().plusDays(45);
			Date pass_date =Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
			LoginMaster loginMaster =LoginMaster.builder()
					.loginId(req.getLoginId())
					.password(StringUtils.isBlank(req.getPassword())?query.getadminpasswordbyloginId(req.getLoginId()):CommonService.encrypt(req.getPassword()))
					.usertype("admin")
					.username(userName)
					.agencyCode(req.getLoginId())
					.userid(new BigDecimal(2))
					.oaCode(req.getLoginId())
					.companyId("1")
					.createdBy("admin")
					.status("Y")
					.userIdCreation("Y")
					.acExecutiveCreation("Y")
					.accesstype("BOTH")
					.passdate(pass_date)
					.branchCode(req.getBranchCode())
					.countryId(bm.getOriginationCountryId()==null?null:bm.getOriginationCountryId())
					.menuId(req.getMenuId())
					.productId(req.getProductId())
					.appId("16")
					.pwdCount("0")
					.userMail(req.getEmailid())
					.attachedUw(req.getAttachedUw())
					.brokerCodes(req.getBrokerCode())
					.attachedBranch(req.getAttachedBranch())
					.mobileNo(req.getMobileNo())
					.entryDate(new Date())
					.onlineYn(StringUtils.isBlank(req.getOnlineYn())?"N":req.getOnlineYn())
					.lastName(req.getLastName())
					.middleName(StringUtils.isBlank(req.getMiddleName())?"":req.getMiddleName())
					.firstName(req.getFirstName())
					.build();
			loginMasterRepo.save(loginMaster);
			response.setMessage("SUCCESS");
			response.setResponse("Admin created successfully");
		}catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			response.setMessage("FAILED");
			response.setResponse("Admin creation failed...Contact admin...!");
		}
		return response;
	}

	@Override
	public MadisonCommonRes createBroker(BrokerSaveReq req) {
		MadisonCommonRes response = new MadisonCommonRes();
		log.info("createBroker request :"+cs.printReq(req));
		try {
			List<ErrorList> list =validation.validateBroker(req);
		
			if("new".equalsIgnoreCase(req.getMode())) {
				if(req.getLoginId().equalsIgnoreCase(query.getLoginByLoginId(req.getLoginId()))) {
					list.add(new ErrorList("1","Login","LoginId Already Exist"));
				}
			}
			if(CollectionUtils.isEmpty(list)) {
				String brokerCode =StringUtils.isBlank(req.getAgencyCode())?personalRepo.getBrokerCode():req.getAgencyCode();
				Long customerId=StringUtils.isBlank(req.getCustomerId())?personalRepo.getCustomerId():Long.valueOf(req.getCustomerId());
				commisionRepo.updateBrokerCode(customerId,req.getBranchCode());
				PersonalInfo personalInfo =PersonalInfo.builder()
						.customerId(customerId)
						.applicationId("2")
						.title(req.getTitle())
						.firstName(req.getFirstName())
						.lastName(StringUtils.isBlank(req.getLastName())?"":req.getLastName())
						.amendId(new BigDecimal(1))
						.nationality(req.getNationality())
						.dob(StringUtils.isBlank(req.getDateOfBirth())?null:sdf.parse(req.getDateOfBirth()))
						.gender(StringUtils.isBlank(req.getGender())?"":req.getGender())
						.telephone(StringUtils.isBlank(req.getPhoneNo())?"":req.getPhoneNo())
						.mobile(req.getMobileNo())
						.email(req.getEmailId())
						.fax(StringUtils.isBlank(req.getFax())?"":req.getFax())
						.address1(StringUtils.isBlank(req.getAddress1())?"":req.getAddress1())
						.address2(StringUtils.isBlank(req.getAddress2())?"":req.getAddress2())
						.occupation(StringUtils.isBlank(req.getOccupation())?"":req.getOccupation())
						.pobox(req.getPoBox())
						.country(req.getCountryId())
						.emirate(req.getCityId())
						.status(StringUtils.isBlank(req.getStatus())?"":req.getStatus())
						.entryDate(new Date())
						.loginId(req.getLoginId())
						//.city(req.getCityId())
						.custArNo(req.getCustArNo())
						.agencyCode(brokerCode)
						.fdCode("")
						.oaCode(brokerCode)
						.build();
				PersonalInfo pi =personalRepo.save(personalInfo);
			
			String sourceFileName="";
			if(req.getFile()!=null) {
				File sourceFile =req.getFile();
				sourceFileName ="G:\\"+sourceFile.getName();
				File destinationFile =new File(sourceFileName);
				FileUtils.copyFile(sourceFile, destinationFile);
			}
			
			String password="";
			if(StringUtils.isBlank(req.getPassword())) {
				password = loginMasterRepo.getPasswordByLoginId(req.getLoginId());
			}
				
				LocalDate localDate =LocalDate.now().plusDays(45);
				Date pass_date =Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
				LoginMaster lm = new LoginMaster();
				if(StringUtils.isNotBlank(req.getAgencyCode())) {
					lm = loginMasterRepo.findByAgencyCode(req.getAgencyCode());
			}
				LoginMaster loginMaster =LoginMaster.builder()
						.oaCode(brokerCode)
						.agencyCode(brokerCode)
						.loginId(req.getLoginId())
						.usertype("Broker")
						.username(req.getFirstName())
						.password(StringUtils.isBlank(req.getPassword())?password:CommonService.encrypt(req.getPassword()))
						.userid(new BigDecimal(1))
						.accesstype("BOTH")
						.rights("")
						.lpass1("")
						.lpass2("")
						.lpass3("")
						.passdate(lm.getPassdate()==null?pass_date:lm.getPassdate())
						.companyId("2")
						.createdBy("Admin")
						.status(StringUtils.isBlank(req.getStatus())?"Y":req.getStatus())
						.userIdCreation("N")
						.acExecutiveCreation("N")
						//.referal("Y")
						.appId("16")
						.pwdCount("0")
						.entryDate(lm.getEntryDate()==null?new Date():lm.getEntryDate())
						.inceptionDate(lm.getInceptionDate()==null?new Date():lm.getInceptionDate())
						.expiryDate(lm.getExpiryDate()==null?Date.from(LocalDate.now().plusYears(25).atStartOfDay(ZoneId.systemDefault()).toInstant()):lm.getExpiryDate())
						.userMail(req.getEmailId())
						.branchCode(req.getBranchCode())
						.countryId(new BigDecimal(req.getCountryId()))
						.mobileNo(req.getMobileNo())
						.attachedBranch(req.getAttachedBranch())
						.subBranch(req.getSubBranchId())
						.firstName(sourceFileName)
						.coreLoginId(StringUtils.isBlank(req.getCoreLoginId())?null:new BigDecimal(req.getCoreLoginId()))
						.build();
				loginMasterRepo.save(loginMaster);
				
				BrokerCompanyMaster brokerCompanyMaster = BrokerCompanyMaster.builder()
						.agencyCode(brokerCode)
						.companyName(req.getBrokerOrganization())
						.contactPerson(req.getFirstName())
						.address1(req.getAddress1())
						.address2(req.getAddress2())
						.city(StringUtils.isBlank(req.getOtherCity())?"":req.getOtherCity())
						.country(req.getCountryId())
						.phone(req.getPhoneNo())
						.pobox(StringUtils.isBlank(req.getPoBox())?null:new BigDecimal(req.getPoBox()))
						.fax(StringUtils.isBlank(req.getFax())?"":req.getFax())
						.emirate(req.getCityId())
						.status(StringUtils.isBlank(req.getStatus())?"Y":req.getStatus())
						.customerId(new BigDecimal(pi.getCustomerId()))
						.branchCode(req.getBranchCode())
						.approvedPreparedBy(req.getApprovedBy())
						.rsaBrokerCode(req.getBrokerCode())
						.acExecutiveId(StringUtils.isBlank(req.getExeutiveBDM())?null:new BigDecimal(req.getExeutiveBDM()))
						.brokerType(req.getUserType())
						.build();
				bcmRepo.save(brokerCompanyMaster);
				response.setMessage("SUCCESS");
				Map<String,Object> result = new HashMap<String,Object>();
				result.put("Message", "Broker saved successfully...");
				result.put("AgencyCode", brokerCode);
				response.setResponse(result);
			}else {
				response.setMessage("ERROR");
				response.setResponse(list);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			response.setMessage("FAILED");
			response.setResponse("Broker save failed...Contact Admin...!");
		}
		return response;
	}

	@Override
	public MadisonCommonRes saveBrokerProduct(BrokerProductSaveReq req) {
		MadisonCommonRes response = new MadisonCommonRes();
		log.info("saveBrokerProduct request :"+cs.printReq(req));
		try {
			List<ErrorList> error =validation.validateBrokerProduct(req);
			if(CollectionUtils.isEmpty(error)) {
				Long useridmax = null;
				if(StringUtils.isBlank(req.getUserId())) {
					useridmax = query.getmaxuserId();
				}
				LoginMaster lm =loginMasterRepo.findByAgencyCode(req.getAgencyCode());
				LoginUserDetails userDetails =LoginUserDetails.builder()
						.agencyCode(req.getAgencyCode())
						.userId(StringUtils.isBlank(req.getUserId())?BigDecimal.valueOf(useridmax):new BigDecimal(req.getUserId()))
						.productId(new BigDecimal(req.getProductId()))
						.userName(lm.getUsername())
						.loginId(lm.getLoginId())
						.oaCode(req.getAgencyCode())
						.companyId(lm.getCompanyId())
						.commission(new BigDecimal(req.getCommission()))
						.insuranceStartLimit(new BigDecimal("1000"))
						.insuranceEndLimit(new BigDecimal(req.getSumInsured()))
						.relativeUserId(new BigDecimal("0"))
						.amendId(new BigDecimal("1"))
						.inceptionDate(new Date())
						.entryDate(new Date())
						.status(StringUtils.isBlank(req.getStatus())?"Y":req.getStatus())
						.minPremiumAmount(new BigDecimal(req.getMinimumPre()))
						.backDateAllowed(new BigDecimal(req.getBackDateAllowed()))
						.freightDebitOption("N")
						.receiptStatus("N")
						.payReceiptStatus("N")
						.issuertype("88888")
						.isB2c("N")
						.build(); 
				userRepo.save(userDetails);
				
				BigDecimal amendId =userRepo.getAmendIdByAgencyCodeAndProductId(req.getAgencyCode(), req.getProductId());
				List<PolicyTypeReq> list =req.getPolicyType();
				for (PolicyTypeReq r :list) {
					
					String sumInsuredStart ="";
					String sumInsuredEnd ="";
					
					if("3".equals(r.getPolicyTypeId())) {
						sumInsuredStart ="0";
						sumInsuredEnd="0";
					}else {
						sumInsuredStart =r.getMinSiValue();
						sumInsuredEnd =r.getMaxSiValue();
					}
					
					BrokerCommissionDetail commissionDetail =BrokerCommissionDetail.builder()
							.agencyCode(req.getAgencyCode())
							.loginId(lm.getLoginId())
							.productId(new BigDecimal(req.getProductId()))
							.policytype(new BigDecimal(r.getPolicyTypeId()))
							.amendId(amendId)
							.policyFee(new BigDecimal(0))
							.otherFee(new BigDecimal(0))
							.suminsuredStart(new BigDecimal(sumInsuredStart))
							.suminsuredEnd(new BigDecimal(sumInsuredEnd))
							.endDate(new Date())
							.effectiveDate(sdf.parse(req.getEffectiveDate()))
							.endDate(sdf.parse(req.getEndDate()))
							.build();
					commisionRepo.saveAndFlush(commissionDetail);
				}
				response.setMessage("SUCCESS");
				response.setResponse("Saved successfully");
			}else {
				response.setMessage("ERROR");
				response.setResponse(error);
			}
		}catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			response.setMessage("FAILED");
			response.setResponse("Failed successfully");
		}
		return response;
	}

	@Override
	public MadisonCommonRes editProduct(GetOrEditProductReq req) {
		MadisonCommonRes response = new MadisonCommonRes();
		try {
			List<Tuple> list =query.editProduct(req);
			if(!CollectionUtils.isEmpty(list)) {
				
				String effectiveDate =list.get(0).get("effectiveDate")==null?"":list.get(0).get("effectiveDate").toString();
				String endDate =list.get(0).get("endDate")==null?"":list.get(0).get("endDate").toString();
				String commission =list.get(0).get("commission")==null?"":list.get(0).get("commission").toString();
				String productId =list.get(0).get("productId")==null?"":list.get(0).get("productId").toString();
				String sumInsured =list.get(0).get("insuranceEndLimit")==null?"":list.get(0).get("insuranceEndLimit").toString();
				String minimumPre =list.get(0).get("minPremiumAmount")==null?"":list.get(0).get("minPremiumAmount").toString();
                String backDaysAllowed =list.get(0).get("backDateAllowed")==null?"":list.get(0).get("backDateAllowed").toString();
				List<PolicyTypeRes> policyTypeRes =new ArrayList<PolicyTypeRes>();
                list.forEach(d->{
					String policyTypeId =d.get("policytype")==null?"":d.get("policytype").toString();
					String policyFee =d.get("policyFee")==null?"":d.get("policyFee").toString();
					String otherFee =d.get("otherFee")==null?"":d.get("otherFee").toString();
					String minSum =d.get("suminsuredStart")==null?"":d.get("suminsuredStart").toString();
					String maxSum =d.get("suminsuredEnd")==null?"":d.get("suminsuredEnd").toString();
					
					PolicyTypeRes policyType =PolicyTypeRes.builder()
							.maxSiValue(maxSum)
							.minSiValue(minSum)
							.policyTypeId(policyTypeId)
							.policyFee(policyFee)
							.otherFee(otherFee)
							.build();
					
					policyTypeRes.add(policyType);
				});
				EditProductRes productRes =EditProductRes.builder()
						.backDateAllowed(backDaysAllowed)
						.effectiveDate(effectiveDate)
						.commission(commission)
						.endDate(endDate)
						.sumInsured(sumInsured)
						.productId(productId)
						.minimumPre(minimumPre)
						.policyType(policyTypeRes)
						.build();
				response.setMessage("SUCCESS");
				response.setResponse(productRes);
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
	public MadisonCommonRes getExcludedMenu(getExcludedMenuReq req) {
		MadisonCommonRes res = new MadisonCommonRes();
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		try {
			List<Tuple> list = query.getExcludedproducts(req);
			if(!CollectionUtils.isEmpty(list) && StringUtils.isNotBlank(req.getSelProducts())){
				String selpro [] = req.getSelProducts().split(",");
				for(int i=0;i<selpro.length;i++) {
					Map<String,Object> resMap = new HashMap<String,Object>();
					List<Map<String,Object>> reslist = new ArrayList<>();
					reslist = query.getMenuByProduct(list.get(i).get("productId").toString(),req.getBranchCode());
					resMap.put("Code",list.get(i).get("productName").toString());
					resMap.put("Description", reslist);
					result.add(resMap);
				}
				res.setResponse(result);
				res.setMessage("SUCCESS");
			}else {
				res.setResponse(result);
				res.setMessage("FAILED");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public MadisonCommonRes getCustomerDetails(GetOrEditProductReq req) {
		MadisonCommonRes res = new MadisonCommonRes();
		List<GetCustomerDetailsRes> response = new ArrayList<GetCustomerDetailsRes>();
		try {
			List<Tuple> customerlist = query.getCustomerDetails(req);
			if(!CollectionUtils.isEmpty(customerlist)) {
				customerlist.forEach(m -> {
					GetCustomerDetailsRes k = GetCustomerDetailsRes.builder()
					.companyname(m.get("companyname")==null?"":m.get("companyname").toString())
					.customerloginId(m.get("customerloginId")==null?"":m.get("customerloginId").toString())
					.brokername(m.get("brokername")==null?"":m.get("brokername").toString())
					.entryDate(m.get("entryDate")==null?"":DateFormat(m.get("entryDate")).toString())
					.agencyCode(m.get("agencyCode")==null?"":m.get("agencyCode").toString())
					.fdCode(m.get("fdCode")==null?"":m.get("fdCode").toString())
					.pobox(m.get("pobox")==null?"":m.get("pobox").toString())
					.mobile(m.get("mobile")==null?"":m.get("mobile").toString())
					.cityname(m.get("cityname")==null?"":m.get("cityname").toString())
					.customerId(m.get("customerId")==null?"":m.get("customerId").toString())
					.missippiCustomerCode(m.get("missippiCustomerCode")==null?"":m.get("missippiCustomerCode").toString())
					.title(m.get("title")==null?"":m.get("title").toString())
					.address1(m.get("address1")==null?"":m.get("address1").toString())
					.address2(m.get("address2")==null?"":m.get("address2").toString())
					.custArNo(m.get("custArNo")==null?"":m.get("custArNo").toString())
					.address(m.get("address")==null?"":m.get("address").toString())
					.build();
					response.add(k);
				});
				res.setResponse(response);
				res.setMessage("SUCCESS");
			}else {
				res.setResponse(response);
				res.setMessage("FAILED");
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public MadisonCommonRes getcustomerdetailsByCustomerId(String customerId) {
		MadisonCommonRes res = new MadisonCommonRes();
		GetCustomerByCustomerIdRes response = new GetCustomerByCustomerIdRes();
		PersonalInfo pi = personalRepo.findByCustomerId(Long.valueOf(customerId));
		if(pi!=null) {
			response.setFirstname(pi.getFirstName());
			response.setLastName(pi.getLastName());
			response.setGender(pi.getGender());
			response.setNationality(pi.getNationality());
			response.setDob(pi.getDob()==null?"":pi.getDob().toString());
			response.setMobile(pi.getMobile());
			response.setPhone(pi.getTelephone());
			response.setFax(pi.getFax());
			response.setEmail(pi.getEmail());
			response.setAddress1(pi.getAddress1());
			response.setAddress2(pi.getAddress2());
			response.setOccupation(pi.getOccupation());
			response.setCountry(pi.getCountry());
			response.setPobox(pi.getPobox());
			response.setAgencyCode(pi.getAgencyCode());
			response.setCity(pi.getCity());			
			res.setResponse(response);
			res.setMessage("SUCCESS");
		}else {
			res.setResponse(response);
			res.setMessage("FAILED");
		}
		return res;
	}

	@Override
	public MadisonCommonRes getOpenCoverList(GetOpenCoverListReq req) {
		MadisonCommonRes res = new MadisonCommonRes();
		List<GetOpenCoverListRes> response = new ArrayList<GetOpenCoverListRes>();
		try {
			List<Tuple> list = query.getOpenCoverList(req);
			if(!CollectionUtils.isEmpty(list)) {
				list.forEach(m -> {
					GetOpenCoverListRes k = GetOpenCoverListRes.builder()
					.proposalNo(m.get("proposalno")==null?"":m.get("proposalno").toString())
					.openCoverNo(m.get("opencoverno")==null?"":m.get("opencoverno").toString())
					.status(m.get("status")==null?"":m.get("status").toString())
					.policyStartDate(m.get("policystartdate")==null?"":m.get("policystartdate").toString())
					.policyEndDate(m.get("policyenddate")==null?"":m.get("policyenddate").toString())
					.name(m.get("name")==null?"":m.get("name").toString())
					.renewalstatus(m.get("renewalstatus")==null?"":m.get("renewalstatus").toString())
					.missippiopencoverno(m.get("missippiopencoverno")==null?"":m.get("missippiopencoverno").toString())
					.build();
					response.add(k);
				});
				res.setResponse(response);
				res.setMessage("SUCCESS");
			}else {
				res.setResponse(response);
				res.setMessage("FAILED");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public MadisonCommonRes updatepassword(UpdatePasswordReq req) throws Exception {
		MadisonCommonRes res = new MadisonCommonRes();
		int count=0;
		try {
			count = query.updatepassword(req);
			if(count>0) {
				res.setMessage("SUCCESS");
				res.setResponse("Password Updated SuccessFully...");
			}else {
				res.setMessage("FAILED");
				res.setResponse("Updated Failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	

}
