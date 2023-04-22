package com.madison.motor.utilityImpl;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.madison.motor.entity.HomePositionMaster;
import com.madison.motor.entity.PersonalInfo;
import com.madison.motor.request.PortfolioSearchReq;

@Component
public class CriteriaQueryImpl {
	
	
	Logger log =LogManager.getLogger(CriteriaQueryImpl.class);
	
	@PersistenceContext
	private EntityManager em;
	
	private Query query_obj ;
	
	@Autowired
	private CommonService cs;
	

	@PostConstruct
	public List<Tuple> portfolioSearch() {
		
		PortfolioSearchReq req = new PortfolioSearchReq();
		req.setBranchCode("01");
		req.setStatus("Y");
		req.setStartDate("01/04/2023");
		req.setEndDate("21/04/2023");
		List<Predicate> predicates =new ArrayList<Predicate>(10);
		try {
			CriteriaBuilder cb =em.getCriteriaBuilder();
			CriteriaQuery<Tuple> query =cb.createTupleQuery();
			Root<HomePositionMaster> hpm =query.from(HomePositionMaster.class);
			Root<PersonalInfo> pi =query.from(PersonalInfo.class);
			predicates.add(cb.equal(hpm.get("customerId"), pi.get("customerId")));
			predicates.add(cb.equal(cb.lower(hpm.get("status")), "Y".toLowerCase()));
			predicates.add(cb.between(hpm.get("entryDate"), cb.function("to_date", Date.class, cb.parameter(String.class,"startDate")),
					cb.function("to_date", Date.class, cb.parameter(String.class,"endDate"))));
			
			Predicate [] predicateArray =new Predicate[predicates.size()];
			predicates.toArray(predicateArray);
			
			query.multiselect(hpm.get("entryDate").alias("entryDate"),cb.count(hpm.get("inceptionDate")))
				.where(predicateArray).groupBy(cb.function("to_char", String.class, hpm.get("entryDate")));
			
			List<Tuple> list =em.createQuery(query)
			.setParameter("endDate", req.getEndDate())
			.setParameter("startDate", req.getStartDate()).getResultList();
			
			log.info("portfolioSearch result:"+cs.printReq(list));
		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}  
		return null;
	}

}
