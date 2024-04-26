package lkm.starterproject.accounting.entity.register;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFixedAsset is a Querydsl query type for FixedAsset
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFixedAsset extends EntityPathBase<FixedAsset> {

    private static final long serialVersionUID = -1429278607L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFixedAsset fixedAsset = new QFixedAsset("fixedAsset");

    public final QAccountTitle accountTitle;

    public final DatePath<java.time.LocalDate> acquisitionDate = createDate("acquisitionDate", java.time.LocalDate.class);

    public final NumberPath<Long> acquisitionValue = createNumber("acquisitionValue", Long.class);

    public final NumberPath<Long> amount = createNumber("amount", Long.class);

    public final QCar car;

    public final NumberPath<Long> code = createNumber("code", Long.class);

    public final lkm.starterproject.accounting.entity.company.QDepartment department;

    public final EnumPath<lkm.starterproject.accounting.constants.DepreciationStatus> depreciationStatus = createEnum("depreciationStatus", lkm.starterproject.accounting.constants.DepreciationStatus.class);

    public final NumberPath<Integer> durableYears = createNumber("durableYears", Integer.class);

    public final DatePath<java.time.LocalDate> endOfUseDate = createDate("endOfUseDate", java.time.LocalDate.class);

    public final StringPath name = createString("name");

    public final StringPath note = createString("note");

    public final EnumPath<lkm.starterproject.accounting.constants.UseStatus> useStatus = createEnum("useStatus", lkm.starterproject.accounting.constants.UseStatus.class);

    public QFixedAsset(String variable) {
        this(FixedAsset.class, forVariable(variable), INITS);
    }

    public QFixedAsset(Path<? extends FixedAsset> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFixedAsset(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFixedAsset(PathMetadata metadata, PathInits inits) {
        this(FixedAsset.class, metadata, inits);
    }

    public QFixedAsset(Class<? extends FixedAsset> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.accountTitle = inits.isInitialized("accountTitle") ? new QAccountTitle(forProperty("accountTitle")) : null;
        this.car = inits.isInitialized("car") ? new QCar(forProperty("car")) : null;
        this.department = inits.isInitialized("department") ? new lkm.starterproject.accounting.entity.company.QDepartment(forProperty("department")) : null;
    }

}

