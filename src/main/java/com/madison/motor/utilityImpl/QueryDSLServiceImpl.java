package com.madison.motor.utilityImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.querydsl.jpa.impl.JPAQueryFactory;

@Service
public class QueryDSLServiceImpl {
	
	
	Logger log =LogManager.getLogger(QueryDSLServiceImpl.class);
	
	@Autowired
	private CommonService cs;
	
	@Autowired
	private JPAQueryFactory jpa;
	
	
	
	

}
