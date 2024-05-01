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
            v-model="selectedTaxOffice.name"
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
          :headers="headers"

          >
      </v-data-table>
    </v-menu>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';

const menu = ref(false);
const taxOffices = ref([]);
const selectedTaxOffice = ref({});

// Define the headers for the data table
const headers = ref([
  { text: 'Code', value: 'code' },
  { text: 'Name', value: 'name' },
  { text: 'Jurisdiction', value: 'jurisdiction' }
]);

async function fetchTaxOffices() {
  try {
    const response = await axios.get('/register/company-info');
    taxOffices.value = response.data;
  } catch (error) {
    console.error('Error fetching tax offices:', error);
  }
}

function selectTaxOffice(item) {
  selectedTaxOffice.value = item;
  menu.value = false; // Close the menu after selection
}

onMounted(fetchTaxOffices);

</script>
