package lkm.starterproject.auth.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberCompanyEntity is a Querydsl query type for MemberCompanyEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberCompanyEntity extends EntityPathBase<MemberCompanyEntity> {

    private static final long serialVersionUID = 1330451581L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberCompanyEntity memberCompanyEntity = new QMemberCompanyEntity("memberCompanyEntity");

    public final NumberPath<Long> code = createNumber("code", Long.class);

    public final lkm.starterproject.accounting.entity.company.QCompany company;

    public final QMemberEntity memberEntity;

    public QMemberCompanyEntity(String variable) {
        this(MemberCompanyEntity.class, forVariable(variable), INITS);
    }

    public QMemberCompanyEntity(Path<? extends MemberCompanyEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberCompanyEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberCompanyEntity(PathMetadata metadata, PathInits inits) {
        this(MemberCompanyEntity.class, metadata, inits);
    }

    public QMemberCompanyEntity(Class<? extends MemberCompanyEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.company = inits.isInitialized("company") ? new lkm.starterproject.accounting.entity.company.QCompany(forProperty("company")) : null;
        this.memberEntity = inits.isInitialized("memberEntity") ? new QMemberEntity(forProperty("memberEntity")) : null;
    }

}

