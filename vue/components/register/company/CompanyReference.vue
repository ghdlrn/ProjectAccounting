<script setup>
import { ref, computed, onMounted } from 'vue';
import { useCompanyStore } from '~/stores/accounting/company.ts';
const store = useCompanyStore();

// icons
import {SearchOutlined, PlusOutlined, DeleteOutlined} from '@ant-design/icons-vue';
import UiParentCard from "~/components/shared/UiParentCard.vue";
import CompanyRegister from "~/components/register/company/CompanyRegister.vue";
import CompanyUpdate from "~/components/register/company/CompanyUpdate.vue";

onMounted(() => { store.fetchCompanies(); });
const company = computed(() => store.companies );

const searchField = ref(['id', 'name', 'businessRegistrationNumber']);
const searchValue = ref('');
const headers = ref([
  { text: '회사 코드', value: 'id', sortable: true },
  { text: '회사명', value: 'name', sortable: true },
  { text: '사업자 등록번호', value: 'businessRegistrationNumber', sortable: true },
  { text: '사용 회사', value: 'currentCompany', sortable: true },
  { text: '삭제', value: 'operation' }
]);
const themeColor = ref('rgb(var(--v-theme-primary))');

const selectedCompany = ref(null);

const getCompany = (item) => {
  store.getCompany(item.id).then(companyData => {
    selectedCompany.value = companyData;
  });
};

const deleteCompany = (item) => {
    if (confirm("정말로 삭제하시겠습니까?")) {
      store.deleteCompany(item.id)
    }
};

const selectCompany = (item) => {
  if (!item || !item.id) {
    console.error('Invalid item selected:', item);
    return;
  }
  store.selectCompany(item.id);
};


const dialog = ref(false);
const menu = ref(false);

</script>

<template>

  <UiParentCard title="회사 조회" class="reference" >
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
                  placeholder="회사 검색"
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
                      회사 등록
                    </v-btn>
                  </template>
                  <CompanyRegister @closeDialog="dialog = false" />
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
                  :items="company"
                  table-class-name="customize-table"
                  :theme-color="themeColor"
                  :search-field="searchField"
                  :search-value="searchValue"
                  @click-row="getCompany"
                  :rows-per-page="10">
                <template #item-currentCompany="item">
                  <v-btn color="success" v-if="item.currentCompany" size="small" label variant="outlined"> 사용 </v-btn>
                  <v-btn color="error" v-if="!item.currentCompany" size="small" label variant="outlined" @click.stop="selectCompany(item)"> 미사용 </v-btn>
                </template>
                <template #item-operation="item">
                  <div class="operation-wrapper">
                    <v-btn icon color="error" variant="text" @click.stop="deleteCompany(item)" rounded>
                      <DeleteOutlined  />
                    </v-btn>
                  </div>
                </template>
              </EasyDataTable>
            </template>
              <CompanyUpdate :company="selectedCompany" @closeDialog="menu = false"/>
          </v-menu>
        </v-card-text>
      </v-card>
    </PerfectScrollbar>
  </UiParentCard>
</template>

<style lang="scss">
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
.company-form {
  width: 100%;
  height: 80%;
  min-height: 800px;
}
</style>
