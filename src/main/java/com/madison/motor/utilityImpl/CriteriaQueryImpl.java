package com.madison.motor.utilityImpl;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import javax.transaction.TransactionScoped;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.madison.motor.entity.BranchMaster;
import com.madison.motor.entity.BrokerCompanyMaster;
import com.madison.motor.entity.CountryMaster;
import com.madison.motor.entity.CurrencyMaster;
import com.madison.motor.entity.ExchangeMaster;
import com.madison.motor.entity.HomePositionMaster;
import com.madison.motor.entity.InstallmentDetails;
import com.madison.motor.entity.ListItemValue;
import com.madison.motor.entity.LoginMaster;
import com.madison.motor.entity.LoginUserDetails;
import com.madison.motor.entity.MotorClaimIntimationDtl;
import com.madison.motor.entity.MotorDataDetail;
import com.madison.motor.entity.MotorGroupMaster;
import com.madison.motor.entity.MotorPolicyCoverData;
import com.madison.motor.entity.MotorPolicyDetails;
import com.madison.motor.entity.MotorPolicytypeMaster;
import com.madison.motor.entity.PaymentDetail;
import com.madison.motor.entity.PersonalInfo;
import com.madison.motor.entity.ProductMaster;
import com.madison.motor.entity.RoadSideAssistantDetails;
import com.madison.motor.request.GetEmiReportReq;
import com.madison.motor.request.GetPortFolioReq;
import com.madison.motor.request.PortfolioSearchReq;
import com.madison.motor.request.ReferalQuoteReq;
import com.madison.motor.request.ReferalSearchQuoteReq;
import com.madison.motor.request.RejectEditReq;
import com.madison.motor.request.UpdateQuotePremiumReq;
import com.madison.motor.request.RoadAssistantListReq;

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
			predicates.add(cb.between(hpm.get("entryDate"), cb.function("to_date", Date.class, cb.parameter(String.class,"startDate")),
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
				iRoot.get("premium_date").alias("premium_date"),
				iRoot.get("noofmonths").alias("noofmonths"),
				iRoot.get("noofemi").alias("noofemi"),
				iRoot.get("applicationNo").alias("applicationNo"),
				iRoot.get("loofterms").alias("loofterms"),
				iRoot.get("remarks").alias("remarks"),
				iRoot.get("installmentNo").alias("installmentNo"),
				iRoot.get("status").alias("status"),
				iRoot.get("balanceamount").alias("balanceamount"),
				iRoot.get("description").alias("description"),
				iRoot.get("productId").alias("productId"),
				iRoot.get("policyNo").alias("policyNo"),
				cb.selectCase().when(cb.equal(iRoot.get("paymentstatus"), "Y"), "Success")
				.otherwise("Pending").alias("paymentstatus"))
		
		.where(cb.equal(iRoot.get("quoteNo"), hRoot.get("quoteNo")),
				cb.equal(iRoot.get("productId"), req.getProductId()),
				cb.between(cb.function("trunc", Date.class, iRoot.get("premiumdate")), sdf.parse(req.getStartDate()), sdf.parse(req.getEndDate())),
				cb.equal(iRoot.get("status"), "Y"),
				cb.equal(hRoot.get("status"), "P"));
		
		return em.createQuery(cq).getResultList();
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
					,cb.count(hpm.get("entryDate")).alias("count"))
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
					hpm.get("productId").alias("productId"))
				
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
	
	public void updateVehiclePremimByVehicleId() {
		List<Predicate> predicates =new ArrayList<Predicate>();
		try {
			CriteriaBuilder cb =em.getCriteriaBuilder();
			CriteriaUpdate<MotorPolicyCoverData> update =cb.createCriteriaUpdate(MotorPolicyCoverData.class);
			Root<MotorPolicyCoverData> mpcd =update.from(MotorPolicyCoverData.class);
			
			update.set(mpcd.get("sumInsured"), "");
			update.set(mpcd.get("rate"), "");
			update.set(mpcd.get("status"), "R");
			
			predicates.add(cb.equal(mpcd.get("applicationNo"), ""));
			predicates.add(cb.equal(mpcd.get("policytypeCoverid"), ""));
			predicates.add(cb.equal(mpcd.get("vehicleId"), ""));
			
			
			
		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
	}
}
