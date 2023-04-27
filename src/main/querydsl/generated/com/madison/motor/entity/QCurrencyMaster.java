package com.madison.motor.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCurrencyMaster is a Querydsl query type for CurrencyMaster
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCurrencyMaster extends EntityPathBase<CurrencyMaster> {

    private static final long serialVersionUID = -1182446045L;

    public static final QCurrencyMaster currencyMaster = new QCurrencyMaster("currencyMaster");

    public final NumberPath<Long> amendid = createNumber("amendid", Long.class);

    public final NumberPath<Long> countryid = createNumber("countryid", Long.class);

    public final NumberPath<Long> currencyid = createNumber("currencyid", Long.class);

    public final StringPath currencyname = createString("currencyname");

    public final NumberPath<Long> displayorder = createNumber("displayorder", Long.class);

    public final DateTimePath<java.util.Date> effectivedate = createDateTime("effectivedate", java.util.Date.class);

    public final DateTimePath<java.util.Date> entrydate = createDateTime("entrydate", java.util.Date.class);

    public final NumberPath<Long> exmaxlmt = createNumber("exmaxlmt", Long.class);

    public final NumberPath<Long> exminlmt = createNumber("exminlmt", Long.class);

    public final DateTimePath<java.util.Date> expirydate = createDateTime("expirydate", java.util.Date.class);

    public final DateTimePath<java.util.Date> inceptiondate = createDateTime("inceptiondate", java.util.Date.class);

    public final StringPath remarks = createString("remarks");

    public final NumberPath<Long> rfactor = createNumber("rfactor", Long.class);

    public final StringPath rsacode = createString("rsacode");

    public final StringPath sbcurrency = createString("sbcurrency");

    public final StringPath shortname = createString("shortname");

    public final NumberPath<Long> sno = createNumber("sno", Long.class);

    public final StringPath status = createString("status");

    public QCurrencyMaster(String variable) {
        super(CurrencyMaster.class, forVariable(variable));
    }

    public QCurrencyMaster(Path<? extends CurrencyMaster> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCurrencyMaster(PathMetadata metadata) {
        super(CurrencyMaster.class, metadata);
    }

}

