package lkm.starterproject.accounting.entity.statement;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStatement is a Querydsl query type for Statement
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStatement extends EntityPathBase<Statement> {

    private static final long serialVersionUID = 1348797402L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStatement statement = new QStatement("statement");

    public final lkm.starterproject.accounting.entity.register.QAccountTitle accountTitle;

    public final NumberPath<Long> code = createNumber("code", Long.class);

    public final lkm.starterproject.accounting.entity.register.QCompendium compendium;

    public final NumberPath<Long> credit = createNumber("credit", Long.class);

    public final lkm.starterproject.accounting.entity.register.QCustomer customer;

    public final NumberPath<Long> debit = createNumber("debit", Long.class);

    public final DatePath<java.time.LocalDate> RegistrationDate = createDate("RegistrationDate", java.time.LocalDate.class);

    public final EnumPath<lkm.starterproject.accounting.constants.StatementTypeStatus> statementTypeStatus = createEnum("statementTypeStatus", lkm.starterproject.accounting.constants.StatementTypeStatus.class);

    public QStatement(String variable) {
        this(Statement.class, forVariable(variable), INITS);
    }

    public QStatement(Path<? extends Statement> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStatement(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStatement(PathMetadata metadata, PathInits inits) {
        this(Statement.class, metadata, inits);
    }

    public QStatement(Class<? extends Statement> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.accountTitle = inits.isInitialized("accountTitle") ? new lkm.starterproject.accounting.entity.register.QAccountTitle(forProperty("accountTitle")) : null;
        this.compendium = inits.isInitialized("compendium") ? new lkm.starterproject.accounting.entity.register.QCompendium(forProperty("compendium")) : null;
        this.customer = inits.isInitialized("customer") ? new lkm.starterproject.accounting.entity.register.QCustomer(forProperty("customer")) : null;
    }

}

