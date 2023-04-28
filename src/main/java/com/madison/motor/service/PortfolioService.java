package com.madison.motor.service;

import com.madison.motor.request.GetPortFolioReq;
import com.madison.motor.request.ModifyRateReq;
import com.madison.motor.request.PortfolioSearchReq;
import com.madison.motor.request.ReferalQuoteReq;
import com.madison.motor.request.ReferalSearchQuoteReq;
import com.madison.motor.request.UpdateQuotePremiumReq;
import com.madison.motor.response.MadisonCommonRes;

public interface PortfolioService {

	MadisonCommonRes portfolioSearch(PortfolioSearchReq req);

	MadisonCommonRes getPortFolioByDate(GetPortFolioReq req);

	MadisonCommonRes getReferalSearchQuote(ReferalSearchQuoteReq req);

	MadisonCommonRes getReferalQuoteByDate(ReferalQuoteReq req);

	MadisonCommonRes getVehiclePremiumByAppNo(String applicationNo);

	MadisonCommonRes getQuotePremiumInfo(String applicationNo);

	MadisonCommonRes updateQuotePremium(UpdateQuotePremiumReq req);

	MadisonCommonRes modifyRate(ModifyRateReq req);

	MadisonCommonRes editModifyRate(String applicationNo, String vehicleId);

}
