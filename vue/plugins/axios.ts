import { defineNuxtPlugin } from '#app';
import axios from 'axios';
import { useAuthStore } from '~/stores/auth/auth';

export default defineNuxtPlugin(() => {
    const apiClient = axios.create({
        baseURL: 'http://localhost:8080',
        withCredentials: true,
        headers: {
            'Content-Type': 'application/json'
        }
    });

    apiClient.interceptors.response.use(
        response => response,
        async error => {
            const authStore = useAuthStore();

            if (error.response && error.response.status === 401) {
                try {
                    await authStore.refreshAccessToken();
                    error.config.headers['Authorization'] = `Bearer ${authStore.accessToken}`;
                    return apiClient.request(error.config);
                } catch (refreshError) {
                    await authStore.logout();
                    return Promise.reject(refreshError);
                }
            }
            return Promise.reject(error);
        }
    );

    return {
        provide: {
            apiClient
        }
    };
});
