package com.madison.motor.utilityImpl;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.Trimspec;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.madison.motor.entity.BranchMaster;
import com.madison.motor.entity.BrokerCommissionDetail;
import com.madison.motor.entity.BrokerCompanyMaster;
import com.madison.motor.entity.CountryMaster;
import com.madison.motor.entity.CurrencyMaster;
import com.madison.motor.entity.ExchangeMaster;
import com.madison.motor.entity.HomePositionMaster;
import com.madison.motor.entity.InstallmentDetails;
import com.madison.motor.entity.ListItemValue;
import com.madison.motor.entity.LoginMaster;
import com.madison.motor.entity.LoginUserDetails;
import com.madison.motor.entity.MenuMaster;
import com.madison.motor.entity.MotorClaimIntimationDtl;
import com.madison.motor.entity.MotorDataDetail;
import com.madison.motor.entity.MotorGroupMaster;
import com.madison.motor.entity.MotorPolicyCoverData;
import com.madison.motor.entity.MotorPolicyDetails;
import com.madison.motor.entity.MotorPolicytypeMaster;
import com.madison.motor.entity.OpenCoverMaster;
import com.madison.motor.entity.OpenCoverPositionMaster;
import com.madison.motor.entity.PaymentDetail;
import com.madison.motor.entity.PersonalInfo;
import com.madison.motor.entity.ProductMaster;
import com.madison.motor.entity.RoadSideAssistantDetails;
import com.madison.motor.request.GetEmiReportReq;
import com.madison.motor.request.GetOpenCoverListReq;
import com.madison.motor.request.GetOrEditProductReq;
import com.madison.motor.request.GetPortFolioReq;
import com.madison.motor.request.GetProductReq;
import com.madison.motor.request.GetReferalByQuoteReq;
import com.madison.motor.request.GetReferalEntryDateReq;
import com.madison.motor.request.GetReferralProductReq;
import com.madison.motor.request.GetUserDetailsReq;
import com.madison.motor.request.ModifyRateDetReq;
import com.madison.motor.request.PortfolioSearchReq;
import com.madison.motor.request.ProductReq;
import com.madison.motor.request.ReferalQuoteReq;
import com.madison.motor.request.ReferalSearchQuoteReq;
import com.madison.motor.request.RejectEditReq;
import com.madison.motor.request.RoadAssistantListReq;
import com.madison.motor.request.UpdatePasswordReq;
import com.madison.motor.request.UpdateQuotePremiumReq;
import com.madison.motor.request.getExcludedMenuReq;

@Component
public class CriteriaQueryImpl {
	
	
	Logger log =LogManager.getLogger(CriteriaQueryImpl.class);
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private CommonService cs;
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public List<Tuple> portfolioSearch(PortfolioSearchReq req) {
		List<Predicate> predicates =new ArrayList<Predicate>(10);
		try {
			CriteriaBuilder cb =em.getCriteriaBuilder();
			CriteriaQuery<Tuple> query =cb.createTupleQuery();
			Root<HomePositionMaster> hpm =query.from(HomePositionMaster.class);
			Root<PersonalInfo> pi =query.from(PersonalInfo.class);
			
			predicates.add(cb.equal(hpm.get("customerId"), pi.get("customerId")));
			predicates.add(cb.equal(cb.lower(hpm.get("status")), req.getStatus().toLowerCase()));
			predicates.add(cb.between(cb.function("to_char",Date.class,hpm.get("entryDate"),cb.literal("DD/MM/YYYY")), cb.function("to_date", Date.class, cb.parameter(String.class,"startDate")),
					cb.function("to_date", Date.class, cb.parameter(String.class,"endDate"))));
			
			Subquery<String> login_id =query.subquery(String.class);
			Root<LoginMaster> lm =login_id.from(LoginMaster.class);
			
			Subquery<String> oa_code =login_id.subquery(String.class);
			Root<BrokerCompanyMaster> bcm =oa_code.from(BrokerCompanyMaster.class);
			oa_code.select(bcm.get("agencyCode")).where(bcm.get("branchCode").in(req.getBranchCode()));

			predicates.add(hpm.get("loginId").in(login_id.select(lm.get("loginId")).where(lm.get("oaCode").in(oa_code))));
			predicates.add(hpm.get("productId").in(new BigDecimal(req.getProductId())));
			
			Predicate [] predicateArray =new Predicate[predicates.size()];
			predicates.toArray(predicateArray);
			
			query.multiselect(cb.function("trunc", Date.class,hpm.get("entryDate")).alias("entryDate"),
				 cb.count(hpm.get("inceptionDate")).alias("count"),hpm.get("productId").alias("productId"))
				.where(predicateArray)
				.groupBy(cb.function("trunc", Date.class, hpm.get("entryDate")),hpm.get("productId"))
				.orderBy(cb.desc(cb.function("trunc", Date.class, hpm.get("entryDate"))));
				
			
			return em.createQuery(query)
					.setParameter("endDate", req.getEndDate())
					.setParameter("startDate", req.getStartDate()).getResultList();
		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}  
		return null;
	}
	
	public List<Tuple> getPortfoliaByDate(GetPortFolioReq req){
		List<Predicate> predicates = new ArrayList<Predicate>(10);
		try {
			CriteriaBuilder cb =em.getCriteriaBuilder();
			CriteriaQuery<Tuple> query =cb.createTupleQuery();
			Root<HomePositionMaster> hpm =query.from(HomePositionMaster.class);
			Root<PersonalInfo> pi =query.from(PersonalInfo.class);
			
			
			Subquery<String> broker_name =query.subquery(String.class);
			Root<BrokerCompanyMaster> bcm1 =broker_name.from(BrokerCompanyMaster.class);
			
			Subquery<String> agency_code=broker_name.subquery(String.class);
			Root<LoginMaster> lm1 =agency_code.from(LoginMaster.class);
			
			Subquery<String> oa_code1=agency_code.subquery(String.class);
			Root<LoginMaster> lm2 =oa_code1.from(LoginMaster.class);
			
			broker_name.select(bcm1.get("companyName")).where(bcm1.get("agencyCode").in(agency_code));
			oa_code1.select(lm2.get("oaCode")).distinct(true).where(cb.equal(lm2.get("loginId"),hpm.get("loginId")));
			agency_code.select(lm1.get("agencyCode")).where(lm1.get("agencyCode").in(oa_code1));		
			
		
			Subquery<String> policy_type=query.subquery(String.class);
			Root<MotorPolicytypeMaster>ptm =policy_type.from(MotorPolicytypeMaster.class);
			Root<MotorDataDetail>mdd =policy_type.from(MotorDataDetail.class);
			policy_type.select(ptm.get("policytypeDescEnglish")).where(cb.equal(ptm.get("policytypeId"), mdd.get("policytype"))
					,cb.equal(hpm.get("quoteNo"),mdd.get("quoteNo"))).distinct(true);
			
			Subquery<String> payment_type=query.subquery(String.class);
			Root<ListItemValue> liv =payment_type.from(ListItemValue.class);
			Root<PaymentDetail> pd =payment_type.from(PaymentDetail.class);
			payment_type.select(liv.get("itemValue")).where(cb.equal(liv.get("itemCode"), pd.get("paymentType"))
					,cb.equal(cb.upper(liv.get("itemType")),"PAYMENTTYPE"),cb.equal(pd.get("quoteNo"),hpm.get("quoteNo"))
					).distinct(true);
			
			Subquery<String> product_name=query.subquery(String.class);
			Root<ProductMaster> pm =product_name.from(ProductMaster.class);
			product_name.select(pm.get("productName")).where(cb.equal(hpm.get("productId"), pm.get("productId"))).distinct(true);
			
			Subquery<String> login_id =query.subquery(String.class);
			Root<LoginMaster> lm =login_id.from(LoginMaster.class);
			
			Subquery<BigDecimal> amend_id =query.subquery(BigDecimal.class);
			Root<PersonalInfo> spi =amend_id.from(PersonalInfo.class);
			amend_id.select(cb.max(spi.get("amendId"))).where(cb.equal(pi.get("customerId"), spi.get("customerId")));

			Subquery<String> oa_code =login_id.subquery(String.class);
			Root<BrokerCompanyMaster> bcm =oa_code.from(BrokerCompanyMaster.class);
			oa_code.select(bcm.get("agencyCode")).where(bcm.get("branchCode").in(req.getBranchCode()));

			login_id.select(lm.get("loginId")).where(lm.get("oaCode").in(oa_code));
			
			predicates.add(cb.equal(cb.function("to_char", Date.class,hpm.get("entryDate"),cb.literal("DD/MM/YYYY")),cb.parameter(String.class,"entryDate")));
			predicates.add(cb.equal(hpm.get("customerId"), pi.get("customerId")));
			predicates.add(cb.equal(cb.lower(hpm.get("status")), req.getType()));
			predicates.add(cb.equal(pi.get("amendId"),amend_id));
			predicates.add(hpm.get("loginId").in(login_id));
			
			if(!"P".equalsIgnoreCase(req.getType()) || !"D".equalsIgnoreCase(req.getType()))
				predicates.add(cb.isNotNull(hpm.get("premium")));
			
			Expression<String> customer_name =cb.concat(cb.concat(pi.get("firstName"), " "),pi.get("lastName"));
			Expression<Double>premium =cb.sum(cb.coalesce(hpm.get("premium"), 0D),cb.coalesce(hpm.get("excessPremium"), 0D));
						
			Predicate predicate_array []= new Predicate[predicates.size()];
			predicates.toArray(predicate_array);
			
			query.multiselect(hpm.get("quoteNo").alias("quoteNo"),hpm.get("remarks").alias("remarks"),
					customer_name.alias("customer_name"),pi.get("companyName").alias("companyName"),
					hpm.get("policyNo").alias("policyNo"),cb.function("to_char", Date.class, hpm.get("entryDate"),cb.literal("DD/MM/YYY")).alias("entryDate"),
					premium.alias("premium"),cb.coalesce(hpm.get("excessPremium"), 0).alias("excessPremium"),
					hpm.get("loginId").alias("loginId"),cb.function("to_char", Date.class, hpm.get("inceptionDate"),cb.literal("DD/MM/YYYY")).alias("inceptionDate"),
					cb.function("to_char", java.util.Date.class, hpm.get("expiryDate"),cb.literal("DD/MM/YYYY")).alias("expiryDate"),hpm.get("applicationNo").alias("applicationNo"),
					product_name.alias("product_name"),hpm.get("productId").alias("productId"),policy_type.alias("policy_type"),
					payment_type.alias("payment_type"),broker_name.alias("broker_name")
					)
				.where(predicate_array);
			
			return em.createQuery(query)
					.setParameter("entryDate", req.getEntryDate())
					.getResultList();
			

		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		
		return null;
	}
	


	public List<Tuple> ClaimIntimationList(String status) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
		Root<MotorClaimIntimationDtl> mRoot = cq.from(MotorClaimIntimationDtl.class);
		
		Expression<String> selectstatus = mRoot.get("status");
		Expression<Object> casestatus = cb.selectCase(selectstatus)
				.when("A", "Approved")
				.when("R", "Rejected")
				.when("P", "Pending")
				.otherwise(selectstatus);
		
		cq.multiselect(mRoot.get("name").alias("name"),
				mRoot.get("nrcPassportNo").alias("nrcPassportNo"),
				mRoot.get("phoneno").alias("phoneno"),
				mRoot.get("policynumber").alias("policynumber"),
				mRoot.get("vehicleRefno").alias("vehicleRefno"),
				mRoot.get("dateofaccident").alias("dateofaccident"),
				casestatus.alias("status"),
				mRoot.get("entrydate").alias("entrydate"),
				mRoot.get("remarks").alias("remarks"),
				mRoot.get("claimid").alias("claimid"),
				mRoot.get("claimref").alias("claimref"))
		
		.where(cb.equal(mRoot.get("status"), status));
		
		return em.createQuery(cq).getResultList();
	}


	public List<Tuple> getRejectEdit(RejectEditReq req) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
		Root<MotorClaimIntimationDtl> mRoot = cq.from(MotorClaimIntimationDtl.class);
		
		cq.multiselect(mRoot.get("name").alias("name"),
				mRoot.get("nrcPassportNo").alias("nrcPassportNo"),
				mRoot.get("phoneno").alias("phoneno"),
				mRoot.get("policynumber").alias("policynumber"),
				mRoot.get("vehicleRefno").alias("vehicleRefno"),
				mRoot.get("dateofaccident").alias("dateofaccident"),
				mRoot.get("status").alias("status"),
				mRoot.get("entrydate").alias("entrydate"),
				mRoot.get("remarks").alias("remarks"),
				mRoot.get("claimid").alias("claimid"),
				mRoot.get("claimref").alias("claimref"))
		
		.where(cb.equal(mRoot.get("policynumber"), req.getPolicyNumber()),
				cb.equal(mRoot.get("claimref"), req.getClaimref()));
		
