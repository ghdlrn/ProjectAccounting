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
            v-model="displayValue"
            v-bind="props"
            variant="outlined"
            type="text"
            single-line
            hide-details
            color="primary"/>
      </template>
      <PerfectScrollbar>
      <v-card title="거래처 조회" class="customerTable">
        <v-card-item >
          <v-row justify="space-between" class="align-center">
            <v-col cols="12" md="5" >
              <v-text-field
                  type="text"
                  variant="outlined"
                  color="primary"
                  persistent-placeholder
                  placeholder="테이블 항목으로 조회 가능"
                  v-model="searchValue"
                  hide-details>
                <template v-slot:prepend-inner>
                  <SearchOutlined :style="{ fontSize: '14px' }"/>
                </template>
              </v-text-field>
            </v-col>
          </v-row>
        </v-card-item>
        <v-divider/>
        <v-card-text class="pa-0">

          <EasyDataTable
              :headers="headers"
              :items="customers"
              :search-field="searchField"
              :search-value="searchValue"
              @click-row="select"
              table-class-name="customize-table"
              :rows-per-page="10"
              buttons-pagination/>

        </v-card-text>
      </v-card>
      </PerfectScrollbar>
    </v-menu>
  </div>
</template>

<script setup>
import {ref, computed, onMounted, watch} from 'vue';
import {useCustomerStore} from '~/stores/accounting/customer.ts';
import {SearchOutlined} from '@ant-design/icons-vue';
const store = useCustomerStore();

onMounted(() => {
  store.fetchCustomer();
});
const customers = computed(() => store.customer);

const searchField = ref(['id', 'name']);
const searchValue = ref('');
const headers = ref([
  {text: '거래처 코드', value: 'id', sortable: true, width: 15, fixed: true},
  {text: '거래처명', value: 'name', sortable: true, width: 15, fixed: true},
]);

const menu = ref(false);

const props = defineProps({
  modelValue: {
    type: Object,
    default: () => null,
  },
});

const emit = defineEmits(['update:modelValue']);

function select(item) {
  emit('update:modelValue', item);
  store.setSelectedCustomer(item);
  menu.value = false;
}

watch(() => props.modelValue, (newValue) => {
      store.setSelectedCustomer(newValue);
    }, {immediate: true});

const displayValue = computed({
  get: () => (props.modelValue ? props.modelValue.name : ''),
  set: (value) => {
    store.setSelectedCustomer(value);
  },
});
</script>

<style lang="scss">
.customerTable {
  width: 800px;
}
</style>
