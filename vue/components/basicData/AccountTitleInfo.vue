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
                :items="filteredAccountTitles"
                item-key="id"
                :sort-by="sortBy"
                :sort-type="sortType"
                :search-field="searchField"
                :search-value="searchValue"
                @click-row="select"
                table-class-name="customize-table"
                :rows-per-page="5"
                buttons-pagination
                class="accountTitleTable"/>
      </PerfectScrollbar>
    </v-menu>
  </div>
</template>

<script setup>
import {ref, computed, onMounted, watch} from 'vue';
import {useAccountTitleStore} from '~/stores/accounting/account-title.ts';
const store = useAccountTitleStore();

onMounted(() => {
  store.fetchAccountTitle();
});
const accountTitles = computed(() => store.accountTitle);

const sortBy = "code";
const sortType = "asc";
const searchField = ref(['code', 'name']);
const searchValue = ref('');
const headers = ref([
  {text: '계정과목 코드', value: 'code', sortable: true, width: 15, fixed: true},
  {text: '계정과목명', value: 'name', sortable: true, width: 15, fixed: true},
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
  store.setSelectedAccountTitle(item);
  menu.value = false;
}

function bindTopResult() {
  if (filteredAccountTitles.value.length > 0) {
    select(filteredAccountTitles.value[0]);
  }
}

watch(() => props.modelValue, (newValue) => {
  store.setSelectedAccountTitle(newValue);
}, {immediate: true});

const displayValue = computed({
  get: () => (props.modelValue ? props.modelValue.name : ''),
  set: (value) => {
    searchValue.value = value;
  },
});

const filteredAccountTitles = computed(() => {
  if (!searchValue.value) {
    return accountTitles.value;
  }
  return accountTitles.value.filter(accountTitle => {
    return searchField.value.some(field => {
      return String(accountTitle[field]).toLowerCase().includes(searchValue.value.toLowerCase());
    });
  });
});
</script>

<style lang="scss">
.accountTitleTable {
  width: 400px;
}
</style>
