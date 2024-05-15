import { defineStore } from 'pinia'
import apiClient from "~/utils/baseUrl";
import type {TaxOffice} from "~/types/accounting/basicdata/taxOffice";

export const useTaxOfficeStore = defineStore('taxOffice', {
    state: () => ({
        taxOffice: [] as TaxOffice[],
        selectedTaxOffice: {} as TaxOffice | null
    }),
    actions: {
        async fetchTaxOffice() {
            try {
                const response = await apiClient().get('/register/company/tax-office');
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
