<script setup>
import { ref, computed, onMounted } from 'vue';
import { useFinanceStore } from "~/stores/accounting/finance";
const store = useFinanceStore();

import {DeleteOutlined, PlusOutlined, SearchOutlined} from "@ant-design/icons-vue";
import UiParentCard from "~/components/shared/UiParentCard.vue";
import FinanceRegister from "~/components/register/finance/FinanceRegister.vue";
import FinanceUpdate from "~/components/register/finance/FinanceUpdate.vue";

onMounted(() => { store.fetchFinance(); });
const finance = computed(() => store.finance );

const sortBy = "code";
const sortType = "asc";
const searchField = ref(['code', 'name', 'accountNumber', 'useStatus']);
const searchValue = ref('');
const headers = ref([
  { text: '금융사 코드', value: 'code', sortable: true },
  { text: '금융사명', value: 'name', sortable: true },
  { text: '계좌번호', value: 'accountNumber', sortable: true },
  { text: '예금종류', value: 'depositType', sortable: true },
  { text: '비고', value: 'note' },
  { text: '사용여부', value: 'useStatus', sortable: true },
  { text: '삭제', value: 'operation' }
]);
const themeColor = ref('rgb(var(--v-theme-primary))');

const selectedFinance = ref(null);

const getFinance = (item) => {
  store.getFinance(item.id).then(financeData => {
    selectedFinance.value = financeData;
  });
};

const deleteFinance = (item) => {
  if (confirm("정말로 삭제하시겠습니까?")) {
    store.deleteFinance(item.id)
  }
};

const dialog = ref(false);
const menu = ref(false);
</script>

<template>
  <UiParentCard title="계좌 조회" class="reference" >
    <PerfectScrollbar>
      <v-card>
        <v-card-item>
          <v-row justify="space-between" class="align-center">
            <v-col cols="6">
              <v-text-field
                  type="text"
                  variant="outlined"
                  color="primary"
                  persistent-placeholder
                  placeholder="계좌 검색"
                  v-model="searchValue"
                  hide-details>
                <template v-slot:prepend-inner>
                  <SearchOutlined :style="{ fontSize: '14px' }" />
                </template>
              </v-text-field>
            </v-col>
            <v-col cols="3" offset="3">
              <div class="d-flex gap-2 justify-end">
                <v-dialog v-model="dialog" class="register">
                  <template v-slot:activator="{ props }">
                    <v-btn variant="flat" color="primary" v-bind="props" size="large">
                      <template v-slot:prepend>
                        <PlusOutlined />
                      </template>
                      계좌 등록
                    </v-btn>
                  </template>
                  <FinanceRegister @closeDialog="dialog = false" />
                </v-dialog>
              </div>
            </v-col>
          </v-row>

        </v-card-item>

        <v-divider></v-divider>

        <v-card-text class="pa-0">
          <v-menu
              v-model="menu"
              :close-on-content-click="false"
              lazy
              transition="scale-transition"
              origin="top right">
            <template v-slot:activator="{ props }">
              <EasyDataTable
                  v-bind="props"
                  :headers="headers"
                  :items="finance"
                  item-key="id"
                  :sort-by="sortBy"
                  :sort-type="sortType"
                  table-class-name="customize-table"
                  :theme-color="themeColor"
                  :search-field="searchField"
                  :search-value="searchValue"
                  @click-row="getFinance"
                  :rows-per-page="10">
                <template v-slot:item-useStatus="{ useStatus }">
                  <v-chip color="success" v-if="useStatus === 'USE'" size="small" label> 사용 </v-chip>
                  <v-chip color="error" v-if="useStatus === 'UNUSED'" size="small" label> 미사용 </v-chip>
                </template>
                <template v-slot:item-operation="item">
                  <div class="operation-wrapper">
                    <v-btn icon color="error" variant="text" @click.stop="deleteFinance(item)" rounded>
                      <DeleteOutlined  />
                    </v-btn>
                  </div>
                </template>
              </EasyDataTable>
            </template>
            <FinanceUpdate :customer="selectedFinance" @closeDialog="menu = false"/>
          </v-menu>
        </v-card-text>
      </v-card>
    </PerfectScrollbar>
  </UiParentCard>
</template>

<style scoped lang="scss">
.reference {
  width: 40%;
  min-width: 900px;
  min-height: 850px;
}
.register {
  width: 50%;
  height: 80%;
  min-width: 1000px;
  min-height: 1300px;
}
</style>