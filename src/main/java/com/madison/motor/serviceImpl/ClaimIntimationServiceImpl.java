package com.madison.motor.serviceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Tuple;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.madison.motor.entity.MotorClaimIntimationDtl;
import com.madison.motor.repository.MotorClaimIntimationDtlRepository;
import com.madison.motor.request.InsertClaimIntimationReq;
import com.madison.motor.request.RejectEditReq;
import com.madison.motor.response.ClaimIntimationListRes;
import com.madison.motor.response.MadisonCommonRes;
import com.madison.motor.response.RejectEditRes;
import com.madison.motor.service.ClaimIntimationService;
import com.madison.motor.utilityImpl.CommonService;
import com.madison.motor.utilityImpl.CriteriaQueryImpl;

@Service
public class ClaimIntimationServiceImpl implements ClaimIntimationService{

Logger log =LogManager.getLogger(ClaimIntimationServiceImpl.class);
	
	@Autowired
	private CommonService cs;
	
	@Autowired
	private MotorClaimIntimationDtlRepository MotorClaimIntimationDtlRepository;
	
	@Autowired
	private CriteriaQueryImpl query;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@Override
	public MadisonCommonRes ClaimIntimationList(String status) {
		MadisonCommonRes res = new MadisonCommonRes();
		List<ClaimIntimationListRes> response = new ArrayList<ClaimIntimationListRes>();
		try {
			List<Tuple> ClaimIntimationList = query.ClaimIntimationList(status);
			if(!CollectionUtils.isEmpty(ClaimIntimationList)) {
				ClaimIntimationList.forEach(p -> {
					ClaimIntimationListRes r = ClaimIntimationListRes.builder()
					.name(p.get("name")==null?"":p.get("name").toString())
					.nrcPassportNo(p.get("nrcPassportNo")==null?"":p.get("nrcPassportNo").toString())
					.phoneNo(p.get("phoneno")==null?"":p.get("phoneno").toString())
					.policyNumber(p.get("policynumber")==null?"":p.get("policynumber").toString())
					.vehicleRegNo(p.get("vehicleRefno")==null?"":p.get("vehicleRefno").toString())
					.dateofAccident(p.get("dateofaccident")==null?"":p.get("dateofaccident").toString())
					.status(p.get("status")==null?"":p.get("status").toString())
					.entryDate(p.get("entrydate")==null?"":p.get("entrydate").toString())
					.remarks(p.get("remarks")==null?"":p.get("remarks").toString())
					.claimId(p.get("claimid")==null?"":p.get("claimid").toString())
					.claimRef(p.get("claimref")==null?"":p.get("claimref").toString())
					.build();
					response.add(r);
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
	public MadisonCommonRes RejectEdit(RejectEditReq req) {
		MadisonCommonRes res = new MadisonCommonRes();
		List<RejectEditRes> response = new ArrayList<RejectEditRes>();
		try {
			List<Tuple> rejectEdit = query.getRejectEdit(req);
				if(!CollectionUtils.isEmpty(rejectEdit)) {
					rejectEdit.forEach(p->{
						RejectEditRes r = RejectEditRes.builder()
							.name(p.get("name")==null?"":p.get("name").toString())
							.nrcPassportNo(p.get("nrcPassportNo")==null?"":p.get("nrcPassportNo").toString())
							.phoneNo(p.get("phoneno")==null?"":p.get("phoneno").toString())
							.policyNumber(p.get("policynumber")==null?"":p.get("policynumber").toString())
							.vehicleRegNo(p.get("vehicleRefno")==null?"":p.get("vehicleRefno").toString())
							.dateofAccident(p.get("dateofaccident")==null?"":p.get("dateofaccident").toString())
							.status(p.get("status")==null?"":p.get("status").toString())
							.entryDate(p.get("entrydate")==null?"":p.get("entrydate").toString())
							.remarks(p.get("remarks")==null?"":p.get("remarks").toString())
							.claimId(p.get("claimid")==null?"":p.get("claimid").toString())
							.claimRef(p.get("claimref")==null?"":p.get("claimref").toString())
							.build();
							response.add(r);
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
	public MadisonCommonRes InsertClaimIntimation(InsertClaimIntimationReq req) {
		MadisonCommonRes res = new MadisonCommonRes();
		Long ClaimRefSeq = null;
		try {
			if(StringUtils.isBlank(req.getClaimRef()))
				ClaimRefSeq = query.getClaimRefSeq();
				Long ClaimId = query.getClaimIdSum();
				MotorClaimIntimationDtl insertClaim = MotorClaimIntimationDtl.builder()
					.name(StringUtils.isBlank(req.getName())?"":req.getName())
					.nrcPassportNo(StringUtils.isBlank(req.getNrcPassportNo())?"":req.getNrcPassportNo())
					.phoneno(StringUtils.isBlank(req.getPhoneNo())?"":req.getPhoneNo())
					.policynumber(StringUtils.isBlank(req.getPolicyNumber())?"":req.getPolicyNumber())
					.vehicleRefno(StringUtils.isBlank(req.getVehicleRegNo())?"":req.getVehicleRegNo())
					.dateofaccident(StringUtils.isBlank(req.getDateofAccident())?null:sdf.parse(req.getDateofAccident()))
					.entrydate(new Date())
					.claimid(StringUtils.isBlank(req.getClaimId())? ClaimId : Long.valueOf(req.getClaimId()))
					.status(StringUtils.isBlank(req.getStatus())? "P" : req.getStatus())
					.claimref(StringUtils.isBlank(req.getClaimRef())?ClaimRefSeq:Long.valueOf(req.getClaimRef()))
					.remarks(req.getRemarks())
					.statusupdate(("R".equalsIgnoreCase(req.getStatus()))? new Date():null)
					.build();
					MotorClaimIntimationDtlRepository.save(insertClaim);
					res.setMessage("SUCCESS");
					res.setResponse("Insert Successful");
						
		} catch (ParseException e) {
			log.info(e);
			e.printStackTrace();
		}
		
		return res;
	}
	

}
