
<template>
  <div class="text-center">
    <v-menu
        v-model="menu"
        :close-on-content-click="false"
        :close-on-click="true"
        lazy
        transition="scale-transition"
        offset-y
        min-width="290px">
      <template v-slot:activator="{ props }">
        <v-text-field
            v-model="selectedLocalTax"
            v-bind="props"
            readonly
            placeholder="ex) 서울특별시 종로구 효자동"
            persistent-placeholder
            prepend-icon="mdi-map-marker"
            variant="outlined"
            color="primary">
        </v-text-field>
      </template>
      <PerfectScrollbar>
      <v-card title="지방세 법정동 조회" class="localTaxTable">
        <v-card-item>
          <v-row justify="space-between" class="align-center">
            <v-col cols="12" md="5">
              <v-text-field
                  type="text"
                  variant="outlined"
                  color="primary"
                  persistent-placeholder
                  placeholder="주소명으로 검색하시오"
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
                :items="localTax"
                :search-field="searchField"
                :search-value="searchValue"
                @click-row="select"
                table-class-name="customize-table"
                :rows-per-page="10"
                buttons-pagination
          ></EasyDataTable>

        </v-card-text>
      </v-card>
      </PerfectScrollbar>
    </v-menu>
  </div>
</template>

<script setup>
import { ref, computed, onMounted} from 'vue';  // onMounted : 반응상태 관리, 계산된속성 생성

import { useLocalTaxStore } from '~/stores/accounting/basicdata/localTax.ts'
import {SearchOutlined} from "@ant-design/icons-vue";
const store = useLocalTaxStore();

onMounted(() => {
  store.fetchLocalTax(); //스토어에서  fetchTaxOffices함수실행시켜 세무서 정보 가져옴
});
const localTax = computed(() => store.localTax);

const searchField = ref('name');
const searchValue = ref('');
const headers = ref( [
  { text: '법정동 코드', value: 'code', sortable: true, width: 20, fixed: true },
  { text: '법정동 주소', value: 'name', sortable: true, width: 40, fixed: true },
]);

const menu = ref(false);

const selectedLocalTax = ref({});   //선택한 세무서 저장

function select(item) {
  selectedLocalTax.value = item.name;
  store.setSelectedLocalTax(item);
  menu.value = false;
}

</script>

<style lang="scss">
.localTaxTable {
  width: 400px;
}
</style>