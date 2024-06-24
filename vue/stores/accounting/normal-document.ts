import { defineStore } from 'pinia';
import type { NormalDocument } from "~/types/accounting/normal-document";
import { useNuxtApp } from "#app";

export const useNormalDocumentStore = defineStore('normalDocument', {
    state: () => ({
        normalDocument: [] as NormalDocument[],
        currentNormalDocument: null as NormalDocument | null,
    }),
    actions: {
        async fetchNormalDocument() {
            try {
                const response = await useNuxtApp().$api.get(`/register/normal-document`);
                this.normalDocument = response.data;
            } catch (error: any) {
                console.error('일반 전표 목록 조회 실패', error);
                throw new Error('일반 전표 목록 조회 실패');
            }
        },
        async getNormalDocument(id: number) {
            try {
                const response = await useNuxtApp().$api.get(`/register/normal-document/${id}`);
                this.currentNormalDocument = response.data;
            } catch (error: any) {
                console.error('일반 전표 조회 실패', error);
                throw new Error('일반 전표 조회 실패');
            }
        },
        async createNormalDocument(data: NormalDocument) {
            try {
                const response = await useNuxtApp().$api.post(`/register/normal-document`, data);
                this.normalDocument.push(response.data);
                alert('일반 전표 정보가 등록되었습니다');
            } catch (error: any) {
                alert('일반 전표 등록 실패');
                console.error('일반 전표 등록 실패', error);
                throw new Error('일반 전표 등록 실패');
            }
        },
        async updateNormalDocument(data: NormalDocument) {
            try {
                const response = await useNuxtApp().$api.put(`/register/normal-document/${data.id}`, data);
                const index = this.normalDocument.findIndex(normalDocument => normalDocument.id === data.id);
                if (index !== -1) {
                    this.normalDocument[index] = response.data;
                    alert('일반 전표 정보가 수정되었습니다');
                } else {
                    this.normalDocument.push(response.data);
                    alert('일반 전표 정보가 등록되었습니다');
                }
            } catch (error: any) {
                alert('일반 전표 정보 수정 실패');
                console.error('일반 전표 정보 수정 실패', error.message);
                throw new Error('일반 전표 정보 수정 실패');
            }
        },
        async deleteNormalDocument(id: number) {
            try {
                const response = await useNuxtApp().$api.delete(`/register/normal-document/${id}`);
                if (response.status === 200) {
                    await this.fetchNormalDocument();
                    alert('일반 전표 정보가 삭제되었습니다');
                } else {
                    alert('일반 전표 정보 삭제에 실패했습니다');
                    console.error('일반 전표 정보 삭제 실패: ', response.status);
                    throw new Error('일반 전표 정보 삭제 실패: ' + response.status);
                }
            } catch (error: any) {
                alert('일반 전표 정보 삭제에 실패했습니다');
                console.error('일반 전표 정보 삭제 실패', error.message);
                throw new Error('일반 전표 정보 삭제 실패');
            }
        },
        setSelectedNormalDocument(normalDocument: NormalDocument) {
            this.currentNormalDocument = normalDocument;
        }
    }
});
