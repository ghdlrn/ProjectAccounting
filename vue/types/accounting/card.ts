import type {Company} from "~/types/accounting/company";
import type {Address} from "~/types/accounting/basicdata/address";
import type {Finance} from "~/types/accounting/finance";

export interface Card {
    id: number;
    company: Company;
    name: string;    //카드사 명
    cardNumber: string;  //카드(가맹점)번호
    note: string;    //비고
    division: string; //매입, 매출 구분
    useStatus: string;    //사용구분
    finance: Finance;   //결제/입금 계좌
    phone: string;   //전화번호
    fax: string;     //팩스번호
    address: Address;    //주소
    registrationNumber: string;  //등록번호
    homePage: string;    //홈페이지주소
    /*매출*/
    contractStartDate: Date; //계약기간 시작일
    contractEndDate: Date; //계약기간 종료일
    commission: number;    //수수료
    /*매입*/
    expirationStartDate: Date;  //유효기간 시작일
    expirationEndDate: Date; //유효기간 종료일
    chargeName: String; //담당자이름
    accountDay: Date; //결제일
}
