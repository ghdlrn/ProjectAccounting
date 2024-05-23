<script setup>
import { ref, computed, onMounted } from 'vue';
import { useAccountTitleStore } from "~/stores/accounting/account-title.ts";
const accountStore = useAccountTitleStore();

import {DeleteOutlined, PlusOutlined, SearchOutlined, EditOutlined, FileAddOutlined } from "@ant-design/icons-vue";
import UiParentCard from "~/components/shared/UiParentCard.vue";
import AccountTitleRegister from "~/components/register/account-title/AccountTitleRegister.vue";
import AccountTitleUpdate from "~/components/register/account-title/AccountTitleUpdate.vue";
import CompendiumRegister from "~/components/register/compendium/CompendiumRegister.vue";

onMounted(() => { accountStore.fetchAccountTitle(); });
const accountTitle = computed(() => accountStore.accountTitle );

const sortBy = "code";
const sortType = "asc";
const searchField = ref(['code', 'name', 'balanceClassification']);
const searchValue = ref('');
const headers = ref([
  { text: '과목코드', value: 'code', sortable: true },
  { text: '계정과목', value: 'name', sortable: true },
  { text: '대차구분', value: 'balanceClassification', sortable: true },
  { text: '적요등록', value: 'operation' },
  { text: '삭제', value: 'useStatus' }
]);
const themeColor = ref('rgb(var(--v-theme-primary))');

const selectedAccountTitle = ref(null);
const dialog = ref(false);
const menu = ref(false);
const compendiumDialog = ref(false);

const getAccountTitle = (item) => {
  accountStore.getAccountTitle(item.id).then(accountTitleData => {
    selectedAccountTitle.value = accountTitleData;
  });
  menu.value = true;
};

const deleteAccountTitle = (item) => {
  if (confirm("정말로 삭제하시겠습니까?")) {
    accountStore.deleteAccountTitle(item.id)
  }
};

const openCompendium = (item) => {
  selectedAccountTitle.value = item;
  compendiumDialog.value = true;
};

</script>

<template>
  <UiParentCard title="계정과목 조회" class="reference" >
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
                  placeholder="계정과목 검색"
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
                      계정과목 등록
                    </v-btn>
                  </template>
                  <template v-if="dialog">
                    <AccountTitleRegister @closeDialog="dialog = false" />
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
                  :items="accountTitle"
                  :sort-by="sortBy"
                  :sort-type="sortType"
                  table-class-name="customize-table"
                  :theme-color="themeColor"
                  :search-field="searchField"
                  :search-value="searchValue"
                  @click-row="getAccountTitle"
                  :rows-per-page="10">
                <template v-slot:item-operation="item">
                  <div class="operation-wrapper">
                     <v-btn icon color="primary" variant="text" @click.stop="openCompendium(item)" rounded >
                       <FileAddOutlined />
                     </v-btn>
                  </div>
                </template>
                <template v-slot:item-useStatus="item">
                  <div class="operation-wrapper">
                    <v-btn icon color="primary" variant="text" v-if="item.useStatus === null" @click.stop="getAccountTitle(item)" rounded>
                      <EditOutlined />
                    </v-btn>
                    <v-btn icon color="error" variant="text"  v-if="item.useStatus === null" @click.stop="deleteAccountTitle(item)" rounded>
                      <DeleteOutlined />
                    </v-btn>
                  </div>
                </template>
              </EasyDataTable>
            </template>
            <AccountTitleUpdate :accountTitle="selectedAccountTitle" @closeDialog="menu = false"/>
          </v-menu>
        </v-card-text>
      </v-card>
    </PerfectScrollbar>
  </UiParentCard>

  <v-dialog v-model="compendiumDialog" max-width="600px">
    <CompendiumRegister :accountTitleId="selectedAccountTitle.id" @closeDialog="compendiumDialog = false" />
  </v-dialog>
</template>

<style scoped lang="scss">
.reference {
  min-height: 850px;
}
.register {
  height: 80%;
  min-height: 1300px;
}
</style>