<script setup lang="ts">

import Logo from "~/components/logo/LogoMain.vue";
</script>

<template>
  <v-row class="bg-containerBg position-relative" no-gutters>
    <div class="blur-logo">
      <svg width="100%" height="150vh" viewBox="0 0 1200 800" fill="none" xmlns="http://www.w3.org/2000/svg">
        <defs>
          <filter id="soft-blur" x="-10%" y="-10%" width="120%" height="120%">
            <feGaussianBlur in="SourceGraphic" stdDeviation="5" />
          </filter>
          <linearGradient id="blue-gradient" x1="0%" y1="50%" x2="100%" y2="50%">
            <stop offset="0%" style="stop-color:#5591CA;stop-opacity:0.5" />
            <stop offset="100%" style="stop-color:#6D8FAD;stop-opacity:0.5" />
          </linearGradient>
        </defs>
        <rect width="1200" height="800" fill="url(#blue-gradient)"/>
        <g filter="url(#soft-blur)">
          <path fill="rgba(255, 255, 255, 0.3)" d="M0,0 L600,800 L1200,0 Z"/>
          <path fill="rgba(255, 255, 255, 0.3)" d="M0,800 L600,0 L1200,800 Z"/>
        </g>
        <path fill="none" stroke="#ffffff" stroke-opacity="0.6" stroke-width="1" d="M0,400 L1200,400"/>
        <path fill="none" stroke="#ffffff" stroke-opacity="0.6" stroke-width="1" d="M600,0 L600,800"/>
      </svg>
    </div>
    <v-col cols="12">
      <div class="pt-6 pl-6">
        <Logo />
      </div>
    </v-col>

    <NuxtPage />

  </v-row>
</template>

<style scoped>
.blur-logo {
  position: absolute;
  filter: blur(18px);
  bottom: 0;
  transform: inherit;
}
</style>