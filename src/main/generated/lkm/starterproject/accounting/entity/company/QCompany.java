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

    public static final QCompany company = new QCompany("company");

    public final NumberPath<Integer> accountNumber = createNumber("accountNumber", Integer.class);

    public final ListPath<lkm.starterproject.accounting.entity.register.AccountTitle, lkm.starterproject.accounting.entity.register.QAccountTitle> accountTitles = this.<lkm.starterproject.accounting.entity.register.AccountTitle, lkm.starterproject.accounting.entity.register.QAccountTitle>createList("accountTitles", lkm.starterproject.accounting.entity.register.AccountTitle.class, lkm.starterproject.accounting.entity.register.QAccountTitle.class, PathInits.DIRECT2);

    public final StringPath address = createString("address");

    public final DatePath<java.time.LocalDate> businessClosureDate = createDate("businessClosureDate", java.time.LocalDate.class);

    public final StringPath businessItem = createString("businessItem");

    public final StringPath businessRegistrationNumber = createString("businessRegistrationNumber");

    public final EnumPath<lkm.starterproject.accounting.constants.BusinessScaleStatus> businessScaleStatus = createEnum("businessScaleStatus", lkm.starterproject.accounting.constants.BusinessScaleStatus.class);

    public final NumberPath<Integer> businessTaxOfficeCode = createNumber("businessTaxOfficeCode", Integer.class);

    public final StringPath businessType = createString("businessType");

    public final ListPath<lkm.starterproject.accounting.entity.register.Card, lkm.starterproject.accounting.entity.register.QCard> cards = this.<lkm.starterproject.accounting.entity.register.Card, lkm.starterproject.accounting.entity.register.QCard>createList("cards", lkm.starterproject.accounting.entity.register.Card.class, lkm.starterproject.accounting.entity.register.QCard.class, PathInits.DIRECT2);

    public final NumberPath<Long> code = createNumber("code", Long.class);

    public final EnumPath<lkm.starterproject.accounting.constants.CompanyStatus> companyStatus = createEnum("companyStatus", lkm.starterproject.accounting.constants.CompanyStatus.class);

    public final EnumPath<lkm.starterproject.accounting.constants.CompanyTypeStatus> companyTypeStatus = createEnum("companyTypeStatus", lkm.starterproject.accounting.constants.CompanyTypeStatus.class);

    public final EnumPath<lkm.starterproject.accounting.constants.CorporationClassifyStatus> corporationClassifyStatus = createEnum("corporationClassifyStatus", lkm.starterproject.accounting.constants.CorporationClassifyStatus.class);

    public final StringPath corporationRegistrationNumber = createString("corporationRegistrationNumber");

    public final ListPath<lkm.starterproject.accounting.entity.register.Customer, lkm.starterproject.accounting.entity.register.QCustomer> customers = this.<lkm.starterproject.accounting.entity.register.Customer, lkm.starterproject.accounting.entity.register.QCustomer>createList("customers", lkm.starterproject.accounting.entity.register.Customer.class, lkm.starterproject.accounting.entity.register.QCustomer.class, PathInits.DIRECT2);

    public final ListPath<Department, QDepartment> departments = this.<Department, QDepartment>createList("departments", Department.class, QDepartment.class, PathInits.DIRECT2);

    public final StringPath email = createString("email");

    public final ListPath<Employee, QEmployee> employees = this.<Employee, QEmployee>createList("employees", Employee.class, QEmployee.class, PathInits.DIRECT2);

    public final DatePath<java.time.LocalDate> establishmentDate = createDate("establishmentDate", java.time.LocalDate.class);

    public final StringPath fax = createString("fax");

    public final ListPath<lkm.starterproject.accounting.entity.register.Finance, lkm.starterproject.accounting.entity.register.QFinance> finances = this.<lkm.starterproject.accounting.entity.register.Finance, lkm.starterproject.accounting.entity.register.QFinance>createList("finances", lkm.starterproject.accounting.entity.register.Finance.class, lkm.starterproject.accounting.entity.register.QFinance.class, PathInits.DIRECT2);

    public final NumberPath<Integer> fiscalYearClass = createNumber("fiscalYearClass", Integer.class);

    public final DatePath<java.time.LocalDate> fiscalYearEnd = createDate("fiscalYearEnd", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> fiscalYearStart = createDate("fiscalYearStart", java.time.LocalDate.class);

    public final ListPath<lkm.starterproject.accounting.entity.register.FixedAsset, lkm.starterproject.accounting.entity.register.QFixedAsset> fixedAssets = this.<lkm.starterproject.accounting.entity.register.FixedAsset, lkm.starterproject.accounting.entity.register.QFixedAsset>createList("fixedAssets", lkm.starterproject.accounting.entity.register.FixedAsset.class, lkm.starterproject.accounting.entity.register.QFixedAsset.class, PathInits.DIRECT2);

    public final EnumPath<lkm.starterproject.accounting.constants.ForeignerStatus> foreignerStatus = createEnum("foreignerStatus", lkm.starterproject.accounting.constants.ForeignerStatus.class);

    public final NumberPath<Integer> headOfficeCode = createNumber("headOfficeCode", Integer.class);

    public final EnumPath<lkm.starterproject.accounting.constants.HeadOfficeStatus> headOfficeStatus = createEnum("headOfficeStatus", lkm.starterproject.accounting.constants.HeadOfficeStatus.class);

    public final NumberPath<Integer> localTaxBillCode = createNumber("localTaxBillCode", Integer.class);

    public final NumberPath<Integer> localTaxBillDivisionCode = createNumber("localTaxBillDivisionCode", Integer.class);

    public final NumberPath<Integer> mainIndustryCode = createNumber("mainIndustryCode", Integer.class);

    public final ListPath<lkm.starterproject.auth.entity.MemberCompanyEntity, lkm.starterproject.auth.entity.QMemberCompanyEntity> memberCompanyEntities = this.<lkm.starterproject.auth.entity.MemberCompanyEntity, lkm.starterproject.auth.entity.QMemberCompanyEntity>createList("memberCompanyEntities", lkm.starterproject.auth.entity.MemberCompanyEntity.class, lkm.starterproject.auth.entity.QMemberCompanyEntity.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final StringPath nameOfRepresentative = createString("nameOfRepresentative");

    public final EnumPath<lkm.starterproject.accounting.constants.PaymentHeadOfficeStatus> paymentHeadOfficeStatus = createEnum("paymentHeadOfficeStatus", lkm.starterproject.accounting.constants.PaymentHeadOfficeStatus.class);

    public final StringPath phone = createString("phone");

    public final DatePath<java.time.LocalDate> privatePracticeDate = createDate("privatePracticeDate", java.time.LocalDate.class);

    public final ListPath<lkm.starterproject.accounting.entity.statement.PromissoryNote, lkm.starterproject.accounting.entity.statement.QPromissoryNote> promissoryNotes = this.<lkm.starterproject.accounting.entity.statement.PromissoryNote, lkm.starterproject.accounting.entity.statement.QPromissoryNote>createList("promissoryNotes", lkm.starterproject.accounting.entity.statement.PromissoryNote.class, lkm.starterproject.accounting.entity.statement.QPromissoryNote.class, PathInits.DIRECT2);

    public final StringPath residentRegistrationNumber = createString("residentRegistrationNumber");

    public final ListPath<lkm.starterproject.accounting.entity.statement.Statement, lkm.starterproject.accounting.entity.statement.QStatement> statements = this.<lkm.starterproject.accounting.entity.statement.Statement, lkm.starterproject.accounting.entity.statement.QStatement>createList("statements", lkm.starterproject.accounting.entity.statement.Statement.class, lkm.starterproject.accounting.entity.statement.QStatement.class, PathInits.DIRECT2);

    public final EnumPath<lkm.starterproject.accounting.constants.UseStatus> useStatus = createEnum("useStatus", lkm.starterproject.accounting.constants.UseStatus.class);

    public final NumberPath<Integer> zipCode = createNumber("zipCode", Integer.class);

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

