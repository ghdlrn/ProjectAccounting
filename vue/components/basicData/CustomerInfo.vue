<template>
  <div class="text-center">
    <v-autocomplete
        v-model="selectedCustomer"
        :items="filteredCustomers"
        :search-input.sync="searchValue"
        item-text="name"
        item-value="id"
        label="코드, 계정과목명으로 조회"
        color="primary"
        @change="select"
        @keydown.enter="selectTopItem"
        solo
        hide-details
        clearable
    ></v-autocomplete>
  </div>
</template>

<script setup>
import { ref, computed, onMounted, watch } from 'vue';
import { useCustomerStore } from '~/stores/accounting/customer.ts';

const store = useCustomerStore();

onMounted(() => {
  store.fetchCustomer();
});

const customers = computed(() => store.customer);
const searchValue = ref('');

const filteredCustomers = computed(() => {
  if (!searchValue.value) {
    return customers.value;
  }
  return customers.value.filter(customer =>
      ['code', 'name'].some(field =>
          customer[field].toString().toLowerCase().includes(searchValue.value.toLowerCase())
      )
  );
});

const selectedCustomer = ref(null);

const props = defineProps({
  modelValue: {
    type: Object,
    default: () => null,
  },
});

const emit = defineEmits(['update:modelValue']);

watch(selectedCustomer, (newValue) => {
  if (newValue) {
    const customer = customers.value.find(c => c.id === newValue);
    if (customer) {
      emit('update:modelValue', customer);
      store.setSelectedCustomer(customer);
    }
  }
}, { immediate: true });

function select(item) {
  const customer = customers.value.find(c => c.id === item);
  if (customer) {
    emit('update:modelValue', customer);
    store.setSelectedCustomer(customer);
  }
}

function selectTopItem() {
  if (filteredCustomers.value.length > 0) {
    const topItem = filteredCustomers.value[0].id;
    select(topItem);
  }
}
</script>

<style lang="scss">
.customerTable {
  width: 300px;
}
</style>
