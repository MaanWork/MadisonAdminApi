package com.madison.motor.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBrokerCompanyMaster is a Querydsl query type for BrokerCompanyMaster
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBrokerCompanyMaster extends EntityPathBase<BrokerCompanyMaster> {

    private static final long serialVersionUID = -229401706L;

    public static final QBrokerCompanyMaster brokerCompanyMaster = new QBrokerCompanyMaster("brokerCompanyMaster");

    public final NumberPath<java.math.BigDecimal> acExecutiveId = createNumber("acExecutiveId", java.math.BigDecimal.class);

    public final StringPath address1 = createString("address1");

    public final StringPath address2 = createString("address2");

    public final StringPath address3 = createString("address3");

    public final StringPath agencyCode = createString("agencyCode");

    public final StringPath approvedPreparedBy = createString("approvedPreparedBy");

    public final StringPath branchCode = createString("branchCode");

    public final StringPath brokerType = createString("brokerType");

    public final StringPath city = createString("city");

    public final StringPath companyName = createString("companyName");

    public final StringPath contactPerson = createString("contactPerson");

    public final StringPath country = createString("country");

    public final NumberPath<java.math.BigDecimal> customerId = createNumber("customerId", java.math.BigDecimal.class);

    public final StringPath emirate = createString("emirate");

    public final StringPath fax = createString("fax");

    public final StringPath imagePath = createString("imagePath");

    public final NumberPath<java.math.BigDecimal> issuerCommissionOneoff = createNumber("issuerCommissionOneoff", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> issuerCommissionOpencover = createNumber("issuerCommissionOpencover", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> missippiId = createNumber("missippiId", java.math.BigDecimal.class);

    public final StringPath phone = createString("phone");

    public final NumberPath<java.math.BigDecimal> pobox = createNumber("pobox", java.math.BigDecimal.class);

    public final StringPath remarks = createString("remarks");

    public final StringPath rsaBrokerCode = createString("rsaBrokerCode");

    public final StringPath status = createString("status");

    public final StringPath subBranch = createString("subBranch");

    public QBrokerCompanyMaster(String variable) {
        super(BrokerCompanyMaster.class, forVariable(variable));
    }

    public QBrokerCompanyMaster(Path<? extends BrokerCompanyMaster> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBrokerCompanyMaster(PathMetadata metadata) {
        super(BrokerCompanyMaster.class, metadata);
    }

}

