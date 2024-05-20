<script setup lang="ts">
import {computed} from 'vue';
import { useCustomizerStore } from '@/stores/customizer';
import { SettingsIcon, Menu2Icon } from 'vue-tabler-icons';

import { useAuthStore } from '~/stores/auth/auth';
import ProfileDD from './ProfileDD.vue';
import HeaderMenu from "~/components/layoutComponents/vertical-header/HeaderMenu.vue";
import HeaderSearchBar from "~/components/shared/SearchBar.vue";
import FullScreen from "~/components/layoutComponents/vertical-header/FullScreen.vue";

const router = useRouter();
const authStore = useAuthStore();
const customizer = useCustomizerStore();
const isLoggedIn = computed(() => !!authStore.member);

const logout = async () => {
  try {
    await authStore.logout();
    await router.push('/'); // 리디렉션
  } catch (error) {
    console.error('Logout failed:', error);
    alert('로그아웃 실패');
  }
};
</script>

<template>
  <v-app-bar elevation="0" height="100">
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
    <!-- 회원가입 -->
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
      <NuxtLink class="mr-8" to="/auth/login">
        <v-btn class="bg-blue-accent-2" size="large" append-icon="mdi-login">
          로그인
        </v-btn>
      </NuxtLink>
    </template>
    <!-- ---------------------------------------------- -->
    <!-- 풀스크린모드 -->
    <!-- ---------------------------------------------- -->
    <FullScreen class="mr-12"/>
    <!-- ---------------------------------------------- -->
    <!-- 셋팅 -->
    <!-- ---------------------------------------------- -->
    <v-btn
        class="customizer-btn"
        icon
        size="large"
        variant="text"
        color="darkText"
        @click.stop="customizer.SET_CUSTOMIZER_DRAWER(!customizer.Customizer_drawer)">
      <SettingsIcon class="icon" />
    </v-btn>
    <!-- ---------------------------------------------- -->
    <!-- 프로필 -->
    <!-- ---------------------------------------------- -->
    <v-menu :close-on-content-click="false" min-width="200" rounded>
      <template v-slot:activator="{ props }">
        <v-btn icon v-bind="props" size="64">
          <v-avatar size="64">
            <v-img src="../../../public/images/profile/user-round.svg" alt="userImage"></v-img>
          </v-avatar>
        </v-btn>
      </template>
      <v-sheet rounded="md" width="330" elevation="12">
        <ProfileDD />
      </v-sheet>
    </v-menu>

  </v-app-bar>
</template>
