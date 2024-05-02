
<template>
  <div class="text-center">
    <v-menu
        :close-on-content-click="false"
        :nudge-right="40"
        lazy
        transition="scale-transition"
        offset-y
        full-width
        min-width="290px">
      <template v-slot:activator="{ props }">
        <v-text-field
            v-bind="props"
            readonly
            :value="taxOffices.length ? taxOffices[0].name : 'OO 세무서'"
            persistent-placeholder
            prepend-icon="mdi-office-building"
            variant="outlined"
            color="primary">
        </v-text-field>
      </template>
      <v-card title="사업장 세무서 조회" flat>

        <v-card-text>
          <v-data-table
              :headers="headers"
              :items="taxOffices"
              @click:row="selectTaxOffice"
              class="elevation-1"
              hide-default-footer>
          </v-data-table>
        </v-card-text>

      </v-card>
    </v-menu>
  </div>
</template>



<script setup>
import { ref, computed, onMounted} from 'vue';
import { useTaxOfficeStore } from '~/stores/accounting/basicdata/taxOffice.js' // Assume store path
const taxOfficeStore = useTaxOfficeStore();

const selectedOffice = ref('');

onMounted(() => {
  taxOfficeStore.fetchTaxOffices(); // Fetch tax offices when component mounts
});
// Computed property to get tax offices from the store
const taxOffices = computed(() => taxOfficeStore.taxOffices);

// Function to handle selection of a tax office
const selectTaxOffice = (item) => {
  selectedOffice.value = item.name; // Update the selected office name
};

const headers = [
  { text: 'Code', value: 'code' },
  { text: 'Tax Office Name', value: 'name' },
  { text: 'Jurisdiction', value: 'jurisdiction' }
];
</script>


