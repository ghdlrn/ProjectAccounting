package lkm.starterproject.accounting.entity.statement;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCashStatement is a Querydsl query type for CashStatement
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCashStatement extends EntityPathBase<CashStatement> {

    private static final long serialVersionUID = -154442649L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCashStatement cashStatement = new QCashStatement("cashStatement");

    public final lkm.starterproject.accounting.entity.register.QAccountTitle accountTitle;

    public final NumberPath<Long> code = createNumber("code", Long.class);

    public final NumberPath<Long> commission = createNumber("commission", Long.class);

    public final lkm.starterproject.accounting.entity.register.QCompendium compendium;

    public final StringPath customerName = createString("customerName");

    public final lkm.starterproject.accounting.entity.register.QFinance finance;

    public final NumberPath<Long> money = createNumber("money", Long.class);

    public final DatePath<java.time.LocalDate> RegistrationDate = createDate("RegistrationDate", java.time.LocalDate.class);

    public QCashStatement(String variable) {
        this(CashStatement.class, forVariable(variable), INITS);
    }

    public QCashStatement(Path<? extends CashStatement> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCashStatement(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCashStatement(PathMetadata metadata, PathInits inits) {
        this(CashStatement.class, metadata, inits);
    }

    public QCashStatement(Class<? extends CashStatement> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.accountTitle = inits.isInitialized("accountTitle") ? new lkm.starterproject.accounting.entity.register.QAccountTitle(forProperty("accountTitle")) : null;
        this.compendium = inits.isInitialized("compendium") ? new lkm.starterproject.accounting.entity.register.QCompendium(forProperty("compendium")) : null;
        this.finance = inits.isInitialized("finance") ? new lkm.starterproject.accounting.entity.register.QFinance(forProperty("finance"), inits.get("finance")) : null;
    }

}

