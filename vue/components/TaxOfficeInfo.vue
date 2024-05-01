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
            v-model="taxOffice"
            v-bind="props"
            readonly
            placeholder="OO 세무서"
            persistent-placeholder
            prepend-icon="mdi-office-building"
            variant="outlined"
            color="primary">
        </v-text-field>
      </template>
      <v-data-table
      @click="menu = false">
      </v-data-table>
    </v-menu>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';

const menu = ref(false);



</script>
