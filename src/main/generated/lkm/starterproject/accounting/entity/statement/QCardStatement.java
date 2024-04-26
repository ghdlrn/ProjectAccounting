package lkm.starterproject.accounting.entity.statement;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCardStatement is a Querydsl query type for CardStatement
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCardStatement extends EntityPathBase<CardStatement> {

    private static final long serialVersionUID = -1866404566L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCardStatement cardStatement = new QCardStatement("cardStatement");

    public final lkm.starterproject.accounting.entity.register.QAccountTitle accountTitle;

    public final lkm.starterproject.accounting.entity.register.QCard card;

    public final NumberPath<Long> code = createNumber("code", Long.class);

    public final lkm.starterproject.accounting.entity.register.QCompendium compendium;

    public final StringPath customerName = createString("customerName");

    public final NumberPath<Long> money = createNumber("money", Long.class);

    public final DatePath<java.time.LocalDate> RegistrationDate = createDate("RegistrationDate", java.time.LocalDate.class);

    public QCardStatement(String variable) {
        this(CardStatement.class, forVariable(variable), INITS);
    }

    public QCardStatement(Path<? extends CardStatement> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCardStatement(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCardStatement(PathMetadata metadata, PathInits inits) {
        this(CardStatement.class, metadata, inits);
    }

    public QCardStatement(Class<? extends CardStatement> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.accountTitle = inits.isInitialized("accountTitle") ? new lkm.starterproject.accounting.entity.register.QAccountTitle(forProperty("accountTitle")) : null;
        this.card = inits.isInitialized("card") ? new lkm.starterproject.accounting.entity.register.QCard(forProperty("card"), inits.get("card")) : null;
        this.compendium = inits.isInitialized("compendium") ? new lkm.starterproject.accounting.entity.register.QCompendium(forProperty("compendium")) : null;
    }

}

