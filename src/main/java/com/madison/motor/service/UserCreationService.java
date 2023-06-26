package com.madison.motor.service;

import com.madison.motor.request.AdminSaveReq;
import com.madison.motor.request.BrokerProductSaveReq;
import com.madison.motor.request.BrokerSaveReq;
import com.madison.motor.request.GetOpenCoverListReq;
import com.madison.motor.request.GetOrEditProductReq;
import com.madison.motor.request.GetProductReq;
import com.madison.motor.request.GetUserDetailsReq;
import com.madison.motor.request.SaveProductReq;
import com.madison.motor.request.UpdatePasswordReq;
import com.madison.motor.request.UserSaveReq;
import com.madison.motor.request.getExcludedMenuReq;
import com.madison.motor.response.MadisonCommonRes;

public interface UserCreationService {

	MadisonCommonRes getUserDetailsByBranchCode(GetUserDetailsReq req);

	MadisonCommonRes editUserByAgencyCode(String agencyCode,String customerId);

	MadisonCommonRes getAdminDetailsByBranchCode(String branchCode);

	MadisonCommonRes editAdminDetailsByLoginId(String loginId, String branchCode);

	MadisonCommonRes getBrokerByBranchCode(String branchCode, String appId);

	MadisonCommonRes editBrokerByAgencyCode(String agencyCode, String branchCode);

	MadisonCommonRes getProductDetailsByAgencyCode(String agencyCode);

	MadisonCommonRes saveUser(UserSaveReq req);

	MadisonCommonRes saveProduct(SaveProductReq req);

	MadisonCommonRes getProduct(GetProductReq req);

	MadisonCommonRes createAdmin(AdminSaveReq req);

	MadisonCommonRes createBroker(BrokerSaveReq req);

	MadisonCommonRes saveBrokerProduct(BrokerProductSaveReq req);

	MadisonCommonRes editProduct(GetOrEditProductReq req);

	MadisonCommonRes getExcludedMenu(getExcludedMenuReq req);

	MadisonCommonRes getCustomerDetails(GetOrEditProductReq req);

	MadisonCommonRes getcustomerdetailsByCustomerId(String customerId);

	MadisonCommonRes getOpenCoverList(GetOpenCoverListReq req);

	MadisonCommonRes updatepassword(UpdatePasswordReq req) throws Exception;

}
