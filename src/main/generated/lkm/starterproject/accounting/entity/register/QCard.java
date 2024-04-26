package lkm.starterproject.accounting.entity.register;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCard is a Querydsl query type for Card
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCard extends EntityPathBase<Card> {

    private static final long serialVersionUID = 54797157L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCard card = new QCard("card");

    public final DatePath<java.time.LocalDate> accountDay = createDate("accountDay", java.time.LocalDate.class);

    public final NumberPath<Integer> accountTitle = createNumber("accountTitle", Integer.class);

    public final StringPath address = createString("address");

    public final StringPath businessRegistrationNumber = createString("businessRegistrationNumber");

    public final StringPath cardNumber = createString("cardNumber");

    public final NumberPath<Long> code = createNumber("code", Long.class);

    public final NumberPath<Double> commission = createNumber("commission", Double.class);

    public final NumberPath<Integer> commissionAccountTitle = createNumber("commissionAccountTitle", Integer.class);

    public final lkm.starterproject.accounting.entity.company.QCompany company;

    public final DatePath<java.time.LocalDate> constractCloseDate = createDate("constractCloseDate", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> constractOpenDate = createDate("constractOpenDate", java.time.LocalDate.class);

    public final NumberPath<Integer> customerGroupingCode = createNumber("customerGroupingCode", Integer.class);

    public final StringPath fax = createString("fax");

    public final ListPath<Finance, QFinance> finances = this.<Finance, QFinance>createList("finances", Finance.class, QFinance.class, PathInits.DIRECT2);

    public final StringPath homePage = createString("homePage");

    public final StringPath name = createString("name");

    public final StringPath note = createString("note");

    public final StringPath phone = createString("phone");

    public final EnumPath<lkm.starterproject.accounting.constants.TradeStatus> tradeStatus = createEnum("tradeStatus", lkm.starterproject.accounting.constants.TradeStatus.class);

    public final EnumPath<lkm.starterproject.accounting.constants.UseStatus> useStatus = createEnum("useStatus", lkm.starterproject.accounting.constants.UseStatus.class);

    public final NumberPath<Integer> zipCode = createNumber("zipCode", Integer.class);

    public QCard(String variable) {
        this(Card.class, forVariable(variable), INITS);
    }

    public QCard(Path<? extends Card> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCard(PathMetadata metadata, PathInits inits) {
        this(Card.class, metadata, inits);
    }

    public QCard(Class<? extends Card> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.company = inits.isInitialized("company") ? new lkm.starterproject.accounting.entity.company.QCompany(forProperty("company")) : null;
    }

}

