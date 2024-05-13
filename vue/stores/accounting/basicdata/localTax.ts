import { defineStore } from 'pinia'
import axios from 'axios'

interface LocalTax {
    id: number;
    name: string;
}

export const useLocalTaxStore = defineStore('localTax', {
    state: () => ({
        localTax: [] as LocalTax[],
        selectedLocalTax: {} as LocalTax | null
    }),
    actions: {
        async fetchLocalTax() {
            try {
                const response = await axios.get('http://localhost:8080/register/company/local-tax');
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
