<template>
  <NuxtLink to="/" class="logo-container">
    <v-card class="d-flex mt-3" flat tile>
      <v-img
          class="mx-auto my-auto bg-blue-lighten-5"
          color="white"
          rounded="lg"
          max-width="60"
          src="/images/BoxLogo.jpg">
      </v-img>
      <span class="logo-name">KMBJ회계</span>
    </v-card>
  </NuxtLink>
</template>

<script setup lang="ts">

</script>

<style scoped>
.logo-container {
  text-decoration: none; /* 링크의 밑줄 제거 */
  width: 250px;
  align-items: center;
  justify-content: center;
}

.logo-name {
  font-size: 2.2em;
  color: #333; /* 글자색 설정 */
  white-space: nowrap;  /*이름이 줄바꿈되지 않도록 함*/
}
</style>
