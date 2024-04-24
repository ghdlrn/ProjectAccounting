<template>
  <v-app-bar
      app
      elevation="0"
      floating
      class="header"
      height="80">
    <v-spacer></v-spacer>
      <HeaderLogo />
    <v-spacer></v-spacer>
      <HeaderSearchBar class="mt-7 ml-16" />
    <v-spacer></v-spacer>
      <HeaderMenu />
    <v-spacer></v-spacer>

    <!-- 셋팅버튼 -->
    <NuxtLink to="/" class="mr-10">
      <v-btn class="customizer-btn bg-blue-grey-lighten-1"
             size="large"
             variant="flat"
             icon="mdi-cog-outline">
      </v-btn>
    </NuxtLink>

    <!-- 회원가입 버튼 -->
    <NuxtLink to="/auth/signup" class="mr-10">
      <v-btn class="bg-success" size="large" append-icon="mdi-account-plus">회원가입</v-btn>
    </NuxtLink>

    <!-- 로그인/로그아웃 버튼 -->
    <template v-if="isLoggedIn">
      <v-btn class="bg-red-accent-2" size="large" append-icon="mdi-logout" @click="logout">
        로그아웃
      </v-btn>
    </template>
    <template v-else>
      <NuxtLink to="/auth/login">
        <v-btn class="bg-blue-accent-2" size="large" append-icon="mdi-login">
          로그인
        </v-btn>
      </NuxtLink>
    </template>

    <v-spacer></v-spacer>

  </v-app-bar>
</template>

<script setup>
import HeaderLogo from "~/components/layoutComponents/header/HeaderLogo.vue";
import HeaderMenu from "~/components/layoutComponents/header/HeaderMenu.vue";
import HeaderSearchBar from "~/components/layoutComponents/header/HeaderSearchBar.vue";

import { computed } from 'vue';
import { useRouter } from 'vue-router';
import { useAuthStore } from '~/stores/auth';
const router = useRouter();
const authStore = useAuthStore();
const isLoggedIn = computed(() => !!authStore.user);

const logout = () => {
  authStore.logout();
  router.push('/'); // 리디렉션
};
</script>

<style scoped>
.header{
  margin: 0 auto;
  width: 100%;
}
</style>
