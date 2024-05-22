import type {AccountTitle} from "~/types/accounting/account-title";

export interface Compendium {
    id: number;
    accountTitle: AccountTitle;
    cashCode: number;   //현금적요 코드
    cashContent: string; //현금적요내용
    cashNote: string;    //현금적요비고
    replacementCode: number;   //대체적요 코드
    replacementContent: string; //대체적요내용
    replacementNote: string;    //대체적요비고
}