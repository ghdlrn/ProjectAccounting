package lkm.starterproject.accounting.entity.register;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCard is a Querydsl query type for Card
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCard extends EntityPathBase<Card> {

    private static final long serialVersionUID = 54797157L;

    public static final QCard card = new QCard("card");

    public final DatePath<java.time.LocalDate> accountDay = createDate("accountDay", java.time.LocalDate.class);

    public final NumberPath<Long> accountTitle = createNumber("accountTitle", Long.class);

    public final StringPath address = createString("address");

    public final StringPath businessRegistrationNumber = createString("businessRegistrationNumber");

    public final StringPath cardNumber = createString("cardNumber");

    public final NumberPath<Double> commission = createNumber("commission", Double.class);

    public final NumberPath<Long> commissionAccountTitle = createNumber("commissionAccountTitle", Long.class);

    public final DatePath<java.time.LocalDate> constractCloseDate = createDate("constractCloseDate", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> constractOpenDate = createDate("constractOpenDate", java.time.LocalDate.class);

    public final NumberPath<Long> customerGroupingCode = createNumber("customerGroupingCode", Long.class);

    public final StringPath fax = createString("fax");

    public final StringPath homePage = createString("homePage");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath note = createString("note");

    public final StringPath phone = createString("phone");

    public final EnumPath<lkm.starterproject.accounting.constants.TradeStatus> tradeStatus = createEnum("tradeStatus", lkm.starterproject.accounting.constants.TradeStatus.class);

    public final EnumPath<lkm.starterproject.accounting.constants.UseStatus> useStatus = createEnum("useStatus", lkm.starterproject.accounting.constants.UseStatus.class);

    public final NumberPath<Long> zipCode = createNumber("zipCode", Long.class);

    public QCard(String variable) {
        super(Card.class, forVariable(variable));
    }

    public QCard(Path<? extends Card> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCard(PathMetadata metadata) {
        super(Card.class, metadata);
    }

}

