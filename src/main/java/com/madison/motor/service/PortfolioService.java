package com.madison.motor.service;

import com.madison.motor.request.PortfolioSearchReq;
import com.madison.motor.response.MadisonCommonRes;

public interface PortfolioService {

	MadisonCommonRes portfolioSearch(PortfolioSearchReq req);

}
