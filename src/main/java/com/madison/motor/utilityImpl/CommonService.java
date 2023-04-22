package com.madison.motor.utilityImpl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CommonService {
	
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	Logger log =LogManager.getLogger(CommonService.class);
	
	
	
	public String printReq(Object req) {
		String response="";
		try {
			response=mapper.writeValueAsString(req);
		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return response;
	}
	
	
	public Properties getApplicationConstants() {
		Properties properties = new Properties();
		InputStream is =getClass().getClassLoader().getResourceAsStream("application.constants");
		try {
			properties.load(is);
		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return properties;
	}
		
}
