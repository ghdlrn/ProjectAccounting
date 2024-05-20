import { useAuthStore } from '~/stores/auth/auth';

export default defineNuxtRouteMiddleware((to, from) => {
    const authStore = useAuthStore();

    if (!authStore.accessToken && to.name !== 'auth-login') {
        return navigateTo('/auth/login');
    }
});
