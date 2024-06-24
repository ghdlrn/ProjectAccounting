<template>
  <UiParentCard title="일반 전표 등록" class="reference">
    <v-row>
      <v-col cols="12" md="12">
        <v-card variant="outlined" class="withbg">
          <v-card-item>
            <v-row>
              <v-col cols="12" md="3">
                <v-label class="mb-2">날짜</v-label>
                <v-menu :close-on-content-click="true">
                  <template v-slot:activator="{ props }">
                    <v-text-field
                        single-line
                        hide-details
                        variant="outlined"
                        v-bind="props"
                        v-model="computedDateFormat"
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
              <v-col cols="12">
                <v-table class="bordered-table" hover>
                  <thead class="bg-containerBg">
                  <tr>
                    <th class="text-center text-uppercase text-caption font-weight-bold" style="width: 50px">번호</th>
                    <th class="text-center text-uppercase text-caption font-weight-bold" style="min-width: 150px">구분</th>
                    <th class="text-center text-uppercase text-caption font-weight-bold" style="min-width: 200px">계정과목</th>
                    <th class="text-center text-uppercase text-caption font-weight-bold" style="width: 200px; min-width: 100px">거래처/금융/카드</th>
                    <th class="text-center text-uppercase text-caption font-weight-bold" style="width: 97px">적요 코드</th>
                    <th class="text-center text-uppercase text-caption font-weight-bold" style="width: 200px">적요</th>
                    <th class="text-center text-uppercase text-caption font-weight-bold" style="width: 200px">차변</th>
                    <th class="text-center text-uppercase text-caption font-weight-bold" style="width: 200px">대변</th>
                    <th class="text-center text-uppercase text-caption font-weight-bold" style="width: 97px">삭제</th>
                  </tr>
                  </thead>
                  <tbody>
                  <tr class="text-lighttext" v-for="(item, index) in tableData" :key="index">
                    <td class="text-subtitle-1 font-weight-regular py-3">{{ item.code }}</td>
                    <td class="text-subtitle-1 font-weight-regular py-3">
                      <v-combobox
                          v-model="item.division"
                          :items="divisionItems"
                          variant="outlined"
                          color="primary"
                          aria-label="division"
                          type="text"
                          single-line
                          hide-details
                          :rules="[value => validateDivisionInput(value) ? true : 'Invalid division']"
                          @blur="item.division = validateDivisionInput(item.division)"
                          @input="item.division = validateDivisionInput(item.division)"
                      ></v-combobox>
                    </td>
                    <td class="text-subtitle-1 font-weight-regular py-3">
                      <AccountTitleInfo v-model="item.accountTitle" />
                    </td>
                    <td class="text-subtitle-1 font-weight-regular py-3">
                      <CustomerOrFinanceOrCardInfo v-model="item.selectedEntity" />
                    </td>
                    <td class="text-subtitle-1 font-weight-regular py-3">
                      <CompendiumInfo v-model="item.compendium" />
                    </td>
                    <td class="text-subtitle-1 font-weight-regular py-3">
                      <v-text-field
                          variant="outlined"
                          aria-label="compendiumContent"
                          type="text"
                          single-line
                          hide-details
                          color="primary"
                          v-model="item.compendium.content"
                      ></v-text-field>
                    </td>
                    <td class="text-subtitle-1 font-weight-regular py-3">
                      <v-text-field
                          variant="outlined"
                          aria-label="debit"
                          type="text"
                          single-line
                          hide-details
                          color="primary"
                          v-model="item.debitFormatted"
                          :readonly="['입금', '대변', '결산대변'].includes(item.division)"
                          @input="(e) => { item.debit = parseNumber(e.target.value); handleDebitChange(item); }"
                          :suffix="item.division === '입금' ? '(현금)' : ''"
                      ></v-text-field>
                    </td>
                    <td class="text-subtitle-1 font-weight-regular py-3">
                      <v-text-field
                          variant="outlined"
                          aria-label="credit"
                          type="text"
                          single-line
                          hide-details
                          color="primary"
                          v-model="item.creditFormatted"
                          :readonly="['출금', '차변', '결산차변'].includes(item.division)"
                          @input="(e) => { item.credit = parseNumber(e.target.value); handleCreditChange(item); }"
                          :suffix="item.division === '출금' ? '(현금)' : ''"
                      ></v-text-field>
                    </td>
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
                        <p class="text-subtitle-1 mb-0">차변 총 합</p>
                        <template v-slot:append>
                          <p class="text-h6 mb-0">{{ debitTotal }}</p>
                        </template>
                      </v-list-item>
                      <v-list-item class="px-0">
                        <p class="text-subtitle-1 mb-0">대변 총 합</p>
                        <template v-slot:append>
                          <p class="text-h6 mb-0">{{ creditTotal }}</p>
                        </template>
                      </v-list-item>
                      <v-list-item class="px-0">
                        <p :class="['text-h6', 'mb-0', difference === 0 ? 'text-primary' : 'text-error']">오차</p>
                        <template v-slot:append>
                          <p :class="['text-h6', 'mb-0', difference === 0 ? 'text-primary' : 'text-error']">{{ difference }}</p>
                        </template>
                      </v-list-item>
                    </v-list>
                    <v-row>
                      <v-col cols="6">
                        <v-label class="mb-2">차변 총 합</v-label>
                        <v-text-field
                            variant="outlined"
                            aria-label="debitTotal"
                            single-line
                            hide-details
                            type="number"
                            :value="debitTotal"
                            readonly
                        ></v-text-field>
                      </v-col>
                      <v-col cols="6">
                        <v-label class="mb-2">대변 총 합</v-label>
                        <v-text-field
                            variant="outlined"
                            aria-label="creditTotal"
                            single-line
                            hide-details
                            type="number"
                            :value="creditTotal"
                            readonly
                        ></v-text-field>
                      </v-col>
                    </v-row>
                  </v-col>
                </v-row>
              </v-col>
              <v-row class="mx-0 mb-0 mt-2 align-end">
                <v-col cols="12">
                  <div class="text-right">
                    <v-btn color="error" variant="flat" size="x-large" @click="deleteDocuments">삭제</v-btn>
                    <v-btn color="primary" variant="flat" size="x-large" @click="register">등록</v-btn>
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

