package lkm.starterproject.accounting.entity.statement;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPromissoryNote is a Querydsl query type for PromissoryNote
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPromissoryNote extends EntityPathBase<PromissoryNote> {

    private static final long serialVersionUID = -1049120972L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPromissoryNote promissoryNote = new QPromissoryNote("promissoryNote");

    public final NumberPath<Long> amount = createNumber("amount", Long.class);

    public final NumberPath<Long> code = createNumber("code", Long.class);

    public final lkm.starterproject.accounting.entity.register.QCustomer customer;

    public final DatePath<java.time.LocalDate> expirationDate = createDate("expirationDate", java.time.LocalDate.class);

    public final StringPath finance = createString("finance");

    public final DatePath<java.time.LocalDate> issuingDate = createDate("issuingDate", java.time.LocalDate.class);

    public final NumberPath<Long> money = createNumber("money", Long.class);

    public final StringPath promissoryNoteNumber = createString("promissoryNoteNumber");

    public final EnumPath<lkm.starterproject.accounting.constants.PromissoryNoteTypeStatus> promissoryNoteTypeStatus = createEnum("promissoryNoteTypeStatus", lkm.starterproject.accounting.constants.PromissoryNoteTypeStatus.class);

    public QPromissoryNote(String variable) {
        this(PromissoryNote.class, forVariable(variable), INITS);
    }

    public QPromissoryNote(Path<? extends PromissoryNote> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPromissoryNote(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPromissoryNote(PathMetadata metadata, PathInits inits) {
        this(PromissoryNote.class, metadata, inits);
    }

    public QPromissoryNote(Class<? extends PromissoryNote> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.customer = inits.isInitialized("customer") ? new lkm.starterproject.accounting.entity.register.QCustomer(forProperty("customer")) : null;
    }

}

