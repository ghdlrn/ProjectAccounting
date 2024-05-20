// stores/auth.js
import { defineStore } from 'pinia';
import type { Member } from  "~/types/member";
import apiClient from "~/utils/baseUrl";


export const useAuthStore = defineStore('auth', {
  state: () => ({
    member: null as Member| null,
    accessToken: null as string | null,
    refreshToken: null as string | null,
  }),
  actions: {
    async signup(username: string, email: string, password: string) {
      try {
        await apiClient().post('auth/signup', {username, email, password});
      } catch (error: any) {
        console.error('Signup failed:', error);
        alert('회원가입 실패');
      }
    },
    async login(email: string, password: string) {
      try {
        const response = await apiClient().post('/login', { email, password }, {
          headers: {
            'Content-Type': 'application/json'
          }
        });
        this.accessToken = response.data.access_token;
        this.refreshToken = response.data.refresh_token;
        this.member = {
          username: response.data.username,
          email: response.data.email,
          role: response.data.role,
        };
        localStorage.setItem('accessToken', <string>this.accessToken);
        localStorage.setItem('refreshToken', <string>this.refreshToken);
        console.log('Logged in successfully!');
      } catch (error: any) {
        console.error('Login failed:', error.response);
        throw error;  // 에러를 다시 throw하여 호출한 곳에서 처리할 수 있도록 함
      }
    },
    async logout() {
      try {
        if (!this.accessToken) {
          throw new Error('No access token found');
        }
        console.log('Access Token:', this.accessToken);  // Debug log
        await apiClient().post('/logout', {}, {
          headers: {Authorization: `Bearer ${this.accessToken}`}
        });
        this.member = null;
        this.accessToken = null;
        this.refreshToken = null;
        localStorage.removeItem('accessToken');
        localStorage.removeItem('refreshToken');
      } catch (error: any) {
        console.error('Logout failed:', error.response);
        throw error;
      }
    },
    async refreshAccessToken() {
      try {
        const response = await apiClient().post('/reissue', {}, {
          headers: { Authorization: `Bearer ${this.refreshToken}` }
        });
        this.accessToken = response.data.access_token;
        localStorage.setItem('accessToken', <string>this.accessToken);
      } catch (error) {
        // Handle error
      }
    },
    loadUserFromLocalStorage() {
      this.accessToken = localStorage.getItem('accessToken');
      this.refreshToken = localStorage.getItem('refreshToken');
      if (this.accessToken) {
        // Optionally, fetch user data from the backend using the access token
        // this.user = fetchedUserData;
      }
    },
  }
});
