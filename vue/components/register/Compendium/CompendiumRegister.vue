<script setup>
import { ref } from 'vue';
import { useCompendiumStore } from "~/stores/accounting/compendium";
const compendiumStore = useCompendiumStore();
import UiParentCard from "~/components/shared/UiParentCard.vue";

const props = defineProps({
  accountTitleId: Number,
});

const content = ref('');
const note = ref('');

</script>

<template>
  <UiParentCard title="적요 등록">
  <v-card>
    <v-card-text>
          <v-form>
            <v-text-field
                v-model="content"
                label="내용"
                variant="outlined"
                color="primary"/>
            <v-textarea
                v-model="note"
                label="비고"
                variant="outlined"
                color="primary"
                auto-grow
                clearable/>
          </v-form>
    </v-card-text>
    <v-card-actions>
      <v-btn color="primary" @click="createCompendium">등록</v-btn>
      <v-btn color="secondary" @click="$emit('closeDialog')">취소</v-btn>
    </v-card-actions>
  </v-card>
  </UiParentCard>
</template>
