package lkm.starterproject.accounting.entity.basic;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTaxOffice is a Querydsl query type for TaxOffice
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTaxOffice extends EntityPathBase<TaxOffice> {

    private static final long serialVersionUID = -769108559L;

    public static final QTaxOffice taxOffice = new QTaxOffice("taxOffice");

    public final StringPath accountNumber = createString("accountNumber");

    public final StringPath address = createString("address");

    public final NumberPath<Integer> code = createNumber("code", Integer.class);

    public final StringPath fax = createString("fax");

    public final StringPath jurisdiction = createString("jurisdiction");

    public final StringPath name = createString("name");

    public final StringPath phone = createString("phone");

    public final NumberPath<Integer> postCode = createNumber("postCode", Integer.class);

    public QTaxOffice(String variable) {
        super(TaxOffice.class, forVariable(variable));
    }

    public QTaxOffice(Path<? extends TaxOffice> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTaxOffice(PathMetadata metadata) {
        super(TaxOffice.class, metadata);
    }

}

