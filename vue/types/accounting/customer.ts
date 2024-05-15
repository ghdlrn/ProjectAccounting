import type {Company} from "~/types/accounting/company";
import type {LocalTax} from "~/types/accounting/basicdata/localTax";
import type {Address} from "~/types/accounting/basicdata/address";

export interface Customer {
    id: number;     //거래처 코드번호
    company: Company;
    name: string;   //거래처명
    registrationNumberType: string;     //등록번호 구분(사업자or주민or외국인 등록번호)
    registrationNumber: string; //사업자등록번호or주민번호or외국인번호 등 등록번호
    nameOfRepresentative: string;    //대표자명
    useStatus: string;
    businessType: string;
    businessItem: string;
    address: Address;
    phone: string;
    fax: string;
    unitReportingCustomerCode: string;
    tradeStartDate: Date;
    tradeEndDate: Date;
    note: string;
    customerChargeDepartment: string;
    customerChargePosition: string;
    customerChargeName: string;
    customerChargePhone: string;
    customerChargeEmail: string;
    homePage: string;
    customerAccountNumber: string;
    customerAccountHolder: string;
    bankLine: number;
    amountOfCollateral: number;
    localTax: LocalTax;
}