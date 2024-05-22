import { defineStore } from 'pinia';
import type {Card} from "~/types/accounting/card";
import {useNuxtApp} from "#app";

export const useCardStore = defineStore('card', {
    state : () => ({
        card: [] as Card[],
        currentCard : null as Card | null,
    }),
    actions: {
        async fetchCard() {
            try {
                const response = await useNuxtApp().$api.get(`/register/card`);
                this.card = response.data;
            } catch (error: any) {
                console.error('카드 목록 조회 실패', error);
                throw new Error('카드 목록 조회 실패');
            }
        },
        async getCard(id: number) {
            try {
                const response = await useNuxtApp().$api.get(`/register/card/${id}`);
                this.currentCard = response.data;
            } catch (error: any) {
                console.error('카드 조회 실패', error);
                throw new Error('카드 조회 실패');
            }
        },
        async createCard(data: Card) {
            try {
                const response = await useNuxtApp().$api.post(`/register/card`, data);
                this.card.push(response.data);
                alert('카드 정보가 등록되었습니다');
            } catch (error: any) {
                alert('카드 등록 실패')
                console.error('카드 등록 실패', error);
                throw new Error('카드 등록 실패');
            }
        },
        async updateCard(data: Card) {
            try {
                const response = await useNuxtApp().$api.put(`/register/card/${data.id}`, data);
                const index = this.card.findIndex(card => card.id === data.id);
                if(index !== -1) {
                    this.card[index] = response.data;
                    alert('카드 정보가 수정되었습니다');
                } else {
                    this.card.push(response.data);
                    alert('카드 정보가 등록되었습니다')
                }
            } catch (error: any) {
                alert(`카드 정보 수정 실패`);
                console.error(`카드 정보 수정 실패`, error.message);
                throw new Error(`카드 정보 수정 실패`);
            }
        },
        async deleteCard(id: number) {
            try {
                const response = await useNuxtApp().$api.delete(`/register/card/${id}`);
                if (response.status === 200) {
                    await this.fetchCard();
                    alert('카드 정보가 삭제되었습니다');
                } else {
                    alert('카드 정보 삭제에 실패했습니다');
                    console.error('카드 정보 삭제 실패: ', response.status);
                    throw new Error('카드 정보 삭제 실패: ' + response.status);
                }
            } catch (error: any) {
                alert('카드 정보 삭제에 실패했습니다');
                console.error('카드 정보 삭제 실패', error.message);
                throw new Error('카드 정보 삭제 실패');
            }
        }
    }
});