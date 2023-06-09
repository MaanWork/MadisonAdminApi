/*
 * Created on 2023-02-13 ( 15:54:15 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.3.0
 */
package com.madison.motor.entity;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


import java.math.BigDecimal;

/**
 * Composite primary key for entity "HomePositionMaster" ( stored in table "HOME_POSITION_MASTER" )
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
public class HomePositionMasterId implements Serializable {

    private static final long serialVersionUID = 1L;

    //--- ENTITY KEY ATTRIBUTES 
    private BigDecimal customerId ;
    
    private String     loginId ;
    
    private BigDecimal productId ;
    
    private BigDecimal applicationNo ;
    
     
}
