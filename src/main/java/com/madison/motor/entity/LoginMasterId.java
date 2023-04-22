/*
 * Created on 2023-02-13 ( 15:54:39 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.3.0
 */
package com.madison.motor.entity;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;



/**
 * Composite primary key for entity "LoginMaster" ( stored in table "LOGIN_MASTER" )
 *
 * @author Telosys
 *
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class LoginMasterId implements Serializable {

    private static final long serialVersionUID = 1L;

    //--- ENTITY KEY ATTRIBUTES 
    private String     loginId ;
    
    private String     agencyCode ;
    
     
}