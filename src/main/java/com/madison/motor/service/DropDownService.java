package com.madison.motor.service;

import java.util.List;

import com.madison.motor.request.GetConditionReq;
import com.madison.motor.request.InsertConditionReq;
import com.madison.motor.response.MadisonCommonRes;

public interface DropDownService {

	MadisonCommonRes getPortFolioType();

	MadisonCommonRes getBranchByLoginId(String loginId);

	MadisonCommonRes getReferalStatus();

	MadisonCommonRes getConditionList(GetConditionReq req);

	MadisonCommonRes editConditionList(String quoteNo);

	MadisonCommonRes insertCondition(List<InsertConditionReq> req);

}
