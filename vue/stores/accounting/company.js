import { defineStore } from 'pinia';
import axios from 'axios';

export const useCompanyStore = defineStore('company', {
    state: () => ({
        companies: [],
        currentCompany: null,
    }),
    actions: {
        async fetchCompanies() {
            const response = await axios.get('http://localhost:8080/register/company');
            this.companies = response.data;
        },
        async fetchCompany(code) {
            const response = await axios.get(`http://localhost:8080/register/company/${code}`);
            this.currentCompany = response.data;
        },
        async saveCompany(company) {
            const response = await axios.post('http://localhost:8080/register/company', company);
            await this.fetchCompanies(); // Refresh the list
            return response.data;
        },
        async updateCompany(company) {
            const response = await axios.put(`http://localhost:8080/register/company/${company.code}`, company);
            await this.fetchCompanies(); // Refresh the list
            return response.data;
        },
        async deleteCompany(code) {
            await axios.delete(`/register/company/${code}`);
            await this.fetchCompanies(); // Refresh the list
        },
    }
});
