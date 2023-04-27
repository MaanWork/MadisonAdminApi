package com.madison.motor.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.madison.motor.request.GetEmiReportReq;
import com.madison.motor.request.RoadAssistantListReq;
import com.madison.motor.response.MadisonCommonRes;
import com.madison.motor.service.ResportService;

@RestController
@RequestMapping("/report")
public class ReportsController {
	
	@Autowired
	private ResportService service;
	
	@PostMapping("/get/road/assistant")
	public MadisonCommonRes RoadAssistantList(@RequestBody RoadAssistantListReq req) throws ParseException {
		return service.RoadAssistantList(req);
	}

	@PostMapping("/get/count/emi")
	public MadisonCommonRes GetEmiReportCount(@RequestBody GetEmiReportReq req) throws ParseException {
		return service.GetEmiReportCount(req);
	}
	
	@PostMapping("get/emi")
	public MadisonCommonRes GetEmiReportList(@RequestBody GetEmiReportReq req) throws ParseException{
		return service.GetEmiReportList(req);
	}
}
