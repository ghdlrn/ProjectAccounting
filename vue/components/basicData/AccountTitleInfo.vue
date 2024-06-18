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
            color="primary"/>
      </template>
      <PerfectScrollbar>
        <v-card title="계정과목 조회" class="accountTitleTable">
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
                :items="accountTitles"
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
import {useAccountTitleStore} from '~/stores/accounting/account-title.ts';
import {SearchOutlined} from '@ant-design/icons-vue';
const store = useAccountTitleStore();

onMounted(() => {
  store.fetchAccountTitle();
});
const accountTitles = computed(() => store.accountTitle);

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

watch(() => props.modelValue, (newValue) => {
  store.setSelectedAccountTitle(newValue);
}, {immediate: true});

const displayValue = computed({
  get: () => (props.modelValue ? props.modelValue.name : ''),
  set: (value) => {
    store.setSelectedAccountTitle(value);
  },
});
</script>

<style lang="scss">
.accountTitleTable {
  width: 800px;
}
</style>
