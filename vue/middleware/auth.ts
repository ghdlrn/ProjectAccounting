import { defineNuxtRouteMiddleware } from 'nuxt/app';
import { useAuthStore } from '@/stores/auth/auth';

export default defineNuxtRouteMiddleware((to, from) => {
    const authStore = useAuthStore();
    if (!authStore.member?.accessToken) {
        return navigateTo('/auth/login');
    }
});
