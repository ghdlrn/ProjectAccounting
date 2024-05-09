<script setup>
import { ref, computed, onMounted } from 'vue';
import { useCompanyStore } from '~/stores/accounting/company';
const store = useCompanyStore();

// icons
import {
  SearchOutlined,
  EyeOutlined,
  DeleteOutlined,
  EditOutlined
} from '@ant-design/icons-vue';

onMounted(() => {
  store.fetchCompanies();
});
const company = computed(() => store.fetchCompanies );

const searchField = ref(['name', 'code']);
const searchValue = ref('');
const headers = ref([
  { text: '회사 코드', value: 'code', sortable: true },
  { text: '회사명', value: 'name', sortable: true },
  { text: '사업자 등록번호', value: 'businessRegistrationNumber', sortable: true },
  { text: '조회/수정/삭제', value: 'operation' }
]);
const themeColor = ref('rgb(var(--v-theme-primary))');

const { deleteCompany } = store;

const selectedCompany = ref<Company>({});

</script>

<template>
  <PerfectScrollbar>
      <v-card title="회사 조회 및 등록">
        <v-card-item>
          <v-row justify="space-between" class="align-center">
            <v-col cols="12" md="6">
              <v-text-field
                  type="text"
                  variant="outlined"
                  color="primary"
                  persistent-placeholder
                  placeholder="회사명 검색"
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
              :items="company"
              table-class-name="customize-table"
              :theme-color="themeColor"
              :search-field="searchField"
              :search-value="searchValue"
              :rows-per-page="10"
              v-model:items-selected="selectedCompany">
            <template #item-operation="company">
              <div class="operation-wrapper">
                <v-btn icon color="secondary" variant="text" rounded>
                  <EyeOutlined />
                </v-btn>
                <v-btn icon color="primary" variant="text" rounded>
                  <EditOutlined />
                </v-btn>
                <v-btn icon color="error" variant="text" @click="deleteCompany(company.code)" rounded>
                  <DeleteOutlined />
                </v-btn>
              </div>
            </template>
          </EasyDataTable>
        </v-card-text>
      </v-card>
  </PerfectScrollbar>
</template>

<style lang="scss">
</style>
