
<script setup>
import { ref } from 'vue';

const tab = ref(null);
import UiParentCard from '~/components/shared/UiParentCard.vue';
import DaumPostcode from "~/components/DaumPostcode.vue";
import DateSelect from "~/components/DateSelect.vue";
import TaxOfficeInfo from "~/components/basicData/TaxOfficeInfo.vue"
import LocalTaxInfo from "~/components/basicData/LocalTaxInfo.vue";
/* ---------------------------정보 제출------------------------------*/
import { useCompanyStore } from "~/stores/accounting/company.ts"
import { useTaxOfficeStore } from "~/stores/accounting/basicdata/taxOffice.ts";
import { useLocalTaxStore } from "~/stores/accounting/basicdata/localTax.ts";
import { storeToRefs }  from "pinia";

const companyStore = useCompanyStore();
const taxOfficeStore = useTaxOfficeStore();
const localTaxStore = useLocalTaxStore();
const { companies } = storeToRefs(companyStore);
const currentCompany = ref(companies.value || {});

const emit = defineEmits(['closeDialog']);

const saveOrUpdateCompany = async () => {
  const companyData = {
    ...currentCompany.value,
    taxOffice: taxOfficeStore.selectedTaxOffice,
    localTax: localTaxStore.selectedLocalTax,
  };
  try {
    if (currentCompany.value.id) {
      await companyStore.updateCompany(companyData);
    } else {
      await companyStore.createCompany(companyData);
    }
    emit('closeDialog');
  } catch (error) {
    console.error("Error saving or updating company: ", error);
  }
};
/*----------------------------양식 검증------------------------------------*/
import {businessRegistrationNumberRules, nameRules} from "~/utils/form.ts";
/*----------------------------초기화---------------------------------------*/
const resetForm = () => {
  Object.keys(currentCompany.value).forEach(key => {
    currentCompany.value[key] = '';
  });
};
</script>

<template>
  <UiParentCard title="회사등록">

    <v-card class="company-form">
      <v-tabs v-model="tab" bg-color="primary">
        <v-tab value="one">기본 정보</v-tab>
        <v-tab value="two">회계/세무 정보</v-tab>
        <v-tab value="three">기타 정보</v-tab>
      </v-tabs>
      <v-form @submit.prevent="saveOrUpdateCompany">
      <v-card-text>
        <v-window v-model="tab">
<!--공통속성-->
<!--tab1-->
          <v-window-item value="one">
<!--------------------------------------1줄-------------------------------->
            <v-row>
              <v-col cols="5">
                <v-row>
                  <v-col cols="3">
                    <v-label class="mt-2">사업자 유형</v-label>
                  </v-col>
                  <v-col cols="9">
                    <v-select
                        v-model="currentCompany.licenseType"
                        :items="['법인사업자', '면세법인사업자', '일반과세자', '면세개인사업자', '간이과세자', '비영리기관 및 국가기관 등']"
                        label="ex) 법인사업자/일반과세자"
                        variant="outlined"
                        color="primary"
                        autofocus
                        hint="사업자등록증 맨 위의 사업자 유형을 확인"
                        persistent-hint
                    ></v-select>
                  </v-col>
                </v-row>
              </v-col>
              <v-col cols="7">
                <v-row>
                  <v-col cols="3">
                    <v-label class="mt-2">본점 여부</v-label>
                  </v-col>
                  <v-col cols="3">
                    <v-select
                        v-model="currentCompany.headOfficeStatus"
                        :items="['본점', '지점']"
                        label="ex) 본점"
                        variant="outlined"
                        color="primary"
                        autofocus
                        hint="본점/지점 여부"
                        persistent-hint
                    ></v-select>
                  </v-col>
                  <v-col cols="3">
                    <v-label class="mt-2">사업자단위과세<br /> 적용사업자 여부</v-label>
                  </v-col>
                  <v-col cols="3">
                    <v-select
                        v-model="currentCompany.paymentHeadOfficeStatus"
                        :items="['여', '부']"
                        label="ex) 여"
                        variant="outlined"
                        color="primary"
                        autofocus
                        hint="세금본점일괄납부 여부"
                        persistent-hint
                    ></v-select>
                  </v-col>
                </v-row>
              </v-col>
            </v-row>
