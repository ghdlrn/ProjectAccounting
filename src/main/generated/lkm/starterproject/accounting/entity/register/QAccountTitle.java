package lkm.starterproject.accounting.entity.register;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAccountTitle is a Querydsl query type for AccountTitle
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAccountTitle extends EntityPathBase<AccountTitle> {

    private static final long serialVersionUID = -2112585856L;

    public static final QAccountTitle accountTitle1 = new QAccountTitle("accountTitle1");

    public final StringPath accountTitle = createString("accountTitle");

    public final NumberPath<Integer> code = createNumber("code", Integer.class);

    public final StringPath debitAndCredit = createString("debitAndCredit");

    public final EnumPath<lkm.starterproject.accounting.constants.UseStatus> useStatus = createEnum("useStatus", lkm.starterproject.accounting.constants.UseStatus.class);

    public QAccountTitle(String variable) {
        super(AccountTitle.class, forVariable(variable));
    }

    public QAccountTitle(Path<? extends AccountTitle> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAccountTitle(PathMetadata metadata) {
        super(AccountTitle.class, metadata);
    }

}

