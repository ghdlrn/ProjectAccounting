import { defineStore } from 'pinia'
import type {TaxOffice} from "~/types/accounting/basicdata/taxOffice";
import {useNuxtApp} from "#app";

export const useTaxOfficeStore = defineStore('taxOffice', {
    state: () => ({
        taxOffice: [] as TaxOffice[],
        selectedTaxOffice: {} as TaxOffice | null
    }),
    actions: {
        async fetchTaxOffice() {
            try {
                const response =  await useNuxtApp().$api.get('/register/company/tax-office');
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
