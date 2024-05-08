package lkm.starterproject.accounting.entity.register;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCustomer is a Querydsl query type for Customer
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCustomer extends EntityPathBase<Customer> {

    private static final long serialVersionUID = -657394253L;

    public static final QCustomer customer = new QCustomer("customer");

    public final NumberPath<Long> accountNumber = createNumber("accountNumber", Long.class);

    public final StringPath address = createString("address");

    public final NumberPath<Long> amountOfCollateral = createNumber("amountOfCollateral", Long.class);

    public final NumberPath<Long> bankLine = createNumber("bankLine", Long.class);

    public final StringPath businessItem = createString("businessItem");

    public final StringPath businessType = createString("businessType");

    public final NumberPath<Long> chargeCode = createNumber("chargeCode", Long.class);

    public final NumberPath<Long> code = createNumber("code", Long.class);

    public final StringPath companyUnitVATTaxationStatus = createString("companyUnitVATTaxationStatus");

    public final StringPath customerAccountHolder = createString("customerAccountHolder");

    public final NumberPath<Long> customerBankAccountNumber = createNumber("customerBankAccountNumber", Long.class);

    public final StringPath customerChargeDepartment = createString("customerChargeDepartment");

    public final StringPath customerChargeEmail = createString("customerChargeEmail");

    public final StringPath customerChargeName = createString("customerChargeName");

    public final StringPath customerChargePhone = createString("customerChargePhone");

    public final StringPath customerChargePosition = createString("customerChargePosition");

    public final StringPath fax = createString("fax");

    public final StringPath homePage = createString("homePage");

    public final EnumPath<lkm.starterproject.accounting.constants.LiquorRetailStatus> liquorRetailStatus = createEnum("liquorRetailStatus", lkm.starterproject.accounting.constants.LiquorRetailStatus.class);

    public final NumberPath<Long> localTaxBillCode = createNumber("localTaxBillCode", Long.class);

    public final NumberPath<Long> myAccountTitle = createNumber("myAccountTitle", Long.class);

    public final StringPath name = createString("name");

    public final StringPath nameOfRepresentative = createString("nameOfRepresentative");

    public final StringPath note = createString("note");

    public final NumberPath<Long> otherAccountTitle = createNumber("otherAccountTitle", Long.class);

    public final StringPath phone = createString("phone");

    public final StringPath RegistrationNumber = createString("RegistrationNumber");

    public final NumberPath<Long> taxRegistrationCode = createNumber("taxRegistrationCode", Long.class);

    public final DatePath<java.time.LocalDate> tradeEndDate = createDate("tradeEndDate", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> tradeStartDate = createDate("tradeStartDate", java.time.LocalDate.class);

    public final EnumPath<lkm.starterproject.accounting.constants.TradeStatus> tradeStatus = createEnum("tradeStatus", lkm.starterproject.accounting.constants.TradeStatus.class);

    public final NumberPath<Long> UnitReportingCustomerCode = createNumber("UnitReportingCustomerCode", Long.class);

    public final EnumPath<lkm.starterproject.accounting.constants.UseStatus> useStatus = createEnum("useStatus", lkm.starterproject.accounting.constants.UseStatus.class);

    public final NumberPath<Long> zipCode = createNumber("zipCode", Long.class);

    public QCustomer(String variable) {
        super(Customer.class, forVariable(variable));
    }

    public QCustomer(Path<? extends Customer> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCustomer(PathMetadata metadata) {
        super(Customer.class, metadata);
    }

}

