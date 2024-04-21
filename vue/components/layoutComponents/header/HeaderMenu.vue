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

  import {ref, nextTick} from 'vue';
  import {useRoute, useRouter} from 'vue-router';

  const tabs = ref([
  {title: '메인화면', path: '/'},
  {title: '샘플페이지', path: '/sample'},
  {title: '로그인페이지', path: '/auth/login'},
  {title: '회원가입페이지', path: '/auth/register'}
  ]);

  const router = useRouter();
  const route = useRoute();
  const tabIndex = ref(0);

  watch(route, async () => {
  const currentTab = tabs.value.findIndex(tab => tab.path === route.path);
  tabIndex.value = currentTab;
  await nextTick();
  tabIndex.value = currentTab; // 강제로 다시 설정
}, {immediate: true});

  const changeTab = async (path, index) => {
  await router.push(path);
};

</script>

<style scoped>
/* 필요한 스타일 추가 */
</style>