<script setup>
import { ref, computed, watch } from 'vue';
import { CalendarOutlined, PlusOutlined, DeleteOutlined } from '@ant-design/icons-vue';
import UiParentCard from "~/components/shared/UiParentCard.vue";
import { useNormalDocumentStore } from "~/stores/accounting/normal-document";
import moment from 'moment-timezone';
import AccountTitleInfo from "~/components/basicData/AccountTitleInfo.vue";
import CompendiumInfo from "~/components/basicData/CompendiumInfo.vue";
import CustomerOrFinanceOrCardInfo from "~/components/basicData/CustomerOrFinanceOrCardInfo.vue";

const normalDocumentStore = useNormalDocumentStore();

const selectedDate = ref(moment().tz('Asia/Seoul').subtract(0, 'days').toDate());
const computedDateFormat = computed(() => moment(selectedDate.value).format('YYYY-MM-DD'));

watch(selectedDate, (newDate) => {
  normalDocumentStore.fetchNormalDocument(newDate);
});

const tableData = computed(() => normalDocumentStore.normalDocument);

let currentCode = 1;
function generateCode() {
  let debitSum = 0;
  let creditSum = 0;
  let foundMatchingSet = false;
  for (const item of tableData.value) {
    if (['차변', '결산차변'].includes(item.division)) {
      debitSum += Number(item.debit);
    } else if (['대변', '결산대변'].includes(item.division)) {
      creditSum += Number(item.credit);
    } else if (['출금'].includes(item.division)) {
      debitSum += Number(item.debit);
      creditSum += Number(item.debit);
    } else if (['입금'].includes(item.division)) {
      creditSum += Number(item.credit);
      debitSum += Number(item.credit);
    }
  }
  if (debitSum === creditSum && debitSum !== 0) {
    foundMatchingSet = true;
  }
  if (foundMatchingSet) {
    currentCode += 1;
  }
  return currentCode;
}

function tableItem() {
  const newItem = {
    code: generateCode(),
    date: new Date(),
    division: '',
    accountTitle: '',
    selectedEntity: { id: null, type: '' },
    compendium: { content: '', code: null, id: null, note: '' },
    debit: 0,
    credit: 0,
    debitFormatted: '',
    creditFormatted: ''
  };
  normalDocumentStore.normalDocument.push(newItem);
}

function deleteRow(index) {
  tableData.value.splice(index, 1);
}

const debitTotal = computed(() => tableData.value.reduce((sum, item) => sum + Number(item.debit), 0));
const creditTotal = computed(() => tableData.value.reduce((sum, item) => sum + Number(item.credit), 0));
const difference = computed(() => debitTotal.value - creditTotal.value);

async function register() {
  if (difference.value !== 0) {
    alert("차변과 대변의 합이 일치하지 않습니다");
  } else {
    const normalDocumentData = tableData.value.map(item => {
      const data = {
        code: item.code,
        date: moment(selectedDate.value).format('YYYY-MM-DD'),
        division: item.division,
        accountTitle: item.accountTitle,
        compendium: { content: item.compendium.content },
        debit: item.debit,
        credit: item.credit
      };
      // 선택된 customer, finance, card 중 하나를 전송
        if (item.selectedEntity.type === 'customer') {
          data.customer = item.selectedEntity;
        } else if (item.selectedEntity.type === 'finance') {
          data.finance = item.selectedEntity;
        } else if (item.selectedEntity.type === 'card') {
          data.card = item.selectedEntity;
        }
      return data;
    });

    try {
      await normalDocumentStore.createNormalDocument(normalDocumentData);
      console.log("전표 등록 성공");
    } catch (error) {
      console.error("전표 등록 실패", error);
    }
  }
}

async function deleteDocuments() {
  try {
    await normalDocumentStore.deleteNormalDocument(selectedDate.value);
    console.log("전표 삭제 성공");
  } catch (error) {
    console.error("전표 삭제 실패", error);
  }
}

const divisionItems = ['출금', '입금', '차변', '대변', '결산차변', '결산대변'];
const divisionMapping = {
  1: '출금',
  2: '입금',
  3: '차변',
  4: '대변',
  5: '결산차변',
  6: '결산대변'
};

function validateDivisionInput(input) {
  if (divisionItems.includes(input)) {
    return input;
  } else if (!isNaN(Number(input)) && divisionMapping[Number(input)]) {
    return divisionMapping[Number(input)];
  }
  return '';
}

function formatNumber(value) {
  if (!value) return '';
  return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
}

function parseNumber(value) {
  if (!value) return 0;
  return Number(value.replace(/,/g, ''));
}

function handleDebitChange(item) {
  if (item.division === '출금') {
    item.credit = item.debit;
  }
  item.debitFormatted = formatNumber(item.debit);
}

function handleCreditChange(item) {
  if (item.division === '입금') {
    item.debit = item.credit;
  }
  item.creditFormatted = formatNumber(item.credit);
}
</script>

<style lang="scss">
.accountTitleTable {
  width: 400px;
}
</style>
