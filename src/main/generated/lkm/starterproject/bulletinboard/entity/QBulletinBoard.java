package lkm.starterproject.bulletinboard.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBulletinBoard is a Querydsl query type for BulletinBoard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBulletinBoard extends EntityPathBase<BulletinBoard> {

    private static final long serialVersionUID = 388780327L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBulletinBoard bulletinBoard = new QBulletinBoard("bulletinBoard");

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final lkm.starterproject.auth.entity.QMemberEntity memberEntity;

    public final StringPath title = createString("title");

    public QBulletinBoard(String variable) {
        this(BulletinBoard.class, forVariable(variable), INITS);
    }

    public QBulletinBoard(Path<? extends BulletinBoard> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBulletinBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBulletinBoard(PathMetadata metadata, PathInits inits) {
        this(BulletinBoard.class, metadata, inits);
    }

    public QBulletinBoard(Class<? extends BulletinBoard> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.memberEntity = inits.isInitialized("memberEntity") ? new lkm.starterproject.auth.entity.QMemberEntity(forProperty("memberEntity")) : null;
    }

}