		return em.createQuery(cq).getResultList();
	}

	public Long getClaimIdSeq() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<MotorClaimIntimationDtl> cRoot = cq.from(MotorClaimIntimationDtl.class);
		
		cq.multiselect(cb.coalesce(cb.max(cRoot.get("claimid")), 1));
		
		return em.createQuery(cq).getSingleResult();
		
	}
	
	public List<Tuple> getReferalSearchQuote(ReferalSearchQuoteReq req){
		List<Predicate> predicates =new ArrayList<Predicate>();
		try {
			CriteriaBuilder cb =em.getCriteriaBuilder();
			CriteriaQuery<Tuple> query =cb.createTupleQuery();
			Root<HomePositionMaster> hpm =query.from(HomePositionMaster.class);
			
			if("P".equalsIgnoreCase(req.getQuoteStatus())) {
				predicates.add(hpm.get("status").in(Arrays.asList("Y","E")));
				predicates.add(cb.equal(hpm.get("remarks"), "Referal"));

			}
			else if("A".equalsIgnoreCase(req.getQuoteStatus())) {
				predicates.add(hpm.get("status").in(Arrays.asList("Y","E")));
				predicates.add(cb.equal(hpm.get("remarks"), "Admin"));

			}
			else if("R".equalsIgnoreCase(req.getQuoteStatus())) {
				predicates.add(hpm.get("status").in(Arrays.asList("R")));
				predicates.add(cb.equal(hpm.get("adminReferralStatus"), "N"));

			}
			
			String is_all_branch =StringUtils.isBlank(req.getIsAllBranch())?"N":req.getIsAllBranch();
			String [] branch_array=null;
			if("Y".equalsIgnoreCase(is_all_branch)) {
				branch_array=new String[] {"01","02","03","04","05","06","07","08"};
			}else {
				branch_array=new String[] {req.getBranchCode()};
			}
			
			Subquery<String[]> login_id =query.subquery(String[].class);
			Root<LoginMaster> lm =login_id.from(LoginMaster.class);
			
			Subquery<String[]> oa_code =login_id.subquery(String[].class);
			Root<BrokerCompanyMaster> bcm =oa_code.from(BrokerCompanyMaster.class);
			oa_code.select(bcm.get("agencyCode")).where(bcm.get("branchCode")
					.in(Arrays.asList(branch_array)));
			
			predicates.add(cb.equal(hpm.get("productId"), new BigDecimal(req.getProductId())));
			predicates.add(hpm.get("loginId").in(login_id.select(lm.get("loginId")).where(lm.get("oaCode").in(oa_code))));
			predicates.add(hpm.get("branchCode").in(Arrays.asList(branch_array)));
		
			Predicate [] predicate_array =new Predicate[predicates.size()];
			predicates.toArray(predicate_array);
			
			query.multiselect(cb.function("trunc", Date.class, hpm.get("entryDate")).alias("entryDate")
					,cb.count(hpm.get("entryDate")).alias("count"),cb.sum(hpm.get("overallPremium")).alias("overallPremium"))
					.where(predicate_array)
					.groupBy(cb.function("trunc", Date.class, hpm.get("entryDate")))
					.orderBy(cb.desc(cb.function("trunc", Date.class, hpm.get("entryDate"))));
			
			return em.createQuery(query).getResultList();
						
		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public List<Tuple> getReferalQuoteByDate(ReferalQuoteReq req){
		List<Predicate> predicates =new ArrayList<Predicate>();
		try {
			CriteriaBuilder cb =em.getCriteriaBuilder();
			CriteriaQuery<Tuple> query =cb.createTupleQuery();
			Root<HomePositionMaster> hpm=query.from(HomePositionMaster.class);
			Root<PersonalInfo> pi =query.from(PersonalInfo.class);
			Root<BrokerCompanyMaster> bcm =query.from(BrokerCompanyMaster.class);
			
			Subquery<String> agency_code =query.subquery(String.class);
			Root<LoginMaster> lm2 =agency_code.from(LoginMaster.class);
			agency_code.select(lm2.get("oaCode")).where(cb.equal(lm2.get("loginId"), hpm.get("loginId")));
			
			String [] branch_array=new String[] {"01","02","03","04","05","06","07","08"};
			Subquery<String[]> login_id =query.subquery(String[].class);
			Root<LoginMaster> lm =login_id.from(LoginMaster.class);
			
			Subquery<String[]> oa_code =login_id.subquery(String[].class);
			Root<BrokerCompanyMaster> bcm2 =oa_code.from(BrokerCompanyMaster.class);
			oa_code.select(bcm2.get("agencyCode")).where(bcm2.get("branchCode")
					.in(Arrays.asList(branch_array)));
						
			if("P".equalsIgnoreCase(req.getQuoteStatus())) {
				predicates.add(hpm.get("status").in(Arrays.asList("Y","E")));
				predicates.add(cb.equal(hpm.get("remarks"), "Referal"));

			}
			else if("A".equalsIgnoreCase(req.getQuoteStatus())) {
				predicates.add(hpm.get("status").in(Arrays.asList("Y","E")));
				predicates.add(cb.equal(hpm.get("remarks"), "Admin"));

			}
			else if("R".equalsIgnoreCase(req.getQuoteStatus())) {
				predicates.add(hpm.get("status").in(Arrays.asList("R")));
				predicates.add(cb.equal(hpm.get("adminReferralStatus"), "N"));

			}
			
			predicates.add(cb.equal(hpm.get("customerId"), pi.get("customerId")));
			predicates.add(cb.equal(cb.function("to_char", Date.class, hpm.get("entryDate"),cb.literal("DD/MM/YYYY")),req.getEntryDate()));
			predicates.add(cb.equal(hpm.get("productId"), new BigDecimal(req.getProductId())));
			predicates.add(hpm.get("loginId").in(login_id.select(lm.get("loginId")).where(lm.get("oaCode").in(oa_code))));
			predicates.add(hpm.get("branchCode").in(Arrays.asList(branch_array)));
			predicates.add(cb.equal(bcm.get("agencyCode"), agency_code));

			Predicate [] predicates_array =new Predicate[predicates.size()];
			predicates.toArray(predicates_array);
	
			Expression<Object> quote_created =cb.selectCase().when(cb.equal(hpm.get("applicationId"), "1"), hpm.get("loginId")).otherwise(hpm.get("applicationId"));
			Expression<Object> quote_status =cb.selectCase().when(cb.equal(cb.upper(hpm.get("status")), "E"), "Endt").otherwise("Normal");
			
			query.multiselect(hpm.get("applicationNo").alias("applicationNo"),hpm.get("loginId").alias("loginId"),
					hpm.get("quoteNo").alias("quoteNo"),hpm.get("remarks").alias("remarks"),hpm.get("referralDescription")
				    .alias("referralDescription"),cb.coalesce(hpm.get("summaryRemarks"), "").alias("summaryRemarks"),
				    hpm.get("applicationId").alias("applicationId"),hpm.get("schemeId").alias("schemeId"),pi.get("customerId")
				    .alias("customerId"),cb.coalesce(pi.get("firstName"),pi.get("companyName")).alias("customer_name"),
				    bcm.get("companyName").alias("companyName"),quote_created.alias("quote_created"),quote_status.alias("status"))
					
			.where(predicates_array)
			.orderBy(cb.asc(hpm.get("quoteNo")));
			
			
			return em.createQuery(query).getResultList();
								
		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Double getPremiumByApplicationId(String applicationNo) {
		Double premium =0D;
		try {
			CriteriaBuilder cb =em.getCriteriaBuilder();
			CriteriaQuery<Double> query =cb.createQuery(Double.class);
			Root<MotorPolicyCoverData> mpcd =query.from(MotorPolicyCoverData.class);
			Expression<Object> case_condi =cb.selectCase().when(cb.equal(cb.upper(mpcd.get("currencyId")), "USD"), cb.coalesce(mpcd.get("usdPremium"),0D))
					.otherwise(cb.coalesce(mpcd.get("premium"),0D));
			Expression<Double> sum_premium =cb.sum(case_condi.as(Double.class));
			
			Subquery<BigDecimal> amend_id =query.subquery(BigDecimal.class);
			Root<MotorPolicyCoverData> mpcd_1 =amend_id.from(MotorPolicyCoverData.class);
			amend_id.select(cb.max(mpcd_1.get("amendId"))).where(cb.equal(mpcd_1.get("quoteNo"),mpcd.get("quoteNo")));
			
			query.select(sum_premium)
			.where(cb.equal(mpcd.get("applicationNo"), applicationNo),cb.equal(mpcd.get("amendId"), amend_id));
			
			premium =em.createQuery(query).getSingleResult();
						
		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		
		return premium;
	}
	
	
	public Tuple getPolicyPercentByAppNo(String applicationNo) {
		try {
			CriteriaBuilder cb =em.getCriteriaBuilder();
			CriteriaQuery<Tuple> query =cb.createTupleQuery();
			Root<HomePositionMaster> hpm =query.from(HomePositionMaster.class);
			Root<BranchMaster> bm =query.from(BranchMaster.class);
			query.multiselect(hpm.get("branchCode").alias("branchCode"),hpm.get("excessSign").alias("excessSign"),
					cb.coalesce(hpm.get("excessPremium"),0).alias("excessPremium"),bm.get("policyFeePercent").alias("policyFeePercent"),
					hpm.get("quoteNo").alias("quoteNo"))					
				.where(cb.equal(hpm.get("applicationNo"), applicationNo),cb.equal(hpm.get("branchCode"), bm.get("branchCode")));	
			
			return em.createQuery(query).getSingleResult();
		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return null;
	}
	
	@Transactional
	public Integer updatePolicyPremium(Object premium,Object policyFees,Object excessSign,
			Object excessPremium,Double net_premium, Double commision_fee, String quoteNo, UpdateQuotePremiumReq req) {
		Integer count =0;
		try {
			CriteriaBuilder cb =em.getCriteriaBuilder();
			CriteriaUpdate<HomePositionMaster> updateQuery =cb.createCriteriaUpdate(HomePositionMaster.class);
			Root<HomePositionMaster> hpm =updateQuery.from(HomePositionMaster.class);
			updateQuery.set(hpm.get("premium"), new BigDecimal(premium.toString()));
			updateQuery.set(hpm.get("policyFee"), new BigDecimal(policyFees.toString()));
			updateQuery.set(hpm.get("excessSign"), excessSign);
			updateQuery.set(hpm.get("excessPremium"), new BigDecimal(excessPremium.toString()));
			updateQuery.set(hpm.get("overallPremium"), new BigDecimal(net_premium.toString()));
			updateQuery.set(hpm.get("commission"), new BigDecimal(commision_fee.toString()));
			updateQuery.set(hpm.get("adminRemarks"), req.getRemarks());
			updateQuery.set(hpm.get("adminReferralStatus"), "N");
			updateQuery.set(hpm.get("installmentYn"),StringUtils.isBlank(req.getInstallmentYN())?"N":req.getInstallmentYN());
			
			java.util.Date effectiveDate = java.util.Date.from(LocalDate.now().plusDays(30).atStartOfDay(ZoneId.systemDefault()).toInstant());
			updateQuery.set(hpm.get("effectiveDate"),effectiveDate);
			if("Y".equalsIgnoreCase(req.getQuoteStatus())) {
				
				updateQuery.set(hpm.get("remarks"), "Admin");
				updateQuery.set(hpm.get("status"), "Y");
				
			}else if("N".equalsIgnoreCase(req.getQuoteStatus())) {
				
				updateQuery.set(hpm.get("remarks"), "Referal");
				updateQuery.set(hpm.get("status"), "R");

			}else if("A".equalsIgnoreCase(req.getQuoteStatus())) {
				
				updateQuery.set(hpm.get("remarks"), "Referal");
				updateQuery.set(hpm.get("status"), "Y");

			}
			
			CriteriaQuery<String> userName =cb.createQuery(String.class);
			Root<LoginMaster> lm =userName.from(LoginMaster.class);
			userName.select(cb.coalesce(lm.get("username"),"")).where(cb.equal(lm.get("loginId"), req.getLoginId()));
			
			
			
			updateQuery.set(hpm.get("approvedBy"), em.createQuery(userName).getSingleResult());
			updateQuery.where(cb.equal(hpm.get("quoteNo"),quoteNo));
			
			em.createQuery(updateQuery).executeUpdate();
		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return count;
	}
	
	public Tuple getQuoteInfoByAppNo(String applicationNo) {
		try {
			CriteriaBuilder cb=em.getCriteriaBuilder();
			CriteriaQuery<Tuple> query=cb.createTupleQuery();
			Root<HomePositionMaster> hpm =query.from(HomePositionMaster.class);
			Root<PersonalInfo> pi =query.from(PersonalInfo.class);
			Root<MotorPolicyDetails> mpd =query.from(MotorPolicyDetails.class);
			
			Expression<Object> case_con =cb.selectCase().when(cb.equal(hpm.get("productId"), "65"), "MOTOR INSURANCE")
					.otherwise("NON MOTOR INSURANCE");
			
			Subquery<String> policy_type =query.subquery(String.class);
			Root<MotorPolicytypeMaster> mptm =policy_type.from(MotorPolicytypeMaster.class);
			policy_type.select(mptm.get("policytypeDescEnglish")).distinct(true)
			.where(cb.equal(mptm.get("policytypeId"), mpd.get("policytype")));
			
			query.multiselect(hpm.get("quoteNo").alias("quoteNo"),cb.function("to_char", Date.class, hpm.get("entryDate"),
					cb.literal("DD/MM/YYYY")).alias("quoteCreatedDate"),hpm.get("currency").alias("currency"),pi.get("email")
					.alias("email"),pi.get("companyName").alias("companyName"),case_con.alias("product"),policy_type.alias("policy_type"),
					hpm.get("productId").alias("productId"),pi.get("firstName").alias("firstName"),pi.get("lastName").alias("lastName"))
				
			.where(cb.equal(hpm.get("applicationNo"), applicationNo),cb.equal(hpm.get("customerId"),pi.get("customerId"))
						,cb.equal(hpm.get("applicationNo"), mpd.get("applicationno")),cb.equal(hpm.get("quoteNo"), mpd.get("quoteno")));
				
			return em.createQuery(query).getSingleResult();
					
		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Tuple> getPremiumByVehIdAndAppNo(String applicationNo){
		try {
			CriteriaBuilder cb =em.getCriteriaBuilder();
			CriteriaQuery<Tuple> query =cb.createQuery(Tuple.class);
			Root<MotorPolicyCoverData> mpd =query.from(MotorPolicyCoverData.class);
			
			Expression<Object> sumInsured =cb.selectCase().when(cb.equal(cb.upper(mpd.get("currencyId")),"USD"), mpd.get("usdSumInsured"))
														.otherwise(mpd.get("sumInsured"));		
			
			Expression<Object> rate=cb.selectCase().when(cb.equal(cb.upper(mpd.get("currencyId")), "USD"),
					cb.selectCase().when(cb.equal(mpd.get("groupId"),"103"), cb.prod(mpd.get("usdRate"), 100))
				    .otherwise(mpd.get("usdRate"))).otherwise(cb.selectCase().when(cb.equal(mpd.get("groupId"), "103"), 
					cb.prod(mpd.get("rate"), 100)).otherwise(mpd.get("rate")));
			
			Expression<Object> premium =cb.selectCase().when(cb.equal(cb.upper(mpd.get("currencyId")),"USD" ), mpd.get("usdPremium"))
					                                   .otherwise(mpd.get("premium")); 
			
			Subquery<String> calc_type =query.subquery(String.class);
			Root<MotorGroupMaster> mgm =calc_type.from(MotorGroupMaster.class);
			Subquery<BigDecimal> amendId =calc_type.subquery(BigDecimal.class);
			Root<MotorGroupMaster> mgm2 =amendId.from(MotorGroupMaster.class);
			
			amendId.select(cb.max(mgm2.get("amendId"))).where(cb.equal(mgm2.get("groupId"),mgm.get("groupId")));
			calc_type.select(mgm.get("calcType")).where(cb.equal(mgm.get("groupId"),mpd.get("groupId")),
					cb.equal(mgm.get("amendId"), amendId));
					
			query.multiselect(sumInsured.alias("sumInsured"),rate.alias("rate"),premium.alias("premium"),
					calc_type.alias("calc_type"),mpd.get("vehicleId").alias("vehicleId"),mpd.get("groupId").alias("groupId"),
					mpd.get("policytypeCoverid").alias("policytypeCoverid"),mpd.get("groupDesc").alias("groupDesc"),
					mpd.get("opcoverDesc").alias("opcoverDesc"))
			.where(cb.equal(mpd.get("applicationNo"), applicationNo))
			.orderBy(cb.asc(mpd.get("displayOrder")));
					
			return em.createQuery(query).getResultList();				
			
		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Tuple getLoginDetailsByAppNo(String applicationNo) {
		try {
			CriteriaBuilder cb =em.getCriteriaBuilder();
			CriteriaQuery<Tuple> query =cb.createTupleQuery();
			Root<HomePositionMaster> hpm =query.from(HomePositionMaster.class);
			query.multiselect(cb.coalesce(hpm.get("loginId"),0).alias("loginId"),
					cb.coalesce(hpm.get("proposalNo"), hpm.get("productId")).alias("productId"))
			.where(cb.equal(hpm.get("applicationNo"), applicationNo));
			return em.createQuery(query).getSingleResult();
		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Double getCommisionByLoginId(String loginId ,String productId) {
		List<Predicate> predicates = new ArrayList<Predicate>();
		try {
			CriteriaBuilder cb =em.getCriteriaBuilder();
			CriteriaQuery<Double> query =cb.createQuery(Double.class);
			Root<LoginMaster> lm =query.from(LoginMaster.class);
			Root<LoginUserDetails> lud=query.from(LoginUserDetails.class);
			
			predicates.add(cb.equal(lm.get("loginId"), loginId));
			predicates.add(cb.equal(lud.get("agencyCode"), lm.get("oaCode")));
			predicates.add(cb.equal(cb.upper(lm.get("status")), "Y"));
			predicates.add(cb.equal(cb.upper(lud.get("status")), "Y"));
			predicates.add(cb.equal(lud.get("productId"), productId));
			
			Subquery<BigDecimal> amend_id=query.subquery(BigDecimal.class);
			Root<LoginUserDetails> lud2 =amend_id.from(LoginUserDetails.class);
			amend_id.select(cb.max(lud2.get("amendId"))).where(cb.equal(lud2.get("loginId"),lm.get("loginId")),
					cb.equal(lud2.get("productId"), lud.get("productId")));
			predicates.add(cb.equal(lud.get("amendId"), amend_id));
			
			Predicate [] predicates_array =new Predicate[predicates.size()];
			predicates.toArray(predicates_array);
			
			query.select(cb.coalesce(lud.get("commission"), 0D).as(Double.class).alias("commission"))
				.where(predicates_array);
			
			return em.createQuery(query).getSingleResult();
		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Transactional
	public Integer updateVehiclePremimByVehicleId(String applicationNo, String vehicleId,
			String currencyType, ModifyRateDetReq req) {
		List<Predicate> predicates =new ArrayList<Predicate>();
		try {
			CriteriaBuilder cb =em.getCriteriaBuilder();
			CriteriaUpdate<MotorPolicyCoverData> update =cb.createCriteriaUpdate(MotorPolicyCoverData.class);
			Root<MotorPolicyCoverData> mpcd =update.from(MotorPolicyCoverData.class);
			
			Double rate =StringUtils.isBlank(req.getRate())?0D:"103".equals(req.getPolicyTypeCoverId())?
					Double.valueOf(req.getPolicyTypeCoverId())/100:Double.valueOf(req.getRate());			
			
			if("ZMW".equals(currencyType)) {
				update.set(mpcd.get("sumInsured"), new BigDecimal(req.getSumInsured()));
				update.set(mpcd.get("rate"), new BigDecimal(rate));
			}else if("USD".equals(currencyType)) {
				update.set(mpcd.get("usdSumInsured"), new BigDecimal(req.getSumInsured()));
				update.set(mpcd.get("usdRate"), new BigDecimal(rate));
			}
			
			update.set(mpcd.get("status"), "R");
			Subquery<BigDecimal> amend_id =update.subquery(BigDecimal.class);
			Root<MotorPolicyCoverData> mpcd2 =amend_id.from(MotorPolicyCoverData.class);
			amend_id.select(cb.max(mpcd2.get("amendId"))).where(cb.equal(mpcd.get("applicationNo"), mpcd2.get("applicationNo")),
					cb.equal(mpcd.get("policytypeCoverid"), mpcd2.get("policytypeCoverid")),cb.equal(mpcd.get("vehicleId"), mpcd2.get("vehicleId")),
					cb.equal(mpcd.get("branchCode"), mpcd2.get("branchCode")),cb.equal(mpcd.get("groupId"), mpcd2.get("groupId")));
					
			predicates.add(cb.equal(mpcd.get("applicationNo"), applicationNo));
			predicates.add(cb.equal(mpcd.get("policytypeCoverid"), req.getPolicyTypeCoverId()));
			predicates.add(cb.equal(mpcd.get("vehicleId"), vehicleId));
			predicates.add(cb.equal(mpcd.get("amendId"),amend_id));
			
			Predicate predicate_array [] =new Predicate[predicates.size()];
			predicates.toArray(predicate_array);
			
			update.where(predicate_array);
			
			return em.createQuery(update).executeUpdate();
			
		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return 0;
	}
	
	public List<Tuple> getMotorPolicyCoverData(String applicationNo,String vehicleId){
		try {
			CriteriaBuilder cb =em.getCriteriaBuilder();
			CriteriaQuery<Tuple> query =cb.createQuery(Tuple.class);
			Root<MotorPolicyCoverData> mpd =query.from(MotorPolicyCoverData.class);
			
			Expression<Object> sumInsured =cb.selectCase().when(cb.equal(cb.upper(mpd.get("currencyId")),"USD"), mpd.get("usdSumInsured"))
														.otherwise(mpd.get("sumInsured"));		
			
			Expression<Object> rate=cb.selectCase().when(cb.equal(cb.upper(mpd.get("currencyId")), "USD"),
					cb.selectCase().when(cb.equal(mpd.get("groupId"),"103"), cb.prod(mpd.get("usdRate"), 100))
				    .otherwise(mpd.get("usdRate"))).otherwise(cb.selectCase().when(cb.equal(mpd.get("groupId"), "103"), 
					cb.prod(mpd.get("rate"), 100)).otherwise(mpd.get("rate")));
			
			Expression<Object> premium =cb.selectCase().when(cb.equal(cb.upper(mpd.get("currencyId")),"USD" ), mpd.get("usdPremium"))
					                                   .otherwise(mpd.get("premium")); 
			
			Subquery<String> calc_type =query.subquery(String.class);
			Root<MotorGroupMaster> mgm =calc_type.from(MotorGroupMaster.class);
			Subquery<BigDecimal> amendId =calc_type.subquery(BigDecimal.class);
			Root<MotorGroupMaster> mgm2 =amendId.from(MotorGroupMaster.class);
			
			amendId.select(cb.max(mgm2.get("amendId"))).where(cb.equal(mgm2.get("groupId"),mgm.get("groupId")));
			calc_type.select(mgm.get("calcType")).where(cb.equal(mgm.get("groupId"),mpd.get("groupId")),
					cb.equal(mgm.get("amendId"), amendId));
					
			query.multiselect(sumInsured.alias("sumInsured"),rate.alias("rate"),premium.alias("premium"),
					calc_type.alias("calc_type"),mpd.get("vehicleId").alias("vehicleId"),mpd.get("groupId").alias("groupId"),
					mpd.get("policytypeCoverid").alias("policytypeCoverid"),mpd.get("groupDesc").alias("groupDesc"),
					mpd.get("opcoverDesc").alias("opcoverDesc"))
			
			.where(cb.equal(mpd.get("applicationNo"), applicationNo),cb.equal(mpd.get("vehicleId"), vehicleId),
					mpd.get("policytypeCoverid").in(Arrays.asList("0","101","102")))
			.orderBy(cb.asc(mpd.get("displayOrder")));
					
			return em.createQuery(query).getResultList();				
			
		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Transactional
	public Integer updateMotorDataDetailByAppNoAndVehId(String applicationNo,String vehicleId,String currencyType, 
			Double suminsured, Double electrical_pre, Double nonelectrical_pre) {
		try {
			CriteriaBuilder cb =em.getCriteriaBuilder();
			CriteriaUpdate<MotorDataDetail> update =cb.createCriteriaUpdate(MotorDataDetail.class);
			Root<MotorDataDetail> mdd =update.from(MotorDataDetail.class);
			
			if("ZMW".equals(currencyType)) {
				update.set(mdd.get("suminsuredValueLocal"), new BigDecimal(suminsured));
			}else if("USD".equals(currencyType)) {
				update.set(mdd.get("suminsuredValueForeign"),new BigDecimal(suminsured));
			}
			
			update.set(mdd.get("electricalSi"), new BigDecimal(electrical_pre));
			update.set(mdd.get("nonelectricalSi"), new BigDecimal(nonelectrical_pre));
			update.where(cb.equal(mdd.get("applicationNo"), applicationNo)
					,cb.equal(mdd.get("vehicleId"), vehicleId));
			
			return em.createQuery(update).executeUpdate();
					
		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}	
		return null;
	}

	public void callProcedure(String applicationNo, String vehicleId, String branchCode, BigDecimal bigDecimal) {
		try {
			StoredProcedureQuery procedure =em.createStoredProcedureQuery("MOTOR_PREMIUM_CALC_B2BV2")
					.registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
					.registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
					.registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
					.registerStoredProcedureParameter(4, Long.class, ParameterMode.IN) 
					.registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
					.registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
					.setParameter(1, "B")
					.setParameter(2, Long.valueOf(applicationNo))
					.setParameter(3, branchCode)
					.setParameter(4,bigDecimal.longValue())
					.setParameter(5, "Referal")
					.setParameter(6, Long.valueOf(vehicleId));					
			procedure.execute();
		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		
	}
	
	public List<Tuple> editModifyRate(String applicationNo,String vehicleId){
		try {
			CriteriaBuilder cb =em.getCriteriaBuilder();
			CriteriaQuery<Tuple> query =cb.createQuery(Tuple.class);
			Root<MotorPolicyCoverData> mpd =query.from(MotorPolicyCoverData.class);
			
			Expression<Object> sumInsured =cb.selectCase().when(cb.equal(cb.upper(mpd.get("currencyId")),"USD"), mpd.get("usdSumInsured"))
														.otherwise(mpd.get("sumInsured"));		
			
			Expression<Object> rate=cb.selectCase().when(cb.equal(cb.upper(mpd.get("currencyId")), "USD"),
					cb.selectCase().when(cb.equal(mpd.get("groupId"),"103"), cb.prod(mpd.get("usdRate"), 100))
				    .otherwise(mpd.get("usdRate"))).otherwise(cb.selectCase().when(cb.equal(mpd.get("groupId"), "103"), 
					cb.prod(mpd.get("rate"), 100)).otherwise(mpd.get("rate")));
			
			Expression<Object> premium =cb.selectCase().when(cb.equal(cb.upper(mpd.get("currencyId")),"USD" ), mpd.get("usdPremium"))
					                                   .otherwise(mpd.get("premium")); 
			
			Subquery<String> calc_type =query.subquery(String.class);
			Root<MotorGroupMaster> mgm =calc_type.from(MotorGroupMaster.class);
			Subquery<BigDecimal> amendId =calc_type.subquery(BigDecimal.class);
			Root<MotorGroupMaster> mgm2 =amendId.from(MotorGroupMaster.class);
			
			amendId.select(cb.max(mgm2.get("amendId"))).where(cb.equal(mgm2.get("groupId"),mgm.get("groupId")));
			calc_type.select(mgm.get("calcType")).where(cb.equal(mgm.get("groupId"),mpd.get("groupId")),
					cb.equal(mgm.get("amendId"), amendId));
					
			query.multiselect(sumInsured.alias("sumInsured"),rate.alias("rate"),premium.alias("premium"),
					calc_type.alias("calc_type"),mpd.get("vehicleId").alias("vehicleId"),mpd.get("groupId").alias("groupId"),
					mpd.get("policytypeCoverid").alias("policytypeCoverid"),mpd.get("groupDesc").alias("groupDesc"),
					mpd.get("opcoverDesc").alias("opcoverDesc"))
			
			.where(cb.equal(mpd.get("applicationNo"), applicationNo),cb.equal(mpd.get("vehicleId"), vehicleId))
			.orderBy(cb.asc(mpd.get("displayOrder")));
					
			return em.createQuery(query).getResultList();				
			
		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<Tuple> getUserListByBranchCode(GetUserDetailsReq req){
		List<Predicate> predicates =new ArrayList<Predicate>();
		try {
			CriteriaBuilder cb =em.getCriteriaBuilder();
			CriteriaQuery<Tuple> query =cb.createTupleQuery();
			Root<PersonalInfo> pi =query.from(PersonalInfo.class);
			Root<LoginMaster> lm =query.from(LoginMaster.class);
			
			
			Expression<Object> status =cb.selectCase().when(cb.equal(cb.upper(pi.get("status")), "Y"), "Active")
					.when(cb.equal(cb.upper(pi.get("status")), "N"), "DeActive").when(cb.equal(cb.upper(pi.get("status")), "D"), "Delete")
					.when(cb.equal(cb.upper(pi.get("status")), "L"), "Locked");
			
			Expression<String> customerName =cb.concat(cb.concat(pi.get("firstName"), " "),pi.get("lastName"));
			
			Subquery<String> userName =query.subquery(String.class);
			Root<LoginMaster> lm2 =userName.from(LoginMaster.class);
			userName.select(lm2.get("username")).distinct(true).where(cb.equal(lm2.get("loginId"), lm.get("oaCode")));
			
			Subquery<String> companyName =query.subquery(String.class);
			Root<BrokerCompanyMaster> bcm =companyName.from(BrokerCompanyMaster.class);
			companyName.select(bcm.get("companyName")).distinct(true).where(cb.equal(bcm.get("agencyCode"), lm.get("oaCode")));
			
			predicates.add(cb.equal(cb.upper(lm.get("usertype")), "USER"));
			predicates.add(cb.equal(pi.get("agencyCode"), lm.get("agencyCode")));
			predicates.add(cb.not(lm.get("loginId").in("NONE")));
			predicates.add(cb.equal(pi.get("applicationId"), "2"));
			predicates.add(cb.equal(lm.get("branchCode"), req.getBranchCode()));
		
			if(StringUtils.isNotBlank(req.getBorganization()))
				predicates.add(cb.equal(lm.get("oaCode"), req.getAgencyCode()));

			
			
			Predicate predicate_array []=new Predicate[predicates.size()];
			predicates.toArray(predicate_array);
			
			query.multiselect(cb.coalesce(customerName, pi.get("companyName")).alias("customerName"),
					lm.get("usertype").alias("usertype"),lm.get("loginId").alias("loginId"),pi.get("applicationId").alias("applicationId"),
					pi.get("agencyCode").alias("agencyCode"),pi.get("oaCode").alias("oaCode"),status.alias("status"),cb.function("to_char", Date.class, pi.get("entryDate"),cb.literal("DD/MM/YYY")).alias("entryDate"),
					userName.alias("username"),companyName.alias("brokerName"),pi.get("customerId").alias("customerId"))
			.where(predicate_array);
				 
			return em.createQuery(query).getResultList();
			
		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return null;
	}
	
	public Tuple editUserDetailsByAgencyCode(String agencyCode,Long customerId) {
		try {
			CriteriaBuilder cb=em.getCriteriaBuilder();
			CriteriaQuery<Tuple> query =cb.createTupleQuery();
			Root<PersonalInfo> pi =query.from(PersonalInfo.class);
			Root<LoginMaster> lm =query.from(LoginMaster.class);
			
			Subquery<String> userName =query.subquery(String.class);
			Root<LoginMaster> lm2 =userName.from(LoginMaster.class);
			userName.select(lm2.get("username")).distinct(true).where(cb.equal(lm2.get("agencyCode"), lm.get("oaCode")));
			
			Subquery<String> brokerCompanyName =query.subquery(String.class);
			Root<BrokerCompanyMaster> bcm =brokerCompanyName.from(BrokerCompanyMaster.class);
			brokerCompanyName.select(bcm.get("companyName")).distinct(true).where(cb.equal(bcm.get("agencyCode"), lm.get("oaCode")));
			
			Subquery<String> countryName =query.subquery(String.class);
			Root<CountryMaster> cm =countryName.from(CountryMaster.class);
			Subquery<Long> amend_id =countryName.subquery(Long.class);
			Root<CountryMaster> cm2 =amend_id.from(CountryMaster.class);
			amend_id.select(cb.max(cm2.get("amendId"))).where(cb.equal(cm2.get("countryId"), cm.get("countryId")));
			countryName.select(cm.get("countryName")).distinct(true).where(cb.equal(cm.get("countryId"), pi.get("country"))
					,cb.equal(cm.get("amendId"), amend_id));
				
			
			Subquery<String> nation_name =query.subquery(String.class);
			Root<CountryMaster> cm3 =nation_name.from(CountryMaster.class);
				nation_name.select(cm3.get("nationalityName")).distinct(true)
					.where(cb.equal(cm3.get("countryId"), pi.get("nationality")));
			
			query.multiselect(pi.get("title").alias("title"),pi.get("gender").alias("gender"),pi.get("firstName").alias("firstName"),
					pi.get("lastName").alias("lastName"),pi.get("nationality").alias("nationality"),cb.function("to_char", Date.class, pi.get("dob"),cb.literal("DD/MM/YYY")).alias("dob"),
					pi.get("telephone").alias("telephone"),pi.get("mobile").alias("mobile"),pi.get("fax").alias("fax"),pi.get("email").alias("email"),
					pi.get("address1").alias("address1"),pi.get("address2").alias("address2"),pi.get("occupation").alias("occupation"),pi.get("emirate").alias("emirate"),
					pi.get("country").alias("countryId"),pi.get("pobox").alias("pobox"),lm.get("agencyCode").alias("agencyCode"),cb.function("to_char", Date.class, pi.get("entryDate"),cb.literal("DD/MM/YYYY")).alias("entryDate"),
					pi.get("status").alias("status"),pi.get("city").alias("city"),lm.get("branchCode").alias("branchCode"),lm.get("loginId").alias("loginId"),lm.get("usertype").alias("usertype"),
					pi.get("custName").alias("custName"),pi.get("custArNo").alias("custArNo"),pi.get("customerId").alias("customerId"),lm.get("oaCode").alias("oaCode"),
					lm.get("attachedBranch").alias("attachedBranch"),lm.get("subBranch").alias("subBranch"),lm.get("isB2c").alias("isB2c"),
					userName.alias("userName"),countryName.alias("countryName"),nation_name.alias("nationalityName"))
			
			.where(cb.equal(pi.get("agencyCode"), agencyCode),cb.equal(pi.get("agencyCode"), lm.get("agencyCode")),
					cb.equal(pi.get("loginId"), lm.get("loginId")),cb.equal(pi.get("customerId"), customerId));
					
			return em.createQuery(query).getSingleResult();				
					
		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<Tuple> getCustomerDetailsByAgencyCode(String agencyCode,String appId){
		try {
			CriteriaBuilder cb=em.getCriteriaBuilder();
			CriteriaQuery<Tuple> query =cb.createTupleQuery();
			Root<PersonalInfo> pi =query.from(PersonalInfo.class);
			
			Subquery<String> broker_name =query.subquery(String.class);
			Root<BrokerCompanyMaster> bcm =broker_name.from(BrokerCompanyMaster.class);
			broker_name.select(bcm.get("companyName")).distinct(true).where(cb.equal(bcm.get("agencyCode"), pi.get("agencyCode")));
			
			Subquery<String> loginId =query.subquery(String.class);
			Root<LoginMaster> lm =loginId.from(LoginMaster.class);
			loginId.select(lm.get("loginId")).where(cb.equal(lm.get("agencyCode"),agencyCode));
			
			query.multiselect(cb.coalesce(pi.get("firstName"), pi.get("companyName")).alias("customerName"),
					cb.coalesce(pi.get("agencyCode"), "0").alias("agencyCode"),cb.coalesce(pi.get("fdCode"), "0").alias("email"),
					pi.get("pobox").alias("pobox"),pi.get("mobile").alias("mobile"),pi.get("emirate").alias("cityName"),
					pi.get("customerId").alias("customerId"),pi.get("missippiCustomerCode").alias("missippiCustomerCode"),
					pi.get("title").alias("title"),pi.get("address1").alias("address1"),pi.get("address2").alias("address2"),
					pi.get("custArNo").alias("custArNo"),cb.function("to_char", Date.class, pi.get("entryDate"),cb.literal("DD/MM/YYYY")),
					pi.get("customerLoginId").alias("customerLoginId"),broker_name.alias("brokerName"))
				
			.where(cb.equal(pi.get("applicationId"), appId),pi.get("loginId").in(loginId))
			.orderBy(cb.asc(pi.get("firstName")));
					
			return em.createQuery(query).getResultList();
			
		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Tuple> getAdminDeatilsByBranchCode(String branchCode){
		try {
			CriteriaBuilder cb =em.getCriteriaBuilder();
			CriteriaQuery<Tuple> query =cb.createTupleQuery();
			Root<LoginMaster> lm =query.from(LoginMaster.class);
			
			Expression<Object> status =cb.selectCase().when(cb.equal(lm.get("status"), "Y"), "Active")
					.when(cb.equal(lm.get("status"), "N"), "DeActive").when(cb.equal(lm.get("status"), "D"), "Delete")
					.when(cb.equal(lm.get("status"), "L"), "Locked");
			
			Subquery<String>branc_name =query.subquery(String.class);
			Root<BranchMaster> bm =branc_name.from(BranchMaster.class);
			branc_name.select(bm.get("branchName")).distinct(true).where(cb.equal(bm.get("branchCode"), lm.get("branchCode")));
			
			query.multiselect(lm.get("loginId").alias("loginId"),lm.get("username").alias("userName"),
					lm.get("usertype").alias("usertype"),status.alias("status"),branc_name.alias("branch_name"))
					
			.where(lm.get("userid").in(Arrays.asList("2","4")),cb.equal(lm.get("branchCode"), branchCode));	
				
			return em.createQuery(query).getResultList();
		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return null;
	}
	
	public Tuple editAdminDetailsByLoginId(String loginId, String branchCode) {
		try {
			CriteriaBuilder cb =em.getCriteriaBuilder();
			CriteriaQuery<Tuple> query =cb.createTupleQuery();
			Root<LoginMaster> lm =query.from(LoginMaster.class);
			
			Expression<Object> status =cb.selectCase().when(cb.equal(lm.get("status"), "Y"), "Active")
					.when(cb.equal(lm.get("status"), "N"), "DeActive").when(cb.equal(lm.get("status"), "D"), "Delete")
					.when(cb.equal(lm.get("status"), "L"), "Locked");
			
			Subquery<String>branc_name =query.subquery(String.class);
			Root<BranchMaster> bm =branc_name.from(BranchMaster.class);
			branc_name.select(bm.get("branchName")).distinct(true).where(cb.equal(bm.get("branchCode"), lm.get("branchCode")));
					
			query.multiselect(lm.get("loginId").alias("loginId"),lm.get("username").alias("userName"),lm.get("onlineYn").alias("onlineYn"),
					lm.get("usertype").alias("usertype"),status.alias("status"),branc_name.alias("branch_name"),lm.get("mobileNo").alias("mobileNo"),
					lm.get("branchCode").alias("branchCode"),cb.trim(Trimspec.BOTH,lm.get("productId")).alias("productId"),lm.get("menuId").alias("menuId"),
					lm.get("brokerCodes").alias("brokerCodes"),lm.get("userMail").alias("userMail"),lm.get("attachedUw").alias("attachedUw"),
					lm.get("attachedBranch").alias("attachedBranch"),lm.get("firstName").alias("firstName"),lm.get("lastName").alias("lastName"),
					lm.get("middleName").alias("middleName"),lm.get("status").alias("statusId"))
					
					
			.where(lm.get("userid").in(Arrays.asList("2","4")),cb.equal(lm.get("branchCode"), branchCode),cb.equal(lm.get("loginId"), loginId));
					
					
			return em.createQuery(query).getSingleResult();	
				
		
		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public Long getClaimIdSum() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<MotorClaimIntimationDtl> cRoot = cq.from(MotorClaimIntimationDtl.class);
		
		cq.multiselect(cb.coalesce(cb.max(cb.sum(cRoot.get("claimid"), 1L)), 1L).alias("claimid"));
		
		return em.createQuery(cq).getSingleResult().longValue();
		
	}

	public Long getClaimRefSeq() {
		Query q = em.createNativeQuery("select MOTOR_CLAIM_INTIMATION_SEQ.nextval from dual");		
		Long nextVal = ((BigDecimal)q.getSingleResult()).longValue();
		return nextVal;
	}

	public List<Tuple> ForeignExchangeList() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
		Root<ExchangeMaster> eRoot = cq.from(ExchangeMaster.class);
		
		Subquery<String> currencyname = cq.subquery(String.class);
		Root<CurrencyMaster> cRoot = currencyname.from(CurrencyMaster.class);
		
		currencyname.select(cRoot.get("currencyname"))
		.where(cb.equal(cRoot.get("currencyid"), eRoot.get("currencyId")));
		
		Expression<String> selectstatus = eRoot.get("status");
		Expression<Object> casestatus = cb.selectCase(selectstatus)
				.when("Y", "Active")
				.when("N", "DeActive")
				.otherwise(selectstatus);
		
		Subquery<String> countryname = cq.subquery(String.class);
		Root<CountryMaster> cmRoot =countryname.from(CountryMaster.class);
		
		countryname.select(cmRoot.get("countryname"))
		.where(cb.equal(cmRoot.get("countryid"), eRoot.get("countryId")));
		
		Subquery<Integer> amdSub = cq.subquery(Integer.class);
		Root<ExchangeMaster> amRoot = amdSub.from(ExchangeMaster.class);
		
		amdSub.select(cb.max(amRoot.get("amendid")))
		.where(cb.equal(amRoot.get("exchId"), eRoot.get("exchId")));
		
		cq.multiselect(eRoot.get("exchId").alias("exchId"),
				eRoot.get("exchRate").alias("exchRate"),
				eRoot.get("currencyId").alias("currencyId"),
				currencyname.alias("currencyname"),
				eRoot.get("amendid").alias("amendid"),
				eRoot.get("incepDate").alias("incepDate"),
				eRoot.get("expirydate").alias("expirydate"),
				eRoot.get("effectivedate").alias("effectivedate"),
				eRoot.get("entrydate").alias("entrydate"),
				eRoot.get("remarks").alias("remarks"),
				casestatus.alias("status"),
				eRoot.get("rsacode").alias("rsacode"),
				eRoot.get("sno").alias("sno"),
				eRoot.get("countryId").alias("countryId"),
				countryname.alias("countryname"),
				eRoot.get("displayorder").alias("displayorder"))
		
		.where(cb.equal(eRoot.get("amendid"), amdSub))
		.orderBy(cb.asc(eRoot.get("sno")));
		
		return em.createQuery(cq).getResultList();
	}

	public List<Tuple> EditForeignExchange(String exchangeId) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
		Root<ExchangeMaster> eRoot = cq.from(ExchangeMaster.class);
		
		Subquery<String> currencyname = cq.subquery(String.class);
		Root<CurrencyMaster> cRoot = currencyname.from(CurrencyMaster.class);
		
		currencyname.select(cRoot.get("currencyname"))
		.where(cb.equal(cRoot.get("currencyid"), eRoot.get("currencyId")));
		
		Subquery<String> countryname = cq.subquery(String.class);
		Root<CountryMaster> cmRoot = countryname.from(CountryMaster.class);
		
		countryname.select(cmRoot.get("countryname"))
		.where(cb.equal(cmRoot.get("countryid"), eRoot.get("countryId")));
		
		Subquery<Integer> amdSub = cq.subquery(Integer.class);
		Root<ExchangeMaster> amdRoot = amdSub.from(ExchangeMaster.class);
		
		amdSub.select(cb.max(amdRoot.get("amendid")))
		.where(cb.equal(amdRoot.get("exchId"), eRoot.get("exchId")));
		
		cq.multiselect(eRoot.get("exchId").alias("exchId"),
				eRoot.get("exchRate").alias("exchRate"),
				eRoot.get("currencyId").alias("currencyId"),
				currencyname.alias("currencyname"),
				eRoot.get("amendid").alias("amendid"),
				eRoot.get("incepDate").alias("incepDate"),
				eRoot.get("expirydate").alias("expirydate"),
				eRoot.get("effectivedate").alias("effectivedate"),
				eRoot.get("entrydate").alias("entrydate"),
				eRoot.get("remarks").alias("remarks"),
				eRoot.get("status").alias("status"),
				eRoot.get("rsacode").alias("rsacode"),
				eRoot.get("sno").alias("sno"),
				eRoot.get("countryId").alias("countryId"),
				countryname.alias("countryname"),
				eRoot.get("displayorder").alias("displayorder"))
		
		.where(cb.equal(eRoot.get("amendid"), amdSub),
				cb.equal(eRoot.get("exchId"), exchangeId));
		
		return em.createQuery(cq).getResultList();
	}

	public List<Tuple> getRoadAssistantList(RoadAssistantListReq req) throws ParseException {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
		Root<RoadSideAssistantDetails> rRoot = cq.from(RoadSideAssistantDetails.class);
		
		Expression<Object> selectdevice = cb.selectCase().when(cb.equal(rRoot.get("deviceId"), "HYBRID"), "Mobile").otherwise("Webapp");
		
		Expression<Object> selectstatus = cb.selectCase().when(cb.equal(rRoot.get("status"), "P"), "PROCESSED")
				.when(cb.equal(rRoot.get("status"), "C"), "CLOSED").otherwise("OPEN");
		
		cq.multiselect(rRoot.get("refNo").alias("refNo"),
				rRoot.get("mobileno").alias("mobileno"),
				rRoot.get("policyNo").alias("policyNo"),
				rRoot.get("customerName").alias("customerName"),
				rRoot.get("assistantType").alias("assistantType"),
				rRoot.get("descripition").alias("descripition"),
				rRoot.get("logitude").alias("logitude"),
				rRoot.get("latitude").alias("latitude"),
				selectdevice.alias("deviceId"),
				rRoot.get("entrydate").alias("entrydate"),
				rRoot.get("location").alias("location"),
				selectstatus.alias("status"),
				rRoot.get("remarks").alias("remarks"),
				rRoot.get("mailId").alias("mailId"),
				rRoot.get("updatedate").alias("updatedate"),
				rRoot.get("customerfeedback").alias("customerfeedback"))
		
		.where(cb.between(cb.function("trunc", Date.class, rRoot.get("entrydate")), sdf.parse(req.getFromDate()), sdf.parse(req.getToDate())))
		.orderBy(cb.asc(rRoot.get("refNo")));
		
		return em.createQuery(cq).getResultList();
	}

	public List<Tuple> GetEmiReportCount(GetEmiReportReq req) throws ParseException {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
		Root<InstallmentDetails> iRoot = cq.from(InstallmentDetails.class);
		Root<HomePositionMaster> hRoot = cq.from(HomePositionMaster.class);
		
		cq.multiselect(cb.sum(iRoot.get("premiumamount")).alias("premiumamount"),
				cb.count(iRoot).alias("emiduecount"))
		.where(cb.equal(hRoot.get("quoteNo"), iRoot.get("quoteNo")),
				cb.equal(hRoot.get("status"), "P"),
				cb.equal(iRoot.get("status"), "Y"),
				cb.between(cb.function("trunc", Date.class, iRoot.get("premiumdate")), sdf.parse(req.getStartDate()), sdf.parse(req.getEndDate())),
				cb.equal(iRoot.get("productId"), req.getProductId()));
		
		return em.createQuery(cq).getResultList();
	}

	public List<Tuple> GetEmiReportList(GetEmiReportReq req) throws ParseException {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> cq= cb.createQuery(Tuple.class);
		Root<InstallmentDetails> iRoot = cq.from(InstallmentDetails.class);
		Root<HomePositionMaster> hRoot = cq.from(HomePositionMaster.class);
		
		Subquery<String> paymentmode = cq.subquery(String.class);
		Root<ListItemValue> pmRoot = paymentmode.from(ListItemValue.class);
		
		paymentmode.select(pmRoot.get("itemValue"))
		.where(cb.equal(hRoot.get("paymentMode"), pmRoot.get("itemCode")),
				cb.equal(pmRoot.get("itemType"), "PAYMENTTYPE"));
		
		Subquery<String> customername = cq.subquery(String.class);
		Root<PersonalInfo> pRoot = customername.from(PersonalInfo.class);
		
		Expression<String> custname = cb.concat(cb.concat(pRoot.get("firstName"), " "), pRoot.get("lastName"));
		customername.select(custname)
		.where(cb.equal(iRoot.get("custId"), pRoot.get("customerId")));
		
		cq.multiselect(iRoot.get("quoteNo").alias("quoteNo"),
				paymentmode.alias("paymentmode"),
				customername.alias("customername"),
				iRoot.get("overallpremium").alias("overallpremium"),
				iRoot.get("premiumamount").alias("premiumamount"),
				iRoot.get("premiumdate").alias("premiumdate"),
				iRoot.get("noofmonths").alias("noofmonths"),
				iRoot.get("noofemi").alias("noofemi"),
				iRoot.get("applicationNo").alias("applicationNo"),
				iRoot.get("noofterms").alias("noofterms"),
				iRoot.get("remarks").alias("remarks"),
				iRoot.get("installmentNo").alias("installmentNo"),
				iRoot.get("status").alias("status"),
				iRoot.get("balanceamount").alias("balanceamount"),
				iRoot.get("description").alias("description"),
				iRoot.get("productId").alias("productId"),
				hRoot.get("policyNo").alias("policyNo"),
				cb.selectCase().when(cb.equal(iRoot.get("paymentstatus"), "Y"), "Success")
				.otherwise("Pending").alias("paymentstatus"))
		
		.where(cb.equal(iRoot.get("quoteNo"), hRoot.get("quoteNo")),
				cb.equal(iRoot.get("productId"), req.getProductId()),
				cb.between(cb.function("trunc", Date.class, iRoot.get("premiumdate")), sdf.parse(req.getStartDate()), sdf.parse(req.getEndDate())),
				cb.equal(iRoot.get("status"), "Y"),
				cb.equal(hRoot.get("status"), "P"));
		
		return em.createQuery(cq).getResultList();
	}
	
	
	public List<Tuple> getBrokerDetailsByBranchCode(String branchCode, String appId){
		try {
			
			CriteriaBuilder cb =em.getCriteriaBuilder();
			CriteriaQuery<Tuple> query =cb.createTupleQuery();
			Root<PersonalInfo> pi =query.from(PersonalInfo.class);
			Root<BrokerCompanyMaster> bcm =query.from(BrokerCompanyMaster.class);
			
			Subquery<String> loginId =query.subquery(String.class);
			Root<LoginMaster> lm =loginId.from(LoginMaster.class);
			
			Subquery<String> agencyCode =loginId.subquery(String.class);
			Root<BrokerCompanyMaster> bcm2 =agencyCode.from(BrokerCompanyMaster.class);
			agencyCode.select(bcm2.get("agencyCode")).where(bcm2.get("branchCode").in(branchCode));
			
			loginId.select(lm.get("loginId")).where(lm.get("oaCode").in(agencyCode));
			
			
			Expression<Object> status =cb.selectCase().when(cb.equal(bcm.get("status"), "Y"), "Active")
					.when(cb.equal(bcm.get("status"), "N"), "DeActive").when(cb.equal(bcm.get("status"),"D"), "Delete")
					.when(cb.equal(bcm.get("status"), "L"),"Locked").otherwise("Error");
			
			query.multiselect(bcm.get("customerId").alias("customerId"),bcm.get("contactPerson").alias("contactPerson"),
					pi.get("agencyCode").alias("agencyCode"),bcm.get("rsaBrokerCode").alias("rsaBrokerCode"),status.alias("status"),
					pi.get("loginId").alias("loginId"),bcm.get("companyName").alias("companyName"),pi.get("entryDate").alias("entryDate"))
			
			.where(cb.equal(bcm.get("agencyCode"), pi.get("agencyCode")),bcm.get("branchCode").in(branchCode),
					cb.equal(pi.get("applicationId"), appId),pi.get("loginId").in(loginId))
			
			.orderBy(cb.asc(cb.lower(bcm.get("companyName"))));
					
					
			return em.createQuery(query).getResultList();	
					
		}catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		
		return null;
	}
	
	public Tuple editBrokerByAgencyCode(String agencyCode, String branchCode) {
		try {
			CriteriaBuilder cb =em.getCriteriaBuilder();
			CriteriaQuery<Tuple> query =cb.createTupleQuery();
			Root<PersonalInfo> pi =query.from(PersonalInfo.class);
			Root<BrokerCompanyMaster> bcm =query.from(BrokerCompanyMaster.class);
			Root<LoginMaster> lm =query.from(LoginMaster.class);
			
			Subquery<String> countryName =query.subquery(String.class);
			Root<CountryMaster> cm =countryName.from(CountryMaster.class);
			countryName.select(cm.get("countryName")).where(cb.or(cb.equal(cb.upper(cm.get("countryName")), cb.upper(pi.get("country"))),
					cb.equal(cm.get("countryId"),pi.get("country"))));
			
			Expression<Object> country =cb.selectCase(pi.get("country"))
					.when("N", countryName).otherwise(pi.get("country"));
			
			Subquery<String> nationalityname = query.subquery(String.class);
			Root<CountryMaster> nm = nationalityname.from(CountryMaster.class);
			
			nationalityname.select(nm.get("nationalityName")).where(cb.equal(nm.get("countryId"), pi.get("nationality")));
			
			Subquery<String> branchName = query.subquery(String.class);
			Root<BranchMaster> bn = branchName.from(BranchMaster.class);
			
			branchName.select(bn.get("branchName")).where(cb.in(bn.get("branchCode")).value(branchCode));
			
			query.multiselect(pi.get("title").alias("title"),pi.get("gender").alias("gender"),pi.get("firstName").alias("firstName"),
					pi.get("lastName").alias("lastName"),pi.get("nationality").alias("nationality"),pi.get("telephone").alias("telephone"),
					pi.get("fax").alias("fax"),pi.get("email").alias("email"),pi.get("mobile").alias("mobile"),pi.get("address1").alias("address1"),
					pi.get("address2").alias("address2"),pi.get("occupation").alias("occupation"),pi.get("emirate").alias("emirate"),
					pi.get("country").alias("country"),pi.get("pobox").alias("pobox"),bcm.get("companyName").alias("companyName"),
					bcm.get("agencyCode").alias("agencyCode"),cb.function("to_char", Date.class, pi.get("entryDate"),cb.literal("DD/MM/YYY")).alias("entryDate"),
					lm.get("status").alias("status"),bcm.get("address3").alias("address3"),bcm.get("city").alias("city"),bcm.get("branchCode").alias("branchCode"),
					bcm.get("missippiId").alias("missippiId"),bcm.get("approvedPreparedBy").alias("approvedPreparedBy"),bcm.get("rsaBrokerCode").alias("rsaBrokerCode"),
					lm.get("loginId").alias("loginId"),bcm.get("acExecutiveId").alias("acExecutiveId"),pi.get("custName").alias("custName"),pi.get("custArNo").alias("custArNo"),
					bcm.get("customerId").alias("customerId"),bcm.get("issuerCommissionOpencover").alias("issuerCommissionOpencover"),bcm.get("issuerCommissionOneoff")
					.alias("issuerCommissionOneoff"),bcm.get("imagePath").alias("imagePath"),lm.get("attachedBranch").alias("attachedBranch"),lm.get("subBranch").alias("subBranch"),
					bcm.get("brokerType").alias("brokerType"),country.alias("countryName"),cb.function("to_char", Date.class, pi.get("dob"),cb.literal("DD/MM/YYYY")).alias("dob"),
					nationalityname.alias("nationalityname"),branchName.alias("branchName"),pi.get("customerId").alias("CustId"),lm.get("userid").alias("userid"))
										
					
			.where(cb.equal(lm.get("agencyCode"), bcm.get("agencyCode")),cb.equal(pi.get("agencyCode"),lm.get("agencyCode")),
					cb.equal(pi.get("agencyCode"), agencyCode),cb.equal(pi.get("applicationId"), "2"),bcm.get("branchCode").in(branchCode));
				
			return em.createQuery(query).getSingleResult();
						
		}catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		
		return null;
	}
	
	public List<Tuple> getProductDetailsByAgencyCode(String agencyCode){
		try {
			CriteriaBuilder cb =em.getCriteriaBuilder();
			CriteriaQuery<Tuple> query =cb.createTupleQuery();
			Root<LoginMaster> lm =query.from(LoginMaster.class);
			Root<LoginUserDetails> lud =query.from(LoginUserDetails.class);
			Root<ProductMaster> pm =query.from(ProductMaster.class);
			
			query.multiselect(pm.get("productName").alias("productName"),lud.get("productId").alias("productId"),lud.get("commission").alias("commission"),
					lud.get("insuranceEndLimit").alias("insuranceEndLimit"),lud.get("specialDiscount").alias("specialDiscount"),cb.coalesce(lm.get("referal"), "N")
					.alias("referal"),lud.get("status").alias("status"),cb.coalesce(lud.get("discountOfPremium"), 0).alias("discountOfPremium"),lud.get("minPremiumAmount")
					.alias("minPremiumAmount"),lud.get("backDateAllowed").alias("backDateAllowed"),cb.coalesce(lud.get("proCommission"), 0).alias("proCommission"),
					cb.function("to_char", Date.class, lud.get("proStartDate"),cb.literal("DD/MM/YYYY")).alias("proStartDate"),cb.function("to_char", Date.class, 
					lud.get("proExpiryDate"),cb.literal("DD/MM/YYYY")).alias("proExpiryDate"),cb.coalesce(lud.get("loadingOfPremium"), 0).alias("loadingOfPremium"),
					cb.coalesce(lud.get("payReceiptStatus"), "N").alias("payReceiptStatus"),cb.coalesce(lud.get("receiptStatus"), "N").alias("receiptStatus"),
					cb.coalesce(lud.get("freightDebitOption"), "N").alias("freightDebitOption"),cb.coalesce(lud.get("provisionForPremium"), "N")
					.alias("provisionForPremium"))
					
				.where(cb.equal(lm.get("agencyCode"), lud.get("agencyCode")),cb.equal(lud.get("productId"),pm.get("productId")),
						cb.equal(lud.get("agencyCode"),agencyCode))
						
				.orderBy(cb.asc(lud.get("productId")));		
						
				return em.createQuery(query).getResultList();
					
		}catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return null;
	}
	
	public Long isProductExits(String productId,String agencyCode) {
		Long count =0L;
		try {
			CriteriaBuilder cb =em.getCriteriaBuilder();
			CriteriaQuery<Long> query =cb.createQuery(Long.class);
			Root<LoginUserDetails> lud =query.from(LoginUserDetails.class);
			query.select(cb.count(lud)).where(cb.equal(lud.get("productId"), productId),cb.equal(lud.get("agencyCode"), agencyCode));
			count =em.createQuery(query).getSingleResult();
		}catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return count;
	}

	@Transactional
	public Integer updateProductByAgencyCodeAndProductId(ProductReq p, String agencyCode,
			LoginMaster lm, PersonalInfo pi) {
		Integer count =0;
		try {
			CriteriaBuilder cb =em.getCriteriaBuilder();
			CriteriaUpdate<LoginUserDetails> query =cb.createCriteriaUpdate(LoginUserDetails.class);
			Root<LoginUserDetails> lud =query.from(LoginUserDetails.class);
			String op_cover ="";
			String issue_type ="Y".equals(lm.getIsB2c())?"90011":"88888";
			if ("11".equalsIgnoreCase(p.getProductId()))
				op_cover = p.getOpenCoverNo();
			
			//query.set(lud.get("commision"), "");
			query.set(lud.get("insuranceEndLimit"), StringUtils.isBlank(p.getSumInsured())?null:new BigDecimal(p.getSumInsured()));
			query.set(lud.get("status"), "Y");
			//query.set(lud.get("discountOfPremium"), "");
			//query.set(lud.get("minPremiumAmount"), "");
			//query.set(lud.get("backDateAllowed"), "");
			//query.set(lud.get("loadingOfPremium"), "");
			//query.set(lud.get("payReceiptStatus"), "");
			query.set(lud.get("freightDebitOption"), p.getCreditYn());
			query.set(lud.get("provisionForPremium"), "");
			//query.set(lud.get("receiptStatus"), "");
			query.set(lud.get("openCoverNo"), op_cover);
			query.set(lud.get("specialDiscount"), StringUtils.isBlank(p.getSpecialDiscount())? null:new BigDecimal(p.getSpecialDiscount()));
			query.set(lud.get("loginId"), lm.getLoginId());
			query.set(lud.get("issuertype"), issue_type);
			query.set(lud.get("isB2c"), lm.getIsB2c());
			
			query.where(cb.equal(lud.get("productId"), p.getProductId()),cb.equal(lud.get("agencyCode"), agencyCode));
			count =em.createQuery(query).executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return count;
		
	}
	
	
	//@PostConstruct
	public List<Map<String,Object>> getPoductDetailsByBranchCode(){
		try {
			String queryTxt ="SELECT pm.product_name uproductname, pm.product_id uproductid, lud.special_discount specialdis, "
					+ "lud.freight_debit_option freight, lud.insurance_end_limit insendlimit, lud.pay_receipt_status receipt,"
					+ "lud.open_cover_no, decode(lud.login_id, NULL, 'N', 'Y') product FROM product_master pm LEFT OUTER JOIN "
					+ "login_user_details lud ON pm.product_id = lud.product_id AND lud.agency_code =:agencyCode AND "
					+ "lud.oa_code =:oaCode WHERE pm.branch_code =( SELECT belonging_branch FROM branch_master WHERE branch_code =:branchCode) "
					+ "AND pm.status =:status ORDER BY lud.product_id";
			
			@SuppressWarnings("unchecked")
			List<Map<String,Object>>  list = em.createNativeQuery(queryTxt)
					.setParameter("agencyCode", "1000397")
					.setParameter("oaCode", "90075")
					.setParameter("branchCode", "01")
					.setParameter("status", "Y")
					//.unwrap(NativeQuery.class)
					.getResultList();
				
			System.out.println(list.size());
		}catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return null;
	}
	
	
	public List<Tuple> getPoductDetails(GetProductReq req){
		try {
			String queryTxt ="select pm.productName as product,pm.productId as productId,lud.specialDiscount as specialDiscount,"
					+ "lud.freightDebitOption as freightDebitOption,lud.insuranceEndLimit as insuranceEndLimit,lud.payReceiptStatus as payReceiptStatus,"
					+ "lud.openCoverNo,case when lud.loginId is null then 'N' else 'Y' end as status from ProductMaster pm left outer join "
					+ "LoginUserDetails lud on pm.productId=lud.productId and lud.agencyCode=:agencyCode and lud.oaCode=:oaCode where "
					+ "pm.branchCode=(select belongingBranch from BranchMaster bm where bm.branchCode=:branchCode) and pm.status=:status "
					+ "order by lud.productId asc";
			return em.createQuery(queryTxt,Tuple.class)
					.setParameter("agencyCode", req.getAgencyCode())
					.setParameter("oaCode", req.getOaCode())
					.setParameter("branchCode", req.getBranchCode())
					.setParameter("status", "Y")
					.getResultList();
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return null;
	}
	
	
	public List<Tuple> editProduct(GetOrEditProductReq req){
		try {
			CriteriaBuilder cb =em.getCriteriaBuilder();
			CriteriaQuery<Tuple> query =cb.createTupleQuery();
			Root<LoginMaster> lm =query.from(LoginMaster.class);
			Root<LoginUserDetails> lud =query.from(LoginUserDetails.class);
			Root<ProductMaster> pm =query.from(ProductMaster.class);
			Root<BrokerCommissionDetail> bcm =query.from(BrokerCommissionDetail.class);
			
			Subquery<BigDecimal> amendId =query.subquery(BigDecimal.class);
			Root<BrokerCommissionDetail> com =amendId.from(BrokerCommissionDetail.class);
			amendId.select(cb.max(com.get("amendId"))).where(cb.equal(bcm.get("agencyCode"), com.get("agencyCode")),
					cb.equal(bcm.get("productId"), com.get("productId"))
					);
			
			query.multiselect(pm.get("productName").alias("productName"),lud.get("productId").alias("productId"),lud.get("commission").alias("commission"),
					lud.get("insuranceEndLimit").alias("insuranceEndLimit"),lud.get("specialDiscount").alias("specialDiscount"),cb.coalesce(lm.get("referal"), "N")
					.alias("referal"),lud.get("status").alias("status"),cb.coalesce(lud.get("discountOfPremium"), 0).alias("discountOfPremium"),lud.get("minPremiumAmount")
					.alias("minPremiumAmount"),lud.get("backDateAllowed").alias("backDateAllowed"),cb.coalesce(lud.get("proCommission"), 0).alias("proCommission"),
					cb.function("to_char", Date.class, lud.get("proStartDate"),cb.literal("DD/MM/YYYY")).alias("proStartDate"),cb.function("to_char", Date.class, 
					lud.get("proExpiryDate"),cb.literal("DD/MM/YYYY")).alias("proExpiryDate"),cb.coalesce(lud.get("loadingOfPremium"), 0).alias("loadingOfPremium"),
					cb.coalesce(lud.get("payReceiptStatus"), "N").alias("payReceiptStatus"),cb.coalesce(lud.get("receiptStatus"), "N").alias("receiptStatus"),
					cb.coalesce(lud.get("freightDebitOption"), "N").alias("freightDebitOption"),cb.coalesce(lud.get("provisionForPremium"), "N")
					.alias("provisionForPremium"),cb.function("to_char", Date.class, bcm.get("effectiveDate"),cb.literal("DD/MM/YYY")).alias("effectiveDate"),
					cb.function("to_char", Date.class, bcm.get("endDate"),cb.literal("DD/MM/YYY")).alias("endDate"),bcm.get("policyFee").alias("policyFee"),
					bcm.get("otherFee").alias("otherFee"),bcm.get("suminsuredStart").alias("suminsuredStart"),bcm.get("suminsuredEnd").alias("suminsuredEnd"),
					bcm.get("policytype").alias("policytype"),bcm.get("amendId").alias("amendId"))
					
					
				.where(cb.equal(lm.get("agencyCode"), lud.get("agencyCode")),cb.equal(lud.get("productId"),pm.get("productId")),
						cb.equal(lud.get("agencyCode"),req.getAgencyCode()),cb.equal(lud.get("productId"), req.getProductId()),cb.equal(lud.get("agencyCode"), bcm.get("agencyCode")),
						cb.equal(lud.get("productId"), bcm.get("productId")),cb.equal(bcm.get("amendId"), amendId))
						
				.orderBy(cb.asc(lud.get("productId"))).orderBy(cb.asc(bcm.get("policytype")));		
					
			
				return em.createQuery(query).getResultList();
					
		}catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return null;
	}
	
	public List<Tuple> getNationality(){
		try {
			String queryTxt ="select cm.countryId as countryId,cm.nationalityName as nationalityName from CountryMaster cm where cm.amendId || cm.countryId in(select max(cm2.amendId)||"
					+ "cm2.countryId from CountryMaster cm2 group by cm2.countryId) and cm.nationalityName is not null order by "
					+ "cm.nationalityName asc";
			return em.createQuery(queryTxt,Tuple.class).getResultList();
		}catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return  null;
	}

	@SuppressWarnings("unchecked")
	public List<HashMap<String, Object>> getExeutiveBdm() {
		List<HashMap<String, Object>>  list=null;
		try {
			String queryTxt="SELECT DISTINCT b.ac_executive_id, b.ac_executive_name FROM login_executive_details b "
					+ "WHERE b.status = 'Y' AND amend_id =( SELECT MAX(a.amend_id) FROM login_executive_details a "
					+ "WHERE a.ac_executive_id = b.ac_executive_id) ORDER BY b.ac_executive_name";
			list = em.createNativeQuery(queryTxt)
					.unwrap(NativeQuery.class)
					.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
					.getResultList();
		}catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return list;
	}

	public List<Tuple> getcountryByBranchCode(String branchCode) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
		Root<CountryMaster> cmRoot = cq.from(CountryMaster.class);
		
		Subquery<Integer> amSub = cq.subquery(Integer.class);
		Root<CountryMaster> amRoot = amSub.from(CountryMaster.class);
		
		amSub.select(cb.max(amRoot.get("amendId"))).where(cb.equal(amRoot.get("countryName"), cmRoot.get("countryName")));
		
		Subquery<String> ciSub = cq.subquery(String.class);
		Root<BranchMaster> ciRoot = ciSub.from(BranchMaster.class);
		
		ciSub.select(ciRoot.get("originationCountryId")).where(cb.equal(ciRoot.get("branchCode"), branchCode));
		
		cq.multiselect(cmRoot.get("countryId").alias("code"),
				cmRoot.get("countryName").alias("description"))
		
		.where(cb.equal(cmRoot.get("amendId"), amSub),
				cb.equal(cmRoot.get("countryId"), ciSub));
		
		return em.createQuery(cq).getResultList();
	}

	public List<Tuple> getstatus() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
		Root<ListItemValue> lRoot = cq.from(ListItemValue.class);
		
		cq.multiselect(lRoot.get("itemCode").alias("code"),
				lRoot.get("itemValue").alias("description"))
		
		.where(cb.equal(lRoot.get("itemId"), "510"));
		
		return em.createQuery(cq).getResultList();
	}

	@Transactional
	public Long getmaxuserId() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<LoginUserDetails> lRoot = cq.from(LoginUserDetails.class);
		
		cq.multiselect(cb.coalesce(cb.max(cb.sum(lRoot.get("userId"), 1)), 1L).as(Long.class));
		
		return em.createQuery(cq).getSingleResult();
	}

	public List<Tuple> getAdminProduct(String branchCode, String agencyCode) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
		Root<ProductMaster> pRoot = cq.from(ProductMaster.class);
		
		List<Predicate> Predicates = new ArrayList<Predicate>();
		
		cq.multiselect(cb.trim(pRoot.get("productId")).alias("Code"),
				pRoot.get("productName").alias("Description"),
				pRoot.get("companyId").alias("companyId"));
		
		Subquery<String> pbranch = cq.subquery(String.class);
		Root<BranchMaster> pbRoot = pbranch.from(BranchMaster.class);
		
		Subquery<Integer> pAmend = cq.subquery(Integer.class);
		Root<BranchMaster> paRoot = pAmend.from(BranchMaster.class);
		
		pAmend.select(cb.max(paRoot.get("amendId")))
		.where(cb.equal(paRoot.get("branchCode"), pbRoot.get("branchCode")),
				cb.equal(paRoot.get("status"), pbRoot.get("status")));
		
		pbranch.select(pbRoot.get("belongingBranch"))
		.where(cb.equal(pbRoot.get("branchCode"), branchCode),
				cb.equal(pbRoot.get("status"), "Y"),
				cb.equal(pbRoot.get("amendId"), pAmend));	
		
		Predicates.add(cb.equal(pRoot.get("status"), "Y"));
		Predicates.add(cb.equal(pRoot.get("branchCode"), pbranch));
		
		Subquery<String> lpro = cq.subquery(String.class);
		Root<LoginUserDetails> lRoot = lpro.from(LoginUserDetails.class);
		
		lpro.select(lRoot.get("productId")).where(cb.equal(lRoot.get("agencyCode"), agencyCode));
		
		if(StringUtils.isNotBlank(agencyCode)) {
			Predicates.add(cb.not(cb.in(pRoot.get("productId")).value(lpro)));
		}
		
		Predicate [] PredicateArray = new Predicate[Predicates.size()];
		Predicates.toArray(PredicateArray);
		cq.where(PredicateArray);
		
		cq.orderBy(cb.asc(pRoot.get("productId")));
		
		return em.createQuery(cq).getResultList();
	}

	public List<Tuple> getExcludedproducts(getExcludedMenuReq req) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
		Root<ProductMaster> pRoot = cq.from(ProductMaster.class);
		List<BigDecimal> list = new ArrayList<BigDecimal>();
		
		if(StringUtils.isNotBlank(req.getSelProducts())) {
			String selpro [] = req.getSelProducts().split(",");
			for(int i=0;i<selpro.length;i++) {
				list.add(new BigDecimal(selpro[i].toString()));
			}
		}
		
		cq.multiselect(pRoot.get("productId").alias("productId"),
				pRoot.get("productName").alias("productName"))
		
		.where(pRoot.get("productId").in(list));
		
		return em.createQuery(cq).getResultList();
	}

	public List<Map<String,Object>> getMenuByProduct(String productId,String branchCode) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
		Root<MenuMaster> mRoot = cq.from(MenuMaster.class);
		
		Subquery<String> mbran = cq.subquery(String.class);
		Root<BranchMaster> bRoot = mbran.from(BranchMaster.class);
		
		mbran.select(bRoot.get("belongingBranch"))
		.where(cb.equal(bRoot.get("branchCode"), branchCode));
		
		cq.multiselect(mRoot.get("menuid").alias("menuid"),mRoot.get("menuname").alias("menuname"))
		
		.where(cb.equal(mRoot.get("status"), "Y"),
				cb.equal(mRoot.get("branchCode"), mbran),
				cb.equal(mRoot.get("productid"), productId),
				cb.equal(mRoot.get("usertype"), "admin"));
		
		List<Tuple> result =  em.createQuery(cq).getResultList();
		
		result.forEach(k->{
			Map<String,Object> resultlist = new HashMap<>();
			resultlist.put("Code", k.get("menuid"));
			resultlist.put("Description", k.get("menuname"));
			list.add(resultlist);
		});	
		return list;			
	}

	public String getadminpasswordbyloginId(String loginId) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<String> cq = cb.createQuery(String.class);
		Root<LoginMaster> lRoot = cq.from(LoginMaster.class);
		
		cq.select(lRoot.get("password")).where(cb.equal(lRoot.get("loginId"), loginId));
		
		return em.createQuery(cq).getSingleResult();
	}

	public Long getexistclaimId(String claimRef) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<MotorClaimIntimationDtl> mRoot = cq.from(MotorClaimIntimationDtl.class);
		
		cq.select(mRoot.get("claimid"))
		.where(cb.equal(mRoot.get("claimref"), claimRef));
		
		return em.createQuery(cq).getSingleResult().longValue();
	}

	public List<Tuple> getCustomerDetails(GetOrEditProductReq req) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
		Root<PersonalInfo> pRoot = cq.from(PersonalInfo.class);
		
		Expression<String> companyname = cb.concat(cb.concat(pRoot.get("firstName"), ""), pRoot.get("companyName"));
		
		Subquery<String> brokername = cq.subquery(String.class);
		Root<BrokerCompanyMaster> bcmRoot = brokername.from(BrokerCompanyMaster.class);
		
		brokername.select(bcmRoot.get("companyName")).where(cb.equal(bcmRoot.get("agencyCode"), pRoot.get("oaCode"))).distinct(true);
		
		Expression<String> address = cb.concat("P.O.BOX.", cb.concat(cb.coalesce(pRoot.get("pobox"), ""), 
				cb.concat(cb.coalesce(pRoot.get("address1"), ""), cb.concat(cb.coalesce(pRoot.get("address2"),""),
				cb.coalesce(pRoot.get("emirate"), "")))));
		
		Expression<String> Raddress = cb.function("REPLACE", String.class, address,
				cb.literal("select"),cb.literal(""));
		
		cq.multiselect(companyname.alias("companyname"),
				pRoot.get("customerLoginId").alias("customerloginId"),
				brokername.alias("brokername"),
				pRoot.get("entryDate").alias("entryDate"),
				cb.coalesce(pRoot.get("agencyCode"), "0").alias("agencyCode"),
				cb.coalesce(pRoot.get("fdCode"), "0").alias("fdCode"),
				pRoot.get("pobox").alias("pobox"),
				pRoot.get("mobile").alias("mobile"),
				pRoot.get("emirate").alias("cityname"),
				pRoot.get("customerId").alias("customerId"),
				pRoot.get("missippiCustomerCode").alias("missippiCustomerCode"),
				pRoot.get("title").alias("title"),
				pRoot.get("address1").alias("address1"),
				pRoot.get("address2").alias("address2"),
				pRoot.get("custArNo").alias("custArNo"),
				Raddress.alias("address"));
		
		List<Predicate> Predicates = new ArrayList<Predicate>();
		
		Subquery<String> loginid = cq.subquery(String.class);
		Root<LoginMaster> lmRoot = loginid.from(LoginMaster.class);
		
		Expression<String> values = cb.literal("Broker");cb.literal("User");
		
		loginid.select(lmRoot.get("loginId")).where(cb.in(lmRoot.get("usertype")).value(values));
		
		Predicates.add(cb.equal(pRoot.get("applicationId"),"1"));
		
		Subquery<String> Alogin = cq.subquery(String.class);
		Root<LoginMaster> alog = Alogin.from(LoginMaster.class);
		
		Alogin.select(alog.get("loginId")).where(cb.equal(alog.get("agencyCode"), req.getAgencyCode()));
		
		if(StringUtils.isBlank(req.getAgencyCode())) {
			Predicates.add(cb.in(pRoot.get("loginId")).value(loginid));
		}else {
			Predicates.add(cb.equal(pRoot.get("loginId"), Alogin));
		}
		
		Predicate predicateArray [] = new Predicate[Predicates.size()];
		Predicates.toArray(predicateArray);
		
		cq.where(predicateArray).orderBy(cb.asc(pRoot.get("firstName")));
		
		return em.createQuery(cq).getResultList();
	}

	public List<Tuple> getOpenCoverList(GetOpenCoverListReq req) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
		Root<OpenCoverPositionMaster> aRoot = cq.from(OpenCoverPositionMaster.class);
		Root<OpenCoverMaster> bRoot = cq.from(OpenCoverMaster.class);
		Root<PersonalInfo> cRoot = cq.from(PersonalInfo.class);
		
		cq.multiselect(aRoot.get("proposalno").alias("proposalno"),
				cb.coalesce(aRoot.get("opencoverno"), "0").alias("opencoverno"),
				cb.selectCase().when(cb.equal(aRoot.get("status"), "Y"), "Quote")
				.when(cb.equal(aRoot.get("status"), "P"), "Policy").alias("status"),
				bRoot.get("policystartdate").alias("policystartdate"),
				bRoot.get("policyenddate").alias("policyenddate"),			
				cb.coalesce(cRoot.get("firstName"), cRoot.get("companyName")).alias("name"),
				aRoot.get("renewalstatus").alias("renewalstatus"),
				bRoot.get("missippiopencoverno").alias("missippiopencoverno"));
		
		Subquery<Integer> bamend = cq.subquery(Integer.class);
		Root<OpenCoverMaster> sbaRoot = bamend.from(OpenCoverMaster.class);
		
		bamend.select(cb.max(sbaRoot.get("amendId"))).where(cb.equal(sbaRoot.get("proposalno"), aRoot.get("proposalno")));
		
		Subquery<String> propcheck = cq.subquery(String.class);
		Root<OpenCoverMaster> propRoot = propcheck.from(OpenCoverMaster.class);
		Subquery<String> brokerid = cq.subquery(String.class);
		Root<LoginUserDetails> brokerRoot = brokerid.from(LoginUserDetails.class);
		
		propcheck.select(propRoot.get("proposalno")).where(cb.equal(propRoot.get("brokerId"), brokerid));
		
		brokerid.select(brokerRoot.get("loginId")).distinct(true).where(cb.equal(brokerRoot.get("agencyCode"), req.getAgencyCode()));
		
		List<Predicate> Predicates = new ArrayList<Predicate>();
		
		Predicates.add(cb.equal(aRoot.get("status"), "Y"));
		Predicates.add(cb.isNull(aRoot.get("originalpolicyno")));
		Predicates.add(cb.equal(aRoot.get("proposalno"), bRoot.get("proposalno")));		
		Predicates.add(cb.equal(bRoot.get("amendId"), bamend));
		Predicates.add(cb.in(aRoot.get("proposalno")).value(propcheck));
		Predicates.add(cb.equal(cRoot.get("customerId"), bRoot.get("customerId")));
		Predicates.add(cb.equal(bRoot.get("customerId"), req.getCustomerId()));;
				
		if("policy".equalsIgnoreCase(req.getSearchBy())) {
			Predicates.add(cb.like(cb.lower(bRoot.get("missippiopencoverno")), "%"+req.getSearchValue().toLowerCase()+"%"));
		}else if("ocover".equalsIgnoreCase(req.getSearchBy())) {
			Predicates.add(cb.like(cb.lower(aRoot.get("opencoverno")), "%"+req.getSearchValue()+"%"));
		}
		
		Predicate predicateArray [] = new Predicate[Predicates.size()];
		Predicates.toArray(predicateArray);
		
		cq.where(predicateArray).orderBy(cb.desc(aRoot.get("proposalno")));
		
		return em.createQuery(cq).getResultList();
	}

	@Transactional
	public Integer updatepassword(UpdatePasswordReq req) throws Exception {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaUpdate<LoginMaster> cq = cb.createCriteriaUpdate(LoginMaster.class);
		Root<LoginMaster> lmRoot = cq.from(LoginMaster.class);
		Predicate p = null;
		
		if("UpdateByLoginId".equalsIgnoreCase(req.getKey())) {
			p = cb.equal(lmRoot.get("loginId"), req.getLoginId());
		}else {
			p = cb.equal(lmRoot.get("agencyCode"), req.getAgencycode());
		}
		
		cq.set(lmRoot.get("password"), CommonService.encrypt(req.getPassword()))
			.set(lmRoot.get("status"), "Y")
			.set(lmRoot.get("passdate"), java.util.Date.from(LocalDate.now().minusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant()))
			.set(lmRoot.get("pwdCount"), 0).where(p);
		
		return em.createQuery(cq).executeUpdate();
	}

	public List<Tuple> getReferralProduct(GetReferralProductReq req) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
		Root<ProductMaster> pRoot = cq.from(ProductMaster.class);
		
		if("3".equalsIgnoreCase(req.getProductId()) || "11".equalsIgnoreCase(req.getProductId())) {
				req.setProductId("3,11");
		}
	
		Subquery<String> subbranch = cq.subquery(String.class);
		Root<BranchMaster> bRoot = subbranch.from(BranchMaster.class);
		Subquery<Integer> subamend = cq.subquery(Integer.class);
		Root<BranchMaster> bmRoot = subamend.from(BranchMaster.class);
		
		String [] proId = req.getProductId().split(",");
		List<BigDecimal> p =new ArrayList<BigDecimal>();
		for (String s :proId) {
			p.add(new BigDecimal(s));
		}
		
		subamend.select(cb.max(bmRoot.get("amendId")))
			.where(cb.equal(bmRoot.get("branchCode"), bRoot.get("branchCode")),
					cb.equal(bmRoot.get("status"), bRoot.get("status")));
		
		subbranch.select(bRoot.get("belongingBranch"))
		.where(cb.equal(bRoot.get("branchCode"), req.getBranchCode()),
				cb.equal(bRoot.get("status"), "Y"),
				cb.equal(bRoot.get("amendId"), subamend));
		
		cq.multiselect(cb.trim(pRoot.get("productId")).alias("code"),
				pRoot.get("productName").alias("description"))
		
		.where(cb.equal(pRoot.get("status"), "Y"),
				cb.equal(pRoot.get("branchCode"), subbranch),
				cb.in(pRoot.get("productId")).value(p))
		
		.orderBy(cb.desc(pRoot.get("productId")));
		
		return em.createQuery(cq).getResultList();
	}

	public String GetReferalEntryDate(GetReferalEntryDateReq req) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Timestamp> cq = cb.createQuery(Timestamp.class);
		Root<HomePositionMaster> hRoot = cq.from(HomePositionMaster.class);
		
		List<String> branchIn = Arrays.asList("01","02","03","04","05");
		List<String> remarkIn = Arrays.asList("Referal","Admin");
		List<String> statusIn = Arrays.asList("Y","E","R");
		
		Subquery<String> sublog = cq.subquery(String.class);
		Root<LoginMaster> lmRoot = sublog.from(LoginMaster.class);
		Subquery<String> subagen = cq.subquery(String.class);
		Root<BrokerCompanyMaster> bcmRoot = subagen.from(BrokerCompanyMaster.class);
		
		subagen.select(bcmRoot.get("agencyCode")).where(cb.in(bcmRoot.get("branchCode")).value(branchIn));
		
		sublog.select(hRoot.get("loginId")).where(cb.in(lmRoot.get("oaCode")).value(subagen));
		
		cq.select(hRoot.get("entryDate").as(Timestamp.class))
			.where(cb.in(hRoot.get("remarks")).value(remarkIn),
					cb.in(hRoot.get("status")).value(statusIn),
					cb.equal(hRoot.get("productId"), req.getProductId()),
					cb.in(hRoot.get("loginId")).value(sublog),
					cb.in(hRoot.get("branchCode")).value(branchIn),
					cb.equal(hRoot.get("quoteNo"), req.getQuoteNo()));
		
		Timestamp date = em.createQuery(cq).getSingleResult();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");

		return dateformat.format(date);
	}

	public List<Tuple> GetReferalByQuote(GetReferalByQuoteReq req) throws ParseException {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
		Root<HomePositionMaster> hRoot = cq.from(HomePositionMaster.class);
		Root<PersonalInfo> pRoot = cq.from(PersonalInfo.class);
		Root<BrokerCompanyMaster> bRoot = cq.from(BrokerCompanyMaster.class);
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = format.parse(req.getEntryDate());
		String formateDate = sdf.format(date);
		
		List<String> remarksIn = Arrays.asList("Admin","NORMAL_EXCESS_PRICE");
		List<String> statusIn = Arrays.asList("P","SP");
		List<String> statusIn1 = Arrays.asList("CCP","SP","UWP");
		List<String> remarks2 = Arrays.asList("Referal","Admin");
		List<String> status3 = Arrays.asList("Y","E","R");
		List<String> branchcodein = Arrays.asList("01","02","03","04","05");
		
		Subquery<String> sublogin = cq.subquery(String.class);
		Root<LoginMaster> slRoot = sublogin.from(LoginMaster.class);
		Subquery<String> suboacode = cq.subquery(String.class);
		Root<BrokerCompanyMaster> sbcmRoot = suboacode.from(BrokerCompanyMaster.class);
		
		suboacode.select(sbcmRoot.get("agencyCode")).where(cb.in(sbcmRoot.get("branchCode")).value(branchcodein));
		
		sublogin.select(slRoot.get("loginId")).where(cb.in(slRoot.get("oaCode")).value(suboacode),
				cb.in(hRoot.get("branchCode")).value(branchcodein),
				cb.equal(hRoot.get("quoteNo"), req.getQuoteNo()));
		
		Subquery<String> subagency = cq.subquery(String.class);
		Root<LoginMaster> saRoot = subagency.from(LoginMaster.class);
		
		subagency.select(saRoot.get("oaCode")).where(cb.equal(saRoot.get("loginId"), hRoot.get("loginId")));
		
		cq.multiselect(cb.selectCase().when(cb.equal(hRoot.get("applicationId"), "1"), hRoot.get("loginId")).otherwise(hRoot.get("applicationId")).alias("QuoteCreated"),
			hRoot.get("applicationNo").alias("applicationNo"),
			hRoot.get("loginId").alias("loginId"),
			hRoot.get("quoteNo").alias("quoteNo"),
			hRoot.get("remarks").alias("remarks"),
			hRoot.get("referralDescription").alias("referralDescription"),
			hRoot.get("summaryRemarks").alias("summaryRemarks"),
			hRoot.get("schemeId").alias("schemeId"),
			hRoot.get("applicationId").alias("applicationId"),
			cb.selectCase().when(cb.equal(hRoot.get("status"), "E"), "Endt").otherwise("Normal").alias("status"),
			pRoot.get("customerId").alias("customerId"),
			cb.coalesce(pRoot.get("firstName"), pRoot.get("companyName")).alias("custname"),
			bRoot.get("companyName").alias("brokername"),
			cb.selectCase().when(cb.and(cb.equal(hRoot.get("status"), "Y"), cb.in(hRoot.get("remarks")).value(remarksIn)), "Referral Approved")
				.when(cb.and(cb.equal(hRoot.get("status"), "Y"),cb.greaterThanOrEqualTo(cb.function("trunc", Date.class, hRoot.get("effectiveDate")),
						cb.function("trunc", Date.class, cb.currentDate())),cb.isNull(hRoot.get("remarks")),cb.isNull(hRoot.get("adminReferralStatus"))),"Existing Quote")
				.when(cb.and(cb.equal(hRoot.get("status"), "Y"),cb.lessThan(cb.function("trunc", Date.class, hRoot.get("effectiveDate")), 
						cb.function("trunc", Date.class, cb.currentDate()))), "Lapsed Quote")
				.when(cb.equal(hRoot.get("status"), "D"), "Rejected Quote")
				.when(cb.and(cb.equal(hRoot.get("status"), "Y"), cb.in(hRoot.get("remarks")).value("Referal")), "Referral UnApproved")
				.when(cb.in(hRoot.get("status")).value("R"), "Referral Rejected")
				.when(cb.or(cb.and(cb.equal(hRoot.get("paymentMode"), "6"),cb.in(hRoot.get("status")).value(statusIn)),cb.and(cb.notEqual(hRoot.get("paymentMode"), "6"),cb.in(hRoot.get("status")).value("P"))),"Policy")
				.when(cb.in(hRoot.get("status")).value(statusIn1),"Pending Policy")
				.when(cb.in(hRoot.get("status")).value("PC"), "Canceled Policy").otherwise(hRoot.get("status")).alias("statusdesc")).distinct(true)
		
		.where(cb.equal(pRoot.get("customerId"), hRoot.get("customerId")),
				cb.equal(bRoot.get("agencyCode"), subagency),
				cb.equal(cb.function("to_char", Date.class, hRoot.get("entryDate"),cb.literal("DD/MM/YYYY")), cb.parameter(String.class,"inputDate")),
				cb.equal(hRoot.get("productId"), new BigDecimal(req.getProductId())),
				cb.in(hRoot.get("remarks")).value(remarks2),
				cb.in(hRoot.get("status")).value(status3),
				cb.in(hRoot.get("loginId")).value(sublogin))
		
		.orderBy(cb.desc(hRoot.get("quoteNo")));
		
		
		return em.createQuery(cq).setParameter("inputDate", formateDate).getResultList();
		
	}

	public String getLoginByLoginId(String loginId) {
		String result="";
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<String> cq = cb.createQuery(String.class);
			Root<LoginMaster> lRoot = cq.from(LoginMaster.class);
			
			cq.select(lRoot.get("loginId"))
			.where(cb.equal(lRoot.get("loginId"), loginId));
			
			result = em.createQuery(cq).getSingleResult();
		} catch (Exception e) {
			log.error("No entity found for query");
		}
			return result;
	}

}
