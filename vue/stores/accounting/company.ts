import { defineStore } from 'pinia';
import axios from 'axios';

interface Address {
    code: number;
    postcode: string;
    roadAddress: string;
    jibunAddress: string;
    extraAddress: string;
    guideText: string;
}

interface TaxOffice {
    code: number;
    name: string;
    jurisdiction: string;
}

interface LocalTax {
    code: number;
    name: string;
}

interface Finance {
    code: number;
}

interface Company {
    code: number;
    licenseType: string;
    businessPersonType: string;
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
    finance: Finance;
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

export const useCompanyStore = defineStore('company', {
    state: () => ({
        companies: [] as Company[],
    }),
    actions: {
        async fetchCompanies() {
            try {
                const response = await axios.get('http://localhost:8080/register/company');
                this.companies = response.data;
            } catch (error: any) {
                console.error('Failed to fetch companies:', error.message);
                throw new Error('Failed to fetch companies');
            }
        },
        async createCompany(data: Company) {
            try {
                const response = await axios.post('http://localhost:8080/register/company', data);
                this.companies.push(response.data);
            } catch (error: any) {
                console.error('Failed to create company:', error.message);
                throw new Error('Failed to create company');
            }
        },
        async updateCompany(data: Partial<Company>) {
            try {
                const response = await axios.put(`http://localhost:8080/register/company/${data.code}`, data);
                const index = this.companies.findIndex(company => company.code === data.code);
                if (index !== -1) {
                    this.companies[index] = { ...this.companies[index], ...response.data };
                }
            } catch (error: any) {
                console.error('Failed to update company:', error.message);
                throw new Error('Failed to update company');
            }
        },
        async deleteCompany(code: number) {
            try {
                await axios.delete(`http://localhost:8080/register/company/${code}`);
                this.companies = this.companies.filter(company => company.code !== code);
            } catch (error: any) {
                console.error('Failed to delete company:', error.message);
                throw new Error('Failed to delete company');
            }
        }
    }
});
