<script setup>
import { ref } from 'vue';

const tab = ref(null);
import UiParentCard from "~/components/shared/UiParentCard.vue";
/* ---------------------------정보 제출------------------------------*/
import { storeToRefs }  from "pinia";
import { useAccountTitleStore } from "~/stores/accounting/account-title.ts";
const accountTitleStore = useAccountTitleStore();

const { accountTitle } = storeToRefs(accountTitleStore);
const currentAccountTitle = ref(accountTitle.value || {});

const emit = defineEmits(['closeDialog']);

const saveOrUpdateAccountTitle = async () => {
  const accountTitleData = {
    ...currentAccountTitle.value,
  };
  try {
    if (currentAccountTitle.value.id) {
      await accountTitleStore.updateAccountTitle(accountTitleData);
    } else {
      await accountTitleStore.createAccountTitle(accountTitleData);
    }
    emit('closeDialog');
  } catch(error) {
    console.error("Error saving or updating accountTitle: ", error);
  }
};
/*----------------------------양식 검증------------------------------------*/
import {integerRules, nameRules, nullableRules} from "~/utils/form.ts";
/*----------------------------초기화---------------------------------------*/
const resetForm = () => {
  Object.keys(currentAccountTitle.value).forEach(key => {
    currentAccountTitle.value[key] = '';
  });
};
</script>

<template>
  <UiParentCard title="계정과목 등록" class="accountTitle-form">

    <v-card>
      <v-tabs v-model="tab" bg-color="primary">
        <v-tab value="one">기본 정보</v-tab>
      </v-tabs>
      <v-form @submit.prevent="saveOrUpdateAccountTitle">
        <v-card-text>
          <v-window v-model="tab">
            <!--공통속성-->
            <!--tab1-->
            <v-window-item value="one" class="accountTitle-window">
              <!--------------------------------------1줄-------------------------------->
              <v-row>
                <v-col cols="3">
                  <v-row>
                    <v-col cols="4" offset="1">
                      <v-label class="mt-2">과목코드</v-label>
                    </v-col>
                    <v-col cols="7">
                      <v-text-field
                          v-model="currentAccountTitle.code"
                          :rules="integerRules"
                          persistent-placeholder
                          placeholder="ex) 1010"
                          variant="outlined"
                          color="primary">
                      </v-text-field>
                    </v-col>
                  </v-row>
                </v-col>
                <v-col cols="5">
                  <v-row>
                    <v-col cols="3">
                      <v-label class="mt-2">계정과목</v-label>
                    </v-col>
                    <v-col cols="8">
                      <v-text-field
                          v-model="currentAccountTitle.name"
                          :rules="nameRules"
                          variant="outlined"
                          persistent-placeholder
                          placeholder="ex) 자산"
                          color="primary">
                      </v-text-field>
                    </v-col>
                  </v-row>
                </v-col>
                <v-col cols="3">
                  <v-row>
                    <v-col cols="5">
                      <v-label class="mt-2">대차구분</v-label>
                    </v-col>
                    <v-col cols="7">
                      <v-select
                          v-model="currentAccountTitle.balanceClassification"
                          :items="['차변', '대변']"
                          label="ex) 차변"
                          :rules="nullableRules"
                          variant="outlined"
                          color="primary"
                          autofocus
                      ></v-select>
                    </v-col>
                  </v-row>
                </v-col>
              </v-row>
            </v-window-item>

            <v-row>
              <v-col cols="2" offset="8" class="pt-8">
                <v-btn @click="resetForm" color="warning" size="large">초기화</v-btn>
              </v-col>
              <v-col cols="2" class="pt-8">
                <v-btn type="submit" color="success" size="large">등록</v-btn>
              </v-col>
            </v-row>
          </v-window>
        </v-card-text>
      </v-form>
    </v-card>

  </UiParentCard>
</template>

<style scoped lang="scss">
.accountTitle-form {
  width: 60%;
  min-height: 150px;
  min-width: 1000px;
}
.accountTitle-window {
  margin: 20px 20px 20px 20px;
  width: 100%;
  min-height: 80px;
  min-width: 800px;
}
</style>