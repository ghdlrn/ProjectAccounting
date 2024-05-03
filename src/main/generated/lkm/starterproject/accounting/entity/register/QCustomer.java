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

    public final NumberPath<Integer> accountNumber = createNumber("accountNumber", Integer.class);

    public final StringPath address = createString("address");

    public final NumberPath<Integer> amountOfCollateral = createNumber("amountOfCollateral", Integer.class);

    public final NumberPath<Integer> bankLine = createNumber("bankLine", Integer.class);

    public final StringPath businessItem = createString("businessItem");

    public final StringPath businessType = createString("businessType");

    public final NumberPath<Integer> chargeCode = createNumber("chargeCode", Integer.class);

    public final NumberPath<Long> code = createNumber("code", Long.class);

    public final StringPath companyUnitVATTaxationStatus = createString("companyUnitVATTaxationStatus");

    public final StringPath customerAccountHolder = createString("customerAccountHolder");

    public final NumberPath<Integer> customerBankAccountNumber = createNumber("customerBankAccountNumber", Integer.class);

    public final StringPath customerChargeDepartment = createString("customerChargeDepartment");

    public final StringPath customerChargeEmail = createString("customerChargeEmail");

    public final StringPath customerChargeName = createString("customerChargeName");

    public final StringPath customerChargePhone = createString("customerChargePhone");

    public final StringPath customerChargePosition = createString("customerChargePosition");

    public final StringPath fax = createString("fax");

    public final StringPath homePage = createString("homePage");

    public final EnumPath<lkm.starterproject.accounting.constants.LiquorRetailStatus> liquorRetailStatus = createEnum("liquorRetailStatus", lkm.starterproject.accounting.constants.LiquorRetailStatus.class);

    public final NumberPath<Integer> localTaxBillCode = createNumber("localTaxBillCode", Integer.class);

    public final NumberPath<Integer> myAccountTitle = createNumber("myAccountTitle", Integer.class);

    public final StringPath name = createString("name");

    public final StringPath nameOfRepresentative = createString("nameOfRepresentative");

    public final StringPath note = createString("note");

    public final NumberPath<Integer> otherAccountTitle = createNumber("otherAccountTitle", Integer.class);

    public final StringPath phone = createString("phone");

    public final StringPath RegistrationNumber = createString("RegistrationNumber");

    public final NumberPath<Integer> taxRegistrationCode = createNumber("taxRegistrationCode", Integer.class);

    public final DatePath<java.time.LocalDate> tradeEndDate = createDate("tradeEndDate", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> tradeStartDate = createDate("tradeStartDate", java.time.LocalDate.class);

    public final EnumPath<lkm.starterproject.accounting.constants.TradeStatus> tradeStatus = createEnum("tradeStatus", lkm.starterproject.accounting.constants.TradeStatus.class);

    public final NumberPath<Integer> UnitReportingCustomerCode = createNumber("UnitReportingCustomerCode", Integer.class);

    public final EnumPath<lkm.starterproject.accounting.constants.UseStatus> useStatus = createEnum("useStatus", lkm.starterproject.accounting.constants.UseStatus.class);

    public final NumberPath<Integer> zipCode = createNumber("zipCode", Integer.class);

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

