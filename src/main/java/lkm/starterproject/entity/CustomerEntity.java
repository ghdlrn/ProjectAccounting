package lkm.starterproject.entity;

import jakarta.persistence.*;
import lkm.starterproject.constants.CompanyUnitVATTaxationStatus;
import lkm.starterproject.constants.LiquorRetailStatus;
import lkm.starterproject.constants.TradeStatus;
import lkm.starterproject.constants.UseStatus;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="customer")
public class CustomerEntity {      //거래처

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

    private int zipCode;    //우편번호

    private String address; //주소

    @Column(length = 13)
    private String phone;   //전화번호

    @Column(length = 13)
    private String fax;     //팩스번호

    private int chargeCode; //담당사원

    @Enumerated(EnumType.STRING)    //사업자단위 주사업장여부
    private CompanyUnitVATTaxationStatus companyUnitVATTaxationStatus;

    private int UnitReportingCustomerCode;  //단위신고거래처

    private int taxRegistrationCode;    //종사업장번호

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

    private int customerBankAccountNumber;  //거래처 은행계좌

    private String customerAccountHolder;   //거래처 예금주

    private int accountNumber;  //입금계좌

    private int myAccountTitle; //내 계정과목

    private int otherAccountTitle;  //상대 계정과목

    private int bankLine;   //여신한도액

    private int amountOfCollateral; //담보설정액

    @Enumerated(EnumType.STRING)
    private LiquorRetailStatus liquorRetailStatus;  //주류코드(소매)

    private int localTaxBillCode;   //지방세 법정동 코드

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_code")
    private CompanyEntity companyEntity;

    @OneToMany(mappedBy = "customer", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<Statement> statements = new ArrayList();
}
