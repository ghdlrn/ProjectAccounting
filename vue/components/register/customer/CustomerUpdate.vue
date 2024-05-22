<script setup>
import { ref, onMounted } from 'vue';

const tab = ref(null);
import UiParentCard from '~/components/shared/UiParentCard.vue';
import DaumPostcode from "~/components/DaumPostcode.vue";
import DateSelect from "~/components/DateSelect.vue";
import LocalTaxInfo from "~/components/basicData/LocalTaxInfo.vue";
/* ---------------------------정보 제출------------------------------*/
import { useCustomerStore } from "~/stores/accounting/customer.ts"

const customerStore = useCustomerStore();
const currentCustomer = ref({});

onMounted(async () => {
  await customerStore.fetchCustomer();  // 초기 데이터 로드
  if (customerStore.currentCustomer) {
    currentCustomer.value = {...customerStore.currentCustomer};
  }
});

const saveOrUpdateCustomer = async () => {
  if (currentCustomer.value.id) {
    await customerStore.updateCustomer(currentCustomer.value);
  } else {
    await customerStore.createCustomer(currentCustomer.value);
  }
};
/*----------------------------양식 검증------------------------------------*/
import {integerRules, nameRules, nullableRules, registrationNumberRules} from "~/utils/form";
</script>

<template>
  <UiParentCard title="거래처 조회 / 수정">

    <v-card class="customer-form">
      <v-tabs v-model="tab" bg-color="primary">
        <v-tab value="one">기본 정보</v-tab>
        <v-tab value="two">기타 정보</v-tab>
      </v-tabs>
      <v-form @submit.prevent="saveOrUpdateCustomer">
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
                      <v-label class="mt-2">거래처명</v-label>
                    </v-col>
                    <v-col cols="8">
                      <v-text-field
                          v-model="currentCustomer.name"
                          :rules="nameRules"
                          variant="outlined"
                          persistent-placeholder
                          placeholder="ex) 삼성전자(주)"
                          color="primary">
                      </v-text-field>
                    </v-col>
                  </v-row>
                </v-col>
                <v-col cols="8">
                  <v-row>
                    <v-col cols="1">
                      <v-label class="mt-2">구분</v-label>
                    </v-col>
                    <v-col cols="5">
                      <v-select
                          v-model="currentCustomer.registrationNumberType"
                          :items="['사업자등록번호', '주민등록번호', '외국인등록번호']"
                          label="ex) 사업자등록번호"
                          :rules="nullableRules"
                          variant="outlined"
                          color="primary"
                          autofocus
                          hint="사업자/주민/외국인 등록번호"
                          persistent-hint
                      ></v-select>
                    </v-col>
                    <v-col cols="2">
                      <v-label class="mt-2">등록번호</v-label>
                    </v-col>
                    <v-col cols="4">
                      <v-text-field
                          v-model="currentCustomer.registrationNumber"
                          :rules="registrationNumberRules"
                          variant="outlined"
                          persistent-placeholder
                          placeholder="ex) xxx-xx-xxxx"
                          color="primary">
                      </v-text-field>
                    </v-col>
                  </v-row>
                </v-col>
              </v-row>
              <!--------------------------2줄---------------------------------------------->
              <v-row>
                <v-col cols="4">
                  <v-row>
                    <v-col cols="4">
                      <v-label class="mt-2">대표자명</v-label>
                    </v-col>
                    <v-col cols="8">
                      <v-text-field
                          v-model="currentCustomer.nameOfRepresentative"
                          hint="대표자명을 입력해주세요"
                          persistent-hint
                          variant="outlined"
                          persistent-placeholder
                          placeholder="ex) 한종희"
                          color="primary">
                      </v-text-field>
                    </v-col>
                  </v-row>
                </v-col>
                <v-col cols="8">
                  <v-row>
                    <v-col cols="1">
                      <v-label class="mt-3">비고</v-label>
                    </v-col>
                    <v-col cols="6">
                      <v-text-field
                          v-model="currentCustomer.note"
                          hint="주석"
                          persistent-hint
                          variant="outlined"
                          persistent-placeholder
                          placeholder="ex) 원재료 매입 거래처"
                          color="primary">
                      </v-text-field>
                    </v-col>
                    <v-col cols="3">
                      <v-label class="mt-3">거래처 사용 여부</v-label>
                    </v-col>
                    <v-col cols="1">
                      <v-switch
                          v-model="currentCustomer.useStatus"
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
                          v-model="currentCustomer.phone"
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
                          v-model="currentCustomer.fax"
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
              <DaumPostcode v-model="currentCustomer.address" />
              <!--------------------------7줄-------------------------------------------------------->
              <v-row>
                <v-col cols="5">
                  <v-row>
                    <v-col cols="3">
                      <v-label class="mt-2">업태</v-label>
                    </v-col>
                    <v-col cols="9">
                      <v-text-field
                          v-model="currentCustomer.businessType"
                          hint="판매형태/표준산업분류표 대분류(2자리)"
                          persistent-hint
                          variant="outlined"
                          persistent-placeholder
                          placeholder="ex) 제조업"
                          color="primary">
                      </v-text-field>
                    </v-col>
                  </v-row>
                </v-col>

                <v-col cols="5" offset="1">
                  <v-row>
                    <v-col cols="3">
                      <v-label class="mt-2">업종</v-label>
                    </v-col>
                    <v-col cols="9">
                      <v-text-field
                          v-model="currentCustomer.businessItem"
                          hint="판매하는 물건/표준산업분류표 세분류(5자리)"
                          persistent-hint
                          variant="outlined"
                          persistent-placeholder
                          placeholder="ex) 전자，전기，통신기계기구외"
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
                <v-col cols="4">
                  <v-row>
                    <v-col cols="5">
                      <v-label class="mt-2">거래 시작일</v-label>
                    </v-col>
                    <v-col cols="7">
                      <DateSelect v-model="currentCustomer.tradeStartDate" />
                    </v-col>
                  </v-row>
                </v-col>

                <v-col cols="4">
                  <v-row>
                    <v-col cols="4">
                      <v-label class="mt-2">거래 종료일</v-label>
                    </v-col>
                    <v-col cols="7">
                      <DateSelect v-model="currentCustomer.tradeEndDate" />
                    </v-col>
                  </v-row>
                </v-col>

                <v-col cols="4">
                  <v-row>
                    <v-col cols="3">
                      <v-label class="mt-2">홈페이지</v-label>
                    </v-col>
                    <v-col cols="9">
                      <v-text-field
                          v-model="currentCustomer.homePage"
                          variant="outlined"
                          persistent-placeholder
                          placeholder="ex) https://OOOOOO.OOO"
                          color="primary">
                      </v-text-field>
                    </v-col>
                  </v-row>
                </v-col>
              </v-row>
              <!--------------------------------2줄------------------------------------------------------->
              <v-row>
                <v-col cols="5">
                  <v-row>
                    <v-col cols="5">
                      <v-label class="mt-2">거래처 담당자 <br /> 부서 / 직급 / 이름</v-label>
                    </v-col>
                    <v-col cols="7">
                      <v-text-field
                          v-model="currentCustomer.customerChargeDepartment"
                          variant="outlined"
                          persistent-placeholder
                          placeholder="ex) 영업팀"
                          color="primary">
                      </v-text-field>
                    </v-col>
                  </v-row>
                </v-col>
                <v-col cols="3">
                  <v-text-field
                      v-model="currentCustomer.customerChargePosition"
                      variant="outlined"
                      persistent-placeholder
                      placeholder="ex) 과장"
                      color="primary">
                  </v-text-field>
                </v-col>
                <v-col cols="3">
                  <v-text-field
                      v-model="currentCustomer.customerChargeName"
                      variant="outlined"
                      persistent-placeholder
                      placeholder="ex) OOO"
                      color="primary">
                  </v-text-field>
                </v-col>
              </v-row>
              <!--------------------------------3줄------------------------------------------->
              <v-row>
                <v-col cols="5">
                  <v-row>
                    <v-col cols="5">
                      <v-label class="mt-2">거래처 담당자 <br />> 전화번호 / 이메일</v-label>
                    </v-col>
                    <v-col cols="7">
                      <v-text-field
                          v-model="currentCustomer.customerChargePhone"
                          variant="outlined"
                          persistent-placeholder
                          placeholder="ex) OOO-OOOO-OOOO"
                          color="primary">
                      </v-text-field>
                    </v-col>
                  </v-row>
                </v-col>

                <v-col cols="5">
                  <v-row>
                    <v-col cols="7">
                      <v-text-field
                          v-model="currentCustomer.customerChargeEmail"
                          variant="outlined"
                          persistent-placeholder
                          placeholder="ex) OOO@OOOO.OOO"
                          color="primary">
                      </v-text-field>
                    </v-col>
                  </v-row>
                </v-col>
              </v-row>
              <!---------------------------------------4줄------------------------------------------->
              <v-row>
                <v-col cols="6">
                  <v-row>
                    <v-col cols="4">
                      <v-label class="mt-2">거래처 은행 <br/> 계좌번호 / 예금주</v-label>
                    </v-col>
                    <v-col cols="8">
                      <v-text-field
                          v-model="currentCustomer.customerAccountNumber"
                          variant="outlined"
                          persistent-placeholder
                          placeholder="ex) OOO-OOOO-OOOO-OO"
                          color="primary">
                      </v-text-field>
                    </v-col>
                  </v-row>
                </v-col>
                <v-col cols="4">
                  <v-text-field
                      v-model="currentCustomer.customerAccountHolder"
                      variant="outlined"
                      persistent-placeholder
                      placeholder="ex) OOO"
                      color="primary">
                  </v-text-field>
                </v-col>
              </v-row>
              <!------------------------5줄----------------------------------------------------->
              <v-row>
                <v-col cols="6">
                  <v-row>
                    <v-col cols="4">
                      <v-label class="mt-2">여신한도액</v-label>
                    </v-col>
                    <v-col cols="8">
                      <v-text-field
                          v-model="currentCustomer.bankLine"
                          :rules="integerRules"
                          variant="outlined"
                          persistent-placeholder
                          placeholder="ex) OOO,OOO,OOO원"
                          color="primary">
                      </v-text-field>
                    </v-col>
                  </v-row>
                </v-col>
                <v-col cols="6">
                  <v-row>
                    <v-col cols="12" lg="4">
                      <v-label class="mt-2">담보 설정액</v-label>
                    </v-col>
                    <v-col cols="12" lg="8">
                      <v-text-field
                          v-model="currentCustomer.amountOfCollateral"
                          :rules="integerRules"
                          variant="outlined"
                          persistent-placeholder
                          placeholder="ex) OOO,OOO,OOO원"
                          color="primary">
                      </v-text-field>
                    </v-col>
                  </v-row>
                </v-col>
              </v-row>
              <!--------------------------------6줄------------------------------------------->
              <v-row>
                <v-col cols="6">
                  <v-row>
                    <v-col cols="4">
                      <v-label class="mt-2">단위 신고 거래처</v-label>
                    </v-col>
                    <v-col cols="8">
                      <v-text-field
                          v-model="currentCustomer.unitReportingCustomerCode"
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
                    <v-col cols="9">
                      <LocalTaxInfo v-model="currentCustomer.localTax" />
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
.customer-form {
  width: 100%;
  height: 80%;
  min-height: 800px;
}
.address{
  font-size: small;
}
.v-card-text{
  width: 100%;
}
.v-window-item {
  min-height: 500px;
  width: 100%;
}
</style>