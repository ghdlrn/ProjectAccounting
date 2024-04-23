import { defineStore } from 'pinia';
import { useRoute, useRouter } from 'vue-router';

const baseUrl = `${import.meta.env.VITE_API_URL}/users`;

export const useAuthStore = defineStore({
  id: 'auth',
  state: () => ({
    // initialize state from local storage to enable user to stay logged in
    /* eslint-disable-next-line @typescript-eslint/ban-ts-comment */
    // @ts-ignore
    user: JSON.parse(localStorage.getItem('user')),
    returnUrl: null
  }),
  actions: {
    async login(email: string, password: string) {
      try {
        const response = await fetch(`${baseUrl}/authenticate`, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({ email, password })
        });
        const user = await response.json();

        // update pinia state
        this.user = user;
        // store user details and jwt in local storage to keep user logged in between page refreshes
        localStorage.setItem('user', JSON.stringify(user));

        const route = useRoute();
        const returnUrl = route.query.returnUrl as string;

        // redirect to previous url or default to home page
        useRouter().push(returnUrl || '/');
      } catch (error) {
        console.error('로그인 실패:', error);
        alert('로그인 실패');
      }
    },
    logout() {
      this.user = null;
      localStorage.removeItem('user');
      useRouter().push('/logout');
    }
  }
});