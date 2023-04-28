package com.madison.motor.service;

import com.madison.motor.response.MadisonCommonRes;

public interface UserCreationService {

	MadisonCommonRes getUserDetailsByBranchCode(String branchCode);

	MadisonCommonRes editUserByAgencyCode(String agencyCode);

	MadisonCommonRes getAdminDetailsByBranchCode(String branchCode);

	MadisonCommonRes editAdminDetailsByLoginId(String loginId, String branchCode);

}
