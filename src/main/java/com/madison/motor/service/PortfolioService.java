package com.madison.motor.service;

import com.madison.motor.request.GetPortFolioReq;
import com.madison.motor.request.PortfolioSearchReq;
import com.madison.motor.response.MadisonCommonRes;

public interface PortfolioService {

	MadisonCommonRes portfolioSearch(PortfolioSearchReq req);

	MadisonCommonRes getPortFolioByDate(GetPortFolioReq req);

}
