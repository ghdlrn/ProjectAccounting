package lkm.starterproject.accounting.entity.basic;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTaxOffice is a Querydsl query type for TaxOffice
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTaxOffice extends EntityPathBase<TaxOffice> {

    private static final long serialVersionUID = -769108559L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTaxOffice taxOffice = new QTaxOffice("taxOffice");

    public final StringPath accountNumber = createString("accountNumber");

    public final StringPath address = createString("address");

    public final NumberPath<Long> code = createNumber("code", Long.class);

    public final lkm.starterproject.accounting.entity.company.QCompany company;

    public final StringPath fax = createString("fax");

    public final StringPath jurisdiction = createString("jurisdiction");

    public final StringPath name = createString("name");

    public final StringPath phone = createString("phone");

    public final NumberPath<Long> postCode = createNumber("postCode", Long.class);

    public QTaxOffice(String variable) {
        this(TaxOffice.class, forVariable(variable), INITS);
    }

    public QTaxOffice(Path<? extends TaxOffice> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTaxOffice(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTaxOffice(PathMetadata metadata, PathInits inits) {
        this(TaxOffice.class, metadata, inits);
    }

    public QTaxOffice(Class<? extends TaxOffice> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.company = inits.isInitialized("company") ? new lkm.starterproject.accounting.entity.company.QCompany(forProperty("company"), inits.get("company")) : null;
    }

}

