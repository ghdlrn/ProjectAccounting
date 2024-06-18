import type {TaxOffice} from "~/types/accounting/basicdata/taxOffice";
import type {LocalTax} from "~/types/accounting/basicdata/localTax";
import type {Address} from "~/types/accounting/basicdata/address";

export interface Company {
  id: number;
  licenseType: string;
  headOfficeStatus: string;
  paymentHeadOfficeStatus: string;
  name: string;
  businessRegistrationNumber: string;
  nameOfRepresentative: string;
  corporationRegistrationNumber: string;
  address: Address;
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
  memberCompanyCode: number;
}