<script setup>
import { ref, computed, onMounted } from 'vue';
import { useCardStore } from "~/stores/accounting/card";
const store = useCardStore();

import {DeleteOutlined, PlusOutlined, SearchOutlined, ShoppingCartOutlined, DollarOutlined } from "@ant-design/icons-vue";
import UiParentCard from "~/components/shared/UiParentCard.vue";
import CardRegister from "~/components/register/card/CardRegister.vue";
import CardUpdate from "~/components/register/card/CardUpdate.vue";

onMounted(() => { store.fetchCard(); });
const card = computed(() => store.card );

const sortBy = "code";
const sortType = "asc";
const searchField = ref(['code', 'name', 'cardNumber', 'division', 'useStatus']);
const searchValue = ref('');
const headers = ref([
  { text: '카드사 코드', value: 'code', sortable: true },
  { text: '카드(사)명', value: 'name', sortable: true },
  { text: '카드(가맹점)번호', value: 'cardNumber', sortable: true },
  { text: '비고', value: 'note' },
  { text: '구분', value: 'division', sortable: true },
  { text: '사용여부', value: 'useStatus', sortable: true },
  { text: '삭제', value: 'operation' }
]);
const themeColor = ref('rgb(var(--v-theme-primary))');

const selectedCard = ref(null);

const getCard = (item) => {
  store.getCard(item.id).then(cardData => {
    selectedCard.value = cardData;
  });
};

const deleteCard = (item) => {
  if (confirm("정말로 삭제하시겠습니까?")) {
    store.deleteCard(item.id)
  }
};

const dialog = ref(false);
const menu = ref(false);
</script>

<template>
  <UiParentCard title="카드 조회" class="reference" >
    <PerfectScrollbar>
      <v-card>
        <v-card-item>
          <v-row justify="space-between" class="align-center">
            <v-col cols="6">
              <v-text-field
                  type="text"
                  variant="outlined"
                  color="primary"
                  persistent-placeholder
                  placeholder="카드 검색"
                  v-model="searchValue"
                  hide-details>
                <template v-slot:prepend-inner>
                  <SearchOutlined :style="{ fontSize: '14px' }" />
                </template>
              </v-text-field>
            </v-col>
            <v-col cols="3" offset="3">
              <div class="d-flex gap-2 justify-end">
                <v-dialog v-model="dialog" class="register">
                  <template v-slot:activator="{ props }">
                    <v-btn variant="flat" color="primary" v-bind="props" size="large">
                      <template v-slot:prepend>
                        <PlusOutlined />
                      </template>
                      카드 등록
                    </v-btn>
                  </template>
                  <template v-if="dialog">
                    <CardRegister @closeDialog="dialog = false" />  <!-- 수정된 부분 -->
                  </template>
                </v-dialog>
              </div>
            </v-col>
          </v-row>

        </v-card-item>

        <v-divider></v-divider>

        <v-card-text class="pa-0">
          <v-menu
              v-model="menu"
              :close-on-content-click="false"
              lazy
              transition="scale-transition"
              origin="top right">
            <template v-slot:activator="{ props }">
              <EasyDataTable
                  v-bind="props"
                  :headers="headers"
                  :items="card"
                  item-key="id"
                  :sort-by="sortBy"
                  :sort-type="sortType"
                  table-class-name="customize-table"
                  :theme-color="themeColor"
                  :search-field="searchField"
                  :search-value="searchValue"
                  @click-row="getCard"
                  :rows-per-page="10">
                <template v-slot:item-division="{ division }">
                  <v-chip color="success" v-if="division === '매입'" size="small" label variant="outlined">
                    <template v-slot:prepend>
                      <v-icon icon="mdi-cart" />
                    </template>
                    매입
                  </v-chip>
                  <v-chip color="primary" v-if="division === '매출'" size="small" label variant="outlined">
                    <template v-slot:prepend>
                      <v-icon icon="mdi-cash" />
                    </template>
                    매출
                  </v-chip>
                </template>
                <template v-slot:item-useStatus="{ useStatus }">
                  <v-chip color="success" v-if="useStatus === 'USE'" size="small" label> 사용 </v-chip>
                  <v-chip color="error" v-if="useStatus === 'UNUSED'" size="small" label> 미사용 </v-chip>
                </template>
                <template v-slot:item-operation="item">
                  <div class="operation-wrapper">
                    <v-btn icon color="error" variant="text" @click.stop="deleteCard(item)" rounded>
                      <DeleteOutlined  />
                    </v-btn>
                  </div>
                </template>
              </EasyDataTable>
            </template>
            <CardUpdate :customer="selectedCard" @closeDialog="menu = false"/>
          </v-menu>
        </v-card-text>
      </v-card>
    </PerfectScrollbar>
  </UiParentCard>
</template>

<style scoped lang="scss">
.reference {
  width: 40%;
  min-width: 900px;
  min-height: 850px;
}
.register {
  width: 50%;
  height: 80%;
  min-width: 1000px;
  min-height: 1300px;
}
</style>