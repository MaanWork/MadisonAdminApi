package com.madison.motor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.madison.motor.response.MadisonCommonRes;
import com.madison.motor.service.MasterService;

@RestController
@RequestMapping("/masters")
public class MastersController {

	@Autowired
	private MasterService service;
	
	@GetMapping("/get/foreign/exchange")
	public MadisonCommonRes ForeignExchange() {
	return service.ForeignExchange();
	}
	
	@GetMapping("get/edit/exchange")
	public MadisonCommonRes EditForeignExchange(@RequestParam ("exchangeId") String exchangeId) {
		return service.EditForeignExchange(exchangeId);
	}
}
