import axios from 'axios';
import { defineNuxtPlugin } from '#app';
import { useAuthStore } from '~/stores/auth/auth';

export default defineNuxtPlugin((nuxtApp) => {
    const api = axios.create({
        baseURL: process.env.VITE_API_URL || 'http://localhost:8080',
        withCredentials: true
    });

    api.interceptors.request.use(
        (config) => {
            const authStore = useAuthStore();
            if (authStore.member.access_token) {
                config.headers['Authorization'] = `Bearer ${authStore.member.access_token}`;
            }
            return config;
        },
        (error) => Promise.reject(error)
    );

    api.interceptors.response.use(
        (response) => response,
        async (error) => {
            const authStore = useAuthStore();
            if (error.response?.status === 401) {
                await authStore.refreshToken();
                return api(error.config);
            }
            return Promise.reject(error);
        }
    );

    return {
        provide: {
            api
        }
    };
});
