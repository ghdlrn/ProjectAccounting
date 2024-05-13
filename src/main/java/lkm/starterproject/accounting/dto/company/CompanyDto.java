package lkm.starterproject.accounting.dto.company;

import lkm.starterproject.accounting.dto.basic.LocalTaxDto;
import lkm.starterproject.accounting.dto.basic.TaxOfficeDto;
import lkm.starterproject.auth.dto.MemberCompanyDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class CompanyDto {

    private Long id;  // 회사코드
    private String licenseType;  // 사업자 유형
    private String headOfficeStatus;  // 본점여부
    private String paymentHeadOfficeStatus;  // 본점일괄납부여부
    private String name;  // 회사명
    private String businessRegistrationNumber;  // 사업자등록번호
    private String nameOfRepresentative;  // 대표자명
    private String corporationRegistrationNumber;  // 법인등록번호
    private String postcode;   //우편번호
    private String roadAddress; //도로명주소
    private String jibunAddress;    //지번주소
    private String extraAddress;    //상세주소
    private String guideText;   //참고사항
    private String businessType;  // 업태
    private String businessItem;  // 업종
    private Long fiscalYearClass;  // 회계연도기수
    private LocalDate fiscalYearStart;  // 회계연도시작일
    private LocalDate fiscalYearEnd;  // 회계연도마감일
    private LocalDate privatePracticeDate;  // 개업연월일
    private TaxOfficeDto taxOffice;  // 사업장 세무서 코드
    private LocalTaxDto localTax;  // 지방세 법정동 코드
    private String finance;  // 국세환급금 계좌 코드
    private String corporationClassifyStatus;  // 법인구분
    private String companyTypeStatus;  // 회사종류구분
    private String businessScaleStatus;  // 중소기업여부
    private String localTaxBillDivision;  // 지방세 신고 구분
    private String residentRegistrationNumber;  // 대표자 주민번호
    private String phone;  // 사업장전화번호
    private String fax;  // 사업장팩스번호
    private String chargeName;  // 담당자 이름
    private String chargeEmail;  // 담당자 이메일
    private String note;  // 비고
    private List<MemberCompanyDto> memberCompanies;
}
