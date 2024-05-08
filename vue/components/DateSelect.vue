<template>
  <div class="text-center">
    <v-menu
        v-model="menu"
        :close-on-content-click="false"
        :nudge-right="40"
        lazy
        transition="scale-transition"
        offset-y
        full-width
        min-width="290px">
      <template v-slot:activator="{ props }">
        <v-text-field
            v-model="formattedDate"
            v-bind="props"
            placeholder="yyyy-MM-dd"
           persistent-placeholder
            prepend-icon="mdi-calendar"
            variant="outlined"
            readonly
          color="primary">
        </v-text-field>
      </template>
        <DatePicker
            v-model="date"
            @change="updateFormattedDate"/>
    </v-menu>
  </div>
</template>

<script setup>
import { ref, watch, defineEmits } from 'vue';
import {DatePicker} from "v-calendar";

const date = ref(new Date());
const menu = ref(false);
const formattedDate = ref('');
const emits = defineEmits(['update']);

// 날짜 포맷 함수
function formatDate(date) {
  if (!date) return '';
  const d = new Date(date);
  const month = String(d.getMonth() + 1).padStart(2, '0');
  const day = String(d.getDate()).padStart(2, '0');
  const year = d.getFullYear();
  return `${year}-${month}-${day}`;
}

// 포맷된 날짜를 초기 설정
formattedDate.value = formatDate(date.value);

// 날짜가 변경될 때마다 포맷 업데이트
function updateFormattedDate(newDate) {
  formattedDate.value = formatDate(newDate);
  menu.value = false;  // 날짜 선택 후 메뉴 닫기
  emits('update', formattedDate.value);
}

watch(date, updateFormattedDate);
</script>