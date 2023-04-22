package com.madison.motor.serviceImpl;

import java.util.List;

import javax.persistence.Tuple;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.madison.motor.request.PortfolioSearchReq;
import com.madison.motor.response.MadisonCommonRes;
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
		try {
			List<Tuple> list =query.portfolioSearch(req);
		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return res;
	}
	
	
}
