package lkm.starterproject.accounting.entity.register;

import jakarta.persistence.*;
import lkm.starterproject.accounting.constants.LiquorRetailStatus;
import lkm.starterproject.accounting.constants.TradeStatus;
import lkm.starterproject.accounting.constants.UseStatus;
import lkm.starterproject.accounting.entity.basic.LocalTax;
import lkm.starterproject.accounting.entity.company.Company;
import lkm.starterproject.auth.entity.MemberCompany;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @ToString
@Builder @NoArgsConstructor @AllArgsConstructor
@Table(name = "customer")
public class Customer {      //거래처

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id")
    private Long id;        //거래처 코드번호

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(nullable = false)
    private String name;        //거래처명

    @Column(nullable = false)
    private String registrationNumberType;  //등록번호 구분(사업자or주민or외국인 등록번호)

    @Column(nullable = false, unique = true)
    private String registrationNumber;      //사업자등록번호or주민번호or외국인번호 등 등록번호

    private String nameOfRepresentative;    //대표자명

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UseStatus useStatus;        //사용여부

    private String businessType;    //업태

    private String businessItem;    //업종

    private String postcode;   //우편번호

    private String roadAddress; //도로명주소

    private String jibunAddress;    //지번주소

    private String extraAddress;    //상세주소

    private String guideText;   //참고사항

    @Column(length = 13)
    private String phone;   //전화번호

    @Column(length = 13)
    private String fax;     //팩스번호

    private Long unitReportingCustomerCode;  //단위신고거래처

    private Long taxRegistrationCode;    //종사업장번호

    private LocalDate tradeStartDate;   //거래시작일

    private LocalDate tradeEndDate;     //거래종료일

    private String note;    //비고

    private String customerChargeDepartment;    //가레처담당자부서

    private String customerChargePosition;  //거래처담당자 직급

    private String customerChargeName;      //거래처 담당자 이름

    private String customerChargePhone;     //거래처 담당자 폰번호

    private String customerChargeEmail;     //거래처 담당자 이메일

    private String homePage;    //거래처 홈페이지

    private String customerAccountNumber;  //거래처 은행계좌

    private String customerAccountHolder;   //거래처 예금주

    private Long bankLine;   //여신한도액

    private Long amountOfCollateral; //담보설정액

    @ManyToOne
    @JoinColumn(name = "local_tax_id")
    private LocalTax localTax;          //지방세 법정동
}
