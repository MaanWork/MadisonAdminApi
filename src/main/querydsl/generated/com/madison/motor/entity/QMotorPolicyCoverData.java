package com.madison.motor.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMotorPolicyCoverData is a Querydsl query type for MotorPolicyCoverData
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMotorPolicyCoverData extends EntityPathBase<MotorPolicyCoverData> {

    private static final long serialVersionUID = 1244854794L;

    public static final QMotorPolicyCoverData motorPolicyCoverData = new QMotorPolicyCoverData("motorPolicyCoverData");

    public final NumberPath<java.math.BigDecimal> amendId = createNumber("amendId", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> applicationNo = createNumber("applicationNo", java.math.BigDecimal.class);

    public final StringPath branchCode = createString("branchCode");

    public final StringPath currencyId = createString("currencyId");

    public final StringPath description = createString("description");

    public final NumberPath<java.math.BigDecimal> displayOrder = createNumber("displayOrder", java.math.BigDecimal.class);

    public final DateTimePath<java.util.Date> effectiveDate = createDateTime("effectiveDate", java.util.Date.class);

    public final DateTimePath<java.util.Date> expiryDate = createDateTime("expiryDate", java.util.Date.class);

    public final StringPath groupDesc = createString("groupDesc");

    public final NumberPath<java.math.BigDecimal> groupId = createNumber("groupId", java.math.BigDecimal.class);

    public final StringPath opcoverDesc = createString("opcoverDesc");

    public final NumberPath<java.math.BigDecimal> policytypeCoverid = createNumber("policytypeCoverid", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> premium = createNumber("premium", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> quoteNo = createNumber("quoteNo", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> rate = createNumber("rate", java.math.BigDecimal.class);

    public final StringPath remarks = createString("remarks");

    public final StringPath status = createString("status");

    public final NumberPath<java.math.BigDecimal> sumInsured = createNumber("sumInsured", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> usdPremium = createNumber("usdPremium", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> usdRate = createNumber("usdRate", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> usdSumInsured = createNumber("usdSumInsured", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> vehicleId = createNumber("vehicleId", java.math.BigDecimal.class);

    public QMotorPolicyCoverData(String variable) {
        super(MotorPolicyCoverData.class, forVariable(variable));
    }

    public QMotorPolicyCoverData(Path<? extends MotorPolicyCoverData> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMotorPolicyCoverData(PathMetadata metadata) {
        super(MotorPolicyCoverData.class, metadata);
    }

}

