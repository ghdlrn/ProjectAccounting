import { defineNuxtPlugin } from '#app';
import { useAuthStore } from '~/stores/auth/auth';

export default defineNuxtPlugin((nuxtApp) => {
    const authStore = useAuthStore();

    nuxtApp.hook('app:mounted', async () => {
        if (authStore.refreshToken) {
            await authStore.refreshAccessToken();
        }
    });
});
