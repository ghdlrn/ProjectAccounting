import { defineStore } from 'pinia'
import axios from 'axios'
import type {TaxOffice} from "~/types/accounting/basicdata/taxOffice";

export const useTaxOfficeStore = defineStore('taxOffice', {
    state: () => ({
        taxOffice: [] as TaxOffice[],
        selectedTaxOffice: {} as TaxOffice | null
    }),
    actions: {
        async fetchTaxOffice() {
            try {
                const response = await axios.get('http://localhost:8080/register/company/tax-office');
                this.taxOffice = response.data;
            } catch (error) {
                console.error('Failed to fetch tax offices:', error);
            }
        },
        setSelectedTaxOffice(taxOffice: TaxOffice) {
            this.selectedTaxOffice = taxOffice;
        }
    }
})
