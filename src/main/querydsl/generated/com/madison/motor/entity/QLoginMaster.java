package com.madison.motor.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QLoginMaster is a Querydsl query type for LoginMaster
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLoginMaster extends EntityPathBase<LoginMaster> {

    private static final long serialVersionUID = 1141710043L;

    public static final QLoginMaster loginMaster = new QLoginMaster("loginMaster");

    public final StringPath accesstype = createString("accesstype");

    public final StringPath acExecutiveCreation = createString("acExecutiveCreation");

    public final StringPath agencyCode = createString("agencyCode");

    public final StringPath allowedIpAddr = createString("allowedIpAddr");

    public final StringPath appId = createString("appId");

    public final StringPath attachedBranch = createString("attachedBranch");

    public final StringPath attachedUw = createString("attachedUw");

    public final StringPath branchCode = createString("branchCode");

    public final StringPath brokerCodes = createString("brokerCodes");

    public final StringPath companyId = createString("companyId");

    public final NumberPath<java.math.BigDecimal> coreLoginId = createNumber("coreLoginId", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> countryId = createNumber("countryId", java.math.BigDecimal.class);

    public final StringPath createdBy = createString("createdBy");

    public final StringPath employeCode = createString("employeCode");

    public final StringPath encryptPassword = createString("encryptPassword");

    public final DatePath<java.util.Date> entryDate = createDate("entryDate", java.util.Date.class);

    public final DatePath<java.util.Date> expiryDate = createDate("expiryDate", java.util.Date.class);

    public final StringPath fdCode = createString("fdCode");

    public final StringPath firstName = createString("firstName");

    public final DatePath<java.util.Date> inceptionDate = createDate("inceptionDate", java.util.Date.class);

    public final StringPath isB2c = createString("isB2c");

    public final StringPath lastName = createString("lastName");

    public final StringPath loginId = createString("loginId");

    public final StringPath losstypeidlist = createString("losstypeidlist");

    public final StringPath lpass1 = createString("lpass1");

    public final StringPath lpass2 = createString("lpass2");

    public final StringPath lpass3 = createString("lpass3");

    public final StringPath lpass4 = createString("lpass4");

    public final StringPath lpass5 = createString("lpass5");

    public final StringPath mailLogin = createString("mailLogin");

    public final StringPath menuId = createString("menuId");

    public final StringPath middleName = createString("middleName");

    public final StringPath mobileNo = createString("mobileNo");

    public final StringPath mobileNumber = createString("mobileNumber");

    public final StringPath oaCode = createString("oaCode");

    public final StringPath onlineYn = createString("onlineYn");

    public final DatePath<java.util.Date> passdate = createDate("passdate", java.util.Date.class);

    public final StringPath password = createString("password");

    public final StringPath productId = createString("productId");

    public final StringPath pwdCount = createString("pwdCount");

    public final StringPath referal = createString("referal");

    public final StringPath regionCode = createString("regionCode");

    public final StringPath remarks = createString("remarks");

    public final StringPath restictIpStatus = createString("restictIpStatus");

    public final StringPath rights = createString("rights");

    public final StringPath shortUrl = createString("shortUrl");

    public final StringPath stateCode = createString("stateCode");

    public final StringPath status = createString("status");

    public final StringPath subBranch = createString("subBranch");

    public final StringPath subUserType = createString("subUserType");

    public final NumberPath<java.math.BigDecimal> suminsuredEnd = createNumber("suminsuredEnd", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> suminsuredStart = createNumber("suminsuredStart", java.math.BigDecimal.class);

    public final StringPath tokenPassword = createString("tokenPassword");

    public final NumberPath<java.math.BigDecimal> userid = createNumber("userid", java.math.BigDecimal.class);

    public final StringPath userIdCreation = createString("userIdCreation");

    public final StringPath userMail = createString("userMail");

    public final StringPath username = createString("username");

    public final StringPath usertype = createString("usertype");

    public final StringPath wajahYn = createString("wajahYn");

    public QLoginMaster(String variable) {
        super(LoginMaster.class, forVariable(variable));
    }

    public QLoginMaster(Path<? extends LoginMaster> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLoginMaster(PathMetadata metadata) {
        super(LoginMaster.class, metadata);
    }

}

