import { defineStore } from 'pinia';
import { useNuxtApp } from '#app';
import type { NormalDocument } from '~/types/accounting/normal-document';

interface NormalDocumentState {
    normalDocument: NormalDocument[];
    currentNormalDocument: NormalDocument | null;
}

export const useNormalDocumentStore = defineStore('normalDocument', {
    state: (): NormalDocumentState => ({
        normalDocument: [],
        currentNormalDocument: null,
    }),
    actions: {
        async fetchNormalDocument(date: Date) {
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
        async deleteNormalDocumentByDateAndCode(date: string, code: number) {
            try {
                await useNuxtApp().$api.delete(`/register/normal-document/${date}/${code}`);
                await this.fetchNormalDocument(new Date(date));
                alert('행 삭제 성공');
            } catch (error: any) {
                alert('행 삭제 실패');
                console.error('행 삭제 실패', error);
            }
        },
        async deleteNormalDocumentByDate(date: string) {
            try {
                await useNuxtApp().$api.delete(`/register/normal-document/${date}`);
                await this.fetchNormalDocument(new Date(date));
                alert('전표 삭제 성공');
            } catch (error: any) {
                alert('전표 삭제 실패');
                console.error('전표 삭제 실패', error);
            }
        },
        setSelectedNormalDocument(normalDocument: NormalDocument) {
            this.currentNormalDocument = normalDocument;
        }
    }
});
