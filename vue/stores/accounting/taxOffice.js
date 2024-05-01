import { defineStore } from 'pinia'
import axios from 'axios'

export const useTaxOfficeStore = defineStore('taxOffice', {
    state: () => ({
        taxOffices: []
    }),
    actions: {
        async fetchTaxOffices() {
            try {
                const response = await axios.get('http://localhost:8080/register/company-info');
                this.taxOffices = response.data;
            } catch (error) {
                console.error('Failed to fetch tax offices:', error);
            }
        }
    }
})
