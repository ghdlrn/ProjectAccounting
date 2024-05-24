import axios from 'axios';
import { defineNuxtPlugin } from '#app';
import { useAuthStore } from '~/stores/auth/auth';

export default defineNuxtPlugin((nuxtApp) => {
    const api = axios.create({
        baseURL: process.env.VITE_API_URL || 'http://localhost:8080',
        withCredentials: true
    });

    const authStore = useAuthStore();
    authStore.loadUserFromLocalStorage(); // 초기 상태 로드

    api.interceptors.request.use((config) => {
            const authStore = useAuthStore();
            if (authStore.member.access_token) {
                config.headers['Authorization'] = `Bearer ${authStore.member.access_token}`;
            }
            return config;
        },
        (error) => Promise.reject(error)
    );

    api.interceptors.response.use((response) => response, async (error) => {
        const authStore = useAuthStore();
        if (error.response?.status === 401 && !error.config._retry) {
            error.config._retry = true;
            await authStore.refreshToken();
            error.config.headers['Authorization'] = `Bearer ${authStore.member.access_token}`;
            return api(error.config);
        }
        return Promise.reject(error);
    });

    return {
        provide: {
            api
        }
    };
});
