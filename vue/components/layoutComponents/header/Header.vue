<template>
  <v-app-bar
      app
      elevation="0"
      floating
      class="header"
      height="80">
    <v-spacer></v-spacer>
      <HeaderLogo />
      <HeaderSearchBar class="mt-7 ml-16" />
    <v-spacer></v-spacer>
      <HeaderMenu />
    <v-spacer></v-spacer>

    <!-- Settings Button -->
    <NuxtLink to="/" class="mr-10">
      <v-btn icon size="large">
        <v-icon>mdi-cog</v-icon>
      </v-btn>
    </NuxtLink>

    <!-- Login Button -->
    <NuxtLink to="/login/LoginPage">
      <v-btn class="bg-blue-accent-2" size="large">로그인</v-btn>
    </NuxtLink>

    <v-spacer></v-spacer>

  </v-app-bar>
</template>

<script setup>
import HeaderLogo from "~/components/layoutComponents/header/HeaderLogo.vue";
import HeaderMenu from "~/components/layoutComponents/header/HeaderMenu.vue";
import HeaderSearchBar from "~/components/layoutComponents/header/HeaderSearchBar.vue";
</script>

<style scoped>
.header{
  margin: 0 auto;
  width: 100%;
}
</style>
