import { defineStore } from 'pinia';
import axios from 'axios';

interface companies {
    licenseType: string;
    headOfficeStatus: string;
    paymentHeadOfficeStatus: string;
    name: string;
    businessRegistrationNumber: string;
    nameOfRepresentative: string;
    corporationRegistrationNumber: string;
    businessType: string;
    businessItem: string;
    fiscalYearClass: string;
    fiscalYearStart: string;
    fiscalYearEnd: string;
    privatePracticeDate: string;
    taxOfficeName: string;
    localTaxName: string;
    accountNumber: string;
    corporationClassifyStatus: string;
    businessScaleStatus: string;
    companyTypeStatus: string;
    localTaxBillDivisionCode: string;
    residentRegistrationNumber: string;
    phone: string;
    fax: string;
    chargeName: string;
    chargeEmail: string;
    note: string;
    postcode: string;
    roadAddress: string;
    jibunAddress: string;
    extraAddress: string;
    guideText: string;
}


export const useCompanyStore = defineStore('company', {
    state: () => ({
        companies: {} as companies,
    }),
    actions: {
        async createCompany(data: companies) {
            try {
                const response = await axios.post('http://localhost:8080/register/company', data);
                this.companies = response.data;
            } catch (error) {
                console.error('Failed to create companies:', error);
            }
        },
        async fetchCompany() {
            try {
                const response = await axios.get('http://localhost:8080/register/company');
                this.companies = response.data;
            } catch (error) {
                console.error('Failed to fetch companies:', error);
            }
        },
        async updateCompany(data: Partial<companies>) {
            try {
                const response = await axios.put('http://localhost:8080/register/company', data);
                this.companies = response.data;
            } catch (error) {
                console.error('Failed to update companies:', error);
            }
        },
        async deleteCompany() {
            try {
                await axios.delete('http://localhost:8080/register/company');
                this.companies = {} as companies;
            } catch (error) {
                console.error('Failed to delete companies:', error);
            }
        }
    }
});
