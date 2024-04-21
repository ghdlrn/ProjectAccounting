<template>

    <v-list class="pa-4" v-model:opened="groups.open">
      <v-list-group
          v-for="(group, index) in groups"
          :key="index"
          v-model="group.open"
          :prepend-icon="group.icon"
          :title="group.title"
          :value="group.title"
          class="mb-1"
          color="primary"
          rounded="xl"
          >
        <template v-slot:activator="{ props }">
          <v-list-item v-bind="props" :title="group.title"></v-list-item>
        </template>

        <v-list-item
            v-for="(item, i) in group.items"
            :key="`item-${i}`"
            :prepend-icon="item.icon"
            :title="item.title"
            :value="item.title"
            class="mb-1"
            color="primary"
            rounded="xl"
        ></v-list-item>
      </v-list-group>
    </v-list>

</template>


<script setup>
import { ref } from 'vue';

const groups = ref([
  {
    title: "기초 정보 등록",
    icon: "mdi-note-edit-outline",
    open: false,
    items: [
      { title: '회사 등록', icon: 'mdi-office-building' },
      { title: '거래처 등록', icon: 'mdi-handshake' },
      { title: '계좌 등록', icon: 'mdi-bank' },
      { title: '카드 등록', icon: 'mdi-card-account-details-outline' },
      { title: '계정과목 및 적요 등록', icon: 'mdi-book-open-page-variant' },
      { title: '고정자산 등록', icon: 'mdi-screw-lag' },
      { title: '업무용 승용차 등록', icon: 'mdi-car' },
    ],
  },
  {
    title: "전표",
    icon: "mdi-notebook-outline",
    open: false,
    items: [
      { title: '일반전표입력', icon: 'mdi-notebook-plus-outline' },
      { title: '매입매출전표입력', icon: 'mdi-swap-horizontal' },
      { title: '차량비용입력', icon: 'mdi-car-outline' },
    ],
  },
  {
    title: "장부",
    icon: "mdi-book-account-outline",
    open: false,
    items: [
      { title: '분개장', icon: 'mdi-notebook-edit-outline' },
      { title: '일/월계표', icon: 'mdi-calendar-month-outline' },
      { title: '합계잔액시산표', icon: 'mdi-calculator-variant' },
      { title: '종별원장', icon: 'mdi-book-check-outline' },
      { title: '현금출납장', icon: 'mdi-cash-register' },
      { title: '매입매출장', icon: 'mdi-swap-vertical' },
      { title: '세금계산서수수현황', icon: 'mdi-receipt' },
      { title: '차량비용현황', icon: 'mdi-car-multiple' },
      { title: '고정자산현황', icon: 'mdi-domain' },
    ],
  },
]);
</script>

<style scoped>

</style>