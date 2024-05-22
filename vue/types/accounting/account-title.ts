import type {Compendium} from "~/types/accounting/compendium";

export interface AccountTitle {
    id: number;        //계정과목 코드
    code: number;
    name: string;    // 계정명
    balanceClassification: string;   //대차구분
    type: string;    //계정종류
    compendiums: Compendium;
    useStatus: string;    //사용구분
    properties: string;  //계정속성
    relatedWork: string; //관련업무
    cheque: string;   //수표
    balanceTallyClassification: string;  //잔액집계구분
    incomeExpenditureClassification: string; //수입지출구분
    incomeExpenditureParentAccount: string;  //수입지출 상위계정
    financialStatementName: string;  //재무제표 표시명
    financialStatementPrintingLocation: string;  //재무제표인쇄위치
    financialStatementAmountThickness: string;   //재무제표금액굵기
    financialStatementAmountParentheses: string; //재무제표금액괄호
    financialStatementHyperlinkTarget: string;   //재무제표 하이퍼링크대상
    financialStatementParentAccount: string; //재무제표  상위계정

}