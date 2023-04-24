package com.madison.motor.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMotorDataDetail is a Querydsl query type for MotorDataDetail
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMotorDataDetail extends EntityPathBase<MotorDataDetail> {

    private static final long serialVersionUID = -1345223520L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMotorDataDetail motorDataDetail = new QMotorDataDetail("motorDataDetail");

    public final StringPath agencyRepair = createString("agencyRepair");

    public final NumberPath<java.math.BigDecimal> agencyRepairPremium = createNumber("agencyRepairPremium", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> amendId = createNumber("amendId", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> applicationNo = createNumber("applicationNo", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> areaCoverage = createNumber("areaCoverage", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> bankId = createNumber("bankId", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> bankName = createNumber("bankName", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> baseRate = createNumber("baseRate", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> body = createNumber("body", java.math.BigDecimal.class);

    public final StringPath bodyName = createString("bodyName");

    public final StringPath certificateNo = createString("certificateNo");

    public final StringPath chassisNo = createString("chassisNo");

    public final NumberPath<java.math.BigDecimal> claimAmount = createNumber("claimAmount", java.math.BigDecimal.class);

    public final StringPath claimyn = createString("claimyn");

    public final NumberPath<java.math.BigDecimal> cubicCapacity = createNumber("cubicCapacity", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> currencyId = createNumber("currencyId", java.math.BigDecimal.class);

    public final StringPath currencyType = createString("currencyType");

    public final NumberPath<java.math.BigDecimal> customerId = createNumber("customerId", java.math.BigDecimal.class);

    public final StringPath customerType = createString("customerType");

    public final NumberPath<java.math.BigDecimal> deductibleAmount = createNumber("deductibleAmount", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> deductibleId = createNumber("deductibleId", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> discountPremium = createNumber("discountPremium", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> driverAge = createNumber("driverAge", java.math.BigDecimal.class);

    public final DateTimePath<java.util.Date> driverDob = createDateTime("driverDob", java.util.Date.class);

    public final StringPath driverGender = createString("driverGender");

    public final StringPath driverId = createString("driverId");

    public final StringPath driverLicense = createString("driverLicense");

    public final NumberPath<java.math.BigDecimal> driverNationality = createNumber("driverNationality", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> driverOtherLicence = createNumber("driverOtherLicence", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> driverUaeLicence = createNumber("driverUaeLicence", java.math.BigDecimal.class);

    public final DateTimePath<java.util.Date> driverUaeLicExpDt = createDateTime("driverUaeLicExpDt", java.util.Date.class);

    public final DateTimePath<java.util.Date> effectiveDate = createDateTime("effectiveDate", java.util.Date.class);

    public final NumberPath<java.math.BigDecimal> electricalPrem = createNumber("electricalPrem", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> electricalRate = createNumber("electricalRate", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> electricalSi = createNumber("electricalSi", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> electricalSiEndt = createNumber("electricalSiEndt", java.math.BigDecimal.class);

    public final DateTimePath<java.util.Date> endorsementDate = createDateTime("endorsementDate", java.util.Date.class);

    public final StringPath endorsementStatus = createString("endorsementStatus");

    public final StringPath engineNumber = createString("engineNumber");

    public final DateTimePath<java.util.Date> entryDate = createDateTime("entryDate", java.util.Date.class);

    public final NumberPath<java.math.BigDecimal> excess = createNumber("excess", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> exchangeRate = createNumber("exchangeRate", java.math.BigDecimal.class);

    public final DateTimePath<java.util.Date> expiryDate = createDateTime("expiryDate", java.util.Date.class);

    public final DateTimePath<java.util.Date> inceptionDate = createDateTime("inceptionDate", java.util.Date.class);

    public final StringPath insCompany = createString("insCompany");

    public final StringPath insuredAddressArabic = createString("insuredAddressArabic");

    public final StringPath insuredNameArabic = createString("insuredNameArabic");

    public final StringPath isClaimdtl = createString("isClaimdtl");

    public final StringPath leased = createString("leased");

    public final NumberPath<java.math.BigDecimal> makeId = createNumber("makeId", java.math.BigDecimal.class);

    public final StringPath makeName = createString("makeName");

    public final NumberPath<java.math.BigDecimal> manufactureLoc = createNumber("manufactureLoc", java.math.BigDecimal.class);

    public final StringPath manufactureMonth = createString("manufactureMonth");

    public final StringPath manufactureYear = createString("manufactureYear");

    public final NumberPath<java.math.BigDecimal> modelId = createNumber("modelId", java.math.BigDecimal.class);

    public final StringPath modelName = createString("modelName");

    public final QMotorPolicytypeMaster motorPolicytypeMaster;

    public final NumberPath<java.math.BigDecimal> noClaimBonus = createNumber("noClaimBonus", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> nonelectricalPrem = createNumber("nonelectricalPrem", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> nonelectricalRate = createNumber("nonelectricalRate", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> nonelectricalSi = createNumber("nonelectricalSi", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> nonelectricalSiEndt = createNumber("nonelectricalSiEndt", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> noOfClaims = createNumber("noOfClaims", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> noOfCylinder = createNumber("noOfCylinder", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> onlineDiscount = createNumber("onlineDiscount", java.math.BigDecimal.class);

    public final StringPath optionalCover = createString("optionalCover");

    public final StringPath originalPolicyNo = createString("originalPolicyNo");

    public final StringPath ownnerdriverYn = createString("ownnerdriverYn");

    public final NumberPath<java.math.BigDecimal> paDriverPremium = createNumber("paDriverPremium", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> paPassengerPremium = createNumber("paPassengerPremium", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> pdfBrokerStatus = createNumber("pdfBrokerStatus", java.math.BigDecimal.class);

    public final StringPath personalAccidentDriver = createString("personalAccidentDriver");

    public final StringPath personalAccidentPassenger = createString("personalAccidentPassenger");

    public final NumberPath<java.math.BigDecimal> plateColor = createNumber("plateColor", java.math.BigDecimal.class);

    public final StringPath policyNo = createString("policyNo");

    public final NumberPath<java.math.BigDecimal> policytype = createNumber("policytype", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> premium = createNumber("premium", java.math.BigDecimal.class);

    public final DateTimePath<java.util.Date> prevPolicyexpirydate = createDateTime("prevPolicyexpirydate", java.util.Date.class);

    public final StringPath prevPolicyno = createString("prevPolicyno");

    public final NumberPath<java.math.BigDecimal> productId = createNumber("productId", java.math.BigDecimal.class);

    public final QProductMaster productMaster;

    public final NumberPath<java.math.BigDecimal> quoteNo = createNumber("quoteNo", java.math.BigDecimal.class);

    public final StringPath referralRemarks = createString("referralRemarks");

    public final StringPath registerLocation = createString("registerLocation");

    public final StringPath registrationNo = createString("registrationNo");

    public final StringPath registrationYear = createString("registrationYear");

    public final StringPath remarks = createString("remarks");

    public final NumberPath<java.math.BigDecimal> schemeId = createNumber("schemeId", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> seatingCapacity = createNumber("seatingCapacity", java.math.BigDecimal.class);

    public final StringPath status = createString("status");

    public final NumberPath<java.math.BigDecimal> suminsuredValueForeign = createNumber("suminsuredValueForeign", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> suminsuredValueLocal = createNumber("suminsuredValueLocal", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> suminsuredValueLocalEndt = createNumber("suminsuredValueLocalEndt", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> thirdPartyLiability = createNumber("thirdPartyLiability", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> trafficId = createNumber("trafficId", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> vat = createNumber("vat", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> vehicleColor = createNumber("vehicleColor", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> vehicleId = createNumber("vehicleId", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> vehicleType = createNumber("vehicleType", java.math.BigDecimal.class);

    public final StringPath vehicleUsageName = createString("vehicleUsageName");

    public QMotorDataDetail(String variable) {
        this(MotorDataDetail.class, forVariable(variable), INITS);
    }

    public QMotorDataDetail(Path<? extends MotorDataDetail> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMotorDataDetail(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMotorDataDetail(PathMetadata metadata, PathInits inits) {
        this(MotorDataDetail.class, metadata, inits);
    }

    public QMotorDataDetail(Class<? extends MotorDataDetail> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.motorPolicytypeMaster = inits.isInitialized("motorPolicytypeMaster") ? new QMotorPolicytypeMaster(forProperty("motorPolicytypeMaster")) : null;
        this.productMaster = inits.isInitialized("productMaster") ? new QProductMaster(forProperty("productMaster")) : null;
    }

}

