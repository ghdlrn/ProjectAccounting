<script setup lang="ts">
import { ref, computed } from 'vue';
import { CalendarOutlined, EditOutlined, PlusOutlined, SearchOutlined, DeleteOutlined } from '@ant-design/icons-vue';
import UiParentCard from "~/components/shared/UiParentCard.vue";

// datepicker
const selectedDate = ref(null);
const selectedDate1 = ref(null);
const computedDateFormattedMomentjs = computed(() => {
  return selectedDate.value;
});

// table data
type TableType = {
  number: number;
  name: string;
  description: string;
  qty: number;
  price: string;
};

const tableData = ref<TableType[]>([
  {
    number: 1,
    name: 'Item name',
    description: 'Description',
    qty: 1,
    price: '1.00'
  }
]);

function tableItem() {
  const newItem: TableType = {
    number: tableData.value.length + 1,
    name: 'Item name',
    description: 'Description',
    qty: 1,
    price: '1.00'
  };

  tableData.value.push(newItem);
}

function deleteRow(index: number) {
  tableData.value.splice(index, 1);
}
</script>

<template>
  <UiParentCard title="일반 전표 등록" class="reference" >
  <v-row>
    <v-col cols="12" md="12">
      <v-card variant="outlined" class="withbg">
        <v-card-item>
          <v-row>
            <v-col cols="12" md="3">
              <v-label class="mb-2">날짜</v-label>
              <v-menu :close-on-content-click="false">
                <template v-slot:activator="{ props }">
                  <v-text-field
                      single-line
                      hide-details
                      variant="outlined"
                      v-bind="props"
                      v-model="computedDateFormattedMomentjs"
                      label="Selected Date"
                      readonly
                      color="primary"
                  >
                    <template v-slot:append-inner>
                      <CalendarOutlined />
                    </template>
                  </v-text-field>
                </template>
                <v-date-picker v-model="selectedDate" hide-header color="primary"></v-date-picker>
              </v-menu>
            </v-col>
