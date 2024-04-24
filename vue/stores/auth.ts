//이메일과 비밀번호를 백엔드로 보냄
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
      const response = await fetchWrapper.post(`${baseUrl}/login`, { email: username, password });
      const data = await response.json();

      if(response.ok) {
        const { access_token, refresh_token, role, email } = data;
        const user = { access_token, refresh_token, role, email };

        // Ensure the token is in 'Bearer {token}' format
        this.user = user;
        localStorage.setItem('user', JSON.stringify(user));
        return true;
      }
      else {
        // handle error
        console.error('Login failed:', data.message);
        return false;
      }
    },
    logout() {
      this.user = null;
      localStorage.removeItem('user');
    }
  }
});