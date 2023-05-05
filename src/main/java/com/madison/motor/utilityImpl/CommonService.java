package com.madison.motor.utilityImpl;

import java.io.InputStream;
import java.security.spec.KeySpec;
import java.util.Properties;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CommonService {
	
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	Logger log =LogManager.getLogger(CommonService.class);
	
	public static SecretKey key ;
	public static Cipher cipher ;
	
	private static final String FORMAT = "ISO-8859-1";
	
	
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
		
	
	public static String encrypt(String unencryptedString) throws Exception {

        String encryptedString = null;
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] plainText = unencryptedString.getBytes(FORMAT);
        byte[] encryptedText = cipher.doFinal(plainText);
        encryptedString = DatatypeConverter.printBase64Binary(encryptedText);
        return encryptedString;
    }
	
	public static String decrypt(String encryptedString)  throws Exception {

        String decryptedText = null;
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] encryptedText = DatatypeConverter.parseBase64Binary(encryptedString.replace(" ", "+"));
        byte[] plainText = cipher.doFinal(encryptedText);
        decryptedText = new String(plainText);

        return decryptedText;
    }
	
	static{
		try{
			final String FORMAT = "ISO-8859-1";
		    final String DESEDE_ENCRYPTION_SCHEME = "DESede"; 
			String secretKey = "rsaassociatesiLinkELoungeegnuoLEkniLisetaicossaasr";
			KeySpec ks = new DESedeKeySpec(secretKey.getBytes(FORMAT));
			SecretKeyFactory skf = SecretKeyFactory.getInstance(DESEDE_ENCRYPTION_SCHEME);
			cipher = Cipher.getInstance(DESEDE_ENCRYPTION_SCHEME);
			key = skf.generateSecret(ks);
			System.out.println(key);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
