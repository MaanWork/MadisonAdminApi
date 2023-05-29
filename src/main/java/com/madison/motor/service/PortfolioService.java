package com.madison.motor.service;

import java.text.ParseException;

import com.madison.motor.request.GetPortFolioReq;
import com.madison.motor.request.GetReferalByQuoteReq;
import com.madison.motor.request.GetReferalEntryDateReq;
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

	MadisonCommonRes GetReferalEntryDate(GetReferalEntryDateReq req) throws ParseException;

	MadisonCommonRes GetReferalByQuote(GetReferalByQuoteReq req);

}
