
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
            readonly
            placeholder="ex) 강남 세무서"
            persistent-placeholder
            prepend-icon="mdi-office-building"
            variant="outlined"
            color="primary">
        </v-text-field>
      </template>
      <PerfectScrollbar>
      <v-card title="사업장 세무서 조회" class="taxOfficeTable">
        <v-card-item>
          <v-row justify="space-between" class="align-center">
            <v-col cols="12" md="5">
              <v-text-field
                  type="text"
                  variant="outlined"
                  color="primary"
                  persistent-placeholder
                  placeholder="테이블 항목으로 조회 가능"
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
                :items="taxOffices"
                :search-field="searchField"
                :search-value="searchValue"
                @click-row="select"
                table-class-name="customize-table"
                :rows-per-page="10"
                buttons-pagination
          ></EasyDataTable>

        </v-card-text>
      </v-card>
      </PerfectScrollbar>
    </v-menu>
  </div>
</template>

<script setup>
import { ref, computed, onMounted, watch} from 'vue';  // onMounted : 반응상태 관리, 계산된속성 생성
import { useTaxOfficeStore } from '~/stores/accounting/basicdata/taxOffice.ts'
import {SearchOutlined} from "@ant-design/icons-vue";
const store = useTaxOfficeStore();

onMounted(() => {
  store.fetchTaxOffice(); //스토어에서  fetchTaxOffices함수실행시켜 세무서 정보 가져옴
});
const taxOffices = computed(() => store.taxOffice);

const searchField = ref(['name', 'id', 'jurisdiction']);
const searchValue = ref('');
const headers = ref( [
  { text: '세무서 코드', value: 'id', sortable: true, width: 15, fixed: true },
  { text: '세무서명', value: 'name', sortable: true, width: 15, fixed: true },
  { text: '관할구역', value: 'jurisdiction', sortable: true, width: 50, fixed: true }
]);

const menu = ref(false);

const props = defineProps({
  modelValue: {
    type: Object,
    default: () => null
  }
});

const emit = defineEmits(['update:modelValue']);

function select(item) {
  emit('update:modelValue', item);
  store.setSelectedTaxOffice(item);
  menu.value = false
}

watch(() => props.modelValue, (newValue) => {
  store.setSelectedTaxOffice(newValue);
}, { immediate: true });

const displayValue = computed({
  get: () => props.modelValue ? props.modelValue.name : '',
  set: (value) => {
    store.setSelectedTaxOffice(value);
  }
});
</script>

<style lang="scss">
.taxOfficeTable {
  width: 800px;
}
</style>