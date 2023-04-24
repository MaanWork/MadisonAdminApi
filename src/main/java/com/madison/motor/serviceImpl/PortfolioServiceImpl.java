package com.madison.motor.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Tuple;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.madison.motor.request.GetPortFolioReq;
import com.madison.motor.request.PortfolioSearchReq;
import com.madison.motor.response.GetPortfolioRecordRes;
import com.madison.motor.response.MadisonCommonRes;
import com.madison.motor.response.PortFolioSearchRes;
import com.madison.motor.service.PortfolioService;
import com.madison.motor.utilityImpl.CommonService;
import com.madison.motor.utilityImpl.CriteriaQueryImpl;

@Service
public class PortfolioServiceImpl implements PortfolioService{
	
	Logger log =LogManager.getLogger(PortfolioServiceImpl.class);
	
	@Autowired
	private CommonService cs;
	
	@Autowired
	private CriteriaQueryImpl query;

	
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
							.entryDate(p.get("entryDate")==null?"":p.get("entryDate").toString())
							.totalRecordsCount(p.get("count")==null?"":p.get("count").toString())
							.ProductId(p.get("productId")==null?"":p.get("productId").toString())
							.build();
					array_list.add(folioSearchRes);
				});
				res.setResponse(array_list);
				res.setResponseStatus("SUCCESS");
			}else {
				res.setResponse(array_list);
				res.setResponseStatus("FAILED");
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
							.brokerName(p.get("")==null?"":p.get("").toString())
							.customerName(p.get("customer_name")==null?"":p.get("customer_name").toString())
							.policyEndDate(p.get("expiryDate")==null?"":p.get("expiryDate").toString())
							.policyStartDate(p.get("inceptionDate")==null?"":p.get("inceptionDate").toString())
							.policyType(p.get("policy_type")==null?"":p.get("policy_type").toString())
							.premium(p.get("premium")==null?"":p.get("premium").toString())
							.quoteCreatedBy(p.get("entryDate")==null?"":p.get("entryDate").toString())
							.quoteNo(p.get("quoteNo")==null?"":p.get("quoteNo").toString())
							.policyNo(p.get("policyNo")==null?"":p.get("policyNo").toString())
							.paymentType(p.get("payment_type")==null?"":p.get("payment_type").toString())
							.build();
					
					array_list.add(portfolioRecordRes);
				});
				res.setResponse(array_list);
				res.setResponseStatus("SUCCESS");
			}else {
				res.setResponse(array_list);
				res.setResponseStatus("FAILED");
			}
		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return null;
	}
	
	
}
