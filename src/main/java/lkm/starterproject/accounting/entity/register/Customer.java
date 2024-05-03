package lkm.starterproject.accounting.entity.register;

import jakarta.persistence.*;
import lkm.starterproject.accounting.constants.LiquorRetailStatus;
import lkm.starterproject.accounting.constants.TradeStatus;
import lkm.starterproject.accounting.constants.UseStatus;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter @Setter @ToString
@Builder @NoArgsConstructor @AllArgsConstructor
@Table(name="customer")
public class Customer {      //거래처

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long code;        //거래처 코드번호

    @Column(nullable = false)
    private String name;        //거래처명

    @Column(length = 14)
    private String RegistrationNumber;      //사업자등록번호or주민번호or외국인번호 등 등록번호

    private String nameOfRepresentative;    //대표자명

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TradeStatus tradeStatus;        //거래구분

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UseStatus useStatus;        //사용여부

    @Column(nullable = false)
    private String businessType;    //업태

    @Column(nullable = false)
    private String businessItem;    //업종

    private Integer zipCode;    //우편번호

    private String address; //주소

    @Column(length = 13)
    private String phone;   //전화번호

    @Column(length = 13)
    private String fax;     //팩스번호

    private Integer chargeCode; //담당사원

    @Enumerated(EnumType.STRING)    //사업자단위 주사업장여부
    private CompanyUnitVATTaxationStatus companyUnitVATTaxationStatus;

    private Integer UnitReportingCustomerCode;  //단위신고거래처

    private Integer taxRegistrationCode;    //종사업장번호

    @Temporal(TemporalType.DATE)
    private LocalDate tradeStartDate;   //거래시작일

    @Temporal(TemporalType.DATE)
    private LocalDate tradeEndDate;     //거래종료일

    private String note;    //비고

    private String customerChargeDepartment;    //가레처담당자부서

    private String customerChargePosition;  //거래처담당자 직급

    private String customerChargeName;      //거래처 담당자 이름

    private String customerChargePhone;     //거래처 담당자 폰번호

    private String customerChargeEmail;     //거래처 담당자 이메일

    private String homePage;    //거래처 홈페이지

    private Integer customerBankAccountNumber;  //거래처 은행계좌

    private String customerAccountHolder;   //거래처 예금주

    private Integer accountNumber;  //입금계좌

    private Integer myAccountTitle; //내 계정과목

    private Integer otherAccountTitle;  //상대 계정과목

    private Integer bankLine;   //여신한도액

    private Integer amountOfCollateral; //담보설정액

    @Enumerated(EnumType.STRING)
    private LiquorRetailStatus liquorRetailStatus;  //주류코드(소매)

    private Integer localTaxBillCode;   //지방세 법정동 코드
}
