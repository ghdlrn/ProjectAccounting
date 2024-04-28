<script setup>
import { ref } from 'vue';
import axios from 'axios';

import UiParentCard from '@/components/shared/UiParentCard.vue';
const tab = ref(null);

const Used = ref(false);
/* ---------------------------정보 제출------------------------------*/
const licenseType = ref('')
const name = ref('')
const headOfficeStatus = ref('')
const paymentHeadOfficeStatus = ref('')

const CompanyRegister = async () => {
  try {
    await axios.post('http://localhost:8080/register/compant-info', {
      licenseType: licenseType.value,
      name: name.value,
      headOfficeStatus: headOfficeStatus.value,
      paymentHeadOfficeStatus: paymentHeadOfficeStatus.value,
    });
  } catch (error) {
    console.error('회사 등록 실패', error);
    alert('회사 등록 실패');
  }
};
</script>

<template>
  <UiParentCard title="회사등록">

    <v-card>
      <v-tabs v-model="tab" bg-color="primary">
        <v-tab value="one">기본 정보</v-tab>
        <v-tab value="two">세무 정보</v-tab>
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
              <v-col cols="12" sm="6">
                <!-- row 1 -->
                <v-row>
                  <v-col cols="12" lg="4" md="3" class="pb-md-3 pb-0">
                    <v-label class="mt-2">사업자 유형</v-label>
                  </v-col>
                  <v-col cols="12" lg="8" md="9">
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
                <v-row class="mt-3">
                  <v-col cols="12" lg="4" md="3" class="pb-md-3 pb-0">
                    <v-label class="mt-2">회사명</v-label>
                  </v-col>
                  <v-col cols="12" lg="8" md="9">
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
              <v-col cols="12" sm="6">
                <!-- row 1 -->
                <v-row>
                  <v-col cols="12" lg="1" md="3" class="pb-md-3 pb-0">
                    <v-label class="mt-2">본점<br />여부</v-label>
                  </v-col>
                  <v-col cols="12" lg="4" md="9">
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
                  <v-col cols="12" lg="4" md="9">
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
                <v-row class="mt-3">
                  <v-col cols="12" lg="4" md="3" class="pb-md-3 pb-0">
                    <v-label class="mt-2">사업자등록번호</v-label>
                  </v-col>
                  <v-col cols="12" lg="8" md="9">
                    <v-text-field
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
              <v-col cols="12" sm="6">
                <!-- row 1 -->
                <v-row>
                  <v-col cols="12" lg="4" md="3" class="pb-md-3 pb-0">
                    <v-label class="mt-2">대표자명</v-label>
                  </v-col>
                  <v-col cols="12" lg="8" md="9">
                    <v-text-field
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
                <v-row class="mt-3">
                  <v-col cols="12" lg="4" md="3" class="pb-md-3 pb-0">
                    <v-label class="mt-2">우편번호</v-label>
                  </v-col>
                  <v-col cols="12" lg="8" md="9">
                    <v-text-field
                        hint="법인 사업자인 경우 법인등록번호"
                        persistent-hint
                        variant="outlined"
                        persistent-placeholder
                        placeholder="ex) 130111-0006246"
                        color="primary">
                    </v-text-field>
                  </v-col>
                </v-row>
                <!-- row 3 -->
              </v-col>
              <!-- column 2 -->
              <v-col cols="12" sm="6">
                <!-- row 1 -->
                <v-row>
                  <v-col cols="12" lg="4" md="3" class="pb-md-3 pb-0">
                    <v-label class="mt-2">법인등록번호</v-label>
                  </v-col>
                  <v-col cols="12" lg="8" md="9">
                    <v-text-field
                        hint="법인 사업자인 경우 법인등록번호"
                        persistent-hint
                        variant="outlined"
                        persistent-placeholder
                        placeholder="ex) 130111-0006246"
                        color="primary">
                    </v-text-field>
                  </v-col>
                </v-row>
                <!-- row 2 -->
                <v-row class="mt-3">
                  <v-col cols="12" lg="4" md="3" class="pb-md-3 pb-0">
                    <v-label class="mt-2">개업연월일</v-label>
                  </v-col>
                  <v-col cols="12" lg="8" md="9">
                    <v-text-field
                        hint="Please enter your Profile URL"
                        persistent-hint
                        variant="outlined"
                        type="password"
                        persistent-placeholder
                        placeholder="Please enter your Profile URL"
                        color="primary">
                    </v-text-field>
                  </v-col>
                </v-row>
              </v-col>
            </v-row>

            <v-row>
              <v-col cols="12" lg="2" md="3" class="pb-md-3 pb-0">
                <v-label class="mt-2">상세 주소</v-label>
              </v-col>
              <v-col cols="12" lg="10" md="9">
                <v-text-field
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
            <v-row>
            <v-col cols="12" sm="6">
              <v-row class="mt-3">
                <v-col cols="12" lg="4" md="3" class="pb-md-3 pb-0">
                  <v-label class="mt-2">업태</v-label>
                </v-col>
                <v-col cols="12" lg="8" md="9">
                  <v-text-field
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
                <!-- row 3 -->

              <!-- column 2 -->
              <v-col cols="12" sm="6">
                <v-row class="mt-3">
                  <v-col cols="12" lg="4" md="3" class="pb-md-3 pb-0">
                    <v-label class="mt-2">종목</v-label>
                  </v-col>
                  <v-col cols="12" lg="8" md="9">
                    <v-text-field
                        hint="Please enter your Profile URL"
                        persistent-hint
                        variant="outlined"
                        type="password"
                        persistent-placeholder
                        placeholder="Please enter your Profile URL"
                        color="primary">
                    </v-text-field>
                  </v-col>
                </v-row>
              </v-col>
            </v-row>
          </v-window-item>
<!--tab2-->
          <v-window-item value="two">
            Two
          </v-window-item>
<!--tab3-->
          <v-window-item value="three">
            Three
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

</style>