import { defineStore } from 'pinia';
import { useRoute, useRouter } from 'vue-router';

// auth.ts 파일 내 baseUrl 설정 부분
const baseUrl = import.meta.env.NUXT_ENV_API_URL ? `${import.meta.env.NUXT_ENV_API_URL}` : 'http://localhost:8080';
console.log("Base URL:", baseUrl); // Debugging the baseUrl

export const useAuthStore = defineStore({
  id: 'auth',
  state: () => ({
    // @ts-ignore
    user: JSON.parse(localStorage.getItem('user') || '{}'),
    returnUrl: null
  }),
  actions: {
    actions: {
      async login(email: string, password: string) {
        const loginUrl = `${baseUrl}/login`;
        try {
          const response = await fetch(loginUrl, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({email, password})
          });
          if (!response.ok) {
            throw new Error('Failed to login');
          }
          const user = await response.json();
          this.user = user;
          localStorage.setItem('user', JSON.stringify(user));
          const route = useRoute();
          const router = useRouter();
          router.push('/');
        } catch (error) {
          console.error('Login failed:', error);
          alert(`Login failed: ${error}`);
        }
      },
      logout() {
        this.user = null;
        localStorage.removeItem('user');
        useRouter().push('/logout');
      }
    }
  }
});
