package com.madison.motor.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QRoadSideAssistantDetails is a Querydsl query type for RoadSideAssistantDetails
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRoadSideAssistantDetails extends EntityPathBase<RoadSideAssistantDetails> {

    private static final long serialVersionUID = 504523915L;

    public static final QRoadSideAssistantDetails roadSideAssistantDetails = new QRoadSideAssistantDetails("roadSideAssistantDetails");

    public final StringPath assistantType = createString("assistantType");

    public final StringPath customerfeedback = createString("customerfeedback");

    public final StringPath customerName = createString("customerName");

    public final StringPath descripition = createString("descripition");

    public final StringPath deviceId = createString("deviceId");

    public final DateTimePath<java.util.Date> entrydate = createDateTime("entrydate", java.util.Date.class);

    public final StringPath latitude = createString("latitude");

    public final StringPath location = createString("location");

    public final StringPath loginId = createString("loginId");

    public final StringPath logitude = createString("logitude");

    public final StringPath mailId = createString("mailId");

    public final NumberPath<Long> mobileno = createNumber("mobileno", Long.class);

    public final StringPath policyNo = createString("policyNo");

    public final NumberPath<Long> refNo = createNumber("refNo", Long.class);

    public final StringPath remarks = createString("remarks");

    public final StringPath status = createString("status");

    public final StringPath updateby = createString("updateby");

    public final DateTimePath<java.util.Date> updatedate = createDateTime("updatedate", java.util.Date.class);

    public QRoadSideAssistantDetails(String variable) {
        super(RoadSideAssistantDetails.class, forVariable(variable));
    }

    public QRoadSideAssistantDetails(Path<? extends RoadSideAssistantDetails> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRoadSideAssistantDetails(PathMetadata metadata) {
        super(RoadSideAssistantDetails.class, metadata);
    }

}

