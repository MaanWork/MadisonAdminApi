package com.madison.motor.service;

import com.madison.motor.response.MadisonCommonRes;

public interface MasterService {

	MadisonCommonRes ForeignExchange();

	MadisonCommonRes EditForeignExchange(String exchangeId);

}