<!--------------------------2줄---------------------------------------------->
            <v-row>
              <v-col cols="5">
                <v-row>
                  <v-col cols="3">
                    <v-label class="mt-2">회사명</v-label>
                  </v-col>
                  <v-col cols="9">
                    <v-text-field
                        v-model="currentCompany.name"
                        :rules="nameRules"
                        hint="법인(단체)/상호 명을 입력해주세요"
                        persistent-hint
                        variant="outlined"
                        persistent-placeholder
                        placeholder="ex) 삼성전자(주)"
                        color="primary">
                    </v-text-field>
                  </v-col>
                </v-row>
              </v-col>
              <v-col cols="7">
                <v-row>
                  <v-col cols="3">
                    <v-label class="mt-2">사업자등록번호</v-label>
                  </v-col>
                  <v-col cols="6">
                    <v-text-field
                        v-model="currentCompany.businessRegistrationNumber"
                        :rules="businessRegistrationNumberRules"
                        hint="사업자등록증 등록번호"
                        persistent-hint
                        variant="outlined"
                        persistent-placeholder
                        placeholder="ex) 124-81-00998"
                        color="primary">
                    </v-text-field>
                  </v-col>
                </v-row>
              </v-col>
            </v-row>
<!--------------------------3줄---------------------------------------------->
            <v-row>
              <v-col cols="5">
                <v-row>
                  <v-col cols="3">
                    <v-label class="mt-2">대표자명</v-label>
                  </v-col>
                  <v-col cols="9">
                    <v-text-field
                        v-model="currentCompany.nameOfRepresentative"
                        hint="사업자등록증 대표자 이름"
                        persistent-hint
                        variant="outlined"
                        persistent-placeholder
                        placeholder="ex) 한종희"
                        color="primary"
                    ></v-text-field>
                  </v-col>
                </v-row>
              </v-col>

              <v-col cols="7">
                <v-row>
                  <v-col cols="3">
                    <v-label class="mt-2">법인등록번호</v-label>
                  </v-col>
                  <v-col cols="6">
                    <v-text-field
                        v-model="currentCompany.corporationRegistrationNumber"
                        hint="법인 사업자인 경우 법인등록번호"
                        persistent-hint
                        variant="outlined"
                        persistent-placeholder
                        placeholder="ex) 130111-0006246"
                        color="primary">
                    </v-text-field>
                  </v-col>
                </v-row>
              </v-col>
            </v-row>
<!--------------------------4줄-------------------------------------------------------->
            <DaumPostcode v-model="currentCompany.address" />
<!--------------------------7줄-------------------------------------------------------->
            <v-row>
              <v-col cols="5">
                <v-row>
                  <v-col cols="3">
                    <v-label class="mt-2">업태</v-label>
                  </v-col>
                  <v-col cols="9">
                    <v-text-field
                        v-model="currentCompany.businessType"
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

              <v-col cols="7">
                <v-row>
                  <v-col cols="3">
                    <v-label class="mt-2">업종</v-label>
                  </v-col>
                  <v-col cols="9">
                    <v-text-field
                        v-model="currentCompany.businessItem"
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
<!-------------------------------------회계/세무정보-------------------------------------->
<!-------------------------------------------------------------------------------------->
          <v-window-item value="two">
            <v-row>
              <v-col cols="4">
                <v-row>
                  <v-col cols="5">
                    <v-label class="mt-2">회계연도</v-label>
                  </v-col>
                  <v-col cols="1">
                    <v-label class="mt-2">제</v-label>
                  </v-col>
                  <v-col cols="4">
                    <v-text-field
                        v-model="currentCompany.fiscalYearClass"
                        hint="숫자"
                        persistent-hint
                        variant="outlined"
                        persistent-placeholder
                        placeholder="ex) 1"
                        color="primary">
                    </v-text-field>
                  </v-col>
                  <v-col cols="1">
                    <v-label class="mt-2">기</v-label>
                  </v-col>
                </v-row>
              </v-col>

              <v-col cols="8">
                <v-row>
                  <v-col cols="2">
                    <v-label class="mt-2"> 회계연도</v-label>
                  </v-col>
                  <v-col cols="1">
                    <v-label class="mt-2"> 시작일</v-label>
                  </v-col>
                  <v-col cols="4">
                    <DateSelect v-model="currentCompany.fiscalYearStart" />
                  </v-col>
                  <v-col cols="1">
                    <v-label class="mt-2"> 종료일</v-label>
                  </v-col>
                  <v-col cols="4">
                    <DateSelect v-model="currentCompany.fiscalYearEnd" />
                  </v-col>
                </v-row>
              </v-col>
            </v-row>
