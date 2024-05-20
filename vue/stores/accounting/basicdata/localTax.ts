import { defineStore } from 'pinia'
import type {LocalTax} from "~/types/accounting/basicdata/localTax";
import {useNuxtApp} from "#app";

export const useLocalTaxStore = defineStore('localTax', {
    state: () => ({
        localTax: [] as LocalTax[],
        selectedLocalTax: {} as LocalTax | null
    }),
    actions: {
        async fetchLocalTax() {
            try {
                const response =  await useNuxtApp().$api.get('/register/company/local-tax');
                this.localTax = response.data;
            } catch (error) {
                console.error('Failed to fetch tax offices:', error);
            }
        },
        setSelectedLocalTax(localTax: LocalTax) {
            this.selectedLocalTax = localTax;
        }
    }
})