<!--.table--------------------------------------------------------------------------------------------------------------->
            <v-col cols="12">
              <v-table class="bordered-table" hover>
                <thead class="bg-containerBg">
                <tr>
                  <th class="text-left text-uppercase text-caption font-weight-bold" style="width: 50px">순번</th>
                  <th class="text-left text-uppercase text-caption font-weight-bold" style="min-width: 97px">구분</th>
                  <th class="text-left text-uppercase text-caption font-weight-bold" style="min-width: 97px">계정과목 코드</th>
                  <th class="text-left text-uppercase text-caption font-weight-bold" style="width: 200px; min-width: 100px">계정과목</th>
                  <th class="text-left text-uppercase text-caption font-weight-bold" style="width: 97px; min-width: 100px">거래처 코드</th>
                  <th class="text-right text-uppercase text-caption font-weight-bold" style="width: 200px">거래처</th>
                  <th class="text-right text-uppercase text-caption font-weight-bold" style="width: 97px">적요 코드</th>
                  <th class="text-right text-uppercase text-caption font-weight-bold" style="width: 200px">적요</th>
                  <th class="text-right text-uppercase text-caption font-weight-bold" style="width: 200px">차변</th>
                  <th class="text-right text-uppercase text-caption font-weight-bold" style="width: 200px">대변</th>
                  <th class="text-center text-uppercase text-caption font-weight-bold" style="width: 97px">삭제</th>
                </tr>
                </thead>
                <tbody>
                <tr class="text-lighttext" v-for="(item, index) in tableData" :key="index">
                  <td class="text-subtitle-1 font-weight-regular py-3">{{ item.number }}</td>
                  <td class="text-subtitle-1 font-weight-regular py-3">
                    <v-text-field variant="outlined" single-line hide-details :placeholder="item.name"></v-text-field>
                  </td>
                  <td class="text-subtitle-1 font-weight-regular py-3">
                    <v-text-field variant="outlined" single-line hide-details :placeholder="item.description"></v-text-field>
                  </td>
                  <td class="text-subtitle-1 font-weight-regular py-3">
                    <v-text-field
                        variant="outlined"
                        aria-label="quantity"
                        type="number"
                        single-line
                        hide-details
                        :model-value="item.qty"
                    ></v-text-field>
                  </td>
                  <td class="text-subtitle-1 font-weight-regular py-3">
                    <v-text-field
                        variant="outlined"
                        aria-label="price"
                        type="number"
                        single-line
                        hide-details
                        :model-value="item.price"
                    ></v-text-field>
                  </td>
                  <td class="text-subtitle-1 font-weight-regular py-3 text-right">${{ item.price }}</td>
                  <td class="text-subtitle-1 font-weight-regular py-3 text-center">
                    <v-btn color="error" icon rounded variant="text" @click="deleteRow(index)">
                      <DeleteOutlined />
                    </v-btn>
                  </td>
                </tr>
                </tbody>
              </v-table>
              <v-divider></v-divider>
              <v-row class="mt-2">
                <v-col cols="12" sm="7" md="8">
                  <v-btn color="primary" class="primary-dashed" variant="tonal" @click="tableItem()">
                    <template v-slot:prepend>
                      <PlusOutlined />
                    </template>
                    Add item
                  </v-btn>
                </v-col>
                <v-col cols="12" sm="5" md="4">
                  <v-row>
                    <v-col cols="6">
                      <v-label class="mb-2">Discount(%)</v-label>
                      <v-text-field
                          variant="outlined"
                          aria-label="discount"
                          single-line
                          hide-details
                          type="number"
                          model-value="0"
                      ></v-text-field>
                    </v-col>
                    <v-col cols="6">
                      <v-label class="mb-2">Tax(%)</v-label>
                      <v-text-field
                          variant="outlined"
                          aria-label="tax"
                          single-line
                          hide-details
                          type="number"
                          model-value="0"
                      ></v-text-field>
                    </v-col>
                  </v-row>
                  <v-list density="compact" aria-label="total list" aria-busy="true">
                    <v-list-item class="px-0">
                      <p class="text-h6 text-lightText mb-0">Sub Total:</p>
                      <template v-slot:append>
                        <p class="text-h6 mb-0">$1.00</p>
                      </template>
                    </v-list-item>
                    <v-list-item class="px-0">
                      <p class="text-h6 text-lightText mb-0">Discount:</p>
                      <template v-slot:append>
                        <p class="text-h6 mb-0 text-success">$0.01</p>
                      </template>
                    </v-list-item>
                    <v-list-item class="px-0">
                      <p class="text-h6 text-lightText mb-0">Tax:</p>
                      <template v-slot:append>
                        <p class="text-h6 mb-0">$0.00</p>
                      </template>
                    </v-list-item>
                    <v-list-item class="px-0">
                      <p class="text-subtitle-1 mb-0">Grand Total:</p>
                      <template v-slot:append>
                        <p class="text-subtitle-1 mb-0">$0.99</p>
                      </template>
                    </v-list-item>
                  </v-list>
                </v-col>
              </v-row>
            </v-col>
            <v-col cols="12">
              <v-label class="mb-2">Notes</v-label>
              <v-textarea variant="outlined" single-line hide-details placeholder="Address"></v-textarea>
            </v-col>
            <v-row class="mx-0 mb-0 mt-2 align-end">
              <v-col cols="12" md="7" lg="9">
                <div class="text-right">
                  <v-btn color="secondary" variant="tonal" disabled>Preview</v-btn>
                  <v-btn color="secondary" variant="outlined" class="mx-2">Save</v-btn>
                  <v-btn color="primary" variant="flat">Create & Send</v-btn>
                </div>
              </v-col>
            </v-row>
          </v-row>
        </v-card-item>
      </v-card>
    </v-col>
  </v-row>
  </UiParentCard>
</template>

<style lang="scss">

</style>