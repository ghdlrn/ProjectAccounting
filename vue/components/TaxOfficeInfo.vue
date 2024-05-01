
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
        <v-card elevation="0" variant="outlined" class="withbg">
          <!-- Your additional code -->
          <!-- Example of listing tax offices -->
          <div>
            <ul>
              <li v-for="office in taxOffices" :key="office.code">
                {{ office.name }} ({{ office.code }} - {{ office.jurisdiction }})
              </li>
            </ul>
          </div>
        </v-card>
      </v-card>
    </v-menu>
  </div>
</template>



<script setup>
import { onMounted } from 'vue';
import { useTaxOfficeStore } from '@/stores/accounting/taxOffice.js'; // Ensure the path is correct

const taxOfficeStore = useTaxOfficeStore();

// Fetch tax offices when the component mounts
onMounted(() => {
  taxOfficeStore.fetchTaxOffices();
});

// Expose the tax offices data to the template
const taxOffices = computed(() => taxOfficeStore.taxOffices);
</script>


<style lang="scss">
.customer-modal {
  width: calc(100% - 48px);
  min-width: 340px;
  max-width: 880px;
}
</style>
