package lkm.starterproject.accounting.dto;

import java.time.LocalDate;

public class CompanyDto {

    private Long code;  // 회사코드
    private String businessPersonType;  // 사업자 유형
    private String headOfficeStatus;  // 본점여부
    private String paymentHeadOfficeStatus;  // 본점일괄납부여부
    private String name;  // 회사명
    private String businessRegistrationNumber;  // 사업자등록번호
    private String nameOfRepresentative;  // 대표자명
    private String corporationRegistrationNumber;  // 법인등록번호
    private String businessType;  // 업태
    private String businessItem;  // 업종
    private Long fiscalYearClass;  // 회계연도기수
    private LocalDate fiscalYearStart;  // 회계연도시작일
    private LocalDate fiscalYearEnd;  // 회계연도마감일
    private LocalDate privatePracticeDate;  // 개업연월일
    private String taxOfficeCode;  // 사업장 세무서 코드
    private String localTaxCode;  // 지방세 법정동 코드
    private String financeCode;  // 국세환급금 계좌 코드
    private String corporationClassifyStatus;  // 법인구분
    private String companyTypeStatus;  // 회사종류구분
    private String businessScaleStatus;  // 중소기업여부
    private String companyStatus;  // 회사종류(주식회사, 개인회사)
    private String localTaxBillDivision;  // 지방세 신고 구분
    private String residentRegistrationNumber;  // 대표자 주민번호
    private String phone;  // 사업장전화번호
    private String fax;  // 사업장팩스번호
    private String chargeName;  // 담당자 이름
    private String chargeEmail;  // 담당자 이메일
    private String note;  // 비고

}
