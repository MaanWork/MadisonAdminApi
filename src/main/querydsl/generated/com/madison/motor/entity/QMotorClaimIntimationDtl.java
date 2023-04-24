package com.madison.motor.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMotorClaimIntimationDtl is a Querydsl query type for MotorClaimIntimationDtl
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMotorClaimIntimationDtl extends EntityPathBase<MotorClaimIntimationDtl> {

    private static final long serialVersionUID = -1644510941L;

    public static final QMotorClaimIntimationDtl motorClaimIntimationDtl = new QMotorClaimIntimationDtl("motorClaimIntimationDtl");

    public final NumberPath<Long> claimid = createNumber("claimid", Long.class);

    public final NumberPath<Long> claimref = createNumber("claimref", Long.class);

    public final DateTimePath<java.util.Date> dateofaccident = createDateTime("dateofaccident", java.util.Date.class);

    public final DateTimePath<java.util.Date> entrydate = createDateTime("entrydate", java.util.Date.class);

    public final StringPath name = createString("name");

    public final StringPath nrcPassportNo = createString("nrcPassportNo");

    public final StringPath phoneno = createString("phoneno");

    public final StringPath policynumber = createString("policynumber");

    public final StringPath remarks = createString("remarks");

    public final StringPath status = createString("status");

    public final DateTimePath<java.util.Date> statusupdate = createDateTime("statusupdate", java.util.Date.class);

    public final StringPath vehicleRefno = createString("vehicleRefno");

    public QMotorClaimIntimationDtl(String variable) {
        super(MotorClaimIntimationDtl.class, forVariable(variable));
    }

    public QMotorClaimIntimationDtl(Path<? extends MotorClaimIntimationDtl> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMotorClaimIntimationDtl(PathMetadata metadata) {
        super(MotorClaimIntimationDtl.class, metadata);
    }

}