<!--------------------------------2줄------------------------------------------------------->
            <v-row>
              <v-col cols="4">
                <v-row>
                  <v-col cols="5">
                    <v-label class="mt-2">개업연월일</v-label>
                  </v-col>
                  <v-col cols="7">
                    <DateSelect v-model="currentCompany.privatePracticeDate" />
                  </v-col>

                </v-row>
              </v-col>

              <v-col cols="7">
                <v-row>
                  <v-col cols="1">
                    <v-label class="mt-2">사업장 <br/> 세무서</v-label>
                  </v-col>
                  <v-col cols="5" class="pl-5">
                    <TaxOfficeInfo v-model="currentCompany.taxOffice" />
                  </v-col>
                  <v-col cols="1">
                    <v-label class="mt-2">지방세 <br/> 법정동</v-label>
                  </v-col>
                  <v-col cols="5">
                    <LocalTaxInfo v-model="currentCompany.localTax" />
                  </v-col>
                </v-row>
              </v-col>
            </v-row>
<!--------------------------------3줄------------------------------------------->
            <v-row>
              <v-col cols="12" sm="6">
                <v-row>
                  <v-col cols="12" lg="4" md="3" class="pb-md-3 pb-0">
                    <v-label class="mt-2">대표자 <br/>주민등록번호</v-label>
                  </v-col>
                  <v-col cols="12" lg="8" md="9">
                    <v-text-field
                        v-model="currentCompany.residentRegistrationNumber"
                        hint="사업자등록증 대표자 주민등록번호"
                        persistent-hint
                        variant="outlined"
                        persistent-placeholder
                        placeholder="ex) OOOOOOO-OOOOOOO"
                        color="primary"
                    ></v-text-field>
                  </v-col>
                </v-row>
              </v-col>
            </v-row>
<!---------------------------------------4줄------------------------------------------->
            <v-row>
              <v-col cols="6">
                <v-row>
                  <v-col cols="4">
                    <v-label class="mt-2">법인 구분</v-label>
                  </v-col>
                  <v-col cols="8">
                    <v-select
                        v-model="currentCompany.corporationClassifyStatus"
                        :items="['내국', '외국', '외투']"
                        hint="ex) 내국/외국/외국투자 기업"
                        persistent-hint
                        variant="outlined"
                        color="primary"
                        autofocus
                    ></v-select>
                  </v-col>
                </v-row>
              </v-col>
              <v-col cols="6">
                <v-row>
                  <v-col cols="12" lg="4">
                    <v-label class="mt-2">종류별 구분</v-label>
                  </v-col>
                  <v-col cols="12" lg="8">
                    <v-select
                        v-model="currentCompany.companyTypeStatus"
                        :items="['중소기업', '일반', '상장', '비영리', '협회 등록']"
                        variant="outlined"
                        color="primary"
                        autofocus
                        hint="ex) 중소기업 / 일반 / 비영리 기업 등"
                        persistent-hint
                    ></v-select>
                  </v-col>
                </v-row>
              </v-col>
            </v-row>
