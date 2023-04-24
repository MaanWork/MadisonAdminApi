package com.madison.motor.service;

import com.madison.motor.request.InsertClaimIntimationReq;
import com.madison.motor.request.RejectEditReq;
import com.madison.motor.response.MadisonCommonRes;

public interface ClaimIntimationService {

	MadisonCommonRes ClaimIntimationList(String status);

	MadisonCommonRes RejectEdit(RejectEditReq req);

	MadisonCommonRes InsertClaimIntimation(InsertClaimIntimationReq req);

}
