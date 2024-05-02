import { defineStore } from 'pinia';
import axios from 'axios';

import type { CustomerStateProps } from '~/types/accounting/company';

export const useCustomers = defineStore({
  id: 'customers',
  state: (): CustomerStateProps => ({
    customers: [],

  }),
  getters: {
    getCustomers(state) {
      return state.customers;
    },
  },
  actions: {
    async fetchCustomers() {
      try {
        const data = await axios.get('/api/data/customers');
        this.customers = data.data;
      } catch (error) {
        alert(error);
      }
    },

    deleteCustomer(itemId: string) {
      this.customers = this.customers.filter((object) => {
        return object.name !== itemId;
      });
    },
  }
});
