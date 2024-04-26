<script setup lang="ts">
import {computed, ref} from 'vue';
import { useCustomizerStore } from '@/stores/customizer';
// Icon Imports
import { SettingsIcon, SearchIcon, Menu2Icon } from 'vue-tabler-icons';

// dropdown imports

import ProfileDD from './ProfileDD.vue';

const customizer = useCustomizerStore();
const showSearch = ref(false);
function searchbox() {
  showSearch.value = !showSearch.value;
}

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
      color="lightsecondary"
      icon
      rounded="sm"
      variant="flat"
      @click.stop="customizer.SET_MINI_SIDEBAR(!customizer.mini_sidebar)"
      size="small"
    >
      <Menu2Icon size="20" stroke-width="1.5" />
    </v-btn>
    <v-btn
      class="hidden-lg-and-up text-secondary ms-3"
      color="lightsecondary"
      icon
      rounded="sm"
      variant="flat"
      @click.stop="customizer.SET_SIDEBAR_DRAWER"
      size="small"
    >
      <Menu2Icon size="20" stroke-width="1.5" />
    </v-btn>

    <!-- search mobile -->
    <v-btn
      class="hidden-lg-and-up text-secondary ml-3"
      color="lightsecondary"
      icon
      rounded="sm"
      variant="flat"
      size="small"
      @click="searchbox"
    >
      <SearchIcon size="17" stroke-width="1.5" />
    </v-btn>

    <v-sheet v-if="showSearch" class="search-sheet v-col-12">
      <Searchbar :closesearch="searchbox" />
    </v-sheet>

    <LogoMain />
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
    <!-- ---------------------------------------------- -->
    <!-- signUp, login -->
    <!-- ---------------------------------------------- -->
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

    <!-- ---------------------------------------------- -->
    <!-- User Profile -->
    <!-- ---------------------------------------------- -->
    <v-menu :close-on-content-click="false">
      <template v-slot:activator="{ props }">
        <v-btn class="profileBtn text-primary" color="lightprimary" variant="flat" rounded="pill" v-bind="props">
          <v-avatar size="30" class="mr-2 py-2">
            <img src="../../../public/images/profile/user-round.svg" alt="Julia" />
          </v-avatar>
          <SettingsIcon stroke-width="1.5" />
        </v-btn>
      </template>
      <v-sheet rounded="md" width="330" elevation="12">
        <ProfileDD />
      </v-sheet>
    </v-menu>
  </v-app-bar>
</template>
