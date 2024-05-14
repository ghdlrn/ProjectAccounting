import type {TaxOffice} from "~/types/accounting/basicdata/taxOffice";
import type {LocalTax} from "~/types/accounting/basicdata/localTax";

export interface Company {
  id: number;
  licenseType: string;
  headOfficeStatus: string;
  paymentHeadOfficeStatus: string;
  name: string;
  businessRegistrationNumber: string;
  nameOfRepresentative: string;
  corporationRegistrationNumber: string;
  postcode: string;   //우편번호
  roadAddress: string; //도로명주소
  jibunAddress: string;    //지번주소
  extraAddress: string;    //상세주소
  guideText: string;   //참고사항
  businessType: string;
  businessItem: string;
  fiscalYearClass: number;
  fiscalYearStart: Date;
  fiscalYearEnd: Date;
  privatePracticeDate: Date;
  taxOffice: TaxOffice;
  localTax: LocalTax;
  finance: string;
  corporationClassifyStatus: string;
  companyTypeStatus: string;
  businessScaleStatus: string;
  companyStatus: string;
  localTaxBillDivision: string;
  residentRegistrationNumber: string;
  phone: string;
  fax: string;
  chargeName: string;
  chargeEmail: string;
  note: string;
}