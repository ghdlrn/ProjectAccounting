<script setup lang="ts">
import {computed, ref} from 'vue';
import { useCustomizerStore } from '@/stores/customizer';
// Icon Imports
import { SettingsIcon, SearchIcon, Menu2Icon } from 'vue-tabler-icons';

// dropdown imports

import ProfileDD from './ProfileDD.vue';

const customizer = useCustomizerStore();

import { useRouter } from 'vue-router';
import { useAuthStore } from '~/stores/auth';
import HeaderMenu from "~/components/layoutComponents/vertical-header/HeaderMenu.vue";
import HeaderSearchBar from "~/components/layoutComponents/vertical-header/HeaderSearchBar.vue";
import LogoMain from "~/components/logo/LogoMain.vue"
const router = useRouter();
const authStore = useAuthStore();
const isLoggedIn = computed(() => !!authStore.user);

const logout = () => {
  authStore.logout();
  router.push('/'); // 리디렉션
};
</script>

<template>
  <v-app-bar elevation="0" height="80">
    <v-btn
      class="hidden-md-and-down text-secondary"
      color="lightprimary"
      icon
      rounded="lg"
      variant="flat"
      @click.stop="customizer.SET_MINI_SIDEBAR(!customizer.mini_sidebar)"
      size="small"
    >
      <Menu2Icon size="20" stroke-width="1.5" />
    </v-btn>
    <v-btn
      class="hidden-lg-and-up text-secondary ms-3"
      color="lightprimary"
      icon
      rounded="sm"
      variant="flat"
      @click.stop="customizer.SET_SIDEBAR_DRAWER"
      size="small"
    >
      <Menu2Icon size="20" stroke-width="1.5" />
    </v-btn>
    <!-- ---------------------------------------------- -->
    <!-- Search part -->
    <!-- ---------------------------------------------- -->
    <v-sheet class="mt-6 mx-3 v-col-3 v-col-xl-2 v-col-lg-4 d-none d-lg-block">
      <HeaderSearchBar />
    </v-sheet>

    <!---/Search part -->

    <v-spacer />
    <!-- ---------------------------------------------- -->
    <!---right part -->
    <!-- ---------------------------------------------- -->
    <HeaderMenu />
    <v-spacer />
    <!-- ---------------------------------------------- -->
    <!-- signUp, login -->
    <!-- ---------------------------------------------- -->
    <NuxtLink to="/auth/signup" class="mr-6">
      <v-btn class="bg-success" size="large" append-icon="mdi-account-plus">회원가입</v-btn>
    </NuxtLink>

    <!-- 로그인/로그아웃 버튼 -->
    <template v-if="isLoggedIn">
      <v-btn class="bg-red-accent-2" size="large" append-icon="mdi-logout" @click="logout">
        로그아웃
      </v-btn>
    </template>
    <template v-else>
      <NuxtLink class="mr-10" to="/auth/login">
        <v-btn class="bg-blue-accent-2" size="large" append-icon="mdi-login">
          로그인
        </v-btn>
      </NuxtLink>
    </template>

    <!-- ---------------------------------------------- -->
    <!-- User Profile -->
    <!-- ---------------------------------------------- -->
    <v-menu :close-on-content-click="false">
      <template v-slot:activator="{ props }">
        <v-btn icon v-bind="props">
          <v-avatar image="../../../public/images/profile/user-round.svg" size="50"></v-avatar>
        </v-btn>
      </template>
      <v-sheet rounded="md" width="330" elevation="12">
        <ProfileDD />
      </v-sheet>
    </v-menu>

  </v-app-bar>
</template>
