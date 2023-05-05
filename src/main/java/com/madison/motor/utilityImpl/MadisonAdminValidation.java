package com.madison.motor.utilityImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.madison.motor.request.BrokerProductSaveReq;
import com.madison.motor.request.BrokerSaveReq;
import com.madison.motor.request.PolicyTypeReq;
import com.madison.motor.request.UserSaveReq;

@Component
public class MadisonAdminValidation {

	   private static final String MOBILE_NO="0[1-9]{9}";
	   private static final String EMAIL="[a-zA-Z]+[0-9]*@[a-zA-Z]+[.][a-zA-Z]+";
	   private static final String PASSWORD ="[a-zA-Z0-9@#!$%^&*+]{7}";
	   private static final String DATE_VAL="[0-9]{2}/[0-9]{2}/[0-9]{4}";
	   
	public List<ErrorList> validateUser(UserSaveReq req){
		ArrayList<ErrorList> list =new ArrayList<ErrorList>();
		try {
			if(StringUtils.isEmpty(req.getBrokerCode())) {
				list.add(new ErrorList("1","Broker","Please choose broker"));
			}if(StringUtils.isBlank(req.getUserType())) {
				list.add(new ErrorList("1","UserType","Please choose usertype"));
			}if(StringUtils.isBlank(req.getTitle())) {
				list.add(new ErrorList("1","Title","Please choose title"));
			}if(StringUtils.isBlank(req.getFirstName())) {
				list.add(new ErrorList("1","FirstName","Please enter firstname"));
			}if(StringUtils.isBlank(req.getNationality())) {
				list.add(new ErrorList("1","Nationality","Please choose nationality"));

			}if(StringUtils.isBlank(req.getCityId())) {
				list.add(new ErrorList("1","City","Please choose city"));

			}if(StringUtils.isBlank(req.getCountryId())) {
				list.add(new ErrorList("1","Country","Please choose country"));

			}if(StringUtils.isBlank(req.getPoBox())) {
				list.add(new ErrorList("1","PoBox","Please enter pobox"));
			}if(StringUtils.isBlank(req.getMobileNo())) {
				list.add(new ErrorList("1","MobileNo","Please enter mobilenumber"));
			}else if(regexVal(req.getMobileNo(),MOBILE_NO)) {
				list.add(new ErrorList("1","MobileNo","MobileNumber should start with 0 & allowed digits 10"));

			}if(StringUtils.isBlank(req.getEmailId())) {
				list.add(new ErrorList("1","EmailId","Please enter email"));
			}else if(regexVal(req.getEmailId(), EMAIL)) {
				list.add(new ErrorList("1","EmailId","Please enter valid email"));
			}if(StringUtils.isBlank(req.getAttachedBranch())) {
				list.add(new ErrorList("1","AttachedBranch","Please choose attached branch"));
			}if(StringUtils.isBlank(req.getLoginId())) {
				list.add(new ErrorList("1","EmailId","Please enter loginId"));
			}if(StringUtils.isBlank(req.getPassword())) {
				list.add(new ErrorList("1","Password","Please enter password"));
			}if(StringUtils.isBlank(req.getConfirmPassword())) {
				list.add(new ErrorList("1","ConfirmPassword","Please enter confirm password"));

			}if(StringUtils.isNotBlank(req.getPassword()) && StringUtils.isNotBlank(req.getConfirmPassword())){
				if(!req.getPassword().equalsIgnoreCase(req.getConfirmPassword())) {
					list.add(new ErrorList("1","Password","Password does not matched"));
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	private  Boolean regexVal(String input,String regex) {
		Boolean status =true;
		try {
			Pattern pattern =Pattern.compile(regex);
			Matcher matcher =pattern.matcher(input);
			if(matcher.find() && input.equals(matcher.group())) {
				status =false;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return status ;
	}
	
	public List<ErrorList> validateBroker(BrokerSaveReq req){
		List<ErrorList> list =new ArrayList<ErrorList>();
		try {
			if(StringUtils.isBlank(req.getApprovedBy())) {
				list.add(new ErrorList("101","ApprovedBy","Please enter approvedby"));
			}if(StringUtils.isBlank(req.getUserType())) {
				list.add(new ErrorList("101","UserType","Please choose usertype"));
			}if(StringUtils.isBlank(req.getBrokerCode())) {
				list.add(new ErrorList("101","BrokerCode","Please enter brokercode"));
			}if(StringUtils.isBlank(req.getBrokerOrganization())) {
				list.add(new ErrorList("101","BrokerCode","Please enter brokerorganization"));
			}if(StringUtils.isBlank(req.getCityId())) {
				list.add(new ErrorList("101","City/Town","Please choose city/town"));
			}if(StringUtils.isBlank(req.getCountryId())) {
				list.add(new ErrorList("101","Country","Please choose country"));

			}if(StringUtils.isBlank(req.getPhoneNo())) {
				list.add(new ErrorList("101","PhoneNo","Please choose phoneNo"));
			}if(StringUtils.isBlank(req.getAttachedBranch())) {
				list.add(new ErrorList("101","AttachedBranch","Please choose attached branch"));

			}if(StringUtils.isBlank(req.getFirstName())) {
				list.add(new ErrorList("101","FirstName","Please enter firstname"));

			}if(StringUtils.isBlank(req.getMobileNo())) {
				list.add(new ErrorList("101","MobileNo","Please enter mobileno"));

			}else if(regexVal(req.getMobileNo(),MOBILE_NO)) {
				list.add(new ErrorList("1","MobileNo","MobileNumber should start with 0 & allowed digits 10"));

			}
			
			
			if(StringUtils.isBlank(req.getEmailId())) {
				list.add(new ErrorList("101","EmailId","Please enter emailId"));

			}else if(regexVal(req.getEmailId(), EMAIL)) {
				list.add(new ErrorList("1","EmailId","Please enter valid email"));
			}if(StringUtils.isBlank(req.getLoginId())) {
				list.add(new ErrorList("101","LoginId","Please enter loginId"));
			}if(StringUtils.isBlank(req.getPassword())) {
				list.add(new ErrorList("101","Password","Please enter password"));

			}if(StringUtils.isBlank(req.getConfirmPassword())) {
				list.add(new ErrorList("101","ConfirmPassword","Please enter confirm password"));

			}if(StringUtils.isNotBlank(req.getPassword()) && StringUtils.isNotBlank(req.getConfirmPassword())){
				if(!req.getPassword().equalsIgnoreCase(req.getConfirmPassword())) {
					list.add(new ErrorList("1","Password","Password does not matched"));
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}
	
	public List<ErrorList> validateBrokerProduct(BrokerProductSaveReq req){
		List<ErrorList> list =new ArrayList<ErrorList>();
		try {
			if(StringUtils.isBlank(req.getCommission())) {
				list.add(new ErrorList("101","Commission","Please enter commission"));
			}else if (!NumberUtils.isParsable(req.getCommission())) {
				list.add(new ErrorList("101","Commission","Commission should not be allowed character & special character"));
			}if(StringUtils.isBlank(req.getSumInsured())) {
				list.add(new ErrorList("101","SumInsured","Please enter suminsured"));
			}else if (!NumberUtils.isParsable(req.getCommission())) {
				list.add(new ErrorList("101","SumInsured","SumInsured should not be allowed character & special character"));
			}if(StringUtils.isBlank(req.getMinimumPre())) {
				list.add(new ErrorList("101","MinimumPremum","Please enter minimumpremium"));
			}else if (!NumberUtils.isParsable(req.getMinimumPre())) {
				list.add(new ErrorList("101","MinimumPremum","MinimumPremum should not be allowed character & special character"));
			}if(StringUtils.isBlank(req.getBackDateAllowed())) {
				list.add(new ErrorList("101","BackDateAllowed","Please enter backdateallowed"));
			}else if (!NumberUtils.isParsable(req.getBackDateAllowed())) {
				list.add(new ErrorList("101","BackDateAllowed","BackDateAllowed should not be allowed character & special character"));
			}if(StringUtils.isBlank(req.getEffectiveDate())) {
				list.add(new ErrorList("101","EffectiveDate","Please enter effectivedate"));
			}else if(regexVal(req.getEffectiveDate(), DATE_VAL)) {
				list.add(new ErrorList("101","EffectiveDate","EffectiveDate format should be like this :DD/MM/YYYY"));
			}if(StringUtils.isBlank(req.getEndDate())) {
				list.add(new ErrorList("101","EndDate","Please enter enddate"));
			}else if(regexVal(req.getEndDate(), DATE_VAL)) {
				list.add(new ErrorList("101","EndDate","EndDate format should be like this :DD/MM/YYYY"));
			}
			
			if(CollectionUtils.isEmpty(req.getPolicyType())) {
				list.add(new ErrorList("101","PolicyType","Please enter policytype details"));

			}else if (!CollectionUtils.isEmpty(req.getPolicyType())) {
				
				for (int i=0;i<req.getPolicyType().size();i++) {
					PolicyTypeReq p =req.getPolicyType().get(i);
					if(StringUtils.isBlank(p.getMinSiValue())) {
						list.add(new ErrorList(""+i+"","MinSiValue","Please enter MinSiValue"));

					}else if (!NumberUtils.isParsable(p.getMinSiValue())) {
						list.add(new ErrorList(""+i+"","MinSiValue","MinSiValue should not be allowed character & special character"));
					}
					if(StringUtils.isBlank(p.getMaxSiValue())) {
						list.add(new ErrorList(""+i+"","MaxSiValue","Please enter MaxSiValue"));

					}else if (!NumberUtils.isParsable(p.getMaxSiValue())) {
						list.add(new ErrorList(""+i+"","MaxSiValue","MaxSiValue should not be allowed character & special character"));
					}
					
					if((StringUtils.isNotBlank(p.getMinSiValue()) && NumberUtils.isParsable(p.getMinSiValue())) 
						&& (StringUtils.isNotBlank(p.getMaxSiValue()) && NumberUtils.isParsable(p.getMaxSiValue()))){
						
						Double minSiValue =Double.valueOf(p.getMinSiValue());
						Double maxSiValue =Double.valueOf(p.getMaxSiValue());
						
						if(minSiValue>=maxSiValue) {
							list.add(new ErrorList(""+i+"","PolicyType","MinSiValue ("+minSiValue+") should not greater than or equal to MaxSiValue ("+maxSiValue+") "));

						}
						
					}
					
					
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
}
