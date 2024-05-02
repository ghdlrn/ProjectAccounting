
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
      <PerfectScrollbar>
      <v-card title="사업장 세무서 조회">
        <v-card-item>
          <v-row justify="space-between" class="align-center">
            <v-col cols="12" md="5">
              <v-text-field
                  type="text"
                  variant="outlined"
                  persistent-placeholder
                  placeholder="Search Customer"
                  v-model="searchValue"
                  hide-details>
                <template v-slot:prepend-inner>
                  <SearchOutlined :style="{ fontSize: '14px' }" />
                </template>
              </v-text-field>
            </v-col>
          </v-row>
        </v-card-item>
        <v-divider></v-divider>
        <v-card-text class="pa-0">

          <EasyDataTable
                :headers="headers"
                :items="taxOffices"
                :search-field="searchField"
                :search-value="searchValue"
                table-class-name="customize-table"
                :rows-per-page="10"
          ></EasyDataTable>

        </v-card-text>
      </v-card>
      </PerfectScrollbar>
    </v-menu>
  </div>
</template>

<script setup>
import { ref, computed, onMounted} from 'vue';  // onMounted : 반응상태 관리, 계산된속성 생성
import { useTaxOfficeStore } from '~/stores/accounting/basicdata/taxOffice.js'
import {SearchOutlined} from "@ant-design/icons-vue";
const taxOfficeStore = useTaxOfficeStore();

onMounted(() => {
  taxOfficeStore.fetchTaxOffices(); //스토어에서  fetchTaxOffices함수실행시켜 세무서 정보 가져옴
});
const taxOffices = computed(() => taxOfficeStore.taxOffices);

const searchField = ref('name');
const searchValue = ref('');
const headers = [
  { text: '세무서 코드', value: 'code', sortable: true },
  { text: '세무서명', value: 'name', sortable: true},
  { text: '관할구역', value: 'jurisdiction', sortable: true }
];

const menu = ref(false);

const selectedOffice = ref('');   //선택한 세무서 이름 저장

const selectTaxOffice = (item) => {
  selectedOffice.value = item.name; // Update the selected office name
};

</script>

<style lang="scss">
</style>