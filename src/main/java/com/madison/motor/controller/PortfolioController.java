package com.madison.motor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.madison.motor.request.GetPortFolioReq;
import com.madison.motor.request.PortfolioSearchReq;
import com.madison.motor.request.ReferalQuoteReq;
import com.madison.motor.request.ReferalSearchQuoteReq;
import com.madison.motor.request.UpdateQuotePremiumReq;
import com.madison.motor.response.MadisonCommonRes;
import com.madison.motor.service.PortfolioService;

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
	
	@PostMapping("/referal/search")
	public MadisonCommonRes getReferalSearchQuote(@RequestBody ReferalSearchQuoteReq req) {
		return service.getReferalSearchQuote(req);
	}
	
	@PostMapping("/get/referalQuoteByDate")
	public MadisonCommonRes getReferalQuoteByDate(@RequestBody ReferalQuoteReq req) {
		return service.getReferalQuoteByDate(req);
	}
	
	@GetMapping("/get/vehicle/premium")
	public MadisonCommonRes getVehiclePremiumByAppNo(@RequestParam ("applicationNo") String applicationNo) {
		return service.getVehiclePremiumByAppNo(applicationNo);
	}
	
	@GetMapping("/get/quote/premiumInfo")
	public MadisonCommonRes getQuotePremiumInfo(@RequestParam ("applicationNo") String applicationNo) {
		return service.getQuotePremiumInfo(applicationNo);
	}
	
	@PostMapping("/update/quote/premium")
	public MadisonCommonRes updateQuotePremium(@RequestBody UpdateQuotePremiumReq req) {
		return service.updateQuotePremium(req);
	}
	
}
