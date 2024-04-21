<template>
  <v-footer
      elevation="0"
      class="footer"
      text-center
  >
    <v-container>
      <v-row class="mx-auto" justify="center">  <!-- 첫 번째 줄: 아이콘 버튼들 -->
        <FooterDevelopSite />
      </v-row>

      <v-row class="mx-auto text-md-center" justify="center"> <!-- 두 번째 줄: 개발자 정보 -->
        <v-col cols="12">
          백엔드 개발자 취업 희망하는 취준생입니다<br />
          해당 사이트는 개인 프로젝트로 아래 기술스택으로 개발되었습니다<br />
          백엔드 -> 언어 : Java, 프레임워크 : SpringBoot<br />
          프론트엔드 -> 라이브러리 : Vue3, Vutify3, Pinia 프레임워크 : Nuxt3
        </v-col>
      </v-row>

      <v-row class="mx-auto text-md-center" justify="center"> <!-- 세 번째 줄: 버전 정보 -->
        <v-col cols="12">
          <strong>Ver 1.0.0</strong>
        </v-col>
      </v-row>
    </v-container>
  </v-footer>
</template>

<script setup lang="ts">
import FooterDevelopSite from "~/components/layoutComponents/footer/FooterDevelopSite.vue";
</script>

<style scoped>
.footer {
  margin: 0 auto;
  width: 100%;
}
</style>
