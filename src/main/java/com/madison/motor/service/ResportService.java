package com.madison.motor.service;

import java.text.ParseException;

import com.madison.motor.request.GetEmiReportReq;
import com.madison.motor.request.RoadAssistantListReq;
import com.madison.motor.response.MadisonCommonRes;

public interface ResportService {

	MadisonCommonRes RoadAssistantList(RoadAssistantListReq req) throws ParseException;

	MadisonCommonRes GetEmiReportCount(GetEmiReportReq req) throws ParseException;

	MadisonCommonRes GetEmiReportList(GetEmiReportReq req) throws ParseException;

}
