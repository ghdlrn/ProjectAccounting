<script setup lang="ts">
import { ref, computed, onMounted } from 'vue';
import { useCustomers } from '@/stores/data/customers';

import BaseBreadcrumb from '@/components/shared/BaseBreadcrumb.vue';
import UiParentCard from '@/components/shared/UiParentCard.vue';
import type { Header, Item } from 'vue3-easy-data-table';
import 'vue3-easy-data-table/dist/style.css';

import { EyeOutlined, DeleteOutlined, CopyOutlined, PrinterOutlined, FilterOutlined } from '@ant-design/icons-vue';
import SearchBar from "~/components/shared/SearchBar.vue";

const page = ref({ title: '회사 등록/조회' });
const breadcrumbs = ref([
  {
    title: '회사 등록/조회',
    disabled: false,
    href: '/register/company-info'
  }
]);
const checkbox1 = ref(true);
const checkbox2 = ref(false);
const checkbox3 = ref(false);

const store = useCustomers();

const getOrders = computed(() => {
  return store.getOrders;
});

onMounted(() => {
  store.fetchOrders();
});

const searchField = ref('name');
const searchValue = ref('');

const headers: Header[] = [
  { text: '회사코드', value: 'id', sortable: true },
  { text: '회사명', value: 'name', sortable: true },
  { text: '사업자/주민등록번호', value: 'company', sortable: true },
  { text: 'Status', value: 'status', sortable: true},
  { text: 'Action', value: 'operation' }
];
const items = ref(getOrders);
const themeColor = ref('rgb(153, 204, 255)');
const { deleteOrder } = store;
const itemsSelected = ref<Item[]>([]);
</script>

<template>
  <BaseBreadcrumb :title="page.title" :breadcrumbs="breadcrumbs"></BaseBreadcrumb>
  <v-row>
<!--조회, 검색-->
    <v-col cols="5">
      <UiParentCard title="회사조회">

        <v-card elevation="0" variant="outlined" class="withbg">

          <v-card-item>
            <v-row justify="space-between" class="align-center">
              <v-col cols="8">
                <SearchBar style="height: 55px" />
              </v-col>
              <v-col cols="4">
                <div class="d-flex gap-2 justify-end">
                  <v-btn icon variant="text" rounded>
                    <CopyOutlined />
                  </v-btn>
                  <v-btn icon variant="text" rounded>
                    <PrinterOutlined />
                  </v-btn>
                  <v-btn icon variant="text" rounded>
                    <FilterOutlined />
                  </v-btn>
                </div>
              </v-col>
            </v-row>
          </v-card-item>

          <v-divider></v-divider>

          <v-card-text class="pa-0">
            <EasyDataTable
                :headers="headers"
                :items="items"
                table-class-name="customize-table"
                :theme-color="themeColor"
                :search-field="searchField"
                :search-value="searchValue"
                :rows-per-page="10"
                show-index
                v-model:items-selected="itemsSelected">
              <template #item-id="{ id }">
                <div class="player-wrapper">
                  <h5 class="text-h5">#{{ id }}</h5>
                </div>
              </template>
              <template #item-name="{ name }">
                <div class="player-wrapper">
                  <h5 class="text-h5">{{ name }}</h5>
                </div>
              </template>
              <template #item-status="{ status }">
                <v-chip color="success" label v-if="status === 1" size="small"> 사용 </v-chip>
                <v-chip color="error" label v-if="status === 2" size="small"> 미사용 </v-chip>
              </template>
              <template #item-operation="item">
                <div class="operation-wrapper">
                  <v-btn icon color="secondary" variant="text" rounded>
                    <EyeOutlined />
                  </v-btn>
                  <v-btn icon color="primary" variant="text" rounded @click="deleteOrder(item.id)">
                    <DeleteOutlined />
                  </v-btn>
                </div>
              </template>
            </EasyDataTable>
          </v-card-text>

        </v-card>

      </UiParentCard>
    </v-col>



<!--회사등록-->
    <v-col>
      <UiParentCard title="회사등록">
        <v-row>
          <!-- column 1 -->
          <v-col cols="12" sm="6">
            <!-- row 1 -->
            <v-row>
              <v-col cols="12" lg="4" md="3" class="pb-md-3 pb-0">
                <v-label class="mt-2">Name</v-label>
              </v-col>
              <v-col cols="12" lg="8" md="9">
                <v-text-field
                    hint="Please enter your full name"
                    persistent-hint
                    variant="outlined"
                    persistent-placeholder
                    placeholder="Enter full name"
                    color="primary"
                ></v-text-field>
              </v-col>
            </v-row>
            <!-- row 2 -->
            <v-row class="mt-3">
              <v-col cols="12" lg="4" md="3" class="pb-md-3 pb-0">
                <v-label class="mt-2">Password</v-label>
              </v-col>
              <v-col cols="12" lg="8" md="9">
                <v-text-field
                    hint="Please enter Password"
                    persistent-hint
                    variant="outlined"
                    type="password"
                    persistent-placeholder
                    placeholder="Enter password"
                    color="primary">
                </v-text-field>
              </v-col>
            </v-row>
            <!-- row 3 -->
            <v-row class="mt-3">
              <v-col cols="12" lg="4" md="3" class="pb-md-3 pb-0">
                <v-label class="mt-0">Language</v-label>
              </v-col>
              <v-col cols="12" lg="8" md="9">
                <div class="d-flex gap-3">
                  <v-checkbox
                      density="compact"
                      v-model="checkbox1"
                      hide-details
                      color="primary"
                      label="English"
                      class="flex-grow-0"
                  ></v-checkbox>
                  <v-checkbox
                      density="compact"
                      v-model="checkbox2"
                      hide-details
                      color="primary"
                      label="French"
                      class="flex-grow-0"
                  ></v-checkbox>
                  <v-checkbox
                      density="compact"
                      v-model="checkbox3"
                      hide-details
                      color="primary"
                      label="Dutch"
                      class="flex-grow-0"
                  ></v-checkbox>
                </div>
              </v-col>
            </v-row>
          </v-col>
          <!-- column 2 -->
          <v-col cols="12" sm="6">
            <!-- row 1 -->
            <v-row>
              <v-col cols="12" lg="4" md="3" class="pb-md-3 pb-0">
                <v-label class="mt-2">Email</v-label>
              </v-col>
              <v-col cols="12" lg="8" md="9">
                <v-text-field
                    hint="Please enter your Email"
                    persistent-hint
                    variant="outlined"
                    persistent-placeholder
                    placeholder="Enter email"
                    color="primary"
                ></v-text-field>
              </v-col>
            </v-row>
            <!-- row 2 -->
            <v-row class="mt-3">
              <v-col cols="12" lg="4" md="3" class="pb-md-3 pb-0">
                <v-label class="mt-2">Profile URL</v-label>
              </v-col>
              <v-col cols="12" lg="8" md="9">
                <v-text-field
                    hint="Please enter your Profile URL"
                    persistent-hint
                    variant="outlined"
                    type="password"
                    persistent-placeholder
                    placeholder="Please enter your Profile URL"
                    color="primary">
                </v-text-field>
              </v-col>
            </v-row>
          </v-col>
        </v-row>
      </UiParentCard>
    </v-col>
  </v-row>
</template>

<style scoped lang="scss">
.customize-table {
  --easy-table-header-background-color: 66CCFF;
}
</style>