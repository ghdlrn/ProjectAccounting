import { defineStore } from 'pinia';
import { useNuxtApp } from '#app';
import type { Member } from '~/types/member';

export const useAuthStore = defineStore('auth', {
  state: () => ({
    member: null as Member | null,
    accessToken: null as string | null,
    refreshToken: null as string | null,
  }),
  actions: {
    async signup(username: string, email: string, password: string) {
      try {
        await useNuxtApp().$apiClient.post('auth/signup', { username, email, password });
        console.log('Sign up successful');
      } catch (error: any) {
        console.error('Signup failed:', error);
        alert('Sign up failed');
      }
    },
    async login(email: string, password: string) {
      try {
        const response = await useNuxtApp().$apiClient.post('/login', { email, password }, {
          headers: {
            'Content-Type': 'application/json'
          },
          withCredentials: true // Ensure cookies are sent
        });
        this.accessToken = response.data.access_token;
        this.refreshToken = response.data.refresh_token;
        this.member = {
          username: response.data.username,
          email: response.data.email,
          role: response.data.role,
        };
        localStorage.setItem('accessToken', this.accessToken as string);
        localStorage.setItem('refreshToken', this.refreshToken as string);
        console.log('Logged in successfully!');
      } catch (error: any) {
        console.error('Login failed:', error.response);
        throw error;
      }
    },

    async logout() {
      try {
        if (!this.accessToken) {
          throw new Error('No access token found');
        }
        console.log('Access Token:', this.accessToken);
        await useNuxtApp().$apiClient.post('/logout', {}, {
          headers: { Authorization: `Bearer ${this.accessToken}` },
          withCredentials: true // Ensure cookies are sent
        });
        this.member = null;
        this.accessToken = null;
        this.refreshToken = null;
        localStorage.removeItem('accessToken');
        localStorage.removeItem('refreshToken');
        console.log('Logged out successfully!');
      } catch (error: any) {
        console.error('Logout failed:', error.response);
        throw error;
      }
    },
    async refreshAccessToken() {
      try {
        const response = await useNuxtApp().$apiClient.post('/reissue', {}, {
          headers: { Authorization: `Bearer ${this.refreshToken}` },
          withCredentials: true // Ensure cookies are sent
        });
        this.accessToken = response.data.access_token;
        localStorage.setItem('accessToken', this.accessToken as string);
      } catch (error: any) {
        console.error('Token refresh failed:', error);
        await this.logout();
      }
    },
    loadUserFromLocalStorage() {
      this.accessToken = localStorage.getItem('accessToken');
      this.refreshToken = localStorage.getItem('refreshToken');
      if (this.accessToken && this.refreshToken) {
        this.member = {
          email: '',
          username: '',
          role: ''
        };
      }
    },
  }
});
