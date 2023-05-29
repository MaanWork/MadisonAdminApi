package com.madison.motor.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMenuMaster is a Querydsl query type for MenuMaster
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMenuMaster extends EntityPathBase<MenuMaster> {

    private static final long serialVersionUID = -478451471L;

    public static final QMenuMaster menuMaster = new QMenuMaster("menuMaster");

    public final StringPath branchCode = createString("branchCode");

    public final StringPath isClick = createString("isClick");

    public final NumberPath<Long> menuid = createNumber("menuid", Long.class);

    public final StringPath menuname = createString("menuname");

    public final StringPath menuurl = createString("menuurl");

    public final StringPath parentmenu = createString("parentmenu");

    public final NumberPath<Long> productid = createNumber("productid", Long.class);

    public final StringPath rsacode = createString("rsacode");

    public final StringPath status = createString("status");

    public final StringPath usertype = createString("usertype");

    public QMenuMaster(String variable) {
        super(MenuMaster.class, forVariable(variable));
    }

    public QMenuMaster(Path<? extends MenuMaster> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMenuMaster(PathMetadata metadata) {
        super(MenuMaster.class, metadata);
    }

}

