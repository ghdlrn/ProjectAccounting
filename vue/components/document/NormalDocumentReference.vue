<script setup lang="ts">
import { ref, computed, watch } from 'vue';
import { CalendarOutlined, PlusOutlined, DeleteOutlined } from '@ant-design/icons-vue';
import UiParentCard from "~/components/shared/UiParentCard.vue";
import { useNormalDocumentStore } from "~/stores/accounting/normal-document";
import type { NormalDocument } from "~/types/accounting/normal-document"

// Initialize the store
const normalDocumentStore = useNormalDocumentStore();

// Set default date to today
const selectedDate = ref(new Date());
const computedDateFormattedMomentjs = computed(() => {
  return selectedDate.value;
});

// Fetch documents when date changes
watch(selectedDate, (newDate) => {
  normalDocumentStore.fetchNormalDocument(newDate);
});

// Populate table data from store
const tableData = computed(() => normalDocumentStore.normalDocument);

function tableItem() {
  const newItem: NormalDocument = {
    code: tableData.value.length + 1,
    date: new Date(),
    division: '',
    accountTitle: { code: '', name: '' },
    customer: { code: '', name: '' },
    compendium: { code: '', content: '' },
    debit: 0,
    credit: 0
  };

  normalDocumentStore.normalDocument.push(newItem);
}

function deleteRow(index: number) {
  tableData.value.splice(index, 1);
}
</script>

