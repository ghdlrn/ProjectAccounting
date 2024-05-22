<script setup>
import { ref } from 'vue';
import { useCompendiumStore } from "~/stores/accounting/compendium";

const props = defineProps({
  accountTitleId: Number,
});

const compendiumStore = useCompendiumStore();
const cashContent = ref('');
const cashNote = ref('');
const replacementContent = ref('');
const replacementNote = ref('');

const createCompendium = async () => {
  try {
    if (!cashContent.value && !replacementContent.value) {
      alert('현금적요 또는 대체적요 중 하나는 반드시 입력되어야 합니다.');
      return;
    }
    const data = {
      cashContent: cashContent.value,
      cashNote: cashNote.value,
      replacementContent: replacementContent.value,
      replacementNote: replacementNote.value
    };
    const createdCompendium = await compendiumStore.createCompendium(props.accountTitleId, data);
    cashCode.value = createdCompendium.cashCode;
    replacementCode.value = createdCompendium.replacementCode;
    alert('적요 정보가 등록되었습니다');
    emit('closeDialog');
  } catch (error) {
    console.error('적요 등록 실패', error);
    alert('적요 등록 실패');
  }
};

</script>

<template>
  <v-card>
    <v-card-title>적요 등록</v-card-title>
    <v-card-text>
      <v-form>
        <v-text-field v-model="cashContent" label="현금적요내용" />
        <v-text-field v-model="cashNote" label="현금적요비고" />
        <v-text-field v-model="replacementContent" label="대체적요내용" />
        <v-text-field v-model="replacementNote" label="대체적요비고" />
      </v-form>
    </v-card-text>
    <v-card-actions>
      <v-btn color="primary" @click="createCompendium">등록</v-btn>
      <v-btn color="secondary" @click="$emit('closeDialog')">취소</v-btn>
    </v-card-actions>
  </v-card>
</template>
