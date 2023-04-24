package com.madison.motor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.madison.motor.request.GetPortFolioReq;
import com.madison.motor.request.PortfolioSearchReq;
import com.madison.motor.response.MadisonCommonRes;
import com.madison.motor.service.PortfolioService;
import com.madison.motor.utilityImpl.QueryDSLServiceImpl;

@RestController
@RequestMapping("/portfolio")
public class PortfolioController {

	
	@Autowired
	private PortfolioService service;
	
	
	@PostMapping("/search")
	public MadisonCommonRes portfolioSearch(@RequestBody PortfolioSearchReq req) {
		return service.portfolioSearch(req);
	}
	
	@PostMapping("/get/portfolioByDate")
	public MadisonCommonRes getPortFolioByDate(@RequestBody GetPortFolioReq req) {
		return service.getPortFolioByDate(req);
	}
	
	
}
