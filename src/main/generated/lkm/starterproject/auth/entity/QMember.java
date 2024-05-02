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

    public static final QMember member = new QMember("member1");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath password = createString("password");

    public final ListPath<lkm.starterproject.bulletinboard.entity.BulletinBoard, lkm.starterproject.bulletinboard.entity.QBulletinBoard> posts = this.<lkm.starterproject.bulletinboard.entity.BulletinBoard, lkm.starterproject.bulletinboard.entity.QBulletinBoard>createList("posts", lkm.starterproject.bulletinboard.entity.BulletinBoard.class, lkm.starterproject.bulletinboard.entity.QBulletinBoard.class, PathInits.DIRECT2);

    public final EnumPath<lkm.starterproject.auth.constants.Role> role = createEnum("role", lkm.starterproject.auth.constants.Role.class);

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public final StringPath username = createString("username");

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}
