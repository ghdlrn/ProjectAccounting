//사용자 데이터 가져오기
import { defineStore } from 'pinia';

import { fetchWrapper } from '@/utils/helpers/fetch-wrapper';

const baseUrl = `http://localhost:8080`;

export const useUsersStore = defineStore({
    id: 'Authuser',
    state: () => ({
        users: null,
        isLoading: false,
        error: null
    }),
    actions: {
        async getAll() {
            this.isLoading = true;
            this.error = null;
            fetchWrapper
                .get(`${baseUrl}/users`)
                .then(users => {
                    this.users = users;
                    this.isLoading = false;
                })
                .catch(error => {
                    this.error = error;
                    this.isLoading = false;
                });
        }
    }
});