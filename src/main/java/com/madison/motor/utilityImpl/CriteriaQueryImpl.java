package com.madison.motor.utilityImpl;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.madison.motor.entity.BrokerCompanyMaster;
import com.madison.motor.entity.HomePositionMaster;
import com.madison.motor.entity.ListItemValue;
import com.madison.motor.entity.LoginMaster;
import com.madison.motor.entity.MotorDataDetail;
import com.madison.motor.entity.MotorPolicytypeMaster;
import com.madison.motor.entity.PaymentDetail;
import com.madison.motor.entity.PersonalInfo;
import com.madison.motor.entity.ProductMaster;
import com.madison.motor.request.GetPortFolioReq;
import com.madison.motor.request.PortfolioSearchReq;

@Component
public class CriteriaQueryImpl {
	
	
	Logger log =LogManager.getLogger(CriteriaQueryImpl.class);
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private CommonService cs;
	

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
				.groupBy(cb.function("trunc", Date.class, hpm.get("entryDate")))
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
					cb.function("to_char", Date.class, hpm.get("expiryDate"),cb.literal("DD/MM/YYYY")).alias("expiryDate"),hpm.get("applicationNo").alias("applicationNo"),
					product_name.alias("product_name"),hpm.get("productId").alias("productId"),policy_type.alias("policy_type"),
					payment_type.alias("payment_type")
					)
				.where(predicate_array);
			
			List<Tuple> tuple =em.createQuery(query)
					.setParameter("entryDate", req.getEntryDate())
					.getResultList();
			
			System.out.println(tuple);

		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		
		return null;
	}
	

}
