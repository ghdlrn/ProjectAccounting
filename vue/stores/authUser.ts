import { defineStore } from 'pinia';

export const useAuthUserStore = defineStore('authUser', {
    state: () => ({
        username: '',
        email: '',
    }),
    actions: {
        setAddress(data) {
            this.username = data.username;
            this.email = data.email;
        },
        clearAddress() {
            this.username = '';
            this.email = '';
        }
    }
});