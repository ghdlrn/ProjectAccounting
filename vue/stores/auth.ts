interface User {
  id: number;
  username: string;
  email: string;
  returnUrl?: string;  // ? : 선택적 제공
}

import { defineStore } from 'pinia';  //pinia store 생성
import { useNuxtApp } from '#app';   //$fetch, $router사용

export const useAuthStore = defineStore('auth', {
  state: () => ({
    user: JSON.parse(localStorage.getItem('user') || '{}'), //user개체 검색 시도
    returnUrl: null
  }),
  actions: {
    async login(username: string, password: string) {   //사용자 아이디 비밀번호 $fetch로 Post요청
      const nuxtApp = useNuxtApp();
      try {
        const response = await nuxtApp.$fetch('/api/users/authenticate', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({ username, password })
        });
        const userData = await response.json();
        this.user = userData;
        localStorage.setItem('user', JSON.stringify(userData));
        nuxtApp.$router.push(this.returnUrl || '/dashboard/default');
      } catch (error) {
        throw new Error('로그인에 실패하셨습니다: ');
      }
    },
    logout() {    //user에서 사용자 데이터 제거 localStorage 사용자 로그아웃, $router로 로그인페이지 리디렉션
      this.user = null;
      localStorage.removeItem('user');
      const nuxtApp = useNuxtApp();
      nuxtApp.$router.push('/auth/login');
    }
  }
});
