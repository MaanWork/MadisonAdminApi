package com.madison.motor.serviceImpl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Tuple;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.madison.motor.entity.BranchMaster;
import com.madison.motor.entity.BrokerBranchMaster;
import com.madison.motor.entity.ListItemValue;
import com.madison.motor.repository.BranchMasterRepository;
import com.madison.motor.repository.BrokerBranchMasterRepository;
import com.madison.motor.repository.ListItemValueRepository;
import com.madison.motor.request.GetAdminProductReq;
import com.madison.motor.request.GetConditionReq;
import com.madison.motor.request.GetReferralProductReq;
import com.madison.motor.request.InsertConditionReq;
import com.madison.motor.response.DropdownRes;
import com.madison.motor.response.MadisonCommonRes;
import com.madison.motor.service.DropDownService;
import com.madison.motor.utilityImpl.CriteriaQueryImpl;

@Service
public class DropDownServiceImpl implements DropDownService{

	Logger log =LogManager.getLogger(DropDownServiceImpl.class);
	
	@Autowired
	private ListItemValueRepository listItemRepo;
	
	@Autowired
	private CriteriaQueryImpl query;
	
	@Autowired
	private BranchMasterRepository branchRepo;
	
	@Autowired
	private BrokerBranchMasterRepository brokerBranchRepo;
	
