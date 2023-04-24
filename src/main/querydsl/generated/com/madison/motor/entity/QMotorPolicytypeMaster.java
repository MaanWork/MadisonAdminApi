package com.madison.motor.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMotorPolicytypeMaster is a Querydsl query type for MotorPolicytypeMaster
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMotorPolicytypeMaster extends EntityPathBase<MotorPolicytypeMaster> {

    private static final long serialVersionUID = -602029773L;

    public static final QMotorPolicytypeMaster motorPolicytypeMaster = new QMotorPolicytypeMaster("motorPolicytypeMaster");

    public final NumberPath<java.math.BigDecimal> amendId = createNumber("amendId", java.math.BigDecimal.class);

    public final StringPath branchCode = createString("branchCode");

    public final StringPath coreappCode = createString("coreappCode");

    public final StringPath coreappcode = createString("coreappcode");

    public final DateTimePath<java.util.Date> effectiveDate = createDateTime("effectiveDate", java.util.Date.class);

    public final DateTimePath<java.util.Date> expiryDate = createDateTime("expiryDate", java.util.Date.class);

    public final StringPath paymentYn = createString("paymentYn");

    public final NumberPath<java.math.BigDecimal> policySubtypeId = createNumber("policySubtypeId", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> policyTerm = createNumber("policyTerm", java.math.BigDecimal.class);

    public final StringPath policytypeDescArabic = createString("policytypeDescArabic");

    public final StringPath policytypeDescEnglish = createString("policytypeDescEnglish");

    public final NumberPath<java.math.BigDecimal> policytypeId = createNumber("policytypeId", java.math.BigDecimal.class);

    public final StringPath policytypeName = createString("policytypeName");

    public final StringPath remarks = createString("remarks");

    public final StringPath status = createString("status");

    public final StringPath type = createString("type");

    public QMotorPolicytypeMaster(String variable) {
        super(MotorPolicytypeMaster.class, forVariable(variable));
    }

    public QMotorPolicytypeMaster(Path<? extends MotorPolicytypeMaster> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMotorPolicytypeMaster(PathMetadata metadata) {
        super(MotorPolicytypeMaster.class, metadata);
    }

}

