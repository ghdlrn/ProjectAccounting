package lkm.starterproject.accounting.entity.statement;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPromissoryNote is a Querydsl query type for PromissoryNote
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPromissoryNote extends EntityPathBase<PromissoryNote> {

    private static final long serialVersionUID = -1049120972L;

    public static final QPromissoryNote promissoryNote = new QPromissoryNote("promissoryNote");

    public final NumberPath<Long> amount = createNumber("amount", Long.class);

    public final NumberPath<Long> code = createNumber("code", Long.class);

    public final DatePath<java.time.LocalDate> expirationDate = createDate("expirationDate", java.time.LocalDate.class);

    public final StringPath finance = createString("finance");

    public final DatePath<java.time.LocalDate> issuingDate = createDate("issuingDate", java.time.LocalDate.class);

    public final NumberPath<Long> money = createNumber("money", Long.class);

    public final StringPath promissoryNoteNumber = createString("promissoryNoteNumber");

    public final StringPath promissoryNoteTypeStatus = createString("promissoryNoteTypeStatus");

    public QPromissoryNote(String variable) {
        super(PromissoryNote.class, forVariable(variable));
    }

    public QPromissoryNote(Path<? extends PromissoryNote> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPromissoryNote(PathMetadata metadata) {
        super(PromissoryNote.class, metadata);
    }

}

