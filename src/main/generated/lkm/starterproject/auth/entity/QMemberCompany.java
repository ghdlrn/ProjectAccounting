package lkm.starterproject.auth.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMemberCompany is a Querydsl query type for MemberCompany
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberCompany extends EntityPathBase<MemberCompany> {

    private static final long serialVersionUID = -1752324102L;

    public static final QMemberCompany memberCompany = new QMemberCompany("memberCompany");

    public final NumberPath<Long> code = createNumber("code", Long.class);

    public QMemberCompany(String variable) {
        super(MemberCompany.class, forVariable(variable));
    }

    public QMemberCompany(Path<? extends MemberCompany> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberCompany(PathMetadata metadata) {
        super(MemberCompany.class, metadata);
    }

}

