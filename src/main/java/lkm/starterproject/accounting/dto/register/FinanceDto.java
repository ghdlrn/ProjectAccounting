package lkm.starterproject.accounting.dto.register;

import jakarta.persistence.*;
import lkm.starterproject.accounting.constants.UseStatus;
import lkm.starterproject.accounting.entity.basic.Address;
import lkm.starterproject.accounting.entity.basic.LocalTax;
import lkm.starterproject.accounting.entity.company.Company;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class FinanceDto {

    private Long id;
    private Company company;
    private String name;    //은행(계좌)명
    private String accountNumber;  //계좌번호
    private String note;    //비고
    private UseStatus useStatus;    //사용구분
    private String depositType; //예금종류
    private Double interestRate;    //이자율
    private Long overdraftLimit; //당좌한도액
    private LocalDate overdraftDate;    //당좌차월기일
    private String businessRegistrationNumber;  //사업자등록번호
    private Address address;    //우편번호
    private String phone;   //전화번호
    private String fax;     //팩스번호
    private String homePage;    //홈페이지주소
    private LocalDate accoountOpenDate; //계좌개설일
    private LocalDate accountCloseDate; //계좌해지일
    private LocalTax localTax;          //지방세 법정동
}
