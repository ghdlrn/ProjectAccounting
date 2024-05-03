package lkm.starterproject.accounting.entity.company;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCompany is a Querydsl query type for Company
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCompany extends EntityPathBase<Company> {

    private static final long serialVersionUID = 216073526L;

    public static final QCompany company = new QCompany("company");

    public final NumberPath<Long> accountNumber = createNumber("accountNumber", Long.class);

    public final StringPath address = createString("address");

    public final DatePath<java.time.LocalDate> businessClosureDate = createDate("businessClosureDate", java.time.LocalDate.class);

    public final StringPath businessItem = createString("businessItem");

    public final StringPath businessRegistrationNumber = createString("businessRegistrationNumber");

    public final EnumPath<lkm.starterproject.accounting.constants.BusinessScaleStatus> businessScaleStatus = createEnum("businessScaleStatus", lkm.starterproject.accounting.constants.BusinessScaleStatus.class);

    public final StringPath businessType = createString("businessType");

    public final NumberPath<Long> code = createNumber("code", Long.class);

    public final EnumPath<lkm.starterproject.accounting.constants.CompanyStatus> companyStatus = createEnum("companyStatus", lkm.starterproject.accounting.constants.CompanyStatus.class);

    public final EnumPath<lkm.starterproject.accounting.constants.CompanyTypeStatus> companyTypeStatus = createEnum("companyTypeStatus", lkm.starterproject.accounting.constants.CompanyTypeStatus.class);

    public final EnumPath<lkm.starterproject.accounting.constants.CorporationClassifyStatus> corporationClassifyStatus = createEnum("corporationClassifyStatus", lkm.starterproject.accounting.constants.CorporationClassifyStatus.class);

    public final StringPath corporationRegistrationNumber = createString("corporationRegistrationNumber");

    public final StringPath email = createString("email");

    public final DatePath<java.time.LocalDate> establishmentDate = createDate("establishmentDate", java.time.LocalDate.class);

    public final StringPath fax = createString("fax");

    public final NumberPath<Long> fiscalYearClass = createNumber("fiscalYearClass", Long.class);

    public final DatePath<java.time.LocalDate> fiscalYearEnd = createDate("fiscalYearEnd", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> fiscalYearStart = createDate("fiscalYearStart", java.time.LocalDate.class);

    public final EnumPath<lkm.starterproject.accounting.constants.ForeignerStatus> foreignerStatus = createEnum("foreignerStatus", lkm.starterproject.accounting.constants.ForeignerStatus.class);

    public final NumberPath<Long> headOfficeCode = createNumber("headOfficeCode", Long.class);

    public final EnumPath<lkm.starterproject.accounting.constants.HeadOfficeStatus> headOfficeStatus = createEnum("headOfficeStatus", lkm.starterproject.accounting.constants.HeadOfficeStatus.class);

    public final NumberPath<Long> localTaxBillCode = createNumber("localTaxBillCode", Long.class);

    public final NumberPath<Long> localTaxBillDivisionCode = createNumber("localTaxBillDivisionCode", Long.class);

    public final NumberPath<Long> mainIndustryCode = createNumber("mainIndustryCode", Long.class);

    public final StringPath name = createString("name");

    public final StringPath nameOfRepresentative = createString("nameOfRepresentative");

    public final EnumPath<lkm.starterproject.accounting.constants.PaymentHeadOfficeStatus> paymentHeadOfficeStatus = createEnum("paymentHeadOfficeStatus", lkm.starterproject.accounting.constants.PaymentHeadOfficeStatus.class);

    public final StringPath phone = createString("phone");

    public final DatePath<java.time.LocalDate> privatePracticeDate = createDate("privatePracticeDate", java.time.LocalDate.class);

    public final StringPath residentRegistrationNumber = createString("residentRegistrationNumber");

    public final NumberPath<Long> taxOfficeCode = createNumber("taxOfficeCode", Long.class);

    public final EnumPath<lkm.starterproject.accounting.constants.UseStatus> useStatus = createEnum("useStatus", lkm.starterproject.accounting.constants.UseStatus.class);

    public final NumberPath<Long> zipCode = createNumber("zipCode", Long.class);

    public QCompany(String variable) {
        super(Company.class, forVariable(variable));
    }

    public QCompany(Path<? extends Company> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCompany(PathMetadata metadata) {
        super(Company.class, metadata);
    }

}

