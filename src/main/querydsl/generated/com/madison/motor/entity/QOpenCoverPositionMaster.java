package com.madison.motor.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QOpenCoverPositionMaster is a Querydsl query type for OpenCoverPositionMaster
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOpenCoverPositionMaster extends EntityPathBase<OpenCoverPositionMaster> {

    private static final long serialVersionUID = -249160824L;

    public static final QOpenCoverPositionMaster openCoverPositionMaster = new QOpenCoverPositionMaster("openCoverPositionMaster");

    public final StringPath adminstatus = createString("adminstatus");

    public final StringPath amendedclauseprintstatus = createString("amendedclauseprintstatus");

    public final NumberPath<Long> amendId = createNumber("amendId", Long.class);

    public final StringPath branchCode = createString("branchCode");

    public final StringPath cancellationclause = createString("cancellationclause");

    public final NumberPath<Long> certno = createNumber("certno", Long.class);

    public final StringPath ckey = createString("ckey");

    public final StringPath customercustomerdebit = createString("customercustomerdebit");

    public final StringPath customerdebit = createString("customerdebit");

    public final StringPath customerschedule = createString("customerschedule");

    public final DateTimePath<java.util.Date> effectivedate = createDateTime("effectivedate", java.util.Date.class);

    public final StringPath endtstatus = createString("endtstatus");

    public final StringPath endttype = createString("endttype");

    public final DateTimePath<java.util.Date> entrydate = createDateTime("entrydate", java.util.Date.class);

    public final DateTimePath<java.util.Date> expirydate = createDateTime("expirydate", java.util.Date.class);

    public final DateTimePath<java.util.Date> inceptiondate = createDateTime("inceptiondate", java.util.Date.class);

    public final StringPath integrationerror = createString("integrationerror");

    public final StringPath integrationstatus = createString("integrationstatus");

    public final StringPath opencoverno = createString("opencoverno");

    public final StringPath originalpolicyno = createString("originalpolicyno");

    public final StringPath proposalno = createString("proposalno");

    public final StringPath rateprintstatus = createString("rateprintstatus");

    public final StringPath remarks = createString("remarks");

    public final StringPath renewalerror = createString("renewalerror");

    public final StringPath renewalstatus = createString("renewalstatus");

    public final StringPath status = createString("status");

    public QOpenCoverPositionMaster(String variable) {
        super(OpenCoverPositionMaster.class, forVariable(variable));
    }

    public QOpenCoverPositionMaster(Path<? extends OpenCoverPositionMaster> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOpenCoverPositionMaster(PathMetadata metadata) {
        super(OpenCoverPositionMaster.class, metadata);
    }

}

