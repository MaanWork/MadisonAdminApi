package com.madison.motor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.madison.motor.request.InsertClaimIntimationReq;
import com.madison.motor.request.RejectEditReq;
import com.madison.motor.response.MadisonCommonRes;
import com.madison.motor.service.ClaimIntimationService;

@RestController
@RequestMapping("/claimintimation")
public class ClaimIntimationController {
	
	@Autowired
	private ClaimIntimationService service;
	
	@GetMapping("/get/claimIntimationList")
	public MadisonCommonRes ClaimIntimationList(@RequestParam ("status") String status) {
		return service.ClaimIntimationList(status);
	}
	
	@PostMapping("/get/edit/rejectEdit")
	public MadisonCommonRes RejectEdit(@RequestBody RejectEditReq req) {
		return service.RejectEdit(req);
	}
	
	@PostMapping("save/claimintimation")
	public MadisonCommonRes InsertClaimIntimation(@RequestBody InsertClaimIntimationReq req) {
		return service.InsertClaimIntimation(req);
	}

}
