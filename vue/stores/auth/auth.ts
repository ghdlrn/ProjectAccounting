import { defineStore } from 'pinia';
import { useRouter } from 'vue-router';
import { useNuxtApp } from '#app';

export const useAuthStore = defineStore('auth', {
  state: () => ({
    member: JSON.parse(localStorage.getItem('member') || '{}'),
  }),
  actions: {
    async login(email: string, password: string) {
      try {
        const response = await useNuxtApp().$api.post('/login', { email, password });
        this.member = response.data;
        localStorage.setItem('member', JSON.stringify(this.member));
        const router = useRouter();
        await router.push('/');
      } catch (error: any) {
        console.error('Failed to login:', error);
      }
    },
    async logout() {
      await useNuxtApp().$api.post('/logout').then(() => {
        this.member = null;
        localStorage.removeItem('member');
        const router = useRouter();
        router.push('/');
      });
    },
    async refreshToken() {
      try {
        const response = await useNuxtApp().$api.post('/reissue');
        this.member.accessToken = response.data.accessToken;
        localStorage.setItem('member', JSON.stringify(this.member));
      } catch (error: any) {
        await this.logout();
      }
    },
    async loadUserFromLocalStorage() {
      const memberData = localStorage.getItem('member');
      if (memberData) {
        this.member = JSON.parse(memberData);
      }
    },
  }
});
