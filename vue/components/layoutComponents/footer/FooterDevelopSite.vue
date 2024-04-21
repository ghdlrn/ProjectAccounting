<template>
  <v-col
      v-for="(shortcut, i) in shortcuts"
      :key="i"
      cols="auto">
    <v-card
        :href="shortcut.href"
        rel="noopener noreferer"
        target="_blank"
        elevation="0">
      <v-avatar :icon="shortcut.icon" class="mx-2 bg-blue-grey"></v-avatar>
      <v-tooltip
      activator="parent"
      location="bottom">
        {{shortcut.title}}
      </v-tooltip>
    </v-card>
  </v-col>
</template>

<script setup lang="ts">
import { ref } from 'vue';

const shortcuts = ref([
  {
    icon: 'mdi-email-outline',
    title: '이메일',
    href: 'mailto:ghdlrn6060@naver.com',
  },
  {
    icon: 'mdi-github',
    title: '깃허브',
    href: 'https://github.com/ghdlrn',
  },
  {
    icon: 'mdi-book-open-page-variant',
    title: '블로그',
    href: 'https://velog.io/@ghdlrn/posts',
  },
  {
    icon: 'mdi-file-code-outline',
    title: '백준 코딩 문제풀이',
    href: 'https://solved.ac/profile/ghdlrn6060',
  }
]);
</script>

<style scoped>

</style>