package com.madison.motor.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QPersonalInfo is a Querydsl query type for PersonalInfo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPersonalInfo extends EntityPathBase<PersonalInfo> {

    private static final long serialVersionUID = 240847710L;

    public static final QPersonalInfo personalInfo = new QPersonalInfo("personalInfo");

    public final NumberPath<java.math.BigDecimal> acExecutiveId = createNumber("acExecutiveId", java.math.BigDecimal.class);

    public final StringPath address1 = createString("address1");

    public final StringPath address2 = createString("address2");

    public final StringPath agencyCode = createString("agencyCode");

    public final StringPath alternateMobile = createString("alternateMobile");

    public final NumberPath<java.math.BigDecimal> amendId = createNumber("amendId", java.math.BigDecimal.class);

    public final StringPath applicationId = createString("applicationId");

    public final StringPath city = createString("city");

    public final StringPath clientCustomerId = createString("clientCustomerId");

    public final StringPath companyName = createString("companyName");

    public final StringPath companyRegNo = createString("companyRegNo");

    public final StringPath country = createString("country");

    public final StringPath custArNo = createString("custArNo");

    public final StringPath custName = createString("custName");

    public final StringPath custNameArabic = createString("custNameArabic");

    public final NumberPath<java.math.BigDecimal> customerId = createNumber("customerId", java.math.BigDecimal.class);

    public final StringPath customerLoginId = createString("customerLoginId");

    public final StringPath customerSource = createString("customerSource");

    public final StringPath customerType = createString("customerType");

    public final DateTimePath<java.util.Date> dob = createDateTime("dob", java.util.Date.class);

    public final StringPath dobAr = createString("dobAr");

    public final DateTimePath<java.util.Date> effectiveDate = createDateTime("effectiveDate", java.util.Date.class);

    public final StringPath email = createString("email");

    public final StringPath emirate = createString("emirate");

    public final DateTimePath<java.util.Date> entryDate = createDateTime("entryDate", java.util.Date.class);

    public final DateTimePath<java.util.Date> expiryDate = createDateTime("expiryDate", java.util.Date.class);

    public final StringPath fax = createString("fax");

    public final StringPath fdCode = createString("fdCode");

    public final StringPath firstName = createString("firstName");

    public final StringPath freightForwardUser = createString("freightForwardUser");

    public final StringPath gender = createString("gender");

    public final DateTimePath<java.util.Date> inceptionDate = createDateTime("inceptionDate", java.util.Date.class);

    public final StringPath lastName = createString("lastName");

    public final StringPath loginId = createString("loginId");

    public final StringPath missippiCustomerCode = createString("missippiCustomerCode");

    public final StringPath mobile = createString("mobile");

    public final StringPath nationality = createString("nationality");

    public final StringPath nrc = createString("nrc");

    public final StringPath oaCode = createString("oaCode");

    public final StringPath occupation = createString("occupation");

    public final StringPath passportNumber = createString("passportNumber");

    public final StringPath pobox = createString("pobox");

    public final StringPath remarks = createString("remarks");

    public final StringPath state = createString("state");

    public final StringPath status = createString("status");

    public final StringPath telephone = createString("telephone");

    public final StringPath title = createString("title");

    public QPersonalInfo(String variable) {
        super(PersonalInfo.class, forVariable(variable));
    }

    public QPersonalInfo(Path<? extends PersonalInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPersonalInfo(PathMetadata metadata) {
        super(PersonalInfo.class, metadata);
    }

}

