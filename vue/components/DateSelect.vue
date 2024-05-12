<template>
  <div class="text-center">
    <v-menu
        v-model="menu"
        :close-on-content-click="false"
        :nudge-right="40"
        lazy
        transition="scale-transition"
        offset-y
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
            @change="updateDate" />
    </v-menu>
  </div>
</template>

<script setup>
import { ref, watch, computed, nextTick } from 'vue';
import {DatePicker} from "v-calendar";

const props = defineProps({
  modelValue: {
    type: String,
    default: () => new Date().toISOString().slice(0, 10)
  }
});

const emit = defineEmits(['update:modelValue']);
const menu = ref(false);
const innerDate = ref(new Date(props.modelValue));

const formattedDate = computed(() => {
  nextTick(() => {
    menu.value = false;
  });
  if (!innerDate.value) return '';
  const d = new Date(innerDate.value);
  return `${d.getFullYear()}-${(d.getMonth() + 1).toString().padStart(2, '0')}-${d.getDate().toString().padStart(2, '0')}`;
});

function updateDate(value) {
  innerDate.value = new Date(value);
  emit('update:modelValue', innerDate.value.toISOString().slice(0, 10));

}

watch(innerDate, (newValue) => {
  if (newValue.toISOString().slice(0, 10) !== props.modelValue) {
    emit('update:modelValue', newValue.toISOString().slice(0, 10));
  }
}, { deep: true });

</script>