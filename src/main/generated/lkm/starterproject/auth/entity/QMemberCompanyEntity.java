package lkm.starterproject.auth.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMemberCompanyEntity is a Querydsl query type for MemberCompanyEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberCompanyEntity extends EntityPathBase<MemberCompanyEntity> {

    private static final long serialVersionUID = 1330451581L;

    public static final QMemberCompanyEntity memberCompanyEntity = new QMemberCompanyEntity("memberCompanyEntity");

    public final NumberPath<Long> code = createNumber("code", Long.class);

    public QMemberCompanyEntity(String variable) {
        super(MemberCompanyEntity.class, forVariable(variable));
    }

    public QMemberCompanyEntity(Path<? extends MemberCompanyEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberCompanyEntity(PathMetadata metadata) {
        super(MemberCompanyEntity.class, metadata);
    }

}

