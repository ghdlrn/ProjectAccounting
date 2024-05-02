<script setup lang="ts">
import { ref, computed, onMounted, shallowRef } from 'vue';
import { useCustomers } from '~/stores/accounting/customers';

import type { Header, Item } from 'vue3-easy-data-table';
import 'vue3-easy-data-table/dist/style.css';

// icons
import {
  SearchOutlined,
  PlusOutlined,
  DownloadOutlined,
  EyeOutlined,
  DeleteOutlined,
  EditOutlined,
  CloseOutlined
} from '@ant-design/icons-vue';

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

const dialog = ref(false);

const radioGroup = ref('1');

const items2 = ref([
  'Adobe XD',
  'After Effect',
  'Angular',
  'Animation',
  'ASP.Net',
  'Bootstrap',
  'C#',
  'CC',
  'Corel draw',
  'CSS',
  'DIV',
  'Dreamweaver',
  'Figma',
  'Graphics',
  'HTML',
  'Illustrator',
  'J2Ee',
  'Java',
  'Javascript',
  'Jquery',
  'Logo design',
  'Material UI',
  'Motion',
  'MVC',
  'MySQL',
  'NodeJs',
  'npm',
  'Photoshop',
  'PHP',
  'React',
  'Redux',
  'Reduxjs & tooltit',
  'SASS',
  'SCSS',
  'SQL server',
  'SVG',
  'UI/UX',
  'User interface designing',
  'Wordpress'
]);
const multi_value = ref(['Angular', 'SCSS']);
</script>

<template>
  <div class="text-center">
    <v-menu
        :close-on-content-click="false"
        :nudge-right="40"
        lazy
        transition="scale-transition"
        offset-y
        full-width
        min-width="290px">
      <template v-slot:activator="{ props }">
        <v-text-field
            v-bind="props"
            placeholder="yyyy-MM-dd"
            persistent-placeholder
            prepend-icon="mdi-calendar"
            variant="outlined"
            readonly
            color="primary">
        </v-text-field>
      </template>
  <v-row>
    <v-col cols="12" md="12">
      <v-card elevation="0" variant="outlined" class="withbg">
        <v-card-item>
          <v-row justify="space-between" class="align-center">
            <v-col cols="12" md="3">
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
              :items="items"
              table-class-name="customize-table"
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
    </v-menu>
  </div>
</template>
<style lang="scss">
.customer-modal {
  width: calc(100% - 48px);
  min-width: 340px;
  max-width: 880px;
}
</style>
