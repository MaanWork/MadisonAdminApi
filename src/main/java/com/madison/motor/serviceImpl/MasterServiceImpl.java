package com.madison.motor.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Tuple;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.madison.motor.response.EditForeignExchangeRes;
import com.madison.motor.response.ForeignExchangeRes;
import com.madison.motor.response.MadisonCommonRes;
import com.madison.motor.service.MasterService;
import com.madison.motor.utilityImpl.CriteriaQueryImpl;

@Service
public class MasterServiceImpl implements MasterService{
	
	
	@Autowired
	private CriteriaQueryImpl query;
	
	Logger log = LogManager.getLogger(MasterServiceImpl.class);
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public String DateFormat(Object output) {
		return new SimpleDateFormat("dd/MM/yyyy").format(output).toString();
	}

	@Override
	public MadisonCommonRes ForeignExchange() {
		MadisonCommonRes res = new MadisonCommonRes();
		List<ForeignExchangeRes> response = new ArrayList<ForeignExchangeRes>();
		try {
			List<Tuple> list = query.ForeignExchangeList();
			if(!CollectionUtils.isEmpty(list)) {
				list.forEach( r ->{
					ForeignExchangeRes m = ForeignExchangeRes.builder()
					.exchangeId(r.get("exchId")==null?"":r.get("exchId").toString())
					.exchangeRate(r.get("exchRate")==null?"":r.get("exchRate").toString())
					.currencyId(r.get("currencyId")==null?"":r.get("currencyId").toString())
					.currencyname(r.get("currencyname")==null?"":r.get("currencyname").toString())
					.amendid(r.get("amendid")==null?"":r.get("amendid").toString())
					.inceptiondate(r.get("incepDate")==null?"":DateFormat(r.get("incepDate")).toString())
					.expirydate(r.get("expirydate")==null?"":DateFormat(r.get("expirydate")).toString())
					.effectivedate(r.get("effectivedate")==null?"":DateFormat(r.get("effectivedate")).toString())
					.entrydate(r.get("entrydate")==null?"":DateFormat(r.get("entrydate")).toString())
					.remarks(r.get("remarks")==null?"":r.get("remarks").toString())
					.status(r.get("status")==null?"":r.get("status").toString())
					.rsacode(r.get("rsacode")==null?"":r.get("rsacode").toString())
					.sno(r.get("sno")==null?"":r.get("sno").toString())
					.countryid(r.get("countryId")==null?"":r.get("countryId").toString())
					.ountryname(r.get("countryname")==null?"":r.get("countryname").toString())
					.displayorder(r.get("displayorder")==null?"":r.get("displayorder").toString())
					.build();
					response.add(m);
				});
				res.setMessage("SUCCESS");
				res.setResponse(response);
			}else {
				res.setMessage("FAILED");
				res.setResponse(response);
			}
		} catch (Exception e) {
			log.info(e);
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public MadisonCommonRes EditForeignExchange(String exchangeId) {
		MadisonCommonRes res = new MadisonCommonRes();
		List<EditForeignExchangeRes> response = new ArrayList<EditForeignExchangeRes>();
		try {
			List<Tuple> list = query.EditForeignExchange(exchangeId);
			if(!CollectionUtils.isEmpty(list)) {
				list.forEach(r->{
					EditForeignExchangeRes k = EditForeignExchangeRes.builder()
					.exchangeId(r.get("exchId")==null?"":r.get("exchId").toString())
					.exchangeRate(r.get("exchRate")==null?"":r.get("exchRate").toString())
					.currencyid(r.get("currencyId")==null?"":r.get("currencyId").toString())
					.currencyname(r.get("currencyname")==null?"":r.get("currencyname").toString())
					.amendid(r.get("amendid")==null?"":r.get("amendid").toString())
					.inceptiondate(r.get("incepDate")==null?"":DateFormat(r.get("incepDate")).toString())
					.expirydate(r.get("expirydate")==null?"":DateFormat(r.get("expirydate")).toString())
					.effectivedate(r.get("effectivedate")==null?"":DateFormat(r.get("effectivedate")).toString())
					.entrydate(r.get("entrydate")==null?"":DateFormat(r.get("entrydate")).toString())
					.remarks(r.get("remarks")==null?"":r.get("remarks").toString())
					.status(r.get("status")==null?"":r.get("status").toString())
					.rsacode(r.get("rsacode")==null?"":r.get("rsacode").toString())
					.sno(r.get("sno")==null?"":r.get("sno").toString())
					.countryid(r.get("countryId")==null?"":r.get("countryId").toString())
					.countryname(r.get("countryname")==null?"":r.get("countryname").toString())
					.displayorder(r.get("displayorder")==null?"":r.get("displayorder").toString())
					.build();
					response.add(k);
				});
				res.setMessage("SUCCESS");
				res.setResponse(response);
			}else {
				res.setMessage("FAILED");
				res.setResponse(response);
			}
		} catch (Exception e) {
			log.info(e);
			e.printStackTrace();
		}
		return res;
	}
	
	

}
