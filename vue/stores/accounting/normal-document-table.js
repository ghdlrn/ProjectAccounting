import { defineStore } from 'pinia';
import axios from 'axios';

export const useTableStore = defineStore('table', {
    state: () => ({
        rows: [{ id: Date.now() }], // 기본적으로 빈 행 하나가 존재하도록 설정
    }),
    actions: {
        async fetchRows() {
            try {
                const response = await axios.get('/api/table-data');
                this.rows = response.data.length ? [...response.data, { id: Date.now() }] : [{ id: Date.now() }];
            } catch (error) {
                console.error('Failed to fetch rows:', error);
            }
        },
        addRow() {
            this.rows.push({ id: Date.now() });
        },
        removeRow(index) {
            this.rows.splice(index, 1);
            if (this.rows.length === 0) {
                this.addRow(); // 최소 한 줄은 유지
            }
        },
        async saveRows() {
            try {
                const response = await axios.post('/api/save-table-data', this.rows);
                console.log('Rows saved:', response.data);
            } catch (error) {
                console.error('Failed to save rows:', error);
            }
        },
    },
});
