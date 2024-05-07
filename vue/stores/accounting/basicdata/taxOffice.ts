import { defineStore } from 'pinia'
import axios from 'axios'

interface TaxOffice {
    code: number;
    name: string;
    jurisdiction: string;
}

export const useTaxOfficeStore = defineStore('taxOffice', {
    state: () => ({
        taxOffices: [] as TaxOffice[],
    }),
    actions: {
        async fetchTaxOffices() {
            try {
                const response = await axios.get('http://localhost:8080/register/company/tax-office');
                this.taxOffices = response.data;
            } catch (error) {
                console.error('Failed to fetch tax offices:', error);
            }
        }
    }
})
