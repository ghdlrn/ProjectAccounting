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

    public final lkm.starterproject.accounting.entity.basic.QAddress address;

    public final StringPath cardNumber = createString("cardNumber");

    public final StringPath chargeName = createString("chargeName");

    public final NumberPath<Double> commission = createNumber("commission", Double.class);

    public final lkm.starterproject.accounting.entity.company.QCompany company;

    public final DatePath<java.time.LocalDate> contractEndDate = createDate("contractEndDate", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> contractStartDate = createDate("contractStartDate", java.time.LocalDate.class);

    public final StringPath division = createString("division");

    public final DatePath<java.time.LocalDate> expirationEndDate = createDate("expirationEndDate", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> expirationStartDate = createDate("expirationStartDate", java.time.LocalDate.class);

    public final StringPath fax = createString("fax");

    public final QFinance finance;

    public final StringPath homePage = createString("homePage");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath note = createString("note");

    public final StringPath phone = createString("phone");

    public final StringPath registrationNumber = createString("registrationNumber");

    public final EnumPath<lkm.starterproject.accounting.constants.UseStatus> useStatus = createEnum("useStatus", lkm.starterproject.accounting.constants.UseStatus.class);

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
        this.address = inits.isInitialized("address") ? new lkm.starterproject.accounting.entity.basic.QAddress(forProperty("address")) : null;
        this.company = inits.isInitialized("company") ? new lkm.starterproject.accounting.entity.company.QCompany(forProperty("company"), inits.get("company")) : null;
        this.finance = inits.isInitialized("finance") ? new QFinance(forProperty("finance"), inits.get("finance")) : null;
    }

}

