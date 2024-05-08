package lkm.starterproject.auth.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberCompany is a Querydsl query type for MemberCompany
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberCompany extends EntityPathBase<MemberCompany> {

    private static final long serialVersionUID = -1752324102L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberCompany memberCompany = new QMemberCompany("memberCompany");

    public final NumberPath<Long> code = createNumber("code", Long.class);

    public final lkm.starterproject.accounting.entity.company.QCompany company;

    public final QMember member;

    public QMemberCompany(String variable) {
        this(MemberCompany.class, forVariable(variable), INITS);
    }

    public QMemberCompany(Path<? extends MemberCompany> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberCompany(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberCompany(PathMetadata metadata, PathInits inits) {
        this(MemberCompany.class, metadata, inits);
    }

    public QMemberCompany(Class<? extends MemberCompany> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.company = inits.isInitialized("company") ? new lkm.starterproject.accounting.entity.company.QCompany(forProperty("company"), inits.get("company")) : null;
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
    }

}

