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

    public final lkm.starterproject.accounting.entity.basic.QAddress address;

    public final StringPath businessItem = createString("businessItem");

    public final StringPath businessPersonType = createString("businessPersonType");

    public final StringPath businessRegistrationNumber = createString("businessRegistrationNumber");

    public final EnumPath<lkm.starterproject.accounting.constants.BusinessScaleStatus> businessScaleStatus = createEnum("businessScaleStatus", lkm.starterproject.accounting.constants.BusinessScaleStatus.class);

    public final StringPath businessType = createString("businessType");

    public final StringPath chargeEmail = createString("chargeEmail");

    public final StringPath chargeName = createString("chargeName");

    public final NumberPath<Long> code = createNumber("code", Long.class);

    public final EnumPath<lkm.starterproject.accounting.constants.CompanyStatus> companyStatus = createEnum("companyStatus", lkm.starterproject.accounting.constants.CompanyStatus.class);

    public final EnumPath<lkm.starterproject.accounting.constants.CompanyTypeStatus> companyTypeStatus = createEnum("companyTypeStatus", lkm.starterproject.accounting.constants.CompanyTypeStatus.class);

    public final EnumPath<lkm.starterproject.accounting.constants.CorporationClassifyStatus> corporationClassifyStatus = createEnum("corporationClassifyStatus", lkm.starterproject.accounting.constants.CorporationClassifyStatus.class);

    public final StringPath corporationRegistrationNumber = createString("corporationRegistrationNumber");

    public final StringPath fax = createString("fax");

    public final lkm.starterproject.accounting.entity.register.QFinance finance;

    public final NumberPath<Long> fiscalYearClass = createNumber("fiscalYearClass", Long.class);

    public final DatePath<java.time.LocalDate> fiscalYearEnd = createDate("fiscalYearEnd", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> fiscalYearStart = createDate("fiscalYearStart", java.time.LocalDate.class);

    public final EnumPath<lkm.starterproject.accounting.constants.HeadOfficeStatus> headOfficeStatus = createEnum("headOfficeStatus", lkm.starterproject.accounting.constants.HeadOfficeStatus.class);

    public final lkm.starterproject.accounting.entity.basic.QLocalTax localTax;

    public final StringPath localTaxBillDivision = createString("localTaxBillDivision");

    public final StringPath name = createString("name");

    public final StringPath nameOfRepresentative = createString("nameOfRepresentative");

    public final StringPath note = createString("note");

    public final EnumPath<lkm.starterproject.accounting.constants.PaymentHeadOfficeStatus> paymentHeadOfficeStatus = createEnum("paymentHeadOfficeStatus", lkm.starterproject.accounting.constants.PaymentHeadOfficeStatus.class);

    public final StringPath phone = createString("phone");

    public final DatePath<java.time.LocalDate> privatePracticeDate = createDate("privatePracticeDate", java.time.LocalDate.class);

    public final StringPath residentRegistrationNumber = createString("residentRegistrationNumber");

    public final lkm.starterproject.accounting.entity.basic.QTaxOffice taxOffice;

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
        this.address = inits.isInitialized("address") ? new lkm.starterproject.accounting.entity.basic.QAddress(forProperty("address")) : null;
        this.finance = inits.isInitialized("finance") ? new lkm.starterproject.accounting.entity.register.QFinance(forProperty("finance")) : null;
        this.localTax = inits.isInitialized("localTax") ? new lkm.starterproject.accounting.entity.basic.QLocalTax(forProperty("localTax")) : null;
        this.taxOffice = inits.isInitialized("taxOffice") ? new lkm.starterproject.accounting.entity.basic.QTaxOffice(forProperty("taxOffice")) : null;
    }

}

