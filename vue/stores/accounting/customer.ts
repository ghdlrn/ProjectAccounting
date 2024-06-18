import { defineStore } from 'pinia';
import type { Customer } from "~/types/accounting/customer";
import { useNuxtApp } from "#app";
import type {TaxOffice} from "~/types/accounting/basicdata/taxOffice";

export const useCustomerStore = defineStore('customer', {
    state: () => ({
        customer: [] as Customer[],
        currentCustomer: null as Customer | null,
    }),
    actions: {
        async fetchCustomer() {
            try {
                const response = await useNuxtApp().$api.get(`/register/customer`);
                this.customer = response.data;
            } catch (error: any) {
                console.error('거래처 목록 조회 실패', error);
                throw new Error('거래처 목록 조회 실패');
            }
        },
        async getCustomer(id: number) {
            try {
                const response = await useNuxtApp().$api.get(`/register/customer/${id}`);
                this.currentCustomer = response.data;
            } catch (error: any) {
                console.error('거래처 조회 실패', error);
                throw new Error('거래처 조회 실패');
            }
        },
        async createCustomer(data: Customer) {
            try {
                const response = await useNuxtApp().$api.post(`/register/customer`, data);
                this.customer.push(response.data);
                alert('거래처 정보가 등록되었습니다');
            } catch (error: any) {
                alert('거래처 등록 실패');
                console.error('거래처 등록 실패', error);
                throw new Error('거래처 등록 실패');
            }
        },
        async updateCustomer(data: Customer) {
            try {
                const response = await useNuxtApp().$api.put(`/register/customer/${data.id}`, data);
                const index = this.customer.findIndex(customer => customer.id === data.id);
                if (index !== -1) {
                    this.customer[index] = response.data;
                    alert('거래처 정보가 수정되었습니다');
                } else {
                    this.customer.push(response.data);
                    alert('거래처 정보가 등록되었습니다');
                }
            } catch (error: any) {
                alert('거래처 정보 수정 실패');
                console.error('거래처 정보 수정 실패', error.message);
                throw new Error('거래처 정보 수정 실패');
            }
        },
        async deleteCustomer(id: number) {
            try {
                const response = await useNuxtApp().$api.delete(`/register/customer/${id}`);
                if (response.status === 200) {
                    await this.fetchCustomer();
                    alert('거래처 정보가 삭제되었습니다');
                } else {
                    alert('거래처 정보 삭제에 실패했습니다');
                    console.error('거래처 정보 삭제 실패: ', response.status);
                    throw new Error('거래처 정보 삭제 실패: ' + response.status);
                }
            } catch (error: any) {
                alert('거래처 정보 삭제에 실패했습니다');
                console.error('거래처 정보 삭제 실패', error.message);
                throw new Error('거래처 정보 삭제 실패');
            }
        },
        setSelectedCustomer(customer: Customer) {
            this.currentCustomer = customer;
        }
    }
});
