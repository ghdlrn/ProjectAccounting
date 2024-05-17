package lkm.starterproject.accounting.dto.register;

import lkm.starterproject.accounting.constants.UseStatus;
import lkm.starterproject.accounting.dto.basic.AddressDto;
import lkm.starterproject.accounting.dto.basic.LocalTaxDto;
import lkm.starterproject.accounting.dto.company.CompanyDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class FinanceDto {

    private Long id;
    private CompanyDto company;
    private String name;    //은행(계좌)명
    private String accountNumber;  //계좌번호
    private String note;    //비고
    private UseStatus useStatus;    //사용구분
    private String depositType; //예금종류
    private Double interestRate;    //이자율
    private Long overdraftLimit; //당좌한도액
    private LocalDate overdraftDate;    //당좌차월기일
    private String businessRegistrationNumber;  //사업자등록번호
    private AddressDto address;    //우편번호
    private String phone;   //전화번호
    private String fax;     //팩스번호
    private String homePage;    //홈페이지주소
    private LocalDate accountOpenDate; //계좌개설일
    private LocalDate accountCloseDate; //계좌해지일
    private LocalTaxDto localTax;          //지방세 법정동
}
