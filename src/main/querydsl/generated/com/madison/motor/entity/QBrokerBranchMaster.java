package com.madison.motor.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBrokerBranchMaster is a Querydsl query type for BrokerBranchMaster
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBrokerBranchMaster extends EntityPathBase<BrokerBranchMaster> {

    private static final long serialVersionUID = 275151213L;

    public static final QBrokerBranchMaster brokerBranchMaster = new QBrokerBranchMaster("brokerBranchMaster");

    public final StringPath branchCode = createString("branchCode");

    public final NumberPath<java.math.BigDecimal> branchId = createNumber("branchId", java.math.BigDecimal.class);

    public final StringPath branchName = createString("branchName");

    public final StringPath brokerId = createString("brokerId");

    public final StringPath mgenBranchId = createString("mgenBranchId");

    public final StringPath remarks = createString("remarks");

    public final NumberPath<java.math.BigDecimal> sno = createNumber("sno", java.math.BigDecimal.class);

    public final StringPath status = createString("status");

    public QBrokerBranchMaster(String variable) {
        super(BrokerBranchMaster.class, forVariable(variable));
    }

    public QBrokerBranchMaster(Path<? extends BrokerBranchMaster> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBrokerBranchMaster(PathMetadata metadata) {
        super(BrokerBranchMaster.class, metadata);
    }

}

