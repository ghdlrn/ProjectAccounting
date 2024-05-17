import type {Company} from "~/types/accounting/company";
import type {Address} from "~/types/accounting/basicdata/address";
import type {LocalTax} from "~/types/accounting/basicdata/localTax";

export interface Finance {
    id: number;
    company: Company;
    name: string;    //은행(계좌)명
    accountNumber: string;  //계좌번호
    depositType: string; //예금종류
    note: string;    //비고
    useStatus: string;    //사용구분
    phone: string;   //전화번호
    fax: string;     //팩스번호
    address: Address;    //우편번호
    accountOpenDate: Date; //계좌개설일
    accountCloseDate: Date; //계좌해지일
    interestRate: number;    //이자율
    overdraftLimit: number; //당좌한도액
    overdraftDate: Date;    //당좌차월기일
    businessRegistrationNumber: string;  //사업자등록번호
    localTax: LocalTax;          //지방세 법정동
    homePage: string;    //홈페이지주소
}