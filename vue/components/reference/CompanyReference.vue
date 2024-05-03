<script setup lang="ts">
import { ref, computed, onMounted, shallowRef } from 'vue';
import { useCustomers } from '~/stores/accounting/customers';

import type { Header, Item } from 'vue3-easy-data-table';
import 'vue3-easy-data-table/dist/style.css';

// icons
import {
  SearchOutlined,
  EyeOutlined,
  DeleteOutlined,
  EditOutlined
} from '@ant-design/icons-vue';

const page = ref({ title: 'Customer list' });

const store = useCustomers();

const getCustomers = computed(() => {
  return store.getCustomers;
});

onMounted(() => {
  store.fetchCustomers();
});

const searchField = ref('name');
const searchValue = ref('');

const headers: Header[] = [
  { text: 'User Info', value: 'name', sortable: true },
  { text: 'Contact', value: 'date', sortable: true },
  { text: 'Age', value: 'orders', sortable: true },
  { text: 'Country', value: 'location', sortable: true },
  { text: 'Status', value: 'status', sortable: true },
  { text: 'Action', value: 'operation' }
];
const items = ref(getCustomers);
const themeColor = ref('rgb(var(--v-theme-primary))');
const { deleteCustomer } = store;

const itemsSelected = ref<Item[]>([]);
</script>

<template>

  <v-row>
    <v-col cols="12" md="12">
      <v-card elevation="0" variant="outlined" class="withbg">
        <v-card-item>
          <v-row justify="space-between" class="align-center">
            <v-col cols="12" md="6">
              <v-text-field
                  type="text"
                  variant="outlined"
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
              :items="items"
              table-class-name="customize-table"
              :theme-color="themeColor"
              :search-field="searchField"
              :search-value="searchValue"
              :rows-per-page="10"
              v-model:items-selected="itemsSelected"
          >
            <template #item-name="{ name, email }">
              <div class="player-wrapper">
                <h6 class="text-subtitle-1 mb-0">{{ name }}</h6>
                <small class="text-h6 text-lightText">{{ email }}</small>
              </div>
            </template>
            <template #item-status="{ status }">
              <v-chip color="success" v-if="status === 1" size="small" label> Verified </v-chip>
              <v-chip color="error" v-if="status === 2" size="small" label> Rejected </v-chip>
              <v-chip color="info" v-if="status === 3" size="small" label> Pending </v-chip>
            </template>
            <template #item-operation="item">
              <div class="operation-wrapper">
                <v-btn icon color="secondary" variant="text" rounded>
                  <EyeOutlined />
                </v-btn>
                <v-btn icon color="primary" variant="text" rounded>
                  <EditOutlined />
                </v-btn>
                <v-btn icon color="error" variant="text" @click="deleteCustomer(item.name)" rounded>
                  <DeleteOutlined />
                </v-btn>
              </div>
            </template>
          </EasyDataTable>
        </v-card-text>
      </v-card>
    </v-col>
  </v-row>
</template>

<style lang="scss">
</style>
