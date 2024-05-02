
<script setup>
import { ref } from 'vue';
import axios from 'axios';

import UiParentCard from '@/components/shared/UiParentCard.vue';
const tab = ref(null);
const Used = ref(false);
/* ---------------------------정보 제출------------------------------*/
const licenseType = ref('')
const headOfficeStatus = ref('')
const paymentHeadOfficeStatus = ref('')
const name = ref('')
const businessRegistrationNumber = ref('')
const nameOfRepresentative = ref('')
const corporationRegistrationNumber = ref('')
const fiscalYearStart = ref('')
const fiscalYearEnd = ref('')
const privatePracticeDate = ref('')
const taxOfficeName = ref('')
const localTaxName = ref('')
const accountNumber = ref('')
const corporationClassifyStatus = ref('')
const businessScaleStatus = ref('')
const companyTypeStatus = ref('')
const localTaxBillDivisionCode = ref('')

const CompanyRegister = async () => {
  try {
    await axios.post('http://localhost:8080/register/compant-info', {
      licenseType: licenseType.value,
      headOfficeStatus: headOfficeStatus.value,
      paymentHeadOfficeStatus: paymentHeadOfficeStatus.value,
      name: name.value,
      businessRegistrationNumber: businessRegistrationNumber.value,
      nameOfRepresentative: nameOfRepresentative.value,
      corporationRegistrationNumber: corporationRegistrationNumber.value,
      postcode: addressStore.postcode,
      roadAddress: addressStore.roadAddress,
      jibunAddress: addressStore.jibunAddress,
      extraAddress: addressStore.extraAddress,
      guideText: addressStore.guideText,

      fiscalYearStart: fiscalYearStart.value,
      fiscalYearEnd: fiscalYearEnd.value,
      privatePracticeDate: privatePracticeDate.value,
      taxOfficeName: taxOfficeName.value,
      localTaxName: localTaxName.value,
      accountNumber: accountNumber.value,
      corporationClassifyStatus: corporationClassifyStatus.value,
      businessScaleStatus: businessScaleStatus.value,
      companyTypeStatus: companyTypeStatus.value,
      localTaxBillDivisionCode: localTaxBillDivisionCode.value,
    });
  } catch (error) {
    console.error('회사 등록 실패', error);
    alert('회사 등록 실패');
  }
};

/*----------------------------우편번호 검색---------------------------------*/
import { useAddressStore } from "~/stores/address.js";
const addressStore = useAddressStore();
/*----------------------------날짜 선택기---------------------------------*/
import DateSelect from "~/components/DateSelect.vue";
import TaxOfficeInfo from "~/components/basicData/TaxOfficeInfo.vue"
import LocalTaxInfo from "~/components/basicData/LocalTaxInfo.vue";
</script>

<template>
  <UiParentCard title="회사등록">

    <v-card>
      <v-tabs v-model="tab" bg-color="primary">
        <v-tab value="one">기본 정보</v-tab>
        <v-tab value="two">회계/세무 정보</v-tab>
        <v-tab value="three">기타 정보</v-tab>
      </v-tabs>
      <v-form ref="Reform">
      <v-card-text>
        <v-window v-model="tab">
<!--공통속성-->
          <v-row>
            <v-col cols="4">
              <v-switch v-model="Used" class="mt-0 ms-2" color="primary" density="compact" label="회사 정보 등록/변경하기" hide-details></v-switch>
            </v-col>
            <v-col cols="1" offset="5">
              <v-btn :disabled="!Used" color="success" variant="flat">수정</v-btn>
            </v-col>
            <v-col cols="1">
              <v-btn :disabled="!Used" color="primary" variant="flat" @click="CompanyRegister()">등록</v-btn>
            </v-col>
          </v-row>
