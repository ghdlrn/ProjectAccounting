package lkm.starterproject.accounting.dto.document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lkm.starterproject.accounting.dto.register.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder @NoArgsConstructor @AllArgsConstructor
public class NormalDocumentDto {

    private Long id;

    private Long code;

    @NotNull
    private LocalDate date;     //일자

    @NotBlank(message = "구분은 필수 입력사항 입니다")
    private String division;    //구분

    private AccountTitleDto accountTitle;  //계정과목

    private CustomerDto customer;  //거래처

    private FinanceDto finance;  //계좌

    private CardDto card;  //카드

    private CompendiumDto compendium;  //적요

    private Long debit; //차변

    private Long credit;    //대변
}
