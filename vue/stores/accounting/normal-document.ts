import { defineStore } from 'pinia';
import type { NormalDocument } from "~/types/accounting/normal-document";
import { useNuxtApp } from "#app";

export const useNormalDocumentStore = defineStore('normalDocument', {
    state: () => ({
        normalDocument: [] as NormalDocument[],
        currentNormalDocument: null as NormalDocument | null,
    }),
    actions: {
        async fetchNormalDocument(date: Date | null = null) {
            try {
                let response;
                if (date) {
                    response = await useNuxtApp().$api.get(`/register/normal-document`, {
                        params: { date }
                    });
                } else {
                    response = await useNuxtApp().$api.get(`/register/normal-document`);
                }
                this.normalDocument = response.data;
            } catch (error: any) {
                console.error('전표 목록 조회 실패', error);
                throw new Error('전표 목록 조회 실패');
            }
        },
        async createOrUpdateNormalDocument(data: NormalDocument[]) {
            try {
                const response = await useNuxtApp().$api.post(`/register/normal-document`, data);
                this.normalDocument = response.data;
                alert('전표 정보가 등록되었습니다');
            } catch (error: any) {
                alert('전표 등록 실패');
                console.error('전표 등록 실패', error);
                throw new Error('전표 등록 실패');
            }
        },
        async deleteNormalDocumentsByDate(date: Date) {
            try {
                const response = await useNuxtApp().$api.delete(`/register/normal-document`, {
                    params: { date: date.toISOString().split('T')[0] }
                });
                if (response.status === 200) {
                    await this.fetchNormalDocument();
                    alert('전표 정보가 삭제되었습니다');
                } else {
                    alert('전표 정보 삭제에 실패했습니다');
                    console.error('전표 정보 삭제 실패: ', response.status);
                    throw new Error('전표 정보 삭제 실패: ' + response.status);
                }
            } catch (error: any) {
                alert('전표 정보 삭제에 실패했습니다');
                console.error('전표 정보 삭제 실패', error.message);
                throw new Error('전표 정보 삭제 실패');
            }
        }
    }
});
