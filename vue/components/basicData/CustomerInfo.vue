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
            color="primary"
            @keyup.enter="bindTopResult"/>
      </template>
      <PerfectScrollbar>
        <EasyDataTable
            :headers="headers"
            :items="filteredCustomer"
            item-key="id"
            :sort-by="sortBy"
            :sort-type="sortType"
            :search-field="searchField"
            :search-value="searchValue"
            @click-row="select"
            table-class-name="customize-table"
            :rows-per-page="5"
            buttons-pagination
            class="customerTable"/>
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

const sortBy = "code";
const sortType = "asc";
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

function select(item) {
  emit('update:modelValue', item);
  store.setSelectedCustomer(item);
  menu.value = false;
}

function bindTopResult() {
  if (filteredCustomer.value.length > 0) {
    select(filteredCustomer.value[0]);
  }
}

watch(() => props.modelValue, (newValue) => {
  store.setSelectedCustomer(newValue);
}, {immediate: true});

const displayValue = computed({
  get: () => (props.modelValue ? props.modelValue.name : ''),
  set: (value) => {
    searchValue.value = value;
  },
});

const filteredCustomer = computed(() => {
  if (!searchValue.value) {
    return customers.value;
  }
  return customers.value.filter(customer => {
    return searchField.value.some(field => {
      return String(customer[field]).toLowerCase().includes(searchValue.value.toLowerCase());
    });
  });
});
</script>

<style lang="scss">
.customerTable {
  width: 400px;
}
</style>
