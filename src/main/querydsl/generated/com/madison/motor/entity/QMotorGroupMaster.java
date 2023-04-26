package com.madison.motor.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMotorGroupMaster is a Querydsl query type for MotorGroupMaster
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMotorGroupMaster extends EntityPathBase<MotorGroupMaster> {

    private static final long serialVersionUID = 1741257308L;

    public static final QMotorGroupMaster motorGroupMaster = new QMotorGroupMaster("motorGroupMaster");

    public final NumberPath<java.math.BigDecimal> amendId = createNumber("amendId", java.math.BigDecimal.class);

    public final StringPath branchCode = createString("branchCode");

    public final StringPath calcType = createString("calcType");

    public final StringPath coreAppCode = createString("coreAppCode");

    public final NumberPath<java.math.BigDecimal> displayOrder = createNumber("displayOrder", java.math.BigDecimal.class);

    public final DateTimePath<java.util.Date> effectiveDate = createDateTime("effectiveDate", java.util.Date.class);

    public final DateTimePath<java.util.Date> expiryDate = createDateTime("expiryDate", java.util.Date.class);

    public final StringPath groupDescEnglish = createString("groupDescEnglish");

    public final NumberPath<java.math.BigDecimal> groupId = createNumber("groupId", java.math.BigDecimal.class);

    public final StringPath intType = createString("intType");

    public final StringPath remarks = createString("remarks");

    public final StringPath status = createString("status");

    public QMotorGroupMaster(String variable) {
        super(MotorGroupMaster.class, forVariable(variable));
    }

    public QMotorGroupMaster(Path<? extends MotorGroupMaster> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMotorGroupMaster(PathMetadata metadata) {
        super(MotorGroupMaster.class, metadata);
    }

}

