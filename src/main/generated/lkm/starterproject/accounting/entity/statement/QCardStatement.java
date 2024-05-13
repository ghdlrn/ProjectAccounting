package lkm.starterproject.accounting.entity.statement;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCardStatement is a Querydsl query type for CardStatement
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCardStatement extends EntityPathBase<CardStatement> {

    private static final long serialVersionUID = -1866404566L;

    public static final QCardStatement cardStatement = new QCardStatement("cardStatement");

    public final StringPath customerName = createString("customerName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> money = createNumber("money", Long.class);

    public final DatePath<java.time.LocalDate> RegistrationDate = createDate("RegistrationDate", java.time.LocalDate.class);

    public QCardStatement(String variable) {
        super(CardStatement.class, forVariable(variable));
    }

    public QCardStatement(Path<? extends CardStatement> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCardStatement(PathMetadata metadata) {
        super(CardStatement.class, metadata);
    }

}