	@Override
	public MadisonCommonRes getPortFolioType() {
		MadisonCommonRes res = new MadisonCommonRes();
		List<DropdownRes> response =new ArrayList<DropdownRes>();
		try {
			List<ListItemValue>list =listItemRepo.findByItemTypeIgnoreCaseAndStatusIgnoreCase("PORTFOLIO_TYPE","Y");
			if(!CollectionUtils.isEmpty(list)) {
				list.forEach(p ->{
					DropdownRes dropdownRes =DropdownRes.builder()
							.code(StringUtils.isBlank(p.getItemCode())?"":p.getItemCode())
							.description(StringUtils.isBlank(p.getItemValue())?"":p.getItemValue())
							.build();
					
					response.add(dropdownRes);
				});
				res.setMessage("FAILED");
				res.setResponse(response);
			}else {
				res.setMessage("FAILED");
				res.setResponse(response);
			}
		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return res;
	}
	
	@Override
	public MadisonCommonRes getBranchByLoginId(String loginId) {
		MadisonCommonRes res = new MadisonCommonRes();
		Set<DropdownRes> resList =new HashSet<DropdownRes>();
		try {
			List<Map<String,Object>> list=listItemRepo.getBranchByLoginId(loginId);
			if(list.size()>0) {
					list.forEach(p ->{
						DropdownRes r =DropdownRes.builder()
							.code(p.get("BRANCH_CODE")==null?"":p.get("BRANCH_CODE").toString())
							.description(p.get("BRANCH_NAME")==null?"":p.get("BRANCH_NAME").toString())
							.build();
					resList.add(r);
					});
				res.setMessage("SUCCESS");
				res.setResponse(resList.stream().sorted(Comparator.comparing(DropdownRes ::getDescription)));
			}else {
				res.setMessage("FAILED");
				res.setResponse(resList);
			}
		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public MadisonCommonRes getReferalStatus() {
		MadisonCommonRes response = new MadisonCommonRes();
		try {
			List<DropdownRes> list = new ArrayList<DropdownRes>();
			list.add(new DropdownRes( "Y","Accept"));
			list.add(new DropdownRes( "N","Reject"));
			list.add(new DropdownRes( "A","Pending"));
			
			response.setMessage("SUCCESS");
			response.setResponse(list);
		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public MadisonCommonRes getConditionList(GetConditionReq req) {
		MadisonCommonRes res = new MadisonCommonRes();
		Set<DropdownRes> resList =new HashSet<DropdownRes>();
		try {
			List<Map<String,Object>> list=listItemRepo.getConditionList(req.getPolicyTypeId(),req.getType(), req.getQuoteNo());
			if(list.size()>0) {
					list.forEach(p ->{
						DropdownRes r =DropdownRes.builder()
							.code(p.get("COREAPPCODE")==null?"":p.get("COREAPPCODE").toString())
							.description(p.get("CODE_DESC")==null?"":p.get("CODE_DESC").toString())
							.build();
					resList.add(r);
					});
				res.setMessage("SUCCESS");
				res.setResponse(resList);
			}else {
				res.setMessage("FAILED");
				res.setResponse(resList);
			}
		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public MadisonCommonRes editConditionList(String quoteNo,String contype) {
		MadisonCommonRes res = new MadisonCommonRes();
		Set<DropdownRes> resList =new HashSet<DropdownRes>();
		try {
			List<Map<String,Object>> list=listItemRepo.editConditionList(quoteNo,contype);
			if(list.size()>0) {
					list.forEach(p ->{
						DropdownRes r =DropdownRes.builder()
							.code(p.get("COREAPPCODE")==null?"":p.get("COREAPPCODE").toString())
							.description(p.get("CODE_DESC")==null?"":p.get("CODE_DESC").toString())
							.build();
					resList.add(r);
					});
				res.setMessage("SUCCESS");
				res.setResponse(resList);
			}else {
				res.setMessage("FAILED");
				res.setResponse(resList);
			}
		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public MadisonCommonRes insertCondition(List<InsertConditionReq> req) {
		MadisonCommonRes res = new MadisonCommonRes();
		try {
			for(InsertConditionReq r :req) {
				listItemRepo.deleteConditionList(r.getQuoteNo(), r.getCoreAppCode());
				listItemRepo.insertCondition(r.getQuoteNo(),r.getType(),r.getContype(),r.getType(),r.getQuoteNo(),r.getDescription(),"Y","",r.getCoreAppCode());
			}
			res.setMessage("SUCCESS");
			res.setResponse("Inserted successfully");
		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			res.setMessage("FAILED");
			res.setResponse("Inserted failed");
		}
		return res;
	}

	@Override
	public MadisonCommonRes getAttachedBranch() {
		MadisonCommonRes res = new MadisonCommonRes();
		Set<DropdownRes> resList =new LinkedHashSet<DropdownRes>();
		try {
		List<BranchMaster> list =branchRepo.findByStatusOrderByBranchNameAsc("Y");
		if(!CollectionUtils.isEmpty(list)) {
			list.forEach(d ->{
				DropdownRes dropdownRes =DropdownRes.builder()
						.code(StringUtils.isBlank(d.getBranchCode())?"":d.getBranchCode())
						.description(StringUtils.isBlank(d.getBranchName())?"":d.getBranchName())
						.build();
				resList.add(dropdownRes);
			});
			res.setMessage("SUCCESS");
			res.setResponse(resList);
		}else {
			res.setMessage("FAILED");
			res.setResponse(null);
		}
		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public MadisonCommonRes getNationality() {
		MadisonCommonRes res = new MadisonCommonRes();
		Set<DropdownRes> resList =new LinkedHashSet<DropdownRes>();
		try {
		List<Tuple> list =query.getNationality();
		if(!CollectionUtils.isEmpty(list)) {
			list.forEach(d ->{
				DropdownRes dropdownRes =DropdownRes.builder()
						.code(d.get("countryId")==null?"":d.get("countryId").toString())
						.description(d.get("nationalityName")==null?"":d.get("nationalityName").toString())
						.build();
				resList.add(dropdownRes);
			});
			res.setMessage("SUCCESS");
			res.setResponse(resList);
		}else {
			res.setMessage("FAILED");
			res.setResponse(null);
		}
		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public MadisonCommonRes getUserType() {
		MadisonCommonRes res = new MadisonCommonRes();
		List<DropdownRes> response =new ArrayList<DropdownRes>();
		try {
			List<ListItemValue>list =listItemRepo.findByItemTypeIgnoreCaseAndStatusIgnoreCase("USERTYPE","Y");
			if(!CollectionUtils.isEmpty(list)) {
				list.forEach(p ->{
					DropdownRes dropdownRes =DropdownRes.builder()
							.code(StringUtils.isBlank(p.getItemCode())?"":p.getItemCode())
							.description(StringUtils.isBlank(p.getItemValue())?"":p.getItemValue())
							.build();
					
					response.add(dropdownRes);
				});
				res.setMessage("FAILED");
				res.setResponse(response);
			}else {
				res.setMessage("FAILED");
				res.setResponse(response);
			}
		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public MadisonCommonRes getExeutiveBdm() {
		MadisonCommonRes res = new MadisonCommonRes();
		List<DropdownRes> resList =new ArrayList<DropdownRes>();
		try {
			List<HashMap<String,Object>> list =query.getExeutiveBdm();	
			if(list.size()>0) {
				list.forEach(p ->{
					DropdownRes r =DropdownRes.builder()
						.code(p.get("AC_EXECUTIVE_ID")==null?"":p.get("AC_EXECUTIVE_ID").toString())
						.description(p.get("AC_EXECUTIVE_NAME")==null?"":p.get("AC_EXECUTIVE_NAME").toString())
						.build();
				resList.add(r);
				});
			res.setMessage("SUCCESS");
			res.setResponse(resList);
		}else {
			res.setMessage("FAILED");
			res.setResponse(resList);
		}
	}catch (Exception e) {
		log.error(e);
		e.printStackTrace();
	}
		return res;
	}

	@Override
	public MadisonCommonRes getsubBranchByBranchCode(String branchCode) {
		MadisonCommonRes res = new MadisonCommonRes();
		List<DropdownRes> resList =new ArrayList<DropdownRes>();

		try {
			String[] branchArray =branchCode.split(",");
			List<BrokerBranchMaster> list= brokerBranchRepo.findByMgenBranchIdInAndStatusOrderByBranchId(branchArray,"Y");
			if(!CollectionUtils.isEmpty(list)) {
				list.forEach(d ->{
					DropdownRes dropdownRes =DropdownRes.builder()
							.code(StringUtils.isBlank(d.getBranchCode())?"":d.getBranchCode())
							.description(StringUtils.isBlank(d.getBranchName())?"":d.getBranchName())
							.build();
					resList.add(dropdownRes);
				});
				res.setMessage("SUCCESS");
				res.setResponse(resList);
			}else {
				res.setMessage("FAILED");
				res.setResponse(resList);
			}
		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public MadisonCommonRes getcountryByBranchCode(String branchCode) {
		MadisonCommonRes res = new MadisonCommonRes();
		List<DropdownRes> relist = new ArrayList<DropdownRes>();
		List<Tuple> list = query.getcountryByBranchCode(branchCode);
		if(!CollectionUtils.isEmpty(list)) {
			list.forEach( m ->{
				DropdownRes r = DropdownRes.builder()
				.code(m.get("code")==null?"":m.get("code").toString())
				.description(m.get("description")==null?"":m.get("description").toString())
				.build();
				relist.add(r);
			});
			res.setMessage("SUCCESS");
			res.setResponse(relist);
		}else {
			res.setMessage("FAILED");
			res.setResponse(relist);
		}
		
		return res;
	}

	@Override
	public MadisonCommonRes getstatus() {
		MadisonCommonRes res = new MadisonCommonRes();
		List<DropdownRes> response = new ArrayList<DropdownRes>();
		List<Tuple> list = query.getstatus();
		if(!CollectionUtils.isEmpty(list)) {
			list.forEach(m->{
				DropdownRes r = DropdownRes.builder()
				.code(m.get("code")==null?"":m.get("code").toString())
				.description(m.get("description")==null?"":m.get("description").toString())
				.build();
				response.add(r);
			});
			res.setMessage("SUCCESS");
			res.setResponse(response);
		}else {
			res.setMessage("FAILED");
			res.setResponse(response);
		}
		return res;
	}

	@Override
	public MadisonCommonRes getbranchListByAgencyCode(String agencyCode) {
		MadisonCommonRes res = new MadisonCommonRes();
		List<DropdownRes> response = new ArrayList<>();
		List<Tuple> list = listItemRepo.getbranchListByAgencyCode(agencyCode);
		if(!CollectionUtils.isEmpty(list)) {
			list.forEach(m -> {
				DropdownRes r = DropdownRes.builder()
				.code(m.get("BRANCH_CODE")==null?"":m.get("BRANCH_CODE").toString())
				.description(m.get("BRANCH_NAME")==null?"":m.get("BRANCH_NAME").toString())
				.build();
				response.add(r);
			});
			res.setResponse(response);
			res.setMessage("SUCCESS");
		}else {
			res.setResponse(response);
			res.setMessage("ERROR");
		}
		return res;
	}

	@Override
	public MadisonCommonRes getAdminUsertype(String branchCode,String appId) {
		MadisonCommonRes res = new MadisonCommonRes();
		List<DropdownRes> response = new ArrayList<DropdownRes>();
		List<Tuple> list = listItemRepo.getAdminUsertype(branchCode,"2".equalsIgnoreCase(appId)?"Marine":appId);
		if(!CollectionUtils.isEmpty(list)) {
			list.forEach( m->{
				DropdownRes k = DropdownRes.builder()
				.code(m.get("Code")==null?"":m.get("Code").toString())
				.description(m.get("Description")==null?"":m.get("Description").toString())
				.build();
				response.add(k);
			});
			res.setResponse(response);
			res.setMessage("SUCCESS");
		}else {
			res.setResponse(response);
			res.setMessage("FAILED");
		}
		return res;
	}
	
	public MadisonCommonRes getAdminProduct(GetAdminProductReq req) {
		MadisonCommonRes res = new MadisonCommonRes();
		List<DropdownRes> response = new ArrayList<DropdownRes>();
		List<Tuple> list = query.getAdminProduct(req.getBranchCode(),req.getAgencyCode());
		
		if(!CollectionUtils.isEmpty(list)) {
			list.forEach(m->{
				DropdownRes k = DropdownRes.builder()
				.code(m.get("Code")==null?"":m.get("Code").toString())
				.description(m.get("Description")==null?"":m.get("Description").toString())
				.build();
				response.add(k);
			});
			res.setResponse(response);
			res.setMessage("SUCCESS");
		}else {
			res.setResponse(response);
			res.setMessage("FAILED");
		}
		return res;
	}

	@Override
	public MadisonCommonRes getReferralProduct(GetReferralProductReq req) {
		MadisonCommonRes res = new MadisonCommonRes();
		List<DropdownRes> response = new ArrayList<DropdownRes>();
		try {
			List<Tuple> list = query.getReferralProduct(req);
			if(!CollectionUtils.isEmpty(list)) {
				list.forEach(m->{
					DropdownRes k = DropdownRes.builder()
						.code(m.get("code")==null?"":m.get("code").toString())
						.description(m.get("description")==null?"":m.get("description").toString())
						.build();
					response.add(k);
				});
				res.setResponse(response);
				res.setMessage("SUCCESS");
			}else {
				res.setResponse(response);
				res.setMessage("ERROR");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
			return res;
	}

}
