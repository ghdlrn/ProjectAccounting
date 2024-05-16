<script setup>
import {ref} from 'vue';

const tab = ref(null);
import UiParentCard from "~/components/shared/UiParentCard.vue";
import DaumPostcode from "~/components/DaumPostcode.vue";
import DateSelect from "~/components/DateSelect.vue";
import LocalTaxInfo from "~/components/basicData/LocalTaxInfo.vue";
/* ---------------------------정보 제출------------------------------*/
import {storeToRefs} from "pinia";
import {useFinanceStore} from "~/stores/accounting/finance.ts";
import {useLocalTaxStore} from "~/stores/accounting/basicdata/localTax";

const financeStore = useFinanceStore();
const localTaxStore = useLocalTaxStore();

const {finance} = storeToRefs(financeStore);
const currentFinance = ref(finance.value || {});

const emit = defineEmits(['closeDialog']);

const saveOrUpdateFinance = async () => {
  const financeData = {
    ...currentFinance.value,
    localTax: localTaxStore.selectedLocalTax,
  };
  try {
    if (currentFinance.value.id) {
      await financeStore.updateFinance(financeData);
    } else {
      await financeStore.createFinance(financeData);
    }
    emit('closeDialog');
  } catch (error) {
    console.error("Error saving or updating finance: ", error);
  }
};
/*----------------------------양식 검증------------------------------------*/
import {nameRules, nullableRules, numberRules, integerRules} from "~/utils/form.ts";
</script>

<template>
  <UiParentCard title="계좌 등록">

    <v-card class="finance-form">
      <v-tabs v-model="tab" bg-color="primary">
        <v-tab value="one">기본 정보</v-tab>
        <v-tab value="two">추가 정보</v-tab>
      </v-tabs>
      <v-form @submit.prevent="saveOrUpdateFinance">
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
                      <v-label class="mt-2">금융사명</v-label>
                    </v-col>
                    <v-col cols="8">
                      <v-text-field
                          v-model="currentFinance.name"
                          :rules="nameRules"
                          variant="outlined"
                          persistent-placeholder
                          placeholder="ex) 농협"
                          color="primary">
                      </v-text-field>
                    </v-col>
                  </v-row>
                </v-col>
                <v-col cols="8">
                  <v-row>
                    <v-col cols="2">
                      <v-label class="mt-2">계좌번호</v-label>
                    </v-col>
                    <v-col cols="4">
                      <v-text-field
                          v-model="currentFinance.accountNumber"
                          :rules="nullableRules"
                          variant="outlined"
                          persistent-placeholder
                          placeholder="ex) 352-0712-2542-03"
                          color="primary">
                      </v-text-field>
                    </v-col>
                    <v-col cols="2">
                      <v-label class="mt-2">예금종류</v-label>
                    </v-col>
                    <v-col cols="4">
                      <v-text-field
                          v-model="currentFinance.depositType"
                          variant="outlined"
                          persistent-placeholder
                          placeholder="ex) 보통예금"
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
                          v-model="currentFinance.note"
                          hint="주석"
                          persistent-hint
                          variant="outlined"
                          persistent-placeholder
                          placeholder="ex) 메인 통장"
                          color="primary">
                      </v-text-field>
                    </v-col>
                  </v-row>
                </v-col>
                <v-col cols="4">
                  <v-row>
                    <v-col cols="4">
                      <v-label class="mt-3">계좌 사용 여부</v-label>
                    </v-col>
                    <v-col cols="8">
                      <v-switch
                          v-model="currentFinance.useStatus"
                          false-value="UNUSED"
                          true-value="USE"
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
                          v-model="currentFinance.phone"
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
                          v-model="currentFinance.fax"
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
              <DaumPostcode v-model="currentFinance.address"/>
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
                      <DateSelect v-model="currentFinance.accoountOpenDate"/>
                    </v-col>
                  </v-row>
                </v-col>

                <v-col cols="6">
                  <v-row>
                    <v-col cols="3">
                      <v-label class="mt-2">계좌 해지일</v-label>
                    </v-col>
                    <v-col cols="8">
                      <DateSelect v-model="currentFinance.accountCloseDate"/>
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
                          v-model="currentFinance.interestRate"
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
                          v-model="currentFinance.overdraftLimit"
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
                      <DateSelect v-model="currentFinance.overdraftDate" />
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
                          v-model="currentFinance.businessRegistrationNumber"
                          variant="outlined"
                          persistent-hint
                          hint="사업자단위 과세의 경우 세금계산서 신고시 단위신고거래처로"
                          persistent-placeholder
                          placeholder="ex) (주) OOOO"
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
                      <LocalTaxInfo v-model="currentFinance.localTax"/>
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
                          v-model="currentFinance.homePage"
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
.finance-form {
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