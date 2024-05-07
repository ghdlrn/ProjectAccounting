package lkm.starterproject.accounting.entity.basic;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLocalTax is a Querydsl query type for LocalTax
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLocalTax extends EntityPathBase<LocalTax> {

    private static final long serialVersionUID = -1295384682L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLocalTax localTax = new QLocalTax("localTax");

    public final NumberPath<Long> code = createNumber("code", Long.class);

    public final lkm.starterproject.accounting.entity.company.QCompany company;

    public final StringPath name = createString("name");

    public final StringPath status = createString("status");

    public QLocalTax(String variable) {
        this(LocalTax.class, forVariable(variable), INITS);
    }

    public QLocalTax(Path<? extends LocalTax> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLocalTax(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLocalTax(PathMetadata metadata, PathInits inits) {
        this(LocalTax.class, metadata, inits);
    }

    public QLocalTax(Class<? extends LocalTax> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.company = inits.isInitialized("company") ? new lkm.starterproject.accounting.entity.company.QCompany(forProperty("company"), inits.get("company")) : null;
    }

}

