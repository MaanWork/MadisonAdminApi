package com.madison.motor.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCountryMaster is a Querydsl query type for CountryMaster
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCountryMaster extends EntityPathBase<CountryMaster> {

    private static final long serialVersionUID = 762135368L;

    public static final QCountryMaster countryMaster = new QCountryMaster("countryMaster");

    public final NumberPath<Long> amendid = createNumber("amendid", Long.class);

    public final NumberPath<Long> countryid = createNumber("countryid", Long.class);

    public final StringPath countryname = createString("countryname");

    public final StringPath countryshortname = createString("countryshortname");

    public final DateTimePath<java.util.Date> effectivedate = createDateTime("effectivedate", java.util.Date.class);

    public final NumberPath<Long> georate = createNumber("georate", Long.class);

    public final StringPath intportcode = createString("intportcode");

    public final StringPath nationalityname = createString("nationalityname");

    public final StringPath remarks = createString("remarks");

    public final StringPath rsacode = createString("rsacode");

    public final NumberPath<Long> sno = createNumber("sno", Long.class);

    public final StringPath status = createString("status");

    public QCountryMaster(String variable) {
        super(CountryMaster.class, forVariable(variable));
    }

    public QCountryMaster(Path<? extends CountryMaster> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCountryMaster(PathMetadata metadata) {
        super(CountryMaster.class, metadata);
    }

}

