import type {AccountTitle} from "~/types/accounting/account-title";

export interface Compendium {
    id: number;
    accountTitle: AccountTitle;
    code: number;   //현금적요 코드
    content: string; //현금적요내용
    note: string;    //현금적요비고

}