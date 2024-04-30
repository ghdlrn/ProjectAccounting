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
            v-model="date"
            v-bind="props"
            placeholder="yyyy-MM-dd"
            persistent-placeholder
            prepend-icon="mdi-calendar"
            variant="outlined"
            readonly
          color="primary">
        </v-text-field>
      </template>
        <v-date-picker
            v-model="date"
        @click="menu = false"
            locale="ko-KR"
        >
        </v-date-picker>
    </v-menu>
  </div>
</template>

<script setup>
import { ref } from 'vue';
const date = ref(new Date);
const menu = ref(false);

</script>