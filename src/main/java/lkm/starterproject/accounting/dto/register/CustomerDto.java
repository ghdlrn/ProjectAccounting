package lkm.starterproject.accounting.dto.register;

import jakarta.validation.constraints.NotBlank;
import lkm.starterproject.accounting.constants.UseStatus;
import lkm.starterproject.accounting.dto.basic.AddressDto;
import lkm.starterproject.accounting.dto.basic.LocalTaxDto;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    private Long id;

    private Long code;  //개인 코드

    @NotBlank(message = "거래처명은 필수 입력사항 입니다")
    private String name;        //거래처명

    @NotBlank(message = "해당 구분은 필수 입력사항 입니다")
    private String registrationNumberType;  //등록번호 구분(사업자or주민or외국인 등록번호)

    @NotBlank(message = "등록번호는 필수 입력사항 입니다")
    private String registrationNumber;      //사업자등록번호or주민번호or외국인번호 등 등록번호

    private String nameOfRepresentative;    //대표자명

    private UseStatus useStatus;        //사용여부

    private String businessType;    //업태

    private String businessItem;    //업종

    private AddressDto address;

    private String phone;   //전화번호

    private String fax;     //팩스번호

    private String unitReportingCustomerCode;  //단위신고거래처

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

    private LocalTaxDto localTax;          //지방세 법정동

}
