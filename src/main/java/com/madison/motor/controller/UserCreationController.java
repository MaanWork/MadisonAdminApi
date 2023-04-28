package com.madison.motor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.madison.motor.response.MadisonCommonRes;
import com.madison.motor.service.UserCreationService;

@RestController
@RequestMapping("/user")
public class UserCreationController {
	
	
	@Autowired
	private UserCreationService service;
	
	
	@GetMapping("/getUserDetailsByBranchCode")
	public MadisonCommonRes getUserDetailsByBranchCode(@RequestParam ("branchCode") String branchCode) {
		return service.getUserDetailsByBranchCode(branchCode);
	}
	
	@GetMapping("/editUserByAgencyCode")
	public MadisonCommonRes editUserByAgencyCode(@RequestParam ("agencyCode") String agencyCode) {
		return service.editUserByAgencyCode(agencyCode);
	}
	
	@GetMapping("/getAdminDetailsByBranchCode")
	public MadisonCommonRes getAdminDetailsByBranchCode(@RequestParam ("branchCode") String branchCode) {
		return service.getAdminDetailsByBranchCode(branchCode);
	}
	
	@GetMapping("/editAdminDetailsByLoginId")
	public MadisonCommonRes editAdminDetailsByLoginId(@RequestParam ("loginId") String loginId,
			@RequestParam ("branchCode") String branchCode) {
		return service.editAdminDetailsByLoginId(loginId,branchCode);
	}

}
