package lkm.starterproject.accounting.entity.register;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFixedAsset is a Querydsl query type for FixedAsset
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFixedAsset extends EntityPathBase<FixedAsset> {

    private static final long serialVersionUID = -1429278607L;

    public static final QFixedAsset fixedAsset = new QFixedAsset("fixedAsset");

    public final DatePath<java.time.LocalDate> acquisitionDate = createDate("acquisitionDate", java.time.LocalDate.class);

    public final NumberPath<Long> acquisitionValue = createNumber("acquisitionValue", Long.class);

    public final NumberPath<Long> amount = createNumber("amount", Long.class);

    public final NumberPath<Long> code = createNumber("code", Long.class);

    public final StringPath depreciationStatus = createString("depreciationStatus");

    public final NumberPath<Long> durableYears = createNumber("durableYears", Long.class);

    public final DatePath<java.time.LocalDate> endOfUseDate = createDate("endOfUseDate", java.time.LocalDate.class);

    public final StringPath name = createString("name");

    public final StringPath note = createString("note");

    public final EnumPath<lkm.starterproject.accounting.constants.UseStatus> useStatus = createEnum("useStatus", lkm.starterproject.accounting.constants.UseStatus.class);

    public QFixedAsset(String variable) {
        super(FixedAsset.class, forVariable(variable));
    }

    public QFixedAsset(Path<? extends FixedAsset> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFixedAsset(PathMetadata metadata) {
        super(FixedAsset.class, metadata);
    }

}

