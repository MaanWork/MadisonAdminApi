/**
 * 
 */
package com.madison.motor.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Bhuvanesh
 *
 */

@Getter
@Setter
public class UpdatePasswordReq {
	
	@JsonProperty("Password")
	private String password;
	
	@JsonProperty("Agencycode")
	private String agencycode;
	
	@JsonProperty("LoginId")
	private String loginId;
	
	@JsonProperty("Key")
	private String key;

}
