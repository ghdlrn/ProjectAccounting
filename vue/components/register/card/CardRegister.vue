<script setup>
import {ref} from 'vue';

const tab = ref(null);
import UiParentCard from "~/components/shared/UiParentCard.vue";
import DaumPostcode from "~/components/DaumPostcode.vue";
import DateSelect from "~/components/DateSelect.vue";
import ReadFinance from "~/components/register/ReadFinance.vue";
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
import {nameRules, nullableRules} from "~/utils/form.ts";
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
                    <v-col cols="3">
                      <v-label class="mt-2">카드(가맹점) <br />번호</v-label>
                    </v-col>
                    <v-col cols="5">
                      <v-text-field
                          v-model="currentCard.cardNumber"
                          :rules="nullableRules"
                          variant="outlined"
                          persistent-placeholder
                          placeholder="ex) OOOO-OOOO-OOOO-OOOO"
                          color="primary">
                      </v-text-field>
                    </v-col>
                    <v-col cols="1">
                      <v-label class="mt-2">구분</v-label>
                    </v-col>
                    <v-col cols="3">
                      <v-select
                          v-model="currentCard.division"
                          :items="['매입', '매출']"
                          label="ex) 매입, 매출"
                          variant="outlined"
                          autofocus
                          color="primary">
                      </v-select>
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

              <!--------------------------4줄---------------------------------------------->
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
              <!--------------------------4줄-------------------------------------------------------->
              <ReadFinance v-model="currentCard.finance"/>
              <!--------------------------4줄-------------------------------------------------------->
              <v-row>
                <v-col cols="5">
                  <v-row>
                    <v-col cols="3">
                      <v-label class="mt-2">사업자/주민<br />등록번호</v-label>
                    </v-col>
                    <v-col cols="9">
                      <v-text-field
                          v-model="currentCard.registrationNumber"
                          persistent-placeholder
                          placeholder="ex) 124-81-00998"
                          variant="outlined"
                          color="primary"
                      ></v-text-field>
                    </v-col>
                  </v-row>
                </v-col>

                <v-col cols="5" offset="1">
                  <v-row>
                    <v-col cols="3">
                      <v-label class="mt-2">홈페이지</v-label>
                    </v-col>
                    <v-col cols="9">
                      <v-text-field
                          v-model="currentCard.homePage"
                          persistent-placeholder
                          placeholder="ex) https://OOOOOO.OOO"
                          variant="outlined"
                          color="primary">
                      </v-text-field>
                    </v-col>
                  </v-row>
                </v-col>
              </v-row>
            </v-window-item>
            <!-------------------------------------------------------------------------------------->
            <!-------------------------------------기타 정보-------------------------------------->
            <!-------------------------------------------------------------------------------------->

            <v-window-item value="two">
              <v-row>
                <v-col cols="6">
                  <v-row>
                    <v-col cols="3">
                      <v-label class="mt-2">계약기간<br /> 시작일</v-label>
                    </v-col>
                    <v-col cols="8">
                      <DateSelect v-model="currentCard.contractStartDate"/>
                    </v-col>
                  </v-row>
                </v-col>

                <v-col cols="6">
                  <v-row>
                    <v-col cols="3">
                      <v-label class="mt-2">계약기간<br /> 종료일</v-label>
                    </v-col>
                    <v-col cols="8">
                      <DateSelect v-model="currentCard.contractEndDate"/>
                    </v-col>
                  </v-row>
                </v-col>
              </v-row>
              <v-row>
                <v-col cols="6">
                  <v-row>
                    <v-col cols="4">
                      <v-label class="mt-2">수수료</v-label>
                    </v-col>
                    <v-col cols="7">
                      <v-text-field
                          v-model="currentCard.commission"
                          variant="outlined"
                          persistent-placeholder
                          placeholder="ex) 2.2% -> 2.2로 입력"
                          color="primary">
                      </v-text-field>
                    </v-col>
                  </v-row>
                </v-col>
              </v-row>
            </v-window-item>
            <v-window-item value="three">
                <v-row>
                  <v-col cols="6">
                    <v-row>
                      <v-col cols="3">
                        <v-label class="mt-2">유효기간<br /> 시작일</v-label>
                      </v-col>
                      <v-col cols="8">
                        <DateSelect v-model="currentCard.expirationStartDate"/>
                      </v-col>
                    </v-row>
                  </v-col>

                  <v-col cols="6">
                    <v-row>
                      <v-col cols="3">
                        <v-label class="mt-2">유효기간<br /> 종료일</v-label>
                      </v-col>
                      <v-col cols="8">
                        <DateSelect v-model="currentCard.expirationEndDate"/>
                      </v-col>
                    </v-row>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col cols="6">
                    <v-row>
                      <v-col cols="4">
                        <v-label class="mt-2">담당자명</v-label>
                      </v-col>
                      <v-col cols="7">
                        <v-text-field
                            v-model="currentCard.chargeName"
                            variant="outlined"
                            persistent-placeholder
                            placeholder="ex) 카드 담당 사원"
                            color="primary">
                        </v-text-field>
                      </v-col>
                    </v-row>
                  </v-col>
                  <v-col cols="6">
                    <v-row>
                      <v-col cols="3">
                        <v-label class="mt-2">결제일</v-label>
                      </v-col>
                      <v-col cols="8">
                        <DateSelect v-model="currentCard.accountDay"/>
                      </v-col>
                    </v-row>
                  </v-col>
                </v-row>
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