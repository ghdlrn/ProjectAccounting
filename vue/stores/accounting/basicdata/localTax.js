import { defineStore } from 'pinia'
import axios from 'axios'

export const useLocalTaxStore = defineStore('localLax', {
    state: () => ({
        localTax: []
    }),
    actions: {
        async fetchLocalTax() {
            try {
                const response = await axios.get('http://localhost:8080/register/company/local-tax');
                this.localTax = response.data;
            } catch (error) {
                console.error('Failed to fetch tax offices:', error);
            }
        }
    }
})
