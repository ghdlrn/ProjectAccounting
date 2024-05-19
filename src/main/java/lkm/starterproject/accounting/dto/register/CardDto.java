package lkm.starterproject.accounting.dto.register;

import jakarta.validation.constraints.NotBlank;
import lkm.starterproject.accounting.constants.UseStatus;
import lkm.starterproject.accounting.dto.basic.AddressDto;
import lkm.starterproject.accounting.dto.company.CompanyDto;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {

    private Long id;

    @NotBlank(message = "카드사명은 필수 입력사항 입니다")
    private String name;    //카드(사)명

    @NotBlank(message = "카드번호는 필수 입력사항 입니다")
    private String cardNumber;  //카드번호
    private String note;    //비고
    private String division;    //거래구분
    private UseStatus useStatus;    //사용구분
    private FinanceDto finance;
    private String phone;   //전화번호
    private String fax;     //팩스번호
    private AddressDto address;    //주소
    private String registrationNumber;  //사업자등록번호
    private String homePage;        //홈페이지주소
    private LocalDate contractStartDate;    //계약시작일
    private LocalDate contractEndDate;       //계약종료일
    private Double commission;      //수수료
    private LocalDate expirationStartDate;  //유효기간 시작일
    private LocalDate expirationEndDate;     //유효기간 종료일
    private String chargeName;   //담당자명
    private LocalDate accountDay;   //결제일
}
