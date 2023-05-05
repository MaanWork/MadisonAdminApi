package com.madison.motor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.madison.motor.request.GetConditionReq;
import com.madison.motor.request.InsertConditionReq;
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
	
	@GetMapping("/get/referalStatus")
	public MadisonCommonRes getReferalStatus() {
		return service.getReferalStatus();
	}
	
	@PostMapping("/get/condition/list")
	public MadisonCommonRes getConditionList(@RequestBody GetConditionReq req) {
		return service.getConditionList(req);
	}
	
	@GetMapping("/edit/condition/list")
	public MadisonCommonRes editConditionList(@RequestParam("quoteNo") String quoteNo,@RequestParam("contype") String contype) {
		return service.editConditionList(quoteNo,contype);
	}
	
	@PostMapping("/insert/condition")
	public MadisonCommonRes insertCondition(@RequestBody List<InsertConditionReq> req) {
		return service.insertCondition(req);
	}
	

	@GetMapping("/get/attached/branch")
	public MadisonCommonRes getAttachedBranch() {
		return service.getAttachedBranch();
	}
	
	@GetMapping("/get/nationality")
	public MadisonCommonRes getNationality() {
		return service.getNationality();	
	}
	
	@GetMapping("/get/usertype")
	public MadisonCommonRes getUserType() {
		return service.getUserType();
	}
	
	@GetMapping("/get/exeutive/bdm")
	public MadisonCommonRes getExeutiveBdm() {
		return service.getExeutiveBdm();
	}
	
	@GetMapping("/get/subBranchByBranchCode")
	public MadisonCommonRes getsubBranchByBranchCode(@RequestParam("branchCode") String branchCode) {
		return service.getsubBranchByBranchCode(branchCode);
	}
}
