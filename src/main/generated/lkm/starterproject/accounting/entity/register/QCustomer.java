package lkm.starterproject.accounting.entity.register;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCustomer is a Querydsl query type for Customer
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCustomer extends EntityPathBase<Customer> {

    private static final long serialVersionUID = -657394253L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCustomer customer = new QCustomer("customer");

    public final lkm.starterproject.accounting.entity.basic.QAddress address;

    public final NumberPath<Long> amountOfCollateral = createNumber("amountOfCollateral", Long.class);

    public final NumberPath<Long> bankLine = createNumber("bankLine", Long.class);

    public final StringPath businessItem = createString("businessItem");

    public final StringPath businessType = createString("businessType");

    public final lkm.starterproject.accounting.entity.company.QCompany company;

    public final StringPath customerAccountHolder = createString("customerAccountHolder");

    public final StringPath customerAccountNumber = createString("customerAccountNumber");

    public final StringPath customerChargeDepartment = createString("customerChargeDepartment");

    public final StringPath customerChargeEmail = createString("customerChargeEmail");

    public final StringPath customerChargeName = createString("customerChargeName");

    public final StringPath customerChargePhone = createString("customerChargePhone");

    public final StringPath customerChargePosition = createString("customerChargePosition");

    public final StringPath fax = createString("fax");

    public final StringPath homePage = createString("homePage");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final lkm.starterproject.accounting.entity.basic.QLocalTax localTax;

    public final StringPath name = createString("name");

    public final StringPath nameOfRepresentative = createString("nameOfRepresentative");

    public final StringPath note = createString("note");

    public final StringPath phone = createString("phone");

    public final StringPath registrationNumber = createString("registrationNumber");

    public final StringPath registrationNumberType = createString("registrationNumberType");

    public final NumberPath<Long> taxRegistrationCode = createNumber("taxRegistrationCode", Long.class);

    public final DatePath<java.time.LocalDate> tradeEndDate = createDate("tradeEndDate", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> tradeStartDate = createDate("tradeStartDate", java.time.LocalDate.class);

    public final NumberPath<Long> unitReportingCustomerCode = createNumber("unitReportingCustomerCode", Long.class);

    public final EnumPath<lkm.starterproject.accounting.constants.UseStatus> useStatus = createEnum("useStatus", lkm.starterproject.accounting.constants.UseStatus.class);

    public QCustomer(String variable) {
        this(Customer.class, forVariable(variable), INITS);
    }

    public QCustomer(Path<? extends Customer> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCustomer(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCustomer(PathMetadata metadata, PathInits inits) {
        this(Customer.class, metadata, inits);
    }

    public QCustomer(Class<? extends Customer> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.address = inits.isInitialized("address") ? new lkm.starterproject.accounting.entity.basic.QAddress(forProperty("address")) : null;
        this.company = inits.isInitialized("company") ? new lkm.starterproject.accounting.entity.company.QCompany(forProperty("company"), inits.get("company")) : null;
        this.localTax = inits.isInitialized("localTax") ? new lkm.starterproject.accounting.entity.basic.QLocalTax(forProperty("localTax")) : null;
    }

}

