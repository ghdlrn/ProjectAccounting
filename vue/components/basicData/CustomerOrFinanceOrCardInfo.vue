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
        <v-tabs v-model="tab" bg-color="primary">
          <v-tab value="customer">거래처</v-tab>
          <v-tab value="finance">계좌</v-tab>
          <v-tab value="card">카드</v-tab>
        </v-tabs>
        <v-window v-model="tab">
          <v-window-item value="customer">
            <EasyDataTable
                :headers="customerHeaders"
                :items="filteredCustomer"
                item-key="id"
                :sort-by="sortBy"
                :sort-type="sortType"
                :search-field="searchField"
                :search-value="searchValue"
                @click-row="select('customer', $event)"
                table-class-name="customize-table"
                :rows-per-page="5"
                buttons-pagination
                class="customerTable"/>
          </v-window-item>
          <v-window-item value="finance">
            <EasyDataTable
                :headers="financeHeaders"
                :items="filteredFinance"
                item-key="id"
                :sort-by="sortBy"
                :sort-type="sortType"
                :search-field="searchField"
                :search-value="searchValue"
                @click-row="select('finance', $event)"
                table-class-name="customize-table"
                :rows-per-page="5"
                buttons-pagination
                class="financeTable"/>
          </v-window-item>
          <v-window-item value="card">
            <EasyDataTable
                :headers="cardHeaders"
                :items="filteredCard"
                item-key="id"
                :sort-by="sortBy"
                :sort-type="sortType"
                :search-field="searchField"
                :search-value="searchValue"
                @click-row="select('card', $event)"
                table-class-name="customize-table"
                :rows-per-page="5"
                buttons-pagination
                class="cardTable"/>
          </v-window-item>
        </v-window>
      </PerfectScrollbar>
    </v-menu>
  </div>
</template>

<script setup>
import { ref, computed, onMounted, watch } from 'vue';
import { useCustomerStore } from '~/stores/accounting/customer.ts';
import { useFinanceStore } from '~/stores/accounting/finance.ts';
import { useCardStore } from '~/stores/accounting/card.ts';

const customerStore = useCustomerStore();
const financeStore = useFinanceStore();
const cardStore = useCardStore();

onMounted(() => {
  customerStore.fetchCustomer();
  financeStore.fetchFinance();
  cardStore.fetchCard();
});

const customers = computed(() => customerStore.customer);
const finances = computed(() => financeStore.finance);
const cards = computed(() => cardStore.card);

const sortBy = 'code';
const sortType = 'asc';
const searchField = ref(['code', 'name']);
const searchValue = ref('');
const customerHeaders = ref([
  { text: '거래처 코드', value: 'code', sortable: true, width: 15, fixed: true },
  { text: '거래처명', value: 'name', sortable: true, width: 15, fixed: true },
]);
const financeHeaders = ref([
  { text: '계좌 코드', value: 'code', sortable: true, width: 15, fixed: true },
  { text: '계좌명', value: 'name', sortable: true, width: 15, fixed: true },
]);
const cardHeaders = ref([
  { text: '카드 코드', value: 'code', sortable: true, width: 15, fixed: true },
  { text: '카드명', value: 'name', sortable: true, width: 15, fixed: true },
]);
const menu = ref(false);
const tab = ref('customer');

const props = defineProps({
  modelValue: {
    type: Object,
    default: () => ({ id: null, type: '', name: '' }),
  },
});

const emit = defineEmits(['update:modelValue']);

function select(type, item) {
  emit('update:modelValue', { ...item, type });
  menu.value = false;
}

function bindTopResult() {
  if (filteredItems.value.length > 0) {
    select(tab.value, filteredItems.value[0]);
  }
}

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
  return customers.value.filter((customer) => {
    return searchField.value.some((field) => {
      return String(customer[field]).toLowerCase().includes(searchValue.value.toLowerCase());
    });
  });
});

const filteredFinance = computed(() => {
  if (!searchValue.value) {
    return finances.value;
  }
  return finances.value.filter((finance) => {
    return searchField.value.some((field) => {
      return String(finance[field]).toLowerCase().includes(searchValue.value.toLowerCase());
    });
  });
});

const filteredCard = computed(() => {
  if (!searchValue.value) {
    return cards.value;
  }
  return cards.value.filter((card) => {
    return searchField.value.some((field) => {
      return String(card[field]).toLowerCase().includes(searchValue.value.toLowerCase());
    });
  });
});
</script>

<style lang="scss">
.customerTable {
  width: 400px;
}
.financeTable {
  width: 400px;
}
.cardTable {
  width: 400px;
}
</style>
