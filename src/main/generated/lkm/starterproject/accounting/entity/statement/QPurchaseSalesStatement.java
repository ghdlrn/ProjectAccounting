package lkm.starterproject.accounting.entity.statement;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPurchaseSalesStatement is a Querydsl query type for PurchaseSalesStatement
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPurchaseSalesStatement extends EntityPathBase<PurchaseSalesStatement> {

    private static final long serialVersionUID = 147887257L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPurchaseSalesStatement purchaseSalesStatement = new QPurchaseSalesStatement("purchaseSalesStatement");

    public final NumberPath<Long> amount = createNumber("amount", Long.class);

    public final StringPath approvalNumber = createString("approvalNumber");

    public final NumberPath<Long> code = createNumber("code", Long.class);

    public final lkm.starterproject.accounting.entity.register.QCustomer customer;

    public final NumberPath<Long> ExtendedPrice = createNumber("ExtendedPrice", Long.class);

    public final EnumPath<lkm.starterproject.accounting.constants.MissingScheduleStatus> missingScheduleStatus = createEnum("missingScheduleStatus", lkm.starterproject.accounting.constants.MissingScheduleStatus.class);

    public final StringPath name = createString("name");

    public final DatePath<java.time.LocalDate> RegistrationDate = createDate("RegistrationDate", java.time.LocalDate.class);

    public final StringPath resentment = createString("resentment");

    public final ListPath<Statement, QStatement> statementEntities = this.<Statement, QStatement>createList("statementEntities", Statement.class, QStatement.class, PathInits.DIRECT2);

    public final NumberPath<Long> supplyVaule = createNumber("supplyVaule", Long.class);

    public final NumberPath<Long> surtax = createNumber("surtax", Long.class);

    public final EnumPath<lkm.starterproject.accounting.constants.SurtaxStatus> surtaxStatus = createEnum("surtaxStatus", lkm.starterproject.accounting.constants.SurtaxStatus.class);

    public final EnumPath<lkm.starterproject.accounting.constants.SurtaxTypeStatus> surtaxTypeStatus = createEnum("surtaxTypeStatus", lkm.starterproject.accounting.constants.SurtaxTypeStatus.class);

    public final NumberPath<Long> unitPrice = createNumber("unitPrice", Long.class);

    public QPurchaseSalesStatement(String variable) {
        this(PurchaseSalesStatement.class, forVariable(variable), INITS);
    }

    public QPurchaseSalesStatement(Path<? extends PurchaseSalesStatement> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPurchaseSalesStatement(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPurchaseSalesStatement(PathMetadata metadata, PathInits inits) {
        this(PurchaseSalesStatement.class, metadata, inits);
    }

    public QPurchaseSalesStatement(Class<? extends PurchaseSalesStatement> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.customer = inits.isInitialized("customer") ? new lkm.starterproject.accounting.entity.register.QCustomer(forProperty("customer")) : null;
    }

}

