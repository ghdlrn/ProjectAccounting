package lkm.starterproject.accounting.dto.company;

import lkm.starterproject.accounting.entity.basic.LocalTax;
import lkm.starterproject.accounting.entity.register.Finance;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CompanyDto {

    private Long code;        //회사코드

    private String licenseType; //사업자 유형

    private HeadOfficeStatus headOfficeStatus;      //본점여부

    private PaymentHeadOfficeStatus paymentHeadOfficeStatus;        //본점일괄납부여부

    private String name;        //회사명

    private String businessRegistrationNumber;      //사업자등록번호

    private String nameOfRepresentative;        //대표자명

    private String corporationRegistrationNumber;       //법인등록번호

    private Integer postcode;   //우편번호

    private String roadAddress; //도로명주소

    private String jibunAddress;    //지번주소

    private String extraAddress;    //상세주소

    private String guideText;   //주소 참고 항목

    private String businessType;        //업태

    private String businessItem;        //업종
    /*---------------------------------------------------------------------
     * ----------------------회계/ 세무 정보-----------------------------------
     * --------------------------------------------------------------------*/
    private Integer fiscalYearClass;        //회계연도기수

    private LocalDate fiscalYearStart;  //회계연도시작일

    private LocalDate fiscalYearEnd;    //회계연도마감일

    private LocalDate privatePracticeDate;      //개업연월일

    private String taxOfficeName;    //사업장별 세무서

    private LocalTax localTax;  //지방세 법정동

    private Finance finance;      //국세환급금 계좌

    private String corporationClassify;    //법인구분

    private String companyType;    //회사종류구분

    private String businessScale;    //기업규모여부

    private String localTaxBillDivision;       //지방세구분

    private String residentRegistrationNumber;      //대표자 주민번호
    /*---------------------------------------------------------------------
     * ---------------------기타정보 정보-----------------------------------
     * --------------------------------------------------------------------*/
    private String phone;   //사업장전화번호

    private String fax;    //사업장팩스번호

    private String chargeName;   //담당자 이름

    private String chargeEmail;    //담당자 이메일

    private String note;    //비고
}
