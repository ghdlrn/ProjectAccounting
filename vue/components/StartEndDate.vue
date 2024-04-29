<template>
  <v-row>
      <v-menu
          ref="menu1"
          v-model="menu1"
          :close-on-content-click="false"
          :return-value.sync="s_date"
          transition="scale-transition"
          offset-y
          min-width="290px">
        <template v-slot:activator="{ props }">
          <v-text-field
              v-model="s_date"
              prepend-icon="mdi-calendar"
              v-bind="props.attrs"
              v-on="props.on"
          ></v-text-field>
        </template>
        <v-date-picker v-model="s_date" no-title scrollable :max="e_date">
          <v-spacer></v-spacer>
          <v-btn color="primary" @click="menu1 = false">Cancel</v-btn>
          <v-btn color="primary" @click="updateStartDate">OK</v-btn>
        </v-date-picker>
      </v-menu>

      <v-menu
          ref="menu2"
          v-model="menu2"
          :close-on-content-click="false"
          :return-value.sync="e_date"
          transition="scale-transition"
          offset-y
          min-width="290px">
        <template v-slot:activator="{ props }">
          <v-text-field
              v-model="e_date"
              prepend-icon="mdi-calendar"
              v-bind="props.attrs"
              v-on="props.on"
          ></v-text-field>
        </template>
        <v-date-picker v-model="e_date" no-title scrollable :min="s_date" :max="date">
          <v-spacer></v-spacer>
          <v-btn color="primary" @click="menu2 = false">Cancel</v-btn>
          <v-btn color="primary" @click="updateEndDate">OK</v-btn>
        </v-date-picker>
      </v-menu>
  </v-row>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue';
import { useDateStore } from '~/stores/dateStore';

const store = useDateStore();

const menu1 = ref(false);
const menu2 = ref(false);
const date = computed(() => new Date().toISOString().substr(0, 10));
const s_date = computed({
  get: () => store.s_date,
  set: value => store.setStartDate(value)
});
const e_date = computed({
  get: () => store.e_date,
  set: value => store.setEndDate(value)
});

function updateStartDate() {
  store.setStartDate(s_date.value);
  menu1.value = false;
}

function updateEndDate() {
  store.setEndDate(e_date.value);
  menu2.value = false;
}
</script>

<style scoped lang="scss">
/* your styles here */
</style>
