package lkm.starterproject.accounting.entity.register;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCar is a Querydsl query type for Car
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCar extends EntityPathBase<Car> {

    private static final long serialVersionUID = 1767647L;

    public static final QCar car = new QCar("car");

    public final StringPath carModel = createString("carModel");

    public final EnumPath<lkm.starterproject.accounting.constants.CarNameStatus> carNameStatus = createEnum("carNameStatus", lkm.starterproject.accounting.constants.CarNameStatus.class);

    public final NumberPath<Long> code = createNumber("code", Long.class);

    public final DatePath<java.time.LocalDate> endInsuranceDate = createDate("endInsuranceDate", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> endTenancy = createDate("endTenancy", java.time.LocalDate.class);

    public final StringPath insurance = createString("insurance");

    public final StringPath licensePlateNumber = createString("licensePlateNumber");

    public final NumberPath<Integer> mileage = createNumber("mileage", Integer.class);

    public final DatePath<java.time.LocalDate> startInsuranceDate = createDate("startInsuranceDate", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> startTenancy = createDate("startTenancy", java.time.LocalDate.class);

    public QCar(String variable) {
        super(Car.class, forVariable(variable));
    }

    public QCar(Path<? extends Car> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCar(PathMetadata metadata) {
        super(Car.class, metadata);
    }

}
