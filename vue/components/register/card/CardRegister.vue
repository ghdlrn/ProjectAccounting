<script setup>
import {ref} from 'vue';

const tab = ref(null);
import UiParentCard from "~/components/shared/UiParentCard.vue";
import DaumPostcode from "~/components/DaumPostcode.vue";
import DateSelect from "~/components/DateSelect.vue";
/* ---------------------------정보 제출------------------------------*/
import {storeToRefs} from "pinia";
import {useCardStore} from "~/stores/accounting/card.ts";

const cardStore = useCardStore();

const {card} = storeToRefs(cardStore);
const currentCard = ref(card.value || {});

const emit = defineEmits(['closeDialog']);

const saveOrUpdateCard = async () => {
  const cardData = {
    ...currentCard.value,
  };
  try {
    if (currentCard.value.id) {
      await cardStore.updateCard(cardData);
    } else {
      await cardStore.createCard(cardData);
    }
    emit('closeDialog');
  } catch (error) {
    console.error("Error saving or updating card: ", error);
  }
};
/*----------------------------양식 검증------------------------------------*/
import {nameRules, nullableRules, numberRules, integerRules} from "~/utils/form.ts";
</script>

<template>
  <UiParentCard title="카드 등록">

    <v-card class="card-form">
      <v-tabs v-model="tab" bg-color="primary">
        <v-tab value="one">기본 정보</v-tab>
        <v-tab value="two">매입</v-tab>
        <v-tab value="three">매출</v-tab>
      </v-tabs>
      <v-form @submit.prevent="saveOrUpdateCard">
        <v-card-text>
          <v-window v-model="tab">
            <!--공통속성-->
            <!--tab1-->
            <v-window-item value="one">
              <!--------------------------------------1줄-------------------------------->
              <v-row>
                <v-col cols="4">
                  <v-row>
                    <v-col cols="4">
                      <v-label class="mt-2">카드사명</v-label>
                    </v-col>
                    <v-col cols="8">
                      <v-text-field
                          v-model="currentCard.name"
                          :rules="nameRules"
                          variant="outlined"
                          persistent-placeholder
                          placeholder="ex) 삼성카드"
                          color="primary">
                      </v-text-field>
                    </v-col>
                  </v-row>
                </v-col>
                <v-col cols="8">
                  <v-row>
                    <v-col cols="2">
                      <v-label class="mt-2">카드(가맹점) 번호</v-label>
                    </v-col>
                    <v-col cols="4">
                      <v-text-field
                          v-model="currentCard.cardNumber"
                          :rules="nullableRules"
                          variant="outlined"
                          persistent-placeholder
                          placeholder="ex) OOOO-OOOO-OOOO-OOOO"
                          color="primary">
                      </v-text-field>
                    </v-col>
                    <v-col cols="2">
                      <v-label class="mt-2">구분</v-label>
                    </v-col>
                    <v-col cols="4">
                      <v-text-field
                          v-model="currentCard.division"
                          variant="outlined"
                          persistent-placeholder
                          placeholder="ex) 매입, 매출"
                          color="primary">
                      </v-text-field>
                    </v-col>
                  </v-row>
                </v-col>
              </v-row>
              <!--------------------------2줄---------------------------------------------->
              <v-row>
                <v-col cols="8">
                  <v-row>
                    <v-col cols="2">
                      <v-label class="mt-2">비고</v-label>
                    </v-col>
                    <v-col cols="10">
                      <v-text-field
                          v-model="currentCard.note"
                          hint="주석"
                          persistent-hint
                          variant="outlined"
                          persistent-placeholder
                          placeholder="ex) 출장 카드"
                          color="primary">
                      </v-text-field>
                    </v-col>
                  </v-row>
                </v-col>
                <v-col cols="4">
                  <v-row>
                    <v-col cols="4">
                      <v-label class="mt-3">사용 여부</v-label>
                    </v-col>
                    <v-col cols="8">
                      <v-switch
                          v-model="currentCard.useStatus"
                          false-value="PURCHASE"
                          true-value="SALES"
                          color="success">
                      </v-switch>
                    </v-col>
                  </v-row>
                </v-col>
              </v-row>
              <!--------------------------3줄---------------------------------------------->
              <v-row>
                <v-col cols="5">
                  <v-row>
                    <v-col cols="3">
                      <v-label class="mt-2">전화번호</v-label>
                    </v-col>
                    <v-col cols="9">
                      <v-text-field
                          v-model="currentCard.phone"
                          persistent-placeholder
                          placeholder="ex) 031-200-1114"
                          variant="outlined"
                          color="primary"
                      ></v-text-field>
                    </v-col>
                  </v-row>
                </v-col>

                <v-col cols="5" offset="1">
                  <v-row>
                    <v-col cols="3">
                      <v-label class="mt-2">팩스</v-label>
                    </v-col>
                    <v-col cols="9">
                      <v-text-field
                          v-model="currentCard.fax"
                          persistent-placeholder
                          placeholder="ex) 031-200-7538"
                          variant="outlined"
                          color="primary">
                      </v-text-field>
                    </v-col>
                  </v-row>
                </v-col>
              </v-row>
              <!--------------------------4줄-------------------------------------------------------->
              <DaumPostcode v-model="currentCard.address"/>
            </v-window-item>
            <!-------------------------------------------------------------------------------------->
            <!-------------------------------------기타 정보-------------------------------------->
            <!-------------------------------------------------------------------------------------->

            <v-window-item value="two">
              <v-row>
                <v-col cols="6">
                  <v-row>
                    <v-col cols="3">
                      <v-label class="mt-2">계좌 개설일</v-label>
                    </v-col>
                    <v-col cols="8">
                      <DateSelect v-model="currentCard.accoountOpenDate"/>
                    </v-col>
                  </v-row>
                </v-col>

                <v-col cols="6">
                  <v-row>
                    <v-col cols="3">
                      <v-label class="mt-2">계좌 해지일</v-label>
                    </v-col>
                    <v-col cols="8">
                      <DateSelect v-model="currentCard.accountCloseDate"/>
                    </v-col>
                  </v-row>
                </v-col>
              </v-row>
              <v-row>
                <v-col cols="6">
                  <v-row>
                    <v-col cols="4">
                      <v-label class="mt-2">이자율</v-label>
                    </v-col>
                    <v-col cols="7">
                      <v-text-field
                          v-model="currentCard.interestRate"
                          :rules="numberRules"
                          variant="outlined"
                          persistent-placeholder
                          placeholder="ex) 2.2% -> 2.2로 입력"
                          color="primary">
                      </v-text-field>
                    </v-col>
                  </v-row>
                </v-col>
              </v-row>
              <v-row>
                <v-col cols="6">
                  <v-row>
                    <v-col cols="4">
                      <v-label class="mt-2">당좌한도액</v-label>
                    </v-col>
                    <v-col cols="7">
                      <v-text-field
                          v-model="currentCard.overdraftLimit"
                          :rules="integerRules"
                          variant="outlined"
                          persistent-placeholder
                          placeholder="ex) 1000000000"
                          color="primary">
                      </v-text-field>
                    </v-col>
                  </v-row>
                </v-col>
                <v-col cols="6">
                  <v-row>
                    <v-col cols="3">
                      <v-label class="mt-2">당좌차월기일</v-label>
                    </v-col>
                    <v-col cols="8">
                      <DateSelect v-model="currentCard.overdraftDate" />
                    </v-col>
                  </v-row>
                </v-col>
              </v-row>
              <!--------------------------------2줄------------------------------------------------------->
              <v-row>
                <v-col cols="6">
                  <v-row>
                    <v-col cols="4">
                      <v-label class="mt-2">사업자등록번호</v-label>
                    </v-col>
                    <v-col cols="7">
                      <v-text-field
                          v-model="currentCard.businessRegistrationNumber"
                          variant="outlined"
                          persistent-placeholder
                          placeholder="ex) 124-81-00998"
                          color="primary">
                      </v-text-field>
                    </v-col>
                  </v-row>
                </v-col>

                <v-col cols="6">
                  <v-row>
                    <v-col cols="3">
                      <v-label class="mt-2">지방세 <br/> 법정동</v-label>
                    </v-col>
                    <v-col cols="8">

                    </v-col>
                  </v-row>
                </v-col>
              </v-row>
              <v-row>
                <v-col cols="6">
                  <v-row>
                    <v-col cols="4">
                      <v-label class="mt-2">홈페이지</v-label>
                    </v-col>
                    <v-col cols="7">
                      <v-text-field
                          v-model="currentCard.homePage"
                          variant="outlined"
                          persistent-placeholder
                          placeholder="ex) https://OOOOOO.OOO"
                          color="primary">
                      </v-text-field>
                    </v-col>
                  </v-row>
                </v-col>
              </v-row>
            </v-window-item>
            <v-window-item value="three">
            </v-window-item>
            <v-row>
              <v-col cols="2" offset="10" class="pt-8">
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
.card-form {
  width: 100%;
  height: 80%;
  min-height: 800px;
}

.address {
  font-size: small;
}

.v-card-text {
  width: 100%;
}

.v-window-item {
  min-height: 500px;
  width: 100%;
}
</style>