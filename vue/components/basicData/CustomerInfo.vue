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
            v-model="searchValue"
            v-bind="props"
            variant="outlined"
            type="text"
            single-line
            hide-details
            persistent-placeholder
            placeholder="코드, 계정과목명으로 조회"
            color="primary"
            @focus="menu = true"
            @keydown.enter="selectTopItem"/>
      </template>
      <PerfectScrollbar>
        <v-card class="customerTable">
          <v-card-text class="pa-0">
            <EasyDataTable
                :headers="headers"
                :items="customers"
                :search-field="searchField"
                :search-value="searchValue"
                @click-row="select"
                table-class-name="customize-table"
                :rows-per-page="5"
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

const store = useCustomerStore();

onMounted(() => {
  store.fetchCustomer();
});

const customers = computed(() => store.customer);

const searchField = ref(['code', 'name']);
const searchValue = ref('');

const headers = ref([
  {text: '거래처 코드', value: 'code', sortable: true, width: 15, fixed: true},
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

const filteredCustomers = computed(() => {
  if (!searchValue.value) {
    return customers.value;
  }
  return customers.value.filter(customer =>
      searchField.value.some(field =>
          customer[field].toString().toLowerCase().includes(searchValue.value.toLowerCase())
      )
  );
});

function select(item) {
  emit('update:modelValue', item);
  store.setSelectedCustomer(item);
  menu.value = false;
}

function selectTopItem() {
  if (filteredCustomers.value.length > 0) {
    select(filteredCustomers.value[0]);
  }
}

watch(() => props.modelValue, (newValue) => {
  store.setSelectedCustomer(newValue);
}, {immediate: true});
</script>

<style lang="scss">
.customerTable {
  width: 300px;
}
</style>
