package lkm.starterproject.accounting.entity.register;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFinance is a Querydsl query type for Finance
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFinance extends EntityPathBase<Finance> {

    private static final long serialVersionUID = -1032565915L;

    public static final QFinance finance = new QFinance("finance");

    public final DatePath<java.time.LocalDate> accoountOpenDate = createDate("accoountOpenDate", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> accountCloseDate = createDate("accountCloseDate", java.time.LocalDate.class);

    public final NumberPath<Integer> accountNumber = createNumber("accountNumber", Integer.class);

    public final NumberPath<Integer> accountTitle = createNumber("accountTitle", Integer.class);

    public final StringPath address = createString("address");

    public final StringPath businessRegistrationNumber = createString("businessRegistrationNumber");

    public final NumberPath<Long> code = createNumber("code", Long.class);

    public final StringPath depositType = createString("depositType");

    public final StringPath fax = createString("fax");

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
        super(Finance.class, forVariable(variable));
    }

    public QFinance(Path<? extends Finance> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFinance(PathMetadata metadata) {
        super(Finance.class, metadata);
    }

}

