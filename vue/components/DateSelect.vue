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

const props = defineProps({
  modelValue: {
    type: String,
    default: () => new Date().toISOString().substr(0, 10)  // 기본값은 오늘 날짜
  }
});

const emit = defineEmits(['update:modelValue']);
const menu = ref(false);
const innerDate = ref(new Date(props.modelValue));  // 문자열 날짜를 Date 객체로 변환

const formattedDate = computed(() => {
  if (!innerDate.value) return '';
  const d = new Date(innerDate.value);
  return `${d.getFullYear()}-${(d.getMonth() + 1).toString().padStart(2, '0')}-${d.getDate().toString().padStart(2, '0')}`;
});

function updateDate(value) {
  innerDate.value = new Date(value);
  emit('update:modelValue', innerDate.value.toISOString().substr(0, 10));  // ISO 문자열의 날짜 부분만 발송
  nextTick(() => {
    menu.value = false;
  });
}

watch(innerDate, (newValue) => {
  if (newValue.toISOString().substr(0, 10) !== props.modelValue) {
    emit('update:modelValue', newValue.toISOString().substr(0, 10));
  }
}, { deep: true });

</script>