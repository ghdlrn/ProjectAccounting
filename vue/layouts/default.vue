<template>
  <v-app id="inspire">
    <LeftNavBar />
    <RightNavBar />

    <v-main class="fill-height ">

      <Header />
      <v-container class="bg-blue-lighten-5 mb-6 rounded-xl page" >
        <div>
          <NuxtPage class="rounded-xl elevation-0 bg-blue-lighten-5 mx-auto my-auto" />
        </div>
      </v-container>
      <Footer />

    </v-main>
  </v-app>
</template>

<script setup>
import Footer from '~/components/layoutComponents/footer/Footer.vue';
import Header from "~/components/layoutComponents/header/Header.vue";
import LeftNavBar from "~/components/layoutComponents/leftNavBar/LeftNavBar.vue";
import RightNavBar from "~/components/layoutComponents/RightNavBar.vue";
</script>

<style scoped>
.page {
  margin: 20px auto;
  width: 100%; /* 전체 너비를 사용 */
}
</style>
