import { defineStore } from 'pinia';
import axios from "axios";

const baseURL = axios.create({
  baseURL: process.env.VITE_API_URL || 'http://localhost:8080',
  withCredentials: true
});

export const useAuthStore = defineStore('auth', {
  state: () => ({
    member: JSON.parse(localStorage.getItem('member') || '{}'),
  }),
  actions: {
    async signup(username: string, email: string, password: string)  {
      try {
        const response = await baseURL.post('/auth/signup', {username, email, password});
        alert('회원가입 성공');
        const router = useRouter();
        await router.push('/auth/login');
      } catch (error: any) {
        console.error('Failed to signup:', error);
      }
    },
    async login(email: string, password: string) {
      try {
        const response = await baseURL.post('/login', { email, password });
        this.member = response.data;
        localStorage.setItem('member', JSON.stringify(this.member));
        const router = useRouter();
        await router.push('/');
      } catch (error: any) {
        alert('로그인 실패');
        console.error('Failed to login:', error);
      }
    },
    async logout() {
      try {
        await baseURL.post('/logout');
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
        const response = await baseURL.post('/reissue');
        this.member.access_token = response.data.access_token;
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
