<template>
  <PerfectScrollbar>
  <v-navigation-drawer
      left
      elevation="0"
      rail-width="80"
      app
      rail
      expand-on-hover
      floating
      width="500px">

    <LeftNavBarUserInfo />

    <v-divider class="left-bar-divider"></v-divider>

      <AccountingLeftNavBarList />

  </v-navigation-drawer>
  </PerfectScrollbar>
</template>

<script setup lang="ts">
import AccountingLeftNavBarList from "~/components/layoutComponents/leftNavBar/AccountingLeftNavBarList.vue";
import LeftNavBarUserInfo from "~/components/layoutComponents/leftNavBar/LeftNavBarUserInfo.vue";
import { PerfectScrollbar } from 'vue3-perfect-scrollbar';
</script>

<style scoped>
.left-bar-divider{
  width: 85%;
  margin: 0 auto;
}
</style>