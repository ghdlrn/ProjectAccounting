import type {AccountTitle} from "~/types/accounting/account-title";
import type {Customer} from "~/types/accounting/customer";
import type {Company} from "~/types/accounting/company";
import type {Compendium} from "~/types/accounting/compendium";
import type {Finance} from "~/types/accounting/finance";
import type {Card} from "~/types/accounting/card";

export interface NormalDocument {
    id: number;
    code: number;
    company: Company;
    date: Date;     //일자
    division: string;    //구분
    accountTitle: AccountTitle;
    customer: Customer;
    finance: Finance;
    card: Card;
    compendium: Compendium;
    debit: number;
    credit: number;
}