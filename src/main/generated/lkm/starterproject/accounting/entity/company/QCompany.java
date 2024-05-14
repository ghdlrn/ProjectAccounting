package lkm.starterproject.accounting.entity.company;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCompany is a Querydsl query type for Company
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCompany extends EntityPathBase<Company> {

    private static final long serialVersionUID = 216073526L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCompany company = new QCompany("company");

    public final lkm.starterproject.auth.entity.QBaseEntity _super = new lkm.starterproject.auth.entity.QBaseEntity(this);

    public final StringPath businessItem = createString("businessItem");

    public final StringPath businessRegistrationNumber = createString("businessRegistrationNumber");

    public final StringPath businessScaleStatus = createString("businessScaleStatus");

    public final StringPath businessType = createString("businessType");

    public final StringPath chargeEmail = createString("chargeEmail");

    public final StringPath chargeName = createString("chargeName");

    public final StringPath companyTypeStatus = createString("companyTypeStatus");

    public final StringPath corporationClassifyStatus = createString("corporationClassifyStatus");

    public final StringPath corporationRegistrationNumber = createString("corporationRegistrationNumber");

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final ListPath<lkm.starterproject.accounting.entity.register.Customer, lkm.starterproject.accounting.entity.register.QCustomer> customers = this.<lkm.starterproject.accounting.entity.register.Customer, lkm.starterproject.accounting.entity.register.QCustomer>createList("customers", lkm.starterproject.accounting.entity.register.Customer.class, lkm.starterproject.accounting.entity.register.QCustomer.class, PathInits.DIRECT2);

    public final StringPath extraAddress = createString("extraAddress");

    public final StringPath fax = createString("fax");

    public final StringPath finance = createString("finance");

    public final NumberPath<Long> fiscalYearClass = createNumber("fiscalYearClass", Long.class);

    public final DatePath<java.time.LocalDate> fiscalYearEnd = createDate("fiscalYearEnd", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> fiscalYearStart = createDate("fiscalYearStart", java.time.LocalDate.class);

    public final StringPath guideText = createString("guideText");

    public final StringPath headOfficeStatus = createString("headOfficeStatus");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath jibunAddress = createString("jibunAddress");

    public final StringPath licenseType = createString("licenseType");

    public final lkm.starterproject.accounting.entity.basic.QLocalTax localTax;

    public final StringPath localTaxBillDivision = createString("localTaxBillDivision");

    public final ListPath<lkm.starterproject.auth.entity.MemberCompany, lkm.starterproject.auth.entity.QMemberCompany> memberCompanies = this.<lkm.starterproject.auth.entity.MemberCompany, lkm.starterproject.auth.entity.QMemberCompany>createList("memberCompanies", lkm.starterproject.auth.entity.MemberCompany.class, lkm.starterproject.auth.entity.QMemberCompany.class, PathInits.DIRECT2);

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    public final StringPath name = createString("name");

    public final StringPath nameOfRepresentative = createString("nameOfRepresentative");

    public final StringPath note = createString("note");

    public final StringPath paymentHeadOfficeStatus = createString("paymentHeadOfficeStatus");

    public final StringPath phone = createString("phone");

    public final StringPath postcode = createString("postcode");

    public final DatePath<java.time.LocalDate> privatePracticeDate = createDate("privatePracticeDate", java.time.LocalDate.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regTime = _super.regTime;

    public final StringPath residentRegistrationNumber = createString("residentRegistrationNumber");

    public final StringPath roadAddress = createString("roadAddress");

    public final lkm.starterproject.accounting.entity.basic.QTaxOffice taxOffice;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateTime = _super.updateTime;

    public QCompany(String variable) {
        this(Company.class, forVariable(variable), INITS);
    }

    public QCompany(Path<? extends Company> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCompany(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCompany(PathMetadata metadata, PathInits inits) {
        this(Company.class, metadata, inits);
    }

    public QCompany(Class<? extends Company> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.localTax = inits.isInitialized("localTax") ? new lkm.starterproject.accounting.entity.basic.QLocalTax(forProperty("localTax")) : null;
        this.taxOffice = inits.isInitialized("taxOffice") ? new lkm.starterproject.accounting.entity.basic.QTaxOffice(forProperty("taxOffice")) : null;
    }

}

