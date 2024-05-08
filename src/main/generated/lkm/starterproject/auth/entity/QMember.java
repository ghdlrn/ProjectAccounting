package lkm.starterproject.auth.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -280866045L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMember member = new QMember("member1");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<MemberCompany, QMemberCompany> memberCompanies = this.<MemberCompany, QMemberCompany>createList("memberCompanies", MemberCompany.class, QMemberCompany.class, PathInits.DIRECT2);

    public final QMemberImg memberImg;

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    public final StringPath password = createString("password");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regTime = _super.regTime;

    public final EnumPath<lkm.starterproject.auth.constants.Role> role = createEnum("role", lkm.starterproject.auth.constants.Role.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateTime = _super.updateTime;

    public final StringPath username = createString("username");

    public QMember(String variable) {
        this(Member.class, forVariable(variable), INITS);
    }

    public QMember(Path<? extends Member> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMember(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMember(PathMetadata metadata, PathInits inits) {
        this(Member.class, metadata, inits);
    }

    public QMember(Class<? extends Member> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.memberImg = inits.isInitialized("memberImg") ? new QMemberImg(forProperty("memberImg")) : null;
    }

}

