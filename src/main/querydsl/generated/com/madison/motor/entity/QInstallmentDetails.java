package com.madison.motor.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QInstallmentDetails is a Querydsl query type for InstallmentDetails
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QInstallmentDetails extends EntityPathBase<InstallmentDetails> {

    private static final long serialVersionUID = -1987061031L;

    public static final QInstallmentDetails installmentDetails = new QInstallmentDetails("installmentDetails");

    public final NumberPath<Long> applicationNo = createNumber("applicationNo", Long.class);

    public final NumberPath<Long> balanceamount = createNumber("balanceamount", Long.class);

    public final StringPath currencyType = createString("currencyType");

    public final StringPath custId = createString("custId");

    public final StringPath description = createString("description");

    public final NumberPath<Long> installmentNo = createNumber("installmentNo", Long.class);

    public final NumberPath<Long> noofemi = createNumber("noofemi", Long.class);

    public final NumberPath<Long> noofmonths = createNumber("noofmonths", Long.class);

    public final NumberPath<Long> noofterms = createNumber("noofterms", Long.class);

    public final NumberPath<Long> overallpremium = createNumber("overallpremium", Long.class);

    public final StringPath paymentstatus = createString("paymentstatus");

    public final NumberPath<Long> premiumamount = createNumber("premiumamount", Long.class);

    public final DateTimePath<java.util.Date> premiumdate = createDateTime("premiumdate", java.util.Date.class);

    public final NumberPath<Long> productId = createNumber("productId", Long.class);

    public final NumberPath<Long> quoteNo = createNumber("quoteNo", Long.class);

    public final StringPath remarks = createString("remarks");

    public final StringPath status = createString("status");

    public final NumberPath<Long> usdpremiumamount = createNumber("usdpremiumamount", Long.class);

    public final NumberPath<Long> zmwpremiumamount = createNumber("zmwpremiumamount", Long.class);

    public QInstallmentDetails(String variable) {
        super(InstallmentDetails.class, forVariable(variable));
    }

    public QInstallmentDetails(Path<? extends InstallmentDetails> path) {
        super(path.getType(), path.getMetadata());
    }

    public QInstallmentDetails(PathMetadata metadata) {
        super(InstallmentDetails.class, metadata);
    }

}

