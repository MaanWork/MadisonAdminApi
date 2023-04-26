package com.madison.motor.service;

import com.madison.motor.response.MadisonCommonRes;

public interface DropDownService {

	MadisonCommonRes getPortFolioType();

	MadisonCommonRes getBranchByLoginId(String loginId);

}
