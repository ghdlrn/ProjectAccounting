<template>
  <v-tabs
      v-model="tabIndex"
      dark
      height="80px">
    <v-tab
        v-for="(tab, index) in tabs"
        :key="tab.path"
        @click="changeTab(tab.path, index)"
        color="primary"
        width="150px">
     <p class="tab-title">{{ tab.title }}</p>
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
  {title: '회계', path: '/'},
  {title: '세무', path: '/'},
  {title: '샘플페이지', path: '/sample'},
  {title: '게시판', path: '/'}
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

<style lang="scss" scoped>
.tab-title {
  font-size: 140%;
  text-align: center;
}
</style>