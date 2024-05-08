package lkm.starterproject.accounting.entity.statement;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCashStatement is a Querydsl query type for CashStatement
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCashStatement extends EntityPathBase<CashStatement> {

    private static final long serialVersionUID = -154442649L;

    public static final QCashStatement cashStatement = new QCashStatement("cashStatement");

    public final NumberPath<Long> code = createNumber("code", Long.class);

    public final NumberPath<Long> commission = createNumber("commission", Long.class);

    public final StringPath customerName = createString("customerName");

    public final NumberPath<Long> money = createNumber("money", Long.class);

    public final DatePath<java.time.LocalDate> RegistrationDate = createDate("RegistrationDate", java.time.LocalDate.class);

    public QCashStatement(String variable) {
        super(CashStatement.class, forVariable(variable));
    }

    public QCashStatement(Path<? extends CashStatement> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCashStatement(PathMetadata metadata) {
        super(CashStatement.class, metadata);
    }

}

