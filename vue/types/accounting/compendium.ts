import type {AccountTitle} from "~/types/accounting/account-title";

export interface Compendium {
    id: number;
    accountTitle: AccountTitle;
    code: number;   //적요 코드
    content: string; //적요내용
    note: string;    //적요비고

}