<template>
  <UiParentCard title="일반 전표 등록" class="reference">
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
                        color="primary">
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
                    <th class="text-center text-uppercase text-caption font-weight-bold" style="width: 50px">번호</th>
                    <th class="text-center text-uppercase text-caption font-weight-bold" style="min-width: 97px">구분</th>
                    <th class="text-center text-uppercase text-caption font-weight-bold" style="min-width: 97px">계정과목 코드</th>
                    <th class="text-center text-uppercase text-caption font-weight-bold" style="width: 200px; min-width: 100px">계정과목</th>
                    <th class="text-center text-uppercase text-caption font-weight-bold" style="width: 97px; min-width: 100px">거래처 코드</th>
                    <th class="text-center text-uppercase text-caption font-weight-bold" style="width: 200px">거래처</th>
                    <th class="text-center text-uppercase text-caption font-weight-bold" style="width: 97px">적요 코드</th>
                    <th class="text-center text-uppercase text-caption font-weight-bold" style="width: 200px">적요</th>
                    <th class="text-center text-uppercase text-caption font-weight-bold" style="width: 200px">차변</th>
                    <th class="text-center text-uppercase text-caption font-weight-bold" style="width: 200px">대변</th>
                    <th class="text-center text-uppercase text-caption font-weight-bold" style="width: 97px">삭제</th>
                  </tr>
                  </thead>
                  <tbody>
                  <tr class="text-lighttext" v-for="(item, index) in tableData" :key="index">
                    <!--번호-->
                    <td class="text-subtitle-1 font-weight-regular py-3">{{ item.code }}</td>
                    <!--구분-->
                    <td class="text-subtitle-1 font-weight-regular py-3">
                      <v-text-field
                          variant="outlined"
                          aria-label="quantity"
                          type="text"
                          single-line
                          hide-details
                          v-model="item.division"
                      ></v-text-field>
                    </td>
                    <!--계정과목코드-->
                    <td class="text-subtitle-1 font-weight-regular py-3">
                      <v-text-field
                          variant="outlined"
                          aria-label="quantity"
                          type="number"
                          single-line
                          hide-details
                          v-model="item.accountTitle.code"
                      ></v-text-field>
                    </td>
                    <!--계정과목-->
                    <td class="text-subtitle-1 font-weight-regular py-3">
                      <v-text-field
                          variant="outlined"
                          aria-label="quantity"
                          type="text"
                          single-line
                          hide-details
                          v-model="item.accountTitle.name"
                      ></v-text-field>
                    </td>
                    <!--거래처코드-->
                    <td class="text-subtitle-1 font-weight-regular py-3">
                      <v-text-field
                          variant="outlined"
                          aria-label="price"
                          type="number"
                          single-line
                          hide-details
                          v-model="item.customer.id"
                      ></v-text-field>
                    </td>
                    <!--거래처명-->
                    <td class="text-subtitle-1 font-weight-regular py-3">
                      <v-text-field
                          variant="outlined"
                          aria-label="price"
                          type="text"
                          single-line
                          hide-details
                          v-model="item.customer.name"
                      ></v-text-field>
                    </td>
                    <!--적요코드-->
                    <td class="text-subtitle-1 font-weight-regular py-3">
                      <v-text-field
                          variant="outlined"
                          aria-label="price"
                          type="number"
                          single-line
                          hide-details
                          v-model="item.compendium.code"
                      ></v-text-field>
                    </td>
                    <!--적요 내용-->
                    <td class="text-subtitle-1 font-weight-regular py-3">
                      <v-text-field
                          variant="outlined"
                          aria-label="price"
                          type="text"
                          single-line
                          hide-details
                          v-model="item.compendium.content"
                      ></v-text-field>
                    </td>
                    <!--차변-->
                    <td class="text-subtitle-1 font-weight-regular py-3">
                      <v-text-field
                          variant="outlined"
                          aria-label="price"
                          type="number"
                          single-line
                          hide-details
                          v-model="item.debit"
                      ></v-text-field>
                    </td>
                    <!--대변-->
                    <td class="text-subtitle-1 font-weight-regular py-3">
                      <v-text-field
                          variant="outlined"
                          aria-label="price"
                          type="number"
                          single-line
                          hide-details
                          v-model="item.credit"
                      ></v-text-field>
                    </td>
                    <!--테이블 삭제-->
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
                    <v-btn color="primary" class="primary-dashed" variant="tonal" @click="tableItem">
                      <template v-slot:prepend>
                        <PlusOutlined />
                      </template>
                      행 추가
                    </v-btn>
                  </v-col>
                  <v-col cols="12" sm="5" md="4">

                    <v-list density="compact" aria-label="total list" aria-busy="true">
                      <v-list-item class="px-0">
                        <p class="text-subtitle-1 mb-0">차변</p>
                        <template v-slot:append>
                          <p class="text-h6 mb-0"></p>
                        </template>
                      </v-list-item>
                      <v-list-item class="px-0">
                        <p class="text-subtitle-1 mb-0">대변</p>
                        <template v-slot:append>
                          <p class="text-h6 mb-0">ㅁㄴㅇㄴㅁ</p>
                        </template>
                      </v-list-item>
                      <v-list-item class="px-0">
                        <p class="text-h6 text-lightText text-error mb-0">오차</p>
                        <template v-slot:append>
                          <p class="text-h6 mb-0 text-error">ㅁㄴㅇㄴㅁㅇ</p>
                        </template>
                      </v-list-item>
                    </v-list>
                    <v-row>
                      <v-col cols="6">
                        <v-label class="mb-2">차변 총 합</v-label>
                        <v-text-field
                            variant="outlined"
                            aria-label="discount"
                            single-line
                            hide-details
                            type="number"
                        ></v-text-field>
                      </v-col>
                      <v-col cols="6">
                        <v-label class="mb-2">대변 총 합</v-label>
                        <v-text-field
                            variant="outlined"
                            aria-label="tax"
                            single-line
                            hide-details
                            type="number"
                        ></v-text-field>
                      </v-col>
                    </v-row>
                  </v-col>
                </v-row>
              </v-col>

              <v-row class="mx-0 mb-0 mt-2 align-end">
                <v-col cols="12">
                  <div class="text-right">
                    <v-btn color="primary" variant="flat" size="x-large">등록</v-btn>
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
