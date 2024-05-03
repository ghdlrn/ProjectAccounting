package lkm.starterproject.accounting.entity.company;

import jakarta.persistence.*;
import lkm.starterproject.accounting.constants.*;
import lkm.starterproject.accounting.entity.basic.Address;
import lkm.starterproject.accounting.entity.basic.LocalTax;
import lkm.starterproject.accounting.entity.basic.TaxOffice;
import lkm.starterproject.accounting.entity.register.Finance;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="company")
public class Company {  //회사

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "company_code")
    private Long code;        //회사코드

    private String licenseType; //사업자 유형

    private String headOfficeStatus;      //본점여부

    private String paymentHeadOfficeStatus;        //본점일괄납부여부

    @Column(nullable = false, length = 50)
    private String name;        //회사명

    @Column(nullable = false, length = 12, unique = true)
    private String businessRegistrationNumber;      //사업자등록번호

    @Column(nullable = false, length = 50)
    private String nameOfRepresentative;        //대표자명

    @Column(length = 14)
    private String corporationRegistrationNumber;       //법인등록번호

    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private Address address;  // Address entity reference

    @Column(length = 50)
    private String businessType;        //업태

    @Column(length = 50)
    private String businessItem;        //업종
    /*---------------------------------------------------------------------
     * ----------------------회계/ 세무 정보-----------------------------------
     * --------------------------------------------------------------------*/
    @Column(nullable = false)
    private Integer fiscalYearClass;        //회계연도기수

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate fiscalYearStart;  //회계연도시작일

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate fiscalYearEnd;    //회계연도마감일

    @Temporal(TemporalType.DATE)
    private LocalDate privatePracticeDate;      //개업연월일

    @OneToOne
    @JoinColumn(name = "taxoffice_code", referencedColumnName = "taxoffice_code")
    private TaxOffice taxOffice;    //사업장별 세무서
    
    @OneToOne
    @JoinColumn(name = "localtax_code", referencedColumnName = "localtax_code")
    private LocalTax localTax;  //지방세 법정동

    @OneToOne(mappedBy = "company")
    @JoinColumn(name = "finance_code", referencedColumnName = "finance_code")
    private Finance finance;      //국세환급금 계좌

    @Column(length = 50)
    private String corporationClassify;    //법인구분

    @Column(length = 50)
    private String companyType;    //회사종류구분

    @Column(length = 50)
    private String businessScale;    //기업규모여부

    @Column(length = 50)
    private String localTaxBillDivision;       //지방세구분

    @Column(length = 14)
    private String residentRegistrationNumber;      //대표자 주민번호
    /*---------------------------------------------------------------------
     * ---------------------기타정보 정보-----------------------------------
     * --------------------------------------------------------------------*/
    @Column(length = 13)
    private String phone;   //사업장전화번호

    @Column(length = 13)
    private String fax;    //사업장팩스번호

    @Column(length = 50)
    private String chargeName;   //담당자 이름

    @Column(length = 50)
    private String chargeEmail;    //담당자 이메일

    private String note;    //비고
}
