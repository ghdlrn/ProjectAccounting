<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { useFinanceStore } from "~/stores/accounting/finance";
import { SearchOutlined } from "@ant-design/icons-vue";
import type { Finance } from '~/types/accounting/finance';

const store = useFinanceStore();

const searchField = ref(['id', 'name', 'accountNumber', 'useStatus']);
const searchValue = ref('');
const headers = ref([
  { text: '금융사 코드', value: 'id', sortable: true },
  { text: '금융사명', value: 'name', sortable: true },
  { text: '계좌번호', value: 'accountNumber', sortable: true },
  { text: '예금종류', value: 'depositType', sortable: true },
  { text: '비고', value: 'note' },
  { text: '사용여부', value: 'useStatus', sortable: true },
  { text: '삭제', value: 'operation' }
]);

const menu = ref(false);
const finance = ref<Finance[]>([]);
const currentFinance = ref<Finance | null>(null);

const fetchFinanceList = async () => {
  await store.fetchFinance();
  finance.value = store.finance;
};

const select = async (item: Finance) => {
  await store.getFinance(item.id);
  store.setSelectedFinance(store.currentFinance);
  menu.value = false;
};

onMounted(async () => {
  await fetchFinanceList();
});
</script>

<template>
  <div class="text-center">
    <v-menu
        v-model="menu"
        :close-on-content-click="false"
        :close-on-click="true"
        lazy
        transition="scale-transition"
        offset-y
        min-width="290px"
    >
      <template v-slot:activator="{ props }">
        <v-row>
          <v-col cols="5">
            <v-row>
              <v-col cols="3">
                <v-label class="mt-2">은행명</v-label>
              </v-col>
              <v-col cols="6">
                <v-text-field
                    :modelValue="store.currentFinance ? store.currentFinance.name : ''"
                    @update:modelValue="val => { if (store.currentFinance) store.currentFinance.name = val }"
                    readonly
                    hint="오른쪽 버튼으로 보기"
                    persist-hint
                    variant="outlined"
                    persist-placeholder
                    placeholder="ex) 농협"
                    color="primary"
                ></v-text-field>
              </v-col>
              <v-col cols="2">
                <v-btn size="large" flat color="primary mt-1" class="btn" v-bind="props" @click="menu = true"> 은행 조회</v-btn>
              </v-col>
            </v-row>
          </v-col>
          <v-col cols="7">
            <v-row>
              <v-col cols="2">
                <v-label class="mt-2">계좌번호</v-label>
              </v-col>
              <v-col cols="5">
                <v-text-field
                    :modelValue="store.currentFinance ? store.currentFinance.accountNumber : ''"
                    @update:modelValue="val => { if (store.currentFinance) store.currentFinance.accountNumber = val }"
                    readonly
                    hint="자동입력"
                    persist-hint
                    variant="outlined"
                    persist-placeholder
                    placeholder="ex) 352-0712-2542-03"
                    color="primary"
                ></v-text-field>
              </v-col>
              <v-col cols="2">
                <v-label class="mt-2">예금 종류</v-label>
              </v-col>
              <v-col cols="3">
                <v-text-field
                    :modelValue="store.currentFinance ? store.currentFinance.depositType : ''"
                    @update:modelValue="val => { if (store.currentFinance) store.currentFinance.depositType = val }"
                    readonly
                    hint="자동입력"
                    persist-hint
                    variant="outlined"
                    persist-placeholder
                    placeholder="ex)북예금"
                    color="primary"
                ></v-text-field>
              </v-col>
            </v-row>
          </v-col>
        </v-row>
      </template>
      <PerfectScrollbar>
        <v-card title="계좌 정보 조회" class="finance-table">
          <v-card-item>
            <v-row justify="space-between" class="align-center">
              <v-col cols="12" md="5">
                <v-text-field
                    type="text"
                    variant="outlined"
                    color="primary"
                    persist-placeholder
                    placeholder="테이블 항목으로 조회 가능"
                    v-model="searchValue"
                    hide-details
                >
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
                :items="finance"
                :search-field="searchField"
                :search-value="searchValue"
                @click-row="select"
                table-class-name="customize-table"
                :rows-per-page="10"
                버튼 페이지 매김
            ></EasyDataTable>
          </v-card-text>
        </v-card>
      </PerfectScrollbar>
    </v-menu>
  </div>
</template>

<style scoped lang="scss">
.btn {
  font-size: small;
}
.finance-table {
  width: 800px;
}
</style>
