package com.madison.motor.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBranchMaster is a Querydsl query type for BranchMaster
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBranchMaster extends EntityPathBase<BranchMaster> {

    private static final long serialVersionUID = -714201356L;

    public static final QBranchMaster branchMaster = new QBranchMaster("branchMaster");

    public final StringPath address1 = createString("address1");

    public final StringPath address2 = createString("address2");

    public final StringPath address3 = createString("address3");

    public final NumberPath<java.math.BigDecimal> amendId = createNumber("amendId", java.math.BigDecimal.class);

    public final StringPath belongingBranch = createString("belongingBranch");

    public final StringPath branchCode = createString("branchCode");

    public final StringPath branchName = createString("branchName");

    public final StringPath branchPrefix = createString("branchPrefix");

    public final StringPath city = createString("city");

    public final StringPath coreAppCode = createString("coreAppCode");

    public final StringPath country = createString("country");

    public final StringPath currencyAbbreviation = createString("currencyAbbreviation");

    public final StringPath currencyAcronym = createString("currencyAcronym");

    public final NumberPath<java.math.BigDecimal> currencyDecimalDigit = createNumber("currencyDecimalDigit", java.math.BigDecimal.class);

    public final StringPath currencyDecimalName = createString("currencyDecimalName");

    public final StringPath currencyName = createString("currencyName");

    public final NumberPath<java.math.BigDecimal> decimalPlaces = createNumber("decimalPlaces", java.math.BigDecimal.class);

    public final StringPath deptCode = createString("deptCode");

    public final NumberPath<java.math.BigDecimal> destinationCountryId = createNumber("destinationCountryId", java.math.BigDecimal.class);

    public final DatePath<java.util.Date> effectiveDate = createDate("effectiveDate", java.util.Date.class);

    public final StringPath email = createString("email");

    public final NumberPath<java.math.BigDecimal> fax = createNumber("fax", java.math.BigDecimal.class);

    public final StringPath footerImg = createString("footerImg");

    public final StringPath headerImg = createString("headerImg");

    public final StringPath langYn = createString("langYn");

    public final NumberPath<java.math.BigDecimal> originationCountryId = createNumber("originationCountryId", java.math.BigDecimal.class);

    public final StringPath pckeyCloseTrn = createString("pckeyCloseTrn");

    public final StringPath phone = createString("phone");

    public final NumberPath<java.math.BigDecimal> policyFeePercent = createNumber("policyFeePercent", java.math.BigDecimal.class);

    public final StringPath remarks = createString("remarks");

    public final StringPath signImg = createString("signImg");

    public final NumberPath<java.math.BigDecimal> sno = createNumber("sno", java.math.BigDecimal.class);

    public final StringPath stamp = createString("stamp");

    public final StringPath status = createString("status");

    public final NumberPath<java.math.BigDecimal> tax = createNumber("tax", java.math.BigDecimal.class);

    public QBranchMaster(String variable) {
        super(BranchMaster.class, forVariable(variable));
    }

    public QBranchMaster(Path<? extends BranchMaster> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBranchMaster(PathMetadata metadata) {
        super(BranchMaster.class, metadata);
    }

}

