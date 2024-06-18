import { defineStore } from 'pinia';
import type { Compendium } from "~/types/accounting/compendium";
import { useNuxtApp } from "#app";

export const useCompendiumStore = defineStore('compendium', {
    state: () => ({
        compendium: [] as Compendium[],
        currentCompendium: null as Compendium | null,
    }),
    actions: {
        async fetchCompendium(accountTitleId: number) {
            try {
                const response = await useNuxtApp().$api.get(`/register/compendium/account-title/${accountTitleId}`);
                this.compendium = response.data;
            } catch (error: any) {
                console.error('적요 목록 조회 실패', error);
                throw new Error('적요 목록 조회 실패');
            }
        },
        async getCompendium(id: number) {
            try {
                const response = await useNuxtApp().$api.get(`/register/compendium/${id}`);
                this.currentCompendium = response.data;
            } catch (error: any) {
                console.error('적요 조회 실패', error);
                throw new Error('적요 조회 실패');
            }
        },
        async createCompendium(accountTitleId: number, data: Partial<Compendium>) {
            try {
                const response = await useNuxtApp().$api.post(`/register/compendium/account-title/${accountTitleId}`, data);
                this.compendium.push(response.data);
                alert('적요 정보가 등록되었습니다');
            } catch (error: any) {
                alert('적요 등록 실패');
                console.error('적요 등록 실패', error);
                throw new Error('적요 등록 실패');
            }
        },
        async updateCompendium(data: Compendium) {
            try {
                const response = await useNuxtApp().$api.put(`/register/compendium/${data.id}`, data);
                const index = this.compendium.findIndex(compendium => compendium.id === data.id);
                if (index !== -1) {
                    this.compendium[index] = response.data;
                    alert('적요 정보가 수정되었습니다');
                } else {
                    this.compendium.push(response.data);
                    alert('적요 정보가 등록되었습니다');
                }
            } catch (error: any) {
                alert('적요 정보 수정 실패');
                console.error('적요 정보 수정 실패', error.message);
                throw new Error('적요 정보 수정 실패');
            }
        },
        async deleteCompendium(id: number) {
            try {
                const response = await useNuxtApp().$api.delete(`/register/compendium/${id}`);
                if (response.status === 200) {
                    this.compendium = this.compendium.filter(compendium => compendium.id !== id);
                    alert('적요 정보가 삭제되었습니다');
                } else {
                    alert('적요 정보 삭제에 실패했습니다');
                    console.error('적요 정보 삭제 실패: ', response.status);
                    throw new Error('적요 정보 삭제 실패: ' + response.status);
                }
            } catch (error: any) {
                alert('적요 정보 삭제에 실패했습니다');
                console.error('적요 정보 삭제 실패', error.message);
                throw new Error('적요 정보 삭제 실패');
            }
        },
        setSelectedCompendium(compendium: Compendium) {
            this.currentCompendium = compendium;
        }
    }
});
