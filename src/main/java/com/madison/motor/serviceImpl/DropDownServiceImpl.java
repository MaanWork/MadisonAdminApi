package com.madison.motor.serviceImpl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;


import com.madison.motor.entity.ListItemValue;
import com.madison.motor.repository.ListItemValueRepository;
import com.madison.motor.response.DropdownRes;
import com.madison.motor.response.MadisonCommonRes;
import com.madison.motor.service.DropDownService;

@Service
public class DropDownServiceImpl implements DropDownService{

	Logger log =LogManager.getLogger(DropDownServiceImpl.class);
	
	@Autowired
	private ListItemValueRepository listItemRepo;
	
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

}
