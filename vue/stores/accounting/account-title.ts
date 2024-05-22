import { defineStore } from 'pinia';
import type { AccountTitle } from "~/types/accounting/account-title";
import {useNuxtApp} from "#app";

export const useAccountTitleStore = defineStore('accountTitle', {
    state: () => ({
        accountTitle: [] as AccountTitle[],
        currentAccountTitle: null as AccountTitle | null,
    }),
    actions: {
        async fetchAccountTitle() {
            try {
                const response = await useNuxtApp().$api.get(`/register/account-title`);
                this.accountTitle = response.data;
            } catch (error: any) {
                console.error('계정과목 목록 조회 실패', error);
                throw new Error('계정과목 목록 조회 실패');
            }
        },
        async getAccountTitle(id: number) {
            try {
                const response = await useNuxtApp().$api.get(`/register/account-title/${id}`);
                this.currentAccountTitle = response.data;
            } catch (error: any) {
                console.error('계정과목 조회 실패', error);
                throw new Error('계정과목 조회 실패');
            }
        },
        async createAccountTitle(data: AccountTitle) {
            try {
                const response = await useNuxtApp().$api.post(`/register/account-title`, data);
                this.accountTitle.push(response.data);
                alert('계정과목 정보가 등록되었습니다');
            } catch (error: any) {
                alert('계정과목 등록 실패');
                console.error('계정과목 등록 실패', error);
                throw new Error('계정과목 등록 실패');
            }
        },
        async updateAccountTitle(data: AccountTitle) {
            try {
                const response = await useNuxtApp().$api.put(`/register/account-title/${data.id}`, data);
                const index = this.accountTitle.findIndex(accountTitle => accountTitle.id === data.id);
                if (index !== -1) {
                    this.accountTitle[index] = response.data;
                    alert('계정과목 정보가 수정되었습니다');
                } else {
                    this.accountTitle.push(response.data);
                    alert('계정과목 정보가 등록되었습니다');
                }
            } catch (error: any) {
                alert('계정과목 정보 수정 실패');
                console.error('계정과목 정보 수정 실패', error.message);
                throw new Error('계정과목 정보 수정 실패');
            }
        },
        async deleteAccountTitle(id: number) {
            try {
                const response = await useNuxtApp().$api.delete(`/register/account-title/${id}`);
                if (response.status === 200) {
                    await this.fetchAccountTitle();
                    alert('계정과목 정보가 삭제되었습니다');
                } else {
                    alert('계정과목 정보 삭제에 실패했습니다');
                    console.error('계정과목 정보 삭제 실패: ', response.status);
                    throw new Error('계정과목 정보 삭제 실패: ' + response.status);
                }
            } catch (error: any) {
                alert('계정과목 정보 삭제에 실패했습니다');
                console.error('계정과목 정보 삭제 실패', error.message);
                throw new Error('계정과목 정보 삭제 실패');
            }
        }
    }
});
