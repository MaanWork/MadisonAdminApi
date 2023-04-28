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

    public final NumberPath<Long> amendId = createNumber("amendId", Long.class);

    public final NumberPath<Long> countryId = createNumber("countryId", Long.class);

    public final StringPath countryName = createString("countryName");

    public final StringPath countryShortName = createString("countryShortName");

    public final DateTimePath<java.util.Date> effectiveDate = createDateTime("effectiveDate", java.util.Date.class);

    public final NumberPath<Long> geoRate = createNumber("geoRate", Long.class);

    public final StringPath intPortCode = createString("intPortCode");

    public final StringPath nationalityName = createString("nationalityName");

    public final StringPath remarks = createString("remarks");

    public final StringPath rsaCode = createString("rsaCode");

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

