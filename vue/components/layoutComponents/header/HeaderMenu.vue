<template>
  <v-tabs
      v-model="tabIndex"
      class="ml-4"
      dark>
    <v-tab
        v-for="(tab, index) in tabs"
        :key="tab.path"
        @click="changeTab(tab.path, index)"
        color="primary">
      {{ tab.title }}
    </v-tab>
  </v-tabs>
  <v-window v-model="tabIndex">
    <v-window-item
        v-for="(tab, index) in tabs"
        :key="tab.path">
      <NuxtLink :to="tab.path">
      </NuxtLink>
    </v-window-item>
  </v-window>
</template>

<script setup>
import { ref, watch } from 'vue';
import { useRoute, useRouter } from 'vue-router';

const tabs = ref([
  { title: 'Home', path: '/' },
  { title: 'About', path: '/about' },
  { title: 'Services', path: '/userinfo' },
  { title: 'Contact', path: '/contact' }
]);

const router = useRouter();
const route = useRoute();
const tabIndex = ref(0);
const changeTab = (path, index) => {
  if (route.path !== path) { // 현재 경로와 다른 경우에만 라우터를 푸시합니다.
    router.push(path);
  }
  tabIndex.value = index;
};

watch(route, () => {
  const currentTab = tabs.value.findIndex(tab => tab.path === route.path);
  tabIndex.value = currentTab >= 0 ? currentTab : 0;
}, { immediate: true }); // 컴포넌트 마운트 시에도 실행

</script>

<style scoped>
/* 필요한 스타일 추가 */
</style>