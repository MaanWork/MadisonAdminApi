/*
 * Created on 2023-02-13 ( 15:58:52 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.3.0
 */
package com.madison.motor.entity;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


import java.math.BigDecimal;

/**
 * Composite primary key for entity "PaymentDetail" ( stored in table "PAYMENT_DETAIL" )
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
public class PaymentDetailId implements Serializable {

    private static final long serialVersionUID = 1L;

    //--- ENTITY KEY ATTRIBUTES 
    private BigDecimal quoteNo ;
    
    private BigDecimal productId ;
    
    private String     merchantReference ;
    
     
}
