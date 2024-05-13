package lkm.starterproject.accounting.entity.basic;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLocalTax is a Querydsl query type for LocalTax
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLocalTax extends EntityPathBase<LocalTax> {

    private static final long serialVersionUID = -1295384682L;

    public static final QLocalTax localTax = new QLocalTax("localTax");

    public final NumberPath<Long> code = createNumber("code", Long.class);

    public final StringPath name = createString("name");

    public final StringPath status = createString("status");

    public QLocalTax(String variable) {
        super(LocalTax.class, forVariable(variable));
    }

    public QLocalTax(Path<? extends LocalTax> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLocalTax(PathMetadata metadata) {
        super(LocalTax.class, metadata);
    }

}