<!--tab1-->
          <v-window-item value="one">
            <!--1줄------------------------------>
            <v-row>
              <!-- column 1 -->
              <v-col cols="12" sm="5">
                <!-- row 1 -->
                <v-row>
                  <v-col cols="12" lg="3" md="3" class="pb-md-3 pb-0">
                    <v-label class="mt-2">사업자 유형</v-label>
                  </v-col>
                  <v-col cols="12" lg="9" md="9">
                    <v-select
                        v-model="licenseType"
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
                <!-- row 2 -->
                <v-row>
                  <v-col cols="12" lg="3" md="3" class="pb-md-3 pb-0">
                    <v-label class="mt-2">회사명</v-label>
                  </v-col>
                  <v-col cols="12" lg="9" md="9">
                    <v-text-field
                        v-model="name"
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
              <!-- column 2 -->
              <v-col cols="12" sm="7">
                <!-- row 1 -->
                <v-row>
                  <v-col cols="12" lg="3" md="3" class="pb-md-3 pb-0">
                    <v-label class="mt-2">본점 여부</v-label>
                  </v-col>
                  <v-col cols="12" lg="3" md="6">
                    <v-select
                        v-model="headOfficeStatus"
                        :items="['본점', '지점']"
                        label="ex) 본점"
                        variant="outlined"
                        color="primary"
                        autofocus
                        hint="본점/지점 여부"
                        persistent-hint
                    ></v-select>
                  </v-col>
                  <v-col cols="12" lg="3" md="3" class="pb-md-3 pb-0">
                    <v-label class="mt-2">사업자단위과세<br /> 적용사업자 여부</v-label>
                  </v-col>
                  <v-col cols="12" lg="3" md="6">
                    <v-select
                        v-model="paymentHeadOfficeStatus"
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
                <!-- row 2 -->
                <v-row>
                  <v-col cols="12" lg="3" md="3" class="pb-md-3 pb-0">
                    <v-label class="mt-2">사업자등록번호</v-label>
                  </v-col>
                  <v-col cols="12" lg="6" md="9">
                    <v-text-field
                        v-model="businessRegistrationNumber"
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
            <!--3줄------------------------------------>
            <v-row>
              <!-- column 1 -->
              <v-col cols="12" sm="5">
                <!-- row 1 -->
                <v-row>
                  <v-col cols="12" lg="3" md="3" class="pb-md-3 pb-0">
                    <v-label class="mt-2">대표자명</v-label>
                  </v-col>
                  <v-col cols="12" lg="9" md="9">
                    <v-text-field
                        v-model="nameOfRepresentative"
                        hint="사업자등록증 대표자 이름"
                        persistent-hint
                        variant="outlined"
                        persistent-placeholder
                        placeholder="ex) 한종희, 경계현"
                        color="primary"
                    ></v-text-field>
                  </v-col>
                </v-row>
                <!-- row 2 -->
                <!-- row 3 -->
              </v-col>
              <!-- column 2 -->
              <v-col cols="12" sm="7">
                <!-- row 1 -->
                <v-row>
                  <v-col cols="12" lg="3" md="3" class="pb-md-3 pb-0">
                    <v-label class="mt-2">법인등록번호</v-label>
                  </v-col>
                  <v-col cols="12" lg="6" md="9">
                    <v-text-field
                        v-model="corporationRegistrationNumber"
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

            <DaumPostcode />
              <!-- row 2 -->
            <v-row>
            <v-col cols="12" sm="5">
              <v-row>
                <v-col cols="12" lg="3" md="3" class="pb-md-3 pb-0">
                  <v-label class="mt-2">업태</v-label>
                </v-col>
                <v-col cols="12" lg="9" md="9">
                  <v-text-field
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
                <!-- row 3 -->

              <!-- column 2 -->
              <v-col cols="12" sm="7">
                <v-row>
                  <v-col cols="12" lg="3" md="3" class="pb-md-3 pb-0">
                    <v-label class="mt-2">종목</v-label>
                  </v-col>
                  <v-col cols="12" lg="9" md="9">
                    <v-text-field
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
              <v-col cols="12" sm="4">
                <v-row>
                  <v-col cols="12" lg="4">
                    <v-label class="mt-2">회계연도</v-label>
                  </v-col>

                  <v-col cols="12" lg="1">
                    <v-label class="mt-2">제</v-label>
                  </v-col>
                  <v-col cols="12" lg="4">
                    <v-text-field
                        hint="숫자"
                        persistent-hint
                        variant="outlined"
                        persistent-placeholder
                        placeholder="ex) 1"
                        color="primary">
                    </v-text-field>
                  </v-col>
                  <v-col cols="12" lg="1">
                    <v-label class="mt-2">기</v-label>
                  </v-col>
                </v-row>
              </v-col>

              <v-col cols="12" sm="8">
                <v-row>
                  <v-col cols="12" lg="2">
                    <v-label class="mt-2"> 회계연도</v-label>
                  </v-col>
                  <v-col cols="12" lg="1">
                    <v-label class="mt-2"> 시작일</v-label>
                  </v-col>
                  <v-col cols="12" lg="4">
                    <DateSelect v-model="fiscalYearStart" />
                  </v-col>

                  <v-col cols="12" lg="1">
                    <v-label class="mt-2"> 종료일</v-label>
                  </v-col>
                  <v-col cols="12" lg="4" md="9">
                    <DateSelect v-model="fiscalYearEnd" />
                  </v-col>
                </v-row>
              </v-col>
            </v-row>
            <!-- row 2 -->
            <v-row>
              <v-col cols="12" sm="4">
                <v-row>
                  <v-col cols="12" lg="3" md="3" class="pb-md-3 pb-0">
                    <v-label class="mt-2">개업연월일</v-label>
                  </v-col>
                  <v-col cols="12" lg="9" md="9">
                    <DateSelect v-model="privatePracticeDate" />
                  </v-col>

                </v-row>
              </v-col>

              <v-col cols="12" sm="8">
                <v-row>
                  <v-col cols="12" lg="1" md="3" class="pb-md-3 pb-0">
                    <v-label class="mt-2">사업장 <br/> 세무서</v-label>
                  </v-col>
                  <v-col cols="12" lg="5" md="9">

                    <TaxOfficeInfo v-model="taxOfficeName" />

                  </v-col>
                  <v-col cols="12" lg="1" md="3" class="pb-md-3 pb-0">
                    <v-label class="mt-2">지방세 <br/> 법정동</v-label>
                  </v-col>
                  <v-col cols="12" lg="5" md="9">

                    <LocalTaxInfo v-model="localTaxName" />

                  </v-col>
                </v-row>
              </v-col>
            </v-row>
