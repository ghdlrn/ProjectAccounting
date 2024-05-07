package lkm.starterproject.auth.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMemberImg is a Querydsl query type for MemberImg
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberImg extends EntityPathBase<MemberImg> {

    private static final long serialVersionUID = -683980352L;

    public static final QMemberImg memberImg = new QMemberImg("memberImg");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final NumberPath<Long> Id = createNumber("Id", Long.class);

    public final StringPath imgName = createString("imgName");

    public final StringPath imgUrl = createString("imgUrl");

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    public final StringPath oriImgName = createString("oriImgName");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regTime = _super.regTime;

    public final StringPath repimgYn = createString("repimgYn");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateTime = _super.updateTime;

    public QMemberImg(String variable) {
        super(MemberImg.class, forVariable(variable));
    }

    public QMemberImg(Path<? extends MemberImg> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberImg(PathMetadata metadata) {
        super(MemberImg.class, metadata);
    }

}

