package lkm.starterproject.accounting.entity.register;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCompendium is a Querydsl query type for Compendium
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCompendium extends EntityPathBase<Compendium> {

    private static final long serialVersionUID = 451730026L;

    public static final QCompendium compendium = new QCompendium("compendium");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath note = createString("note");

    public QCompendium(String variable) {
        super(Compendium.class, forVariable(variable));
    }

    public QCompendium(Path<? extends Compendium> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCompendium(PathMetadata metadata) {
        super(Compendium.class, metadata);
    }

}

