package lkm.starterproject.accounting.entity.company;

import jakarta.persistence.*;
import lkm.starterproject.accounting.entity.basic.LocalTax;
import lkm.starterproject.accounting.entity.basic.TaxOffice;
import lkm.starterproject.auth.entity.BaseEntity;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter @Setter @ToString
@Builder @NoArgsConstructor @AllArgsConstructor
@Table(name = "company")
public class Company extends BaseEntity {  //회사

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "company_code")
    private Long code;        //회사코드

    private String licenseType;    //사업자 유형

    private String headOfficeStatus;      //본점여부

    private String paymentHeadOfficeStatus;        //본점일괄납부여부

    @Column(nullable = false)
    private String name;        //회사명

    @Column(nullable = false, length = 12, unique = true)
    private String businessRegistrationNumber;      //사업자등록번호

    private String nameOfRepresentative;        //대표자명

    @Column(length = 14)
    private String corporationRegistrationNumber;       //법인등록번호

    private String postcode;   //우편번호

    private String roadAddress; //도로명주소

    private String jibunAddress;    //지번주소

    private String extraAddress;    //상세주소

    private String guideText;   //참고사항

    private String businessType;        //업태

    private String businessItem;        //업종
/*------------------------------------------------------------------------------------
* ----------------------------------회계 / 세무 정보------------------------------------
* ----------------------------------------------------------------------------------*/
    private Long fiscalYearClass;        //회계연도기수

    private LocalDate fiscalYearStart;  //회계연도시작일

    private LocalDate fiscalYearEnd;    //회계연도마감일

    private LocalDate privatePracticeDate;      //개업연월일

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tax_office_code", referencedColumnName = "tax_office_code")
    private TaxOffice taxOffice;        //사업장 세무서

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "local_tax_code", referencedColumnName = "local_tax_code")
    private LocalTax localTax;          //지방세 법정동

    private String finance;            //국세환급금 계좌

    private String corporationClassifyStatus;    //법인구분

    private String companyTypeStatus;    //회사종류구분

    private String businessScaleStatus;    //중소기업여부

    private String localTaxBillDivision;       //지방세 신고 구분

    @Column(length = 14)
    private String residentRegistrationNumber;      //대표자 주민번호
    /*------------------------------------------------------------------------------------
     * ----------------------------------기타 정보------------------------------------------
     * ----------------------------------------------------------------------------------*/
    @Column(length = 13)
    private String phone;   //사업장전화번호

    @Column(length = 13)
    private String fax;    //사업장팩스번호

    private String chargeName;  //담당자 이름

    private String chargeEmail;       //담당자 이메일
    
    private String note;    //비고
    /*------------------------------------------------------------------------------------
     * ----------------------------------Mapping------------------------------------------
     * ----------------------------------------------------------------------------------*/
}
