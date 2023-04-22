package com.madison.motor.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBrokerCommissionDetail is a Querydsl query type for BrokerCommissionDetail
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBrokerCommissionDetail extends EntityPathBase<BrokerCommissionDetail> {

    private static final long serialVersionUID = -72296603L;

    public static final QBrokerCommissionDetail brokerCommissionDetail = new QBrokerCommissionDetail("brokerCommissionDetail");

    public final StringPath agencyCode = createString("agencyCode");

    public final NumberPath<java.math.BigDecimal> amendId = createNumber("amendId", java.math.BigDecimal.class);

    public final StringPath checkerYn = createString("checkerYn");

    public final NumberPath<java.math.BigDecimal> commissionPercentage = createNumber("commissionPercentage", java.math.BigDecimal.class);

    public final DateTimePath<java.util.Date> effectiveDate = createDateTime("effectiveDate", java.util.Date.class);

    public final DateTimePath<java.util.Date> endDate = createDateTime("endDate", java.util.Date.class);

    public final DateTimePath<java.util.Date> entryDate = createDateTime("entryDate", java.util.Date.class);

    public final StringPath loginId = createString("loginId");

    public final NumberPath<java.math.BigDecimal> minPremium = createNumber("minPremium", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> otherFee = createNumber("otherFee", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> otherFee1 = createNumber("otherFee1", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> otherFee2 = createNumber("otherFee2", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> otherFee3 = createNumber("otherFee3", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> policyFee = createNumber("policyFee", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> policytype = createNumber("policytype", java.math.BigDecimal.class);

    public final StringPath policytypeOpted = createString("policytypeOpted");

    public final NumberPath<java.math.BigDecimal> productId = createNumber("productId", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> suminsuredEnd = createNumber("suminsuredEnd", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> suminsuredStart = createNumber("suminsuredStart", java.math.BigDecimal.class);

    public QBrokerCommissionDetail(String variable) {
        super(BrokerCommissionDetail.class, forVariable(variable));
    }

    public QBrokerCommissionDetail(Path<? extends BrokerCommissionDetail> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBrokerCommissionDetail(PathMetadata metadata) {
        super(BrokerCommissionDetail.class, metadata);
    }

}

