package lkm.starterproject.accounting.entity.basic;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAddress is a Querydsl query type for Address
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAddress extends EntityPathBase<Address> {

    private static final long serialVersionUID = -973699618L;

    public static final QAddress address = new QAddress("address");

    public final NumberPath<Long> code = createNumber("code", Long.class);

    public final StringPath extraAddress = createString("extraAddress");

    public final StringPath guideText = createString("guideText");

    public final StringPath jibunAddress = createString("jibunAddress");

    public final NumberPath<Integer> postcode = createNumber("postcode", Integer.class);

    public final StringPath roadAddress = createString("roadAddress");

    public QAddress(String variable) {
        super(Address.class, forVariable(variable));
    }

    public QAddress(Path<? extends Address> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAddress(PathMetadata metadata) {
        super(Address.class, metadata);
    }

}

