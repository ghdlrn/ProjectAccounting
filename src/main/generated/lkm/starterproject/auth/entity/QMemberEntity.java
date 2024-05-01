package lkm.starterproject.auth.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberEntity is a Querydsl query type for MemberEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberEntity extends EntityPathBase<Member> {

    private static final long serialVersionUID = -1246915386L;

    public static final QMemberEntity memberEntity = new QMemberEntity("memberEntity");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath password = createString("password");

    public final ListPath<lkm.starterproject.bulletinboard.entity.BulletinBoard, lkm.starterproject.bulletinboard.entity.QBulletinBoard> posts = this.<lkm.starterproject.bulletinboard.entity.BulletinBoard, lkm.starterproject.bulletinboard.entity.QBulletinBoard>createList("posts", lkm.starterproject.bulletinboard.entity.BulletinBoard.class, lkm.starterproject.bulletinboard.entity.QBulletinBoard.class, PathInits.DIRECT2);

    public final EnumPath<lkm.starterproject.auth.constants.Role> role = createEnum("role", lkm.starterproject.auth.constants.Role.class);

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public final StringPath username = createString("username");

    public QMemberEntity(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMemberEntity(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberEntity(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

