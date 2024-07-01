<script setup>
import { ref, computed, watch } from 'vue';
import { useCompendiumStore } from '~/stores/accounting/compendium';
import {DeleteOutlined, EditOutlined, PlusOutlined, SearchOutlined} from "@ant-design/icons-vue";
import UiParentCard from '~/components/shared/UiParentCard.vue';
import CompendiumUpdate from '~/components/register/compendium/CompendiumUpdate.vue';

const props = defineProps({
  accountTitleId: Number,
});

const compendiumStore = useCompendiumStore();
const compendium = computed(() => compendiumStore.compendium);

watch(() => props.accountTitleId, (newId) => {
  if (newId !== null) {
    compendiumStore.fetchCompendium(newId);
  }
});

const sortBy = "code";
const sortType = "asc";
const searchField = ref(['code', 'content', 'note']);
const searchValue = ref('');
const headers = [
  { text: '코드', value: 'code', sortable: true },
  { text: '내용', value: 'content', sortable: true },
  { text: '비고', value: 'note', sortable: true },
  { text: '수정 / 삭제', value: 'operation' }
];
const themeColor = ref('rgb(var(--v-theme-primary))');

const dialog = ref(false);

const deleteCompendium = (item) => {
  if (confirm("정말로 삭제하시겠습니까?")) {
    compendiumStore.deleteCompendium(item.id);
  }
};

const getCompendium = (item) => {
  compendiumStore.getCompendium(item.id).then(compendiumData => {
    dialog.value = true;
  });
};

const closeDialog = () => {
  dialog.value = false;
  compendiumStore.currentCompendium = null;
};
</script>

<template>
  <div class="align-container">
    <UiParentCard title="적요">
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
              placeholder="적요 검색"
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
          <v-dialog
              v-model="dialog"
              :close-on-content-click="false"
              lazy
              transition="scale-transition"
              origin="top right">
            <template v-slot:activator="{ props }">
              <EasyDataTable
                  v-bind="props"
                  :headers="headers"
                  :items="compendium"
                  item-key="id"
                  :sort-by="sortBy"
                  :sort-type="sortType"
                  table-class-name="customize-table"
                  :theme-color="themeColor"
                  :search-field="searchField"
                  :search-value="searchValue"
                  @click-row="getCompendium"
                  :rows-per-page="10">
                <template v-slot:item-operation="item">
                  <v-btn icon color="primary" variant="text" @click.stop="getCompendium(item)" rounded>
                    <EditOutlined />
                  </v-btn>
                  <v-btn icon color="error" variant="text" @click.stop="deleteCompendium(item)" rounded>
                    <DeleteOutlined />
                  </v-btn>
                </template>
              </EasyDataTable>
            </template>
            <CompendiumUpdate :compendium="compendiumStore.currentCompendium" @close="closeDialog" @save="closeDialog" />
          </v-dialog>
        </v-card-text>
       </v-card>
      </PerfectScrollbar>
    </UiParentCard>
  </div>
</template>

<style scoped lang="scss">
.align-container {
  width: 100%;
  display: flex;
  flex-direction: column;
  gap: 16px;
}

</style>
