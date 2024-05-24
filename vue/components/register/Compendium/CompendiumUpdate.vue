<script setup>
import { ref, watch } from 'vue';
import { useCompendiumStore } from '~/stores/accounting/compendium';
import UiParentCard from '~/components/shared/UiParentCard.vue';

const compendiumStore = useCompendiumStore();
const emit = defineEmits(['close', 'save']);

const props = defineProps({
  compendium: Object,
});

const content = ref('');
const note = ref('');

watch(() => props.compendium, (newCompendium) => {
  if (newCompendium) {
    content.value = newCompendium.content || '';
    note.value = newCompendium.note || '';
  }
}, { immediate: true });

const updateCompendium = async () => {
  const updatedData = {
    ...props.compendium,
    content: content.value,
    note: note.value,
  };
  try {
    await compendiumStore.updateCompendium(updatedData);
    emit('save');
  } catch (error) {
    console.error("Error updating compendium: ", error);
  }
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
              clearablevue n
          />
        </v-form>
      </v-card-text>
      <v-card-actions>
        <v-btn color="primary" @click="updateCompendium">수정</v-btn>
        <v-btn color="secondary" @click="$emit('close')">취소</v-btn>
      </v-card-actions>
    </v-card>
  </UiParentCard>
</template>

<style scoped>
</style>
