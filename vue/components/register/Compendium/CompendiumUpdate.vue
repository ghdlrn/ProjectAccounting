<script setup>
import { ref, watch } from 'vue';
import { useCompendiumStore } from '~/stores/accounting/compendium';
import UiParentCard from '~/components/shared/UiParentCard.vue';

const compendiumStore = useCompendiumStore();

const props = defineProps({
  compendium: Object,
});

const content = ref('');
const note = ref('');

watch(props, () => {
  content.value = props.compendium.content;
  note.value = props.compendium.note;
}, { immediate: true });

const updateCompendium = () => {
  const updatedData = {
    ...props.compendium,
    content: content.value,
    note: note.value,
  };
  compendiumStore.updateCompendium(updatedData);
};
</script>

<template>
  <UiParentCard title="적요 수정">
    <v-card>
      <v-card-text>
        <v-form>
          <v-text-field
              v-model="content"
              label="내용"
              variant="outlined"
              color="primary"
          />
          <v-textarea
              v-model="note"
              label="비고"
              variant="outlined"
              color="primary"
              auto-grow
              clearable
          />
        </v-form>
      </v-card-text>
      <v-card-actions>
        <v-btn color="primary" @click="updateCompendium">수정</v-btn>
        <v-btn color="secondary" @click="$emit('closeDialog')">취소</v-btn>
      </v-card-actions>
    </v-card>
  </UiParentCard>
</template>

<style scoped>
</style>
