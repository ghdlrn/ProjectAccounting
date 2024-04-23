import { defineStore } from 'pinia';
import { fetchWrapper } from '@/utils/helpers/fetch-wrapper';

const baseUrl = process.env.NUXT_ENV_API_URL || 'http://localhost:8080';

export const useAuthStore = defineStore({
  id: 'auth',
  state: () => ({
    user: JSON.parse(localStorage.getItem('user') || '{}'),
    returnUrl: null
  }),
  actions: {
    async login(username: string, password: string) {
      try {
        const user = await fetchWrapper.post(`${baseUrl}/login`, { username, password });
        this.user = user;
        localStorage.setItem('user', JSON.stringify(user));
        return true;
      } catch (error) {
        console.error('Login failed:', error);
        return false;
      }
    },
    logout() {
      this.user = null;
      localStorage.removeItem('user');
    }
  }
});
