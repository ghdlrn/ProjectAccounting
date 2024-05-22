<script setup>
import { ref, watch, onMounted } from 'vue';
import { useCompendiumStore } from '~/stores/accounting/compendium';
import { useForm } from 'vee-validate';
import * as yup from 'yup';

const props = defineProps({
  compendium: {
    type: Object,
    required: true
  }
});

const emit = defineEmits(['save', 'close']);

const compendiumForm = useForm({
  initialValues: {
    cashCode: props.compendium.cashCode,
    cashContent: props.compendium.cashContent,
    cashNote: props.compendium.cashNote,
    replacementCode: props.compendium.replacementCode,
    replacementContent: props.compendium.replacementContent,
    replacementNote: props.compendium.replacementNote
  },
  validationSchema: yup.object({
    cashCode: yup.string().required('현금적요 코드를 입력해주세요.'),
    cashContent: yup.string().required('현금적요 내용을 입력해주세요.'),
    cashNote: yup.string(),
    replacementCode: yup.string().required('대체적요 코드를 입력해주세요.'),
    replacementContent: yup.string().required('대체적요 내용을 입력해주세요.'),
    replacementNote: yup.string()
  })
});

watch(() => props.compendium, (newCompendium) => {
  compendiumForm.resetForm({
    values: {
      cashCode: newCompendium.cashCode,
      cashContent: newCompendium.cashContent,
      cashNote: newCompendium.cashNote,
      replacementCode: newCompendium.replacementCode,
      replacementContent: newCompendium.replacementContent,
      replacementNote: newCompendium.replacementNote
    }
  });
});

const updateCompendium = async () => {
  try {
    await emit('save', { ...compendiumForm.values, id: props.compendium.id });
  } catch (error) {
    console.error("Error updating compendium: ", error);
  }
};

</script>

<template>
  <v-card>
    <v-card-title>
      <span>적요 수정</span>
      <v-spacer></v-spacer>
      <v-btn icon @click="() => emit('close')">
        <v-icon>mdi-close</v-icon>
      </v-btn>
    </v-card-title>
    <v-card-text>
      <v-form @submit.prevent="updateCompendium">
        <v-row>
          <v-col cols="6">
            <v-text-field
                v-model="compendiumForm.values.cashCode"
                label="현금적요 코드"
                :error-messages="compendiumForm.errors.cashCode"
            ></v-text-field>
          </v-col>
          <v-col cols="6">
            <v-text-field
                v-model="compendiumForm.values.cashContent"
                label="현금적요 내용"
                :error-messages="compendiumForm.errors.cashContent"
            ></v-text-field>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="12">
            <v-text-field
                v-model="compendiumForm.values.cashNote"
                label="현금적요 비고"
                :error-messages="compendiumForm.errors.cashNote"
            ></v-text-field>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="6">
            <v-text-field
                v-model="compendiumForm.values.replacementCode"
                label="대체적요 코드"
                :error-messages="compendiumForm.errors.replacementCode"
            ></v-text-field>
          </v-col>
          <v-col cols="6">
            <v-text-field
                v-model="compendiumForm.values.replacementContent"
                label="대체적요 내용"
                :error-messages="compendiumForm.errors.replacementContent"
            ></v-text-field>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="12">
            <v-text-field
                v-model="compendiumForm.values.replacementNote"
                label="대체적요 비고"
                :error-messages="compendiumForm.errors.replacementNote"
            ></v-text-field>
          </v-col>
        </v-row>
        <v-card-actions>
          <v-btn type="submit" color="primary">수정</v-btn>
          <v-btn @click="() => emit('close')" color="secondary">취소</v-btn>
        </v-card-actions>
      </v-form>
    </v-card-text>
  </v-card>
</template>
