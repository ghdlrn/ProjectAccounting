<script setup>
import { ref, computed, onMounted } from 'vue';
import { useCustomerStore } from "~/stores/accounting/customer";
const store = useCustomerStore();

import {DeleteOutlined, PlusOutlined, SearchOutlined} from "@ant-design/icons-vue";
import UiParentCard from "~/components/shared/UiParentCard.vue";
import CustomerRegister from "~/components/register/customer/CustomerRegister.vue";
import CustomerUpdate from "~/components/register/customer/CustomerUpdate.vue";

onMounted(() => { store.fetchCustomer(); });
const customer = computed(() => store.customer );

const searchField = ref(['name', 'id', 'registrationNumber', 'nameOfRepresentative', 'useStatus']);
const searchValue = ref('');
const headers = ref([
  { text: '거래처 코드', value: 'id', sortable: true },
  { text: '거래처명', value: 'name', sortable: true },
  { text: '등록번호 / 유형', value: 'registrationNumber', sortable: true },
  { text: '대표자명', value: 'nameOfRepresentative', sortable: true },
  { text: '비고', value: 'note' },
  { text: '사용여부', value: 'useStatus', sortable: true },
  { text: '삭제', value: 'operation' }
]);
const themeColor = ref('rgb(var(--v-theme-primary))');

const selectedCustomer = ref(null);

const getCustomer = (item) => {
  store.getCustomer(item.id).then(customerData => {
    selectedCustomer.value = customerData;
  });
};

const deleteCustomer = (item) => {
  if (confirm("정말로 삭제하시겠습니까?")) {
    store.deleteCustomer(item.id)
  }
};

const dialog = ref(false);
const menu = ref(false);
</script>

<template>
  <UiParentCard title="거래처 조회" class="reference" >
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
                  placeholder="거래처 검색"
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
                      거래처 등록
                    </v-btn>
                  </template>
                  <CustomerRegister @closeDialog="dialog = false" />
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
                  :items="customer"
                  table-class-name="customize-table"
                  :theme-color="themeColor"
                  :search-field="searchField"
                  :search-value="searchValue"
                  @click-row="getCustomer"
                  :rows-per-page="10">
                <template v-slot:item-registrationNumber="{ registrationNumber, registrationNumberType }">
                  <div class="player-wrapper">
                    <small class="text-h6 text-lightText">{{ registrationNumber }}</small>
                    <h6 class="text-subtitle-2 font-weight-thin text-medium-emphasis text-grey">{{ registrationNumberType }}</h6>
                  </div>
                </template>
                <template v-slot:item-useStatus="{ useStatus }">
                  <v-chip color="success" v-if="useStatus === 'USE'" size="small" label>사용 </v-chip>
                  <v-chip color="error" v-if="useStatus === 'UNUSED'" size="small" label> 미사용 </v-chip>
                </template>
                <template v-slot:item-operation="item">
                  <div class="operation-wrapper">
                    <v-btn icon color="error" variant="text" @click.stop="deleteCustomer(item)" rounded>
                      <DeleteOutlined  />
                    </v-btn>
                  </div>
                </template>
              </EasyDataTable>
            </template>
            <CustomerUpdate :customer="selectedCustomer" @closeDialog="menu = false"/>
          </v-menu>
        </v-card-text>
      </v-card>
    </PerfectScrollbar>
  </UiParentCard>
</template>

<style scoped lang="scss">
.reference {
  width: 60%;
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