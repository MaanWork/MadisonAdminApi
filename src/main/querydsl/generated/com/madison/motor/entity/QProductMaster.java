package com.madison.motor.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QProductMaster is a Querydsl query type for ProductMaster
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProductMaster extends EntityPathBase<ProductMaster> {

    private static final long serialVersionUID = -830303775L;

    public static final QProductMaster productMaster = new QProductMaster("productMaster");

    public final NumberPath<java.math.BigDecimal> amendId = createNumber("amendId", java.math.BigDecimal.class);

    public final StringPath appLoginUrl = createString("appLoginUrl");

    public final StringPath branchCode = createString("branchCode");

    public final NumberPath<java.math.BigDecimal> companyId = createNumber("companyId", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> coreappcode = createNumber("coreappcode", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> displayOrder = createNumber("displayOrder", java.math.BigDecimal.class);

    public final DateTimePath<java.util.Date> effectiveDate = createDateTime("effectiveDate", java.util.Date.class);

    public final DateTimePath<java.util.Date> entryDate = createDateTime("entryDate", java.util.Date.class);

    public final DateTimePath<java.util.Date> expiryDate = createDateTime("expiryDate", java.util.Date.class);

    public final DateTimePath<java.util.Date> inceptionDate = createDateTime("inceptionDate", java.util.Date.class);

    public final StringPath paymentRedirUrl = createString("paymentRedirUrl");

    public final StringPath productCategory = createString("productCategory");

    public final NumberPath<java.math.BigDecimal> productId = createNumber("productId", java.math.BigDecimal.class);

    public final StringPath productName = createString("productName");

    public final StringPath remarks = createString("remarks");

    public final StringPath rsacode = createString("rsacode");

    public final StringPath status = createString("status");

    public QProductMaster(String variable) {
        super(ProductMaster.class, forVariable(variable));
    }

    public QProductMaster(Path<? extends ProductMaster> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProductMaster(PathMetadata metadata) {
        super(ProductMaster.class, metadata);
    }

}

