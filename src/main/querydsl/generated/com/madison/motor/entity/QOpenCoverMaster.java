package com.madison.motor.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QOpenCoverMaster is a Querydsl query type for OpenCoverMaster
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOpenCoverMaster extends EntityPathBase<OpenCoverMaster> {

    private static final long serialVersionUID = -1674000897L;

    public static final QOpenCoverMaster openCoverMaster = new QOpenCoverMaster("openCoverMaster");

    public final StringPath additionalinfo = createString("additionalinfo");

    public final StringPath additionalinsured = createString("additionalinsured");

    public final StringPath additionalinsuredname = createString("additionalinsuredname");

    public final NumberPath<Long> amendId = createNumber("amendId", Long.class);

    public final NumberPath<Long> backdatedays = createNumber("backdatedays", Long.class);

    public final StringPath branchCode = createString("branchCode");

    public final StringPath brokerarno = createString("brokerarno");

    public final StringPath brokerId = createString("brokerId");

    public final StringPath brokermissippiId = createString("brokermissippiId");

    public final StringPath brokeruserId = createString("brokeruserId");

    public final NumberPath<Long> businesstype = createNumber("businesstype", Long.class);

    public final StringPath claimratio = createString("claimratio");

    public final StringPath clausesyn = createString("clausesyn");

    public final NumberPath<Long> commission = createNumber("commission", Long.class);

    public final StringPath confirmstatus = createString("confirmstatus");

    public final StringPath countryremarks = createString("countryremarks");

    public final DateTimePath<java.util.Date> creditnotedate = createDateTime("creditnotedate", java.util.Date.class);

    public final StringPath creditnoteno = createString("creditnoteno");

    public final StringPath credittoId = createString("credittoId");

    public final NumberPath<Long> crossminpremiumamount = createNumber("crossminpremiumamount", Long.class);

    public final StringPath crossvoyage = createString("crossvoyage");

    public final NumberPath<Long> crossvoyagepercentage = createNumber("crossvoyagepercentage", Long.class);

    public final NumberPath<Long> crossvoyagesuminsuredlimit = createNumber("crossvoyagesuminsuredlimit", Long.class);

    public final NumberPath<Long> crossvoyageturnover = createNumber("crossvoyageturnover", Long.class);

    public final NumberPath<Long> currencyId = createNumber("currencyId", Long.class);

    public final NumberPath<Long> customerId = createNumber("customerId", Long.class);

    public final StringPath debitnote = createString("debitnote");

    public final DateTimePath<java.util.Date> debitnotedate = createDateTime("debitnotedate", java.util.Date.class);

    public final StringPath debitnotename = createString("debitnotename");

    public final StringPath debitnoteno = createString("debitnoteno");

    public final StringPath debittoId = createString("debittoId");

    public final StringPath declarationtype = createString("declarationtype");

    public final NumberPath<Long> depositamount = createNumber("depositamount", Long.class);

    public final StringPath depositpremiumyn = createString("depositpremiumyn");

    public final StringPath deposittype = createString("deposittype");

    public final DateTimePath<java.util.Date> effectivedate = createDateTime("effectivedate", java.util.Date.class);

    public final NumberPath<Long> exchangerate = createNumber("exchangerate", Long.class);

    public final NumberPath<Long> executiveId = createNumber("executiveId", Long.class);

    public final StringPath existingdeposityn = createString("existingdeposityn");

    public final NumberPath<Long> exportminpremiumamount = createNumber("exportminpremiumamount", Long.class);

    public final NumberPath<Long> foreignturnover = createNumber("foreignturnover", Long.class);

    public final StringPath freetextallowed = createString("freetextallowed");

    public final StringPath guestloginstatus = createString("guestloginstatus");

    public final NumberPath<Long> haulierpremium = createNumber("haulierpremium", Long.class);

    public final NumberPath<Long> importminpremiumamount = createNumber("importminpremiumamount", Long.class);

    public final StringPath installmenttype = createString("installmenttype");

    public final NumberPath<Long> issuancefee = createNumber("issuancefee", Long.class);

    public final NumberPath<Long> issuancefeedebit = createNumber("issuancefeedebit", Long.class);

    public final StringPath loginId = createString("loginId");

    public final StringPath lossdetail = createString("lossdetail");

    public final NumberPath<Long> marinepremium = createNumber("marinepremium", Long.class);

    public final NumberPath<Long> minpremium = createNumber("minpremium", Long.class);

    public final NumberPath<Long> minpremiumissuancefee = createNumber("minpremiumissuancefee", Long.class);

    public final NumberPath<Long> minpremul = createNumber("minpremul", Long.class);

    public final StringPath minpremultype = createString("minpremultype");

    public final NumberPath<Long> missippicode = createNumber("missippicode", Long.class);

    public final StringPath missippiopencoverno = createString("missippiopencoverno");

    public final StringPath mississipiopenpolicyno = createString("mississipiopenpolicyno");

    public final NumberPath<Long> noofinsurancycompany = createNumber("noofinsurancycompany", Long.class);

    public final NumberPath<Long> noofvehicles = createNumber("noofvehicles", Long.class);

    public final DateTimePath<java.util.Date> policyenddate = createDateTime("policyenddate", java.util.Date.class);

    public final DateTimePath<java.util.Date> policystartdate = createDateTime("policystartdate", java.util.Date.class);

    public final NumberPath<Long> procommission = createNumber("procommission", Long.class);

    public final NumberPath<Long> productId = createNumber("productId", Long.class);

    public final DateTimePath<java.util.Date> proexpirydate = createDateTime("proexpirydate", java.util.Date.class);

    public final StringPath proposalno = createString("proposalno");

    public final StringPath proposalstatus = createString("proposalstatus");

    public final DateTimePath<java.util.Date> prostartdate = createDateTime("prostartdate", java.util.Date.class);

    public final StringPath remarks = createString("remarks");

    public final NumberPath<Long> rsasharedpercentage = createNumber("rsasharedpercentage", Long.class);

    public final StringPath status = createString("status");

    public final StringPath type = createString("type");

    public final StringPath utilizedamount = createString("utilizedamount");

    public final NumberPath<Long> warpremium = createNumber("warpremium", Long.class);

    public final StringPath wrscyn = createString("wrscyn");

    public QOpenCoverMaster(String variable) {
        super(OpenCoverMaster.class, forVariable(variable));
    }

    public QOpenCoverMaster(Path<? extends OpenCoverMaster> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOpenCoverMaster(PathMetadata metadata) {
        super(OpenCoverMaster.class, metadata);
    }

}

