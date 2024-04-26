package lkm.starterproject.accounting.entity.register;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFinance is a Querydsl query type for Finance
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFinance extends EntityPathBase<Finance> {

    private static final long serialVersionUID = -1032565915L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFinance finance1 = new QFinance("finance1");

    public final DatePath<java.time.LocalDate> accoountOpenDate = createDate("accoountOpenDate", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> accountCloseDate = createDate("accountCloseDate", java.time.LocalDate.class);

    public final NumberPath<Integer> accountNumber = createNumber("accountNumber", Integer.class);

    public final NumberPath<Integer> accountTitle = createNumber("accountTitle", Integer.class);

    public final StringPath address = createString("address");

    public final StringPath businessRegistrationNumber = createString("businessRegistrationNumber");

    public final NumberPath<Long> code = createNumber("code", Long.class);

    public final lkm.starterproject.accounting.entity.company.QCompany company;

    public final StringPath depositType = createString("depositType");

    public final StringPath fax = createString("fax");

    public final QFinance finance;

    public final StringPath homePage = createString("homePage");

    public final NumberPath<Double> interestRate = createNumber("interestRate", Double.class);

    public final NumberPath<Integer> localTaxBillCode = createNumber("localTaxBillCode", Integer.class);

    public final StringPath name = createString("name");

    public final StringPath note = createString("note");

    public final DatePath<java.time.LocalDate> overdraftDate = createDate("overdraftDate", java.time.LocalDate.class);

    public final NumberPath<Long> overdraftLimit = createNumber("overdraftLimit", Long.class);

    public final StringPath phone = createString("phone");

    public final EnumPath<lkm.starterproject.accounting.constants.UseStatus> useStatus = createEnum("useStatus", lkm.starterproject.accounting.constants.UseStatus.class);

    public final NumberPath<Integer> zipCode = createNumber("zipCode", Integer.class);

    public QFinance(String variable) {
        this(Finance.class, forVariable(variable), INITS);
    }

    public QFinance(Path<? extends Finance> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFinance(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFinance(PathMetadata metadata, PathInits inits) {
        this(Finance.class, metadata, inits);
    }

    public QFinance(Class<? extends Finance> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.company = inits.isInitialized("company") ? new lkm.starterproject.accounting.entity.company.QCompany(forProperty("company")) : null;
        this.finance = inits.isInitialized("finance") ? new QFinance(forProperty("finance"), inits.get("finance")) : null;
    }

}

