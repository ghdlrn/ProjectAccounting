package lkm.starterproject.accounting.dto.register;

import jakarta.validation.constraints.NotBlank;
import lkm.starterproject.accounting.constants.UseStatus;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountTitleDto {

    private Long id;

    private Long code;

    @NotBlank(message = "계정과목 이름은 필수 입력사항 입니다")
    private String name;    // 계정명

    private String balanceClassification;   //대차구분

    private String type;    //계정종류

    private List<CompendiumDto> compendiums;

    private UseStatus useStatus;    //사용구분

    private String properties;  //계정속성

    private String relatedWork; //관련업무

    private String cheque;   //수표

    private String balanceTallyClassification;  //잔액집계구분

    private String incomeExpenditureClassification; //수입지출구분

    private String incomeExpenditureParentAccount;  //수입지출 상위계정

    private String financialStatementName;  //재무제표 표시명

    private String financialStatementPrintingLocation;  //재무제표인쇄위치

    private String financialStatementAmountThickness;   //재무제표금액굵기

    private String financialStatementAmountParentheses; //재무제표금액괄호

    private String financialStatementHyperlinkTarget;   //재무제표 하이퍼링크대상

    private String financialStatementParentAccount; //재무제표  상위계정

}
