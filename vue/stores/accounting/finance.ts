import { defineStore } from 'pinia';
import type {Finance} from "~/types/accounting/finance";

export const useFinanceStore = defineStore('finance', {
    state : () => ({
        finance: [] as Finance[],
        currentFinance : null as Finance | null,
    }),
    actions: {
        async fetchFinance() {
            try {
                const response = await useNuxtApp().$api.get(`/register/finance`);
                this.finance = response.data;
            } catch (error: any) {
                console.error('계좌 목록 조회 실패', error);
                throw new Error('계좌 목록 조회 실패');
            }
        },
        async getFinance(id: number) {
            try {
                const response = await useNuxtApp().$api.get(`/register/finance/${id}`);
                this.currentFinance = response.data;
            } catch (error: any) {
                console.error('계좌 조회 실패', error);
                throw new Error('계좌 조회 실패');
            }
        },
        async createFinance(data: Finance) {
            try {
                const response = await useNuxtApp().$api.post(`/register/finance`, data);
                this.finance.push(response.data);
                alert('계좌 정보가 등록되었습니다');
            } catch (error: any) {
                alert('계좌 등록 실패')
                console.error('계좌 등록 실패', error);
                throw new Error('계좌 등록 실패');
            }
        },
        async updateFinance(data: Finance) {
            try {
                const response = await useNuxtApp().$api.put(`/register/finance/${data.id}`, data);
                const index = this.finance.findIndex(finance => finance.id === data.id);
                if(index !== -1) {
                    this.finance[index] = response.data;
                    alert('계좌 정보가 수정되었습니다');
                } else {
                    this.finance.push(response.data);
                    alert('계좌 정보가 등록되었습니다')
                }
            } catch (error: any) {
                alert(`계좌 정보 수정 실패`);
                console.error(`계좌 정보 수정 실패`, error.message);
                throw new Error(`계좌 정보 수정 실패`);
            }
        },
        async deleteFinance(id: number) {
            try {
                const response = await useNuxtApp().$api.delete(`/register/finance/${id}`);
                if (response.status === 200) {
                    await this.fetchFinance();
                    alert('계좌 정보가 삭제되었습니다');
                } else {
                    alert('계좌 정보 삭제에 실패했습니다');
                    console.error('계좌 정보 삭제 실패: ', response.status);
                    throw new Error('계좌 정보 삭제 실패: ' + response.status);
                }
            } catch (error: any) {
                alert('계좌 정보 삭제에 실패했습니다');
                console.error('계좌 정보 삭제 실패', error.message);
                throw new Error('계좌 정보 삭제 실패');
            }
        },
        setSelectedFinance(data: Finance | null) {
            this.currentFinance = data;
        },
    }
});