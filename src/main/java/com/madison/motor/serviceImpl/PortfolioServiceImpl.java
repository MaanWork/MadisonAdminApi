package com.madison.motor.serviceImpl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.Tuple;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.madison.motor.entity.HomePositionMaster;
import com.madison.motor.entity.MotorDataDetail;
import com.madison.motor.repository.HomePositionMasterRepository;
import com.madison.motor.repository.MotorDataDetailRepository;
import com.madison.motor.request.GetPortFolioReq;
import com.madison.motor.request.ModifyRateDetReq;
import com.madison.motor.request.ModifyRateReq;
import com.madison.motor.request.PortfolioSearchReq;
import com.madison.motor.request.ReferalQuoteReq;
import com.madison.motor.request.ReferalSearchQuoteReq;
import com.madison.motor.request.UpdateQuotePremiumReq;
import com.madison.motor.response.GetPortfolioRecordRes;
import com.madison.motor.response.MadisonCommonRes;
import com.madison.motor.response.PortFolioSearchRes;
import com.madison.motor.response.PremiumRes;
import com.madison.motor.response.QuotePremiumInfoRes;
import com.madison.motor.response.ReferalQuoteRes;
import com.madison.motor.response.ReferalSearchQuoteRes;
import com.madison.motor.response.VehiclePremiumInfoRes;
import com.madison.motor.service.PortfolioService;
import com.madison.motor.utilityImpl.CommonService;
import com.madison.motor.utilityImpl.CriteriaQueryImpl;

@Service
public class PortfolioServiceImpl implements PortfolioService{
	
	Logger log =LogManager.getLogger(PortfolioServiceImpl.class);
	
	@Autowired
	private CommonService cs;
	
	@Autowired
	private HomePositionMasterRepository hpmRepo;
	
	@Autowired
	private MotorDataDetailRepository mddRepo;
	
	@Autowired
	private CriteriaQueryImpl query;

	private static SimpleDateFormat sdf =new SimpleDateFormat("dd/MM/yyyy");
	
	private static final  DecimalFormat df =new DecimalFormat("#.00");
	
