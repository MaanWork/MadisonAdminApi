package com.madison.motor.serviceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Tuple;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.madison.motor.request.GetEmiReportReq;
import com.madison.motor.request.RoadAssistantListReq;
import com.madison.motor.response.GetEmiReportCountRes;
import com.madison.motor.response.MadisonCommonRes;
import com.madison.motor.response.RoadAssistantListRes;
import com.madison.motor.service.ResportService;
import com.madison.motor.utilityImpl.CriteriaQueryImpl;

@Service
public class ResportServiceImpl implements ResportService{
	
	@Autowired
	private CriteriaQueryImpl query;
	
	Logger log = LogManager.getLogger(ResportServiceImpl.class);
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public String DateFormat(Object output) {
		return new SimpleDateFormat("dd/MM/yyyy").format(output).toString();
	}

	@Override
	public MadisonCommonRes RoadAssistantList(RoadAssistantListReq req) throws ParseException {
		MadisonCommonRes res = new MadisonCommonRes();
		List<RoadAssistantListRes> response = new ArrayList<RoadAssistantListRes>();
		try {
			List<Tuple> list = query.getRoadAssistantList(req);
			if(!CollectionUtils.isEmpty(list)) {
				list.forEach(m->{
					RoadAssistantListRes k = RoadAssistantListRes.builder()
						.refNo(m.get("refNo")==null?"":m.get("refNo").toString())
						.mobileNo(m.get("mobileno")==null?"":m.get("mobileno").toString())
						.policyNo(m.get("policyNo")==null?"":m.get("policyNo").toString())
						.customerName(m.get("customerName")==null?"":m.get("customerName").toString())
						.assistantType(m.get("assistantType")==null?"":m.get("assistantType").toString())
						.description(m.get("descripition")==null?"":m.get("descripition").toString())
						.longitude(m.get("logitude")==null?"":m.get("logitude").toString())
						.latitude(m.get("latitude")==null?"":m.get("latitude").toString())
						.deviceId(m.get("deviceId")==null?"":m.get("deviceId").toString())
						.entryDate(m.get("entrydate")==null?"":DateFormat(m.get("entrydate")).toString())
						.location(m.get("location")==null?"":m.get("location").toString())
						.status(m.get("status")==null?"":m.get("status").toString())
						.remarks(m.get("remarks")==null?"":m.get("remarks").toString())
						.mailId(m.get("mailId")==null?"":m.get("mailId").toString())
						.updateDate(m.get("updatedate")==null?"":DateFormat(m.get("updatedate")).toString())
						.customerFeedback(m.get("customerfeedback")==null?"":m.get("customerfeedback").toString())
						.build();
					response.add(k);
				});
				res.setMessage("SUCCESS");
				res.setResponse(response);
			}else {
				res.setMessage("FAILED");
				res.setResponse(response);
			}
		} catch (ParseException e) {
			log.info(e);
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public MadisonCommonRes GetEmiReportCount(GetEmiReportReq req) throws ParseException {
		MadisonCommonRes res = new MadisonCommonRes();
		List<GetEmiReportCountRes> response = new ArrayList<GetEmiReportCountRes>();
		try {
			List<Tuple> list = query.GetEmiReportCount(req);
			if(!CollectionUtils.isEmpty(list)) {
				list.forEach(m->{
					GetEmiReportCountRes k = GetEmiReportCountRes.builder()
					.emiDueCount(m.get("emiduecount")==null?"":m.get("emiduecount").toString())
					.dueAmount(m.get("premiumamount")==null?"":m.get("premiumamount").toString())
					.build();
					response.add(k);
				});
				res.setMessage("SUCCESS");
				res.setResponse(response);
			}else {
				res.setMessage("FAILED");
				res.setResponse(response);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public MadisonCommonRes GetEmiReportList(GetEmiReportReq req) throws ParseException {
		MadisonCommonRes res = new MadisonCommonRes();
		
		List<Tuple> list = query.GetEmiReportList(req);
		return null;
	}

}
