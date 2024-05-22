<script setup>
import { ref, computed, onMounted } from 'vue';
import { useCompendiumStore } from '~/stores/accounting/compendium';
import { useRoute } from 'vue-router';
import {DeleteOutlined, PlusOutlined, EditOutlined } from "@ant-design/icons-vue";
import UiParentCard from '~/components/shared/UiParentCard.vue';
import CompendiumRegister from '~/components/register/compendium/CompendiumRegister.vue';
import CompendiumUpdate from '~/components/register/compendium/CompendiumUpdate.vue';

const route = useRoute();
const compendiumStore = useCompendiumStore();
const accountTitleId = computed(() => Number(route.params.accountTitleId));

onMounted(() => {
  compendiumStore.fetchCompendium(accountTitleId.value);
});

const compendium = computed(() => compendiumStore.compendium);

const cashHeaders = [
  { text: '코드', value: 'cashCode' },
  { text: '내용', value: 'cashContent' },
  { text: '비고', value: 'cashNote' },
  { text: '수정', value: 'edit' },
  { text: '삭제', value: 'delete' }
];

const replacementHeaders = [
  { text: '코드', value: 'replacementCode' },
  { text: '내용', value: 'replacementContent' },
  { text: '비고', value: 'replacementNote' },
  { text: '수정', value: 'edit' },
  { text: '삭제', value: 'delete' }
];

const selectedCompendium = ref(null);
const dialog = ref(false);
const menu = ref(false);

const getCompendium = (item) => {
  compendiumStore.getCompendium(item.id).then(compendiumData => {
    selectedCompendium.value = compendiumData;
  });
};

const deleteCompendium = (item) => {
  if (confirm("정말로 삭제하시겠습니까?")) {
    compendiumStore.deleteCompendium(item.id);
  }
};

const createCompendium = () => {
  selectedCompendium.value = null;
  dialog.value = true;
};

const editCompendium = (item) => {
  getCompendium(item);
  dialog.value = true;
};

const saveOrUpdateCompendium = async (data) => {
  try {
    if (selectedCompendium.value?.id) {
      await compendiumStore.updateCompendium(data);
    } else {
      await compendiumStore.createCompendium(accountTitleId.value, data);
    }
    dialog.value = false;
  } catch (error) {
    console.error("Error saving or updating compendium: ", error);
  }
};

</script>

<template>
  <div class="align-container">
    <v-row>
      <v-col cols="12">
        <UiParentCard title="현금적요">
          <EasyDataTable
              :headers="cashHeaders"
              :items="compendium"
              item-key="id"
              @click-row="editCompendium"
              :rows-per-page="10"
          >
            <template v-slot:item-edit="{ item }">
              <v-btn icon @click="editCompendium(item)">
                <EditOutlined />
              </v-btn>
            </template>
            <template v-slot:item-delete="{ item }">
              <v-btn icon @click="deleteCompendium(item)">
                <DeleteOutlined />
              </v-btn>
            </template>
          </EasyDataTable>
          <v-btn @click="createCompendium" color="primary">
            <PlusOutlined /> 현금적요 등록
          </v-btn>
        </UiParentCard>
      </v-col>

      <v-col cols="12">
        <UiParentCard title="대체적요">
          <EasyDataTable
              :headers="replacementHeaders"
              :items="compendium"
              item-key="id"
              @click-row="editCompendium"
              :rows-per-page="10"
          >
            <template v-slot:item-edit="{ item }">
              <v-btn icon @click="editCompendium(item)">
                <EditOutlined />
              </v-btn>
            </template>
            <template v-slot:item-delete="{ item }">
              <v-btn icon @click="deleteCompendium(item)">
                <DeleteOutlined />
              </v-btn>
            </template>
          </EasyDataTable>
          <v-btn @click="createCompendium" color="primary">
            <PlusOutlined /> 대체적요 등록
          </v-btn>
        </UiParentCard>
      </v-col>
    </v-row>

    <v-dialog v-model="dialog" persistent max-width="600px">
      <CompendiumRegister v-if="!selectedCompendium" @save="saveOrUpdateCompendium" @close="dialog = false" />
      <CompendiumUpdate v-else :compendium="selectedCompendium" @save="saveOrUpdateCompendium" @close="dialog = false" />
    </v-dialog>
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