	@Override
	public MadisonCommonRes portfolioSearch(PortfolioSearchReq req) {
		MadisonCommonRes res = new MadisonCommonRes();
		log.info("portfolioSearch request :"+cs.printReq(req));
		ArrayList<PortFolioSearchRes> array_list =new ArrayList<PortFolioSearchRes>();
		try {
			List<Tuple> list =query.portfolioSearch(req);
			if(!CollectionUtils.isEmpty(list)) {
				list.forEach(p ->{
					PortFolioSearchRes folioSearchRes =PortFolioSearchRes.builder()
							.entryDate(p.get("entryDate")==null?"":sdf.format(p.get("entryDate")))
							.totalRecordsCount(p.get("count")==null?"":p.get("count").toString())
							.ProductId(p.get("productId")==null?"":p.get("productId").toString())
							.build();
					array_list.add(folioSearchRes);
				});
				res.setResponse(array_list);
				res.setMessage("SUCCESS");
			}else {
				res.setResponse(array_list);
				res.setMessage("FAILED");
			}
		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return res;
	}


	@Override
	public MadisonCommonRes getPortFolioByDate(GetPortFolioReq req) {
		MadisonCommonRes res = new MadisonCommonRes();
		log.info("getPortFolioByDate request :"+cs.printReq(req));
		ArrayList<GetPortfolioRecordRes> array_list =new ArrayList<GetPortfolioRecordRes>();
		try {
			List<Tuple> list =query.getPortfoliaByDate(req);
			if(!CollectionUtils.isEmpty(list)) {
				list.forEach(p ->{
					GetPortfolioRecordRes portfolioRecordRes =  GetPortfolioRecordRes.builder()
							.brokerName(p.get("broker_name")==null?"":p.get("broker_name").toString())
							.customerName(p.get("customer_name")==null?"":p.get("customer_name").toString())
							.policyEndDate(p.get("expiryDate")==null?"":p.get("expiryDate").toString())
							.policyStartDate(p.get("inceptionDate")==null?"":p.get("inceptionDate").toString())
							.policyType(p.get("policy_type")==null?"":p.get("policy_type").toString())
							.premium(p.get("premium")==null?"":p.get("premium").toString())
							.quoteCreatedBy(p.get("loginId")==null?"":p.get("loginId").toString())
							.quoteNo(p.get("quoteNo")==null?"":p.get("quoteNo").toString())
							.policyNo(p.get("policyNo")==null?"":p.get("policyNo").toString())
							.paymentType(p.get("payment_type")==null?"":p.get("payment_type").toString())
							.build();
					
					array_list.add(portfolioRecordRes);
				});
				res.setResponse(array_list);
				res.setMessage("SUCCESS");
			}else {
				res.setResponse(array_list);
				res.setMessage("FAILED");
			}
		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return res;
	}


	@Override
	public MadisonCommonRes getReferalSearchQuote(ReferalSearchQuoteReq req) {
		MadisonCommonRes res = new MadisonCommonRes();
		log.info("getReferalSearchQuote request :"+cs.printReq(req));
		ArrayList<ReferalSearchQuoteRes> array_list =new ArrayList<ReferalSearchQuoteRes>();
		try {
			List<Tuple> list =query.getReferalSearchQuote(req);
			if(!CollectionUtils.isEmpty(list)) {
				list.forEach(p ->{
					ReferalSearchQuoteRes classQuoteRes =ReferalSearchQuoteRes.builder()
							.entryDate(p.get("entryDate")==null?"":sdf.format(p.get("entryDate")))
							.totalRecordsCount(p.get("count")==null?"":p.get("count").toString())
							.overallPremium(p.get("overallPremium")==null?"":p.get("overallPremium").toString())
							.build();
					array_list.add(classQuoteRes);
				});
				res.setResponse(array_list);
				res.setMessage("SUCCESS");
			}else {
				res.setResponse(array_list);
				res.setMessage("FAILED");
			}
		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return res;
	}


	@Override
	public MadisonCommonRes getReferalQuoteByDate(ReferalQuoteReq req) {
		MadisonCommonRes res = new MadisonCommonRes();
		log.info("getReferalQuoteByDate request :"+cs.printReq(req));
		ArrayList<ReferalQuoteRes> array_list =new ArrayList<ReferalQuoteRes>();
		try {
			List<Tuple> list =query.getReferalQuoteByDate(req);
			if(!CollectionUtils.isEmpty(list)) {
				list.forEach(p ->{
					ReferalQuoteRes referalQuoteRes = ReferalQuoteRes.builder()
							.brokerName(p.get("companyName")==null?"":p.get("companyName").toString())
							.applicationNo(p.get("applicationNo")==null?"":p.get("applicationNo").toString())
							.customerId(p.get("customerId")==null?"":p.get("customerId").toString())
							.customerName(p.get("customer_name")==null?"":p.get("customer_name").toString())
							.quoteCreated(p.get("quote_created")==null?"":p.get("quote_created").toString())
							.quoteNo(p.get("quoteNo")==null?"":p.get("quoteNo").toString())
							.remarks(p.get("summaryRemarks")==null?"":p.get("summaryRemarks").toString())
							.status(p.get("status")==null?"":p.get("status").toString())
					.build();
				array_list.add(referalQuoteRes);
					
				});
				res.setResponse(array_list);
				res.setMessage("SUCCESS");
			}else {
				res.setResponse(array_list);
				res.setMessage("FAILED");
			}
		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return res;
	}
	
	
	@Override
	public MadisonCommonRes getVehiclePremiumByAppNo(String appNo) {
		MadisonCommonRes commonRes = new MadisonCommonRes();
		List<VehiclePremiumInfoRes> response =new ArrayList<VehiclePremiumInfoRes>();
		try {
			List<Tuple> list =query.getPremiumByVehIdAndAppNo(appNo);
			if(!CollectionUtils.isEmpty(list)) {
				Map<Object,List<Tuple>> groupBy = list.stream().collect(Collectors.groupingBy(g ->g.get("vehicleId"))); 
				for (Map.Entry<Object,List<Tuple>> key :groupBy.entrySet()) {
					Object vehicleId =key.getKey();
					List<Tuple> value =key.getValue();
					VehiclePremiumInfoRes res = new VehiclePremiumInfoRes();
					List<PremiumRes> premium_list =new ArrayList<PremiumRes>();
					value.forEach(v ->{
						PremiumRes premiumRes =PremiumRes.builder()
								.description(v.get("groupDesc")==null?"":v.get("groupDesc").toString())
								.sumInsured(v.get("sumInsured")==null?"":v.get("sumInsured").toString())
								.rate(v.get("rate")==null?"":v.get("rate").toString())
								.premium(v.get("premium")==null?"":v.get("premium").toString())
								.vehicleId(vehicleId.toString())
								.policyTypeCoverId(v.get("policytypeCoverid")==null?"":v.get("policytypeCoverid").toString())
								.build(); 
						premium_list.add(premiumRes);
						
					MotorDataDetail mdd =mddRepo.findByApplicationNoAndVehicleId(new BigDecimal(appNo),new BigDecimal(vehicleId.toString()));
						res.setMake(StringUtils.isBlank(mdd.getMakeName())?"":mdd.getMakeName());
						res.setModel(StringUtils.isBlank(mdd.getModelName())?"":mdd.getModelName());
						res.setVehicleUsage(StringUtils.isBlank(mdd.getVehicleUsageName())?"":mdd.getVehicleUsageName());
						res.setVehcileId(vehicleId.toString());
						res.setApplicationNo(appNo);
					});
					res.setPremium(premium_list);
					response.add(res);
				}	
				
				commonRes.setResponse(response.stream().sorted(Comparator.comparing(VehiclePremiumInfoRes ::getVehcileId)));
				commonRes.setMessage("SUCCESS");
			}else {
				commonRes.setResponse(response);
				commonRes.setMessage("FAILED");
			}
		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return commonRes;
	}


	@Override
	public MadisonCommonRes getQuotePremiumInfo(String applicationNo) {
		MadisonCommonRes response =new MadisonCommonRes();
		try {
			Double premium =query.getPremiumByApplicationId(applicationNo);
			Tuple tuple =query.getPolicyPercentByAppNo(applicationNo);
			String branchCode =tuple.get("branchCode")==null?"":tuple.get("branchCode").toString();
			String excessSign =tuple.get("excessSign")==null?"":tuple.get("excessSign").toString();
			String excessPremium =tuple.get("excessPremium")==null?"":tuple.get("excessPremium").toString();
			String policyFeePercent=tuple.get("policyFeePercent")==null?"0":tuple.get("policyFeePercent").toString();
			Double policy_tax =0D;
			Double net_premium =0D;
			if(StringUtils.isNotEmpty(excessSign) && !"0".equals(excessPremium)) {
				Double after_minus_excess =0D;
				if("-".equalsIgnoreCase(excessSign)) {
					after_minus_excess=premium - Double.valueOf(excessPremium);
				}else if("+".equalsIgnoreCase(excessSign)) {
					after_minus_excess =premium +  Double.valueOf(excessPremium);
				}
				
				policy_tax =after_minus_excess * Double.valueOf(policyFeePercent)/100;
				net_premium =after_minus_excess+policy_tax;
			}else {
				
				policy_tax =premium * Double.valueOf(policyFeePercent)/100;
				net_premium =premium+policy_tax;
			}
			
			Tuple quoteInfo =query.getQuoteInfoByAppNo(applicationNo);
						
			QuotePremiumInfoRes quotePremium = QuotePremiumInfoRes.builder()
					.excessSign(excessSign)
					.excessAmount(excessPremium)
					.premiumTax(df.format(policy_tax))
					.premium(df.format(premium))
					.netPremium(df.format(net_premium))
					.quoteNo(quoteInfo.get("quoteNo")==null?"":quoteInfo.get("quoteNo").toString())
					.currency(quoteInfo.get("currency")==null?"":quoteInfo.get("currency").toString())
					.email(quoteInfo.get("email")==null?"":quoteInfo.get("email").toString())
					.customerName(quoteInfo.get("companyName")==null?"":quoteInfo.get("companyName").toString())
					.policyType(quoteInfo.get("policy_type")==null?"":quoteInfo.get("policy_type").toString())
					.productName(quoteInfo.get("product")==null?"":quoteInfo.get("product").toString())
					.quoteCreatedDate(quoteInfo.get("quoteCreatedDate")==null?"":quoteInfo.get("quoteCreatedDate").toString())
					.branchCode(branchCode)
					.productId(quoteInfo.get("productId")==null?"":quoteInfo.get("productId").toString())
					.applicationNo(applicationNo)
					.build();
			response.setMessage("SUCCESS");
			response.setResponse(quotePremium);
		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			response.setMessage("FAILED");
			response.setResponse(null);
		}
		return response;
	}


	@Override
	public MadisonCommonRes updateQuotePremium(UpdateQuotePremiumReq req) {
		MadisonCommonRes response = new MadisonCommonRes();
		log.info("updateQuotePremium request :" +cs.printReq(req));
		try {
			Tuple tuple =query.getPolicyPercentByAppNo(req.getApplicationNo());
			String policyPercent =tuple.get("policyFeePercent")==null?"":tuple.get("policyFeePercent").toString();
			String quoteNo =tuple.get("quoteNo")==null?"":tuple.get("quoteNo").toString();
			String premium =StringUtils.isBlank(req.getPremium())?"0":req.getPremium();
			String excessSign =StringUtils.isBlank(req.getExcessSign())?"":req.getExcessSign();
			String excessAmount =StringUtils.isBlank(req.getExcessAmount())?"0":req.getExcessAmount();
			Double after_excess =0D;
			Double policy_tax =0D;
			Double net_premium=0D;
			Double commision_fee=0D;
			if(StringUtils.isEmpty(excessSign) && !"0".equals(excessAmount)) {
				if("_".equals(excessSign)) {
					
					after_excess =Double.valueOf(premium) - Double.valueOf(excessAmount) ;
					
				}else if("+".equals(excessSign)) {
					
					after_excess =Double.valueOf(premium) + Double.valueOf(excessAmount) ;
	
				}
				
				policy_tax =after_excess * Double.valueOf(policyPercent)/100;
				net_premium =after_excess + policy_tax;
			}else {
				
				policy_tax =Double.valueOf(premium) * Double.valueOf(policyPercent)/100;
				net_premium =Double.valueOf(premium) + policy_tax;
			}
			
			Tuple login_tuple=query.getLoginDetailsByAppNo(req.getApplicationNo());
			String productId =login_tuple.get("productId")==null?"":login_tuple.get("productId").toString();
			String loginId =login_tuple.get("loginId")==null?"":login_tuple.get("loginId").toString();
			Double commision_precent=query.getCommisionByLoginId(loginId, productId);
			if(commision_precent!=0) {
				commision_fee =Double.valueOf(premium) * commision_precent/100;
			}else {
				commision_fee=0D;
			}
			query.updatePolicyPremium(net_premium, policy_tax, excessSign, excessAmount,net_premium,commision_fee,
					quoteNo,req);
			response.setMessage("SUCCESS");
			response.setResponse("Premium updated successfully");
		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			response.setMessage("FAILED");
			response.setResponse("Something went wrong contact admin...!");
		}
		return response;
	}


	@Override
	public MadisonCommonRes modifyRate(ModifyRateReq req) {
		MadisonCommonRes response = new MadisonCommonRes();
		log.info("modifyRate request :"+cs.printReq(req));
		try {
			List<ModifyRateDetReq> modifyList =req.getModifyRate();
			for (ModifyRateDetReq rate :modifyList) {
				query.updateVehiclePremimByVehicleId(req.getApplicationNo(),req.getVehicleId(),req.getCurrencyType(),rate);		
			}	
			Double premimum=0D;
			Double electrical_pre=0D;
			Double nonelectrical_pre=0D;
			List<Tuple> mpcdList = query.getMotorPolicyCoverData(req.getApplicationNo(), req.getVehicleId());
			for(Tuple t :mpcdList) {
				String coverId=t.get("policytypeCoverid")==null?"":t.get("policytypeCoverid").toString();
				if("0".equals(coverId)) {
					premimum=t.get("sumInsured")==null?0D:Double.valueOf(t.get("sumInsured").toString());
				}else if("101".equals(coverId)) {
					electrical_pre=t.get("sumInsured")==null?0D:Double.valueOf(t.get("sumInsured").toString());
				}else if ("102".equals(coverId)) {
					nonelectrical_pre =t.get("sumInsured")==null?0D:Double.valueOf(t.get("sumInsured").toString());
				}
			}
			
			query.updateMotorDataDetailByAppNoAndVehId(req.getApplicationNo(), req.getVehicleId(),
					req.getCurrencyType(),premimum,electrical_pre,nonelectrical_pre);
			
			HomePositionMaster hpm =hpmRepo.findByApplicationNo(new BigDecimal(req.getApplicationNo()));
			query.callProcedure(req.getApplicationNo(),req.getVehicleId(),hpm.getBranchCode(),hpm.getProductId());
			response.setMessage("SUCCESS");
			response.setResponse("Premium updated successfully");
		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			response.setMessage("FAILED");
			response.setResponse("Something went wrong contact admin...!");
		}
		return response;
	}


	@Override
	public MadisonCommonRes editModifyRate(String applicationNo, String vehicleId) {
		MadisonCommonRes commonRes = new MadisonCommonRes();
		VehiclePremiumInfoRes response =new VehiclePremiumInfoRes();
		try {
			List<Tuple> list =query.editModifyRate(applicationNo, vehicleId);
			if(!CollectionUtils.isEmpty(list)) {
				List<PremiumRes> premium_list=new ArrayList<PremiumRes>();
				list.forEach(v ->{
						PremiumRes premiumRes =PremiumRes.builder()
								.description(v.get("groupDesc")==null?"":v.get("groupDesc").toString())
								.sumInsured(v.get("sumInsured")==null?"":v.get("sumInsured").toString())
								.rate(v.get("rate")==null?"":v.get("rate").toString())
								.premium(v.get("premium")==null?"":v.get("premium").toString())
								.vehicleId(vehicleId.toString())
								.policyTypeCoverId(v.get("policytypeCoverid")==null?"":v.get("policytypeCoverid").toString())
								.build(); 
						premium_list.add(premiumRes);
				});
				MotorDataDetail mdd =mddRepo.findByApplicationNoAndVehicleId(new BigDecimal(applicationNo),new BigDecimal(vehicleId));
				response.setMake(StringUtils.isBlank(mdd.getMakeName())?"":mdd.getMakeName());
				response.setModel(StringUtils.isBlank(mdd.getModelName())?"":mdd.getModelName());
				response.setVehicleUsage(StringUtils.isBlank(mdd.getVehicleUsageName())?"":mdd.getVehicleUsageName());
				response.setVehcileId(vehicleId);
				response.setApplicationNo(applicationNo);
				response.setPremium(premium_list);
				
				commonRes.setMessage("SUCCESS");
				commonRes.setResponse(response);
			}else {
				commonRes.setMessage("FAILED");
				commonRes.setResponse(null);
			}
		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return commonRes;
	}
}
