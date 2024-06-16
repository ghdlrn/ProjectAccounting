<script setup lang="ts">
import {computed, ref} from "vue";

const selectedDate = ref(null);
const computedDateFormattedMomentjs = computed(() => {
  return selectedDate.value;
});
import {CalendarOutlined} from "@ant-design/icons-vue";
</script>

<template>
  <v-label class="mb-2">Date</v-label>
  <v-menu :close-on-content-click="false">
    <template v-slot:activator="{ props }">
      <v-text-field
          single-line
          hide-details
          variant="outlined"
          v-bind="props"
          v-model="computedDateFormattedMomentjs"
          label="Selected Date"
          readonly
          color="primary"
      >
        <template v-slot:append-inner>
          <CalendarOutlined />
        </template>
      </v-text-field>
    </template>
    <v-date-picker v-model="selectedDate" hide-header color="primary"></v-date-picker>
  </v-menu>
</template>

<style scoped lang="scss">

</style>