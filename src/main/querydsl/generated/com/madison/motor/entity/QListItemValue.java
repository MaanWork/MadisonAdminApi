package com.madison.motor.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QListItemValue is a Querydsl query type for ListItemValue
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QListItemValue extends EntityPathBase<ListItemValue> {

    private static final long serialVersionUID = -301156144L;

    public static final QListItemValue listItemValue = new QListItemValue("listItemValue");

    public final StringPath coreAppCode = createString("coreAppCode");

    public final StringPath itemCode = createString("itemCode");

    public final StringPath itemDesc = createString("itemDesc");

    public final NumberPath<java.math.BigDecimal> itemId = createNumber("itemId", java.math.BigDecimal.class);

    public final StringPath itemType = createString("itemType");

    public final StringPath itemValue = createString("itemValue");

    public final StringPath packageDesc = createString("packageDesc");

    public final StringPath param1 = createString("param1");

    public final StringPath param2 = createString("param2");

    public final StringPath status = createString("status");

    public QListItemValue(String variable) {
        super(ListItemValue.class, forVariable(variable));
    }

    public QListItemValue(Path<? extends ListItemValue> path) {
        super(path.getType(), path.getMetadata());
    }

    public QListItemValue(PathMetadata metadata) {
        super(ListItemValue.class, metadata);
    }

}

