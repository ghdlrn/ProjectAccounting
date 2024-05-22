<script setup>
import { ref } from 'vue';
import { useCompendiumStore } from '~/stores/accounting/compendium';
import { useRoute } from 'vue-router';
import { defineEmits } from 'vue';
import { useForm } from 'vee-validate';
import * as yup from 'yup';

const route = useRoute();
const compendiumStore = useCompendiumStore();
const accountTitleId = computed(() => Number(route.params.accountTitleId));

const emit = defineEmits(['save', 'close']);

const compendiumForm = useForm({
  initialValues: {
    cashCode: '',
    cashContent: '',
    cashNote: '',
    replacementCode: '',
    replacementContent: '',
    replacementNote: ''
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

const saveCompendium = async () => {
  try {
    await emit('save', compendiumForm.values);
  } catch (error) {
    console.error("Error saving compendium: ", error);
  }
};

</script>

<template>
  <v-card>
    <v-card-title>
      <span>적요 등록</span>
      <v-spacer></v-spacer>
      <v-btn icon @click="() => emit('close')">
        <v-icon>mdi-close</v-icon>
      </v-btn>
    </v-card-title>
    <v-card-text>
      <v-form @submit.prevent="saveCompendium">
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
          <v-btn type="submit" color="primary">등록</v-btn>
          <v-btn @click="() => emit('close')" color="secondary">취소</v-btn>
        </v-card-actions>
      </v-form>
    </v-card-text>
  </v-card>
</template>
