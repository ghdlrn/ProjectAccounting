// stores/auth.js
import { defineStore } from 'pinia';
import apiClient from "~/utils/baseUrl";

export const useAuthStore = defineStore('auth', {
  state: () => ({
    user: null,  // 사용자 정보 저장
    accessToken: null  // 접근 토큰 저장
  }),
  actions: {
    async login(email, password) {
      try {
        const response = await apiClient().post('/login', {
          email: email,
          password: password
        }, {
          headers: {
            'Content-Type': 'application/json'
          }
        });
        // 로그인 성공 시, 사용자 정보 및 토큰 저장
        this.user = response.data.user;
        this.accessToken = response.data.access_token;
        localStorage.setItem('accessToken', this.accessToken);  // 토큰을 localStorage에 저장
        console.log('Logged in successfully!');
      } catch (error) {
        console.error('Login failed:', error.response);
        throw error;  // 에러를 다시 throw하여 호출한 곳에서 처리할 수 있도록 함
      }
    },
    logout() {
      this.user = null;
      this.accessToken = null;
      localStorage.removeItem('accessToken');  // 로그아웃 시 localStorage에서 토큰 제거
    }
  }
});
