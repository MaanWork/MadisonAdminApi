package com.madison.motor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.madison.motor.request.AdminSaveReq;
import com.madison.motor.request.BrokerProductSaveReq;
import com.madison.motor.request.BrokerSaveReq;
import com.madison.motor.request.GetOrEditProductReq;
import com.madison.motor.request.GetProductReq;
import com.madison.motor.request.GetUserDetailsReq;
import com.madison.motor.request.SaveProductReq;
import com.madison.motor.request.UserSaveReq;
import com.madison.motor.response.MadisonCommonRes;
import com.madison.motor.service.UserCreationService;

@RestController
@RequestMapping("/user")
public class UserCreationController {
	
	
	@Autowired
	private UserCreationService service;
	
	
	@PostMapping("/getUserDetails")
	public MadisonCommonRes getUserDetailsByBranchCode(@RequestBody GetUserDetailsReq req) {
		return service.getUserDetailsByBranchCode(req);
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

	@GetMapping("/getBrokerByBranchCode")
	public MadisonCommonRes getBrokerByBranchCode(@RequestParam("branchCode") String branchCode
			,@RequestParam("appId") String appId) {
		return service.getBrokerByBranchCode(branchCode,appId);
	}
	
	@GetMapping("/editBrokerByAgencyCode")
	public MadisonCommonRes editBrokerByAgencyCode(@RequestParam("agencyCode") String agencyCode
			,@RequestParam("branchCode") String branchCode) {
		return service.editBrokerByAgencyCode(agencyCode,branchCode);
	}
	
	@GetMapping("/getProductDetailsByAgencyCode")
	public MadisonCommonRes getProductDetailsByAgencyCode(@RequestParam("agencyCode") String agencyCode) {
		return service.getProductDetailsByAgencyCode(agencyCode);
	}
	
	@PostMapping("/edit/product")
	public MadisonCommonRes editProduct(@RequestBody GetOrEditProductReq req) {
		return service.editProduct(req);
	}
	
	@PostMapping("/create/user")
	public MadisonCommonRes saveUser(@RequestBody UserSaveReq req) {
		return service.saveUser(req);
	}
	
	@PostMapping("/create/product")
	public MadisonCommonRes saveProduct(@RequestBody SaveProductReq req) {
		return service.saveProduct(req);
	}
	
	@PostMapping("/get/product")
	public MadisonCommonRes getProduct(@RequestBody GetProductReq req) {
		return service.getProduct(req);
	}
	
	@PostMapping("/create/admin")
	public MadisonCommonRes createAdmin(@RequestBody AdminSaveReq req) {
		return service.createAdmin(req);
	}
	
	@PostMapping("/create/broker")
	public MadisonCommonRes createBroker(@RequestBody BrokerSaveReq req) {
		return service.createBroker(req);
	}
	
	@PostMapping("/save/broker/product")
	public MadisonCommonRes saveBrokerProduct(@RequestBody BrokerProductSaveReq req) {
		return service.saveBrokerProduct(req);
	}
}
