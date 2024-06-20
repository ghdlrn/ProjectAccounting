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
            :items="filteredCompendium"
            item-key="id"
            :sort-by="sortBy"
            :sort-type="sortType"
            :search-field="searchField"
            :search-value="searchValue"
            @click-row="select"
            table-class-name="customize-table"
            :rows-per-page="5"
            buttons-pagination
            class="compendiumTable"/>
      </PerfectScrollbar>
    </v-menu>
  </div>
</template>

<script setup>
import {ref, computed, onMounted, watch} from 'vue';
import {useCompendiumStore} from '~/stores/accounting/compendium.ts';
const store = useCompendiumStore();

onMounted(() => {
  store.fetchCompendium();
});
const compendiums = computed(() => store.compendium);

const sortBy = "code";
const sortType = "asc";
const searchField = ref(['code', 'content']);
const searchValue = ref('');
const headers = ref([
  {text: '적요 코드', value: 'code', sortable: true, width: 15, fixed: true},
  {text: '적요명', value: 'content', sortable: true, width: 15, fixed: true},
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
  store.setSelectedCompendium(item);
  menu.value = false;
}

function bindTopResult() {
  if (filteredCompendium.value.length > 0) {
    select(filteredCompendium.value[0]);
  }
}

watch(() => props.modelValue, (newValue) => {
  store.setSelectedCompendium(newValue);
}, {immediate: true});

const displayValue = computed({
  get: () => (props.modelValue ? props.modelValue.name : ''),
  set: (value) => {
    searchValue.value = value;
  },
});

const filteredCompendium = computed(() => {
  if (!searchValue.value) {
    return compendiums.value;
  }
  return compendiums.value.filter(compendium => {
    return searchField.value.some(field => {
      return String(compendium[field]).toLowerCase().includes(searchValue.value.toLowerCase());
    });
  });
});
</script>

<style lang="scss">
.compendiumTable {
  width: 400px;
}
</style>