<!--------------------------------6줄------------------------------------------->
            <v-row>
              <v-col cols="6">
                <v-row>
                  <v-col cols="4">
                    <v-label class="mt-2">중소기업 여부</v-label>
                  </v-col>
                  <v-col cols="8">
                    <v-select
                        v-model="currentCompany.businessScaleStatus"
                        :items="['중소기업', '비중소기업']"
                        hint="ex) 여 / 부"
                        persistent-hint
                        variant="outlined"
                        color="primary"
                        autofocus
                    ></v-select>
                  </v-col>
                </v-row>
              </v-col>

              <v-col cols="6">
                <v-row>
                  <v-col cols="4">
                    <v-label class="mt-2">지방소득세 <br />신고 구분</v-label>
                  </v-col>
                  <v-col cols="8">
                    <v-select
                        v-model="currentCompany.localTaxBillDivision"
                        :items="['개인(내국인)', '외국인', '종중, 문중', '종교단체', '마을회', '기타단체', 'OO 주식회사', '주식회사 OO', 'OO 합자회사', '합자회사 OO', 'OO 합병회사', '합병회사 OO', 'OO 유한(책임)회사', '유한(책임)회사 OO', '농업회사법인', 'OO 재단법인', '재단법인 OO', 'OO 사단법인', '사단법인 OO', 'OO 학교법인', '학교법인 OO', '의료법인', '사회복지법인', '특수법인', '광역자치단체', '기초자치단체', '외국정부 및 주한국제기관', '자치단체조합', '기타법인']"
                        variant="outlined"
                        color="primary"
                        autofocus
                        hint="ex) 체류자격 ( 개인, 외국인, OO주식회사 등)"
                        persistent-hint
                    ></v-select>
                  </v-col>
                </v-row>
              </v-col>
            </v-row>
          </v-window-item>
<!--tab3-->
          <v-window-item value="three">
            <v-row>
              <v-col cols="6">
                <v-row>
                  <v-col cols="4">
                    <v-label class="mt-2">사업장 <br/> 전화번호</v-label>
                  </v-col>
                  <v-col cols="8">
                    <v-text-field
                        v-model="currentCompany.phone"
                        placeholder="000-0000-0000"
                        persistent-placeholder
                        hint="-까지 포함하여 입력"
                        persistent-hint
                        variant="outlined"
                        color="primary"
                    ></v-text-field>
                  </v-col>
                </v-row>
              </v-col>
              <v-col cols="6">
                <v-row>
                  <v-col cols="4">
                    <v-label class="mt-2">사업장 <br/> 팩스번호</v-label>
                  </v-col>
                  <v-col cols="8">
                    <v-text-field
                        v-model="currentCompany.fax"
                        placeholder="000-0000-0000"
                        persistent-placeholder
                        hint="-까지 포함하여 입력"
                        persistent-hint
                        variant="outlined"
                        color="primary"
                    ></v-text-field>
                  </v-col>
                </v-row>
              </v-col>
            </v-row>
<!--------------------------------2줄------------------------------------------->
            <v-row>
              <v-col cols="6">
                <v-row>
                  <v-col cols="4">
                    <v-label class="mt-2">담당자 <br />이름</v-label>
                  </v-col>
                  <v-col cols="8">
                    <v-text-field
                        v-model="currentCompany.chargeName"
                        placeholder="김OO"
                        persistent-placeholder
                        variant="outlined"
                        color="primary"
                    ></v-text-field>
                  </v-col>
                </v-row>
              </v-col>
              <v-col cols="6">
                <v-row>
                  <v-col cols="4">
                    <v-label class="mt-2">담당자 <br />이메일</v-label>
                  </v-col>
                  <v-col cols="8">
                    <v-text-field
                        v-model="currentCompany.chargeEmail"
                        placeholder="OOO@OOOO.OOO"
                        persistent-placeholder
                        variant="outlined"
                        color="primary"
                    ></v-text-field>
                  </v-col>
                </v-row>
              </v-col>
            </v-row>
<!--------------------------------3줄------------------------------------------->
            <v-row>
              <v-col cols="2">
                <v-label class="mt-2">비고</v-label>
              </v-col>
              <v-col cols="10">
                <v-textarea
                    v-model="currentCompany.note"
                    variant="outlined"
                    color="primary"
                    auto-grow
                    clearable
                ></v-textarea>
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
.company-form {
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