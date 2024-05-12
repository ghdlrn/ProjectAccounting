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
            v-model="innerDate"
            @input="updateDate" />
    </v-menu>
  </div>
</template>

<script setup>
import { ref, watch, computed, nextTick } from 'vue';
import {DatePicker} from "v-calendar";

const menu = ref(false);
const props = defineProps({
  modelValue: {
    type: [Date, String],
    default: () => new Date()
  }
});
const emits = defineEmits(['update:modelValue']);
const innerDate = ref(new Date(props.modelValue));
// 날짜 포맷 함수
const formattedDate = computed(() => {
  if (!innerDate.value) return '';
  const d = new Date(innerDate.value);
  const month = String(d.getMonth() + 1).padStart(2, '0');
  const day = String(d.getDate()).padStart(2, '0');
  const year = d.getFullYear();
  return `${year}-${month}-${day}`;
});

function updateDate(value) {
  const dateValue = new Date(value);
  innerDate.value = dateValue;
  emits('update:modelValue', dateValue);
  menu.value = false
}

watch(() => props.modelValue, (newVal) => {
  const newDate = newVal instanceof Date ? newVal : new Date(newVal);
  if (newDate.getTime() !== innerDate.value.getTime()) {
    innerDate.value = newDate;
  }
});

</script>