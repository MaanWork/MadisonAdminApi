package com.madison.motor.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QExchangeMaster is a Querydsl query type for ExchangeMaster
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QExchangeMaster extends EntityPathBase<ExchangeMaster> {

    private static final long serialVersionUID = -1328245067L;

    public static final QExchangeMaster exchangeMaster = new QExchangeMaster("exchangeMaster");

    public final NumberPath<Long> amendid = createNumber("amendid", Long.class);

    public final StringPath countryId = createString("countryId");

    public final NumberPath<Long> currencyId = createNumber("currencyId", Long.class);

    public final StringPath currencytype = createString("currencytype");

    public final NumberPath<Long> displayorder = createNumber("displayorder", Long.class);

    public final DateTimePath<java.util.Date> effectivedate = createDateTime("effectivedate", java.util.Date.class);

    public final DateTimePath<java.util.Date> entrydate = createDateTime("entrydate", java.util.Date.class);

    public final NumberPath<Long> exchId = createNumber("exchId", Long.class);

    public final NumberPath<Long> exchRate = createNumber("exchRate", Long.class);

    public final DateTimePath<java.util.Date> expirydate = createDateTime("expirydate", java.util.Date.class);

    public final DateTimePath<java.util.Date> incepDate = createDateTime("incepDate", java.util.Date.class);

    public final StringPath remarks = createString("remarks");

    public final StringPath rsacode = createString("rsacode");

    public final NumberPath<Long> sno = createNumber("sno", Long.class);

    public final StringPath status = createString("status");

    public final StringPath subcurrency = createString("subcurrency");

    public QExchangeMaster(String variable) {
        super(ExchangeMaster.class, forVariable(variable));
    }

    public QExchangeMaster(Path<? extends ExchangeMaster> path) {
        super(path.getType(), path.getMetadata());
    }

    public QExchangeMaster(PathMetadata metadata) {
        super(ExchangeMaster.class, metadata);
    }

}

