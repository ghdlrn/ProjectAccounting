<template>
  <UiParentCard title="홈페이지 기능 설명">
    <div class="d-flex align-center gap-3">
      <v-text-field
          variant="outlined"
          v-model="searchValue"
          persistent-placeholder
          placeholder="검색"
          hide-details
      >
        <template v-slot:prepend-inner>
          <SearchOutlined class="text-lightText" />
        </template>
      </v-text-field>
    </div>
    <div class="button-list mt-4">
      <v-btn
          v-for="item in filteredContact"
          :key="item.title"
          :href="item.path"
          target="_blank"
          class="mb-2 youtube-btn"
          color="red darken-1"
          dark
          variant="outlined"
          size="x-large"
      >
        <v-icon left>{{ item.icon }}</v-icon>
        {{ item.title }}
      </v-btn>
    </div>
  </UiParentCard>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue';
import UiParentCard from "~/components/shared/UiParentCard.vue";
import _ from 'lodash';
import { SearchOutlined } from '@ant-design/icons-vue';

const searchValue = ref('');
const allContacts = ref([
  { title: '레이아웃', path: 'https://www.youtube.com/watch?v=LKiDEchLM0s&t=68s&ab_channel=LeeKyuMin', icon: 'mdi-youtube' },
  { title: '회원가입, 로그인, 로그아웃', path: 'https://www.youtube.com/watch?v=w4gf2AzLqYw&t=54s&ab_channel=LeeKyuMin', icon: 'mdi-youtube' },
  { title: '회사 및 기초정보 등록', path: 'https://www.youtube.com/watch?v=BJpHzU4Ynys&t=2s&ab_channel=LeeKyuMin', icon: 'mdi-youtube' },
  // 리스트목록 추가
]);

const { flow, groupBy, flatMap, get, filter } = _;

const groupItems = flow([
  (arr: any) => groupBy(arr, (o: any) => get(o, 'title[0]', '').toUpperCase()),
  (groups: any) => flatMap(groups, (v: any, k: any) => [k, ...v]),
  (arr: any) => filter(arr, (o: any) => get(o, 'title', '').toLowerCase().includes(searchValue.value.toLowerCase()))
]);

const filteredContact = computed(() => {
  return groupItems(allContacts.value).filter(item=> item.title); // 빈 제목 필터링
});
</script>

<style scoped>
.button-list {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.youtube-btn {
  background-color: white !important; /* YouTube red color */
  color: #ff0000 !important;
}

</style>
