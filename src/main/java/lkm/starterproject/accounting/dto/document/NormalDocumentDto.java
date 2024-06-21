package lkm.starterproject.accounting.dto.document;

import jakarta.validation.constraints.NotBlank;
import lkm.starterproject.accounting.entity.register.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NormalDocumentDto {

    private Long id;

    private Long code;

    @NotBlank(message = "날짜는 필수 입력사항 입니다")
    private LocalDate date;     //일자

    @NotBlank(message = "구분은 필수 입력사항 입니다")
    private String division;    //구분

    @NotBlank(message = "계정과목은 필수 입력사항 입니다")
    private AccountTitle accountTitle;  //계정과목

    private Customer customer;  //거래처

    private Finance finance;  //계좌

    private Card card;  //카드

    private Compendium compendium;  //적요

    private Long debit; //차변

    private Long credit;    //대변
}
