import { defineStore } from 'pinia';
import { useFetch } from 'nuxt/app';

const baseUrl = `${process.env.API_URL}/users`;  //api 기본 url

export const useUsersStore = defineStore('Authuser', {
  state: () => ({
    users: {}   //유저정보
  }),
  actions: {
    async getAll() {  //사용자 정보를 가져옴
      this.users = { loading: true };   //현재 데이터가 로딩되고있음
      try {
        const response = await useFetch(baseUrl);   //데이터가져오기시도
        if (response.data) {
          this.users = response.data;
        }
      } catch (error) {
        this.users = { error };
      }
    }
  }
});
