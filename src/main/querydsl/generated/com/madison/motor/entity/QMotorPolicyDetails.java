package com.madison.motor.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMotorPolicyDetails is a Querydsl query type for MotorPolicyDetails
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMotorPolicyDetails extends EntityPathBase<MotorPolicyDetails> {

    private static final long serialVersionUID = -235219317L;

    public static final QMotorPolicyDetails motorPolicyDetails = new QMotorPolicyDetails("motorPolicyDetails");

    public final NumberPath<java.math.BigDecimal> applicationno = createNumber("applicationno", java.math.BigDecimal.class);

    public final StringPath conditionsClauses1 = createString("conditionsClauses1");

    public final StringPath conditionsClauses2 = createString("conditionsClauses2");

    public final StringPath conditionsClauses3 = createString("conditionsClauses3");

    public final StringPath currencyType = createString("currencyType");

    public final NumberPath<java.math.BigDecimal> isselectcover = createNumber("isselectcover", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> liabilitySi = createNumber("liabilitySi", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> liabilitySiUsd = createNumber("liabilitySiUsd", java.math.BigDecimal.class);

    public final StringPath liabilityYn = createString("liabilityYn");

    public final StringPath optionalCover = createString("optionalCover");

    public final DateTimePath<java.util.Date> policyenddate = createDateTime("policyenddate", java.util.Date.class);

    public final DateTimePath<java.util.Date> policystartdate = createDateTime("policystartdate", java.util.Date.class);

    public final NumberPath<java.math.BigDecimal> policytype = createNumber("policytype", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> premium = createNumber("premium", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> quoteno = createNumber("quoteno", java.math.BigDecimal.class);

    public final StringPath referralRemarks = createString("referralRemarks");

    public QMotorPolicyDetails(String variable) {
        super(MotorPolicyDetails.class, forVariable(variable));
    }

    public QMotorPolicyDetails(Path<? extends MotorPolicyDetails> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMotorPolicyDetails(PathMetadata metadata) {
        super(MotorPolicyDetails.class, metadata);
    }

}

