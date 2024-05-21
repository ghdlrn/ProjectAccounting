import { defineStore } from 'pinia';
import { useNuxtApp } from '#app';

export const useAuthStore = defineStore('auth', {
  state: () => ({
    member: JSON.parse(localStorage.getItem('member') || '{}'),
  }),
  actions: {
    async signup(username: string, email: string, password: string)  {
      try {
        const response = await useNuxtApp().$api.post('/auth/signup', {username, email, password});
        alert('Sign up successful');
        const router = useRouter();
        await router.push('/auth/login');
      } catch (error: any) {
        console.error('Failed to signup:', error);
      }
    },
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
      try {
        await useNuxtApp().$api.post('/logout');
        this.member = null;
        localStorage.removeItem('member');
        const router = useRouter();
        await router.push('/');
      } catch (error: any) {
        console.error('Failed to logout:', error);
      }
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
