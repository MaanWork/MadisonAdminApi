package com.madison.motor.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QPaymentDetail is a Querydsl query type for PaymentDetail
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPaymentDetail extends EntityPathBase<PaymentDetail> {

    private static final long serialVersionUID = -696420441L;

    public static final QPaymentDetail paymentDetail = new QPaymentDetail("paymentDetail");

    public final StringPath applicationNo = createString("applicationNo");

    public final StringPath bankName = createString("bankName");

    public final StringPath billToAddressCity = createString("billToAddressCity");

    public final StringPath billToAddressCountry = createString("billToAddressCountry");

    public final StringPath billToAddressLine1 = createString("billToAddressLine1");

    public final StringPath billToAddressPostalCode = createString("billToAddressPostalCode");

    public final StringPath billToEmail = createString("billToEmail");

    public final StringPath billToForename = createString("billToForename");

    public final StringPath billToSurname = createString("billToSurname");

    public final StringPath branchCode = createString("branchCode");

    public final StringPath cardNumberMasked = createString("cardNumberMasked");

    public final NumberPath<java.math.BigDecimal> chequeAmount = createNumber("chequeAmount", java.math.BigDecimal.class);

    public final DateTimePath<java.util.Date> chequeDate = createDateTime("chequeDate", java.util.Date.class);

    public final StringPath chequeNo = createString("chequeNo");

    public final StringPath currencyType = createString("currencyType");

    public final StringPath customerEmail = createString("customerEmail");

    public final StringPath customerName = createString("customerName");

    public final StringPath deviceType = createString("deviceType");

    public final NumberPath<java.math.BigDecimal> installmentAmount = createNumber("installmentAmount", java.math.BigDecimal.class);

    public final StringPath installmentFrequency = createString("installmentFrequency");

    public final NumberPath<java.math.BigDecimal> installmentNo = createNumber("installmentNo", java.math.BigDecimal.class);

    public final StringPath installmentRemarks = createString("installmentRemarks");

    public final StringPath installmentStartDate = createString("installmentStartDate");

    public final StringPath installmentYn = createString("installmentYn");

    public final StringPath manualLoginId = createString("manualLoginId");

    public final StringPath manualStatus = createString("manualStatus");

    public final DateTimePath<java.util.Date> manualUpdateTime = createDateTime("manualUpdateTime", java.util.Date.class);

    public final StringPath merchantReference = createString("merchantReference");

    public final StringPath micrCode = createString("micrCode");

    public final StringPath mobileNo = createString("mobileNo");

    public final StringPath mtnMobileNo = createString("mtnMobileNo");

    public final NumberPath<java.math.BigDecimal> noOfInstallment = createNumber("noOfInstallment", java.math.BigDecimal.class);

    public final StringPath paymentType = createString("paymentType");

    public final StringPath policyNo = createString("policyNo");

    public final NumberPath<java.math.BigDecimal> premium = createNumber("premium", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> productId = createNumber("productId", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> quoteNo = createNumber("quoteNo", java.math.BigDecimal.class);

    public final StringPath referenceNo = createString("referenceNo");

    public final StringPath renewalYn = createString("renewalYn");

    public final DateTimePath<java.util.Date> requestTime = createDateTime("requestTime", java.util.Date.class);

    public final StringPath resAuthAmount = createString("resAuthAmount");

    public final StringPath resAuthAvsCodeRaw = createString("resAuthAvsCodeRaw");

    public final StringPath resAuthCode = createString("resAuthCode");

    public final StringPath resAuthResponse = createString("resAuthResponse");

    public final StringPath resAuthTime = createString("resAuthTime");

    public final StringPath resAuthTransRefNo = createString("resAuthTransRefNo");

    public final StringPath resBillTransRefNo = createString("resBillTransRefNo");

    public final StringPath resDecision = createString("resDecision");

    public final StringPath resMessage = createString("resMessage");

    public final StringPath resPayerAuthCavv = createString("resPayerAuthCavv");

    public final StringPath resPayerAuthentication = createString("resPayerAuthentication");

    public final StringPath resPayerAuthParesStatus = createString("resPayerAuthParesStatus");

    public final StringPath resPayerAuthReasonCode = createString("resPayerAuthReasonCode");

    public final StringPath resPaymentToken = createString("resPaymentToken");

    public final StringPath responseCode = createString("responseCode");

    public final StringPath responseMessage = createString("responseMessage");

    public final StringPath responseStatus = createString("responseStatus");

    public final DateTimePath<java.util.Date> responseTime = createDateTime("responseTime", java.util.Date.class);

    public final StringPath responseTranNo = createString("responseTranNo");

    public final StringPath resReasonCode = createString("resReasonCode");

    public final StringPath resReqAmount = createString("resReqAmount");

    public final StringPath resReqBillToAddressCity = createString("resReqBillToAddressCity");

    public final StringPath resReqBillToEmail = createString("resReqBillToEmail");

    public final StringPath resReqBillToForename = createString("resReqBillToForename");

    public final StringPath resReqBillToSurname = createString("resReqBillToSurname");

    public final StringPath resReqCardExpiryDate = createString("resReqCardExpiryDate");

    public final StringPath resReqCardType = createString("resReqCardType");

    public final StringPath resReqCurrency = createString("resReqCurrency");

    public final StringPath resReqNoOfInstallments = createString("resReqNoOfInstallments");

    public final StringPath resReqPaymentMethod = createString("resReqPaymentMethod");

    public final StringPath resReqPostalCode = createString("resReqPostalCode");

    public final StringPath resReqRecurringAmount = createString("resReqRecurringAmount");

    public final StringPath resReqRecurringFrequency = createString("resReqRecurringFrequency");

    public final StringPath resReqTransactionType = createString("resReqTransactionType");

    public final StringPath resRequestToken = createString("resRequestToken");

    public final StringPath resTransactionId = createString("resTransactionId");

    public final StringPath riskId = createString("riskId");

    public QPaymentDetail(String variable) {
        super(PaymentDetail.class, forVariable(variable));
    }

    public QPaymentDetail(Path<? extends PaymentDetail> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPaymentDetail(PathMetadata metadata) {
        super(PaymentDetail.class, metadata);
    }

}