<!--                3줄---------------------------------------->
            <v-row>
              <v-col cols="12" sm="6">
                <v-row>
                  <v-col cols="12" lg="4">
                    <v-label class="mt-2">국세 환급금 계좌번호</v-label>
                  </v-col>
                  <v-col cols="12" lg="8">
                    <v-text-field v-model="accountNumber"></v-text-field>
                  </v-col>
                </v-row>
              </v-col>
              <v-col cols="12" sm="6">
                <v-row>
                    <v-col cols="12" lg="4">
                      <v-text-field readonly></v-text-field>
                    </v-col>
                </v-row>
              </v-col>
            </v-row>
            <v-row>
              <!-- column 1 -->
              <v-col cols="12" sm="5">
                <!-- row 1 -->
                <v-row>
                  <v-col cols="12" lg="3" md="3" class="pb-md-3 pb-0">
                    <v-label class="mt-2">법인 구분</v-label>
                  </v-col>
                  <v-col cols="12" lg="9" md="9">
                    <v-select
                        v-model="corporationClassifyStatus"
                        :items="['내국', '외국', '외투']"
                        hint="ex) 내국/외국/외국투자 기업"
                        persistent-hint
                        variant="outlined"
                        color="primary"
                        autofocus
                    ></v-select>
                  </v-col>
                </v-row>
                <!-- row 2 -->
                <v-row>
                  <v-col cols="12" lg="3" md="3" class="pb-md-3 pb-0">
                    <v-label class="mt-2">중소기업 여부</v-label>
                  </v-col>
                  <v-col cols="12" lg="9" md="9">
                    <v-select
                        v-model="businessScaleStatus"
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
              <!-- column 2 -->
              <v-col cols="12" sm="7">
                <!-- row 1 -->
                <v-row>
                  <v-col cols="12" lg="3" md="3" class="pb-md-3 pb-0">
                    <v-label class="mt-2">종류별 구분</v-label>
                  </v-col>
                  <v-col cols="12" lg="7" md="9">
                    <v-select
                        v-model="companyTypeStatus"
                        :items="['중소기업', '일반', '상장', '비영리', '협회 등록']"
                        label="ex) 본점"
                        variant="outlined"
                        color="primary"
                        autofocus
                        hint="ex) 중소기업 / 일반 / 비영리 기업 등"
                        persistent-hint
                    ></v-select>
                  </v-col>
                </v-row>
                <!-- row 2 -->
                <v-row>
                  <v-col cols="12" lg="3" md="3" class="pb-md-3 pb-0">
                    <v-label class="mt-2">지방소득세 신고 구분</v-label>
                  </v-col>
                  <v-col cols="12" lg="7" md="9">
                    <v-select
                        v-model="localTaxBillDivisionCode"
                        :items="['개인 (내국인)', '일반', '상장', '비영리', '협회 등록']"
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



          </v-window-item>
          <v-col cols="1" offset="11">
<!--삭제버튼-->
            <v-btn :disabled="!Used" color="error">삭제</v-btn>
          </v-col>
        </v-window>
      </v-card-text>
      </v-form>
    </v-card>


  </UiParentCard>
</template>

<style scoped lang="scss">
.address{
  font-size: small;
}
</style>