package lkm.starterproject.entity;

import jakarta.persistence.*;
import lkm.starterproject.constants.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="company")
public class CompanyEntity {  //회사

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long code;        //회사코드

    @Column(nullable = false)
    private String name;        //회사명

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CompanyStatus companyStatus;        //회사종류(주식회사, 개인회사)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UseStatus useStatus;        //사용구분

    @Column(nullable = false)
    private int fiscalYearClass;        //회계연도기수

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate fiscalYearStart;  //회계연도시작일

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate fiscalYearEnd;    //회계연도마감일

    @Column(nullable = false, length = 12, unique = true)
    private String businessRegistrationNumber;      //사업자등록번호

    @Column(length = 14)
    private String corporationRegistrationNumber;       //법인등록번호

    @Column(nullable = false)
    private String nameOfRepresentative;        //대표자명

    @Column(length = 14)
    private String residentRegistrationNumber;      //대표자 주민번호

    @Enumerated(EnumType.STRING)
    private ForeignerStatus foreignerStatus;        //대표자 내,외국인구분

    private int zipCode;        //우편번호

    private String address;     //주소

    @Column(length = 13)
    private String phone;   //사업장전화번호

    @Column(length = 13)
    private String fax;    //사업장팩스번호

    private int mainIndustryCode;       //메인업종코드

    @Column(nullable = false)
    private String businessType;        //업태

    @Column(nullable = false)
    private String businessItem;        //업종

    private int businessTaxOfficeCode;  //사업장세무서코드

    private int localTaxBillCode;       //지방세 법정동 코드

    private int localTaxBillDivisionCode;       //지방세구분코드

    @Temporal(TemporalType.DATE)
    private LocalDate establishmentDate;        //설립연월일

    @Temporal(TemporalType.DATE)
    private LocalDate privatePracticeDate;      //개업연월일

    @Temporal(TemporalType.DATE)
    private LocalDate businessClosureDate;      //폐업연월일

    private int accountNumber;      //국세환급금 계좌

    @Enumerated(EnumType.STRING)
    private HeadOfficeStatus headOfficeStatus;      //본점여부

    @Enumerated(EnumType.STRING)
    private PaymentHeadOfficeStatus paymentHeadOfficeStatus;        //본점일괄납부여부

    private int headOfficeCode;     //본점코드

    @Enumerated(EnumType.STRING)
    private CorporationClassifyStatus corporationClassifyStatus;    //법인구분

    @Enumerated(EnumType.STRING)
    private BusinessScaleStatus businessScaleStatus;    //기업규모여부

    @Enumerated(EnumType.STRING)
    private CompanyTypeStatus companyTypeStatus;    //회사종류구분

    private String email;       //담당자 이메일

    @OneToMany(mappedBy = "company", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<CustomerEntity> customers = new ArrayList();

    @OneToMany(mappedBy = "company", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<Finance> finances = new ArrayList();

    @OneToMany(mappedBy = "company", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<Card> cards = new ArrayList();

    @OneToMany(mappedBy = "company", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<Statement> statements = new ArrayList();

}
