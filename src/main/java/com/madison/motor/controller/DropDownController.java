package com.madison.motor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.madison.motor.response.MadisonCommonRes;
import com.madison.motor.service.DropDownService;

@RestController
@RequestMapping("/dropdown")
public class DropDownController {

	
	@Autowired
	private DropDownService service;
	
	
	@GetMapping("/get/portfolio/types")
	public MadisonCommonRes getPortFolioType() {
		return service.getPortFolioType();
	}
	
	@GetMapping("/getBranchByLoginId")
	public MadisonCommonRes getBranchByLoginId(@RequestParam ("loginId") String loginId) {
		return service.getBranchByLoginId(loginId);
	}
	
	
}
