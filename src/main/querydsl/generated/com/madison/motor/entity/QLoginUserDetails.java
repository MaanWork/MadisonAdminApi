package com.madison.motor.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QLoginUserDetails is a Querydsl query type for LoginUserDetails
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLoginUserDetails extends EntityPathBase<LoginUserDetails> {

    private static final long serialVersionUID = -2130934498L;

    public static final QLoginUserDetails loginUserDetails = new QLoginUserDetails("loginUserDetails");

    public final StringPath agencyCode = createString("agencyCode");

    public final NumberPath<java.math.BigDecimal> amendId = createNumber("amendId", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> backDateAllowed = createNumber("backDateAllowed", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> commission = createNumber("commission", java.math.BigDecimal.class);

    public final StringPath companyId = createString("companyId");

    public final NumberPath<java.math.BigDecimal> corporateDiscountLimit = createNumber("corporateDiscountLimit", java.math.BigDecimal.class);

    public final StringPath customerCertificate = createString("customerCertificate");

    public final StringPath customerCustomerdebit = createString("customerCustomerdebit");

    public final StringPath customerDebit = createString("customerDebit");

    public final NumberPath<java.math.BigDecimal> customerId = createNumber("customerId", java.math.BigDecimal.class);

    public final StringPath customerPolicy = createString("customerPolicy");

    public final StringPath customerQuote = createString("customerQuote");

    public final StringPath customerSchedule = createString("customerSchedule");

    public final NumberPath<java.math.BigDecimal> discountOfPremium = createNumber("discountOfPremium", java.math.BigDecimal.class);

    public final DateTimePath<java.util.Date> effectiveDate = createDateTime("effectiveDate", java.util.Date.class);

    public final StringPath endorsementStatus = createString("endorsementStatus");

    public final DatePath<java.util.Date> entryDate = createDate("entryDate", java.util.Date.class);

    public final DatePath<java.util.Date> expiryDate = createDate("expiryDate", java.util.Date.class);

    public final NumberPath<java.math.BigDecimal> exportMinPremiumAmount = createNumber("exportMinPremiumAmount", java.math.BigDecimal.class);

    public final StringPath fdCode = createString("fdCode");

    public final StringPath freightAdminOption = createString("freightAdminOption");

    public final StringPath freightAuotoOption = createString("freightAuotoOption");

    public final StringPath freightDebitOption = createString("freightDebitOption");

    public final StringPath freightRateOption = createString("freightRateOption");

    public final StringPath freightScheduleOption = createString("freightScheduleOption");

    public final NumberPath<java.math.BigDecimal> importMinPremiumAmount = createNumber("importMinPremiumAmount", java.math.BigDecimal.class);

    public final DateTimePath<java.util.Date> inceptionDate = createDateTime("inceptionDate", java.util.Date.class);

    public final NumberPath<java.math.BigDecimal> insuranceEndLimit = createNumber("insuranceEndLimit", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> insuranceStartLimit = createNumber("insuranceStartLimit", java.math.BigDecimal.class);

    public final StringPath isB2c = createString("isB2c");

    public final StringPath issuertype = createString("issuertype");

    public final NumberPath<java.math.BigDecimal> loadingOfPremium = createNumber("loadingOfPremium", java.math.BigDecimal.class);

    public final StringPath loginId = createString("loginId");

    public final NumberPath<java.math.BigDecimal> maxDiscount = createNumber("maxDiscount", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> maxLoading = createNumber("maxLoading", java.math.BigDecimal.class);

    public final StringPath membershipStatus = createString("membershipStatus");

    public final NumberPath<java.math.BigDecimal> minDiscount = createNumber("minDiscount", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> minLoading = createNumber("minLoading", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> minPremiumAmount = createNumber("minPremiumAmount", java.math.BigDecimal.class);

    public final StringPath oaCode = createString("oaCode");

    public final StringPath openCoverNo = createString("openCoverNo");

    public final StringPath paymentType = createString("paymentType");

    public final StringPath payReceiptStatus = createString("payReceiptStatus");

    public final NumberPath<java.math.BigDecimal> polCustomerId = createNumber("polCustomerId", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> proCommission = createNumber("proCommission", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> productId = createNumber("productId", java.math.BigDecimal.class);

    public final DateTimePath<java.util.Date> proExpiryDate = createDateTime("proExpiryDate", java.util.Date.class);

    public final DateTimePath<java.util.Date> proStartDate = createDateTime("proStartDate", java.util.Date.class);

    public final StringPath provisionForPremium = createString("provisionForPremium");

    public final StringPath provisionForRate = createString("provisionForRate");

    public final StringPath receiptStatus = createString("receiptStatus");

    public final NumberPath<java.math.BigDecimal> relativeUserId = createNumber("relativeUserId", java.math.BigDecimal.class);

    public final StringPath remarks = createString("remarks");

    public final StringPath renewalStatus = createString("renewalStatus");

    public final NumberPath<java.math.BigDecimal> schemeId = createNumber("schemeId", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> specialDiscount = createNumber("specialDiscount", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> specialDiscountLimit = createNumber("specialDiscountLimit", java.math.BigDecimal.class);

    public final StringPath status = createString("status");

    public final NumberPath<java.math.BigDecimal> userId = createNumber("userId", java.math.BigDecimal.class);

    public final StringPath userName = createString("userName");

    public final NumberPath<java.math.BigDecimal> volumeDiscountLimit = createNumber("volumeDiscountLimit", java.math.BigDecimal.class);

    public QLoginUserDetails(String variable) {
        super(LoginUserDetails.class, forVariable(variable));
    }

    public QLoginUserDetails(Path<? extends LoginUserDetails> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLoginUserDetails(PathMetadata metadata) {
        super(LoginUserDetails.class, metadata);
    }

}

