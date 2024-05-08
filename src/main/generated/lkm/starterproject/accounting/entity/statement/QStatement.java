package lkm.starterproject.accounting.entity.statement;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QStatement is a Querydsl query type for Statement
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStatement extends EntityPathBase<Statement> {

    private static final long serialVersionUID = 1348797402L;

    public static final QStatement statement = new QStatement("statement");

    public final NumberPath<Long> code = createNumber("code", Long.class);

    public final NumberPath<Long> credit = createNumber("credit", Long.class);

    public final NumberPath<Long> debit = createNumber("debit", Long.class);

    public final DatePath<java.time.LocalDate> RegistrationDate = createDate("RegistrationDate", java.time.LocalDate.class);

    public final EnumPath<lkm.starterproject.accounting.constants.StatementTypeStatus> statementTypeStatus = createEnum("statementTypeStatus", lkm.starterproject.accounting.constants.StatementTypeStatus.class);

    public QStatement(String variable) {
        super(Statement.class, forVariable(variable));
    }

    public QStatement(Path<? extends Statement> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStatement(PathMetadata metadata) {
        super(Statement.class, metadata);
    }

}

