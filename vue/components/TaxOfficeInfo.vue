
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
            v-bind="props"
            readonly
            placeholder="OO 세무서"
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
              @click-row="selectTaxOffice"
              class="elevation-1"
              hide-default-footer>
          </v-data-table>
        </v-card-text>

      </v-card>
    </v-menu>
  </div>
</template>



<script setup>
import { ref, computed, onMounted} from 'vue';  // onMounted : 반응상태 관리, 계산된속성 생성
import { useTaxOfficeStore } from '~/stores/accounting/basicdata/taxOffice.js'
const taxOfficeStore = useTaxOfficeStore();

onMounted(() => {
  taxOfficeStore.fetchTaxOffices(); //스토어에서  fetchTaxOffices함수실행시켜 세무서 정보 가져옴
});

const selectedOffice = ref('');   //선택한 세무서 이름 저장

const taxOffices = computed(() => taxOfficeStore.taxOffices);

const selectTaxOffice = (item) => {
  selectedOffice.value = item.name; // Update the selected office name
};

const headers = [
  { text: 'Code', value: 'code' },
  { text: 'Tax Office Name', value: 'name' },
  { text: 'Jurisdiction', value: 'jurisdiction' }
];

const menu = ref(false);
</script>


