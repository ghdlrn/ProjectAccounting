<template>
  <v-locale-provider>
    <v-app
        theme="PurpleTheme"
        :class="[customizer.fontTheme, customizer.mini_sidebar ? 'mini-sidebar' : '', customizer.inputBg ? 'inputWithbg' : '']"
    >
      <Customizer />
      <VerticalSidebarVue />
      <VerticalHeaderVue />

      <v-main>
        <v-container fluid class="page-wrapper  position-relative" no-gutters>
          <NuxtPage />
        </v-container>
        <v-container fluid class="pt-0">
          <div>
            <Footer />
          </div>
        </v-container>
      </v-main>
    </v-app>
  </v-locale-provider>
</template>

<script setup>
import VerticalSidebarVue from '~/components/layoutComponents/vertical-sidebar/VerticalSidebar.vue';
import VerticalHeaderVue from '~/components/layoutComponents/vertical-header/VerticalHeader.vue';
import Customizer from '~/components/layoutComponents/customizer/CustomizerPanel.vue';
import Footer from '~/components/layoutComponents/footer/Footer.vue';
import { useCustomizerStore } from '~/stores/customizer';
const customizer = useCustomizerStore();
</script>

<style scoped>

</style>
