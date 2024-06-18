import type {AccountTitle} from "~/types/accounting/account-title";
import type {Customer} from "~/types/accounting/customer";
import type {Company} from "~/types/accounting/company";
import type {Compendium} from "~/types/accounting/compendium";

export interface NormalDocument {
    id: number;
    code: number;
    company: Company;
    date: Date;     //일자
    division: string;    //구분
    accountTitle: AccountTitle;
    customer: Customer;
    compendium: Compendium;
    debit: number;
    credit: number;
}