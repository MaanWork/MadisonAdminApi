package com.madison.motor.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QHomePositionMaster is a Querydsl query type for HomePositionMaster
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QHomePositionMaster extends EntityPathBase<HomePositionMaster> {

    private static final long serialVersionUID = 500722682L;

    public static final QHomePositionMaster homePositionMaster = new QHomePositionMaster("homePositionMaster");

    public final NumberPath<java.math.BigDecimal> acExecutiveId = createNumber("acExecutiveId", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> actualOppremium = createNumber("actualOppremium", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> actualPremium = createNumber("actualPremium", java.math.BigDecimal.class);

    public final StringPath adminReferralStatus = createString("adminReferralStatus");

    public final StringPath adminRemarks = createString("adminRemarks");

    public final StringPath adminSummaryStatus = createString("adminSummaryStatus");

    public final StringPath ageAboveSixtyFive = createString("ageAboveSixtyFive");

    public final StringPath airmilesNo = createString("airmilesNo");

    public final NumberPath<java.math.BigDecimal> amendId = createNumber("amendId", java.math.BigDecimal.class);

    public final StringPath applicationId = createString("applicationId");

    public final NumberPath<java.math.BigDecimal> applicationNo = createNumber("applicationNo", java.math.BigDecimal.class);

    public final StringPath approvedBy = createString("approvedBy");

    public final NumberPath<java.math.BigDecimal> bedRoom = createNumber("bedRoom", java.math.BigDecimal.class);

    public final StringPath branchCode = createString("branchCode");

    public final NumberPath<java.math.BigDecimal> brokerAdditionalCommission = createNumber("brokerAdditionalCommission", java.math.BigDecimal.class);

    public final StringPath brokerBranch = createString("brokerBranch");

    public final StringPath brokerCode = createString("brokerCode");

    public final StringPath btoc = createString("btoc");

    public final StringPath cancelledBy = createString("cancelledBy");

    public final DateTimePath<java.util.Date> cancelledDate = createDateTime("cancelledDate", java.util.Date.class);

    public final StringPath cancelledReason = createString("cancelledReason");

    public final StringPath chqInvNo = createString("chqInvNo");

    public final StringPath classId = createString("classId");

    public final NumberPath<java.math.BigDecimal> commission = createNumber("commission", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> commissionPercentage = createNumber("commissionPercentage", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> companyId = createNumber("companyId", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> contentTypeId = createNumber("contentTypeId", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> corporateDiscountAmount = createNumber("corporateDiscountAmount", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> coverNoteNumber = createNumber("coverNoteNumber", java.math.BigDecimal.class);

    public final DateTimePath<java.util.Date> creditDate = createDateTime("creditDate", java.util.Date.class);

    public final StringPath creditNo = createString("creditNo");

    public final NumberPath<java.math.BigDecimal> cshIdTypCode = createNumber("cshIdTypCode", java.math.BigDecimal.class);

    public final StringPath currency = createString("currency");

    public final NumberPath<java.math.BigDecimal> customerId = createNumber("customerId", java.math.BigDecimal.class);

    public final DateTimePath<java.util.Date> debitNoteDate = createDateTime("debitNoteDate", java.util.Date.class);

    public final StringPath debitNoteNo = createString("debitNoteNo");

    public final StringPath declarationStatus = createString("declarationStatus");

    public final NumberPath<java.math.BigDecimal> discountPremium = createNumber("discountPremium", java.math.BigDecimal.class);

    public final DateTimePath<java.util.Date> effectiveDate = createDateTime("effectiveDate", java.util.Date.class);

    public final NumberPath<java.math.BigDecimal> endtCommission = createNumber("endtCommission", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> endtPremium = createNumber("endtPremium", java.math.BigDecimal.class);

    public final StringPath endtStatus = createString("endtStatus");

    public final StringPath endtTypeId = createString("endtTypeId");

    public final DateTimePath<java.util.Date> entryDate = createDateTime("entryDate", java.util.Date.class);

    public final NumberPath<java.math.BigDecimal> excessPremium = createNumber("excessPremium", java.math.BigDecimal.class);

    public final StringPath excessSign = createString("excessSign");

    public final StringPath existingMedicalCondition = createString("existingMedicalCondition");

    public final DateTimePath<java.util.Date> expiryDate = createDateTime("expiryDate", java.util.Date.class);

    public final StringPath fleetIntStatus = createString("fleetIntStatus");

    public final StringPath fleetNo = createString("fleetNo");

    public final NumberPath<java.math.BigDecimal> hitCount = createNumber("hitCount", java.math.BigDecimal.class);

    public final DateTimePath<java.util.Date> inceptionDate = createDateTime("inceptionDate", java.util.Date.class);

    public final StringPath ingserialNumber = createString("ingserialNumber");

    public final StringPath installmentYn = createString("installmentYn");

    public final StringPath insuranceDetails = createString("insuranceDetails");

    public final DateTimePath<java.util.Date> lapsedDate = createDateTime("lapsedDate", java.util.Date.class);

    public final StringPath lapsedRemarks = createString("lapsedRemarks");

    public final StringPath lapsedUpdatedBy = createString("lapsedUpdatedBy");

    public final StringPath loginId = createString("loginId");

    public final StringPath medicalTravelClaims = createString("medicalTravelClaims");

    public final StringPath minPremiumYn = createString("minPremiumYn");

    public final StringPath mobilePaymentStatus = createString("mobilePaymentStatus");

    public final StringPath noClaimDiscount = createString("noClaimDiscount");

    public final NumberPath<java.math.BigDecimal> noClaimDiscountValue = createNumber("noClaimDiscountValue", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> onlineDiscount = createNumber("onlineDiscount", java.math.BigDecimal.class);

    public final StringPath originalPolicyNo = createString("originalPolicyNo");

    public final NumberPath<java.math.BigDecimal> overallPremium = createNumber("overallPremium", java.math.BigDecimal.class);

    public final StringPath paymentId = createString("paymentId");

    public final StringPath paymentMode = createString("paymentMode");

    public final StringPath paymentStatus = createString("paymentStatus");

    public final NumberPath<java.math.BigDecimal> pdfBrokerStatus = createNumber("pdfBrokerStatus", java.math.BigDecimal.class);

    public final DateTimePath<java.util.Date> policyDate = createDateTime("policyDate", java.util.Date.class);

    public final NumberPath<java.math.BigDecimal> policyFee = createNumber("policyFee", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> policyFeePercent = createNumber("policyFeePercent", java.math.BigDecimal.class);

    public final StringPath policyNo = createString("policyNo");

    public final StringPath policyTerm = createString("policyTerm");

    public final NumberPath<java.math.BigDecimal> premium = createNumber("premium", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> productId = createNumber("productId", java.math.BigDecimal.class);

    public final StringPath proposalNo = createString("proposalNo");

    public final NumberPath<java.math.BigDecimal> quaterId = createNumber("quaterId", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> quoteNo = createNumber("quoteNo", java.math.BigDecimal.class);

    public final DateTimePath<java.util.Date> receiptDate = createDateTime("receiptDate", java.util.Date.class);

    public final StringPath receiptNo = createString("receiptNo");

    public final StringPath referralDescription = createString("referralDescription");

    public final StringPath reissuedPolicyNo = createString("reissuedPolicyNo");

    public final NumberPath<java.math.BigDecimal> reissuedQuoteNo = createNumber("reissuedQuoteNo", java.math.BigDecimal.class);

    public final StringPath remarks = createString("remarks");

    public final StringPath renewalDateYn = createString("renewalDateYn");

    public final StringPath renewalOldPolicy = createString("renewalOldPolicy");

    public final StringPath renewalStatus = createString("renewalStatus");

    public final NumberPath<java.math.BigDecimal> schemeId = createNumber("schemeId", java.math.BigDecimal.class);

    public final StringPath smsYn = createString("smsYn");

    public final NumberPath<java.math.BigDecimal> specialDiscountAmount = createNumber("specialDiscountAmount", java.math.BigDecimal.class);

    public final StringPath status = createString("status");

    public final StringPath summaryClauses = createString("summaryClauses");

    public final StringPath summaryRemarks = createString("summaryRemarks");

    public final NumberPath<java.math.BigDecimal> tranId = createNumber("tranId", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> vehicleId = createNumber("vehicleId", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> volumeDiscountAmount = createNumber("volumeDiscountAmount", java.math.BigDecimal.class);

    public QHomePositionMaster(String variable) {
        super(HomePositionMaster.class, forVariable(variable));
    }

    public QHomePositionMaster(Path<? extends HomePositionMaster> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHomePositionMaster(PathMetadata metadata) {
        super(HomePositionMaster.class, metadata);
    }

}

