// stores/dateStore.js
import { defineStore } from 'pinia';

export const useDateStore = defineStore('dateStore', {
    state: () => ({
        s_date: new Date().toISOString().substr(0, 10),
        e_date: new Date().toISOString().substr(0, 10),
    }),
    actions: {
        setStartDate(date) {
            this.s_date = date;
        },
        setEndDate(date) {
            this.e_date = date;
        }
    }
});
